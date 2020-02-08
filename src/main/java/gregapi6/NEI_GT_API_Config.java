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

package gregapi6;

import static gregapi6.data.CS.*;

import gregapi6.data.MD;
import gregapi6.recipes.Recipe.RecipeMap;
import gregapi6.tileentity.tools.MultiTileEntityAdvancedCraftingTable.MultiTileEntityGUIClientAdvancedCraftingTable;
import gregtech.BuildInfo;
//import gregtech6.BuildInfo;

/**
 * @author Gregorius Techneticies
 */
public class NEI_GT_API_Config implements codechicken.nei.api.IConfigureNEI {
	public static boolean ADDED = T;

	@Override
	public void loadConfig() {
		ADDED = F;

		for (RecipeMap tMap : RecipeMap.RECIPE_MAPS.values()) if (tMap.mNEIAllowed) new NEI_RecipeMap(tMap);

		if (CODE_CLIENT) {
			codechicken.nei.api.API.registerGuiOverlay(MultiTileEntityGUIClientAdvancedCraftingTable.class, "crafting", 55, 22);
			codechicken.nei.api.API.registerGuiOverlayHandler(MultiTileEntityGUIClientAdvancedCraftingTable.class, new codechicken.nei.recipe.DefaultOverlayHandler(55, 22), "crafting");
		}

		NEI = T;

		ADDED = T;
	}

	@Override
	public String getName() {
		return MD.GAPI.mName + " NEI Plugin";
	}

	@Override
	public String getVersion() {
		return BuildInfo.version;
	}
}
