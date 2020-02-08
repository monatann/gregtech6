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
import gregapi6.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi6.util.UT;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class Behavior_FeedPig extends AbstractBehaviorDefault {
	public static final Behavior_FeedPig INSTANCE = new Behavior_FeedPig();
	
	@Override
	public boolean onRightClickEntity(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, Entity aEntity) {
		if (aEntity instanceof EntityPig) {
			if (((EntityAnimal)aEntity).getGrowingAge() == 0 && !((EntityAnimal)aEntity).isInLove()) {
				UT.Entities.consumeCurrentItem(aPlayer);
				((EntityAnimal)aEntity).func_146082_f(aPlayer);
				return T;
			}
		}
		return F;
	}
	
	static {
		LH.add("gt6.behaviour.feed.pig", "Is usable as Pig Food");
	}
	
	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		aList.add(LH.get("gt6.behaviour.feed.pig"));
		return aList;
	}
}
