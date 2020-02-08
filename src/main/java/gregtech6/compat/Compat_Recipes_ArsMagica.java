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
import gregapi6.oredict.event.OreDictListenerEvent_Names;
import gregapi6.oredict.event.OreDictListenerEvent_ThreeNames;
import gregapi6.util.CR;
import gregapi6.util.OM;
import gregapi6.util.ST;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class Compat_Recipes_ArsMagica extends CompatMods {
	public Compat_Recipes_ArsMagica(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {OUT.println("GT_Mod: Doing Ars Magica Recipes.");
		CR.delate(MD.ARS, "manacake");
		
		CR.shaped(ST.make(MD.ARS, "crystal_wrench", 1, 0), CR.DEF_REV_MIR | CR.DEL_OTHER_NATIVE_RECIPES, "I I", "CVD", " I ", 'C', "flowerCerublossom", 'D', "flowerDesertNova", 'V', OP.dust.dat(MT.Vinteum), 'I', OP.ingot.dat(ANY.Fe));
		
		new OreDictListenerEvent_Names() {@Override public void addAllListeners() {
		addListener(new OreDictListenerEvent_ThreeNames("flowerCerublossom", "flowerDesertNova", "foodSugarDough") {@Override public void onOreRegistration(ItemStack aStack1, ItemStack aStack2, ItemStack aStack3) {
		RM.Mixer.addRecipeX(T, 16, 16, ST.array(aStack1, aStack2, ST.amount(2, aStack3)), ST.make(MD.ARS, "manaCake", 3, 0));
		}});
		}};
		
		RM.Injector.addRecipe2(T, 16, 16, IL.Bottle_Empty.get(1)                                , ST.make(Items.wheat_seeds, 1, W), FL.make("potion.mundane", 250), NF, ST.make(MD.ARS, "lesserManaPotion", 1, 0));
		RM.Injector.addRecipe2(T, 16, 16, ST.make(Items.potionitem                  , 1,   64)  , ST.make(Items.wheat_seeds, 1, W), ST.make(MD.ARS, "lesserManaPotion", 1, 0));
		RM.Injector.addRecipe2(T, 16, 16, ST.make(Items.potionitem                  , 1,16384)  , ST.make(Items.wheat_seeds, 1, W), ST.make(MD.ARS, "standardManaPotion", 1, 0));
		RM.Injector.addRecipe2(T, 16, 16, ST.make(MD.ARS, "lesserManaPotion"        , 1,    0)  , OM.dust(MT.Gunpowder), ST.make(MD.ARS, "standardManaPotion", 1, 0));
		RM.Injector.addRecipe2(T, 16, 16, ST.make(MD.ARS, "standardManaPotion"      , 1,    0)  , OM.dust(MT.Vinteum), ST.make(MD.ARS, "greaterManaPotion", 1, 0));
		RM.Injector.addRecipe2(T, 16, 16, ST.make(MD.ARS, "greaterManaPotion"       , 1,    0)  , OM.dust(MT.ArcaneAsh), ST.make(MD.ARS, "epicManaPotion", 1, 0));
		RM.Injector.addRecipe2(T, 16, 16, ST.make(MD.ARS, "epicManaPotion"          , 1,    0)  , OM.dust(MT.VinteumPurified), ST.make(MD.ARS, "legendaryManaPotion", 1, 0));
	}
}
