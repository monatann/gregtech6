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

import gregapi6.data.CS.FluidsGT;
import gregapi6.data.FL;
import gregapi6.data.FM;
import gregapi6.data.MT;
import gregapi6.data.OP;
import gregapi6.oredict.OreDictMaterial;
import gregapi6.util.OM;
import gregapi6.util.UT;


public class Loader_Fuels implements Runnable {
	@Override
	public void run() {
		for (OreDictMaterial tMat : new OreDictMaterial[] {MT.Charcoal, MT.Coal, MT.CoalCoke, MT.Lignite, MT.LigniteCoke, MT.Anthracite, MT.Prismane, MT.Lonsdaleite, MT.PetCoke, MT.Peat, MT.PeatBituminous}) {
		FM.FluidBed     .addRecipe1(T, -1, (tMat.mFurnaceBurnTime * 3 * EU_PER_FURNACE_TICK) *  9, OP.blockDust.mat(tMat, 1), FL.Calcite.make(648), NF, OM.dust(tMat.mTargetBurning.mMaterial, UT.Code.units(tMat.mTargetBurning.mAmount, U*2, U*9, F)));
		FM.FluidBed     .addRecipe1(T, -1, (tMat.mFurnaceBurnTime * 3 * EU_PER_FURNACE_TICK)     , OP.dust     .mat(tMat, 1), FL.Calcite.make( 72), NF, OM.dust(tMat.mTargetBurning.mMaterial, UT.Code.units(tMat.mTargetBurning.mAmount, U*2, U  , F)));
		FM.FluidBed     .addRecipe1(T, -1, (tMat.mFurnaceBurnTime * 3 * EU_PER_FURNACE_TICK) /  4, OP.dustSmall.mat(tMat, 1), FL.Calcite.make( 18), NF, OM.dust(tMat.mTargetBurning.mMaterial, UT.Code.units(tMat.mTargetBurning.mAmount, U*2, U4 , F)));
		FM.FluidBed     .addRecipe1(T, -1, (tMat.mFurnaceBurnTime * 3 * EU_PER_FURNACE_TICK) /  9, OP.dustTiny .mat(tMat, 1), FL.Calcite.make(  8), NF, OM.dust(tMat.mTargetBurning.mMaterial, UT.Code.units(tMat.mTargetBurning.mAmount, U*2, U9 , F)));
		FM.FluidBed     .addRecipe1(T, -1, (tMat.mFurnaceBurnTime * 3 * EU_PER_FURNACE_TICK) / 72, OP.dustDiv72.mat(tMat, 1), FL.Calcite.make(  1), NF, OM.dust(tMat.mTargetBurning.mMaterial, UT.Code.units(tMat.mTargetBurning.mAmount, U*2, U72, F)));
		}

		FM.Burn         .addRecipe0(T, - 64,  1, FL.make("liquid_extra_heavy_oil", 1)        , FL.make("carbondioxide", 1), ZL_IS);
		FM.Burn         .addRecipe0(T, - 48,  1, FL.make("liquid_heavy_oil", 1)              , FL.make("carbondioxide", 1), ZL_IS);
		FM.Burn         .addRecipe0(T, - 32,  1, FL.make("liquid_medium_oil", 1)             , FL.make("carbondioxide", 1), ZL_IS);
		FM.Burn         .addRecipe0(T, - 24,  1, FL.make("liquid_light_oil", 1)              , FL.make("carbondioxide", 1), ZL_IS);
		FM.Burn         .addRecipe0(T, - 32,  1, FL.make("oil", 1)                           , FL.make("carbondioxide", 1), ZL_IS);
		FM.Burn         .addRecipe0(T, - 16,  1, FL.make("creosote", 1)                      , FL.make("carbondioxide", 1), ZL_IS);
		FM.Burn         .addRecipe0(T, - 16,  1, FL.make("biomass", 1)                       , FL.make("carbondioxide", 1), ZL_IS);
		FM.Burn         .addRecipe0(T, - 16,  1, FL.make("ic2biomass", 1)                    , FL.make("carbondioxide", 1), ZL_IS);
		FM.Burn         .addRecipe0(T, -  4,  1, FL.make("glue", 1)                          , FL.make("carbondioxide", 1), ZL_IS);
		FM.Burn         .addRecipe0(T, -  4,  1, FL.Oil_Fish.make(1)                         , FL.make("carbondioxide", 1), ZL_IS);
		FM.Burn         .addRecipe0(T, -  4,  1, FL.Oil_Sunflower.make(1)                    , FL.make("carbondioxide", 1), ZL_IS);
		FM.Burn         .addRecipe0(T, -  4,  1, FL.Oil_Olive.make(1)                        , FL.make("carbondioxide", 1), ZL_IS);
		FM.Burn         .addRecipe0(T, -  4,  1, FL.Oil_Nut.make(1)                          , FL.make("carbondioxide", 1), ZL_IS);
		FM.Burn         .addRecipe0(T, -  2,  1, FL.Oil_Hemp.make(1)                         , FL.make("carbondioxide", 1), ZL_IS);
		FM.Burn         .addRecipe0(T, -  2,  1, FL.Oil_Lin.make(1)                          , FL.make("carbondioxide", 1), ZL_IS);
		FM.Burn         .addRecipe0(T, -  2,  1, FL.Oil_Seed.make(1)                         , FL.make("carbondioxide", 1), ZL_IS);
		if (FL.Oil_Canola.exists())
		FM.Burn         .addRecipe0(T, -  2,  1, FL.Oil_Canola.make(1)                       , FL.make("carbondioxide", 1), ZL_IS);
		if (FL.Oil_Plant.exists())
		FM.Burn         .addRecipe0(T, -  1,  1, FL.Oil_Plant.make(1)                        , FL.make("carbondioxide", 1), ZL_IS);
		if (FL.exists("heavyoil"))
		FM.Burn         .addRecipe0(T, - 48,  1, FL.make("heavyoil", 1)                      , FL.make("carbondioxide", 1), ZL_IS);
		if (FL.exists("hotcrude"))
		FM.Burn         .addRecipe0(T, - 32,  1, FL.make("hotcrude", 1)                      , FL.make("carbondioxide", 1), ZL_IS);
		if (FL.exists("lightoil"))
		FM.Burn         .addRecipe0(T, - 24,  1, FL.make("lightoil", 1)                      , FL.make("carbondioxide", 1), ZL_IS);

		if (FL.exists("kerosene")) {
		FM.Burn         .addRecipe0(T, - 64,  5, FL.make("kerosene", 1)                      , FL.make("carbondioxide", 1), ZL_IS);
		FM.Engine       .addRecipe0(T, - 64,  7, FL.make("kerosene", 1)                      , FL.make("carbondioxide", 1), ZL_IS);
		}
		FM.Burn         .addRecipe0(T, - 64,  5, FL.make("kerosine", 1)                      , FL.make("carbondioxide", 1), ZL_IS);
		FM.Engine       .addRecipe0(T, - 64,  7, FL.make("kerosine", 1)                      , FL.make("carbondioxide", 1), ZL_IS);

		if (FL.exists("gasoline")) {
		FM.Burn         .addRecipe0(T, - 64,  5, FL.make("gasoline", 1)                      , FL.make("carbondioxide", 1), ZL_IS);
		FM.Engine       .addRecipe0(T, - 64,  7, FL.make("gasoline", 1)                      , FL.make("carbondioxide", 1), ZL_IS);
		}
		FM.Burn         .addRecipe0(T, - 64,  5, FL.make("petrol", 1)                        , FL.make("carbondioxide", 1), ZL_IS);
		FM.Engine       .addRecipe0(T, - 64,  7, FL.make("petrol", 1)                        , FL.make("carbondioxide", 1), ZL_IS);

		FM.Burn         .addRecipe0(T, - 64,  5, FL.make("diesel", 1)                        , FL.make("carbondioxide", 1), ZL_IS);
		FM.Engine       .addRecipe0(T, - 64,  7, FL.make("diesel", 1)                        , FL.make("carbondioxide", 1), ZL_IS);

		FM.Burn         .addRecipe0(T, - 64,  6, FL.make("fuel", 1)                          , FL.make("carbondioxide", 1), ZL_IS);
		FM.Engine       .addRecipe0(T, - 64,  8, FL.make("fuel", 1)                          , FL.make("carbondioxide", 1), ZL_IS);

		FM.Burn         .addRecipe0(T, - 64,  9, FL.make("nitrofuel", 1)                     , FL.make("carbondioxide", 1), ZL_IS);
		FM.Engine       .addRecipe0(T, - 64, 12, FL.make("nitrofuel", 1)                     , FL.make("carbondioxide", 1), ZL_IS);

		for (String tAlcohol : FluidsGT.RUM) if (FL.exists(tAlcohol)) {
		FM.Burn         .addRecipe0(T, - 16,  6, FL.make(tAlcohol, 1)                        , FL.make("carbondioxide", 1), ZL_IS);
		FM.Engine       .addRecipe0(T, - 16,  9, FL.make(tAlcohol, 1)                        , FL.make("carbondioxide", 1), ZL_IS);
		}
		for (String tAlcohol : FluidsGT.WHISKEY) if (FL.exists(tAlcohol)) {
		FM.Burn         .addRecipe0(T, - 16,  6, FL.make(tAlcohol, 1)                        , FL.make("carbondioxide", 1), ZL_IS);
		FM.Engine       .addRecipe0(T, - 16,  9, FL.make(tAlcohol, 1)                        , FL.make("carbondioxide", 1), ZL_IS);
		}
		for (String tAlcohol : FluidsGT.VINEGAR) if (FL.exists(tAlcohol)) {
		FM.Burn         .addRecipe0(T, - 16,  6, FL.make(tAlcohol, 1)                        , FL.make("carbondioxide", 1), ZL_IS);
		FM.Engine       .addRecipe0(T, - 16,  9, FL.make(tAlcohol, 1)                        , FL.make("carbondioxide", 1), ZL_IS);
		}
		for (String tAlcohol : new String[] {"vodka", "binnie.vodka"}) if (FL.exists(tAlcohol)) {
		FM.Burn         .addRecipe0(T, - 16,  6, FL.make(tAlcohol, 1)                        , FL.make("carbondioxide", 1), ZL_IS);
		FM.Engine       .addRecipe0(T, - 16,  9, FL.make(tAlcohol, 1)                        , FL.make("carbondioxide", 1), ZL_IS);
		}
		for (String tAlcohol : new String[] {"potion.dragonblood", "potion.goldencider", "potion.notchesbrew"}) if (FL.exists(tAlcohol)) {
		FM.Burn         .addRecipe0(T, - 16, 12, FL.make(tAlcohol, 1)                        , FL.make("carbondioxide", 1), ZL_IS);
		FM.Engine       .addRecipe0(T, - 16, 18, FL.make(tAlcohol, 1)                        , FL.make("carbondioxide", 1), ZL_IS);
		}

		FM.Burn         .addRecipe0(T, - 16,  9, FL.make("bioethanol", 1)                    , FL.make("carbondioxide", 1), ZL_IS);
		FM.Engine       .addRecipe0(T, - 16, 12, FL.make("bioethanol", 1)                    , FL.make("carbondioxide", 1), ZL_IS);

		if (FL.BioDiesel.exists()) {
		FM.Burn         .addRecipe0(T, - 16,  9, FL.make("biodiesel", 1)                     , FL.make("carbondioxide", 1), ZL_IS);
		FM.Engine       .addRecipe0(T, - 16, 12, FL.make("biodiesel", 1)                     , FL.make("carbondioxide", 1), ZL_IS);
		}
		if (FL.BioFuel.exists()) {
		FM.Burn         .addRecipe0(T, - 64,  9, FL.make("biofuel", 1)                       , FL.make("carbondioxide", 1), ZL_IS);
		FM.Engine       .addRecipe0(T, - 64, 12, FL.make("biofuel", 1)                       , FL.make("carbondioxide", 1), ZL_IS);
		}
		if (FL.exists("hootch")) {
		FM.Burn         .addRecipe0(T, - 16,  5, FL.make("hootch", 1)                        , FL.make("carbondioxide", 1), ZL_IS);
		FM.Engine       .addRecipe0(T, - 16,  6, FL.make("hootch", 1)                        , FL.make("carbondioxide", 1), ZL_IS);
		}
		if (FL.exists("fire_water")) {
		FM.Burn         .addRecipe0(T, - 32,  9, FL.make("fire_water", 1)                    , FL.make("carbondioxide", 1), ZL_IS);
		FM.Engine       .addRecipe0(T, - 32, 10, FL.make("fire_water", 1)                    , FL.make("carbondioxide", 1), ZL_IS);
		}
		if (FL.exists("rocket_fuel")) {
		FM.Burn         .addRecipe0(T, - 64,  4, FL.make("rocket_fuel", 1)                   , FL.make("carbondioxide", 1), ZL_IS);
		FM.Engine       .addRecipe0(T, - 64,  5, FL.make("rocket_fuel", 1)                   , FL.make("carbondioxide", 1), ZL_IS);
		}



		FM.Burn         .addRecipe0(T, - 16,  1, FL.make("hydrogen", 2)                      , FL.Water.make(3), ZL_IS);
		FM.Gas          .addRecipe0(T, - 16,  2, FL.make("hydrogen", 2)                      , FL.Water.make(3), ZL_IS);

		FM.Burn         .addRecipe0(T, - 64, 24, FL.make("methane", 5)                       , FL.Water.make(6), FL.make("carbondioxide", 3));
		FM.Burn         .addRecipe0(T, - 64, 24, FL.make("gas_natural_gas", 5)               , FL.Water.make(6), FL.make("carbondioxide", 3));
		FM.Gas          .addRecipe0(T, - 64, 30, FL.make("methane", 5)                       , FL.Water.make(6), FL.make("carbondioxide", 3));
		FM.Gas          .addRecipe0(T, - 64, 30, FL.make("gas_natural_gas", 5)               , FL.Water.make(6), FL.make("carbondioxide", 3));
		if (FL.exists("naturalgas")) {
		FM.Burn         .addRecipe0(T, - 64, 24, FL.make("naturalgas", 5)                    , FL.Water.make(6), FL.make("carbondioxide", 3));
		FM.Gas          .addRecipe0(T, - 64, 30, FL.make("naturalgas", 5)                    , FL.Water.make(6), FL.make("carbondioxide", 3));
		}
		if (FL.exists("gas.natural")) {
		FM.Burn         .addRecipe0(T, - 64, 24, FL.make("gas.natural", 5)                   , FL.Water.make(6), FL.make("carbondioxide", 3));
		FM.Gas          .addRecipe0(T, - 64, 30, FL.make("gas.natural", 5)                   , FL.Water.make(6), FL.make("carbondioxide", 3));
		}
		if (FL.exists("ic2biogas")) {
		FM.Burn         .addRecipe0(T, - 64, 24, FL.make("ic2biogas", 20)                    , FL.Water.make(6), FL.make("carbondioxide", 3));
		FM.Gas          .addRecipe0(T, - 64, 30, FL.make("ic2biogas", 20)                    , FL.Water.make(6), FL.make("carbondioxide", 3));
		}
		if (FL.LPG.exists()) {
		FM.Burn         .addRecipe0(T, - 64, 42, FL.LPG.make(7)                                     , FL.Water.make(7), FL.make("carbondioxide", 6));
		}
		
		FM.Burn         .addRecipe0(T, - 64, 42, FL.make("butane", 7)                        , FL.Water.make(7), FL.make("carbondioxide", 6));
		FM.Gas          .addRecipe0(T, - 64, 56, FL.make("butane", 7)                        , FL.Water.make(7), FL.make("carbondioxide", 6));
		
		FM.Burn         .addRecipe0(T, - 64, 30, FL.make("propane", 5)                       , FL.Water.make(5), FL.make("carbondioxide", 4));
		FM.Gas          .addRecipe0(T, - 64, 40, FL.make("propane", 5)                       , FL.Water.make(5), FL.make("carbondioxide", 4));
		
		FM.Burn         .addRecipe0(T, - 64,  4, FL.make("ethylene", 1)                      , FL.Water.make(1), FL.make("carbondioxide", 1));
		FM.Gas          .addRecipe0(T, - 64,  5, FL.make("ethylene", 1)                      , FL.Water.make(1), FL.make("carbondioxide", 1));
		
		FM.Burn         .addRecipe0(T, - 64,  3, FL.make("propylene", 1)                     , FL.Water.make(1), FL.make("carbondioxide", 1));
		FM.Gas          .addRecipe0(T, - 64,  4, FL.make("propylene", 1)                     , FL.Water.make(1), FL.make("carbondioxide", 1));
		
		FM.Hot          .addRecipe0(T, - 16, EU_PER_LAVA/16, FL.Lava.make(1)                 , FL.Lava_Pahoehoe.make(1), ZL_IS);
		if (FL.Water_Hot.exists())
		FM.Hot          .addRecipe0(T, -  2,  1, FL.Water_Hot.make(1)                        , FL.Water.make(1), ZL_IS);
		FM.Hot          .addRecipe0(T, -EU_PER_COOLANT, 1, FL.Coolant_IC2_Hot.make(1)        , FL.Coolant_IC2.make(1), ZL_IS);
		
		FM.Turbine      .addRecipe0(T, - 16,  5, FL.Steam.make(160)                          , FL.DistW.make(1), ZL_IS);
//      FM.Turbine      .addRecipe0(T, - 10,  5, FL.Steam_IC2.make(100)                      , FL.DistW.make(1), ZL_IS);
//      FM.Turbine      .addRecipe0(T, - 30,  5, FL.Steam_IC2_Superheated.make(100)          , FL.DistW.make(1), ZL_IS);
		
		
		
		FM.Magic        .addRecipe0(T, - 64, 64, FL.make("mercury", 1));
		
		
//      new Recipe(new ItemStack(Items.lava_bucket), new ItemStack(Blocks.obsidian), OM.get(OP.ingot, MT.Copper, 1), OM.get(OP.ingot, MT.Tin, 1), OM.get(OP.ingot, MT.Electrum, 1), 30, 2);

//      RecipeMap.sSmallNaquadahReactorFuels.addRecipe(T, ST.array(OM.get(OP.bolt , MT.Nq_528, 1)}, ST.array(OM.get(OP.bolt , MT.Nq     , 1)}, null, null, null, null, 0, 0,  25000);
//      RecipeMap.sLargeNaquadahReactorFuels.addRecipe(T, ST.array(OM.get(OP.ingot, MT.Nq_528, 1)}, ST.array(OM.get(OP.ingot, MT.Nq     , 1)}, null, null, null, null, 0, 0, 200000);
//      RecipeMap.sFluidNaquadahReactorFuels.addRecipe(T, ST.array(OM.get(OP.cell , MT.Nq_528, 1)}, ST.array(OM.get(OP.cell , MT.Empty  , 1)}, null, null, null, null, 0, 0, 200000);

//      RA.addFuel(GT_ModHandler.getModItem(ModIDs.TC, "ItemResource", 1, 4)                , null,      4, 5);
//      RA.addFuel(new ItemStack(Items.experience_bottle, 1)                                , null,     10, 5);
//      RA.addFuel(new ItemStack(Items.ghast_tear, 1)                                       , null,    500, 5);
//      RA.addFuel(new ItemStack(Blocks.beacon, 1)                                          , null, MT.NetherStar.mFuelPower * 2, MT.NetherStar.mFuelType);
	}
}
