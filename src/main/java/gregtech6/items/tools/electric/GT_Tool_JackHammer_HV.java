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

import java.util.List;

import gregapi6.code.ArrayListNoNulls;
import gregapi6.data.CS.SFX;
import gregapi6.data.RM;
import gregapi6.item.multiitem.MultiItemTool;
import gregapi6.item.multiitem.behaviors.Behavior_Switch_Metadata;
import gregapi6.old.Textures;
import gregapi6.recipes.Recipe;
import gregapi6.render.IIconContainer;
import gregapi6.util.ST;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;

public class GT_Tool_JackHammer_HV extends GT_Tool_MiningDrill_LV {
	public final int mSwitchIndex;
	
	public GT_Tool_JackHammer_HV(int aSwitchIndex) {
		mSwitchIndex = aSwitchIndex;
	}
	
	@Override
	public int getToolDamagePerBlockBreak() {
		return 200;
	}
	
	@Override
	public int getToolDamagePerDropConversion() {
		return 200;
	}
	
	@Override
	public int getToolDamagePerContainerCraft() {
		return 3200;
	}
	
	@Override
	public int getToolDamagePerEntityAttack() {
		return 800;
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
		return 12.0F;
	}
	
	@Override
	public float getMaxDurabilityMultiplier() {
		return 2.0F;
	}
	
	@Override
	public String getCraftingSound() {
		return SFX.IC_DRILL_HARD;
	}
	
	@Override
	public String getEntityHitSound() {
		return SFX.IC_DRILL_HARD;
	}
	
	@Override
	public String getMiningSound() {
		return SFX.IC_DRILL_HARD;
	}
	
	@Override public boolean canCollect()                                                   {return F;}
	
	@Override
	public boolean isMinableBlock(Block aBlock, byte aMetaData) {
		String tTool = aBlock.getHarvestTool(aMetaData);
		return (tTool != null && tTool.equalsIgnoreCase("pickaxe")) || aBlock == Blocks.monster_egg || aBlock.getMaterial() == Material.rock || aBlock.getMaterial() == Material.glass || aBlock.getMaterial() == Material.ice || aBlock.getMaterial() == Material.packedIce;
	}
	
	@Override
	public int convertBlockDrops(List<ItemStack> aDrops, ItemStack aStack, EntityPlayer aPlayer, Block aBlock, long aAvailableDurability, int aX, int aY, int aZ, byte aMetaData, int aFortune, boolean aSilkTouch, BlockEvent.HarvestDropsEvent aEvent) {
		int rConversions = 0;
		Recipe tRecipe;
		if (aBlock.hasTileEntity(aMetaData) || null == (tRecipe = RM.Hammer.findRecipe(null, null, true, Integer.MAX_VALUE, null, ZL_FS, ST.make(aBlock, 1, aMetaData)))) {
			List<ItemStack> tDrops = new ArrayListNoNulls<>();
			for (int i = 0; i < aDrops.size(); i++) {
				tRecipe = RM.Hammer.findRecipe(null, null, true, Integer.MAX_VALUE, null, ZL_FS, ST.amount(1, aDrops.get(i)));
				if (tRecipe != null) {
					byte tStackSize = (byte)aDrops.get(i).stackSize;
					rConversions += tStackSize;
					aDrops.remove(i--);
					if (tRecipe.mOutputs.length > 0) for (byte j = 0; j < tStackSize; j++) {
						ItemStack[] tHammeringOutput = tRecipe.getOutputs();
						for (int k = 0; k < tHammeringOutput.length; k++) if (tHammeringOutput[k] != null) tDrops.add(tHammeringOutput[k]);
					}
				}
			}
			aDrops.addAll(tDrops);
		} else {
			aDrops.clear();
			ItemStack[] tHammeringOutput = tRecipe.getOutputs(RNGSUS);
			for (int k = 0; k < tHammeringOutput.length; k++) if (tHammeringOutput[k] != null) aDrops.add(tHammeringOutput[k]);
			rConversions++;
		}
		return rConversions;
	}
	
	@Override
	public void onStatsAddedToTool(MultiItemTool aItem, int aID) {
		aItem.addItemBehavior(aID, new Behavior_Switch_Metadata(mSwitchIndex));
		super.onStatsAddedToTool(aItem, aID);
	}
	
	@Override
	public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? Textures.ItemIcons.JACKHAMMER : Textures.ItemIcons.VOID;
	}
	
	@Override
	public String getDeathMessage() {
		return "[VICTIM] has been jackhammered into pieces by [KILLER]";
	}
}
