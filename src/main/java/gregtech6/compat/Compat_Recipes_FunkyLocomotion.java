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

public class Compat_Recipes_FunkyLocomotion extends CompatMods {
	public Compat_Recipes_FunkyLocomotion(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {OUT.println("GT_Mod: Doing Funky Locomotion Recipes.");
		CR.shaped(ST.make(MD.FUNK, "pusher" , 1, 0), CR.DEF_REM_REV_NCC, "FFF", "IPI", "IDI", 'P', OD.craftingPiston, 'D', OP.gem.dat(ANY.Diamond), 'I', OP.plate.dat(MT.Invar), 'F', OP.foil.dat(MT.Enderium));
		CR.shaped(ST.make(MD.FUNK, "pusher" , 1, 6), CR.DEF_REM_REV_NCC, " G ", "RMR", " R ", 'M', ST.make(MD.FUNK, "pusher", 1, 0), 'R', OD.itemRedstone, 'G', OD.itemGlue);
		CR.shaped(ST.make(MD.FUNK, "slider" , 1, 0), CR.DEF_REM_REV_NCC, " S ", "LML", " L ", 'M', ST.make(MD.FUNK, "pusher", 1, 0), 'L', OP.gem.dat(MT.Lapis), 'S', OP.wireFine.dat(MT.Signalum));
		CR.shaped(ST.make(MD.FUNK, "slider" , 1, 0), CR.DEF_NCC        , " S ", "LML", " L ", 'M', ST.make(MD.FUNK, "pusher", 1, 6), 'L', OP.gem.dat(MT.Lapis), 'S', OP.wireFine.dat(MT.Signalum));
		CR.shaped(ST.make(MD.FUNK, "booster", 1, 0), CR.DEF_REM_REV_NCC, "EEE", "IPI", "IMI", 'M', ST.make(MD.FUNK, "pusher", 1, 0), 'P', OD.craftingPiston, 'I', OP.plate.dat(MT.Invar), 'E', OP.plate.dat(MT.Electrum));
		CR.shaped(ST.make(MD.FUNK, "booster", 1, 0), CR.DEF_NCC        , "EEE", "IPI", "IMI", 'M', ST.make(MD.FUNK, "pusher", 1, 6), 'P', OD.craftingPiston, 'I', OP.plate.dat(MT.Invar), 'E', OP.plate.dat(MT.Electrum));
		CR.shaped(ST.make(MD.FUNK, "booster", 1, 0), CR.DEF_NCC        , "EEE", "IPI", "IMI", 'M', ST.make(MD.FUNK, "slider", 1, 0), 'P', OD.craftingPiston, 'I', OP.plate.dat(MT.Invar), 'E', OP.plate.dat(MT.Electrum));
		CR.shaped(ST.make(MD.FUNK, "frame"  , 7, 0), CR.DEF_REM_NCC    , "PPP", "S S", "PPP", 'P', OP.plate.dat(ANY.Steel), 'S', OP.stick.dat(ANY.Steel));
	}
}
