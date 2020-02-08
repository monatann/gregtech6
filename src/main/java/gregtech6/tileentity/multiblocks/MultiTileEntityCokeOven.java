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
import gregapi6.util.WD;
import gregtech6.tileentity.multiblocks.builder.BuilderCokeOven;
import gregtech6.tileentity.multiblocks.templete.Multiblock3_3_3_Air;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.IFluidHandler;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityCokeOven extends TileEntityBase10MultiBlockMachine {
	@Override
	public boolean checkStructure2() {
		int tX = getOffsetXN(mFacing), tY = getOffsetYN(mFacing), tZ = getOffsetZN(mFacing);
		int[] inOutOption = {MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_ENERGY, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_ENERGY, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_ENERGY};

		return Multiblock3_3_3_Air.checkStructure(mStructureOkay, this, worldObj, tX, tY, tZ, BuilderCokeOven.blockId, getMultiTileEntityRegistryID(), inOutOption);

		/*
		if (worldObj.blockExists(tX-1, tY, tZ-1) && worldObj.blockExists(tX+1, tY, tZ-1) && worldObj.blockExists(tX-1, tY, tZ+1) && worldObj.blockExists(tX+1, tY, tZ+1)) {
			boolean tSuccess = T;
			for (int i = -1; i <= 1; i++) for (int j = -1; j <= 1; j++) for (int k = -1; k <= 1; k++) {
				if (i == 0 && j == 0 && k == 0) {
					if (getAir(tX+i, tY+j, tZ+k)) worldObj.setBlockToAir(tX+i, tY+j, tZ+k); else tSuccess = F;
				} else {
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+j, tZ+k, 18000, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_ENERGY)) tSuccess = F;
				}
			}
			return tSuccess;
		}
		return mStructureOkay;
		*/
	}

	static {
		LH.add("gt6.tooltip.multiblock.cokeoven.1", "3x3x3 Hollow of 25 Fire Bricks filled with Air");
		LH.add("gt6.tooltip.multiblock.cokeoven.2", "Main Block centered on Side and facing outwards");
	}

	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get(LH.STRUCTURE) + ":");
		aList.add(Chat.WHITE    + LH.get("gt6.tooltip.multiblock.cokeoven.1"));
		aList.add(Chat.WHITE    + LH.get("gt6.tooltip.multiblock.cokeoven.2"));
		super.addToolTips(aList, aStack, aF3_H);
	}

	@Override
	public boolean isInsideStructure(int aX, int aY, int aZ) {
		int tX = getOffsetXN(mFacing), tY = getOffsetYN(mFacing), tZ = getOffsetZN(mFacing);
		return aX >= tX - 1 && aY >= tY - 1 && aZ >= tZ - 1 && aX <= tX + 1 && aY <= tY + 1 && aZ <= tZ + 1;
	}

	public DelegatorTileEntity<IFluidHandler> mFluidOutputTarget = null;

	@Override
	public DelegatorTileEntity<IFluidHandler> getFluidOutputTarget(byte aSide, Fluid aOutput) {
		if (mFluidOutputTarget != null && mFluidOutputTarget.exists()) return mFluidOutputTarget;
		if (aOutput != null) {
			int tX = getOffsetXN(mFacing), tY = getOffsetYN(mFacing)-2, tZ = getOffsetZN(mFacing);
			for (int i = -1; i <= 1; i++) for (int j = -1; j <= 1; j++) {
				DelegatorTileEntity<TileEntity> tTarget = WD.te(worldObj, tX+i, tY, tZ+j, SIDE_TOP, F);
				if (tTarget.mTileEntity instanceof IFluidHandler && ((IFluidHandler)tTarget.mTileEntity).canFill(tTarget.getForgeSideOfTileEntity(), aOutput)) {
					return mFluidOutputTarget = new DelegatorTileEntity<>((IFluidHandler)tTarget.mTileEntity, tTarget);
				}
			}
		}
		return mFluidOutputTarget = null;
	}

	@Override public DelegatorTileEntity<IInventory> getItemInputTarget(byte aSide) {return null;}
	@Override public DelegatorTileEntity<TileEntity> getItemOutputTarget(byte aSide) {return null;}
	@Override public DelegatorTileEntity<IFluidHandler> getFluidInputTarget(byte aSide) {return null;}

	@Override public String getTileEntityName() {return "gt6.multitileentity.multiblock.cokeoven";}
}
