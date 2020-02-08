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

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import gregapi6.api.Abstract_Mod;
import gregapi6.code.ModData;
import gregapi6.compat.CompatMods;
import gregapi6.data.CS.BlocksGT;
import gregapi6.data.FL;
import gregapi6.data.IL;
import gregapi6.data.MD;
import gregapi6.data.MT;
import gregapi6.data.OP;
import gregapi6.data.RM;
import gregapi6.oredict.event.IOreDictListenerEvent;
import gregapi6.oredict.event.OreDictListenerEvent_Names;
import gregapi6.util.CR;
import gregapi6.util.OM;
import gregapi6.util.ST;
import net.minecraft.init.Items;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
import net.minecraftforge.fluids.FluidStack;

public class Compat_Recipes_Erebus extends CompatMods {
	public Compat_Recipes_Erebus(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {OUT.println("GT_Mod: Doing Erebus Recipes.");
		RM.add_smelting(ST.make(BlocksGT.Diggables, 1, 0), IL.ERE_Mud_Brick.get(1));
		
		RM.generify(IL.BoP_Mud_Brick.get(1), IL.ERE_Mud_Brick.get(1));
		RM.generify(IL.ERE_Mud_Brick.get(1), IL.BoP_Mud_Brick.get(1));
		
		RM.Mortar       .addRecipe1(T, 16, 16       , ST.make(MD.ERE, "fireBloom"           , 1, W), OM.dust(MT.Blaze, U9));
		RM.Mortar       .addRecipe1(T, 16, 16       , ST.make(MD.ERE, "materials"           , 1,56), OM.dust(MT.Jade, U9));
		RM.Mortar       .addRecipe1(T, 16, 16       , ST.make(MD.ERE, "materials"           , 1, 0), IL.Dye_Bonemeal.get(1));
		RM.Mortar       .addRecipe1(T, 16, 16       , ST.make(MD.ERE, "materials"           , 1, 2), IL.Dye_Bonemeal.get(1));
		RM.Mortar       .addRecipe1(T, 16,128       , ST.make(MD.ERE, "materials"           , 1,16), IL.Dye_Bonemeal.get(8));
		
		RM.Crusher      .addRecipe1(T, 16, 64       , ST.make(MD.ERE, "oreEncrustedDiamond" , 1, 0), ST.make(MD.ERE, "encrustedDiamond", 2*MT.Diamond.mOreMultiplier*MT.Diamond.mOreProcessingMultiplier, 0), OM.dust(MT.VolcanicAsh));
		RM.Hammer       .addRecipe1(T, 16, 16,  7500, ST.make(MD.ERE, "oreEncrustedDiamond" , 1, 0), ST.make(MD.ERE, "encrustedDiamond", 2*MT.Diamond.mOreMultiplier*MT.Diamond.mOreProcessingMultiplier, 0));
		
		RM.Crusher      .addRecipe1(T, 16, 64       , ST.make(MD.ERE, "umberstone"          , 1, 1), OP.rockGt.mat(MT.Umber, 4));
		RM.Hammer       .addRecipe1(T, 16, 16,  7000, ST.make(MD.ERE, "umberstone"          , 1, 1), OP.rockGt.mat(MT.Umber, 4));
		
		RM.Crusher      .addRecipe1(T, 16, 64       , ST.make(MD.ERE, "volcanicRock"        , 1, 0), OM.dust(MT.VolcanicAsh));
		RM.Hammer       .addRecipe1(T, 16, 16       , ST.make(MD.ERE, "volcanicRock"        , 1, 0), OM.dust(MT.VolcanicAsh));
		RM.Shredder     .addRecipe1(T, 16, 96       , ST.make(MD.ERE, "volcanicRock"        , 1, 0), OM.dust(MT.VolcanicAsh));
		
		RM.Sharpening   .addRecipe1(T, 16, 16       , ST.make(MD.ERE, "encrustedDiamond"    , 1, W), ST.make(Items.diamond, 1, 0), OM.dust(MT.VolcanicAsh, U2));
		
		RM.pack(rockGt.mat(MT.Umber     , 4), ST.make(MD.ERE, "umberstone", 1, 1));
		RM.pack(rockGt.mat(MT.Gneiss    , 4), ST.make(MD.ERE, "gneiss", 1, 0));
		
		RM.pack(ST.make(Items.string, 9, 0), 9, ST.make(MD.ERE, "blockSilk", 1, 0));
		RM.unpack(ST.make(MD.ERE, "blockSilk", 1, 0), ST.make(Items.string, 9, 0));
		
		CR.shaped(ST.make(MD.ERE, "umberstone"  , 1, 1), CR.DEF_NAC, "XX", "XX", 'X', rockGt.dat(MT.Umber));
		CR.shaped(ST.make(MD.ERE, "gneiss"      , 1, 0), CR.DEF_NAC, "XX", "XX", 'X', rockGt.dat(MT.Gneiss));
		
		
		CR.delate(MD.ERE, "mirbrick");
		if (IL.BoP_Mud_Brick.exists())
		CR.shaped(ST.make(MD.ERE, "mirbrick"    , 1, 0), CR.DEF_NAC_MIR, "XY", "YX", 'X', IL.BoP_Mud_Brick, 'Y', "itemClay");
		CR.shaped(ST.make(MD.ERE, "mirbrick"    , 1, 0), CR.DEF_NAC_MIR, "XY", "YX", 'X', IL.ERE_Mud_Brick, 'Y', "itemClay");
		if (IL.BoP_Mud_Bricks.exists())
		CR.shaped(ST.make(MD.ERE, "mirbrick"    , 4, 0), CR.DEF_NAC_MIR, "XY", "YX", 'X', IL.BoP_Mud_Bricks, 'Y', "blockClay");
		CR.shaped(ST.make(MD.ERE, "mirbrick"    , 4, 0), CR.DEF_NAC_MIR, "XY", "YX", 'X', ST.make(MD.ERE, "mudBricks", 1, 0), 'Y', "blockClay");
		
		
		CR.delate(IL.ERE_Spray_Repellant.get(1));
		
		RM.Canner       .addRecipe2(T, 16,144,  ST.make(MD.ERE, "materials"     , 1,29), IL.Spray_Empty.get(9), IL.ERE_Spray_Repellant.get(9));
		
		for (FluidStack tFluid : FL.array(FL.Water.make(250), FL.DistW.make(250)))
		RM.Mixer        .addRecipe1(T, 16, 16, IL.ERE_Herbicide.get(1), tFluid, FL.Potion_Poison_2.make(250), ZL_IS);
		
		RM.Squeezer     .addRecipe1(T, 16, 16,  ST.make(MD.ERE, "weepingBlue"   , 1, 0), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Blue], ST.make(MD.ERE, "materials", 2,26));
		RM.Juicer       .addRecipe1(T, 16, 16,  ST.make(MD.ERE, "weepingBlue"   , 1, 0), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Blue], ST.make(MD.ERE, "materials", 2,26));
		RM.ic2_extractor(                       ST.make(MD.ERE, "weepingBlue"   , 1, 0), ST.make(MD.ERE, "materials", 2,26));
		
		RM.Squeezer     .addRecipe1(T, 16, 16,  ST.make(MD.ERE, "waterFlower"   , 1, 0), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Pink], ST.make(Items.dye, 2, DYE_INDEX_Pink));
		RM.Juicer       .addRecipe1(T, 16, 16,  ST.make(MD.ERE, "waterFlower"   , 1, 0), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Pink], ST.make(Items.dye, 2, DYE_INDEX_Pink));
		RM.ic2_extractor(                       ST.make(MD.ERE, "waterFlower"   , 1, 0), ST.make(Items.dye, 3, DYE_INDEX_Pink));
		
		RM.Squeezer     .addRecipe1(T, 16, 16,  ST.make(MD.ERE, "pricklyPair"   , 1, W), NF, FL.Juice_Cactus.make(100), IL.Dye_Cactus.get(2));
		RM.Juicer       .addRecipe1(T, 16, 16,  ST.make(MD.ERE, "pricklyPair"   , 1, W), NF, FL.Juice_Cactus.make(100), IL.Dye_Cactus.get(2));
		RM.ic2_extractor(                       ST.make(MD.ERE, "pricklyPair"   , 1, W), IL.Dye_Cactus.get(2));
		
		RM.Squeezer     .addRecipe1(T, 16, 16,  ST.make(MD.ERE, "materials"     , 1,22), NF, FL.Potion_Poison_2.make(750), ZL_IS);
		RM.Juicer       .addRecipe1(T, 16, 16,  ST.make(MD.ERE, "materials"     , 1,22), NF, FL.Potion_Poison_2.make(750), ZL_IS);
		
		FL.set(new FluidContainerData(FL.Honey     .make(1000), ST.make(MD.ERE, "bucketHoney"      , 1, 0), ST.make(Items.bucket       , 1, 0), F), F, F);
		FL.set(new FluidContainerData(FL.HoneyBoP  .make(1000), ST.make(MD.ERE, "bucketHoney"      , 1, 0), ST.make(Items.bucket       , 1, 0), F), F, F);
		FL.set(new FluidContainerData(FL.HoneyGrC  .make(1000), ST.make(MD.ERE, "bucketHoney"      , 1, 0), ST.make(Items.bucket       , 1, 0), F), F, F);
		FL.set(new FluidContainerData(FL.Honey     .make(1000), ST.make(MD.ERE, "bambucketHoney"   , 1, 0), ST.make(MD.ERE, "bambucket", 1, 0), F), T, F);
		FL.set(new FluidContainerData(FL.HoneyBoP  .make(1000), ST.make(MD.ERE, "bambucketHoney"   , 1, 0), ST.make(MD.ERE, "bambucket", 1, 0), F), T, F);
		FL.set(new FluidContainerData(FL.HoneyGrC  .make(1000), ST.make(MD.ERE, "bambucketHoney"   , 1, 0), ST.make(MD.ERE, "bambucket", 1, 0), F), T, F);
		FL.set(new FluidContainerData(FL.Milk      .make(1000), ST.make(MD.ERE, "bambucketMilk"    , 1, 0), ST.make(MD.ERE, "bambucket", 1, 0), F), T, F);
		FL.set(new FluidContainerData(FL.MilkGrC   .make(1000), ST.make(MD.ERE, "bambucketMilk"    , 1, 0), ST.make(MD.ERE, "bambucket", 1, 0), F), T, F);
		
		new OreDictListenerEvent_Names() {@Override public void addAllListeners() {
		addListener("stoneGneiss", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Crusher  .addRecipe1(T, 16, 64       , aEvent.mStack, OP.rockGt.mat(MT.Gneiss, 4));
			RM.Hammer   .addRecipe1(T, 16, 64,  7000, aEvent.mStack, OP.rockGt.mat(MT.Gneiss, 4));
		}});
		}};
	}
}
