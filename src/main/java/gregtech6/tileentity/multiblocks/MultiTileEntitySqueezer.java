/**
 * Copyright (c) 2020 Gregorius Techneticies
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
public class MultiTileEntitySqueezer extends TileEntityBase10MultiBlockMachine {
	@Override
	public boolean checkStructure2() {
		int tX = getOffsetXN(mFacing, 2)-2, tY = yCoord, tZ = getOffsetZN(mFacing, 2)-2;
		int[] blockId = {18034};
		int[] inOutOption = {MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_OUT, MultiTileEntityMultiBlockPart.NOTHING, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN, };

		if (worldObj.blockExists(tX, tY, tZ) && worldObj.blockExists(tX+4, tY, tZ) && worldObj.blockExists(tX, tY, tZ+4) && worldObj.blockExists(tX+4, tY, tZ+4)) {
			boolean tSuccess = T;

			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY  , tZ  , blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY  , tZ  , blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY  , tZ  , blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+3, tY  , tZ  , blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+4, tY  , tZ  , blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY  , tZ+1, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY  , tZ+1, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY  , tZ+1, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+3, tY  , tZ+1, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+4, tY  , tZ+1, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY  , tZ+2, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY  , tZ+2, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY  , tZ+2, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+3, tY  , tZ+2, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+4, tY  , tZ+2, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY  , tZ+3, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY  , tZ+3, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY  , tZ+3, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+3, tY  , tZ+3, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+4, tY  , tZ+3, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY  , tZ+4, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY  , tZ+4, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY  , tZ+4, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+3, tY  , tZ+4, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+4, tY  , tZ+4, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[0])) tSuccess = F;

			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+1, tZ  , blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY+1, tZ  , blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY+1, tZ  , blockId[0], getMultiTileEntityRegistryID(), SIDES_AXIS_Z[mFacing]?0:3, SIDES_AXIS_Z[mFacing]?inOutOption[1]:inOutOption[2])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+3, tY+1, tZ  , blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+4, tY+1, tZ  , blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+1, tZ+1, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[1])) tSuccess = F;
			if (getAir(tX+1, tY+1, tZ+1)) worldObj.setBlockToAir(tX+1, tY+1, tZ+1); else tSuccess = F;
			if (getAir(tX+2, tY+1, tZ+1)) worldObj.setBlockToAir(tX+2, tY+1, tZ+1); else tSuccess = F;
			if (getAir(tX+3, tY+1, tZ+1)) worldObj.setBlockToAir(tX+3, tY+1, tZ+1); else tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+4, tY+1, tZ+1, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+1, tZ+2, blockId[0], getMultiTileEntityRegistryID(), SIDES_AXIS_X[mFacing]?0:3, SIDES_AXIS_X[mFacing]?inOutOption[1]:inOutOption[2])) tSuccess = F;
			if (getAir(tX+1, tY+1, tZ+2)) worldObj.setBlockToAir(tX+1, tY+1, tZ+2); else tSuccess = F;
			if (getAir(tX+2, tY+1, tZ+2)) worldObj.setBlockToAir(tX+2, tY+1, tZ+2); else tSuccess = F;
			if (getAir(tX+3, tY+1, tZ+2)) worldObj.setBlockToAir(tX+3, tY+1, tZ+2); else tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+4, tY+1, tZ+2, blockId[0], getMultiTileEntityRegistryID(), SIDES_AXIS_X[mFacing]?0:3, SIDES_AXIS_X[mFacing]?inOutOption[1]:inOutOption[2])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+1, tZ+3, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[1])) tSuccess = F;
			if (getAir(tX+1, tY+1, tZ+3)) worldObj.setBlockToAir(tX+1, tY+1, tZ+3); else tSuccess = F;
			if (getAir(tX+2, tY+1, tZ+3)) worldObj.setBlockToAir(tX+2, tY+1, tZ+3); else tSuccess = F;
			if (getAir(tX+3, tY+1, tZ+3)) worldObj.setBlockToAir(tX+3, tY+1, tZ+3); else tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+4, tY+1, tZ+3, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+1, tZ+4, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY+1, tZ+4, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY+1, tZ+4, blockId[0], getMultiTileEntityRegistryID(), SIDES_AXIS_Z[mFacing]?0:3, SIDES_AXIS_Z[mFacing]?inOutOption[1]:inOutOption[2])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+3, tY+1, tZ+4, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+4, tY+1, tZ+4, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[1])) tSuccess = F;

			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+2, tZ  , blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[3])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY+2, tZ  , blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[3])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY+2, tZ  , blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[3])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+3, tY+2, tZ  , blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[3])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+4, tY+2, tZ  , blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[3])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+2, tZ+1, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[3])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY+2, tZ+1, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[3])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY+2, tZ+1, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[3])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+3, tY+2, tZ+1, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[3])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+4, tY+2, tZ+1, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[3])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+2, tZ+2, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[3])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY+2, tZ+2, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[3])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY+2, tZ+2, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[3])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+3, tY+2, tZ+2, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[3])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+4, tY+2, tZ+2, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[3])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+2, tZ+3, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[3])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY+2, tZ+3, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[3])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY+2, tZ+3, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[3])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+3, tY+2, tZ+3, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[3])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+4, tY+2, tZ+3, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[3])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+2, tZ+4, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[3])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY+2, tZ+4, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[3])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY+2, tZ+4, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[3])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+3, tY+2, tZ+4, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[3])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+4, tY+2, tZ+4, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[3])) tSuccess = F;

			return tSuccess;
		}
		return mStructureOkay;
	}

	static {
		LH.add("gt6.tooltip.multiblock.squeezer.1", "5x5x3 Hollow of 65 Steel Walls");
		LH.add("gt6.tooltip.multiblock.squeezer.2", "Main Block centered on Side-Bottom and facing outwards");
		LH.add("gt6.tooltip.multiblock.squeezer.3", "Input at Top Layer, Output at Bottom Layer");
	}

	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get(LH.STRUCTURE) + ":");
		aList.add(Chat.WHITE    + LH.get("gt6.tooltip.multiblock.squeezer.1"));
		aList.add(Chat.WHITE    + LH.get("gt6.tooltip.multiblock.squeezer.2"));
		aList.add(Chat.WHITE    + LH.get("gt6.tooltip.multiblock.squeezer.3"));
		super.addToolTips(aList, aStack, aF3_H);
	}

	@Override
	public boolean isInsideStructure(int aX, int aY, int aZ) {
		int tX = getOffsetXN(mFacing, 2), tY = yCoord, tZ = getOffsetZN(mFacing, 2);
		return aX >= tX - 2 && aY >= tY && aZ >= tZ - 2 && aX <= tX + 2 && aY <= tY + 2 && aZ <= tZ + 2;
	}

	@Override
	public void updateAdjacentToggleableEnergySources() {
		DelegatorTileEntity<TileEntity> tDelegator;
		if (SIDES_AXIS_X[mFacing]) {
			tDelegator = WD.te(worldObj, getOffsetXN(mFacing, 2), yCoord+1, zCoord - 3, SIDE_Z_POS, F);
			if (tDelegator.mTileEntity instanceof ITileEntityAdjacentOnOff && tDelegator.mTileEntity instanceof ITileEntityEnergy && ((ITileEntityEnergy)tDelegator.mTileEntity).isEnergyEmittingTo(mEnergyTypeAccepted, tDelegator.mSideOfTileEntity, T)) ((ITileEntityAdjacentOnOff)tDelegator.mTileEntity).setAdjacentOnOff(getStateOnOff());
			tDelegator = WD.te(worldObj, getOffsetXN(mFacing, 2), yCoord+1, zCoord + 3, SIDE_Z_NEG, F);
			if (tDelegator.mTileEntity instanceof ITileEntityAdjacentOnOff && tDelegator.mTileEntity instanceof ITileEntityEnergy && ((ITileEntityEnergy)tDelegator.mTileEntity).isEnergyEmittingTo(mEnergyTypeAccepted, tDelegator.mSideOfTileEntity, T)) ((ITileEntityAdjacentOnOff)tDelegator.mTileEntity).setAdjacentOnOff(getStateOnOff());
		} else {
			tDelegator = WD.te(worldObj, xCoord - 3, yCoord+1, getOffsetZN(mFacing, 2), SIDE_X_POS, F);
			if (tDelegator.mTileEntity instanceof ITileEntityAdjacentOnOff && tDelegator.mTileEntity instanceof ITileEntityEnergy && ((ITileEntityEnergy)tDelegator.mTileEntity).isEnergyEmittingTo(mEnergyTypeAccepted, tDelegator.mSideOfTileEntity, T)) ((ITileEntityAdjacentOnOff)tDelegator.mTileEntity).setAdjacentOnOff(getStateOnOff());
			tDelegator = WD.te(worldObj, xCoord + 3, yCoord+1, getOffsetZN(mFacing, 2), SIDE_X_NEG, F);
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

	@Override public String getTileEntityName() {return "gt6.multitileentity.multiblock.squeezer";}
}
