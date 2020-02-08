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
import gregapi6.data.MT;
import gregapi6.data.RM;
import gregapi6.util.CR;
import gregapi6.util.OM;
import gregapi6.util.ST;
import net.minecraft.init.Items;

public class Compat_Recipes_Witchery extends CompatMods {
	public Compat_Recipes_Witchery(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {OUT.println("GT_Mod: Doing Witchery Recipes.");
		CR.shaped(ST.make(MD.WTCH, "ingredient", 1, 7), CR.DEF_NCC, "B  ", "   ", "  k", 'B', Items.bone);
		
		RM.Hammer       .addRecipe1(T, 16,  16, ST.make(MD.WTCH, "perpetualice", 1, 0), OM.dust(MT.Ice, U));
		RM.Squeezer     .addRecipe1(T, 16, 128, ST.make(MD.WTCH, "perpetualice", 1, 0), NF, FL.Ice.make(1000), NI);
		RM.Juicer       .addRecipe1(T, 16, 128, ST.make(MD.WTCH, "perpetualice", 1, 0), NF, FL.Ice.make(1000), NI);
		RM.Hammer       .addRecipe1(T, 16,  16, ST.make(MD.WTCH, "icestairs", 1, 0), OM.dust(MT.Ice, 3*U4));
		RM.Squeezer     .addRecipe1(T, 16,  96, ST.make(MD.WTCH, "icestairs", 1, 0), NF, FL.Ice.make(750), NI);
		RM.Juicer       .addRecipe1(T, 16,  96, ST.make(MD.WTCH, "icestairs", 1, 0), NF, FL.Ice.make(750), NI);
		RM.Hammer       .addRecipe1(T, 16,  16, ST.make(MD.WTCH, "iceslab", 1, 0), OM.dust(MT.Ice, U2));
		RM.Squeezer     .addRecipe1(T, 16,  64, ST.make(MD.WTCH, "iceslab", 1, 0), NF, FL.Ice.make(500), NI);
		RM.Juicer       .addRecipe1(T, 16,  64, ST.make(MD.WTCH, "iceslab", 1, 0), NF, FL.Ice.make(500), NI);
		
		RM.Mortar       .addRecipe1(T, 16, 32, ST.make(MD.WTCH, "shadedglass", 1, W), OM.dust(MT.Glass));
		RM.Mortar       .addRecipe1(T, 16, 32, ST.make(MD.WTCH, "shadedglass_active", 1, W), OM.dust(MT.Glass));
	}
}
