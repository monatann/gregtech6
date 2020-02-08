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

package gregapi6.tileentity.connectors;

import static gregapi6.data.CS.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_GetDebugInfo;
import gregapi6.code.ArrayListNoNulls;
import gregapi6.code.HashSetNoNulls;
import gregapi6.code.TagData;
import gregapi6.data.CS.SFX;
import gregapi6.data.LH;
import gregapi6.data.LH.Chat;
import gregapi6.data.MT;
import gregapi6.data.TD;
import gregapi6.old.Textures;
import gregapi6.oredict.OreDictMaterial;
import gregapi6.render.BlockTextureDefault;
import gregapi6.render.ITexture;
import gregapi6.tileentity.ITileEntityQuickObstructionCheck;
import gregapi6.tileentity.data.ITileEntityProgress;
import gregapi6.tileentity.delegate.DelegatorTileEntity;
import gregapi6.tileentity.energy.ITileEntityEnergy;
import gregapi6.tileentity.energy.ITileEntityEnergyDataConductor;
import gregapi6.util.UT;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityAxle extends TileEntityBase11ConnectorStraight implements ITileEntityQuickObstructionCheck, ITileEntityEnergy, ITileEntityEnergyDataConductor, ITileEntityProgress, IMTE_GetDebugInfo {
	public long mTransferredPower = 0, mTransferredSpeed = 0, mTransferredEnergy = 0, mTransferredLast = 0, mPower = 1, mSpeed = 32;
	public byte mRotationDir = 0, oRotationDir = 0;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_ACTIVE_DATA)) mRotationDir = aNBT.getByte(NBT_ACTIVE_DATA);
		if (aNBT.hasKey(NBT_PIPESIZE)) mSpeed = Math.max(1, aNBT.getLong(NBT_PIPESIZE));
		if (aNBT.hasKey(NBT_PIPEBANDWIDTH)) mPower = Math.max(1, aNBT.getLong(NBT_PIPEBANDWIDTH));
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		aNBT.setByte(NBT_ACTIVE_DATA, mRotationDir);
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN + LH.get(LH.AXLE_STATS_SPEED) + mSpeed + " " + TD.Energy.RU.getLocalisedNameShort());
		aList.add(Chat.CYAN + LH.get(LH.AXLE_STATS_POWER) + mPower);
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		super.onTick2(aTimer, aIsServerSide);
		
		if (aIsServerSide) {
			if (mTransferredSpeed == 0 && aTimer > 5) mRotationDir = 0;
			mTransferredLast = mTransferredEnergy;
			mTransferredEnergy = mTransferredSpeed = mTransferredPower = 0;
		}
	}
	
	@Override public boolean onTickCheck(long aTimer) {return mRotationDir != oRotationDir || super.onTickCheck(aTimer);}
	@Override public void onTickResetChecks(long aTimer, boolean aIsServerSide) {super.onTickResetChecks(aTimer, aIsServerSide); oRotationDir = mRotationDir;}
	@Override public void setVisualData(byte aData) {mRotationDir = aData;}
	@Override public byte getVisualData() {return mRotationDir;}
	
	public long transferRotations(byte aSide, long aSpeed, long aPower, long aChannel, HashSetNoNulls<TileEntity> aAlreadyPassed) {
		if (mTimer < 1) return 0;
		
		// Replaced a switch/case with "simple" Math.
		// Sides pointing to the negative Axis direction are ?1:2, while the positive direction is ?2:1
		// Abusing the Fact that negative is always even and positive is always odd.
		mRotationDir = (byte)(aSpeed < 0 ? 1+(aSide & 1) : 2-(aSide & 1));
		if (oRotationDir == 0) return addToEnergyTransferred(aSpeed, aPower, aPower);
		
		if (!canEmitEnergyTo(OPPOSITES[aSide])) return addToEnergyTransferred(aSpeed, aPower, 0);
		DelegatorTileEntity<TileEntity> tDelegator = getAdjacentTileEntity(OPPOSITES[aSide]);
		return addToEnergyTransferred(aSpeed, aPower, aAlreadyPassed.add(tDelegator.mTileEntity) ? tDelegator.mTileEntity instanceof MultiTileEntityAxle ? ((MultiTileEntityAxle)tDelegator.mTileEntity).isEnergyAcceptingFrom(TD.Energy.RU, tDelegator.mSideOfTileEntity, F) ? ((MultiTileEntityAxle)tDelegator.mTileEntity).transferRotations(tDelegator.mSideOfTileEntity, aSpeed, aPower, aChannel, aAlreadyPassed) : 0 : ITileEntityEnergy.Util.insertEnergyInto(TD.Energy.RU, aSpeed, aPower, this, tDelegator) : 0);
	}
	
	public long addToEnergyTransferred(long aSpeed, long aOriginalPower, long aPower) {
		mTransferredSpeed += aSpeed;
		mTransferredPower += aPower;
		mTransferredEnergy += Math.abs(aSpeed * aPower);
		// Yes Rotation Speed only becomes a problem when it is actually being transferred,
		// If the Axle just Rotates Idle then it can spin at ludicrous Speeds.
		if (Math.abs(aSpeed) > mSpeed || mTransferredPower > mPower) {
			UT.Sounds.send(SFX.MC_BREAK, this);
			popOff();
			return aOriginalPower;
		}
		return aPower;
	}
	
	@Override
	public boolean canConnect(byte aSide, DelegatorTileEntity<TileEntity> aDelegator) {
		if (aDelegator.mTileEntity instanceof ITileEntityEnergy) return ((ITileEntityEnergy)aDelegator.mTileEntity).isEnergyAcceptingFrom(TD.Energy.RU, aDelegator.mSideOfTileEntity, T) || ((ITileEntityEnergy)aDelegator.mTileEntity).isEnergyEmittingTo(TD.Energy.RU, aDelegator.mSideOfTileEntity, T);
		return F;
	}
	
	@Override public boolean isEnergyType(TagData aEnergyType, byte aSide, boolean aEmitting) {return aEnergyType == TD.Energy.RU;}
	@Override public Collection<TagData> getEnergyTypes(byte aSide) {return TD.Energy.RU.AS_LIST;}
	
	@Override public boolean isEnergyEmittingTo   (TagData aEnergyType, byte aSide, boolean aTheoretical) {return isEnergyType(aEnergyType, aSide, T) && canEmitEnergyTo    (aSide);}
	@Override public boolean isEnergyAcceptingFrom(TagData aEnergyType, byte aSide, boolean aTheoretical) {return isEnergyType(aEnergyType, aSide, F) && canAcceptEnergyFrom(aSide);}
	@Override public synchronized long doEnergyExtraction(TagData aEnergyType, byte aSide, long aSize, long aAmount, boolean aDoExtract) {return 0;}
	@Override public synchronized long doEnergyInjection (TagData aEnergyType, byte aSide, long aSize, long aAmount, boolean aDoInject ) {return aSize != 0 && isEnergyAcceptingFrom(aEnergyType, aSide, F) ? aDoInject ? transferRotations(aSide, aSize, aAmount, -1, new HashSetNoNulls<TileEntity>(F, this)) : aAmount : 0;}
	@Override public long getEnergySizeOutputRecommended(TagData aEnergyType, byte aSide) {return mSpeed;}
	@Override public long getEnergySizeOutputMin(TagData aEnergyType, byte aSide) {return 0;}
	@Override public long getEnergySizeOutputMax(TagData aEnergyType, byte aSide) {return mSpeed;}
	@Override public long getEnergySizeInputRecommended(TagData aEnergyType, byte aSide) {return mSpeed;}
	@Override public long getEnergySizeInputMin(TagData aEnergyType, byte aSide) {return 0;}
	@Override public long getEnergySizeInputMax(TagData aEnergyType, byte aSide) {return mSpeed;}
	
	@Override public boolean canDrop(int aInventorySlot) {return F;}
	@Override public boolean isObstructingBlockAt(byte aSide) {return T;}
	
	@Override public boolean isEnergyConducting(TagData aEnergyType) {return aEnergyType == TD.Energy.RU;}
	@Override public long getEnergyMaxSize(TagData aEnergyType) {return aEnergyType == TD.Energy.RU ? mSpeed : 0;}
	@Override public long getEnergyMaxPackets(TagData aEnergyType) {return aEnergyType == TD.Energy.RU ? mPower : 0;}
	@Override public long getEnergyLossPerMeter(TagData aEnergyType) {return 0;}
	@Override public OreDictMaterial getEnergyConductorMaterial() {return mMaterial;}
	@Override public OreDictMaterial getEnergyConductorInsulation() {return MT.NULL;}
	
	public boolean canEmitEnergyTo                          (byte aSide) {return connected(aSide);}
	public boolean canAcceptEnergyFrom                      (byte aSide) {return connected(aSide);}
	
	@Override public long getProgressValue                  (byte aSide) {return mTransferredPower;}
	@Override public long getProgressMax                    (byte aSide) {return mPower;}
	
	@Override public ArrayList<String> getDebugInfo(int aScanLevel) {return aScanLevel > 0 ? new ArrayListNoNulls<>(F, "Transferred Power: " + mTransferredEnergy) : null;}
	
	@Override public ITexture getTextureSide                (byte aSide, byte aConnections, float aDiameter, int aRenderPass) {return BlockTextureDefault.get(Textures.BlockIcons.AXLES[(mConnections & 12) != 0 ? 0 : (mConnections & 48) != 0 ? 2 : 1][aSide][mRotationDir], mRGBa);}
	@Override public ITexture getTextureConnected           (byte aSide, byte aConnections, float aDiameter, int aRenderPass) {return BlockTextureDefault.get(Textures.BlockIcons.AXLES[(mConnections & 12) != 0 ? 0 : (mConnections & 48) != 0 ? 2 : 1][aSide][mRotationDir], mRGBa);}
	
	@Override public Collection<TagData> getConnectorTypes  (byte aSide) {return TD.Connectors.AXLE_ROTATION.AS_LIST;}
	
	@Override public String getFacingTool                   () {return TOOL_wrench;}
	
	@Override public String getTileEntityName               () {return "gt6.multitileentity.connector.axle.rotation";}
}
