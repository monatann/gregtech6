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
import gregapi6.data.CS.ItemsGT;
import gregapi6.data.FL;
import gregapi6.data.IL;
import gregapi6.data.MD;
import gregapi6.data.MT;
import gregapi6.data.OP;
import gregapi6.data.RM;
import gregapi6.util.CR;
import gregapi6.util.OM;
import gregapi6.util.ST;
import net.minecraft.init.Items;

public class Compat_Recipes_Mariculture extends CompatMods {
	public Compat_Recipes_Mariculture(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {OUT.println("GT_Mod: Doing Mariculture Recipes.");
		CR.delate(IL.MaCu_Polished_Logs.get(1));
		CR.delate(IL.MaCu_Polished_Planks.get(1));
		CR.remove(IL.MaCu_Polished_Planks.get(1), null, null, IL.MaCu_Polished_Planks.get(1));
		
		RM.generify(ST.make(ItemsGT.BUMBLEBEES, 1, W), IL.MaCu_Bait_Bee.get(1));
		CR.shapeless(IL.MaCu_Bait_Bee.get(1), CR.DEF_NAC_NCC, new Object[] {ST.make(ItemsGT.BUMBLEBEES, 1, W)});
		
		CR.shaped(ST.make(MD.MaCu, "crafting", 1,19), CR.DEF_NAC_REV_NCC, "h R", " R ", "R f", 'R', OP.stickLong.dat(MT.Ti));
		
		RM.Bath             .addRecipe1(T,  0,   16, ST.make(Items.string           , 1, W), MT.Au.liquid(4*U, T)   , NF, ST.make(MD.MaCu, "crafting", 1, 0));
		RM.Bath             .addRecipe1(T,  0,   16, ST.make(MD.MaCu, "crafting"    , 1,19), FL.Oil_Fish.make(6500) , NF, ST.make(MD.MaCu, "crafting", 1, 2));
		RM.Bath             .addRecipe1(T,  0,   16, ST.make(Items.netherbrick      , 1, W), FL.Lava.make(100)      , NF, ST.make(MD.MaCu, "crafting", 1,14));
		RM.Bath             .addRecipe1(T,  0,   16, ST.make(Items.brick            , 1, W), FL.Lava.make(250)      , NF, ST.make(MD.MaCu, "crafting", 1,14));
		
		RM.Mixer            .addRecipe1(T, 16,   48, OM.dust(MT.Sugar               ), FL.Milk   .make(500), FL.make("custard", 500), ZL_IS);
		RM.Mixer            .addRecipe1(T, 16,   16, OM.dust(MT.Sugar           , U4), FL.Milk   .make(125), FL.make("custard", 125), ZL_IS);
		RM.Mixer            .addRecipe1(T, 16,   16, OP.gemChipped.mat(MT.Sugar ,  1), FL.Milk   .make(125), FL.make("custard", 125), ZL_IS);
		RM.Mixer            .addRecipe1(T, 16,   48, OM.dust(MT.Sugar               ), FL.MilkGrC.make(500), FL.make("custard", 500), ZL_IS);
		RM.Mixer            .addRecipe1(T, 16,   16, OM.dust(MT.Sugar           , U4), FL.MilkGrC.make(125), FL.make("custard", 125), ZL_IS);
		RM.Mixer            .addRecipe1(T, 16,   16, OP.gemChipped.mat(MT.Sugar ,  1), FL.MilkGrC.make(125), FL.make("custard", 125), ZL_IS);
		RM.Mixer            .addRecipe1(T, 16,   48, OM.dust(MT.Sugar               ), FL.MilkSoy.make(500), FL.make("custard", 500), ZL_IS);
		RM.Mixer            .addRecipe1(T, 16,   16, OM.dust(MT.Sugar           , U4), FL.MilkSoy.make(125), FL.make("custard", 125), ZL_IS);
		RM.Mixer            .addRecipe1(T, 16,   16, OP.gemChipped.mat(MT.Sugar ,  1), FL.MilkSoy.make(125), FL.make("custard", 125), ZL_IS);
		
		RM.Squeezer         .addRecipe1(T, 16,   16, ST.make(MD.MaCu, "plant_static", 1, 0), IL.MaCu_Dye_Green.get(1));
		RM.Squeezer         .addRecipe1(T, 16,   16, ST.make(MD.MaCu, "plant_static", 1, 1), IL.MaCu_Dye_Green.get(1));
		RM.Squeezer         .addRecipe1(T, 16,   16, ST.make(MD.MaCu, "plant_static", 1, 2), ST.make(Items.dye, 2, DYE_INDEX_Pink));
		RM.Squeezer         .addRecipe1(T, 16,   16, ST.make(MD.MaCu, "plant_static", 1, 3), IL.MaCu_Dye_Red.get(2));
		RM.Squeezer         .addRecipe1(T, 16,   16, ST.make(MD.MaCu, "plant_static", 1, 4), ST.make(Items.dye, 2, DYE_INDEX_LightBlue));
		RM.Squeezer         .addRecipe1(T, 16,   16, ST.make(MD.MaCu, "plant_static", 1, 5), ST.make(Items.dye, 2, DYE_INDEX_Magenta));
		RM.Squeezer         .addRecipe1(T, 16,   16, ST.make(MD.MaCu, "plant_static", 1, 6), IL.MaCu_Dye_Brown.get(2));
		RM.Squeezer         .addRecipe1(T, 16,   16, ST.make(MD.MaCu, "plant_static", 1, 7), IL.MaCu_Dye_Yellow.get(2));
		RM.Squeezer         .addRecipe1(T, 16,   16, ST.make(MD.MaCu, "plant_static", 1, 8), ST.make(Items.dye, 2, DYE_INDEX_Orange));
		RM.Squeezer         .addRecipe1(T, 16,   16, ST.make(MD.MaCu, "plant_static", 1, 9), ST.make(Items.dye, 2, DYE_INDEX_Purple));
		RM.Squeezer         .addRecipe1(T, 16,   16, ST.make(MD.MaCu, "plant_static", 1,10), ST.make(Items.dye, 2, DYE_INDEX_Gray));
		RM.Squeezer         .addRecipe1(T, 16,   16, ST.make(MD.MaCu, "plant_static", 1,11), ST.make(Items.dye, 2, DYE_INDEX_LightGray));
		RM.Squeezer         .addRecipe1(T, 16,   16, ST.make(MD.MaCu, "plant_static", 1,12), IL.MaCu_Dye_White.get(2));
		
		RM.Squeezer         .addRecipe1(T, 16,   16, ST.make(MD.MaCu, "plant_growable", 1, 0), IL.MaCu_Dye_Green.get(1));
		RM.Squeezer         .addRecipe1(T, 16,   16, ST.make(MD.MaCu, "plant_growable", 1, 1), IL.MaCu_Dye_Green.get(1));
		RM.Squeezer         .addRecipe1(T, 16,   16, ST.make(MD.MaCu, "plant_growable", 1, 2), ST.make(Items.dye, 2, DYE_INDEX_Pink));
		RM.Squeezer         .addRecipe1(T, 16,   16, ST.make(MD.MaCu, "plant_growable", 1, 3), IL.MaCu_Dye_Red.get(2));
		RM.Squeezer         .addRecipe1(T, 16,   16, ST.make(MD.MaCu, "plant_growable", 1, 4), ST.make(Items.dye, 2, DYE_INDEX_LightBlue));
		RM.Squeezer         .addRecipe1(T, 16,   16, ST.make(MD.MaCu, "plant_growable", 1, 5), ST.make(Items.dye, 2, DYE_INDEX_Magenta));
		RM.Squeezer         .addRecipe1(T, 16,   16, ST.make(MD.MaCu, "plant_growable", 1, 6), IL.MaCu_Dye_Brown.get(2));
		RM.Squeezer         .addRecipe1(T, 16,   16, ST.make(MD.MaCu, "plant_growable", 1, 7), IL.MaCu_Dye_Yellow.get(2));
		RM.Squeezer         .addRecipe1(T, 16,   16, ST.make(MD.MaCu, "plant_growable", 1, 8), ST.make(Items.dye, 2, DYE_INDEX_Orange));
		RM.Squeezer         .addRecipe1(T, 16,   16, ST.make(MD.MaCu, "plant_growable", 1, 9), ST.make(Items.dye, 2, DYE_INDEX_Purple));
		RM.Squeezer         .addRecipe1(T, 16,   16, ST.make(MD.MaCu, "plant_growable", 1,10), ST.make(Items.dye, 2, DYE_INDEX_Gray));
		RM.Squeezer         .addRecipe1(T, 16,   16, ST.make(MD.MaCu, "plant_growable", 1,11), ST.make(Items.dye, 2, DYE_INDEX_LightGray));
		RM.Squeezer         .addRecipe1(T, 16,   16, ST.make(MD.MaCu, "plant_growable", 1,12), IL.MaCu_Dye_White.get(2));
		
		if (ENABLE_ADDING_IC2_EXTRACTOR_RECIPES) {
		RM.ic2_extractor(ST.make(MD.MaCu, "plant_static", 1, 0), IL.MaCu_Dye_Green.get(1));
		RM.ic2_extractor(ST.make(MD.MaCu, "plant_static", 1, 1), IL.MaCu_Dye_Green.get(1));
		RM.ic2_extractor(ST.make(MD.MaCu, "plant_static", 1, 2), ST.make(Items.dye, 2, DYE_INDEX_Pink));
		RM.ic2_extractor(ST.make(MD.MaCu, "plant_static", 1, 3), IL.MaCu_Dye_Red.get(2));
		RM.ic2_extractor(ST.make(MD.MaCu, "plant_static", 1, 4), ST.make(Items.dye, 2, DYE_INDEX_LightBlue));
		RM.ic2_extractor(ST.make(MD.MaCu, "plant_static", 1, 5), ST.make(Items.dye, 2, DYE_INDEX_Magenta));
		RM.ic2_extractor(ST.make(MD.MaCu, "plant_static", 1, 6), IL.MaCu_Dye_Brown.get(2));
		RM.ic2_extractor(ST.make(MD.MaCu, "plant_static", 1, 7), IL.MaCu_Dye_Yellow.get(2));
		RM.ic2_extractor(ST.make(MD.MaCu, "plant_static", 1, 8), ST.make(Items.dye, 2, DYE_INDEX_Orange));
		RM.ic2_extractor(ST.make(MD.MaCu, "plant_static", 1, 9), ST.make(Items.dye, 2, DYE_INDEX_Purple));
		RM.ic2_extractor(ST.make(MD.MaCu, "plant_static", 1,10), ST.make(Items.dye, 2, DYE_INDEX_Gray));
		RM.ic2_extractor(ST.make(MD.MaCu, "plant_static", 1,11), ST.make(Items.dye, 2, DYE_INDEX_LightGray));
		RM.ic2_extractor(ST.make(MD.MaCu, "plant_static", 1,12), IL.MaCu_Dye_White.get(2));
		
		RM.ic2_extractor(ST.make(MD.MaCu, "plant_growable", 1, 0), IL.MaCu_Dye_Green.get(1));
		RM.ic2_extractor(ST.make(MD.MaCu, "plant_growable", 1, 1), IL.MaCu_Dye_Green.get(1));
		RM.ic2_extractor(ST.make(MD.MaCu, "plant_growable", 1, 2), ST.make(Items.dye, 2, DYE_INDEX_Pink));
		RM.ic2_extractor(ST.make(MD.MaCu, "plant_growable", 1, 3), IL.MaCu_Dye_Red.get(2));
		RM.ic2_extractor(ST.make(MD.MaCu, "plant_growable", 1, 4), ST.make(Items.dye, 2, DYE_INDEX_LightBlue));
		RM.ic2_extractor(ST.make(MD.MaCu, "plant_growable", 1, 5), ST.make(Items.dye, 2, DYE_INDEX_Magenta));
		RM.ic2_extractor(ST.make(MD.MaCu, "plant_growable", 1, 6), IL.MaCu_Dye_Brown.get(2));
		RM.ic2_extractor(ST.make(MD.MaCu, "plant_growable", 1, 7), IL.MaCu_Dye_Yellow.get(2));
		RM.ic2_extractor(ST.make(MD.MaCu, "plant_growable", 1, 8), ST.make(Items.dye, 2, DYE_INDEX_Orange));
		RM.ic2_extractor(ST.make(MD.MaCu, "plant_growable", 1, 9), ST.make(Items.dye, 2, DYE_INDEX_Purple));
		RM.ic2_extractor(ST.make(MD.MaCu, "plant_growable", 1,10), ST.make(Items.dye, 2, DYE_INDEX_Gray));
		RM.ic2_extractor(ST.make(MD.MaCu, "plant_growable", 1,11), ST.make(Items.dye, 2, DYE_INDEX_LightGray));
		RM.ic2_extractor(ST.make(MD.MaCu, "plant_growable", 1,12), IL.MaCu_Dye_White.get(2));
		}
	}
}
