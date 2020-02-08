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
import static gregapi6.util.CR.*;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import gregapi6.api.Abstract_Mod;
import gregapi6.code.ModData;
import gregapi6.compat.CompatMods;
import gregapi6.data.CS.OreDictToolNames;
import gregapi6.data.FL;
import gregapi6.data.IL;
import gregapi6.data.MD;
import gregapi6.data.MT;
import gregapi6.data.OD;
import gregapi6.data.OP;
import gregapi6.data.RM;
import gregapi6.util.CR;
import gregapi6.util.OM;
import gregapi6.util.ST;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;

public class Compat_Recipes_Aether extends CompatMods {
	public Compat_Recipes_Aether(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {OUT.println("GT_Mod: Doing Aether Recipes.");
		ST.item(MD.AETHER, "moaEgg").setMaxStackSize(64);
		
		CR.shaped(ST.make(MD.AETHER, "shoyrootBowl", 1, 0), DEF | DEL_OTHER_SHAPED_RECIPES, "k", "X", 'X', OD.plankSkyroot);
		CR.shapeless(ST.make(Items.bowl, 1, 0), CR.DEF_NAC_NCC, new Object[] {ST.make(MD.AETHER, "shoyrootBowl", 1, 0)});
		RM.generify(ST.make(MD.AETHER, "shoyrootBowl", 1, 0), ST.make(Items.bowl, 1, 0));
		
		RM.sawing(16,  32, F, 100, ST.make(MD.AETHER, "skyrootSignItem"         , 1, W), IL.AETHER_Skyroot_Planks.get(2), OM.dust(MT.Skyroot, OP.stick.mAmount / 3));
		RM.sawing(16,  32, F, 100, ST.make(MD.AETHER, "skyrootFenceGate"        , 1, W), IL.AETHER_Skyroot_Planks.get(2), OM.dust(MT.Skyroot, OP.stick.mAmount * 4));
		RM.sawing(16,  48, F, 100, ST.make(MD.AETHER, "skyrootBedItem"          , 1, W), IL.AETHER_Skyroot_Planks.get(3), ST.make(Blocks.wool, 3, 0));
		RM.sawing(16,  48, F, 100, ST.make(MD.AETHER, "skyrootTrapDoor"         , 1, W), IL.AETHER_Skyroot_Planks.get(3));
		RM.sawing(16,  64, F, 100, ST.make(MD.AETHER, "skyrootCraftingTable"    , 1, W), IL.AETHER_Skyroot_Planks.get(4));
		RM.sawing(16,  96, F, 100, ST.make(MD.AETHER, "skyrootDoorItem"         , 1, W), IL.AETHER_Skyroot_Planks.get(6));
		RM.sawing(16,  96, F, 100, ST.make(MD.AETHER, "skyrootBookshelf"        , 1, W), IL.AETHER_Skyroot_Planks.get(6), ST.make(Items.book, 3, 0));
		RM.sawing(16, 128, F, 100, ST.make(MD.AETHER, "skyrootChest"            , 1, W), IL.AETHER_Skyroot_Planks.get(8));
		
		CR.shapeless(IL.AETHER_Skyroot_Planks.get(2), CR.DEF_NAC_NCC, new Object[] {OreDictToolNames.saw, ST.make(MD.AETHER, "skyrootSignItem"      , 1, W)});
		CR.shapeless(IL.AETHER_Skyroot_Planks.get(2), CR.DEF_NAC_NCC, new Object[] {OreDictToolNames.saw, ST.make(MD.AETHER, "skyrootFenceGate"     , 1, W)});
		CR.shapeless(IL.AETHER_Skyroot_Planks.get(3), CR.DEF_NAC_NCC, new Object[] {OreDictToolNames.saw, ST.make(MD.AETHER, "skyrootTrapDoor"      , 1, W)});
		CR.shapeless(IL.AETHER_Skyroot_Planks.get(3), CR.DEF_NAC_NCC, new Object[] {OreDictToolNames.saw, ST.make(MD.AETHER, "skyrootBedItem"       , 1, W)});
		CR.shapeless(IL.AETHER_Skyroot_Planks.get(4), CR.DEF_NAC_NCC, new Object[] {OreDictToolNames.saw, ST.make(MD.AETHER, "skyrootCraftingTable" , 1, W)});
		CR.shapeless(IL.AETHER_Skyroot_Planks.get(6), CR.DEF_NAC_NCC, new Object[] {OreDictToolNames.saw, ST.make(MD.AETHER, "skyrootDoorItem"      , 1, W)});
		CR.shapeless(IL.AETHER_Skyroot_Planks.get(6), CR.DEF_NAC_NCC, new Object[] {OreDictToolNames.saw, ST.make(MD.AETHER, "skyrootBookshelf"     , 1, W)});
		CR.shapeless(IL.AETHER_Skyroot_Planks.get(8), CR.DEF_NAC_NCC, new Object[] {OreDictToolNames.saw, ST.make(MD.AETHER, "skyrootChest"         , 1, W)});
		
		RM.unbox(IL.AETHER_Skyroot_Planks.get(3), ST.make(MD.AETHER, "skyrootBookshelf", 1, W), ST.make(Items.book, 3, 0));
		
		// TODO: Magical Infuser
		RM.Injector.addRecipe2(T, 16, 16, OP.gem.mat(MT.Ambrosium, 4), OP.dustTiny    .mat(MT.Gravitite, 9), ST.make(MD.AETHER, "enchantedGravitite", 1, 0));
		RM.Injector.addRecipe2(T, 16, 16, OP.gem.mat(MT.Ambrosium, 4), OP.dustSmall   .mat(MT.Gravitite, 4), ST.make(MD.AETHER, "enchantedGravitite", 1, 0));
		RM.Injector.addRecipe2(T, 16, 16, OP.gem.mat(MT.Ambrosium, 4), OP.dust        .mat(MT.Gravitite, 1), ST.make(MD.AETHER, "enchantedGravitite", 1, 0));
		RM.Injector.addRecipe2(T, 16, 16, OP.gem.mat(MT.Ambrosium, 4), OP.gemChipped  .mat(MT.Gravitite, 4), ST.make(MD.AETHER, "enchantedGravitite", 1, 0));
		RM.Injector.addRecipe2(T, 16, 16, OP.gem.mat(MT.Ambrosium, 4), OP.gemFlawed   .mat(MT.Gravitite, 2), ST.make(MD.AETHER, "enchantedGravitite", 1, 0));
		RM.Injector.addRecipe2(T, 16, 16, OP.gem.mat(MT.Ambrosium, 3), OP.gem         .mat(MT.Gravitite, 1), ST.make(MD.AETHER, "enchantedGravitite", 1, 0));
		RM.Injector.addRecipe2(T, 16, 16, OP.gem.mat(MT.Ambrosium, 5), OP.gemFlawless .mat(MT.Gravitite, 1), ST.make(MD.AETHER, "enchantedGravitite", 2, 0));
		RM.Injector.addRecipe2(T, 16, 16, OP.gem.mat(MT.Ambrosium, 9), OP.gemExquisite.mat(MT.Gravitite, 1), ST.make(MD.AETHER, "enchantedGravitite", 4, 0));
		RM.Injector.addRecipe2(T, 16, 16, OP.gem.mat(MT.Ambrosium,16), OP.gemLegendary.mat(MT.Gravitite, 1), ST.make(MD.AETHER, "enchantedGravitite", 8, 0));
		
		RM.biomass(ST.make(MD.AETHER, "purpleFlower", 16, W));
		RM.biomass(ST.make(MD.AETHER, "whiteRose", 16, W));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.AETHER, "purpleFlower" , 1, W), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_Purple], 2), ST.make(Items.dye, 1, DYE_INDEX_Purple));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.AETHER, "whiteRose"    , 1, W), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_White], 2), OM.dust(MT.White));
		RM.Juicer.addRecipe1(T, 16, 16, ST.make(MD.AETHER, "purpleFlower"   , 1, W), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_Purple], 2), ST.make(Items.dye, 1, DYE_INDEX_Purple));
		RM.Juicer.addRecipe1(T, 16, 16, ST.make(MD.AETHER, "whiteRose"      , 1, W), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_White], 2), OM.dust(MT.White));
		RM.ic2_extractor(ST.make(MD.AETHER, "purpleFlower"  , 1, W), ST.make(Items.dye, 3, DYE_INDEX_Purple));
		RM.ic2_extractor(ST.make(MD.AETHER, "whiteRose"     , 1, W), OM.dust(MT.White, U*3));
	}
}
