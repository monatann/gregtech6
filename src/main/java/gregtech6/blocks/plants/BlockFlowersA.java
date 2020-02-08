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

package gregtech6.blocks.plants;

import static gregapi6.data.CS.*;

import java.util.List;

import gregapi6.block.misc.BlockBaseFlower;
import gregapi6.data.CS.BlocksGT;
import gregapi6.data.LH;
import gregapi6.data.MT;
import gregapi6.data.RM;
import gregapi6.old.Textures;
import gregapi6.util.CR;
import gregapi6.util.OM;
import gregapi6.util.ST;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class BlockFlowersA extends BlockBaseFlower implements Runnable {
	public BlockFlowersA(String aUnlocalised) {
		super(null, aUnlocalised, 8, Textures.BlockIcons.FLOWERS_A);
		LH.add(getUnlocalizedName()+ ".0.name", "Altered Andesite Buckwheat"); // Gold, Silver
		LH.add(getUnlocalizedName()+ ".1.name", "Crosby Buckwheat"); // Gold, Silver, Sulfur
		LH.add(getUnlocalizedName()+ ".2.name", "Alpine Catchfly"); // Copper
		LH.add(getUnlocalizedName()+ ".3.name", "Viola Calaminaria"); // Zinc and heavy Metals
		LH.add(getUnlocalizedName()+ ".4.name", "Thlaspi Lereschianum"); // Nickel and Zinc
		LH.add(getUnlocalizedName()+ ".5.name", "Tufted Evening Primrose"); // Uranium
		LH.add(getUnlocalizedName()+ ".6.name", "Narcissus Sheldonia"); // Cooperite
		LH.add(getUnlocalizedName()+ ".7.name", "Orechid"); // Any Random Ore that doesn't have a specific Flower
		
		GT.mAfterInit.add(this);
		BlocksGT.FLOWERS.add(this);
		
		OM.data(ST.make(this, 1, 0), MT.Wheat, U);
		OM.data(ST.make(this, 1, 1), MT.Wheat, U);
		
		for (int i = 0; i < mMaxMeta; i++) OM.reg(ST.make(this, 1, i), "flower");
	}
	
	@Override
	public void addInformation(ItemStack aStack, int aMeta, EntityPlayer aPlayer, List<String> aList, boolean aF3_H) {
		switch(aMeta) {
		case  0: aList.add("Indicates presence of a Gold Deposit nearby"); break;
		case  1: aList.add("Indicates presence of a Silver Deposit nearby"); break;
		case  2: aList.add("Indicates presence of a Copper Deposit nearby"); break;
		case  3: aList.add("Indicates presence of a Zinc Deposit nearby"); break;
		case  4: aList.add("Indicates presence of a Nickel Deposit nearby"); break;
		case  5: aList.add("Indicates presence of an Uranium Deposit nearby"); break;
		case  6: aList.add("Indicates presence of a Cooperite Deposit nearby"); break;
		case  7: aList.add("Indicates presence of an Ore Deposit nearby"); break;
		}
	}
	
	@Override
	public void run() {
		RM.Mortar   .addRecipe1(T, 16, 16, ST.make(this, 1, 0), OM.dust(MT.Wheat));
		RM.Mortar   .addRecipe1(T, 16, 16, ST.make(this, 1, 1), OM.dust(MT.Wheat));
		
		RM.Shredder .addRecipe1(T, 16, 16, ST.make(this, 1, 0), OM.dust(MT.Wheat));
		RM.Shredder .addRecipe1(T, 16, 16, ST.make(this, 1, 1), OM.dust(MT.Wheat));
		
		RM.Squeezer .addRecipe1(T, 16, 16, ST.make(this, 1, 0), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Yellow], OM.dust(MT.Wheat));
		RM.Squeezer .addRecipe1(T, 16, 16, ST.make(this, 1, 1), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Yellow], OM.dust(MT.Wheat));
		RM.Squeezer .addRecipe1(T, 16, 16, ST.make(this, 1, 2), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Magenta], OM.dust(MT.Magenta));
		RM.Squeezer .addRecipe1(T, 16, 16, ST.make(this, 1, 3), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Yellow], OM.dust(MT.Yellow));
		RM.Squeezer .addRecipe1(T, 16, 16, ST.make(this, 1, 4), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Pink], OM.dust(MT.Pink));
		RM.Squeezer .addRecipe1(T, 16, 16, ST.make(this, 1, 5), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_White], OM.dust(MT.White));
		RM.Squeezer .addRecipe1(T, 16, 16, ST.make(this, 1, 6), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_LightBlue], OM.dust(MT.LightBlue));
		RM.Squeezer .addRecipe1(T, 16, 16, ST.make(this, 1, 7), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Brown], OM.dust(MT.Brown));
		
		RM.Juicer   .addRecipe1(T, 16, 16, ST.make(this, 1, 0), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Yellow], OM.dust(MT.Wheat));
		RM.Juicer   .addRecipe1(T, 16, 16, ST.make(this, 1, 1), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Yellow], OM.dust(MT.Wheat));
		RM.Juicer   .addRecipe1(T, 16, 16, ST.make(this, 1, 2), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Magenta], OM.dust(MT.Magenta));
		RM.Juicer   .addRecipe1(T, 16, 16, ST.make(this, 1, 3), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Yellow], OM.dust(MT.Yellow));
		RM.Juicer   .addRecipe1(T, 16, 16, ST.make(this, 1, 4), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Pink], OM.dust(MT.Pink));
		RM.Juicer   .addRecipe1(T, 16, 16, ST.make(this, 1, 5), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_White], OM.dust(MT.White));
		RM.Juicer   .addRecipe1(T, 16, 16, ST.make(this, 1, 6), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_LightBlue], OM.dust(MT.LightBlue));
		RM.Juicer   .addRecipe1(T, 16, 16, ST.make(this, 1, 7), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Brown], OM.dust(MT.Brown));
		
		CR.shapeless(OM.dust(MT.Yellow), CR.DEF_NAC_NCC, new Object[] {ST.make(this, 1, 0)});
		CR.shapeless(OM.dust(MT.Yellow), CR.DEF_NAC_NCC, new Object[] {ST.make(this, 1, 1)});
		CR.shapeless(OM.dust(MT.Magenta), CR.DEF_NAC_NCC, new Object[] {ST.make(this, 1, 2)});
		CR.shapeless(OM.dust(MT.Yellow), CR.DEF_NAC_NCC, new Object[] {ST.make(this, 1, 3)});
		CR.shapeless(OM.dust(MT.Pink), CR.DEF_NAC_NCC, new Object[] {ST.make(this, 1, 4)});
		CR.shapeless(OM.dust(MT.White), CR.DEF_NAC_NCC, new Object[] {ST.make(this, 1, 5)});
		CR.shapeless(OM.dust(MT.LightBlue), CR.DEF_NAC_NCC, new Object[] {ST.make(this, 1, 6)});
		CR.shapeless(OM.dust(MT.Brown), CR.DEF_NAC_NCC, new Object[] {ST.make(this, 1, 7)});
		
		RM.pulverizing(ST.make(this, 1, 0), OM.dust(MT.Wheat));
		RM.pulverizing(ST.make(this, 1, 1), OM.dust(MT.Wheat));
		
		RM.biomass(ST.make(this, 8, W));
		
		if (ENABLE_ADDING_IC2_EXTRACTOR_RECIPES) {
		RM.ic2_extractor(ST.make(this, 1, 0), OM.dust(MT.Yellow));
		RM.ic2_extractor(ST.make(this, 1, 1), OM.dust(MT.Yellow));
		RM.ic2_extractor(ST.make(this, 1, 2), OM.dust(MT.Magenta, U * 2));
		RM.ic2_extractor(ST.make(this, 1, 3), OM.dust(MT.Yellow, U * 2));
		RM.ic2_extractor(ST.make(this, 1, 4), OM.dust(MT.Pink, U * 2));
		RM.ic2_extractor(ST.make(this, 1, 5), OM.dust(MT.White, U * 2));
		RM.ic2_extractor(ST.make(this, 1, 6), OM.dust(MT.LightBlue, U * 2));
		RM.ic2_extractor(ST.make(this, 1, 7), OM.dust(MT.Brown, U * 2));
		}
	}
}
