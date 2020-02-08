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

import gregapi6.data.FM;
import gregapi6.data.LH;
import gregapi6.data.LH.Chat;
import gregapi6.fluid.FluidTankGT;
import gregapi6.recipes.Recipe;
import gregapi6.recipes.Recipe.RecipeMap;
import gregapi6.util.UT;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityLargeTurbineGas extends MultiTileEntityLargeTurbine {
	public FluidTankGT mInputTank = new FluidTankGT(Integer.MAX_VALUE), mTanksOutput[] = new FluidTankGT[] {new FluidTankGT(Integer.MAX_VALUE), new FluidTankGT(Integer.MAX_VALUE), new FluidTankGT(Integer.MAX_VALUE)};
	public FluidTankGT[] mTanks = new FluidTankGT[] {mInputTank, mTanksOutput[0], mTanksOutput[1], mTanksOutput[2]};
	public RecipeMap mRecipes = FM.Gas;
	public Recipe mLastRecipe = null;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_FUELMAP)) mRecipes = RecipeMap.RECIPE_MAPS.get(aNBT.getString(NBT_FUELMAP));
		for (int i = 0; i < mTanksOutput.length; i++)
		mTanksOutput[i].readFromNBT(aNBT, NBT_TANK+"."+i).setCapacity(mEnergyIN.mMax*4);
		mInputTank.readFromNBT(aNBT, NBT_TANK).setCapacity(mEnergyIN.mMax*4);
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		for (int i = 0; i < mTanksOutput.length; i++)
		mTanksOutput[i].writeToNBT(aNBT, NBT_TANK+"."+i);
		mInputTank.writeToNBT(aNBT, NBT_TANK);
	}
	
	static {
		LH.add("gt6.tooltip.multiblock.gasturbine.1", "3x3x4 of the Block you crafted this one with");
		LH.add("gt6.tooltip.multiblock.gasturbine.2", "Main centered on the 3x3 facing outwards");
		LH.add("gt6.tooltip.multiblock.gasturbine.3", "Input only possible at frontal 3x3");
		LH.add("gt6.tooltip.multiblock.gasturbine.4", "Exhaust Gas has to be removed!");
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get(LH.STRUCTURE) + ":");
		aList.add(Chat.WHITE    + LH.get("gt6.tooltip.multiblock.gasturbine.1"));
		aList.add(Chat.WHITE    + LH.get("gt6.tooltip.multiblock.gasturbine.2"));
		aList.add(Chat.WHITE    + LH.get("gt6.tooltip.multiblock.gasturbine.3"));
		aList.add(Chat.ORANGE   + LH.get("gt6.tooltip.multiblock.gasturbine.4"));
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	@Override
	public void addToolTipsEnergy(List<String> aList, ItemStack aStack, boolean aF3_H) {
		mEnergyOUT.addToolTips(aList, aStack, aF3_H, null, T);
	}
	
	@Override
	public void doConversion(long aTimer) {
		if (mStorage.mEnergy >= mConverter.mEnergyIN.mMax) {
			// hacking my own code, lol
			long tEnergy = mStorage.mEnergy;
			mStorage.mEnergy = mConverter.mEnergyIN.mMax;
			super.doConversion(aTimer);
			mStorage.mEnergy = tEnergy - mConverter.mEnergyIN.mMax;
			return;
		}
		if (!mStopped && !mInputTank.isEmpty() && !(mTanksOutput[0].isHalf() || mTanksOutput[1].isHalf() || mTanksOutput[2].isHalf())) {
			Recipe tRecipe = mRecipes.findRecipe(this, mLastRecipe, F, mEnergyIN.mMax, NI, mInputTank.AS_ARRAY, ZL_IS);
			if (tRecipe != null) {
				mLastRecipe = tRecipe;
				if (tRecipe.mEUt < 0 && tRecipe.mDuration > 0) {
					int tMax = UT.Code.bindInt(UT.Code.divup(mEnergyIN.mMax - mStorage.mEnergy, -tRecipe.mEUt * tRecipe.mDuration)), tParallel = tRecipe.isRecipeInputEqual(tMax, mInputTank.AS_ARRAY, ZL_IS);
					if (tParallel < tMax) mInputTank.setEmpty();
					if (tParallel > 0) {
						mStorage.mEnergy -= tParallel * tRecipe.mEUt * tRecipe.mDuration;
						for (int i = 0; i < tRecipe.mFluidOutputs.length && i < mTanksOutput.length; i++) {
							if (!mTanksOutput[i].fillAll(tRecipe.mFluidOutputs[i], tParallel)) {
								mStorage.mEnergy = 0;
							}
						}
						super.doConversion(aTimer);
						return;
					}
				}
			}
		}
		mStorage.mEnergy -= mConverter.mEnergyIN.mMax;
		if (mStorage.mEnergy < 0) mStorage.mEnergy = 0;
		super.doConversion(aTimer);
	}
	
	@Override protected IFluidTank getFluidTankFillable2(byte aSide, FluidStack aFluidToFill) {return !mStopped && mRecipes.containsInput(aFluidToFill, this, NI) ? mInputTank : null;}
	@Override protected IFluidTank[] getFluidTanks2(byte aSide) {return mTanks;}
	
	@Override
	protected IFluidTank getFluidTankDrainable2(byte aSide, FluidStack aFluidToDrain) {
		if (aFluidToDrain == null) {
			for (int i = 0; i < mTanksOutput.length; i++) if (!mTanksOutput[i].isEmpty()) return mTanksOutput[i];
		} else {
			for (int i = 0; i < mTanksOutput.length; i++) if (mTanksOutput[i].contains(aFluidToDrain)) return mTanksOutput[i];
		}
		return null;
	}
	
	@Override public String getTileEntityName() {return "gt6.multitileentity.multiblock.turbine.gas";}
}
