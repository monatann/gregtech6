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
import gregapi6.data.RM;
import gregapi6.util.CR;
import gregapi6.util.ST;
import net.minecraft.init.Blocks;

public class Compat_Recipes_Chisel extends CompatMods {
	public Compat_Recipes_Chisel(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {OUT.println("GT_Mod: Doing Chisel Mod Recipes.");
		RM.rem_smelting(ST.make(Blocks.gravel, 1, 0));
		
		CR.delate(IL.CHSL_Granite.get(1));
		CR.delate(IL.CHSL_Diorite.get(1));
		CR.delate(IL.CHSL_Andesite.get(1));
		CR.delate(IL.CHSL_Granite_Smooth.get(1));
		CR.delate(IL.CHSL_Diorite_Smooth.get(1));
		CR.delate(IL.CHSL_Andesite_Smooth.get(1));
	}
}
