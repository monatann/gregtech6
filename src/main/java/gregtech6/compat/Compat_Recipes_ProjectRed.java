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
import gregapi6.data.MD;
import gregapi6.data.MT;
import gregapi6.data.OD;
import gregapi6.data.OP;
import gregapi6.data.RM;
import gregapi6.util.CR;
import gregapi6.util.ST;

public class Compat_Recipes_ProjectRed extends CompatMods {
	public Compat_Recipes_ProjectRed(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {OUT.println("GT_Mod: Doing PR Recipes.");
		CR.delate(MD.PR, "projectred.core.part", 40);
		
		CR.shaped(ST.make(MD.PR, "projectred.core.part", 1, 40), CR.DEF_NCC, " D ", "DID", " D ", 'D', OD.itemRedstone, 'I', OP.ingot.dat(ANY.Cu));
		CR.shaped(ST.make(MD.PR, "projectred.core.part", 4, 12), CR.DEF_NCC, " X", "s ", 'X', OP.plateGem.dat(ANY.Si));
		CR.shaped(ST.make(MD.PR, "projectred.core.part", 4, 13), CR.DEF_NCC, " X", "s ", 'X', OP.plateGem.dat(MT.RedstoneAlloy));
		
		RM.sawing(64, 64, F, 1000, ST.make(MD.PR, "projectred.core.part", 1, 11), ST.make(MD.PR, "projectred.core.part", 16, 12));
	}
}
