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

import java.util.Collection;
import java.util.List;

import gregapi6.code.TagData;
import gregapi6.data.FL;
import gregapi6.data.FM;
import gregapi6.data.LH;
import gregapi6.data.LH.Chat;
import gregapi6.data.TD;
import gregapi6.fluid.FluidTankGT;
import gregapi6.recipes.Recipe;
import gregapi6.recipes.Recipe.RecipeMap;
import gregapi6.tileentity.behavior.TE_Behavior_Active_Trinary;
import gregapi6.tileentity.energy.ITileEntityEnergy;
import gregapi6.tileentity.machines.ITileEntityRunningActively;
import gregapi6.tileentity.multiblocks.IMultiBlockEnergy;
import gregapi6.tileentity.multiblocks.IMultiBlockFluidHandler;
import gregapi6.tileentity.multiblocks.MultiTileEntityMultiBlockPart;
import gregapi6.tileentity.multiblocks.TileEntityBase10MultiBlockBase;
import gregapi6.util.UT;
import gregapi6.util.WD;
import gregtech6.tileentity.multiblocks.templete.Multiblock3_2_3_Ring;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityLargeHeatExchanger extends TileEntityBase10MultiBlockBase implements IFluidHandler, ITileEntityRunningActively, ITileEntityEnergy, IMultiBlockFluidHandler, IMultiBlockEnergy {
	public short mEfficiency = 10000;
	public long mEnergy = 0, mRate = 8;
	public TagData mEnergyTypeEmitted = TD.Energy.HU;
	public RecipeMap mRecipes = FM.Hot;
	public Recipe mLastRecipe = null;
	public FluidTankGT[] mTanks = {new FluidTankGT(10000), new FluidTankGT(Long.MAX_VALUE)};
	public TE_Behavior_Active_Trinary mActivity = null;

	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		mEnergy = aNBT.getLong(NBT_ENERGY);
		mActivity = new TE_Behavior_Active_Trinary(this, aNBT);
		if (aNBT.hasKey(NBT_OUTPUT)) mRate = aNBT.getLong(NBT_OUTPUT);
		if (aNBT.hasKey(NBT_FUELMAP)) mRecipes = RecipeMap.RECIPE_MAPS.get(aNBT.getString(NBT_FUELMAP));
		if (aNBT.hasKey(NBT_EFFICIENCY)) mEfficiency = (short)UT.Code.bind_(0, 10000, aNBT.getShort(NBT_EFFICIENCY));
		if (aNBT.hasKey(NBT_ENERGY_EMITTED)) mEnergyTypeEmitted = TagData.createTagData(aNBT.getString(NBT_ENERGY_EMITTED));
		mTanks[0].setCapacity(mRate * 10);
		mTanks[0].readFromNBT(aNBT, NBT_TANK+".0");
		mTanks[1].readFromNBT(aNBT, NBT_TANK+".1");
	}

	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setNumber(aNBT, NBT_ENERGY, mEnergy);
		mActivity.save(aNBT);
		mTanks[0].writeToNBT(aNBT, NBT_TANK+".0");
		mTanks[1].writeToNBT(aNBT, NBT_TANK+".1");
	}

	@Override
	public boolean checkStructure2() {
		int tX = xCoord-1, tY = yCoord, tZ = zCoord-1;
		int[] blockId = {18101, 18067, 18101};
		int[] inOutOption = {MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_ENERGY_IN, MultiTileEntityMultiBlockPart.NOTHING, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_ENERGY_IN};

		return Multiblock3_2_3_Ring.checkStructure(mStructureOkay, this, worldObj, tX, tY, tZ, blockId, getMultiTileEntityRegistryID(), inOutOption);

		/*
		if (worldObj.blockExists(tX-1, tY, tZ-1) && worldObj.blockExists(tX+1, tY, tZ-1) && worldObj.blockExists(tX-1, tY, tZ+1) && worldObj.blockExists(tX+1, tY, tZ+1)) {
			boolean tSuccess = T;

			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY  , tZ  , 18024, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_ENERGY_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY  , tZ  , 18024, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_ENERGY_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY  , tZ  , 18024, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_ENERGY_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY  , tZ+1, 18024, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_ENERGY_IN)) tSuccess = F;

			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY  , tZ+1, 18024, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_ENERGY_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY  , tZ+2, 18024, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_ENERGY_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY  , tZ+2, 18024, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_ENERGY_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY  , tZ+2, 18024, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_ENERGY_IN)) tSuccess = F;

			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+1, tZ  , 18101, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY+1, tZ  , 18101, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY+1, tZ  , 18101, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+1, tZ+1, 18101, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY+1, tZ+1, 18024, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY+1, tZ+1, 18101, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+1, tZ+2, 18101, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY+1, tZ+2, 18101, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY+1, tZ+2, 18101, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;

			return tSuccess;
		}
		return mStructureOkay;
		*/
	}

	@Override
	public boolean isInsideStructure(int aX, int aY, int aZ) {
		int tX = xCoord, tY = yCoord, tZ = zCoord;
		return aX >= tX - 1 && aY >= tY && aZ >= tZ - 1 && aX <= tX + 1 && aY <= tY + 1 && aZ <= tZ + 1;
	}

	static {
		LH.add("gt6.tooltip.multiblock.heatexchanger.1", "3x3 Ring of 8 Heat Transmitters with Dense Tungsten Wall inside");
		LH.add("gt6.tooltip.multiblock.heatexchanger.2", "3x3 Ring of 8 Dense Tungsten Walls with Main inside");
		LH.add("gt6.tooltip.multiblock.heatexchanger.3", "Energy Output split over the 8 Heat Transmitters (Top)");
		LH.add("gt6.tooltip.multiblock.heatexchanger.4", "Input at Bottom Layer, Fluid Output at Main Block");
	}

	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get(LH.STRUCTURE) + ":");
		aList.add(Chat.WHITE    + LH.get("gt6.tooltip.multiblock.heatexchanger.1"));
		aList.add(Chat.WHITE    + LH.get("gt6.tooltip.multiblock.heatexchanger.2"));
		aList.add(Chat.WHITE    + LH.get("gt6.tooltip.multiblock.heatexchanger.3"));
		aList.add(Chat.WHITE    + LH.get("gt6.tooltip.multiblock.heatexchanger.4"));
		aList.add(Chat.CYAN     + LH.get(LH.RECIPES) + ": " + Chat.WHITE + LH.get(mRecipes.mNameInternal));
		aList.add(LH.getToolTipEfficiency(mEfficiency));
		LH.addEnergyToolTips(this, aList, null, mEnergyTypeEmitted, null, LH.get(LH.FACE_TOP));
		aList.add(Chat.ORANGE   + LH.get(LH.NO_GUI_FUNNEL_TAP_TO_TANK));
		super.addToolTips(aList, aStack, aF3_H);
	}

	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		super.onTick2(aTimer, aIsServerSide);
		if (aIsServerSide) {
			// Emit buffered Energy. And yes if you use a strong enough Fuel, that Energy would stay buffered even while the Box is Off. This is very intended and represents partially used Fuel.
			if (mEnergy >= 8) {
				long tTransferred = Math.min(mRate / 8, mEnergy / 8);
				mEnergy -= tTransferred * 8;
				ITileEntityEnergy.Util.insertEnergyInto(mEnergyTypeEmitted, 1, tTransferred, this, WD.te(worldObj, xCoord  , yCoord+2, zCoord-1, SIDE_BOTTOM, T));
				ITileEntityEnergy.Util.insertEnergyInto(mEnergyTypeEmitted, 1, tTransferred, this, WD.te(worldObj, xCoord-1, yCoord+2, zCoord  , SIDE_BOTTOM, T));
				ITileEntityEnergy.Util.insertEnergyInto(mEnergyTypeEmitted, 1, tTransferred, this, WD.te(worldObj, xCoord  , yCoord+2, zCoord+1, SIDE_BOTTOM, T));
				ITileEntityEnergy.Util.insertEnergyInto(mEnergyTypeEmitted, 1, tTransferred, this, WD.te(worldObj, xCoord+1, yCoord+2, zCoord  , SIDE_BOTTOM, T));
				ITileEntityEnergy.Util.insertEnergyInto(mEnergyTypeEmitted, 1, tTransferred, this, WD.te(worldObj, xCoord+1, yCoord+2, zCoord-1, SIDE_BOTTOM, T));
				ITileEntityEnergy.Util.insertEnergyInto(mEnergyTypeEmitted, 1, tTransferred, this, WD.te(worldObj, xCoord-1, yCoord+2, zCoord-1, SIDE_BOTTOM, T));
				ITileEntityEnergy.Util.insertEnergyInto(mEnergyTypeEmitted, 1, tTransferred, this, WD.te(worldObj, xCoord-1, yCoord+2, zCoord+1, SIDE_BOTTOM, T));
				ITileEntityEnergy.Util.insertEnergyInto(mEnergyTypeEmitted, 1, tTransferred, this, WD.te(worldObj, xCoord+1, yCoord+2, zCoord+1, SIDE_BOTTOM, T));
			}
			// Check if it needs to use more Fuel, or if the buffered Energy is enough.
			if (mEnergy < mRate * 2) {
				// Will be set back to true if the Recipe finds enough Fuel.
				mActivity.mActive = F;
				// Output isn't allowed to be completely filled.
				if (!mTanks[1].has(mRate * 20)) {
					// Find and apply fitting Recipe.
					Recipe tRecipe = mRecipes.findRecipe(this, mLastRecipe, T, Long.MAX_VALUE, NI, mTanks[0].AS_ARRAY, ZL_IS);
					if (tRecipe != null) {
						if (tRecipe.mFluidOutputs.length <= 0 || mTanks[1].canFillAll(tRecipe.mFluidOutputs[0])) {
							if (tRecipe.isRecipeInputEqual(T, F, mTanks[0].AS_ARRAY, ZL_IS)) {
								mActivity.mActive = T;
								mLastRecipe = tRecipe;
								mEnergy += UT.Code.units(Math.abs(tRecipe.mEUt * tRecipe.mDuration), 10000, mEfficiency, F);
								if (tRecipe.mFluidOutputs.length > 0) mTanks[1].fill(tRecipe.mFluidOutputs[0]);
								// Use as much as needed to keep up the Power per Tick.
								while (mEnergy < mRate * 2 && (tRecipe.mFluidOutputs.length <= 0 || mTanks[1].canFillAll(tRecipe.mFluidOutputs[0])) && tRecipe.isRecipeInputEqual(T, F, mTanks[0].AS_ARRAY, ZL_IS)) {
									mEnergy += UT.Code.units(Math.abs(tRecipe.mEUt * tRecipe.mDuration), 10000, mEfficiency, F);
									if (tRecipe.mFluidOutputs.length > 0) mTanks[1].fill(tRecipe.mFluidOutputs[0]);
									if (mTanks[0].isEmpty()) break;
								}
							} else {
								// set remaining Fluid to null, in case the Fuel Type needs to be swapped out.
								mTanks[0].setEmpty();
							}
						}
					} else {
						// set remaining Fluid to null, in case the Fuel Type needs to be swapped out.
						mTanks[0].setEmpty();
					}
				}
			}
			// Out of Fuel I guess.
			if (mEnergy < 8) mEnergy = 0;
			// Output used Liquid to the Front.
			if (mTanks[1].has()) FL.move(mTanks[1], getAdjacentTank(SIDE_BOTTOM));
		}
	}

	@Override
	public void onMagnifyingGlass2(List<String> aChatReturn) {
		aChatReturn.add("Structure is formed already!");
		aChatReturn.add("Input: "  + mTanks[0].content());
		aChatReturn.add("Output: " + mTanks[1].content());
	}

	@Override
	public boolean onTickCheck(long aTimer) {
		return mActivity.check(F) || super.onTickCheck(aTimer);
	}

	@Override
	public void setVisualData(byte aData) {
		mActivity.mState = (byte)(aData & 127);
	}

	@Override public byte getVisualData() {return mActivity.mState;}
	@Override public byte getDefaultSide() {return SIDE_BOTTOM;}
	@Override public boolean[] getValidSides() {return SIDES_BOTTOM;}

	@Override
	protected IFluidTank getFluidTankFillable2(byte aSide, FluidStack aFluidToFill) {
		return mRecipes.containsInput(aFluidToFill, this, NI) ? mTanks[0] : null;
	}

	@Override
	protected IFluidTank getFluidTankDrainable2(byte aSide, FluidStack aFluidToDrain) {
		return mTanks[1];
	}

	@Override
	protected IFluidTank[] getFluidTanks2(byte aSide) {
		return mTanks;
	}

	@Override
	public int funnelFill(byte aSide, FluidStack aFluid, boolean aDoFill) {
		if (!mRecipes.containsInput(aFluid, this, NI)) return 0;
		updateInventory();
		return mTanks[0].fill(aFluid, aDoFill);
	}

	@Override
	public FluidStack tapDrain(byte aSide, int aMaxDrain, boolean aDoDrain) {
		updateInventory();
		return mTanks[mTanks[1].has() ? 1 : 0].drain(aMaxDrain, aDoDrain);
	}

	@Override public ItemStack[] getDefaultInventory(NBTTagCompound aNBT) {return ZL_IS;}

	@Override public boolean isEnergyType(TagData aEnergyType, byte aSide, boolean aEmitting) {return aEmitting && aEnergyType == mEnergyTypeEmitted;}
	@Override public boolean isEnergyEmittingTo(TagData aEnergyType, byte aSide, boolean aTheoretical) {return SIDES_TOP[aSide] && super.isEnergyEmittingTo(aEnergyType, aSide, aTheoretical);}
	@Override public long getEnergyOffered(TagData aEnergyType, byte aSide, long aSize) {return Math.min(mRate, mEnergy);}
	@Override public long getEnergySizeOutputRecommended(TagData aEnergyType, byte aSide) {return mRate;}
	@Override public long getEnergySizeOutputMin(TagData aEnergyType, byte aSide) {return mRate;}
	@Override public long getEnergySizeOutputMax(TagData aEnergyType, byte aSide) {return mRate;}
	@Override public Collection<TagData> getEnergyTypes(byte aSide) {return mEnergyTypeEmitted.AS_LIST;}

	@Override public boolean getStateRunningPassively() {return mActivity.mActive;}
	@Override public boolean getStateRunningPossible() {return mActivity.mActive;}
	@Override public boolean getStateRunningActively() {return mActivity.mActive;}

	@Override public boolean canDrop(int aInventorySlot) {return F;}

	@Override public String getTileEntityName() {return "gt6.multitileentity.multiblock.heatexchanger";}
}
