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
import java.util.List;

import gregapi6.code.ArrayListNoNulls;
import gregapi6.data.IL;
import gregapi6.data.LH;
import gregapi6.gui.Slot_Normal;
import gregapi6.gui.Slot_Whitelist;
import gregapi6.item.IItemGTHandTool;
import gregapi6.oredict.OreDictManager;
import gregapi6.random.IHasWorldAndCoords;
import gregapi6.recipes.ICraftingRecipeGT;
import gregapi6.recipes.Recipe;
import gregapi6.recipes.Recipe.RecipeMap;
import gregapi6.tileentity.ITileEntityInventoryGUI;
import gregapi6.tileentity.computer.ITileEntityUSBPort;
import gregapi6.tileentity.delegate.DelegatorTileEntity;
import gregapi6.util.CR;
import gregapi6.util.OM;
import gregapi6.util.ST;
import gregapi6.util.UT;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author Gregorius Techneticies
 */
public class RecipeMapAutocrafting extends RecipeMap {
	public RecipeMapAutocrafting(Collection<Recipe> aRecipeList, String aUnlocalizedName, String aNameLocal, String aNameNEI, long aProgressBarDirection, long aProgressBarAmount, String aNEIGUIPath, long aInputItemsCount, long aOutputItemsCount, long aMinimalInputItems, long aInputFluidCount, long aOutputFluidCount, long aMinimalInputFluids, long aMinimalInputs, long aPower, String aNEISpecialValuePre, long aNEISpecialValueMultiplier, String aNEISpecialValuePost, boolean aShowVoltageAmperageInNEI, boolean aNEIAllowed, boolean aConfigAllowed, boolean aNeedsOutputs) {
		super(aRecipeList, aUnlocalizedName, aNameLocal, aNameNEI, aProgressBarDirection, aProgressBarAmount, aNEIGUIPath, aInputItemsCount, aOutputItemsCount, aMinimalInputItems, aInputFluidCount, aOutputFluidCount, aMinimalInputFluids, aMinimalInputs, aPower, aNEISpecialValuePre, aNEISpecialValueMultiplier, aNEISpecialValuePost, aShowVoltageAmperageInNEI, aNEIAllowed, aConfigAllowed, aNeedsOutputs);
	}
	
	public static final List<IRecipe> ALLOWED_RECIPES = new ArrayListNoNulls<>();
	public static final List<IRecipe> RECENT_RECIPES = new ArrayListNoNulls<>();
	
	@Override
	public Recipe findRecipe(IHasWorldAndCoords aTileEntity, Recipe aRecipe, boolean aNotUnificated, long aSize, ItemStack aSpecialSlot, FluidStack[] aFluids, ItemStack... aInputs) {
		Recipe rRecipe = super.findRecipe(aTileEntity, aRecipe, aNotUnificated, aSize, aSpecialSlot, aFluids, aInputs);
		if (rRecipe != null || aSpecialSlot == null || aInputs == null || aInputs.length < 1 || GAPI_POST.mFinishedServerStarted <= 0) return rRecipe;
		
		ItemStack[] tBlueprint = getBlueprint(aTileEntity, aSpecialSlot);
		
		if (tBlueprint.length <= 0) return null;
		
		for (ItemStack tPlan : tBlueprint) if (tPlan != null && tPlan.getItem() instanceof IItemGTHandTool) return null;
		
		if (ALLOWED_RECIPES.isEmpty()) {
			for (Object tCraftingRecipe : CR.list()) if (tCraftingRecipe instanceof IRecipe) {
				if (!(tCraftingRecipe instanceof ICraftingRecipeGT) || ((ICraftingRecipeGT)tCraftingRecipe).isAutocraftableByGT()) {
					ALLOWED_RECIPES.add((IRecipe)tCraftingRecipe);
				}
			}
		}
		
		InventoryCrafting tCraftInv = new InventoryCrafting(new Container() {@Override public boolean canInteractWith(EntityPlayer var1) {return F;}}, 3, 3);
		for (int i = 0; i < 9; i++) tCraftInv.setInventorySlotContents(i, tBlueprint[i]);
		
		IRecipe tIRecipe = null;
		
		for (int i = 0, j = RECENT_RECIPES.size(); i < j; i++) {
			if (RECENT_RECIPES.get(i).matches(tCraftInv, aTileEntity == null ? DW : aTileEntity.getWorld())) {
				tIRecipe = RECENT_RECIPES.get(i);
				break;
			}
		}
		
		if (tIRecipe == null) for (int i = 0, j = ALLOWED_RECIPES.size(); i < j; i++) {
			if (ALLOWED_RECIPES.get(i).matches(tCraftInv, aTileEntity == null ? DW : aTileEntity.getWorld())) {
				tIRecipe = ALLOWED_RECIPES.get(i);
				ALLOWED_RECIPES.remove(i);
				RECENT_RECIPES.add(tIRecipe);
				break;
			}
		}
		
		if (tIRecipe == null) return null;
		
		ItemStack tOutput = tIRecipe.getCraftingResult(tCraftInv);
		
		if (ST.invalid(tOutput)) return null; 
		
		ArrayListNoNulls<ItemStack> tInputs  = new ArrayListNoNulls<>();
		ArrayListNoNulls<ItemStack> tOutputs = new ArrayListNoNulls<>(F, tOutput);
		
		for (ItemStack tPlan : tBlueprint) if (tPlan != null) {
			boolean temp = T;
			for (ItemStack tInput : tInputs) if (ST.equal(tInput, tPlan, F)) {
				tInput.stackSize++;
				tOutputs.add(ST.container(tPlan, F));
				temp = F;
			}
			if (temp) {
				tInputs .add(ST.amount(1, tPlan));
				tOutputs.add(ST.container(tPlan, F));
			}
		}
		
		for (ItemStack tInput : tInputs) if (OM.is_("gt:autocrafterinfinite", tInput)) tInput.stackSize = 0;
		
		return new Recipe(T, F, T, tInputs.toArray(ZL_IS), tOutputs.toArray(ZL_IS), null, null, null, null, 1024, 16, 0);
	}
	
	public ItemStack[] getBlueprint(IHasWorldAndCoords aTileEntity, ItemStack aSpecialSlot) {
		ItemStack[] rBlueprint = ZL_IS;
		
		if (IL.Paper_Blueprint_Used.equal(aSpecialSlot, F, T)) {
			rBlueprint = UT.NBT.getBlueprintCrafting(aSpecialSlot);
		} else if (OM.is_(OD_USB_STICKS[1], aSpecialSlot)) {
			if (!aSpecialSlot.hasTagCompound()) return rBlueprint;
			NBTTagCompound tData = aSpecialSlot.getTagCompound().getCompoundTag(NBT_USB_DATA);
			if (tData == null) return rBlueprint;
			rBlueprint = UT.NBT.getBlueprintCrafting(tData);
		} else if (OM.is_(OD_USB_CABLES[1], aSpecialSlot)) {
			if (aTileEntity == null) return rBlueprint;
			for (byte tSide : ALL_SIDES_VALID_ONLY[aSpecialSlot.hasTagCompound() && aSpecialSlot.getTagCompound().hasKey(NBT_USB_DIRECTION) ? aSpecialSlot.getTagCompound().getByte(NBT_USB_DIRECTION) : SIDE_ANY]) {
				DelegatorTileEntity<TileEntity> tDelegator = aTileEntity.getAdjacentTileEntity(tSide);
				if (tDelegator.mTileEntity instanceof ITileEntityUSBPort) {
					NBTTagCompound tData = ((ITileEntityUSBPort)tDelegator.mTileEntity).getUSBData(tDelegator.mSideOfTileEntity, 1);
					if (tData == null) continue;
					rBlueprint = UT.NBT.getBlueprintCrafting(tData);
					if (rBlueprint.length > 0) return rBlueprint;
				}
			}
		}
		
		return rBlueprint;
	}
	
	@Override
	public boolean containsInput(ItemStack aStack, IHasWorldAndCoords aTileEntity, ItemStack aSpecialSlot) {
		ItemStack[] tRecipe = getBlueprint(aTileEntity, aSpecialSlot);
		for (ItemStack tStack : tRecipe) if (ST.equal(tStack, aStack, F)) return T;
		return super.containsInput(aStack, aTileEntity, aSpecialSlot);
	}
	
	@Override
	public Slot_Normal getSpecialSlot(ITileEntityInventoryGUI aInventory, int aIndex, int aX, int aY) {
		return new Slot_Whitelist(aInventory, aIndex, aX, aY, OreDictManager.getOres("gt:autocrafterblueprintitem", F).toArray(ZL_IS)).setTooltip(LH.AUTOCRAFTING_INSERT_BLUEPRINT, LH.Chat.WHITE);
	}
}
