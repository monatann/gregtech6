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

package gregtech6.worldgen.tree;

import static gregapi6.data.CS.*;

import java.util.List;
import java.util.Random;
import java.util.Set;

import gregapi6.block.tree.BlockBaseSapling;
import gregapi6.data.CS.BlocksGT;
import gregapi6.worldgen.WorldgenObject;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;

/**
 * @author Gregorius Techneticies
 */
public class WorldgenTreeCinnamon extends WorldgenObject {
	@SafeVarargs
	public WorldgenTreeCinnamon(String aName, boolean aDefault, List<WorldgenObject>... aLists) {
		super(aName, aDefault, aLists);
	}
	
	@Override
	public boolean generate(World aWorld, Chunk aChunk, int aDimType, int aMinX, int aMinZ, int aMaxX, int aMaxZ, Random aRandom, BiomeGenBase[][] aBiomes, Set<String> aBiomeNames) {
		if (aRandom.nextInt(3) != 0 || checkForMajorWorldgen(aWorld, aMinX, aMinZ, aMaxX, aMaxZ)) return F;
		boolean temp = T;
		for (String tName : aBiomeNames) if (BIOMES_JUNGLE.contains(tName)) {temp = F; break;}
		if (temp) return F;
		int tX = aRandom.nextInt(16), tZ = aRandom.nextInt(16);
		for (int tY = aWorld.provider.hasNoSky ? 80 : aWorld.getHeight()-50; tY > 0; tY--) if (BlocksGT.plantableTrees.contains(aChunk.getBlock(tX, tY, tZ))) return ((BlockBaseSapling)BlocksGT.Sapling).grow(aWorld, aMinX + tX, tY+1, aMinZ + tZ, (byte)5, aRandom);
		return T;
	}
}
