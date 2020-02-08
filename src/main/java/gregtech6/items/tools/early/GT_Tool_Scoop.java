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
import gregapi6.data.MD;
import gregapi6.data.MT;
import gregapi6.item.multiitem.MultiItem;
import gregapi6.item.multiitem.MultiItemTool;
import gregapi6.item.multiitem.behaviors.Behavior_Tool;
import gregapi6.item.multiitem.behaviors.IBehavior;
import gregapi6.item.multiitem.tools.ToolStats;
import gregapi6.old.Textures;
import gregapi6.render.IIconContainer;
import gregapi6.util.UT;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;

public class GT_Tool_Scoop extends ToolStats {
	public static Material sBeeHiveMaterial;
	
	@Override
	public int getToolDamagePerBlockBreak() {
		return 200;
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
	public int getBaseQuality() {
		return 0;
	}
	
	@Override
	public float getBaseDamage() {
		return 1.0F;
	}
	
	@Override
	public float getSpeedMultiplier() {
		return 1.0F;
	}
	
	@Override
	public float getMaxDurabilityMultiplier() {
		return 1.0F;
	}
	
	@Override public boolean canCollect()                                                   {return T;}
	
	@Override
	public boolean isMinableBlock(Block aBlock, byte aMetaData) {
		String tTool = aBlock.getHarvestTool(aMetaData);
		return (tTool != null && tTool.equalsIgnoreCase(TOOL_scoop)) || aBlock.getMaterial() == sBeeHiveMaterial;
	}
	
	@Override
	public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? Textures.ItemIcons.SCOOP : Textures.ItemIcons.VOID;
	}
	
	@Override
	public short[] getRGBa(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mRGBaSolid : UNCOLOURED;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void onStatsAddedToTool(MultiItemTool aItem, int aID) {
		aItem.addItemBehavior(aID, new Behavior_Tool(TOOL_scoop, SFX.MC_DIG_CLOTH, getToolDamagePerContainerCraft(), !canBlock()));
		try {
			Object tObject = UT.Reflection.callConstructor("gregtech6.items.behaviors.Behavior_Scoop", 0, null, F, 200);
			if (tObject instanceof IBehavior<?>) aItem.addItemBehavior(aID, (IBehavior<MultiItem>)tObject);
		} catch (Throwable e) {
			if (MD.FR.mLoaded) e.printStackTrace(ERR);
		}
	}
	
	@Override
	public String getDeathMessage() {
		return "[VICTIM] got scooped up by [KILLER]";
	}
}
