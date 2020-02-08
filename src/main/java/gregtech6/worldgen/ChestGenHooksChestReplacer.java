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

package gregtech6.worldgen;

import static gregapi6.data.CS.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import gregapi6.block.multitileentity.MultiTileEntityRegistry;
import gregapi6.util.UT;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;

/**
 * @author Gregorius Techneticies
 */
public class ChestGenHooksChestReplacer extends ChestGenHooks {
	public final ChestGenHooks mHookToReplaceChestsOf;
	public final String mCategory;
	
	// MineTweaker does Reflection the wrong way...
	@SuppressWarnings("rawtypes")
	public ArrayList contents;
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	public ChestGenHooksChestReplacer(String aCategory) {
		super(aCategory);
		mCategory = aCategory;
		mHookToReplaceChestsOf = ChestGenHooks.getInfo(aCategory);
		super.setMin(mHookToReplaceChestsOf.getMin());
		super.setMax(mHookToReplaceChestsOf.getMax());
		try {
			Field tField = ChestGenHooks.class.getDeclaredField("chestInfo");
			tField.setAccessible(T);
			((HashMap)tField.get(null)).put(aCategory, this);
		} catch(Throwable e) {e.printStackTrace(ERR);}
		try {
			Field tField = ChestGenHooks.class.getDeclaredField("contents");
			tField.setAccessible(T);
			tField.set(this, contents = (ArrayList)(tField.get(mHookToReplaceChestsOf)));
		} catch(Throwable e) {e.printStackTrace(ERR);}
	}
	
	@Override
	public WeightedRandomChestContent[] getItems(Random aRandom) {
		WeightedRandomChestContent[] rReturn = mHookToReplaceChestsOf.getItems(aRandom);
		if (GAPI.mStartedServerStarted < 1 || aRandom == RNGSUS) return rReturn;
		for (int i = 0; i < rReturn.length; i++) rReturn[i] = new WeightedRandomChestContentChestReplacer(rReturn[i], mCategory);
		return rReturn;
	}
	
	@Override public void addItem(WeightedRandomChestContent aItem) {mHookToReplaceChestsOf.addItem(aItem);}
	@Override public void removeItem(ItemStack aStack) {mHookToReplaceChestsOf.removeItem(aStack);}
	@Override public int getCount(Random aRandom) {return mHookToReplaceChestsOf.getCount(aRandom);}
	@Override public ItemStack getOneItem(Random aRandom) {return mHookToReplaceChestsOf.getOneItem(aRandom);}
	@Override public int getMin() {return mHookToReplaceChestsOf.getMin();}
	@Override public int getMax() {return mHookToReplaceChestsOf.getMax();}
	@Override public void setMin(int aValue) {mHookToReplaceChestsOf.setMin(aValue); super.setMin(aValue);}
	@Override public void setMax(int aValue) {mHookToReplaceChestsOf.setMax(aValue); super.setMax(aValue);}
	
	public static class WeightedRandomChestContentChestReplacer extends WeightedRandomChestContent {
		public final WeightedRandomChestContent mContent;
		public final String mCategory;
		
		public WeightedRandomChestContentChestReplacer(WeightedRandomChestContent aContent, String aCategory) {
			super(aContent.theItemId, aContent.theMinimumChanceToGenerateItem, aContent.theMaximumChanceToGenerateItem, aContent.itemWeight);
			mCategory = aCategory;
			mContent = aContent;
		}
		
		@Override
		protected ItemStack[] generateChestContent(Random aRandom, IInventory aInventory) {
			if (aInventory.getClass() != TileEntityChest.class || ((TileEntityChest)aInventory).getWorldObj() == null || Blocks.chest != ((TileEntityChest)aInventory).getWorldObj().getBlock(((TileEntityChest)aInventory).xCoord, ((TileEntityChest)aInventory).yCoord, ((TileEntityChest)aInventory).zCoord)) return generateChestContent2(aRandom, aInventory);
			MultiTileEntityRegistry tRegistry = MultiTileEntityRegistry.getRegistry("gt6.multitileentity");
			if (tRegistry == null) return generateChestContent2(aRandom, aInventory);
			tRegistry.mBlock.placeBlock(((TileEntityChest)aInventory).getWorldObj(), ((TileEntityChest)aInventory).xCoord, ((TileEntityChest)aInventory).yCoord, ((TileEntityChest)aInventory).zCoord, SIDE_UNKNOWN, (short)32745, UT.NBT.make(NBT_FACING, VALIDATE_HORIZONTAL[((TileEntityChest)aInventory).getWorldObj().getBlockMetadata(((TileEntityChest)aInventory).xCoord, ((TileEntityChest)aInventory).yCoord, ((TileEntityChest)aInventory).zCoord)], "gt6.dungeonloot", mCategory), F, T);
			return ZL_IS;
		}
		
		protected ItemStack[] generateChestContent2(Random aRandom, IInventory aInventory) {
			try {
				Method tMethod = mContent.getClass().getDeclaredMethod("generateChestContent", Random.class, IInventory.class);
				tMethod.setAccessible(T);
				return (ItemStack[])tMethod.invoke(mContent, aRandom, aInventory);
			} catch(Throwable e) {
				e.printStackTrace(ERR);
			}
			return super.generateChestContent(aRandom, aInventory);
		}
	}
}
