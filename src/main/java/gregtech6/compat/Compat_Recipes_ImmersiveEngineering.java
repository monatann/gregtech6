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
import gregapi6.code.ArrayListNoNulls;
import gregapi6.code.ModData;
import gregapi6.compat.CompatMods;
import gregapi6.data.ANY;
import gregapi6.data.CS.BlocksGT;
import gregapi6.data.IL;
import gregapi6.data.MD;
import gregapi6.data.MT;
import gregapi6.data.OP;
import gregapi6.data.RM;
import gregapi6.oredict.event.OreDictListenerEvent_Names;
import gregapi6.oredict.event.OreDictListenerEvent_TwoNames;
import gregapi6.util.CR;
import gregapi6.util.ST;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class Compat_Recipes_ImmersiveEngineering extends CompatMods {
	public Compat_Recipes_ImmersiveEngineering(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {OUT.println("GT_Mod: Doing Immersive Engineering Recipes.");
		RM.sawing(16, 96, F, 100, ST.make(MD.IE, "woodenDevice", 1, 6), IL.Plank.get(6), OP.dustSmall.mat(MT.Wood, 2));
		
		RM.generify(ST.make(BlocksGT.Planks, 1, 10), ST.make(MD.IE, "treatedWood", 1, 0));
		RM.generify(ST.make(BlocksGT.PlanksFireProof, 1, 10), ST.make(MD.IE, "treatedWood", 1, 0));
		RM.generify(ST.make(MD.IE, "treatedWood", 1, 0), ST.make(BlocksGT.Planks, 1, 10));
		CR.shapeless(ST.make(BlocksGT.Planks, 1, 10), CR.DEF, new Object[] {ST.make(MD.IE, "treatedWood", 1, 2)});
		CR.shapeless(ST.make(MD.IE, "treatedWood", 1, 0), CR.DEF, new Object[] {ST.make(BlocksGT.Planks, 1, 10)});
		CR.shapeless(ST.make(MD.IE, "treatedWood", 1, 0), CR.DEF, new Object[] {ST.make(BlocksGT.PlanksFireProof, 1, 10)});
		
		RM.Compressor.addRecipe1(F, 64, 64, OP.plateGem.mat(MT.CoalCoke, 8), ST.make(MD.IE, "metal", 1, 20));
		RM.ic2_compressor(OP.plateGem.mat(MT.CoalCoke, 8), ST.make(MD.IE, "metal", 1, 20));
		
		RM.Shredder.addRecipe1(F, 16, 64, ST.make(MD.IE, "metal", 1, 20), ST.make(MD.IE, "metal", 1, 19));
		RM.pulverizing(ST.make(MD.IE, "metal", 1, 20), ST.make(MD.IE, "metal", 1, 19));
		
		new OreDictListenerEvent_Names() {@Override public void addAllListeners() {
		addListener(new OreDictListenerEvent_TwoNames("cropHemp", OP.stick.dat(ANY.Wood)) {@Override public void onOreRegistration(ItemStack aStack1, ItemStack aStack2) {
			RM.Loom.addRecipe2(T, 16, 64, ST.amount(8, aStack1), aStack2, ST.make(MD.IE, "material", 1, 4));
		}});
		}};
		
		if (IL.IE_Hammer.exists()) {
			ArrayListNoNulls tRecipesToRemove = new ArrayListNoNulls();
			for (Object tRecipe : CR.list()) {
				if (tRecipe instanceof ShapelessOreRecipe) {
					for (Object tInput : ((ShapelessOreRecipe)tRecipe).getInput()) {
						if (tInput instanceof ItemStack && IL.IE_Hammer.equal(tInput, F, T)) {
							tRecipesToRemove.add(tRecipe);
							break;
						}
					}
				}
			}
			CR.list().removeAll(tRecipesToRemove);
		}
	}
}
