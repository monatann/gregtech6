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
public class MultiTileEntityMixer extends TileEntityBase10MultiBlockMachine {
	@Override
	public boolean checkStructure2() {
		int tX = getOffsetXN(mFacing)-1, tY = yCoord, tZ = getOffsetZN(mFacing)-1;
		if (worldObj.blockExists(tX-1, tY, tZ-1) && worldObj.blockExists(tX+1, tY, tZ-1) && worldObj.blockExists(tX-1, tY, tZ+1) && worldObj.blockExists(tX+1, tY, tZ+1)) {
			boolean tSuccess = T;

			int[] blockId = {18035};
			int[] inOutOption = {MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_OUT, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_IN};

			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY  , tZ  , blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY  , tZ  , blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY  , tZ  , blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY  , tZ+1, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY  , tZ+1, blockId[0], getMultiTileEntityRegistryID(), 3, inOutOption[1]     )) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY  , tZ+1, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY  , tZ+2, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY  , tZ+2, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY  , tZ+2, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[0])) tSuccess = F;

			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+1, tZ  , blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[2] )) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY+1, tZ  , blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[2] )) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY+1, tZ  , blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[2] )) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+1, tZ+1, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[2] )) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY+1, tZ+1, blockId[0], getMultiTileEntityRegistryID(), 3, inOutOption[1]     )) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY+1, tZ+1, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[2] )) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+1, tZ+2, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[2] )) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY+1, tZ+2, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[2] )) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY+1, tZ+2, blockId[0], getMultiTileEntityRegistryID(), 0, inOutOption[2] )) tSuccess = F;

			return tSuccess;
		}
		return mStructureOkay;
	}

	static {
		LH.add("gt6.tooltip.multiblock.mixer.1", "3x3x2 of Stainless Steel Walls");
		LH.add("gt6.tooltip.multiblock.mixer.2", "Main Block centered on Side-Bottom and facing outwards");
		LH.add("gt6.tooltip.multiblock.mixer.3", "Top Half accepts Input, Bottom Half and Main Block emit Output");
	}

	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get(LH.STRUCTURE) + ":");
		aList.add(Chat.WHITE    + LH.get("gt6.tooltip.multiblock.mixer.1"));
		aList.add(Chat.WHITE    + LH.get("gt6.tooltip.multiblock.mixer.2"));
		aList.add(Chat.WHITE    + LH.get("gt6.tooltip.multiblock.mixer.3"));
		super.addToolTips(aList, aStack, aF3_H);
	}

	@Override
	public boolean isInsideStructure(int aX, int aY, int aZ) {
		int tX = getOffsetXN(mFacing), tY = yCoord, tZ = getOffsetZN(mFacing);
		return aX >= tX - 1 && aY >= tY && aZ >= tZ - 1 && aX <= tX + 1 && aY <= tY + 1 && aZ <= tZ + 1;
	}

	@Override
	public void updateAdjacentToggleableEnergySources() {
		DelegatorTileEntity<TileEntity>
		tDelegator = WD.te(worldObj, getOffsetXN(mFacing), yCoord-1, getOffsetZN(mFacing), SIDE_TOP, F);
		if (tDelegator.mTileEntity instanceof ITileEntityAdjacentOnOff && tDelegator.mTileEntity instanceof ITileEntityEnergy && ((ITileEntityEnergy)tDelegator.mTileEntity).isEnergyEmittingTo(mEnergyTypeAccepted, tDelegator.mSideOfTileEntity, T)) {
			((ITileEntityAdjacentOnOff)tDelegator.mTileEntity).setAdjacentOnOff(getStateOnOff());
		}
		tDelegator = WD.te(worldObj, getOffsetXN(mFacing), yCoord+2, getOffsetZN(mFacing), SIDE_BOTTOM, F);
		if (tDelegator.mTileEntity instanceof ITileEntityAdjacentOnOff && tDelegator.mTileEntity instanceof ITileEntityEnergy && ((ITileEntityEnergy)tDelegator.mTileEntity).isEnergyEmittingTo(mEnergyTypeAccepted, tDelegator.mSideOfTileEntity, T)) {
			((ITileEntityAdjacentOnOff)tDelegator.mTileEntity).setAdjacentOnOff(getStateOnOff());
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

	@Override public String getTileEntityName() {return "gt6.multitileentity.multiblock.mixer";}
}
