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

package gregapi6.oredict.listeners;

import gregapi6.data.LH;
import gregapi6.data.MT;
import gregapi6.data.TD;
import gregapi6.lang.LanguageHandler;
import gregapi6.oredict.OreDictMaterial;
import gregapi6.oredict.OreDictPrefix;
import gregapi6.oredict.listeners.IOreDictListenerItem.OreDictListenerItem;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class OreDictListenerItem_Rocks extends OreDictListenerItem {
	public OreDictListenerItem_Rocks() {
		LH.add("gt6.behaviour.rocks", "Indicates occurence of ");
	}
	
	@Override
	public String getListenerToolTip(OreDictPrefix aPrefix, OreDictMaterial aMaterial, ItemStack aStack) {
		return aMaterial == MT.MeteoricIron ? null : LanguageHandler.translate("gt6.behaviour.rocks", "Indicates occurence of ") + (aMaterial.contains(TD.Properties.STONE)?LH.Chat.WHITE:LH.Chat.YELLOW) + aMaterial.mNameLocal;
	}
}
