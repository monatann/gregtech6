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

package gregtech6.items.behaviors;

import static gregapi6.data.CS.*;

import java.util.List;

import forestry.api.lepidopterology.EnumFlutterType;
import forestry.api.lepidopterology.IButterfly;
import forestry.api.lepidopterology.IButterflyRoot;
import forestry.api.lepidopterology.IEntityButterfly;
import gregapi6.data.LH;
import gregapi6.item.multiitem.MultiItem;
import gregapi6.item.multiitem.MultiItemTool;
import gregapi6.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi6.util.UT;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class Behavior_Scoop extends AbstractBehaviorDefault {
	private final int mCosts;
	
	public Behavior_Scoop(int aCosts) {
		mCosts = aCosts;
	}
	
	@Override
	public boolean onLeftClickEntity(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, Entity aEntity) {
		if (aEntity instanceof IEntityButterfly) {
			if (aPlayer.worldObj.isRemote) return T;
			if (UT.Entities.hasInfiniteItems(aPlayer) || ((MultiItemTool)aItem).doDamage(aStack, mCosts, aPlayer)) {
				Object tButterfly = ((IEntityButterfly)aEntity).getButterfly(), tRoot = ((IButterfly)tButterfly).getGenome().getPrimary().getRoot();
				((IButterflyRoot)tRoot).getBreedingTracker(aEntity.worldObj, aPlayer.getGameProfile()).registerCatch(((IButterfly)tButterfly));
				UT.Inventories.addStackToPlayerInventoryOrDrop(aPlayer, ((IButterflyRoot)tRoot).getMemberStack(((IButterfly)tButterfly).copy(), EnumFlutterType.BUTTERFLY.ordinal()), F);
				aEntity.setDead();
			}
			return T;
		}
		return F;
	}
	
	@Override
	public boolean onRightClickEntity(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, Entity aEntity) {
		return onLeftClickEntity(aItem, aStack, aPlayer, aEntity);
	}
	
	static {
		LH.add("gt6.behaviour.scoop", "Catches Butterflies");
	}
	
	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		aList.add(LH.get("gt6.behaviour.scoop"));
		return aList;
	}
}
