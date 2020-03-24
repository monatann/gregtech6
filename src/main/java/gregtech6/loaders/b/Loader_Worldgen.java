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

package gregtech6.loaders.b;

import static gregapi6.data.CS.*;

import gregapi6.block.BlockBase;
import gregapi6.block.metatype.BlockStones;
import gregapi6.config.ConfigCategories;
import gregapi6.data.CS.BlocksGT;
import gregapi6.data.CS.ConfigsGT;
import gregapi6.data.FL;
import gregapi6.data.MD;
import gregapi6.data.MT;
import gregapi6.util.ST;
import gregapi6.worldgen.StoneLayer;
import gregapi6.worldgen.StoneLayerOres;
import gregapi6.worldgen.WorldgenOresBedrock;
import gregapi6.worldgen.WorldgenOresLarge;
import gregapi6.worldgen.WorldgenOresSmall;
import gregapi6.worldgen.WorldgenStone;
import gregapi6.worldgen.dungeon.WorldgenDungeonGT;
import gregtech6.worldgen.WorldgenBlackSand;
import gregtech6.worldgen.WorldgenBushes;
import gregtech6.worldgen.WorldgenDeepOcean;
import gregtech6.worldgen.WorldgenFluidSpring;
import gregtech6.worldgen.WorldgenGlowtus;
import gregtech6.worldgen.WorldgenHives;
import gregtech6.worldgen.WorldgenOcean;
import gregtech6.worldgen.WorldgenPit;
import gregtech6.worldgen.WorldgenRiver;
import gregtech6.worldgen.WorldgenRocks;
import gregtech6.worldgen.WorldgenSticks;
import gregtech6.worldgen.WorldgenStoneLayers;
import gregtech6.worldgen.WorldgenSwamp;
import gregtech6.worldgen.WorldgenTurf;
import gregtech6.worldgen.aether.WorldgenAetherRocks;
import gregtech6.worldgen.alfheim.WorldgenAlfheimRocks;
import gregtech6.worldgen.center.WorldgenBeacon;
import gregtech6.worldgen.center.WorldgenCenterBiomes;
import gregtech6.worldgen.center.WorldgenNexus;
import gregtech6.worldgen.center.WorldgenStreets;
import gregtech6.worldgen.center.WorldgenTesting;
import gregtech6.worldgen.erebus.WorldgenErebusRocks;
import gregtech6.worldgen.mars.WorldgenMarsRocks;
import gregtech6.worldgen.moon.WorldgenMoonRocks;
import gregtech6.worldgen.planets.WorldgenPlanetRocks;
import gregtech6.worldgen.tree.WorldgenLogDry;
import gregtech6.worldgen.tree.WorldgenLogFrozen;
import gregtech6.worldgen.tree.WorldgenLogMossy;
import gregtech6.worldgen.tree.WorldgenLogRotten;
import gregtech6.worldgen.tree.WorldgenTreeBlueMahoe;
import gregtech6.worldgen.tree.WorldgenTreeCinnamon;
import gregtech6.worldgen.tree.WorldgenTreeHazel;
import gregtech6.worldgen.tree.WorldgenTreeMaple;
import gregtech6.worldgen.tree.WorldgenTreeRainbowood;
import gregtech6.worldgen.tree.WorldgenTreeRubber;
import gregtech6.worldgen.tree.WorldgenTreeWillow;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

/**
 * @author Gregorius Techneticies
 */
public class Loader_Worldgen implements Runnable {
	@Override
	public void run() {
		boolean
		tInfiniteOil = ConfigsGT.WORLDGEN.get(ConfigCategories.general, "GenerateInfiniteOilSources", T),
		tInfiniteGas = ConfigsGT.WORLDGEN.get(ConfigCategories.general, "GenerateInfiniteGasSources", T);

		new WorldgenStoneLayers("stonelayers", T, GEN_GT, GEN_ENVM); // MUST BE FIRST



		StoneLayer.LAYERS.add(new StoneLayer(Blocks.stone, 0, Blocks.cobblestone, 0, Blocks.mossy_cobblestone, 0, MT.Stone));
		StoneLayer.LAYERS.add(new StoneLayer(Blocks.stone, 0, Blocks.cobblestone, 0, Blocks.mossy_cobblestone, 0, MT.Stone));
		StoneLayer.LAYERS.add(new StoneLayer(Blocks.stone, 0, Blocks.cobblestone, 0, Blocks.mossy_cobblestone, 0, MT.Stone));
		StoneLayer.LAYERS.add(new StoneLayer(Blocks.stone, 0, Blocks.cobblestone, 0, Blocks.mossy_cobblestone, 0, MT.Stone));
		StoneLayer.LAYERS.add(new StoneLayer(Blocks.stone, 0, Blocks.cobblestone, 0, Blocks.mossy_cobblestone, 0, MT.Stone
		, MD.Mek .mLoaded ? new StoneLayerOres(MT.FakeOsmium            , U32, 30, 60) : null
		, MD.RP  .mLoaded ? new StoneLayerOres(MT.Nikolite              , U64,  0, 20) : null
		, MD.BP  .mLoaded ? new StoneLayerOres(MT.Teslatite             , U64,  0, 20) : null
		, MD.PR  .mLoaded ? new StoneLayerOres(MT.Electrotine           , U64,  0, 20) : null
		));
		StoneLayer.LAYERS.add(new StoneLayer(Blocks.stone, 0, Blocks.cobblestone, 0, Blocks.mossy_cobblestone, 0, MT.Stone
		, MD.ARS .mLoaded ? new StoneLayerOres(MT.Vinteum               , U64, 16, 40) : null
		, MD.TC  .mLoaded ? new StoneLayerOres(MT.InfusedOrder          , U64, 24, 40, BIOMES_MAGICAL_GOOD) : null
		, MD.TC  .mLoaded ? new StoneLayerOres(MT.InfusedWater          , U64, 48, 64, BIOMES_MAGICAL_GOOD) : null
		, MD.TC  .mLoaded ? new StoneLayerOres(MT.InfusedEarth          , U64,  0, 16, BIOMES_MAGICAL_GOOD) : null
		));
		StoneLayer.LAYERS.add(new StoneLayer(Blocks.stone, 0, Blocks.cobblestone, 0, Blocks.mossy_cobblestone, 0, MT.Stone
		, MD.TC  .mLoaded ? new StoneLayerOres(MT.InfusedEntropy        , U64, 24, 40, BIOMES_MAGICAL_GOOD) : null
		, MD.TC  .mLoaded ? new StoneLayerOres(MT.InfusedAir            , U64, 48, 64, BIOMES_MAGICAL_GOOD) : null
		, MD.TC  .mLoaded ? new StoneLayerOres(MT.InfusedFire           , U64,  0, 16, BIOMES_MAGICAL_GOOD) : null
		));
		StoneLayer.LAYERS.add(new StoneLayer(Blocks.stone, 0, Blocks.cobblestone, 0, Blocks.mossy_cobblestone, 0, MT.Stone
		, new StoneLayerOres(MT.Fe2O3                   , U6 , 30, 70)
		, new StoneLayerOres(MT.Jasper                  , U64, 32, 64, BIOMES_MOUNTAINS)
		, new StoneLayerOres(MT.JasperBlue              , U64, 32, 64, BIOMES_FROZEN)
		, new StoneLayerOres(MT.JasperGreen             , U64, 32, 64, BIOMES_DESERT)
		, new StoneLayerOres(MT.JasperYellow            , U64, 32, 64, BIOMES_SAVANNA)
		, new StoneLayerOres(MT.JasperRainforest        , U64, 32, 64, BIOMES_JUNGLE)
		, new StoneLayerOres(MT.JasperOcean             , U64, 32, 64, BIOMES_OCEAN_BEACH)
		, new StoneLayerOres(MT.TigerEyeYellow          , U64, 32, 64, BIOMES_TAIGA)
		, new StoneLayerOres(MT.TigerEyeGreen           , U64, 32, 64, BIOMES_SWAMP)
		, new StoneLayerOres(MT.TigerEyeRed             , U64, 32, 64, BIOMES_PLAINS)
		, new StoneLayerOres(MT.TigerEyeBlue            , U64, 32, 64, BIOMES_RIVER_LAKE)
		, new StoneLayerOres(MT.TigerEyeBlack           , U64, 32, 64, BIOMES_MESA)
		, new StoneLayerOres(MT.TigerIron               , U64, 32, 64, BIOMES_SHROOM)
		));


		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.GraniteBlack));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.GraniteBlack));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.GraniteBlack
		, new StoneLayerOres(MT.OREMATS.Cooperite       , U32,  0, 16)
		, new StoneLayerOres(MT.OREMATS.Sperrylite      , U32,  0, 16)
		, new StoneLayerOres(MT.Ir                      , U64,  0,  8)
		, new StoneLayerOres(MT.Emerald                 , U64, 24, 48, BIOMES_MOUNTAINS)
		, new StoneLayerOres(MT.Aquamarine              , U64,  8, 32, BIOMES_OCEAN_BEACH)
		, new StoneLayerOres(MT.Morganite               , U64, 24, 48, BIOMES_JUNGLE)
		, new StoneLayerOres(MT.Heliodor                , U64, 24, 48, BIOMES_DESERT)
		, new StoneLayerOres(MT.Goshenite               , U64, 24, 48, BIOMES_FROZEN)
		, new StoneLayerOres(MT.Bixbite                 , U64, 24, 48, BIOMES_SAVANNA)
		, new StoneLayerOres(MT.Maxixe                  , U64, 24, 48, BIOMES_TAIGA)
		));


		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.GraniteRed));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.GraniteRed));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.GraniteRed
		, new StoneLayerOres(MT.OREMATS.Pitchblende     , U32,  0, 18)
		, new StoneLayerOres(MT.OREMATS.Uraninite       , U32,  0, 16)
		, new StoneLayerOres(MT.OREMATS.Tantalite       , U16, 20, 50)
		));


		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Komatiite));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Komatiite));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Komatiite));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Komatiite));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Komatiite
		, new StoneLayerOres(MT.OREMATS.Magnesite       , U16, 20, 50)
		, new StoneLayerOres(MT.OREMATS.Cinnabar        , U12,  0, 32)
		, new StoneLayerOres(MT.Redstone                , U8 ,  0, 30)
		, new StoneLayerOres(MT.Pyrite                  , U12,  0, 30)
		));


		Block
		tBlock = ST.block(MD.GaSu, "basalt");
		if (tBlock != NB)
		StoneLayer.LAYERS.add(new StoneLayer(tBlock, MT.Basalt));
		else
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Basalt));
		tBlock = ST.block(MD.PR_EXPLORATION, "projectred.exploration.stone");
		if (tBlock != NB)
		StoneLayer.LAYERS.add(new StoneLayer(tBlock, 3, tBlock, 2, MT.Basalt));
		else
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Basalt));
		tBlock = ST.block(MD.BP, "basalt");
		if (tBlock != NB)
		StoneLayer.LAYERS.add(new StoneLayer(tBlock, MT.Basalt));
		else
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Basalt));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Basalt
		, new StoneLayerOres(MT.OREMATS.Bastnasite      , U24, 24, 32)
		, new StoneLayerOres(MT.Monazite                , U32, 24, 32)
		, new StoneLayerOres(MT.MnO2                    , U8 , 16, 48)
		));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Basalt
		, new StoneLayerOres(MT.Olivine                 , U32,  0, 32)
		, new StoneLayerOres(MT.Uvarovite               , U32,  8, 40)
		, new StoneLayerOres(MT.Grossular               , U32, 16, 48)
		, new StoneLayerOres(MT.OREMATS.Chromite        , U8 , 32, 64)
		));


		tBlock = ST.block(MD.CHSL, "marble");
		if (tBlock != NB)
		StoneLayer.LAYERS.add(new StoneLayer(tBlock, MT.Marble));
		else
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Marble));
		tBlock = ST.block(MD.RC, "cube");
		if (tBlock != NB)
		StoneLayer.LAYERS.add(new StoneLayer(tBlock, 7, MT.Marble));
		else
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Marble));
		tBlock = ST.block(MD.PR_EXPLORATION, "projectred.exploration.stone");
		if (tBlock != NB)
		StoneLayer.LAYERS.add(new StoneLayer(tBlock, MT.Marble));
		else
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Marble));
		tBlock = ST.block(MD.BP, "marble");
		if (tBlock != NB)
		StoneLayer.LAYERS.add(new StoneLayer(tBlock, MT.Marble));
		else
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Marble));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Marble
		, new StoneLayerOres(MT.Dioptase                , U64, 24, 48, BIOMES_MOUNTAINS)
		));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Marble
		, new StoneLayerOres(MT.OREMATS.Cassiterite     , U16, 40, 80)
		, new StoneLayerOres(MT.OREMATS.Stannite        , U16, 38, 82)
		, new StoneLayerOres(MT.OREMATS.Kesterite       , U16, 38, 82)
		, new StoneLayerOres(MT.OREMATS.Sphalerite      , U8 , 10, 30)
		, new StoneLayerOres(MT.OREMATS.Chalcopyrite    , U8 ,  0, 20)
		, new StoneLayerOres(MT.Pyrite                  , U12,  0, 30)
		));


		tBlock = ST.block(MD.CHSL, "limestone");
		if (tBlock != NB)
		StoneLayer.LAYERS.add(new StoneLayer(tBlock, MT.Limestone));
		else
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Limestone));
		tBlock = ST.block(MD.BoP, "rocks");
		if (tBlock != NB)
		StoneLayer.LAYERS.add(new StoneLayer(tBlock, MT.Limestone));
		else
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Limestone));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Limestone));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Limestone
		, new StoneLayerOres(MT.Pyrite                  , U16,  0, 30)
		, new StoneLayerOres(MT.OREMATS.Arsenopyrite    , U16,  0, 20)
		, new StoneLayerOres(MT.OREMATS.Galena          , U8 ,  5, 25)
		, new StoneLayerOres(MT.OREMATS.Stibnite        , U24, 10, 30)
		, new StoneLayerOres(MT.OREMATS.Powellite       , U24, 30, 50)
		, new StoneLayerOres(MT.OREMATS.Molybdenite     ,U128, 30, 50)
		, new StoneLayerOres(MT.OREMATS.Tetrahedrite    , U8 , 40, 80)
		, new StoneLayerOres(MT.Cu                      , U16, 40, 80)
		));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Limestone
		, new StoneLayerOres(MT.OREMATS.Scheelite       , U64,  0, 16)
		, new StoneLayerOres(MT.OREMATS.Wolframite      , U64,  0, 16)
		, new StoneLayerOres(MT.OREMATS.Ferberite       , U64,  0, 16)
		, new StoneLayerOres(MT.OREMATS.Huebnerite      , U64,  0, 16)
		, new StoneLayerOres(MT.OREMATS.Tungstate       , U64,  0, 16)
		, new StoneLayerOres(MT.OREMATS.YellowLimonite  , U8 , 16, 48)
		, new StoneLayerOres(MT.OREMATS.BrownLimonite   , U8 , 32, 64)
		, new StoneLayerOres(MT.OREMATS.Malachite       , U12, 16, 64)
		, new StoneLayerOres(MT.Azurite                 , U24, 16, 64)
		));


		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.SchistGreen));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.SchistGreen
		, new StoneLayerOres(MT.Andradite               , U32,  8, 40)
		, new StoneLayerOres(MT.Almandine               , U32, 16, 48)
		));


		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.SchistBlue));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.SchistBlue
		, new StoneLayerOres(MT.Spessartine             , U32,  8, 40)
		, new StoneLayerOres(MT.Pyrope                  , U32, 16, 48)
		));


		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Kimberlite));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Kimberlite));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Kimberlite));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Kimberlite
		, new StoneLayerOres(MT.Diamond                 ,U128,  0, 12)
		, new StoneLayerOres(MT.Spinel                  , U64, 24, 48, BIOMES_MOUNTAINS)
		, new StoneLayerOres(MT.BalasRuby               , U64, 24, 48, BIOMES_JUNGLE)
		));


		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Quartzite));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Quartzite));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Quartzite));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Quartzite));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Quartzite
		, new StoneLayerOres(MT.CertusQuartz            , U16, 16, 48)
		, new StoneLayerOres(MT.OREMATS.Barite          , U32,  0, 32)
		));


		tBlock = ST.block(MD.BoP, "rocks");
		if (tBlock != NB)
		StoneLayer.LAYERS.add(new StoneLayer(tBlock, 4, MT.Shale));
		else
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Granite));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Granite));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Granite));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Granite
		, new StoneLayerOres(MT.BlueTopaz               , U64,  8, 32, BIOMES_OCEAN_BEACH)
		, new StoneLayerOres(MT.Topaz                   , U64, 24, 48, BIOMES_FROZEN)
		));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Granite
		, new StoneLayerOres(MT.Apatite                 , U8 , 32, 64)
		, new StoneLayerOres(MT.PO4                     , U24, 36, 60)
		, new StoneLayerOres(MT.Phosphorite             , U24, 40, 56)
		, new StoneLayerOres(MT.Phosphorus              , U24, 44, 52)
		));


		tBlock = ST.block(MD.BoP, "rocks");
		if (tBlock != NB)
		StoneLayer.LAYERS.add(new StoneLayer(tBlock, 2, MT.Siltstone));
		else
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Diorite));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Diorite));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Diorite));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Diorite
		, new StoneLayerOres(MT.Sapphire                , U64, 24, 48, BIOMES_FROZEN)
		, new StoneLayerOres(MT.GreenSapphire           , U64, 24, 48, BIOMES_JUNGLE)
		, new StoneLayerOres(MT.YellowSapphire          , U64, 24, 48, BIOMES_MOUNTAINS)
		, new StoneLayerOres(MT.OrangeSapphire          , U64, 24, 48, BIOMES_SAVANNA)
		, new StoneLayerOres(MT.BlueSapphire            , U64,  8, 32, BIOMES_OCEAN_BEACH)
		, new StoneLayerOres(MT.PurpleSapphire          , U64, 24, 48, BIOMES_TAIGA)
		, new StoneLayerOres(MT.Ruby                    , U64, 24, 48, BIOMES_DESERT)
		));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Diorite
		, new StoneLayerOres(MT.OREMATS.Garnierite      , U8 , 16, 48)
		, new StoneLayerOres(MT.OREMATS.Pentlandite     , U8 , 24, 56)
		, new StoneLayerOres(MT.OREMATS.Cobaltite       , U8 , 32, 64)
		, new StoneLayerOres(MT.Craponite               , U64, 24, 48, BIOMES_JUNGLE)
		, new StoneLayerOres(MT.Amethyst                , U64, 24, 48, BIOMES_TAIGA)
		, new StoneLayerOres(MT.Alexandrite             , U64, 24, 48, BIOMES_JUNGLE)
		));


		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Andesite));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Andesite));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Andesite));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Andesite));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Andesite
		, new StoneLayerOres(MT.OREMATS.Bromargyrite    , U8 ,  0, 20)
		));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Andesite
		, new StoneLayerOres(MT.Au                      , U32,  0, 20)
		));


		if (MD.EB.mLoaded) {
		Block tStone = ST.block(MD.EB, "enhancedbiomes.tile.stoneEB"), tCobble = ST.block(MD.EB, "enhancedbiomes.tile.stoneCobbleEB");
		StoneLayer.LAYERS.add(new StoneLayer(tStone, 0, tCobble, 0, MT.Basalt    ));
		StoneLayer.LAYERS.add(new StoneLayer(tStone, 1, tCobble, 1, MT.Shale     ));
		StoneLayer.LAYERS.add(new StoneLayer(tStone, 2, tCobble, 2, MT.Sand      ));
		StoneLayer.LAYERS.add(new StoneLayer(tStone, 3, tCobble, 3, MT.Limestone ));
		StoneLayer.LAYERS.add(new StoneLayer(tStone, 4, tCobble, 4, MT.Slate     ));
		StoneLayer.LAYERS.add(new StoneLayer(tStone, 5, tCobble, 5, MT.Rhyolite  ));
		StoneLayer.LAYERS.add(new StoneLayer(tStone, 6, tCobble, 6, MT.Chalk     ));
		StoneLayer.LAYERS.add(new StoneLayer(tStone, 7, tCobble, 7, MT.Marble    ));
		StoneLayer.LAYERS.add(new StoneLayer(tStone, 8, tCobble, 8, MT.Dolomite  ));
		StoneLayer.LAYERS.add(new StoneLayer(tStone, 9, tCobble, 9, MT.Blueschist)); // More of a Neutral Gray
		StoneLayer.LAYERS.add(new StoneLayer(tStone,10, tCobble,10, MT.Chert     ));
		StoneLayer.LAYERS.add(new StoneLayer(tStone,11, tCobble,11, MT.Gabbro    ));
		StoneLayer.LAYERS.add(new StoneLayer(tStone,12, tCobble,12, MT.Dacite    ));
		}


		if (MD.MIN.mLoaded) {
		StoneLayer.LAYERS.add(new StoneLayer(ST.block(MD.MIN, "basalt"      ), MT.Basalt    ));
		StoneLayer.LAYERS.add(new StoneLayer(ST.block(MD.MIN, "granite"     ), MT.Granite   ));
		StoneLayer.LAYERS.add(new StoneLayer(ST.block(MD.MIN, "andesite"    ), MT.Andesite  ));
		StoneLayer.LAYERS.add(new StoneLayer(ST.block(MD.MIN, "diorite"     ), MT.Diorite   ));
		StoneLayer.LAYERS.add(new StoneLayer(ST.block(MD.MIN, "limestone"   ), MT.Limestone ));
		StoneLayer.LAYERS.add(new StoneLayer(ST.block(MD.MIN, "shale"       ), MT.Shale     ));
		StoneLayer.LAYERS.add(new StoneLayer(ST.block(MD.MIN, "slate"       ), MT.Slate     ));
		StoneLayer.LAYERS.add(new StoneLayer(ST.block(MD.MIN, "schist"      ), MT.Blueschist));
		StoneLayer.LAYERS.add(new StoneLayer(ST.block(MD.MIN, "gneiss"      ), MT.Gneiss    ));
		StoneLayer.LAYERS.add(new StoneLayer(ST.block(MD.MIN, "dolomite"    ), MT.Dolomite  ));
		StoneLayer.LAYERS.add(new StoneLayer(ST.block(MD.MIN, "rhyolite"    ), MT.Rhyolite  ));
		StoneLayer.LAYERS.add(new StoneLayer(ST.block(MD.MIN, "pumice"      ), MT.Stone     ));
		StoneLayer.LAYERS.add(new StoneLayer(ST.block(MD.MIN, "conglomerate"), MT.Stone     ));
		StoneLayer.LAYERS.add(new StoneLayer(ST.block(MD.MIN, "pegmatite"   ), MT.Stone     ));
		StoneLayer.LAYERS.add(new StoneLayer(ST.block(MD.MIN, "chert"       ), MT.Chert     ));

		StoneLayer.LAYERS.add(new StoneLayer(ST.block(MD.MIN, "sulfur_ore"  ), MT.S         ));
		StoneLayer.LAYERS.add(new StoneLayer(ST.block(MD.MIN, "nitrate_ore" ), MT.KNO3      ));
		}


		if (MD.UB.mLoaded) {
		Block tStone = ST.block(MD.UB, "igneousStone"), tCobble = ST.block(MD.UB, "igneousCobblestone");
		StoneLayer.LAYERS.add(new StoneLayer(tStone, 0+8, tCobble, 0, MT.GraniteRed  ));
		StoneLayer.LAYERS.add(new StoneLayer(tStone, 1+8, tCobble, 1, MT.GraniteBlack));
		StoneLayer.LAYERS.add(new StoneLayer(tStone, 2+8, tCobble, 2, MT.Rhyolite    ));
		StoneLayer.LAYERS.add(new StoneLayer(tStone, 3+8, tCobble, 3, MT.Andesite    ));
		StoneLayer.LAYERS.add(new StoneLayer(tStone, 4+8, tCobble, 4, MT.Gabbro      ));
		StoneLayer.LAYERS.add(new StoneLayer(tStone, 5+8, tCobble, 5, MT.Basalt      ));
		StoneLayer.LAYERS.add(new StoneLayer(tStone, 6+8, tCobble, 6, MT.Komatiite   ));
		StoneLayer.LAYERS.add(new StoneLayer(tStone, 7+8, tCobble, 7, MT.Dacite      ));
		tStone = ST.block(MD.UB, "metamorphicStone"); tCobble = ST.block(MD.UB, "metamorphicCobblestone");
		StoneLayer.LAYERS.add(new StoneLayer(tStone, 0+8, tCobble, 0, MT.Gneiss      ));
		StoneLayer.LAYERS.add(new StoneLayer(tStone, 1+8, tCobble, 1, MT.Eclogite    ));
		StoneLayer.LAYERS.add(new StoneLayer(tStone, 2+8, tCobble, 2, MT.Marble      ));
		StoneLayer.LAYERS.add(new StoneLayer(tStone, 3+8, tCobble, 3, MT.Quartzite   ));
		StoneLayer.LAYERS.add(new StoneLayer(tStone, 4+8, tCobble, 4, MT.Blueschist  ));
		StoneLayer.LAYERS.add(new StoneLayer(tStone, 5+8, tCobble, 5, MT.Greenschist ));
		StoneLayer.LAYERS.add(new StoneLayer(tStone, 6+8, tCobble, 6, MT.Soapstone   ));
		StoneLayer.LAYERS.add(new StoneLayer(tStone, 7+8, tCobble, 7, MT.Migmatite   ));
		tStone = ST.block(MD.UB, "sedimentaryStone");
		StoneLayer.LAYERS.add(new StoneLayer(tStone, 0+8, MT.Limestone));
		StoneLayer.LAYERS.add(new StoneLayer(tStone, 1+8, MT.Chalk    ));
		StoneLayer.LAYERS.add(new StoneLayer(tStone, 2+8, MT.Shale    ));
		StoneLayer.LAYERS.add(new StoneLayer(tStone, 3+8, MT.Siltstone));
		StoneLayer.LAYERS.add(new StoneLayer(tStone, 4+8, MT.Lignite  ));
		StoneLayer.LAYERS.add(new StoneLayer(tStone, 5+8, MT.Dolomite ));
		StoneLayer.LAYERS.add(new StoneLayer(tStone, 6+8, MT.Greywacke));
		StoneLayer.LAYERS.add(new StoneLayer(tStone, 7+8, MT.Chert    ));
		}


		StoneLayer.put(BlocksGT.Basalt, BlocksGT.Limestone
		, new StoneLayerOres(MT.OREMATS.Ilmenite        , U8 ,  0, 32)
		, new StoneLayerOres(MT.TiO2                    , U12,  0, 32)
		);
		StoneLayer.put(BlocksGT.Basalt, 0, BlocksGT.RockOres, 2
		, new StoneLayerOres(MT.OREMATS.Zeolite         , U8 , 16, 48)
		);
		StoneLayer.put(BlocksGT.Basalt, 0, BlocksGT.RockOres, 3
		, new StoneLayerOres(MT.OREMATS.Lepidolite      , U12, 16, 48)
		, new StoneLayerOres(MT.OREMATS.Spodumene       , U12, 32, 64)
		);
		StoneLayer.put(BlocksGT.Diorite, BlocksGT.Limestone
		, new StoneLayerOres(MT.Opal                    , U64, 48, 64)
		);
		StoneLayer.put(BlocksGT.Limestone, 0, BlocksGT.RockOres, 2
		, new StoneLayerOres(MT.OREMATS.Borax           , U8 , 16, 48)
		);
		StoneLayer.put(BlocksGT.Limestone, 0, BlocksGT.RockOres, 3
		, new StoneLayerOres(MT.KIO3                        , U12, 32, 64)
		);
		StoneLayer.put(BlocksGT.GraniteBlack, BlocksGT.Marble
		, new StoneLayerOres(MT.Lapis                   , U8 ,  0, 48)
		, new StoneLayerOres(MT.Sodalite                , U16,  0, 48)
		, new StoneLayerOres(MT.Lazurite                , U16,  0, 48)
		, new StoneLayerOres(MT.Pyrite                  , U16,  0, 48)
		);
		StoneLayer.put(BlocksGT.GraniteBlack, BlocksGT.Basalt
		, new StoneLayerOres(MT.Diamond                 , U64,  0, 32)
		, new StoneLayerOres(MT.DiamondPink             , U48,  0, 32, BIOMES_JUNGLE)
		, new StoneLayerOres(MT.Graphite                , U8 ,  0, 32)
		);
		StoneLayer.put(BlocksGT.GraniteBlack, BlocksGT.GraniteRed
		, new StoneLayerOres(MT.Zircon                  , U24,  0, 32)
		);

		// TODO Asbestos missing!




		new WorldgenOcean           ("ocean.seawater"    , T, GEN_OVERWORLD, GEN_GT, GEN_PFAA); // IT IS IMPORTANT THAT OCEAN COMES BEFORE RIVER AND SWAMP!!!
		new WorldgenRiver           ("river.riverwater"  , T, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_TWILIGHT); // IT IS IMPORTANT THAT RIVER COMES AFTER OCEAN AND BEFORE SWAMP!!!
		new WorldgenSwamp           ("swamp.dirtywater"  , T, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_TWILIGHT, GEN_EREBUS); // IT IS IMPORTANT THAT SWAMP COMES AFTER RIVER AND OCEAN!!!

		new WorldgenDeepOcean       ("ocean.prismacorals", T, GEN_OVERWORLD, GEN_GT, GEN_PFAA);

		new WorldgenPit             ("pit.clay.vanilla"  , T, Blocks.clay       , 0, 1, 256, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_TWILIGHT, GEN_TROPICS, GEN_ALFHEIM);
		new WorldgenPit             ("pit.clay.brown"    , T, BlocksGT.Diggables, 1, 3, 256, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_TWILIGHT, GEN_TROPICS, GEN_ALFHEIM);

		new WorldgenBlackSand       ("river.magnetite"   , T, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_TWILIGHT, GEN_TROPICS);
		new WorldgenTurf            ("swamp.turf"        , T, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_TWILIGHT);

		new WorldgenLogDry          ("log.dry"           , T, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_TWILIGHT, GEN_EREBUS, GEN_TROPICS, GEN_ALFHEIM);
		new WorldgenLogRotten       ("log.rotten"        , T, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_TWILIGHT, GEN_EREBUS, GEN_TROPICS);
		new WorldgenLogMossy        ("log.mossy"         , T, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_TWILIGHT, GEN_EREBUS, GEN_TROPICS, GEN_ALFHEIM);
		new WorldgenLogFrozen       ("log.frozen"        , T, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_TWILIGHT, GEN_EREBUS);

		new WorldgenTreeRubber      ("tree.rubber"       , T, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_TWILIGHT);
		new WorldgenTreeMaple       ("tree.maple"        , T, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_TWILIGHT, GEN_EREBUS);
		new WorldgenTreeWillow      ("tree.willow"       , T, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_TWILIGHT, GEN_EREBUS);
		new WorldgenTreeBlueMahoe   ("tree.bluemahoe"    , T, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_TWILIGHT, GEN_EREBUS, GEN_TROPICS);
		new WorldgenTreeHazel       ("tree.hazel"        , T, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_TWILIGHT, GEN_EREBUS, GEN_ALFHEIM);
		new WorldgenTreeCinnamon    ("tree.cinnamon"     , T, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_TWILIGHT, GEN_EREBUS, GEN_TROPICS);
		new WorldgenTreeRainbowood  ("tree.rainbowood"   , T, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_TWILIGHT, GEN_EREBUS, GEN_ALFHEIM, GEN_AETHER);

		new WorldgenRocks           ("rocks"             , T, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_TWILIGHT, GEN_TROPICS, GEN_ATUM);
		new WorldgenAlfheimRocks    ("alfheim.rocks"     , T, GEN_ALFHEIM);
		new WorldgenAetherRocks     ("aether.rocks"      , T, GEN_AETHER);
		new WorldgenErebusRocks     ("erebus.rocks"      , T, GEN_EREBUS);
		new WorldgenMoonRocks       ("moon.rocks"        , T, GEN_MOON);
		new WorldgenMarsRocks       ("mars.rocks"        , T, GEN_MARS);
		new WorldgenPlanetRocks     ("planet.rocks"      , T, GEN_PLANETS);
		new WorldgenSticks          ("sticks"            , T, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_TWILIGHT, GEN_TROPICS, GEN_EREBUS, GEN_ALFHEIM, GEN_BETWEENLANDS, GEN_AETHER, GEN_ENVM);

		new WorldgenGlowtus         ("plant.glowtus"     , T, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_TWILIGHT, GEN_TROPICS, GEN_EREBUS);
		new WorldgenBushes          ("plant.bush"        , T, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_TWILIGHT, GEN_TROPICS, GEN_EREBUS, GEN_ALFHEIM);
		new WorldgenHives           ("bumblehives"       , T, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_TWILIGHT, GEN_TROPICS, GEN_EREBUS, GEN_ALFHEIM, GEN_BETWEENLANDS, GEN_AETHER, GEN_ENVM, GEN_ATUM, GEN_NETHER, GEN_END);

		new WorldgenCenterBiomes    ("center.biomes"     , F, GEN_OVERWORLD, GEN_GT, GEN_PFAA);
		new WorldgenStreets         ("center.streets"    , F, GEN_OVERWORLD, GEN_GT, GEN_PFAA);
		new WorldgenNexus           ("center.nexus"      , F, GEN_OVERWORLD, GEN_GT, GEN_PFAA);
		new WorldgenBeacon          ("center.beacon"     , F, GEN_OVERWORLD, GEN_GT, GEN_PFAA);
		new WorldgenTesting         ("center.testing"    , F, GEN_OVERWORLD, GEN_GT, GEN_PFAA);

		new WorldgenDungeonGT("overworld.structure.dungeon.large", T, 100, 3, 7, 20, 20, 6, T, F, F, T, T, T, T, GEN_OVERWORLD, GEN_GT, GEN_PFAA);

		new WorldgenFluidSpring("overworld.fluid.oil.extraheavy", T, BlocksGT.OilExtraHeavy,  7,  1, 50,200,   0,  16, null, F, tInfiniteOil ? FL.Oil_ExtraHeavy.make( 6000) : NF, 10, GEN_OVERWORLD, GEN_GT, GEN_PFAA);
		new WorldgenFluidSpring("overworld.fluid.oil.heavy"     , T, BlocksGT.OilHeavy     ,  7,  1, 60,150,   0,  16, null, F, tInfiniteOil ? FL.Oil_Heavy     .make( 6000) : NF, 10, GEN_OVERWORLD, GEN_GT, GEN_PFAA);
		new WorldgenFluidSpring("overworld.fluid.oil.medium"    , T, BlocksGT.OilMedium    ,  7,  1, 70,100,   0,  16, null, F, tInfiniteOil ? FL.Oil_Medium    .make( 6000) : NF, 10, GEN_OVERWORLD, GEN_GT, GEN_PFAA);
		new WorldgenFluidSpring("overworld.fluid.oil.light"     , T, BlocksGT.OilLight     ,  7,  1, 80, 50,   0,  16, null, F, tInfiniteOil ? FL.Oil_Light     .make( 6000) : NF, 10, GEN_OVERWORLD, GEN_GT, GEN_PFAA);
		new WorldgenFluidSpring("overworld.fluid.gas.natural"   , T, BlocksGT.GasNatural   ,  7,  1, 80, 25,   0,  16, null, F, tInfiniteGas ? FL.Gas_Natural   .make( 3000) : NF, 10, GEN_OVERWORLD, GEN_GT, GEN_PFAA);
		new WorldgenFluidSpring("overworld.fluid.water"         , T, Blocks.water          ,  0,  1, 50, 50,   0,  16, null, F, FL.Water.make(1), 10, GEN_OVERWORLD, GEN_GT, GEN_PFAA);
		new WorldgenFluidSpring("overworld.fluid.lava"          , T, Blocks.lava           ,  0,  1, 50, 50,   0,  16, null, F, FL.Lava.make(1000), 10, GEN_OVERWORLD, GEN_GT, GEN_PFAA);
		new WorldgenFluidSpring("nether.fluid.lava"             , T, Blocks.lava           ,  0,  1, 90, 25,   0,  16, null, F, FL.Lava.make(1), 10, GEN_NETHER);

		for (BlockBase tStone : BlocksGT.stones) {
		new WorldgenStone("erebus.stone."   + ((BlockStones)tStone).mMaterial.mNameInternal.toLowerCase(), T, tStone,  0,  1, 200, 200, 0, 120, null, F, GEN_EREBUS);
		new WorldgenStone("twilight.stone." + ((BlockStones)tStone).mMaterial.mNameInternal.toLowerCase(), T, tStone,  0,  1, 100, 200, 0,  40, null, F, GEN_TWILIGHT);
		new WorldgenStone("overworld.stone."+ ((BlockStones)tStone).mMaterial.mNameInternal.toLowerCase(), T, tStone,  0,  1, 200, 100, 0, 120, null, F, GEN_OVERWORLD, GEN_PFAA);
		new WorldgenStone("nether.stone."   + ((BlockStones)tStone).mMaterial.mNameInternal.toLowerCase(), F, tStone,  0,  1, 200, 200, 0, 120, null, F, GEN_NETHER);
		new WorldgenStone("tropics.stone."  + ((BlockStones)tStone).mMaterial.mNameInternal.toLowerCase(), T, tStone,  0,  1, 200, 100, 0, 120, null, F, GEN_TROPICS);
		}

		new WorldgenOresBedrock("ore.bedrock.tungstate"         , T, T,  96000, MT.OREMATS.Tungstate            , (Block)BlocksGT.FlowersA,  7, GEN_FLOOR); // TODO Flower
		new WorldgenOresBedrock("ore.bedrock.ferberite"         , T, T,  96000, MT.OREMATS.Ferberite            , (Block)BlocksGT.FlowersA,  7, GEN_FLOOR); // TODO Flower
		new WorldgenOresBedrock("ore.bedrock.wolframite"        , T, T,  96000, MT.OREMATS.Wolframite           , (Block)BlocksGT.FlowersA,  7, GEN_FLOOR); // TODO Flower
		new WorldgenOresBedrock("ore.bedrock.stolzite"          , T, T,  96000, MT.OREMATS.Stolzite             , (Block)BlocksGT.FlowersA,  7, GEN_FLOOR); // TODO Flower
		new WorldgenOresBedrock("ore.bedrock.scheelite"         , T, T,  96000, MT.OREMATS.Scheelite            , (Block)BlocksGT.FlowersA,  7, GEN_FLOOR); // TODO Flower
		new WorldgenOresBedrock("ore.bedrock.huebnerite"        , T, T,  96000, MT.OREMATS.Huebnerite           , (Block)BlocksGT.FlowersA,  7, GEN_FLOOR); // TODO Flower
		new WorldgenOresBedrock("ore.bedrock.russelite"         , T, T,  96000, MT.OREMATS.Russellite           , (Block)BlocksGT.FlowersA,  7, GEN_FLOOR); // TODO Flower
		new WorldgenOresBedrock("ore.bedrock.pinalite"          , T, T,  96000, MT.OREMATS.Pinalite             , (Block)BlocksGT.FlowersA,  7, GEN_FLOOR); // TODO Flower
		new WorldgenOresBedrock("ore.bedrock.uraninite"         , T, T,  60000, MT.OREMATS.Uraninite            , (Block)BlocksGT.FlowersA,  5, GEN_FLOOR);
		new WorldgenOresBedrock("ore.bedrock.pitchblende"       , T, T,  60000, MT.OREMATS.Pitchblende          , (Block)BlocksGT.FlowersB,  5, GEN_FLOOR);
		new WorldgenOresBedrock("ore.bedrock.cooperite"         , T, T,  16000, MT.OREMATS.Cooperite            , (Block)BlocksGT.FlowersA,  6, GEN_FLOOR);
		new WorldgenOresBedrock("ore.bedrock.copper"            , T, T,  16000, MT.Cu                           , (Block)BlocksGT.FlowersB,  3, GEN_FLOOR);
		new WorldgenOresBedrock("ore.bedrock.gold.a"            , T, T,  32000, MT.Au                           , (Block)BlocksGT.FlowersA,  0, GEN_FLOOR);
		new WorldgenOresBedrock("ore.bedrock.gold.b"            , T, T,  32000, MT.Au                           , (Block)BlocksGT.FlowersB,  2, GEN_FLOOR);
		new WorldgenOresBedrock("ore.bedrock.monazite"          , T, T,  16000, MT.Monazite                     , (Block)BlocksGT.FlowersA,  7, GEN_FLOOR); // TODO Flower
		new WorldgenOresBedrock("ore.bedrock.powellite"         , T, T,  14000, MT.OREMATS.Powellite            , (Block)BlocksGT.FlowersA,  7, GEN_FLOOR); // TODO Flower
		new WorldgenOresBedrock("ore.bedrock.stibnite"          , T, T,   8000, MT.OREMATS.Arsenopyrite         , (Block)BlocksGT.FlowersB,  0, GEN_FLOOR);
		new WorldgenOresBedrock("ore.bedrock.redstone"          , T, T,   7000, MT.Redstone                     , (Block)BlocksGT.FlowersB,  4, GEN_FLOOR);
		new WorldgenOresBedrock("ore.bedrock.vanadium"          , T, T,   6000, MT.V2O5                         , (Block)BlocksGT.FlowersA,  7, GEN_FLOOR); // TODO Flower
		new WorldgenOresBedrock("ore.bedrock.galena"            , T, T,   6000, MT.OREMATS.Galena               , (Block)BlocksGT.FlowersA,  1, GEN_FLOOR);
		new WorldgenOresBedrock("ore.bedrock.graphite"          , T, T,   5000, MT.Graphite                     , (Block)BlocksGT.FlowersA,  7, GEN_FLOOR); // TODO Flower
		new WorldgenOresBedrock("ore.bedrock.stibnite"          , T, T,   4000, MT.OREMATS.Stibnite             , (Block)BlocksGT.FlowersB,  1, GEN_FLOOR);
		new WorldgenOresBedrock("ore.bedrock.hematite"          , T, T,   4000, MT.Fe2O3                        , (Block)BlocksGT.FlowersA,  7, GEN_FLOOR); // TODO Flower
		new WorldgenOresBedrock("ore.bedrock.sphalerite"        , T, T,   3000, MT.OREMATS.Sphalerite           , (Block)BlocksGT.FlowersA,  3, GEN_FLOOR);
		new WorldgenOresBedrock("ore.bedrock.smithsonite"       , T, T,   3000, MT.OREMATS.Smithsonite          , (Block)BlocksGT.FlowersA,  3, GEN_FLOOR); // TODO Flower
		new WorldgenOresBedrock("ore.bedrock.pentlandite"       , T, T,   3000, MT.OREMATS.Pentlandite          , (Block)BlocksGT.FlowersA,  4, GEN_FLOOR); // TODO Flower
		new WorldgenOresBedrock("ore.bedrock.saltpeter"         , T, T,   3000, MT.Niter                        , (Block)BlocksGT.FlowersA,  7, GEN_FLOOR); // TODO Flower
		new WorldgenOresBedrock("ore.bedrock.bauxite"           , T, T,   2000, MT.OREMATS.Bauxite              , (Block)BlocksGT.FlowersA,  7, GEN_FLOOR); // TODO Flower
		new WorldgenOresBedrock("ore.bedrock.cassiterite"       , T, T,   2000, MT.OREMATS.Cassiterite          , (Block)BlocksGT.FlowersA,  7, GEN_FLOOR); // TODO Flower
		new WorldgenOresBedrock("ore.bedrock.chalcopyrite"      , T, T,   2000, MT.OREMATS.Chalcopyrite         , (Block)BlocksGT.FlowersA,  2, GEN_FLOOR);
		new WorldgenOresBedrock("ore.bedrock.naquadah"          , T, T,  10000, MT.Nq                           , (Block)BlocksGT.FlowersA,  7, GEN_MARS); // TODO Flower
		new WorldgenOresBedrock("ore.bedrock.adamantine"        , T, T,  10000, MT.Adamantine                   , (Block)BlocksGT.FlowersA,  7, GEN_MARS, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS); // TODO Flower

		for (int i = 0, j = ConfigsGT.WORLDGEN.get(ConfigCategories.general, "AmountOfCustomBedrockOreSlots", 0); i < j; i++) {
		new WorldgenOresBedrock("ore.bedrock.custom" + (i<10?"0":"") + i, F, T, 100000, MT.NULL, (Block)BlocksGT.FlowersA, 7, GEN_FLOOR);
		}

		new WorldgenOresSmall("ore.small.chalcopyrite"      , T,  60, 120,  16, MT.OREMATS.Chalcopyrite         , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_MARS, GEN_NETHER, GEN_END);
		new WorldgenOresSmall("ore.small.cassiterite"       , T,  60, 120,  16, MT.OREMATS.Cassiterite          , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_ALFHEIM, GEN_AETHER, GEN_MARS, GEN_NETHER, GEN_END);
		new WorldgenOresSmall("ore.small.copper"            , T,  60, 120,  16, MT.Cu                           , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_ALFHEIM, GEN_AETHER, GEN_MARS, GEN_NETHER, GEN_ASTEROIDS, GEN_MOON, GEN_PLANETS, GEN_END);
		new WorldgenOresSmall("ore.small.tin"               , T,  60, 120,  16, MT.Sn                           , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_ALFHEIM, GEN_AETHER, GEN_MARS, GEN_NETHER, GEN_ASTEROIDS, GEN_MOON, GEN_PLANETS, GEN_END);
		new WorldgenOresSmall("ore.small.bismuth"           , T,  80, 120,   8, MT.Bi                           , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_ALFHEIM, GEN_AETHER, GEN_MARS, GEN_NETHER, GEN_ASTEROIDS, GEN_MOON, GEN_PLANETS);
		new WorldgenOresSmall("ore.small.coal"              , T,  60, 100,  24, MT.Coal                         , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM);
		new WorldgenOresSmall("ore.small.hematite"          , T,  40,  80,  24, MT.Fe2O3                        , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_ALFHEIM, GEN_AETHER, GEN_MARS, GEN_NETHER, GEN_END);
		new WorldgenOresSmall("ore.small.salt"              , T,  40,  80,   6, MT.NaCl                         , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_ALFHEIM, GEN_AETHER, GEN_MARS, GEN_NETHER, GEN_END, GEN_PFAA);
		new WorldgenOresSmall("ore.small.rocksalt"          , T,  40,  80,   6, MT.KCl                          , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_ALFHEIM, GEN_AETHER, GEN_MARS, GEN_NETHER, GEN_END, GEN_PFAA);
		new WorldgenOresSmall("ore.small.sphalerite"        , T,  30,  60,  12, MT.OREMATS.Sphalerite           , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_MARS, GEN_NETHER, GEN_END);
		new WorldgenOresSmall("ore.small.zinc"              , T,  40,  70,   4, MT.Zn                           , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_ALFHEIM, GEN_AETHER, GEN_MARS, GEN_NETHER, GEN_END, GEN_ASTEROIDS, GEN_MOON, GEN_PLANETS);
		new WorldgenOresSmall("ore.small.smithsonite"       , T,  30,  60,   2, MT.OREMATS.Smithsonite          , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_ALFHEIM, GEN_AETHER, GEN_MARS, GEN_NETHER, GEN_END);
		new WorldgenOresSmall("ore.small.galena"            , T,  40,  80,  16, MT.OREMATS.Galena               , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_MARS, GEN_NETHER, GEN_END);
		new WorldgenOresSmall("ore.small.lead"              , T,  40,  80,  16, MT.Pb                           , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_ALFHEIM, GEN_AETHER, GEN_MARS, GEN_NETHER, GEN_END, GEN_ASTEROIDS, GEN_MOON, GEN_PLANETS);
		new WorldgenOresSmall("ore.small.gold"              , T,  20,  40,   4, MT.Au                           , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_ALFHEIM, GEN_AETHER, GEN_MARS, GEN_NETHER, GEN_END, GEN_ASTEROIDS, GEN_MOON, GEN_PLANETS);
		new WorldgenOresSmall("ore.small.pyrite"            , T,  20,  40,   4, MT.Pyrite                       , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_MARS, GEN_NETHER, GEN_END, GEN_ASTEROIDS, GEN_MOON, GEN_PLANETS);
		new WorldgenOresSmall("ore.small.silver"            , T,  20,  40,   4, MT.Ag                           , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_ALFHEIM, GEN_AETHER, GEN_MARS, GEN_NETHER, GEN_END, GEN_ASTEROIDS, GEN_MOON, GEN_PLANETS);
		new WorldgenOresSmall("ore.small.pyrolusite"        , T,  20,  40,   4, MT.MnO2                         , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_ALFHEIM, GEN_AETHER, GEN_MARS, GEN_NETHER, GEN_END, GEN_MOON, GEN_PLANETS);
		new WorldgenOresSmall("ore.small.garnierite"        , T,  20,  40,   4, MT.OREMATS.Garnierite           , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_ALFHEIM, GEN_AETHER, GEN_MARS, GEN_NETHER, GEN_END);
		new WorldgenOresSmall("ore.small.pentlandite"       , T,  20,  40,   4, MT.OREMATS.Pentlandite          , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_MARS, GEN_NETHER, GEN_END);
		new WorldgenOresSmall("ore.small.stibnite"          , T,  20,  40,   2, MT.OREMATS.Stibnite             , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_MARS, GEN_NETHER, GEN_END);
		new WorldgenOresSmall("ore.small.asbestos"          , T,  20,  40,   8, MT.Asbestos                     , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM);
		new WorldgenOresSmall("ore.small.eudialyte"         , T,  20,  40,   4, MT.Eudialyte                    , GEN_OVERWORLD, GEN_GT, GEN_ENVM);
		new WorldgenOresSmall("ore.small.azurite"           , T,  20,  40,   4, MT.Azurite                      , GEN_OVERWORLD, GEN_GT, GEN_ENVM);
		new WorldgenOresSmall("ore.small.zeolite"           , T,   1, 250,   1, MT.OREMATS.Zeolite              , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT);
		new WorldgenOresSmall("ore.small.lapis"             , T,  20,  40,   4, MT.Lapis                        , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_ALFHEIM, GEN_AETHER);
		new WorldgenOresSmall("ore.small.redstone"          , T,   5,  20,   8, MT.Redstone                     , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_ALFHEIM, GEN_AETHER, GEN_PFAA, GEN_MARS, GEN_PLANETS, GEN_NETHER);
		new WorldgenOresSmall("ore.small.graphite"          , T,   5,  10,   2, MT.Graphite                     , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_ALFHEIM, GEN_AETHER, GEN_PFAA, GEN_MARS, GEN_PLANETS, GEN_ASTEROIDS, GEN_NETHER);
		new WorldgenOresSmall("ore.small.scheelite"         , T,   5,  50,   1, MT.OREMATS.Scheelite            , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_ALFHEIM, GEN_AETHER, GEN_PFAA, GEN_MARS, GEN_PLANETS, GEN_ASTEROIDS, GEN_MOON, GEN_END);
		new WorldgenOresSmall("ore.small.cooperite"         , T,  20,  40,   4, MT.OREMATS.Cooperite            , GEN_END, GEN_ASTEROIDS);
		new WorldgenOresSmall("ore.small.sperrylite"        , T,  20,  40,   4, MT.OREMATS.Sperrylite           , GEN_END, GEN_ASTEROIDS, GEN_ALFHEIM, GEN_AETHER);
		new WorldgenOresSmall("ore.small.platinum"          , T,  20,  40,   6, MT.Pt                           , GEN_END, GEN_ASTEROIDS, GEN_ALFHEIM, GEN_AETHER);
		new WorldgenOresSmall("ore.small.iridium"           , T,  20,  40,   6, MT.Ir                           , GEN_END, GEN_ASTEROIDS, GEN_ALFHEIM, GEN_AETHER);
		new WorldgenOresSmall("ore.small.ambrosium"         , T,  30, 120,  64, MT.Ambrosium                    , GEN_AETHER);
		new WorldgenOresSmall("ore.small.zanite"            , T,  30, 120,  16, MT.Zanite                       , GEN_AETHER);
		new WorldgenOresSmall("ore.small.netherquartz"      , T,  30, 120,  64, MT.NetherQuartz                 , GEN_NETHER);
		new WorldgenOresSmall("ore.small.niter"             , T,  10,  60,  16, MT.Niter                        , GEN_NETHER);
		new WorldgenOresSmall("ore.small.sulfur_n"          , T,  10,  60,  32, MT.S                            , GEN_NETHER);
		new WorldgenOresSmall("ore.small.sulfur_o"          , T,   5,  15,   8, MT.S                            , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_MARS);


		new WorldgenOresSmall("ore.small.blackquartz"       , MD.AA .mLoaded,  20,  40,   1, MT.BlackQuartz         , GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM);
		new WorldgenOresSmall("ore.small.certus"            , MD.AE .mLoaded,  20,  40,   1, MT.CertusQuartz        , GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_MARS, GEN_PLANETS, GEN_ALFHEIM, GEN_ASTEROIDS, GEN_MOON);
		new WorldgenOresSmall("ore.small.vinteum"           , MD.ARS.mLoaded,  30,  60,   8, MT.Vinteum             , GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_MARS, GEN_PLANETS, GEN_ALFHEIM);
		new WorldgenOresSmall("ore.small.perdito"           , MD.TC .mLoaded,  10,  60,   8, MT.InfusedEntropy      , GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_MARS, GEN_PLANETS, GEN_ALFHEIM, GEN_NETHER, GEN_END);
		new WorldgenOresSmall("ore.small.ignis"             , MD.TC .mLoaded,  10,  60,   8, MT.InfusedFire         , GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_MARS, GEN_PLANETS, GEN_ALFHEIM, GEN_NETHER, GEN_END);
		new WorldgenOresSmall("ore.small.aer"               , MD.TC .mLoaded,  10,  60,   8, MT.InfusedAir          , GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_MARS, GEN_PLANETS, GEN_ALFHEIM, GEN_NETHER, GEN_END);
		new WorldgenOresSmall("ore.small.aqua"              , MD.TC .mLoaded,  10,  60,   8, MT.InfusedWater        , GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_MARS, GEN_PLANETS, GEN_ALFHEIM, GEN_NETHER, GEN_END);
		new WorldgenOresSmall("ore.small.terra"             , MD.TC .mLoaded,  10,  60,   8, MT.InfusedEarth        , GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_MARS, GEN_PLANETS, GEN_ALFHEIM, GEN_NETHER, GEN_END);
		new WorldgenOresSmall("ore.small.ordo"              , MD.TC .mLoaded,  10,  60,   8, MT.InfusedOrder        , GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_MARS, GEN_PLANETS, GEN_ALFHEIM, GEN_NETHER, GEN_END);
		new WorldgenOresSmall("ore.small.nikolite"          , MD.RP .mLoaded,  10,  40,   4, MT.Nikolite            , GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_MARS, GEN_PLANETS, GEN_ALFHEIM, GEN_NETHER, GEN_END);
		new WorldgenOresSmall("ore.small.teslatite"         , MD.BP .mLoaded,  10,  40,   4, MT.Teslatite           , GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_MARS, GEN_PLANETS, GEN_ALFHEIM, GEN_NETHER, GEN_END);
		new WorldgenOresSmall("ore.small.electrotine"       , MD.PR .mLoaded,  10,  40,   4, MT.Electrotine         , GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_MARS, GEN_PLANETS, GEN_ALFHEIM, GEN_NETHER, GEN_END);
		new WorldgenOresSmall("ore.small.bischofite"        , MD.IHL.mLoaded,  40,  80,   1, MT.OREMATS.Bischofite  , GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM);
		new WorldgenOresSmall("ore.small.datolite"          , MD.IHL.mLoaded,  40,  80,   1, MT.Datolite            , GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM);
		new WorldgenOresSmall("ore.small.mica"              , MD.IHL.mLoaded,  40,  80,   1, MT.OREMATS.Mica        , GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM);
		new WorldgenOresSmall("ore.small.gypsum"            , MD.IHL.mLoaded,  40,  80,   1, MT.OREMATS.Gypsum      , GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM);
		new WorldgenOresSmall("ore.small.potassiumfeldspar" , MD.IHL.mLoaded,  40,  80,   1, MT.PotassiumFeldspar   , GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM);
		new WorldgenOresSmall("ore.small.trona"             , MD.IHL.mLoaded,  40,  80,   1, MT.OREMATS.Trona       , GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM);


		new WorldgenOresSmall("ore.small.diamond"           , T,   5,  10,   2, MT.Diamond                      , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_PFAA, GEN_MARS, GEN_PLANETS, GEN_ASTEROIDS, GEN_MOON, GEN_NETHER);
		new WorldgenOresSmall("ore.small.emerald"           , T,   5, 250,   1, MT.Emerald                      , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_PFAA, GEN_MARS);
		new WorldgenOresSmall("ore.small.aquamarine"        , T,   5, 250,   1, MT.Aquamarine                   , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_PFAA, GEN_MARS);
		new WorldgenOresSmall("ore.small.morganite"         , T,   5, 250,   1, MT.Morganite                    , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_PFAA, GEN_MARS);
		new WorldgenOresSmall("ore.small.heliodor"          , T,   5, 250,   1, MT.Heliodor                     , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_PFAA, GEN_MARS);
		new WorldgenOresSmall("ore.small.goshenite"         , T,   5, 250,   1, MT.Goshenite                    , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_PFAA, GEN_MARS);
		new WorldgenOresSmall("ore.small.bixbite"           , T,   5, 250,   1, MT.Bixbite                      , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_PFAA, GEN_MARS);
		new WorldgenOresSmall("ore.small.maxixe"            , T,   5, 250,   1, MT.Maxixe                       , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_PFAA, GEN_MARS);
		new WorldgenOresSmall("ore.small.dioptase"          , T,   5, 250,   1, MT.Dioptase                     , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_PFAA, GEN_MARS);
		new WorldgenOresSmall("ore.small.ruby"              , T,   5, 250,   1, MT.Ruby                         , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_PFAA, GEN_MARS);
		new WorldgenOresSmall("ore.small.bluesapphire"      , T,   5, 250,   1, MT.BlueSapphire                 , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_PFAA, GEN_MARS);
		new WorldgenOresSmall("ore.small.greensapphire"     , T,   5, 250,   1, MT.GreenSapphire                , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_PFAA, GEN_MARS);
		new WorldgenOresSmall("ore.small.yellowsapphire"    , T,   5, 250,   1, MT.YellowSapphire               , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_PFAA, GEN_MARS);
		new WorldgenOresSmall("ore.small.orangesapphire"    , T,   5, 250,   1, MT.OrangeSapphire               , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_PFAA, GEN_MARS);
		new WorldgenOresSmall("ore.small.purplesapphire"    , T,   5, 250,   1, MT.PurpleSapphire               , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_PFAA, GEN_MARS);
		new WorldgenOresSmall("ore.small.olivine"           , T,   5, 250,   1, MT.Olivine                      , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_PFAA, GEN_MARS);
		new WorldgenOresSmall("ore.small.craponite"         , T,   5, 250,   1, MT.Craponite                    , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_PFAA, GEN_MARS, GEN_ASTEROIDS, GEN_MOON, GEN_PLANETS, GEN_ALFHEIM, GEN_NETHER, GEN_END);
		new WorldgenOresSmall("ore.small.topaz"             , T,   5, 250,   1, MT.Topaz                        , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_PFAA, GEN_MARS);
		new WorldgenOresSmall("ore.small.tanzanite"         , T,   5, 250,   1, MT.Tanzanite                    , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_PFAA, GEN_MARS);
		new WorldgenOresSmall("ore.small.amethyst"          , T,   5, 250,   1, MT.Amethyst                     , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_PFAA, GEN_MARS);
		new WorldgenOresSmall("ore.small.alexandrite"       , T,   5, 250,   1, MT.Alexandrite                  , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_PFAA, GEN_MARS);
		new WorldgenOresSmall("ore.small.opal"              , T,   5, 250,   1, MT.Opal                         , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_PFAA, GEN_MARS);
		new WorldgenOresSmall("ore.small.jasper"            , T,   5, 250,   1, MT.Jasper                       , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_PFAA, GEN_MARS);
		new WorldgenOresSmall("ore.small.aventurinegreen"   , T,   5, 250,   1, MT.AventurineGreen              , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_PFAA, GEN_MARS);
		new WorldgenOresSmall("ore.small.aventurineblue"    , T,   5, 250,   1, MT.AventurineBlue               , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_PFAA, GEN_MARS);
		new WorldgenOresSmall("ore.small.aventurineblack"   , T,   5, 250,   1, MT.AventurineBlack              , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_PFAA, GEN_MARS);
		new WorldgenOresSmall("ore.small.aventurinebrown"   , T,   5, 250,   1, MT.AventurineBrown              , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_PFAA, GEN_MARS);
		new WorldgenOresSmall("ore.small.aventurinered"     , T,   5, 250,   1, MT.AventurineRed                , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_PFAA, GEN_MARS);
		new WorldgenOresSmall("ore.small.aventurineyellow"  , T,   5, 250,   1, MT.AventurineYellow             , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_PFAA, GEN_MARS);
		new WorldgenOresSmall("ore.small.bluetopaz"         , T,   5, 250,   1, MT.BlueTopaz                    , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_PFAA, GEN_MARS);
		new WorldgenOresSmall("ore.small.balasruby"         , T,   5, 250,   1, MT.BalasRuby                    , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_PFAA, GEN_MARS);
		new WorldgenOresSmall("ore.small.spinel"            , T,   5, 250,   1, MT.Spinel                       , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_PFAA, GEN_MARS);
		new WorldgenOresSmall("ore.small.pyrope"            , T,   5, 250,   1, MT.Pyrope                       , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_PFAA, GEN_MARS);
		new WorldgenOresSmall("ore.small.almandine"         , T,   5, 250,   1, MT.Almandine                    , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_PFAA, GEN_MARS);
		new WorldgenOresSmall("ore.small.spessartine"       , T,   5, 250,   1, MT.Spessartine                  , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_PFAA, GEN_MARS);
		new WorldgenOresSmall("ore.small.andradite"         , T,   5, 250,   1, MT.Andradite                    , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_PFAA, GEN_MARS);
		new WorldgenOresSmall("ore.small.grossular"         , T,   5, 250,   1, MT.Grossular                    , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_PFAA, GEN_MARS);
		new WorldgenOresSmall("ore.small.uvarovite"         , T,   5, 250,   1, MT.Uvarovite                    , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_PFAA, GEN_MARS);
		new WorldgenOresSmall("ore.small.jade"              , T,   5, 250,   1, MT.Jade                         , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_PFAA, GEN_MARS);
		new WorldgenOresSmall("ore.small.amazonite"         , T,   5, 250,   1, MT.Amazonite                    , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_PFAA, GEN_MARS);
		new WorldgenOresSmall("ore.small.redonyx"           , T,   5, 250,   1, MT.OnyxRed                      , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_PFAA, GEN_MARS);
		new WorldgenOresSmall("ore.small.blackonyx"         , T,   5, 250,   1, MT.OnyxBlack                    , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_PFAA, GEN_MARS);
		new WorldgenOresSmall("ore.small.fluoritered"       , T,   5, 250,   1, MT.FluoriteRed                  , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_PFAA, GEN_MARS);
		new WorldgenOresSmall("ore.small.fluoritepink"      , T,   5, 250,   1, MT.FluoritePink                 , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_PFAA, GEN_MARS);
		new WorldgenOresSmall("ore.small.fluoriteblue"      , T,   5, 250,   1, MT.FluoriteBlue                 , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_PFAA, GEN_MARS);
		new WorldgenOresSmall("ore.small.fluoritegreen"     , T,   5, 250,   1, MT.FluoriteGreen                , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_PFAA, GEN_MARS);
		new WorldgenOresSmall("ore.small.fluoriteblack"     , T,   5, 250,   1, MT.FluoriteBlack                , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_PFAA, GEN_MARS);
		new WorldgenOresSmall("ore.small.fluoritewhite"     , T,   5, 250,   1, MT.FluoriteWhite                , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_PFAA, GEN_MARS);
		new WorldgenOresSmall("ore.small.fluoriteyellow"    , T,   5, 250,   1, MT.FluoriteYellow               , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_PFAA, GEN_MARS);
		new WorldgenOresSmall("ore.small.fluoriteorange"    , T,   5, 250,   1, MT.FluoriteOrange               , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_PFAA, GEN_MARS);
		new WorldgenOresSmall("ore.small.fluoritemagenta"   , T,   5, 250,   1, MT.FluoriteMagenta              , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_PFAA, GEN_MARS);
		new WorldgenOresSmall("ore.small.amber"             , T,   5, 250,   1, MT.Amber                        , GEN_OVERWORLD, GEN_GT, GEN_ENVM, GEN_TWILIGHT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_PFAA);

		for (int i = 0, j = ConfigsGT.WORLDGEN.get(ConfigCategories.general, "AmountOfCustomSmallOreSlots", 0); i < j; i++) {
		new WorldgenOresSmall("ore.small.custom" + (i<10?"0":"") + i, F, 0, 0, 0, MT.NULL, GEN_ALL);
		}

		new WorldgenOresLarge("ore.large.lignite"           , T, T,  50, 130,    160,   8,  32, MT.Lignite                  , MT.Lignite                , MT.Lignite                        , MT.Coal                           , ORE_OVERWORLD, ORE_TWILIGHT, ORE_EREBUS, ORE_ATUM);
		new WorldgenOresLarge("ore.large.coal"              , T, T,  50,  80,     80,   6,  32, MT.Coal                     , MT.Coal                   , MT.Coal                           , MT.Lignite                        , ORE_OVERWORLD, ORE_TWILIGHT, ORE_EREBUS, ORE_ATUM);
		new WorldgenOresLarge("ore.large.apatite"           , T, T,  40,  60,     60,   3,  16, MT.Apatite                  , MT.Apatite                , MT.Phosphorus                     , MT.PO4                            , ORE_OVERWORLD, ORE_TWILIGHT, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS);
		new WorldgenOresLarge("ore.large.lapis"             , T, T,  20,  50,     40,   5,  16, MT.Lazurite                 , MT.Sodalite               , MT.Lapis                          , MT.Azurite                        , ORE_OVERWORLD, ORE_TWILIGHT, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS);
		new WorldgenOresLarge("ore.large.bauxite"           , T, T,  50,  90,     80,   4,  24, MT.OREMATS.Bauxite          , MT.OREMATS.Bauxite        , MT.OREMATS.Bauxite                , MT.OREMATS.Ilmenite               , ORE_OVERWORLD, ORE_TWILIGHT, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS);
		new WorldgenOresLarge("ore.large.iodinesalt"        , T, T,  50,  60,     30,   3,  24, MT.KIO3                     , MT.NaCl                   , MT.OREMATS.Borax                  , MT.OREMATS.Zeolite                , ORE_OVERWORLD, ORE_TWILIGHT, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS, ORE_MARS);
		new WorldgenOresLarge("ore.large.rocksalt"          , T, T,  50,  60,     30,   3,  24, MT.KCl                      , MT.NaCl                   , MT.OREMATS.Lepidolite             , MT.OREMATS.Spodumene              , ORE_OVERWORLD, ORE_TWILIGHT, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS, ORE_MARS);
		new WorldgenOresLarge("ore.large.soapstone"         , T, T,  10,  40,     10,   3,  16, MT.Soapstone                , MT.Talc                   , MT.OREMATS.Glauconite             , MT.OREMATS.Pentlandite            , ORE_OVERWORLD, ORE_TWILIGHT, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS, ORE_MARS);
		new WorldgenOresLarge("ore.large.asbestos"          , T, T,  10,  40,     30,   3,  16, MT.Soapstone                , MT.Talc                   , MT.OREMATS.Gypsum                 , MT.Asbestos                       , ORE_OVERWORLD, ORE_TWILIGHT, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS, ORE_MARS);
		new WorldgenOresLarge("ore.large.sapphire"          , T, T,  10,  40,     30,   3,  16, MT.BlueSapphire             , MT.OrangeSapphire         , MT.YellowSapphire                 , MT.Ruby                           , ORE_OVERWORLD, ORE_TWILIGHT, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS, ORE_MARS);
		new WorldgenOresLarge("ore.large.sapphire2"         , T, T,  10,  40,     30,   3,  16, MT.GreenSapphire            , MT.Ruby                   , MT.BlueSapphire                   , MT.PurpleSapphire                 , ORE_OVERWORLD, ORE_TWILIGHT, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS, ORE_MARS);
		new WorldgenOresLarge("ore.large.garnet"            , T, T,  10,  40,     60,   3,  16, MT.Almandine                , MT.Pyrope                 , MT.Andradite                      , MT.Uvarovite                      , ORE_OVERWORLD, ORE_TWILIGHT, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS, ORE_MARS);
		new WorldgenOresLarge("ore.large.pitchblende"       , T, T,  10,  40,     40,   3,  16, MT.OREMATS.Pitchblende      , MT.OREMATS.Pitchblende    , MT.OREMATS.Uraninite              , MT.OREMATS.Uraninite              , ORE_OVERWORLD, ORE_TWILIGHT, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS, ORE_MARS, ORE_PLANETS);
		new WorldgenOresLarge("ore.large.monazite"          , T, T,  20,  40,     30,   3,  16, MT.OREMATS.Bastnasite       , MT.OREMATS.Bastnasite     , MT.Monazite                       , MT.Nd                             , ORE_OVERWORLD, ORE_TWILIGHT, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS, ORE_MARS, ORE_PLANETS);
		new WorldgenOresLarge("ore.large.diamond"           , T, T,   5,  20,     40,   2,  16, MT.Graphite                 , MT.Graphite               , MT.Diamond                        , MT.Graphite                       , ORE_OVERWORLD, ORE_TWILIGHT, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS, ORE_MARS, ORE_PLANETS);
		new WorldgenOresLarge("ore.large.galena"            , T, T,  30,  60,     40,   5,  16, MT.OREMATS.Galena           , MT.OREMATS.Galena         , MT.Ag                             , MT.Pb                             , ORE_OVERWORLD, ORE_TWILIGHT, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS, ORE_MARS, ORE_PLANETS);
		new WorldgenOresLarge("ore.large.quartz"            , T, T,  40,  80,     60,   3,  16, MT.MilkyQuartz              , MT.OREMATS.Barite         , MT.CertusQuartz                   , MT.CertusQuartz                   , ORE_OVERWORLD, ORE_TWILIGHT, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS, ORE_MARS, ORE_PLANETS, ORE_MOON, ORE_ASTEROIDS);
		new WorldgenOresLarge("ore.large.olivine"           , T, T,  10,  40,     60,   3,  16, MT.OREMATS.Bentonite        , MT.OREMATS.Magnesite      , MT.Olivine                        , MT.OREMATS.Glauconite             , ORE_OVERWORLD, ORE_TWILIGHT, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS, ORE_MARS, ORE_PLANETS, ORE_MOON, ORE_END);
		new WorldgenOresLarge("ore.large.gold"              , T, T,  20,  30,      5,   3,  16, MT.Pyrite                   , MT.OREMATS.Chalcopyrite   , MT.OREMATS.Arsenopyrite           , MT.Au                             , ORE_OVERWORLD, ORE_TWILIGHT, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS, ORE_MARS, ORE_PLANETS, ORE_END, ORE_ASTEROIDS);
		new WorldgenOresLarge("ore.large.platinum"          , T, T,  40,  50,      5,   3,  16, MT.OREMATS.Cooperite        , MT.Pd                     , MT.Pt                             , MT.Ir                             , ORE_OVERWORLD, ORE_TWILIGHT, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS, ORE_MARS, ORE_PLANETS, ORE_END, ORE_ASTEROIDS);
		new WorldgenOresLarge("ore.large.molybdenum"        , T, T,  20,  50,      5,   3,  16, MT.OREMATS.Wulfenite        , MT.OREMATS.Molybdenite    , MT.Mo                             , MT.OREMATS.Powellite              , ORE_OVERWORLD, ORE_TWILIGHT, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS, ORE_MARS, ORE_PLANETS, ORE_END);
		new WorldgenOresLarge("ore.large.cassiterite"       , T, T,  40,  90,    170,   5,  24, MT.OREMATS.Stannite         , MT.OREMATS.Kesterite      , MT.OREMATS.Huebnerite             , MT.OREMATS.Cassiterite            , ORE_OVERWORLD, ORE_TWILIGHT, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS, ORE_MARS, ORE_PLANETS, ORE_END);
		new WorldgenOresLarge("ore.large.tungstate"         , T, T,  20,  50,     10,   3,  16, MT.OREMATS.Scheelite        , MT.OREMATS.Russellite     , MT.OREMATS.Tungstate              , MT.OREMATS.Pinalite               , ORE_OVERWORLD, ORE_TWILIGHT, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS, ORE_MARS, ORE_END, ORE_ASTEROIDS);
		new WorldgenOresLarge("ore.large.manganese"         , T, T,  20,  30,     20,   3,  16, MT.Grossular                , MT.Spessartine            , MT.MnO2                           , MT.OREMATS.Tantalite              , ORE_OVERWORLD, ORE_TWILIGHT, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS, ORE_MARS, ORE_END);
		new WorldgenOresLarge("ore.large.beryllium"         , T, T,   5,  30,     15,   3,  16, MT.Aquamarine               , MT.Maxixe                 , MT.Emerald                        , MT.Th                             , ORE_OVERWORLD, ORE_TWILIGHT, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS, ORE_MARS, ORE_END);
		new WorldgenOresLarge("ore.large.beryllium2"        , T, T,   5,  30,     15,   3,  16, MT.Bixbite                  , MT.Goshenite              , MT.Heliodor                       , MT.Morganite                      , ORE_OVERWORLD, ORE_TWILIGHT, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS, ORE_MARS, ORE_END);
		new WorldgenOresLarge("ore.large.titanium"          , T, T,  10,  40,     40,   3,  16, MT.TiO2                     , MT.TiO2                   , MT.Zircon                         , MT.OREMATS.Ilmenite               , ORE_OVERWORLD, ORE_TWILIGHT, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS, ORE_MARS, ORE_NETHER, ORE_END);
		new WorldgenOresLarge("ore.large.nickel"            , T, T,  10,  40,     40,   3,  16, MT.OREMATS.Garnierite       , MT.Ni                     , MT.OREMATS.Cobaltite              , MT.OREMATS.Pentlandite            , ORE_OVERWORLD, ORE_TWILIGHT, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS, ORE_MARS, ORE_NETHER, ORE_END);
		new WorldgenOresLarge("ore.large.tetrahedrite"      , T, T,  70, 120,    150,   4,  24, MT.OREMATS.Tetrahedrite     , MT.OREMATS.Tetrahedrite   , MT.Cu                             , MT.OREMATS.Stibnite               , ORE_OVERWORLD, ORE_TWILIGHT, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS, ORE_MARS, ORE_NETHER, ORE_PLANETS);
		new WorldgenOresLarge("ore.large.redstone"          , T, T,  10,  40,     60,   3,  24, MT.Redstone                 , MT.Redstone               , MT.Ruby                           , MT.OREMATS.Cinnabar               , ORE_OVERWORLD, ORE_TWILIGHT, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS, ORE_MARS, ORE_NETHER, ORE_PLANETS);
		new WorldgenOresLarge("ore.large.iron"              , T, T,  10,  40,    120,   4,  24, MT.OREMATS.BrownLimonite    , MT.OREMATS.YellowLimonite , MT.Fe2O3                          , MT.OREMATS.Malachite              , ORE_OVERWORLD, ORE_TWILIGHT, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS, ORE_MARS, ORE_NETHER);
		new WorldgenOresLarge("ore.large.copper"            , T, T,  10,  30,     80,   4,  24, MT.OREMATS.Chalcopyrite     , MT.Fe2O3                  , MT.Pyrite                         , MT.Cu                             , ORE_OVERWORLD, ORE_TWILIGHT, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS, ORE_MARS, ORE_NETHER);
		new WorldgenOresLarge("ore.large.netherquartz"      , T, T,  40,  80,     80,   5,  24, MT.NetherQuartz             , MT.NetherQuartz           , MT.NetherQuartz                   , MT.NetherQuartz                   , ORE_NETHER);
		new WorldgenOresLarge("ore.large.sulfur"            , T, T,   5,  20,    100,   5,  24, MT.S                        , MT.OREMATS.Arsenopyrite   , MT.Pyrite                         , MT.OREMATS.Sphalerite             , ORE_NETHER);
		new WorldgenOresLarge("ore.large.adamantium"        , T, T,  10, 120,      5,   2,  16, MT.OREMATS.BrownLimonite    , MT.OREMATS.YellowLimonite , MT.Fe2O3                          , MT.Adamantine                     , ORE_MARS);
		new WorldgenOresLarge("ore.large.naquadah"          , T, T,  10,  60,     10,   4,  32, MT.Nq                       , MT.Nq                     , MT.Nq                             , MT.Nq_528                         , ORE_MARS, ORE_PLANETS, ORE_ASTEROIDS, ORE_END);
		new WorldgenOresLarge("ore.large.trinium"           , T, T,  10,  90,    100,   1,  12, MT.Ke                       , MT.Ke                     , MT.Ke                             , MT.Ke                             , ORE_MARS, ORE_PLANETS, ORE_ASTEROIDS);
		new WorldgenOresLarge("ore.large.dolamide"          , T, T,   5,  60,     40,   3,  16, MT.Dolamide                 , MT.Dolamide               , MT.OREMATS.DuraniumHexabromide    , MT.OREMATS.DuraniumHexaastatide   , ORE_MARS, ORE_PLANETS, ORE_ASTEROIDS);
		new WorldgenOresLarge("ore.large.moonmars"          , T, T,  10,  90,    240,   1,   8, MT.OREMATS.Magnesite        , MT.MnO2                   , MT.Al2O3                          , MT.TiO2                           , ORE_MARS, ORE_PLANETS, ORE_MOON);
		new WorldgenOresLarge("ore.large.cheese"            , T, T,  10,  90,    100,   3,  16, MT.Cheese                   , MT.Cheese                 , MT.Cheese                         , MT.Se                             , ORE_MOON);
		if (MD.GC.mLoaded) {
		new WorldgenOresLarge("ore.large.desh"              , T, T,  10,  90,    100,   3,  16, MT.Desh                     , MT.Desh                   , MT.Desh                           , MT.Desh                           , ORE_MARS);
		}
		if (MD.BTL.mLoaded) {
		new WorldgenOresLarge("ore.large.syrmorite"         , T, T,  30,  45,    160,   2,  32, MT.Syrmorite                , MT.Syrmorite              , MT.Syrmorite                      , MT.Syrmorite                      , ORE_BETWEENLANDS);
		new WorldgenOresLarge("ore.large.octine"            , T, T,  10,  25,     40,   1,  32, MT.Octine                   , MT.Octine                 , MT.Octine                         , MT.Octine                         , ORE_BETWEENLANDS);
		}

		for (int i = 0, j = ConfigsGT.WORLDGEN.get(ConfigCategories.general, "AmountOfCustomLargeVeinSlots", 0); i < j; i++) {
		new WorldgenOresLarge("ore.large.custom" + (i<10?"0":"") + i, F, T, 0, 0, 0, 0, 0, MT.NULL, MT.NULL, MT.NULL, MT.NULL, ORE_ALL);
		}
	}
}
