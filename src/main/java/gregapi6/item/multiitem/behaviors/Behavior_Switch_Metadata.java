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

import gregapi6.item.multiitem.MultiItem;
import gregapi6.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi6.util.ST;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Behavior_Switch_Metadata extends AbstractBehaviorDefault {
	public final int mSwitchIndex;
	
	public Behavior_Switch_Metadata(int aSwitchIndex) {
		mSwitchIndex = aSwitchIndex;
	}
	
	@Override
	public boolean onItemUseFirst(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (aStack != null && (aPlayer == null || aPlayer.isSneaking()) && !aWorld.isRemote) {
			ST.update_(ST.meta_(aStack, mSwitchIndex), aWorld, aX, aY, aZ);
			return T;
		}
		return F;
	}
}
