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
import gregapi6.tileentity.multiblocks.MultiTileEntityMultiBlockPart;
import gregapi6.tileentity.multiblocks.TileEntityBase10MultiBlockMachine;
import gregtech6.tileentity.multiblocks.templete.Multiblock5_2_5;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.IFluidHandler;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityCoagulator extends TileEntityBase10MultiBlockMachine {
	@Override
	public boolean checkStructure2() {
		int tX = getOffsetXN(mFacing, 2)-2, tY = yCoord, tZ = getOffsetZN(mFacing, 2)-2;
		int[] blockId = {18035, 18035};
		int[] inOutOption = {MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID};

		return Multiblock5_2_5.checkStructure(mStructureOkay, this, worldObj, tX, tY, tZ, blockId, getMultiTileEntityRegistryID(), inOutOption);
		/*
		if (worldObj.blockExists(tX-2, tY, tZ-2) && worldObj.blockExists(tX+2, tY, tZ-2) && worldObj.blockExists(tX-2, tY, tZ+2) && worldObj.blockExists(tX+2, tY, tZ+2)) {
			boolean tSuccess = T;

			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY  , tZ  , 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY  , tZ  , 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY  , tZ  , 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+3, tY  , tZ  , 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+4, tY  , tZ  , 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY  , tZ+1, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY  , tZ+1, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY  , tZ+1, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+3, tY  , tZ+1, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+4, tY  , tZ+1, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY  , tZ+2, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY  , tZ+2, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY  , tZ+2, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+3, tY  , tZ+2, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+4, tY  , tZ+2, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY  , tZ+3, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY  , tZ+3, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY  , tZ+3, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+3, tY  , tZ+3, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+4, tY  , tZ+3, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY  , tZ+4, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY  , tZ+4, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY  , tZ+4, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+3, tY  , tZ+4, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+4, tY  , tZ+4, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;

			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+1, tZ  , 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY+1, tZ  , 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY+1, tZ  , 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+3, tY+1, tZ  , 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+4, tY+1, tZ  , 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+1, tZ+1, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY+1, tZ+1, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY+1, tZ+1, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+3, tY+1, tZ+1, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+4, tY+1, tZ+1, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+1, tZ+2, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY+1, tZ+2, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY+1, tZ+2, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+3, tY+1, tZ+2, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+4, tY+1, tZ+2, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+1, tZ+3, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY+1, tZ+3, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY+1, tZ+3, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+3, tY+1, tZ+3, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+4, tY+1, tZ+3, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+1, tZ+4, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY+1, tZ+4, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY+1, tZ+4, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+3, tY+1, tZ+4, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+4, tY+1, tZ+4, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;

			return tSuccess;
		}
		return mStructureOkay;
		*/
	}

	static {
		LH.add("gt6.tooltip.multiblock.coagulator.1", "5x5x2 of Stainless Steel Walls");
		LH.add("gt6.tooltip.multiblock.coagulator.2", "Main Block centered on Side-Bottom and facing outwards");
		LH.add("gt6.tooltip.multiblock.coagulator.3", "Input and Output at any Blocks");
	}

	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get(LH.STRUCTURE) + ":");
		aList.add(Chat.WHITE    + LH.get("gt6.tooltip.multiblock.coagulator.1"));
		aList.add(Chat.WHITE    + LH.get("gt6.tooltip.multiblock.coagulator.2"));
		aList.add(Chat.WHITE    + LH.get("gt6.tooltip.multiblock.coagulator.3"));
		super.addToolTips(aList, aStack, aF3_H);
	}

	@Override
	public boolean isInsideStructure(int aX, int aY, int aZ) {
		int tX = getOffsetXN(mFacing, 2), tY = yCoord, tZ = getOffsetZN(mFacing, 2);
		return aX >= tX - 2 && aY >= tY && aZ >= tZ - 2 && aX <= tX + 2 && aY <= tY + 1 && aZ <= tZ + 2;
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

	@Override public String getTileEntityName() {return "gt6.multitileentity.multiblock.coagulator";}
}
