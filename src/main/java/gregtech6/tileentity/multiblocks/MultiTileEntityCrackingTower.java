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

import gregapi6.data.FL;
import gregapi6.data.LH;
import gregapi6.data.LH.Chat;
import gregapi6.data.TD;
import gregapi6.tileentity.delegate.DelegatorTileEntity;
import gregapi6.tileentity.energy.ITileEntityEnergy;
import gregapi6.tileentity.machines.ITileEntityAdjacentOnOff;
import gregapi6.tileentity.multiblocks.MultiTileEntityMultiBlockPart;
import gregapi6.tileentity.multiblocks.TileEntityBase10MultiBlockMachine;
import gregapi6.util.ST;
import gregapi6.util.WD;
import gregtech6.tileentity.multiblocks.templete.Multiblock3_11_3_Tower;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityCrackingTower extends TileEntityBase10MultiBlockMachine {
	@Override
	public boolean checkStructure2() {
		int tX = getOffsetXN(mFacing), tY = yCoord, tZ = getOffsetZN(mFacing);

		int[] blockId = {18101, 18109};
		int[] inOutOption = {MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID, MultiTileEntityMultiBlockPart.ONLY_FLUID_OUT};

		return Multiblock3_11_3_Tower.checkStructure(mStructureOkay, this, worldObj, tX, tY, tZ, blockId, getMultiTileEntityRegistryID(), mFacing, inOutOption);

		/*
		if (worldObj.blockExists(tX-1, tY, tZ-1) && worldObj.blockExists(tX+1, tY, tZ-1) && worldObj.blockExists(tX-1, tY, tZ+1) && worldObj.blockExists(tX+1, tY, tZ+1)) {
			boolean tSuccess = T;

			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-1, tY-1, tZ-1, 18101, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY-1, tZ-1, 18101, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY-1, tZ-1, 18101, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-1, tY-1, tZ  , 18101, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY-1, tZ  , 18101, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY-1, tZ  , 18101, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-1, tY-1, tZ+1, 18101, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY-1, tZ+1, 18101, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY-1, tZ+1, 18101, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN)) tSuccess = F;

			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-1, tY  , tZ-1, 18109, getMultiTileEntityRegistryID(),                              0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY  , tZ-1, 18109, getMultiTileEntityRegistryID(),  mFacing == SIDE_Z_POS ? 1 : 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY  , tZ-1, 18109, getMultiTileEntityRegistryID(),                              0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-1, tY  , tZ  , 18109, getMultiTileEntityRegistryID(),  mFacing == SIDE_X_POS ? 1 : 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY  , tZ  , 18109, getMultiTileEntityRegistryID(),                              0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY  , tZ  , 18109, getMultiTileEntityRegistryID(),  mFacing == SIDE_X_NEG ? 1 : 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-1, tY  , tZ+1, 18109, getMultiTileEntityRegistryID(),                              0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY  , tZ+1, 18109, getMultiTileEntityRegistryID(),  mFacing == SIDE_Z_NEG ? 1 : 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY  , tZ+1, 18109, getMultiTileEntityRegistryID(),                              0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;

			for (int i = 1; i < 8; i++) {
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-1, tY+i, tZ-1, 18109, getMultiTileEntityRegistryID(),                              0, MultiTileEntityMultiBlockPart.ONLY_FLUID_OUT)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+i, tZ-1, 18109, getMultiTileEntityRegistryID(),  mFacing == SIDE_Z_POS ? 1 : 0, MultiTileEntityMultiBlockPart.ONLY_FLUID_OUT)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY+i, tZ-1, 18109, getMultiTileEntityRegistryID(),                              0, MultiTileEntityMultiBlockPart.ONLY_FLUID_OUT)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-1, tY+i, tZ  , 18109, getMultiTileEntityRegistryID(),  mFacing == SIDE_X_POS ? 1 : 0, MultiTileEntityMultiBlockPart.ONLY_FLUID_OUT)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+i, tZ  , 18109, getMultiTileEntityRegistryID(),                              0, MultiTileEntityMultiBlockPart.ONLY_FLUID_OUT)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY+i, tZ  , 18109, getMultiTileEntityRegistryID(),  mFacing == SIDE_X_NEG ? 1 : 0, MultiTileEntityMultiBlockPart.ONLY_FLUID_OUT)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-1, tY+i, tZ+1, 18109, getMultiTileEntityRegistryID(),                              0, MultiTileEntityMultiBlockPart.ONLY_FLUID_OUT)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+i, tZ+1, 18109, getMultiTileEntityRegistryID(),  mFacing == SIDE_Z_NEG ? 1 : 0, MultiTileEntityMultiBlockPart.ONLY_FLUID_OUT)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY+i, tZ+1, 18109, getMultiTileEntityRegistryID(),                              0, MultiTileEntityMultiBlockPart.ONLY_FLUID_OUT)) tSuccess = F;
			}

			return tSuccess;
		}
		return mStructureOkay;
		*/
	}

	static {
		LH.add("gt6.tooltip.multiblock.crackingtower.1", "3x3 Base of Heat Transmitters");
		LH.add("gt6.tooltip.multiblock.crackingtower.2", "3x3x8 of Cracking Tower Parts");
		LH.add("gt6.tooltip.multiblock.crackingtower.3", "Main centered on Side-Bottom of Tower facing outwards");
		LH.add("gt6.tooltip.multiblock.crackingtower.4", "Outputs automatically to the Holes on the Backside");
		LH.add("gt6.tooltip.multiblock.crackingtower.5", "Bottom Hole is for outputting all Items");
		LH.add("gt6.tooltip.multiblock.crackingtower.6", "Input only possible at Bottom Layer of Tower");
	}

	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get(LH.STRUCTURE) + ":");
		aList.add(Chat.WHITE    + LH.get("gt6.tooltip.multiblock.crackingtower.1"));
		aList.add(Chat.WHITE    + LH.get("gt6.tooltip.multiblock.crackingtower.2"));
		aList.add(Chat.WHITE    + LH.get("gt6.tooltip.multiblock.crackingtower.3"));
		aList.add(Chat.WHITE    + LH.get("gt6.tooltip.multiblock.crackingtower.4"));
		aList.add(Chat.WHITE    + LH.get("gt6.tooltip.multiblock.crackingtower.5"));
		aList.add(Chat.WHITE    + LH.get("gt6.tooltip.multiblock.crackingtower.6"));
		super.addToolTips(aList, aStack, aF3_H);
	}

	@Override
	public void addToolTipsSided(List<String> aList, ItemStack aStack, boolean aF3_H) {
		String tSideNames = ""; boolean temp = F;
		if (mEnergyTypeAccepted != TD.Energy.TU) {
		for (byte tSide : ALL_SIDES_VALID) if (FACE_CONNECTED[tSide][mEnergyInputs])    {tSideNames += (temp?", ":"")+LH.get(LH.FACES[tSide]); temp = T;}
		LH.addEnergyToolTips(this, aList, mEnergyTypeAccepted, null, tSideNames, null);
		}
	}

	@Override
	public boolean isInsideStructure(int aX, int aY, int aZ) {
		int tX = getOffsetXN(mFacing), tY = yCoord, tZ = getOffsetZN(mFacing);
		return aX >= tX - 1 && aY >= tY - 1 && aZ >= tZ - 1 && aX <= tX + 1 && aY <= tY + 7 && aZ <= tZ + 1;
	}

	@Override
	public void updateAdjacentToggleableEnergySources() {
		int tX = getOffsetXN(mFacing) - 1, tZ = getOffsetZN(mFacing) - 1;
		for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) {
			DelegatorTileEntity<TileEntity> tDelegator = WD.te(worldObj, tX+i, yCoord-2, tZ+j, SIDE_TOP, F);
			if (tDelegator.mTileEntity instanceof ITileEntityAdjacentOnOff && tDelegator.mTileEntity instanceof ITileEntityEnergy && ((ITileEntityEnergy)tDelegator.mTileEntity).isEnergyEmittingTo(mEnergyTypeAccepted, tDelegator.mSideOfTileEntity, T)) {
				((ITileEntityAdjacentOnOff)tDelegator.mTileEntity).setAdjacentOnOff(getStateOnOff());
			}
		}
	}

	@Override
	public void doOutputItems() {
		ST.moveAll(delegator(FACING_TO_SIDE[mFacing][mItemAutoOutput]), WD.te(worldObj, getOffsetXN(mFacing, 3), yCoord, getOffsetZN(mFacing, 3), mFacing, F));
	}

	@Override
	public void doOutputFluids() {
		for (int i=0;i<mTanksOutput.length;i++) {
			IFluidTank tTank = mTanksOutput[i];
			FluidStack tFluid = tTank.getFluid();
			if (tFluid != null && tFluid.amount > 0) {
				DelegatorTileEntity<TileEntity> tDelegator = null;
				tDelegator = WD.te(worldObj, getOffsetXN(mFacing, 3), yCoord+i+1, getOffsetZN(mFacing, 3), mFacing, F);

				if (FL.move(tTank, tDelegator) > 0) updateInventory();
			}
		}
	}

	@Override public DelegatorTileEntity<IInventory> getItemInputTarget(byte aSide) {return null;}
	@Override public DelegatorTileEntity<TileEntity> getItemOutputTarget(byte aSide) {return null;}
	@Override public DelegatorTileEntity<IFluidHandler> getFluidInputTarget(byte aSide) {return null;}
	@Override public DelegatorTileEntity<IFluidHandler> getFluidOutputTarget(byte aSide, Fluid aOutput) {return null;}

	@Override public String getTileEntityName() {return "gt6.multitileentity.multiblock.crackingtower";}
}
