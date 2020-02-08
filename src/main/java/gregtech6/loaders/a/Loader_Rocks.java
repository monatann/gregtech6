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

package gregtech6.loaders.a;

import static gregapi6.data.CS.*;

import gregapi6.block.behaviors.Drops;
import gregapi6.block.behaviors.Drops_SmallOre;
import gregapi6.block.metatype.BlockStones;
import gregapi6.block.prefixblock.PrefixBlock;
import gregapi6.block.prefixblock.PrefixBlock_;
import gregapi6.code.ItemStackContainer;
import gregapi6.data.CS.BlocksGT;
import gregapi6.data.CS.ItemsGT;
import gregapi6.data.IL;
import gregapi6.data.MD;
import gregapi6.data.MT;
import gregapi6.data.OP;
import gregapi6.data.RM;
import gregapi6.oredict.OreDictMaterial;
import gregapi6.render.BlockTextureDefault;
import gregapi6.util.CR;
import gregapi6.util.ST;
import gregtech6.blocks.BlockStonesGT;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class Loader_Rocks implements Runnable {
	@Override
	public void run() {
		BlockStones tStone;
		
		int n = 0;
		BlocksGT.stones     [n] = BlocksGT.GraniteBlack     = tStone = new BlockStonesGT("gt6.stone.granite.black"           , MT.GraniteBlack                                                                                                                       , 6.00F, 3.00F,  3, T);
		BlocksGT.ores_normal[n] = new PrefixBlock_(MD.GT, "gt6.meta.ore.normal.blackgranite"     , OP.oreBlackgranite        , null                                  , BlockTextureDefault.get(tStone.mIcons[0]), Material.rock, Block.soundTypeStone, TOOL_pickaxe  , 3.00F, 6.00F,  0, tStone.mHarvestLevel  , F,F, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.ores_broken[n] = new PrefixBlock_(MD.GT, "gt6.meta.ore.broken.blackgranite"     , OP.oreBlackgranite        , null                                  , BlockTextureDefault.get(tStone.mIcons[1]), Material.rock, Block.soundTypeStone, TOOL_pickaxe  , 1.50F, 3.00F, -1, tStone.mHarvestLevel-1, T,F, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.ores_small [n] = new PrefixBlock_(MD.GT, "gt6.meta.ore.small.blackgranite"      , OP.oreSmall               , new Drops_SmallOre(tStone.mMaterial)  , BlockTextureDefault.get(tStone.mIcons[0]), Material.rock, Block.soundTypeStone, TOOL_pickaxe  , 3.00F, 6.00F, -1, tStone.mHarvestLevel  , F,F, OreDictMaterial.MATERIAL_ARRAY);
		n++;
		BlocksGT.stones     [n] = BlocksGT.GraniteRed       = tStone = new BlockStonesGT("gt6.stone.granite.red"             , MT.GraniteRed                                                                                                                         , 6.00F, 3.00F,  3, T);
		BlocksGT.ores_normal[n] = new PrefixBlock_(MD.GT, "gt6.meta.ore.normal.redgranite"       , OP.oreRedgranite          , null                                  , BlockTextureDefault.get(tStone.mIcons[0]), Material.rock, Block.soundTypeStone, TOOL_pickaxe  , 3.00F, 6.00F,  0, tStone.mHarvestLevel  , F,F, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.ores_broken[n] = new PrefixBlock_(MD.GT, "gt6.meta.ore.broken.redgranite"       , OP.oreRedgranite          , null                                  , BlockTextureDefault.get(tStone.mIcons[1]), Material.rock, Block.soundTypeStone, TOOL_pickaxe  , 1.50F, 3.00F, -1, tStone.mHarvestLevel-1, T,F, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.ores_small [n] = new PrefixBlock_(MD.GT, "gt6.meta.ore.small.redgranite"        , OP.oreSmall               , new Drops_SmallOre(tStone.mMaterial)  , BlockTextureDefault.get(tStone.mIcons[0]), Material.rock, Block.soundTypeStone, TOOL_pickaxe  , 3.00F, 6.00F, -1, tStone.mHarvestLevel  , F,F, OreDictMaterial.MATERIAL_ARRAY);
		n++;
		BlocksGT.stones     [n] = BlocksGT.Basalt           = tStone = new BlockStonesGT("gt6.stone.basalt"                  , MT.Basalt                                                                                                                             , 3.00F, 2.00F,  2, F);
		BlocksGT.ores_normal[n] = new PrefixBlock_(MD.GT, "gt6.meta.ore.normal.basalt"           , OP.oreBasalt              , null                                  , BlockTextureDefault.get(tStone.mIcons[0]), Material.rock, Block.soundTypeStone, TOOL_pickaxe  , 2.00F, 3.00F,  0, tStone.mHarvestLevel  , F,F, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.ores_broken[n] = new PrefixBlock_(MD.GT, "gt6.meta.ore.broken.basalt"           , OP.oreBasalt              , null                                  , BlockTextureDefault.get(tStone.mIcons[1]), Material.rock, Block.soundTypeStone, TOOL_pickaxe  , 1.00F, 1.50F, -1, tStone.mHarvestLevel-1, T,F, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.ores_small [n] = new PrefixBlock_(MD.GT, "gt6.meta.ore.small.basalt"            , OP.oreSmall               , new Drops_SmallOre(tStone.mMaterial)  , BlockTextureDefault.get(tStone.mIcons[0]), Material.rock, Block.soundTypeStone, TOOL_pickaxe  , 2.00F, 3.00F, -1, tStone.mHarvestLevel  , F,F, OreDictMaterial.MATERIAL_ARRAY);
		n++;
		BlocksGT.stones     [n] = BlocksGT.Marble           = tStone = new BlockStonesGT("gt6.stone.marble"                  , MT.Marble                                                                                                                             , 0.75F, 0.50F,  0, F);
		BlocksGT.ores_normal[n] = new PrefixBlock_(MD.GT, "gt6.meta.ore.normal.marble"           , OP.oreMarble              , null                                  , BlockTextureDefault.get(tStone.mIcons[0]), Material.rock, Block.soundTypeStone, TOOL_pickaxe  , 0.50F, 0.75F,  0, tStone.mHarvestLevel  , F,F, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.ores_broken[n] = new PrefixBlock_(MD.GT, "gt6.meta.ore.broken.marble"           , OP.oreMarble              , null                                  , BlockTextureDefault.get(tStone.mIcons[1]), Material.rock, Block.soundTypeStone, TOOL_pickaxe  , 0.25F, 0.37F, -1, tStone.mHarvestLevel-1, T,F, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.ores_small [n] = new PrefixBlock_(MD.GT, "gt6.meta.ore.small.marble"            , OP.oreSmall               , new Drops_SmallOre(tStone.mMaterial)  , BlockTextureDefault.get(tStone.mIcons[0]), Material.rock, Block.soundTypeStone, TOOL_pickaxe  , 0.50F, 0.75F, -1, tStone.mHarvestLevel  , F,F, OreDictMaterial.MATERIAL_ARRAY);
		n++;
		BlocksGT.stones     [n] = BlocksGT.Limestone        = tStone = new BlockStonesGT("gt6.stone.limestone"               , MT.Limestone                                                                                                                          , 0.75F, 0.50F,  0, F);
		BlocksGT.ores_normal[n] = new PrefixBlock_(MD.GT, "gt6.meta.ore.normal.limestone"        , OP.oreLimestone           , null                                  , BlockTextureDefault.get(tStone.mIcons[0]), Material.rock, Block.soundTypeStone, TOOL_pickaxe  , 0.50F, 0.75F,  0, tStone.mHarvestLevel  , F,F, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.ores_broken[n] = new PrefixBlock_(MD.GT, "gt6.meta.ore.broken.limestone"        , OP.oreLimestone           , null                                  , BlockTextureDefault.get(tStone.mIcons[1]), Material.rock, Block.soundTypeStone, TOOL_pickaxe  , 0.25F, 0.37F, -1, tStone.mHarvestLevel-1, T,F, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.ores_small [n] = new PrefixBlock_(MD.GT, "gt6.meta.ore.small.limestone"         , OP.oreSmall               , new Drops_SmallOre(tStone.mMaterial)  , BlockTextureDefault.get(tStone.mIcons[0]), Material.rock, Block.soundTypeStone, TOOL_pickaxe  , 0.50F, 0.75F, -1, tStone.mHarvestLevel  , F,F, OreDictMaterial.MATERIAL_ARRAY);
		n++;
		BlocksGT.stones     [n] = BlocksGT.Granite          = tStone = new BlockStonesGT("gt6.stone.granite"                 , MT.Granite                                                                                                                            , 2.00F, 1.00F,  1, F);
		BlocksGT.ores_normal[n] = new PrefixBlock_(MD.GT, "gt6.meta.ore.normal.granite"          , OP.oreVanillagranite      , null                                  , BlockTextureDefault.get(tStone.mIcons[0]), Material.rock, Block.soundTypeStone, TOOL_pickaxe  , 1.00F, 1.50F,  0, tStone.mHarvestLevel  , F,F, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.ores_broken[n] = new PrefixBlock_(MD.GT, "gt6.meta.ore.broken.granite"          , OP.oreVanillagranite      , null                                  , BlockTextureDefault.get(tStone.mIcons[1]), Material.rock, Block.soundTypeStone, TOOL_pickaxe  , 0.50F, 0.75F, -1, tStone.mHarvestLevel-1, T,F, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.ores_small [n] = new PrefixBlock_(MD.GT, "gt6.meta.ore.small.granite"           , OP.oreSmall               , new Drops_SmallOre(tStone.mMaterial)  , BlockTextureDefault.get(tStone.mIcons[0]), Material.rock, Block.soundTypeStone, TOOL_pickaxe  , 1.00F, 1.50F, -1, tStone.mHarvestLevel  , F,F, OreDictMaterial.MATERIAL_ARRAY);
		n++;
		BlocksGT.stones     [n] = BlocksGT.Diorite          = tStone = new BlockStonesGT("gt6.stone.diorite"                 , MT.Diorite                                                                                                                            , 0.75F, 0.50F,  0, F);
		BlocksGT.ores_normal[n] = new PrefixBlock_(MD.GT, "gt6.meta.ore.normal.diorite"          , OP.oreDiorite             , null                                  , BlockTextureDefault.get(tStone.mIcons[0]), Material.rock, Block.soundTypeStone, TOOL_pickaxe  , 0.50F, 0.75F,  0, tStone.mHarvestLevel  , F,F, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.ores_broken[n] = new PrefixBlock_(MD.GT, "gt6.meta.ore.broken.diorite"          , OP.oreDiorite             , null                                  , BlockTextureDefault.get(tStone.mIcons[1]), Material.rock, Block.soundTypeStone, TOOL_pickaxe  , 0.25F, 0.37F, -1, tStone.mHarvestLevel-1, T,F, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.ores_small [n] = new PrefixBlock_(MD.GT, "gt6.meta.ore.small.diorite"           , OP.oreSmall               , new Drops_SmallOre(tStone.mMaterial)  , BlockTextureDefault.get(tStone.mIcons[0]), Material.rock, Block.soundTypeStone, TOOL_pickaxe  , 0.50F, 0.75F, -1, tStone.mHarvestLevel  , F,F, OreDictMaterial.MATERIAL_ARRAY);
		n++;
		BlocksGT.stones     [n] = BlocksGT.Andesite         = tStone = new BlockStonesGT("gt6.stone.andesite"                , MT.Andesite                                                                                                                           , 0.75F, 0.50F,  0, F);
		BlocksGT.ores_normal[n] = new PrefixBlock_(MD.GT, "gt6.meta.ore.normal.andesite"         , OP.oreAndesite            , null                                  , BlockTextureDefault.get(tStone.mIcons[0]), Material.rock, Block.soundTypeStone, TOOL_pickaxe  , 0.50F, 0.75F,  0, tStone.mHarvestLevel  , F,F, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.ores_broken[n] = new PrefixBlock_(MD.GT, "gt6.meta.ore.broken.andesite"         , OP.oreAndesite            , null                                  , BlockTextureDefault.get(tStone.mIcons[1]), Material.rock, Block.soundTypeStone, TOOL_pickaxe  , 0.25F, 0.37F, -1, tStone.mHarvestLevel-1, T,F, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.ores_small [n] = new PrefixBlock_(MD.GT, "gt6.meta.ore.small.andesite"          , OP.oreSmall               , new Drops_SmallOre(tStone.mMaterial)  , BlockTextureDefault.get(tStone.mIcons[0]), Material.rock, Block.soundTypeStone, TOOL_pickaxe  , 0.50F, 0.75F, -1, tStone.mHarvestLevel  , F,F, OreDictMaterial.MATERIAL_ARRAY);
		n++;
		BlocksGT.stones     [n] = BlocksGT.Komatiite        = tStone = new BlockStonesGT("gt6.stone.komatiite"               , MT.Komatiite                                                                                                                          , 3.00F, 2.00F,  2, F);
		BlocksGT.ores_normal[n] = new PrefixBlock_(MD.GT, "gt6.meta.ore.normal.komatiite"        , OP.oreKomatiite           , null                                  , BlockTextureDefault.get(tStone.mIcons[0]), Material.rock, Block.soundTypeStone, TOOL_pickaxe  , 2.00F, 3.00F,  0, tStone.mHarvestLevel  , F,F, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.ores_broken[n] = new PrefixBlock_(MD.GT, "gt6.meta.ore.broken.komatiite"        , OP.oreKomatiite           , null                                  , BlockTextureDefault.get(tStone.mIcons[1]), Material.rock, Block.soundTypeStone, TOOL_pickaxe  , 1.00F, 1.50F, -1, tStone.mHarvestLevel-1, T,F, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.ores_small [n] = new PrefixBlock_(MD.GT, "gt6.meta.ore.small.komatiite"         , OP.oreSmall               , new Drops_SmallOre(tStone.mMaterial)  , BlockTextureDefault.get(tStone.mIcons[0]), Material.rock, Block.soundTypeStone, TOOL_pickaxe  , 2.00F, 3.00F, -1, tStone.mHarvestLevel  , F,F, OreDictMaterial.MATERIAL_ARRAY);
		n++;
		BlocksGT.stones     [n] = BlocksGT.SchistGreen      = tStone = new BlockStonesGT("gt6.stone.greenschist"             , MT.Greenschist                                                                                                                        , 0.75F, 0.50F,  0, F);
		BlocksGT.ores_normal[n] = new PrefixBlock_(MD.GT, "gt6.meta.ore.normal.greenschist"      , OP.oreGreenschist         , null                                  , BlockTextureDefault.get(tStone.mIcons[0]), Material.rock, Block.soundTypeStone, TOOL_pickaxe  , 0.50F, 0.75F,  0, tStone.mHarvestLevel  , F,F, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.ores_broken[n] = new PrefixBlock_(MD.GT, "gt6.meta.ore.broken.greenschist"      , OP.oreGreenschist         , null                                  , BlockTextureDefault.get(tStone.mIcons[1]), Material.rock, Block.soundTypeStone, TOOL_pickaxe  , 0.25F, 0.37F, -1, tStone.mHarvestLevel-1, T,F, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.ores_small [n] = new PrefixBlock_(MD.GT, "gt6.meta.ore.small.greenschist"       , OP.oreSmall               , new Drops_SmallOre(tStone.mMaterial)  , BlockTextureDefault.get(tStone.mIcons[0]), Material.rock, Block.soundTypeStone, TOOL_pickaxe  , 0.50F, 0.75F, -1, tStone.mHarvestLevel  , F,F, OreDictMaterial.MATERIAL_ARRAY);
		n++;
		BlocksGT.stones     [n] = BlocksGT.SchistBlue       = tStone = new BlockStonesGT("gt6.stone.blueschist"              , MT.Blueschist                                                                                                                         , 0.75F, 0.50F,  0, F);
		BlocksGT.ores_normal[n] = new PrefixBlock_(MD.GT, "gt6.meta.ore.normal.blueschist"       , OP.oreBlueschist          , null                                  , BlockTextureDefault.get(tStone.mIcons[0]), Material.rock, Block.soundTypeStone, TOOL_pickaxe  , 0.50F, 0.75F,  0, tStone.mHarvestLevel  , F,F, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.ores_broken[n] = new PrefixBlock_(MD.GT, "gt6.meta.ore.broken.blueschist"       , OP.oreBlueschist          , null                                  , BlockTextureDefault.get(tStone.mIcons[1]), Material.rock, Block.soundTypeStone, TOOL_pickaxe  , 0.25F, 0.37F, -1, tStone.mHarvestLevel-1, T,F, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.ores_small [n] = new PrefixBlock_(MD.GT, "gt6.meta.ore.small.blueschist"        , OP.oreSmall               , new Drops_SmallOre(tStone.mMaterial)  , BlockTextureDefault.get(tStone.mIcons[0]), Material.rock, Block.soundTypeStone, TOOL_pickaxe  , 0.50F, 0.75F, -1, tStone.mHarvestLevel  , F,F, OreDictMaterial.MATERIAL_ARRAY);
		n++;
		BlocksGT.stones     [n] = BlocksGT.Kimberlite       = tStone = new BlockStonesGT("gt6.stone.kimberlite"              , MT.Kimberlite                                                                                                                         , 3.00F, 2.00F,  2, F);
		BlocksGT.ores_normal[n] = new PrefixBlock_(MD.GT, "gt6.meta.ore.normal.kimberlite"       , OP.oreKimberlite          , null                                  , BlockTextureDefault.get(tStone.mIcons[0]), Material.rock, Block.soundTypeStone, TOOL_pickaxe  , 2.00F, 3.00F,  0, tStone.mHarvestLevel  , F,F, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.ores_broken[n] = new PrefixBlock_(MD.GT, "gt6.meta.ore.broken.kimberlite"       , OP.oreKimberlite          , null                                  , BlockTextureDefault.get(tStone.mIcons[1]), Material.rock, Block.soundTypeStone, TOOL_pickaxe  , 1.00F, 1.50F, -1, tStone.mHarvestLevel-1, T,F, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.ores_small [n] = new PrefixBlock_(MD.GT, "gt6.meta.ore.small.kimberlite"        , OP.oreSmall               , new Drops_SmallOre(tStone.mMaterial)  , BlockTextureDefault.get(tStone.mIcons[0]), Material.rock, Block.soundTypeStone, TOOL_pickaxe  , 2.00F, 3.00F, -1, tStone.mHarvestLevel  , F,F, OreDictMaterial.MATERIAL_ARRAY);
		n++;
		BlocksGT.stones     [n] = BlocksGT.Quartzite        = tStone = new BlockStonesGT("gt6.stone.quartzite"               , MT.Quartzite                                                                                                                          , 0.75F, 0.50F,  0, F);
		BlocksGT.ores_normal[n] = new PrefixBlock_(MD.GT, "gt6.meta.ore.normal.quartzite"        , OP.oreQuartzite           , null                                  , BlockTextureDefault.get(tStone.mIcons[0]), Material.rock, Block.soundTypeStone, TOOL_pickaxe  , 0.50F, 0.75F,  0, tStone.mHarvestLevel  , F,F, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.ores_broken[n] = new PrefixBlock_(MD.GT, "gt6.meta.ore.broken.quartzite"        , OP.oreQuartzite           , null                                  , BlockTextureDefault.get(tStone.mIcons[1]), Material.rock, Block.soundTypeStone, TOOL_pickaxe  , 0.25F, 0.37F, -1, tStone.mHarvestLevel-1, T,F, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.ores_small [n] = new PrefixBlock_(MD.GT, "gt6.meta.ore.small.quartzite"         , OP.oreSmall               , new Drops_SmallOre(tStone.mMaterial)  , BlockTextureDefault.get(tStone.mIcons[0]), Material.rock, Block.soundTypeStone, TOOL_pickaxe  , 0.50F, 0.75F, -1, tStone.mHarvestLevel  , F,F, OreDictMaterial.MATERIAL_ARRAY);
		n++;
		BlocksGT.stones     [n] = BlocksGT.PrismarineLight  = tStone = new BlockStonesGT("gt6.stone.prismarine.light"        , MT.PrismarineLight                                                                                                                    , 0.75F, 0.50F,  0, F);
		BlocksGT.ores_normal[n] = new PrefixBlock_(MD.GT, "gt6.meta.ore.normal.prismarine.light" , OP.oreLightprismarine     , null                                  , BlockTextureDefault.get(tStone.mIcons[0]), Material.rock, Block.soundTypeStone, TOOL_pickaxe  , 0.50F, 0.75F,  0, tStone.mHarvestLevel  , F,F, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.ores_broken[n] = new PrefixBlock_(MD.GT, "gt6.meta.ore.broken.prismarine.light" , OP.oreLightprismarine     , null                                  , BlockTextureDefault.get(tStone.mIcons[1]), Material.rock, Block.soundTypeStone, TOOL_pickaxe  , 0.25F, 0.37F, -1, tStone.mHarvestLevel-1, T,F, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.ores_small [n] = new PrefixBlock_(MD.GT, "gt6.meta.ore.small.prismarine.light"  , OP.oreSmall               , new Drops_SmallOre(tStone.mMaterial)  , BlockTextureDefault.get(tStone.mIcons[0]), Material.rock, Block.soundTypeStone, TOOL_pickaxe  , 0.50F, 0.75F, -1, tStone.mHarvestLevel  , F,F, OreDictMaterial.MATERIAL_ARRAY);
		n++;
		BlocksGT.stones     [n] = BlocksGT.PrismarineDark   = tStone = new BlockStonesGT("gt6.stone.prismarine.dark"         , MT.PrismarineDark                                                                                                                     , 0.75F, 0.50F,  1, F);
		BlocksGT.ores_normal[n] = new PrefixBlock_(MD.GT, "gt6.meta.ore.normal.prismarine.dark"  , OP.oreDarkprismarine      , null                                  , BlockTextureDefault.get(tStone.mIcons[0]), Material.rock, Block.soundTypeStone, TOOL_pickaxe  , 0.50F, 0.75F,  0, tStone.mHarvestLevel  , F,F, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.ores_broken[n] = new PrefixBlock_(MD.GT, "gt6.meta.ore.broken.prismarine.dark"  , OP.oreDarkprismarine      , null                                  , BlockTextureDefault.get(tStone.mIcons[1]), Material.rock, Block.soundTypeStone, TOOL_pickaxe  , 0.25F, 0.37F, -1, tStone.mHarvestLevel-1, T,F, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.ores_small [n] = new PrefixBlock_(MD.GT, "gt6.meta.ore.small.prismarine.dark"   , OP.oreSmall               , new Drops_SmallOre(tStone.mMaterial)  , BlockTextureDefault.get(tStone.mIcons[0]), Material.rock, Block.soundTypeStone, TOOL_pickaxe  , 0.50F, 0.75F, -1, tStone.mHarvestLevel  , F,F, OreDictMaterial.MATERIAL_ARRAY);
		
		for (int i = 0; i < BlocksGT.stones.length; i++) {
			VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.stones[i]);
			((PrefixBlock)BlocksGT.ores_normal[i]).mDrops = new Drops((PrefixBlock)BlocksGT.ores_broken[i], (PrefixBlock)BlocksGT.ores_normal[i]);
			BlocksGT.stoneToNormalOres.put(new ItemStackContainer(BlocksGT.stones[i], 1, 0), BlocksGT.ores_normal[i]);
			BlocksGT.stoneToBrokenOres.put(new ItemStackContainer(BlocksGT.stones[i], 1, 0), BlocksGT.ores_broken[i]);
			BlocksGT.stoneToSmallOres .put(new ItemStackContainer(BlocksGT.stones[i], 1, 0), BlocksGT.ores_small [i]);
			BlocksGT.stoneOverridable.add(BlocksGT.ores_normal[i]);
			BlocksGT.stoneOverridable.add(BlocksGT.ores_broken[i]);
			BlocksGT.stoneOverridable.add(BlocksGT.ores_small [i]);
		}
		
		if (MD.CHSL.mLoaded) {
		CR.shapeless(IL.CHSL_Granite            .get(1), CR.DEF_NCC, new Object[] {ST.make(BlocksGT.stones[5], 1, 0)});
		CR.shapeless(IL.CHSL_Diorite            .get(1), CR.DEF_NCC, new Object[] {ST.make(BlocksGT.stones[6], 1, 0)});
		CR.shapeless(IL.CHSL_Andesite           .get(1), CR.DEF_NCC, new Object[] {ST.make(BlocksGT.stones[7], 1, 0)});
		CR.shapeless(IL.CHSL_Granite_Smooth     .get(1), CR.DEF_NCC, new Object[] {ST.make(BlocksGT.stones[5], 1, 7)});
		CR.shapeless(IL.CHSL_Diorite_Smooth     .get(1), CR.DEF_NCC, new Object[] {ST.make(BlocksGT.stones[6], 1, 7)});
		CR.shapeless(IL.CHSL_Andesite_Smooth    .get(1), CR.DEF_NCC, new Object[] {ST.make(BlocksGT.stones[7], 1, 7)});
		CR.shapeless(ST.make(BlocksGT.stones[5], 1, 0), CR.DEF_NCC, new Object[] {IL.CHSL_Granite           .get(1)});
		CR.shapeless(ST.make(BlocksGT.stones[6], 1, 0), CR.DEF_NCC, new Object[] {IL.CHSL_Diorite           .get(1)});
		CR.shapeless(ST.make(BlocksGT.stones[7], 1, 0), CR.DEF_NCC, new Object[] {IL.CHSL_Andesite          .get(1)});
		CR.shapeless(ST.make(BlocksGT.stones[5], 1, 7), CR.DEF_NCC, new Object[] {IL.CHSL_Granite_Smooth    .get(1)});
		CR.shapeless(ST.make(BlocksGT.stones[6], 1, 7), CR.DEF_NCC, new Object[] {IL.CHSL_Diorite_Smooth    .get(1)});
		CR.shapeless(ST.make(BlocksGT.stones[7], 1, 7), CR.DEF_NCC, new Object[] {IL.CHSL_Andesite_Smooth   .get(1)});
		}
		
		RM.generify(IL.CHSL_Granite             .get(1), ST.make(BlocksGT.stones[ 5], 1, 0));
		RM.generify(IL.CHSL_Diorite             .get(1), ST.make(BlocksGT.stones[ 6], 1, 0));
		RM.generify(IL.CHSL_Andesite            .get(1), ST.make(BlocksGT.stones[ 7], 1, 0));
		RM.generify(IL.CHSL_Granite_Smooth      .get(1), ST.make(BlocksGT.stones[ 5], 1, 7));
		RM.generify(IL.CHSL_Diorite_Smooth      .get(1), ST.make(BlocksGT.stones[ 6], 1, 7));
		RM.generify(IL.CHSL_Andesite_Smooth     .get(1), ST.make(BlocksGT.stones[ 7], 1, 7));
		RM.generify(IL.EtFu_Granite             .get(1), ST.make(BlocksGT.stones[ 5], 1, 0));
		RM.generify(IL.EtFu_Diorite             .get(1), ST.make(BlocksGT.stones[ 6], 1, 0));
		RM.generify(IL.EtFu_Andesite            .get(1), ST.make(BlocksGT.stones[ 7], 1, 0));
		RM.generify(IL.EtFu_Granite_Smooth      .get(1), ST.make(BlocksGT.stones[ 5], 1, 7));
		RM.generify(IL.EtFu_Diorite_Smooth      .get(1), ST.make(BlocksGT.stones[ 6], 1, 7));
		RM.generify(IL.EtFu_Andesite_Smooth     .get(1), ST.make(BlocksGT.stones[ 7], 1, 7));
		RM.generify(IL.GaSu_Granite             .get(1), ST.make(BlocksGT.stones[ 5], 1, 0));
		RM.generify(IL.GaSu_Diorite             .get(1), ST.make(BlocksGT.stones[ 6], 1, 0));
		RM.generify(IL.GaSu_Andesite            .get(1), ST.make(BlocksGT.stones[ 7], 1, 0));
		RM.generify(IL.GaSu_Granite_Smooth      .get(1), ST.make(BlocksGT.stones[ 5], 1, 7));
		RM.generify(IL.GaSu_Diorite_Smooth      .get(1), ST.make(BlocksGT.stones[ 6], 1, 7));
		RM.generify(IL.GaSu_Andesite_Smooth     .get(1), ST.make(BlocksGT.stones[ 7], 1, 7));
		RM.generify(IL.BOTA_Granite             .get(1), ST.make(BlocksGT.stones[ 5], 1, 0));
		RM.generify(IL.BOTA_Diorite             .get(1), ST.make(BlocksGT.stones[ 6], 1, 0));
		RM.generify(IL.BOTA_Andesite            .get(1), ST.make(BlocksGT.stones[ 7], 1, 0));
		RM.generify(IL.BOTA_Granite_Smooth      .get(1), ST.make(BlocksGT.stones[ 5], 1, 7));
		RM.generify(IL.BOTA_Diorite_Smooth      .get(1), ST.make(BlocksGT.stones[ 6], 1, 7));
		RM.generify(IL.BOTA_Andesite_Smooth     .get(1), ST.make(BlocksGT.stones[ 7], 1, 7));
		RM.generify(IL.BOTA_Granite_Bricks      .get(1), ST.make(BlocksGT.stones[ 5], 1, 3));
		RM.generify(IL.BOTA_Diorite_Bricks      .get(1), ST.make(BlocksGT.stones[ 6], 1, 3));
		RM.generify(IL.BOTA_Andesite_Bricks     .get(1), ST.make(BlocksGT.stones[ 7], 1, 3));
		RM.generify(IL.BOTA_Granite_Chiseled    .get(1), ST.make(BlocksGT.stones[ 5], 1, 6));
		RM.generify(IL.BOTA_Diorite_Chiseled    .get(1), ST.make(BlocksGT.stones[ 6], 1, 6));
		RM.generify(IL.BOTA_Andesite_Chiseled   .get(1), ST.make(BlocksGT.stones[ 7], 1, 6));
		RM.generify(IL.BOTA_Prismarine          .get(1), ST.make(BlocksGT.stones[13], 1, 0));
		RM.generify(IL.BOTA_Prismarine_Bricks   .get(1), ST.make(BlocksGT.stones[13], 1, 3));
		RM.generify(IL.BOTA_Prismarine_Dark     .get(1), ST.make(BlocksGT.stones[14], 1,11));
		
		ItemsGT.addNEIRedirects(ST.make(BlocksGT.stones[ 5], 1, 0), IL.CHSL_Granite.get(1), IL.GaSu_Granite.get(1), IL.EtFu_Granite.get(1), IL.BOTA_Granite.get(1));
		ItemsGT.addNEIRedirects(ST.make(BlocksGT.stones[ 6], 1, 0), IL.CHSL_Diorite.get(1), IL.GaSu_Diorite.get(1), IL.EtFu_Diorite.get(1), IL.BOTA_Diorite.get(1));
		ItemsGT.addNEIRedirects(ST.make(BlocksGT.stones[ 7], 1, 0), IL.CHSL_Andesite.get(1), IL.GaSu_Andesite.get(1), IL.EtFu_Andesite.get(1), IL.BOTA_Andesite.get(1));
		ItemsGT.addNEIRedirects(ST.make(BlocksGT.stones[ 5], 1, 7), IL.CHSL_Granite_Smooth.get(1), IL.GaSu_Granite_Smooth.get(1), IL.EtFu_Granite_Smooth.get(1), IL.BOTA_Granite_Smooth.get(1));
		ItemsGT.addNEIRedirects(ST.make(BlocksGT.stones[ 6], 1, 7), IL.CHSL_Diorite_Smooth.get(1), IL.GaSu_Diorite_Smooth.get(1), IL.EtFu_Diorite_Smooth.get(1), IL.BOTA_Diorite_Smooth.get(1));
		ItemsGT.addNEIRedirects(ST.make(BlocksGT.stones[ 7], 1, 7), IL.CHSL_Andesite_Smooth.get(1), IL.GaSu_Andesite_Smooth.get(1), IL.EtFu_Andesite_Smooth.get(1), IL.BOTA_Andesite_Smooth.get(1));
		ItemsGT.addNEIRedirects(ST.make(BlocksGT.stones[ 5], 1, 3), IL.BOTA_Granite_Bricks.get(1));
		ItemsGT.addNEIRedirects(ST.make(BlocksGT.stones[ 6], 1, 3), IL.BOTA_Diorite_Bricks.get(1));
		ItemsGT.addNEIRedirects(ST.make(BlocksGT.stones[ 7], 1, 3), IL.BOTA_Andesite_Bricks.get(1));
		ItemsGT.addNEIRedirects(ST.make(BlocksGT.stones[ 5], 1, 6), IL.BOTA_Granite_Chiseled.get(1));
		ItemsGT.addNEIRedirects(ST.make(BlocksGT.stones[ 6], 1, 6), IL.BOTA_Diorite_Chiseled.get(1));
		ItemsGT.addNEIRedirects(ST.make(BlocksGT.stones[ 7], 1, 6), IL.BOTA_Andesite_Chiseled.get(1));
		ItemsGT.addNEIRedirects(ST.make(BlocksGT.stones[13], 1, 0), IL.BOTA_Prismarine.get(1));
		ItemsGT.addNEIRedirects(ST.make(BlocksGT.stones[13], 1, 3), IL.BOTA_Prismarine_Bricks.get(1));
		ItemsGT.addNEIRedirects(ST.make(BlocksGT.stones[14], 1,11), IL.BOTA_Prismarine_Dark.get(1));
		
		((BlockStones)BlocksGT.stones[ 5]).mEqualBlocks[ 0].add(IL.CHSL_Granite           .get(1));
		((BlockStones)BlocksGT.stones[ 6]).mEqualBlocks[ 0].add(IL.CHSL_Diorite           .get(1));
		((BlockStones)BlocksGT.stones[ 7]).mEqualBlocks[ 0].add(IL.CHSL_Andesite          .get(1));
		((BlockStones)BlocksGT.stones[ 5]).mEqualBlocks[ 0].add(IL.EtFu_Granite           .get(1));
		((BlockStones)BlocksGT.stones[ 6]).mEqualBlocks[ 0].add(IL.EtFu_Diorite           .get(1));
		((BlockStones)BlocksGT.stones[ 7]).mEqualBlocks[ 0].add(IL.EtFu_Andesite          .get(1));
		((BlockStones)BlocksGT.stones[ 5]).mEqualBlocks[ 0].add(IL.GaSu_Granite           .get(1));
		((BlockStones)BlocksGT.stones[ 6]).mEqualBlocks[ 0].add(IL.GaSu_Diorite           .get(1));
		((BlockStones)BlocksGT.stones[ 7]).mEqualBlocks[ 0].add(IL.GaSu_Andesite          .get(1));
		((BlockStones)BlocksGT.stones[ 5]).mEqualBlocks[ 0].add(IL.BOTA_Granite           .get(1));
		((BlockStones)BlocksGT.stones[ 6]).mEqualBlocks[ 0].add(IL.BOTA_Diorite           .get(1));
		((BlockStones)BlocksGT.stones[ 7]).mEqualBlocks[ 0].add(IL.BOTA_Andesite          .get(1));
		((BlockStones)BlocksGT.stones[ 5]).mEqualBlocks[ 7].add(IL.CHSL_Granite_Smooth    .get(1));
		((BlockStones)BlocksGT.stones[ 6]).mEqualBlocks[ 7].add(IL.CHSL_Diorite_Smooth    .get(1));
		((BlockStones)BlocksGT.stones[ 7]).mEqualBlocks[ 7].add(IL.CHSL_Andesite_Smooth   .get(1));
		((BlockStones)BlocksGT.stones[ 5]).mEqualBlocks[ 7].add(IL.EtFu_Granite_Smooth    .get(1));
		((BlockStones)BlocksGT.stones[ 6]).mEqualBlocks[ 7].add(IL.EtFu_Diorite_Smooth    .get(1));
		((BlockStones)BlocksGT.stones[ 7]).mEqualBlocks[ 7].add(IL.EtFu_Andesite_Smooth   .get(1));
		((BlockStones)BlocksGT.stones[ 5]).mEqualBlocks[ 7].add(IL.GaSu_Granite_Smooth    .get(1));
		((BlockStones)BlocksGT.stones[ 6]).mEqualBlocks[ 7].add(IL.GaSu_Diorite_Smooth    .get(1));
		((BlockStones)BlocksGT.stones[ 7]).mEqualBlocks[ 7].add(IL.GaSu_Andesite_Smooth   .get(1));
		((BlockStones)BlocksGT.stones[ 5]).mEqualBlocks[ 7].add(IL.BOTA_Granite_Smooth    .get(1));
		((BlockStones)BlocksGT.stones[ 6]).mEqualBlocks[ 7].add(IL.BOTA_Diorite_Smooth    .get(1));
		((BlockStones)BlocksGT.stones[ 7]).mEqualBlocks[ 7].add(IL.BOTA_Andesite_Smooth   .get(1));
		((BlockStones)BlocksGT.stones[ 5]).mEqualBlocks[ 3].add(IL.BOTA_Granite_Bricks    .get(1));
		((BlockStones)BlocksGT.stones[ 6]).mEqualBlocks[ 3].add(IL.BOTA_Diorite_Bricks    .get(1));
		((BlockStones)BlocksGT.stones[ 7]).mEqualBlocks[ 3].add(IL.BOTA_Andesite_Bricks   .get(1));
		((BlockStones)BlocksGT.stones[ 5]).mEqualBlocks[ 6].add(IL.BOTA_Granite_Chiseled  .get(1));
		((BlockStones)BlocksGT.stones[ 6]).mEqualBlocks[ 6].add(IL.BOTA_Diorite_Chiseled  .get(1));
		((BlockStones)BlocksGT.stones[ 7]).mEqualBlocks[ 6].add(IL.BOTA_Andesite_Chiseled .get(1));
		((BlockStones)BlocksGT.stones[13]).mEqualBlocks[ 0].add(IL.BOTA_Prismarine        .get(1));
		((BlockStones)BlocksGT.stones[13]).mEqualBlocks[ 3].add(IL.BOTA_Prismarine_Bricks .get(1));
		((BlockStones)BlocksGT.stones[14]).mEqualBlocks[11].add(IL.BOTA_Prismarine_Dark   .get(1));
	}
}
