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
import gregapi6.block.tree.BlockBaseLog;
import gregapi6.data.CS.BlocksGT;
import gregapi6.data.LH;
import gregapi6.data.MT;
import gregapi6.old.Textures;
import gregapi6.util.OM;
import gregapi6.util.ST;
import gregapi6.util.UT;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockTreeLogAFireProof extends BlockBaseLog implements IBlockToolable {
	public BlockTreeLogAFireProof(String aUnlocalised) {
		super(null, aUnlocalised, Material.wood, soundTypeWood, 4, Textures.BlockIcons.LOGS_A);
		
		LH.add(getUnlocalizedName()+ ".0.name", "Rubber Log (Fireproof)");
		LH.add(getUnlocalizedName()+ ".4.name", "Rubber Log (Fireproof)");
		LH.add(getUnlocalizedName()+ ".8.name", "Rubber Log (Fireproof)");
		LH.add(getUnlocalizedName()+".12.name", "Rubber Log (Fireproof)");
		OM.reg(ST.make(this, 1, 0), "logRubber");
		OM.reg(ST.make(this, 1, 4), "logRubber");
		OM.reg(ST.make(this, 1, 8), "logRubber");
		OM.reg(ST.make(this, 1,12), "logRubber");
		
		LH.add(getUnlocalizedName()+ ".1.name", "Maple Log (Fireproof)");
		LH.add(getUnlocalizedName()+ ".5.name", "Maple Log (Fireproof)");
		LH.add(getUnlocalizedName()+ ".9.name", "Maple Log (Fireproof)");
		LH.add(getUnlocalizedName()+".13.name", "Maple Log (Fireproof)");
		OM.reg(ST.make(this, 1, 1), "logWood");
		OM.reg(ST.make(this, 1, 5), "logWood");
		OM.reg(ST.make(this, 1, 9), "logWood");
		OM.reg(ST.make(this, 1,13), "logWood");
		
		LH.add(getUnlocalizedName()+ ".2.name", "Willow Log (Fireproof)");
		LH.add(getUnlocalizedName()+ ".6.name", "Willow Log (Fireproof)");
		LH.add(getUnlocalizedName()+".10.name", "Willow Log (Fireproof)");
		LH.add(getUnlocalizedName()+".14.name", "Willow Log (Fireproof)");
		OM.reg(ST.make(this, 1, 2), "logWood");
		OM.reg(ST.make(this, 1, 6), "logWood");
		OM.reg(ST.make(this, 1,10), "logWood");
		OM.reg(ST.make(this, 1,14), "logWood");
		
		LH.add(getUnlocalizedName()+ ".3.name", "Blue Mahoe Log (Fireproof)");
		LH.add(getUnlocalizedName()+ ".7.name", "Blue Mahoe Log (Fireproof)");
		LH.add(getUnlocalizedName()+".11.name", "Blue Mahoe Log (Fireproof)");
		LH.add(getUnlocalizedName()+".15.name", "Blue Mahoe Log (Fireproof)");
		OM.reg(ST.make(this, 1, 3), "logWood");
		OM.reg(ST.make(this, 1, 7), "logWood");
		OM.reg(ST.make(this, 1,11), "logWood");
		OM.reg(ST.make(this, 1,15), "logWood");
	}
	
	@Override public int getLeavesRangeSide(byte aMetaData) {return 0;}
	@Override public int getLeavesRangeYPos(byte aMetaData) {return 0;}
	@Override public int getLeavesRangeYNeg(byte aMetaData) {return 0;}
	
	@Override
	public long onToolClick(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, World aWorld, byte aSide, int aX, int aY, int aZ, float aHitX, float aHitY, float aHitZ) {
		if (aTool.equals(TOOL_axe) || aTool.equals(TOOL_saw) || aTool.equals(TOOL_knife)) {
			if (aWorld.isRemote) return 0;
			aWorld.setBlock(aX, aY, aZ, BlocksGT.BeamAFireProof, aWorld.getBlockMetadata(aX, aY, aZ), 3);
			UT.Inventories.addStackToPlayerInventoryOrDrop(aPlayer instanceof EntityPlayer ? (EntityPlayer)aPlayer : null, OM.dust(MT.Bark), aWorld, aX+OFFSETS_X[aSide], aY+OFFSETS_Y[aSide], aZ+OFFSETS_Z[aSide]);
			return aTool.equals(TOOL_axe) ? 500 : 1000;
		}
		return ToolCompat.onToolClick(this, aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aWorld, aSide, aX, aY, aZ, aHitX, aHitY, aHitZ);
	}
}
