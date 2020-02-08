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
import gregapi6.data.OP;
import gregapi6.data.RM;
import gregapi6.oredict.OreDictMaterial;
import gregapi6.util.CR;
import gregapi6.util.ST;
import net.minecraft.init.Blocks;

public class Compat_Recipes_WRCBE extends CompatMods {
	public Compat_Recipes_WRCBE(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {OUT.println("GT_Mod: Doing WR-CBE Recipes.");
		CR.remove(ST.make(Blocks.obsidian, 1, 0), NI, NI, ST.make(Blocks.obsidian, 1, 0));
		CR.delate(MD.WR_CBE_C, "retherPearl");
		
		for (OreDictMaterial tGlowstone : ANY.Glowstone.mToThis) {
			RM.Mixer.addRecipeX(T, 16, 16, ST.array(OP.gem.mat(MT.EnderPearl, 1), OP.dust     .mat(MT.Redstone,  4), OP.dust     .mat(tGlowstone,  4)), ST.make(MD.WR_CBE_C, "retherPearl", 1, 0));
			RM.Mixer.addRecipeX(T, 16, 16, ST.array(OP.gem.mat(MT.EnderPearl, 1), OP.dustSmall.mat(MT.Redstone, 16), OP.dustSmall.mat(tGlowstone, 16)), ST.make(MD.WR_CBE_C, "retherPearl", 1, 0));
			RM.Mixer.addRecipeX(T, 16, 16, ST.array(OP.gem.mat(MT.EnderPearl, 1), OP.dustTiny .mat(MT.Redstone, 36), OP.dustTiny .mat(tGlowstone, 36)), ST.make(MD.WR_CBE_C, "retherPearl", 1, 0));
		}
	}
}
