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

package gregtech6.loaders.c;

import static gregapi6.data.CS.*;
import static gregapi6.data.OP.*;

import gregapi6.code.ItemStackContainer;
import gregapi6.config.ConfigCategories;
import gregapi6.data.CS.BlocksGT;
import gregapi6.data.CS.BooksGT;
import gregapi6.data.CS.ConfigsGT;
import gregapi6.data.FL;
import gregapi6.data.IL;
import gregapi6.data.MT;
import gregapi6.data.OD;
import gregapi6.data.OP;
import gregapi6.data.RM;
import gregapi6.oredict.OreDictMaterial;
import gregapi6.oredict.event.IOreDictListenerEvent;
import gregapi6.oredict.event.OreDictListenerEvent_Names;
import gregapi6.util.CR;
import gregapi6.util.OM;
import gregapi6.util.ST;
import gregapi6.wooddict.WoodDictionary;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class Loader_Recipes_Vanilla_OreDict extends OreDictListenerEvent_Names {
	@Override
	public void addAllListeners() {
		addListener(OP.dust.dat(MT.Glass), OP.ingot.dat(MT.Glass), OP.gem.dat(MT.Glass), new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.add_smelting(aEvent.mStack, ST.make(Blocks.glass, 1, 0));
		}});
		addListener(OP.dust.dat(MT.Stone), new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.add_smelting(aEvent.mStack, ST.make(Blocks.stone, 1, 0));
		}});
		addListener(DYE_OREDICTS_LENS[DYE_INDEX_White], new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.LaserEngraver    .addRecipe2(T, 16,  64, ST.make(Blocks.stone, 1, W), ST.amount(0, aEvent.mStack), ST.make(Blocks.stonebrick, 1, 3));
			RM.LaserEngraver    .addRecipe2(T, 16, 128, IL.Module_Stone_Generator.get(  0), ST.amount(0, aEvent.mStack), ST.make(Blocks.stonebrick, 1, 3));
		}});
		addListener("blockSolidObsidian", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Hammer       .addRecipe1(T, 16,  64, aEvent.mStack, IL.RC_Crushed_Obsidian.get(1, OP.dust.mat(MT.Obsidian, 8)));
			RM.Crusher      .addRecipe1(T, 16, 600, new long[] {10000, 2500}, aEvent.mStack, IL.RC_Crushed_Obsidian.get(1, OP.dust.mat(MT.Obsidian, 8)), OP.dust.mat(MT.Obsidian, 1));
			RM.pulverizing(aEvent.mStack, IL.RC_Crushed_Obsidian.get(1, OP.dust.mat(MT.Obsidian, 8)), OP.dust.mat(MT.Obsidian, 1), 25, T);
		}});
		addListener("stoneNetherBrick", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Hammer       .addRecipe1(T, 16,  16, aEvent.mStack, ST.make(Items.netherbrick, 3, 0));
			RM.Crusher      .addRecipe1(T, 16,  16, new long[] {10000, 9000, 8000, 7000}, aEvent.mStack, ST.make(Items.netherbrick, 1, 0), ST.make(Items.netherbrick, 1, 0), ST.make(Items.netherbrick, 1, 0), ST.make(Items.netherbrick, 1, 0));
		}});
		addListener("stoneNetherrack", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Hammer       .addRecipe1(T, 16,  16, aEvent.mStack, OP.dustImpure.mat(MT.Netherrack, 1));
			RM.Crusher      .addRecipe1(T, 16,  16, new long[] {10000, 500}, aEvent.mStack, OP.dustImpure.mat(MT.Netherrack, 1), OM.dust(MT.S));
		}});
		addListener("stoneEndstone", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Hammer       .addRecipe1(T, 16,  16, aEvent.mStack, OP.dustImpure.mat(MT.Endstone, 1));
			RM.Crusher      .addRecipe1(T, 16,  16, new long[] {10000, 500}, aEvent.mStack, OP.dustImpure.mat(MT.Endstone, 1), OM.dust(MT.EnderPearl));
		}});
		addListener("stoneRedrock", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Hammer       .addRecipe1(T, 16,  16, aEvent.mStack, OP.dustImpure.mat(MT.Redrock, 1));
			RM.Crusher      .addRecipe1(T, 16,  16, new long[] {10000, 1000}, aEvent.mStack, OP.dustImpure.mat(MT.Redrock, 1), OM.dust(MT.ClayBrown));
		}});
		addListener(OD.slimeball, new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (!OM.is(OD.slimeballSwet, aEvent.mStack) && !OM.is(OD.slimeballPink, aEvent.mStack) && !OM.is(OD.slimeballRice, aEvent.mStack) && !OM.is(OD.itemTar, aEvent.mStack)) {
			RM.Squeezer     .addRecipe1(T, 16,   64, aEvent.mStack, NF, FL.Slime_Green.make(250), ZL_IS);
			RM.Juicer       .addRecipe1(T, 16,   64, aEvent.mStack, NF, FL.Slime_Green.make(125), ZL_IS);
			RM.Centrifuge   .addRecipe1(T, 16,   64, aEvent.mStack, NF, FL.Latex.make(L/2), FL.Glue.make(250));
			RM.generify(aEvent.mStack, ST.make(Items.slime_ball, 1, 0));
			}
			RM.Laminator    .addRecipe2(T, 16,   16, aEvent.mStack, ST.make(Blocks.piston, 1, W), ST.make(Blocks.sticky_piston, 1, 0));
			RM.Mixer        .addRecipe1(T, 16,   16, aEvent.mStack, FL.Water.make(250), FL.Glue.make(250), ZL_IS);
			RM.Mixer        .addRecipe1(T, 16,   16, aEvent.mStack, FL.DistW.make(200), FL.Glue.make(250), ZL_IS);
			RM.Mixer        .addRecipe2(T, 16,   16, aEvent.mStack, OM.dust(MT.Blaze), ST.make(Items.magma_cream, 1, 0));
			for (byte i = 0; i < 16; i++)
			RM.Loom         .addRecipeX(T, 16,   16, ST.array(ST.tag(3), aEvent.mStack, OP.plantGtFiber.mat(MT.DATA.Dye_Materials[15-i], 4)), ST.make(Items.lead, 2, 0));
			RM.Loom         .addRecipeX(T, 16,   16, ST.array(ST.tag(3), aEvent.mStack, OP.plantGtFiber.mat(MT.Cu, 4)), ST.make(Items.lead, 2, 0));
			RM.Loom         .addRecipeX(T, 16,   16, ST.array(ST.tag(3), aEvent.mStack, ST.make(Items.string, 4, W)), ST.make(Items.lead, 2, 0));
		}});
		addListener(OD.slimeballPink, new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Squeezer     .addRecipe1(T, 16,   64, aEvent.mStack, NF, FL.Slime_Pink.make(250, FL.Slime_Green), ZL_IS);
			RM.Juicer       .addRecipe1(T, 16,   64, aEvent.mStack, NF, FL.Slime_Pink.make(125, FL.Slime_Green), ZL_IS);
			RM.Centrifuge   .addRecipe1(T, 16,   64, aEvent.mStack, NF, FL.Latex.make(L/2), FL.Glue.make(250));
			RM.generify(aEvent.mStack, ST.make(Items.slime_ball, 1, 0));
		}});
		addListener(OD.slimeballSwet, new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Squeezer     .addRecipe1(T, 16,   64, aEvent.mStack, NF, FL.Slime_Green.make(250), ZL_IS);
			RM.Juicer       .addRecipe1(T, 16,   64, aEvent.mStack, NF, FL.Slime_Green.make(125), ZL_IS);
			RM.Centrifuge   .addRecipe1(T, 16,   64, aEvent.mStack, NF, FL.Latex.make(L/2), FL.Glue.make(250));
			RM.generify(aEvent.mStack, ST.make(Items.slime_ball, 1, 0));
		}});
		addListener("foodJellyfishraw", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Squeezer     .addRecipe1(T, 16,   16,  9000, aEvent.mStack, NF, NF, ST.make(Items.slime_ball, 1, 0));
			RM.Juicer       .addRecipe1(T, 16,   16,  7000, aEvent.mStack, NF, NF, ST.make(Items.slime_ball, 1, 0));
			RM.Centrifuge   .addRecipe1(T, 16,   16       , aEvent.mStack, NF, FL.Glue.make(500), ZL_IS);
		}});
		addListener(OD.record, new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (!BooksGT.BOOK_REGISTER.containsKey(new ItemStackContainer(aEvent.mStack))) BooksGT.BOOK_REGISTER.put(new ItemStackContainer(aEvent.mStack), (byte)30);
		}});
		addListener(OP.treeSapling, new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.sawing(16, 16, F, 1, aEvent.mStack, OP.stick.mat(MT.Wood, 1));
		}});
		addListener(OD.logRubber, new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.ic2_extractor(aEvent.mStack, dustSmall.mat(MT.Rubber, 2));
			RM.pulverizing(aEvent.mStack, dust.mat(MT.WoodRubber, 6), IL.IC2_Resin.get(1, IL.Resin.get(1)), 33, F);
			if (FL.Resin_Rubber.exists())
			RM.Squeezer.addRecipe1(T, 16, 64, 5000, aEvent.mStack, NF, FL.Resin_Rubber.make(200), IL.IC2_Resin.get(1, IL.Resin.get(1)));
		}});
		addListener(OD.logWood, OD.logRubber, new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.disabledrecipes, "wood2charcoalsmelting", T)) RM.rem_smelting(aEvent.mStack, ST.make(Items.coal, 1, 1));

			if (ST.block(aEvent.mStack) == BlocksGT.Log1) return;

			if (IL.MaCu_Polished_Logs.exists())
			RM.Bath         .addRecipe1(T,  0,  144, aEvent.mStack, FL.Oil_Fish.make(3000), NF  , IL.MaCu_Polished_Logs.get(1));
			RM.Freezer      .addRecipe1(T, 16,   16, aEvent.mStack, FL.Water.make(1000), NF     , ST.make(BlocksGT.Log1, 1, 3));
			RM.Freezer      .addRecipe1(T, 16,   16, aEvent.mStack, FL.DistW.make(1000), NF     , ST.make(BlocksGT.Log1, 1, 3));
			RM.Drying       .addRecipe1(T, 16,  128, aEvent.mStack, NF, FL.DistW.make(64)       , ST.make(BlocksGT.Log1, 1, 0));
			RM.Fermenter    .addRecipe1(T, 16,  128, aEvent.mStack                              , ST.make(BlocksGT.Log1, 1, 1));

			if (WoodDictionary.IGNORED_OREDICT_REGISTRATIONS.contains(ST.item_(aEvent.mStack))) return;

			OreDictMaterial tWood = MT.Wood;

			if (aEvent.mOreDictName.equals(OD.logRubber.toString())) {
				tWood = MT.WoodRubber;
				RM.debarking(aEvent.mStack, ST.make(BlocksGT.Beam2, 1, 2), OM.dust(MT.Bark));
			} else {
				RM.debarking(aEvent.mStack, ST.make(BlocksGT.Beam2, 1, 3), OM.dust(MT.Bark));
			}

			RM.Lathe        .addRecipe1(T, 16,   80, aEvent.mStack, stickLong.mat(tWood, 4), dust.mat(tWood, 2));
			RM.CokeOven     .addRecipe1(T,  0, 3600, aEvent.mStack, NF, MT.Creosote.liquid(U4, F), OP.gem.mat(MT.Charcoal, 1));

			if (ST.meta_(aEvent.mStack) == W) {
				ItemStack tPlank;
				for (int i = 0; i < W; i++) {
					tPlank = CR.get(ST.make(ST.item_(aEvent.mStack), 1, i));
					if (tPlank == null) {if (i < 16) continue; break;}
					ItemStack tPlanks = ST.amount((tPlank.stackSize * 3) / 2, tPlank);
					tPlanks.stackSize = (tPlanks.stackSize * 3) / 2;
					RM.sawing(16, 128, F, 5, ST.make(ST.item_(aEvent.mStack), 1, i), ST.copy(tPlanks), dust.mat(tWood, 1), OM.dust(MT.Bark, U2));
					CR.remove(ST.make(ST.item_(aEvent.mStack), 1, i));
					CR.shaped(ST.amount(NERFED_WOOD?tPlank.stackSize:(tPlank.stackSize * 5) / 4, tPlank), CR.DEF_NAC_NCC | CR.ONLY_IF_HAS_RESULT, "s", "L", 'L', ST.make(ST.item_(aEvent.mStack), 1, i));
					CR.shapeless(ST.amount(tPlank.stackSize / (NERFED_WOOD?2:1), tPlank), CR.DEF_NAC_NCC | CR.ONLY_IF_HAS_RESULT, new Object[] {ST.make(ST.item_(aEvent.mStack), 1, i)});
				}
			} else {
				ItemStack tPlank = CR.get(ST.array(aEvent.mStack));
				if (tPlank != null) {
					ItemStack tPlanks = ST.copy(tPlank);
					tPlanks.stackSize = (tPlanks.stackSize * 3) / 2;
					RM.sawing(16, 128, F, 5, aEvent.mStack, ST.copy(tPlanks), dust.mat(tWood, 1), OM.dust(MT.Bark, U2));
					CR.remove(aEvent.mStack);
					CR.shaped(ST.amount(NERFED_WOOD?tPlank.stackSize:(tPlank.stackSize * 5) / 4, tPlank), CR.DEF_NAC_NCC | CR.ONLY_IF_HAS_RESULT, "s", "L", 'L', aEvent.mStack);
					CR.shapeless(ST.amount(tPlank.stackSize / (NERFED_WOOD?2:1), tPlank), CR.DEF_NAC_NCC | CR.ONLY_IF_HAS_RESULT, new Object[] {aEvent.mStack});
				}
			}
		}});
		addListener(OD.slabWood, new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.generify(aEvent.mStack, IL.Plank_Slab.get(1));
		}});
		addListener(OD.plankSkyroot, OD.plankWeedwood, OD.plankWood, new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.generify(aEvent.mStack, IL.Plank.get(1));

			RM.Assembler.addRecipe2(T, 16,  32, ST.amount(8, aEvent.mStack), dust.mat(MT.Redstone, 1), ST.make(Blocks.noteblock, 1, 0));
			RM.Assembler.addRecipe2(T, 16,  64, ST.amount(8, aEvent.mStack), gem .mat(MT.Diamond , 1), ST.make(Blocks.jukebox, 1, 0));
//          RM.Assembler.addRecipe2(T, 16,  32, aEvent.mStack, screw.mat(MT.Fe, 1), IL.Crate_Empty.get(1));
//          RM.Assembler.addRecipe2(T, 16,  32, aEvent.mStack, screw.mat(MT.WroughtIron, 1), IL.Crate_Empty.get(1));
//          RM.Assembler.addRecipe2(T, 16,  32, aEvent.mStack, screw.mat(MT.Steel, 1), IL.Crate_Empty.get(1));
			RM.Assembler.addRecipe2(T, 16,  16, aEvent.mStack, ST.tag(1), ST.make(Blocks.wooden_button, 1, 0));
			RM.Assembler.addRecipe2(T, 16,  32, ST.amount(2, aEvent.mStack), ST.tag(2), ST.make(Blocks.wooden_pressure_plate, 1, 0));
			RM.Assembler.addRecipe2(T, 16,  48, ST.amount(3, aEvent.mStack), ST.tag(3), ST.make(Blocks.trapdoor, 1, 0));
			RM.Assembler.addRecipe2(T, 16,  64, ST.amount(4, aEvent.mStack), ST.tag(4), ST.make(Blocks.crafting_table, 1, 0));
			RM.Assembler.addRecipe2(T, 16,  96, ST.amount(2, aEvent.mStack), ST.tag(6), ST.make(Items.wooden_door, 1, 0));
			RM.Assembler.addRecipe2(T, 16, 128, ST.amount(8, aEvent.mStack), ST.tag(8), ST.make(Blocks.chest, 1, 0));
			RM.Assembler.addRecipe2(T, 16,  64, ST.amount(6, aEvent.mStack), ST.make(Items.book, 3, 0), ST.make(Blocks.bookshelf, 1, 0));

			if (WoodDictionary.IGNORED_OREDICT_REGISTRATIONS.contains(ST.item_(aEvent.mStack))) return;

			if (IL.MaCu_Polished_Planks.exists())
			RM.Bath     .addRecipe1(T,  0, 144, aEvent.mStack, FL.Oil_Fish      .make(1000), NF, IL.MaCu_Polished_Planks.get(1));
			ItemStack tTreated = IL.IE_Treated_Planks.get(1, IL.Treated_Planks.get(1));
			RM.Bath     .addRecipe1(T,  0, 144, aEvent.mStack, FL.Oil_Seed      .make( 100), NF, tTreated);
			RM.Bath     .addRecipe1(T,  0, 144, aEvent.mStack, FL.Oil_Lin       .make( 100), NF, tTreated);
			RM.Bath     .addRecipe1(T,  0, 144, aEvent.mStack, FL.Oil_Hemp      .make( 100), NF, tTreated);
			RM.Bath     .addRecipe1(T,  0, 144, aEvent.mStack, FL.Oil_Nut       .make( 100), NF, tTreated);
			RM.Bath     .addRecipe1(T,  0, 144, aEvent.mStack, FL.Oil_Olive     .make( 100), NF, tTreated);
			RM.Bath     .addRecipe1(T,  0, 144, aEvent.mStack, FL.Oil_Sunflower .make( 100), NF, tTreated);
			RM.Bath     .addRecipe1(T,  0, 144, aEvent.mStack, FL.Oil_Creosote  .make( 100), NF, tTreated);
		}});
	}
}
