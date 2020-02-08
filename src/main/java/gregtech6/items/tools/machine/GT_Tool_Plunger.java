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

package gregtech6.items.tools.machine;

import static gregapi6.data.CS.*;

import gregapi6.data.CS.SFX;
import gregapi6.data.MT;
import gregapi6.item.multiitem.MultiItem;
import gregapi6.item.multiitem.MultiItemTool;
import gregapi6.item.multiitem.behaviors.Behavior_Tool;
import gregapi6.item.multiitem.behaviors.IBehavior;
import gregapi6.item.multiitem.tools.ToolStats;
import gregapi6.old.Textures;
import gregapi6.render.IIconContainer;
import gregapi6.util.UT;
import gregtech6.items.behaviors.Behavior_Plunger_Fluid;
import gregtech6.items.behaviors.Behavior_Plunger_Item;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class GT_Tool_Plunger extends ToolStats {
	@Override
	public float getBaseDamage() {
		return 1.25F;
	}
	
	@Override
	public float getMaxDurabilityMultiplier() {
		return 0.25F;
	}
	
	@Override
	public String getCraftingSound() {
		return SFX.IC_TRAMPOLINE;
	}
	
	@Override
	public String getEntityHitSound() {
		return SFX.IC_TRAMPOLINE;
	}
	
	@Override
	public String getMiningSound() {
		return SFX.IC_TRAMPOLINE;
	}
	
	@Override public boolean canCollect()                                                   {return T;}
	
	@Override
	public boolean isMinableBlock(Block aBlock, byte aMetaData) {
		String tTool = aBlock.getHarvestTool(aMetaData);
		return tTool != null && tTool.equalsIgnoreCase(TOOL_plunger);
	}
	
	@Override
	public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? Textures.ItemIcons.PLUNGER : Textures.ItemIcons.VOID;
	}
	
	@Override
	public short[] getRGBa(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mRGBaSolid : UNCOLOURED;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void onStatsAddedToTool(MultiItemTool aItem, int aID) {
		aItem.addItemBehavior(aID, new Behavior_Tool(TOOL_plunger, SFX.IC_TRAMPOLINE, 100, !canBlock()));
		aItem.addItemBehavior(aID, new Behavior_Plunger_Item(getToolDamagePerDropConversion()));
		aItem.addItemBehavior(aID, new Behavior_Plunger_Fluid(getToolDamagePerDropConversion()));
		try {
			Object tObject = UT.Reflection.callConstructor("gregtech6.common.items.behaviors.Behaviour_Plunger_Essentia", 0, null, false, getToolDamagePerDropConversion());
			if (tObject instanceof IBehavior<?>) aItem.addItemBehavior(aID, (IBehavior<MultiItem>)tObject);
		} catch (Throwable e) {/**/}
	}
	
	@Override
	public String getDeathMessage() {
		return "[VICTIM] got stuck trying to escape through a Pipe while fighting [KILLER]";
	}
}
