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

import gregapi6.data.LH;
import gregapi6.data.LH.Chat;
import gregapi6.tileentity.delegate.DelegatorTileEntity;
import gregapi6.tileentity.energy.ITileEntityEnergy;
import gregapi6.tileentity.machines.ITileEntityAdjacentOnOff;
import gregapi6.tileentity.multiblocks.ITileEntityMultiBlockController;
import gregapi6.tileentity.multiblocks.MultiTileEntityMultiBlockPart;
import gregapi6.tileentity.multiblocks.TileEntityBase10MultiBlockMachine;
import gregapi6.util.WD;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.IFluidHandler;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntitySluice extends TileEntityBase10MultiBlockMachine {
	@Override
	public boolean checkStructure2() {
		int
		tMinX = xCoord-(SIDE_X_NEG==mFacing?0:SIDE_X_POS==mFacing?6:1),
		tMinZ = zCoord-(SIDE_Z_NEG==mFacing?0:SIDE_Z_POS==mFacing?6:1),
		tMaxX = xCoord+(SIDE_X_POS==mFacing?0:SIDE_X_NEG==mFacing?6:1),
		tMaxZ = zCoord+(SIDE_Z_POS==mFacing?0:SIDE_Z_NEG==mFacing?6:1),
		tD = (mActive?mFacing+2:mFacing-2);

		int[] blockId = {18013, 18106};
		int[] inOutOption = {MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_OUT, MultiTileEntityMultiBlockPart.NOTHING, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_IN};

		if (worldObj.blockExists(tMinX, yCoord, tMinZ) && worldObj.blockExists(tMaxX, yCoord+2, tMaxZ)) {
			boolean tSuccess = T;
			for (int tX = tMinX; tX <= tMaxX; tX++) for (int tZ = tMinZ; tZ <= tMaxZ; tZ++) {
				if (SIDES_AXIS_X[mFacing] ? tX == xCoord : tZ == zCoord) {
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX, yCoord  , tZ, blockId[0], getMultiTileEntityRegistryID(), 1, inOutOption[0])) tSuccess = F;
				} else {
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX, yCoord  , tZ, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[1])) tSuccess = F;
				}

				if (SIDES_AXIS_X[mFacing] ? Math.abs(tX-xCoord)==5 && tZ != zCoord : Math.abs(tZ-zCoord)==5 && tX != xCoord) {
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX, yCoord+1, tZ, blockId[0], getMultiTileEntityRegistryID(), 3, inOutOption[2])) tSuccess = F;
				} else {
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX, yCoord+1, tZ, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[1])) tSuccess = F;
				}

				if (SIDES_AXIS_X[mFacing] ? Math.abs(tX-xCoord)==6 : Math.abs(tZ-zCoord)==6) {
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX, yCoord+2, tZ, blockId[1], getMultiTileEntityRegistryID(),tD, inOutOption[3])) tSuccess = F;
				} else {
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX, yCoord+2, tZ, blockId[1], getMultiTileEntityRegistryID(),tD, inOutOption[1])) tSuccess = F;
				}
			}
			return tSuccess;
		}
		return mStructureOkay;
	}

	static {
		LH.add("gt6.tooltip.multiblock.sluice.1", "Two 3x7 Layers of Titanium Walls");
		LH.add("gt6.tooltip.multiblock.sluice.2", "3x7 Layer of Sluice Parts ontop of that");
		LH.add("gt6.tooltip.multiblock.sluice.3", "Main Block centered on Slim-Side-Bottom and facing outwards");
		LH.add("gt6.tooltip.multiblock.sluice.4", "Input only at the Top of the Far Side");
		LH.add("gt6.tooltip.multiblock.sluice.5", "Output only at the Bottom of the Close Side");
	}

	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get(LH.STRUCTURE) + ":");
		aList.add(Chat.WHITE    + LH.get("gt6.tooltip.multiblock.sluice.1"));
		aList.add(Chat.WHITE    + LH.get("gt6.tooltip.multiblock.sluice.2"));
		aList.add(Chat.WHITE    + LH.get("gt6.tooltip.multiblock.sluice.3"));
		aList.add(Chat.WHITE    + LH.get("gt6.tooltip.multiblock.sluice.4"));
		aList.add(Chat.WHITE    + LH.get("gt6.tooltip.multiblock.sluice.5"));
		super.addToolTips(aList, aStack, aF3_H);
	}

	@Override
	public boolean isInsideStructure(int aX, int aY, int aZ) {
		return
		aX >= xCoord-(SIDE_X_NEG==mFacing?0:SIDE_X_POS==mFacing?6:1) &&
		aY >= yCoord   &&
		aZ >= zCoord-(SIDE_Z_NEG==mFacing?0:SIDE_Z_POS==mFacing?6:1) &&
		aX <= xCoord+(SIDE_X_POS==mFacing?0:SIDE_X_NEG==mFacing?6:1) &&
		aY <= yCoord+2 &&
		aZ <= zCoord+(SIDE_Z_POS==mFacing?0:SIDE_Z_NEG==mFacing?6:1);
	}

	@Override
	public void updateAdjacentToggleableEnergySources() {
		DelegatorTileEntity<TileEntity> tDelegator;
		if (SIDES_AXIS_X[mFacing]) {
			tDelegator = WD.te(worldObj, getOffsetXN(mFacing, 5), yCoord+1, zCoord - 2, SIDE_Z_POS, F);
			if (tDelegator.mTileEntity instanceof ITileEntityAdjacentOnOff && tDelegator.mTileEntity instanceof ITileEntityEnergy && ((ITileEntityEnergy)tDelegator.mTileEntity).isEnergyEmittingTo(mEnergyTypeAccepted, tDelegator.mSideOfTileEntity, T)) ((ITileEntityAdjacentOnOff)tDelegator.mTileEntity).setAdjacentOnOff(getStateOnOff());
			tDelegator = WD.te(worldObj, getOffsetXN(mFacing, 5), yCoord+1, zCoord + 2, SIDE_Z_NEG, F);
			if (tDelegator.mTileEntity instanceof ITileEntityAdjacentOnOff && tDelegator.mTileEntity instanceof ITileEntityEnergy && ((ITileEntityEnergy)tDelegator.mTileEntity).isEnergyEmittingTo(mEnergyTypeAccepted, tDelegator.mSideOfTileEntity, T)) ((ITileEntityAdjacentOnOff)tDelegator.mTileEntity).setAdjacentOnOff(getStateOnOff());
		} else {
			tDelegator = WD.te(worldObj, xCoord - 2, yCoord+1, getOffsetZN(mFacing, 5), SIDE_X_POS, F);
			if (tDelegator.mTileEntity instanceof ITileEntityAdjacentOnOff && tDelegator.mTileEntity instanceof ITileEntityEnergy && ((ITileEntityEnergy)tDelegator.mTileEntity).isEnergyEmittingTo(mEnergyTypeAccepted, tDelegator.mSideOfTileEntity, T)) ((ITileEntityAdjacentOnOff)tDelegator.mTileEntity).setAdjacentOnOff(getStateOnOff());
			tDelegator = WD.te(worldObj, xCoord + 2, yCoord+1, getOffsetZN(mFacing, 5), SIDE_X_NEG, F);
			if (tDelegator.mTileEntity instanceof ITileEntityAdjacentOnOff && tDelegator.mTileEntity instanceof ITileEntityEnergy && ((ITileEntityEnergy)tDelegator.mTileEntity).isEnergyEmittingTo(mEnergyTypeAccepted, tDelegator.mSideOfTileEntity, T)) ((ITileEntityAdjacentOnOff)tDelegator.mTileEntity).setAdjacentOnOff(getStateOnOff());
		}
	}

	@Override
	public DelegatorTileEntity<IFluidHandler> getFluidOutputTarget(byte aSide, Fluid aOutput) {
		return getAdjacentTank(SIDE_BOTTOM);
	}

	@Override
	public DelegatorTileEntity<TileEntity> getItemOutputTarget(byte aSide) {
		return getAdjacentTileEntity(SIDE_BOTTOM);
	}

	@Override public DelegatorTileEntity<IInventory> getItemInputTarget(byte aSide) {return null;}
	@Override public DelegatorTileEntity<IFluidHandler> getFluidInputTarget(byte aSide) {return null;}

	@Override public boolean refreshStructureOnActiveStateChange() {return T;}

	@Override public String getTileEntityName() {return "gt6.multitileentity.multiblock.sluice";}
}
