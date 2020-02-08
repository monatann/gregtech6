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

package gregtech6.tileentity.tanks;

import static gregapi6.data.CS.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import gregapi6.code.HashSetNoNulls;
import gregapi6.code.TagData;
import gregapi6.data.CS.BlocksGT;
import gregapi6.data.FL;
import gregapi6.data.LH;
import gregapi6.data.LH.Chat;
import gregapi6.data.TD;
import gregapi6.fluid.FluidTankGT;
import gregapi6.old.Textures;
import gregapi6.render.BlockTextureDefault;
import gregapi6.render.BlockTextureMulti;
import gregapi6.render.IIconContainer;
import gregapi6.render.ITexture;
import gregapi6.tileentity.base.TileEntityBase09FacingSingle;
import gregapi6.tileentity.energy.ITileEntityEnergy;
import gregapi6.tileentity.machines.ITileEntityRunningActively;
import gregapi6.tileentity.machines.ITileEntitySwitchableOnOff;
import gregapi6.util.UT;
import gregapi6.util.WD;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityPump extends TileEntityBase09FacingSingle implements IFluidHandler, ITileEntityEnergy, ITileEntityRunningActively, ITileEntitySwitchableOnOff {
	protected boolean mStopped = F, mActive = F;
	protected long mEnergy = 0, mInput = 32, mActiveData = 0, mNextCheck = 0;
	protected byte mActiveState = 0, mExplosionPrevention = 0, mDir = 0;
	protected TagData mEnergyType = TD.Energy.RU;
	protected FluidTankGT mTank = new FluidTankGT(16000);
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		mEnergy = aNBT.getLong(NBT_ENERGY);
		if (aNBT.hasKey(NBT_STOPPED)) mStopped = aNBT.getBoolean(NBT_STOPPED);
		if (aNBT.hasKey(NBT_ACTIVE)) mActive = aNBT.getBoolean(NBT_ACTIVE);
		if (aNBT.hasKey(NBT_ACTIVE_DATA)) {mActiveData = aNBT.getLong(NBT_ACTIVE_DATA);}
		if (aNBT.hasKey(NBT_INPUT)) {mInput = aNBT.getLong(NBT_INPUT);}
		if (aNBT.hasKey(NBT_ENERGY_ACCEPTED)) mEnergyType = TagData.createTagData(aNBT.getString(NBT_ENERGY_ACCEPTED));
		mTank.readFromNBT(aNBT, NBT_TANK);
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setNumber(aNBT, NBT_ACTIVE_DATA, mActiveData);
		UT.NBT.setNumber(aNBT, NBT_ENERGY, mEnergy);
		UT.NBT.setBoolean(aNBT, NBT_ACTIVE, mActive);
		UT.NBT.setBoolean(aNBT, NBT_STOPPED, mStopped);
		mTank.writeToNBT(aNBT, NBT_TANK);
	}
	
	static {
		LH.add("gt6.tooltip.pump.1", "Usable to clear large Bodies of Fluid");
		LH.add("gt6.tooltip.pump.2", "Not suitable for making infinite Fluid Sources!");
		LH.add("gt6.tooltip.pump.3", "Use a Drain Cover for that instead!");
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN + LH.get("gt6.tooltip.pump.1"));
		LH.addEnergyToolTips(this, aList, mEnergyType, null, LH.get(LH.FACE_BACK), null);
		aList.add(Chat.ORANGE + LH.get("gt6.tooltip.pump.2"));
		aList.add(Chat.ORANGE + LH.get("gt6.tooltip.pump.3"));
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	public ArrayList<ChunkCoordinates> mCheckList = new ArrayList<>();
	public LinkedList<ChunkCoordinates> mPumpList = new LinkedList<>();
	public HashSetNoNulls<ChunkCoordinates> mChecked = new HashSetNoNulls<>();
	public HashSetNoNulls<Block> mPumpedFluids = new HashSetNoNulls<>();
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		if (aIsServerSide) {
			for (byte tSide : ALL_SIDES_VALID_BUT_AXIS[mFacing]) if (!mTank.isEmpty()) FL.move(mTank, getAdjacentTileEntity(tSide)); else break;
			
			if (mEnergy < 2048 || mStopped) {
				mActive = F;
			} else {
				mActive = T;
				if (mCheckList.isEmpty()) {
					if (--mNextCheck < 0 || (mPumpList.isEmpty() && aTimer % 200 == 10)) {
						scanForFluid(getOffsetX(mFacing), getOffsetY(mFacing), getOffsetZ(mFacing));
					} else {
						if (mTank.isEmpty()) {
							mIgnoreUnloadedChunks = F;
							while (!mPumpList.isEmpty() && !drainFluid(mPumpList.removeLast())) {/*Do nothing*/}
							mIgnoreUnloadedChunks = T;
							if (mPumpList.isEmpty()) mNextCheck = 10;
						}
					}
				} else {
					scanForFluid(getOffsetX(mFacing), getOffsetZ(mFacing));
				}
			}
			
			if (mTimer % 600 == 5) if (mActiveData != 0) doDefaultStructuralChecks(); else if (mExplosionPrevention > 0) mExplosionPrevention--;
		}
	}
	
	private void scanForFluid(int aX, int aZ) {
		ChunkCoordinates[] tNeedsToBeChecked = mCheckList.toArray(ZL_COORDS);
		mCheckList.clear();
		
		for (ChunkCoordinates tPos : tNeedsToBeChecked) {
			if (mDir != 0 && mPumpedFluids.contains(getBlock(tPos.posX, tPos.posY + mDir, tPos.posZ))) {
				mPumpList = new LinkedList<>();
				mCheckList.clear();
				mChecked.clear();
				mNextCheck = 1000;
				addToList(tPos.posX, tPos.posY + mDir, tPos.posZ);
				return;
			}
			if (tPos.posX < aX + 64) addToList(tPos.posX + 1, tPos.posY, tPos.posZ);
			if (tPos.posX > aX - 64) addToList(tPos.posX - 1, tPos.posY, tPos.posZ);
			if (tPos.posZ < aZ + 64) addToList(tPos.posX, tPos.posY, tPos.posZ + 1);
			if (tPos.posZ > aZ - 64) addToList(tPos.posX, tPos.posY, tPos.posZ - 1);
		}
		
		mNextCheck = mPumpList.size() * 256;
	}
	
	private void scanForFluid(int aX, int aY, int aZ) {
		mPumpList = new LinkedList<>();
		mCheckList.clear();
		mChecked.clear();
		mNextCheck = 1000;
		
		mPumpedFluids.clear();
		Block aBlock = getBlockAtSide(mFacing);
		if (aBlock == Blocks.lava || aBlock == Blocks.flowing_lava) {
			mPumpedFluids.add(Blocks.lava);
			mPumpedFluids.add(Blocks.flowing_lava);
			mDir = +1;
		} else
		if (aBlock == Blocks.water || aBlock == Blocks.flowing_water || aBlock == BlocksGT.River) {
			mPumpedFluids.add(Blocks.water);
			mPumpedFluids.add(Blocks.flowing_water);
			mPumpedFluids.add(BlocksGT.River);
			mDir = +1;
		} else
		if (aBlock instanceof IFluidBlock) {
			mPumpedFluids.add(aBlock);
			mDir = (byte)(FL.lighter(((IFluidBlock)aBlock).getFluid()) ? -1 : +1);
		} else return;
		
		addToList(aX, aY, aZ);
	}
	
	private boolean addToList(int aX, int aY, int aZ) {
		ChunkCoordinates tCoordinate = new ChunkCoordinates(aX, aY, aZ);
		if (mChecked.add(tCoordinate)) {
			if (mPumpedFluids.contains(getBlock(aX, aY, aZ))) {
				mPumpList.add(tCoordinate);
				mCheckList.add(tCoordinate);
				return T;
			}
		}
		return F;
	}
	
	private boolean drainFluid(ChunkCoordinates aCoords) {
		Block aBlock = getBlock(aCoords);
		byte aMeta = getMetaData(aCoords);
		if (mPumpedFluids.contains(aBlock)) {
			if (aBlock == Blocks.water || aBlock == Blocks.flowing_water || aBlock == BlocksGT.River) {
				if (aMeta == 0) {
					if (mTank.fillAll(FL.Water.make(1000))) {
						mEnergy -= 2048;
						worldObj.setBlock(aCoords.posX, aCoords.posY, aCoords.posZ, NB, 0, 2);
						if (mPumpedFluids.contains(worldObj.getBlock(aCoords.posX+1, aCoords.posY  , aCoords.posZ  ))
										&& worldObj.getBlockMetadata(aCoords.posX+1, aCoords.posY  , aCoords.posZ  ) > 0)
												   worldObj.setBlock(aCoords.posX+1, aCoords.posY  , aCoords.posZ  , NB, 0, 2);
						if (mPumpedFluids.contains(worldObj.getBlock(aCoords.posX  , aCoords.posY+1, aCoords.posZ  ))
										&& worldObj.getBlockMetadata(aCoords.posX  , aCoords.posY+1, aCoords.posZ  ) > 0)
												   worldObj.setBlock(aCoords.posX  , aCoords.posY+1, aCoords.posZ  , NB, 0, 2);
						if (mPumpedFluids.contains(worldObj.getBlock(aCoords.posX  , aCoords.posY  , aCoords.posZ+1))
										&& worldObj.getBlockMetadata(aCoords.posX  , aCoords.posY  , aCoords.posZ+1) > 0)
												   worldObj.setBlock(aCoords.posX  , aCoords.posY  , aCoords.posZ+1, NB, 0, 2);
						if (mPumpedFluids.contains(worldObj.getBlock(aCoords.posX-1, aCoords.posY  , aCoords.posZ  ))
										&& worldObj.getBlockMetadata(aCoords.posX-1, aCoords.posY  , aCoords.posZ  ) > 0)
												   worldObj.setBlock(aCoords.posX-1, aCoords.posY  , aCoords.posZ  , NB, 0, 2);
						if (mPumpedFluids.contains(worldObj.getBlock(aCoords.posX  , aCoords.posY-1, aCoords.posZ  ))
										&& worldObj.getBlockMetadata(aCoords.posX  , aCoords.posY-1, aCoords.posZ  ) > 0)
												   worldObj.setBlock(aCoords.posX  , aCoords.posY-1, aCoords.posZ  , NB, 0, 2);
						if (mPumpedFluids.contains(worldObj.getBlock(aCoords.posX  , aCoords.posY  , aCoords.posZ-1))
										&& worldObj.getBlockMetadata(aCoords.posX  , aCoords.posY  , aCoords.posZ-1) > 0)
												   worldObj.setBlock(aCoords.posX  , aCoords.posY  , aCoords.posZ-1, NB, 0, 2);
						return T;
					}
					return F;
				}
				mEnergy -= 128;
				worldObj.setBlock(aCoords.posX, aCoords.posY, aCoords.posZ, NB, 0, 2);
				return T;
			}
			if (aBlock == Blocks.lava || aBlock == Blocks.flowing_lava) {
				if (aMeta == 0) {
					if (mTank.fillAll(FL.Lava.make(1000))) {
						mEnergy -= 2048;
						worldObj.setBlock(aCoords.posX, aCoords.posY, aCoords.posZ, NB, 0, 2);
						if (mPumpedFluids.contains(worldObj.getBlock(aCoords.posX+1, aCoords.posY  , aCoords.posZ  ))
										&& worldObj.getBlockMetadata(aCoords.posX+1, aCoords.posY  , aCoords.posZ  ) > 0)
												   worldObj.setBlock(aCoords.posX+1, aCoords.posY  , aCoords.posZ  , NB, 0, 2);
						if (mPumpedFluids.contains(worldObj.getBlock(aCoords.posX  , aCoords.posY+1, aCoords.posZ  ))
										&& worldObj.getBlockMetadata(aCoords.posX  , aCoords.posY+1, aCoords.posZ  ) > 0)
												   worldObj.setBlock(aCoords.posX  , aCoords.posY+1, aCoords.posZ  , NB, 0, 2);
						if (mPumpedFluids.contains(worldObj.getBlock(aCoords.posX  , aCoords.posY  , aCoords.posZ+1))
										&& worldObj.getBlockMetadata(aCoords.posX  , aCoords.posY  , aCoords.posZ+1) > 0)
												   worldObj.setBlock(aCoords.posX  , aCoords.posY  , aCoords.posZ+1, NB, 0, 2);
						if (mPumpedFluids.contains(worldObj.getBlock(aCoords.posX-1, aCoords.posY  , aCoords.posZ  ))
										&& worldObj.getBlockMetadata(aCoords.posX-1, aCoords.posY  , aCoords.posZ  ) > 0)
												   worldObj.setBlock(aCoords.posX-1, aCoords.posY  , aCoords.posZ  , NB, 0, 2);
						if (mPumpedFluids.contains(worldObj.getBlock(aCoords.posX  , aCoords.posY-1, aCoords.posZ  ))
										&& worldObj.getBlockMetadata(aCoords.posX  , aCoords.posY-1, aCoords.posZ  ) > 0)
												   worldObj.setBlock(aCoords.posX  , aCoords.posY-1, aCoords.posZ  , NB, 0, 2);
						if (mPumpedFluids.contains(worldObj.getBlock(aCoords.posX  , aCoords.posY  , aCoords.posZ-1))
										&& worldObj.getBlockMetadata(aCoords.posX  , aCoords.posY  , aCoords.posZ-1) > 0)
												   worldObj.setBlock(aCoords.posX  , aCoords.posY  , aCoords.posZ-1, NB, 0, 2);
						return T;
					}
					return F;
				}
				mEnergy -= 128;
				worldObj.setBlock(aCoords.posX, aCoords.posY, aCoords.posZ, NB, 0, 2);
				return T;
			}
			if (aBlock instanceof IFluidBlock) {
				FluidStack tDrained = ((IFluidBlock)aBlock).drain(worldObj, aCoords.posX, aCoords.posY, aCoords.posZ, F);
				if (tDrained == null) {
					mEnergy -= 128;
					worldObj.setBlock(aCoords.posX, aCoords.posY, aCoords.posZ, NB, 0, 2);
					return T;
				}
				mEnergy -= UT.Code.units(tDrained.amount, 1000, 2048, T);
				mTank.fill(tDrained, T);
				worldObj.setBlock(aCoords.posX, aCoords.posY, aCoords.posZ, NB, 0, 2);
				return T;
			}
			mEnergy -= 128;
			worldObj.setBlock(aCoords.posX, aCoords.posY, aCoords.posZ, NB, 0, 2);
			return T;
		}
		return F;
	}
	
	@Override
	public boolean onTickCheck(long aTimer) {
		mActiveData <<= 1;
		if (mActive) mActiveData |= 1;
		byte tActiveState = mActiveState;
		if (mActiveData == 0 || mStopped) mActiveState = 0; else if (mActiveData == ~0L) mActiveState = 1; else mActiveState = 2;
		return tActiveState != mActiveState || super.onTickCheck(aTimer);
	}
	
	@Override
	public void setVisualData(byte aData) {
		mActiveState = aData;
	}
	
	@Override
	protected IFluidTank getFluidTankDrainable2(byte aSide, FluidStack aFluidToDrain) {
		return mTank;
	}
	
	@Override
	protected IFluidTank[] getFluidTanks2(byte aSide) {
		return mTank.AS_ARRAY;
	}
	
	@Override
	public long doInject(TagData aEnergyType, byte aSide, long aSize, long aAmount, boolean aDoInject) {
		aSize = Math.abs(aSize);
		if (aSize > getEnergySizeInputMax(aEnergyType, aSide)) {
			if (aDoInject) overload(aSize, aEnergyType);
			return aAmount;
		}
		if (mEnergy >= 8192) return 0;
		long tInput = Math.min(8192 - mEnergy, aSize * aAmount), tConsumed = Math.min(aAmount, (tInput/aSize) + (tInput%aSize!=0?1:0));
		if (aDoInject) mEnergy += tConsumed * aSize;
		return tConsumed;
	}
	
	public void overload(long aSize, TagData aEnergyType) {
		if (mExplosionPrevention < 100) {
			if (mTimer < 100) DEB.println("Machine overloaded on startup with: " + aSize + " " + aEnergyType.getLocalisedNameLong());
			mExplosionPrevention++;
			mEnergy = 0;
		} else {
			overcharge(aSize, aEnergyType);
		}
	}
	
	@Override public boolean isEnergyType                   (TagData aEnergyType, byte aSide, boolean aEmitting) {return !aEmitting && aEnergyType == mEnergyType;}
	@Override public boolean isEnergyAcceptingFrom          (TagData aEnergyType, byte aSide, boolean aTheoretical) {return (aTheoretical || !mStopped) && (SIDES_INVALID[aSide] || aSide == OPPOSITES[mFacing]) && super.isEnergyAcceptingFrom(aEnergyType, aSide, aTheoretical);}
	@Override public long getEnergySizeInputRecommended     (TagData aEnergyType, byte aSide) {return mInput;}
	@Override public Collection<TagData> getEnergyTypes(byte aSide) {return mEnergyType.AS_LIST;}
	
	@Override public boolean canDrop(int aInventorySlot) {return F;}
	@Override public void onFacingChange(byte aPreviousFacing) {mNextCheck = 20; mChecked.clear(); mCheckList.clear(); mPumpList.clear(); mPumpedFluids.clear();}
	
	@Override public boolean getStateRunningPossible() {return !mPumpList.isEmpty() || WD.liquid(getBlockAtSide(mFacing));}
	@Override public boolean getStateRunningPassively() {return mActiveData != 0;}
	@Override public boolean getStateRunningActively() {return mActiveData != 0 && !mPumpedFluids.isEmpty();}
	@Override public boolean setStateOnOff(boolean aOnOff) {mStopped = !aOnOff; return !mStopped;}
	@Override public boolean getStateOnOff() {return !mStopped;}
	
	@Override public byte getVisualData() {return mActiveState;}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		if (!aShouldSideBeRendered[aSide]) return null;
		int aIndex = aSide==mFacing?0:aSide==OPPOSITES[mFacing]?1:2;
		return BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[aIndex], mRGBa), BlockTextureDefault.get((mActiveState>0?sOverlaysActive:sOverlays)[aIndex]));
	}
	
	// Icons
	public static IIconContainer sColoreds[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/pump/rotation/colored/front"),
		new Textures.BlockIcons.CustomIcon("machines/pump/rotation/colored/back"),
		new Textures.BlockIcons.CustomIcon("machines/pump/rotation/colored/side"),
	}, sOverlays[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/pump/rotation/overlay/front"),
		new Textures.BlockIcons.CustomIcon("machines/pump/rotation/overlay/back"),
		new Textures.BlockIcons.CustomIcon("machines/pump/rotation/overlay/side"),
	}, sOverlaysActive[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/pump/rotation/overlay_active/front"),
		new Textures.BlockIcons.CustomIcon("machines/pump/rotation/overlay_active/back"),
		new Textures.BlockIcons.CustomIcon("machines/pump/rotation/overlay_active/side"),
	};
	
	@Override public String getTileEntityName() {return "gt6.multitileentity.pump.rotation";}
}
