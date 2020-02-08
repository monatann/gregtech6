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

import gregapi6.block.misc.BlockBaseBars;
import gregapi6.data.CS.SFX;
import gregapi6.data.MT;
import gregapi6.data.OP;
import gregapi6.item.multiitem.MultiItemTool;
import gregapi6.old.Textures;
import gregapi6.render.IIconContainer;
import gregtech6.items.tools.early.GT_Tool_Saw;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPane;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;

public class GT_Tool_BuzzSaw_LV extends GT_Tool_Saw {
	@Override
	public int getToolDamagePerContainerCraft() {
		return 100;
	}
	
	@Override
	public int getToolDamagePerEntityAttack() {
		return 300;
	}
	
	@Override
	public float getBaseDamage() {
		return 1.0F;
	}
	
	@Override
	public float getMaxDurabilityMultiplier() {
		return 1.0F;
	}
	
	@Override
	public String getCraftingSound() {
		return SFX.IC_CHAINSAW_01;
	}
	
	@Override
	public String getEntityHitSound() {
		return SFX.IC_CHAINSAW_02;
	}
	
	@Override
	public String getMiningSound() {
		return SFX.IC_CHAINSAW_01;
	}
	
	@Override
	public boolean isMinableBlock(Block aBlock, byte aMetaData) {
		return aBlock instanceof BlockBaseBars || (aBlock instanceof BlockPane && aBlock.getMaterial() == Material.iron);
	}
	
	@Override
	public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
		return !aIsToolHead ? MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mTextureSetsItems.get(OP.toolHeadBuzzSaw.mIconIndexItem) : Textures.ItemIcons.HANDLE_BUZZSAW;
	}
	
	@Override
	public short[] getRGBa(boolean aIsToolHead, ItemStack aStack) {
		return !aIsToolHead ? MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mRGBaSolid : MultiItemTool.getSecondaryMaterial(aStack, MT.StainlessSteel).mRGBaSolid;
	}
	
	@Override
	public String getDeathMessage() {
		return "[VICTIM] got buzzed by [KILLER]";
	}
}
