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

import gregapi6.block.BlockBaseMeta;
import gregapi6.data.LH;
import gregapi6.data.MT;
import gregapi6.old.Textures;
import gregapi6.util.OM;
import gregapi6.util.ST;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class BlockSands extends BlockBaseMeta {
	public BlockSands(String aUnlocalised) {
		super(null, aUnlocalised, Material.sand, soundTypeSand, 1, Textures.BlockIcons.SANDS);
		LH.add(getUnlocalizedName()+ ".0.name", "Black Sand");
		
		OM.data(ST.make(this, 1, 0), MT.OREMATS.Magnetite, U);
	}
	
	@Override public boolean useGravity(int aMeta) {return T;}
	@Override public boolean canCreatureSpawn(int aMeta) {return T;}
	@Override public boolean doesPistonPush(short aMeta) {return T;}
	@Override public boolean isSealable(int aMeta, byte aSide) {return F;}
	@Override public String getHarvestTool(int aMeta) {return TOOL_shovel;}
	@Override public int getHarvestLevel(int aMeta) {return 0;}
	@Override public float getBlockHardness(World aWorld, int aX, int aY, int aZ) {return Blocks.sand.getBlockHardness(aWorld, aX, aY, aZ);}
	@Override public float getExplosionResistance(int aMeta) {return Blocks.sand.getExplosionResistance(null);}
}
