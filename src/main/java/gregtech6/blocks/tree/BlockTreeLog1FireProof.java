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

package gregtech6.blocks.tree;

import static gregapi6.data.CS.*;

import java.util.List;

import gregapi6.block.IBlockToolable;
import gregapi6.block.ToolCompat;
import gregapi6.block.tree.BlockBaseBeam;
import gregapi6.data.IL;
import gregapi6.data.LH;
import gregapi6.data.MT;
import gregapi6.old.Textures;
import gregapi6.util.OM;
import gregapi6.util.UT;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockTreeLog1FireProof extends BlockBaseBeam implements IBlockToolable {
	public BlockTreeLog1FireProof(String aUnlocalised) {
		super(null, aUnlocalised, Material.wood, soundTypeWood, 4, Textures.BlockIcons.LOGS_1);
		
		LH.add(getUnlocalizedName()+ ".0.name", "Dead Log (Fireproof)");
		LH.add(getUnlocalizedName()+ ".4.name", "Dead Log (Fireproof)");
		LH.add(getUnlocalizedName()+ ".8.name", "Dead Log (Fireproof)");
		LH.add(getUnlocalizedName()+".12.name", "Dead Log (Fireproof)");
		
		LH.add(getUnlocalizedName()+ ".1.name", "Rotten Log (Fireproof)");
		LH.add(getUnlocalizedName()+ ".5.name", "Rotten Log (Fireproof)");
		LH.add(getUnlocalizedName()+ ".9.name", "Rotten Log (Fireproof)");
		LH.add(getUnlocalizedName()+".13.name", "Rotten Log (Fireproof)");
		
		LH.add(getUnlocalizedName()+ ".2.name", "Mossy Log (Fireproof)");
		LH.add(getUnlocalizedName()+ ".6.name", "Mossy Log (Fireproof)");
		LH.add(getUnlocalizedName()+".10.name", "Mossy Log (Fireproof)");
		LH.add(getUnlocalizedName()+".14.name", "Mossy Log (Fireproof)");
		
		LH.add(getUnlocalizedName()+ ".3.name", "Frozen Log (Fireproof)");
		LH.add(getUnlocalizedName()+ ".7.name", "Frozen Log (Fireproof)");
		LH.add(getUnlocalizedName()+".11.name", "Frozen Log (Fireproof)");
		LH.add(getUnlocalizedName()+".15.name", "Frozen Log (Fireproof)");
	}
	
	@Override
	public long onToolClick(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, World aWorld, byte aSide, int aX, int aY, int aZ, float aHitX, float aHitY, float aHitZ) {
		if (aTool.equals(TOOL_axe) || aTool.equals(TOOL_saw) || aTool.equals(TOOL_knife)) {
			if (aWorld.isRemote) return 0;
			switch (aWorld.getBlockMetadata(aX, aY, aZ) & PILLAR_DATA) {
			case 0: UT.Inventories.addStackToPlayerInventoryOrDrop(aPlayer instanceof EntityPlayer ? (EntityPlayer)aPlayer : null, IL.Bark_Dry.get(1), aWorld, aX+OFFSETS_X[aSide], aY+OFFSETS_Y[aSide], aZ+OFFSETS_Z[aSide]); break;
			case 1: UT.Inventories.addStackToPlayerInventoryOrDrop(aPlayer instanceof EntityPlayer ? (EntityPlayer)aPlayer : null, IL.FR_Mulch.get(1, OM.dust(MT.Wood)), aWorld, aX+OFFSETS_X[aSide], aY+OFFSETS_Y[aSide], aZ+OFFSETS_Z[aSide]); break;
			case 2: UT.Inventories.addStackToPlayerInventoryOrDrop(aPlayer instanceof EntityPlayer ? (EntityPlayer)aPlayer : null, IL.FR_Mulch.get(1, OM.dust(MT.Wood)), aWorld, aX+OFFSETS_X[aSide], aY+OFFSETS_Y[aSide], aZ+OFFSETS_Z[aSide]); break;
			case 3: UT.Inventories.addStackToPlayerInventoryOrDrop(aPlayer instanceof EntityPlayer ? (EntityPlayer)aPlayer : null, OM.dust(MT.Ice), aWorld, aX+OFFSETS_X[aSide], aY+OFFSETS_Y[aSide], aZ+OFFSETS_Z[aSide]); break;
			}
			aWorld.setBlockToAir(aX, aY, aZ);
			return 1000;
		}
		return ToolCompat.onToolClick(this, aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aWorld, aSide, aX, aY, aZ, aHitX, aHitY, aHitZ);
	}
	
	@Override public float getBlockHardness(World aWorld, int aX, int aY, int aZ) {return Blocks.log.getBlockHardness(aWorld, aX, aY, aZ) / 2;}
}
