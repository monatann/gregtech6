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

import gregapi6.data.MT;
import gregapi6.data.OP;
import gregapi6.item.multiitem.MultiItemTool;
import gregapi6.render.IIconContainer;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

public class GT_Tool_AxeDouble extends GT_Tool_Axe {
	@Override
	public float getBaseDamage() {
		return 6.0F;
	}
	
	@Override
	public float getMaxDurabilityMultiplier() {
		return super.getMaxDurabilityMultiplier() * 1.5F;
	}
	
	@Override
	public int getHurtResistanceTime(int aOriginalHurtResistance, Entity aEntity) {
		return aOriginalHurtResistance*2;
	}
	
	@Override
	public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mTextureSetsItems.get(OP.toolHeadAxeDouble.mIconIndexItem) : MultiItemTool.getSecondaryMaterial(aStack, MT.Wood).mTextureSetsItems.get(OP.stick.mIconIndexItem);
	}
	
	@Override
	public String getDeathMessage() {
		return "[VICTIM] got beheaded by [KILLER]";
	}
}
