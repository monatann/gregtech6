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
import gregapi6.util.UT;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author Gregorius Techneticies
 */
public class RecipeMapAssembler extends RecipeMap {
	public RecipeMapAssembler(Collection<Recipe> aRecipeList, String aUnlocalizedName, String aNameLocal, String aNameNEI, long aProgressBarDirection, long aProgressBarAmount, String aNEIGUIPath, long aInputItemsCount, long aOutputItemsCount, long aMinimalInputItems, long aInputFluidCount, long aOutputFluidCount, long aMinimalInputFluids, long aMinimalInputs, long aPower, String aNEISpecialValuePre, long aNEISpecialValueMultiplier, String aNEISpecialValuePost, boolean aShowVoltageAmperageInNEI, boolean aNEIAllowed, boolean aConfigAllowed, boolean aNeedsOutputs) {
		super(aRecipeList, aUnlocalizedName, aNameLocal, aNameNEI, aProgressBarDirection, aProgressBarAmount, aNEIGUIPath, aInputItemsCount, aOutputItemsCount, aMinimalInputItems, aInputFluidCount, aOutputFluidCount, aMinimalInputFluids, aMinimalInputs, aPower, aNEISpecialValuePre, aNEISpecialValueMultiplier, aNEISpecialValuePost, aShowVoltageAmperageInNEI, aNEIAllowed, aConfigAllowed, aNeedsOutputs);
	}
	
	@Override
	public Recipe findRecipe(IHasWorldAndCoords aTileEntity, Recipe aRecipe, boolean aNotUnificated, long aSize, ItemStack aSpecialSlot, FluidStack[] aFluids, ItemStack... aInputs) {
		Recipe rRecipe = super.findRecipe(aTileEntity, aRecipe, aNotUnificated, aSize, aSpecialSlot, aFluids, aInputs);
		if (aInputs == null || aInputs.length <= 0 || aInputs[0] == null || rRecipe == null || GAPI_POST.mFinishedServerStarted <= 0) return rRecipe;
		for (ItemStack aInput : aInputs) {
			if (IL.Paper_Printed_Pages.equal(aInput, F, T) || IL.Paper_Printed_Pages_Many.equal(aInput, F, T)) {
				rRecipe = rRecipe.copy();
				rRecipe.mCanBeBuffered = F;
				UT.NBT.set(rRecipe.mOutputs[0], aInput.getTagCompound());
			}
		}
		return rRecipe;
	}
}
