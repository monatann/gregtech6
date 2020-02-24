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
import static gregapi6.util.CR.*;

import gregapi6.config.ConfigCategories;
import gregapi6.data.ANY;
import gregapi6.data.CS.BlocksGT;
import gregapi6.data.CS.ConfigsGT;
import gregapi6.data.CS.FluidsGT;
import gregapi6.data.CS.OreDictToolNames;
import gregapi6.data.FL;
import gregapi6.data.IL;
import gregapi6.data.MT;
import gregapi6.data.OD;
import gregapi6.data.OP;
import gregapi6.data.RM;
import gregapi6.oredict.OreDictMaterial;
import gregapi6.util.CR;
import gregapi6.util.OM;
import gregapi6.util.ST;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class Loader_Recipes_Vanilla implements Runnable {
	@Override public void run() {
		CR.remove(ST.make(Blocks.cobblestone, 1, 0), ST.make(Items.quartz, 1, 0), NI, ST.make(Items.quartz, 1, 0), ST.make(Blocks.cobblestone, 1, 0));
		CR.remove(ST.make(Items.blaze_rod, 1, 0));
		CR.remove(ST.make(Items.reeds, 1, 0));
		CR.remove(ST.make(Items.bone, 1, 0));

		CR.shapeless(OP.dust.mat(MT.White, 1), DEF_NAC, new Object[] {ST.make(Items.bone, 1, W)});
		CR.shapeless(ST.make(Items.blaze_powder, 1, 0), DEF_NAC, new Object[] {ST.make(Items.blaze_rod, 1, W)});
		CR.shaped(ST.make(Items.paper, 1, 0), DEF_NAC, "XXX", 'X', ST.make(Items.reeds, 1, 0));
		CR.shapeless(ST.make(Items.book, 1, 0), DEF, new Object[] {OD.craftingLeather, "paperEmpty", "paperEmpty", "paperEmpty"});

		CR.shaped(ST.make(Blocks.furnace, 1, 0), DEF_NCC, "XXX", "XFX", "XXX", 'X', OP.cobblestone, 'F', OD.craftingFirestarter);

		CR.delate(ST.make(Blocks.enchanting_table, 1, 0));
		CR.delate(ST.make(Blocks.ender_chest, 1, 0));
		CR.delate(ST.make(Blocks.furnace, 1, 0));
		CR.delate(ST.make(Items.saddle, 1, 0));

		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.disabledrecipes, "enchantmenttable", F)) {
			OUT.println("GT_Mod: Removing the Recipe of the Enchantment Table, to have Fun enchanting with the Anvil and Books from Dungeons.");
		} else {
			CR.shaped(ST.make(Blocks.enchanting_table, 1, 0), CR.DEF_NCC, " B ", "DOD", "OOO", 'B', IL.TC_Thaumonomicon.get(1, ST.make(Items.book, 1, W)), 'O', OP.blockSolid.dat(MT.Obsidian), 'D', OP.gem.dat(ANY.Diamond));
		}

		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.disabledrecipes, "enderchest", F)) {
			OUT.println("GT_Mod: Removing the Recipe of the Enderchest.");
		} else {
			CR.shaped(ST.make(Blocks.ender_chest, 1, 0), CR.DEF_NCC, "OOO", "OEO", "OOO", 'O', OP.blockSolid.dat(MT.Obsidian), 'E', OP.gem.dat(MT.EnderEye));
		}

		CR.shaped(ST.make(Items.bucket, 1, 0), DEF_NAC | DEL_OTHER_SHAPED_RECIPES, "X X", "XhX", "XYX", 'Y', OP.plate.dat(ANY.Fe), 'X', OP.plateCurved.dat(ANY.Fe));
		if (!ConfigsGT.RECIPES.get(ConfigCategories.Recipes.recipereplacements, "Iron.Bucket", T))
		CR.shaped(ST.make(Items.bucket, 1, 0), DEF_NAC | DEL_OTHER_SHAPED_RECIPES, "X X", " X ", 'X', OP.ingot.dat(ANY.Fe));

		ItemStack tMat = ST.make(Items.iron_ingot, 1, 0), tStack;
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.recipereplacements, "Iron.PressurePlate", T))    if (null != (tStack = CR.remove(tMat, tMat, null, null, null, null, null, null, null))) {
			CR.shaped(tStack, DEF | DEL_OTHER_SHAPED_RECIPES, "XXh", 'X', OP.plate.dat(ANY.Fe), 'S', OP.stick.dat(ANY.Wood), 'I', OP.ingot.dat(ANY.Fe));
		}
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.recipereplacements, "Iron.Compass", T)) {
			CR.shaped(ST.make(Items.compass, 1, 0), DEF | DEL_OTHER_SHAPED_RECIPES | ONLY_IF_HAS_OTHER_RECIPES, "sSR", "CIC", "dPh", 'P', OP.plate.dat(ANY.Fe), 'R', OP.gem.dat(MT.Redstone), 'C', OP.plateCurved.dat(ANY.Fe), 'I', OP.stick.dat(ANY.Fe), 'S', OP.stick.dat(MT.IronMagnetic));
		} else {
			CR.shaped(ST.make(Items.compass, 1, 0), DEF | DEL_OTHER_SHAPED_RECIPES | ONLY_IF_HAS_OTHER_RECIPES, " X ", "XRX", " X ", 'X', OP.ingot.dat(ANY.Fe), 'R', OD.itemRedstone);
		}
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.recipereplacements, "Iron.Door", T)) {
			CR.shaped(ST.make(Items.iron_door, 3, 0), DEF | DEL_OTHER_SHAPED_RECIPES | ONLY_IF_HAS_OTHER_RECIPES, "XX ", "XXh", "XX ", 'X', OP.plate.dat(ANY.Fe));
		} else {
			CR.shaped(ST.make(Items.iron_door, 3, 0), DEF | DEL_OTHER_SHAPED_RECIPES | ONLY_IF_HAS_OTHER_RECIPES, "II" , "II" , "II" , 'I', OP.ingot.dat(ANY.Fe));
		}
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.recipereplacements, "Iron.Cauldron", T))         if (null != (tStack = CR.remove(tMat, null, tMat, tMat, null, tMat, tMat, tMat, tMat))) {
			CR.shaped(tStack, DEF | DEL_OTHER_SHAPED_RECIPES, "X X", "XhX", "XXX", 'X', OP.block.dat(ANY.Fe), 'S', OP.stick.dat(ANY.Wood), 'I', OP.ingot.dat(ANY.Fe));
		}
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.recipereplacements, "Iron.Hopper", T))           if (null != (tStack = CR.remove(tMat, null, tMat, tMat, ST.make(Blocks.chest, 1, 0), tMat, null, tMat, null))) {
			CR.shaped(tStack, DEF | DEL_OTHER_SHAPED_RECIPES, "XwX", "XCX", " X ", 'X', OP.plate.dat(ANY.Iron), 'S', OP.stick.dat(ANY.Wood), 'I', OP.ingot.dat(ANY.Iron), 'C', "craftingChest");
		}
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.recipereplacements, "Iron.Bars", T)) CR.remove(tMat, tMat, tMat, tMat, tMat, tMat, null, null, null);
		CR.shaped(ST.make(Blocks.iron_bars, 8, 0), DEF, " w ", "XXX", "XXX", 'X', OP.stick.dat(ANY.Fe), 'S', OP.stick.dat(ANY.Wood), 'I', OP.ingot.dat(ANY.Fe));

		CR.shaped(ST.make(Blocks.glass_pane, 8, 0), DEF, "XXX", 'X', OP.plate.dat(MT.Glass));
		CR.shaped(ST.make(Blocks.glass_pane, 8, 0), DEF, "XXX", 'X', OP.plateGem.dat(MT.Glass));

		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.recipereplacements, "Gold.Clock", T)) {
			CR.shaped(ST.make(Items.clock, 1, 0), DEF | DEL_OTHER_SHAPED_RECIPES | ONLY_IF_HAS_OTHER_RECIPES, "sGr", "CRC", "dPh", 'P', OP.plate.dat(MT.Au), 'C', OP.plateCurved.dat(MT.Au), 'R', OP.gem.dat(MT.Redstone), 'G', OP.gearGtSmall.dat(MT.Au));
		} else {
			CR.shaped(ST.make(Items.clock, 1, 0), DEF | DEL_OTHER_SHAPED_RECIPES | ONLY_IF_HAS_OTHER_RECIPES, " X ", "XRX", " X ", 'X', OP.ingot.dat(MT.Au), 'R', OD.itemRedstone);
		}
		tMat = ST.make(Items.gold_ingot, 1, 0);
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.recipereplacements, "Gold.PressurePlate", T))    if (null != (tStack = CR.remove(tMat, tMat, null, null, null, null, null, null, null))) {
			CR.shaped(tStack, DEF | DEL_OTHER_SHAPED_RECIPES, "XXh", 'X', OP.plate.dat(MT.Au), 'S', OP.stick.dat(ANY.Wood), 'I', OP.ingot.dat(MT.Au));
		}
		tMat = OP.ingot.mat(MT.Rubber, 1);
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.recipereplacements, "Rubber.Sheet", T))          if (null != (tStack = CR.remove(tMat, tMat, tMat, tMat, tMat, tMat, null, null, null))) {
			CR.shaped(tStack, DEF | DEL_OTHER_SHAPED_RECIPES, "XXX", "XXX", 'X', OP.plate.dat(MT.Rubber));
		}

		CR.shaped(ST.make(Blocks.wooden_pressure_plate, 1, 0)   , DEF, "PP", 'P', OD.plankAnyWood);
		CR.shaped(ST.make(Blocks.stone_button, 2, 0)            , DEF, "S", "S", 'S', Blocks.stone);
		CR.shaped(ST.make(Blocks.stone_button, 2, 0)            , DEF, "S", "S", 'S', OP.stone);
		CR.shaped(ST.make(Blocks.stone_pressure_plate, 1, 0)    , DEF, "SS", 'S', Blocks.stone);
		CR.shaped(ST.make(Blocks.stone_pressure_plate, 1, 0)    , DEF, "SS", 'S', OP.stone);

		CR.shapeless(OP.stick       .mat(MT.IronMagnetic, 1), DEF_NAC, new Object[] {OP.stick       .dat(ANY.Fe), OD.itemRedstone, OD.itemRedstone, OD.itemRedstone, OD.itemRedstone});
		CR.shapeless(OP.stickLong   .mat(MT.IronMagnetic, 1), DEF_NAC, new Object[] {OP.stickLong   .dat(ANY.Fe), OD.itemRedstone, OD.itemRedstone, OD.itemRedstone, OD.itemRedstone, OD.itemRedstone, OD.itemRedstone, OD.itemRedstone, OD.itemRedstone});

		CR.shaped(IL.Dye_Bonemeal.get(1), DEF, "h", "X", 'X', ST.make(Items.bone, 1, W));


		RM.pack(rockGt.mat(MT.Stone     , 4), ST.make(Blocks.cobblestone, 1, 0));
		RM.pack(rockGt.mat(MT.Netherrack, 4), ST.make(Blocks.netherrack , 1, 0));
		RM.pack(rockGt.mat(MT.Endstone  , 4), ST.make(Blocks.end_stone  , 1, 0));

		CR.shaped(ST.make(Blocks.netherrack                 , 1, 0), DEF    , "XX", "XX", 'X', rockGt.dat(MT.Netherrack));
		CR.shaped(ST.make(Blocks.end_stone                  , 1, 0), DEF    , "XX", "XX", 'X', rockGt.dat(MT.Endstone));
		CR.shaped(ST.make(Blocks.cobblestone                , 1, 0), DEF    , "XX", "XX", 'X', OP.rockGt.dat(MT.Stone));
		CR.shaped(ST.make(Blocks.stone_stairs               , 1, 0), DEF_MIR, " X", "XX", 'X', OP.rockGt.dat(MT.Stone));
		CR.shaped(ST.make(Blocks.stone_slab                 , 1, 3), DEF    , "  ", "XX", 'X', OP.rockGt.dat(MT.Stone));
		CR.shaped(ST.make(Blocks.stone_stairs               , 4, 0), DEF_MIR, " X", "XX", 'X', Blocks.cobblestone);
		CR.shaped(ST.make(Blocks.stone_slab                 , 4, 3), DEF    , "  ", "XX", 'X', Blocks.cobblestone);

		CR.shaped(ST.make(Blocks.gravel                     , 1, 0), DEF, "h", "X", 'X', ST.make(Blocks.cobblestone, 1, 0));
		CR.shaped(ST.make(Blocks.cobblestone                , 1, 0), DEF, "h", "X", 'X', ST.make(Blocks.stone, 1, 0));
		CR.shaped(ST.make(Blocks.stonebrick                 , 1, 2), DEF, "h", "X", 'X', ST.make(Blocks.stonebrick, 1, 0));
		CR.shaped(ST.make(Blocks.stonebrick                 , 1, 3), DEF, "f", "X", 'X', ST.make(Blocks.double_stone_slab, 1, 8));
		CR.shapeless(ST.make(Blocks.double_stone_slab       , 1, 8), DEF, new Object[] {ST.make(Blocks.double_stone_slab    , 1, 0)});
		CR.shapeless(ST.make(Blocks.double_stone_slab       , 1, 0), DEF, new Object[] {ST.make(Blocks.double_stone_slab    , 1, 8)});
		CR.shaped(ST.make(Blocks.double_stone_slab          , 1, 0), DEF, "B", "B", 'B', ST.make(Blocks.stone_slab, 1, 0));
		CR.shaped(ST.make(Blocks.cobblestone                , 1, 0), DEF, "B", "B", 'B', ST.make(Blocks.stone_slab, 1, 3));
		CR.shaped(ST.make(Blocks.brick_block                , 1, 0), DEF, "B", "B", 'B', ST.make(Blocks.stone_slab, 1, 4));
		CR.shaped(ST.make(Blocks.stonebrick                 , 1, 0), DEF, "B", "B", 'B', ST.make(Blocks.stone_slab, 1, 5));
		CR.shaped(ST.make(Blocks.nether_brick               , 1, 0), DEF, "B", "B", 'B', ST.make(Blocks.stone_slab, 1, 6));
		CR.shaped(ST.make(Blocks.quartz_block               , 1, 0), DEF, "B", "B", 'B', ST.make(Blocks.stone_slab, 1, 7));
		CR.shaped(ST.make(Blocks.double_stone_slab          , 1, 8), DEF, "B", "B", 'B', ST.make(Blocks.stone_slab, 1, 8));
		CR.shaped(ST.make(Blocks.planks                     , 1, 0), DEF, "B", "B", 'B', ST.make(Blocks.wooden_slab, 1, 0));
		CR.shaped(ST.make(Blocks.planks                     , 1, 1), DEF, "B", "B", 'B', ST.make(Blocks.wooden_slab, 1, 1));
		CR.shaped(ST.make(Blocks.planks                     , 1, 2), DEF, "B", "B", 'B', ST.make(Blocks.wooden_slab, 1, 2));
		CR.shaped(ST.make(Blocks.planks                     , 1, 3), DEF, "B", "B", 'B', ST.make(Blocks.wooden_slab, 1, 3));
		CR.shaped(ST.make(Blocks.planks                     , 1, 4), DEF, "B", "B", 'B', ST.make(Blocks.wooden_slab, 1, 4));
		CR.shaped(ST.make(Blocks.planks                     , 1, 5), DEF, "B", "B", 'B', ST.make(Blocks.wooden_slab, 1, 5));
		CR.shaped(ST.make(Blocks.planks                     , 1, 6), DEF, "B", "B", 'B', ST.make(Blocks.wooden_slab, 1, 6));
		CR.shaped(ST.make(Blocks.planks                     , 1, 7), DEF, "B", "B", 'B', ST.make(Blocks.wooden_slab, 1, 7));
		CR.shaped(ST.make(Blocks.stone_slab                 , 1, 4), DEF, "BB", 'B', ST.make(Items.brick, 1, 0));

		CR.shaped(ST.make(Items.stick                       , 2, 0), DEF, "s", "X", 'X', ST.make(Blocks.deadbush, 1, W));
		CR.shaped(ST.make(Items.stick                       , 2, 0), DEF, "k", "X", 'X', ST.make(Blocks.deadbush, 1, W));
		CR.shaped(ST.make(Items.stick                       , 2, 0), DEF, "s", "X", 'X', ST.make(Blocks.tallgrass, 1, 0));
		CR.shaped(ST.make(Items.stick                       , 2, 0), DEF, "k", "X", 'X', ST.make(Blocks.tallgrass, 1, 0));
		CR.shaped(ST.make(Items.stick                       , 1, 0), DEF, "s", "X", 'X', OP.treeSapling);
		CR.shaped(ST.make(Items.stick                       , 1, 0), DEF, "k", "X", 'X', OP.treeSapling);

		CR.shaped(ST.make(Blocks.torch, 2, 0), DEF_NAC,  "X" ,  "S" , 'S', OP.stick.dat(ANY.Wood), 'X', OP.gem.dat(MT.S)                        );
		CR.shaped(ST.make(Blocks.torch, 2, 0), DEF_NAC,  "X" ,  "S" , 'S', OP.stick.dat(ANY.Wood), 'X', OP.dust.dat(MT.S)                       );
		CR.shaped(ST.make(Blocks.torch, 2, 0), DEF_NAC,  "X" ,  "S" , 'S', OP.stick.dat(ANY.Wood), 'X', OP.crushed.dat(MT.S)                    );
		CR.shaped(ST.make(Blocks.torch, 2, 0), DEF_NAC,  "X" ,  "S" , 'S', OP.stick.dat(ANY.Wood), 'X', OP.crushedPurified.dat(MT.S)            );
		CR.shaped(ST.make(Blocks.torch, 2, 0), DEF_NAC,  "X" ,  "S" , 'S', OP.stick.dat(ANY.Wood), 'X', OP.crushedCentrifuged.dat(MT.S)         );
		CR.shaped(ST.make(Blocks.torch, 6, 0), DEF_NAC,  "X" ,  "S" , 'S', OP.stick.dat(ANY.Wood), 'X', OP.gem.dat(MT.P)                        );
		CR.shaped(ST.make(Blocks.torch, 6, 0), DEF_NAC,  "X" ,  "S" , 'S', OP.stick.dat(ANY.Wood), 'X', OP.dust.dat(MT.P)                       );
		CR.shaped(ST.make(Blocks.torch, 6, 0), DEF_NAC,  "X" ,  "S" , 'S', OP.stick.dat(ANY.Wood), 'X', OP.crushed.dat(MT.P)                    );
		CR.shaped(ST.make(Blocks.torch, 6, 0), DEF_NAC,  "X" ,  "S" , 'S', OP.stick.dat(ANY.Wood), 'X', OP.crushedPurified.dat(MT.P)            );
		CR.shaped(ST.make(Blocks.torch, 6, 0), DEF_NAC,  "X" ,  "S" , 'S', OP.stick.dat(ANY.Wood), 'X', OP.crushedCentrifuged.dat(MT.P)         );
		CR.shaped(ST.make(Blocks.torch, 6, 0), DEF_NAC,  "X" ,  "S" , 'S', OP.stick.dat(ANY.Wood), 'X', OP.gem.dat(MT.Phosphorus)               );
		CR.shaped(ST.make(Blocks.torch, 6, 0), DEF_NAC,  "X" ,  "S" , 'S', OP.stick.dat(ANY.Wood), 'X', OP.dust.dat(MT.Phosphorus)              );
		CR.shaped(ST.make(Blocks.torch, 6, 0), DEF_NAC,  "X" ,  "S" , 'S', OP.stick.dat(ANY.Wood), 'X', OP.crushed.dat(MT.Phosphorus)           );
		CR.shaped(ST.make(Blocks.torch, 6, 0), DEF_NAC,  "X" ,  "S" , 'S', OP.stick.dat(ANY.Wood), 'X', OP.crushedPurified.dat(MT.Phosphorus)   );
		CR.shaped(ST.make(Blocks.torch, 6, 0), DEF_NAC,  "X" ,  "S" , 'S', OP.stick.dat(ANY.Wood), 'X', OP.crushedCentrifuged.dat(MT.Phosphorus));
		CR.shaped(ST.make(Blocks.torch, 4, 0), DEF_NAC,  "X" ,  "S" , 'S', OP.stick.dat(ANY.Wood), 'X', OD.itemResin                            );
		CR.shaped(ST.make(Blocks.torch, 1, 0), DEF_NAC,  "X" ,  "S" , 'S', OP.stick.dat(ANY.Wood), 'X', OD.itemGrassDry                         );

		CR.shaped(ST.make(Blocks.torch, 6, 0), DEF_NAC,  "WC",  "S ", 'S', OP.stick.dat(ANY.Wood), 'C', OD.container1000rubbertreesap, 'W', ST.make(Blocks.wool, 1, W));
		CR.shaped(ST.make(Blocks.torch, 6, 0), DEF_NAC,  "WC",  "S ", 'S', OP.stick.dat(ANY.Wood), 'C', OD.container1000spruceresin  , 'W', ST.make(Blocks.wool, 1, W));
		CR.shaped(ST.make(Blocks.torch, 6, 0), DEF_NAC,  "WC",  "S ", 'S', OP.stick.dat(ANY.Wood), 'C', OD.container1000maplesap     , 'W', ST.make(Blocks.wool, 1, W));
		CR.shaped(ST.make(Blocks.torch, 2, 0), DEF_NAC,  "WC",  "S ", 'S', OP.stick.dat(ANY.Wood), 'C', OD.container250maplesap      , 'W', ST.make(Blocks.wool, 1, W));
		CR.shaped(ST.make(Blocks.torch, 8, 0), DEF_NAC,  "WC",  "S ", 'S', OP.stick.dat(ANY.Wood), 'C', OD.container1000rainbowsap   , 'W', ST.make(Blocks.wool, 1, W));
		CR.shaped(ST.make(Blocks.torch, 4, 0), DEF_NAC,  "WC",  "S ", 'S', OP.stick.dat(ANY.Wood), 'C', OD.container250rainbowsap    , 'W', ST.make(Blocks.wool, 1, W));
		CR.shaped(ST.make(Blocks.torch, 6, 0), DEF_NAC,  "WC",  "S ", 'S', OP.stick.dat(ANY.Wood), 'C', OD.container1000creosote     , 'W', ST.make(Blocks.wool, 1, W));
		CR.shaped(ST.make(Blocks.torch, 2, 0), DEF_NAC,  "WC",  "S ", 'S', OP.stick.dat(ANY.Wood), 'C', OD.container250creosote      , 'W', ST.make(Blocks.wool, 1, W));

		CR.shaped(ST.make(Blocks.torch, 6, 0), DEF_NAC, "WCW", "WSW", 'S', OP.stick.dat(ANY.Wood), 'C', OD.container1000rubbertreesap, 'W', OD.itemString);
		CR.shaped(ST.make(Blocks.torch, 6, 0), DEF_NAC, "WCW", "WSW", 'S', OP.stick.dat(ANY.Wood), 'C', OD.container1000spruceresin  , 'W', OD.itemString);
		CR.shaped(ST.make(Blocks.torch, 6, 0), DEF_NAC, "WCW", "WSW", 'S', OP.stick.dat(ANY.Wood), 'C', OD.container1000maplesap     , 'W', OD.itemString);
		CR.shaped(ST.make(Blocks.torch, 2, 0), DEF_NAC, "WCW", "WSW", 'S', OP.stick.dat(ANY.Wood), 'C', OD.container250maplesap      , 'W', OD.itemString);
		CR.shaped(ST.make(Blocks.torch, 8, 0), DEF_NAC, "WCW", "WSW", 'S', OP.stick.dat(ANY.Wood), 'C', OD.container1000rainbowsap   , 'W', OD.itemString);
		CR.shaped(ST.make(Blocks.torch, 4, 0), DEF_NAC, "WCW", "WSW", 'S', OP.stick.dat(ANY.Wood), 'C', OD.container250rainbowsap    , 'W', OD.itemString);
		CR.shaped(ST.make(Blocks.torch, 6, 0), DEF_NAC, "WCW", "WSW", 'S', OP.stick.dat(ANY.Wood), 'C', OD.container1000creosote     , 'W', OD.itemString);
		CR.shaped(ST.make(Blocks.torch, 2, 0), DEF_NAC, "WCW", "WSW", 'S', OP.stick.dat(ANY.Wood), 'C', OD.container250creosote      , 'W', OD.itemString);

		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.disabledrecipes, "torchesFromCoal", F)) {
		CR.remove(ST.make(Items.coal, 1, 0), NI, NI, ST.make(Items.stick, 1, 0));
		CR.remove(ST.make(Items.coal, 1, 1), NI, NI, ST.make(Items.stick, 1, 0));
		} else {
		CR.shaped(ST.make(Blocks.torch, 4, 0), DEF_NAC, "X", "S", 'X', OP.ingot.dat(MT.Charcoal)                    , 'S', OP.stick.dat(ANY.Wood));
		CR.shaped(ST.make(Blocks.torch, 4, 0), DEF_NAC, "X", "S", 'X', OP.gem.dat(MT.Charcoal)                      , 'S', OP.stick.dat(ANY.Wood));
		CR.shaped(ST.make(Blocks.torch, 4, 0), DEF_NAC, "X", "S", 'X', OP.dust.dat(MT.Charcoal)                     , 'S', OP.stick.dat(ANY.Wood));

		CR.shaped(ST.make(Blocks.torch, 2, 0), DEF_NAC, "X", "S", 'X', OP.rockGt.dat(MT.Coal)                       , 'S', OP.stick.dat(ANY.Wood));
		CR.shaped(ST.make(Blocks.torch, 4, 0), DEF_NAC, "X", "S", 'X', OP.ingot.dat(MT.Coal)                        , 'S', OP.stick.dat(ANY.Wood));
		CR.shaped(ST.make(Blocks.torch, 4, 0), DEF_NAC, "X", "S", 'X', OP.gem.dat(MT.Coal)                          , 'S', OP.stick.dat(ANY.Wood));
		CR.shaped(ST.make(Blocks.torch, 4, 0), DEF_NAC, "X", "S", 'X', OP.dust.dat(MT.Coal)                         , 'S', OP.stick.dat(ANY.Wood));
		CR.shaped(ST.make(Blocks.torch, 4, 0), DEF_NAC, "X", "S", 'X', OP.crushed.dat(MT.Coal)                      , 'S', OP.stick.dat(ANY.Wood));
		CR.shaped(ST.make(Blocks.torch, 4, 0), DEF_NAC, "X", "S", 'X', OP.crushedPurified.dat(MT.Coal)              , 'S', OP.stick.dat(ANY.Wood));
		CR.shaped(ST.make(Blocks.torch, 4, 0), DEF_NAC, "X", "S", 'X', OP.crushedCentrifuged.dat(MT.Coal)           , 'S', OP.stick.dat(ANY.Wood));

		CR.shaped(ST.make(Blocks.torch, 4, 0), DEF_NAC, "X", "S", 'X', OP.rockGt.dat(MT.CoalCoke)                   , 'S', OP.stick.dat(ANY.Wood));
		CR.shaped(ST.make(Blocks.torch, 8, 0), DEF_NAC, "X", "S", 'X', OP.ingot.dat(MT.CoalCoke)                    , 'S', OP.stick.dat(ANY.Wood));
		CR.shaped(ST.make(Blocks.torch, 8, 0), DEF_NAC, "X", "S", 'X', OP.gem.dat(MT.CoalCoke)                      , 'S', OP.stick.dat(ANY.Wood));
		CR.shaped(ST.make(Blocks.torch, 8, 0), DEF_NAC, "X", "S", 'X', OP.dust.dat(MT.CoalCoke)                     , 'S', OP.stick.dat(ANY.Wood));
		CR.shaped(ST.make(Blocks.torch, 8, 0), DEF_NAC, "X", "S", 'X', OP.crushed.dat(MT.CoalCoke)                  , 'S', OP.stick.dat(ANY.Wood));
		CR.shaped(ST.make(Blocks.torch, 8, 0), DEF_NAC, "X", "S", 'X', OP.crushedPurified.dat(MT.CoalCoke)          , 'S', OP.stick.dat(ANY.Wood));
		CR.shaped(ST.make(Blocks.torch, 8, 0), DEF_NAC, "X", "S", 'X', OP.crushedCentrifuged.dat(MT.CoalCoke)       , 'S', OP.stick.dat(ANY.Wood));

		CR.shaped(ST.make(Blocks.torch, 1, 0), DEF_NAC, "X", "S", 'X', OP.rockGt.dat(MT.Lignite)                    , 'S', OP.stick.dat(ANY.Wood));
		CR.shaped(ST.make(Blocks.torch, 2, 0), DEF_NAC, "X", "S", 'X', OP.ingot.dat(MT.Lignite)                     , 'S', OP.stick.dat(ANY.Wood));
		CR.shaped(ST.make(Blocks.torch, 2, 0), DEF_NAC, "X", "S", 'X', OP.gem.dat(MT.Lignite)                       , 'S', OP.stick.dat(ANY.Wood));
		CR.shaped(ST.make(Blocks.torch, 2, 0), DEF_NAC, "X", "S", 'X', OP.dust.dat(MT.Lignite)                      , 'S', OP.stick.dat(ANY.Wood));
		CR.shaped(ST.make(Blocks.torch, 2, 0), DEF_NAC, "X", "S", 'X', OP.crushed.dat(MT.Lignite)                   , 'S', OP.stick.dat(ANY.Wood));
		CR.shaped(ST.make(Blocks.torch, 2, 0), DEF_NAC, "X", "S", 'X', OP.crushedPurified.dat(MT.Lignite)           , 'S', OP.stick.dat(ANY.Wood));
		CR.shaped(ST.make(Blocks.torch, 2, 0), DEF_NAC, "X", "S", 'X', OP.crushedCentrifuged.dat(MT.Lignite)        , 'S', OP.stick.dat(ANY.Wood));

		CR.shaped(ST.make(Blocks.torch, 2, 0), DEF_NAC, "X", "S", 'X', OP.rockGt.dat(MT.LigniteCoke)                , 'S', OP.stick.dat(ANY.Wood));
		CR.shaped(ST.make(Blocks.torch, 4, 0), DEF_NAC, "X", "S", 'X', OP.ingot.dat(MT.LigniteCoke)                 , 'S', OP.stick.dat(ANY.Wood));
		CR.shaped(ST.make(Blocks.torch, 4, 0), DEF_NAC, "X", "S", 'X', OP.gem.dat(MT.LigniteCoke)                   , 'S', OP.stick.dat(ANY.Wood));
		CR.shaped(ST.make(Blocks.torch, 4, 0), DEF_NAC, "X", "S", 'X', OP.dust.dat(MT.LigniteCoke)                  , 'S', OP.stick.dat(ANY.Wood));
		CR.shaped(ST.make(Blocks.torch, 4, 0), DEF_NAC, "X", "S", 'X', OP.crushed.dat(MT.LigniteCoke)               , 'S', OP.stick.dat(ANY.Wood));
		CR.shaped(ST.make(Blocks.torch, 4, 0), DEF_NAC, "X", "S", 'X', OP.crushedPurified.dat(MT.LigniteCoke)       , 'S', OP.stick.dat(ANY.Wood));
		CR.shaped(ST.make(Blocks.torch, 4, 0), DEF_NAC, "X", "S", 'X', OP.crushedCentrifuged.dat(MT.LigniteCoke)    , 'S', OP.stick.dat(ANY.Wood));
		}

		CR.shaped(ST.make(Items.coal   , 1, 0), DEF, "  ", " X", 'X', OP.ingot.dat(MT.Coal    ));
		CR.shaped(ST.make(Items.coal   , 1, 1), DEF, "  ", " X", 'X', OP.ingot.dat(MT.Charcoal));
		CR.shaped(OP.ingot.mat(MT.Coal    , 1), DEF, "  ", " X", 'X', ST.make(Items.coal, 1, 0));
		CR.shaped(OP.ingot.mat(MT.Charcoal, 1), DEF, "  ", " X", 'X', ST.make(Items.coal, 1, 1));
		RM.generify(OP.ingot.mat(MT.Coal    , 1), ST.make(Items.coal, 1, 0));
		RM.generify(OP.ingot.mat(MT.Charcoal, 1), ST.make(Items.coal, 1, 1));
		RM.generify(ST.make(Items.coal, 1, 0), OP.ingot.mat(MT.Coal    , 1));
		RM.generify(ST.make(Items.coal, 1, 1), OP.ingot.mat(MT.Charcoal, 1));


		CR.shaped(ST.make(Items.lead                    , 1, 0), DEF_NAC_MIR                    , " SS", " GS", "S  ", 'S', OD.itemString, 'G', OD.itemGlue);
		CR.shaped(ST.make(Items.lead                    , 1, 0), DEF_NAC_MIR                    , " SS", " GS", "S  ", 'S', OD.itemString, 'G', OD.slimeball);

		CR.shaped(ST.make(Blocks.lever                  , 1, 0), DEF | DEL_OTHER_SHAPED_RECIPES ,         "W" ,  "S" , 'W', OP.stick.dat(ANY.Wood), 'S', OP.cobblestone);
		CR.shaped(ST.make(Blocks.redstone_torch         , 1, 0), DEF | DEL_OTHER_SHAPED_RECIPES ,         "D" ,  "W" , 'W', OP.stick.dat(ANY.Wood), 'D', OD.itemRedstone);
		CR.shaped(ST.make(Blocks.redstone_torch         , 1, 0), DEF                            ,  "D" ,  "D" ,  "W" , 'W', OP.stick.dat(ANY.Wood), 'D', OP.gemFlawed.dat(MT.Redstone));
		CR.shaped(ST.make(Blocks.redstone_torch         , 2, 0), DEF                            ,  "D" ,  "W" ,  "W" , 'W', OP.stick.dat(ANY.Wood), 'D', OP.gemFlawless.dat(MT.Redstone));
		CR.shaped(ST.make(Items.repeater                , 1, 0), DEF | DEL_OTHER_SHAPED_RECIPES , "D D", "WDW", "SSS", 'W', OP.stick.dat(ANY.Wood), 'D', OD.itemRedstone, 'S', OP.stoneSmooth);
		CR.shaped(ST.make(Items.repeater                , 1, 0), DEF                            ,        "TDT", "SSS", 'S', OP.stoneSmooth, 'T', OD.craftingRedstoneTorch, 'D', OD.itemRedstone);
		CR.shaped(ST.make(Items.comparator              , 1, 0), DEF | DEL_OTHER_SHAPED_RECIPES , " T ", "TQT", "SSS", 'S', OP.stoneSmooth, 'T', OD.craftingRedstoneTorch, 'Q', OD.craftingQuartz);
		CR.shaped(ST.make(Blocks.piston                 , 1, 0), DEF | DEL_OTHER_SHAPED_RECIPES , "WWW", "CBC", "CRC", 'W', OD.plankAnyWood, 'C', OP.stoneCobble, 'B', OD.craftingPistonIngot, 'R', OD.itemRedstone);
		CR.shaped(ST.make(Blocks.sticky_piston          , 1, 0), DEF | DEL_OTHER_SHAPED_RECIPES ,  "G" ,  "P"        , 'G', OD.craftingPistonGlue, 'P', Blocks.piston);
		CR.shaped(ST.make(Items.bow                     , 1, 0), DEF_MIR                        , " WS", "W S", " WS", 'S', OD.itemString, 'W', OP.stick.dat(ANY.Wood));
		CR.shaped(ST.make(Blocks.dropper                , 1, 0), DEF | DEL_OTHER_SHAPED_RECIPES , "CCC", "C C", "CRC", 'C', OP.stoneCobble, 'R', OD.itemRedstone);
		CR.shaped(ST.make(Blocks.dispenser              , 1, 0), DEF | DEL_OTHER_SHAPED_RECIPES , "CCC", "CBC", "CRC", 'C', OP.stoneCobble, 'R', OD.itemRedstone, 'B', ST.make(Items.bow, 1, 0));
		CR.shaped(ST.make(Blocks.dispenser              , 1, 0), DEF_MIR                        , " WS", "WDS", " WS", 'S', OD.itemString, 'W', OP.stick.dat(ANY.Wood), 'D', Blocks.dropper);
		CR.shaped(ST.make(Blocks.dispenser              , 1, 0), DEF                            ,  "B" ,  "D"        , 'B', ST.make(Items.bow, 1, 0), 'D', Blocks.dropper);
		CR.shaped(ST.make(Blocks.noteblock              , 1, 0), DEF | DEL_OTHER_SHAPED_RECIPES , "WWW", "WRW", "WWW", 'W', OD.plankAnyWood, 'R', OD.itemRedstone);
		CR.shaped(ST.make(Blocks.redstone_lamp          , 1, 0), DEF | DEL_OTHER_SHAPED_RECIPES , " R ", "RGR", " R ", 'G', Blocks.glowstone, 'R', OD.itemRedstone);

		CR.shaped(ST.make(Blocks.tnt                    , 1, 0), DEF                            , "GSG", "SGS", "GSG", 'G', OP.dust.dat(MT.Gunpowder), 'S', OP.dust.dat(ANY.SiO2));

		CR.shaped(ST.make(Items.minecart                , 1, 0), DEF | DEL_OTHER_SHAPED_RECIPES , " h ", "PwP", "WPW", 'P', OP.plate.dat(ANY.Iron), 'W', OP.minecartWheels.dat(ANY.Iron));

		CR.shaped(ST.make(Items.chest_minecart          , 1, 0), DEF_REV | DEL_OTHER_SHAPED_RECIPES, "X", "C", 'C', ST.make(Items.minecart, 1, 0), 'X', Blocks.chest);
		CR.shaped(ST.make(Items.furnace_minecart        , 1, 0), DEF_REV | DEL_OTHER_SHAPED_RECIPES, "X", "C", 'C', ST.make(Items.minecart, 1, 0), 'X', OD.craftingFurnace);
		CR.shaped(ST.make(Items.hopper_minecart         , 1, 0), DEF_REV | DEL_OTHER_SHAPED_RECIPES, "X", "C", 'C', ST.make(Items.minecart, 1, 0), 'X', Blocks.hopper);
		CR.shaped(ST.make(Items.tnt_minecart            , 1, 0), DEF_REV | DEL_OTHER_SHAPED_RECIPES, "X", "C", 'C', ST.make(Items.minecart, 1, 0), 'X', Blocks.tnt);

		RM.boxunbox(ST.make(Items.minecart, 1, 0), ST.make(Items.chest_minecart  , 1, 0), ST.make(Blocks.chest, 1, 0));
		RM.boxunbox(ST.make(Items.minecart, 1, 0), ST.make(Items.furnace_minecart, 1, 0), ST.make(Blocks.furnace, 1, 0));
		RM.boxunbox(ST.make(Items.minecart, 1, 0), ST.make(Items.hopper_minecart , 1, 0), ST.make(Blocks.hopper, 1, 0));
		RM.boxunbox(ST.make(Items.minecart, 1, 0), ST.make(Items.tnt_minecart    , 1, 0), ST.make(Blocks.tnt, 1, 0));


		RM.boxunbox(OP.stick.mat(MT.Wood, 1), ST.make(Items.wooden_sword     , 1, 0), OP.toolHeadSword  .mat(MT.Wood   , 1));
		RM.boxunbox(OP.stick.mat(MT.Wood, 1), ST.make(Items.stone_sword      , 1, 0), OP.toolHeadSword  .mat(MT.Stone  , 1));
		RM.boxunbox(OP.stick.mat(MT.Wood, 1), ST.make(Items.iron_sword       , 1, 0), OP.toolHeadSword  .mat(MT.Fe     , 1));
		RM.boxunbox(OP.stick.mat(MT.Wood, 1), ST.make(Items.golden_sword     , 1, 0), OP.toolHeadSword  .mat(MT.Au     , 1));
		RM.boxunbox(OP.stick.mat(MT.Wood, 1), ST.make(Items.diamond_sword    , 1, 0), OP.toolHeadSword  .mat(MT.Diamond, 1));
		RM.boxunbox(OP.stick.mat(MT.Wood, 1), IL.Tool_Sword_Bronze  .getUndamaged(1), OP.toolHeadSword  .mat(MT.Bronze , 1));
		RM.boxunbox(OP.stick.mat(MT.Wood, 1), IL.Tool_Sword_Steel   .getUndamaged(1), OP.toolHeadSword  .mat(MT.Steel  , 1));
		RM.boxunbox(OP.stick.mat(MT.Wood, 2), ST.make(Items.wooden_pickaxe   , 1, 0), OP.toolHeadPickaxe.mat(MT.Wood   , 1));
		RM.boxunbox(OP.stick.mat(MT.Wood, 2), ST.make(Items.stone_pickaxe    , 1, 0), OP.toolHeadPickaxe.mat(MT.Stone  , 1));
		RM.boxunbox(OP.stick.mat(MT.Wood, 2), ST.make(Items.iron_pickaxe     , 1, 0), OP.toolHeadPickaxe.mat(MT.Fe     , 1));
		RM.boxunbox(OP.stick.mat(MT.Wood, 2), ST.make(Items.golden_pickaxe   , 1, 0), OP.toolHeadPickaxe.mat(MT.Au     , 1));
		RM.boxunbox(OP.stick.mat(MT.Wood, 2), ST.make(Items.diamond_pickaxe  , 1, 0), OP.toolHeadPickaxe.mat(MT.Diamond, 1));
		RM.boxunbox(OP.stick.mat(MT.Wood, 2), IL.Tool_Pickaxe_Bronze.getUndamaged(1), OP.toolHeadPickaxe.mat(MT.Bronze , 1));
		RM.boxunbox(OP.stick.mat(MT.Wood, 2), IL.Tool_Pickaxe_Steel .getUndamaged(1), OP.toolHeadPickaxe.mat(MT.Steel  , 1));
		RM.boxunbox(OP.stick.mat(MT.Wood, 2), ST.make(Items.wooden_shovel    , 1, 0), OP.toolHeadShovel .mat(MT.Wood   , 1));
		RM.boxunbox(OP.stick.mat(MT.Wood, 2), ST.make(Items.stone_shovel     , 1, 0), OP.toolHeadShovel .mat(MT.Stone  , 1));
		RM.boxunbox(OP.stick.mat(MT.Wood, 2), ST.make(Items.iron_shovel      , 1, 0), OP.toolHeadShovel .mat(MT.Fe     , 1));
		RM.boxunbox(OP.stick.mat(MT.Wood, 2), ST.make(Items.golden_shovel    , 1, 0), OP.toolHeadShovel .mat(MT.Au     , 1));
		RM.boxunbox(OP.stick.mat(MT.Wood, 2), ST.make(Items.diamond_shovel   , 1, 0), OP.toolHeadShovel .mat(MT.Diamond, 1));
		RM.boxunbox(OP.stick.mat(MT.Wood, 2), IL.Tool_Shovel_Bronze .getUndamaged(1), OP.toolHeadShovel .mat(MT.Bronze , 1));
		RM.boxunbox(OP.stick.mat(MT.Wood, 2), IL.Tool_Shovel_Steel  .getUndamaged(1), OP.toolHeadShovel .mat(MT.Steel  , 1));
		RM.boxunbox(OP.stick.mat(MT.Wood, 2), ST.make(Items.wooden_axe       , 1, 0), OP.toolHeadAxe    .mat(MT.Wood   , 1));
		RM.boxunbox(OP.stick.mat(MT.Wood, 2), ST.make(Items.stone_axe        , 1, 0), OP.toolHeadAxe    .mat(MT.Stone  , 1));
		RM.boxunbox(OP.stick.mat(MT.Wood, 2), ST.make(Items.iron_axe         , 1, 0), OP.toolHeadAxe    .mat(MT.Fe     , 1));
		RM.boxunbox(OP.stick.mat(MT.Wood, 2), ST.make(Items.golden_axe       , 1, 0), OP.toolHeadAxe    .mat(MT.Au     , 1));
		RM.boxunbox(OP.stick.mat(MT.Wood, 2), ST.make(Items.diamond_axe      , 1, 0), OP.toolHeadAxe    .mat(MT.Diamond, 1));
		RM.boxunbox(OP.stick.mat(MT.Wood, 2), IL.Tool_Axe_Bronze    .getUndamaged(1), OP.toolHeadAxe    .mat(MT.Bronze , 1));
		RM.boxunbox(OP.stick.mat(MT.Wood, 2), IL.Tool_Axe_Steel     .getUndamaged(1), OP.toolHeadAxe    .mat(MT.Steel  , 1));
		RM.boxunbox(OP.stick.mat(MT.Wood, 2), ST.make(Items.wooden_hoe       , 1, 0), OP.toolHeadHoe    .mat(MT.Wood   , 1));
		RM.boxunbox(OP.stick.mat(MT.Wood, 2), ST.make(Items.stone_hoe        , 1, 0), OP.toolHeadHoe    .mat(MT.Stone  , 1));
		RM.boxunbox(OP.stick.mat(MT.Wood, 2), ST.make(Items.iron_hoe         , 1, 0), OP.toolHeadHoe    .mat(MT.Fe     , 1));
		RM.boxunbox(OP.stick.mat(MT.Wood, 2), ST.make(Items.golden_hoe       , 1, 0), OP.toolHeadHoe    .mat(MT.Au     , 1));
		RM.boxunbox(OP.stick.mat(MT.Wood, 2), ST.make(Items.diamond_hoe      , 1, 0), OP.toolHeadHoe    .mat(MT.Diamond, 1));
		RM.boxunbox(OP.stick.mat(MT.Wood, 2), IL.Tool_Hoe_Bronze    .getUndamaged(1), OP.toolHeadHoe    .mat(MT.Bronze , 1));
		RM.boxunbox(OP.stick.mat(MT.Wood, 2), IL.Tool_Hoe_Steel     .getUndamaged(1), OP.toolHeadHoe    .mat(MT.Steel  , 1));


		CR.shaped(ST.make(Items.chainmail_helmet        , 1, 0), DEF_REV | DEL_OTHER_SHAPED_RECIPES, "RRR", "RhR"       , 'R', OP.ring.dat(ANY.Steel));
		CR.shaped(ST.make(Items.chainmail_chestplate    , 1, 0), DEF_REV | DEL_OTHER_SHAPED_RECIPES, "RhR", "RRR", "RRR", 'R', OP.ring.dat(ANY.Steel));
		CR.shaped(ST.make(Items.chainmail_leggings      , 1, 0), DEF_REV | DEL_OTHER_SHAPED_RECIPES, "RRR", "RhR", "R R", 'R', OP.ring.dat(ANY.Steel));
		CR.shaped(ST.make(Items.chainmail_boots         , 1, 0), DEF_REV | DEL_OTHER_SHAPED_RECIPES, "R R", "RhR"       , 'R', OP.ring.dat(ANY.Steel));

		for (int i = 1; i < 16; i++)
		CR.shapeless(ST.make(Blocks.wool, 1,  i), DEF_NAC, new Object[] {ST.make(Blocks.wool, 1, 0), DYE_OREDICTS[15-i]});

		CR.delate(IL.Food_Bread.get(1));
		CR.delate(ST.make(Items.arrow, 1, 0));
		CR.delate(ST.make(Items.cookie, 1, 0));
		CR.delate(ST.make(Items.golden_apple, 1, 0));
		CR.delate(ST.make(Items.golden_apple, 1, 1));
		CR.delate(ST.make(Items.golden_carrot, 1, 0));

		CR.shapeless(ST.make(Items.arrow, 1, 0), DEF_NCC, new Object[] {OD.itemFlint, OP.arrowGtWood.dat(MT.Empty)});

		CR.shaped(ST.make(Blocks.stained_glass, 8, 0), DEF, "GGG", "GDG", "GGG", 'G', ST.make(Blocks.glass, 1, 0), 'D', DYE_OREDICTS[15]);
		CR.shaped(ST.make(Items.bowl, 1, 0), DEF | DEL_OTHER_SHAPED_RECIPES, "k", "X", 'X', OD.plankWood);
		CR.shaped(ST.make(Items.speckled_melon, 1, 0), DEF_NAC | DEL_OTHER_SHAPED_RECIPES, "GGG", "GMG", "GGG", 'M', "cropMelon", 'G', OP.nugget.dat(MT.Au));

		CR.remove(ST.make(Items.dye, 1, 1), ST.make(Items.dye, 1, 4));
		CR.remove(ST.make(Items.dye, 1, 2), ST.make(Items.dye, 1, 4));
		CR.remove(ST.make(Items.dye, 1, 5), ST.make(Items.dye, 1, 9));
		CR.remove(ST.make(Items.dye, 1, 1), ST.make(Items.dye, 1,11));
		CR.remove(ST.make(Items.dye, 1, 1), ST.make(Items.dye, 1,15));
		CR.remove(ST.make(Items.dye, 1, 2), ST.make(Items.dye, 1,15));
		CR.remove(ST.make(Items.dye, 1, 4), ST.make(Items.dye, 1,15));
		CR.remove(ST.make(Items.dye, 1, 8), ST.make(Items.dye, 1,15));
		CR.remove(ST.make(Items.dye, 1, 0), ST.make(Items.dye, 1,15));
		CR.remove(ST.make(Items.dye, 1, 0), ST.make(Items.dye, 1, 0), ST.make(Items.dye, 1,15));
		CR.remove(ST.make(Items.dye, 1, 1), ST.make(Items.dye, 1, 4), ST.make(Items.dye, 1, 9));
		CR.remove(ST.make(Items.dye, 1, 1), ST.make(Items.dye, 1, 1), ST.make(Items.dye, 1, 4), ST.make(Items.dye, 1,15));

		CR.shapeless(ST.make(Items.dye, 2, DYE_INDEX_Purple     ), DEF_NAC, new Object[] {DYE_OREDICTS_MIXABLE[DYE_INDEX_Red    ], DYE_OREDICTS_MIXABLE[DYE_INDEX_Blue  ]});
		CR.shapeless(ST.make(Items.dye, 2, DYE_INDEX_Cyan       ), DEF_NAC, new Object[] {DYE_OREDICTS_MIXABLE[DYE_INDEX_Green  ], DYE_OREDICTS_MIXABLE[DYE_INDEX_Blue  ]});
		CR.shapeless(ST.make(Items.dye, 2, DYE_INDEX_Pink       ), DEF_NAC, new Object[] {DYE_OREDICTS_MIXABLE[DYE_INDEX_Red    ], DYE_OREDICTS_MIXABLE[DYE_INDEX_White ]});
		CR.shapeless(ST.make(Items.dye, 2, DYE_INDEX_Lime       ), DEF_NAC, new Object[] {DYE_OREDICTS_MIXABLE[DYE_INDEX_Green  ], DYE_OREDICTS_MIXABLE[DYE_INDEX_White ]});
		CR.shapeless(ST.make(Items.dye, 2, DYE_INDEX_LightBlue  ), DEF_NAC, new Object[] {DYE_OREDICTS_MIXABLE[DYE_INDEX_Blue   ], DYE_OREDICTS_MIXABLE[DYE_INDEX_White ]});
		CR.shapeless(ST.make(Items.dye, 2, DYE_INDEX_Magenta    ), DEF_NAC, new Object[] {DYE_OREDICTS_MIXABLE[DYE_INDEX_Purple ], DYE_OREDICTS_MIXABLE[DYE_INDEX_Pink  ]});
		CR.shapeless(ST.make(Items.dye, 2, DYE_INDEX_Orange     ), DEF_NAC, new Object[] {DYE_OREDICTS_MIXABLE[DYE_INDEX_Red    ], DYE_OREDICTS_MIXABLE[DYE_INDEX_Yellow]});
		CR.shapeless(ST.make(Items.dye, 2, DYE_INDEX_Gray       ), DEF_NAC, new Object[] {DYE_OREDICTS_MIXABLE[DYE_INDEX_Black  ], DYE_OREDICTS_MIXABLE[DYE_INDEX_White ]});
		CR.shapeless(ST.make(Items.dye, 2, DYE_INDEX_LightGray  ), DEF_NAC, new Object[] {DYE_OREDICTS_MIXABLE[DYE_INDEX_Gray   ], DYE_OREDICTS_MIXABLE[DYE_INDEX_White ]});
		CR.shapeless(ST.make(Items.dye, 3, DYE_INDEX_LightGray  ), DEF_NAC, new Object[] {DYE_OREDICTS_MIXABLE[DYE_INDEX_Black  ], DYE_OREDICTS_MIXABLE[DYE_INDEX_White ], DYE_OREDICTS_MIXABLE[DYE_INDEX_White]});
		CR.shapeless(ST.make(Items.dye, 3, DYE_INDEX_Magenta    ), DEF_NAC, new Object[] {DYE_OREDICTS_MIXABLE[DYE_INDEX_Blue   ], DYE_OREDICTS_MIXABLE[DYE_INDEX_Red   ], DYE_OREDICTS_MIXABLE[DYE_INDEX_Pink]});
		CR.shapeless(ST.make(Items.dye, 4, DYE_INDEX_Magenta    ), DEF_NAC, new Object[] {DYE_OREDICTS_MIXABLE[DYE_INDEX_Blue   ], DYE_OREDICTS_MIXABLE[DYE_INDEX_Red   ], DYE_OREDICTS_MIXABLE[DYE_INDEX_Red], DYE_OREDICTS_MIXABLE[DYE_INDEX_White]});

		if (!ConfigsGT.RECIPES.get(ConfigCategories.Recipes.storageblockcrafting, "tile.glowstone", F)) CR.remove(ST.make(Items.glowstone_dust, 1, 0), ST.make(Items.glowstone_dust, 1, 0), NI, ST.make(Items.glowstone_dust, 1, 0), ST.make(Items.glowstone_dust, 1, 0));

		CR.shaped(OP.toolHeadArrow.mat(MT.Flint, 1), DEF, "fX", 'X', "itemFlint");
		RM.Sharpening   .addRecipe1(T, 16,  64, ST.make(Items.flint, 1, W), OP.toolHeadArrow.mat(MT.Flint, 1));
		RM.Sharpening   .addRecipe1(T, 16,  64, ST.make(Blocks.glass, 1, W), OP.lens.mat(MT.Glass, 1));

		RM.Lathe        .addRecipe1(T, 16,  16, ST.make(Blocks.glass, 1, W), OP.lens.mat(MT.Glass, 1), OP.dustSmall.mat(MT.Glass, 1));
		RM.Lathe        .addRecipe1(T, 16,  16, ST.make(Blocks.stone, 1, W), OP.stickLong.mat(MT.Stone, 1));
		RM.Lathe        .addRecipe1(T, 16,  32, IL.Module_Stone_Generator.get(  0), OP.stickLong.mat(MT.Stone, 1));

		RM.Hammer       .addRecipe1(T, 16,  16, ST.make(Blocks.stonebrick               , 1, 0), ST.make(Blocks.stonebrick, 1, 2));
		RM.Hammer       .addRecipe1(T, 16,  16, ST.make(Blocks.stone                    , 1, 0), ST.make(Blocks.cobblestone, 1, 0));
		RM.Hammer       .addRecipe1(T, 16,  32, IL.Module_Stone_Generator.get(               0), ST.make(Blocks.cobblestone, 1, 0));
		RM.Hammer       .addRecipe1(T, 16,  16, ST.make(Blocks.cobblestone              , 1, 0), ST.make(Blocks.gravel, 1, 0));
		RM.Hammer       .addRecipe1(T, 16,  16, ST.make(Blocks.sandstone                , 1, W), ST.make(Blocks.sand, 1, 0));
		RM.Hammer       .addRecipe1(T, 16,  16, ST.make(Blocks.ice                      , 1, W), OM.dust(MT.Ice));
		RM.Hammer       .addRecipe1(T, 16,  16, ST.make(Blocks.packed_ice               , 1, W), OM.dust(MT.Ice, 2*U));
		RM.Hammer       .addRecipe1(T, 16,  16, ST.make(Blocks.hardened_clay            , 1, W), OM.dust(MT.Clay, 2*U));
		RM.Hammer       .addRecipe1(T, 16,  16, ST.make(Blocks.stained_hardened_clay    , 1, W), OM.dust(MT.Clay, 2*U));
		RM.Hammer       .addRecipe1(T, 16,  16, ST.make(Blocks.stained_glass            , 1, W), OM.dust(MT.Glass));
		RM.Hammer       .addRecipe1(T, 16,  16, ST.make(Blocks.glass                    , 1, W), OM.dust(MT.Glass));
		RM.Hammer       .addRecipe1(T, 16,  16, ST.make(Blocks.stained_glass_pane       , 1, W), OM.dust(MT.Glass, 3*U8));
		RM.Hammer       .addRecipe1(T, 16,  16, ST.make(Blocks.glass_pane               , 1, W), OM.dust(MT.Glass, 3*U8));
		RM.Hammer       .addRecipe1(T, 16,  16, ST.make(Blocks.quartz_block             , 1, W), OP.gem.mat(MT.NetherQuartz, 4));
		RM.Hammer       .addRecipe1(T, 16,  16, ST.make(Blocks.double_stone_slab        , 1, 7), OP.gem.mat(MT.NetherQuartz, 4));
		RM.Hammer       .addRecipe1(T, 16,  16, ST.make(Blocks.quartz_stairs            , 1, W), OP.gem.mat(MT.NetherQuartz, 6));
		RM.Hammer       .addRecipe1(T, 16,  16, ST.make(Blocks.stone_slab               , 1, 7), OP.gem.mat(MT.NetherQuartz, 2));
		RM.Hammer       .addRecipe1(T, 16,  16, ST.make(Blocks.brick_block              , 1, W), ST.make(Items.brick, 3, 0));
		RM.Hammer       .addRecipe1(T, 16,  16, ST.make(Blocks.double_stone_slab        , 1, 4), ST.make(Items.brick, 3, 0));
		RM.Hammer       .addRecipe1(T, 16,  16, ST.make(Blocks.brick_stairs             , 1, W), ST.make(Items.brick, 5, 0));
		RM.Hammer       .addRecipe1(T, 16,  16, ST.make(Blocks.stone_slab               , 1, 4), ST.make(Items.brick, 1, 0));

		RM.Crusher      .addRecipe1(T, 16,  32, ST.make(Blocks.stonebrick               , 1, 0), ST.make(Blocks.stonebrick, 1, 2));
		RM.Crusher      .addRecipe1(T, 16,  32, ST.make(Blocks.stonebrick               , 1, 1), ST.make(Blocks.cobblestone, 1, 0));
		RM.Crusher      .addRecipe1(T, 16,  32, ST.make(Blocks.stonebrick               , 1, 2), ST.make(Blocks.cobblestone, 1, 0));
		RM.Crusher      .addRecipe1(T, 16,  32, ST.make(Blocks.stonebrick               , 1, 3), ST.make(Blocks.cobblestone, 1, 0));
		RM.Crusher      .addRecipe1(T, 16,  16, ST.make(Blocks.stone                    , 1, 0), ST.make(Blocks.cobblestone, 1, 0));
		RM.Crusher      .addRecipe1(T, 16,  32, IL.Module_Stone_Generator.get(               0), ST.make(Blocks.cobblestone, 1, 0));
		RM.Crusher      .addRecipe1(T, 16,  32, ST.make(Blocks.cobblestone              , 1, 0), ST.make(Blocks.gravel, 1, 0));
		RM.Crusher      .addRecipe1(T, 16,  16, ST.make(Blocks.sandstone                , 1, W), ST.make(Blocks.sand, 1, 0));
		RM.Crusher      .addRecipe1(T, 16,  32, ST.make(Blocks.quartz_block             , 1, W), OP.gem.mat(MT.NetherQuartz, 4));
		RM.Crusher      .addRecipe1(T, 16,  16, ST.make(Blocks.double_stone_slab        , 1, 7), OP.gem.mat(MT.NetherQuartz, 4));
		RM.Crusher      .addRecipe1(T, 16,  48, ST.make(Blocks.quartz_stairs            , 1, W), OP.gem.mat(MT.NetherQuartz, 6));
		RM.Crusher      .addRecipe1(T, 16,  16, ST.make(Blocks.stone_slab               , 1, 7), OP.gem.mat(MT.NetherQuartz, 2));
		RM.Crusher      .addRecipe1(T, 16,  64, new long[] {10000, 9000, 8000, 7000}, ST.make(Blocks.brick_block        , 1, W), ST.make(Items.brick, 1, 0), ST.make(Items.brick, 1, 0), ST.make(Items.brick, 1, 0), ST.make(Items.brick, 1, 0));
		RM.Crusher      .addRecipe1(T, 16,  64, new long[] {10000, 9000, 8000, 7000}, ST.make(Blocks.double_stone_slab  , 1, 4), ST.make(Items.brick, 1, 0), ST.make(Items.brick, 1, 0), ST.make(Items.brick, 1, 0), ST.make(Items.brick, 1, 0));
		RM.Crusher      .addRecipe1(T, 16,  64, new long[] {10000, 9000, 8000, 7000}, ST.make(Blocks.brick_stairs       , 1, W), ST.make(Items.brick, 2, 0), ST.make(Items.brick, 2, 0), ST.make(Items.brick, 1, 0), ST.make(Items.brick, 1, 0));
		RM.Crusher      .addRecipe1(T, 16,  32, new long[] {10000, 8000            }, ST.make(Blocks.stone_slab         , 1, 4), ST.make(Items.brick, 1, 0), ST.make(Items.brick, 1, 0));

		for (OreDictMaterial tMaterial : ANY.Wood.mToThis) {
		RM.sawing(16,  16, F,   3, OP.stick.mat(tMaterial, 1), OP.bolt.mat(tMaterial, 4));
		RM.sawing(16,  16, F,   1, OP.stickLong.mat(tMaterial, 1), OP.stick.mat(tMaterial, 2));
		}
		for (byte i = 0; i < 16; i++) {
		RM.sawing(16,  32, F,  50, ST.make(Blocks.stained_glass             , 3, i), ST.make(Blocks.stained_glass_pane, 8, i));
		RM.sawing(16,  32, F,  50, ST.make(Blocks.wool                      , 2, i), ST.make(Blocks.carpet, 3, i));
		}
		RM.sawing(16,  32, F,  50, ST.make(Blocks.glass                     , 3, 0), ST.make(Blocks.glass_pane, 8, 0));
		RM.sawing(16,  16, F, 100, ST.make(Blocks.stone                     , 1, 0), ST.make(Blocks.stone_slab, 2, 0));
		RM.sawing(16,  16, F, 100, IL.Module_Stone_Generator.get(                0), ST.make(Blocks.stone_slab, 1, 0));
		RM.sawing(16,  16, F, 100, ST.make(Blocks.sandstone                 , 1, 0), ST.make(Blocks.stone_slab, 2, 1));
		RM.sawing(16,  16, F, 100, ST.make(Blocks.cobblestone               , 1, 0), ST.make(Blocks.stone_slab, 2, 3));
		RM.sawing(16,  16, F, 100, ST.make(Blocks.brick_block               , 1, 0), ST.make(Blocks.stone_slab, 2, 4));
		RM.sawing(16,  16, F, 100, ST.make(Blocks.stonebrick                , 1, 0), ST.make(Blocks.stone_slab, 2, 5));
		RM.sawing(16,  16, F, 100, ST.make(Blocks.nether_brick              , 1, 0), ST.make(Blocks.stone_slab, 2, 6));
		RM.sawing(16,  16, F, 100, ST.make(Blocks.quartz_block              , 1, W), ST.make(Blocks.stone_slab, 2, 7));
		RM.sawing(16,  64, F,  25, ST.make(Blocks.glowstone                 , 1, W), OP.plate.mat(MT.Glowstone, 4));
		RM.sawing(16,  16, F, 100, ST.make(Blocks.wooden_button             , 1, W), IL.Plank.get(1));
		RM.sawing(16,  32, F, 100, ST.make(Blocks.wooden_pressure_plate     , 1, W), IL.Plank.get(2));
		RM.sawing(16,  32, F, 100, ST.make(Items.sign                       , 1, W), IL.Plank.get(2), OM.dust(MT.Wood, OP.stick.mAmount / 3));
		RM.sawing(16,  32, F, 100, ST.make(Items.wooden_door                , 1, W), IL.Plank.get(2));
		RM.sawing(16,  32, F, 100, ST.make(Blocks.fence_gate                , 1, W), IL.Plank.get(2), OM.dust(MT.Wood, OP.stick.mAmount * 4));
		RM.sawing(16,  48, F, 100, ST.make(Blocks.trapdoor                  , 1, W), IL.Plank.get(3));
		RM.sawing(16,  48, F, 100, ST.make(Items.bed                        , 1, W), IL.Plank.get(3), ST.make(Blocks.wool, 3, 0));
		RM.sawing(16,  64, F, 100, ST.make(Blocks.crafting_table            , 1, W), IL.Plank.get(4));
		RM.sawing(16,  80, F, 100, ST.make(Items.boat                       , 1, W), IL.Plank.get(5));
		RM.sawing(16,  96, F, 100, ST.make(Blocks.bookshelf                 , 1, W), IL.Plank.get(6), ST.make(Items.book, 3, 0));
		RM.sawing(16, 128, F, 100, ST.make(Blocks.chest                     , 1, W), IL.Plank.get(8));
		RM.sawing(16, 128, F, 100, ST.make(Blocks.trapped_chest             , 1, W), IL.Plank.get(8), ST.make(Blocks.tripwire_hook, 1, 0));
		RM.sawing(16, 128, F, 100, ST.make(Blocks.noteblock                 , 1, W), IL.Plank.get(8), OP.dust.mat(MT.Redstone, 1));
		RM.sawing(16, 128, F, 100, ST.make(Blocks.jukebox                   , 1, W), IL.Plank.get(8), OP.gem.mat(MT.Diamond, 1));
		RM.sawing(16,  16, T,  50, ST.make(Blocks.melon_block               , 1, W), ST.make(Items.melon, 8, 0), ST.make(Items.melon_seeds, 1, 0));

		CR.shapeless(IL.Plank.get(1), CR.DEF_NAC_NCC, new Object[] {OreDictToolNames.saw, ST.make(Blocks.wooden_button          , 1, W)});
		CR.shapeless(IL.Plank.get(2), CR.DEF_NAC_NCC, new Object[] {OreDictToolNames.saw, ST.make(Blocks.wooden_pressure_plate  , 1, W)});
		CR.shapeless(IL.Plank.get(2), CR.DEF_NAC_NCC, new Object[] {OreDictToolNames.saw, ST.make(Items.sign                    , 1, W)});
		CR.shapeless(IL.Plank.get(2), CR.DEF_NAC_NCC, new Object[] {OreDictToolNames.saw, ST.make(Items.wooden_door             , 1, W)});
		CR.shapeless(IL.Plank.get(2), CR.DEF_NAC_NCC, new Object[] {OreDictToolNames.saw, ST.make(Blocks.fence_gate             , 1, W)});
		CR.shapeless(IL.Plank.get(3), CR.DEF_NAC_NCC, new Object[] {OreDictToolNames.saw, ST.make(Blocks.trapdoor               , 1, W)});
		CR.shapeless(IL.Plank.get(3), CR.DEF_NAC_NCC, new Object[] {OreDictToolNames.saw, ST.make(Items.bed                     , 1, W)});
		CR.shapeless(IL.Plank.get(4), CR.DEF_NAC_NCC, new Object[] {OreDictToolNames.saw, ST.make(Blocks.crafting_table         , 1, W)});
		CR.shapeless(IL.Plank.get(5), CR.DEF_NAC_NCC, new Object[] {OreDictToolNames.saw, ST.make(Items.boat                    , 1, W)});
		CR.shapeless(IL.Plank.get(6), CR.DEF_NAC_NCC, new Object[] {OreDictToolNames.saw, ST.make(Blocks.bookshelf              , 1, W)});
		CR.shapeless(IL.Plank.get(8), CR.DEF_NAC_NCC, new Object[] {OreDictToolNames.saw, ST.make(Blocks.chest                  , 1, W)});
		CR.shapeless(IL.Plank.get(8), CR.DEF_NAC_NCC, new Object[] {OreDictToolNames.saw, ST.make(Blocks.trapped_chest          , 1, W)});
		CR.shapeless(IL.Plank.get(8), CR.DEF_NAC_NCC, new Object[] {OreDictToolNames.saw, ST.make(Blocks.noteblock              , 1, W)});
		CR.shapeless(IL.Plank.get(8), CR.DEF_NAC_NCC, new Object[] {OreDictToolNames.saw, ST.make(Blocks.jukebox                , 1, W)});

		RM.Slicer       .addRecipe2(T, 16,   16, ST.make(Blocks.melon_block, 1, W), IL.Shape_Slicer_Eigths.get(0), ST.make(Items.melon, 8, 0), ST.make(Items.melon_seeds, 1, 0));

		RM.Compressor   .addRecipe1(T, 64,   32, ST.make(Blocks.ice, 2, W), ST.make(Blocks.packed_ice, 1, 0));
		RM.Compressor   .addRecipe1(T, 16,   32, OM.dust(MT.Ice), ST.make(Blocks.ice, 1, 0));
		RM.Compressor   .addRecipe1(T, 16,   16, OM.dust(MT.Ice, U4), OP.gemChipped.mat(MT.Ice, 1));
		RM.Compressor   .addRecipe1(T, 16,   16, OP.gemChipped  .mat(MT.Ice, 4), ST.make(Blocks.ice, 1, 0));
		RM.Compressor   .addRecipe1(T, 16,   16, OP.gemFlawed   .mat(MT.Ice, 2), ST.make(Blocks.ice, 1, 0));
		RM.Compressor   .addRecipe1(T, 16,   16, OP.gem         .mat(MT.Ice, 1), ST.make(Blocks.ice, 1, 0));
		RM.Compressor   .addRecipe1(T, 16,   32, ST.make(Blocks.snow, 1, W), ST.make(Blocks.ice, 1, 0));
		RM.Compressor   .addRecipe1(T, 16,   32, ST.make(Items.snowball, 4, W), ST.make(Blocks.snow, 1, 0));
		RM.Compressor   .addRecipe1(T, 16,   16, ST.make(Items.quartz, 4, 0), ST.make(Blocks.quartz_block, 1, 0));
		RM.Compressor   .addRecipe1(T, 16,   16, IL.Crop_Wheat.get(9), IL.Bale_Wheat.get(1));
		RM.Compressor   .addRecipe1(T, 16,   32, ST.make(Blocks.sand, 4, 0), ST.make(Blocks.sandstone, 1, 0));
		RM.Compressor   .addRecipe1(T, 16,   32, ST.make(Items.clay_ball, 4, W), ST.make(Blocks.clay, 1, 0));
		RM.Compressor   .addRecipe1(T, 16,   32, IL.Clay_Ball_Brown.get(4), ST.make(BlocksGT.Diggables, 1, 1));
		RM.Compressor   .addRecipe1(T, 16,   32, OM.dust(MT.Clay, 4*U), ST.make(Blocks.clay, 1, 0));
		RM.Compressor   .addRecipe1(T, 16,   32, OM.dust(MT.ClayBrown, 4*U), ST.make(BlocksGT.Diggables, 1, 1));
		RM.Compressor   .addRecipe1(T, 16,   64, OM.dust(MT.Glowstone, 4*U), ST.make(Blocks.glowstone, 1, 0));
		RM.Compressor   .addRecipe1(T, 16,   32, OM.dust(MT.Blaze), OP.plate.mat(MT.Blaze, 1));
		RM.Compressor   .addRecipe1(T, 16,   32, OM.dust(MT.Lapis), OP.plateGem.mat(MT.Lapis, 1));
		RM.Compressor   .addRecipe1(T, 16,   32, OM.dust(MT.Asbestos), OP.plate.mat(MT.Asbestos, 1));
		RM.Compressor   .addRecipe1(T, 16,   32, OM.dust(MT.Lazurite), OP.plateGem.mat(MT.Lazurite, 1));
		RM.Compressor   .addRecipe1(T, 16,   32, OM.dust(MT.Sodalite), OP.plateGem.mat(MT.Sodalite, 1));

		RM.Freezer      .addRecipe1(T, 16,   16, ST.tag(0), FL.Water.make( 250), FL.Ice.make(250), ZL_IS);
		RM.Freezer      .addRecipe1(T, 16,   16, ST.tag(0), FL.DistW.make( 250), FL.Ice.make(250), ZL_IS);
		RM.Freezer      .addRecipe1(T, 16,   16, ST.tag(1), FL.Water.make( 250), NF, ST.make(Items.snowball, 1, 0));
		RM.Freezer      .addRecipe1(T, 16,   16, ST.tag(1), FL.DistW.make( 250), NF, ST.make(Items.snowball, 1, 0));
		RM.Freezer      .addRecipe1(T, 16,   32, ST.tag(2), FL.Water.make( 500), NF, ST.make(Blocks.snow_layer, 1, 0));
		RM.Freezer      .addRecipe1(T, 16,   32, ST.tag(2), FL.DistW.make( 500), NF, ST.make(Blocks.snow_layer, 1, 0));
		RM.Freezer      .addRecipe1(T, 16,   64, ST.tag(3), FL.Water.make(1000), NF, ST.make(Blocks.snow, 1, 0));
		RM.Freezer      .addRecipe1(T, 16,   64, ST.tag(3), FL.DistW.make(1000), NF, ST.make(Blocks.snow, 1, 0));
		RM.Freezer      .addRecipe1(T, 16,  128, ST.tag(4), FL.Water.make(1000), NF, ST.make(Blocks.ice, 1, 0));
		RM.Freezer      .addRecipe1(T, 16,  128, ST.tag(4), FL.DistW.make(1000), NF, ST.make(Blocks.ice, 1, 0));
		RM.Freezer      .addRecipe1(T, 16,   32, ST.tag(5), FL.Water.make( 250), NF, OP.gemChipped.mat(MT.Ice, 1));
		RM.Freezer      .addRecipe1(T, 16,   32, ST.tag(5), FL.DistW.make( 250), NF, OP.gemChipped.mat(MT.Ice, 1));
		RM.Freezer      .addRecipe1(T, 16,   64, ST.tag(6), FL.Water.make( 500), NF, OP.gemFlawed.mat(MT.Ice, 1));
		RM.Freezer      .addRecipe1(T, 16,   64, ST.tag(6), FL.DistW.make( 500), NF, OP.gemFlawed.mat(MT.Ice, 1));
		RM.Freezer      .addRecipe1(T, 16,  128, ST.tag(7), FL.Water.make(1000), NF, OP.gem.mat(MT.Ice, 1));
		RM.Freezer      .addRecipe1(T, 16,  128, ST.tag(7), FL.DistW.make(1000), NF, OP.gem.mat(MT.Ice, 1));
		RM.Freezer      .addRecipe1(T, 16,  128, ST.tag(8), FL.Water.make(1000), NF, OP.dust.mat(MT.Ice, 1));
		RM.Freezer      .addRecipe1(T, 16,  128, ST.tag(8), FL.DistW.make(1000), NF, OP.dust.mat(MT.Ice, 1));
		RM.Freezer      .addRecipe1(T, 16,  128, ST.tag(9), FL.Water.make(1000), NF, OP.dust.mat(MT.Snow, 1));
		RM.Freezer      .addRecipe1(T, 16,  128, ST.tag(9), FL.DistW.make(1000), NF, OP.dust.mat(MT.Snow, 1));

		RM.Mortar       .addRecipe1(T, 16, 32, ST.make(Blocks.glass             , 1, W), OM.dust(MT.Glass));
		RM.Mortar       .addRecipe1(T, 16, 32, ST.make(Blocks.stained_glass     , 1, W), OM.dust(MT.Glass, 3*U8));
		RM.Mortar       .addRecipe1(T, 16, 32, ST.make(Blocks.glass_pane        , 1, W), OM.dust(MT.Glass));
		RM.Mortar       .addRecipe1(T, 16, 32, ST.make(Blocks.stained_glass_pane, 1, W), OM.dust(MT.Glass, 3*U8));
		RM.Mortar       .addRecipe1(T, 16, 32, ST.make(Items.bone, 1, W), IL.Dye_Bonemeal.get(2));
		RM.Mortar       .addRecipe1(T, 16, 32, ST.make(Items.blaze_rod, 1, W), ST.make(Items.blaze_powder, 2, 0));
		RM.Mortar       .addRecipe1(T, 16, 16, ST.make(Items.clay_ball, 1, W), OM.dust(MT.Clay));
		RM.Mortar       .addRecipe1(T, 16, 64, ST.make(Blocks.clay, 1, W), OM.dust(MT.Clay, U*4));
		RM.Mortar       .addRecipe1(T, 16, 16, IL.Clay_Ball_Brown.get(1), OM.dust(MT.ClayBrown));
		RM.Mortar       .addRecipe1(T, 16, 64, ST.make(BlocksGT.Diggables, 1, 1), OM.dust(MT.ClayBrown, U*4));
		RM.Mortar       .addRecipe1(T, 16, 16, ST.make(Items.flint, 1, W), OP.dustSmall.mat(MT.Flint, 1));
		RM.Mortar       .addRecipe1(T, 16, 32, ST.make(Blocks.gravel, 1, W), ST.make(Items.flint, 1, 0));
		RM.Mortar       .addRecipe1(T, 16, 16, ST.make(Items.coal, 1, 0), OM.dust(MT.Coal));
		RM.Mortar       .addRecipe1(T, 16, 16, ST.make(Items.coal, 1, 1), OM.dust(MT.Charcoal));
		RM.Mortar       .addRecipe1(T, 16, 16, ST.make(Items.rotten_flesh, 1, W), OP.dust.mat(MT.MeatRotten, 1));

		RM.Shredder     .addRecipe1(T, 16, 16, ST.make(Items.flint, 1, W), OP.dustSmall.mat(MT.Flint, 2));
		RM.Shredder     .addRecipe1(T, 16, 16, ST.make(Blocks.gravel, 1, W), ST.make(Blocks.sand, 1, 0));
		RM.Shredder     .addRecipe1(T, 16, 16, ST.make(Blocks.web, 1, W), ST.make(Items.string, 1, 0));
		RM.Shredder     .addRecipe1(T, 16, 16, ST.make(Items.reeds, 1, W), IL.Remains_Plant.get(1));
		RM.Shredder     .addRecipe1(T, 16, 16, ST.make(Blocks.cobblestone, 1, W), OM.dust(MT.Stone));
		RM.Shredder     .addRecipe1(T, 16, 16, ST.make(Blocks.stone, 1, W), OM.dust(MT.Stone));
		RM.Shredder     .addRecipe1(T, 16, 32, IL.Module_Stone_Generator.get(0), OM.dust(MT.Stone));
		RM.Shredder     .addRecipe1(T, 16, 32, ST.make(Items.bone, 1, W), IL.Dye_Bonemeal.get(4));
		RM.Shredder     .addRecipe1(T, 16, 32, ST.make(Items.blaze_rod, 1, W), ST.make(Items.blaze_powder, 4, 0));
		RM.Shredder     .addRecipe1(T, 16,128,  6000, ST.make(Blocks.melon_block, 1, W), IL.Remains_Fruit.get(9));

		for (byte i = 0; i < 16; i++) {
		RM.Shredder     .addRecipe1(T, 16,   16,  9000, ST.make(Blocks.wool, 1, i), i==0?ST.make(Items.string, 4, 0):OP.plantGtFiber.mat(MT.DATA.Dye_Materials[15-i], 4));
		CR.shaped(ST.make(Blocks.wool, 1, i), DEF, "XX", "XX", 'X', OP.plantGtFiber.dat(MT.DATA.Dye_Materials[15-i]));

		if (i != DYE_INDEX_White) {
		RM.Bath         .addRecipe1(T,  0,   16, ST.make(Blocks.wool    , 1, 15-i), MT.Cl.fluid(U20, T), NF, ST.make(Blocks.wool    , 1, 0));
		RM.Bath         .addRecipe1(T,  0,   16, ST.make(Blocks.carpet  , 1, 15-i), MT.Cl.fluid(U40, T), NF, ST.make(Blocks.carpet  , 1, 0));
		}

		for (FluidStack tDye : DYE_FLUIDS[i]) {
		if (i != DYE_INDEX_White) {
		RM.Bath         .addRecipe1(T,  0,   16, ST.make(Blocks.wool, 1, 0)             , FL.mul(tDye, 1,16, T), NF, ST.make(Blocks.wool                     , 1, 15-i));
		RM.Bath         .addRecipe1(T,  0,   16, ST.make(Blocks.carpet, 1, 0)           , FL.mul(tDye, 1,32, T), NF, ST.make(Blocks.carpet                   , 1, 15-i));
		}
		RM.Bath         .addRecipe1(T,  0,   16, ST.make(Blocks.glass, 1, 0)            , FL.mul(tDye, 1,16, T), NF, ST.make(Blocks.stained_glass            , 1, 15-i));
		RM.Bath         .addRecipe1(T,  0,   16, ST.make(Blocks.hardened_clay, 1, 0)    , FL.mul(tDye, 1,16, T), NF, ST.make(Blocks.stained_hardened_clay    , 1, 15-i));
		}
		RM.Loom         .addRecipe2(T, 16,   16, ST.tag(0), OP.plantGtFiber.mat(MT.DATA.Dye_Materials[15-i], 4), ST.make(Blocks.wool, 1, i));
		}

		for (FluidStack tWater : FL.array(FL.Water.make(125), FL.DistW.make(100))) {
		RM.Bath         .addRecipe1(T,  0,   16, ST.make(Items.reeds, 1, W)             , tWater, NF, ST.make(Items.paper, 1, 0));
		RM.Bath         .addRecipe1(T,  0,   16, OM.dust(MT.Paper)                      , tWater, NF, ST.make(Items.paper, 1, 0));
		RM.Bath         .addRecipe1(T,  0,   16, OM.dust(MT.Clay)                       , tWater, NF, ST.make(Items.clay_ball, 1, 0));
		RM.Bath         .addRecipe1(T,  0,   16, OM.dust(MT.ClayBrown)                  , tWater, NF, IL.Clay_Ball_Brown.get(1));
		}

		RM.Bath         .addRecipe1(T,  0,   16, ST.make(Blocks.stained_hardened_clay   , 1, W), MT.Cl.fluid(U20, T), NF, ST.make(Blocks.hardened_clay  , 1, 0));
		RM.Bath         .addRecipe1(T,  0,   16, ST.make(Blocks.stained_glass           , 1, W), MT.Cl.fluid(U20, T), NF, ST.make(Blocks.glass          , 1, 0));
		RM.Bath         .addRecipe1(T,  0,   16, ST.make(Blocks.stained_glass_pane      , 1, W), MT.Cl.fluid(U50, T), NF, ST.make(Blocks.glass_pane     , 1, 0));

		RM.Loom         .addRecipe2(T, 16,   16, ST.tag(0), OP.plantGtFiber.mat(MT.Cu, 4), ST.make(Blocks.wool, 1, 1, "ORANGE WOOOOOOL!!!"));
		RM.Loom         .addRecipe2(T, 16,   16, ST.tag(0), ST.make(Items.string, 4, W), ST.make(Blocks.wool, 1, 0));
		RM.Loom         .addRecipe2(T, 16,   64, ST.tag(1), ST.make(Items.string, 4, W), ST.make(Blocks.web, 1, 0));
		RM.Loom         .addRecipe2(T, 16,   16, ST.tag(0), ST.make(Items.reeds, 1, W), ST.make(Items.paper, 1, 0));

		for (OreDictMaterial tMat2 : ANY.Iron.mToThis)
		RM.Loom         .addRecipe2(T, 64,  128, ST.make(Items.leather, 6, W), (tMat2==MT.Enori?OP.plateGem:OP.plate)   .mat(tMat2      , 8), ST.make(Items.iron_horse_armor, 1, 0));
		RM.Loom         .addRecipe2(T, 64,  128, ST.make(Items.leather, 6, W), OP.plate                                 .mat(MT.Au      , 8), ST.make(Items.golden_horse_armor, 1, 0));
		RM.Loom         .addRecipe2(T, 64,  128, ST.make(Items.leather, 6, W), OP.plateGem                              .mat(MT.Diamond , 8), ST.make(Items.diamond_horse_armor, 1, 0));
		for (OreDictMaterial tMat2 : ANY.Steel.mToThis)
		RM.Loom         .addRecipeX(T, 64,  128, ST.array(ST.make(Items.leather, 6, W), OP.ring.mat(tMat2, 2), OP.stick.mat(tMat2, 3)), ST.make(Items.saddle, 1, 0));

		RM.Loom         .addRecipe2(T, 16,  128, ST.tag(4), ST.make(Items.leather, 5, W), ST.make(Items.leather_helmet, 1, 0));
		RM.Loom         .addRecipe2(T, 16,  128, ST.tag(5), ST.make(Items.leather, 8, W), ST.make(Items.leather_chestplate, 1, 0));
		RM.Loom         .addRecipe2(T, 16,  128, ST.tag(6), ST.make(Items.leather, 7, W), ST.make(Items.leather_leggings, 1, 0));
		RM.Loom         .addRecipe2(T, 16,  128, ST.tag(7), ST.make(Items.leather, 4, W), ST.make(Items.leather_boots, 1, 0));

		for (OreDictMaterial tMat2 : ANY.Steel.mToThis) {
		RM.Loom         .addRecipe2(T, 96,  128, ST.tag(4), OP.ring.mat(tMat2, 5), ST.make(Items.chainmail_helmet, 1, 0));
		RM.Loom         .addRecipe2(T, 96,  128, ST.tag(5), OP.ring.mat(tMat2, 8), ST.make(Items.chainmail_chestplate, 1, 0));
		RM.Loom         .addRecipe2(T, 96,  128, ST.tag(6), OP.ring.mat(tMat2, 7), ST.make(Items.chainmail_leggings, 1, 0));
		RM.Loom         .addRecipe2(T, 96,  128, ST.tag(7), OP.ring.mat(tMat2, 4), ST.make(Items.chainmail_boots, 1, 0));
		}

		for (OreDictMaterial tMat2 : ANY.Fe.mToThis) if (tMat2 != MT.Enori)
		RM.RollBender   .addRecipe1(T, 16,  256, OP.plateCurved.mat(tMat2, 3), ST.make(Items.bucket, 1, 0));

		RM.Chisel       .addRecipe1(T, 16,   16, ST.make(Blocks.stone, 1, W), ST.make(Blocks.stonebrick, 1, 3));
		RM.Chisel       .addRecipe1(T, 16,   16, ST.make(Blocks.stonebrick, 1, 0), ST.make(Blocks.stonebrick, 1, 2));


		for (OreDictMaterial tMat2 : ANY.Glowstone.mToThis) {
		RM.Press        .addRecipe2(T, 16,   16, OP.dust     .mat(MT.Redstone ,  4), OP.dust     .mat(tMat2,  4), ST.make(Blocks.redstone_lamp, 1, 0));
		RM.Press        .addRecipe2(T, 16,   16, OP.dust     .mat(MT.Redstone , 16), OP.dust     .mat(tMat2,  4), ST.make(Blocks.redstone_lamp, 1, 0));
		RM.Press        .addRecipe2(T, 16,   16, OP.dust     .mat(MT.Redstone , 36), OP.dust     .mat(tMat2,  4), ST.make(Blocks.redstone_lamp, 1, 0));
		RM.Press        .addRecipe2(T, 16,   16, OP.dust     .mat(MT.Redstone ,  4), OP.dustSmall.mat(tMat2, 16), ST.make(Blocks.redstone_lamp, 1, 0));
		RM.Press        .addRecipe2(T, 16,   16, OP.dust     .mat(MT.Redstone , 16), OP.dustSmall.mat(tMat2, 16), ST.make(Blocks.redstone_lamp, 1, 0));
		RM.Press        .addRecipe2(T, 16,   16, OP.dust     .mat(MT.Redstone , 36), OP.dustSmall.mat(tMat2, 16), ST.make(Blocks.redstone_lamp, 1, 0));
		RM.Press        .addRecipe2(T, 16,   16, OP.dust     .mat(MT.Redstone ,  4), OP.dustTiny .mat(tMat2, 36), ST.make(Blocks.redstone_lamp, 1, 0));
		RM.Press        .addRecipe2(T, 16,   16, OP.dust     .mat(MT.Redstone , 16), OP.dustTiny .mat(tMat2, 36), ST.make(Blocks.redstone_lamp, 1, 0));
		RM.Press        .addRecipe2(T, 16,   16, OP.dust     .mat(MT.Redstone , 36), OP.dustTiny .mat(tMat2, 36), ST.make(Blocks.redstone_lamp, 1, 0));
		}
		for (OreDictMaterial tMat2 : ANY.SiO2.mToThis) {
		RM.Press        .addRecipe2(T, 16,   16, OP.dust     .mat(MT.Gunpowder,  4), OP.dust     .mat(tMat2,  4), ST.make(Blocks.tnt, 1, 0));
		RM.Press        .addRecipe2(T, 16,   16, OP.dustSmall.mat(MT.Gunpowder, 16), OP.dust     .mat(tMat2,  4), ST.make(Blocks.tnt, 1, 0));
		RM.Press        .addRecipe2(T, 16,   16, OP.dustTiny .mat(MT.Gunpowder, 36), OP.dust     .mat(tMat2,  4), ST.make(Blocks.tnt, 1, 0));
		RM.Press        .addRecipe2(T, 16,   16, OP.dust     .mat(MT.Gunpowder,  4), OP.dustSmall.mat(tMat2, 16), ST.make(Blocks.tnt, 1, 0));
		RM.Press        .addRecipe2(T, 16,   16, OP.dustSmall.mat(MT.Gunpowder, 16), OP.dustSmall.mat(tMat2, 16), ST.make(Blocks.tnt, 1, 0));
		RM.Press        .addRecipe2(T, 16,   16, OP.dustTiny .mat(MT.Gunpowder, 36), OP.dustSmall.mat(tMat2, 16), ST.make(Blocks.tnt, 1, 0));
		RM.Press        .addRecipe2(T, 16,   16, OP.dust     .mat(MT.Gunpowder,  4), OP.dustTiny .mat(tMat2, 36), ST.make(Blocks.tnt, 1, 0));
		RM.Press        .addRecipe2(T, 16,   16, OP.dustSmall.mat(MT.Gunpowder, 16), OP.dustTiny .mat(tMat2, 36), ST.make(Blocks.tnt, 1, 0));
		RM.Press        .addRecipe2(T, 16,   16, OP.dustTiny .mat(MT.Gunpowder, 36), OP.dustTiny .mat(tMat2, 36), ST.make(Blocks.tnt, 1, 0));
		}
		RM.Press        .addRecipe2(T, 16,   16, OP.dust     .mat(MT.Gunpowder,  4), ST.make(Blocks.sand,  1, W), ST.make(Blocks.tnt, 1, 0));
		RM.Press        .addRecipe2(T, 16,   16, OP.dustSmall.mat(MT.Gunpowder, 16), ST.make(Blocks.sand,  1, W), ST.make(Blocks.tnt, 1, 0));
		RM.Press        .addRecipe2(T, 16,   16, OP.dustTiny .mat(MT.Gunpowder, 36), ST.make(Blocks.sand,  1, W), ST.make(Blocks.tnt, 1, 0));


		RM.Squeezer     .addRecipe1(T, 16,  128,  6000, ST.make(Blocks.melon_block      , 1, W), NF, FL.Juice_Melon.make(2250), IL.Remains_Fruit.get(9));
		RM.Squeezer     .addRecipe1(T, 16,   16,  2000, ST.make(Blocks.red_flower       , 1, 0), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_Red          ], 2), IL.Remains_Plant.get(1));
		RM.Squeezer     .addRecipe1(T, 16,   16,  2000, ST.make(Blocks.red_flower       , 1, 1), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_LightBlue    ], 2), IL.Remains_Plant.get(1));
		RM.Squeezer     .addRecipe1(T, 16,   16,  2000, ST.make(Blocks.red_flower       , 1, 2), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_Magenta      ], 2), IL.Remains_Plant.get(1));
		RM.Squeezer     .addRecipe1(T, 16,   16,  2000, ST.make(Blocks.red_flower       , 1, 3), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_LightGray    ], 2), IL.Remains_Plant.get(1));
		RM.Squeezer     .addRecipe1(T, 16,   16,  2000, ST.make(Blocks.red_flower       , 1, 4), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_Red          ], 2), IL.Remains_Plant.get(1));
		RM.Squeezer     .addRecipe1(T, 16,   16,  2000, ST.make(Blocks.red_flower       , 1, 5), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_Orange       ], 2), IL.Remains_Plant.get(1));
		RM.Squeezer     .addRecipe1(T, 16,   16,  2000, ST.make(Blocks.red_flower       , 1, 6), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_LightGray    ], 2), IL.Remains_Plant.get(1));
		RM.Squeezer     .addRecipe1(T, 16,   16,  2000, ST.make(Blocks.red_flower       , 1, 7), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_Pink         ], 2), IL.Remains_Plant.get(1));
		RM.Squeezer     .addRecipe1(T, 16,   16,  2000, ST.make(Blocks.red_flower       , 1, 8), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_LightGray    ], 2), IL.Remains_Plant.get(1));
		RM.Squeezer     .addRecipe1(T, 16,   16,  2000, ST.make(Blocks.yellow_flower    , 1, 0), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_Yellow       ], 2), IL.Remains_Plant.get(1));
		RM.Squeezer     .addRecipe1(T, 16,   16,  4000, ST.make(Blocks.double_plant     , 1, 1), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_Magenta      ], 3), IL.Remains_Plant.get(1));
		RM.Squeezer     .addRecipe1(T, 16,   16,  4000, ST.make(Blocks.double_plant     , 1, 4), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_Red          ], 3), IL.Remains_Plant.get(1));
		RM.Squeezer     .addRecipe1(T, 16,   16,  4000, ST.make(Blocks.double_plant     , 1, 5), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_Pink         ], 3), IL.Remains_Plant.get(1));
		RM.Squeezer     .addRecipe1(T, 16,   16, 10000, ST.make(Blocks.double_plant     , 1, 0), NF, FL.Oil_Sunflower.make(100), ST.make(Items.dye, 2, DYE_INDEX_Yellow));
		RM.Squeezer     .addRecipe1(T, 16,   16,  7000, ST.make(Blocks.cactus           , 1, W), NF, FL.Juice_Cactus.make(100), IL.Dye_Cactus.get(2));
		RM.Squeezer     .addRecipe1(T, 16,   16,  4000, ST.make(Items.reeds             , 1, W), NF, FL.Juice_Reed.make(100), IL.Remains_Plant.get(1));
		RM.Squeezer     .addRecipe1(T, 16,   64, 10000, OM.dust(MT.Ice, U9)                    , NF, FL.Ice.make( 111), NI);
		RM.Squeezer     .addRecipe1(T, 16,   64, 10000, OM.dust(MT.Ice, U4)                    , NF, FL.Ice.make( 250), NI);
		RM.Squeezer     .addRecipe1(T, 16,   64, 10000, OM.dust(MT.Ice)                        , NF, FL.Ice.make(1000), NI);
		RM.Squeezer     .addRecipe1(T, 16,   64, 10000, OP.gemChipped.mat(MT.Ice        , 1   ), NF, FL.Ice.make( 250), NI);
		RM.Squeezer     .addRecipe1(T, 16,   64, 10000, OP.gemFlawed.mat(MT.Ice         , 1   ), NF, FL.Ice.make( 500), NI);
		RM.Squeezer     .addRecipe1(T, 16,   64, 10000, OP.gem.mat(MT.Ice               , 1   ), NF, FL.Ice.make(1000), NI);
		RM.Squeezer     .addRecipe1(T, 16,   64, 10000, ST.make(Blocks.ice              , 1, W), NF, FL.Ice.make(1000), NI);
		RM.Squeezer     .addRecipe1(T, 16,  128, 10000, ST.make(Blocks.packed_ice       , 1, W), NF, FL.Ice.make(2000), NI);
		RM.Squeezer     .addRecipe1(T, 16,   64, 10000, OM.dust(MT.Snow, U9)                   , NF, FL.Ice.make( 111), NI);
		RM.Squeezer     .addRecipe1(T, 16,   64, 10000, OM.dust(MT.Snow, U4)                   , NF, FL.Ice.make( 250), NI);
		RM.Squeezer     .addRecipe1(T, 16,   64, 10000, OM.dust(MT.Snow)                       , NF, FL.Ice.make(1000), NI);
		RM.Squeezer     .addRecipe1(T, 16,   64, 10000, ST.make(Items.snowball          , 1, W), NF, FL.Ice.make( 250), NI);
		RM.Squeezer     .addRecipe1(T, 16,   64, 10000, ST.make(Blocks.snow             , 1, W), NF, FL.Ice.make(1000), NI);
		RM.Squeezer     .addRecipe1(T, 16,   16, 10000, ST.make(Blocks.red_mushroom     , 1, W), NF, FL.Potion_Poison_1.make(250), NI);
		RM.Squeezer     .addRecipe1(T, 16,   16, 10000, ST.make(Items.poisonous_potato  , 1, W), NF, FL.Potion_Poison_1.make(250), NI);
		RM.Squeezer     .addRecipe1(T, 16,   16, 10000, ST.make(Items.spider_eye        , 1, W), NF, FL.Potion_Poison_1.make(250), OM.dust(MT.MeatRaw, U2));
		RM.Squeezer     .addRecipe1(T, 16,   32, 10000, ST.make(Items.fish              , 1, 3), NF, FL.Potion_Poison_2.make(250), OM.dust(MT.FishRaw, U));
		RM.Squeezer     .addRecipe1(T, 16,  128, 10000, ST.make(Items.golden_apple      , 1, 0), NF, FL.make("potion.goldenapplejuice", 250), OM.dust(MT.Au, U*2));
		RM.Squeezer     .addRecipe1(T, 16,  128, 10000, ST.make(Items.golden_apple      , 1, 1), NF, FL.make("potion.idunsapplejuice", 250), OM.dust(MT.Au, U*18));
		RM.Squeezer     .addRecipe1(T, 16,  128, 10000, ST.make(Items.golden_carrot     , 1, 0), NF, FL.make("goldencarrotjuice", 250), OM.dust(MT.Au, 2*U9));
		RM.Squeezer     .addRecipe1(T, 16,   16, 10000, IL.Dye_SquidInk                 .get(1), NF, FL.make("squidink", 2*L), NI);

		RM.Juicer       .addRecipe1(T, 16,   16,  3000, ST.make(Blocks.red_flower       , 1, 0), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_Red          ], 1), IL.Remains_Plant.get(1));
		RM.Juicer       .addRecipe1(T, 16,   16,  3000, ST.make(Blocks.red_flower       , 1, 1), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_LightBlue    ], 1), IL.Remains_Plant.get(1));
		RM.Juicer       .addRecipe1(T, 16,   16,  3000, ST.make(Blocks.red_flower       , 1, 2), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_Magenta      ], 1), IL.Remains_Plant.get(1));
		RM.Juicer       .addRecipe1(T, 16,   16,  3000, ST.make(Blocks.red_flower       , 1, 3), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_LightGray    ], 1), IL.Remains_Plant.get(1));
		RM.Juicer       .addRecipe1(T, 16,   16,  3000, ST.make(Blocks.red_flower       , 1, 4), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_Red          ], 1), IL.Remains_Plant.get(1));
		RM.Juicer       .addRecipe1(T, 16,   16,  3000, ST.make(Blocks.red_flower       , 1, 5), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_Orange       ], 1), IL.Remains_Plant.get(1));
		RM.Juicer       .addRecipe1(T, 16,   16,  3000, ST.make(Blocks.red_flower       , 1, 6), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_LightGray    ], 1), IL.Remains_Plant.get(1));
		RM.Juicer       .addRecipe1(T, 16,   16,  3000, ST.make(Blocks.red_flower       , 1, 7), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_Pink         ], 1), IL.Remains_Plant.get(1));
		RM.Juicer       .addRecipe1(T, 16,   16,  3000, ST.make(Blocks.red_flower       , 1, 8), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_LightGray    ], 1), IL.Remains_Plant.get(1));
		RM.Juicer       .addRecipe1(T, 16,   16,  3000, ST.make(Blocks.yellow_flower    , 1, 0), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_Yellow       ], 1), IL.Remains_Plant.get(1));
		RM.Juicer       .addRecipe1(T, 16,   16,  6000, ST.make(Blocks.double_plant     , 1, 1), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_Magenta      ], 2), IL.Remains_Plant.get(1));
		RM.Juicer       .addRecipe1(T, 16,   16,  6000, ST.make(Blocks.double_plant     , 1, 4), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_Red          ], 2), IL.Remains_Plant.get(1));
		RM.Juicer       .addRecipe1(T, 16,   16,  6000, ST.make(Blocks.double_plant     , 1, 5), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_Pink         ], 2), IL.Remains_Plant.get(1));
		RM.Juicer       .addRecipe1(T, 16,   16, 10000, ST.make(Blocks.double_plant     , 1, 0), NF, FL.Oil_Sunflower.make(75), ST.make(Items.dye, 2, 11));
		RM.Juicer       .addRecipe1(T, 16,   16,  9000, ST.make(Blocks.cactus           , 1, W), NF, FL.Juice_Cactus.make(75), IL.Dye_Cactus.get(2));
		RM.Juicer       .addRecipe1(T, 16,   16,  5000, ST.make(Items.reeds             , 1, W), NF, FL.Juice_Reed.make(75), IL.Remains_Plant.get(1));
		RM.Juicer       .addRecipe1(T, 16,   64, 10000, OM.dust(MT.Ice, U9)                    , NF, FL.Ice.make( 111), NI);
		RM.Juicer       .addRecipe1(T, 16,   64, 10000, OM.dust(MT.Ice, U4)                    , NF, FL.Ice.make( 250), NI);
		RM.Juicer       .addRecipe1(T, 16,   64, 10000, OM.dust(MT.Ice)                        , NF, FL.Ice.make(1000), NI);
		RM.Juicer       .addRecipe1(T, 16,   64, 10000, OP.gemChipped.mat(MT.Ice        , 1   ), NF, FL.Ice.make( 250), NI);
		RM.Juicer       .addRecipe1(T, 16,   64, 10000, OP.gemFlawed.mat(MT.Ice         , 1   ), NF, FL.Ice.make( 500), NI);
		RM.Juicer       .addRecipe1(T, 16,   64, 10000, OP.gem.mat(MT.Ice               , 1   ), NF, FL.Ice.make(1000), NI);
		RM.Juicer       .addRecipe1(T, 16,   64, 10000, ST.make(Blocks.ice              , 1, W), NF, FL.Ice.make(1000), NI);
		RM.Juicer       .addRecipe1(T, 16,  128, 10000, ST.make(Blocks.packed_ice       , 1, W), NF, FL.Ice.make(2000), NI);
		RM.Juicer       .addRecipe1(T, 16,   64, 10000, OM.dust(MT.Snow, U9)                   , NF, FL.Ice.make( 111), NI);
		RM.Juicer       .addRecipe1(T, 16,   64, 10000, OM.dust(MT.Snow, U4)                   , NF, FL.Ice.make( 250), NI);
		RM.Juicer       .addRecipe1(T, 16,   64, 10000, OM.dust(MT.Snow)                       , NF, FL.Ice.make(1000), NI);
		RM.Juicer       .addRecipe1(T, 16,   64, 10000, ST.make(Items.snowball          , 1, W), NF, FL.Ice.make( 250), NI);
		RM.Juicer       .addRecipe1(T, 16,   64, 10000, ST.make(Blocks.snow             , 1, W), NF, FL.Ice.make(1000), NI);
		RM.Juicer       .addRecipe1(T, 16,   16, 10000, ST.make(Blocks.red_mushroom     , 1, W), NF, FL.Potion_Poison_1.make(125), NI);
		RM.Juicer       .addRecipe1(T, 16,   16, 10000, ST.make(Items.poisonous_potato  , 1, W), NF, FL.Potion_Poison_1.make(125), NI);
		RM.Juicer       .addRecipe1(T, 16,   16, 10000, ST.make(Items.spider_eye        , 1, W), NF, FL.Potion_Poison_1.make(125), OM.dust(MT.MeatRaw, U2));
		RM.Juicer       .addRecipe1(T, 16,   32, 10000, ST.make(Items.fish              , 1, 3), NF, FL.Potion_Poison_2.make(125), OM.dust(MT.FishRaw, U));
		RM.Juicer       .addRecipe1(T, 16,   16, 10000, IL.Dye_SquidInk                 .get(1), NF, FL.make("squidink", 3*L/2), NI);

		RM.Bath         .addRecipe1(T,  0,  128, ST.make(Items.golden_apple , 1, 0), MT.Au.liquid(U*64, T), NF, ST.make(Items.golden_apple, 1, 1));
		RM.Bath         .addRecipe1(T,  0,   16, ST.make(Items.carrot       , 1, W), MT.Au.liquid(8*U9, T), NF, ST.make(Items.golden_carrot, 1, 0));
		RM.Bath         .addRecipe1(T,  0,   16, ST.make(Items.melon        , 1, W), MT.Au.liquid(8*U9, T), NF, ST.make(Items.speckled_melon, 1, 0));
		RM.Mixer        .addRecipe2(T, 16,   16, ST.make(Items.melon        , 1, W), OP.nugget.mat(MT.Au, 8), ST.make(Items.speckled_melon, 1, 0));
		RM.Mixer        .addRecipe0(T, 16,   16, FL.array(FL.Water.make(50), FL.Lava.make(1000)), NF, ST.make(Blocks.obsidian, 1, 0));
		RM.Mixer        .addRecipe0(T, 16,   16, FL.array(FL.DistW.make(50), FL.Lava.make(1000)), NF, ST.make(Blocks.obsidian, 1, 0));
		RM.Mixer        .addRecipe1(T, 16,   16, OM.dust(MT.Redrock), FL.Water.make(3000), NF, IL.Clay_Ball_Brown.get(4));
		RM.Mixer        .addRecipe1(T, 16,   16, OM.dust(MT.Redrock), FL.DistW.make(3000), NF, IL.Clay_Ball_Brown.get(4));
		RM.Mixer        .addRecipe2(T, 16,   16, OM.dust(MT.EnderPearl), OM.dust(MT.Blaze), OM.dust(MT.EnderEye));
		RM.Mixer        .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.Sugar              ), ST.make(Items.spider_eye, 1, W), ST.make(Blocks.brown_mushroom, 1, W)), ST.make(Items.fermented_spider_eye, 1, 0));
		RM.Mixer        .addRecipeX(T, 16,   16, ST.array(OP.gemChipped.mat(MT.Sugar , 4), ST.make(Items.spider_eye, 1, W), ST.make(Blocks.brown_mushroom, 1, W)), ST.make(Items.fermented_spider_eye, 1, 0));
		RM.Mixer        .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.Coal               ), OM.dust(MT.Blaze), OM.dust(MT.Gunpowder)), ST.make(Items.fire_charge, 3, 0));
		RM.Mixer        .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.Charcoal           ), OM.dust(MT.Blaze), OM.dust(MT.Gunpowder)), ST.make(Items.fire_charge, 3, 0));
		RM.Mixer        .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.CoalCoke           ), OM.dust(MT.Blaze), OM.dust(MT.Gunpowder)), ST.make(Items.fire_charge, 3, 0));
		RM.Mixer        .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.LigniteCoke        ), OM.dust(MT.Blaze), OM.dust(MT.Gunpowder)), ST.make(Items.fire_charge, 3, 0));


		RM.Electrolyzer .addRecipe1(T, 16, 3000, ST.tag(0), FL.DistW.make(3000), MT.H.gas(2*U, F), MT.O.gas(U, F));
		RM.Electrolyzer .addRecipe1(T, 16, 3200, ST.tag(0), FL.Water.make(3000), MT.H.gas(2*U, F), MT.O.gas(U, F));
		RM.Electrolyzer .addRecipe2(T, 64,   64, ST.tag(0), ST.make(Blocks.sand, 1, 0), OM.dust(MT.SiO2));

		RM.Centrifuge   .addRecipe1(T, 16,   16, OM.dust(MT.SlimyBone), NF, FL.Slime_Green.make(250), OM.dust(MT.Bone));
		RM.Centrifuge   .addRecipe1(T, 16,   16, ST.make(Items.magma_cream, 1, W), NF, FL.Slime_Green.make(250), ST.make(Items.blaze_powder, 1, 0));
		for (String tFluid : FluidsGT.SLIME) if (FL.exists(tFluid)) {
		RM.Centrifuge   .addRecipe0(T, 16,   64, FL.make(tFluid, 250), FL.Latex.make(L/2), FL.Glue.make(250));
		RM.Mixer        .addRecipe1(T, 16,   16, OM.dust(MT.Blaze), FL.make(tFluid, 250), NF, ST.make(Items.magma_cream, 1, 0));
		}

		if (ENABLE_ADDING_IC2_EXTRACTOR_RECIPES) {
		RM.ic2_extractor(ST.make(Blocks.red_flower, 1, 0), ST.make(Items.dye, 2,  1));
		RM.ic2_extractor(ST.make(Blocks.red_flower, 1, 1), ST.make(Items.dye, 2, 12));
		RM.ic2_extractor(ST.make(Blocks.red_flower, 1, 2), ST.make(Items.dye, 2, 13));
		RM.ic2_extractor(ST.make(Blocks.red_flower, 1, 3), ST.make(Items.dye, 2,  7));
		RM.ic2_extractor(ST.make(Blocks.red_flower, 1, 4), ST.make(Items.dye, 2,  1));
		RM.ic2_extractor(ST.make(Blocks.red_flower, 1, 5), ST.make(Items.dye, 2, 14));
		RM.ic2_extractor(ST.make(Blocks.red_flower, 1, 6), ST.make(Items.dye, 2,  7));
		RM.ic2_extractor(ST.make(Blocks.red_flower, 1, 7), ST.make(Items.dye, 2,  9));
		RM.ic2_extractor(ST.make(Blocks.red_flower, 1, 8), ST.make(Items.dye, 2,  7));
		RM.ic2_extractor(ST.make(Blocks.yellow_flower,1, 0), ST.make(Items.dye, 2, 11));
		RM.ic2_extractor(ST.make(Blocks.double_plant, 1, 0), ST.make(Items.dye, 3, 11));
		RM.ic2_extractor(ST.make(Blocks.double_plant, 1, 1), ST.make(Items.dye, 3, 13));
		RM.ic2_extractor(ST.make(Blocks.double_plant, 1, 4), ST.make(Items.dye, 3,  1));
		RM.ic2_extractor(ST.make(Blocks.double_plant, 1, 5), ST.make(Items.dye, 3,  9));
		RM.ic2_extractor(ST.make(Blocks.cactus, 1, W), IL.Dye_Cactus.get(2));
		}

		RM.ic2_compressor(ST.make(Blocks.red_flower, 8, W), IL.IC2_Plantball.get(1));
		RM.ic2_compressor(ST.make(Blocks.yellow_flower, 8, W), IL.IC2_Plantball.get(1));
		RM.ic2_compressor(ST.make(Blocks.double_plant, 8, W), IL.IC2_Plantball.get(1));

		RM.pulverizing(ST.make(Items.reeds, 1, W), IL.Remains_Plant.get(1), T);

		RM.biomass(ST.make(Blocks.brown_mushroom, 8, W));
		RM.biomass(ST.make(Blocks.red_mushroom, 8, W));
		RM.biomass(ST.make(Blocks.red_flower, 8, W));
		RM.biomass(ST.make(Blocks.yellow_flower, 8, W));
		RM.biomass(ST.make(Blocks.double_plant, 8, W));
		RM.biomass(ST.make(Blocks.melon_block, 1, W));
		RM.biomass(ST.make(Blocks.pumpkin, 1, W));
		RM.biomass(ST.make(Blocks.cactus, 8, W));
		RM.biomass(ST.make(Items.reeds, 8, W));
		RM.biomass(ST.make(Items.melon, 9, W));
		RM.biomass(ST.make(Items.wheat, 9, W));
		RM.biomass(ST.make(Items.carrot, 9, W));
		RM.biomass(ST.make(Items.potato, 9, W));
		RM.biomass(ST.make(Items.poisonous_potato, 9, W));
		RM.biomass(IL.Dye_Cactus.get(16));
		RM.biomass(IL.Dye_Cocoa.get(16));

		RM.add_smelting(ST.make(Blocks.sticky_piston, 1, W), ST.make(Blocks.piston, 1, 0));
		RM.add_smelting(ST.make(Items.glass_bottle, 1, W), ST.make(Blocks.glass, 1, 0));

		RM.unbox(IL.Plank.get(3), ST.make(Blocks.bookshelf, 1, W), ST.make(Items.book, 3, 0));

		RM.pack(ST.make(Items.brick, 4, W), ST.make(Blocks.brick_block, 1, 0));
		RM.pack(ST.make(Items.netherbrick, 4, W), ST.make(Blocks.nether_brick, 1, 0));

		RM.generify(OP.plantGtFiber.mat(MT.Black        , 1), ST.make(Items.string, 1, 0));
		RM.generify(OP.plantGtFiber.mat(MT.Red          , 1), ST.make(Items.string, 1, 0));
		RM.generify(OP.plantGtFiber.mat(MT.Green        , 1), ST.make(Items.string, 1, 0));
		RM.generify(OP.plantGtFiber.mat(MT.Brown        , 1), ST.make(Items.string, 1, 0));
		RM.generify(OP.plantGtFiber.mat(MT.Blue         , 1), ST.make(Items.string, 1, 0));
		RM.generify(OP.plantGtFiber.mat(MT.Purple       , 1), ST.make(Items.string, 1, 0));
		RM.generify(OP.plantGtFiber.mat(MT.Cyan         , 1), ST.make(Items.string, 1, 0));
		RM.generify(OP.plantGtFiber.mat(MT.LightGray    , 1), ST.make(Items.string, 1, 0));
		RM.generify(OP.plantGtFiber.mat(MT.Gray         , 1), ST.make(Items.string, 1, 0));
		RM.generify(OP.plantGtFiber.mat(MT.Pink         , 1), ST.make(Items.string, 1, 0));
		RM.generify(OP.plantGtFiber.mat(MT.Lime         , 1), ST.make(Items.string, 1, 0));
		RM.generify(OP.plantGtFiber.mat(MT.Yellow       , 1), ST.make(Items.string, 1, 0));
		RM.generify(OP.plantGtFiber.mat(MT.LightBlue    , 1), ST.make(Items.string, 1, 0));
		RM.generify(OP.plantGtFiber.mat(MT.Magenta      , 1), ST.make(Items.string, 1, 0));
		RM.generify(OP.plantGtFiber.mat(MT.Orange       , 1), ST.make(Items.string, 1, 0));
		RM.generify(OP.plantGtFiber.mat(MT.White        , 1), ST.make(Items.string, 1, 0));
		RM.generify(OP.plantGtFiber.mat(MT.Cu           , 1), ST.make(Items.string, 1, 0));

		new Loader_Recipes_Vanilla_OreDict();
	}
}
