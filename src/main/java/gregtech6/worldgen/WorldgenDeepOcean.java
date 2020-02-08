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
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;

/**
 * @author Gregorius Techneticies
 */
public class WorldgenDeepOcean extends WorldgenObject {
	@SafeVarargs
	public WorldgenDeepOcean(String aName, boolean aDefault, List<WorldgenObject>... aLists) {
		super(aName, aDefault, aLists);
	}
	
	@Override
	public boolean generate(World aWorld, Chunk aChunk, int aDimType, int aMinX, int aMinZ, int aMaxX, int aMaxZ, Random aRandom, BiomeGenBase[][] aBiomes, Set<String> aBiomeNames) {
		if (!aBiomeNames.contains(BiomeGenBase.deepOcean.biomeName)) return F;
		int i = 3 + aRandom.nextInt(9), j = 30 + aRandom.nextInt(9), k = 3 + aRandom.nextInt(9);
		if (WD.anywater(aChunk.getBlock(i, j, k))) {
			switch ((int)(new NoiseGenerator(aWorld).get(aMinX+8, 32, aMinZ+8)*16)) {
			default:
				// Keep Deep Ocean Normal.
				return F;
			case  5: case  6: case  7: case  8: case  9:
				// Corals maybe?
				return F;
			case 10: case 11: case 12:
				// Add Dark Prismarine Pylons.
				for (int l =  8; l < 11; l++) {
					WD.set(aChunk, i  , j+l, k  , BlocksGT.PrismarineDark, 0);
					WD.set(aChunk, i  , j-l, k  , BlocksGT.PrismarineDark, 0);
				}
				for (int l =  5; l <  8; l++) for (int m = -1; m <= 1; m++) for (int n = -1; n <= 1; n++) {
					WD.set(aChunk, i+m, j+l, k+n, BlocksGT.PrismarineDark, 0);
					WD.set(aChunk, i+m, j-l, k+n, BlocksGT.PrismarineDark, 0);
				}
				for (int l =  2; l <  5; l++) for (int m = -2; m <= 2; m++) for (int n = -2; n <= 2; n++) {
					WD.set(aChunk, i+m, j+l, k+n, BlocksGT.PrismarineDark, 0);
					WD.set(aChunk, i+m, j-l, k+n, BlocksGT.PrismarineDark, 0);
				}
				for (int l =  0; l <  2; l++) for (int m = -3; m <= 3; m++) for (int n = -3; n <= 3; n++) {
					WD.set(aChunk, i+m, j+l, k+n, BlocksGT.PrismarineDark, 0);
					WD.set(aChunk, i+m, j-l, k+n, BlocksGT.PrismarineDark, 0);
				}
				return T;
			case 13: case 14: case 15:
				// Add Light Prismarine Pylons.
				for (int l =  8; l < 11; l++) {
					WD.set(aChunk, i  , j+l, k  , BlocksGT.PrismarineLight, 0);
					WD.set(aChunk, i  , j-l, k  , BlocksGT.PrismarineLight, 0);
				}
				for (int l =  5; l <  8; l++) for (int m = -1; m <= 1; m++) for (int n = -1; n <= 1; n++) {
					WD.set(aChunk, i+m, j+l, k+n, BlocksGT.PrismarineLight, 0);
					WD.set(aChunk, i+m, j-l, k+n, BlocksGT.PrismarineLight, 0);
				}
				for (int l =  2; l <  5; l++) for (int m = -2; m <= 2; m++) for (int n = -2; n <= 2; n++) {
					WD.set(aChunk, i+m, j+l, k+n, BlocksGT.PrismarineLight, 0);
					WD.set(aChunk, i+m, j-l, k+n, BlocksGT.PrismarineLight, 0);
				}
				for (int l =  0; l <  2; l++) for (int m = -3; m <= 3; m++) for (int n = -3; n <= 3; n++) {
					WD.set(aChunk, i+m, j+l, k+n, BlocksGT.PrismarineLight, 0);
					WD.set(aChunk, i+m, j-l, k+n, BlocksGT.PrismarineLight, 0);
				}
				return T;
			}
		}
		return F;
	}
}
