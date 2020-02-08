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
import net.minecraft.item.ItemStack;

public class GT_Tool_PickaxeGem extends GT_Tool_Pickaxe {
	@Override public float getMaxDurabilityMultiplier() {return super.getMaxDurabilityMultiplier() / 4;}
	
	@Override
	public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? MultiItemTool.getPrimaryMaterial(aStack, MT.Diamond).mTextureSetsItems.get(OP.toolHeadPickaxeGem.mIconIndexItem) : MultiItemTool.getSecondaryMaterial(aStack, MT.Wood).mTextureSetsItems.get(OP.stick.mIconIndexItem);
	}
	
	@Override
	public short[] getRGBa(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? MultiItemTool.getPrimaryMaterial(aStack, MT.Diamond).mRGBaSolid : MultiItemTool.getSecondaryMaterial(aStack, MT.Wood).mRGBaSolid;
	}
}
