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
import gregapi6.data.MD;
import gregapi6.data.RM;
import gregapi6.util.ST;

public class Compat_Recipes_ForbiddenMagic extends CompatMods {
	public Compat_Recipes_ForbiddenMagic(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {OUT.println("GT_Mod: Doing TC Forbidden Magic Recipes.");
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.TCFM, "InkFlower"  , 1, W), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_Black], 2), ST.make(MD.TCFM, "FMResource", 1, 1));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.TCFM, "UmbralBush" , 1, W), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_Black], 4), ST.make(MD.TCFM, "FMResource", 2, 1));
		RM.Juicer.addRecipe1(T, 16, 16, ST.make(MD.TCFM, "InkFlower"    , 1, W), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_Black], 1), ST.make(MD.TCFM, "FMResource", 1, 1));
		RM.Juicer.addRecipe1(T, 16, 16, ST.make(MD.TCFM, "UmbralBush"   , 1, W), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_Black], 2), ST.make(MD.TCFM, "FMResource", 2, 1));
		RM.ic2_extractor(ST.make(MD.TCFM, "InkFlower"   , 1, W), ST.make(MD.TCFM, "FMResource", 3, 1));
		RM.ic2_extractor(ST.make(MD.TCFM, "UmbralBush"  , 1, W), ST.make(MD.TCFM, "FMResource", 6, 1));
	}
}
