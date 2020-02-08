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
import gregapi6.util.CR;
import gregapi6.util.ST;
import net.minecraft.init.Items;

public class Compat_Recipes_Lycanites extends CompatMods {
	public Compat_Recipes_Lycanites(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {OUT.println("GT_Mod: Doing Lycanite Mobs Recipes.");
		CR.delate(MD.LycM_Inferno, "bucketpurelava");
		if (FL.Lava_Pure.exists()) RM.Mixer.addRecipe1(T, 16, 16, ST.make(Items.ghast_tear, 1, W), FL.Lava.make(1000), FL.Lava_Pure.make(1000), ZL_IS);
	}
}
