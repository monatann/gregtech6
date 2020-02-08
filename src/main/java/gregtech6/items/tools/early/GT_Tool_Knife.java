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

package gregtech6.items.tools.early;

import static gregapi6.data.CS.*;

import gregapi6.data.CS.SFX;
import gregapi6.item.multiitem.MultiItemTool;
import gregapi6.item.multiitem.behaviors.Behavior_Tool;
import gregapi6.old.Textures;
import gregapi6.render.IIconContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.AchievementList;

public class GT_Tool_Knife extends GT_Tool_Sword {
	@Override
	public int getToolDamagePerBlockBreak() {
		return 100;
	}
	
	@Override
	public int getToolDamagePerDropConversion() {
		return 100;
	}
	
	@Override
	public int getToolDamagePerContainerCraft() {
		return 100;
	}
	
	@Override
	public int getToolDamagePerEntityAttack() {
		return 200;
	}
	
	@Override
	public float getBaseDamage() {
		return 2.0F;
	}
	
	@Override
	public float getSpeedMultiplier() {
		return 0.5F;
	}
	
	@Override
	public float getMaxDurabilityMultiplier() {
		return 1.0F;
	}
	
	@Override public boolean canCollect()                                                   {return T;}
	
	@Override
	public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
		return !aIsToolHead ? Textures.ItemIcons.KNIFE : Textures.ItemIcons.VOID;
	}
	
	@Override
	public String getDeathMessage() {
		return "<[VICTIM]> [KILLER] what are you doing?, [KILLER]?!? STAHP!!!";
	}
	
	@Override
	public void onStatsAddedToTool(MultiItemTool aItem, int aID) {
		aItem.addItemBehavior(aID, new Behavior_Tool(TOOL_knife, SFX.MC_DIG_CLOTH, getToolDamagePerContainerCraft(), !canBlock()));
	}
	
	@Override
	public void onToolCrafted(ItemStack aStack, EntityPlayer aPlayer) {
		super.onToolCrafted(aStack, aPlayer);
		aPlayer.triggerAchievement(AchievementList.buildSword);
	}
}
