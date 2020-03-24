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

package gregapi6.recipes.maps;

import static gregapi6.data.CS.*;

import java.util.Collection;

import gregapi6.data.IL;
import gregapi6.random.IHasWorldAndCoords;
import gregapi6.recipes.Recipe;
import gregapi6.recipes.Recipe.RecipeMap;
import gregapi6.util.ST;
import gregapi6.util.UT;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author Gregorius Techneticies
 */
public class RecipeMapUnboxinator extends RecipeMap {
	public RecipeMapUnboxinator(Collection<Recipe> aRecipeList, String aUnlocalizedName, String aNameLocal, String aNameNEI, long aProgressBarDirection, long aProgressBarAmount, String aNEIGUIPath, long aInputItemsCount, long aOutputItemsCount, long aMinimalInputItems, long aInputFluidCount, long aOutputFluidCount, long aMinimalInputFluids, long aMinimalInputs, long aPower, String aNEISpecialValuePre, long aNEISpecialValueMultiplier, String aNEISpecialValuePost, boolean aShowVoltageAmperageInNEI, boolean aNEIAllowed, boolean aConfigAllowed, boolean aNeedsOutputs, boolean aCombinePower) {
		super(aRecipeList, aUnlocalizedName, aNameLocal, aNameNEI, aProgressBarDirection, aProgressBarAmount, aNEIGUIPath, aInputItemsCount, aOutputItemsCount, aMinimalInputItems, aInputFluidCount, aOutputFluidCount, aMinimalInputFluids, aMinimalInputs, aPower, aNEISpecialValuePre, aNEISpecialValueMultiplier, aNEISpecialValuePost, aShowVoltageAmperageInNEI, aNEIAllowed, aConfigAllowed, aNeedsOutputs, aCombinePower, 0);
	}

	@Override
	public Recipe findRecipe(IHasWorldAndCoords aTileEntity, Recipe aRecipe, boolean aNotUnificated, long aSize, ItemStack aSpecialSlot, FluidStack[] aFluids, ItemStack... aInputs) {
		if (aInputs == null || aInputs.length <= 0 || aInputs[0] == null) return super.findRecipe(aTileEntity, aRecipe, aNotUnificated, aSize, aSpecialSlot, aFluids, aInputs);
		if (COMPAT_IC2 != null && IL.IC2_Scrapbox.equal(aInputs[0], F, T)) {
			ItemStack tOutput = COMPAT_IC2.scrapbox(aInputs[0]);
			if (tOutput == null) return new Recipe(F, F, F, aInputs, aInputs, null, null, null, null, 1, 1, 0);
			// Due to the randomness it is not good if there are Items in the Output Slot, because those Items could manipulate the outcome.
			return new Recipe(F, F, F, ST.array(IL.IC2_Scrapbox.get(1)), ST.array(tOutput), null, null, null, null, 16, 16, 0).setNeedEmptyOut();
		}
		if (IL.LOOTBAGS_Bag_0.equal(aInputs[0], T, T)) {
			ItemStack tBag = ST.amount(1, aInputs[0]);
			UT.Reflection.callPrivateMethod(tBag.getItem().getClass(), "generateInventory", tBag);
			// Due to the randomness it is not good if there are Items in the Output Slot, because those Items could manipulate the outcome.
			return new Recipe(F, F, F, ST.array(ST.amount(1, aInputs[0])), tBag.hasTagCompound() ? (ItemStack[])UT.Reflection.callPublicMethod(tBag.getItem().getClass(), "getInventory", tBag) : ZL_IS, null, null, null, null, 16, 16, 0).setNeedEmptyOut();
		}
		return super.findRecipe(aTileEntity, aRecipe, aNotUnificated, aSize, aSpecialSlot, aFluids, aInputs);
	}
}
