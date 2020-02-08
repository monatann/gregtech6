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

import gregapi6.code.TagData;
import gregapi6.item.IItemEnergy;
import gregapi6.item.multiitem.MultiItemTool;
import gregapi6.util.ST;
import gregapi6.util.UT;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapelessOreRecipe;

/**
 * @author Gregorius Techneticies
 */
public class AdvancedCraftingShapeless extends ShapelessOreRecipe implements ICraftingRecipeGT {
	public final boolean mDismantleable, mRemovableByGT, mAutoCraftable, mKeepingNBT;
	private final Enchantment[] mEnchantmentsAdded;
	private final int[] mEnchantmentLevelsAdded;
	
	public AdvancedCraftingShapeless(ItemStack aResult, boolean aDismantleAble, boolean aRemovableByGT, boolean aKeepingNBT, boolean aAutoCraftable, Enchantment[] aEnchantmentsAdded, int[] aEnchantmentLevelsAdded, Object... aRecipe) {
		super(aResult, aRecipe);
		mEnchantmentsAdded = aEnchantmentsAdded;
		mEnchantmentLevelsAdded = aEnchantmentLevelsAdded;
		mRemovableByGT = aRemovableByGT;
		mKeepingNBT = aKeepingNBT;
		mDismantleable = aDismantleAble;
		mAutoCraftable = aAutoCraftable;
	}
	
	@Override
	public boolean matches(InventoryCrafting aGrid, World aWorld) {
		if (mKeepingNBT) {
			ItemStack tStack = null, tMainInput = ((getInput().get(0) instanceof ItemStack) ? (ItemStack)getInput().get(0) : null);
			for (int i = 0; i < aGrid.getSizeInventory(); i++) {
				if (aGrid.getStackInSlot(i) != null) {
					if (tMainInput == null || ST.equal_(aGrid.getStackInSlot(i), tMainInput, T)) {
						if (tStack != null && !ST.equal_(tStack, aGrid.getStackInSlot(i), F)) return F;
						tStack = aGrid.getStackInSlot(i);
					}
				}
			}
		}
		return super.matches(aGrid, aWorld);
	}
	
	@Override
	public ItemStack getCraftingResult(InventoryCrafting aGrid) {
		ItemStack rStack = super.getCraftingResult(aGrid);
		if (rStack != null) {
			// Update the Stack
			ST.update(rStack);
			
			// Keeping NBT
			if (mKeepingNBT) {
				ItemStack tMainInput = ((getInput().get(0) instanceof ItemStack) ? (ItemStack)getInput().get(0) : null);
				for (int i = 0; i < aGrid.getSizeInventory(); i++) {
					if (aGrid.getStackInSlot(i) != null && aGrid.getStackInSlot(i).hasTagCompound() && (tMainInput == null || ST.equal_(aGrid.getStackInSlot(i), tMainInput, T))) {
						UT.NBT.set(rStack, (NBTTagCompound)aGrid.getStackInSlot(i).getTagCompound().copy());
						break;
					}
				}
			}
			
			// GT Charge Values
			if (rStack.getItem() instanceof IItemEnergy) {
				for (TagData tEnergyType : ((IItemEnergy)rStack.getItem()).getEnergyTypes(rStack)) {
					long tCharge = 0;
					for (int i = 0; i < aGrid.getSizeInventory(); i++) if (aGrid.getStackInSlot(i) != null && aGrid.getStackInSlot(i).getItem() instanceof IItemEnergy) {
						tCharge += ((IItemEnergy)aGrid.getStackInSlot(i).getItem()).getEnergyStored(tEnergyType, aGrid.getStackInSlot(i));
					}
					((IItemEnergy)rStack.getItem()).setEnergyStored(tEnergyType, rStack, tCharge);
				}
			}
			
			// Saving Ingredients inside the Item.
			if (mDismantleable) {
				NBTTagCompound rNBT = rStack.getTagCompound(), tNBT = UT.NBT.make();
				if (rNBT == null) rNBT = UT.NBT.make();
				for (int i = 0; i < 9; i++) {
					ItemStack tStack = aGrid.getStackInSlot(i);
					if (tStack != null && ST.container(tStack, true) == null && !(tStack.getItem() instanceof MultiItemTool)) {
						tStack = ST.amount(1, tStack);
						tNBT.setTag(""+i, ST.save(tStack));
					}
				}
				rNBT.setTag(NBT_RECYCLING_COMPS, tNBT);
				UT.NBT.set(rStack, rNBT);
			}
			
			// Add Enchantments
			for (int i = 0; i < mEnchantmentsAdded.length; i++) UT.NBT.addEnchantment(rStack, mEnchantmentsAdded[i], EnchantmentHelper.getEnchantmentLevel(mEnchantmentsAdded[i].effectId, rStack) + mEnchantmentLevelsAdded[i]);
			
			// Update the Stack again
			ST.update(rStack);
		}
		return rStack;
	}
	
	@Override
	public boolean isRemovableByGT() {
		return mRemovableByGT;
	}
	
	@Override
	public boolean isAutocraftableByGT() {
		return mAutoCraftable;
	}
}
