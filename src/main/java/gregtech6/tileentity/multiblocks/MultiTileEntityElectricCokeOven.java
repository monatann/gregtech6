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
import gregtech6.tileentity.multiblocks.templete.Multiblock3_3_3_Air;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.IFluidHandler;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityElectricCokeOven extends TileEntityBase10MultiBlockMachine {
	@Override
	public boolean checkStructure2() {
		int tX = getOffsetXN(mFacing), tY = getOffsetYN(mFacing)+1, tZ = getOffsetZN(mFacing);
		//if (worldObj.blockExists(tX-1, tY, tZ-1) && worldObj.blockExists(tX+1, tY, tZ-1) && worldObj.blockExists(tX-1, tY, tZ+1) && worldObj.blockExists(tX+1, tY, tZ+1)) {
			int[] blockId = {18035, 18097, 18035};
			int[] inOutOption = {MultiTileEntityMultiBlockPart.ONLY_ITEM_ENERGY, MultiTileEntityMultiBlockPart.NOTHING, MultiTileEntityMultiBlockPart.ONLY_ITEM_IN};

			return Multiblock3_3_3_Air.checkStructure(mStructureOkay, this, worldObj, tX, tY, tZ, blockId, getMultiTileEntityRegistryID(), inOutOption);

			/*
			boolean tSuccess = T;
			for (int i = -1; i <= 1; i++) for (int j = -1; j <= 1; j++) for (int k = -1; k <= 1; k++) {
				if (i == 0 && j == 0 && k == 0) {
					if (getAir(tX+i, tY+j, tZ+k)) worldObj.setBlockToAir(tX+i, tY+j, tZ+k); else tSuccess = F;
				} else {
					if(j == -1) {
						if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+j, tZ+k  , 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_ENERGY)) tSuccess = F;
					}else if(j == 1) {
						if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+j, tZ+k  , 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_IN)) tSuccess = F;
					}
					else if(j == 0) {
						if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+j, tZ+k  , 18042, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
					}
				}
			}
			return tSuccess;
			*/
		//}
		//return mStructureOkay;
	}

	static {
		LH.add("gt6.tooltip.multiblock.electriccokeoven.1", "3x3 of StainlessSteel Walls");
		LH.add("gt6.tooltip.multiblock.electriccokeoven.2", "3x3 Hollow of 8 Large Nichrome Coils");
		LH.add("gt6.tooltip.multiblock.electriccokeoven.3", "3x3 of StainlessSteel Walls");
		LH.add("gt6.tooltip.multiblock.electriccokeoven.4", "Main Block centered on Side-Bottom and facing outwards");
	}

	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get(LH.STRUCTURE) + ":");
		aList.add(Chat.WHITE    + LH.get("gt6.tooltip.multiblock.electriccokeoven.1"));
		aList.add(Chat.WHITE    + LH.get("gt6.tooltip.multiblock.electriccokeoven.2"));
		aList.add(Chat.WHITE    + LH.get("gt6.tooltip.multiblock.electriccokeoven.3"));
		aList.add(Chat.WHITE    + LH.get("gt6.tooltip.multiblock.electriccokeoven.4"));
		super.addToolTips(aList, aStack, aF3_H);
	}

	@Override
	public boolean isInsideStructure(int aX, int aY, int aZ) {
		int tX = getOffsetXN(mFacing), tY = getOffsetYN(mFacing), tZ = getOffsetZN(mFacing);
		return aX >= tX - 1 && aY >= tY - 1 && aZ >= tZ - 1 && aX <= tX + 1 && aY <= tY + 1 && aZ <= tZ + 1;
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


	@Override public String getTileEntityName() {return "gt6.multitileentity.multiblock.electriccokeoven";}
}
