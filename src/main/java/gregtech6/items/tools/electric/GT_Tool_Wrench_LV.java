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

import static gregapi6.data.CS.*;

import gregapi6.data.MT;
import gregapi6.data.OP;
import gregapi6.item.multiitem.MultiItemTool;
import gregapi6.item.multiitem.behaviors.Behavior_Switch_Metadata;
import gregapi6.old.Textures;
import gregapi6.render.IIconContainer;
import gregtech6.items.tools.machine.GT_Tool_Wrench;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GT_Tool_Wrench_LV extends GT_Tool_Wrench {
	public final int mSwitchIndex;
	
	public GT_Tool_Wrench_LV(int aSwitchIndex) {
		mSwitchIndex = aSwitchIndex;
	}
	
	@Override
	public float getNormalDamageAgainstEntity(float aOriginalDamage, Entity aEntity, ItemStack aStack, EntityPlayer aPlayer) {
		return aOriginalDamage;
	}
	
	@Override
	public int getToolDamagePerBlockBreak() {
		return 50;
	}
	
	@Override
	public int getToolDamagePerDropConversion() {
		return 100;
	}
	
	@Override
	public int getToolDamagePerContainerCraft() {
		return 800;
	}
	
	@Override
	public int getToolDamagePerEntityAttack() {
		return 200;
	}
	
	@Override
	public int getBaseQuality() {
		return 0;
	}
	
	@Override
	public float getBaseDamage() {
		return 1.0F;
	}
	
	@Override
	public float getSpeedMultiplier() {
		return 2.0F;
	}
	
	@Override
	public float getMaxDurabilityMultiplier() {
		return 1.0F;
	}
	
	@Override
	public boolean canBlock() {
		return F;
	}
	
	@Override
	public boolean isMinableBlock(Block aBlock, byte aMetaData) {
		if (super.isMinableBlock(aBlock, aMetaData)) return T;
		String tTool = aBlock.getHarvestTool(aMetaData);
		return (tTool != null && tTool.equals(TOOL_monkeywrench));
	}
	
	@Override
	public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? Textures.ItemIcons.POWER_UNIT_LV : MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mTextureSetsItems.get(OP.toolHeadWrench.mIconIndexItem);
	}
	
	@Override
	public short[] getRGBa(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? MultiItemTool.getSecondaryMaterial(aStack, MT.StainlessSteel).mRGBaSolid : MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mRGBaSolid;
	}
	
	@Override
	public void onStatsAddedToTool(MultiItemTool aItem, int aID) {
		super.onStatsAddedToTool(aItem, aID);
		aItem.addItemBehavior(aID, new Behavior_Switch_Metadata(mSwitchIndex));
	}
}
