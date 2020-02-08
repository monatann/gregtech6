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

package gregtech6.loaders.c;

import static gregapi6.data.CS.*;
import static gregapi6.data.OP.*;
import static gregapi6.data.TD.Atomic.*;
import static gregapi6.data.TD.Compounds.*;
import static gregapi6.data.TD.Prefix.*;
import static gregapi6.data.TD.Processing.*;
import static gregapi6.data.TD.Properties.*;
import static gregapi6.oredict.OreDictMaterialCondition.*;

import cpw.mods.fml.common.registry.GameRegistry;
import gregapi6.code.ICondition;
import gregapi6.code.ICondition.And;
import gregapi6.code.ICondition.Nor;
import gregapi6.code.ICondition.Or;
import gregapi6.data.ANY;
import gregapi6.data.FL;
import gregapi6.data.IL;
import gregapi6.data.MT;
import gregapi6.data.OP;
import gregapi6.data.RM;
import gregapi6.data.TD;
import gregapi6.oredict.OreDictManager;
import gregapi6.oredict.OreDictMaterial;
import gregapi6.oredict.OreDictPrefix;
import gregapi6.recipes.AdvancedCrafting1ToY;
import gregapi6.recipes.AdvancedCraftingXToY;
import gregapi6.recipes.handlers.RecipeMapHandlerCrushing;
import gregapi6.recipes.handlers.RecipeMapHandlerMaterial;
import gregapi6.recipes.handlers.RecipeMapHandlerPrefix;
import gregapi6.recipes.handlers.RecipeMapHandlerPrefixForging;
import gregapi6.recipes.handlers.RecipeMapHandlerPrefixShredding;
import gregapi6.util.ST;
import gregapi6.util.UT;
import gregtech6.loaders.b.Loader_OreProcessing.OreProcessing_CraftFrom;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author Gregorius Techneticies
 */
public class Loader_Recipes_Handlers implements Runnable {
	@Override
	@SuppressWarnings({"unchecked", "rawtypes"})
	public void run() {
		RM.Sifting              .add(new RecipeMapHandlerPrefix(pebbles                         , 1, null           , 0, NF,  16, 0,   512, NF, dust                    , 3, null       , 0, NI, NI, T, F, F, ANTIMATTER.NOT));
		
		RM.Crusher              .add(new RecipeMapHandlerPrefix(rockGt                          , 1, null           , 0, NF,  16, 0,    16, NF, null                    , 0, null       , 0, NI, NI, T, T, F, ANTIMATTER.NOT));
		RM.Crusher              .add(new RecipeMapHandlerPrefix(rawOreChunk                     , 1, null           , 0, NF,  16, 0,    64, NF, crushedTiny             , 3, null       , 0, NI, NI, T, F, F, ANTIMATTER.NOT));
		RM.Crusher              .add(new RecipeMapHandlerPrefix(chunk                           , 1, null           , 0, NF,  16, 0,   128, NF, rubble                  , 1, null       , 0, NI, NI, T, F, F, ANTIMATTER.NOT));
		RM.Crusher              .add(new RecipeMapHandlerPrefix(rubble                          , 1, null           , 0, NF,  16, 0,   128, NF, pebbles                 , 1, null       , 0, NI, NI, T, F, F, ANTIMATTER.NOT));
		
		RM.Crusher              .add(new RecipeMapHandlerPrefix(gemLegendary                    , 1, null           , 0, NF,  16, 0,   256, NF, gemExquisite            , 2, null       , 0, NI, NI, T, T, F, ANTIMATTER.NOT));
		RM.Crusher              .add(new RecipeMapHandlerPrefix(gemExquisite                    , 1, null           , 0, NF,  16, 0,   256, NF, gemFlawless             , 2, null       , 0, NI, NI, T, T, F, ANTIMATTER.NOT));
		RM.Crusher              .add(new RecipeMapHandlerPrefix(gemFlawless                     , 1, null           , 0, NF,  16, 0,   256, NF, gem                     , 2, null       , 0, NI, NI, T, T, F, ANTIMATTER.NOT));
		RM.Crusher              .add(new RecipeMapHandlerPrefix(gem                             , 1, null           , 0, NF,  16, 0,   256, NF, gemFlawed               , 2, null       , 0, NI, NI, T, T, F, ANTIMATTER.NOT));
		RM.Crusher              .add(new RecipeMapHandlerPrefix(gemFlawed                       , 1, null           , 0, NF,  16, 0,   256, NF, gemChipped              , 2, null       , 0, NI, NI, T, T, F, ANTIMATTER.NOT));
		RM.Crusher              .add(new RecipeMapHandlerPrefix(gemChipped                      , 1, null           , 0, NF,  16, 0,   256, NF, null                    , 0, null       , 0, NI, NI, T, T, F, ANTIMATTER.NOT));
		RM.Crusher              .add(new RecipeMapHandlerPrefix(bouleGt                         , 1, null           , 0, NF,  16, 0,   256, NF, gem                     , 4, null       , 0, NI, NI, T, T, F, ANTIMATTER.NOT));
		
		RM.Crusher              .add(new RecipeMapHandlerCrushing());
		
		RM.Mortar               .add(new RecipeMapHandlerPrefix(rockGt                          , 1, null           , 0, NF,  16, 0,    16, NF, null                    , 0, null       , 0, NI, NI, F, T, F, ANTIMATTER.NOT));
		RM.Mortar               .add(new RecipeMapHandlerPrefix(crushedPurified                 , 1, null           , 0, NF,  16, 0,    16, NF, null                    , 0, null       , 0, NI, NI, F, T, F, new And(ANTIMATTER.NOT, MORTAR)));
		RM.Mortar               .add(new RecipeMapHandlerPrefix(crushedPurifiedTiny             , 1, null           , 0, NF,  16, 0,    16, NF, null                    , 0, null       , 0, NI, NI, F, T, F, new And(ANTIMATTER.NOT, MORTAR)));
		RM.Mortar               .add(new RecipeMapHandlerPrefix(crushedCentrifuged              , 1, null           , 0, NF,  16, 0,    16, NF, null                    , 0, null       , 0, NI, NI, F, T, F, new And(ANTIMATTER.NOT, MORTAR)));
		RM.Mortar               .add(new RecipeMapHandlerPrefix(crushedCentrifugedTiny          , 1, null           , 0, NF,  16, 0,    16, NF, null                    , 0, null       , 0, NI, NI, F, T, F, new And(ANTIMATTER.NOT, MORTAR)));
		RM.Mortar               .add(new RecipeMapHandlerPrefix(cleanGravel                     , 1, null           , 0, NF,  16, 0,    16, NF, null                    , 0, null       , 0, NI, NI, F, T, F, new And(ANTIMATTER.NOT, MORTAR)));
		RM.Mortar               .add(new RecipeMapHandlerPrefix(dirtyGravel                     , 1, null           , 0, NF,  16, 0,    16, NF, null                    , 0, null       , 0, NI, NI, F, T, F, new And(ANTIMATTER.NOT, MORTAR)));
		RM.Mortar               .add(new RecipeMapHandlerPrefix(crystalline                     , 1, null           , 0, NF,  16, 0,    16, NF, null                    , 0, null       , 0, NI, NI, F, T, F, new And(ANTIMATTER.NOT, MORTAR)));
		RM.Mortar               .add(new RecipeMapHandlerPrefix(crystal                         , 1, null           , 0, NF,  16, 0,    16, NF, null                    , 0, null       , 0, NI, NI, F, T, F, new And(ANTIMATTER.NOT, MORTAR)));
		RM.Mortar               .add(new RecipeMapHandlerPrefix(reduced                         , 1, null           , 0, NF,  16, 0,    16, NF, null                    , 0, null       , 0, NI, NI, F, T, F, new And(ANTIMATTER.NOT, MORTAR)));
		RM.Mortar               .add(new RecipeMapHandlerPrefix(clump                           , 1, null           , 0, NF,  16, 0,    16, NF, null                    , 0, null       , 0, NI, NI, F, T, F, new And(ANTIMATTER.NOT, MORTAR)));
		RM.Mortar               .add(new RecipeMapHandlerPrefix(scrapGt                         , 1, null           , 0, NF,  16, 0,    16, NF, null                    , 0, null       , 0, NI, NI, F, T, F, new And(ANTIMATTER.NOT, MORTAR)));
		RM.Mortar               .add(new RecipeMapHandlerPrefix(chunkGt                         , 1, null           , 0, NF,  16, 0,    16, NF, null                    , 0, null       , 0, NI, NI, F, T, F, new And(ANTIMATTER.NOT, MORTAR)));
		RM.Mortar               .add(new RecipeMapHandlerPrefix(nugget                          , 1, null           , 0, NF,  16, 0,    16, NF, null                    , 0, null       , 0, NI, NI, F, T, F, new And(ANTIMATTER.NOT, MORTAR)));
		RM.Mortar               .add(new RecipeMapHandlerPrefix(plateTiny                       , 1, null           , 0, NF,  16, 0,    16, NF, null                    , 0, null       , 0, NI, NI, F, T, F, new And(ANTIMATTER.NOT, MORTAR)));
		RM.Mortar               .add(new RecipeMapHandlerPrefix(plateGemTiny                    , 1, null           , 0, NF,  16, 0,    16, NF, null                    , 0, null       , 0, NI, NI, F, T, F, new And(ANTIMATTER.NOT, MORTAR)));
		RM.Mortar               .add(new RecipeMapHandlerPrefix(round                           , 1, null           , 0, NF,  16, 0,    16, NF, null                    , 0, null       , 0, NI, NI, F, T, F, new And(ANTIMATTER.NOT, MORTAR)));
		RM.Mortar               .add(new RecipeMapHandlerPrefix(screw                           , 1, null           , 0, NF,  16, 0,    16, NF, null                    , 0, null       , 0, NI, NI, F, T, F, new And(ANTIMATTER.NOT, MORTAR)));
		RM.Mortar               .add(new RecipeMapHandlerPrefix(bolt                            , 1, null           , 0, NF,  16, 0,    16, NF, null                    , 0, null       , 0, NI, NI, F, T, F, new And(ANTIMATTER.NOT, MORTAR)));
		RM.Mortar               .add(new RecipeMapHandlerPrefix(wireFine                        , 1, null           , 0, NF,  16, 0,    16, NF, null                    , 0, null       , 0, NI, NI, F, T, F, new And(ANTIMATTER.NOT, MORTAR)));
		RM.Mortar               .add(new RecipeMapHandlerPrefix(toolHeadArrow                   , 1, null           , 0, NF,  16, 0,    16, NF, null                    , 0, null       , 0, NI, NI, F, T, F, new And(ANTIMATTER.NOT, MORTAR)));
		RM.Mortar               .add(new RecipeMapHandlerPrefix(toolHeadRawArrow                , 1, null           , 0, NF,  16, 0,    16, NF, null                    , 0, null       , 0, NI, NI, F, T, F, new And(ANTIMATTER.NOT, MORTAR)));
		RM.Mortar               .add(new RecipeMapHandlerPrefix(gemChipped                      , 1, null           , 0, NF,  16, 0,    16, NF, null                    , 0, null       , 0, NI, NI, F, T, F, new And(ANTIMATTER.NOT, MORTAR)));
		RM.Mortar               .add(new RecipeMapHandlerPrefix(gemFlawed                       , 1, null           , 0, NF,  16, 0,    16, NF, null                    , 0, null       , 0, NI, NI, F, T, F, new And(ANTIMATTER.NOT, MORTAR, new Or(BRITTLE, FOOD, gemChipped.NOT))));
		RM.Mortar               .add(new RecipeMapHandlerPrefix(gem                             , 1, null           , 0, NF,  16, 0,    16, NF, null                    , 0, null       , 0, NI, NI, T, T, F, new And(ANTIMATTER.NOT, MORTAR, new Or(BRITTLE, FOOD, gemFlawed.NOT))));
		RM.Mortar               .add(new RecipeMapHandlerPrefix(gemFlawless                     , 1, null           , 0, NF,  16, 0,    16, NF, null                    , 0, null       , 0, NI, NI, F, T, F, new And(ANTIMATTER.NOT, MORTAR, new Or(BRITTLE, FOOD, gem.NOT))));
		RM.Mortar               .add(new RecipeMapHandlerPrefix(gemExquisite                    , 1, null           , 0, NF,  16, 0,    16, NF, null                    , 0, null       , 0, NI, NI, F, T, F, new And(ANTIMATTER.NOT, MORTAR, new Or(BRITTLE, FOOD, gemFlawless.NOT))));
		RM.Mortar               .add(new RecipeMapHandlerPrefix(gemLegendary                    , 1, null           , 0, NF,  16, 0,    16, NF, null                    , 0, null       , 0, NI, NI, F, T, F, new And(ANTIMATTER.NOT, MORTAR, new Or(BRITTLE, FOOD, gemExquisite.NOT))));
		RM.Mortar               .add(new RecipeMapHandlerPrefix(stick                           , 1, null           , 0, NF,  16, 0,    16, NF, null                    , 0, null       , 0, NI, NI, F, T, F, new And(ANTIMATTER.NOT, MORTAR, new Or(BRITTLE, FOOD, WOOD))));
		RM.Mortar               .add(new RecipeMapHandlerPrefix(stickLong                       , 1, null           , 0, NF,  16, 0,    16, NF, null                    , 0, null       , 0, NI, NI, F, T, F, new And(ANTIMATTER.NOT, MORTAR, new Or(BRITTLE, FOOD, WOOD))));
		RM.Mortar               .add(new RecipeMapHandlerPrefix(ingot                           , 1, null           , 0, NF,  16, 0,    16, NF, null                    , 0, null       , 0, NI, NI, T, T, F, new And(ANTIMATTER.NOT, MORTAR, new Or(BRITTLE, FOOD, WOOD))));
		RM.Mortar               .add(new RecipeMapHandlerPrefix(plate                           , 1, null           , 0, NF,  16, 0,    16, NF, null                    , 0, null       , 0, NI, NI, F, T, F, new And(ANTIMATTER.NOT, MORTAR, new Or(BRITTLE, FOOD, WOOD))));
		RM.Mortar               .add(new RecipeMapHandlerPrefix(plateGem                        , 1, null           , 0, NF,  16, 0,    16, NF, null                    , 0, null       , 0, NI, NI, F, T, F, new And(ANTIMATTER.NOT, MORTAR, new Or(BRITTLE, FOOD, WOOD))));
		
		RM.Shredder             .add(new RecipeMapHandlerPrefixShredding(chunk                  , 1, null           , 0, NF,  16, 0,   256, NF, dust                    , 2, dustTiny   , 1, NI, NI, T, F, F, new And(ANTIMATTER.NOT, MORTAR.NOT)));
		RM.Shredder             .add(new RecipeMapHandlerPrefixShredding(rubble                 , 1, null           , 0, NF,  16, 0,   256, NF, dust                    , 2, dustTiny   , 1, NI, NI, T, F, F, new And(ANTIMATTER.NOT, MORTAR.NOT)));
		RM.Shredder             .add(new RecipeMapHandlerPrefixShredding(pebbles                , 1, null           , 0, NF,  16, 0,   256, NF, dust                    , 2, dustTiny   , 1, NI, NI, T, F, F, new And(ANTIMATTER.NOT, MORTAR.NOT)));
		RM.Shredder             .add(new RecipeMapHandlerPrefixShredding(clump                  , 1, null           , 0, NF,  16, 0,   256, NF, dust                    , 1, null       , 0, NI, NI, T, F, F, new And(ANTIMATTER.NOT, MORTAR.NOT)));
		RM.Shredder             .add(new RecipeMapHandlerPrefixShredding(reduced                , 1, null           , 0, NF,  16, 0,   256, NF, dust                    , 1, null       , 0, NI, NI, T, F, F, new And(ANTIMATTER.NOT, MORTAR.NOT)));
		RM.Shredder             .add(new RecipeMapHandlerPrefixShredding(crystalline            , 1, null           , 0, NF,  16, 0,   256, NF, dust                    , 1, null       , 0, NI, NI, T, F, F, new And(ANTIMATTER.NOT, MORTAR.NOT)));
		RM.Shredder             .add(new RecipeMapHandlerPrefixShredding(cleanGravel            , 1, null           , 0, NF,  16, 0,   256, NF, dust                    , 1, null       , 0, NI, NI, T, F, F, new And(ANTIMATTER.NOT, MORTAR.NOT)));
		RM.Shredder             .add(new RecipeMapHandlerPrefixShredding(cluster                , 1, null           , 0, NF,  16, 0,   256, NF, dust                    , 2, dustTiny   , 1, NI, NI, T, F, F, new And(ANTIMATTER.NOT, MORTAR.NOT)));
		RM.Shredder             .add(new RecipeMapHandlerPrefixShredding(crushed                , 1, null           , 0, NF,  16, 0,   256, NF, dust                    , 1, dustDiv72  , 9, NI, NI, T, F, F, new And(ANTIMATTER.NOT, MORTAR.NOT)));
		RM.Shredder             .add(new RecipeMapHandlerPrefixShredding(crushedPurified        , 1, null           , 0, NF,  16, 0,   256, NF, dust                    , 1, dustDiv72  ,18, NI, NI, T, F, F, new And(ANTIMATTER.NOT, MORTAR.NOT)));
		RM.Shredder             .add(new RecipeMapHandlerPrefixShredding(crushedCentrifuged     , 1, null           , 0, NF,  16, 0,   256, NF, dust                    , 1, dustDiv72  ,27, NI, NI, T, F, F, new And(ANTIMATTER.NOT, MORTAR.NOT)));
		RM.Shredder             .add(new RecipeMapHandlerPrefixShredding(crushedTiny            , 1, null           , 0, NF,  16, 0,   256, NF, dustDiv72               , 9, null       , 0, NI, NI, T, F, F, new And(ANTIMATTER.NOT, MORTAR.NOT)));
		RM.Shredder             .add(new RecipeMapHandlerPrefixShredding(crushedPurifiedTiny    , 1, null           , 0, NF,  16, 0,   256, NF, dustDiv72               ,10, null       , 0, NI, NI, T, F, F, new And(ANTIMATTER.NOT, MORTAR.NOT)));
		RM.Shredder             .add(new RecipeMapHandlerPrefixShredding(crushedCentrifugedTiny , 1, null           , 0, NF,  16, 0,   256, NF, dustDiv72               ,11, null       , 0, NI, NI, T, F, F, new And(ANTIMATTER.NOT, MORTAR.NOT)));
		
		RM.Shredder             .add(new RecipeMapHandlerPrefixShredding(chunk                  , 1, null           , 0, NF,  16, 0,    16, NF, dust                    , 2, dustTiny   , 1, NI, NI, T, F, F, new And(ANTIMATTER.NOT, MORTAR)));
		RM.Shredder             .add(new RecipeMapHandlerPrefixShredding(rubble                 , 1, null           , 0, NF,  16, 0,    16, NF, dust                    , 2, dustTiny   , 1, NI, NI, T, F, F, new And(ANTIMATTER.NOT, MORTAR)));
		RM.Shredder             .add(new RecipeMapHandlerPrefixShredding(pebbles                , 1, null           , 0, NF,  16, 0,    16, NF, dust                    , 2, dustTiny   , 1, NI, NI, T, F, F, new And(ANTIMATTER.NOT, MORTAR)));
		RM.Shredder             .add(new RecipeMapHandlerPrefixShredding(clump                  , 1, null           , 0, NF,  16, 0,    16, NF, dust                    , 1, null       , 0, NI, NI, T, F, F, new And(ANTIMATTER.NOT, MORTAR)));
		RM.Shredder             .add(new RecipeMapHandlerPrefixShredding(reduced                , 1, null           , 0, NF,  16, 0,    16, NF, dust                    , 1, null       , 0, NI, NI, T, F, F, new And(ANTIMATTER.NOT, MORTAR)));
		RM.Shredder             .add(new RecipeMapHandlerPrefixShredding(crystalline            , 1, null           , 0, NF,  16, 0,    16, NF, dust                    , 1, null       , 0, NI, NI, T, F, F, new And(ANTIMATTER.NOT, MORTAR)));
		RM.Shredder             .add(new RecipeMapHandlerPrefixShredding(cleanGravel            , 1, null           , 0, NF,  16, 0,    16, NF, dust                    , 1, null       , 0, NI, NI, T, F, F, new And(ANTIMATTER.NOT, MORTAR)));
		RM.Shredder             .add(new RecipeMapHandlerPrefixShredding(cluster                , 1, null           , 0, NF,  16, 0,    16, NF, dust                    , 2, dustTiny   , 1, NI, NI, T, F, F, new And(ANTIMATTER.NOT, MORTAR)));
		RM.Shredder             .add(new RecipeMapHandlerPrefixShredding(crushed                , 1, null           , 0, NF,  16, 0,    16, NF, dust                    , 1, dustDiv72  , 9, NI, NI, T, F, F, new And(ANTIMATTER.NOT, MORTAR)));
		RM.Shredder             .add(new RecipeMapHandlerPrefixShredding(crushedPurified        , 1, null           , 0, NF,  16, 0,    16, NF, dust                    , 1, dustDiv72  ,18, NI, NI, T, F, F, new And(ANTIMATTER.NOT, MORTAR)));
		RM.Shredder             .add(new RecipeMapHandlerPrefixShredding(crushedCentrifuged     , 1, null           , 0, NF,  16, 0,    16, NF, dust                    , 1, dustDiv72  ,27, NI, NI, T, F, F, new And(ANTIMATTER.NOT, MORTAR)));
		RM.Shredder             .add(new RecipeMapHandlerPrefixShredding(crushedTiny            , 1, null           , 0, NF,  16, 0,    16, NF, dustDiv72               , 9, null       , 0, NI, NI, T, F, F, new And(ANTIMATTER.NOT, MORTAR)));
		RM.Shredder             .add(new RecipeMapHandlerPrefixShredding(crushedPurifiedTiny    , 1, null           , 0, NF,  16, 0,    16, NF, dustDiv72               ,10, null       , 0, NI, NI, T, F, F, new And(ANTIMATTER.NOT, MORTAR)));
		RM.Shredder             .add(new RecipeMapHandlerPrefixShredding(crushedCentrifugedTiny , 1, null           , 0, NF,  16, 0,    16, NF, dustDiv72               ,11, null       , 0, NI, NI, T, F, F, new And(ANTIMATTER.NOT, MORTAR)));
		
		for (OreDictPrefix tPrefix : OreDictPrefix.VALUES) if (tPrefix.mByProducts.isEmpty() && tPrefix.contains(RECYCLABLE) && !tPrefix.containsAny(ORE, ORE_PROCESSING_BASED, DUST_BASED, IS_CONTAINER) && !tPrefix.mNameInternal.startsWith("cableGt") && !tPrefix.mNameInternal.startsWith("wireGt") && !tPrefix.mNameInternal.startsWith("pipe")) {
		RM.Shredder             .add(new RecipeMapHandlerPrefix(tPrefix                         , 1, null           , 0, NF,  16, 0,   256, NF, null                    , 0, null       , 0, NI, NI, F, T, F, new And(ANTIMATTER.NOT, MORTAR.NOT)));
		RM.Shredder             .add(new RecipeMapHandlerPrefix(tPrefix                         , 1, null           , 0, NF,  16, 0,    16, NF, null                    , 0, null       , 0, NI, NI, F, T, F, new And(ANTIMATTER.NOT, MORTAR)));
		}
		
		RM.Anvil                .add(new RecipeMapHandlerPrefixShredding(rockGt                 , 1, null           , 0, NF,  16, 0,    16, NF, dustSmall               , 1, null       , 0, ST.emptySlot(), NI, T, F, F, ANTIMATTER.NOT));
		RM.Anvil                .add(new RecipeMapHandlerPrefixShredding(chunk                  , 1, null           , 0, NF,  16, 0,    16, NF, dust                    , 2, dustTiny   , 1, ST.emptySlot(), NI, T, F, F, new And(ANTIMATTER.NOT, MORTAR)));
		RM.Anvil                .add(new RecipeMapHandlerPrefixShredding(rubble                 , 1, null           , 0, NF,  16, 0,    16, NF, dust                    , 2, dustTiny   , 1, ST.emptySlot(), NI, T, F, F, new And(ANTIMATTER.NOT, MORTAR)));
		RM.Anvil                .add(new RecipeMapHandlerPrefixShredding(pebbles                , 1, null           , 0, NF,  16, 0,    16, NF, dust                    , 2, dustTiny   , 1, ST.emptySlot(), NI, T, F, F, new And(ANTIMATTER.NOT, MORTAR)));
		RM.Anvil                .add(new RecipeMapHandlerPrefixShredding(clump                  , 1, null           , 0, NF,  16, 0,    16, NF, dust                    , 1, null       , 0, ST.emptySlot(), NI, T, F, F, new And(ANTIMATTER.NOT, MORTAR)));
		RM.Anvil                .add(new RecipeMapHandlerPrefixShredding(reduced                , 1, null           , 0, NF,  16, 0,    16, NF, dust                    , 1, null       , 0, ST.emptySlot(), NI, T, F, F, new And(ANTIMATTER.NOT, MORTAR)));
		RM.Anvil                .add(new RecipeMapHandlerPrefixShredding(crystalline            , 1, null           , 0, NF,  16, 0,    16, NF, dust                    , 1, null       , 0, ST.emptySlot(), NI, T, F, F, new And(ANTIMATTER.NOT, MORTAR)));
		RM.Anvil                .add(new RecipeMapHandlerPrefixShredding(cleanGravel            , 1, null           , 0, NF,  16, 0,    16, NF, dust                    , 1, null       , 0, ST.emptySlot(), NI, T, F, F, new And(ANTIMATTER.NOT, MORTAR)));
		RM.Anvil                .add(new RecipeMapHandlerPrefixShredding(cluster                , 1, null           , 0, NF,  16, 0,    16, NF, dust                    , 2, dustTiny   , 1, ST.emptySlot(), NI, T, F, F, new And(ANTIMATTER.NOT, MORTAR)));
		RM.Anvil                .add(new RecipeMapHandlerPrefixShredding(crushed                , 1, null           , 0, NF,  16, 0,    16, NF, dust                    , 1, dustDiv72  , 9, ST.emptySlot(), NI, T, F, F, new And(ANTIMATTER.NOT, MORTAR)));
		RM.Anvil                .add(new RecipeMapHandlerPrefixShredding(crushedPurified        , 1, null           , 0, NF,  16, 0,    16, NF, dust                    , 1, dustDiv72  ,18, ST.emptySlot(), NI, T, F, F, new And(ANTIMATTER.NOT, MORTAR)));
		RM.Anvil                .add(new RecipeMapHandlerPrefixShredding(crushedCentrifuged     , 1, null           , 0, NF,  16, 0,    16, NF, dust                    , 1, dustDiv72  ,27, ST.emptySlot(), NI, T, F, F, new And(ANTIMATTER.NOT, MORTAR)));
		RM.Anvil                .add(new RecipeMapHandlerPrefixShredding(crushedTiny            , 1, null           , 0, NF,  16, 0,    16, NF, dustDiv72               , 9, null       , 0, ST.emptySlot(), NI, T, F, F, new And(ANTIMATTER.NOT, MORTAR)));
		RM.Anvil                .add(new RecipeMapHandlerPrefixShredding(crushedPurifiedTiny    , 1, null           , 0, NF,  16, 0,    16, NF, dustDiv72               ,10, null       , 0, ST.emptySlot(), NI, T, F, F, new And(ANTIMATTER.NOT, MORTAR)));
		RM.Anvil                .add(new RecipeMapHandlerPrefixShredding(crushedCentrifugedTiny , 1, null           , 0, NF,  16, 0,    16, NF, dustDiv72               ,11, null       , 0, ST.emptySlot(), NI, T, F, F, new And(ANTIMATTER.NOT, MORTAR)));
		
		RM.Anvil                .add(new RecipeMapHandlerPrefix(ingot                           , 1, ingot          , 1, NF,  64, 0,    64, NF, ingotDouble             , 1, null       , 0, NI, NI, T, F, F, new And(ANTIMATTER.NOT, FLAMMABLE.NOT, SMITHABLE, COATED.NOT)));
		RM.Anvil                .add(new RecipeMapHandlerPrefix(ingot                           , 1, ingotDouble    , 1, NF,  64, 0,    64, NF, ingotTriple             , 1, null       , 0, NI, NI, T, F, F, new And(ANTIMATTER.NOT, FLAMMABLE.NOT, SMITHABLE, COATED.NOT)));
		RM.Anvil                .add(new RecipeMapHandlerPrefix(ingot                           , 1, ingotTriple    , 1, NF,  64, 0,    64, NF, ingotQuadruple          , 1, null       , 0, NI, NI, T, F, F, new And(ANTIMATTER.NOT, FLAMMABLE.NOT, SMITHABLE, COATED.NOT)));
		RM.Anvil                .add(new RecipeMapHandlerPrefix(ingot                           , 1, ingotQuadruple , 1, NF,  64, 0,    64, NF, ingotQuintuple          , 1, null       , 0, NI, NI, T, F, F, new And(ANTIMATTER.NOT, FLAMMABLE.NOT, SMITHABLE, COATED.NOT)));
		RM.Anvil                .add(new RecipeMapHandlerPrefix(ingotDouble                     , 1, ingotDouble    , 1, NF,  64, 0,    64, NF, ingotQuadruple          , 1, null       , 0, NI, NI, T, F, F, new And(ANTIMATTER.NOT, FLAMMABLE.NOT, SMITHABLE, COATED.NOT)));
		RM.Anvil                .add(new RecipeMapHandlerPrefix(ingotDouble                     , 1, ingotTriple    , 1, NF,  64, 0,    64, NF, ingotQuintuple          , 1, null       , 0, NI, NI, T, F, F, new And(ANTIMATTER.NOT, FLAMMABLE.NOT, SMITHABLE, COATED.NOT)));
		RM.Anvil                .add(new RecipeMapHandlerPrefix(plate                           , 1, plate          , 1, NF,  64, 0,    64, NF, plateDouble             , 1, null       , 0, NI, NI, T, F, F, new And(ANTIMATTER.NOT, FLAMMABLE.NOT, SMITHABLE, COATED.NOT)));
		RM.Anvil                .add(new RecipeMapHandlerPrefix(plate                           , 1, plateDouble    , 1, NF,  64, 0,    64, NF, plateTriple             , 1, null       , 0, NI, NI, T, F, F, new And(ANTIMATTER.NOT, FLAMMABLE.NOT, SMITHABLE, COATED.NOT)));
		RM.Anvil                .add(new RecipeMapHandlerPrefix(plate                           , 1, plateTriple    , 1, NF,  64, 0,    64, NF, plateQuadruple          , 1, null       , 0, NI, NI, T, F, F, new And(ANTIMATTER.NOT, FLAMMABLE.NOT, SMITHABLE, COATED.NOT)));
		RM.Anvil                .add(new RecipeMapHandlerPrefix(plate                           , 1, plateQuadruple , 1, NF,  64, 0,    64, NF, plateQuintuple          , 1, null       , 0, NI, NI, T, F, F, new And(ANTIMATTER.NOT, FLAMMABLE.NOT, SMITHABLE, COATED.NOT)));
		RM.Anvil                .add(new RecipeMapHandlerPrefix(plateDouble                     , 1, plateDouble    , 1, NF,  64, 0,    64, NF, plateQuadruple          , 1, null       , 0, NI, NI, T, F, F, new And(ANTIMATTER.NOT, FLAMMABLE.NOT, SMITHABLE, COATED.NOT)));
		RM.Anvil                .add(new RecipeMapHandlerPrefix(plateDouble                     , 1, plateTriple    , 1, NF,  64, 0,    64, NF, plateQuintuple          , 1, null       , 0, NI, NI, T, F, F, new And(ANTIMATTER.NOT, FLAMMABLE.NOT, SMITHABLE, COATED.NOT)));
		RM.Anvil                .add(new RecipeMapHandlerPrefix(stick                           , 1, stick          , 1, NF,  64, 0,    64, NF, stickLong               , 1, null       , 0, NI, NI, T, F, F, new And(ANTIMATTER.NOT, FLAMMABLE.NOT, SMITHABLE, COATED.NOT)));
		RM.Anvil                .add(new RecipeMapHandlerPrefix(ring                            , 2, ring           , 2, NF,  64, 0,    64, NF, chain                   , 1, null       , 0, NI, NI, T, F, F, new And(ANTIMATTER.NOT, FLAMMABLE.NOT, SMITHABLE)));
		
		RM.Anvil                .add(new RecipeMapHandlerPrefix(chunkGt                         , 1, null           , 0, NF,  64, 0,    64, NF, plateTiny               , 1, scrapGt    , 1, ST.emptySlot(), NI, T, F, F, new And(ANTIMATTER.NOT, FLAMMABLE.NOT, SMITHABLE, COATED.NOT)).chances(10000, 9000));
		RM.Anvil                .add(new RecipeMapHandlerPrefix(ingotDouble                     , 1, null           , 0, NF,  64, 0,    64, NF, plate                   , 1, scrapGt    , 9, ST.emptySlot(), NI, T, F, F, new And(ANTIMATTER.NOT, FLAMMABLE.NOT, SMITHABLE, COATED.NOT)).chances(10000, 9000));
		RM.Anvil                .add(new RecipeMapHandlerPrefix(ingotTriple                     , 1, null           , 0, NF,  64, 0,    64, NF, plateDouble             , 1, scrapGt    , 9, ST.emptySlot(), NI, T, F, F, new And(ANTIMATTER.NOT, FLAMMABLE.NOT, SMITHABLE, COATED.NOT)).chances(10000, 9000));
		RM.Anvil                .add(new RecipeMapHandlerPrefix(ingotQuadruple                  , 1, null           , 0, NF,  64, 0,    64, NF, plateTriple             , 1, scrapGt    , 9, ST.emptySlot(), NI, T, F, F, new And(ANTIMATTER.NOT, FLAMMABLE.NOT, SMITHABLE, COATED.NOT)).chances(10000, 9000));
		RM.Anvil                .add(new RecipeMapHandlerPrefix(ingotQuintuple                  , 1, null           , 0, NF,  64, 0,    64, NF, plateQuadruple          , 1, scrapGt    , 9, ST.emptySlot(), NI, T, F, F, new And(ANTIMATTER.NOT, FLAMMABLE.NOT, SMITHABLE, COATED.NOT)).chances(10000, 9000));
		RM.Anvil                .add(new RecipeMapHandlerPrefix(plate                           , 1, null           , 0, NF,  64, 0,    64, NF, casingSmall             , 1, scrapGt    , 4, ST.emptySlot(), NI, T, F, F, new And(ANTIMATTER.NOT, FLAMMABLE.NOT, SMITHABLE, COATED.NOT)).chances(10000, 9000));
		RM.Anvil                .add(new RecipeMapHandlerPrefix(plateCurved                     , 1, null           , 0, NF,  64, 0,    64, NF, plate                   , 1, null       , 0, ST.emptySlot(), NI, T, F, F, new And(ANTIMATTER.NOT, FLAMMABLE.NOT, SMITHABLE, COATED.NOT)));
		RM.Anvil                .add(new RecipeMapHandlerPrefix(gemLegendary                    , 1, null           , 0, NF,  64, 0,    64, NF, gemExquisite            , 2, null       , 0, ST.emptySlot(), NI, T, F, F, new And(ANTIMATTER.NOT)));
		RM.Anvil                .add(new RecipeMapHandlerPrefix(gemExquisite                    , 1, null           , 0, NF,  64, 0,    64, NF, gemFlawless             , 2, null       , 0, ST.emptySlot(), NI, T, F, F, new And(ANTIMATTER.NOT)));
		RM.Anvil                .add(new RecipeMapHandlerPrefix(gemFlawless                     , 1, null           , 0, NF,  64, 0,    64, NF, gem                     , 2, null       , 0, ST.emptySlot(), NI, T, F, F, new And(ANTIMATTER.NOT)));
		RM.Anvil                .add(new RecipeMapHandlerPrefix(gem                             , 1, null           , 0, NF,  64, 0,    64, NF, gemFlawed               , 2, null       , 0, ST.emptySlot(), NI, T, F, F, new And(ANTIMATTER.NOT)));
		RM.Anvil                .add(new RecipeMapHandlerPrefix(gemFlawed                       , 1, null           , 0, NF,  64, 0,    64, NF, gemChipped              , 2, null       , 0, ST.emptySlot(), NI, T, F, F, new And(ANTIMATTER.NOT)));
		RM.Anvil                .add(new RecipeMapHandlerPrefix(gemChipped                      , 1, null           , 0, NF,  64, 0,    64, NF, dustSmall               , 1, null       , 0, ST.emptySlot(), NI, T, F, F, new And(ANTIMATTER.NOT)));
		
		RM.AnvilBendBig         .add(new RecipeMapHandlerPrefix(plate                           , 1, null           , 0, NF,  64, 0,    64, NF, plateCurved             , 1, null       , 0, ST.emptySlot(), NI, T, F, F, new And(ANTIMATTER.NOT, FLAMMABLE.NOT, SMITHABLE)));
		RM.AnvilBendBig         .add(new RecipeMapHandlerPrefix(stick                           , 1, null           , 0, NF,  64, 0,    64, NF, springSmall             , 1, scrapGt    , 2, ST.emptySlot(), NI, T, F, F, new And(ANTIMATTER.NOT, FLAMMABLE.NOT, SMITHABLE)).chances(10000, 9000));
		RM.AnvilBendBig         .add(new RecipeMapHandlerPrefix(stickLong                       , 1, null           , 0, NF,  64, 0,    64, NF, spring                  , 1, null       , 0, ST.emptySlot(), NI, T, F, F, new And(ANTIMATTER.NOT, FLAMMABLE.NOT, SMITHABLE)));
		
		RM.AnvilBendSmall       .add(new RecipeMapHandlerPrefix(plate                           , 1, null           , 0, NF,  64, 0,    64, NF, foil                    , 2, scrapGt    , 4, ST.emptySlot(), NI, T, F, F, new And(ANTIMATTER.NOT, FLAMMABLE.NOT, SMITHABLE, COATED.NOT)).chances(10000, 9000));
		RM.AnvilBendSmall       .add(new RecipeMapHandlerPrefix(stick                           , 1, null           , 0, NF,  64, 0,    64, NF, ring                    , 1, scrapGt    , 2, ST.emptySlot(), NI, T, F, F, new And(ANTIMATTER.NOT, FLAMMABLE.NOT, SMITHABLE, COATED.NOT)).chances(10000, 9000));
		
		RM.Compressor           .add(new RecipeMapHandlerPrefix(dust                            , 1, NF,  16, 0,   256, NF, plateGem        , 1, NI, NI, T, F, F, new Nor(gemLegendary, gemExquisite, gemFlawless, bouleGt, MT.Ice, ANTIMATTER, LAYERED, COATED)));
		RM.Compressor           .add(new RecipeMapHandlerPrefix(compressed                      , 9, NF,  16, 0,   256, NF, plateDense      , 1, NI, NI, T, F, F, new And(ANTIMATTER.NOT, COATED.NOT)));
		RM.Compressor           .add(new RecipeMapHandlerPrefix(plate                           , 9, NF,  16, 0,   256, NF, plateDense      , 1, NI, NI, T, F, F, new And(ANTIMATTER.NOT, COATED.NOT)));
		RM.Compressor           .add(new RecipeMapHandlerPrefix(plateTriple                     , 3, NF,  16, 0,   256, NF, plateDense      , 1, NI, NI, T, F, F, new And(ANTIMATTER.NOT, COATED.NOT)));
		RM.Compressor           .add(new RecipeMapHandlerPrefix(blockPlate                      , 1, NF,  16, 0,   256, NF, plateDense      , 1, NI, NI, T, F, F, new And(ANTIMATTER.NOT, COATED.NOT)));
		RM.Compressor           .add(new RecipeMapHandlerPrefix(blockSolid                      , 1, NF,  16, 0,   256, NF, plateDense      , 1, NI, NI, T, F, F, new And(ANTIMATTER.NOT, COATED.NOT)));
		RM.Compressor           .add(new RecipeMapHandlerPrefix(ingot                           , 1, NF,  16, 0,   256, NF, compressed      , 1, NI, NI, T, F, F, new And(ANTIMATTER.NOT, COATED.NOT)));
		if (IL.IC2_Plantball.exists()) {
		RM.Compressor           .add(new RecipeMapHandlerPrefix(plantGtBerry                    ,16, NF,  16,16,     0, NF, null, 0, NI, IL.IC2_Plantball.get(1), F, F, F, ANTIMATTER.NOT));
		RM.Compressor           .add(new RecipeMapHandlerPrefix(plantGtBlossom                  , 8, NF,  16,16,     0, NF, null, 0, NI, IL.IC2_Plantball.get(1), F, F, F, ANTIMATTER.NOT));
		RM.Compressor           .add(new RecipeMapHandlerPrefix(plantGtTwig                     , 4, NF,  16,16,     0, NF, null, 0, NI, IL.IC2_Plantball.get(1), F, F, F, ANTIMATTER.NOT));
		RM.Compressor           .add(new RecipeMapHandlerPrefix(plantGtWart                     , 8, NF,  16,16,     0, NF, null, 0, NI, IL.IC2_Plantball.get(1), F, F, F, ANTIMATTER.NOT));
		}
		
		RM.Press                .add(new RecipeMapHandlerPrefix(toolHeadArrow                   , 1, NF,  16,16,     0, NF, arrowGtWood     , 1, OP.arrowGtWood     .mat(MT.Empty, 1), NI, T, F, F, new And(ANTIMATTER.NOT, MT.Empty.NOT)));
		RM.Press                .add(new RecipeMapHandlerPrefix(toolHeadArrow                   , 1, NF,  16,16,     0, NF, arrowGtPlastic  , 1, OP.arrowGtPlastic  .mat(MT.Empty, 1), NI, T, F, F, new And(ANTIMATTER.NOT, MT.Empty.NOT)));
		RM.Press                .add(new RecipeMapHandlerPrefix(round                           , 1, NF,  16,16,     0, NF, bulletGtSmall   , 1, OP.bulletGtSmall   .mat(MT.Empty, 1), NI, T, F, F, new And(ANTIMATTER.NOT, MT.Empty.NOT)));
		RM.Press                .add(new RecipeMapHandlerPrefix(round                           , 2, NF,  16,32,     0, NF, bulletGtMedium  , 1, OP.bulletGtMedium  .mat(MT.Empty, 1), NI, T, F, F, new And(ANTIMATTER.NOT, MT.Empty.NOT)));
		RM.Press                .add(new RecipeMapHandlerPrefix(round                           , 3, NF,  16,64,     0, NF, bulletGtLarge   , 1, OP.bulletGtLarge   .mat(MT.Empty, 1), NI, T, F, F, new And(ANTIMATTER.NOT, MT.Empty.NOT)));
		
		RM.RollingMill          .add(new RecipeMapHandlerPrefix(compressed                      , 1, NF,  16, 0,   256, NF, plate           , 1, NI, NI, T, F, F, new And(ANTIMATTER.NOT, COATED.NOT, SMITHABLE)));
		RM.RollingMill          .add(new RecipeMapHandlerPrefix(plateCurved                     , 1, NF,  16, 0,   256, NF, plate           , 1, NI, NI, T, F, F, new And(ANTIMATTER.NOT, COATED.NOT, SMITHABLE)));
		RM.RollingMill          .add(new RecipeMapHandlerPrefix(ingot                           , 1, NF,  16, 0,   256, NF, plate           , 1, NI, NI, T, F, F, new And(ANTIMATTER.NOT, COATED.NOT, SMITHABLE)));
		RM.RollingMill          .add(new RecipeMapHandlerPrefix(ingotDouble                     , 1, NF,  16, 0,   256, NF, plateDouble     , 1, NI, NI, T, F, F, new And(ANTIMATTER.NOT, COATED.NOT, SMITHABLE)));
		RM.RollingMill          .add(new RecipeMapHandlerPrefix(ingotTriple                     , 1, NF,  16, 0,   256, NF, plateTriple     , 1, NI, NI, T, F, F, new And(ANTIMATTER.NOT, COATED.NOT, SMITHABLE)));
		RM.RollingMill          .add(new RecipeMapHandlerPrefix(ingotQuadruple                  , 1, NF,  16, 0,   256, NF, plateQuadruple  , 1, NI, NI, T, F, F, new And(ANTIMATTER.NOT, COATED.NOT, SMITHABLE)));
		RM.RollingMill          .add(new RecipeMapHandlerPrefix(ingotQuintuple                  , 1, NF,  16, 0,   256, NF, plateQuintuple  , 1, NI, NI, T, F, F, new And(ANTIMATTER.NOT, COATED.NOT, SMITHABLE)));
		RM.RollingMill          .add(new RecipeMapHandlerPrefix(blockSolid                      , 1, NF,  16, 0,   256, NF, plateDense      , 1, NI, NI, T, F, F, new And(ANTIMATTER.NOT, COATED.NOT, SMITHABLE)));
		RM.RollingMill          .add(new RecipeMapHandlerPrefix(plate                           , 1, NF,  16, 0,   256, NF, sheetGt         , 1, NI, NI, T, F, F, new And(ANTIMATTER.NOT, COATED.NOT, SMITHABLE)));
		
		RM.Wiremill             .add(new RecipeMapHandlerPrefix(stick                           , 1, NF,  16, 0,   128, NF, wireFine        , 4, NI, NI, T, F, F, new And(ANTIMATTER.NOT, COATED.NOT, SMITHABLE)));
		RM.Wiremill             .add(new RecipeMapHandlerPrefix(stickLong                       , 1, NF,  16, 0,   128, NF, wireFine        , 8, NI, NI, T, F, F, new And(ANTIMATTER.NOT, COATED.NOT, SMITHABLE)));
		RM.Wiremill             .add(new RecipeMapHandlerPrefix(ingot                           , 1, NF,  16, 0,   128, NF, wireGt01        , 2, NI, NI, T, F, F, new And(ANTIMATTER.NOT, COATED.NOT, SMITHABLE)));
		RM.Wiremill             .add(new RecipeMapHandlerPrefix(compressed                      , 1, NF,  16, 0,   128, NF, wireGt01        , 2, NI, NI, T, F, F, new And(ANTIMATTER.NOT, COATED.NOT, SMITHABLE)));
		
		RM.RollBender           .add(new RecipeMapHandlerPrefix(wireFine                        , 2, NF,  16, 0,   256, NF, springSmall     , 1, NI, NI, T, F, F, new And(ANTIMATTER.NOT, SMITHABLE)));
		RM.RollBender           .add(new RecipeMapHandlerPrefix(stick                           , 1, NF,  16, 0,   256, NF, ring            , 2, NI, NI, T, F, F, new And(ANTIMATTER.NOT, SMITHABLE)));
		RM.RollBender           .add(new RecipeMapHandlerPrefix(stickLong                       , 1, NF,  16, 0,   256, NF, spring          , 1, NI, NI, T, F, F, new And(ANTIMATTER.NOT, SMITHABLE)));
		RM.RollBender           .add(new RecipeMapHandlerPrefix(plate                           , 1, NF,  16, 0,   256, NF, plateCurved     , 1, NI, NI, T, F, F, new And(ANTIMATTER.NOT, SMITHABLE)));
		
		RM.ClusterMill          .add(new RecipeMapHandlerPrefix(plate                           , 1, NF,  16, 0,   256, NF, foil            , 4, NI, NI, T, F, F, new And(ANTIMATTER.NOT, SMITHABLE)));
		
		RM.RollFormer           .add(new RecipeMapHandlerPrefix(plate                           , 1, NF,  16, 0,    64, NF, railGt          , 4, NI, NI, T, F, F, new And(ANTIMATTER.NOT, COATED.NOT, SMITHABLE)));
		
		RM.Loom                 .add(new RecipeMapHandlerPrefix(wireGt01                        , 2, NF,  16, 0,    64, NF, wireGt02        , 1, ST.tag( 2), NI, F, F, F, ANTIMATTER.NOT));
		RM.Loom                 .add(new RecipeMapHandlerPrefix(wireGt01                        , 4, NF,  16, 0,    64, NF, wireGt04        , 1, ST.tag( 4), NI, F, F, F, ANTIMATTER.NOT));
		RM.Loom                 .add(new RecipeMapHandlerPrefix(wireGt01                        , 8, NF,  16, 0,    64, NF, wireGt08        , 1, ST.tag( 8), NI, F, F, F, ANTIMATTER.NOT));
		RM.Loom                 .add(new RecipeMapHandlerPrefix(wireGt01                        ,12, NF,  16, 0,    64, NF, wireGt12        , 1, ST.tag(12), NI, F, F, F, ANTIMATTER.NOT));
		RM.Loom                 .add(new RecipeMapHandlerPrefix(wireGt01                        ,16, NF,  16, 0,    64, NF, wireGt16        , 1, ST.tag(16), NI, F, F, F, ANTIMATTER.NOT));
		RM.Loom                 .add(new RecipeMapHandlerPrefix(wireGt02                        , 2, NF,  16, 0,    64, NF, wireGt04        , 1, ST.tag( 4), NI, F, F, F, ANTIMATTER.NOT));
		RM.Loom                 .add(new RecipeMapHandlerPrefix(wireGt02                        , 4, NF,  16, 0,    64, NF, wireGt08        , 1, ST.tag( 8), NI, F, F, F, ANTIMATTER.NOT));
		RM.Loom                 .add(new RecipeMapHandlerPrefix(wireGt02                        , 6, NF,  16, 0,    64, NF, wireGt12        , 1, ST.tag(12), NI, F, F, F, ANTIMATTER.NOT));
		RM.Loom                 .add(new RecipeMapHandlerPrefix(wireGt02                        , 8, NF,  16, 0,    64, NF, wireGt16        , 1, ST.tag(16), NI, F, F, F, ANTIMATTER.NOT));
		RM.Loom                 .add(new RecipeMapHandlerPrefix(wireGt04                        , 2, NF,  16, 0,    64, NF, wireGt08        , 1, ST.tag( 8), NI, F, F, F, ANTIMATTER.NOT));
		RM.Loom                 .add(new RecipeMapHandlerPrefix(wireGt04                        , 3, NF,  16, 0,    64, NF, wireGt12        , 1, ST.tag(12), NI, F, F, F, ANTIMATTER.NOT));
		RM.Loom                 .add(new RecipeMapHandlerPrefix(wireGt04                        , 4, NF,  16, 0,    64, NF, wireGt16        , 1, ST.tag(16), NI, F, F, F, ANTIMATTER.NOT));
		RM.Loom                 .add(new RecipeMapHandlerPrefix(wireGt08                        , 2, NF,  16, 0,    64, NF, wireGt16        , 1, ST.tag(16), NI, F, F, F, ANTIMATTER.NOT));
		
		RM.Welder               .add(new RecipeMapHandlerPrefix(ingot                           , 2, NF,  16, 0,    64, NF, ingotDouble     , 1, ST.tag( 2), NI, T, F, F, new And(ANTIMATTER.NOT, COATED.NOT, FLAMMABLE.NOT, SMITHABLE)));
		RM.Welder               .add(new RecipeMapHandlerPrefix(ingot                           , 3, NF,  16, 0,    64, NF, ingotTriple     , 1, ST.tag( 3), NI, T, F, F, new And(ANTIMATTER.NOT, COATED.NOT, FLAMMABLE.NOT, SMITHABLE)));
		RM.Welder               .add(new RecipeMapHandlerPrefix(ingot                           , 4, NF,  16, 0,    64, NF, ingotQuadruple  , 1, ST.tag( 4), NI, T, F, F, new And(ANTIMATTER.NOT, COATED.NOT, FLAMMABLE.NOT, SMITHABLE)));
		RM.Welder               .add(new RecipeMapHandlerPrefix(ingot                           , 5, NF,  16, 0,    64, NF, ingotQuintuple  , 1, ST.tag( 5), NI, T, F, F, new And(ANTIMATTER.NOT, COATED.NOT, FLAMMABLE.NOT, SMITHABLE)));
		RM.Welder               .add(new RecipeMapHandlerPrefix(ingot                           , 9, NF,  16, 0,    64, NF, blockSolid      , 1, ST.tag( 9), NI, T, F, F, new And(ANTIMATTER.NOT, COATED.NOT, FLAMMABLE.NOT, SMITHABLE)));
		RM.Welder               .add(new RecipeMapHandlerPrefix(casingSmall                     , 2, NF,  16, 0,    64, NF, plate           , 1, ST.tag( 2), NI, T, F, F, new And(ANTIMATTER.NOT, COATED.NOT, FLAMMABLE.NOT, SMITHABLE)));
		RM.Welder               .add(new RecipeMapHandlerPrefix(bolt                            , 4, NF,  16, 0,    64, NF, stick           , 1, ST.tag( 4), NI, T, F, F, new And(ANTIMATTER.NOT, COATED.NOT, FLAMMABLE.NOT, SMITHABLE)));
		RM.Welder               .add(new RecipeMapHandlerPrefix(bolt                            , 8, NF,  16, 0,    64, NF, stickLong       , 1, ST.tag( 8), NI, T, F, F, new And(ANTIMATTER.NOT, COATED.NOT, FLAMMABLE.NOT, SMITHABLE)));
		RM.Welder               .add(new RecipeMapHandlerPrefix(stick                           , 2, NF,  16, 0,    64, NF, stickLong       , 1, ST.tag( 2), NI, T, F, F, new And(ANTIMATTER.NOT, COATED.NOT, FLAMMABLE.NOT, SMITHABLE)));
		
		RM.Welder               .add(new RecipeMapHandlerPrefix(plateCurved                     , 4, ring           , 1, NF,  16, 0,    64, NF, rotor                   , 1, null       , 0, NI, NI, T, F, F, new And(ANTIMATTER.NOT, FLAMMABLE.NOT, SMITHABLE)));
		RM.Welder               .add(new RecipeMapHandlerPrefix(plate                           , 6, stickLong      , 2, NF,  16, 0,    64, NF, casingMachine           , 1, null       , 0, NI, NI, T, F, F, new And(ANTIMATTER.NOT, FLAMMABLE.NOT, SMITHABLE)));
		RM.Welder               .add(new RecipeMapHandlerPrefix(plateDouble                     , 6, stickLong      , 2, NF,  16, 0,    64, NF, casingMachineDouble     , 1, null       , 0, NI, NI, T, F, F, new And(ANTIMATTER.NOT, FLAMMABLE.NOT, SMITHABLE)));
		RM.Welder               .add(new RecipeMapHandlerPrefix(plateQuadruple                  , 6, stickLong      , 2, NF,  16, 0,    64, NF, casingMachineQuadruple  , 1, null       , 0, NI, NI, T, F, F, new And(ANTIMATTER.NOT, FLAMMABLE.NOT, SMITHABLE)));
		RM.Welder               .add(new RecipeMapHandlerPrefix(plateDense                      , 6, stickLong      , 2, NF,  16, 0,    64, NF, casingMachineDense      , 1, null       , 0, NI, NI, T, F, F, new And(ANTIMATTER.NOT, FLAMMABLE.NOT, SMITHABLE)));
		RM.Welder               .add(new RecipeMapHandlerPrefix(plate                           , 6, stick          , 4, NF,  16, 0,    64, NF, casingMachine           , 1, null       , 0, NI, NI, T, F, F, new And(ANTIMATTER.NOT, FLAMMABLE.NOT, SMITHABLE)));
		RM.Welder               .add(new RecipeMapHandlerPrefix(plateDouble                     , 6, stick          , 4, NF,  16, 0,    64, NF, casingMachineDouble     , 1, null       , 0, NI, NI, T, F, F, new And(ANTIMATTER.NOT, FLAMMABLE.NOT, SMITHABLE)));
		RM.Welder               .add(new RecipeMapHandlerPrefix(plateQuadruple                  , 6, stick          , 4, NF,  16, 0,    64, NF, casingMachineQuadruple  , 1, null       , 0, NI, NI, T, F, F, new And(ANTIMATTER.NOT, FLAMMABLE.NOT, SMITHABLE)));
		RM.Welder               .add(new RecipeMapHandlerPrefix(plateDense                      , 6, stick          , 4, NF,  16, 0,    64, NF, casingMachineDense      , 1, null       , 0, NI, NI, T, F, F, new And(ANTIMATTER.NOT, FLAMMABLE.NOT, SMITHABLE)));
		
		RM.Lathe                .add(new RecipeMapHandlerPrefix(bolt                                                , 1, NF,  16, 0,    64, NF, screw                                   , 1, NI, NI, T, T, F, new And(ANTIMATTER.NOT, COATED.NOT)));
		RM.Lathe                .add(new RecipeMapHandlerPrefix(nugget                                              , 1, NF,  16, 0,    64, NF, round                                   , 1, NI, NI, T, T, F, new And(ANTIMATTER.NOT, COATED.NOT, LAYERED.NOT)));
		RM.Lathe                .add(new RecipeMapHandlerPrefix(plateGem                                            , 1, NF,  16, 0,    64, NF, lens                                    , 1, NI, NI, T, T, F, new And(ANTIMATTER.NOT, COATED.NOT)));
		RM.Lathe                .add(new RecipeMapHandlerPrefix(ingot                                               , 1, NF,  16, 0,    64, NF, stick                                   , 1, NI, NI, T, T, F, new And(ANTIMATTER.NOT, COATED.NOT, LAYERED.NOT)));
		RM.Lathe                .add(new RecipeMapHandlerPrefix(bouleGt                                             , 1, NF,  16, 0,    64, NF, stickLong                               , 3, NI, NI, T, T, F, new And(ANTIMATTER.NOT, COATED.NOT, LAYERED.NOT)));
		RM.Lathe                .add(new RecipeMapHandlerPrefix(gemChipped                                          , 1, NF,  16, 0,    64, NF, bolt                                    , 1, NI, NI, T, T, F, new And(ANTIMATTER.NOT, COATED.NOT, LAYERED.NOT)));
		RM.Lathe                .add(new RecipeMapHandlerPrefix(gemFlawed                                           , 1, NF,  16, 0,    64, NF, bolt                                    , 3, NI, NI, T, T, F, new And(ANTIMATTER.NOT, COATED.NOT, LAYERED.NOT)));
		
		RM.Sharpening           .add(new RecipeMapHandlerPrefix(nugget                                              , 1, NF,  16, 0,   256, NF, round                                   , 1, NI, NI, T, T, F, new And(ANTIMATTER.NOT, COATED.NOT)).chances(10000, 7500));
		RM.Sharpening           .add(new RecipeMapHandlerPrefix(plateGem                                            , 1, NF,  16, 0,   256, NF, lens                                    , 1, NI, NI, T, T, F, new And(ANTIMATTER.NOT, COATED.NOT)).chances(10000, 7500));
		RM.Sharpening           .add(new RecipeMapHandlerPrefix(ingot                                               , 1, NF,  16, 0,   256, NF, stick                                   , 1, NI, NI, T, T, F, new And(ANTIMATTER.NOT, COATED.NOT)).chances(10000, 7500));
		RM.Sharpening           .add(new RecipeMapHandlerPrefix(gemChipped                                          , 1, NF,  16, 0,   256, NF, toolHeadArrow                           , 1, NI, NI, T, T, F, new And(ANTIMATTER.NOT, COATED.NOT)).chances(10000, 7500));
		RM.Sharpening           .add(new RecipeMapHandlerPrefix(rockGt                                              , 1, NF,  16, 0,    64, NF, toolHeadArrow                           , 1, NI, NI, T, T, F, new And(ANTIMATTER.NOT, COATED.NOT, STONE)).chances(10000, 7500));
		RM.Sharpening           .add(new RecipeMapHandlerPrefix(toolHeadRawArrow                                    , 1, NF,  16, 0,    64, NF, toolHeadArrow                           , 1, NI, NI, T, T, F, ANTIMATTER.NOT));
		RM.Sharpening           .add(new RecipeMapHandlerPrefix(toolHeadRawSaw                                      , 1, NF,  16, 0,    64, NF, toolHeadSaw                             , 1, NI, NI, T, T, F, ANTIMATTER.NOT));
		RM.Sharpening           .add(new RecipeMapHandlerPrefix(toolHeadRawChisel                                   , 1, NF,  16, 0,    64, NF, toolHeadChisel                          , 1, NI, NI, T, T, F, ANTIMATTER.NOT));
		RM.Sharpening           .add(new RecipeMapHandlerPrefix(toolHeadRawSword                                    , 1, NF,  16, 0,    64, NF, toolHeadSword                           , 1, NI, NI, T, T, F, ANTIMATTER.NOT));
		RM.Sharpening           .add(new RecipeMapHandlerPrefix(toolHeadRawPickaxe                                  , 1, NF,  16, 0,    64, NF, toolHeadPickaxe                         , 1, NI, NI, T, T, F, ANTIMATTER.NOT));
		RM.Sharpening           .add(new RecipeMapHandlerPrefix(toolHeadRawShovel                                   , 1, NF,  16, 0,    64, NF, toolHeadShovel                          , 1, NI, NI, T, T, F, ANTIMATTER.NOT));
		RM.Sharpening           .add(new RecipeMapHandlerPrefix(toolHeadRawSpade                                    , 1, NF,  16, 0,    64, NF, toolHeadSpade                           , 1, NI, NI, T, T, F, ANTIMATTER.NOT));
		RM.Sharpening           .add(new RecipeMapHandlerPrefix(toolHeadRawUniversalSpade                           , 1, NF,  16, 0,    64, NF, toolHeadUniversalSpade                  , 1, NI, NI, T, T, F, ANTIMATTER.NOT));
		RM.Sharpening           .add(new RecipeMapHandlerPrefix(toolHeadRawAxe                                      , 1, NF,  16, 0,    64, NF, toolHeadAxe                             , 1, NI, NI, T, T, F, ANTIMATTER.NOT));
		RM.Sharpening           .add(new RecipeMapHandlerPrefix(toolHeadRawAxeDouble                                , 1, NF,  16, 0,    64, NF, toolHeadAxeDouble                       , 1, NI, NI, T, T, F, ANTIMATTER.NOT));
		RM.Sharpening           .add(new RecipeMapHandlerPrefix(toolHeadRawHoe                                      , 1, NF,  16, 0,    64, NF, toolHeadHoe                             , 1, NI, NI, T, T, F, ANTIMATTER.NOT));
		RM.Sharpening           .add(new RecipeMapHandlerPrefix(toolHeadRawSense                                    , 1, NF,  16, 0,    64, NF, toolHeadSense                           , 1, NI, NI, T, T, F, ANTIMATTER.NOT));
		RM.Sharpening           .add(new RecipeMapHandlerPrefix(toolHeadRawPlow                                     , 1, NF,  16, 0,    64, NF, toolHeadPlow                            , 1, NI, NI, T, T, F, ANTIMATTER.NOT));
		
		lens                    .addListener(new OreProcessing_CraftFrom( 1, null, new String[][] {{"X ", " f"}}, plateGem                  , null, null, null, null, new And(ANTIMATTER.NOT, COATED.NOT)));
		stick                   .addListener(new OreProcessing_CraftFrom( 1, null, new String[][] {{"X ", " f"}}, ingot                     , null, null, null, null, new And(ANTIMATTER.NOT, COATED.NOT)));
		toolHeadArrow           .addListener(new OreProcessing_CraftFrom( 1, null, new String[][] {{"X ", " f"}}, gemChipped                , null, null, null, null, new And(ANTIMATTER.NOT, COATED.NOT)));
		toolHeadArrow           .addListener(new OreProcessing_CraftFrom( 1, null, new String[][] {{"X ", " f"}}, rockGt                    , null, null, null, null, new And(ANTIMATTER.NOT, COATED.NOT, STONE)));
		toolHeadArrow           .addListener(new OreProcessing_CraftFrom( 1, null, new String[][] {{"X ", " f"}}, toolHeadRawArrow          , null, null, null, null, ANTIMATTER.NOT));
		toolHeadSaw             .addListener(new OreProcessing_CraftFrom( 1, null, new String[][] {{"X ", " f"}}, toolHeadRawSaw            , null, null, null, null, ANTIMATTER.NOT));
		toolHeadChisel          .addListener(new OreProcessing_CraftFrom( 1, null, new String[][] {{"X ", " f"}}, toolHeadRawChisel         , null, null, null, null, ANTIMATTER.NOT));
		toolHeadSword           .addListener(new OreProcessing_CraftFrom( 1, null, new String[][] {{"X ", " f"}}, toolHeadRawSword          , null, null, null, null, ANTIMATTER.NOT));
		toolHeadPickaxe         .addListener(new OreProcessing_CraftFrom( 1, null, new String[][] {{"X ", " f"}}, toolHeadRawPickaxe        , null, null, null, null, ANTIMATTER.NOT));
		toolHeadShovel          .addListener(new OreProcessing_CraftFrom( 1, null, new String[][] {{"X ", " f"}}, toolHeadRawShovel         , null, null, null, null, ANTIMATTER.NOT));
		toolHeadSpade           .addListener(new OreProcessing_CraftFrom( 1, null, new String[][] {{"X ", " f"}}, toolHeadRawSpade          , null, null, null, null, ANTIMATTER.NOT));
		toolHeadUniversalSpade  .addListener(new OreProcessing_CraftFrom( 1, null, new String[][] {{"X ", " f"}}, toolHeadRawUniversalSpade , null, null, null, null, ANTIMATTER.NOT));
		toolHeadAxe             .addListener(new OreProcessing_CraftFrom( 1, null, new String[][] {{"X ", " f"}}, toolHeadRawAxe            , null, null, null, null, ANTIMATTER.NOT));
		toolHeadAxeDouble       .addListener(new OreProcessing_CraftFrom( 1, null, new String[][] {{"X ", " f"}}, toolHeadRawAxeDouble      , null, null, null, null, ANTIMATTER.NOT));
		toolHeadHoe             .addListener(new OreProcessing_CraftFrom( 1, null, new String[][] {{"X ", " f"}}, toolHeadRawHoe            , null, null, null, null, ANTIMATTER.NOT));
		toolHeadSense           .addListener(new OreProcessing_CraftFrom( 1, null, new String[][] {{"X ", " f"}}, toolHeadRawSense          , null, null, null, null, ANTIMATTER.NOT));
		toolHeadPlow            .addListener(new OreProcessing_CraftFrom( 1, null, new String[][] {{"X ", " f"}}, toolHeadRawPlow           , null, null, null, null, ANTIMATTER.NOT));
		
		RM.Autoclave    .add(new RecipeMapHandlerPrefix(dustSmall                       , 1, FL.Steam.make(   25600), 0,    800, 0, FL.DistW.make( 120), gemChipped             , 1, ST.tag(0), NI, T, F, F, new And(ANTIMATTER.NOT, CRYSTALLISABLE)));
		RM.Autoclave    .add(new RecipeMapHandlerPrefix(dustSmall                       , 2, FL.Steam.make(   51200), 0,   1600, 0, FL.DistW.make( 240), gemFlawed              , 1, ST.tag(1), NI, T, F, F, new And(ANTIMATTER.NOT, CRYSTALLISABLE)));
		RM.Autoclave    .add(new RecipeMapHandlerPrefix(dustSmall                       , 4, FL.Steam.make(  102400), 0,   3200, 0, FL.DistW.make( 480), gem                    , 1, ST.tag(2), NI, T, F, F, new And(ANTIMATTER.NOT, CRYSTALLISABLE)));
		RM.Autoclave    .add(new RecipeMapHandlerPrefix(dustSmall                       , 8, FL.Steam.make(  204800), 0,   6400, 0, FL.DistW.make( 960), gemFlawless            , 1, ST.tag(3), NI, T, F, F, new And(ANTIMATTER.NOT, CRYSTALLISABLE)));
		RM.Autoclave    .add(new RecipeMapHandlerPrefix(dustSmall                       ,16, FL.Steam.make(  409600), 0,  12800, 0, FL.DistW.make(1920), gemExquisite           , 1, ST.tag(4), NI, T, F, F, new And(ANTIMATTER.NOT, CRYSTALLISABLE)));
		RM.Autoclave    .add(new RecipeMapHandlerPrefix(dustSmall                       ,32, FL.Steam.make(  819200), 0,  25600, 0, FL.DistW.make(3840), gemLegendary           , 1, ST.tag(5), NI, T, F, F, new And(ANTIMATTER.NOT, CRYSTALLISABLE)));
		
		RM.Autoclave    .add(new RecipeMapHandlerPrefix(dust                            , 1, FL.Steam.make(  102400), 0,   3200, 0, FL.DistW.make( 480), gemChipped             , 4, ST.tag(0), NI, T, F, F, new And(ANTIMATTER.NOT, CRYSTALLISABLE)));
		RM.Autoclave    .add(new RecipeMapHandlerPrefix(dust                            , 1, FL.Steam.make(  102400), 0,   3200, 0, FL.DistW.make( 480), gemFlawed              , 2, ST.tag(1), NI, T, F, F, new And(ANTIMATTER.NOT, CRYSTALLISABLE)));
		RM.Autoclave    .add(new RecipeMapHandlerPrefix(dust                            , 1, FL.Steam.make(  102400), 0,   3200, 0, FL.DistW.make( 480), gem                    , 1, ST.tag(2), NI, T, F, F, new And(ANTIMATTER.NOT, CRYSTALLISABLE)));
		RM.Autoclave    .add(new RecipeMapHandlerPrefix(dust                            , 2, FL.Steam.make(  204800), 0,   6400, 0, FL.DistW.make( 960), gemFlawless            , 1, ST.tag(3), NI, T, F, F, new And(ANTIMATTER.NOT, CRYSTALLISABLE)));
		RM.Autoclave    .add(new RecipeMapHandlerPrefix(dust                            , 4, FL.Steam.make(  409600), 0,  12800, 0, FL.DistW.make(1920), gemExquisite           , 1, ST.tag(4), NI, T, F, F, new And(ANTIMATTER.NOT, CRYSTALLISABLE)));
		RM.Autoclave    .add(new RecipeMapHandlerPrefix(dust                            , 8, FL.Steam.make(  819200), 0,  25600, 0, FL.DistW.make(3840), gemLegendary           , 1, ST.tag(5), NI, T, F, F, new And(ANTIMATTER.NOT, CRYSTALLISABLE)));
		
		RM.Unboxinator  .add(new RecipeMapHandlerPrefix(arrowGtWood                     , 1, NF,  16,16,     0, NF, toolHeadArrow           , 1, NI, arrowGtWood    .mat(MT.Empty, 1), F, F, F, ANTIMATTER.NOT));
		RM.Unboxinator  .add(new RecipeMapHandlerPrefix(arrowGtPlastic                  , 1, NF,  16,16,     0, NF, toolHeadArrow           , 1, NI, arrowGtPlastic .mat(MT.Empty, 1), F, F, F, ANTIMATTER.NOT));
		RM.Unboxinator  .add(new RecipeMapHandlerPrefix(bulletGtSmall                   , 1, NF,  16,16,     0, NF, round                   , 1, NI, bulletGtSmall  .mat(MT.Empty, 1), F, F, F, ANTIMATTER.NOT));
		RM.Unboxinator  .add(new RecipeMapHandlerPrefix(bulletGtMedium                  , 1, NF,  16,16,     0, NF, round                   , 2, NI, bulletGtMedium .mat(MT.Empty, 1), F, F, F, ANTIMATTER.NOT));
		RM.Unboxinator  .add(new RecipeMapHandlerPrefix(bulletGtLarge                   , 1, NF,  16,16,     0, NF, round                   , 3, NI, bulletGtLarge  .mat(MT.Empty, 1), F, F, F, ANTIMATTER.NOT));
		
		RM.Unboxinator  .add(new RecipeMapHandlerPrefix(pipeQuadruple                   , 1, NF,  16,16,     0, NF, pipeMedium              , 4, NI, NI, F, F, F, ANTIMATTER.NOT));
		RM.Unboxinator  .add(new RecipeMapHandlerPrefix(pipeNonuple                     , 1, NF,  16,16,     0, NF, pipeSmall               , 9, NI, NI, F, F, F, ANTIMATTER.NOT));
		
		RM.Unboxinator  .add(new RecipeMapHandlerPrefix(crateGtDust                     , 1, NF,  16,16,     0, NF, dust                    ,16, NI, IL.Crate.get(1), T, F, F, ANTIMATTER.NOT));
		RM.Unboxinator  .add(new RecipeMapHandlerPrefix(crateGtGem                      , 1, NF,  16,16,     0, NF, gem                     ,16, NI, IL.Crate.get(1), T, F, F, ANTIMATTER.NOT));
		RM.Unboxinator  .add(new RecipeMapHandlerPrefix(crateGtIngot                    , 1, NF,  16,16,     0, NF, ingot                   ,16, NI, IL.Crate.get(1), T, F, F, ANTIMATTER.NOT));
		RM.Unboxinator  .add(new RecipeMapHandlerPrefix(crateGtPlate                    , 1, NF,  16,16,     0, NF, plate                   ,16, NI, IL.Crate.get(1), T, F, F, ANTIMATTER.NOT));
		RM.Unboxinator  .add(new RecipeMapHandlerPrefix(crateGtPlateGem                 , 1, NF,  16,16,     0, NF, plateGem                ,16, NI, IL.Crate.get(1), T, F, F, ANTIMATTER.NOT));
		
		RM.Unboxinator  .add(new RecipeMapHandlerPrefix(crateGt64Dust                   , 1, NF,  16,16,     0, NF, dust                    ,64, NI, IL.Crate.get(1), T, F, F, ANTIMATTER.NOT));
		RM.Unboxinator  .add(new RecipeMapHandlerPrefix(crateGt64Gem                    , 1, NF,  16,16,     0, NF, gem                     ,64, NI, IL.Crate.get(1), T, F, F, ANTIMATTER.NOT));
		RM.Unboxinator  .add(new RecipeMapHandlerPrefix(crateGt64Ingot                  , 1, NF,  16,16,     0, NF, ingot                   ,64, NI, IL.Crate.get(1), T, F, F, ANTIMATTER.NOT));
		RM.Unboxinator  .add(new RecipeMapHandlerPrefix(crateGt64Plate                  , 1, NF,  16,16,     0, NF, plate                   ,64, NI, IL.Crate.get(1), T, F, F, ANTIMATTER.NOT));
		RM.Unboxinator  .add(new RecipeMapHandlerPrefix(crateGt64PlateGem               , 1, NF,  16,16,     0, NF, plateGem                ,64, NI, IL.Crate.get(1), T, F, F, ANTIMATTER.NOT));
		
		RM.Unboxinator  .add(new RecipeMapHandlerPrefix(blockDust                       , 1, NF,  16,16,     0, NF, dust                    , 9, NI, NI, T, F, F, ANTIMATTER.NOT));
		RM.Unboxinator  .add(new RecipeMapHandlerPrefix(blockGem                        , 1, NF,  16,16,     0, NF, gem                     , 9, NI, NI, T, F, F, ANTIMATTER.NOT));
		RM.Unboxinator  .add(new RecipeMapHandlerPrefix(blockIngot                      , 1, NF,  16,16,     0, NF, ingot                   , 9, NI, NI, T, F, F, ANTIMATTER.NOT));
		RM.Unboxinator  .add(new RecipeMapHandlerPrefix(blockPlate                      , 1, NF,  16,16,     0, NF, plate                   , 9, NI, NI, T, F, F, ANTIMATTER.NOT));
		RM.Unboxinator  .add(new RecipeMapHandlerPrefix(blockPlateGem                   , 1, NF,  16,16,     0, NF, plateGem                , 9, NI, NI, T, F, F, ANTIMATTER.NOT));
		RM.Unboxinator  .add(new RecipeMapHandlerPrefix(crushed                         , 1, NF,  16,16,     0, NF, crushedTiny             , 9, NI, NI, F, F, F, ANTIMATTER.NOT));
		RM.Unboxinator  .add(new RecipeMapHandlerPrefix(crushedPurified                 , 1, NF,  16,16,     0, NF, crushedPurifiedTiny     , 9, NI, NI, F, F, F, ANTIMATTER.NOT));
		RM.Unboxinator  .add(new RecipeMapHandlerPrefix(crushedCentrifuged              , 1, NF,  16,16,     0, NF, crushedCentrifugedTiny  , 9, NI, NI, F, F, F, ANTIMATTER.NOT));
		
		RM.Boxinator    .add(new RecipeMapHandlerPrefix(pipeMedium                      , 4, NF,  16,16,     0, NF, pipeQuadruple           , 1, ST.tag( 4), NI, F, F, F, ANTIMATTER.NOT));
		RM.Boxinator    .add(new RecipeMapHandlerPrefix(pipeSmall                       , 9, NF,  16,16,     0, NF, pipeNonuple             , 1, ST.tag( 9), NI, F, F, F, ANTIMATTER.NOT));
		
		RM.Boxinator    .add(new RecipeMapHandlerPrefix(dustSmall                       ,64, NF,  16,16,     0, NF, crateGtDust             , 1, IL.Crate.get(1), NI, F, F, F, ANTIMATTER.NOT));
		RM.Boxinator    .add(new RecipeMapHandlerPrefix(chunkGt                         ,64, NF,  16,16,     0, NF, crateGtIngot            , 1, IL.Crate.get(1), NI, F, F, F, ANTIMATTER.NOT));
		RM.Boxinator    .add(new RecipeMapHandlerPrefix(dust                            ,64, NF,  16,16,     0, NF, crateGt64Dust           , 1, IL.Crate.get(1), NI, T, F, F, ANTIMATTER.NOT));
		RM.Boxinator    .add(new RecipeMapHandlerPrefix(gem                             ,64, NF,  16,16,     0, NF, crateGt64Gem            , 1, IL.Crate.get(1), NI, T, F, F, ANTIMATTER.NOT));
		RM.Boxinator    .add(new RecipeMapHandlerPrefix(ingot                           ,64, NF,  16,16,     0, NF, crateGt64Ingot          , 1, IL.Crate.get(1), NI, T, F, F, ANTIMATTER.NOT));
		RM.Boxinator    .add(new RecipeMapHandlerPrefix(plate                           ,64, NF,  16,16,     0, NF, crateGt64Plate          , 1, IL.Crate.get(1), NI, T, F, F, ANTIMATTER.NOT));
		RM.Boxinator    .add(new RecipeMapHandlerPrefix(plateGem                        ,64, NF,  16,16,     0, NF, crateGt64PlateGem       , 1, IL.Crate.get(1), NI, T, F, F, ANTIMATTER.NOT));
		
		RM.Boxinator    .add(new RecipeMapHandlerPrefix(dustSmall                       ,64, NF,  16,16,     0, NF, crateGtDust             , 1, IL.Crate_Fireproof.get(1), NI, F, F, F, ANTIMATTER.NOT));
		RM.Boxinator    .add(new RecipeMapHandlerPrefix(chunkGt                         ,64, NF,  16,16,     0, NF, crateGtIngot            , 1, IL.Crate_Fireproof.get(1), NI, F, F, F, ANTIMATTER.NOT));
		RM.Boxinator    .add(new RecipeMapHandlerPrefix(dust                            ,64, NF,  16,16,     0, NF, crateGt64Dust           , 1, IL.Crate_Fireproof.get(1), NI, T, F, F, ANTIMATTER.NOT));
		RM.Boxinator    .add(new RecipeMapHandlerPrefix(gem                             ,64, NF,  16,16,     0, NF, crateGt64Gem            , 1, IL.Crate_Fireproof.get(1), NI, T, F, F, ANTIMATTER.NOT));
		RM.Boxinator    .add(new RecipeMapHandlerPrefix(ingot                           ,64, NF,  16,16,     0, NF, crateGt64Ingot          , 1, IL.Crate_Fireproof.get(1), NI, T, F, F, ANTIMATTER.NOT));
		RM.Boxinator    .add(new RecipeMapHandlerPrefix(plate                           ,64, NF,  16,16,     0, NF, crateGt64Plate          , 1, IL.Crate_Fireproof.get(1), NI, T, F, F, ANTIMATTER.NOT));
		RM.Boxinator    .add(new RecipeMapHandlerPrefix(plateGem                        ,64, NF,  16,16,     0, NF, crateGt64PlateGem       , 1, IL.Crate_Fireproof.get(1), NI, T, F, F, ANTIMATTER.NOT));
		
		RM.Boxinator    .add(new RecipeMapHandlerPrefix(dustSmall                       ,36, NF,  16,16,     0, NF, blockDust               , 1, ST.tag( 9), NI, F, F, F, ANTIMATTER.NOT));
		RM.Boxinator    .add(new RecipeMapHandlerPrefix(dust                            , 9, NF,  16,16,     0, NF, blockDust               , 1, ST.tag( 9), NI, T, F, F, ANTIMATTER.NOT));
		RM.Boxinator    .add(new RecipeMapHandlerPrefix(gem                             , 9, NF,  16,16,     0, NF, blockGem                , 1, ST.tag( 9), NI, T, F, F, ANTIMATTER.NOT));
		RM.Boxinator    .add(new RecipeMapHandlerPrefix(chunkGt                         ,36, NF,  16,16,     0, NF, blockIngot              , 1, ST.tag( 9), NI, F, F, F, ANTIMATTER.NOT));
		RM.Boxinator    .add(new RecipeMapHandlerPrefix(ingot                           , 9, NF,  16,16,     0, NF, blockIngot              , 1, ST.tag( 9), NI, T, F, F, ANTIMATTER.NOT));
		RM.Boxinator    .add(new RecipeMapHandlerPrefix(plate                           , 9, NF,  16,16,     0, NF, blockPlate              , 1, ST.tag( 9), NI, T, F, F, ANTIMATTER.NOT));
		RM.Boxinator    .add(new RecipeMapHandlerPrefix(plateGem                        , 9, NF,  16,16,     0, NF, blockPlateGem           , 1, ST.tag( 9), NI, T, F, F, ANTIMATTER.NOT));
		RM.Boxinator    .add(new RecipeMapHandlerPrefix(crushedTiny                     , 9, NF,  16,16,     0, NF, crushed                 , 1, ST.tag( 9), NI, F, F, F, ANTIMATTER.NOT));
		RM.Boxinator    .add(new RecipeMapHandlerPrefix(crushedPurifiedTiny             , 9, NF,  16,16,     0, NF, crushedPurified         , 1, ST.tag( 9), NI, F, F, F, ANTIMATTER.NOT));
		RM.Boxinator    .add(new RecipeMapHandlerPrefix(crushedCentrifugedTiny          , 9, NF,  16,16,     0, NF, crushedCentrifuged      , 1, ST.tag( 9), NI, F, F, F, ANTIMATTER.NOT));
		
		
		GameRegistry.addRecipe(new AdvancedCrafting1ToY(crushed                     , crushedTiny               , 9, F));
		GameRegistry.addRecipe(new AdvancedCrafting1ToY(crushedPurified             , crushedPurifiedTiny       , 9, F));
		GameRegistry.addRecipe(new AdvancedCrafting1ToY(crushedCentrifuged          , crushedCentrifugedTiny    , 9, F));
		GameRegistry.addRecipe(new AdvancedCrafting1ToY(ingot                       , nugget                    , 9, F));
		GameRegistry.addRecipe(new AdvancedCrafting1ToY(ingot                       , chunkGt                   , 4, F));
		GameRegistry.addRecipe(new AdvancedCrafting1ToY(dust                        , dustTiny                  , 9, F));
		GameRegistry.addRecipe(new AdvancedCrafting1ToY(dust                        , dustSmall                 , 4, F));
		GameRegistry.addRecipe(new AdvancedCrafting1ToY(dustTiny                    , dustDiv72                 , 8, F));
		GameRegistry.addRecipe(new AdvancedCrafting1ToY(dustSmall                   , dustDiv72                 ,18, F));
		GameRegistry.addRecipe(new AdvancedCrafting1ToY(blockDust                   , dust                      , 9, F));
		GameRegistry.addRecipe(new AdvancedCrafting1ToY(blockDust                   , dustSmall                 ,36, F));
		GameRegistry.addRecipe(new AdvancedCrafting1ToY(blockIngot                  , ingot                     , 9, F));
		GameRegistry.addRecipe(new AdvancedCrafting1ToY(blockIngot                  , chunkGt                   ,36, F));
		GameRegistry.addRecipe(new AdvancedCrafting1ToY(blockGem                    , gem                       , 9, F));
		GameRegistry.addRecipe(new AdvancedCrafting1ToY(blockPlate                  , plate                     , 9, F));
		GameRegistry.addRecipe(new AdvancedCrafting1ToY(blockPlateGem               , plateGem                  , 9, F));
		GameRegistry.addRecipe(new AdvancedCrafting1ToY(wireGt02                    , wireGt01                  , 2, F));
		GameRegistry.addRecipe(new AdvancedCrafting1ToY(wireGt04                    , wireGt01                  , 4, F));
		GameRegistry.addRecipe(new AdvancedCrafting1ToY(wireGt04                    , wireGt02                  , 2, F));
		GameRegistry.addRecipe(new AdvancedCrafting1ToY(wireGt08                    , wireGt01                  , 8, F));
		GameRegistry.addRecipe(new AdvancedCrafting1ToY(wireGt08                    , wireGt02                  , 4, F));
		GameRegistry.addRecipe(new AdvancedCrafting1ToY(wireGt08                    , wireGt04                  , 2, F));
		GameRegistry.addRecipe(new AdvancedCrafting1ToY(wireGt12                    , wireGt01                  ,12, F));
		GameRegistry.addRecipe(new AdvancedCrafting1ToY(wireGt12                    , wireGt02                  , 6, F));
		GameRegistry.addRecipe(new AdvancedCrafting1ToY(wireGt12                    , wireGt04                  , 3, F));
		GameRegistry.addRecipe(new AdvancedCrafting1ToY(wireGt16                    , wireGt01                  ,16, F));
		GameRegistry.addRecipe(new AdvancedCrafting1ToY(wireGt16                    , wireGt02                  , 8, F));
		GameRegistry.addRecipe(new AdvancedCrafting1ToY(wireGt16                    , wireGt04                  , 4, F));
		GameRegistry.addRecipe(new AdvancedCrafting1ToY(wireGt16                    , wireGt08                  , 2, F));
		
		GameRegistry.addRecipe(new AdvancedCraftingXToY(crushedTiny             ,  9, crushed                   , 1, F));
		GameRegistry.addRecipe(new AdvancedCraftingXToY(crushedPurifiedTiny     ,  9, crushedPurified           , 1, F));
		GameRegistry.addRecipe(new AdvancedCraftingXToY(crushedCentrifugedTiny  ,  9, crushedCentrifuged        , 1, F));
		GameRegistry.addRecipe(new AdvancedCraftingXToY(nugget                  ,  9, ingot                     , 1, F));
		GameRegistry.addRecipe(new AdvancedCraftingXToY(chunkGt                 ,  4, ingot                     , 1, F));
		GameRegistry.addRecipe(new AdvancedCraftingXToY(chunkGt                 ,  8, ingot                     , 2, F));
		GameRegistry.addRecipe(new AdvancedCraftingXToY(dustDiv72               ,  8, dustTiny                  , 1, F));
		GameRegistry.addRecipe(new AdvancedCraftingXToY(dustTiny                ,  9, dust                      , 1, F));
		GameRegistry.addRecipe(new AdvancedCraftingXToY(dustSmall               ,  4, dust                      , 1, F));
		GameRegistry.addRecipe(new AdvancedCraftingXToY(dustSmall               ,  8, dust                      , 2, F));
		GameRegistry.addRecipe(new AdvancedCraftingXToY(dust                    ,  9, blockDust                 , 1, F));
		GameRegistry.addRecipe(new AdvancedCraftingXToY(ingot                   ,  9, blockIngot                , 1, F));
		GameRegistry.addRecipe(new AdvancedCraftingXToY(gem                     ,  9, blockGem                  , 1, F));
		GameRegistry.addRecipe(new AdvancedCraftingXToY(plate                   ,  9, blockPlate                , 1, F));
		GameRegistry.addRecipe(new AdvancedCraftingXToY(plateGem                ,  9, blockPlateGem             , 1, F));
		GameRegistry.addRecipe(new AdvancedCraftingXToY(wireGt01                ,  2, wireGt02                  , 1, F));
		GameRegistry.addRecipe(new AdvancedCraftingXToY(wireGt01                ,  4, wireGt04                  , 1, F));
		GameRegistry.addRecipe(new AdvancedCraftingXToY(wireGt01                ,  8, wireGt08                  , 1, F));
		GameRegistry.addRecipe(new AdvancedCraftingXToY(wireGt02                ,  2, wireGt04                  , 1, F));
		GameRegistry.addRecipe(new AdvancedCraftingXToY(wireGt02                ,  4, wireGt08                  , 1, F));
		GameRegistry.addRecipe(new AdvancedCraftingXToY(wireGt02                ,  6, wireGt12                  , 1, F));
		GameRegistry.addRecipe(new AdvancedCraftingXToY(wireGt02                ,  8, wireGt16                  , 1, F));
		GameRegistry.addRecipe(new AdvancedCraftingXToY(wireGt04                ,  2, wireGt08                  , 1, F));
		GameRegistry.addRecipe(new AdvancedCraftingXToY(wireGt04                ,  3, wireGt12                  , 1, F));
		GameRegistry.addRecipe(new AdvancedCraftingXToY(wireGt04                ,  4, wireGt16                  , 1, F));
		GameRegistry.addRecipe(new AdvancedCraftingXToY(wireGt08                ,  2, wireGt16                  , 1, F));
		
		
		FluidStack[] tFluids = FL.array(FL.Water.make(1000), FL.DistW.make(1000), FL.Lubricant.make(1000), FL.LubRoCant.make(1000));
		long[] tMultiplier = new long[] {4, 3, 1, 1};
		ICondition tConditionM = new And(ANTIMATTER.NOT, COATED.NOT);
		
		for (int i = 0; i < 4; i++) if (tFluids[i] != null) {
		RM.Cutter       .add(new RecipeMapHandlerPrefix(blockSolid          , 1, FL.mul(tFluids[i], tMultiplier[i] * 7 * 16, 1000, T), 32, tMultiplier[i] * 7 * 16, 0, NF, plate         , 8, NI, NI, T, T, F, tConditionM));
		RM.Cutter       .add(new RecipeMapHandlerPrefix(stickLong           , 1, FL.mul(tFluids[i], tMultiplier[i]     * 16, 1000, T), 32, tMultiplier[i]     * 16, 0, NF, stick         , 2, NI, NI, F, T, F, tConditionM));
		RM.Cutter       .add(new RecipeMapHandlerPrefix(stick               , 1, FL.mul(tFluids[i], tMultiplier[i] * 3 * 16, 1000, T), 32, tMultiplier[i] * 3 * 16, 0, NF, bolt          , 4, NI, NI, F, T, F, tConditionM));
		RM.Cutter       .add(new RecipeMapHandlerPrefix(plate               , 1, FL.mul(tFluids[i], tMultiplier[i] * 4 * 16, 1000, T), 32, tMultiplier[i] * 4 * 16, 0, NF, plateTiny     , 8, NI, NI, F, T, F, tConditionM));
		RM.Cutter       .add(new RecipeMapHandlerPrefix(plateGem            , 1, FL.mul(tFluids[i], tMultiplier[i] * 4 * 16, 1000, T), 32, tMultiplier[i] * 4 * 16, 0, NF, plateGemTiny  , 8, NI, NI, F, T, F, tConditionM));
		RM.Cutter       .add(new RecipeMapHandlerPrefix(gemChipped          , 1, FL.mul(tFluids[i], tMultiplier[i]     * 16, 1000, T), 32, tMultiplier[i]     * 16, 0, NF, plateGemTiny  , 2, NI, NI, F, T, F, tConditionM));
		RM.Cutter       .add(new RecipeMapHandlerPrefix(gemFlawed           , 1, FL.mul(tFluids[i], tMultiplier[i] * 2 * 16, 1000, T), 32, tMultiplier[i] * 2 * 16, 0, NF, plateGemTiny  , 4, NI, NI, F, T, F, tConditionM));
		RM.Cutter       .add(new RecipeMapHandlerPrefix(gem                 , 1, FL.mul(tFluids[i], tMultiplier[i]     * 16, 1000, T), 96, tMultiplier[i]     * 16, 0, NF, plateGem      , 1, NI, NI, T, T, F, tConditionM));
		RM.Cutter       .add(new RecipeMapHandlerPrefix(gemFlawless         , 1, FL.mul(tFluids[i], tMultiplier[i]     * 16, 1000, T), 96, tMultiplier[i]     * 16, 0, NF, plateGem      , 2, NI, NI, F, T, F, tConditionM));
		RM.Cutter       .add(new RecipeMapHandlerPrefix(gemExquisite        , 1, FL.mul(tFluids[i], tMultiplier[i] * 3 * 16, 1000, T), 96, tMultiplier[i] * 3 * 16, 0, NF, plateGem      , 4, NI, NI, F, T, F, tConditionM));
		RM.Cutter       .add(new RecipeMapHandlerPrefix(gemLegendary        , 1, FL.mul(tFluids[i], tMultiplier[i] * 7 * 16, 1000, T), 96, tMultiplier[i] * 7 * 16, 0, NF, plateGem      , 8, NI, NI, F, T, F, tConditionM));
		RM.Cutter       .add(new RecipeMapHandlerPrefix(bouleGt             , 1, FL.mul(tFluids[i], tMultiplier[i] * 3 * 16, 1000, T), 32, tMultiplier[i] * 3 * 16, 0, NF, plateGem      , 4, NI, NI, T, T, F, tConditionM));
		RM.Cutter       .add(new RecipeMapHandlerPrefix(ingotDouble         , 1, FL.mul(tFluids[i], tMultiplier[i]     * 16, 1000, T), 32, tMultiplier[i]     * 16, 0, NF, ingot         , 2, NI, NI, T, T, F, tConditionM));
		RM.Cutter       .add(new RecipeMapHandlerPrefix(ingotTriple         , 1, FL.mul(tFluids[i], tMultiplier[i] * 2 * 16, 1000, T), 32, tMultiplier[i] * 2 * 16, 0, NF, ingot         , 3, NI, NI, F, T, F, tConditionM));
		RM.Cutter       .add(new RecipeMapHandlerPrefix(ingotQuadruple      , 1, FL.mul(tFluids[i], tMultiplier[i] * 3 * 16, 1000, T), 32, tMultiplier[i] * 3 * 16, 0, NF, ingot         , 4, NI, NI, F, T, F, tConditionM));
		RM.Cutter       .add(new RecipeMapHandlerPrefix(ingotQuintuple      , 1, FL.mul(tFluids[i], tMultiplier[i] * 4 * 16, 1000, T), 32, tMultiplier[i] * 4 * 16, 0, NF, ingot         , 5, NI, NI, F, T, F, tConditionM));
		}
		
		ICondition tConditionP = new Nor(PREFIX_UNUSED, PLANT_DROP, IS_CONTAINER, DUST_BASED, ORE, ORE_PROCESSING_BASED, scrapGt, ingotHot);
		
		
		RM.Polarizer    .add(new RecipeMapHandlerMaterial(MT.Nd                     , NF                                , 128, 144, NF, MT.NeodymiumMagnetic    , NI, T, tConditionP));
		
		for (OreDictMaterial tMaterial : ANY.WoodUntreated.mToThis) {
		RM.Bath         .add(new RecipeMapHandlerMaterial(tMaterial, FL.Oil_Seed        .make( 100),   0, 144, NF, MT.WoodSealed    , NI, T, tConditionP));
		RM.Bath         .add(new RecipeMapHandlerMaterial(tMaterial, FL.Oil_Lin         .make( 100),   0, 144, NF, MT.WoodSealed    , NI, T, tConditionP));
		RM.Bath         .add(new RecipeMapHandlerMaterial(tMaterial, FL.Oil_Hemp        .make( 100),   0, 144, NF, MT.WoodSealed    , NI, T, tConditionP));
		RM.Bath         .add(new RecipeMapHandlerMaterial(tMaterial, FL.Oil_Nut         .make( 100),   0, 144, NF, MT.WoodSealed    , NI, T, tConditionP));
		RM.Bath         .add(new RecipeMapHandlerMaterial(tMaterial, FL.Oil_Olive       .make( 100),   0, 144, NF, MT.WoodSealed    , NI, T, tConditionP));
		RM.Bath         .add(new RecipeMapHandlerMaterial(tMaterial, FL.Oil_Sunflower   .make( 100),   0, 144, NF, MT.WoodSealed    , NI, T, tConditionP));
		RM.Bath         .add(new RecipeMapHandlerMaterial(tMaterial, FL.Oil_Creosote    .make( 100),   0, 144, NF, MT.WoodSealed    , NI, T, tConditionP));
		RM.Bath         .add(new RecipeMapHandlerMaterial(tMaterial, FL.Oil_Fish        .make(1000),   0, 144, NF, MT.WoodPolished  , NI, T, tConditionP));
		}
		
		if (FL.Mana_TE.exists()) {
		OreDictManager.INSTANCE.triggerVisibility("ingotThaumium");
		OreDictManager.INSTANCE.triggerVisibility("ingotArdite");
		for (OreDictMaterial tMat : ANY.Iron.mToThis)
		RM.Bath         .add(new RecipeMapHandlerMaterial(tMat                      , FL.Mana_TE        .make( 250)     ,   0, 144, NF, MT.Thaumium             , NI, T, tConditionP));
		RM.Bath         .add(new RecipeMapHandlerMaterial(MT.Ag                     , FL.Mana_TE        .make( 125)     ,   0, 144, NF, MT.AstralSilver         , NI, T, tConditionP));
		RM.Bath         .add(new RecipeMapHandlerMaterial(MT.Au                     , FL.Mana_TE        .make( 125)     ,   0, 144, NF, MT.Midasium             , NI, T, tConditionP));
		RM.Bath         .add(new RecipeMapHandlerMaterial(MT.Pt                     , FL.Mana_TE        .make( 125)     ,   0, 144, NF, MT.Mithril              , NI, T, tConditionP));
		RM.Bath         .add(new RecipeMapHandlerMaterial(MT.Pb                     , FL.Mana_TE        .make(  50)     ,   0, 144, NF, MT.Au                   , NI, T, tConditionP));
		RM.Bath         .add(new RecipeMapHandlerMaterial(MT.Ni                     , FL.Mana_TE        .make(  50)     ,   0, 144, NF, MT.Ardite               , NI, T, tConditionP));
		RM.Bath         .add(new RecipeMapHandlerMaterial(MT.MilkyQuartz            , FL.Mana_TE        .make(  50)     ,   0, 144, NF, MT.NetherQuartz         , NI, T, tConditionP));
		RM.Bath         .add(new RecipeMapHandlerMaterial(MT.NetherQuartz           , FL.Mana_TE        .make(  50)     ,   0, 144, NF, MT.CertusQuartz         , NI, T, tConditionP));
		}
		
		for (OreDictMaterial tMat : ANY.Fe.mToThis) {
		RM.Freezer      .add(new RecipeMapHandlerMaterial(tMat                      , NF                                , 128, 144, NF, MT.FrozenIron           , NI, T, tConditionP));
		RM.Polarizer    .add(new RecipeMapHandlerMaterial(tMat                      , NF                                ,  16, 144, NF, MT.IronMagnetic         , NI, T, tConditionP));
		RM.Bath         .add(new RecipeMapHandlerMaterial(tMat                      , MT.Au             .liquid(U9, T)  ,   0, 144, NF, MT.GildedIron           , NI, T, tConditionP));
		RM.Bath         .add(new RecipeMapHandlerMaterial(tMat                      , FL.FieryBlood     .make(   L)     ,   0, 144, NF, MT.FierySteel           , NI, T, tConditionP));
		RM.Bath         .add(new RecipeMapHandlerMaterial(tMat                      , FL.FieryTears     .make(   L)     ,   0, 144, NF, MT.FierySteel           , NI, T, tConditionP));
		}
		for (OreDictMaterial tMat : ANY.Steel.mToThis) {
		RM.Polarizer    .add(new RecipeMapHandlerMaterial(tMat                      , NF                                ,  16, 144, NF, MT.SteelMagnetic        , NI, T, tConditionP));
		RM.Bath         .add(new RecipeMapHandlerMaterial(tMat                      , MT.Zn             .liquid(U9, T)  ,   0, 144, NF, MT.SteelGalvanized      , NI, T, tConditionP));
		RM.Bath         .add(new RecipeMapHandlerMaterial(tMat                      , FL.FieryBlood     .make(   L)     ,   0, 144, NF, MT.FierySteel           , NI, T, tConditionP));
		RM.Bath         .add(new RecipeMapHandlerMaterial(tMat                      , FL.FieryTears     .make(   L)     ,   0, 144, NF, MT.FierySteel           , NI, T, tConditionP));
		}
		
		tConditionP = new And(SIMPLIFIABLE, ingotHot.NOT);
		
		RM.Generifier   .add(new RecipeMapHandlerMaterial(MT.NetherQuartz           , NF                                ,   0,   1, NF, MT.MilkyQuartz          , NI, T, tConditionP));
		RM.Generifier   .add(new RecipeMapHandlerMaterial(MT.ChargedCertusQuartz    , NF                                ,   0,   1, NF, MT.CertusQuartz         , NI, T, tConditionP));
		RM.Generifier   .add(new RecipeMapHandlerMaterial(MT.Teslatite              , NF                                ,   0,   1, NF, MT.Nikolite             , NI, T, tConditionP));
		RM.Generifier   .add(new RecipeMapHandlerMaterial(MT.Nikolite               , NF                                ,   0,   1, NF, MT.Teslatite            , NI, T, tConditionP));
		RM.Generifier   .add(new RecipeMapHandlerMaterial(MT.Redstonia              , NF                                ,   0,   1, NF, MT.Redstone             , NI, T, tConditionP));
		RM.Generifier   .add(new RecipeMapHandlerMaterial(MT.Palis                  , NF                                ,   0,   1, NF, MT.Lapis                , NI, T, tConditionP));
		RM.Generifier   .add(new RecipeMapHandlerMaterial(MT.VoidCrystal            , NF                                ,   0,   1, NF, MT.Coal                 , NI, T, tConditionP));
		RM.Generifier   .add(new RecipeMapHandlerMaterial(MT.AnnealedCopper         , NF                                ,   0,   1, NF, MT.Cu                   , NI, T, tConditionP));
		RM.Generifier   .add(new RecipeMapHandlerMaterial(MT.NeodymiumMagnetic      , NF                                ,   0,   1, NF, MT.Nd                   , NI, T, tConditionP));
		RM.Generifier   .add(new RecipeMapHandlerMaterial(MT.AstralSilver           , NF                                ,   0,   1, NF, MT.Ag                   , NI, T, tConditionP));
		RM.Generifier   .add(new RecipeMapHandlerMaterial(MT.Midasium               , NF                                ,   0,   1, NF, MT.Au                   , NI, T, tConditionP));
		RM.Generifier   .add(new RecipeMapHandlerMaterial(MT.Mithril                , NF                                ,   0,   1, NF, MT.Pt                   , NI, T, tConditionP));
		RM.Generifier   .add(new RecipeMapHandlerMaterial(MT.TungstenSintered       , NF                                ,   0,   1, NF, MT.W                    , NI, T, tConditionP));
		RM.Generifier   .add(new RecipeMapHandlerMaterial(MT.Os                     , NF                                ,   0,   1, NF, MT.FakeOsmium           , NI, T, tConditionP));
		
		for (OreDictMaterial tMaterial : new OreDictMaterial[] {MT.WroughtIron, MT.MeteoricIron, MT.IronMagnetic, MT.Meteorite, MT.PigIron, MT.IronCompressed, MT.Thaumium, MT.Enori})
		RM.Generifier   .add(new RecipeMapHandlerMaterial(tMaterial                 , NF                                ,   0,   1, NF, MT.Fe                   , NI, T, tConditionP));
		for (OreDictMaterial tMaterial : new OreDictMaterial[] {MT.HSLA, MT.MeteoricSteel, MT.SteelMagnetic, MT.Knightmetal})
		RM.Generifier   .add(new RecipeMapHandlerMaterial(tMaterial                 , NF                                ,   0,   1, NF, MT.Steel                , NI, T, tConditionP));
		for (OreDictMaterial tMaterial : new OreDictMaterial[] {MT.Betweenstone, MT.Pitstone, MT.Umber, MT.Gravel, MT.Diorite, MT.Redrock, MT.GraniteBlack, MT.GraniteRed, MT.Granite, MT.Limestone, MT.Marble, MT.Basalt, MT.Gabbro, MT.Concrete, MT.Eclogite, MT.Shale, MT.Andesite, MT.Dacite, MT.Chert, MT.Blueschist, MT.Epidote, MT.Migmatite, MT.Quartzite, MT.Gneiss, MT.Greenschist, MT.Greywacke, MT.Komatiite, MT.Kimberlite, MT.Siltstone, MT.Rhyolite, MT.MoonRock, MT.MoonTurf, MT.MarsRock, MT.MarsSand, MT.SpaceRock, MT.Livingrock, MT.Holystone})
		RM.Generifier   .add(new RecipeMapHandlerMaterial(tMaterial                 , NF                                ,   0,   1, NF, MT.Stone                , NI, T, tConditionP));
		for (OreDictMaterial tMaterial : ANY.Ash.mToThis) if (tMaterial != MT.Ash)
		RM.Generifier   .add(new RecipeMapHandlerMaterial(tMaterial                 , NF                                ,   0,   1, NF, MT.Ash                  , NI, T, tConditionP));
		for (OreDictMaterial tMaterial : ANY.Diamond.mToThis) if (tMaterial != MT.Diamond)
		RM.Generifier   .add(new RecipeMapHandlerMaterial(tMaterial                 , NF                                ,   0,   1, NF, MT.Diamond              , NI, T, tConditionP));
		for (OreDictMaterial tMaterial : ANY.Emerald.mToThis) if (tMaterial != MT.Emerald)
		RM.Generifier   .add(new RecipeMapHandlerMaterial(tMaterial                 , NF                                ,   0,   1, NF, MT.Emerald              , NI, T, tConditionP));
		for (OreDictMaterial tMaterial : ANY.Sapphire.mToThis) if (tMaterial != MT.Sapphire)
		RM.Generifier   .add(new RecipeMapHandlerMaterial(tMaterial                 , NF                                ,   0,   1, NF, MT.Sapphire             , NI, T, tConditionP));
		for (OreDictMaterial tMaterial : ANY.Jasper.mToThis) if (tMaterial != MT.Jasper)
		RM.Generifier   .add(new RecipeMapHandlerMaterial(tMaterial                 , NF                                ,   0,   1, NF, MT.Jasper               , NI, T, tConditionP));
		for (OreDictMaterial tMaterial : ANY.CaF2.mToThis) if (tMaterial != MT.CaF2)
		RM.Generifier   .add(new RecipeMapHandlerMaterial(tMaterial                 , NF                                ,   0,   1, NF, MT.CaF2                 , NI, T, tConditionP));
		for (OreDictMaterial tMaterial : ANY.Wood.mToThis) if (tMaterial != MT.Wood)
		RM.Generifier   .add(new RecipeMapHandlerMaterial(tMaterial                 , NF                                ,   0,   1, NF, MT.Wood                 , NI, T, tConditionP));
		for (OreDictMaterial tMaterial : ANY.Wax.mToThis) if (tMaterial != MT.Wax)
		RM.Generifier   .add(new RecipeMapHandlerMaterial(tMaterial                 , NF                                ,   0,   1, NF, MT.Wax                  , NI, T, tConditionP));
		for (OreDictMaterial tMaterial : ANY.Glowstone.mToThis) if (tMaterial != MT.Glowstone)
		RM.Generifier   .add(new RecipeMapHandlerMaterial(tMaterial                 , NF                                ,   0,   1, NF, MT.Glowstone            , NI, T, tConditionP));
		for (OreDictMaterial tMaterial : ANY.Clay.mToThis) if (tMaterial != MT.Clay)
		RM.Generifier   .add(new RecipeMapHandlerMaterial(tMaterial                 , NF                                ,   0,   1, NF, MT.Clay                 , NI, T, tConditionP));
		for (OreDictMaterial tMaterial : ANY.W.mToThis) if (tMaterial != MT.W)
		RM.Generifier   .add(new RecipeMapHandlerMaterial(tMaterial                 , NF                                ,   0,   1, NF, MT.W                    , NI, T, tConditionP));
		for (OreDictMaterial tMaterial : ANY.Si.mToThis) if (tMaterial != MT.Si)
		RM.Generifier   .add(new RecipeMapHandlerMaterial(tMaterial                 , NF                                ,   0,   1, NF, MT.Si                   , NI, T, tConditionP));
		
		for (OreDictPrefix tInput : OreDictPrefix.VALUES) if (tInput.contains(TD.Prefix.EXTRUDER_FODDER)) {
			addExtruderRecipe(tInput, ingot                     , T, IL.Shape_Extruder_Ingot            .get(0));
			addExtruderRecipe(tInput, plate                     , T, IL.Shape_Extruder_Plate            .get(0));
			addExtruderRecipe(tInput, plateCurved               , T, IL.Shape_Extruder_Plate_Curved     .get(0));
			addExtruderRecipe(tInput, stick                     , T, IL.Shape_Extruder_Rod              .get(0));
			addExtruderRecipe(tInput, stickLong                 , T, IL.Shape_Extruder_Rod_Long         .get(0));
			addExtruderRecipe(tInput, bolt                      , T, IL.Shape_Extruder_Bolt             .get(0));
			addExtruderRecipe(tInput, ring                      , T, IL.Shape_Extruder_Ring             .get(0));
			addExtruderRecipe(tInput, wireGt01                  , T, IL.Shape_Extruder_Wire             .get(0));
			addExtruderRecipe(tInput, casingSmall               , T, IL.Shape_Extruder_Casing           .get(0));
			addExtruderRecipe(tInput, toolHeadRawShovel         , T, IL.Shape_Extruder_Shovel           .get(0));
			addExtruderRecipe(tInput, pipeTiny                  , T, IL.Shape_Extruder_Pipe_Tiny        .get(0));
			addExtruderRecipe(tInput, pipeSmall                 , T, IL.Shape_Extruder_Pipe_Small       .get(0));
			addExtruderRecipe(tInput, pipeMedium                , T, IL.Shape_Extruder_Pipe_Medium      .get(0));
			addExtruderRecipe(tInput, pipeLarge                 , T, IL.Shape_Extruder_Pipe_Large       .get(0));
			addExtruderRecipe(tInput, pipeHuge                  , T, IL.Shape_Extruder_Pipe_Huge        .get(0));
			addExtruderRecipe(tInput, toolHeadRawSword          , T, IL.Shape_Extruder_Sword            .get(0));
			addExtruderRecipe(tInput, toolHeadRawHoe            , T, IL.Shape_Extruder_Hoe              .get(0));
			addExtruderRecipe(tInput, toolHeadRawSaw            , T, IL.Shape_Extruder_Saw              .get(0));
			addExtruderRecipe(tInput, toolHeadRawPickaxe        , T, IL.Shape_Extruder_Pickaxe          .get(0));
			addExtruderRecipe(tInput, toolHeadRawAxe            , T, IL.Shape_Extruder_Axe              .get(0));
			addExtruderRecipe(tInput, toolHeadFile              , T, IL.Shape_Extruder_File             .get(0));
			addExtruderRecipe(tInput, toolHeadHammer            , T, IL.Shape_Extruder_Hammer           .get(0));
			addExtruderRecipe(tInput, gearGt                    , T, IL.Shape_Extruder_Gear             .get(0));
			addExtruderRecipe(tInput, gearGtSmall               , T, IL.Shape_Extruder_Gear_Small       .get(0));
			addExtruderRecipe(tInput, blockSolid                , T, IL.Shape_Extruder_Block            .get(0));
			addExtruderRecipe(tInput, capcellcon                , T, IL.Shape_Extruder_CCC              .get(0));
			addExtruderRecipe(tInput, plateTiny                 , T, IL.Shape_Extruder_Plate_Tiny       .get(0));
			addExtruderRecipe(tInput, foil                      , T, IL.Shape_Extruder_Foil             .get(0), SMITHABLE.NOT);
			
			addExtruderRecipe(tInput, ingot                     , F, IL.Shape_SimpleEx_Ingot            .get(0));
			addExtruderRecipe(tInput, plate                     , F, IL.Shape_SimpleEx_Plate            .get(0));
			addExtruderRecipe(tInput, plateCurved               , F, IL.Shape_SimpleEx_Plate_Curved     .get(0));
			addExtruderRecipe(tInput, stick                     , F, IL.Shape_SimpleEx_Rod              .get(0));
			addExtruderRecipe(tInput, stickLong                 , F, IL.Shape_SimpleEx_Rod_Long         .get(0));
			addExtruderRecipe(tInput, bolt                      , F, IL.Shape_SimpleEx_Bolt             .get(0));
			addExtruderRecipe(tInput, ring                      , F, IL.Shape_SimpleEx_Ring             .get(0));
			addExtruderRecipe(tInput, wireGt01                  , F, IL.Shape_SimpleEx_Wire             .get(0));
			addExtruderRecipe(tInput, casingSmall               , F, IL.Shape_SimpleEx_Casing           .get(0));
			addExtruderRecipe(tInput, toolHeadRawShovel         , F, IL.Shape_SimpleEx_Shovel           .get(0));
			addExtruderRecipe(tInput, pipeTiny                  , F, IL.Shape_SimpleEx_Pipe_Tiny        .get(0));
			addExtruderRecipe(tInput, pipeSmall                 , F, IL.Shape_SimpleEx_Pipe_Small       .get(0));
			addExtruderRecipe(tInput, pipeMedium                , F, IL.Shape_SimpleEx_Pipe_Medium      .get(0));
			addExtruderRecipe(tInput, pipeLarge                 , F, IL.Shape_SimpleEx_Pipe_Large       .get(0));
			addExtruderRecipe(tInput, pipeHuge                  , F, IL.Shape_SimpleEx_Pipe_Huge        .get(0));
			addExtruderRecipe(tInput, toolHeadRawSword          , F, IL.Shape_SimpleEx_Sword            .get(0));
			addExtruderRecipe(tInput, toolHeadRawHoe            , F, IL.Shape_SimpleEx_Hoe              .get(0));
			addExtruderRecipe(tInput, toolHeadRawSaw            , F, IL.Shape_SimpleEx_Saw              .get(0));
			addExtruderRecipe(tInput, toolHeadRawPickaxe        , F, IL.Shape_SimpleEx_Pickaxe          .get(0));
			addExtruderRecipe(tInput, toolHeadRawAxe            , F, IL.Shape_SimpleEx_Axe              .get(0));
			addExtruderRecipe(tInput, toolHeadFile              , F, IL.Shape_SimpleEx_File             .get(0));
			addExtruderRecipe(tInput, toolHeadHammer            , F, IL.Shape_SimpleEx_Hammer           .get(0));
			addExtruderRecipe(tInput, gearGt                    , F, IL.Shape_SimpleEx_Gear             .get(0));
			addExtruderRecipe(tInput, gearGtSmall               , F, IL.Shape_SimpleEx_Gear_Small       .get(0));
			addExtruderRecipe(tInput, blockSolid                , F, IL.Shape_SimpleEx_Block            .get(0));
			addExtruderRecipe(tInput, capcellcon                , F, IL.Shape_SimpleEx_CCC              .get(0));
			addExtruderRecipe(tInput, plateTiny                 , F, IL.Shape_SimpleEx_Plate_Tiny       .get(0));
			addExtruderRecipe(tInput, foil                      , F, IL.Shape_SimpleEx_Foil             .get(0), SMITHABLE.NOT);
		}
		
		sExtruderNormal = sExtruderSimple = null;
	}
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	private static ICondition<OreDictMaterial> sExtruderSimple = new And(ANTIMATTER.NOT, EXTRUDER_SIMPLE, EXTRUDER, fullforge()), sExtruderNormal = new And(ANTIMATTER.NOT, EXTRUDER_SIMPLE.NOT, EXTRUDER, fullforge());
	
	private static void addExtruderRecipe(OreDictPrefix aInput, OreDictPrefix aOutput, boolean aHot, ItemStack aShape) {
		if (aInput == aOutput) return;
		long tInputAmount = 1, tOutputAmount = 1;
		if (aOutput.mAmount > aInput.mAmount) tInputAmount = UT.Code.divup(aOutput.mAmount, aInput.mAmount); else tOutputAmount = aInput.mAmount / aOutput.mAmount;
		if (tInputAmount > 64 || tOutputAmount < 1) return;
		if (tOutputAmount > 64) tOutputAmount = 64;
		if (aHot)
		RM.Extruder.add(new RecipeMapHandlerPrefixForging(aInput, tInputAmount, NF, 96, 0                                                       , 0, NF, aOutput, tOutputAmount, aShape, NI, aInput == OP.dust || aInput == OP.ingot, F, F, sExtruderNormal));
		RM.Extruder.add(new RecipeMapHandlerPrefixForging(aInput, tInputAmount, NF, 16, UT.Code.units(aOutput.mAmount*tOutputAmount, U, 64, T)  , 0, NF, aOutput, tOutputAmount, aShape, NI, aInput == OP.dust || aInput == OP.ingot, F, F, sExtruderSimple));
	}
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	private static void addExtruderRecipe(OreDictPrefix aInput, OreDictPrefix aOutput, boolean aHot, ItemStack aShape, ICondition<OreDictMaterial> aCondition) {
		if (aInput == aOutput) return;
		long tInputAmount = 1, tOutputAmount = 1;
		if (aOutput.mAmount > aInput.mAmount) tInputAmount = UT.Code.divup(aOutput.mAmount, aInput.mAmount); else tOutputAmount = aInput.mAmount / aOutput.mAmount;
		if (tInputAmount > 64 || tOutputAmount < 1) return;
		if (tOutputAmount > 64) tOutputAmount = 64;
		if (aHot)
		RM.Extruder.add(new RecipeMapHandlerPrefixForging(aInput, tInputAmount, NF, 96, 0                                                       , 0, NF, aOutput, tOutputAmount, aShape, NI, aInput == OP.dust || aInput == OP.ingot, F, F, new And(aCondition, sExtruderNormal)));
		RM.Extruder.add(new RecipeMapHandlerPrefixForging(aInput, tInputAmount, NF, 16, UT.Code.units(aOutput.mAmount*tOutputAmount, U, 64, T)  , 0, NF, aOutput, tOutputAmount, aShape, NI, aInput == OP.dust || aInput == OP.ingot, F, F, new And(aCondition, sExtruderSimple)));
	}
}
