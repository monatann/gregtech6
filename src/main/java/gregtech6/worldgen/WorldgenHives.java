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

import gregapi6.block.multitileentity.MultiTileEntityRegistry;
import gregapi6.data.CS.ItemsGT;
import gregapi6.data.IL;
import gregapi6.item.bumble.IItemBumbleBee;
import gregapi6.util.ST;
import gregapi6.util.UT;
import gregapi6.util.WD;
import gregapi6.worldgen.WorldgenObject;
import gregtech6.blocks.fluids.BlockWaterlike;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;

/**
 * @author Gregorius Techneticies
 */
public class WorldgenHives extends WorldgenObject {
	@SafeVarargs
	public WorldgenHives(String aName, boolean aDefault, List<WorldgenObject>... aLists) {
		super(aName, aDefault, aLists);
	}
	
	@Override
	public boolean generate(World aWorld, Chunk aChunk, int aDimType, int aMinX, int aMinZ, int aMaxX, int aMaxZ, Random aRandom, BiomeGenBase[][] aBiomes, Set<String> aBiomeNames) {
		if (checkForMajorWorldgen(aWorld, aMinX, aMinZ, aMaxX, aMaxZ)) return F;
		MultiTileEntityRegistry tRegistry = MultiTileEntityRegistry.getRegistry("gt6.multitileentity");
		if (tRegistry == null) return F;
		int tX = aMinX + aRandom.nextInt(16), tY = 0, tZ = aMinZ + aRandom.nextInt(16), tCount = 0;
		boolean rResult = F;
		
		
		switch (aDimType) {
		case DIM_TWILIGHT:
			tY =  8+aRandom.nextInt(24);
			if (!WD.stone(WD.block(aWorld, tX, tY, tZ), WD.meta(aWorld, tX, tY, tZ))) return rResult;
			for (byte tSide : ALL_SIDES_VALID) if (WD.opq(aWorld, tX+OFFSETS_X[tSide], tY+OFFSETS_Y[tSide], tZ+OFFSETS_Z[tSide], F, T)) tCount++;
			return (tCount == 5 && placeHive(tRegistry, aWorld, tX, tY, tZ, DYE_INT_Yellow      , aRandom.nextBoolean()?500:0, aRandom)) || rResult;
		case DIM_EREBUS:
			tY = 16+aRandom.nextInt(96);
			if (!IL.ERE_Umberstone.equal(WD.block(aWorld, tX, tY, tZ))) return rResult;
			for (byte tSide : ALL_SIDES_VALID) if (WD.opq(aWorld, tX+OFFSETS_X[tSide], tY+OFFSETS_Y[tSide], tZ+OFFSETS_Z[tSide], F, T)) tCount++;
			return (tCount == 5 && placeHive(tRegistry, aWorld, tX, tY, tZ, DYE_INT_Brown       , aRandom.nextBoolean()?500:0, aRandom)) || rResult;
		case DIM_BETWEENLANDS:
			tY = 16+aRandom.nextInt(96);
			if (!IL.BTL_Betweenstone.equal(WD.block(aWorld, tX, tY, tZ))) return rResult;
			for (byte tSide : ALL_SIDES_VALID) if (WD.opq(aWorld, tX+OFFSETS_X[tSide], tY+OFFSETS_Y[tSide], tZ+OFFSETS_Z[tSide], F, T)) tCount++;
			return (tCount == 5 && placeHive(tRegistry, aWorld, tX, tY, tZ, DYE_INT_Green       , aRandom.nextBoolean()?500:0, aRandom)) || rResult;
		case DIM_ATUM:
			tY = 16+aRandom.nextInt(64);
			if (!IL.ATUM_Limestone.equal(WD.block(aWorld, tX, tY, tZ))) return rResult;
			for (byte tSide : ALL_SIDES_VALID) if (WD.opq(aWorld, tX+OFFSETS_X[tSide], tY+OFFSETS_Y[tSide], tZ+OFFSETS_Z[tSide], F, T)) tCount++;
			return (tCount == 5 && placeHive(tRegistry, aWorld, tX, tY, tZ, DYE_INT_Yellow      ,   900, aRandom)) || rResult;
		case DIM_ENVM:
			tY = 16+aRandom.nextInt(96);
			if (WD.block(aWorld, tX, tY, tZ) != Blocks.netherrack) return rResult;
			for (byte tSide : ALL_SIDES_VALID) if (WD.opq(aWorld, tX+OFFSETS_X[tSide], tY+OFFSETS_Y[tSide], tZ+OFFSETS_Z[tSide], F, T)) tCount++;
			return (tCount == 5 && placeHive(tRegistry, aWorld, tX, tY, tZ, DYE_INT_LightGray   ,   500, aRandom)) || rResult;
		case DIM_AETHER:
			tY = 16+aRandom.nextInt(96);
			if (WD.block(aWorld, tX, tY, tZ).getMaterial() == Material.ground) return rResult;
			for (byte tSide : ALL_SIDES_VALID) if (WD.opq(aWorld, tX+OFFSETS_X[tSide], tY+OFFSETS_Y[tSide], tZ+OFFSETS_Z[tSide], F, T)) tCount++;
			return (tCount == 5 && placeHive(tRegistry, aWorld, tX, tY, tZ, DYE_INT_Cyan        ,     0, aRandom)) || rResult;
		case DIM_NETHER:
			tY = 16+aRandom.nextInt(96);
			if (WD.block(aWorld, tX, tY, tZ) != Blocks.netherrack) return rResult;
			for (byte tSide : ALL_SIDES_VALID) if (WD.opq(aWorld, tX+OFFSETS_X[tSide], tY+OFFSETS_Y[tSide], tZ+OFFSETS_Z[tSide], F, T)) tCount++;
			return (tCount == 5 && placeHive(tRegistry, aWorld, tX, tY, tZ, 0xaa0000            ,   300, aRandom)) || rResult;
		case DIM_END:
			if (aRandom.nextInt(3) > 0) return F;
			for (tY = 16; tY < 128; tY++) if (WD.block(aWorld, tX, tY, tZ) == Blocks.end_stone) {
				for (byte tSide : ALL_SIDES_VALID) if (WD.opq(aWorld, tX+OFFSETS_X[tSide], tY+OFFSETS_Y[tSide], tZ+OFFSETS_Z[tSide], F, T)) tCount++;
				if (tCount == 5) {
					rResult = placeHive(tRegistry, aWorld, tX, tY, tZ, 0x00aaaa, 400, aRandom) || rResult;
					break;
				}
				tCount = 0;
			}
			return rResult;
		case DIM_OVERWORLD: case DIM_ALFHEIM: case DIM_TROPICS: case DIM_UNKNOWN:
			for (tY = 8; tY < 28; tY++) {
				Block tBlock = WD.block(aWorld, tX, tY, tZ);
				if (tBlock.getMaterial() == Material.rock && WD.opq(tBlock) && WD.stone(tBlock, WD.meta(aWorld, tX, tY, tZ))) {
					for (byte tSide : ALL_SIDES_VALID) if (WD.opq(aWorld, tX+OFFSETS_X[tSide], tY+OFFSETS_Y[tSide], tZ+OFFSETS_Z[tSide], F, T)) tCount++;
					if (tCount == 5) {
						rResult = placeHive(tRegistry, aWorld, tX, tY, tZ, DYE_INT_LightGray,   500, aRandom);
						break;
					}
					tCount = 0;
				}
			}
			
			for (tY = aWorld.provider.hasNoSky ? 80 : aWorld.getHeight()-50; tY > 1; tY--) {
				Block tContact = aWorld.getBlock(tX, tY, tZ);
				if (tContact.getMaterial().isLiquid()) return rResult;
				if (tContact == NB || tContact.isAir(aWorld, tX, tY, tZ) || tContact.getMaterial() == Material.wood || (tContact.getMaterial() == Material.rock && !WD.stone(tContact, WD.meta(aWorld, tX, tY, tZ))) || tContact.isLeaves(aWorld, tX, tY, tZ) || tContact.isWood(aWorld, tX, tY, tZ)) continue;
				
				if (!tContact.isOpaqueCube()) continue;
				for (byte tSide : ALL_SIDES_HORIZONTAL_DOWN) {
					Block tBlock = aWorld.getBlock(tX+OFFSETS_X[tSide], tY-1+OFFSETS_Y[tSide], tZ+OFFSETS_Z[tSide]);
					if (WD.hasCollide(aWorld, tX+OFFSETS_X[tSide], tY-1+OFFSETS_Y[tSide], tZ+OFFSETS_Z[tSide], tBlock)) continue;
					
					
				//  for (String tName : aBiomeNames) if (BIOMES_SPACE.contains(tName))
				//  return placeHive(tRegistry, aWorld, tX, tY-1, tZ, 0x44bbbb          ,   400, aRandom) || rResult;
					if (tBlock == Blocks.water || tBlock == Blocks.flowing_water || tBlock instanceof BlockWaterlike)
					return placeHive(tRegistry, aWorld, tX, tY-1, tZ, DYE_INT_LightBlue ,   100, aRandom) || rResult;
					for (String tName : aBiomeNames) if (BIOMES_MAGICAL.contains(tName) && (aDimType != DIM_ALFHEIM || aRandom.nextBoolean()))
					return placeHive(tRegistry, aWorld, tX, tY-1, tZ, DYE_INT_Purple    ,   200, aRandom) || rResult;
					for (String tName : aBiomeNames) if (BIOMES_END.contains(tName))
					return placeHive(tRegistry, aWorld, tX, tY-1, tZ, 0x00aaaa          ,   400, aRandom) || rResult;
					for (String tName : aBiomeNames) if (BIOMES_NETHER.contains(tName))
					return placeHive(tRegistry, aWorld, tX, tY-1, tZ, 0xaa0000          ,   300, aRandom) || rResult;
					for (String tName : aBiomeNames) if (BIOMES_SHROOM.contains(tName))
					return placeHive(tRegistry, aWorld, tX, tY-1, tZ, DYE_INT_Pink      ,   800, aRandom) || rResult;
					for (String tName : aBiomeNames) if (BIOMES_OCEAN_BEACH.contains(tName))
					return placeHive(tRegistry, aWorld, tX, tY-1, tZ, DYE_INT_LightBlue ,   100, aRandom) || rResult;
					for (String tName : aBiomeNames) if (BIOMES_JUNGLE.contains(tName))
					return placeHive(tRegistry, aWorld, tX, tY-1, tZ, DYE_INT_Green     ,   600, aRandom) || rResult;
					for (String tName : aBiomeNames) if (BIOMES_FROZEN.contains(tName))
					return placeHive(tRegistry, aWorld, tX, tY-1, tZ, DYE_INT_White     ,   700, aRandom) || rResult;
					if (tContact == Blocks.mycelium)
					return placeHive(tRegistry, aWorld, tX, tY-1, tZ, DYE_INT_Pink      ,   800, aRandom) || rResult;
					if (tContact == Blocks.sand && aWorld.getBlockMetadata(tX, tY, tZ) == 1)
					return placeHive(tRegistry, aWorld, tX, tY-1, tZ, DYE_INT_Red       ,   900, aRandom) || rResult;
					if (tContact == Blocks.sandstone || tContact.getMaterial() == Material.sand)
					return placeHive(tRegistry, aWorld, tX, tY-1, tZ, DYE_INT_Yellow    ,   900, aRandom) || rResult;
					if (tContact == Blocks.gravel || tContact.getMaterial() == Material.rock)
					return placeHive(tRegistry, aWorld, tX, tY-1, tZ, DYE_INT_LightGray ,   500, aRandom) || rResult;
					if (tContact == Blocks.grass || tContact.getMaterial() == Material.grass)
					return placeHive(tRegistry, aWorld, tX, tY-1, tZ, 0xffdd99          ,     0, aRandom) || rResult;
					if (tContact == Blocks.dirt || tContact.getMaterial() == Material.ground)
					return placeHive(tRegistry, aWorld, tX, tY-1, tZ, DYE_INT_Brown     ,     0, aRandom) || rResult;
					
					return placeHive(tRegistry, aWorld, tX, tY-1, tZ, DYE_INT_Gray      ,     0, aRandom) || rResult;
				}
				return rResult;
			}
			return rResult;
		}
		return rResult;
	}
	
	public boolean placeHive(MultiTileEntityRegistry aRegistry, World aWorld, int aX, int aY, int aZ, int aColor, int aSpeciesID, Random aRandom) {
		NBTTagCompound aBumbleTag = IItemBumbleBee.Util.getBumbleGenes(WD.envTemp(aWorld, aX, aY, aZ), aWorld.getBiomeGenForCoords(aX, aZ), !aWorld.provider.hasNoSky && aWorld.getPrecipitationHeight(aX, aZ) <= aY + 5, aRandom);
		return aRegistry.mBlock.placeBlock(aWorld, aX, aY, aZ, SIDE_UNKNOWN, (short)32755, UT.NBT.make(NBT_COLOR, aColor, NBT_INV_LIST, UT.NBT.makeInv(((IItemBumbleBee)ItemsGT.BUMBLEBEES).bumbleProductStack(NI, (short)aSpeciesID, UT.Code.units(IItemBumbleBee.Util.getWorkForce(aBumbleTag), 10000, 10, T), 0), IItemBumbleBee.Util.setBumbleTag(ST.make(ItemsGT.BUMBLEBEES, 1, aSpeciesID+1), aBumbleTag), IItemBumbleBee.Util.setBumbleTag(ST.make(ItemsGT.BUMBLEBEES, IItemBumbleBee.Util.getOffspring(aBumbleTag), aSpeciesID), aBumbleTag)), NBT_PAINTED, T), F, T);
	}
}
