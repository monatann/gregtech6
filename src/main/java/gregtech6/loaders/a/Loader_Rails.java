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

import gregapi6.block.misc.BlockBaseRail;
import gregapi6.data.ANY;
import gregapi6.data.CS.BlocksGT;
import gregapi6.data.IL;
import gregapi6.data.MD;
import gregapi6.data.MT;
import gregapi6.data.OD;
import gregapi6.data.OP;
import gregapi6.old.Textures;
import gregapi6.util.CR;
import gregapi6.util.ST;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class Loader_Rails implements Runnable {
	@Override
	public void run() {
		BlocksGT.RailAluminium                      = new BlockBaseRail(null, "gt6.block.rail.aluminium"                 , "Aluminium Track"                 , F, F, 0.20F,  6, MT.Al                .mToolQuality-1, Textures.BlockIcons.RAIL_STRAIGHT_ALUMINIUM        , Textures.BlockIcons.RAIL_TURNED_ALUMINIUM);
		BlocksGT.RailBronze                         = new BlockBaseRail(null, "gt6.block.rail.bronze"                    , "Bronze Track"                    , F, F, 0.30F,  8, MT.Bronze            .mToolQuality-1, Textures.BlockIcons.RAIL_STRAIGHT_BRONZE           , Textures.BlockIcons.RAIL_TURNED_BRONZE);
		BlocksGT.RailSteel                          = new BlockBaseRail(null, "gt6.block.rail.steel"                     , "Steel Track"                     , F, F, 0.50F, 12, MT.Steel             .mToolQuality-1, Textures.BlockIcons.RAIL_STRAIGHT_STEEL            , Textures.BlockIcons.RAIL_TURNED_STEEL);
		BlocksGT.RailStainlessSteel                 = new BlockBaseRail(null, "gt6.block.rail.stainlesssteel"            , "Stainless Steel Track"           , F, F, 0.60F, 10, MT.StainlessSteel    .mToolQuality-1, Textures.BlockIcons.RAIL_STRAIGHT_STAINLESSSTEEL   , Textures.BlockIcons.RAIL_TURNED_STAINLESSSTEEL);
		BlocksGT.RailTungsten                       = new BlockBaseRail(null, "gt6.block.rail.tungsten"                  , "Tungsten Track"                  , F, F, 0.70F, 20, MT.W                 .mToolQuality-1, Textures.BlockIcons.RAIL_STRAIGHT_TUNGSTEN         , Textures.BlockIcons.RAIL_TURNED_TUNGSTEN);
		BlocksGT.RailTitanium                       = new BlockBaseRail(null, "gt6.block.rail.titanium"                  , "Titanium Track"                  , F, F, 0.80F, 16, MT.Ti                .mToolQuality-1, Textures.BlockIcons.RAIL_STRAIGHT_TITANIUM         , Textures.BlockIcons.RAIL_TURNED_TITANIUM);
		BlocksGT.RailTungstenSteel                  = new BlockBaseRail(null, "gt6.block.rail.tungstensteel"             , "Tungstensteel Track"             , F, F, 0.90F, 20, MT.TungstenSteel     .mToolQuality-1, Textures.BlockIcons.RAIL_STRAIGHT_TUNGSTENSTEEL    , Textures.BlockIcons.RAIL_TURNED_TUNGSTENSTEEL);
		BlocksGT.RailTungstenCarbide                = new BlockBaseRail(null, "gt6.block.rail.tungstencarbide"           , "Tungstencarbide Track"           , F, F, 1.00F, 24, MT.TungstenCarbide   .mToolQuality-1, Textures.BlockIcons.RAIL_STRAIGHT_TUNGSTENCARBIDE  , Textures.BlockIcons.RAIL_TURNED_TUNGSTENCARBIDE);
		
		BlocksGT.RailAluminiumBooster               = new BlockBaseRail(null, "gt6.block.rail.booster.aluminium"         , "Aluminium Booster Track"         , T, F, 0.20F,  6, MT.Al                .mToolQuality-1, Textures.BlockIcons.RAIL_BOOSTER_ALUMINIUM         , Textures.BlockIcons.RAIL_BOOSTER_ACTIVE_ALUMINIUM);
		BlocksGT.RailBronzeBooster                  = new BlockBaseRail(null, "gt6.block.rail.booster.bronze"            , "Bronze Booster Track"            , T, F, 0.30F,  8, MT.Bronze            .mToolQuality-1, Textures.BlockIcons.RAIL_BOOSTER_BRONZE            , Textures.BlockIcons.RAIL_BOOSTER_ACTIVE_BRONZE);
		BlocksGT.RailSteelBooster                   = new BlockBaseRail(null, "gt6.block.rail.booster.steel"             , "Steel Booster Track"             , T, F, 0.50F, 12, MT.Steel             .mToolQuality-1, Textures.BlockIcons.RAIL_BOOSTER_STEEL             , Textures.BlockIcons.RAIL_BOOSTER_ACTIVE_STEEL);
		BlocksGT.RailStainlessSteelBooster          = new BlockBaseRail(null, "gt6.block.rail.booster.stainlesssteel"    , "Stainless Steel Booster Track"   , T, F, 0.60F, 10, MT.StainlessSteel    .mToolQuality-1, Textures.BlockIcons.RAIL_BOOSTER_STAINLESSSTEEL    , Textures.BlockIcons.RAIL_BOOSTER_ACTIVE_STAINLESSSTEEL);
		BlocksGT.RailTungstenBooster                = new BlockBaseRail(null, "gt6.block.rail.booster.tungsten"          , "Tungsten Booster Track"          , T, F, 0.70F, 20, MT.W                 .mToolQuality-1, Textures.BlockIcons.RAIL_BOOSTER_TUNGSTEN          , Textures.BlockIcons.RAIL_BOOSTER_ACTIVE_TUNGSTEN);
		BlocksGT.RailTitaniumBooster                = new BlockBaseRail(null, "gt6.block.rail.booster.titanium"          , "Titanium Booster Track"          , T, F, 0.80F, 16, MT.Ti                .mToolQuality-1, Textures.BlockIcons.RAIL_BOOSTER_TITANIUM          , Textures.BlockIcons.RAIL_BOOSTER_ACTIVE_TITANIUM);
		BlocksGT.RailTungstenSteelBooster           = new BlockBaseRail(null, "gt6.block.rail.booster.tungstensteel"     , "Tungstensteel Booster Track"     , T, F, 0.90F, 20, MT.TungstenSteel     .mToolQuality-1, Textures.BlockIcons.RAIL_BOOSTER_TUNGSTENSTEEL     , Textures.BlockIcons.RAIL_BOOSTER_ACTIVE_TUNGSTENSTEEL);
		BlocksGT.RailTungstenCarbideBooster         = new BlockBaseRail(null, "gt6.block.rail.booster.tungstencarbide"   , "Tungstencarbide Booster Track"   , T, F, 1.00F, 24, MT.TungstenCarbide   .mToolQuality-1, Textures.BlockIcons.RAIL_BOOSTER_TUNGSTENCARBIDE   , Textures.BlockIcons.RAIL_BOOSTER_ACTIVE_TUNGSTENCARBIDE);
		
		BlocksGT.RailAluminiumDetector              = new BlockBaseRail(null, "gt6.block.rail.detector.aluminium"        , "Aluminium Detector Track"        , F, T, 0.20F,  6, MT.Al                .mToolQuality-1, Textures.BlockIcons.RAIL_DETECTOR_ALUMINIUM        , Textures.BlockIcons.RAIL_DETECTOR_ACTIVE_ALUMINIUM);
		BlocksGT.RailBronzeDetector                 = new BlockBaseRail(null, "gt6.block.rail.detector.bronze"           , "Bronze Detector Track"           , F, T, 0.30F,  8, MT.Bronze            .mToolQuality-1, Textures.BlockIcons.RAIL_DETECTOR_BRONZE           , Textures.BlockIcons.RAIL_DETECTOR_ACTIVE_BRONZE);
		BlocksGT.RailSteelDetector                  = new BlockBaseRail(null, "gt6.block.rail.detector.steel"            , "Steel Detector Track"            , F, T, 0.50F, 12, MT.Steel             .mToolQuality-1, Textures.BlockIcons.RAIL_DETECTOR_STEEL            , Textures.BlockIcons.RAIL_DETECTOR_ACTIVE_STEEL);
		BlocksGT.RailStainlessSteelDetector         = new BlockBaseRail(null, "gt6.block.rail.detector.stainlesssteel"   , "Stainless Steel Detector Track"  , F, T, 0.60F, 10, MT.StainlessSteel    .mToolQuality-1, Textures.BlockIcons.RAIL_DETECTOR_STAINLESSSTEEL   , Textures.BlockIcons.RAIL_DETECTOR_ACTIVE_STAINLESSSTEEL);
		BlocksGT.RailTungstenDetector               = new BlockBaseRail(null, "gt6.block.rail.detector.tungsten"         , "Tungsten Detector Track"         , F, T, 0.70F, 20, MT.W                 .mToolQuality-1, Textures.BlockIcons.RAIL_DETECTOR_TUNGSTEN         , Textures.BlockIcons.RAIL_DETECTOR_ACTIVE_TUNGSTEN);
		BlocksGT.RailTitaniumDetector               = new BlockBaseRail(null, "gt6.block.rail.detector.titanium"         , "Titanium Detector Track"         , F, T, 0.80F, 16, MT.Ti                .mToolQuality-1, Textures.BlockIcons.RAIL_DETECTOR_TITANIUM         , Textures.BlockIcons.RAIL_DETECTOR_ACTIVE_TITANIUM);
		BlocksGT.RailTungstenSteelDetector          = new BlockBaseRail(null, "gt6.block.rail.detector.tungstensteel"    , "Tungstensteel Detector Track"    , F, T, 0.90F, 20, MT.TungstenSteel     .mToolQuality-1, Textures.BlockIcons.RAIL_DETECTOR_TUNGSTENSTEEL    , Textures.BlockIcons.RAIL_DETECTOR_ACTIVE_TUNGSTENSTEEL);
		BlocksGT.RailTungstenCarbideDetector        = new BlockBaseRail(null, "gt6.block.rail.detector.tungstencarbide"  , "Tungstencarbide Detector Track"  , F, T, 1.00F, 24, MT.TungstenCarbide   .mToolQuality-1, Textures.BlockIcons.RAIL_DETECTOR_TUNGSTENCARBIDE  , Textures.BlockIcons.RAIL_DETECTOR_ACTIVE_TUNGSTENCARBIDE);
		
		if (MD.RC.mLoaded) {
			CR.shaped(ST.make((Block)BlocksGT.RailSteel                     , 12, 0), CR.DEF_REV_NCC, "R R", "RBR", "R R", 'R', OP.railGt.dat(ANY.Steel             ), 'B', IL.RC_Bed_Wood);
			CR.shaped(ST.make((Block)BlocksGT.RailAluminium                 , 12, 0), CR.DEF_REV_NCC, "R R", "RBR", "R R", 'R', OP.railGt.dat(MT.Al                 ), 'B', IL.RC_Bed_Wood);
			CR.shaped(ST.make((Block)BlocksGT.RailBronze                    , 12, 0), CR.DEF_REV_NCC, "R R", "RBR", "R R", 'R', OP.railGt.dat(MT.Bronze             ), 'B', IL.RC_Bed_Wood);
			CR.shaped(ST.make((Block)BlocksGT.RailStainlessSteel            , 12, 0), CR.DEF_REV_NCC, "R R", "RBR", "R R", 'R', OP.railGt.dat(MT.StainlessSteel     ), 'B', IL.RC_Bed_Wood);
			CR.shaped(ST.make((Block)BlocksGT.RailTitanium                  , 12, 0), CR.DEF_REV_NCC, "R R", "RBR", "R R", 'R', OP.railGt.dat(MT.Ti                 ), 'B', IL.RC_Bed_Wood);
			CR.shaped(ST.make((Block)BlocksGT.RailTungsten                  , 12, 0), CR.DEF_REV_NCC, "R R", "RBR", "R R", 'R', OP.railGt.dat(ANY.W                 ), 'B', IL.RC_Bed_Wood);
			CR.shaped(ST.make((Block)BlocksGT.RailTungstenSteel             , 12, 0), CR.DEF_REV_NCC, "R R", "RBR", "R R", 'R', OP.railGt.dat(MT.TungstenSteel      ), 'B', IL.RC_Bed_Wood);
			CR.shaped(ST.make((Block)BlocksGT.RailTungstenCarbide           , 12, 0), CR.DEF_REV_NCC, "R R", "RBR", "R R", 'R', OP.railGt.dat(MT.TungstenCarbide    ), 'B', IL.RC_Bed_Wood);
			
			CR.shaped(ST.make((Block)BlocksGT.RailSteelBooster              , 12, 0), CR.DEF_REV_NCC, "RDR", "GBG", "RDR", 'R', OP.railGt.dat(ANY.Steel             ), 'B', IL.RC_Bed_Wood, 'D', OD.itemRedstone, 'G', OP.railGt.dat(MT.Au));
			CR.shaped(ST.make((Block)BlocksGT.RailAluminiumBooster          , 12, 0), CR.DEF_REV_NCC, "RDR", "GBG", "RDR", 'R', OP.railGt.dat(MT.Al                 ), 'B', IL.RC_Bed_Wood, 'D', OD.itemRedstone, 'G', OP.railGt.dat(MT.Ag));
			CR.shaped(ST.make((Block)BlocksGT.RailBronzeBooster             , 12, 0), CR.DEF_REV_NCC, "RDR", "GBG", "RDR", 'R', OP.railGt.dat(MT.Bronze             ), 'B', IL.RC_Bed_Wood, 'D', OD.itemRedstone, 'G', OP.railGt.dat(MT.Ag));
			CR.shaped(ST.make((Block)BlocksGT.RailStainlessSteelBooster     , 12, 0), CR.DEF_REV_NCC, "RDR", "GBG", "RDR", 'R', OP.railGt.dat(MT.StainlessSteel     ), 'B', IL.RC_Bed_Wood, 'D', OD.itemRedstone, 'G', OP.railGt.dat(MT.Au));
			CR.shaped(ST.make((Block)BlocksGT.RailTitaniumBooster           , 12, 0), CR.DEF_REV_NCC, "RDR", "GBG", "RDR", 'R', OP.railGt.dat(MT.Ti                 ), 'B', IL.RC_Bed_Wood, 'D', OD.itemRedstone, 'G', OP.railGt.dat(MT.Electrum));
			CR.shaped(ST.make((Block)BlocksGT.RailTungstenBooster           , 12, 0), CR.DEF_REV_NCC, "RDR", "GBG", "RDR", 'R', OP.railGt.dat(ANY.W                 ), 'B', IL.RC_Bed_Wood, 'D', OD.itemRedstone, 'G', OP.railGt.dat(MT.Electrum));
			CR.shaped(ST.make((Block)BlocksGT.RailTungstenSteelBooster      , 12, 0), CR.DEF_REV_NCC, "RDR", "GBG", "RDR", 'R', OP.railGt.dat(MT.TungstenSteel      ), 'B', IL.RC_Bed_Wood, 'D', OD.itemRedstone, 'G', OP.railGt.dat(MT.Pt));
			CR.shaped(ST.make((Block)BlocksGT.RailTungstenCarbideBooster    , 12, 0), CR.DEF_REV_NCC, "RDR", "GBG", "RDR", 'R', OP.railGt.dat(MT.TungstenCarbide    ), 'B', IL.RC_Bed_Wood, 'D', OD.itemRedstone, 'G', OP.railGt.dat(MT.Pt));
			
			CR.shaped(ST.make((Block)BlocksGT.RailSteelDetector             , 12, 0), CR.DEF_REV_NCC, "RBR", "RPR", "RDR", 'R', OP.railGt.dat(ANY.Steel             ), 'B', IL.RC_Bed_Wood, 'D', OD.itemRedstone, 'P', ST.make(Blocks.stone_pressure_plate, 1, W));
			CR.shaped(ST.make((Block)BlocksGT.RailAluminiumDetector         , 12, 0), CR.DEF_REV_NCC, "RBR", "RPR", "RDR", 'R', OP.railGt.dat(MT.Al                 ), 'B', IL.RC_Bed_Wood, 'D', OD.itemRedstone, 'P', ST.make(Blocks.stone_pressure_plate, 1, W));
			CR.shaped(ST.make((Block)BlocksGT.RailBronzeDetector            , 12, 0), CR.DEF_REV_NCC, "RBR", "RPR", "RDR", 'R', OP.railGt.dat(MT.Bronze             ), 'B', IL.RC_Bed_Wood, 'D', OD.itemRedstone, 'P', ST.make(Blocks.stone_pressure_plate, 1, W));
			CR.shaped(ST.make((Block)BlocksGT.RailStainlessSteelDetector    , 12, 0), CR.DEF_REV_NCC, "RBR", "RPR", "RDR", 'R', OP.railGt.dat(MT.StainlessSteel     ), 'B', IL.RC_Bed_Wood, 'D', OD.itemRedstone, 'P', ST.make(Blocks.stone_pressure_plate, 1, W));
			CR.shaped(ST.make((Block)BlocksGT.RailTitaniumDetector          , 12, 0), CR.DEF_REV_NCC, "RBR", "RPR", "RDR", 'R', OP.railGt.dat(MT.Ti                 ), 'B', IL.RC_Bed_Wood, 'D', OD.itemRedstone, 'P', ST.make(Blocks.stone_pressure_plate, 1, W));
			CR.shaped(ST.make((Block)BlocksGT.RailTungstenDetector          , 12, 0), CR.DEF_REV_NCC, "RBR", "RPR", "RDR", 'R', OP.railGt.dat(ANY.W                 ), 'B', IL.RC_Bed_Wood, 'D', OD.itemRedstone, 'P', ST.make(Blocks.stone_pressure_plate, 1, W));
			CR.shaped(ST.make((Block)BlocksGT.RailTungstenSteelDetector     , 12, 0), CR.DEF_REV_NCC, "RBR", "RPR", "RDR", 'R', OP.railGt.dat(MT.TungstenSteel      ), 'B', IL.RC_Bed_Wood, 'D', OD.itemRedstone, 'P', ST.make(Blocks.stone_pressure_plate, 1, W));
			CR.shaped(ST.make((Block)BlocksGT.RailTungstenCarbideDetector   , 12, 0), CR.DEF_REV_NCC, "RBR", "RPR", "RDR", 'R', OP.railGt.dat(MT.TungstenCarbide    ), 'B', IL.RC_Bed_Wood, 'D', OD.itemRedstone, 'P', ST.make(Blocks.stone_pressure_plate, 1, W));
		} else {
			CR.shaped(ST.make((Block)BlocksGT.RailSteel                     ,  4, 0), CR.DEF_REV_NCC, "RSR", "RSR", "RSR", 'R', OP.railGt.dat(ANY.Steel             ), 'S', OP.stick.dat(MT.WoodSealed));
			CR.shaped(ST.make((Block)BlocksGT.RailAluminium                 ,  4, 0), CR.DEF_REV_NCC, "RSR", "RSR", "RSR", 'R', OP.railGt.dat(MT.Al                 ), 'S', OP.stick.dat(MT.WoodSealed));
			CR.shaped(ST.make((Block)BlocksGT.RailBronze                    ,  4, 0), CR.DEF_REV_NCC, "RSR", "RSR", "RSR", 'R', OP.railGt.dat(MT.Bronze             ), 'S', OP.stick.dat(MT.WoodSealed));
			CR.shaped(ST.make((Block)BlocksGT.RailStainlessSteel            ,  4, 0), CR.DEF_REV_NCC, "RSR", "RSR", "RSR", 'R', OP.railGt.dat(MT.StainlessSteel     ), 'S', OP.stick.dat(MT.WoodSealed));
			CR.shaped(ST.make((Block)BlocksGT.RailTitanium                  ,  4, 0), CR.DEF_REV_NCC, "RSR", "RSR", "RSR", 'R', OP.railGt.dat(MT.Ti                 ), 'S', OP.stick.dat(MT.WoodSealed));
			CR.shaped(ST.make((Block)BlocksGT.RailTungsten                  ,  4, 0), CR.DEF_REV_NCC, "RSR", "RSR", "RSR", 'R', OP.railGt.dat(ANY.W                 ), 'S', OP.stick.dat(MT.WoodSealed));
			CR.shaped(ST.make((Block)BlocksGT.RailTungstenSteel             ,  4, 0), CR.DEF_REV_NCC, "RSR", "RSR", "RSR", 'R', OP.railGt.dat(MT.TungstenSteel      ), 'S', OP.stick.dat(MT.WoodSealed));
			CR.shaped(ST.make((Block)BlocksGT.RailTungstenCarbide           ,  4, 0), CR.DEF_REV_NCC, "RSR", "RSR", "RSR", 'R', OP.railGt.dat(MT.TungstenCarbide    ), 'S', OP.stick.dat(MT.WoodSealed));
			
			CR.shaped(ST.make((Block)BlocksGT.RailSteelBooster              ,  4, 0), CR.DEF_REV_NCC, "RSR", "GDG", "RSR", 'R', OP.railGt.dat(ANY.Steel             ), 'S', OP.stick.dat(MT.WoodSealed), 'D', OD.itemRedstone, 'G', OP.railGt.dat(MT.Au));
			CR.shaped(ST.make((Block)BlocksGT.RailAluminiumBooster          ,  4, 0), CR.DEF_REV_NCC, "RSR", "GDG", "RSR", 'R', OP.railGt.dat(MT.Al                 ), 'S', OP.stick.dat(MT.WoodSealed), 'D', OD.itemRedstone, 'G', OP.railGt.dat(MT.Ag));
			CR.shaped(ST.make((Block)BlocksGT.RailBronzeBooster             ,  4, 0), CR.DEF_REV_NCC, "RSR", "GDG", "RSR", 'R', OP.railGt.dat(MT.Bronze             ), 'S', OP.stick.dat(MT.WoodSealed), 'D', OD.itemRedstone, 'G', OP.railGt.dat(MT.Ag));
			CR.shaped(ST.make((Block)BlocksGT.RailStainlessSteelBooster     ,  4, 0), CR.DEF_REV_NCC, "RSR", "GDG", "RSR", 'R', OP.railGt.dat(MT.StainlessSteel     ), 'S', OP.stick.dat(MT.WoodSealed), 'D', OD.itemRedstone, 'G', OP.railGt.dat(MT.Au));
			CR.shaped(ST.make((Block)BlocksGT.RailTitaniumBooster           ,  4, 0), CR.DEF_REV_NCC, "RSR", "GDG", "RSR", 'R', OP.railGt.dat(MT.Ti                 ), 'S', OP.stick.dat(MT.WoodSealed), 'D', OD.itemRedstone, 'G', OP.railGt.dat(MT.Electrum));
			CR.shaped(ST.make((Block)BlocksGT.RailTungstenBooster           ,  4, 0), CR.DEF_REV_NCC, "RSR", "GDG", "RSR", 'R', OP.railGt.dat(ANY.W                 ), 'S', OP.stick.dat(MT.WoodSealed), 'D', OD.itemRedstone, 'G', OP.railGt.dat(MT.Electrum));
			CR.shaped(ST.make((Block)BlocksGT.RailTungstenSteelBooster      ,  4, 0), CR.DEF_REV_NCC, "RSR", "GDG", "RSR", 'R', OP.railGt.dat(MT.TungstenSteel      ), 'S', OP.stick.dat(MT.WoodSealed), 'D', OD.itemRedstone, 'G', OP.railGt.dat(MT.Pt));
			CR.shaped(ST.make((Block)BlocksGT.RailTungstenCarbideBooster    ,  4, 0), CR.DEF_REV_NCC, "RSR", "GDG", "RSR", 'R', OP.railGt.dat(MT.TungstenCarbide    ), 'S', OP.stick.dat(MT.WoodSealed), 'D', OD.itemRedstone, 'G', OP.railGt.dat(MT.Pt));
			
			CR.shaped(ST.make((Block)BlocksGT.RailSteelDetector             ,  4, 0), CR.DEF_REV_NCC, "RSR", "RPR", "RDR", 'R', OP.railGt.dat(ANY.Steel             ), 'S', OP.stick.dat(MT.WoodSealed), 'D', OD.itemRedstone, 'P', ST.make(Blocks.stone_pressure_plate, 1, W));
			CR.shaped(ST.make((Block)BlocksGT.RailAluminiumDetector         ,  4, 0), CR.DEF_REV_NCC, "RSR", "RPR", "RDR", 'R', OP.railGt.dat(MT.Al                 ), 'S', OP.stick.dat(MT.WoodSealed), 'D', OD.itemRedstone, 'P', ST.make(Blocks.stone_pressure_plate, 1, W));
			CR.shaped(ST.make((Block)BlocksGT.RailBronzeDetector            ,  4, 0), CR.DEF_REV_NCC, "RSR", "RPR", "RDR", 'R', OP.railGt.dat(MT.Bronze             ), 'S', OP.stick.dat(MT.WoodSealed), 'D', OD.itemRedstone, 'P', ST.make(Blocks.stone_pressure_plate, 1, W));
			CR.shaped(ST.make((Block)BlocksGT.RailStainlessSteelDetector    ,  4, 0), CR.DEF_REV_NCC, "RSR", "RPR", "RDR", 'R', OP.railGt.dat(MT.StainlessSteel     ), 'S', OP.stick.dat(MT.WoodSealed), 'D', OD.itemRedstone, 'P', ST.make(Blocks.stone_pressure_plate, 1, W));
			CR.shaped(ST.make((Block)BlocksGT.RailTitaniumDetector          ,  4, 0), CR.DEF_REV_NCC, "RSR", "RPR", "RDR", 'R', OP.railGt.dat(MT.Ti                 ), 'S', OP.stick.dat(MT.WoodSealed), 'D', OD.itemRedstone, 'P', ST.make(Blocks.stone_pressure_plate, 1, W));
			CR.shaped(ST.make((Block)BlocksGT.RailTungstenDetector          ,  4, 0), CR.DEF_REV_NCC, "RSR", "RPR", "RDR", 'R', OP.railGt.dat(ANY.W                 ), 'S', OP.stick.dat(MT.WoodSealed), 'D', OD.itemRedstone, 'P', ST.make(Blocks.stone_pressure_plate, 1, W));
			CR.shaped(ST.make((Block)BlocksGT.RailTungstenSteelDetector     ,  4, 0), CR.DEF_REV_NCC, "RSR", "RPR", "RDR", 'R', OP.railGt.dat(MT.TungstenSteel      ), 'S', OP.stick.dat(MT.WoodSealed), 'D', OD.itemRedstone, 'P', ST.make(Blocks.stone_pressure_plate, 1, W));
			CR.shaped(ST.make((Block)BlocksGT.RailTungstenCarbideDetector   ,  4, 0), CR.DEF_REV_NCC, "RSR", "RPR", "RDR", 'R', OP.railGt.dat(MT.TungstenCarbide    ), 'S', OP.stick.dat(MT.WoodSealed), 'D', OD.itemRedstone, 'P', ST.make(Blocks.stone_pressure_plate, 1, W));
		}
	}
}
