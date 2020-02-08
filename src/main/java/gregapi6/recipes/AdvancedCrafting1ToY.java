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

package gregapi6.recipes;

import static gregapi6.data.CS.*;

import java.util.List;

import gregapi6.code.ICondition;
import gregapi6.oredict.OreDictItemData;
import gregapi6.oredict.OreDictMaterial;
import gregapi6.oredict.OreDictPrefix;
import gregapi6.util.CR;
import gregapi6.util.OM;
import gregapi6.util.ST;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

/**
 * @author Gregorius Techneticies
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class AdvancedCrafting1ToY implements ICraftingRecipeGT {
	public final ICondition mCondition;
	public final OreDictPrefix mInput, mOutput;
	public final boolean mAutoCraftable;
	public final int mOutputCount, mEmpty;
	
	public AdvancedCrafting1ToY(OreDictPrefix aInput, OreDictPrefix aOutput, int aOutputCount, boolean aAutoCraftable) {this(aInput, aOutput, aOutputCount, aAutoCraftable, ICondition.TRUE);}
	public AdvancedCrafting1ToY(OreDictPrefix aInput, OreDictPrefix aOutput, int aOutputCount, boolean aAutoCraftable, ICondition aCondition) {
		mAutoCraftable = aAutoCraftable;
		mCondition = aCondition;
		mInput = aInput;
		mEmpty = mInput.mShapelessManagersSingle.size();
		mOutput = aOutput;
		mOutputCount = aOutputCount;
		mInput.mShapelessManagersSingle.add(this);
		
		List<IRecipe> tRecipeList = CR.list();
		try {for (int i = 0; i < tRecipeList.size(); i++) {
			IRecipe tRecipe = tRecipeList.get(i);
			int tCount = 0;
			
			if (tRecipeList.get(i) instanceof ICraftingRecipeGT) {
				// NOTHING
			} else if (tRecipe instanceof ShapedOreRecipe) {
				Object[] tInputs = ((ShapedOreRecipe)tRecipe).getInput();
				
				if (tInputs != null) for (Object tObject : tInputs) if (tObject != null) {
					if (++tCount > 1) {
						tCount = 0;
						break;
					}
					if (tObject instanceof ItemStack) {
						OreDictItemData tData = OM.data((ItemStack)tObject);
						if (tData == null || tData.mPrefix != mInput || tData.mMaterial == null || !mCondition.isTrue(tData.mMaterial.mMaterial)) {
							tCount = 0;
							break;
						}
					} else if (tObject instanceof List) {
						if (((List)tObject).isEmpty()) {
							tCount = 0;
							break;
						}
						if (((List)tObject).get(0) instanceof ItemStack) {
							OreDictItemData tData = OM.data((ItemStack)((List)tObject).get(0));
							if (tData == null || tData.mPrefix != mInput || tData.mMaterial == null || !mCondition.isTrue(tData.mMaterial.mMaterial)) {
								tCount = 0;
								break;
							}
						}
					} else {
						tCount = 0;
						break;
					}
				}
			} else if (tRecipe instanceof ShapelessOreRecipe) {
				List tInputs = ((ShapelessOreRecipe)tRecipe).getInput();
				
				if (tInputs != null && tInputs.size() == 1) for (Object tObject : tInputs) if (tObject != null) {
					tCount++;
					if (tObject instanceof ItemStack) {
						OreDictItemData tData = OM.data((ItemStack)tObject);
						if (tData == null || tData.mPrefix != mInput || tData.mMaterial == null || !mCondition.isTrue(tData.mMaterial.mMaterial)) {
							tCount = 0;
							break;
						}
					} else if (tObject instanceof List) {
						if (((List)tObject).isEmpty()) {
							tCount = 0;
							break;
						}
						if (((List)tObject).get(0) instanceof ItemStack) {
							OreDictItemData tData = OM.data((ItemStack)((List)tObject).get(0));
							if (tData == null || tData.mPrefix != mInput || tData.mMaterial == null || !mCondition.isTrue(tData.mMaterial.mMaterial)) {
								tCount = 0;
								break;
							}
						}
					} else {
						tCount = 0;
						break;
					}
				}
			} else if (tRecipe instanceof ShapedRecipes) {
				ItemStack[] tInputs = ((ShapedRecipes)tRecipe).recipeItems;
				
				if (tInputs != null) for (ItemStack tObject : tInputs) if (tObject != null) {
					if (++tCount > 1) {
						tCount = 0;
						break;
					}
					OreDictItemData tData = OM.data(tObject);
					if (tData == null || tData.mPrefix != mInput || tData.mMaterial == null || !mCondition.isTrue(tData.mMaterial.mMaterial)) {
						tCount = 0;
						break;
					}
				}
			} else if (tRecipe instanceof ShapelessRecipes) {
				List tInputs = ((ShapelessRecipes)tRecipe).recipeItems;
				
				if (tInputs != null && tInputs.size() == 1) for (Object tObject : tInputs) if (tObject != null) {
					tCount++;
					if (tObject instanceof ItemStack) {
						OreDictItemData tData = OM.data((ItemStack)tObject);
						if (tData == null || tData.mPrefix != mInput || tData.mMaterial == null || !mCondition.isTrue(tData.mMaterial.mMaterial)) {
							tCount = 0;
							break;
						}
					} else {
						tCount = 0;
						break;
					}
				}
			}
			
			if (tCount == 1) tRecipeList.remove(i--);
			
		}} catch(Throwable e) {e.printStackTrace(ERR);}
	}
	
	@Override
	public boolean matches(InventoryCrafting aGrid, World aWorld) {
		ItemStack tStack = null;
		OreDictMaterial rMaterial = null;
		
		int tInventorySize = aGrid.getSizeInventory(), tCounter = 0, tEmpty = 0;
		if (tInventorySize < 1+mEmpty) return F;
		for (int i = 0; i < tInventorySize; i++) {
			tStack = aGrid.getStackInSlot(i);
			if (ST.valid(tStack)) {
				OreDictItemData tData = OM.anydata_(tStack);
				if (tData == null || tData.mPrefix != mInput) return F;
				if (rMaterial == null) rMaterial = tData.mMaterial.mMaterial; else if (rMaterial != tData.mMaterial.mMaterial) return F;
				tCounter++;
				continue;
			}
			if (tCounter == 0) tEmpty++;
			if (i-tCounter+1+mEmpty-tEmpty >= tInventorySize) return F;
		}
		return rMaterial != null && tCounter == 1 && tEmpty % mInput.mShapelessManagersSingle.size() == mEmpty && hasOutputFor(rMaterial);
	}
	
	@Override
	public ItemStack getCraftingResult(InventoryCrafting aGrid) {
		for (int i = 0, j = aGrid.getSizeInventory(); i < j; i++) {
			OreDictItemData tData = OM.anydata(aGrid.getStackInSlot(i));
			if (tData == null || tData.mMaterial == null) continue;
			return mOutput.mat(tData.mMaterial.mMaterial, mOutputCount);
		}
		return ERROR_OUTPUT.copy();
	}
	
	public boolean hasOutputFor(OreDictMaterial aMaterial) {
		return ST.valid(mOutput.mat(aMaterial, mOutputCount)) && mCondition.isTrue(aMaterial);
	}
	
	@Override public boolean isRemovableByGT() {return F;}
	@Override public boolean isAutocraftableByGT() {return mAutoCraftable;}
	@Override public int getRecipeSize() {return 1;}
	@Override public ItemStack getRecipeOutput() {return ERROR_OUTPUT.copy();}
}
