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

package gregtech6.worldgen.aether;

import static gregapi6.data.CS.*;

import java.util.List;
import java.util.Random;
import java.util.Set;

import gregapi6.block.multitileentity.MultiTileEntityRegistry;
import gregapi6.data.MT;
import gregapi6.data.OP;
import gregapi6.util.ST;
import gregapi6.util.UT;
import gregapi6.util.WD;
import gregapi6.worldgen.WorldgenObject;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;

/**
 * @author Gregorius Techneticies
 */
public class WorldgenAetherRocks extends WorldgenObject {
	@SafeVarargs
	public WorldgenAetherRocks(String aName, boolean aDefault, List<WorldgenObject>... aLists) {
		super(aName, aDefault, aLists);
	}
	
	@Override
	public boolean generate(World aWorld, Chunk aChunk, int aDimType, int aMinX, int aMinZ, int aMaxX, int aMaxZ, Random aRandom, BiomeGenBase[][] aBiomes, Set<String> aBiomeNames) {
		MultiTileEntityRegistry tRegistry = MultiTileEntityRegistry.getRegistry("gt6.multitileentity");
		if (tRegistry == null) return F;
		for (int i = 0, j = 1+aRandom.nextInt(2); i < j; i++) {
			int tX = aMinX + aRandom.nextInt(16), tZ = aMinZ + aRandom.nextInt(16);
			for (int tY = 60+aRandom.nextInt(40); tY > 0; tY--) {
				Block tContact = aChunk.getBlock(tX&15, tY, tZ&15);
				if (tContact.getMaterial().isLiquid()) break;
				if (tContact == NB || tContact.isAir(aWorld, tX, tY, tZ)) continue;
				if (tContact.getMaterial() != Material.grass && tContact.getMaterial() != Material.ground && tContact.getMaterial() != Material.sand) continue;
				if (WD.easyRep(aWorld, tX, tY+1, tZ)) tRegistry.mBlock.placeBlock(aWorld, tX, tY+1, tZ, SIDE_UNKNOWN, (short)32757, aRandom.nextInt(5)==0?ST.save(UT.NBT.make(), NBT_VALUE, UT.Code.select(null, OP.gem.mat(MT.Ambrosium, 1), ST.make(Items.flint, 1, 0), ST.make(Items.flint, 1, 0), OP.rockGt.mat(MT.MeteoricIron, 1))):null, F, T);
				break;
			}
		}
		return T;
	}
}
