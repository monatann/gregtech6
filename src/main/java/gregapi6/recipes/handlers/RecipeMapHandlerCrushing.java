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

package gregapi6.recipes.handlers;

import static gregapi6.data.CS.*;
import static gregapi6.data.OP.*;

import gregapi6.data.CS.BlocksGT;
import gregapi6.data.OP;
import gregapi6.data.TD;
import gregapi6.oredict.OreDictItemData;
import gregapi6.oredict.OreDictMaterial;
import gregapi6.oredict.OreDictMaterialStack;
import gregapi6.recipes.IRecipeMapHandler.RecipeMapHandler;
import gregapi6.recipes.Recipe;
import gregapi6.recipes.Recipe.RecipeMap;
import gregapi6.util.OM;
import gregapi6.util.ST;
import gregapi6.util.UT;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class RecipeMapHandlerCrushing extends RecipeMapHandler {
	public RecipeMapHandlerCrushing() {/**/}
	
	@Override
	public boolean addRecipesUsing(RecipeMap aMap, ItemStack aInput, OreDictItemData aData) {
		if (aData == null || !aData.hasValidPrefixMaterialData() || aData.mPrefix == oreBedrock || !aData.mPrefix.contains(TD.Prefix.ORE) || aData.mPrefix.contains(TD.Prefix.DUST_ORE) || aData.mMaterial.mMaterial.contains(TD.Atomic.ANTIMATTER)) return F;
		OreDictMaterial aCrushedMat = aData.mMaterial.mMaterial.mTargetCrushing.mMaterial;
		long aCrushedAmount = aData.mMaterial.mMaterial.mTargetCrushing.mAmount, aMultiplier = aData.mMaterial.mMaterial.mOreMultiplier * aData.mMaterial.mMaterial.mOreProcessingMultiplier;
		
		if (aData.mPrefix == orePoor) {
			ItemStack tOutput = OP.crushedTiny          .mat(aCrushedMat, UT.Code.bindStack(UT.Code.units(aCrushedAmount, U, 3 * aMultiplier, F)));
			if (tOutput == null) tOutput = OP.dustTiny  .mat(aCrushedMat, UT.Code.bindStack(UT.Code.units(aCrushedAmount, U, 3 * aMultiplier, F)));
			return ST.valid(tOutput) && null != aMap.addRecipe(new Recipe(F, F, T, ST.array(ST.amount(1, aInput)), ST.array(tOutput), null, null, ZL_FS, ZL_FS, Math.max(1, 16*tOutput.stackSize*Math.max(1, aData.mMaterial.mMaterial.mToolQuality+1)), 16, 0));
		}
		if (aData.mPrefix == oreSmall || aData.mPrefix == oreRich || aData.mPrefix == oreNormal) {
			// TODO
			return F;
		}
		ItemStack[] tOutputs = new ItemStack[aMap.mOutputItemsCount];
		tOutputs[0] = OP.crushed.mat(aCrushedMat, UT.Code.bindStack(UT.Code.units(aCrushedAmount, U, aMultiplier, F)));
		if (tOutputs[0] == null) tOutputs[0] = OP.dust.mat(aCrushedMat, UT.Code.bindStack(UT.Code.units(aCrushedAmount, U, aMultiplier, F)));
		if (tOutputs[0] == null) tOutputs[0] = OP.gem.mat(aCrushedMat, UT.Code.bindStack(UT.Code.units(aCrushedAmount, U, aMultiplier, F)));
		if (tOutputs[0] == null) return F;
		int i = 1, tDuration = 128*tOutputs[0].stackSize*Math.max(1, aData.mMaterial.mMaterial.mToolQuality+1);
		tOutputs[i++] = ST.copy_(tOutputs[0]);
		if (aData.mPrefix.contains(TD.Prefix.DENSE_ORE)) {
			tOutputs[i++] = ST.copy_(tOutputs[0]);
			tOutputs[i++] = ST.copy_(tOutputs[0]);
			tDuration *= 2;
		}
		for (OreDictMaterialStack tMaterial : aData.mPrefix.mByProducts) {
			tDuration += UT.Code.units(tMaterial.mAmount, U, 64*Math.max(1, tMaterial.mMaterial.mToolQuality+1), T);
			if (i < tOutputs.length) {
				ItemStack tStack = OM.dust(tMaterial.mMaterial.mTargetCrushing.mMaterial, UT.Code.units(tMaterial.mAmount, U, tMaterial.mMaterial.mTargetCrushing.mAmount, F));
				if (tStack != null) tOutputs[i++] = tStack;
			}
		}
		return null != aMap.addRecipe(new Recipe(F, F, T, ST.array(ST.amount(1, aInput)), tOutputs, null, null, ZL_FS, ZL_FS, Math.max(1, tDuration), 16, 0));
	}
	
	@Override
	public boolean addRecipesProducing(RecipeMap aMap, ItemStack aStack, OreDictItemData aData) {
		if (BlocksGT.ore != null && aData != null && aData.hasValidPrefixMaterialData() && (aData.mPrefix == OP.crushed || aData.mPrefix == OP.dust || aData.mPrefix == OP.gem)) {
			boolean temp = F;
			for (OreDictMaterial tMaterial : OreDictMaterial.MATERIAL_ARRAY) if (tMaterial != null && tMaterial.mTargetCrushing.mMaterial == aData.mMaterial.mMaterial && OP.oreVanillastone.isGeneratingItem(aData.mMaterial.mMaterial)) {
				if (addRecipesUsing(aMap, ST.make((Block)BlocksGT.ore, 1, aData.mMaterial.mMaterial.mID), OP.oreVanillastone.dat(tMaterial))) temp = T;
			}
			return temp;
		}
		return F;
	}
}
