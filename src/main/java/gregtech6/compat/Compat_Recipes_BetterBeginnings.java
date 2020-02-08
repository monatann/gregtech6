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
import gregapi6.data.IL;
import gregapi6.data.MD;
import gregapi6.data.OD;
import gregapi6.data.RM;
import gregapi6.util.CR;
import gregapi6.util.ST;

public class Compat_Recipes_BetterBeginnings extends CompatMods {
	public Compat_Recipes_BetterBeginnings(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {OUT.println("GT_Mod: Doing Better Beginnings Recipes.");
		// They forgot to OreDict the Wooden Planks in this one.
		CR.shaped(ST.make(MD.BB, "doubleWorkbench", 1, 0), CR.DEF, "PP", "PP", 'P', OD.plankAnyWood);
		RM.Mortar.addRecipe1(T, 16, 32, ST.make(MD.BB, "boneShard", 1, W), IL.Dye_Bonemeal.get(1));
		
	}
}
