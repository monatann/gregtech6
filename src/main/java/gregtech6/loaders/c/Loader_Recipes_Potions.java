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

import gregapi6.data.ANY;
import gregapi6.data.FL;
import gregapi6.data.IL;
import gregapi6.data.MT;
import gregapi6.data.OP;
import gregapi6.data.RM;
import gregapi6.oredict.OreDictMaterial;
import gregapi6.util.OM;
import gregapi6.util.ST;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class Loader_Recipes_Potions implements Runnable {
	@Override public void run() {
		for (FluidStack tWater : FL.array(FL.Water.make(750), FL.DistW.make(750))) {
		RM.Distillery   .addRecipe1(T, 16,   48, ST.make(Items.nether_wart      , 1,  W), tWater, FL.Potion_Awkward     .make(750), ZL_IS);
		
		RM.Distillery   .addRecipe1(T, 16,   48, ST.make(Items.magma_cream      , 1,  W), tWater, FL.Potion_Mundane     .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, ST.make(Items.speckled_melon   , 1,  W), tWater, FL.Potion_Mundane     .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, ST.make(Items.ghast_tear       , 1,  W), tWater, FL.Potion_Mundane     .make(750), ZL_IS);
		
		RM.Distillery   .addRecipe1(T, 16,   48, ST.make(Items.spider_eye       , 1,  W), tWater, FL.Potion_Mundane     .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, IL.Food_Potato_Poisonous       .get( 1), tWater, FL.Potion_Poison_1    .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, ST.make(Blocks.red_mushroom    , 1,  W), tWater, FL.Potion_Poison_1    .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, ST.make(Items.fish             , 1,  3), tWater, FL.Potion_Poison_2    .make(750), ZL_IS);
		}
		
		RM.Distillery   .addRecipe1(T, 16,   16, OM.dust(MT.WaxRefractory)              , FL.Potion_Awkward .make(250), FL.Potion_FireResistance_1  .make(250), ZL_IS);
		
		RM.Distillery   .addRecipe1(T, 16,   48, ST.make(Items.fish             , 1,  3), FL.Potion_Awkward .make(750), FL.Potion_WaterBreathing_1  .make(750), ZL_IS);
		
		RM.Distillery   .addRecipe1(T, 16,   48, ST.make(Items.magma_cream      , 1,  W), FL.Potion_Awkward .make(750), FL.Potion_FireResistance_1  .make(750), ZL_IS);
		
		RM.Distillery   .addRecipe1(T, 16,   48, ST.make(Items.golden_carrot    , 1,  W), FL.Potion_Awkward .make(750), FL.Potion_NightVision_1     .make(750), ZL_IS);
		
		RM.Distillery   .addRecipe1(T, 16,   48, ST.make(Items.speckled_melon   , 1,  W), FL.Potion_Awkward .make(750), FL.Potion_Heal_1            .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, ST.make(Items.speckled_melon   , 1,  W), FL.Potion_Thick   .make(750), FL.Potion_Heal_2            .make(750), ZL_IS);
		
		RM.Distillery   .addRecipe1(T, 16,   48, ST.make(Items.ghast_tear       , 1,  W), FL.Potion_Awkward .make(750), FL.Potion_Regen_1           .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, ST.make(Items.ghast_tear       , 1,  W), FL.Potion_Thick   .make(750), FL.Potion_Regen_2           .make(750), ZL_IS);
		
		
		for (ItemStack tStack : ST.array(OP.dust.mat(MT.Sugar, 1), OP.dustSmall.mat(MT.Sugar, 4), OP.dustTiny.mat(MT.Sugar, 9), gemChipped.mat(MT.Sugar, 4))) if (tStack != null) {
		for (FluidStack tWater : FL.array(FL.Water.make(750), FL.DistW.make(750)))
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, tWater                       , FL.Potion_Mundane       .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Mundane  .make(750), FL.Purple_Drink         .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Awkward  .make(750), FL.Potion_Speed_1       .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Thick    .make(750), FL.Potion_Speed_2       .make(750), ZL_IS);
		}
		
		
		for (ItemStack tStack : ST.array(OP.dust.mat(MT.Blaze, 1), OP.dustSmall.mat(MT.Blaze, 4), OP.dustTiny.mat(MT.Blaze, 9))) if (tStack != null) {
		for (FluidStack tWater : FL.array(FL.Water.make(750), FL.DistW.make(750)))
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, tWater                       , FL.Potion_Mundane       .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Awkward  .make(750), FL.Potion_Strength_1    .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Thick    .make(750), FL.Potion_Strength_2    .make(750), ZL_IS);
		}
		
		
		for (ItemStack tStack : ST.array(ST.make(Items.spider_eye, 1, W), IL.Food_Potato_Poisonous.get(1), ST.make(Blocks.red_mushroom, 1, W))) if (tStack != null) {
		for (FluidStack tWater : FL.array(FL.Water.make(750), FL.DistW.make(750)))
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, tWater                       , FL.Potion_Mundane       .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Awkward  .make(750), FL.Potion_Poison_1      .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Thick    .make(750), FL.Potion_Poison_2      .make(750), ZL_IS);
		}
		
		
		for (ItemStack tStack : ST.array(OP.plantGtWart.mat(MT.Glowstone, 1), IL.EtFu_Rabbit_Foot.get(1), IL.TF_Mushgloom.get(1))) if (tStack != null) {
		for (FluidStack tWater : FL.array(FL.Water.make(750), FL.DistW.make(750)))
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, tWater                       , FL.Potion_Mundane       .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Awkward  .make(750), FL.Potion_Jump_1        .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Thick    .make(750), FL.Potion_Jump_2        .make(750), ZL_IS);
		}
		
		
		for (ItemStack tStack : ST.array(ST.make(Items.fermented_spider_eye, 1, W))) if (tStack != null) {
		for (FluidStack tWater : FL.array(FL.Water.make(750), FL.DistW.make(750)))
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, tWater                                   , FL.Potion_Mundane               .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Thick                .make(750), FL.Potion_Weakness_1            .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Awkward              .make(750), FL.Potion_Weakness_1            .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Mundane              .make(750), FL.Potion_Weakness_1            .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Heal_1               .make(750), FL.Potion_Harm_1                .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Heal_1S              .make(750), FL.Potion_Harm_1S               .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Heal_1D              .make(750), FL.Potion_Harm_1D               .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Heal_2               .make(750), FL.Potion_Harm_2                .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Heal_2S              .make(750), FL.Potion_Harm_2S               .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Heal_2D              .make(750), FL.Potion_Harm_2D               .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Jump_1               .make(750), FL.Potion_Slowness_1            .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Jump_1S              .make(750), FL.Potion_Slowness_1S           .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Jump_1D              .make(750), FL.Potion_Slowness_1D           .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Jump_2               .make(750), FL.Potion_Slowness_1            .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Jump_2S              .make(750), FL.Potion_Slowness_1S           .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Jump_2D              .make(750), FL.Potion_Slowness_1D           .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Speed_1              .make(750), FL.Potion_Slowness_1            .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Speed_1S             .make(750), FL.Potion_Slowness_1S           .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Speed_1D             .make(750), FL.Potion_Slowness_1D           .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Speed_2              .make(750), FL.Potion_Slowness_1            .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Speed_2S             .make(750), FL.Potion_Slowness_1S           .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Speed_2D             .make(750), FL.Potion_Slowness_1D           .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Speed_1L             .make(750), FL.Potion_Slowness_1L           .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Speed_1LS            .make(750), FL.Potion_Slowness_1LS          .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Speed_1LD            .make(750), FL.Potion_Slowness_1LD          .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Strength_1           .make(750), FL.Potion_Weakness_1            .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Strength_1S          .make(750), FL.Potion_Weakness_1S           .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Strength_1D          .make(750), FL.Potion_Weakness_1D           .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Strength_2           .make(750), FL.Potion_Weakness_1            .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Strength_2S          .make(750), FL.Potion_Weakness_1S           .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Strength_2D          .make(750), FL.Potion_Weakness_1D           .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Strength_1L          .make(750), FL.Potion_Weakness_1L           .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Strength_1LS         .make(750), FL.Potion_Weakness_1LS          .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Strength_1LD         .make(750), FL.Potion_Weakness_1LD          .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Regen_1              .make(750), FL.Potion_Poison_1              .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Regen_1S             .make(750), FL.Potion_Poison_1S             .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Regen_1D             .make(750), FL.Potion_Poison_1D             .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Regen_2              .make(750), FL.Potion_Poison_2              .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Regen_2S             .make(750), FL.Potion_Poison_2S             .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Regen_2D             .make(750), FL.Potion_Poison_2D             .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Regen_1L             .make(750), FL.Potion_Poison_1L             .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Regen_1LS            .make(750), FL.Potion_Poison_1LS            .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Regen_1LD            .make(750), FL.Potion_Poison_1LD            .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Poison_1             .make(750), FL.Potion_Harm_1                .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Poison_1S            .make(750), FL.Potion_Harm_1S               .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Poison_1D            .make(750), FL.Potion_Harm_1D               .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Poison_1L            .make(750), FL.Potion_Harm_1                .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Poison_1LS           .make(750), FL.Potion_Harm_1S               .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Poison_1LD           .make(750), FL.Potion_Harm_1D               .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Poison_2             .make(750), FL.Potion_Harm_2                .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Poison_2S            .make(750), FL.Potion_Harm_2S               .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Poison_2D            .make(750), FL.Potion_Harm_2D               .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_FireResistance_1     .make(750), FL.Potion_Slowness_1            .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_FireResistance_1S    .make(750), FL.Potion_Slowness_1S           .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_FireResistance_1D    .make(750), FL.Potion_Slowness_1D           .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_FireResistance_1L    .make(750), FL.Potion_Slowness_1L           .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_FireResistance_1LS   .make(750), FL.Potion_Slowness_1LS          .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_FireResistance_1LD   .make(750), FL.Potion_Slowness_1LD          .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_NightVision_1        .make(750), FL.Potion_Invisibility_1        .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_NightVision_1S       .make(750), FL.Potion_Invisibility_1S       .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_NightVision_1D       .make(750), FL.Potion_Invisibility_1D       .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_NightVision_1L       .make(750), FL.Potion_Invisibility_1L       .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_NightVision_1LS      .make(750), FL.Potion_Invisibility_1LS      .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_NightVision_1LD      .make(750), FL.Potion_Invisibility_1LD      .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_WaterBreathing_1     .make(750), FL.Potion_Harm_1                .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_WaterBreathing_1S    .make(750), FL.Potion_Harm_1S               .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_WaterBreathing_1D    .make(750), FL.Potion_Harm_1D               .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_WaterBreathing_1L    .make(750), FL.Potion_Harm_1                .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_WaterBreathing_1LS   .make(750), FL.Potion_Harm_1S               .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_WaterBreathing_1LD   .make(750), FL.Potion_Harm_1D               .make(750), ZL_IS);
		}
		
		
		for (OreDictMaterial tMat : ANY.Glowstone.mToThis) for (ItemStack tStack : ST.array(OP.dust.mat(tMat, 1), OP.dustSmall.mat(tMat, 4), OP.dustTiny.mat(tMat, 9))) if (tStack != null) {
		for (FluidStack tWater : FL.array(FL.Water.make(750), FL.DistW.make(750)))
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, tWater                                   , FL.Potion_Thick                 .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Harm_1               .make(750), FL.Potion_Harm_2                .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Heal_1               .make(750), FL.Potion_Heal_2                .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Jump_1               .make(750), FL.Potion_Jump_2                .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Speed_1              .make(750), FL.Potion_Speed_2               .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Strength_1           .make(750), FL.Potion_Strength_2            .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Regen_1              .make(750), FL.Potion_Regen_2               .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Poison_1             .make(750), FL.Potion_Poison_2              .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Harm_1S              .make(750), FL.Potion_Harm_2S               .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Heal_1S              .make(750), FL.Potion_Heal_2S               .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Jump_1S              .make(750), FL.Potion_Jump_2S               .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Speed_1S             .make(750), FL.Potion_Speed_2S              .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Strength_1S          .make(750), FL.Potion_Strength_2S           .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Regen_1S             .make(750), FL.Potion_Regen_2S              .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Poison_1S            .make(750), FL.Potion_Poison_2S             .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Harm_1D              .make(750), FL.Potion_Harm_2D               .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Heal_1D              .make(750), FL.Potion_Heal_2D               .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Jump_1D              .make(750), FL.Potion_Jump_2D               .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Speed_1D             .make(750), FL.Potion_Speed_2D              .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Strength_1D          .make(750), FL.Potion_Strength_2D           .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Regen_1D             .make(750), FL.Potion_Regen_2D              .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Poison_1D            .make(750), FL.Potion_Poison_2D             .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Speed_1L             .make(750), FL.Potion_Speed_1               .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Strength_1L          .make(750), FL.Potion_Strength_1            .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Regen_1L             .make(750), FL.Potion_Regen_1               .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Poison_1L            .make(750), FL.Potion_Poison_1              .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_FireResistance_1L    .make(750), FL.Potion_FireResistance_1      .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_NightVision_1L       .make(750), FL.Potion_NightVision_1         .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Weakness_1L          .make(750), FL.Potion_Weakness_1            .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Slowness_1L          .make(750), FL.Potion_Slowness_1            .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_WaterBreathing_1L    .make(750), FL.Potion_WaterBreathing_1      .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Invisibility_1L      .make(750), FL.Potion_Invisibility_1        .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Speed_1LS            .make(750), FL.Potion_Speed_1S              .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Strength_1LS         .make(750), FL.Potion_Strength_1S           .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Regen_1LS            .make(750), FL.Potion_Regen_1S              .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Poison_1LS           .make(750), FL.Potion_Poison_1S             .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_FireResistance_1LS   .make(750), FL.Potion_FireResistance_1S     .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_NightVision_1LS      .make(750), FL.Potion_NightVision_1S        .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Weakness_1LS         .make(750), FL.Potion_Weakness_1S           .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Slowness_1LS         .make(750), FL.Potion_Slowness_1S           .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_WaterBreathing_1LS   .make(750), FL.Potion_WaterBreathing_1S     .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Invisibility_1LS     .make(750), FL.Potion_Invisibility_1S       .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Speed_1LD            .make(750), FL.Potion_Speed_1D              .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Strength_1LD         .make(750), FL.Potion_Strength_1D           .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Regen_1LD            .make(750), FL.Potion_Regen_1D              .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Poison_1LD           .make(750), FL.Potion_Poison_1D             .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_FireResistance_1LD   .make(750), FL.Potion_FireResistance_1D     .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_NightVision_1LD      .make(750), FL.Potion_NightVision_1D        .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Weakness_1LD         .make(750), FL.Potion_Weakness_1D           .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Slowness_1LD         .make(750), FL.Potion_Slowness_1D           .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_WaterBreathing_1LD   .make(750), FL.Potion_WaterBreathing_1D     .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Invisibility_1LD     .make(750), FL.Potion_Invisibility_1D       .make(750), ZL_IS);
		}
		
		
		for (ItemStack tStack : ST.array(OP.dust.mat(MT.Redstone, 1), OP.dustSmall.mat(MT.Redstone, 4), OP.dustTiny.mat(MT.Redstone, 9))) if (tStack != null) {
		for (FluidStack tWater : FL.array(FL.Water.make(750), FL.DistW.make(750)))
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, tWater                                   , FL.Potion_Mundane               .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Speed_1              .make(750), FL.Potion_Speed_1L              .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Strength_1           .make(750), FL.Potion_Strength_1L           .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Regen_1              .make(750), FL.Potion_Regen_1L              .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Poison_1             .make(750), FL.Potion_Poison_1L             .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_FireResistance_1     .make(750), FL.Potion_FireResistance_1L     .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_NightVision_1        .make(750), FL.Potion_NightVision_1L        .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Weakness_1           .make(750), FL.Potion_Weakness_1L           .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Slowness_1           .make(750), FL.Potion_Slowness_1L           .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_WaterBreathing_1     .make(750), FL.Potion_WaterBreathing_1L     .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Invisibility_1       .make(750), FL.Potion_Invisibility_1L       .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Speed_1S             .make(750), FL.Potion_Speed_1LS             .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Strength_1S          .make(750), FL.Potion_Strength_1LS          .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Regen_1S             .make(750), FL.Potion_Regen_1LS             .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Poison_1S            .make(750), FL.Potion_Poison_1LS            .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_FireResistance_1S    .make(750), FL.Potion_FireResistance_1LS    .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_NightVision_1S       .make(750), FL.Potion_NightVision_1LS       .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Weakness_1S          .make(750), FL.Potion_Weakness_1LS          .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Slowness_1S          .make(750), FL.Potion_Slowness_1LS          .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_WaterBreathing_1S    .make(750), FL.Potion_WaterBreathing_1LS    .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Invisibility_1S      .make(750), FL.Potion_Invisibility_1LS      .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Speed_1D             .make(750), FL.Potion_Speed_1LD             .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Strength_1D          .make(750), FL.Potion_Strength_1LD          .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Regen_1D             .make(750), FL.Potion_Regen_1LD             .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Poison_1D            .make(750), FL.Potion_Poison_1LD            .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_FireResistance_1D    .make(750), FL.Potion_FireResistance_1LD    .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_NightVision_1D       .make(750), FL.Potion_NightVision_1LD       .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Weakness_1D          .make(750), FL.Potion_Weakness_1LD          .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Slowness_1D          .make(750), FL.Potion_Slowness_1LD          .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_WaterBreathing_1D    .make(750), FL.Potion_WaterBreathing_1LD    .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Invisibility_1D      .make(750), FL.Potion_Invisibility_1LD      .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Harm_2               .make(750), FL.Potion_Harm_1                .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Heal_2               .make(750), FL.Potion_Heal_1                .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Jump_2               .make(750), FL.Potion_Jump_1                .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Speed_2              .make(750), FL.Potion_Speed_1               .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Strength_2           .make(750), FL.Potion_Strength_1            .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Regen_2              .make(750), FL.Potion_Regen_1               .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Poison_2             .make(750), FL.Potion_Poison_1              .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Harm_2S              .make(750), FL.Potion_Harm_1S               .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Heal_2S              .make(750), FL.Potion_Heal_1S               .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Jump_2S              .make(750), FL.Potion_Jump_1S               .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Speed_2S             .make(750), FL.Potion_Speed_1S              .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Strength_2S          .make(750), FL.Potion_Strength_1S           .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Regen_2S             .make(750), FL.Potion_Regen_1S              .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Poison_2S            .make(750), FL.Potion_Poison_1S             .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Harm_2D              .make(750), FL.Potion_Harm_1D               .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Heal_2D              .make(750), FL.Potion_Heal_1D               .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Jump_2D              .make(750), FL.Potion_Jump_1D               .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Speed_2D             .make(750), FL.Potion_Speed_1D              .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Strength_2D          .make(750), FL.Potion_Strength_1D           .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Regen_2D             .make(750), FL.Potion_Regen_1D              .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Poison_2D            .make(750), FL.Potion_Poison_1D             .make(750), ZL_IS);
		}
		
		
		for (ItemStack tStack : ST.array(OP.dust.mat(MT.Gunpowder, 1), OP.dustSmall.mat(MT.Gunpowder, 4), OP.dustTiny.mat(MT.Gunpowder, 9))) if (tStack != null) {
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Harm_1               .make(750), FL.Potion_Harm_1S               .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Heal_1               .make(750), FL.Potion_Heal_1S               .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Jump_1               .make(750), FL.Potion_Jump_1S               .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Speed_1              .make(750), FL.Potion_Speed_1S              .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Strength_1           .make(750), FL.Potion_Strength_1S           .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Regen_1              .make(750), FL.Potion_Regen_1S              .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Poison_1             .make(750), FL.Potion_Poison_1S             .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_FireResistance_1     .make(750), FL.Potion_FireResistance_1S     .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_NightVision_1        .make(750), FL.Potion_NightVision_1S        .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Weakness_1           .make(750), FL.Potion_Weakness_1S           .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Slowness_1           .make(750), FL.Potion_Slowness_1S           .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_WaterBreathing_1     .make(750), FL.Potion_WaterBreathing_1S     .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Invisibility_1       .make(750), FL.Potion_Invisibility_1S       .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Harm_2               .make(750), FL.Potion_Harm_2S               .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Heal_2               .make(750), FL.Potion_Heal_2S               .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Jump_2               .make(750), FL.Potion_Jump_2S               .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Speed_2              .make(750), FL.Potion_Speed_2S              .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Strength_2           .make(750), FL.Potion_Strength_2S           .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Regen_2              .make(750), FL.Potion_Regen_2S              .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Poison_2             .make(750), FL.Potion_Poison_2S             .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Speed_1L             .make(750), FL.Potion_Speed_1LS             .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Strength_1L          .make(750), FL.Potion_Strength_1LS          .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Regen_1L             .make(750), FL.Potion_Regen_1LS             .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Poison_1L            .make(750), FL.Potion_Poison_1LS            .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_FireResistance_1L    .make(750), FL.Potion_FireResistance_1LS    .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_NightVision_1L       .make(750), FL.Potion_NightVision_1LS       .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Weakness_1L          .make(750), FL.Potion_Weakness_1LS          .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Slowness_1L          .make(750), FL.Potion_Slowness_1LS          .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_WaterBreathing_1L    .make(750), FL.Potion_WaterBreathing_1LS    .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   48, tStack, FL.Potion_Invisibility_1L      .make(750), FL.Potion_Invisibility_1LS      .make(750), ZL_IS);
		}
		
		
		
		RM.Mixer        .addRecipe0(T, 16, 16, FL.array(FL.Dragon_Breath.make(10), FL.Potion_Harm_1S               .make(30)), FL.Potion_Harm_1D               .make(30), ZL_IS);
		RM.Mixer        .addRecipe0(T, 16, 16, FL.array(FL.Dragon_Breath.make(10), FL.Potion_Heal_1S               .make(30)), FL.Potion_Heal_1D               .make(30), ZL_IS);
		RM.Mixer        .addRecipe0(T, 16, 16, FL.array(FL.Dragon_Breath.make(10), FL.Potion_Jump_1S               .make(30)), FL.Potion_Jump_1D               .make(30), ZL_IS);
		RM.Mixer        .addRecipe0(T, 16, 16, FL.array(FL.Dragon_Breath.make(10), FL.Potion_Speed_1S              .make(30)), FL.Potion_Speed_1D              .make(30), ZL_IS);
		RM.Mixer        .addRecipe0(T, 16, 16, FL.array(FL.Dragon_Breath.make(10), FL.Potion_Strength_1S           .make(30)), FL.Potion_Strength_1D           .make(30), ZL_IS);
		RM.Mixer        .addRecipe0(T, 16, 16, FL.array(FL.Dragon_Breath.make(10), FL.Potion_Regen_1S              .make(30)), FL.Potion_Regen_1D              .make(30), ZL_IS);
		RM.Mixer        .addRecipe0(T, 16, 16, FL.array(FL.Dragon_Breath.make(10), FL.Potion_Poison_1S             .make(30)), FL.Potion_Poison_1D             .make(30), ZL_IS);
		RM.Mixer        .addRecipe0(T, 16, 16, FL.array(FL.Dragon_Breath.make(10), FL.Potion_FireResistance_1S     .make(30)), FL.Potion_FireResistance_1D     .make(30), ZL_IS);
		RM.Mixer        .addRecipe0(T, 16, 16, FL.array(FL.Dragon_Breath.make(10), FL.Potion_NightVision_1S        .make(30)), FL.Potion_NightVision_1D        .make(30), ZL_IS);
		RM.Mixer        .addRecipe0(T, 16, 16, FL.array(FL.Dragon_Breath.make(10), FL.Potion_Weakness_1S           .make(30)), FL.Potion_Weakness_1D           .make(30), ZL_IS);
		RM.Mixer        .addRecipe0(T, 16, 16, FL.array(FL.Dragon_Breath.make(10), FL.Potion_Slowness_1S           .make(30)), FL.Potion_Slowness_1D           .make(30), ZL_IS);
		RM.Mixer        .addRecipe0(T, 16, 16, FL.array(FL.Dragon_Breath.make(10), FL.Potion_WaterBreathing_1S     .make(30)), FL.Potion_WaterBreathing_1D     .make(30), ZL_IS);
		RM.Mixer        .addRecipe0(T, 16, 16, FL.array(FL.Dragon_Breath.make(10), FL.Potion_Invisibility_1S       .make(30)), FL.Potion_Invisibility_1D       .make(30), ZL_IS);
		RM.Mixer        .addRecipe0(T, 16, 16, FL.array(FL.Dragon_Breath.make(10), FL.Potion_Harm_2S               .make(30)), FL.Potion_Harm_2D               .make(30), ZL_IS);
		RM.Mixer        .addRecipe0(T, 16, 16, FL.array(FL.Dragon_Breath.make(10), FL.Potion_Heal_2S               .make(30)), FL.Potion_Heal_2D               .make(30), ZL_IS);
		RM.Mixer        .addRecipe0(T, 16, 16, FL.array(FL.Dragon_Breath.make(10), FL.Potion_Jump_2S               .make(30)), FL.Potion_Jump_2D               .make(30), ZL_IS);
		RM.Mixer        .addRecipe0(T, 16, 16, FL.array(FL.Dragon_Breath.make(10), FL.Potion_Speed_2S              .make(30)), FL.Potion_Speed_2D              .make(30), ZL_IS);
		RM.Mixer        .addRecipe0(T, 16, 16, FL.array(FL.Dragon_Breath.make(10), FL.Potion_Strength_2S           .make(30)), FL.Potion_Strength_2D           .make(30), ZL_IS);
		RM.Mixer        .addRecipe0(T, 16, 16, FL.array(FL.Dragon_Breath.make(10), FL.Potion_Regen_2S              .make(30)), FL.Potion_Regen_2D              .make(30), ZL_IS);
		RM.Mixer        .addRecipe0(T, 16, 16, FL.array(FL.Dragon_Breath.make(10), FL.Potion_Poison_2S             .make(30)), FL.Potion_Poison_2D             .make(30), ZL_IS);
		RM.Mixer        .addRecipe0(T, 16, 16, FL.array(FL.Dragon_Breath.make(10), FL.Potion_Speed_1LS             .make(30)), FL.Potion_Speed_1LD             .make(30), ZL_IS);
		RM.Mixer        .addRecipe0(T, 16, 16, FL.array(FL.Dragon_Breath.make(10), FL.Potion_Strength_1LS          .make(30)), FL.Potion_Strength_1LD          .make(30), ZL_IS);
		RM.Mixer        .addRecipe0(T, 16, 16, FL.array(FL.Dragon_Breath.make(10), FL.Potion_Regen_1LS             .make(30)), FL.Potion_Regen_1LD             .make(30), ZL_IS);
		RM.Mixer        .addRecipe0(T, 16, 16, FL.array(FL.Dragon_Breath.make(10), FL.Potion_Poison_1LS            .make(30)), FL.Potion_Poison_1LD            .make(30), ZL_IS);
		RM.Mixer        .addRecipe0(T, 16, 16, FL.array(FL.Dragon_Breath.make(10), FL.Potion_FireResistance_1LS    .make(30)), FL.Potion_FireResistance_1LD    .make(30), ZL_IS);
		RM.Mixer        .addRecipe0(T, 16, 16, FL.array(FL.Dragon_Breath.make(10), FL.Potion_NightVision_1LS       .make(30)), FL.Potion_NightVision_1LD       .make(30), ZL_IS);
		RM.Mixer        .addRecipe0(T, 16, 16, FL.array(FL.Dragon_Breath.make(10), FL.Potion_Weakness_1LS          .make(30)), FL.Potion_Weakness_1LD          .make(30), ZL_IS);
		RM.Mixer        .addRecipe0(T, 16, 16, FL.array(FL.Dragon_Breath.make(10), FL.Potion_Slowness_1LS          .make(30)), FL.Potion_Slowness_1LD          .make(30), ZL_IS);
		RM.Mixer        .addRecipe0(T, 16, 16, FL.array(FL.Dragon_Breath.make(10), FL.Potion_WaterBreathing_1LS    .make(30)), FL.Potion_WaterBreathing_1LD    .make(30), ZL_IS);
		RM.Mixer        .addRecipe0(T, 16, 16, FL.array(FL.Dragon_Breath.make(10), FL.Potion_Invisibility_1LS      .make(30)), FL.Potion_Invisibility_1LD      .make(30), ZL_IS);
		
		
		
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Potion_Awkward               .make(50), FL.Potion_Weakness_1         .make(25), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Potion_Mundane               .make(50), FL.Potion_Weakness_1         .make(25), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Potion_Thick                 .make(50), FL.Potion_Weakness_1         .make(25), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Potion_Heal_1                .make(50), FL.Potion_Harm_1             .make(25), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Potion_Heal_1S               .make(50), FL.Potion_Harm_1S            .make(25), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Potion_Heal_1D               .make(50), FL.Potion_Harm_1D            .make(25), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,  128, ST.tag(0), FL.Potion_Heal_2                .make(50), FL.Potion_Harm_2             .make(10), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,  128, ST.tag(0), FL.Potion_Heal_2S               .make(50), FL.Potion_Harm_2S            .make(10), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,  128, ST.tag(0), FL.Potion_Heal_2D               .make(50), FL.Potion_Harm_2D            .make(10), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Potion_Jump_1                .make(50), FL.Potion_Slowness_1         .make(25), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Potion_Jump_1S               .make(50), FL.Potion_Slowness_1S        .make(25), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Potion_Jump_1D               .make(50), FL.Potion_Slowness_1D        .make(25), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,  128, ST.tag(0), FL.Potion_Jump_2                .make(50), FL.Potion_Slowness_1         .make(10), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,  128, ST.tag(0), FL.Potion_Jump_2S               .make(50), FL.Potion_Slowness_1S        .make(10), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,  128, ST.tag(0), FL.Potion_Jump_2D               .make(50), FL.Potion_Slowness_1D        .make(10), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Potion_Speed_1               .make(50), FL.Potion_Slowness_1         .make(25), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Potion_Speed_1S              .make(50), FL.Potion_Slowness_1S        .make(25), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Potion_Speed_1D              .make(50), FL.Potion_Slowness_1D        .make(25), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,  128, ST.tag(0), FL.Potion_Speed_2               .make(50), FL.Potion_Slowness_1         .make(10), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,  128, ST.tag(0), FL.Potion_Speed_2S              .make(50), FL.Potion_Slowness_1S        .make(10), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,  128, ST.tag(0), FL.Potion_Speed_2D              .make(50), FL.Potion_Slowness_1D        .make(10), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,  128, ST.tag(0), FL.Potion_Speed_1L              .make(50), FL.Potion_Slowness_1L        .make(10), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,  128, ST.tag(0), FL.Potion_Speed_1LS             .make(50), FL.Potion_Slowness_1LS       .make(10), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,  128, ST.tag(0), FL.Potion_Speed_1LD             .make(50), FL.Potion_Slowness_1LD       .make(10), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Potion_Strength_1            .make(50), FL.Potion_Weakness_1         .make(25), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Potion_Strength_1S           .make(50), FL.Potion_Weakness_1S        .make(25), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Potion_Strength_1D           .make(50), FL.Potion_Weakness_1D        .make(25), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,  128, ST.tag(0), FL.Potion_Strength_2            .make(50), FL.Potion_Weakness_1         .make(10), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,  128, ST.tag(0), FL.Potion_Strength_2S           .make(50), FL.Potion_Weakness_1S        .make(10), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,  128, ST.tag(0), FL.Potion_Strength_2D           .make(50), FL.Potion_Weakness_1D        .make(10), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,  128, ST.tag(0), FL.Potion_Strength_1L           .make(50), FL.Potion_Weakness_1L        .make(10), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,  128, ST.tag(0), FL.Potion_Strength_1LS          .make(50), FL.Potion_Weakness_1LS       .make(10), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,  128, ST.tag(0), FL.Potion_Strength_1LD          .make(50), FL.Potion_Weakness_1LD       .make(10), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Potion_Regen_1               .make(50), FL.Potion_Poison_1           .make(25), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Potion_Regen_1S              .make(50), FL.Potion_Poison_1S          .make(25), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Potion_Regen_1D              .make(50), FL.Potion_Poison_1D          .make(25), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,  128, ST.tag(0), FL.Potion_Regen_2               .make(50), FL.Potion_Poison_2           .make(10), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,  128, ST.tag(0), FL.Potion_Regen_2S              .make(50), FL.Potion_Poison_2S          .make(10), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,  128, ST.tag(0), FL.Potion_Regen_2D              .make(50), FL.Potion_Poison_2D          .make(10), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,  128, ST.tag(0), FL.Potion_Regen_1L              .make(50), FL.Potion_Poison_1L          .make(10), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,  128, ST.tag(0), FL.Potion_Regen_1LS             .make(50), FL.Potion_Poison_1LS         .make(10), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,  128, ST.tag(0), FL.Potion_Regen_1LD             .make(50), FL.Potion_Poison_1LD         .make(10), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Potion_Poison_1              .make(50), FL.Potion_Harm_1             .make(25), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Potion_Poison_1S             .make(50), FL.Potion_Harm_1S            .make(25), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Potion_Poison_1D             .make(50), FL.Potion_Harm_1D            .make(25), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,  128, ST.tag(0), FL.Potion_Poison_2              .make(50), FL.Potion_Harm_2             .make(10), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,  128, ST.tag(0), FL.Potion_Poison_2S             .make(50), FL.Potion_Harm_2S            .make(10), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,  128, ST.tag(0), FL.Potion_Poison_2D             .make(50), FL.Potion_Harm_2D            .make(10), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,  128, ST.tag(0), FL.Potion_Poison_1L             .make(50), FL.Potion_Harm_1             .make(10), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,  128, ST.tag(0), FL.Potion_Poison_1LS            .make(50), FL.Potion_Harm_1S            .make(10), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,  128, ST.tag(0), FL.Potion_Poison_1LD            .make(50), FL.Potion_Harm_1D            .make(10), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Potion_FireResistance_1      .make(50), FL.Potion_Slowness_1         .make(25), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Potion_FireResistance_1S     .make(50), FL.Potion_Slowness_1S        .make(25), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Potion_FireResistance_1D     .make(50), FL.Potion_Slowness_1D        .make(25), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,  128, ST.tag(0), FL.Potion_FireResistance_1L     .make(50), FL.Potion_Slowness_1L        .make(10), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,  128, ST.tag(0), FL.Potion_FireResistance_1LS    .make(50), FL.Potion_Slowness_1LS       .make(10), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,  128, ST.tag(0), FL.Potion_FireResistance_1LD    .make(50), FL.Potion_Slowness_1LD       .make(10), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Potion_NightVision_1         .make(50), FL.Potion_Invisibility_1     .make(25), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Potion_NightVision_1S        .make(50), FL.Potion_Invisibility_1S    .make(25), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Potion_NightVision_1D        .make(50), FL.Potion_Invisibility_1D    .make(25), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,  128, ST.tag(0), FL.Potion_NightVision_1L        .make(50), FL.Potion_Invisibility_1L    .make(10), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,  128, ST.tag(0), FL.Potion_NightVision_1LS       .make(50), FL.Potion_Invisibility_1LS   .make(10), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,  128, ST.tag(0), FL.Potion_NightVision_1LD       .make(50), FL.Potion_Invisibility_1LD   .make(10), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Potion_WaterBreathing_1      .make(50), FL.Potion_Harm_1             .make(25), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Potion_WaterBreathing_1S     .make(50), FL.Potion_Harm_1S            .make(25), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Potion_WaterBreathing_1D     .make(50), FL.Potion_Harm_1D            .make(25), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,  128, ST.tag(0), FL.Potion_WaterBreathing_1L     .make(50), FL.Potion_Harm_1             .make(10), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,  128, ST.tag(0), FL.Potion_WaterBreathing_1LS    .make(50), FL.Potion_Harm_1S            .make(10), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,  128, ST.tag(0), FL.Potion_WaterBreathing_1LD    .make(50), FL.Potion_Harm_1D            .make(10), ZL_IS);
	}
}
