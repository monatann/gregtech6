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
import static gregapi6.data.OP.*;

import java.util.Collection;
import java.util.List;

import gregapi6.code.ArrayListNoNulls;
import gregapi6.code.ItemStackContainer;
import gregapi6.data.OP;
import gregapi6.data.TD;
import gregapi6.oredict.OreDictItemData;
import gregapi6.oredict.OreDictMaterial;
import gregapi6.oredict.OreDictMaterialStack;
import gregapi6.recipes.Recipe;
import gregapi6.util.OM;
import gregapi6.util.ST;
import gregapi6.util.UT;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class RecipeMapHammer extends RecipeMapSpecialSingleInput {
	public RecipeMapHammer(Collection<Recipe> aRecipeList, String aUnlocalizedName, String aNameLocal, String aNameNEI, long aProgressBarDirection, long aProgressBarAmount, String aNEIGUIPath, long aInputItemsCount, long aOutputItemsCount, long aMinimalInputItems, long aInputFluidCount, long aOutputFluidCount, long aMinimalInputFluids, long aMinimalInputs, long aPower, String aNEISpecialValuePre, long aNEISpecialValueMultiplier, String aNEISpecialValuePost, boolean aShowVoltageAmperageInNEI, boolean aNEIAllowed, boolean aConfigAllowed, boolean aNeedsOutputs) {
		super(aRecipeList, aUnlocalizedName, aNameLocal, aNameNEI, aProgressBarDirection, aProgressBarAmount, aNEIGUIPath, aInputItemsCount, aOutputItemsCount, aMinimalInputItems, aInputFluidCount, aOutputFluidCount, aMinimalInputFluids, aMinimalInputs, aPower, aNEISpecialValuePre, aNEISpecialValueMultiplier, aNEISpecialValuePost, aShowVoltageAmperageInNEI, aNEIAllowed, aConfigAllowed, aNeedsOutputs);
	}
	
	private List<Recipe> mBufferedDynamicRecipes = null;
	
	@Override
	public List<Recipe> getNEIAllRecipes() {
		List<Recipe> rList = super.getNEIAllRecipes();
		if (mBufferedDynamicRecipes == null) {
			mBufferedDynamicRecipes = new ArrayListNoNulls<>();
			for (OreDictMaterial tMaterial : OP.crushed.mRegisteredMaterials) {
				for (ItemStackContainer tStack : tMaterial.mRegisteredItems) {
					mBufferedDynamicRecipes.add(getRecipeFor(tStack.toStack()));
				}
			}
		}
		rList.addAll(mBufferedDynamicRecipes);
		return rList;
	}
	
	@Override
	public List<Recipe> getNEIRecipes(ItemStack... aOutputs) {
		List<Recipe> rList = super.getNEIRecipes(aOutputs);
		for (ItemStack aOutput : aOutputs) {
			OreDictItemData aData = OM.anyassociation(aOutput);
			if (aData != null && aData.mPrefix == OP.crushed) {
				for (ItemStackContainer tStack : aData.mMaterial.mMaterial.mRegisteredItems) {
					rList.add(getRecipeFor(tStack.toStack()));
				}
				break;
			}
		}
		return rList;
	}
	
	@Override
	protected Recipe getRecipeFor(ItemStack aInput) {
		OreDictItemData aData = OM.anydata(aInput);
		if (aData == null || (aData.mMaterial != null && aData.mMaterial.mMaterial.contains(TD.Atomic.ANTIMATTER)) || (aData.mPrefix == null || !aData.mPrefix.contains(TD.Prefix.ORE) || aData.mPrefix.contains(TD.Prefix.DUST_ORE) || aData.mPrefix == oreBedrock)) return null;
		OreDictMaterial aCrushedMat = aData.mMaterial.mMaterial.mTargetCrushing.mMaterial;
		long aCrushedAmount = aData.mMaterial.mMaterial.mTargetCrushing.mAmount, aMultiplier = aData.mMaterial.mMaterial.mOreMultiplier * aData.mMaterial.mMaterial.mOreProcessingMultiplier * (aData.mPrefix.contains(TD.Prefix.DENSE_ORE) ? 2 : 1);
		
		if (aData.mPrefix == orePoor) {
			ItemStack tOutput = OP.crushedTiny          .mat(aCrushedMat, UT.Code.units(aCrushedAmount, U, 2 * aMultiplier, F));
			if (tOutput == null) tOutput = OP.dustTiny  .mat(aCrushedMat, UT.Code.units(aCrushedAmount, U, 2 * aMultiplier, F));
			return tOutput == null ? null : new Recipe(F, F, T, ST.array(ST.amount(1, aInput)), ST.array(tOutput), null, null, ZL_FS, ZL_FS, Math.max(1, 16*tOutput.stackSize*Math.max(1, aData.mMaterial.mMaterial.mToolQuality+1)), 16, 0);
		}
		if (aData.mPrefix == oreSmall || aData.mPrefix == oreRich || aData.mPrefix == oreNormal) {
			// TODO
			return null;
		}
		ItemStack[] tOutputs = new ItemStack[Math.max(1, mOutputItemsCount)];
		tOutputs[0] = OP.crushed.mat(aCrushedMat, UT.Code.units(aCrushedAmount, U, aMultiplier, F));
		if (tOutputs[0] == null) tOutputs[0] = OP.dust.mat(aCrushedMat, UT.Code.units(aCrushedAmount, U, aMultiplier, F));
		if (tOutputs[0] == null) return null;
		int i = 0, tDuration = 32*tOutputs[0].stackSize*Math.max(1, aData.mMaterial.mMaterial.mToolQuality+1);
		for (OreDictMaterialStack tMaterial : aData.mPrefix.mByProducts) {
			tDuration += UT.Code.units(tMaterial.mAmount, U, 64*Math.max(1, tMaterial.mMaterial.mToolQuality+1), T);
			if (i < tOutputs.length - 1) {
				ItemStack tStack = OM.dust(tMaterial.mMaterial.mTargetCrushing.mMaterial, UT.Code.units(tMaterial.mAmount, U, tMaterial.mMaterial.mTargetCrushing.mAmount, F));
				if (tStack != null) tOutputs[++i] = tStack;
			}
		}
		return new Recipe(F, F, T, ST.array(ST.amount(1, aInput)), tOutputs, null, null, ZL_FS, ZL_FS, Math.max(1, tDuration), 16, 0);
	}
}
