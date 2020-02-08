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
import gregapi6.data.FL;
import gregapi6.data.IL;
import gregapi6.data.RM;
import gregapi6.util.CR;
import net.minecraftforge.fluids.FluidStack;

public class Compat_Recipes_Mystcraft extends CompatMods {
	public Compat_Recipes_Mystcraft(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {OUT.println("GT_Mod: Doing Mystcraft Recipes.");
		CR.delate(IL.Myst_Ink_Vial.get(1));
		
		for (FluidStack tDye : DYE_FLUIDS[DYE_INDEX_Black]) {
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(tDye, FL.Water.make(125)), FL.Myst_Ink.make(500), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(tDye, FL.DistW.make(125)), FL.Myst_Ink.make(500), ZL_IS);
		}
	}
}
