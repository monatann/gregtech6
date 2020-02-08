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

package gregtech6.items.tools.electric;

import gregapi6.data.MT;
import gregapi6.data.OP;
import gregapi6.item.multiitem.MultiItemTool;
import gregapi6.old.Textures;
import gregapi6.render.IIconContainer;
import net.minecraft.item.ItemStack;

public class GT_Tool_MiningDrill_HV extends GT_Tool_MiningDrill_LV {
	@Override
	public int getToolDamagePerBlockBreak() {
		return 400;
	}
	
	@Override
	public int getToolDamagePerDropConversion() {
		return 1600;
	}
	
	@Override
	public int getToolDamagePerContainerCraft() {
		return 12800;
	}
	
	@Override
	public int getToolDamagePerEntityAttack() {
		return 3200;
	}
	
	@Override
	public int getBaseQuality() {
		return 1;
	}
	
	@Override
	public float getBaseDamage() {
		return 3.0F;
	}
	
	@Override
	public float getSpeedMultiplier() {
		return 9.0F;
	}
	
	@Override
	public float getMaxDurabilityMultiplier() {
		return 4.0F;
	}
	
	@Override
	public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? Textures.ItemIcons.POWER_UNIT_HV : MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mTextureSetsItems.get(OP.toolHeadDrill.mIconIndexItem);
	}
}
