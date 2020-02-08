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

import java.util.ArrayList;
import java.util.List;

import gregapi6.data.CS.SFX;
import gregapi6.data.MD;
import gregapi6.data.MT;
import gregapi6.data.OP;
import gregapi6.item.multiitem.MultiItemTool;
import gregapi6.item.multiitem.behaviors.Behavior_Tool;
import gregapi6.old.Textures;
import gregapi6.render.IIconContainer;
import gregapi6.util.ST;
import gregtech6.items.tools.early.GT_Tool_Axe;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.event.world.BlockEvent;

public class GT_Tool_Chainsaw_LV extends GT_Tool_Axe {
	@Override
	public int getToolDamagePerBlockBreak() {
		return 50;
	}
	
	@Override
	public int getToolDamagePerDropConversion() {
		return 5;
	}
	
	@Override
	public int getToolDamagePerContainerCraft() {
		return 200;
	}
	
	@Override
	public int getToolDamagePerEntityAttack() {
		return 200;
	}
	
	@Override
	public int getHurtResistanceTime(int aOriginalHurtResistance, Entity aEntity) {
		if (aEntity instanceof EntityCreeper) return aOriginalHurtResistance / 3;
		return aOriginalHurtResistance;
	}
	
	@Override
	public DamageSource getDamageSource(EntityLivingBase aPlayer, Entity aEntity) {
		if (MD.IC2.mLoaded && aPlayer instanceof EntityPlayer && aEntity instanceof EntityCreeper) try {
		((EntityPlayer)aPlayer).triggerAchievement(AchievementList.openInventory);
		((EntityPlayer)aPlayer).triggerAchievement(AchievementList.mineWood);
		((EntityPlayer)aPlayer).triggerAchievement(AchievementList.buildWorkBench);
		((EntityPlayer)aPlayer).triggerAchievement(AchievementList.buildPickaxe);
		((EntityPlayer)aPlayer).triggerAchievement(AchievementList.buildFurnace);
		((EntityPlayer)aPlayer).triggerAchievement(AchievementList.acquireIron);
		ic2.core.IC2.achievements.issueAchievement((EntityPlayer)aPlayer, "buildCable");
		ic2.core.IC2.achievements.issueAchievement((EntityPlayer)aPlayer, "buildGenerator");
		ic2.core.IC2.achievements.issueAchievement((EntityPlayer)aPlayer, "buildBatBox");
		ic2.core.IC2.achievements.issueAchievement((EntityPlayer)aPlayer, "buildChainsaw");
		ic2.core.IC2.achievements.issueAchievement((EntityPlayer)aPlayer, "killCreeperChainsaw");
		} catch(Throwable e) {e.printStackTrace(ERR);}
		return super.getDamageSource(aPlayer, aEntity);
	}
	
	@Override
	public int getBaseQuality() {
		return 0;
	}
	
	@Override
	public float getBaseDamage() {
		return 3.0F;
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
		String tTool = aBlock.getHarvestTool(aMetaData);
		return (tTool != null && (tTool.equalsIgnoreCase(TOOL_axe) || tTool.equalsIgnoreCase(TOOL_saw))) || aBlock.getMaterial() == Material.wood || aBlock.getMaterial() == Material.cactus || aBlock.getMaterial() == Material.leaves || aBlock.getMaterial() == Material.vine || aBlock.getMaterial() == Material.plants || aBlock.getMaterial() == Material.gourd || aBlock.getMaterial() == Material.ice || aBlock.getMaterial() == Material.packedIce;
	}
	
	@Override
	public int convertBlockDrops(List<ItemStack> aDrops, ItemStack aStack, EntityPlayer aPlayer, Block aBlock, long aAvailableDurability, int aX, int aY, int aZ, byte aMetaData, int aFortune, boolean aSilkTouch, BlockEvent.HarvestDropsEvent aEvent) {
		if (aBlock.getMaterial() == Material.leaves && aBlock instanceof IShearable) {
			aPlayer.worldObj.setBlock(aX, aY, aZ, aBlock, aMetaData, 0);
			if (((IShearable)aBlock).isShearable(aStack, aPlayer.worldObj, aX, aY, aZ)) {
				ArrayList<ItemStack> tDrops = ((IShearable)aBlock).onSheared(aStack, aPlayer.worldObj, aX, aY, aZ, aFortune);
				aDrops.clear();
				aDrops.addAll(tDrops);
				aEvent.dropChance = 1.0F;
			}
			aPlayer.worldObj.setBlock(aX, aY, aZ, NB, 0, 0);
			return 0;
		}
		if ((aBlock.getMaterial() == Material.ice || aBlock.getMaterial() == Material.packedIce) && aDrops.isEmpty()) {
			aDrops.add(ST.make(aBlock, 1, aMetaData));
			aPlayer.worldObj.setBlockToAir(aX, aY, aZ);
			aEvent.dropChance = 1.0F;
			return 0;
		}
		return super.convertBlockDrops(aDrops, aStack, aPlayer, aBlock, aAvailableDurability, aX, aY, aZ, aMetaData, aFortune, aSilkTouch, aEvent);
	}
	
	@Override
	public float getMiningSpeed(Block aBlock, byte aMetaData, float aDefault, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ) {
		return aBlock.getMaterial() == Material.leaves || aBlock.getMaterial() == Material.vine || aBlock.getMaterial() == Material.plants || aBlock.getMaterial() == Material.gourd ? aDefault : super.getMiningSpeed(aBlock, aMetaData, aDefault, aPlayer, aWorld, aX, aY, aZ);
	}
	
	@Override
	public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? Textures.ItemIcons.POWER_UNIT_LV : MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mTextureSetsItems.get(OP.toolHeadChainsaw.mIconIndexItem);
	}
	
	@Override
	public short[] getRGBa(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? MultiItemTool.getSecondaryMaterial(aStack, MT.StainlessSteel).mRGBaSolid : MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mRGBaSolid;
	}
	
	@Override
	public void onToolCrafted(ItemStack aStack, EntityPlayer aPlayer) {
		super.onToolCrafted(aStack, aPlayer);
		if (MD.IC2.mLoaded) try {
		aPlayer.triggerAchievement(AchievementList.buildPickaxe);
		aPlayer.triggerAchievement(AchievementList.buildFurnace);
		aPlayer.triggerAchievement(AchievementList.acquireIron);
		ic2.core.IC2.achievements.issueAchievement(aPlayer, "buildCable");
		ic2.core.IC2.achievements.issueAchievement(aPlayer, "buildGenerator");
		ic2.core.IC2.achievements.issueAchievement(aPlayer, "buildBatBox");
		ic2.core.IC2.achievements.issueAchievement(aPlayer, "buildChainsaw");
		} catch(Throwable e) {e.printStackTrace(ERR);}
	}
	
	@Override
	public void onStatsAddedToTool(MultiItemTool aItem, int aID) {
		aItem.addItemBehavior(aID, new Behavior_Tool(TOOL_saw, SFX.MC_DIG_WOOD, getToolDamagePerContainerCraft(), T));
	}
	
	@Override
	public String getDeathMessage() {
		return "[VICTIM] was massacred by [KILLER]";
	}
}
