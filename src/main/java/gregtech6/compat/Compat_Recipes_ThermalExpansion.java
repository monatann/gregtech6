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
import static gregapi6.data.OP.*;
import static gregapi6.data.TD.Atomic.*;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import gregapi6.api.Abstract_Mod;
import gregapi6.code.ModData;
import gregapi6.compat.CompatMods;
import gregapi6.data.ANY;
import gregapi6.data.FL;
import gregapi6.data.IL;
import gregapi6.data.MD;
import gregapi6.data.MT;
import gregapi6.data.OD;
import gregapi6.data.OP;
import gregapi6.data.RM;
import gregapi6.oredict.OreDictMaterial;
import gregapi6.oredict.event.IOreDictListenerEvent;
import gregapi6.oredict.event.OreDictListenerEvent_Names;
import gregapi6.oredict.event.OreDictListenerEvent_TwoNames;
import gregapi6.recipes.handlers.RecipeMapHandlerPrefix;
import gregapi6.util.CR;
import gregapi6.util.OM;
import gregapi6.util.ST;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
import net.minecraftforge.fluids.FluidStack;

public class Compat_Recipes_ThermalExpansion extends CompatMods {
	public Compat_Recipes_ThermalExpansion(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {OUT.println("GT_Mod: Doing Thermal Foundation Recipes.");
		CR.delate(MD.TE, "wrench", "tool.battleWrenchInvar");
		
		CR.remove(IL.TE_Rod_Blizz .get(1));
		CR.remove(IL.TE_Rod_Blitz .get(1));
		CR.remove(IL.TE_Rod_Basalz.get(1));
		
		CR.shapeless(OP.dust.mat(MT.Blizz , 1), CR.DEF_NAC, new Object[] {IL.TE_Rod_Blizz .get(1)});
		CR.shapeless(OP.dust.mat(MT.Blitz , 1), CR.DEF_NAC, new Object[] {IL.TE_Rod_Blitz .get(1)});
		CR.shapeless(OP.dust.mat(MT.Basalz, 1), CR.DEF_NAC, new Object[] {IL.TE_Rod_Basalz.get(1)});
		
		RM.Mortar       .addRecipe1(T, 16, 32, IL.TE_Rod_Blizz .get(1), OP.dust.mat(MT.Blizz , 2));
		RM.Mortar       .addRecipe1(T, 16, 32, IL.TE_Rod_Blitz .get(1), OP.dust.mat(MT.Blitz , 2));
		RM.Mortar       .addRecipe1(T, 16, 32, IL.TE_Rod_Basalz.get(1), OP.dust.mat(MT.Basalz, 2));
		
		RM.Shredder     .addRecipe1(T, 16, 32, IL.TE_Rod_Blizz .get(1), OP.dust.mat(MT.Blizz , 4));
		RM.Shredder     .addRecipe1(T, 16, 32, IL.TE_Rod_Blitz .get(1), OP.dust.mat(MT.Blitz , 4));
		RM.Shredder     .addRecipe1(T, 16, 32, IL.TE_Rod_Basalz.get(1), OP.dust.mat(MT.Basalz, 4));
		
		RM.Sluice.add(new RecipeMapHandlerPrefix(crushed        , 1, null, 0, MT.Petrotheum.liquid(9*U50, T), 16, 144, 0, NF, crushedPurified       , 1, crushedPurifiedTiny, 9, NI, OM.dust(MT.SluiceSand    ), T, F, T, ANTIMATTER.NOT).chances(10000, 5000, 10000));
		RM.Sluice.add(new RecipeMapHandlerPrefix(crushedTiny    , 1, null, 0, MT.Petrotheum.liquid(  U50, T), 16,  16, 0, NF, crushedPurifiedTiny   , 1, crushedPurifiedTiny, 1, NI, OM.dust(MT.SluiceSand, U9), T, F, T, ANTIMATTER.NOT).chances(10000, 5000, 10000));
		
		RM.Bath             .addRecipe1(T,  0,  128, OP.crushed.mat(MT.OREMATS.Cinnabar, 1), MT.Cryotheum.liquid(U, T), NF, IL.TE_Cinnabar.get(1));
		
		FluidStack tPyrotheum = MT.Pyrotheum.liquid(U2, T);
		if (!FL.Error.is(tPyrotheum)) {
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,  3000}, OP.crushed.mat(MT.Au                , 1), tPyrotheum, NF, OM.ingot(MT.Au            , 5*U4), IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,  2250}, OP.crushed.mat(MT.Ni                , 1), tPyrotheum, NF, OM.ingot(MT.Ni            , 5*U4), IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,  1500}, OP.crushed.mat(MT.Co                , 1), tPyrotheum, NF, OM.ingot(MT.Co            , 5*U4), IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.Pb                , 1), tPyrotheum, NF, OM.ingot(MT.Pb            , 5*U4), IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.Fe                , 1), tPyrotheum, NF, OM.ingot(MT.Fe            , 5*U4), IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.Sn                , 1), tPyrotheum, NF, OM.ingot(MT.Sn            , 5*U4), IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.Cu                , 1), tPyrotheum, NF, OM.ingot(MT.Cu            , 5*U4), IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.Pt                , 1), tPyrotheum, NF, OM.ingot(MT.Pt            , 5*U4), IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.Ir                , 1), tPyrotheum, NF, OM.ingot(MT.Ir            , 5*U4), IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.Os                , 1), tPyrotheum, NF, OM.ingot(MT.Os            , 5*U4), IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.Hg                , 1), tPyrotheum, NF, OM.ingot(MT.Hg            , 5*U4), IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.Cr                , 1), tPyrotheum, NF, OM.ingot(MT.Cr            , 5*U4), IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.Mn                , 1), tPyrotheum, NF, OM.ingot(MT.Mn            , 5*U4), IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.Mo                , 1), tPyrotheum, NF, OM.ingot(MT.Mo            , 5*U4), IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.Nd                , 1), tPyrotheum, NF, OM.ingot(MT.Nd            , 5*U4), IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.Ag                , 1), tPyrotheum, NF, OM.ingot(MT.Ag            , 5*U4), IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.Bi                , 1), tPyrotheum, NF, OM.ingot(MT.Bi            , 5*U4), IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.Zn                , 1), tPyrotheum, NF, OM.ingot(MT.Zn            , 5*U4), IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.Al                , 1), tPyrotheum, NF, OM.ingot(MT.Al            , 5*U4), IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.Cd                , 1), tPyrotheum, NF, OM.ingot(MT.Cd            , 5*U4), IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.Pd                , 1), tPyrotheum, NF, OM.ingot(MT.Pd            , 5*U4), IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.In                , 1), tPyrotheum, NF, OM.ingot(MT.In            , 5*U4), IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.Si                , 1), tPyrotheum, NF, OM.ingot(MT.Si            , 5*U4), IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.Ti                , 1), tPyrotheum, NF, OM.ingot(MT.Ti            , 5*U4), IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.W                 , 1), tPyrotheum, NF, OM.ingot(MT.W             , 5*U4), IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.FakeOsmium        , 1), tPyrotheum, NF, OM.ingot(MT.FakeOsmium    , 5*U4), IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.Electrum          , 1), tPyrotheum, NF, OM.ingot(MT.Electrum      , 5*U4), IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.AstralSilver      , 1), tPyrotheum, NF, OM.ingot(MT.Mithril       , 5*U4), IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.Midasium          , 1), tPyrotheum, NF, OM.ingot(MT.Midasium      , 5*U4), IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.Mithril           , 1), tPyrotheum, NF, OM.ingot(MT.AstralSilver  , 5*U4), IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.Cheese            , 1), tPyrotheum, NF, OM.ingot(MT.Cheese        , 5*U4), IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.Ardite            , 1), tPyrotheum, NF, OM.ingot(MT.Ardite        , 5*U4), IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.Aredrite          , 1), tPyrotheum, NF, OM.ingot(MT.Aredrite      , 5*U4), IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.Desh              , 1), tPyrotheum, NF, OM.ingot(MT.Desh          , 5*U4), IL.TE_Slag_Rich.get(1));
		}
		
		FL.set(new FluidContainerData(FL.Redstone  .make(1440), ST.make(MD.TE_FOUNDATION, "bucket", 1, 0), ST.make(Items.bucket, 1, 0), F), F, F);
		FL.set(new FluidContainerData(FL.Ender     .make( 576), ST.make(MD.TE_FOUNDATION, "bucket", 1, 2), ST.make(Items.bucket, 1, 0), F), F, F);
		
		for (FluidStack tRedstone : FL.array(FL.Redstone.make(L), FL.Redstone_TE.make(100))) {
			RM.Injector     .addRecipe1(T, 16, 16, ST.make(Items.snowball, 1, W)    , FL.mul(tRedstone, 2), NF, OP.dust.mat(MT.Blizz, 1));
			RM.Injector     .addRecipe1(T, 16, 16, OP.dustSmall.mat(MT.Snow, 1)     , FL.mul(tRedstone, 2), NF, OP.dust.mat(MT.Blizz, 1));
			RM.Injector     .addRecipe1(T, 16, 16, OP.dustSmall.mat(MT.Ice, 1)      , FL.mul(tRedstone, 2), NF, OP.dust.mat(MT.Blizz, 1));
			RM.Injector     .addRecipe1(T, 16, 64, ST.make(Blocks.snow, 1, W)       , FL.mul(tRedstone, 8), NF, OP.dust.mat(MT.Blizz, 4));
			RM.Injector     .addRecipe1(T, 16, 64, OP.dust.mat(MT.Snow, 1)          , FL.mul(tRedstone, 8), NF, OP.dust.mat(MT.Blizz, 4));
			RM.Injector     .addRecipe1(T, 16, 64, OP.dust.mat(MT.Ice, 1)           , FL.mul(tRedstone, 8), NF, OP.dust.mat(MT.Blizz, 4));
			for (OreDictMaterial tMat : ANY.SiO2.mToThis)
			RM.Injector     .addRecipe1(T, 16, 16, OM.dust(tMat)                    , FL.mul(tRedstone, 2), NF, OP.dust.mat(MT.Blitz, 1));
			RM.Injector     .addRecipe1(T, 16, 16, OP.dust.mat(MT.Stone, 1)         , FL.mul(tRedstone, 2), NF, OP.dust.mat(MT.Blitz, 1));
			RM.Injector     .addRecipe1(T, 16, 16, ST.make(Blocks.sand, 1, W)       , FL.mul(tRedstone, 2), NF, OP.dust.mat(MT.Blitz, 1));
			
			RM.Injector     .addRecipe1(T, 16, 16, OP.dust.mat(MT.Obsidian, 1)      , FL.mul(tRedstone, 2), NF, OP.dust.mat(MT.Basalz, 1));
		}
		
		if (MD.TE_DYNAMICS.mLoaded) {
			OUT.println("GT_Mod: Doing Thermal Dynamics Recipes.");
			for (FluidStack tEnder : FL.array(FL.Ender.make(L), FL.Ender_TE.make(250))) {
			RM.Injector         .addRecipe1(T, 16,   80, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_64", 1, 0)    , FL.mul(tEnder,  4), NF, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_64", 1, 2));
			RM.Canner           .addRecipe1(T, 16,   80, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_64", 1, 0)    , FL.mul(tEnder,  4), NF, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_64", 1, 2));
			}
			for (FluidStack tRedstone : FL.array(FL.Redstone.make(L), FL.Redstone_TE.make(100))) {
			RM.Injector         .addRecipe1(T, 16,   16, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_0" , 1, 5)    , FL.mul(tRedstone,  2), NF, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_0" , 1, 4));
			RM.Injector         .addRecipe1(T, 16,   16, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_0" , 1, 3)    , FL.mul(tRedstone,  2), NF, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_0" , 1, 2));
			RM.Injector         .addRecipe1(T, 16,   16, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_32", 1, 0)    , FL.mul(tRedstone,  2), NF, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_32", 1, 6));
			RM.Injector         .addRecipe1(T, 16,   16, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_32", 1, 1)    , FL.mul(tRedstone,  2), NF, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_32", 1, 7));
			RM.Canner           .addRecipe1(T, 16,   16, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_0" , 1, 5)    , FL.mul(tRedstone,  2), NF, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_0" , 1, 4));
			RM.Canner           .addRecipe1(T, 16,   16, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_0" , 1, 3)    , FL.mul(tRedstone,  2), NF, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_0" , 1, 2));
			RM.Canner           .addRecipe1(T, 16,   16, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_32", 1, 0)    , FL.mul(tRedstone,  2), NF, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_32", 1, 6));
			RM.Canner           .addRecipe1(T, 16,   16, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_32", 1, 1)    , FL.mul(tRedstone,  2), NF, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_32", 1, 7));
			}
			RM.Injector         .addRecipe1(T, 16,   16, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_32", 1, 0)    , FL.Glowstone_TE.make(200), NF, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_32", 1, 2));
			RM.Injector         .addRecipe1(T, 16,   16, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_32", 1, 1)    , FL.Glowstone_TE.make(200), NF, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_32", 1, 3));
			RM.Canner           .addRecipe1(T, 16,   16, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_32", 1, 0)    , FL.Glowstone_TE.make(200), NF, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_32", 1, 2));
			RM.Canner           .addRecipe1(T, 16,   16, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_32", 1, 1)    , FL.Glowstone_TE.make(200), NF, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_32", 1, 3));
			
			RM.Injector         .addRecipe1(T, 16,   16, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_0" , 1, 7)    , MT.Cryotheum.liquid(2*U, T), NF, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_0" , 1, 6));
			RM.Canner           .addRecipe1(T, 16,   16, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_0" , 1, 7)    , MT.Cryotheum.liquid(2*U, T), NF, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_0" , 1, 6));
			
			RM.Injector         .addRecipe1(T, 16,   16, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_64", 1, 3)    , MT.Aerotheum.gas(U5, T), NF, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_64", 1, 0));
			RM.Canner           .addRecipe1(T, 16,   16, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_64", 1, 3)    , MT.Aerotheum.gas(U5, T), NF, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_64", 1, 0));
		}
		if (MD.TE.mLoaded) {
			OUT.println("GT_Mod: Doing Thermal Expansion Recipes.");
			
			for (FluidStack tEnder : FL.array(FL.Ender.make(L), FL.Ender_TE.make(250))) {
			RM.Injector         .addRecipe1(T, 16,   80, ST.make(MD.TE, "Frame", 1,10)                          , FL.mul(tEnder,  4), NF, ST.make(MD.TE, "Frame", 1,11));
			RM.Injector         .addRecipe1(T, 16,   80, ST.make(MD.TE, "Plate", 1, 0)                          , FL.mul(tEnder,  4), NF, ST.make(MD.TE, "Plate", 1, 3));
			RM.Canner           .addRecipe1(T, 16,   80, ST.make(MD.TE, "Frame", 1,10)                          , FL.mul(tEnder,  4), NF, ST.make(MD.TE, "Frame", 1,11));
			RM.Canner           .addRecipe1(T, 16,   80, ST.make(MD.TE, "Plate", 1, 0)                          , FL.mul(tEnder,  4), NF, ST.make(MD.TE, "Plate", 1, 3));
			}
			for (FluidStack tRedstone : FL.array(FL.Redstone.make(L), FL.Redstone_TE.make(100))) {
			RM.Injector         .addRecipe1(T, 16,   16, OP.spring.mat(MT.Au        , 1)                        , FL.mul(tRedstone,  2), NF, ST.make(MD.TE, "material", 1, 1));
			RM.Injector         .addRecipe1(T, 16,   16, OP.spring.mat(MT.Ag        , 1)                        , FL.mul(tRedstone,  2), NF, ST.make(MD.TE, "material", 1, 2));
			RM.Injector         .addRecipe1(T, 16,   16, OP.spring.mat(MT.Electrum  , 1)                        , FL.mul(tRedstone,  2), NF, ST.make(MD.TE, "material", 1, 3));
			RM.Injector         .addRecipe1(T, 16,   80, ST.make(MD.TE, "Plate", 1, 0)                          , FL.mul(tRedstone, 10), NF, ST.make(MD.TE, "Plate", 1, 1));
			RM.Injector         .addRecipe1(T, 16,  360, ST.make(MD.TE, "Frame", 1, 6)                          , FL.mul(tRedstone, 40), NF, ST.make(MD.TE, "Frame", 1, 7));
			RM.Injector         .addRecipe1(T, 16,  360, ST.make(MD.TE, "Frame", 1, 8)                          , FL.mul(tRedstone, 40), NF, ST.make(MD.TE, "Frame", 1, 9));
			RM.Canner           .addRecipe1(T, 16,   16, OP.spring.mat(MT.Au        , 1)                        , FL.mul(tRedstone,  2), NF, ST.make(MD.TE, "material", 1, 1));
			RM.Canner           .addRecipe1(T, 16,   16, OP.spring.mat(MT.Ag        , 1)                        , FL.mul(tRedstone,  2), NF, ST.make(MD.TE, "material", 1, 2));
			RM.Canner           .addRecipe1(T, 16,   16, OP.spring.mat(MT.Electrum  , 1)                        , FL.mul(tRedstone,  2), NF, ST.make(MD.TE, "material", 1, 3));
			RM.Canner           .addRecipe1(T, 16,   80, ST.make(MD.TE, "Plate", 1, 0)                          , FL.mul(tRedstone, 10), NF, ST.make(MD.TE, "Plate", 1, 1));
			RM.Canner           .addRecipe1(T, 16,  360, ST.make(MD.TE, "Frame", 1, 6)                          , FL.mul(tRedstone, 40), NF, ST.make(MD.TE, "Frame", 1, 7));
			RM.Canner           .addRecipe1(T, 16,  360, ST.make(MD.TE, "Frame", 1, 8)                          , FL.mul(tRedstone, 40), NF, ST.make(MD.TE, "Frame", 1, 9));
			}
			RM.Injector         .addRecipe1(T, 16,   80, ST.make(MD.TE, "Plate", 1, 0)                          , FL.Glowstone_TE.make(1000), NF, ST.make(MD.TE, "Plate", 1, 2));
			RM.Injector         .addRecipe1(T, 16,   80, ST.make(MD.TE, "Frame", 1,12)                          , FL.Glowstone_TE.make(1000), NF, ST.make(MD.TE, "Light", 1, 0));
			RM.Canner           .addRecipe1(T, 16,   80, ST.make(MD.TE, "Plate", 1, 0)                          , FL.Glowstone_TE.make(1000), NF, ST.make(MD.TE, "Plate", 1, 2));
			RM.Canner           .addRecipe1(T, 16,   80, ST.make(MD.TE, "Frame", 1,12)                          , FL.Glowstone_TE.make(1000), NF, ST.make(MD.TE, "Light", 1, 0));
			
			if (IL.IE_Slag.exists())
			RM.Mixer            .addRecipe2(T, 16,   16, IL.IE_Slag.get(2), ST.make(Blocks.dirt, 1, W), FL.Water.make(1000), NF, ST.make(Items.clay_ball, 4, 0));
			RM.Mixer            .addRecipe2(T, 16,   16, IL.TE_Slag.get(2), ST.make(Blocks.dirt, 1, W), FL.Water.make(1000), NF, ST.make(Items.clay_ball, 4, 0));
			RM.Mixer            .addRecipe2(T, 16,   16, IL.TE_Slag_Rich.get(1), ST.make(Blocks.dirt, 1, W), FL.Water.make(1000), NF, ST.make(Items.clay_ball, 4, 0));
			
			
			for (OreDictMaterial tMat : new OreDictMaterial[] {MT.KNO3, MT.NaNO3, MT.Niter}) {
			RM.Mixer            .addRecipeX(T, 16,   16, ST.array(OM.dust(tMat, U*1), IL.IE_Slag        .get(1), OM.dust(MT.Charcoal, U*1)), IL.TE_Phyto_Gro_Rich.get(32));
			RM.Mixer            .addRecipeX(T, 16,   16, ST.array(OM.dust(tMat, U*1), IL.TE_Slag_Rich   .get(1), OM.dust(MT.Charcoal, U*1)), IL.TE_Phyto_Gro_Rich.get(32));
			RM.Mixer            .addRecipeX(T, 16,   16, ST.array(OM.dust(tMat, U*1), IL.TE_Slag        .get(1), OM.dust(MT.Charcoal, U*1)), IL.TE_Phyto_Gro.get(32));
			for (OreDictMaterial tWood : ANY.Wood.mToThis) {
				if (ANY.WoodDefault.mToThis.contains(tWood)) {
					if (IL.IE_Slag.exists())
					RM.Mixer    .addRecipeX(T, 16,   16, ST.array(OM.dust(tMat, U*1), IL.IE_Slag        .get(1), OM.dust(tWood, U*2)), IL.TE_Phyto_Gro_Rich.get( 8));
					RM.Mixer    .addRecipeX(T, 16,   16, ST.array(OM.dust(tMat, U*1), IL.TE_Slag_Rich   .get(1), OM.dust(tWood, U*2)), IL.TE_Phyto_Gro_Rich.get( 8));
					RM.Mixer    .addRecipeX(T, 16,   16, ST.array(OM.dust(tMat, U*1), IL.TE_Slag        .get(1), OM.dust(tWood, U*2)), IL.TE_Phyto_Gro.get(8));
				} else {
					if (IL.IE_Slag.exists())
					RM.Mixer    .addRecipeX(T, 16,   16, ST.array(OM.dust(tMat, U*1), IL.IE_Slag        .get(1), OM.dust(tWood, U*2)), IL.TE_Phyto_Gro_Rich.get(16));
					RM.Mixer    .addRecipeX(T, 16,   16, ST.array(OM.dust(tMat, U*1), IL.TE_Slag_Rich   .get(1), OM.dust(tWood, U*2)), IL.TE_Phyto_Gro_Rich.get(16));
					RM.Mixer    .addRecipeX(T, 16,   16, ST.array(OM.dust(tMat, U*1), IL.TE_Slag        .get(1), OM.dust(tWood, U*2)), IL.TE_Phyto_Gro.get(16));
				}
			}}
			
			new OreDictListenerEvent_Names() {@Override public void addAllListeners() {
			addListener(new OreDictListenerEvent_TwoNames(OP.dust.dat(ANY.Wood), OD.slimeball) {@Override public void onOreRegistration(ItemStack aStack1, ItemStack aStack2) {
				if (IL.IE_Slag.exists())
				RM.Mixer        .addRecipeX(T, 16,   16, ST.array(aStack1, IL.IE_Slag.get(1), aStack2), ST.make(MD.TE, "florb", 4, 0));
				RM.Mixer        .addRecipeX(T, 16,   16, ST.array(aStack1, IL.TE_Slag.get(1), aStack2), ST.make(MD.TE, "florb", 4, 0));
				RM.Mixer        .addRecipeX(T, 16,   16, ST.array(aStack1, IL.TE_Slag_Rich.get(1), aStack2), ST.make(MD.TE, "florb", 4, 0));
				RM.Loom         .addRecipeX(T, 16,   16, ST.array(ST.amount(4, aStack1), ST.make(Items.string, 4, W), aStack2), ST.make(MD.TE, "Sponge", 1, 1));
			}});
			addListener(OP.dust.dat(ANY.Wood), new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
				if (IL.IE_Slag.exists())
				RM.Mixer        .addRecipeX(T, 16,   16, ST.array(aEvent.mStack, IL.IE_Slag.get(1), ST.make(Items.magma_cream, 1, W)), ST.make(MD.TE, "florb", 4, 1));
				RM.Mixer        .addRecipeX(T, 16,   16, ST.array(aEvent.mStack, IL.TE_Slag.get(1), ST.make(Items.magma_cream, 1, W)), ST.make(MD.TE, "florb", 4, 1));
				RM.Mixer        .addRecipeX(T, 16,   16, ST.array(aEvent.mStack, IL.TE_Slag_Rich.get(1), ST.make(Items.magma_cream, 1, W)), ST.make(MD.TE, "florb", 4, 1));
				RM.Loom         .addRecipeX(T, 16,   16, ST.array(ST.amount(4, aEvent.mStack), ST.make(Items.string, 4, W), ST.make(Items.magma_cream, 1, W)), ST.make(MD.TE, "Sponge", 1, 2));
			}});
			}};
		}
	}
}
