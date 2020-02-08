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

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import gregapi6.api.Abstract_Mod;
import gregapi6.code.ModData;
import gregapi6.compat.CompatMods;
import gregapi6.config.ConfigCategories;
import gregapi6.data.CS.ConfigsGT;
import gregapi6.data.IL;
import gregapi6.data.MD;
import gregapi6.data.MT;
import gregapi6.data.OP;
import gregapi6.data.RM;
import gregapi6.util.CR;
import gregapi6.util.ST;
import net.minecraft.init.Items;
import net.minecraftforge.fluids.FluidStack;

public class Compat_Recipes_GrowthCraft extends CompatMods {
	public Compat_Recipes_GrowthCraft(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {OUT.println("GT_Mod: Doing GrowthCraft Recipes.");
		CR.delate(IL.GrC_Honey_Jar          .get(1));
		CR.delate(IL.GrC_Honey_Bucket       .get(1));
		CR.delate(IL.GrC_Honey_Bottle       .get(1));
		CR.delate(IL.GrC_Milk_Bucket        .get(1));
		CR.delate(IL.GrC_Milk_Bottle        .get(1));
		CR.delate(IL.GrC_Applecore          .get(1));
		CR.delate(IL.GrC_Ice_Cream          .get(1));
		CR.delate(IL.GrC_Ice_Cream_Chocolate.get(1));
		CR.delate(IL.GrC_Ice_Cream_Grape    .get(1));
		CR.delate(IL.GrC_Ice_Cream_Apple    .get(1));
		CR.delate(IL.GrC_Ice_Cream_Honey    .get(1));
		CR.delate(IL.GrC_Ice_Cream_Melon    .get(1));
		
		RM.rem_smelting(ST.make(MD.GrC, "grccore.BottleFluidSaltWater", 1, 0));
		RM.rem_smelting(ST.make(MD.GrC, "grccore.BucketFluidSaltWater", 1, 0));
		
		CR.shaped(IL.GrC_Ice_Cream          .get(1), CR.DEF_NAC_NCC, "I", "B", 'B', Items.bowl, 'I', IL.Food_Ice_Cream);
		CR.shaped(IL.GrC_Ice_Cream_Chocolate.get(1), CR.DEF_NAC_NCC, "I", "B", 'B', Items.bowl, 'I', IL.Food_Ice_Cream_Chocolate);
		CR.shaped(IL.GrC_Ice_Cream_Grape    .get(1), CR.DEF_NAC_NCC, "I", "B", 'B', Items.bowl, 'I', IL.Food_Ice_Cream_Grape);
		CR.shaped(IL.GrC_Ice_Cream_Apple    .get(1), CR.DEF_NAC_NCC, "I", "B", 'B', Items.bowl, 'I', IL.Food_Ice_Cream_Apple);
		CR.shaped(IL.GrC_Ice_Cream_Honey    .get(1), CR.DEF_NAC_NCC, "I", "B", 'B', Items.bowl, 'I', IL.Food_Ice_Cream_Honey);
		CR.shaped(IL.GrC_Ice_Cream_Melon    .get(1), CR.DEF_NAC_NCC, "I", "B", 'B', Items.bowl, 'I', IL.Food_Ice_Cream_Melon);
		
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.disabledrecipes, "wood2charcoalsmelting", T)) RM.rem_smelting(IL.GrC_Bamboo.get(1));
		
		for (FluidStack tDye : DYE_FLUIDS[DYE_INDEX_Red     ]) RM.Bath.addRecipe1(T, 0, 16, OP.ingot.mat(MT.WaxBee, 1), tDye, NF, ST.make(MD.GrC_Bees, "grcbees.BeesWax", 1, 1));
		for (FluidStack tDye : DYE_FLUIDS[DYE_INDEX_Black   ]) RM.Bath.addRecipe1(T, 0, 16, OP.ingot.mat(MT.WaxBee, 1), tDye, NF, ST.make(MD.GrC_Bees, "grcbees.BeesWax", 1, 2));
	}
}
