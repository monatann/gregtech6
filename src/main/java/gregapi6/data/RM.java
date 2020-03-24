/**
 * Copyright (c) 2020 Gregorius Techneticies
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

package gregapi6.data;

import static gregapi6.data.CS.*;

import java.util.Iterator;
import java.util.Map.Entry;

import appeng.api.AEApi;
import cpw.mods.fml.common.event.FMLInterModComms;
import gregapi6.code.IItemContainer;
import gregapi6.config.ConfigCategories;
import gregapi6.data.CS.ConfigsGT;
import gregapi6.data.CS.FluidsGT;
import gregapi6.data.CS.FoodsGT;
import gregapi6.data.CS.ItemsGT;
import gregapi6.item.multiitem.MultiItemRandom;
import gregapi6.oredict.OreDictMaterial;
import gregapi6.recipes.Recipe.RecipeMap;
import gregapi6.recipes.maps.RecipeMapAssembler;
import gregapi6.recipes.maps.RecipeMapAutocrafting;
import gregapi6.recipes.maps.RecipeMapBath;
import gregapi6.recipes.maps.RecipeMapBumblelyzer;
import gregapi6.recipes.maps.RecipeMapChisel;
import gregapi6.recipes.maps.RecipeMapFluidCanner;
import gregapi6.recipes.maps.RecipeMapFormingPress;
import gregapi6.recipes.maps.RecipeMapFurnace;
import gregapi6.recipes.maps.RecipeMapHammer;
import gregapi6.recipes.maps.RecipeMapMicrowave;
import gregapi6.recipes.maps.RecipeMapPlantalyzer;
import gregapi6.recipes.maps.RecipeMapPrinter;
import gregapi6.recipes.maps.RecipeMapReplicator;
import gregapi6.recipes.maps.RecipeMapScannerMolecular;
import gregapi6.recipes.maps.RecipeMapScannerVisuals;
import gregapi6.recipes.maps.RecipeMapShredder;
import gregapi6.recipes.maps.RecipeMapUnboxinator;
import gregapi6.util.CR;
import gregapi6.util.OM;
import gregapi6.util.ST;
import gregapi6.util.UT;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author Gregorius Techneticies
 *
 * Class containing Recipe Maps, for Fuel Maps go into gregapi6.data.FM
 */
@SuppressWarnings("deprecation")
public class RM {
	public static final RecipeMap
	  Furnace                   = new RecipeMapFurnace              (null, "mc.recipe.furnace"                      , "Furnace"                         , "smelting", 0, 1, RES_PATH_GUI+"machines/Oven"                ,/*IN-OUT-MIN-ITEM=*/ 1, 1, 1,/*IN-OUT-MIN-FLUID=*/ 0, 0, 0,/*MIN*/ 0,/*AMP=*/ 1, ""                    ,    1, ""      , T, F, F, T, F)
	, Microwave                 = new RecipeMapMicrowave            (null, "gt6.recipe.microwave"                    , "Microwave"                       , "smelting", 0, 1, RES_PATH_GUI+"machines/Oven"                ,/*IN-OUT-MIN-ITEM=*/ 1, 1, 1,/*IN-OUT-MIN-FLUID=*/ 0, 0, 0,/*MIN*/ 0,/*AMP=*/ 1, ""                    ,    1, ""      , T, F, F, T, F)

	, BedrockOreList            = new RecipeMap                     (null, "gt6.recipe.bedrockorelist"               , "Bedrock Drill"                   , null, 0, 1, RES_PATH_GUI+"machines/BedrockOreList"            ,/*IN-OUT-MIN-ITEM=*/ 1,12, 1,/*IN-OUT-MIN-FLUID=*/ 0, 0, 0,/*MIN*/ 0,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, F, T, F, 0)
	, ByProductList             = new RecipeMap                     (null, "gt6.recipe.byproductlist"                , "Ore Byproduct List"              , null, 0, 1, RES_PATH_GUI+"machines/OreByproducts"             ,/*IN-OUT-MIN-ITEM=*/ 6,12, 1,/*IN-OUT-MIN-FLUID=*/ 0, 0, 0,/*MIN*/ 0,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, F, T, F, 0)
	, CrucibleSmelting          = new RecipeMap                     (null, "gt6.recipe.cruciblesmelting"             , "Crucible Smelting"               , null, 0, 1, RES_PATH_GUI+"machines/Default"                   ,/*IN-OUT-MIN-ITEM=*/ 6, 6, 1,/*IN-OUT-MIN-FLUID=*/ 0, 0, 0,/*MIN*/ 0,/*AMP=*/ 1, "Temperature: "       ,    1, " K"    , T, T, F, T, F, 0)
	, CrucibleAlloying          = new RecipeMap                     (null, "gt6.recipe.cruciblealloying"             , "Combination Smelting"            , null, 0, 1, RES_PATH_GUI+"machines/Default"                   ,/*IN-OUT-MIN-ITEM=*/ 6, 6, 1,/*IN-OUT-MIN-FLUID=*/ 0, 0, 0,/*MIN*/ 0,/*AMP=*/ 1, "Temperature: "       ,    1, " K"    , T, T, F, T, F, 0)
	, BumbleQueens              = new RecipeMap                     (null, "gt6.recipe.bumblequeen"                  , "Bumblebee Queen"                 , null, 0, 1, RES_PATH_GUI+"machines/Default"                   ,/*IN-OUT-MIN-ITEM=*/ 2, 6, 0,/*IN-OUT-MIN-FLUID=*/ 0, 0, 0,/*MIN*/ 1,/*AMP=*/ 1, ""                    ,    1, ""      , F, T, F, T, F, 0)
	, Trees                     = new RecipeMap                     (null, "gt6.recipe.trees"                        , "Family Tree"                     , null, 0, 1, RES_PATH_GUI+"machines/Default"                   ,/*IN-OUT-MIN-ITEM=*/ 2, 6, 0,/*IN-OUT-MIN-FLUID=*/ 0, 0, 0,/*MIN*/ 1,/*AMP=*/ 1, ""                    ,    1, ""      , F, T, F, F, F, 0)
	, Other                     = new RecipeMap                     (null, "gt6.recipe.other"                        , "Other Relations"                 , null, 0, 1, RES_PATH_GUI+"machines/Default"                   ,/*IN-OUT-MIN-ITEM=*/ 6, 6, 0,/*IN-OUT-MIN-FLUID=*/ 3, 3, 0,/*MIN*/ 1,/*AMP=*/ 1, ""                    ,    1, ""      , F, T, F, F, F, 0)

	, Generifier                = new RecipeMap                     (null, "gt6.recipe.generifier"                   , "Generifier"                      , null, 0, 1, RES_PATH_GUI+"machines/Generifier"                ,/*IN-OUT-MIN-ITEM=*/ 1, 1, 0,/*IN-OUT-MIN-FLUID=*/ 1, 1, 0,/*MIN*/ 1,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, Boxinator                 = new RecipeMap                     (null, "gt6.recipe.boxinator"                    , "Boxinator"                       , null, 0, 1, RES_PATH_GUI+"machines/Boxinator"                 ,/*IN-OUT-MIN-ITEM=*/ 2, 1, 2,/*IN-OUT-MIN-FLUID=*/ 0, 0, 0,/*MIN*/ 0,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, Unboxinator               = new RecipeMapUnboxinator          (null, "gt6.recipe.unboxinator"                  , "Unboxinator"                     , null, 0, 1, RES_PATH_GUI+"machines/Unboxinator"               ,/*IN-OUT-MIN-ITEM=*/ 1,12, 1,/*IN-OUT-MIN-FLUID=*/ 0, 0, 0,/*MIN*/ 0,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F)
	, Sharpening                = new RecipeMap                     (null, "gt6.recipe.sharpener"                    , "Sharpener"                       , null, 0, 1, RES_PATH_GUI+"machines/Sharpener"                 ,/*IN-OUT-MIN-ITEM=*/ 1, 2, 1,/*IN-OUT-MIN-FLUID=*/ 0, 0, 0,/*MIN*/ 0,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, Sifting                   = new RecipeMap                     (null, "gt6.recipe.sifter"                       , "Sifter"                          , null, 2, 1, RES_PATH_GUI+"machines/Sifter"                    ,/*IN-OUT-MIN-ITEM=*/ 1,12, 1,/*IN-OUT-MIN-FLUID=*/ 0, 0, 0,/*MIN*/ 0,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, Anvil                     = new RecipeMap                     (null, "gt6.recipe.anvil"                        , "Anvil"                           , null, 2, 1, RES_PATH_GUI+"machines/Anvil"                     ,/*IN-OUT-MIN-ITEM=*/ 2, 2, 2,/*IN-OUT-MIN-FLUID=*/ 0, 0, 0,/*MIN*/ 0,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, AnvilBendSmall            = new RecipeMap                     (null, "gt6.recipe.anvil.bend.small"             , "Anvil Bending (Small)"           , null, 2, 1, RES_PATH_GUI+"machines/Anvil"                     ,/*IN-OUT-MIN-ITEM=*/ 2, 2, 2,/*IN-OUT-MIN-FLUID=*/ 0, 0, 0,/*MIN*/ 0,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, AnvilBendBig              = new RecipeMap                     (null, "gt6.recipe.anvil.bend.big"               , "Anvil Bending (Big)"             , null, 2, 1, RES_PATH_GUI+"machines/Anvil"                     ,/*IN-OUT-MIN-ITEM=*/ 2, 2, 2,/*IN-OUT-MIN-FLUID=*/ 0, 0, 0,/*MIN*/ 0,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, Hammer                    = new RecipeMapHammer               (null, "gt6.recipe.hammer"                       , "Hammer"                          , null, 6, 3, RES_PATH_GUI+"machines/Hammer"                    ,/*IN-OUT-MIN-ITEM=*/ 1, 1, 1,/*IN-OUT-MIN-FLUID=*/ 0, 0, 0,/*MIN*/ 0,/*AMP=*/ 1, ""                    ,    1, ""      , T, F, T, T, F)
	, Chisel                    = new RecipeMapChisel               (null, "gt6.recipe.chisel"                       , "Chisel"                          , null, 0, 1, RES_PATH_GUI+"machines/Chisel"                    ,/*IN-OUT-MIN-ITEM=*/ 1, 1, 1,/*IN-OUT-MIN-FLUID=*/ 0, 0, 0,/*MIN*/ 0,/*AMP=*/ 1, ""                    ,    1, ""      , T, F, T, T, F)
	, Shredder                  = new RecipeMapShredder             (null, "gt6.recipe.shredder"                     , "Shredder"                        , null, 0, 1, RES_PATH_GUI+"machines/Shredder"                  ,/*IN-OUT-MIN-ITEM=*/ 1, 6, 1,/*IN-OUT-MIN-FLUID=*/ 0, 0, 0,/*MIN*/ 0,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F)
	, Crusher                   = new RecipeMap                     (null, "gt6.recipe.crusher"                      , "Crusher"                         , null, 0, 1, RES_PATH_GUI+"machines/Crusher"                   ,/*IN-OUT-MIN-ITEM=*/ 1, 6, 1,/*IN-OUT-MIN-FLUID=*/ 0, 0, 0,/*MIN*/ 0,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, Lathe                     = new RecipeMap                     (null, "gt6.recipe.lathe"                        , "Lathe"                           , null, 0, 1, RES_PATH_GUI+"machines/Lathe"                     ,/*IN-OUT-MIN-ITEM=*/ 1, 2, 1,/*IN-OUT-MIN-FLUID=*/ 0, 0, 0,/*MIN*/ 0,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, Cutter                    = new RecipeMap                     (null, "gt6.recipe.cutter"                       , "Cutter"                          , null, 0, 1, RES_PATH_GUI+"machines/Cutter"                    ,/*IN-OUT-MIN-ITEM=*/ 9, 3, 1,/*IN-OUT-MIN-FLUID=*/ 3, 0, 1,/*MIN*/ 0,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, Debarker                  = new RecipeMap                     (null, "gt6.recipe.debarker"                     , "Debarker"                        , null, 0, 1, RES_PATH_GUI+"machines/Debarker"                  ,/*IN-OUT-MIN-ITEM=*/ 1, 2, 1,/*IN-OUT-MIN-FLUID=*/ 1, 0, 1,/*MIN*/ 0,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, Coagulator                = new RecipeMap                     (null, "gt6.recipe.coagulator"                   , "Coagulator"                      , null, 0, 1, RES_PATH_GUI+"machines/Coagulator"                ,/*IN-OUT-MIN-ITEM=*/ 0, 1, 0,/*IN-OUT-MIN-FLUID=*/ 1, 0, 1,/*MIN*/ 0,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, Squeezer                  = new RecipeMap                     (null, "gt6.recipe.squeezer"                     , "Squeezer"                        , null, 0, 1, RES_PATH_GUI+"machines/Squeezer"                  ,/*IN-OUT-MIN-ITEM=*/ 1, 1, 1,/*IN-OUT-MIN-FLUID=*/ 0, 1, 0,/*MIN*/ 0,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, Juicer                    = new RecipeMap                     (null, "gt6.recipe.juicer"                       , "Juicer"                          , null, 0, 1, RES_PATH_GUI+"machines/Juicer"                    ,/*IN-OUT-MIN-ITEM=*/ 1, 1, 1,/*IN-OUT-MIN-FLUID=*/ 0, 1, 0,/*MIN*/ 0,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, Mortar                    = new RecipeMap                     (null, "gt6.recipe.mortar"                       , "Mortar"                          , null, 0, 1, RES_PATH_GUI+"machines/Mortar"                    ,/*IN-OUT-MIN-ITEM=*/ 1, 2, 1,/*IN-OUT-MIN-FLUID=*/ 0, 0, 0,/*MIN*/ 0,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, Compressor                = new RecipeMap                     (null, "gt6.recipe.compressor"                   , "Compressor"                      , null, 0, 1, RES_PATH_GUI+"machines/Compressor"                ,/*IN-OUT-MIN-ITEM=*/ 1, 1, 1,/*IN-OUT-MIN-FLUID=*/ 0, 0, 0,/*MIN*/ 0,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, Centrifuge                = new RecipeMap                     (null, "gt6.recipe.centrifuge"                   , "Centrifuge"                      , null, 0, 1, RES_PATH_GUI+"machines/Centrifuge"                ,/*IN-OUT-MIN-ITEM=*/ 1, 6, 0,/*IN-OUT-MIN-FLUID=*/ 1, 6, 0,/*MIN*/ 0,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, Electrolyzer              = new RecipeMap                     (null, "gt6.recipe.electrolyzer"                 , "Electrolyzer"                    , null, 0, 1, RES_PATH_GUI+"machines/Electrolyzer"              ,/*IN-OUT-MIN-ITEM=*/ 2, 6, 1,/*IN-OUT-MIN-FLUID=*/ 2, 6, 0,/*MIN*/ 2,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, Lightning                 = new RecipeMap                     (null, "gt6.recipe.lightning"                    , "Lightning Processor"             , null, 0, 1, RES_PATH_GUI+"machines/Lightning"                 ,/*IN-OUT-MIN-ITEM=*/ 6, 6, 0,/*IN-OUT-MIN-FLUID=*/ 6, 6, 0,/*MIN*/ 2,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, ImplosionCompressor       = new RecipeMap                     (null, "gt6.recipe.implosioncompressor"          , "Implosion Compressor"            , null, 0, 1, RES_PATH_GUI+"machines/ImplosionCompressor"       ,/*IN-OUT-MIN-ITEM=*/ 3, 3, 3,/*IN-OUT-MIN-FLUID=*/ 0, 0, 0,/*MIN*/ 0,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, RollingMill               = new RecipeMap                     (null, "gt6.recipe.rollingmill"                  , "Rolling Mill"                    , null, 0, 1, RES_PATH_GUI+"machines/RollingMill"               ,/*IN-OUT-MIN-ITEM=*/ 1, 1, 1,/*IN-OUT-MIN-FLUID=*/ 0, 0, 0,/*MIN*/ 0,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, RollBender                = new RecipeMap                     (null, "gt6.recipe.rollbender"                   , "Roll Bender"                     , null, 0, 1, RES_PATH_GUI+"machines/RollBender"                ,/*IN-OUT-MIN-ITEM=*/ 1, 1, 1,/*IN-OUT-MIN-FLUID=*/ 0, 0, 0,/*MIN*/ 0,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, RollFormer                = new RecipeMap                     (null, "gt6.recipe.rollformer"                   , "Roll Former"                     , null, 0, 1, RES_PATH_GUI+"machines/RollFormer"                ,/*IN-OUT-MIN-ITEM=*/ 1, 1, 1,/*IN-OUT-MIN-FLUID=*/ 0, 0, 0,/*MIN*/ 0,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, ClusterMill               = new RecipeMap                     (null, "gt6.recipe.clustermill"                  , "Cluster Mill"                    , null, 0, 1, RES_PATH_GUI+"machines/ClusterMill"               ,/*IN-OUT-MIN-ITEM=*/ 1, 1, 1,/*IN-OUT-MIN-FLUID=*/ 0, 0, 0,/*MIN*/ 0,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, Wiremill                  = new RecipeMap                     (null, "gt6.recipe.wiremill"                     , "Wiremill"                        , null, 0, 1, RES_PATH_GUI+"machines/Wiremill"                  ,/*IN-OUT-MIN-ITEM=*/ 1, 1, 1,/*IN-OUT-MIN-FLUID=*/ 0, 0, 0,/*MIN*/ 0,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, Mixer                     = new RecipeMap                     (null, "gt6.recipe.mixer"                        , "Mixer"                           , null, 0, 1, RES_PATH_GUI+"machines/Mixer"                     ,/*IN-OUT-MIN-ITEM=*/ 6, 1, 0,/*IN-OUT-MIN-FLUID=*/ 6, 2, 0,/*MIN*/ 2,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, HeatMixer                 = Mixer
	, BurnMixer                 = new RecipeMap                     (null, "gt6.recipe.burnmixer"                    , "Burner Mixer"                    , null, 0, 1, RES_PATH_GUI+"machines/BurnMixer"                 ,/*IN-OUT-MIN-ITEM=*/ 6, 1, 0,/*IN-OUT-MIN-FLUID=*/ 6, 2, 0,/*MIN*/ 2,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, CryoMixer                 = new RecipeMap                     (null, "gt6.recipe.cryomixer"                    , "Cryo Mixer"                      , null, 0, 1, RES_PATH_GUI+"machines/CryoMixer"                 ,/*IN-OUT-MIN-ITEM=*/ 6, 1, 0,/*IN-OUT-MIN-FLUID=*/ 6, 2, 0,/*MIN*/ 2,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, Freezer                   = new RecipeMap                     (null, "gt6.recipe.freezer"                      , "Freezer"                         , null, 0, 1, RES_PATH_GUI+"machines/Freezer"                   ,/*IN-OUT-MIN-ITEM=*/ 1, 1, 0,/*IN-OUT-MIN-FLUID=*/ 1, 1, 0,/*MIN*/ 1,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, Canner                    = new RecipeMapFluidCanner          (null, "gt6.recipe.canner"                       , "Canning Machine"                 , null, 0, 1, RES_PATH_GUI+"machines/Canner"                    ,/*IN-OUT-MIN-ITEM=*/ 2, 2, 1,/*IN-OUT-MIN-FLUID=*/ 1, 1, 0,/*MIN*/ 1,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F)
	, Injector                  = new RecipeMap                     (null, "gt6.recipe.injector"                     , "Injector"                        , null, 0, 1, RES_PATH_GUI+"machines/Injector"                  ,/*IN-OUT-MIN-ITEM=*/ 2, 1, 0,/*IN-OUT-MIN-FLUID=*/ 2, 1, 0,/*MIN*/ 2,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, Calciner                  = new RecipeMap                     (null, "gt6.recipe.calciner"                     , "Calciner"                        , null, 0, 1, RES_PATH_GUI+"machines/Calciner"                  ,/*IN-OUT-MIN-ITEM=*/ 3, 3, 0,/*IN-OUT-MIN-FLUID=*/ 3, 3, 0,/*MIN*/ 2,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, Roasting                  = new RecipeMap                     (null, "gt6.recipe.roaster"                      , "Roaster"                         , null, 0, 1, RES_PATH_GUI+"machines/Roaster"                   ,/*IN-OUT-MIN-ITEM=*/ 1, 3, 1,/*IN-OUT-MIN-FLUID=*/ 1, 1, 1,/*MIN*/ 2,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, Drying                    = new RecipeMap                     (null, "gt6.recipe.drying"                       , "Dryer"                           , null, 0, 1, RES_PATH_GUI+"machines/Dryer"                     ,/*IN-OUT-MIN-ITEM=*/ 1, 1, 0,/*IN-OUT-MIN-FLUID=*/ 1, 1, 0,/*MIN*/ 1,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, Fermenter                 = new RecipeMap                     (null, "gt6.recipe.fermenter"                    , "Fermenter"                       , null, 0, 1, RES_PATH_GUI+"machines/Fermenter"                 ,/*IN-OUT-MIN-ITEM=*/ 1, 1, 1,/*IN-OUT-MIN-FLUID=*/ 1, 1, 0,/*MIN*/ 1,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, Distillery                = new RecipeMap                     (null, "gt6.recipe.distillery"                   , "Distillery"                      , null, 0, 1, RES_PATH_GUI+"machines/Distillery"                ,/*IN-OUT-MIN-ITEM=*/ 1, 2, 1,/*IN-OUT-MIN-FLUID=*/ 1, 2, 1,/*MIN*/ 2,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, Extruder                  = new RecipeMap                     (null, "gt6.recipe.extruder"                     , "Extruder"                        , null, 0, 1, RES_PATH_GUI+"machines/Extruder"                  ,/*IN-OUT-MIN-ITEM=*/ 2, 1, 2,/*IN-OUT-MIN-FLUID=*/ 0, 0, 0,/*MIN*/ 0,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, Polarizer                 = new RecipeMap                     (null, "gt6.recipe.polarizer"                    , "Polarizer"                       , null, 0, 1, RES_PATH_GUI+"machines/Polarizer"                 ,/*IN-OUT-MIN-ITEM=*/ 1, 1, 1,/*IN-OUT-MIN-FLUID=*/ 0, 0, 0,/*MIN*/ 0,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, Loom                      = new RecipeMap                     (null, "gt6.recipe.loom"                         , "Loom"                            , null, 0, 1, RES_PATH_GUI+"machines/Loom"                      ,/*IN-OUT-MIN-ITEM=*/ 6, 1, 1,/*IN-OUT-MIN-FLUID=*/ 0, 0, 0,/*MIN*/ 0,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, Cooking                   = new RecipeMap                     (null, "gt6.recipe.cooker"                       , "Cooker"                          , null, 0, 1, RES_PATH_GUI+"machines/Cooker"                    ,/*IN-OUT-MIN-ITEM=*/ 9, 1, 1,/*IN-OUT-MIN-FLUID=*/ 3, 1, 1,/*MIN*/ 2,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, Press                     = new RecipeMapFormingPress         (null, "gt6.recipe.press"                        , "Press"                           , null, 0, 1, RES_PATH_GUI+"machines/Press"                     ,/*IN-OUT-MIN-ITEM=*/ 9, 1, 2,/*IN-OUT-MIN-FLUID=*/ 3, 0, 0,/*MIN*/ 0,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F)
	, Bath                      = new RecipeMapBath                 (null, "gt6.recipe.bath"                         , "Bath"                            , null, 0, 1, RES_PATH_GUI+"machines/Bath"                      ,/*IN-OUT-MIN-ITEM=*/ 6, 6, 1,/*IN-OUT-MIN-FLUID=*/ 1, 3, 1,/*MIN*/ 2,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F)
	, Smelter                   = new RecipeMap                     (null, "gt6.recipe.smelter"                      , "Smelter"                         , null, 0, 1, RES_PATH_GUI+"machines/Smelter"                   ,/*IN-OUT-MIN-ITEM=*/ 1, 1, 0,/*IN-OUT-MIN-FLUID=*/ 1, 1, 0,/*MIN*/ 1,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, LaserEngraver             = new RecipeMap                     (null, "gt6.recipe.laserengraver"                , "Precision Laser Engraver"        , null, 0, 1, RES_PATH_GUI+"machines/LaserEngraver"             ,/*IN-OUT-MIN-ITEM=*/ 3, 1, 2,/*IN-OUT-MIN-FLUID=*/ 0, 0, 0,/*MIN*/ 2,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, Welder                    = new RecipeMap                     (null, "gt6.recipe.welder"                       , "Welding Machine"                 , null, 0, 1, RES_PATH_GUI+"machines/Welder"                    ,/*IN-OUT-MIN-ITEM=*/ 9, 1, 2,/*IN-OUT-MIN-FLUID=*/ 1, 0, 0,/*MIN*/ 2,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, CrystallisationCrucible   = new RecipeMap                     (null, "gt6.recipe.crystallisationcrucible"      , "Crystallisation Crucible"        , null, 0, 1, RES_PATH_GUI+"machines/CrystallisationCrucible"   ,/*IN-OUT-MIN-ITEM=*/ 9, 1, 1,/*IN-OUT-MIN-FLUID=*/ 3, 0, 1,/*MIN*/ 1,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, Plantalyzer               = new RecipeMapPlantalyzer          (null, "gt6.recipe.plantalyzer"                  , "Plantalyzer"                     , null, 0, 1, RES_PATH_GUI+"machines/Plantalyzer"               ,/*IN-OUT-MIN-ITEM=*/ 2, 2, 0,/*IN-OUT-MIN-FLUID=*/ 1, 0, 0,/*MIN*/ 1,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F)
	, Bumblelyzer               = new RecipeMapBumblelyzer          (null, "gt6.recipe.bumblelyzer"                  , "Bumblelyzer"                     , null, 0, 1, RES_PATH_GUI+"machines/Bumblelyzer"               ,/*IN-OUT-MIN-ITEM=*/ 2, 2, 0,/*IN-OUT-MIN-FLUID=*/ 1, 0, 0,/*MIN*/ 2,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F)
	, ScannerVisuals            = new RecipeMapScannerVisuals       (null, "gt6.recipe.scannervisuals"               , "Scanner (Visuals)"               , null, 0, 1, RES_PATH_GUI+"machines/ScannerVisuals"            ,/*IN-OUT-MIN-ITEM=*/ 2, 2, 2,/*IN-OUT-MIN-FLUID=*/ 0, 0, 0,/*MIN*/ 2,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F)
	, Printer                   = new RecipeMapPrinter              (null, "gt6.recipe.printer"                      , "Printer"                         , null, 0, 1, RES_PATH_GUI+"machines/Printer"                   ,/*IN-OUT-MIN-ITEM=*/ 2, 1, 1,/*IN-OUT-MIN-FLUID=*/ 6, 0, 1,/*MIN*/ 2,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F)
	, Sluice                    = new RecipeMap                     (null, "gt6.recipe.sluice"                       , "Sluice"                          , null, 0, 1, RES_PATH_GUI+"machines/Sluice"                    ,/*IN-OUT-MIN-ITEM=*/ 1, 9, 1,/*IN-OUT-MIN-FLUID=*/ 1, 1, 1,/*MIN*/ 2,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, MagneticSeparator         = new RecipeMap                     (null, "gt6.recipe.magneticseparator"            , "Magnetic Separator"              , null, 0, 1, RES_PATH_GUI+"machines/MagneticSeparator"         ,/*IN-OUT-MIN-ITEM=*/ 1, 6, 1,/*IN-OUT-MIN-FLUID=*/ 0, 0, 0,/*MIN*/ 1,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, Autocrafter               = new RecipeMapAutocrafting         (null, "gt6.recipe.autocrafting"                 , "Crafting"                        , null, 0, 1, RES_PATH_GUI+"machines/Crafting"                  ,/*IN-OUT-MIN-ITEM=*/ 9,12, 1,/*IN-OUT-MIN-FLUID=*/ 0, 0, 0,/*MIN*/ 1,/*AMP=*/ 1, ""                    ,    1, ""      , T, F, F, T, F)
	, Nanofab                   = new RecipeMap                     (null, "gt6.recipe.nanofab"                      , "Nanoscale Fabricator"            , null, 0, 1, RES_PATH_GUI+"machines/Nanofab"                   ,/*IN-OUT-MIN-ITEM=*/ 2, 1, 0,/*IN-OUT-MIN-FLUID=*/ 1, 1, 0,/*MIN*/ 1,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, Massfab                   = new RecipeMap                     (null, "gt6.recipe.massfab"                      , "Matter Fabricator"               , null, 0, 1, RES_PATH_GUI+"machines/Massfab"                   ,/*IN-OUT-MIN-ITEM=*/ 1, 1, 0,/*IN-OUT-MIN-FLUID=*/ 1, 2, 0,/*MIN*/ 1,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, ScannerMolecular          = new RecipeMapScannerMolecular     (null, "gt6.recipe.scannermolecular"             , "Molecular Scanner"               , null, 0, 1, RES_PATH_GUI+"machines/ScannerMolecular"          ,/*IN-OUT-MIN-ITEM=*/ 2, 1, 1,/*IN-OUT-MIN-FLUID=*/ 0, 0, 0,/*MIN*/ 2,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F)
	, Replicator                = new RecipeMapReplicator           (null, "gt6.recipe.replicator"                   , "Matter Replicator"               , null, 0, 1, RES_PATH_GUI+"machines/Replicator"                ,/*IN-OUT-MIN-ITEM=*/ 3, 3, 1,/*IN-OUT-MIN-FLUID=*/ 3, 3, 0,/*MIN*/ 2,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F)
	, Slicer                    = new RecipeMap                     (null, "gt6.recipe.slicer"                       , "Slicer"                          , null, 0, 1, RES_PATH_GUI+"machines/Slicer"                    ,/*IN-OUT-MIN-ITEM=*/ 2, 2, 2,/*IN-OUT-MIN-FLUID=*/ 0, 0, 0,/*MIN*/ 2,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, Laminator                 = new RecipeMap                     (null, "gt6.recipe.laminator"                    , "Laminator"                       , null, 0, 1, RES_PATH_GUI+"machines/Laminator"                 ,/*IN-OUT-MIN-ITEM=*/ 2, 1, 2,/*IN-OUT-MIN-FLUID=*/ 0, 0, 0,/*MIN*/ 2,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, CokeOven                  = new RecipeMap                     (null, "gt6.recipe.cokeoven"                     , "Coke Oven"                       , null, 0, 1, RES_PATH_GUI+"machines/CokeOven"                  ,/*IN-OUT-MIN-ITEM=*/ 3, 9, 1,/*IN-OUT-MIN-FLUID=*/ 3, 3, 0,/*MIN*/ 1,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, DistillationTower         = new RecipeMap                     (null, "gt6.recipe.distillationtower"            , "Distillation Tower"              , null, 0, 1, RES_PATH_GUI+"machines/DistillationTower"         ,/*IN-OUT-MIN-ITEM=*/ 1, 3, 0,/*IN-OUT-MIN-FLUID=*/ 1, 9, 0,/*MIN*/ 1,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, CryoDistillationTower     = new RecipeMap                     (null, "gt6.recipe.cryodistillationtower"        , "Cryo Distillation Tower"         , null, 0, 1, RES_PATH_GUI+"machines/CryoDistillationTower"     ,/*IN-OUT-MIN-ITEM=*/ 1, 3, 0,/*IN-OUT-MIN-FLUID=*/ 1, 9, 0,/*MIN*/ 1,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, SteamCracking             = new RecipeMap                     (null, "gt6.recipe.steamcracking"                , "Steam Cracking"                  , null, 0, 1, RES_PATH_GUI+"machines/SteamCracking"             ,/*IN-OUT-MIN-ITEM=*/ 1, 3, 0,/*IN-OUT-MIN-FLUID=*/ 2, 9, 1,/*MIN*/ 2,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, CatalyticCracking         = new RecipeMap                     (null, "gt6.recipe.catalyticcracking"            , "Catalytic Cracking"              , null, 0, 1, RES_PATH_GUI+"machines/CatalyticCracking"         ,/*IN-OUT-MIN-ITEM=*/ 1, 3, 0,/*IN-OUT-MIN-FLUID=*/ 2, 9, 1,/*MIN*/ 2,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, Autoclave                 = new RecipeMap                     (null, "gt6.recipe.autoclave"                    , "Autoclave"                       , null, 0, 1, RES_PATH_GUI+"machines/Autoclave"                 ,/*IN-OUT-MIN-ITEM=*/ 2, 3, 2,/*IN-OUT-MIN-FLUID=*/ 1, 1, 1,/*MIN*/ 0,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, Fusion                    = new RecipeMap                     (null, "gt6.recipe.fusionreactor"                , "Fusion Reactor"                  , null, 0, 1, RES_PATH_GUI+"machines/Fusion"                    ,/*IN-OUT-MIN-ITEM=*/ 2, 6, 1,/*IN-OUT-MIN-FLUID=*/ 2, 6, 0,/*MIN*/ 2,/*AMP=*/ 1, "Start: "             ,    1, " LU"   , T, T, T, T, F, 0)
	, CrackingTower     = new RecipeMap                     (null, "gt6.recipe.crackingtower"        , "CrackingTower"         , null, 0, 1, RES_PATH_GUI+"machines/SteamCracking"     ,/*IN-OUT-MIN-ITEM=*/ 1, 3, 0,/*IN-OUT-MIN-FLUID=*/ 2, 9, 0,/*MIN*/ 1,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)
	, ElectricCokeOven         = new RecipeMap                     (null, "gt6.recipe.electriccokeoven"                     , "Electric Coke Oven"                       , null, 0, 1, RES_PATH_GUI+"machines/ElectricCokeOven"                  ,/*IN-OUT-MIN-ITEM=*/ 3, 3, 3,/*IN-OUT-MIN-FLUID=*/ 3, 3, 3,/*MIN*/ 3,/*AMP=*/ 1, "", 1, ""   , T, T, T, T, F, 0)
	, Circuitassembling     = new RecipeMap                     (null, "gt6.recipe.circuitassembling"        , "Circuitassembling "         , null, 0, 1, RES_PATH_GUI+"machines/CircuitAssembler"     ,/*IN-OUT-MIN-ITEM=*/ 9, 1, 0,/*IN-OUT-MIN-FLUID=*/ 3, 0, 0,/*MIN*/ 1,/*AMP=*/ 1, ""                    ,    1, ""      , T, T, T, T, F, 0)

	, BlastFurnace              = new RecipeMap                     (null, "gt6.recipe.blastfurnace"                 , "Blast Furnace"                   , null, 0, 1, RES_PATH_GUI+"machines/Default"                   ,/*IN-OUT-MIN-ITEM=*/ 2, 2, 1,/*IN-OUT-MIN-FLUID=*/ 0, 0, 0,/*MIN*/ 0,/*AMP=*/ 1, "Heat Capacity: "     ,    1, " K"    , F, F/*T*/, F/*T*/, T, F, 0)
	, VacuumFreezer             = new RecipeMap                     (null, "gt6.recipe.vacuumfreezer"                , "Vacuum Freezer"                  , null, 0, 1, RES_PATH_GUI+"machines/Default"                   ,/*IN-OUT-MIN-ITEM=*/ 1, 1, 1,/*IN-OUT-MIN-FLUID=*/ 0, 0, 0,/*MIN*/ 0,/*AMP=*/ 1, ""                    ,    1, ""      , T, F/*T*/, F/*T*/, T, F, 0)
	, Assembler                 = new RecipeMapAssembler            (null, "gt6.recipe.assembler"                    , "Assembler"                       , null, 0, 1, RES_PATH_GUI+"machines/Assembler"                 ,/*IN-OUT-MIN-ITEM=*/ 2, 1, 1,/*IN-OUT-MIN-FLUID=*/ 1, 0, 0,/*MIN*/ 0,/*AMP=*/ 1, ""                    ,    1, ""      , T, F/*T*/, F/*T*/, T, F)
	, CNC                       = new RecipeMap                     (null, "gt6.recipe.cncmachine"                   , "CNC Machine"                     , null, 0, 1, RES_PATH_GUI+"machines/Default"                   ,/*IN-OUT-MIN-ITEM=*/ 2, 1, 2,/*IN-OUT-MIN-FLUID=*/ 1, 0, 1,/*MIN*/ 0,/*AMP=*/ 1, ""                    ,    1, ""      , T, F/*T*/, F/*T*/, T, F, 0)
	;

	// For Compatibility with old API Stuff.
	static {RecipeMap.sFurnaceRecipes=Furnace;RecipeMap.sMicrowaveRecipes=Microwave;RecipeMap.sFurnaceFuel=FM.Furnace;RecipeMap.sByProductList=ByProductList;RecipeMap.sCrucibleSmelting=CrucibleSmelting;RecipeMap.sCrucibleAlloying=CrucibleAlloying;RecipeMap.sGenerifierRecipes=Generifier;RecipeMap.sSharpeningRecipes=Sharpening;RecipeMap.sSifterRecipes=Sifting;RecipeMap.sHammerRecipes=Hammer;RecipeMap.sChiselRecipes=Chisel;RecipeMap.sShredderRecipes=Shredder;RecipeMap.sCrusherRecipes=Crusher;RecipeMap.sLatheRecipes=Lathe;RecipeMap.sCutterRecipes=Cutter;RecipeMap.sCoagulatorRecipes=Coagulator;RecipeMap.sSqueezerRecipes=Squeezer;RecipeMap.sJuicerRecipes=Juicer;RecipeMap.sMortarRecipes=Mortar;RecipeMap.sCompressorRecipes=Compressor;RecipeMap.sCentrifugeRecipes=Centrifuge;RecipeMap.sElectrolyzerRecipes=Electrolyzer;RecipeMap.sRollingMillRecipes=RollingMill;RecipeMap.sRollBenderRecipes=RollBender;RecipeMap.sRollFormerRecipes=RollFormer;RecipeMap.sClusterMillRecipes=ClusterMill;RecipeMap.sWiremillRecipes=Wiremill;RecipeMap.sMixerRecipes=Mixer;RecipeMap.sCannerRecipes=Canner;RecipeMap.sInjectorRecipes=Injector;RecipeMap.sRoastingRecipes=Roasting;RecipeMap.sDryingRecipes=Drying;RecipeMap.sFermenterRecipes=Fermenter;RecipeMap.sDistilleryRecipes=Distillery;RecipeMap.sExtruderRecipes=Extruder;RecipeMap.sPolarizerRecipes=Polarizer;RecipeMap.sLoomRecipes=Loom;RecipeMap.sCookingRecipes=Cooking;RecipeMap.sPressRecipes=Press;RecipeMap.sBathRecipes=Bath;RecipeMap.sSmelterRecipes=Smelter;RecipeMap.sLaserEngraverRecipes=LaserEngraver;RecipeMap.sWelderRecipes=Welder;RecipeMap.sCrystallisationCrucibleRecipes=CrystallisationCrucible;RecipeMap.sScannerVisualsRecipes=ScannerVisuals;RecipeMap.sPrinterRecipes=Printer;RecipeMap.sSluiceRecipes=Sluice;RecipeMap.sMagneticSeparatorRecipes=MagneticSeparator;RecipeMap.sAutocrafterRecipes=Autocrafter;RecipeMap.sMassfabRecipes=Massfab;RecipeMap.sScannerMolecularRecipes=ScannerMolecular;RecipeMap.sReplicatorRecipes=Replicator;RecipeMap.sSlicerRecipes=Slicer;RecipeMap.sCokeOvenRecipes=CokeOven;RecipeMap.sDistillationTowerRecipes=DistillationTower;RecipeMap.sAutoclaveRecipes=Autoclave;RecipeMap.sBoxinatorRecipes=Boxinator;RecipeMap.sUnboxinatorRecipes=Unboxinator;RecipeMap.sFusionRecipes=Fusion;RecipeMap.sBlastRecipes=BlastFurnace;RecipeMap.sImplosionRecipes=ImplosionCompressor;RecipeMap.sVacuumRecipes=VacuumFreezer;RecipeMap.sAssemblerRecipes=Assembler;RecipeMap.sCNCRecipes=CNC;RecipeMap.sFuelsBurn=FM.Burn;RecipeMap.sFuelsGas=FM.Gas;RecipeMap.sFuelsHot=FM.Hot;RecipeMap.sFuelsPlasma=FM.Plasma;RecipeMap.sFuelsEngine=FM.Engine;RecipeMap.sFuelsTurbine=FM.Turbine;RecipeMap.sFuelsMagic=FM.Magic;}

	public static boolean generify(ItemStack aStack1, ItemStack aStack2) {
		return RM.Generifier.addRecipe1(F, T, F, F, F, 0, 1, aStack1, aStack2) != null;
	}

	public static boolean generify(FluidStack aFluid1, FluidStack aFluid2) {
		return RM.Generifier.addRecipe0(F, T, F, F, F, 0, 1, aFluid1, aFluid2, ZL_IS) != null;
	}

	public static boolean box(ItemStack aEmpty, ItemStack aFull, ItemStack aContent) {
		if (ST.invalid(aEmpty) || ST.invalid(aFull) || ST.invalid(aContent)) return F;
		Boxinator  .addRecipe2(T, 16, 16, aContent, aEmpty, aFull);
		return T;
	}
	public static boolean unbox(ItemStack aEmpty, ItemStack aFull, ItemStack aContent) {
		if (ST.invalid(aEmpty) || ST.invalid(aFull) || ST.invalid(aContent)) return F;
		Unboxinator.addRecipe1(T, 16, 16, aFull, aContent, aEmpty);
		return T;
	}
	public static boolean boxunbox(ItemStack aEmpty, ItemStack aFull, ItemStack aContent) {
		if (ST.invalid(aEmpty) || ST.invalid(aFull) || ST.invalid(aContent)) return F;
		Boxinator  .addRecipe2(T, 16, 16, aContent, aEmpty, aFull);
		Unboxinator.addRecipe1(T, 16, 16, aFull, aContent, aEmpty);
		return T;
	}

	public static boolean pack(ItemStack aContent, ItemStack aFull) {
		if (ST.invalid(aFull) || ST.invalid(aContent)) return F;
		Boxinator.addRecipe2(T, 16, 16, aContent, ST.tag(aContent.stackSize), aFull);
		return T;
	}

	public static boolean pack(ItemStack aContent, long aAmount, ItemStack aFull) {
		if (ST.invalid(aFull) || ST.invalid(aContent)) return F;
		Boxinator.addRecipe2(T, 16, 16, ST.amount(aAmount, aContent), ST.tag(aAmount), aFull);
		return T;
	}

	public static boolean compact(ItemStack aContent, long aAmount, ItemStack aFull) {
		if (ST.invalid(aFull) || ST.invalid(aContent)) return F;
		Boxinator.addRecipe2(T, 16, 16, ST.amount(aAmount, aContent), ST.tag(aAmount), aFull);
		Compressor.addRecipe1(T, 16, 16, ST.amount(aAmount, aContent), aFull);
		ic2_compressor(ST.amount(aAmount, aContent), aFull);
		return T;
	}

	public static boolean unpack(ItemStack aFull, ItemStack aContent) {
		if (ST.invalid(aFull) || ST.invalid(aContent)) return F;
		Unboxinator.addRecipe1(T, 16, 16, aFull, aContent);
		ic2_extractor(aFull, aContent);
		return T;
	}

	public static boolean packunpack(ItemStack aContent, ItemStack aFull) {
		return pack(aContent, aFull) && unpack(aFull, aContent);
	}

	public static boolean biomass(ItemStack aBiomass) {return biomass(aBiomass, 64);}
	public static boolean biomass(ItemStack aBiomass, long aSpeed) {
		if (ST.invalid(aBiomass)) return F;
		int tSize = aBiomass.stackSize;
		if (tSize <= 0) return F;
		aBiomass = ST.amount(1, aBiomass);
		for (String tFluid : FluidsGT.WATER) if (FL.exists(tFluid) && !"riverwater".equals(tFluid))
		RM.Fermenter.addRecipe1(F, 16, (aSpeed * 4) / tSize, aBiomass, FL.make(tFluid                   , 1080 / tSize), FL.BiomassIC2.make(1080    / tSize), ZL_IS);
		for (String tFluid : FluidsGT.MILK ) if (FL.exists(tFluid))
		RM.Fermenter.addRecipe1(F, 16, (aSpeed * 3) / tSize, aBiomass, FL.make(tFluid                   , 1080 / tSize), FL.BiomassIC2.make(2160    / tSize), ZL_IS);
		for (String tFluid : FluidsGT.JUICE) if (FL.exists(tFluid) && !"potion.idunsapplejuice".equals(tFluid) && !"potion.goldenapplejuice".equals(tFluid) && !"goldencarrotjuice".equals(tFluid))
		RM.Fermenter.addRecipe1(F, 16, (aSpeed * 3) / tSize, aBiomass, FL.make(tFluid                   , 1080 / tSize), FL.BiomassIC2.make(3240    / tSize), ZL_IS);
		RM.Fermenter.addRecipe1(F, 16,  aSpeed      / tSize, aBiomass, FL.make("potion.idunsapplejuice" , 1080 / tSize), FL.BiomassIC2.make(2099520 / tSize), ZL_IS);
		RM.Fermenter.addRecipe1(F, 16,  aSpeed      / tSize, aBiomass, FL.make("potion.goldenapplejuice", 1080 / tSize), FL.BiomassIC2.make(233280  / tSize), ZL_IS);
		RM.Fermenter.addRecipe1(F, 16,  aSpeed      / tSize, aBiomass, FL.make("goldencarrotjuice"      , 1080 / tSize), FL.BiomassIC2.make(25920   / tSize), ZL_IS);
		for (String tFluid : FluidsGT.HONEY) if (FL.exists(tFluid))
		RM.Fermenter.addRecipe1(F, 16, (aSpeed * 3) / tSize, aBiomass, FL.make(tFluid                   , 1080 / tSize), FL.BiomassIC2.make(3240    / tSize), ZL_IS);
		RM.Fermenter.addRecipe1(F, 16, (aSpeed * 2) / tSize, aBiomass, FL.Honeydew.make(                  1080 / tSize), FL.BiomassIC2.make(3240    / tSize), ZL_IS);
		RM.Fermenter.addRecipe1(F, 16, (aSpeed * 2) / tSize, aBiomass, FL.RoyalJelly.make(                1080 / tSize), FL.BiomassIC2.make(32400   / tSize), ZL_IS);
		return T;
	}

	public static boolean debarking(ItemStack aInput, ItemStack... aOutputs) {return debarking(16, 64, 1000, aInput, aOutputs);}
	public static boolean debarking(long aEUt, long aDuration, ItemStack aInput, ItemStack... aOutputs) {return debarking(aEUt, aDuration, 1000, aInput, aOutputs);}
	public static boolean debarking(long aEUt, long aDuration, long aWater, ItemStack aInput, ItemStack... aOutputs) {
		if (ST.invalid(aInput) || aOutputs.length <= 0 || ST.invalid(aOutputs[0])) return F;
		if (aWater <= 0) aWater = 1;
		Debarker.addRecipe1(T, aEUt, aDuration, aInput, FL.Water.make(aWater), NF, aOutputs);
		Debarker.addRecipe1(T, aEUt, aDuration, aInput, FL.DistW.make(aWater), NF, aOutputs);
		return T;
	}

	public static boolean sawing(long aEUt, long aDuration, boolean aIsFoodItem, long aLubricantAmount, ItemStack aInput, ItemStack... aOutputs) {
		if (ST.invalid(aInput) || aOutputs.length <= 0 || ST.invalid(aOutputs[0])) return F;
		if (aLubricantAmount <= 0) aLubricantAmount = 1;
		Cutter.addRecipe1(T, aEUt, aDuration*4, aInput, FL.Water.make(aLubricantAmount*4), NF, aOutputs);
		Cutter.addRecipe1(T, aEUt, aDuration*3, aInput, FL.DistW.make(aLubricantAmount*3), NF, aOutputs);
		if (!aIsFoodItem) for (String tFluidName : FluidsGT.LUBRICANT) {
			FluidStack tFluid = FL.make(tFluidName, aLubricantAmount);
			if (tFluid != null) Cutter.addRecipe1(T, aEUt, aDuration, aInput, tFluid, NF, aOutputs);
		}
		return T;
	}

	public static boolean lathing(long aEUt, long aDuration, ItemStack aInput, ItemStack... aOutputs) {
		if (ST.invalid(aInput) || aOutputs.length <= 0 || ST.invalid(aOutputs[0])) return F;
		RM.Lathe.addRecipe1(T, aEUt, aDuration, aInput, aOutputs);
		return T;
	}

	public static boolean food_can(ItemStack aStack, int aFoodValue, String aCannedName, IItemContainer... aCans) {
		if (ST.invalid(aStack) || aStack.getItem() == ItemsGT.CANS || IL.IC2_Food_Can_Filled.equal(aStack, T, T)) return F;
		if (aFoodValue > 0) switch(aFoodValue / 2) {
		case  0: case  1:                            return null != Canner.addRecipe2(T, 16, 16, aStack, IL.Food_Can_Empty.get(1), aCans[0].getWithName(1, aCannedName), ST.container(aStack, T));
		case  2:                                     return null != Canner.addRecipe2(T, 16, 16, aStack, IL.Food_Can_Empty.get(1), aCans[1].getWithName(1, aCannedName), ST.container(aStack, T));
		case  3:                                     return null != Canner.addRecipe2(T, 16, 16, aStack, IL.Food_Can_Empty.get(1), aCans[2].getWithName(1, aCannedName), ST.container(aStack, T));
		case  4:                                     return null != Canner.addRecipe2(T, 16, 16, aStack, IL.Food_Can_Empty.get(1), aCans[3].getWithName(1, aCannedName), ST.container(aStack, T));
		case  5:                                     return null != Canner.addRecipe2(T, 16, 16, aStack, IL.Food_Can_Empty.get(1), aCans[4].getWithName(1, aCannedName), ST.container(aStack, T));
		case  8: case  9:                            return null != Canner.addRecipe2(T, 16, 16, aStack, IL.Food_Can_Empty.get(2), aCans[3].getWithName(2, aCannedName), ST.container(aStack, T));
		case 10: case 11:                            return null != Canner.addRecipe2(T, 16, 16, aStack, IL.Food_Can_Empty.get(2), aCans[4].getWithName(2, aCannedName), ST.container(aStack, T));
		case 15: case 16: case 17:                   return null != Canner.addRecipe2(T, 16, 16, aStack, IL.Food_Can_Empty.get(3), aCans[4].getWithName(3, aCannedName), ST.container(aStack, T));
		case 20: case 21: case 22: case 23:          return null != Canner.addRecipe2(T, 16, 16, aStack, IL.Food_Can_Empty.get(4), aCans[4].getWithName(4, aCannedName), ST.container(aStack, T));
		case 25: case 26: case 27: case 28: case 29: return null != Canner.addRecipe2(T, 16, 16, aStack, IL.Food_Can_Empty.get(5), aCans[4].getWithName(5, aCannedName), ST.container(aStack, T));
		default:                                     return null != Canner.addRecipe2(T, 16, 16, aStack, IL.Food_Can_Empty.get(aFoodValue/12), aCans[5].getWithName(aFoodValue/12, aCannedName), ST.container(aStack, T));
		}
		return F;
	}

	public static boolean crop_veggie(ItemStack aStack, FL aFluid, long aAmount                    , long aChance, String aCannedName, int aAlcohol, int aCaffeine, int aDehydration, int aSugar, int aFat) {return crop(aStack, aFluid       , aAmount, IL.Remains_Veggie    .get( 1), aChance, aCannedName, IL.CANS_VEGGIE      , aAlcohol, aCaffeine, aDehydration, aSugar, aFat);}
	public static boolean crop_veggie(ItemStack aStack, FL aFluid, long aAmount, ItemStack aRemains, long aChance, String aCannedName, int aAlcohol, int aCaffeine, int aDehydration, int aSugar, int aFat) {return crop(aStack, aFluid       , aAmount, aRemains                     , aChance, aCannedName, IL.CANS_VEGGIE      , aAlcohol, aCaffeine, aDehydration, aSugar, aFat);}
	public static boolean crop_fruit (ItemStack aStack, FL aFluid, long aAmount                    , long aChance, String aCannedName, int aAlcohol, int aCaffeine, int aDehydration, int aSugar, int aFat) {return crop(aStack, aFluid       , aAmount, IL.Remains_Fruit     .get( 1), aChance, aCannedName, IL.CANS_FRUIT       , aAlcohol, aCaffeine, aDehydration, aSugar, aFat);}
	public static boolean crop_fruit (ItemStack aStack, FL aFluid, long aAmount, ItemStack aRemains, long aChance, String aCannedName, int aAlcohol, int aCaffeine, int aDehydration, int aSugar, int aFat) {return crop(aStack, aFluid       , aAmount, aRemains                     , aChance, aCannedName, IL.CANS_FRUIT       , aAlcohol, aCaffeine, aDehydration, aSugar, aFat);}
	public static boolean crop_nut   (ItemStack aStack           , long aAmount                    , long aChance, String aCannedName                                                                     ) {return crop(aStack, FL.Oil_Nut   , aAmount, IL.Remains_Nut       .get( 1), aChance, aCannedName, IL.CANS_UNDEFINED   , 0, 0, 0, 0,16);}
	public static boolean crop_nut   (ItemStack aStack           , long aAmount, ItemStack aRemains, long aChance, String aCannedName                                                                     ) {return crop(aStack, FL.Oil_Nut   , aAmount, aRemains                     , aChance, aCannedName, IL.CANS_UNDEFINED   , 0, 0, 0, 0,16);}

	public static boolean crop(ItemStack aStack, FL aFluid, long aAmount, ItemStack aRemains, long aChance, String aCannedName, IItemContainer[] aCans, int aAlcohol, int aCaffeine, int aDehydration, int aSugar, int aFat) {
		if (aCans != null && UT.Code.stringValid(aCannedName)) food_can(aStack, Math.max(1, ST.food(aStack)), aCannedName, aCans);
		if (aFluid   != null) Squeezer.addRecipe1(T, 16, 16, aChance-1000 , aStack, NF, (aFluid.exists()?aFluid:FL.Juice).make(aAmount)                                               , aRemains);
		if (aFluid   != null) Juicer  .addRecipe1(T, 16, 16, aChance      , aStack, NF, (aFluid.exists()?aFluid:FL.Juice).make(aAmount-(aAmount<100?aAmount/3:1+(aAmount/250))*25)    , aRemains);
		if (aRemains != null) Shredder.addRecipe1(T, 16, 16, aChance      , aStack, aRemains);
		if (aRemains != null) Mortar  .addRecipe1(T, 16, 16, aChance/2    , aStack, aRemains);
		if (!(aStack.getItem() instanceof MultiItemRandom)) FoodsGT.put(aStack, aAlcohol, aCaffeine, aDehydration, aSugar, aFat);
		return T;
	}

	public static ItemStack get_smelting(ItemStack aInput, boolean aRemoveInput, ItemStack aOutputSlot) {
		if (aInput == null || aInput.stackSize < 1) return NI;
		ItemStack rStack = OM.get(FurnaceRecipes.smelting().getSmeltingResult(aInput));
		if (rStack != null && (aOutputSlot == null || (ST.equal(rStack, aOutputSlot) && rStack.stackSize + aOutputSlot.stackSize <= aOutputSlot.getMaxStackSize()))) {
			if (aRemoveInput) aInput.stackSize--;
			return rStack;
		}
		return NI;
	}
	public static boolean add_smelting(ItemStack aInput, ItemStack aOutput) {
		return add_smelting(aInput, aOutput, 0, T);
	}
	public static boolean add_smelting(ItemStack aInput, ItemStack aOutput, boolean aRemoveOthers) {
		return add_smelting(aInput, aOutput, 0, aRemoveOthers);
	}
	public static boolean add_smelting(ItemStack aInput, ItemStack aOutput, float aEXP) {
		return add_smelting(aInput, aOutput, aEXP, T);
	}
	public static boolean add_smelting(ItemStack aInput, ItemStack aOutput, float aEXP, boolean aRemoveOthers) {
		if (ST.invalid(aInput) || ST.invalid(aOutput)) return F;
		if (aRemoveOthers) rem_smelting(aInput);
		aOutput = OM.get_(aOutput);
		if (ST.container(aInput, F) != null || ST.equal_(aInput, aOutput, F) || !ConfigsGT.RECIPES.get(ConfigCategories.Machines.smelting, aInput, T)) return F;
		FurnaceRecipes.smelting().func_151394_a(aInput, ST.copy_(aOutput), aEXP);
		return T;
	}
	public static boolean rem_smelting(ItemStack aInput) {
		if (ST.invalid(aInput)) return F;
		ItemStack tPyrotheum = OP.dust.mat(MT.Pyrotheum, 1);
		if (ST.valid(tPyrotheum)) CR.remove(aInput, tPyrotheum);
		@SuppressWarnings("unchecked")
		Iterator<Entry<ItemStack, ItemStack>> tIterator = FurnaceRecipes.smelting().getSmeltingList().entrySet().iterator();
		boolean temp = F;
		while (tIterator.hasNext()) if (ST.equal(aInput, tIterator.next().getKey(), T)) {
			tIterator.remove();
			temp = T;
		}
		return temp;
	}
	public static boolean rem_smelting(ItemStack aInput, ItemStack aOutput) {
		if (ST.invalid(aInput) || ST.invalid(aOutput)) return F;
		@SuppressWarnings("unchecked")
		Iterator<Entry<ItemStack, ItemStack>> tIterator = FurnaceRecipes.smelting().getSmeltingList().entrySet().iterator();
		boolean temp = F;
		while (tIterator.hasNext()) {
			Entry<ItemStack, ItemStack> tEntry = tIterator.next();
			if (ST.equal(aInput, tEntry.getKey(), T) && ST.equal(aOutput, tEntry.getValue(), T)) {
				tIterator.remove();
				temp = T;
			}
		}
		return temp;
	}

	public static boolean ae_grinder(int aTurns, ItemStack aInput, ItemStack aOutput) {if (MD.AE.mLoaded && ST.valid(aInput) && ST.valid(aOutput)) try {AEApi.instance().registries().grinder().addRecipe(ST.copy_(aInput), ST.copy_(aOutput), Math.max(1, aTurns)); return T;} catch(Throwable e) {e.printStackTrace(ERR);} return F;}
	public static boolean ae_grinder(int aTurns, ItemStack aInput, ItemStack aOutput, ItemStack aOutput2, float aChance2) {if (MD.AE.mLoaded && ST.valid(aInput) && ST.valid(aOutput)) try {AEApi.instance().registries().grinder().addRecipe(ST.copy_(aInput), ST.copy_(aOutput), ST.copy(aOutput2), aChance2, Math.max(1, aTurns)); return T;} catch(Throwable e) {e.printStackTrace(ERR);} return F;}
	public static boolean ae_grinder(int aTurns, ItemStack aInput, ItemStack aOutput, ItemStack aOutput2, float aChance2, ItemStack aOutput3, float aChance3) {if (MD.AE.mLoaded && ST.valid(aInput) && ST.valid(aOutput)) try {AEApi.instance().registries().grinder().addRecipe(ST.copy_(aInput), ST.copy_(aOutput), ST.copy(aOutput2), aChance2, ST.copy(aOutput3), aChance3, Math.max(1, aTurns)); return T;} catch(Throwable e) {e.printStackTrace(ERR);} return F;}

	public static boolean pulverizing(ItemStack aInput, ItemStack aOutput1) {return pulverizing(aInput, aOutput1, null, 0, F);}
	public static boolean pulverizing(ItemStack aInput, ItemStack aOutput1, ItemStack aOutput2) {return pulverizing(aInput, aOutput1, aOutput2, 100, F);}
	public static boolean pulverizing(ItemStack aInput, ItemStack aOutput1, ItemStack aOutput2, int aChance) {return pulverizing(aInput, aOutput1, aOutput2, aChance, F);}
	public static boolean pulverizing(ItemStack aInput, ItemStack aOutput1, boolean aOverwrite) {return pulverizing(aInput, aOutput1, null, 0, aOverwrite);}
	public static boolean pulverizing(ItemStack aInput, ItemStack aOutput1, ItemStack aOutput2, boolean aOverwrite) {return pulverizing(aInput, aOutput1, aOutput2, 100, aOverwrite);}
	public static boolean pulverizing(ItemStack aInput, ItemStack aOutput1, ItemStack aOutput2, int aChance, boolean aOverwrite) {return pulverizing(aInput, aOutput1, aOutput2, aChance, null, 0, aOverwrite);}
	public static boolean pulverizing(ItemStack aInput, ItemStack aOutput1, ItemStack aOutput2, int aChance2, ItemStack aOutput3, int aChance3, boolean aOverwrite) {
		if (ST.invalid(aInput) || ST.invalid(aOutput1)) return F;
		aOutput1 = ST.validMeta(OM.get_(aOutput1));
		aOutput2 = ST.validMeta(OM.get (aOutput2));

		if (ST.container(aInput, F) == null) {
			if (ENABLE_ADDING_IC2_MACERATOR_RECIPES) {
				if (ConfigsGT.RECIPES.get(ConfigCategories.Machines.maceration, aInput, T)) {
					UT.addSimpleIC2MachineRecipe(ic2.api.recipe.Recipes.macerator, aInput, null, aOutput1);
				} else {
					UT.removeSimpleIC2MachineRecipe(aInput, ic2.api.recipe.Recipes.macerator.getRecipes(), null);
				}
			}

			if (!OP.log.contains(aInput) && ANY.Wood.contains(aOutput1)) {
				if (ConfigsGT.RECIPES.get(ConfigCategories.Machines.pulverization, aInput, T)) {
					if (aOutput2 == null)
						te_sawmill(32000, ST.copy(aInput), ST.copy(aOutput1));
					else
						te_sawmill(32000, ST.copy(aInput), ST.copy(aOutput1), ST.copy(aOutput2), aChance2<=0?10:aChance2);
				}
			} else {
				if (!OP.log.contains(aInput) && ConfigsGT.RECIPES.get(ConfigCategories.Machines.rockcrushing, aInput, ST.block(aInput) != NB)) {
					try {
						if (ST.block(aInput) != Blocks.obsidian && ST.block(aInput) != Blocks.gravel) {
							mods.railcraft.api.crafting.IRockCrusherRecipe tRecipe = mods.railcraft.api.crafting.RailcraftCraftingManager.rockCrusher.createNewRecipe(ST.amount(1, aInput), ST.meta_(aInput) != W, F);
							tRecipe.addOutput(ST.copy(aOutput1), 1.0F/aInput.stackSize);
							if (aOutput2 != null) tRecipe.addOutput(ST.copy(aOutput2), (0.01F*(aChance2<=0?10:aChance2))/aInput.stackSize);
							if (aOutput3 != null) tRecipe.addOutput(ST.copy(aOutput3), (0.01F*(aChance3<=0?10:aChance3))/aInput.stackSize);
						}
					} catch(Throwable e) {/*Do nothing*/}
				}
				if (ConfigsGT.RECIPES.get(ConfigCategories.Machines.pulverization, aInput, T)) {
					if (aOutput2 == null)
						te_pulverizer(32000, ST.copy(aInput), ST.copy(aOutput1));
					else
						te_pulverizer(32000, ST.copy(aInput), ST.copy(aOutput1), ST.copy(aOutput2), aChance2<=0?10:aChance2);
				}
			}
		}
		return T;
	}
	public static boolean ic2_extractor(ItemStack aInput, ItemStack aOutput) {
		if (!ENABLE_ADDING_IC2_EXTRACTOR_RECIPES || ST.invalid(aInput) || ST.invalid(aOutput)) return F;
		aOutput = ST.validMeta(OM.get_(aOutput));
		if (!ConfigsGT.RECIPES.get(ConfigCategories.Machines.extractor, aInput, T)) {
			UT.removeSimpleIC2MachineRecipe(aInput, ic2.api.recipe.Recipes.extractor.getRecipes(), null);
			return F;
		}
		UT.addSimpleIC2MachineRecipe(ic2.api.recipe.Recipes.extractor, aInput, null, aOutput);
		return T;
	}
	public static boolean ic2_compressor(ItemStack aInput, ItemStack aOutput) {
		if (!ENABLE_ADDING_IC2_COMPRESSOR_RECIPES || ST.invalid(aInput) || ST.invalid(aOutput)) return F;
		aOutput = ST.validMeta(OM.get_(aOutput));
		if (!ConfigsGT.RECIPES.get(ConfigCategories.Machines.compression, aInput, T)) {
			UT.removeSimpleIC2MachineRecipe(aInput, ic2.api.recipe.Recipes.compressor.getRecipes(), null);
			return F;
		}
		UT.addSimpleIC2MachineRecipe(ic2.api.recipe.Recipes.compressor, aInput, null, aOutput);
		return T;
	}
	public static boolean ic2_orewasher(ItemStack aInput, long aWaterAmount, Object... aOutput) {
		if (!ENABLE_ADDING_IC2_OREWASHER_RECIPES || ST.invalid(aInput) || aOutput == null || aOutput.length <= 0 || aOutput[0] == null) return F;
		if (!ConfigsGT.RECIPES.get(ConfigCategories.Machines.orewashing, aInput, T)) {
			UT.removeSimpleIC2MachineRecipe(aInput, ic2.api.recipe.Recipes.oreWashing.getRecipes(), null);
			return F;
		}
		UT.addSimpleIC2MachineRecipe(ic2.api.recipe.Recipes.oreWashing, aInput, UT.NBT.makeLong("amount", aWaterAmount), aOutput);
		return T;
	}
	public static boolean ic2_centrifuge(ItemStack aInput, long aHeat, Object... aOutput) {
		if (!ENABLE_ADDING_IC2_CENTRIFUGE_RECIPES || ST.invalid(aInput) || aOutput == null || aOutput.length <= 0 || aOutput[0] == null) return F;
		if (!ConfigsGT.RECIPES.get(ConfigCategories.Machines.thermalcentrifuge, aInput, T)) {
			UT.removeSimpleIC2MachineRecipe(aInput, ic2.api.recipe.Recipes.centrifuge.getRecipes(), null);
			return F;
		}
		UT.addSimpleIC2MachineRecipe(ic2.api.recipe.Recipes.centrifuge, aInput, UT.NBT.makeLong("minHeat", aHeat), aOutput);
		return T;
	}

	public static void te_furnace(int energy, ItemStack input, ItemStack output) {
		NBTTagCompound toSend = UT.NBT.make();
		toSend.setInteger("energy", energy);
		toSend.setTag("input", UT.NBT.make());
		toSend.setTag("output", UT.NBT.make());
		input.writeToNBT(toSend.getCompoundTag("input"));
		output.writeToNBT(toSend.getCompoundTag("output"));
		FMLInterModComms.sendMessage("ThermalExpansion", "FurnaceRecipe", toSend);
	}
	public static void te_pulverizer(int energy, ItemStack input, ItemStack primaryOutput) {
		te_pulverizer(energy, input, primaryOutput, null, 0);
	}
	public static void te_pulverizer(int energy, ItemStack input, ItemStack primaryOutput, ItemStack secondaryOutput) {
		te_pulverizer(energy, input, primaryOutput, secondaryOutput, 100);
	}
	public static void te_pulverizer(int energy, ItemStack input, ItemStack primaryOutput, ItemStack secondaryOutput, int secondaryChance) {
		if (input == null || primaryOutput == null) return;
		NBTTagCompound toSend = UT.NBT.make();
		toSend.setInteger("energy", energy);
		toSend.setTag("input", UT.NBT.make());
		toSend.setTag("primaryOutput", UT.NBT.make());
		toSend.setTag("secondaryOutput", UT.NBT.make());
		input.writeToNBT(toSend.getCompoundTag("input"));
		primaryOutput.writeToNBT(toSend.getCompoundTag("primaryOutput"));
		if (secondaryOutput != null) secondaryOutput.writeToNBT(toSend.getCompoundTag("secondaryOutput"));
		toSend.setInteger("secondaryChance", secondaryChance);
		FMLInterModComms.sendMessage("ThermalExpansion", "PulverizerRecipe", toSend);
	}
	public static void te_sawmill(int energy, ItemStack input, ItemStack primaryOutput) {
		te_sawmill(energy, input, primaryOutput, null, 0);
	}
	public static void te_sawmill(int energy, ItemStack input, ItemStack primaryOutput, ItemStack secondaryOutput) {
		te_sawmill(energy, input, primaryOutput, secondaryOutput, 100);
	}
	public static void te_sawmill(int energy, ItemStack input, ItemStack primaryOutput, ItemStack secondaryOutput, int secondaryChance) {
		if (input == null || primaryOutput == null) return;
		NBTTagCompound toSend = UT.NBT.make();
		toSend.setInteger("energy", energy);
		toSend.setTag("input", UT.NBT.make());
		toSend.setTag("primaryOutput", UT.NBT.make());
		toSend.setTag("secondaryOutput", UT.NBT.make());
		input.writeToNBT(toSend.getCompoundTag("input"));
		primaryOutput.writeToNBT(toSend.getCompoundTag("primaryOutput"));
		if (secondaryOutput != null) secondaryOutput.writeToNBT(toSend.getCompoundTag("secondaryOutput"));
		toSend.setInteger("secondaryChance", secondaryChance);
		FMLInterModComms.sendMessage("ThermalExpansion", "SawmillRecipe", toSend);
	}
	public static void te_smelter(int energy, ItemStack primaryInput, ItemStack secondaryInput, ItemStack primaryOutput) {
		te_smelter(energy, primaryInput, secondaryInput, primaryOutput, null, 0);
	}
	public static void te_smelter(int energy, ItemStack primaryInput, ItemStack secondaryInput, ItemStack primaryOutput, ItemStack secondaryOutput) {
		te_smelter(energy, primaryInput, secondaryInput, primaryOutput, secondaryOutput, 100);
	}
	public static void te_smelter(int energy, ItemStack primaryInput, ItemStack secondaryInput, ItemStack primaryOutput, ItemStack secondaryOutput, int secondaryChance) {
		if (primaryInput == null || secondaryInput == null || primaryOutput == null) return;
		NBTTagCompound toSend = UT.NBT.make();
		toSend.setInteger("energy", energy);
		toSend.setTag("primaryInput", UT.NBT.make());
		toSend.setTag("secondaryInput", UT.NBT.make());
		toSend.setTag("primaryOutput", UT.NBT.make());
		toSend.setTag("secondaryOutput", UT.NBT.make());
		primaryInput.writeToNBT(toSend.getCompoundTag("primaryInput"));
		secondaryInput.writeToNBT(toSend.getCompoundTag("secondaryInput"));
		primaryOutput.writeToNBT(toSend.getCompoundTag("primaryOutput"));
		if (secondaryOutput != null) secondaryOutput.writeToNBT(toSend.getCompoundTag("secondaryOutput"));
		toSend.setInteger("secondaryChance", secondaryChance);
		FMLInterModComms.sendMessage("ThermalExpansion", "SmelterRecipe", toSend);
	}
	public static void te_smelter_ore(OreDictMaterial aMaterial) {
		NBTTagCompound toSend = UT.NBT.make();
		toSend.setString("oreType", aMaterial.toString());
		FMLInterModComms.sendMessage("ThermalExpansion", "SmelterBlastOreType", toSend);
	}
	public static void te_crucible(int energy, ItemStack input, FluidStack output) {
		if (input == null || output == null) return;
		NBTTagCompound toSend = UT.NBT.make();
		toSend.setInteger("energy", energy);
		toSend.setTag("input", UT.NBT.make());
		toSend.setTag("output", UT.NBT.make());
		input.writeToNBT(toSend.getCompoundTag("input"));
		output.writeToNBT(toSend.getCompoundTag("output"));
		FMLInterModComms.sendMessage("ThermalExpansion", "CrucibleRecipe", toSend);
	}
	public static void te_fill(int energy, ItemStack input, ItemStack output, FluidStack fluid, boolean reversible) {
		if (input == null || output == null || fluid == null) return;
		NBTTagCompound toSend = UT.NBT.make();
		toSend.setInteger("energy", energy);
		toSend.setTag("input", UT.NBT.make());
		toSend.setTag("output", UT.NBT.make());
		toSend.setTag("fluid", UT.NBT.make());
		input.writeToNBT(toSend.getCompoundTag("input"));
		output.writeToNBT(toSend.getCompoundTag("output"));
		UT.NBT.setBoolean(toSend, "reversible", reversible);
		fluid.writeToNBT(toSend.getCompoundTag("fluid"));
		FMLInterModComms.sendMessage("ThermalExpansion", "TransposerFillRecipe", toSend);
	}
	public static void te_extract(int energy, ItemStack input, ItemStack output, FluidStack fluid, int chance, boolean reversible) {
		if (input == null || output == null || fluid == null) return;
		NBTTagCompound toSend = UT.NBT.make();
		toSend.setInteger("energy", energy);
		toSend.setTag("input", UT.NBT.make());
		toSend.setTag("output", UT.NBT.make());
		toSend.setTag("fluid", UT.NBT.make());
		input.writeToNBT(toSend.getCompoundTag("input"));
		output.writeToNBT(toSend.getCompoundTag("output"));
		UT.NBT.setBoolean(toSend, "reversible", reversible);
		toSend.setInteger("chance", chance);
		fluid.writeToNBT(toSend.getCompoundTag("fluid"));
		FMLInterModComms.sendMessage("ThermalExpansion", "TransposerExtractRecipe", toSend);
	}
}
