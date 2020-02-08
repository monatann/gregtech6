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

package gregapi6.item.multiitem.food;

import static gregapi6.data.CS.*;

import java.util.List;

import gregapi6.data.CS.DrinksGT;
import gregapi6.data.LH;
import gregapi6.data.MD;
import gregapi6.util.UT;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public class FoodStatDrink extends FoodStat {
	public final String mFluid;
	
	public FoodStatDrink(FluidStack aFluid, String aToolTip, int aFoodLevel, float aSaturation, float aHydration, float aTemperature, float aTemperatureEffect, int aAlcohol, int aCaffeine, int aDehydration, int aSugar, int aFat, EnumAction aAction, boolean aAlwaysEdible, boolean aInvisibleParticles, boolean aIsRotten, int... aPotionEffects) {
		this(aFluid.getFluid(), aToolTip, aFoodLevel, aSaturation, aHydration, aTemperature, aTemperatureEffect, aAlcohol, aCaffeine, aDehydration, aSugar, aFat, aAction, aAlwaysEdible, aInvisibleParticles, aIsRotten, aPotionEffects);
	}
	public FoodStatDrink(Fluid aFluid, String aToolTip, int aFoodLevel, float aSaturation, float aHydration, float aTemperature, float aTemperatureEffect, int aAlcohol, int aCaffeine, int aDehydration, int aSugar, int aFat, EnumAction aAction, boolean aAlwaysEdible, boolean aInvisibleParticles, boolean aIsRotten, int... aPotionEffects) {
		this(aFluid.getName(), aToolTip, aFoodLevel, aSaturation, aHydration, aTemperature, aTemperatureEffect, aAlcohol, aCaffeine, aDehydration, aSugar, aFat, aAction, aAlwaysEdible, aInvisibleParticles, aIsRotten, aPotionEffects);
	}
	public FoodStatDrink(String aFluid, String aToolTip, int aFoodLevel, float aSaturation, float aHydration, float aTemperature, float aTemperatureEffect, int aAlcohol, int aCaffeine, int aDehydration, int aSugar, int aFat, EnumAction aAction, boolean aAlwaysEdible, boolean aInvisibleParticles, boolean aIsRotten, int... aPotionEffects) {
		super(aFoodLevel, aSaturation, aHydration, aTemperature, aTemperatureEffect, aAlcohol, aCaffeine, aDehydration, aSugar, aFat, aAction, NI, aAlwaysEdible || DRINKS_ALWAYS_DRINKABLE || MD.ENVM.mLoaded, aInvisibleParticles, aIsRotten, T, aPotionEffects);
		mFluid = aFluid;
		if (UT.Code.stringValid(mFluid)) {
			LH.add("gt6.drink." + mFluid, aToolTip);
			DrinksGT.REGISTER.put(mFluid, this);
		}
	}
	
	public FoodStatDrink(FluidStack aFluid, String aToolTip, int aFoodLevel, float aSaturation, float aHydration, float aTemperature, float aTemperatureEffect, EnumAction aAction, boolean aAlwaysEdible, boolean aInvisibleParticles, boolean aIsRotten, int... aPotionEffects) {
		this(aFluid.getFluid(), aToolTip, aFoodLevel, aSaturation, aHydration, aTemperature, aTemperatureEffect, aAction, aAlwaysEdible, aInvisibleParticles, aIsRotten, aPotionEffects);
	}
	public FoodStatDrink(Fluid aFluid, String aToolTip, int aFoodLevel, float aSaturation, float aHydration, float aTemperature, float aTemperatureEffect, EnumAction aAction, boolean aAlwaysEdible, boolean aInvisibleParticles, boolean aIsRotten, int... aPotionEffects) {
		this(aFluid.getName(), aToolTip, aFoodLevel, aSaturation, aHydration, aTemperature, aTemperatureEffect, aAction, aAlwaysEdible, aInvisibleParticles, aIsRotten, aPotionEffects);
	}
	public FoodStatDrink(String aFluid, String aToolTip, int aFoodLevel, float aSaturation, float aHydration, float aTemperature, float aTemperatureEffect, EnumAction aAction, boolean aAlwaysEdible, boolean aInvisibleParticles, boolean aIsRotten, int... aPotionEffects) {
		super(aFoodLevel, aSaturation, aHydration, aTemperature, aTemperatureEffect, aAction, NI, aAlwaysEdible || DRINKS_ALWAYS_DRINKABLE || MD.ENVM.mLoaded, aInvisibleParticles, aIsRotten, T, aPotionEffects);
		mFluid = aFluid;
		if (UT.Code.stringValid(mFluid)) {
			LH.add("gt6.drink." + mFluid, aToolTip);
			DrinksGT.REGISTER.put(mFluid, this);
		}
	}
	
	@Override
	public void addAdditionalToolTips(Item aItem, List<String> aList, ItemStack aStack, boolean aF3_H) {
		String tTooltip = LH.get("gt6.drink." + mFluid, "");
		if (UT.Code.stringValid(tTooltip)) aList.add(tTooltip);
		super.addAdditionalToolTips(aItem, aList, aStack, aF3_H);
	}
}
