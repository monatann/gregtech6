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

package gregtech6.items;

import static gregapi6.data.CS.*;

import gregapi6.GT_API;
import gregapi6.code.IItemContainer;
import gregapi6.code.ItemStackContainer;
import gregapi6.cover.covers.CoverTextureCanvas;
import gregapi6.data.ANY;
import gregapi6.data.CS.BlocksGT;
import gregapi6.data.CS.BooksGT;
import gregapi6.data.CS.GarbageGT;
import gregapi6.data.CS.ItemsGT;
import gregapi6.data.CS.OreDictToolNames;
import gregapi6.data.FL;
import gregapi6.data.IL;
import gregapi6.data.MD;
import gregapi6.data.MT;
import gregapi6.data.OD;
import gregapi6.data.OP;
import gregapi6.data.RM;
import gregapi6.data.TC;
import gregapi6.data.TD;
import gregapi6.item.CreativeTab;
import gregapi6.item.IItemRottable;
import gregapi6.item.multiitem.MultiItem;
import gregapi6.item.multiitem.MultiItemRandom;
import gregapi6.item.multiitem.behaviors.Behavior_Bucket_Simple;
import gregapi6.item.multiitem.behaviors.Behavior_PrintedPages;
import gregapi6.item.multiitem.behaviors.Behavior_Switch_Metadata;
import gregapi6.item.multiitem.behaviors.Behavior_Tool;
import gregapi6.item.multiitem.behaviors.IBehavior;
import gregapi6.item.multiitem.energy.EnergyStat;
import gregapi6.item.multiitem.energy.EnergyStatDebug;
import gregapi6.old.Textures;
import gregapi6.oredict.OreDictItemData;
import gregapi6.oredict.OreDictMaterial;
import gregapi6.render.BlockTextureDefault;
import gregapi6.util.CR;
import gregapi6.util.OM;
import gregapi6.util.ST;
import gregapi6.util.UT;
import gregtech6.items.behaviors.Behavior_Cropnalyzer;
import gregtech6.items.behaviors.Behavior_Duct_Tape;
import gregtech6.items.behaviors.Behavior_Key;
import gregtech6.items.behaviors.Behavior_Lighter;
import gregtech6.items.behaviors.Behavior_MultiblockBuilder;
import gregtech6.items.behaviors.Behavior_Remote;
import gregtech6.items.behaviors.Behavior_Scanner;
import gregtech6.items.behaviors.Behavior_Spray_Color;
import gregtech6.items.behaviors.Behavior_Spray_Color_Remover;
import gregtech6.items.behaviors.Behavior_Spray_Extinguisher;
import gregtech6.items.behaviors.Behavior_Spray_Foam;
import gregtech6.items.behaviors.Behavior_Spray_Foam_Hardener;
import gregtech6.items.behaviors.Behavior_Spray_Foam_Remover;
import gregtech6.items.behaviors.Behavior_Worldgen_Debugger;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

public class MultiItemRandomTools extends MultiItemRandom implements IItemRottable {
	public MultiItemRandomTools() {
		super(MD.GT.mID, "gt6.multiitem.randomtools");
		setCreativeTab(new CreativeTab(getUnlocalizedName(), "GregTech: Equipment", this, (short)5008));
	}
	
	@Override
	public void addItems() {
		int tLastID = 0;
		IBehavior<MultiItem> tBehaviour;
		
		IL.Compound_Bronze.set(                 addItem(tLastID =     0, "Bronze Compound"              , "Put in Furnace to smelt it"      , new OreDictItemData(MT.Bronze       , U9), TC.stack(TC.METALLUM, 1)));
		IL.Compound_Brass.set(                  addItem(tLastID =     1, "Brass Compound"               , "Put in Furnace to smelt it"      , new OreDictItemData(MT.Brass        , U9), TC.stack(TC.METALLUM, 1)));
		IL.Compound_BismuthBronze.set(          addItem(tLastID =     2, "Bismuth Bronze Compound"      , "Put in Furnace to smelt it"      , new OreDictItemData(MT.BismuthBronze, U9), TC.stack(TC.METALLUM, 1)));
		
		RM.add_smelting(IL.Compound_Bronze       .get(1), OP.nugget.mat(MT.Bronze, 1));
		RM.add_smelting(IL.Compound_Brass        .get(1), OP.nugget.mat(MT.Brass, 1));
		RM.add_smelting(IL.Compound_BismuthBronze.get(1), OP.nugget.mat(MT.BismuthBronze, 1));
		
		IL.Ceramic_Jug_Raw.set(                 addItem(tLastID =   996, "Raw Clay Jug"                 , "Put in Furnace to harden it"     , new OreDictItemData(MT.Clay, U*6), TC.stack(TC.VACUOS, 1), TC.stack(TC.TERRA, 2)));
		IL.Measuring_Pot_Raw.set(               addItem(tLastID =   997, "Raw Measuring Pot"            , "Put in Furnace to harden it"     , new OreDictItemData(MT.Clay, U*4), TC.stack(TC.VACUOS, 1), TC.stack(TC.TERRA, 2)));
		IL.Food_Can_Empty.set(                  addItem(tLastID =   998, "Empty Food Can"               , "Used for canning Food"           , new OreDictItemData(MT.TinAlloy, OP.plateCurved.mAmount), TC.stack(TC.VACUOS, 1), TC.stack(TC.FABRICO, 1)));
		IL.Spray_Empty.set(                     addItem(tLastID =   999, "Empty Spray Can"              , "Used for making Sprays"          , new OreDictItemData(MT.Sn, OP.plateCurved.mAmount, MT.Redstone, OP.dust.mAmount), TC.stack(TC.VACUOS, 1), TC.stack(TC.MOTUS, 1)));
		
		CR.shaped(IL.Ceramic_Jug_Raw    .get(1), CR.DEF_NCC     , "kCR", "C C", "CCC", 'C', "itemClay", 'R', OreDictToolNames.rollingpin);
		CR.shaped(IL.Measuring_Pot_Raw  .get(1), CR.DEF_NCC     , "CkC", "CCR"     , 'C', "itemClay", 'R', OreDictToolNames.rollingpin);
		CR.shaped(IL.Food_Can_Empty     .get(1), CR.DEF_NAC_NCC , "fh" , "oP"       , 'P', OP.plateCurved.dat(MT.TinAlloy));
		CR.shaped(IL.Spray_Empty        .get(1), CR.DEF_NCC     , "Rf" , "Cs"       , 'R', OD.itemRedstone, 'C', OP.plateCurved.dat(MT.Sn));
		
		RM.RollBender.addRecipe1(T, 16, 64, OP.plateCurved.mat(MT.TinAlloy, 1), IL.Food_Can_Empty.get(1));
		
// TODO RA.addAssemblerRecipe(OP.dust.mat(MT.Redstone, 1), OP.cell.mat(MT.Empty, 1), IL.Spray_Empty.get(1), 800, 1);
		
		for (byte i = 0; i < 16; i++) {
		IL.SPRAY_CAN_DYES[i].set(               addItem(tLastID =  1000 + 2 * i , "Spray Paint ("+DYE_NAMES[i]+")", "Full", TC.stack(TC.SENSUS, 4)));
		IL.SPRAY_CAN_DYES_USED[i].set(          addItem(tLastID +    1          , "Spray Paint ("+DYE_NAMES[i]+")", "Used", TC.stack(TC.SENSUS, 3), TD.Creative.HIDDEN));
		tBehaviour = new Behavior_Spray_Color(IL.Spray_Empty.get(1), IL.SPRAY_CAN_DYES_USED[i].get(1), IL.SPRAY_CAN_DYES[i].get(1), 512, i);
		addItemBehavior(tLastID, tBehaviour); addItemBehavior(tLastID+1, tBehaviour);
		RM.Canner.addRecipe1(T, 16, 256, IL.Spray_Empty.get(1), FL.mul(DYE_FLUIDS_CHEMICAL[i], 16), NF, IL.SPRAY_CAN_DYES[i].get(1));
		ItemsGT.addNEIRedirects(IL.SPRAY_CAN_DYES_USED[i].get(1), IL.SPRAY_CAN_DYES[i].get(1));
		RM.Other.addFakeRecipe(F, ST.array(IL.SPRAY_CAN_DYES[i].get(1), IL.SPRAY_CAN_DYES_USED[i].get(1), ST.make(Blocks.wool, 1, 0), ST.make(Blocks.glass_pane, 1, 0), ST.make(Blocks.glass, 1, 0), ST.make(Blocks.hardened_clay, 1, 0)), ST.array(NI, NI, ST.make(Blocks.wool, 1, 15-i), ST.make(Blocks.stained_glass_pane, 1, 15-i), ST.make(Blocks.stained_glass, 1, 15-i), ST.make(Blocks.stained_hardened_clay, 1, 15-i)), null, ZL_LONG, ZL_FS, ZL_FS, 0, 0, 0);
		
		
		IL.SPRAY_CAN_FOAM[i].set(               addItem(tLastID =  1100 + 2 * i , "C-Foam Spray ("+DYE_NAMES[i]+")", "Full", TC.stack(TC.TERRA, 2), TC.stack(TC.FABRICO, 2)));
		IL.SPRAY_CAN_FOAM_USED[i].set(          addItem(tLastID +    1          , "C-Foam Spray ("+DYE_NAMES[i]+")", "Used", TC.stack(TC.TERRA, 1), TC.stack(TC.FABRICO, 1), TD.Creative.HIDDEN));
		tBehaviour = new Behavior_Spray_Foam(IL.Spray_Empty.get(1), IL.SPRAY_CAN_FOAM_USED[i].get(1), IL.SPRAY_CAN_FOAM[i].get(1), 256, i, F);
		addItemBehavior(tLastID, tBehaviour); addItemBehavior(tLastID+1, tBehaviour);
		RM.Canner.addRecipe1(T, 16, 256, IL.Spray_Empty.get(1), FL.mul(DYED_C_FOAMS[i], 256), NF, IL.SPRAY_CAN_FOAM[i].get(1));
		ItemsGT.addNEIRedirects(IL.SPRAY_CAN_FOAM_USED[i].get(1), IL.SPRAY_CAN_FOAM[i].get(1), ST.make(BlocksGT.CFoamFresh, 1, i), ST.make(BlocksGT.CFoam, 1, i));
		RM.Other.addFakeRecipe(F, ST.array(IL.SPRAY_CAN_FOAM[i].get(1), IL.SPRAY_CAN_FOAM_USED[i].get(1)), ST.array(ST.make(BlocksGT.CFoamFresh, 1, i), ST.make(BlocksGT.CFoam, 1, i)), null, ZL_LONG, ZL_FS, ZL_FS, 0, 0, 0);
		
		
		IL.SPRAY_CAN_FOAM_OWNED[i].set(         addItem(tLastID =  1132 + 2 * i , "Advanced C-Foam Spray ("+DYE_NAMES[i]+")", "Full (C-Foam only breakable by Owner once dry)", TC.stack(TC.TERRA, 2), TC.stack(TC.FABRICO, 2), TC.stack(TC.SPIRITUS, 2)));
		IL.SPRAY_CAN_FOAM_OWNED_USED[i].set(    addItem(tLastID +    1          , "Advanced C-Foam Spray ("+DYE_NAMES[i]+")", "Used (C-Foam only breakable by Owner once dry)", TC.stack(TC.TERRA, 1), TC.stack(TC.FABRICO, 1), TC.stack(TC.SPIRITUS, 1), TD.Creative.HIDDEN));
		tBehaviour = new Behavior_Spray_Foam(IL.Spray_Empty.get(1), IL.SPRAY_CAN_FOAM_OWNED_USED[i].get(1), IL.SPRAY_CAN_FOAM_OWNED[i].get(1), 256, i, T);
		addItemBehavior(tLastID, tBehaviour); addItemBehavior(tLastID+1, tBehaviour);
		RM.Canner.addRecipe1(T, 16, 256, IL.Spray_Empty.get(1), FL.mul(DYED_C_FOAMS_OWNED[i], 256), NF, IL.SPRAY_CAN_FOAM_OWNED[i].get(1));
		ItemsGT.addNEIRedirects(IL.SPRAY_CAN_FOAM_OWNED_USED[i].get(1), IL.SPRAY_CAN_FOAM_OWNED[i].get(1));
		RM.Other.addFakeRecipe(F, ST.array(IL.SPRAY_CAN_FOAM_OWNED[i].get(1), IL.SPRAY_CAN_FOAM_OWNED_USED[i].get(1)), ST.array(ST.make(BlocksGT.CFoamFresh, 1, i, "Player-Owned C-Foam"), ST.make(BlocksGT.CFoam, 1, i, "Player-Owned C-Foam")), null, ZL_LONG, ZL_FS, ZL_FS, 0, 0, 0);
		}
		
		
		
		IL.Spray_Color_Remover.set(             addItem(tLastID =  1096, "Paint Removal Spray", "Full", TC.stack(TC.SENSUS, 2), TC.stack(TC.PERDITIO, 2)));
		IL.Spray_Color_Remover_Used.set(        addItem(tLastID =  1097, "Paint Removal Spray", "Used", TC.stack(TC.SENSUS, 1), TC.stack(TC.PERDITIO, 1), TD.Creative.HIDDEN));
		tBehaviour = new Behavior_Spray_Color_Remover(IL.Spray_Empty.get(1), IL.Spray_Color_Remover_Used.get(1), IL.Spray_Color_Remover.get(1), 256);
		addItemBehavior(1096, tBehaviour); addItemBehavior(1097, tBehaviour);
		RM.Canner.addRecipe1(T, 16, 256, IL.Spray_Empty.get(1), MT.Cl.fluid(16*U, T), NF, IL.Spray_Color_Remover.get(1));
		ItemsGT.addNEIRedirects(IL.Spray_Color_Remover_Used.get(1), IL.Spray_Color_Remover.get(1));
		
		
		
		IL.Spray_Foam_Remover.set(              addItem(tLastID =  1196, "C-Foam Removal Spray", "Full", TC.stack(TC.TERRA, 2), TC.stack(TC.PERDITIO, 2)));
		IL.Spray_Foam_Remover_Used.set(         addItem(tLastID =  1197, "C-Foam Removal Spray", "Used", TC.stack(TC.TERRA, 1), TC.stack(TC.PERDITIO, 1), TD.Creative.HIDDEN));
		tBehaviour = new Behavior_Spray_Foam_Remover(IL.Spray_Empty.get(1), IL.Spray_Foam_Remover_Used.get(1), IL.Spray_Foam_Remover.get(1), 256);
		addItemBehavior(1196, tBehaviour); addItemBehavior(1197, tBehaviour);
		RM.Canner.addRecipe1(T, 16, 256, IL.Spray_Empty.get(1), MT.H2SO4.fluid(16*U, T), NF, IL.Spray_Foam_Remover.get(1));
		RM.Canner.addRecipe1(T, 16, 256, IL.Spray_Empty.get(1), MT.HNO3.fluid(16*U, T), NF, IL.Spray_Foam_Remover.get(1));
		ItemsGT.addNEIRedirects(IL.Spray_Foam_Remover_Used.get(1), IL.Spray_Foam_Remover.get(1));
		
		
		
		IL.Spray_Foam_Hardener.set(             addItem(tLastID =  1198, "Hardening Spray", "Full", TC.stack(TC.TERRA, 2), TC.stack(TC.TUTAMEN, 2)));
		IL.Spray_Foam_Hardener_Used.set(        addItem(tLastID =  1199, "Hardening Spray", "Used", TC.stack(TC.TERRA, 1), TC.stack(TC.TUTAMEN, 1), TD.Creative.HIDDEN));
		tBehaviour = new Behavior_Spray_Foam_Hardener(IL.Spray_Empty.get(1), IL.Spray_Foam_Hardener_Used.get(1), IL.Spray_Foam_Hardener.get(1), 256);
		addItemBehavior(1198, tBehaviour); addItemBehavior(1199, tBehaviour);
		RM.Canner.addRecipe2(T, 16, 256, ST.make(Blocks.sand, 16, W), IL.Spray_Empty.get(1), IL.Spray_Foam_Hardener.get(1));
		for (OreDictMaterial tMat : ANY.SiO2.mToThis)
		RM.Canner.addRecipe2(T, 16, 256, OM.dust(tMat, 16*U), IL.Spray_Empty.get(1), IL.Spray_Foam_Hardener.get(1));
		ItemsGT.addNEIRedirects(IL.Spray_Foam_Hardener_Used.get(1), IL.Spray_Foam_Hardener.get(1));
		
		
		
		IL.Spray_Extinguisher.set(              addItem(tLastID =  1998, "Fire Extinguisher (CO2)", "Full", TC.stack(TC.PERDITIO, 2), TC.stack(TC.IGNIS, 2)));
		IL.Spray_Extinguisher_Used.set(         addItem(tLastID =  1999, "Fire Extinguisher (CO2)", "Used", TC.stack(TC.PERDITIO, 1), TC.stack(TC.IGNIS, 1), TD.Creative.HIDDEN));
		tBehaviour = new Behavior_Spray_Extinguisher(IL.Spray_Empty.get(1), IL.Spray_Extinguisher_Used.get(1), IL.Spray_Extinguisher.get(1), 256);
		addItemBehavior(1998, tBehaviour); addItemBehavior(1999, tBehaviour);
		RM.Canner.addRecipe1(T, 16, 256, IL.Spray_Empty.get(1), MT.CO2.fluid(16*U, T), NF, IL.Spray_Extinguisher.get(1));
		ItemsGT.addNEIRedirects(IL.Spray_Extinguisher_Used.get(1), IL.Spray_Extinguisher.get(1));
		
		
		
		OreDictMaterial[] tBucketMaterials = new OreDictMaterial[] {MT.Cu, MT.Sn, MT.Zn, MT.Pb, MT.Bi, MT.Brass, MT.Bronze, MT.BismuthBronze};
		for (int i = 0; i < 8; i++) {
			OreDictItemData tData = new OreDictItemData(ANY.Wood, U*3, OM.stack(tBucketMaterials[i], U*1));
			ItemStack tBucket = addItem(tLastID = 2000 + i*100, "Wooden Bucket", "Empty", TC.stack(TC.ARBOR, 2), TC.stack(TC.VACUOS, 2), Behavior_Bucket_Simple.INSTANCE, tData);
			CR.shaped(tBucket, CR.DEF_NCC, "WPW", " Wh", 'P', OP.plate.dat(tBucketMaterials[i]), 'W', OD.plankAnyWood);
			ItemsGT.addNEIRedirects(tBucket
			, addItem(++tLastID, "Wooden Bucket", "Water"                                   , TC.stack(TC.ARBOR, 2), TC.stack(TC.AQUA       , 2), new Behavior_Bucket_Simple(ST.make(Items.water_bucket, 1, 0))                       , tData.copy(), FL.Water.make(1000), FL.DistW.make(1000), FL.River_Water.make(1000)     , OD.container1000water)
			, addItem(++tLastID, "Wooden Bucket", "Milk (you cannot drink out of Buckets!)" , TC.stack(TC.ARBOR, 2), TC.stack(TC.SANO       , 2), new Behavior_Bucket_Simple(ST.make(Items.milk_bucket, 1, 0))                        , tData.copy(), FL.Milk.make(1000), FL.MilkGrC.make(1000)                               , OD.container1000milk)
			, addItem(++tLastID, "Wooden Bucket", "Latex"                                   , TC.stack(TC.ARBOR, 2), TC.stack(TC.LIMUS      , 2), Behavior_Bucket_Simple.INSTANCE                                                     , tData.copy(), FL.Latex.make(1000), FL.make("molten.latex", 1000)               , OD.container1000latex)
			, addItem(++tLastID, "Wooden Bucket", "Creosote Oil"                            , TC.stack(TC.ARBOR, 2), TC.stack(TC.IGNIS      , 2), new Behavior_Bucket_Simple(IL.RC_Creosote_Bucket.get(1))                            , tData.copy(), FL.Oil_Creosote.make(1000)                                              , OD.container1000creosote)
			, addItem(++tLastID, "Wooden Bucket", "Sea Water (you cannot place this!)"      , TC.stack(TC.ARBOR, 2), TC.stack(TC.TEMPESTAS  , 2), new Behavior_Switch_Metadata(2000 + i*100)                                          , tData.copy(), FL.Ocean.make(1000), FL.OceanGrC.make(1000), FL.Tropics_Water.make(1000))
			, addItem(++tLastID, "Wooden Bucket", "Rubber Tree Sap"                         , TC.stack(TC.ARBOR, 2), TC.stack(TC.LIMUS      , 2), Behavior_Bucket_Simple.INSTANCE                                                     , tData.copy(), FL.Resin_Rubber.make(1000)                                              , OD.container1000rubbertreesap)
			, addItem(++tLastID, "Wooden Bucket", "Spruce Tree Resin"                       , TC.stack(TC.ARBOR, 3), TC.stack(TC.LIMUS      , 1), Behavior_Bucket_Simple.INSTANCE                                                     , tData.copy(), FL.Resin_Spruce.make(1000)                                              , OD.container1000spruceresin)
			, addItem(++tLastID, "Wooden Bucket", "Honey"                                   , TC.stack(TC.ARBOR, 3), TC.stack(TC.BESTIA     , 1), new Behavior_Bucket_Simple(ST.make(MD.ERE, "bucketHoney", 1, 0, IL.FR_Honey_Bucket)), tData.copy(), FL.Honey.make(1000), FL.HoneyGrC.make(1000), FL.HoneyBoP.make(1000)     , OD.container1000honey)
			, addItem(++tLastID, "Wooden Bucket", "Dirty Water (you cannot place this!)"    , TC.stack(TC.ARBOR, 2), TC.stack(TC.AQUA       , 1), TC.stack(TC.VENENUM, 1), new Behavior_Switch_Metadata(2000 + i*100)                 , tData.copy(), FL.Dirty_Water.make(1000)                                               )
			, addItem(++tLastID, "Wooden Bucket", "Lubricant"                               , TC.stack(TC.ARBOR, 2), TC.stack(TC.LIMUS      , 2), Behavior_Bucket_Simple.INSTANCE                                                     , tData.copy(), FL.LubRoCant.make(1000), FL.Lubricant.make(1000)                        , OD.container1000lubricant)
			, addItem(++tLastID, "Wooden Bucket", "Milk (you cannot drink out of Buckets!)" , TC.stack(TC.ARBOR, 2), TC.stack(TC.VENENUM    , 2), Behavior_Bucket_Simple.INSTANCE                                                     , tData.copy(), FL.Milk_Spoiled.make(1000)                                              )
			, addItem(++tLastID, "Wooden Bucket", "Maple Sap"                               , TC.stack(TC.ARBOR, 2), TC.stack(TC.FAMES      , 2), Behavior_Bucket_Simple.INSTANCE                                                     , tData.copy(), FL.Sap_Maple.make(1000)                                                 , OD.container1000maplesap)
			, addItem(++tLastID, "Wooden Bucket", "Rainbow Sap"                             , TC.stack(TC.ARBOR, 2), TC.stack(TC.AURAM      , 2), Behavior_Bucket_Simple.INSTANCE                                                     , tData.copy(), FL.Sap_Rainbow.make(1000)                                               , OD.container1000rainbowsap)
			);
		}
		
		IL.Wooden_Bucket_Copper         .set(this, 2000);
		IL.Wooden_Bucket_Tin            .set(this, 2100);
		IL.Wooden_Bucket_Zinc           .set(this, 2200);
		IL.Wooden_Bucket_Lead           .set(this, 2300);
		IL.Wooden_Bucket_Bismuth        .set(this, 2400);
		IL.Wooden_Bucket_Brass          .set(this, 2500);
		IL.Wooden_Bucket_Bronze         .set(this, 2600);
		IL.Wooden_Bucket_BismuthBronze  .set(this, 2700);
		
		
		
		IL.Tool_Matches.set(                    addItem(tLastID =  5000, "Match"                            , ""                                            , new Behavior_Lighter(9000), TC.stack(TC.IGNIS, 1), TC.stack(TC.VACUOS, 1), OD.craftingFirestarter));
		RM.Assembler.addRecipe2(T, 16, 16, OP.bolt.mat(MT.Wood, 1), OP.dustSmall.mat(MT.P           , 1), IL.Tool_Matches.get(1));
		RM.Assembler.addRecipe2(T, 16, 16, OP.bolt.mat(MT.Wood, 1), OP.dustSmall.mat(MT.Phosphorus  , 1), IL.Tool_Matches.get(1));
		RM.Assembler.addRecipe2(T, 16, 64, OP.bolt.mat(MT.Wood, 4), OP.dust     .mat(MT.P           , 1), IL.Tool_Matches.get(4));
		RM.Assembler.addRecipe2(T, 16, 64, OP.bolt.mat(MT.Wood, 4), OP.dust     .mat(MT.Phosphorus  , 1), IL.Tool_Matches.get(4));
		CR.shaped(IL.Tool_Matches.get(1), CR.DEF, "P", "S", 'P', OP.dustSmall.dat(MT.P                  ), 'S', OP.bolt.dat(ANY.Wood));
		CR.shaped(IL.Tool_Matches.get(1), CR.DEF, "P", "S", 'P', OP.dustSmall.dat(MT.Phosphorus         ), 'S', OP.bolt.dat(ANY.Wood));
		CR.shaped(IL.Tool_Matches.get(4), CR.DEF, " S ", "SPS", " S ", 'P', OP.dust.dat(MT.P            ), 'S', OP.bolt.dat(ANY.Wood));
		CR.shaped(IL.Tool_Matches.get(4), CR.DEF, " S ", "SPS", " S ", 'P', OP.dust.dat(MT.Phosphorus   ), 'S', OP.bolt.dat(ANY.Wood));
		IL.Tool_MatchBox_Used.set(              addItem(tLastID =  5002, "Match Box"                        , "This is not a Car"                           , TC.stack(TC.IGNIS, 2), TC.stack(TC.POTENTIA, 1), OD.craftingFirestarter, TD.Creative.HIDDEN));
		IL.Tool_MatchBox_Full.set(              addItem(tLastID =  5003, "Match Box (Full)"                 , "This is not a Car"                           , TC.stack(TC.IGNIS, 1), TC.stack(TC.POTENTIA, 2), OD.craftingFirestarter));
		tBehaviour = new Behavior_Lighter(null, IL.Tool_MatchBox_Used.get(1), IL.Tool_MatchBox_Full.get(1), 64, 9000);
		addItemBehavior(5002, tBehaviour); addItemBehavior(5003, tBehaviour);
		ItemsGT.addNEIRedirects(IL.Tool_Matches.get(1), IL.Tool_MatchBox_Used.get(1), IL.Tool_MatchBox_Full.get(1));
		RM.Boxinator.addRecipe2(T, 16, 64, IL.Tool_Matches.get(64), OP.plateDouble.mat(MT.Paper, 1), IL.Tool_MatchBox_Full.get(1));
		RM.Unboxinator.addRecipe1(T, 16, 32, IL.Tool_MatchBox_Full.get(1), IL.Tool_Matches.get(64), OP.scrapGt.mat(MT.Paper, 16));
		
		
		
		IL.Tool_Lighter_Invar_Empty.set(        addItem(tLastID =  5004, "Lighter (Empty)"                  , ""                                            , new OreDictItemData(MT.Invar, OP.plateCurved.mAmount * 2), TC.stack(TC.IGNIS, 1), TC.stack(TC.VACUOS, 1)));
		IL.Tool_Lighter_Invar_Used.set(         addItem(tLastID =  5005, "Lighter"                          , ""                                            , new OreDictItemData(MT.Invar, OP.plateCurved.mAmount * 2), TC.stack(TC.IGNIS, 2), TC.stack(TC.POTENTIA, 1), OD.craftingFirestarter, TD.Creative.HIDDEN));
		IL.Tool_Lighter_Invar_Full.set(         addItem(tLastID =  5006, "Lighter (Full)"                   , ""                                            , new OreDictItemData(MT.Invar, OP.plateCurved.mAmount * 2), TC.stack(TC.IGNIS, 1), TC.stack(TC.POTENTIA, 2), OD.craftingFirestarter));
		tBehaviour = new Behavior_Lighter(IL.Tool_Lighter_Invar_Empty.get(1), IL.Tool_Lighter_Invar_Used.get(1), IL.Tool_Lighter_Invar_Full.get(1), 100, 10000);
		addItemBehavior(5005, tBehaviour); addItemBehavior(5006, tBehaviour);
		RM.Canner.addRecipe1(T, 16, 16, IL.Tool_Lighter_Invar_Empty.get(1), FL.Butane .make(100), NF, IL.Tool_Lighter_Invar_Full.get(1));
		RM.Canner.addRecipe1(T, 16, 16, IL.Tool_Lighter_Invar_Empty.get(1), FL.Propane.make(100), NF, IL.Tool_Lighter_Invar_Full.get(1));
		ItemsGT.addNEIRedirects(IL.Tool_Lighter_Invar_Empty.get(1), IL.Tool_Lighter_Invar_Used.get(1), IL.Tool_Lighter_Invar_Full.get(1));
		
		
		
		IL.Tool_Lighter_Platinum_Empty.set(     addItem(tLastID =  5007, "Shiny Lighter (Empty)"            , "A known Master of Pranks is engraved on it"  , new OreDictItemData(MT.Pt, OP.plateCurved.mAmount * 2), TC.stack(TC.IGNIS, 1), TC.stack(TC.NEBRISUM, 1), TC.stack(TC.VACUOS, 1)));
		IL.Tool_Lighter_Platinum_Used.set(      addItem(tLastID =  5008, "Shiny Lighter"                    , "A known Master of Pranks is engraved on it"  , new OreDictItemData(MT.Pt, OP.plateCurved.mAmount * 2), TC.stack(TC.IGNIS, 2), TC.stack(TC.NEBRISUM, 1), TC.stack(TC.POTENTIA, 1), OD.craftingFirestarter, TD.Creative.HIDDEN));
		IL.Tool_Lighter_Platinum_Full.set(      addItem(tLastID =  5009, "Shiny Lighter (Full)"             , "A known Master of Pranks is engraved on it"  , new OreDictItemData(MT.Pt, OP.plateCurved.mAmount * 2), TC.stack(TC.IGNIS, 1), TC.stack(TC.NEBRISUM, 1), TC.stack(TC.POTENTIA, 2), OD.craftingFirestarter));
		tBehaviour = new Behavior_Lighter(IL.Tool_Lighter_Platinum_Empty.get(1), IL.Tool_Lighter_Platinum_Used.get(1), IL.Tool_Lighter_Platinum_Full.get(1), 1000, 10000);
		addItemBehavior(5008, tBehaviour); addItemBehavior(5009, tBehaviour);
		RM.Canner.addRecipe1(T, 16, 64, IL.Tool_Lighter_Platinum_Empty.get(1), FL.Butane .make(1000), NF, IL.Tool_Lighter_Platinum_Full.get(1));
		RM.Canner.addRecipe1(T, 16, 64, IL.Tool_Lighter_Platinum_Empty.get(1), FL.Propane.make(1000), NF, IL.Tool_Lighter_Platinum_Full.get(1));
		ItemsGT.addNEIRedirects(IL.Tool_Lighter_Platinum_Empty.get(1), IL.Tool_Lighter_Platinum_Used.get(1), IL.Tool_Lighter_Platinum_Full.get(1));
		
		
		
		IL.Tool_Lighter_Plastic_Empty.set(      addItem(tLastID =  5010, "BIC (Empty)"                      , ""                                            , new OreDictItemData(MT.Plastic, OP.plateCurved.mAmount * 2, ANY.Fe, OP.screw.mAmount), TC.stack(TC.IGNIS, 1), TC.stack(TC.VACUOS, 1)));
		IL.Tool_Lighter_Plastic_Used.set(       addItem(tLastID =  5011, "BIC"                              , ""                                            , new OreDictItemData(MT.Plastic, OP.plateCurved.mAmount * 2, ANY.Fe, OP.screw.mAmount), TC.stack(TC.IGNIS, 2), TC.stack(TC.POTENTIA, 1), OD.craftingFirestarter, TD.Creative.HIDDEN));
		IL.Tool_Lighter_Plastic_Full.set(       addItem(tLastID =  5012, "BIC (Full)"                       , ""                                            , new OreDictItemData(MT.Plastic, OP.plateCurved.mAmount * 2, ANY.Fe, OP.screw.mAmount), TC.stack(TC.IGNIS, 1), TC.stack(TC.POTENTIA, 2), OD.craftingFirestarter));
		IL.Tool_Lighter_Plastic_Broken.set(     addItem(tLastID =  5013, "BIC (Broken)"                     , ""                                            , new OreDictItemData(MT.Plastic, OP.plateCurved.mAmount * 2, ANY.Fe, OP.screw.mAmount), TC.stack(TC.IGNIS, 1), TC.stack(TC.PERDITIO, 1), TD.Creative.HIDDEN));
		tBehaviour = new Behavior_Lighter(IL.Tool_Lighter_Plastic_Broken.get(1), IL.Tool_Lighter_Plastic_Used.get(1), IL.Tool_Lighter_Plastic_Full.get(1), 100, 9000);
		addItemBehavior(5011, tBehaviour); addItemBehavior(5012, tBehaviour);
		CR.shaped(IL.Tool_Lighter_Plastic_Empty.get(1), CR.DEF_NCC, "IF", "dP", "xP", 'F', "itemFlint", 'P', OP.plateCurved.dat(MT.Plastic), 'I', OP.screw.dat(ANY.Iron));
		RM.Canner.addRecipe1(T, 16, 16, IL.Tool_Lighter_Plastic_Empty.get(1), FL.Butane .make(100), NF, IL.Tool_Lighter_Plastic_Full.get(1));
		RM.Canner.addRecipe1(T, 16, 16, IL.Tool_Lighter_Plastic_Empty.get(1), FL.Propane.make(100), NF, IL.Tool_Lighter_Plastic_Full.get(1));
		ItemsGT.addNEIRedirects(IL.Tool_Lighter_Plastic_Empty.get(1), IL.Tool_Lighter_Plastic_Used.get(1), IL.Tool_Lighter_Plastic_Full.get(1), IL.Tool_Lighter_Plastic_Broken.get(1));
		
		
		
		IL.Tool_Fire_Starter.set(               addItem(tLastID =  5014, "Fire Starter"                     , "(Made with Dry Grass)"                       , new OreDictItemData(MT.Wood, U), new Behavior_Lighter(5000), TC.stack(TC.IGNIS, 1), TC.stack(TC.ARBOR, 1), TC.stack(TC.HERBA, 1), OD.craftingFirestarter));
		CR.shaped(IL.Tool_Fire_Starter.get(1)       , CR.DEF_NAC_NCC_MIR, "S ", "GS", 'S', OP.stick.dat(ANY.Wood), 'G', OD.itemGrassDry);
		
		IL.Tool_Fire_Starter_Bark.set(          addItem(tLastID =  5015, "Fire Starter"                     , "(Made with Dry Tree Bark)"                   , new OreDictItemData(MT.Wood, U), new Behavior_Lighter(5500), TC.stack(TC.IGNIS, 1), TC.stack(TC.ARBOR, 2), OD.craftingFirestarter));
		CR.shaped(IL.Tool_Fire_Starter_Bark.get(1)  , CR.DEF_NAC_NCC_MIR, "S ", "GS", 'S', OP.stick.dat(ANY.Wood), 'G', OD.itemBarkDry);
		
		
		IL.Pellet_Wood.set(                     addItem(tLastID =  5999, "Wood Pellet"                      , ""                                            , new OreDictItemData(MT.Wood, U), TICKS_PER_SMELT, TC.stack(TC.POTENTIA, 1)));
		RM.Mixer.addRecipe1(T, 16, 16, OM.dust(MT.LiveRoot      , U2), FL.Glue.make(125), NF, IL.Pellet_Wood.get(1));
		RM.Mixer.addRecipe1(T, 16, 32, OM.dust(MT.LiveRoot          ), FL.Glue.make(250), NF, IL.Pellet_Wood.get(2));
		RM.Mixer.addRecipe1(T, 16, 16, OM.dust(MT.Livingwood    , U2), FL.Glue.make(125), NF, IL.Pellet_Wood.get(1));
		RM.Mixer.addRecipe1(T, 16, 32, OM.dust(MT.Livingwood        ), FL.Glue.make(250), NF, IL.Pellet_Wood.get(2));
		RM.Mixer.addRecipe1(T, 16, 16, OM.dust(MT.Greatwood     , U2), FL.Glue.make(125), NF, IL.Pellet_Wood.get(1));
		RM.Mixer.addRecipe1(T, 16, 32, OM.dust(MT.Greatwood         ), FL.Glue.make(250), NF, IL.Pellet_Wood.get(2));
		RM.Mixer.addRecipe1(T, 16, 16, OM.dust(MT.Dreamwood     , U4), FL.Glue.make(125), NF, IL.Pellet_Wood.get(1));
		RM.Mixer.addRecipe1(T, 16, 64, OM.dust(MT.Dreamwood         ), FL.Glue.make(500), NF, IL.Pellet_Wood.get(4));
		RM.Mixer.addRecipe1(T, 16, 16, OM.dust(MT.Shimmerwood   , U4), FL.Glue.make(125), NF, IL.Pellet_Wood.get(1));
		RM.Mixer.addRecipe1(T, 16, 64, OM.dust(MT.Shimmerwood       ), FL.Glue.make(500), NF, IL.Pellet_Wood.get(4));
		RM.Mixer.addRecipe1(T, 16, 16, OM.dust(MT.Silverwood    , U4), FL.Glue.make(125), NF, IL.Pellet_Wood.get(1));
		RM.Mixer.addRecipe1(T, 16, 64, OM.dust(MT.Silverwood        ), FL.Glue.make(500), NF, IL.Pellet_Wood.get(4));
		for (OreDictMaterial tWood : ANY.Wood.mToThis)
		RM.Mixer.addRecipe1(T, 16, 16, OM.dust(tWood), FL.Glue.make(125), NF, IL.Pellet_Wood.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, OM.dust(MT.Bark), FL.Glue.make(125), NF, IL.Pellet_Wood.get(1));
		RM.Compressor.addRecipe1(T, 16, 16, IL.Pellet_Wood.get(2), ST.make(BlocksGT.Planks, 1, 8));
		
		
		IL.Module_Stone_Generator.set(          addItem(tLastID =  6000, "Stone Generator Module"           , "Generates Stone for Recipes"                 , TC.stack(TC.MACHINA, 1), TC.stack(TC.FABRICO, 1), TC.stack(TC.IGNIS, 1), TC.stack(TC.TERRA, 1), TC.stack(TC.AQUA, 1)));
		CR.shaped(IL.Module_Stone_Generator.get(1), CR.DEF_REV_NCC, "CPC", "LMW", "COC", 'M', OP.casingMachine.dat(MT.SteelGalvanized), 'O', IL.Shape_Extruder_Block, 'C', OD_CIRCUITS[4], 'P', OD.craftingPiston, 'L', "container1000lava", 'W', "container1000water");
		
		
		IL.Paper_Printed_Pages.set(             addItem(tLastID =  7002, "Printed Pages"                    , ""                                                            , Behavior_PrintedPages.INSTANCE, new OreDictItemData(MT.Paper, U*3), TC.stack(TC.COGNITO, 3)));
		IL.Paper_Printed_Pages_Many.set(        addItem(tLastID =  7003, "Many Printed Pages"               , ""                                                            , Behavior_PrintedPages.INSTANCE, new OreDictItemData(MT.Paper, U*6), TC.stack(TC.COGNITO, 6)));
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Paper_Printed_Pages.get(1)), (byte)26);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Paper_Printed_Pages_Many.get(1)), (byte)26);
		
		IL.Paper_Blueprint_Empty.set(           addItem(tLastID =  7010, "Empty Blueprint"                  , "Place in Blueprint Slot and Shiftclick it, to assign Recipe" , new OreDictItemData(MT.Paper, U), TC.stack(TC.COGNITO, 1)));
		IL.Paper_Blueprint_Used.set(            addItem(tLastID =  7011, "Blueprint"                        , "Blueprint containing a Crafting Recipe"                      , new OreDictItemData(MT.Paper, U), TC.stack(TC.COGNITO, 2), "gt:autocrafterblueprintitem"));
		for (FluidStack tDye : DYE_FLUIDS[DYE_INDEX_Blue])
		RM.Bath.addRecipe1(T, 0, 16, ST.make(Items.paper, 1, W), tDye, NF, IL.Paper_Blueprint_Empty.get(1));
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Paper_Blueprint_Empty.get(1)), (byte)25);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Paper_Blueprint_Used.get(1)), (byte)28);
		ItemsGT.addNEIRedirects(IL.Paper_Blueprint_Empty.get(1), IL.Paper_Blueprint_Used.get(1));
		
		
		for (int i = 0; i < 16; i++)            addItem(i      +  7030, DYE_NAMES[i] + " Canvas"            , "Can be used together with the Obscurator"    , "gt:canvas", new CoverTextureCanvas(BlockTextureDefault.get("machines/covers/canvas", DYES[i])), new OreDictItemData(MT.Paper, U), TC.stack(TC.PANNUS, 1));
		for (FluidStack tDye : DYE_FLUIDS[DYE_INDEX_White])
		RM.Bath.addRecipe1(T, 0, 16, ST.make(Items.paper, 1, W), tDye, NF, ST.make(this, 1, 7030+DYE_INDEX_White));
		for (int i = 0; i < 16; i++) if (i != DYE_INDEX_White) for (FluidStack tDye : DYE_FLUIDS[i])
		RM.Bath.addRecipe1(T, 0, 16, ST.make(this, 1, 7030+DYE_INDEX_White), tDye, NF, ST.make(this, 1, 7030+i));
		for (int i = 0; i < 16; i++)
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(ST.make(this, 1, 7030+i)), (byte)27);
		
		IL.Robot_Tip_Wrench.set(                addItem(tLastID =  8000, "Robot Arm Wrench Tip"         , "Infinitely usable inside an Autocrafter", OreDictToolNames.wrench                                , "gt:autocrafterinfinite", TC.stack(TC.INSTRUMENTUM, 4)));
		IL.Robot_Tip_Screwdriver.set(           addItem(tLastID =  8001, "Robot Arm Screwdriver Tip"    , "Infinitely usable inside an Autocrafter", OreDictToolNames.screwdriver                           , "gt:autocrafterinfinite", TC.stack(TC.INSTRUMENTUM, 4)));
		IL.Robot_Tip_Saw.set(                   addItem(tLastID =  8002, "Robot Arm Saw Tip"            , "Infinitely usable inside an Autocrafter", OreDictToolNames.saw                                   , "gt:autocrafterinfinite", TC.stack(TC.INSTRUMENTUM, 4)));
		IL.Robot_Tip_Hammer.set(                addItem(tLastID =  8003, "Robot Arm Hammer Tip"         , "Infinitely usable inside an Autocrafter", OreDictToolNames.hammer                                , "gt:autocrafterinfinite", TC.stack(TC.INSTRUMENTUM, 4)));
		IL.Robot_Tip_Cutter.set(                addItem(tLastID =  8004, "Robot Arm Cutter Tip"         , "Infinitely usable inside an Autocrafter", OreDictToolNames.wirecutter, OreDictToolNames.scissors , "gt:autocrafterinfinite", TC.stack(TC.INSTRUMENTUM, 4)));
		IL.Robot_Tip_Chisel.set(                addItem(tLastID =  8005, "Robot Arm Chisel Tip"         , "Infinitely usable inside an Autocrafter", OreDictToolNames.chisel                                , "gt:autocrafterinfinite", TC.stack(TC.INSTRUMENTUM, 4)));
		IL.Robot_Tip_Rubber.set(                addItem(tLastID =  8006, "Robot Arm Rubber Hammer Tip"  , "Infinitely usable inside an Autocrafter", OreDictToolNames.softhammer                            , "gt:autocrafterinfinite", TC.stack(TC.INSTRUMENTUM, 4)));
		IL.Robot_Tip_Blade.set(                 addItem(tLastID =  8007, "Robot Arm Blade Tip"          , "Infinitely usable inside an Autocrafter", OreDictToolNames.sword, OreDictToolNames.blade         , "gt:autocrafterinfinite", TC.stack(TC.INSTRUMENTUM, 4)));
		IL.Robot_Tip_Drill.set(                 addItem(tLastID =  8008, "Robot Arm Drill Tip"          , "Infinitely usable inside an Autocrafter", OreDictToolNames.drill                                 , "gt:autocrafterinfinite", TC.stack(TC.INSTRUMENTUM, 4)));
		IL.Robot_Tip_File.set(                  addItem(tLastID =  8009, "Robot Arm File Tip"           , "Infinitely usable inside an Autocrafter", OreDictToolNames.file                                  , "gt:autocrafterinfinite", TC.stack(TC.INSTRUMENTUM, 4)));
		
		CR.shaped(IL.Robot_Tip_Wrench       .get(1), CR.DEF_REV, "wPh", "CMC", " X ", 'P', OP.plateCurved.dat(MT.SteelGalvanized), 'C', OD_CIRCUITS[3], 'M', IL.MOTORS      [3], 'X', OP.toolHeadWrench.dat(MT.Cr));
		CR.shaped(IL.Robot_Tip_Screwdriver  .get(1), CR.DEF_REV, "wPh", "CMC", " X ", 'P', OP.plateCurved.dat(MT.SteelGalvanized), 'C', OD_CIRCUITS[3], 'M', IL.MOTORS      [3], 'X', OP.toolHeadScrewdriver.dat(MT.StainlessSteel));
		CR.shaped(IL.Robot_Tip_Saw          .get(1), CR.DEF_REV, "wPh", "CMC", "DXd", 'P', OP.plateCurved.dat(MT.SteelGalvanized), 'C', OD_CIRCUITS[3], 'M', IL.MOTORS      [3], 'X', OP.toolHeadBuzzSaw.dat(MT.CobaltBrass), 'D', OP.dust.dat(ANY.Diamond));
		CR.shaped(IL.Robot_Tip_Hammer       .get(1), CR.DEF_REV, "wPh", "CMC", " X ", 'P', OP.plateCurved.dat(MT.SteelGalvanized), 'C', OD_CIRCUITS[3], 'M', IL.PISTONS     [3], 'X', OP.toolHeadHammer.dat(MT.TungstenCarbide));
		CR.shaped(IL.Robot_Tip_Cutter       .get(1), CR.DEF_REV, "wPh", "CMC", "XfX", 'P', OP.plateCurved.dat(MT.SteelGalvanized), 'C', OD_CIRCUITS[3], 'M', IL.MOTORS      [3], 'X', OP.plate.dat(MT.StainlessSteel));
		CR.shaped(IL.Robot_Tip_Chisel       .get(1), CR.DEF_REV, "wPh", "CMC", " X ", 'P', OP.plateCurved.dat(MT.SteelGalvanized), 'C', OD_CIRCUITS[3], 'M', IL.PISTONS     [3], 'X', OP.toolHeadChisel.dat(MT.TungstenSteel));
		CR.shaped(IL.Robot_Tip_Rubber       .get(1), CR.DEF_REV, "wPh", "CMC", " X ", 'P', OP.plateCurved.dat(MT.SteelGalvanized), 'C', OD_CIRCUITS[3], 'M', IL.PISTONS     [3], 'X', OP.toolHeadHammer.dat(MT.Rubber));
		CR.shaped(IL.Robot_Tip_Blade        .get(1), CR.DEF_REV, "wPh", "CMC", " X ", 'P', OP.plateCurved.dat(MT.SteelGalvanized), 'C', OD_CIRCUITS[3], 'M', IL.PISTONS     [3], 'X', OP.toolHeadSword.dat(MT.Bronze));
		CR.shaped(IL.Robot_Tip_Drill        .get(1), CR.DEF_REV, "wPh", "CMC", "fX ", 'P', OP.plateCurved.dat(MT.SteelGalvanized), 'C', OD_CIRCUITS[3], 'M', IL.MOTORS      [3], 'X', OP.stick.dat(ANY.Steel));
		CR.shaped(IL.Robot_Tip_File         .get(1), CR.DEF_REV, "wPh", "CMC", " X ", 'P', OP.plateCurved.dat(MT.SteelGalvanized), 'C', OD_CIRCUITS[3], 'M', IL.CONVEYERS   [3], 'X', OP.dust.dat(ANY.Diamond));
		
		IL.Tool_Remote_Activator.set(           addItem(tLastID =  9000, "Remote Activator"         , "", Behavior_Remote.INSTANCE, TC.stack(TC.MOTUS, 1), TC.stack(TC.PERMUTATIO, 1)));
		IL.Tool_Cheat.set(                      addItem(tLastID =  9001, "Debug Scanner"            , "", Behavior_Cropnalyzer.INSTANCE, ItemsGT.DEBUG_ITEMS, ItemsGT.ILLEGAL_DROPS, GarbageGT.BLACKLIST, new Behavior_Scanner(Integer.MAX_VALUE), EnergyStatDebug.INSTANCE, TC.stack(TC.SENSUS,10), TC.stack(TC.INSTRUMENTUM,10)));
		IL.Tool_Scanner.set(                    addItem(tLastID =  9002, "Portable Scanner"         , "", Behavior_Cropnalyzer.INSTANCE, new Behavior_Scanner(2), EnergyStat.makeTool(TD.Energy.EU, V[3]*8000, V[3], 2, ST.make(this, 1, tLastID)), TC.stack(TC.SENSUS,10), TC.stack(TC.INSTRUMENTUM,10)));
		IL.Tool_Cropnalyzer.set(                addItem(tLastID =  9003, "Portable Cropnalyzer"     , "", Behavior_Cropnalyzer.INSTANCE, EnergyStat.makeTool(TD.Energy.EU, V[2]*8000, V[2], 2, ST.make(this, 1, tLastID)), TC.stack(TC.SENSUS, 5), TC.stack(TC.INSTRUMENTUM, 5), TC.stack(TC.HERBA, 5)));
		
		IL.Tool_Multiblock_Builder.set(       addItem(tLastID =  9004, "Multiblock Builder"         , "", new Behavior_MultiblockBuilder(2), EnergyStat.makeTool(TD.Energy.EU, V[3]*8000, V[3], 2, ST.make(this, 1, tLastID)), TC.stack(TC.SENSUS,10), TC.stack(TC.INSTRUMENTUM,10)));

		IL.Tool_Worldgen_Debugger.set(          addItem(tLastID =  9999, "Worldgen Debug Wand"      , "", Behavior_Worldgen_Debugger.INSTANCE, ItemsGT.DEBUG_ITEMS, ItemsGT.ILLEGAL_DROPS, GarbageGT.BLACKLIST, TC.stack(TC.TERRA,10), TC.stack(TC.PRAECANTIO,10), TC.stack(TC.INSTRUMENTUM,10)));
		
		CR.shaped(IL.Tool_Remote_Activator      .get(1), CR.DEF_REV, "TPE", "BCd", "xPT", 'P', OP.plate.dat(MT.Cr), 'T', OP.screw.dat(MT.Cr), 'C', OD_CIRCUITS[4], 'E', IL.EMITTERS[4], 'B', ST.make(Blocks.stone_button, 1, W));
//      Moved to the Battery Section of the MTE Loader.
//      CR.shaped(IL.Tool_Scanner               .get(1), CR.DEF_REV, "EXR", "CPU", "BXB", 'B', IL.Battery_Alkaline_HV, 'X', OP.plate.dat(MT.Cr), 'U', OD_USB_STICKS[3], 'C', OD_USB_CABLES[3], 'E', IL.EMITTERS[4], 'R', IL.SENSORS[4], 'P', IL.Processor_Crystal_Sapphire);
//      CR.shaped(IL.Tool_Cropnalyzer           .get(1), CR.DEF_REV, "EXR", "CPU", "BXB", 'B', IL.Battery_Alkaline_MV, 'X', OP.plate.dat(MT.Al), 'U', OD_USB_STICKS[1], 'C', OD_USB_CABLES[1], 'E', IL.EMITTERS[2], 'R', IL.SENSORS[2], 'P', OD_CIRCUITS[6]);
		
		IL.Thermometer_Quicksilver.set(         addItem(tLastID = 10000, "Quicksilver Thermometer"  , "", new Behavior_Tool(TOOL_thermometer  , null, 0, T), TC.stack(TC.VENENUM, 1), TC.stack(TC.VITREUS, 1)));
		IL.Geiger_Counter_Empty.set(            addItem(tLastID = 10001, "Geiger Counter (Empty)"   , "Fill with proper inert Gas"                         , TC.stack(TC.SENSUS, 1), TC.stack(TC.RADIO, 1)));
		IL.Geiger_Counter.set(                  addItem(tLastID = 10002, "Geiger Counter"           , "", new Behavior_Tool(TOOL_geigercounter, null, 0, T), TC.stack(TC.SENSUS, 5), TC.stack(TC.RADIO, 5)));
		
		RM.Canner.addRecipe1(T, 16, 64, IL.Geiger_Counter_Empty.get(1), FL.Helium.make(1000), NF, IL.Geiger_Counter.get(1));
		RM.Canner.addRecipe1(T, 16, 64, IL.Geiger_Counter_Empty.get(1), FL.Neon  .make(1000), NF, IL.Geiger_Counter.get(1));
		RM.Canner.addRecipe1(T, 16, 64, IL.Geiger_Counter_Empty.get(1), FL.Argon .make(1000), NF, IL.Geiger_Counter.get(1));
		
		CR.shaped(IL.Thermometer_Quicksilver    .get(1), CR.DEF_REV, " GD", "GQG", "PG ", 'P', OP.plate.dat(ANY.Cu), 'G', ST.make(Blocks.glass, 1, W), 'D', DYE_OREDICTS[DYE_INDEX_Red], 'Q', OP.bottle.dat(MT.Hg));
		CR.shaped(IL.Geiger_Counter_Empty       .get(1), CR.DEF_REV, "TUT", "PCP", "TdT", 'U', OP.capcellcon.dat(MT.Al), 'P', OP.plate.dat(MT.Al), 'T', OP.screw.dat(MT.Al), 'C', OD_CIRCUITS[1]);
		OM.data(IL.Geiger_Counter.get(1), OM.data(IL.Geiger_Counter_Empty.get(1)));
		
		
		IL.Compass_North.set(                   addItem(tLastID = 11000, "Compass (NORTH)"      , "Sneak Rightclick to switch Mode", OD.itemCompass, new Behavior_Switch_Metadata(11001), OM.data(ST.make(Items.compass, 1, 0)), TC.stack(TC.METALLUM, 2), TC.stack(TC.MAGNETO                                             , 2))); CR.shapeless(ST.make(Items.compass, 1, 0), CR.DEF, new Object[] {ST.make(this, 1, tLastID)});
		IL.Compass_Face.set(                    addItem(tLastID = 11001, "Compass (FACE)"       , "Sneak Rightclick to switch Mode", OD.itemCompass, new Behavior_Switch_Metadata(11002), OM.data(ST.make(Items.compass, 1, 0)), TD.Creative.HIDDEN, TC.stack(TC.METALLUM, 2), TC.stack(TC.MAGNETO, 1), TC.stack(TC.COGNITO, 1))); CR.shapeless(ST.make(Items.compass, 1, 0), CR.DEF, new Object[] {ST.make(this, 1, tLastID)});
		IL.Compass_Spawn.set(                   addItem(tLastID = 11002, "Compass (SPAWN)"      , "Sneak Rightclick to switch Mode", OD.itemCompass, new Behavior_Switch_Metadata(11003), OM.data(ST.make(Items.compass, 1, 0)), TD.Creative.HIDDEN, TC.stack(TC.METALLUM, 2), TC.stack(TC.MAGNETO, 1), TC.stack(TC.HUMANUS, 1))); CR.shapeless(ST.make(Items.compass, 1, 0), CR.DEF, new Object[] {ST.make(this, 1, tLastID)});
		IL.Compass_Center.set(                  addItem(tLastID = 11003, "Compass (CENTER)"     , "Sneak Rightclick to switch Mode", OD.itemCompass, new Behavior_Switch_Metadata(11004), OM.data(ST.make(Items.compass, 1, 0)), TD.Creative.HIDDEN, TC.stack(TC.METALLUM, 2), TC.stack(TC.MAGNETO, 1), TC.stack(TC.TERRA  , 1))); CR.shapeless(ST.make(Items.compass, 1, 0), CR.DEF, new Object[] {ST.make(this, 1, tLastID)});
		IL.Compass_Death.set(                   addItem(tLastID = 11004, "Compass (DEATH)"      , "Sneak Rightclick to switch Mode", OD.itemCompass, new Behavior_Switch_Metadata(11000), OM.data(ST.make(Items.compass, 1, 0)), TD.Creative.HIDDEN, TC.stack(TC.METALLUM, 2), TC.stack(TC.MAGNETO, 1), TC.stack(TC.MORTUUS, 1))); CR.shapeless(ST.make(Items.compass, 1, 0), CR.DEF, new Object[] {ST.make(this, 1, tLastID)});
		ItemsGT.addNEIRedirects(IL.Compass_North.get(1), IL.Compass_Face.get(1), IL.Compass_Spawn.get(1), IL.Compass_Center.get(1), IL.Compass_Death.get(1));
		CR.shapeless(IL.Compass_North.get(1), CR.DEF, new Object[] {ST.make(Items.compass, 1, W)});
		
		
		IL.Tape.set(                            addItem(tLastID = 12000, "Tape", "Full Roll", TC.stack(TC.PANNUS, 1), TC.stack(TC.LIMUS, 1)));
		IL.Tape_Used.set(                       addItem(tLastID = 12001, "Tape", "Used Roll", TC.stack(TC.PANNUS, 1), TC.stack(TC.LIMUS, 1)));
		CR.shaped(IL.Tape.get(1), CR.DEF, "PPP", " G ", 'P', OD.paperEmpty, 'G', OD.itemGlue);
		tBehaviour = new Behavior_Duct_Tape(null, IL.Tape_Used.get(1), IL.Tape.get(1), 0, 10000);
		addItemBehavior(12000, tBehaviour);
		addItemBehavior(12001, tBehaviour);
		
		IL.Duct_Tape.set(                       addItem(tLastID = 12002, "Duct Tape", "Full Roll", TC.stack(TC.FABRICO, 1), TC.stack(TC.LIMUS, 1), OD.craftingDuctTape));
		IL.Duct_Tape_Used.set(                  addItem(tLastID = 12003, "Duct Tape", "Used Roll", TC.stack(TC.FABRICO, 1), TC.stack(TC.LIMUS, 1)));
		CR.shaped(IL.Duct_Tape.get(1), CR.DEF, "PPP", " G ", 'P', OP.foil.dat(MT.Plastic), 'G', OD.itemGlue);
		tBehaviour = new Behavior_Duct_Tape(null, IL.Duct_Tape_Used.get(1), IL.Duct_Tape.get(1), 1, 100000);
		addItemBehavior(12002, tBehaviour);
		addItemBehavior(12003, tBehaviour);
		
		IL.Brain_Tape.set(                      addItem(tLastID = 12008, "BrainTech Aerospace Advanced Reinforced Duct Tape FAL-84", "Full Roll", TC.stack(TC.TUTAMEN, 1), TC.stack(TC.LIMUS, 1), OD.craftingDuctTape));
		IL.Brain_Tape_Used.set(                 addItem(tLastID = 12009, "BrainTech Aerospace Advanced Reinforced Duct Tape FAL-84", "Used Roll", TC.stack(TC.TUTAMEN, 1), TC.stack(TC.LIMUS, 1), OD.craftingDuctTape));
		CR.shaped(IL.Brain_Tape.get(1), CR.DEF, "PPP", " G ", 'P', OP.foil.dat(ANY.W), 'G', OD.itemGlue);
		tBehaviour = new Behavior_Duct_Tape(null, IL.Brain_Tape_Used.get(1), IL.Brain_Tape.get(1), 2, 10000000);
		addItemBehavior(12008, tBehaviour);
		addItemBehavior(12009, tBehaviour);
		
		
		
		IL.Key_Iron.set(                        addItem(tLastID = 30000, "Iron Key"    , "", OD.itemKey, new OreDictItemData(ANY.Iron  , U4), Behavior_Key.INSTANCE, TC.stack(TC.MACHINA, 1))); CR.shaped(IL.Key_Iron    .get(3), CR.DEF_NCC, "fPx", 'P', OP.plate.dat(ANY.Iron));
		IL.Key_Gold.set(                        addItem(tLastID = 30001, "Gold Key"    , "", OD.itemKey, new OreDictItemData(MT.Au     , U4), Behavior_Key.INSTANCE, TC.stack(TC.MACHINA, 1))); CR.shaped(IL.Key_Gold    .get(3), CR.DEF_NCC, "fPx", 'P', OP.plate.dat(MT.Au));
		IL.Key_Copper.set(                      addItem(tLastID = 30002, "Copper Key"  , "", OD.itemKey, new OreDictItemData(ANY.Cu    , U4), Behavior_Key.INSTANCE, TC.stack(TC.MACHINA, 1))); CR.shaped(IL.Key_Copper  .get(3), CR.DEF_NCC, "fPx", 'P', OP.plate.dat(ANY.Cu));
		IL.Key_Tin.set(                         addItem(tLastID = 30003, "Tin Key"     , "", OD.itemKey, new OreDictItemData(MT.Sn     , U4), Behavior_Key.INSTANCE, TC.stack(TC.MACHINA, 1))); CR.shaped(IL.Key_Tin     .get(3), CR.DEF_NCC, "fPx", 'P', OP.plate.dat(MT.Sn));
		IL.Key_Bronze.set(                      addItem(tLastID = 30004, "Bronze Key"  , "", OD.itemKey, new OreDictItemData(MT.Bronze , U4), Behavior_Key.INSTANCE, TC.stack(TC.MACHINA, 1))); CR.shaped(IL.Key_Bronze  .get(3), CR.DEF_NCC, "fPx", 'P', OP.plate.dat(MT.Bronze));
		IL.Key_Brass.set(                       addItem(tLastID = 30005, "Brass Key"   , "", OD.itemKey, new OreDictItemData(MT.Brass  , U4), Behavior_Key.INSTANCE, TC.stack(TC.MACHINA, 1))); CR.shaped(IL.Key_Brass   .get(3), CR.DEF_NCC, "fPx", 'P', OP.plate.dat(MT.Brass));
		IL.Key_Silver.set(                      addItem(tLastID = 30006, "Silver Key"  , "", OD.itemKey, new OreDictItemData(MT.Ag     , U4), Behavior_Key.INSTANCE, TC.stack(TC.MACHINA, 1))); CR.shaped(IL.Key_Silver  .get(3), CR.DEF_NCC, "fPx", 'P', OP.plate.dat(MT.Ag));
		IL.Key_Platinum.set(                    addItem(tLastID = 30007, "Platinum Key", "", OD.itemKey, new OreDictItemData(MT.Pt     , U4), Behavior_Key.INSTANCE, TC.stack(TC.MACHINA, 1))); CR.shaped(IL.Key_Platinum.get(3), CR.DEF_NCC, "fPx", 'P', OP.plate.dat(MT.Pt));
		IL.Key_Lead.set(                        addItem(tLastID = 30008, "Lead Key"    , "", OD.itemKey, new OreDictItemData(MT.Pb     , U4), Behavior_Key.INSTANCE, TC.stack(TC.MACHINA, 1))); CR.shaped(IL.Key_Lead    .get(3), CR.DEF_NCC, "fPx", 'P', OP.plate.dat(MT.Pb));
		IL.Key_Plastic.set(                     addItem(tLastID = 30009, "Plastic Key" , "", OD.itemKey, new OreDictItemData(MT.Plastic, U4), Behavior_Key.INSTANCE, TC.stack(TC.MACHINA, 1))); CR.shaped(IL.Key_Plastic .get(3), CR.DEF_NCC, "fPx", 'P', OP.plate.dat(MT.Plastic));
		
		// In order to be able to hide Keys inside Bookshelves
		for (IItemContainer tContainer : IL.KEYS) BooksGT.BOOK_REGISTER.put(new ItemStackContainer(tContainer.get(1)), (byte)1);
	}
	
	@Override public int getRenderPasses(int aMetaData) {return UT.Code.inside(11000, 11004, aMetaData) ? 2 : 1;}
	@Override public boolean requiresMultipleRenderPasses() {return T;}
	
	@Override
	public IIcon getIcon(ItemStack aStack, int aRenderPass) {
		if (aRenderPass == 0) return getIconIndex(aStack);
		EntityPlayer aPlayer = GT_API.api_proxy.getThePlayer();
		if (aPlayer == null) return getIconIndex(aStack);
		ChunkCoordinates aTarget;
		switch(ST.meta_(aStack)) {
		case 11000: return Textures.ItemIcons.COMPASS[UT.Code.roundDown(0.5+Textures.ItemIcons.COMPASS.length*(361260+aPlayer.rotationYaw)/360)%Textures.ItemIcons.COMPASS.length].getIcon(0);
		case 11001: return Textures.ItemIcons.COMPASS[UT.Code.roundDown(0.5+Textures.ItemIcons.COMPASS.length*(361260-aPlayer.rotationYaw)/360)%Textures.ItemIcons.COMPASS.length].getIcon(0);
		case 11003: return Textures.ItemIcons.COMPASS[UT.Code.roundDown(0.5+Textures.ItemIcons.COMPASS.length*(361170+aPlayer.rotationYaw-Math.atan2(-aPlayer.posZ, -aPlayer.posX)*180/Math.PI)/360)%Textures.ItemIcons.COMPASS.length].getIcon(0);
		case 11002: aTarget = aPlayer.worldObj.getSpawnPoint(); break;
		case 11004: aTarget = LAST_DEATH_OF_THE_PLAYER; break;
		default: return getIconIndex(aStack);
		}
		return aTarget == null ? Textures.ItemIcons.COMPASS[(int)(CLIENT_TIME % Textures.ItemIcons.COMPASS.length)].getIcon(0) : Textures.ItemIcons.COMPASS[UT.Code.roundDown(0.5+Textures.ItemIcons.COMPASS.length*(361170+aPlayer.rotationYaw-Math.atan2(aTarget.posZ+0.5-aPlayer.posZ, aTarget.posX+0.5-aPlayer.posX)*180/Math.PI)/360)%Textures.ItemIcons.COMPASS.length].getIcon(0);
	}
	
	@Override
	public IIcon getIconFromDamageForRenderPass(int aMetaData, int aRenderPass) {
		if (aRenderPass == 0) return getIconFromDamage(aMetaData);
		EntityPlayer aPlayer = GT_API.api_proxy.getThePlayer();
		if (aPlayer == null) return getIconFromDamage(aMetaData);
		ChunkCoordinates aTarget;
		switch(aMetaData) {
		case 11000: return Textures.ItemIcons.COMPASS[UT.Code.roundDown(0.5+Textures.ItemIcons.COMPASS.length*(361260+aPlayer.rotationYaw)/360)%Textures.ItemIcons.COMPASS.length].getIcon(0);
		case 11001: return Textures.ItemIcons.COMPASS[UT.Code.roundDown(0.5+Textures.ItemIcons.COMPASS.length*(361260-aPlayer.rotationYaw)/360)%Textures.ItemIcons.COMPASS.length].getIcon(0);
		case 11003: return Textures.ItemIcons.COMPASS[UT.Code.roundDown(0.5+Textures.ItemIcons.COMPASS.length*(361170+aPlayer.rotationYaw-Math.atan2(-aPlayer.posZ, -aPlayer.posX)*180/Math.PI)/360)%Textures.ItemIcons.COMPASS.length].getIcon(0);
		case 11002: aTarget = aPlayer.worldObj.getSpawnPoint(); break;
		case 11004: aTarget = LAST_DEATH_OF_THE_PLAYER; break;
		default: return getIconFromDamage(aMetaData);
		}
		return aTarget == null ? Textures.ItemIcons.COMPASS[(int)(CLIENT_TIME % Textures.ItemIcons.COMPASS.length)].getIcon(0) : Textures.ItemIcons.COMPASS[UT.Code.roundDown(0.5+Textures.ItemIcons.COMPASS.length*(361170+aPlayer.rotationYaw-Math.atan2(aTarget.posZ+0.5-aPlayer.posZ, aTarget.posX+0.5-aPlayer.posX)*180/Math.PI)/360)%Textures.ItemIcons.COMPASS.length].getIcon(0);
	}
	
	@Override
	public ItemStack getContainerItem(ItemStack aStack) {
		int aMeta = ST.meta_(aStack);
		if (aMeta >=  1000 && aMeta <=  1999) return ST.make(this, 1,   999);
		if (aMeta >   2000 && aMeta <=  2099) return ST.make(this, 1,  2000);
		if (aMeta >   2100 && aMeta <=  2199) return ST.make(this, 1,  2100);
		if (aMeta >   2200 && aMeta <=  2299) return ST.make(this, 1,  2200);
		if (aMeta >   2300 && aMeta <=  2399) return ST.make(this, 1,  2300);
		if (aMeta >   2400 && aMeta <=  2499) return ST.make(this, 1,  2400);
		if (aMeta >   2500 && aMeta <=  2599) return ST.make(this, 1,  2500);
		if (aMeta >   2600 && aMeta <=  2699) return ST.make(this, 1,  2600);
		if (aMeta >   2700 && aMeta <=  2799) return ST.make(this, 1,  2700);
		if (aMeta >   2800 && aMeta <=  2899) return ST.make(this, 1,  2800);
		if (aMeta >   2900 && aMeta <=  2999) return ST.make(this, 1,  2900);
		if (aMeta ==  5005 || aMeta ==  5008) return ST.make(this, 1, aMeta - 1);
		if (aMeta ==  5006 || aMeta ==  5009) return ST.make(this, 1, aMeta - 2);
		if (aMeta ==  5011) return ST.make(this, 1,  5013);
		if (aMeta ==  5012) return ST.make(this, 1,  5010);
//      if (aMeta == 32401) return ST.make(this, 1, aMeta - 1);
		return super.getContainerItem(aStack);
	}
	
	@Override
	public int getDefaultStackLimit(ItemStack aStack) {
		int aMeta = ST.meta_(aStack);
		switch (aMeta) {
		case 5002: case 5005: case 5008: case 5011: case 5014: case 5015: case 9001: case 9002: return 1;
		default: return (aMeta >= 2000 && aMeta < 3000) ? 1 : 64;
		}
	}
	
	@Override
	public ItemStack getRotten(ItemStack aStack) {
		switch(ST.meta_(aStack)) {
		case 2002: return ST.make(this, aStack.stackSize, 2011, aStack.getTagCompound());
		case 2102: return ST.make(this, aStack.stackSize, 2111, aStack.getTagCompound());
		case 2202: return ST.make(this, aStack.stackSize, 2211, aStack.getTagCompound());
		case 2302: return ST.make(this, aStack.stackSize, 2311, aStack.getTagCompound());
		case 2402: return ST.make(this, aStack.stackSize, 2411, aStack.getTagCompound());
		case 2502: return ST.make(this, aStack.stackSize, 2511, aStack.getTagCompound());
		case 2602: return ST.make(this, aStack.stackSize, 2611, aStack.getTagCompound());
		case 2702: return ST.make(this, aStack.stackSize, 2711, aStack.getTagCompound());
		case 2802: return ST.make(this, aStack.stackSize, 2811, aStack.getTagCompound());
		case 2902: return ST.make(this, aStack.stackSize, 2911, aStack.getTagCompound());
		}
		return aStack;
	}
	
	@Override public ItemStack getRotten(ItemStack aStack, World aWorld, int aX, int aY, int aZ) {return getRotten(aStack);}
}
