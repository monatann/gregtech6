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

package gregtech6.compat;

import static gregapi6.data.CS.*;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import gregapi6.api.Abstract_Mod;
import gregapi6.code.ModData;
import gregapi6.compat.CompatMods;
import gregapi6.data.ANY;
import gregapi6.data.FL;
import gregapi6.data.IL;
import gregapi6.data.MD;
import gregapi6.data.MT;
import gregapi6.data.OP;
import gregapi6.data.RM;
import gregapi6.util.CR;
import gregapi6.util.OM;
import gregapi6.util.ST;
import vazkii.botania.api.BotaniaAPI;

public class Compat_Recipes_Botania extends CompatMods {
	public Compat_Recipes_Botania(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {
		OUT.println("GT_Mod: Doing Botania Recipes.");
		CR.delate(IL.BOTA_Granite.get(1));
		CR.delate(IL.BOTA_Diorite.get(1));
		CR.delate(IL.BOTA_Andesite.get(1));
		CR.delate(IL.BOTA_Granite_Smooth.get(1));
		CR.delate(IL.BOTA_Diorite_Smooth.get(1));
		CR.delate(IL.BOTA_Andesite_Smooth.get(1));
		CR.delate(IL.BOTA_Granite_Bricks.get(1));
		CR.delate(IL.BOTA_Diorite_Bricks.get(1));
		CR.delate(IL.BOTA_Andesite_Bricks.get(1));
		CR.delate(IL.BOTA_Granite_Chiseled.get(1));
		CR.delate(IL.BOTA_Diorite_Chiseled.get(1));
		CR.delate(IL.BOTA_Andesite_Chiseled.get(1));
		
		if (MD.ALF.mLoaded) {
		RM.Hammer       .addRecipe1(T, 16,  16, IL.ALF_Ice.get(1), OM.dust(MT.Ice, U));
		RM.Squeezer     .addRecipe1(T, 16, 128, IL.ALF_Ice.get(1), NF, FL.Ice.make(1000), NI);
		RM.Juicer       .addRecipe1(T, 16, 128, IL.ALF_Ice.get(1), NF, FL.Ice.make(1000), NI);
		}
		
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.BOTA, "mushroom", 1, W), NF, FL.make("mushroomsoup", 500), ZL_IS);
		RM.Juicer.addRecipe1(T, 16, 16, ST.make(MD.BOTA, "mushroom", 1, W), NF, FL.make("mushroomsoup", 500), ZL_IS);
		for (int i = 0; i < 16; i++) {
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.BOTA, "petal", 1, i), NF, DYE_FLUIDS_FLOWER[15-i], ST.make(MD.BOTA, "dye", 1, i));
		RM.Juicer.addRecipe1(T, 16, 16, ST.make(MD.BOTA, "petal", 1, i), NF, DYE_FLUIDS_FLOWER[15-i], ST.make(MD.BOTA, "dye", 1, i));
		RM.ic2_extractor(ST.make(MD.BOTA, "petal", 1, i), ST.make(MD.BOTA, "dye", 1, i));
		}
		
		try {
		BotaniaAPI.registerManaInfusionRecipe(OP.ingot.mat(MT.Manasteel, 1), OP.ingot.dat(ANY.Iron ).toString(), 3000);
		BotaniaAPI.registerManaInfusionRecipe(OP.ingot.mat(MT.Manasteel, 1), OP.ingot.dat(ANY.Steel).toString(), 1500);
		} catch(Throwable e) {e.printStackTrace(ERR);}
		
		for (int i = 0; i < 16; i++) if ((i & 3) != 1) CR.delate(ST.make(MD.BOTA, "stone", 1, i));
		
		RM.Hammer       .addRecipe1(T, 16,   16     , ST.make(MD.BOTA, "quartzSlabManaHalf"         , 1, W), OP.gem.mat(MT.ManaQuartz, 2));
		RM.Hammer       .addRecipe1(T, 16,   16     , ST.make(MD.BOTA, "quartzSlabManaFull"         , 1, W), OP.gem.mat(MT.ManaQuartz, 4));
		RM.Hammer       .addRecipe1(T, 16,   16     , ST.make(MD.BOTA, "quartzTypeMana"             , 1, W), OP.gem.mat(MT.ManaQuartz, 4));
		RM.Hammer       .addRecipe1(T, 16,   16     , ST.make(MD.BOTA, "quartzStairsMana"           , 1, W), OP.gem.mat(MT.ManaQuartz, 6));
		RM.Hammer       .addRecipe1(T, 16,   16     , ST.make(MD.BOTA, "quartzSlabDarkHalf"         , 1, W), OP.gem.mat(MT.SmokeyQuartz, 2));
		RM.Hammer       .addRecipe1(T, 16,   16     , ST.make(MD.BOTA, "quartzSlabDarkFull"         , 1, W), OP.gem.mat(MT.SmokeyQuartz, 4));
		RM.Hammer       .addRecipe1(T, 16,   16     , ST.make(MD.BOTA, "quartzTypeDark"             , 1, W), OP.gem.mat(MT.SmokeyQuartz, 4));
		RM.Hammer       .addRecipe1(T, 16,   16     , ST.make(MD.BOTA, "quartzStairsDark"           , 1, W), OP.gem.mat(MT.SmokeyQuartz, 6));
		RM.Hammer       .addRecipe1(T, 16,   16     , ST.make(MD.BOTA, "quartzSlabBlazeHalf"        , 1, W), OP.gem.mat(MT.BlazeQuartz, 2));
		RM.Hammer       .addRecipe1(T, 16,   16     , ST.make(MD.BOTA, "quartzSlabBlazeFull"        , 1, W), OP.gem.mat(MT.BlazeQuartz, 4));
		RM.Hammer       .addRecipe1(T, 16,   16     , ST.make(MD.BOTA, "quartzTypeBlaze"            , 1, W), OP.gem.mat(MT.BlazeQuartz, 4));
		RM.Hammer       .addRecipe1(T, 16,   16     , ST.make(MD.BOTA, "quartzStairsBlaze"          , 1, W), OP.gem.mat(MT.BlazeQuartz, 6));
		RM.Hammer       .addRecipe1(T, 16,   16     , ST.make(MD.BOTA, "quartzSlabLavenderHalf"     , 1, W), OP.gem.mat(MT.LavenderQuartz, 2));
		RM.Hammer       .addRecipe1(T, 16,   16     , ST.make(MD.BOTA, "quartzSlabLavenderFull"     , 1, W), OP.gem.mat(MT.LavenderQuartz, 4));
		RM.Hammer       .addRecipe1(T, 16,   16     , ST.make(MD.BOTA, "quartzTypeLavender"         , 1, W), OP.gem.mat(MT.LavenderQuartz, 4));
		RM.Hammer       .addRecipe1(T, 16,   16     , ST.make(MD.BOTA, "quartzStairsLavender"       , 1, W), OP.gem.mat(MT.LavenderQuartz, 6));
		RM.Hammer       .addRecipe1(T, 16,   16     , ST.make(MD.BOTA, "quartzSlabRedHalf"          , 1, W), OP.gem.mat(MT.RedQuartz, 2));
		RM.Hammer       .addRecipe1(T, 16,   16     , ST.make(MD.BOTA, "quartzSlabRedFull"          , 1, W), OP.gem.mat(MT.RedQuartz, 4));
		RM.Hammer       .addRecipe1(T, 16,   16     , ST.make(MD.BOTA, "quartzTypeRed"              , 1, W), OP.gem.mat(MT.RedQuartz, 4));
		RM.Hammer       .addRecipe1(T, 16,   16     , ST.make(MD.BOTA, "quartzStairsRed"            , 1, W), OP.gem.mat(MT.RedQuartz, 6));
		RM.Hammer       .addRecipe1(T, 16,   16     , ST.make(MD.BOTA, "quartzSlabElfHalf"          , 1, W), OP.gem.mat(MT.ElvenQuartz, 2));
		RM.Hammer       .addRecipe1(T, 16,   16     , ST.make(MD.BOTA, "quartzSlabElfFull"          , 1, W), OP.gem.mat(MT.ElvenQuartz, 4));
		RM.Hammer       .addRecipe1(T, 16,   16     , ST.make(MD.BOTA, "quartzTypeElf"              , 1, W), OP.gem.mat(MT.ElvenQuartz, 4));
		RM.Hammer       .addRecipe1(T, 16,   16     , ST.make(MD.BOTA, "quartzStairsElf"            , 1, W), OP.gem.mat(MT.ElvenQuartz, 6));
		RM.Hammer       .addRecipe1(T, 16,   16     , ST.make(MD.BOTA, "quartzSlabSunnyHalf"        , 1, W), OP.gem.mat(MT.SunnyQuartz, 2));
		RM.Hammer       .addRecipe1(T, 16,   16     , ST.make(MD.BOTA, "quartzSlabSunnyFull"        , 1, W), OP.gem.mat(MT.SunnyQuartz, 4));
		RM.Hammer       .addRecipe1(T, 16,   16     , ST.make(MD.BOTA, "quartzTypeSunny"            , 1, W), OP.gem.mat(MT.SunnyQuartz, 4));
		RM.Hammer       .addRecipe1(T, 16,   16     , ST.make(MD.BOTA, "quartzStairsSunny"          , 1, W), OP.gem.mat(MT.SunnyQuartz, 6));
		RM.Hammer       .addRecipe1(T, 16,   48,8000, ST.make(MD.BOTA, "livingrock"                 , 1, W), OP.rockGt.mat(MT.Livingrock, 4));
		RM.Hammer       .addRecipe1(T, 16,   48,8000, ST.make(MD.BOTA, "livingrock0SlabFull"        , 1, W), OP.rockGt.mat(MT.Livingrock, 4));
		RM.Hammer       .addRecipe1(T, 16,   48,8000, ST.make(MD.BOTA, "livingrock1SlabFull"        , 1, W), OP.rockGt.mat(MT.Livingrock, 4));
		RM.Hammer       .addRecipe1(T, 16,   48,8000, ST.make(MD.BOTA, "livingrock0Wall"            , 1, W), OP.rockGt.mat(MT.Livingrock, 4));
		RM.Hammer       .addRecipe1(T, 16,   48,8000, ST.make(MD.BOTA, "livingrock0Stairs"          , 1, W), OP.rockGt.mat(MT.Livingrock, 6));
		RM.Hammer       .addRecipe1(T, 16,   48,8000, ST.make(MD.BOTA, "livingrock1Stairs"          , 1, W), OP.rockGt.mat(MT.Livingrock, 6));
		RM.Hammer       .addRecipe1(T, 16,   48,8000, ST.make(MD.BOTA, "livingrock0Slab"            , 1, W), OP.rockGt.mat(MT.Livingrock, 2));
		RM.Hammer       .addRecipe1(T, 16,   48,8000, ST.make(MD.BOTA, "livingrock1Slab"            , 1, W), OP.rockGt.mat(MT.Livingrock, 2));
		
		
		RM.Crusher      .addRecipe1(T, 16,   32     , ST.make(MD.BOTA, "quartzSlabManaHalf"         , 1, W), OP.gem.mat(MT.ManaQuartz, 2));
		RM.Crusher      .addRecipe1(T, 16,   32     , ST.make(MD.BOTA, "quartzSlabManaFull"         , 1, W), OP.gem.mat(MT.ManaQuartz, 4));
		RM.Crusher      .addRecipe1(T, 16,   32     , ST.make(MD.BOTA, "quartzTypeMana"             , 1, W), OP.gem.mat(MT.ManaQuartz, 4));
		RM.Crusher      .addRecipe1(T, 16,   48     , ST.make(MD.BOTA, "quartzStairsMana"           , 1, W), OP.gem.mat(MT.ManaQuartz, 6));
		RM.Crusher      .addRecipe1(T, 16,   32     , ST.make(MD.BOTA, "quartzSlabDarkHalf"         , 1, W), OP.gem.mat(MT.SmokeyQuartz, 2));
		RM.Crusher      .addRecipe1(T, 16,   32     , ST.make(MD.BOTA, "quartzSlabDarkFull"         , 1, W), OP.gem.mat(MT.SmokeyQuartz, 4));
		RM.Crusher      .addRecipe1(T, 16,   32     , ST.make(MD.BOTA, "quartzTypeDark"             , 1, W), OP.gem.mat(MT.SmokeyQuartz, 4));
		RM.Crusher      .addRecipe1(T, 16,   48     , ST.make(MD.BOTA, "quartzStairsDark"           , 1, W), OP.gem.mat(MT.SmokeyQuartz, 6));
		RM.Crusher      .addRecipe1(T, 16,   32     , ST.make(MD.BOTA, "quartzSlabBlazeHalf"        , 1, W), OP.gem.mat(MT.BlazeQuartz, 2));
		RM.Crusher      .addRecipe1(T, 16,   32     , ST.make(MD.BOTA, "quartzSlabBlazeFull"        , 1, W), OP.gem.mat(MT.BlazeQuartz, 4));
		RM.Crusher      .addRecipe1(T, 16,   32     , ST.make(MD.BOTA, "quartzTypeBlaze"            , 1, W), OP.gem.mat(MT.BlazeQuartz, 4));
		RM.Crusher      .addRecipe1(T, 16,   48     , ST.make(MD.BOTA, "quartzStairsBlaze"          , 1, W), OP.gem.mat(MT.BlazeQuartz, 6));
		RM.Crusher      .addRecipe1(T, 16,   32     , ST.make(MD.BOTA, "quartzSlabLavenderHalf"     , 1, W), OP.gem.mat(MT.LavenderQuartz, 2));
		RM.Crusher      .addRecipe1(T, 16,   32     , ST.make(MD.BOTA, "quartzSlabLavenderFull"     , 1, W), OP.gem.mat(MT.LavenderQuartz, 4));
		RM.Crusher      .addRecipe1(T, 16,   32     , ST.make(MD.BOTA, "quartzTypeLavender"         , 1, W), OP.gem.mat(MT.LavenderQuartz, 4));
		RM.Crusher      .addRecipe1(T, 16,   48     , ST.make(MD.BOTA, "quartzStairsLavender"       , 1, W), OP.gem.mat(MT.LavenderQuartz, 6));
		RM.Crusher      .addRecipe1(T, 16,   32     , ST.make(MD.BOTA, "quartzSlabRedHalf"          , 1, W), OP.gem.mat(MT.RedQuartz, 2));
		RM.Crusher      .addRecipe1(T, 16,   32     , ST.make(MD.BOTA, "quartzSlabRedFull"          , 1, W), OP.gem.mat(MT.RedQuartz, 4));
		RM.Crusher      .addRecipe1(T, 16,   32     , ST.make(MD.BOTA, "quartzTypeRed"              , 1, W), OP.gem.mat(MT.RedQuartz, 4));
		RM.Crusher      .addRecipe1(T, 16,   48     , ST.make(MD.BOTA, "quartzStairsRed"            , 1, W), OP.gem.mat(MT.RedQuartz, 6));
		RM.Crusher      .addRecipe1(T, 16,   32     , ST.make(MD.BOTA, "quartzSlabElfHalf"          , 1, W), OP.gem.mat(MT.ElvenQuartz, 2));
		RM.Crusher      .addRecipe1(T, 16,   32     , ST.make(MD.BOTA, "quartzSlabElfFull"          , 1, W), OP.gem.mat(MT.ElvenQuartz, 4));
		RM.Crusher      .addRecipe1(T, 16,   32     , ST.make(MD.BOTA, "quartzTypeElf"              , 1, W), OP.gem.mat(MT.ElvenQuartz, 4));
		RM.Crusher      .addRecipe1(T, 16,   48     , ST.make(MD.BOTA, "quartzStairsElf"            , 1, W), OP.gem.mat(MT.ElvenQuartz, 6));
		RM.Crusher      .addRecipe1(T, 16,   32     , ST.make(MD.BOTA, "quartzSlabSunnyHalf"        , 1, W), OP.gem.mat(MT.SunnyQuartz, 2));
		RM.Crusher      .addRecipe1(T, 16,   32     , ST.make(MD.BOTA, "quartzSlabSunnyFull"        , 1, W), OP.gem.mat(MT.SunnyQuartz, 4));
		RM.Crusher      .addRecipe1(T, 16,   32     , ST.make(MD.BOTA, "quartzTypeSunny"            , 1, W), OP.gem.mat(MT.SunnyQuartz, 4));
		RM.Crusher      .addRecipe1(T, 16,   48     , ST.make(MD.BOTA, "quartzStairsSunny"          , 1, W), OP.gem.mat(MT.SunnyQuartz, 6));
		RM.Crusher      .addRecipe1(T, 16,   48     , ST.make(MD.BOTA, "livingrock"                 , 1, W), OP.rockGt.mat(MT.Livingrock, 4));
		RM.Crusher      .addRecipe1(T, 16,   48     , ST.make(MD.BOTA, "livingrock0SlabFull"        , 1, W), OP.rockGt.mat(MT.Livingrock, 4));
		RM.Crusher      .addRecipe1(T, 16,   48     , ST.make(MD.BOTA, "livingrock1SlabFull"        , 1, W), OP.rockGt.mat(MT.Livingrock, 4));
		RM.Crusher      .addRecipe1(T, 16,   48     , ST.make(MD.BOTA, "livingrock0Wall"            , 1, W), OP.rockGt.mat(MT.Livingrock, 4));
		RM.Crusher      .addRecipe1(T, 16,   48     , ST.make(MD.BOTA, "livingrock0Stairs"          , 1, W), OP.rockGt.mat(MT.Livingrock, 6));
		RM.Crusher      .addRecipe1(T, 16,   48     , ST.make(MD.BOTA, "livingrock1Stairs"          , 1, W), OP.rockGt.mat(MT.Livingrock, 6));
		RM.Crusher      .addRecipe1(T, 16,   48     , ST.make(MD.BOTA, "livingrock0Slab"            , 1, W), OP.rockGt.mat(MT.Livingrock, 2));
		RM.Crusher      .addRecipe1(T, 16,   48     , ST.make(MD.BOTA, "livingrock1Slab"            , 1, W), OP.rockGt.mat(MT.Livingrock, 2));
	}
}
