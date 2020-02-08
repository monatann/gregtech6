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
import gregapi6.data.ANY;
import gregapi6.data.FL;
import gregapi6.data.IL;
import gregapi6.data.MD;
import gregapi6.data.MT;
import gregapi6.data.OP;
import gregapi6.data.RM;
import gregapi6.oredict.OreDictMaterial;
import gregapi6.oredict.event.IOreDictListenerEvent;
import gregapi6.oredict.event.OreDictListenerEvent_Names;
import gregapi6.util.CR;
import gregapi6.util.OM;
import gregapi6.util.ST;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class Compat_Recipes_Reika extends CompatMods {
	public Compat_Recipes_Reika(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {
		if (MD.RoC.mLoaded) {
			OUT.println("GT_Mod: Doing RotaryCraft Recipes.");
			CR.delate(MD.RoC, "rotarycraft_item_borecraft", 13, 14);
			CR.delate(MD.RoC, "rotarycraft_item_shaftcraft", 0, 2, 9, 10);
			new OreDictListenerEvent_Names(OP.seed) {@Override public void addAllListeners() {
			addListener("seedCanola", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Shredder         .addRecipe1(T, 16,   16, aEvent.mStack, ST.make(MD.RoC, "rotarycraft_item_canola", 1, 2));
			}});
			}};
			RM.Shredder         .addRecipe1(T, 16,  144, ST.make(MD.RoC, "rotarycraft_item_canola", 1, 1), ST.make(MD.RoC, "rotarycraft_item_canola", 9, 2));
			RM.Squeezer         .addRecipe1(T, 16,  144, ST.make(MD.RoC, "rotarycraft_item_canola", 1, 1), NF, FL.lube(405), NI);
			RM.Squeezer         .addRecipe1(T, 16,   16, ST.make(MD.RoC, "rotarycraft_item_canola", 1, 2), NF, FL.lube( 90), NI);
			RM.Juicer           .addRecipe1(T, 16,  144, ST.make(MD.RoC, "rotarycraft_item_canola", 1, 1), NF, FL.lube(270), NI);
			RM.Juicer           .addRecipe1(T, 16,   16, ST.make(MD.RoC, "rotarycraft_item_canola", 1, 2), NF, FL.lube( 60), NI);
			
			RM.Compressor       .addRecipe1(T,512, 1024, OM.dust(MT.Bedrock, U*8), ST.make(Blocks.bedrock, 1, 0));
			
			for (FluidStack tRedstone : FL.array(FL.Redstone.make(L), FL.Redstone_TE.make(100))) if (tRedstone != null) {
			RM.Bath             .addRecipe1(T,  0,   64, IL.Circuit_Board_HSLA_Circuit.get(1), FL.mul(tRedstone, 2, 9, T), NF, ST.make(MD.RoC, "rotarycraft_item_borecraft", 1, 4));
			RM.Bath             .addRecipe1(T,  0,   64, IL.Circuit_Board_Power_Module.get(1), FL.mul(tRedstone, 2, 9, T), NF, ST.make(MD.RoC, "rotarycraft_item_misccraft", 1, 2));
			}
			
			RM.Centrifuge       .addRecipe1(T, 16,   64,  8000, IL.RoC_Comb_Slippery        .get(1), NF, FL.lube( 50), IL.RoC_Propolis_Slippery.get(1));
			RM.Centrifuge       .addRecipe1(T, 16,   64       , IL.RoC_Propolis_Slippery    .get(1), NF, FL.lube(150), ZL_IS);
			
			
			RM.Freezer          .addRecipe1(T, 16,  256, ST.tag(0), MT.CO2.gas(U*3, T), NF, ST.make(MD.RoC, "rotarycraft_item_powders", 1, 11));
			
			
			CR.delate(MD.RoC, "rotarycraft_item_powders", 6, 7);
			RM.Mixer            .addRecipeX(T, 16,   64, ST.array(OM.dust(MT.Redstone, U ), OM.dust(MT.Coal, U ), OM.dust(MT.NaCl,U ), OM.dust(MT.Gunpowder, U )), ST.make(MD.RoC, "rotarycraft_item_powders", 4, 6));
			RM.Mixer            .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.Redstone, U4), OM.dust(MT.Coal, U4), OM.dust(MT.NaCl,U4), OM.dust(MT.Gunpowder, U4)), ST.make(MD.RoC, "rotarycraft_item_powders", 1, 6));
			RM.Mixer            .addRecipeX(T, 16,   64, ST.array(OM.dust(MT.Redstone, U ), OM.dust(MT.Coal, U ), OM.dust(MT.KCl, U ), OM.dust(MT.Gunpowder, U )), ST.make(MD.RoC, "rotarycraft_item_powders", 4, 6));
			RM.Mixer            .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.Redstone, U4), OM.dust(MT.Coal, U4), OM.dust(MT.KCl, U4), OM.dust(MT.Gunpowder, U4)), ST.make(MD.RoC, "rotarycraft_item_powders", 1, 6));
		}
		if (MD.ReC.mLoaded) {
			OUT.println("GT_Mod: Doing ReactorCraft Recipes.");
			final ItemStack tQuicklime = ST.make(MD.ReC, "reactorcraft_item_raw", 1, 4);
			new OreDictListenerEvent_Names(OP.seed) {@Override public void addAllListeners() {
			addListener("gemAnyCalcite", "dustAnyCalcite", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.add_smelting(aEvent.mStack, tQuicklime);
			}});
			}};
		}
		if (MD.ElC.mLoaded) {
			OUT.println("GT_Mod: Doing ElectriCraft Recipes.");
			CR.delate(MD.ElC, "electricraft_item_crafting", 3);
			for (OreDictMaterial tMat1 : ANY.Glowstone.mToThis) for (OreDictMaterial tMat2 : ANY.SiO2.mToThis) for (OreDictMaterial tMat3 : ANY.Diamond.mToThis) {
			RM.Mixer            .addRecipeX(T, 16,   32, ST.array(OM.dust(MT.Redstone, U*4), OM.dust(tMat1, U*1), OM.dust(MT.Lapis, U*1), OM.dust(tMat2, U*1), OM.dust(tMat3, U*1)), ST.make(MD.ElC, "electricraft_item_crafting", 2, 3));
			RM.Mixer            .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.Redstone, U*2), OM.dust(tMat1, U2 ), OM.dust(MT.Lapis, U2 ), OM.dust(tMat2, U2 ), OM.dust(tMat3, U2 )), ST.make(MD.ElC, "electricraft_item_crafting", 1, 3));
			}
		}
		if (MD.CrC.mLoaded) {
			OUT.println("GT_Mod: Doing ChromatiCraft Recipes.");
		}
	}
}
