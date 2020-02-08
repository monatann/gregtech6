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
import gregapi6.util.CR;
import gregapi6.util.ST;

public class Compat_Recipes_BalkonsWeaponMod extends CompatMods {
	public Compat_Recipes_BalkonsWeaponMod(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {OUT.println("GT_Mod: Doing Balkon's Weapon Mod Recipes.");
		CR.shaped(ST.make(MD.BWM, "crossbow"            , 1, 0), CR.DEF_REM_REV_NCC , "OSP", "SWI", "PIW", 'P', OP.plate.dat(ANY.Steel), 'O', OP.ring.dat(ANY.Steel), 'I', "itemString", 'S', OP.stick.dat(ANY.Wood), 'W', OD.plankAnyWood);
		CR.shaped(ST.make(MD.BWM, "gun-stock"           , 1, 0), CR.DEF_REM_REV_NCC , "SSW", " rf", 'S', OP.stick.dat(ANY.Wood), 'W', OD.plankAnyWood);
		CR.shaped(ST.make(MD.BWM, "musket-ironpart"     , 1, 0), CR.DEF_REM_REV_NCC , "PPF", " hG", 'G', OP.gearGtSmall .dat(ANY.Steel), 'F', "itemFlint", 'P', OP.pipeSmall.dat(ANY.Steel));
		CR.shaped(ST.make(MD.BWM, "blunder-ironpart"    , 1, 0), CR.DEF_REM_REV_NCC ,  "PF",  "hG", 'G', OP.gearGtSmall .dat(ANY.Steel), 'F', "itemFlint", 'P', OP.pipeSmall.dat(ANY.Steel));
		CR.shaped(ST.make(MD.BWM, "flintlock"           , 1, 0), CR.DEF_REM_REV_NCC , "PGF", "hSW", 'G', OP.gearGtSmall .dat(ANY.Steel), 'F', "itemFlint", 'P', OP.pipeSmall.dat(ANY.Steel), 'S', OP.stick.dat(ANY.Wood), 'W', OD.plankAnyWood);
		CR.shaped(ST.make(MD.BWM, "bolt"                , 4, 0), CR.DEF_REM_REV_NCC ,  "Ss",  "F ", 'S', OP.stickLong   .dat(ANY.Steel), 'F', OD.craftingFeather);
		CR.shaped(ST.make(MD.BWM, "bolt"                , 4, 0), CR.DEF_NCC         , "SsS", " F ", 'S', OP.stick       .dat(ANY.Steel), 'F', OD.craftingFeather);
		CR.shaped(ST.make(MD.BWM, "bolt"                , 4, 0), CR.DEF_NCC         , " Ss", "F F", 'S', OP.stickLong   .dat(ANY.Steel), 'F', OP.plateTiny.dat(MT.Plastic));
		CR.shaped(ST.make(MD.BWM, "bolt"                , 4, 0), CR.DEF_NCC         , "SsS", "F F", 'S', OP.stick       .dat(ANY.Steel), 'F', OP.plateTiny.dat(MT.Plastic));
		CR.shaped(ST.make(MD.BWM, "bullet"              , 1, 0), CR.DEF_REM_REV_NCC ,  "R","G","P", 'R', OP.round.dat(MT.Pb), 'G', OP.dustTiny.dat(MT.Gunpowder), 'P', OP.plateTiny.dat(MT.Paper));
		CR.shaped(ST.make(MD.BWM, "shot"                , 1, 0), CR.DEF_REM_REV_NCC ,  "R","G","P", 'R', OP.dustSmall.dat(ANY.Stone), 'G', OP.dustTiny.dat(MT.Gunpowder), 'P', OP.plateTiny.dat(MT.Paper));
		
		CR.shapeless(ST.make(MD.BWM, "musket"           , 1, 0), CR.DEF_REM_REV_NCC , new Object[] {ST.make(MD.BWM, "musket-ironpart", 1, 0), ST.make(MD.BWM, "gun-stock", 1, 0)});
		CR.shapeless(ST.make(MD.BWM, "blunderbuss"      , 1, 0), CR.DEF_REM_REV_NCC , new Object[] {ST.make(MD.BWM, "blunder-ironpart", 1, 0), ST.make(MD.BWM, "gun-stock", 1, 0)});
	}
}
