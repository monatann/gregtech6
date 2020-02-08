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
import gregapi6.data.MT;
import gregapi6.data.OP;
import gregapi6.data.RM;
import gregapi6.util.ST;

public class Compat_Recipes_Factorization extends CompatMods {
	public Compat_Recipes_Factorization(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {OUT.println("GT_Mod: Doing Factorization Recipes.");
		RM.Canner.addRecipe1(T, 16, 16, IL.Bottle_Empty.get(1), MT.H2SO4        .fluid(U, T), NF, ST.make(MD.FZ, "acid", 1, 0));
		RM.Canner.addRecipe1(T, 16, 16, IL.Bottle_Empty.get(1), MT.AquaRegia    .fluid(U, T), NF, ST.make(MD.FZ, "acid", 1, 1));
		
		RM.sawing(16, 800, F, 5000, ST.make(MD.FZ, "daybarrel", 1, W), OP.plate.mat(MT.Wood, 42), OP.dustSmall.mat(MT.Wood, 30));
		
		RM.generify(ST.make(MD.FZ, "diamond_shard", 1, W), OP.gemFlawed.mat(MT.Diamond, 1));
	}
}
