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

package gregtech6.worldgen;

import static gregapi6.data.CS.*;

import java.util.List;
import java.util.Random;
import java.util.Set;

import gregapi6.data.CS.BlocksGT;
import gregapi6.util.WD;
import gregapi6.worldgen.WorldgenObject;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;

/**
 * @author Gregorius Techneticies
 */
public class WorldgenTurf extends WorldgenObject {
	@SafeVarargs
	public WorldgenTurf(String aName, boolean aDefault, List<WorldgenObject>... aLists) {
		super(aName, aDefault, aLists);
	}
	
	@Override
	public boolean generate(World aWorld, Chunk aChunk, int aDimType, int aMinX, int aMinZ, int aMaxX, int aMaxZ, Random aRandom, BiomeGenBase[][] aBiomes, Set<String> aBiomeNames) {
		if (aRandom.nextInt(32) > 0 || checkForMajorWorldgen(aWorld, aMinX, aMinZ, aMaxX, aMaxZ)) return F;
		boolean temp = T;
		for (String tName : aBiomeNames) if (BIOMES_SWAMP.contains(tName)) {temp = F; break;}
		if (temp) return F;
		
		int tX = aMinX - 16, tZ = aMinZ -16, tUpperBound = WD.dimTF(aWorld) ? 31 : 63, tLowerBound = WD.dimTF(aWorld) ? 24 : 48;
		for (int i = 0; i < 48; i++) for (int j = 0; j < 48; j++) if (WorldgenPit.SHAPE[i][j]) {
			Block tBlock = NB, tLastBlock = aWorld.getBlock(tX+i, 64, tZ+j);
			for (int tY = tUpperBound, tGenerated = 0; tY > tLowerBound && tGenerated < 2; tY--, tLastBlock = tBlock) {
				tBlock = aWorld.getBlock(tX+i, tY, tZ+j);
				if (tBlock == BlocksGT.Diggables && 2 == aWorld.getBlockMetadata(tX+i, tY, tZ+j)) {tGenerated++; continue;}
				if (!tBlock.isOpaqueCube()) {if (tGenerated > 0) break; continue;}
				if (tBlock == Blocks.dirt) {
					if (tGenerated <= 0 && (tLastBlock.getMaterial() == Material.wood || tLastBlock.getMaterial() == Material.gourd)) continue;
				} else {
					if (tGenerated > 0) {
						if (tBlock.getMaterial() != Material.rock) break;
					} else {
						continue;
					}
				}
				aWorld.setBlock(tX+i, tY, tZ+j, BlocksGT.Diggables, 2, 3);
				tGenerated++;
			}
		}
		return temp;
	}
}
