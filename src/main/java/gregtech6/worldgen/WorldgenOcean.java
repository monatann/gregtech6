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
import gregapi6.data.CS.ConfigsGT;
import gregapi6.util.WD;
import gregapi6.worldgen.WorldgenObject;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;

/**
 * @author Gregorius Techneticies
 */
public class WorldgenOcean extends WorldgenObject {
	public int mHeight = 62;
	
	@SafeVarargs
	public WorldgenOcean(String aName, boolean aDefault, List<WorldgenObject>... aLists) {
		super(aName, aDefault, aLists);
		mHeight = ConfigsGT.WORLDGEN.get(mCategory, "Height", mHeight);
	}
	
	@Override
	public boolean generate(World aWorld, Chunk aChunk, int aDimType, int aMinX, int aMinZ, int aMaxX, int aMaxZ, Random aRandom, BiomeGenBase[][] aBiomes, Set<String> aBiomeNames) {
		boolean temp = T;
		for (String tName : aBiomeNames) if (BIOMES_OCEAN.contains(tName)) {temp = F; break;}
		if (temp) return F;
		int tHeight = WD.dimTF(aWorld) ? 31 : mHeight;
		final ExtendedBlockStorage[] tStorages = aChunk.getBlockStorageArray();
		for (int tX = 0; tX < 16; tX++) for (int tZ = 0; tZ < 16; tZ++) {
			boolean tPlacedNone = T;
			for (int tY = tHeight; tY > 0; tY--) {
				final ExtendedBlockStorage tStorage = tStorages[tY >> 4];
				if (tStorage == null) continue;
				final Block tBlock = tStorage.getBlockByExtId(tX, tY & 15, tZ);
				if (tBlock.isOpaqueCube()) break;
				if (tBlock == NB || tBlock == BlocksGT.Ocean || tBlock.isAir(aWorld, aMinX+tX, tY, aMinZ+tZ)) continue;
				if (tBlock == Blocks.water || tBlock == Blocks.flowing_water) {
					tStorage.func_150818_a(tX, tY & 15, tZ, BlocksGT.Ocean);
					tStorage.setExtBlockMetadata(tX, tY & 15, tZ, 0);
					if (tPlacedNone) {
						aWorld.scheduleBlockUpdate(aMinX+tX, tY, aMinZ+tZ, BlocksGT.Ocean, 10+RNGSUS.nextInt(90));
						tPlacedNone = F;
					}
					temp = T;
				}
			}
		}
		return temp;
	}
}
