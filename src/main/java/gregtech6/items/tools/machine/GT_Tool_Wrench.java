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

import java.util.Arrays;
import java.util.List;

import gregapi6.data.CS.SFX;
import gregapi6.data.MT;
import gregapi6.item.multiitem.MultiItemTool;
import gregapi6.item.multiitem.behaviors.Behavior_Tool;
import gregapi6.item.multiitem.tools.ToolStats;
import gregapi6.old.Textures;
import gregapi6.render.IIconContainer;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class GT_Tool_Wrench extends ToolStats {
	public static final List<String> mEffectiveList = Arrays.asList(
		"EntityTowerGuardian"
	);
	
	@Override
	public float getNormalDamageAgainstEntity(float aOriginalDamage, Entity aEntity, ItemStack aStack, EntityPlayer aPlayer) {
		String tName = aEntity.getClass().getName();
		tName = tName.substring(tName.lastIndexOf(".")+1);
		return aEntity instanceof EntityIronGolem || mEffectiveList.contains(tName) || tName.contains("Golem") ? aOriginalDamage*2 : aOriginalDamage;
	}
	
	@Override public int getToolDamagePerBlockBreak()                                       {return  50;}
	@Override public int getToolDamagePerContainerCraft()                                   {return 800;}
	@Override public int getToolDamagePerEntityAttack()                                     {return 200;}
	@Override public int getHurtResistanceTime(int aOriginalHurtResistance, Entity aEntity) {return aOriginalHurtResistance * 2;}
	@Override public float getBaseDamage()                                                  {return 5.0F;}
	@Override public float getExhaustionPerAttack(Entity aEntity)                           {return 0.6F;}
	@Override public String getCraftingSound()                                              {return SFX.GT_WRENCH;}
	@Override public String getMiningSound()                                                {return SFX.GT_WRENCH;}
	@Override public boolean canCollect()                                                   {return T;}
	@Override public boolean isWrench()                                                     {return T;}
	
	@Override
	public boolean isMinableBlock(Block aBlock, byte aMetaData) {
		String tTool = aBlock.getHarvestTool(aMetaData);
		return (tTool != null && tTool.equals(TOOL_wrench)) || aBlock.getMaterial() == Material.piston || aBlock.getMaterial() == Material.redstoneLight || aBlock == Blocks.hopper || aBlock == Blocks.dispenser || aBlock == Blocks.dropper;
	}
	
	@Override
	public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? Textures.ItemIcons.WRENCH : Textures.ItemIcons.VOID;
	}
	
	@Override
	public short[] getRGBa(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mRGBaSolid : UNCOLOURED;
	}
	
	@Override
	public void onStatsAddedToTool(MultiItemTool aItem, int aID) {
		aItem.addItemBehavior(aID, new Behavior_Tool(TOOL_wrench, SFX.GT_WRENCH, 100, !canBlock()));
	}
	
	@Override
	public String getDeathMessage() {
		return "[KILLER] gave [VICTIM] a whack with the Wrench!";
	}
}
