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

package gregtech6.blocks;

import static gregapi6.data.CS.*;

import java.util.List;

import gregapi6.block.IBlockToolable;
import gregapi6.block.ToolCompat;
import gregapi6.block.metatype.BlockColored;
import gregapi6.block.metatype.BlockMetaType;
import gregapi6.block.metatype.ItemBlockMetaType;
import gregapi6.data.CS.BlocksGT;
import gregapi6.data.MT;
import gregapi6.data.OP;
import gregapi6.old.Textures;
import gregapi6.oredict.OreDictItemData;
import gregapi6.oredict.OreDictMaterial;
import gregapi6.render.IIconContainer;
import gregapi6.util.OM;
import gregapi6.util.ST;
import gregapi6.util.WD;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockConcrete extends BlockColored implements IBlockToolable {
	public BlockConcrete(String aUnlocalised) {
		super(ItemBlockMetaType.class, Material.rock, soundTypeStone, aUnlocalised, "Concrete", null, 2.0F, 1.0F, 1, Textures.BlockIcons.CONCRETES);
		for (int i = 0; i < 16; i++) OM.reg(ST.make(this, 1, i), OP.stone, MT.Concrete);
	}
	
	@Override
	protected BlockMetaType makeSlab(Class<? extends ItemBlock> aItemClass, Material aVanillaMaterial, SoundType aVanillaSoundType, String aName, String aDefaultLocalised, OreDictMaterial aMaterial, float aResistanceMultiplier, float aHardnessMultiplier, int aHarvestLevel, int aCount, IIconContainer[] aIcons, byte aSlabType, BlockMetaType aBlock) {
		return new BlockConcrete(aItemClass, aVanillaMaterial, aVanillaSoundType, aName, aDefaultLocalised, aMaterial, aResistanceMultiplier, aHardnessMultiplier, aHarvestLevel, aCount, aIcons, aSlabType, aBlock);
	}
	
	protected BlockConcrete(Class<? extends ItemBlock> aItemClass, Material aVanillaMaterial, SoundType aVanillaSoundType, String aName, String aDefaultLocalised, OreDictMaterial aMaterial, float aResistanceMultiplier, float aHardnessMultiplier, int aHarvestLevel, int aCount, IIconContainer[] aIcons, byte aSlabType, BlockMetaType aBlock) {
		super(aItemClass, aVanillaMaterial, aVanillaSoundType, aName, aDefaultLocalised, aMaterial, aResistanceMultiplier, aHardnessMultiplier, aHarvestLevel, aCount, aIcons, aSlabType, aBlock);
		OM.data(ST.make(this, 1, W), new OreDictItemData(MT.Concrete, U2));
	}
	
	@Override
	public long onToolClick(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, World aWorld, byte aSide, int aX, int aY, int aZ, float aHitX, float aHitY, float aHitZ) {
		if (aTool.equals(TOOL_drill)) {
			if (mBlock == this && aPlayer instanceof EntityPlayer) {
				for (int i = 0; i < ((EntityPlayer)aPlayer).inventory.mainInventory.length; i++) {
					int tIndex = ((EntityPlayer)aPlayer).inventory.mainInventory.length-i-1;
					ItemStack tStack = ((EntityPlayer)aPlayer).inventory.mainInventory[tIndex];
					if (OM.is("stickAnyIronOrSteel", tStack)) {
						if (WD.set(aWorld, aX, aY, aZ, BlocksGT.ConcreteReinforced, WD.meta(aWorld, aX, aY, aZ), 3)) {
							ST.use(aPlayer, tIndex, tStack);
							return 10000;
						}
						break;
					}
				}
			}
		}
		return ToolCompat.onToolClick(this, aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aWorld, aSide, aX, aY, aZ, aHitX, aHitY, aHitZ);
	}
}
