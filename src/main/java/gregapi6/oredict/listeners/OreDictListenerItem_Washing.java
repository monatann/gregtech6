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

package gregapi6.oredict.listeners;

import static gregapi6.data.CS.*;

import gregapi6.code.ArrayListNoNulls;
import gregapi6.data.LH;
import gregapi6.lang.LanguageHandler;
import gregapi6.oredict.OreDictMaterial;
import gregapi6.oredict.OreDictPrefix;
import gregapi6.oredict.listeners.IOreDictListenerItem.OreDictListenerItem;
import gregapi6.util.ST;
import gregapi6.util.UT;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCauldron;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class OreDictListenerItem_Washing extends OreDictListenerItem {
	private final OreDictPrefix mItemToGet, mByProductPrefixes[];
	private final int mChance;
	
	/**
	 * @param aItemToGet the Main Item you get from washing this Prefix.
	 * @param aChance the Chance of getting a secondary Output.
	 * @param aByProductPrefixes the Prefixes it can select from, if Items of those Prefixes exist. You can add the same Prefix multiple times to weight it.
	 */
	public OreDictListenerItem_Washing(OreDictPrefix aItemToGet, int aChance, OreDictPrefix... aByProductPrefixes) {
		LH.add("gt6.behaviour.washing", "Throw into Cauldron to clean this Item");
		mByProductPrefixes = aByProductPrefixes;
		mItemToGet = aItemToGet;
		mChance = aChance;
	}
	
	@Override
	public ItemStack onTickWorld(OreDictPrefix aPrefix, OreDictMaterial aMaterial, ItemStack aStack, EntityItem aItem) {
		if (aMaterial != null) {
			int tX = UT.Code.roundDown(aItem.posX), tY = UT.Code.roundDown(aItem.posY-0.25), tZ = UT.Code.roundDown(aItem.posZ);
			Block tBlock = aItem.worldObj.getBlock(tX, tY, tZ);
			byte tMetaData = (byte)aItem.worldObj.getBlockMetadata(tX, tY, tZ);
			
			if (tBlock instanceof BlockCauldron && tMetaData > 0) {
				ItemStack tStack = mItemToGet.mat(aMaterial, 1);
				if (tStack != null) {
					((BlockCauldron)tBlock).func_150024_a(aItem.worldObj, tX, tY, tZ, tMetaData-1);
					if (mByProductPrefixes.length > 0 && RNGSUS.nextInt(mChance) > 0) {
						ArrayListNoNulls<ItemStack> tStacks = new ArrayListNoNulls<>();
						for (OreDictPrefix tPrefix : mByProductPrefixes) tStacks.add(tPrefix.mat(aMaterial.mByProducts.size()>0?UT.Code.select(RNGSUS.nextInt(aMaterial.mByProducts.size()), aMaterial, aMaterial.mByProducts):aMaterial, 1));
						if (tStacks.size() > 0) ST.drop(aItem.worldObj, aItem.posX, aItem.posY, aItem.posZ, tStacks.get(RNGSUS.nextInt(tStacks.size())));
					}
					ST.drop(aItem.worldObj, aItem.posX, aItem.posY, aItem.posZ, tStack);
					return aStack.stackSize > 1 ? ST.amount(aStack.stackSize - 1, aStack) : null;
				}
			}
		}
		return aStack;
	}
	
	@Override
	public String getListenerToolTip(OreDictPrefix aPrefix, OreDictMaterial aMaterial, ItemStack aStack) {
		return LanguageHandler.translate("gt6.behaviour.washing", "Throw into Cauldron to clean this Item");
	}
}
