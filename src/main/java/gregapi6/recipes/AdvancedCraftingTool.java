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

import gregapi6.code.ICondition;
import gregapi6.data.CS.ToolsGT;
import gregapi6.data.MT;
import gregapi6.data.OP;
import gregapi6.item.multiitem.MultiItemTool;
import gregapi6.oredict.OreDictItemData;
import gregapi6.oredict.OreDictMaterial;
import gregapi6.oredict.OreDictPrefix;
import gregapi6.util.OM;
import gregapi6.util.ST;
import gregapi6.util.UT;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapelessOreRecipe;

/**
 * @author Gregorius Techneticies
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class AdvancedCraftingTool extends ShapelessOreRecipe implements ICraftingRecipeGT {
	public final ICondition mCondition;
	public final OreDictPrefix mToolHead;
	public final short mToolID;
	public final MultiItemTool mTool;
	
	public AdvancedCraftingTool(long aToolID, OreDictPrefix aToolHead) {this(ToolsGT.sMetaTool, aToolID, aToolHead, ICondition.TRUE, MT.Steel);}
	public AdvancedCraftingTool(long aToolID, OreDictPrefix aToolHead, OreDictMaterial aHead) {this(ToolsGT.sMetaTool, aToolID, aToolHead, ICondition.TRUE, aHead);}
	public AdvancedCraftingTool(long aToolID, OreDictPrefix aToolHead, ICondition aCondition) {this(ToolsGT.sMetaTool, aToolID, aToolHead, aCondition, MT.Steel);}
	public AdvancedCraftingTool(long aToolID, OreDictPrefix aToolHead, ICondition aCondition, OreDictMaterial aHead) {this(ToolsGT.sMetaTool, aToolID, aToolHead, aCondition, aHead);}
	public AdvancedCraftingTool(MultiItemTool aTool, long aToolID, OreDictPrefix aToolHead) {this(aTool, aToolID, aToolHead, ICondition.TRUE, MT.Steel);}
	public AdvancedCraftingTool(MultiItemTool aTool, long aToolID, OreDictPrefix aToolHead, OreDictMaterial aHead) {this(aTool, aToolID, aToolHead, ICondition.TRUE, aHead);}
	public AdvancedCraftingTool(MultiItemTool aTool, long aToolID, OreDictPrefix aToolHead, ICondition aCondition) {this(aTool, aToolID, aToolHead, aCondition, MT.Steel);}
	public AdvancedCraftingTool(MultiItemTool aTool, long aToolID, OreDictPrefix aToolHead, ICondition aCondition, OreDictMaterial aHead) {
		super(aTool.getToolWithStats(UT.Code.bind15(aToolID), aHead, aHead.mHandleMaterial), aToolHead.dat(aHead).toString(), OP.stick.dat(aHead.mHandleMaterial).toString());
		mTool = aTool;
		mCondition = aCondition;
		mToolHead = aToolHead;
		mToolID = UT.Code.bind15(aToolID);
	}
	
	@Override
	public boolean matches(InventoryCrafting aGrid, World aWorld) {
		ItemStack tStack = null;
		OreDictMaterial rHead = null, rRod = null;
		for (int i = 0; i < aGrid.getSizeInventory(); i++) {
			tStack = aGrid.getStackInSlot(i);
			if (ST.valid(tStack)) {
				OreDictItemData tData = OM.anydata_(tStack);
				if (tData == null) return F;
				if (tData.mPrefix == OP.stick) {
					if (rRod != null) return F;
					rRod = tData.mMaterial.mMaterial;
				} else if (tData.mPrefix == mToolHead) {
					if (rHead != null) return F;
					rHead = tData.mMaterial.mMaterial;
				} else {
					return F;
				}
			}
		}
		return rHead != null && rRod != null && mCondition.isTrue(rHead) && (rHead.mHandleMaterial == rRod || rRod.mReRegistrations.contains(rHead.mHandleMaterial));
	}
	
	@Override
	public ItemStack getCraftingResult(InventoryCrafting aGrid) {
		ItemStack tStack = null;
		OreDictMaterial rHead = null, rRod = null;
		for (int i = 0; i < aGrid.getSizeInventory(); i++) {
			tStack = aGrid.getStackInSlot(i);
			if (ST.valid(tStack)) {
				OreDictItemData tData = OM.anydata_(tStack);
				if (tData == null) continue;
				if (tData.mPrefix == OP.stick) {
					rRod = tData.mMaterial.mMaterial;
				} else if (tData.mPrefix == mToolHead) {
					rHead = tData.mMaterial.mMaterial;
				}
			}
		}
		return mTool.getToolWithStats(mToolID, rHead, rRod);
	}
	
	@Override public boolean isRemovableByGT() {return F;}
	@Override public boolean isAutocraftableByGT() {return T;}
	@Override public int getRecipeSize() {return 2;}
}
