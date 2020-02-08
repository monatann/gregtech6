/**
 * Copyright (c) 2019 Gregorius Techneticies
 *
 * This file is part of gregtech6.
 *
 * GregTech is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GregTech is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with gregtech6. If not, see <http://www.gnu.org/licenses/>.
 */

package gregapi6.tileentity.energy;

import static gregapi6.data.CS.*;

import java.util.Collection;
import java.util.List;

import gregapi6.code.ArrayListNoNulls;
import gregapi6.code.TagData;
import gregapi6.data.IL;
import gregapi6.data.LH;
import gregapi6.data.LH.Chat;
import gregapi6.data.TD;
import gregapi6.gui.ContainerClientDefault;
import gregapi6.gui.ContainerCommonDefault;
import gregapi6.item.IItemEnergy;
import gregapi6.tileentity.base.TileEntityBase09FacingSingle;
import gregapi6.tileentity.data.ITileEntityProgress;
import gregapi6.tileentity.machines.ITileEntityRunningActively;
import gregapi6.tileentity.machines.ITileEntitySwitchableMode;
import gregapi6.tileentity.machines.ITileEntitySwitchableOnOff;
import gregapi6.util.ST;
import gregapi6.util.UT;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author Gregorius Techneticies
 */
public abstract class TileEntityBase10EnergyBatBox extends TileEntityBase09FacingSingle implements ITileEntityEnergy, ITileEntityEnergyDataCapacitor, ITileEntityRunningActively, ITileEntitySwitchableOnOff, ITileEntitySwitchableMode, ITileEntityProgress {
	public boolean mEmitsEnergy = F, mStopped = F, mActive = F;
	public long mEnergy = 0, mInput = 32, mOutput = 32;
	public byte mActiveState = 0, mMode = 0;
	public TagData mEnergyType = TD.Energy.QU;
	public TagData mEnergyTypeOut = TD.Energy.QU;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		mEnergy = aNBT.getLong(NBT_ENERGY);
		if (aNBT.hasKey(NBT_MODE)) mMode = aNBT.getByte(NBT_MODE);
		if (aNBT.hasKey(NBT_ACTIVE_ENERGY)) mEmitsEnergy = aNBT.getBoolean(NBT_ACTIVE_ENERGY);
		if (aNBT.hasKey(NBT_STOPPED)) mStopped = aNBT.getBoolean(NBT_STOPPED);
		if (aNBT.hasKey(NBT_ACTIVE)) mActive = aNBT.getBoolean(NBT_ACTIVE);
		if (aNBT.hasKey(NBT_INPUT)) mInput = aNBT.getLong(NBT_INPUT);
		if (aNBT.hasKey(NBT_OUTPUT)) mOutput = aNBT.getLong(NBT_OUTPUT);
		if (aNBT.hasKey(NBT_ENERGY_EMITTED)) mEnergyType = mEnergyTypeOut = TagData.createTagData(aNBT.getString(NBT_ENERGY_EMITTED));
		if (aNBT.hasKey(NBT_ENERGY_ACCEPTED)) mEnergyType = TagData.createTagData(aNBT.getString(NBT_ENERGY_ACCEPTED));
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		if (mMode != 0) aNBT.setByte(NBT_MODE, mMode);
		UT.NBT.setNumber(aNBT, NBT_ENERGY, mEnergy);
		UT.NBT.setBoolean(aNBT, NBT_ACTIVE, mActive);
		UT.NBT.setBoolean(aNBT, NBT_STOPPED, mStopped);
		UT.NBT.setBoolean(aNBT, NBT_ACTIVE_ENERGY, mEmitsEnergy);
	}
	
	static {LH.add("gt6.tooltip.energybattery.1", "Selector Covers can set the amount of emitted Packets");}
	static {LH.add("gt6.tooltip.energybattery.2", "Mode 0 = Emit as much as possible, this is Default");}
	static {LH.add("gt6.tooltip.energybattery.3", "Mode 1 - 15 = Emit up to 1 - 15 Packets if enough Energy Storages");}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		addToolTipsEnergy(aList, aStack, aF3_H);
		aList.add(Chat.DGRAY + LH.get("gt6.tooltip.energybattery.1"));
		aList.add(Chat.DGRAY + LH.get("gt6.tooltip.energybattery.2"));
		aList.add(Chat.DGRAY + LH.get("gt6.tooltip.energybattery.3"));
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	public void addToolTipsEnergy(List<String> aList, ItemStack aStack, boolean aF3_H) {
		LH.addEnergyToolTips(this, aList, mEnergyType, mEnergyTypeOut, getLocalisedInputSide(), getLocalisedOutputSide());
	}
	
	@Override
	public boolean onBlockActivated3(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isServerSide() && isUseableByPlayerGUI(aPlayer)) openGUI(aPlayer);
		return T;
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		if (aIsServerSide) {
			if (COMPAT_EU_ITEM == null || mEnergyType != TD.Energy.EU) {
				switch(UT.Code.bind3(mEnergy / (mInput * getSizeInventory() * 2))) {
				case 0: for (ItemStack tStack : getInventory()) if (ST.valid(tStack) && tStack.getItem() instanceof IItemEnergy) mEnergy += mOutput * ((IItemEnergy)tStack.getItem()).doEnergyExtraction(mEnergyType, tStack, mOutput, 2, this, worldObj, xCoord, yCoord, zCoord, T); break;
				case 1: for (ItemStack tStack : getInventory()) if (ST.valid(tStack) && tStack.getItem() instanceof IItemEnergy) mEnergy += mOutput * ((IItemEnergy)tStack.getItem()).doEnergyExtraction(mEnergyType, tStack, mOutput, 1, this, worldObj, xCoord, yCoord, zCoord, T); break;
				case 6: for (ItemStack tStack : getInventory()) if (ST.valid(tStack) && tStack.getItem() instanceof IItemEnergy) mEnergy -= mInput  * ((IItemEnergy)tStack.getItem()).doEnergyInjection (mEnergyType, tStack, mInput , 1, this, worldObj, xCoord, yCoord, zCoord, T); break;
				case 7: for (ItemStack tStack : getInventory()) if (ST.valid(tStack) && tStack.getItem() instanceof IItemEnergy) mEnergy -= mInput  * ((IItemEnergy)tStack.getItem()).doEnergyInjection (mEnergyType, tStack, mInput , 2, this, worldObj, xCoord, yCoord, zCoord, T); break;
				}
			} else {
				switch(UT.Code.bind3(mEnergy / (mInput * getSizeInventory() * 2))) {
				case 0: for (ItemStack tStack : getInventory()) if (ST.valid(tStack)) {if (tStack.getItem() instanceof IItemEnergy) mEnergy += mOutput * ((IItemEnergy)tStack.getItem()).doEnergyExtraction(mEnergyType, tStack, mOutput, 2, this, worldObj, xCoord, yCoord, zCoord, T); else if (COMPAT_EU_ITEM.is(tStack) && COMPAT_EU_ITEM.provider(tStack)  && !IL.IC2_EnergyCrystal.equal(tStack, T, T) && !IL.IC2_LapotronCrystal.equal(tStack, T, T) && COMPAT_EU_ITEM.insidevolt(tStack, mOutput    / 2, mOutput    * 2)) mEnergy += COMPAT_EU_ITEM.decharge(tStack, mOutput    * 2, T);} break;
				case 1: for (ItemStack tStack : getInventory()) if (ST.valid(tStack)) {if (tStack.getItem() instanceof IItemEnergy) mEnergy += mOutput * ((IItemEnergy)tStack.getItem()).doEnergyExtraction(mEnergyType, tStack, mOutput, 1, this, worldObj, xCoord, yCoord, zCoord, T); else if (COMPAT_EU_ITEM.is(tStack) && COMPAT_EU_ITEM.provider(tStack)  && !IL.IC2_EnergyCrystal.equal(tStack, T, T) && !IL.IC2_LapotronCrystal.equal(tStack, T, T) && COMPAT_EU_ITEM.insidevolt(tStack, mOutput    / 2, mOutput    * 2)) mEnergy += COMPAT_EU_ITEM.decharge(tStack, mOutput       , T);} break;
				case 6: for (ItemStack tStack : getInventory()) if (ST.valid(tStack)) {if (tStack.getItem() instanceof IItemEnergy) mEnergy -= mInput  * ((IItemEnergy)tStack.getItem()).doEnergyInjection (mEnergyType, tStack, mInput , 1, this, worldObj, xCoord, yCoord, zCoord, T); else if (COMPAT_EU_ITEM.is(tStack)                                     && !IL.IC2_EnergyCrystal.equal(tStack, T, T) && !IL.IC2_LapotronCrystal.equal(tStack, T, T) && COMPAT_EU_ITEM.insidevolt(tStack, mInput     / 2, mInput     * 2)) mEnergy -= COMPAT_EU_ITEM.charge  (tStack, mInput        , T);} break;
				case 7: for (ItemStack tStack : getInventory()) if (ST.valid(tStack)) {if (tStack.getItem() instanceof IItemEnergy) mEnergy -= mInput  * ((IItemEnergy)tStack.getItem()).doEnergyInjection (mEnergyType, tStack, mInput , 2, this, worldObj, xCoord, yCoord, zCoord, T); else if (COMPAT_EU_ITEM.is(tStack)                                     && !IL.IC2_EnergyCrystal.equal(tStack, T, T) && !IL.IC2_LapotronCrystal.equal(tStack, T, T) && COMPAT_EU_ITEM.insidevolt(tStack, mInput     / 2, mInput     * 2)) mEnergy -= COMPAT_EU_ITEM.charge  (tStack, mInput     * 2, T);} break;
				}
			}
			
			mActive = (mEnergy >= mOutput);
			
			if (mActive) {
				long tOutput = 0;
				
				if (COMPAT_EU_ITEM == null || mEnergyType != TD.Energy.EU) {
					for (ItemStack tStack : getInventory()) if (ST.valid(tStack) && tStack.getItem() instanceof IItemEnergy && ((IItemEnergy)tStack.getItem()).canEnergyExtraction(mEnergyType, tStack, mOutput)) tOutput++;
				} else {
					for (ItemStack tStack : getInventory()) if (ST.valid(tStack)) {if (tStack.getItem() instanceof IItemEnergy) {if (((IItemEnergy)tStack.getItem()).canEnergyExtraction(mEnergyType, tStack, mOutput)) tOutput++;} else if (COMPAT_EU_ITEM.is(tStack) && COMPAT_EU_ITEM.provider(tStack)) tOutput++;}
				}
				
				if (mMode != 0) tOutput = Math.min(mMode, tOutput);
				if (!mStopped && tOutput > 0) {
					long tEmittedPackets = ITileEntityEnergy.Util.emitEnergyToNetwork(mEnergyTypeOut, mOutput, tOutput, this);
					mEmitsEnergy = (tEmittedPackets > 0);
					mEnergy -= mOutput * tEmittedPackets;
				}
				
				if (mTimer % 600 == 5) doDefaultStructuralChecks();
			}
		}
	}
	
	@Override
	public boolean onTickCheck(long aTimer) {
		if (aTimer % 20 != 0) return super.onTickCheck(aTimer);
		byte tActiveState = mActiveState;
		if (mEnergy < mOutput) {
			mActiveState = 0;
		} else if (mEnergy >= mInput * getSizeInventory() * 15) {
			mActiveState = 1;
		} else {
			mActiveState = 2;
		}
		if (!invempty()) mActiveState |= 4;
		return tActiveState != mActiveState || super.onTickCheck(aTimer);
	}
	
	@Override
	public void setVisualData(byte aData) {
		mActiveState = aData;
	}
	
	@Override
	public long doInject(TagData aEnergyType, byte aSide, long aSize, long aAmount, boolean aDoInject) {
		aSize = Math.abs(aSize);
		if (aSize > getEnergySizeInputMax(aEnergyType, aSide)) {
			if (aDoInject) overcharge(aSize, aEnergyType);
			return aAmount;
		}
		if (mEnergy >= mInput * getSizeInventory() * 16) return 0;
		long tInput = Math.min(mInput * getSizeInventory() * 16 - mEnergy, aSize * aAmount), tConsumed = Math.min(aAmount, (tInput/aSize) + (tInput%aSize!=0?1:0));
		if (aDoInject) mEnergy += tConsumed * aSize;
		return tConsumed;
	}
	
	@Override public Object getGUIClient2(int aGUIID, EntityPlayer aPlayer) {return new ContainerClientDefault(aPlayer.inventory, this, aGUIID);}
	@Override public Object getGUIServer2(int aGUIID, EntityPlayer aPlayer) {return new ContainerCommonDefault(aPlayer.inventory, this, aGUIID);}
	
	@Override public int[] getAccessibleSlotsFromSide2(byte aSide) {return UT.Code.getAscendingArray(getSizeInventory());}
	@Override public boolean canInsertItem2 (int aSlot, ItemStack aStack, byte aSide) {return aStack != null && aStack.getItem() instanceof IItemEnergy && (((IItemEnergy)aStack.getItem()).isEnergyType(mEnergyType, aStack, F) || ((IItemEnergy)aStack.getItem()).isEnergyType(mEnergyType, aStack, T));}
	@Override public boolean canExtractItem2(int aSlot, ItemStack aStack, byte aSide) {return T;}
	
	@Override public boolean isEnergyType                   (TagData aEnergyType, byte aSide, boolean aEmitting) {return aEnergyType == (aEmitting ? mEnergyTypeOut : mEnergyType);}
	@Override public boolean isEnergyCapacitorType          (TagData aEnergyType, byte aSide) {return aEnergyType == mEnergyType;}
	@Override public boolean isEnergyAcceptingFrom          (TagData aEnergyType, byte aSide, boolean aTheoretical) {return                                 (SIDES_INVALID[aSide] || isInput (aSide)) && super.isEnergyAcceptingFrom(aEnergyType, aSide, aTheoretical);}
	@Override public boolean isEnergyEmittingTo             (TagData aEnergyType, byte aSide, boolean aTheoretical) {return (aTheoretical || !mStopped) &&  (SIDES_INVALID[aSide] || isOutput(aSide)) && super.isEnergyEmittingTo   (aEnergyType, aSide, aTheoretical);}
	@Override public long getEnergySizeOutputMin            (TagData aEnergyType, byte aSide) {return mOutput;}
	@Override public long getEnergySizeOutputRecommended    (TagData aEnergyType, byte aSide) {return mOutput;}
	@Override public long getEnergySizeOutputMax            (TagData aEnergyType, byte aSide) {return mOutput;}
	@Override public long getEnergySizeInputRecommended     (TagData aEnergyType, byte aSide) {return mInput;}
	@Override public long getEnergyStored                   (TagData aEnergyType, byte aSide) {long rAmount = 0; for (ItemStack tStack : getInventory()) if (tStack != null && tStack.getItem() instanceof IItemEnergy) rAmount += ((IItemEnergy)tStack.getItem()).getEnergyStored  (aEnergyType, tStack); return rAmount;}
	@Override public long getEnergyCapacity                 (TagData aEnergyType, byte aSide) {long rAmount = 0; for (ItemStack tStack : getInventory()) if (tStack != null && tStack.getItem() instanceof IItemEnergy) rAmount += ((IItemEnergy)tStack.getItem()).getEnergyCapacity(aEnergyType, tStack); return rAmount;}
	@Override public Collection<TagData> getEnergyTypes(byte aSide) {return new ArrayListNoNulls<>(F, mEnergyType, mEnergyTypeOut);}
	@Override public Collection<TagData> getEnergyCapacitorTypes(byte aSide) {return mEnergyType.AS_LIST;}
	
	@Override public long getProgressValue(byte aSide) {return mEnergy;}
	@Override public long getProgressMax(byte aSide) {return mInput * getSizeInventory() * 16;}
	
	@Override public int getInventoryStackLimit() {return 1;}
	@Override public boolean canDrop(int aInventorySlot) {return T;}
	
	@Override public boolean getStateRunningPossible() {return mEnergy > mOutput;}
	@Override public boolean getStateRunningPassively() {return mActive;}
	@Override public boolean getStateRunningActively() {return mEmitsEnergy;}
	@Override public boolean setStateOnOff(boolean aOnOff) {mStopped = !aOnOff; return !mStopped;}
	@Override public boolean getStateOnOff() {return !mStopped;}
	
	@Override public byte setStateMode(byte aMode) {mMode = aMode; return mMode;}
	@Override public byte getStateMode() {return mMode;}
	@Override public byte getVisualData() {return mActiveState;}
	
	public boolean isInput (byte aSide) {return aSide != mFacing;}
	public boolean isOutput(byte aSide) {return aSide == mFacing;}
	public String getLocalisedInputSide () {return LH.get(LH.FACE_ANYBUT_FRONT);}
	public String getLocalisedOutputSide() {return LH.get(LH.FACE_FRONT);}
}
