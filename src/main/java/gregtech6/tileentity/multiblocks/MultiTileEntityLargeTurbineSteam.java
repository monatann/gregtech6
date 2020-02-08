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

package gregtech6.tileentity.multiblocks;

import static gregapi6.data.CS.*;

import java.util.List;

import gregapi6.data.FL;
import gregapi6.data.LH;
import gregapi6.data.LH.Chat;
import gregapi6.fluid.FluidTankGT;
import gregapi6.tileentity.multiblocks.ITileEntityMultiBlockController;
import gregapi6.tileentity.multiblocks.MultiTileEntityMultiBlockPart;
import gregapi6.util.UT;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityLargeTurbineSteam extends MultiTileEntityLargeTurbine {
	public FluidTankGT[] mTanks = new FluidTankGT[] {new FluidTankGT(Integer.MAX_VALUE), new FluidTankGT(Integer.MAX_VALUE)};
	public long mSteamCounter = 0, mEnergyProducedNextTick = 0; 
	public static final int STEAM_PER_WATER = 170;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_ENERGY_SU)) mSteamCounter = aNBT.getLong(NBT_ENERGY_SU);
		if (aNBT.hasKey(NBT_OUTPUT_SU)) mEnergyProducedNextTick = aNBT.getLong(NBT_OUTPUT_SU);

		for (int i = 0; i < mTanks.length; i++) mTanks[i].readFromNBT(aNBT, NBT_TANK+"."+i);
		mTanks[0].setCapacity(mEnergyIN.mMax*4);
		mTanks[1].setCapacity(mEnergyIN.mMax).setVoidExcess();
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setNumber(aNBT, NBT_ENERGY_SU, mSteamCounter);
		UT.NBT.setNumber(aNBT, NBT_OUTPUT_SU, mEnergyProducedNextTick);
		for (int i = 0; i < mTanks.length; i++) mTanks[i].writeToNBT(aNBT, NBT_TANK+"."+i);
	}
	
	@Override
	public boolean checkStructure2() {
		int
		tMinX = xCoord-(SIDE_X_NEG==mFacing?0:SIDE_X_POS==mFacing?3:1),
		tMinY = yCoord-(SIDE_Y_NEG==mFacing?0:SIDE_Y_POS==mFacing?3:1),
		tMinZ = zCoord-(SIDE_Z_NEG==mFacing?0:SIDE_Z_POS==mFacing?3:1),
		tMaxX = xCoord+(SIDE_X_POS==mFacing?0:SIDE_X_NEG==mFacing?3:1),
		tMaxY = yCoord+(SIDE_Y_POS==mFacing?0:SIDE_Y_NEG==mFacing?3:1),
		tMaxZ = zCoord+(SIDE_Z_POS==mFacing?0:SIDE_Z_NEG==mFacing?3:1),
		tOutX = getOffsetXN(mFacing, 3),
		tOutY = getOffsetYN(mFacing, 3),
		tOutZ = getOffsetZN(mFacing, 3);
		
		if (worldObj.blockExists(tMinX, tMinY, tMinZ) && worldObj.blockExists(tMaxX, tMaxY, tMaxZ)) {
			mEmitter = null;
			boolean tSuccess = T;
			for (int tX = tMinX; tX <= tMaxX; tX++) for (int tY = tMinY; tY <= tMaxY; tY++) for (int tZ = tMinZ; tZ <= tMaxZ; tZ++) {
				int tBits = 0;
				if (tX == tOutX && tY == tOutY && tZ == tOutZ) {
					tBits = MultiTileEntityMultiBlockPart.ONLY_ENERGY_OUT;
				} else {
					if (SIDES_AXIS_X[mFacing] && tX == xCoord || SIDES_AXIS_Y[mFacing] && tY == yCoord || SIDES_AXIS_Z[mFacing] && tZ == zCoord) {
						tBits = (tY == tMinY ? MultiTileEntityMultiBlockPart.ONLY_FLUID : MultiTileEntityMultiBlockPart.ONLY_FLUID_IN);
					} else {
						tBits = (tY == tMinY ? MultiTileEntityMultiBlockPart.ONLY_FLUID_OUT : MultiTileEntityMultiBlockPart.NOTHING);
					}
				}
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX, tY, tZ, mTurbineWalls, getMultiTileEntityRegistryID(), tX == tOutX && tY == tOutY && tZ == tOutZ ? 3 : 0, tBits)) tSuccess = F;
			}
			return tSuccess;
		}
		return mStructureOkay;
	}
	
	static {
		LH.add("gt6.tooltip.multiblock.steamturbine.1", "3x3x4 of the Block you crafted this one with");
		LH.add("gt6.tooltip.multiblock.steamturbine.2", "Main centered on the 3x3 facing outwards");
		LH.add("gt6.tooltip.multiblock.steamturbine.3", "Input only possible at frontal 3x3");
		LH.add("gt6.tooltip.multiblock.steamturbine.4", "Distilled Water can be pumped out at Bottom Layer");
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get(LH.STRUCTURE) + ":");
		aList.add(Chat.WHITE    + LH.get("gt6.tooltip.multiblock.steamturbine.1"));
		aList.add(Chat.WHITE    + LH.get("gt6.tooltip.multiblock.steamturbine.2"));
		aList.add(Chat.WHITE    + LH.get("gt6.tooltip.multiblock.steamturbine.3"));
		aList.add(Chat.WHITE    + LH.get("gt6.tooltip.multiblock.steamturbine.4"));
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	@Override
	public void addToolTipsEnergy(List<String> aList, ItemStack aStack, boolean aF3_H) {
		super.addToolTipsEnergy(aList, aStack, aF3_H);
		aList.add(Chat.ORANGE   + LH.get(LH.EMITS_USED_STEAM) + " ("+LH.get(LH.FACE_SIDES)+", 95%)");
	}
	
	@Override
	public void doConversion(long aTimer) {
		if (mEnergyProducedNextTick > 0) {
			mStorage.mEnergy += mEnergyProducedNextTick;
			mEnergyProducedNextTick = 0;
		} else if (!mStopped && mTanks[0].has(getEnergySizeInputMin(mEnergyIN.mType, SIDE_ANY) * 2)) {
			long tSteam = mTanks[0].amount();
			mSteamCounter += tSteam;
			mStorage.mEnergy += tSteam / 2;
			mEnergyProducedNextTick += tSteam / 2;
			mTanks[0].setEmpty();
			if (mSteamCounter >= STEAM_PER_WATER) {
				mTanks[1].fillAll(FL.DistW.make(mSteamCounter / STEAM_PER_WATER));
				mSteamCounter %= STEAM_PER_WATER;
			}
		}
		super.doConversion(aTimer);
	}
	
	@Override protected IFluidTank getFluidTankFillable2(byte aSide, FluidStack aFluidToFill) {return !mStopped && FL.steam(aFluidToFill) ? mTanks[0] : null;}
	@Override protected IFluidTank getFluidTankDrainable2(byte aSide, FluidStack aFluidToDrain) {return mTanks[1];}
	@Override protected IFluidTank[] getFluidTanks2(byte aSide) {return mTanks;}
	
	@Override public String getTileEntityName() {return "gt6.multitileentity.multiblock.turbine.steam";}
}
