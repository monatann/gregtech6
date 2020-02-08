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

import java.util.List;

import gregapi6.block.tree.BlockBaseLeaves;
import gregapi6.data.IL;
import gregapi6.data.MT;
import gregapi6.item.multiitem.MultiItemTool;
import gregapi6.item.multiitem.tools.ToolStats;
import gregapi6.old.Textures;
import gregapi6.render.IIconContainer;
import gregapi6.util.ST;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;

public class GT_Tool_BranchCutter extends ToolStats {
	@Override
	public int getToolDamagePerBlockBreak() {
		return 100;
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
		return 100;
	}
	
	@Override
	public float getBaseDamage() {
		return 2.5F;
	}
	
	@Override
	public float getSpeedMultiplier() {
		return 0.25F;
	}
	
	@Override
	public float getMaxDurabilityMultiplier() {
		return 0.25F;
	}
	
	@Override public boolean canCollect()                                                   {return T;}
	@Override public boolean isGrafter()                                                    {return T;}
	
	@Override
	public int convertBlockDrops(List<ItemStack> aDrops, ItemStack aStack, EntityPlayer aPlayer, Block aBlock, long aAvailableDurability, int aX, int aY, int aZ, byte aMetaData, int aFortune, boolean aSilkTouch, BlockEvent.HarvestDropsEvent aEvent) {
		if (aBlock.getMaterial() == Material.leaves) aEvent.dropChance = Math.min(1.0F, Math.max(aEvent.dropChance, (aStack.getItem().getHarvestLevel(aStack, "")+1) * 0.2F));
		if (aBlock == Blocks.leaves) {
			aDrops.clear();
			if ((aMetaData & 3) == 0 && RNGSUS.nextInt(9) <= aFortune * 2) aDrops.add(IL.Food_Apple_Red.get(1)); else aDrops.add(ST.make(Blocks.sapling, 1, aMetaData & 3));
		} else if (aBlock == Blocks.leaves2) {
			aDrops.clear();
			aDrops.add(ST.make(Blocks.sapling, 1, (aMetaData & 3) + 4));
		} else if (aBlock instanceof BlockBaseLeaves) {
			aDrops.clear();
			aDrops.add(ST.make(aBlock.getItemDropped(aMetaData, RNGSUS, aFortune), 1, aBlock.damageDropped(aMetaData)));
		} else if (IL.IC2_Leaves_Rubber.equal(aBlock)) {
			aDrops.clear();
			aDrops.add(IL.IC2_Sapling_Rubber.get(1));
		} else if (IL.AETHER_Skyroot_Leaves_Gold.equal(aBlock)) {
			aDrops.clear();
			aDrops.add(IL.AETHER_Skyroot_Sapling_Gold.get(1));
		} else if (IL.AETHER_Skyroot_Leaves_Green.equal(aBlock)) {
			aDrops.clear();
			aDrops.add(IL.AETHER_Skyroot_Sapling_Green.get(1));
		} else if (IL.AETHER_Skyroot_Leaves_Blue.equal(aBlock)) {
			aDrops.clear();
			aDrops.add(IL.AETHER_Skyroot_Sapling_Blue.get(1));
		} else if (IL.AETHER_Skyroot_Leaves_Dark.equal(aBlock)) {
			aDrops.clear();
			aDrops.add(IL.AETHER_Skyroot_Sapling_Dark.get(1));
		} else if (IL.AETHER_Skyroot_Leaves_Purple.equal(aBlock)) {
			aDrops.clear();
			aDrops.add(IL.AETHER_Skyroot_Sapling_Purple.get(1));
		} else if (IL.AETHER_Skyroot_Leaves_Apple.equal(aBlock)) {
			if (RNGSUS.nextInt(9) <= aFortune * 2) aDrops.add(IL.AETHER_Apple.get(1)); else aDrops.add(IL.AETHER_Skyroot_Sapling_Purple.get(1));
		}
		return 0;
	}
	
	@Override
	public boolean isMinableBlock(Block aBlock, byte aMetaData) {
		String tTool = aBlock.getHarvestTool(aMetaData);
		return (tTool != null && tTool.equalsIgnoreCase("grafter")) || aBlock.getMaterial() == Material.leaves;
	}
	
	@Override
	public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? Textures.ItemIcons.GRAFTER : Textures.ItemIcons.VOID;
	}
	
	@Override
	public short[] getRGBa(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mRGBaSolid : UNCOLOURED;
	}
	
	@Override
	public String getDeathMessage() {
		return "[VICTIM] has been trimmed by [KILLER]";
	}
}
