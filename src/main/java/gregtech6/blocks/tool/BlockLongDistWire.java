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

package gregtech6.blocks.tool;

import static gregapi6.data.CS.*;

import java.util.List;

import gregapi6.block.misc.BlockBaseMachineUpdate;
import gregapi6.data.LH;
import gregapi6.data.LH.Chat;
import gregapi6.data.TD;
import gregapi6.render.IIconContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockLongDistWire extends BlockBaseMachineUpdate {
	public final byte[] mTiers;
	
	public BlockLongDistWire(String aUnlocalised, IIconContainer[] aIcons, byte[] aTiers) {
		super(null, aUnlocalised, Material.iron, soundTypeCloth, 16, aIcons, ~0);
		mTiers = aTiers;
		for (byte i = 0; i < 16; i++) LH.add(aUnlocalised+"."+i+".name" , "Long Distance Electric Wire ("+VN[mTiers[i]]+")");
	}
	
	@Override
	public void addInformation(ItemStack aStack, int aMeta, EntityPlayer aPlayer, List<String> aList, boolean aF3_H) {
		aList.add(Chat.CYAN + LH.get(LH.WIRE_STATS_VOLTAGE) + (VMAX[mTiers[aMeta]]) + " " + TD.Energy.EU.getLocalisedNameShort() + " (" + VN[mTiers[aMeta]] + ")");
		aList.add(Chat.CYAN + LH.get(LH.WIRE_STATS_AMPERAGE) + "UNLIMITED");
		aList.add(Chat.CYAN + LH.get(LH.WIRE_STATS_LOSS) + "0.125 " + TD.Energy.EU.getLocalisedNameShort() + "/m");
	}
	
	@Override public final int getFlammability(IBlockAccess aWorld, int aX, int aY, int aZ, ForgeDirection aSide) {return 150;}
	@Override public final int getFireSpreadSpeed(IBlockAccess aWorld, int aX, int aY, int aZ, ForgeDirection aSide) {return 150;}
	@Override public String getHarvestTool(int aMeta) {return TOOL_cutter;}
	@Override public int getHarvestLevel(int aMeta) {return 3;}
	@Override public boolean isSealable(int aMeta, byte aSide) {return T;}
	@Override public float getBlockHardness(World aWorld, int aX, int aY, int aZ) {return Blocks.iron_block.getBlockHardness(aWorld, aX, aY, aZ);}
	@Override public float getExplosionResistance(Entity aEntity, World aWorld, int aX, int aY, int aZ, double eX, double eY, double eZ) {return 15;}
	@Override public float getExplosionResistance(Entity aEntity) {return 15;}
	@Override public float getExplosionResistance(int aMeta) {return 15;}
}
