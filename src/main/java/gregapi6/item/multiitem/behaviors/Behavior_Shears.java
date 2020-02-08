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

package gregapi6.item.multiitem.behaviors;

import static gregapi6.data.CS.*;

import java.util.List;

import gregapi6.data.LH;
import gregapi6.item.multiitem.MultiItem;
import gregapi6.item.multiitem.MultiItemTool;
import gregapi6.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi6.util.UT;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IShearable;

public class Behavior_Shears extends AbstractBehaviorDefault {
	private final int mCosts;
	
	public Behavior_Shears(int aCosts) {
		mCosts = aCosts;
	}
	
	@Override
	public boolean onLeftClickEntity(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, Entity aEntity) {
		if (aEntity instanceof IShearable) {
			if (aPlayer.worldObj.isRemote) return T;
			if (((IShearable)aEntity).isShearable(aStack, aPlayer.worldObj, (int)aEntity.posX, (int)aEntity.posY, (int)aEntity.posZ) && (UT.Entities.hasInfiniteItems(aPlayer) || ((MultiItemTool)aItem).doDamage(aStack, mCosts, aPlayer))) {
				for(ItemStack tStack : ((IShearable)aEntity).onSheared(aStack, aPlayer.worldObj, (int)aEntity.posX, (int)aEntity.posY, (int)aEntity.posZ, EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, aStack))) {
					UT.Inventories.addStackToPlayerInventoryOrDrop(aPlayer, tStack, F);
				}
				return T;
			}
		}
		return F;
	}
	
	@Override
	public boolean onRightClickEntity(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, Entity aEntity) {
		return onLeftClickEntity(aItem, aStack, aPlayer, aEntity);
	}
	
	static {
		LH.add("gt6.behaviour.shears", "Shaves Sheep, Mooshrooms and alike");
	}
	
	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		aList.add(LH.get("gt6.behaviour.shears"));
		return aList;
	}
}
