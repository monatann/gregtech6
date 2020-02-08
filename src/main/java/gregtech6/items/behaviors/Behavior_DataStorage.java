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

package gregtech6.items.behaviors;

import static gregapi6.data.CS.*;

import java.util.List;

import gregapi6.data.LH;
import gregapi6.item.multiitem.MultiItem;
import gregapi6.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi6.util.UT;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class Behavior_DataStorage extends AbstractBehaviorDefault {
	public static final Behavior_DataStorage INSTANCE = new Behavior_DataStorage();
	
	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		if (aStack != null && aStack.hasTagCompound()) {
			NBTTagCompound tUSB = aStack.getTagCompound().getCompoundTag(NBT_USB_DATA);
			if (tUSB != null) {
				UT.NBT.getDataToolTip(tUSB, aList, T);
				aList.add(LH.Chat.DGRAY + "Data: USB " + aStack.getTagCompound().getByte(NBT_USB_TIER) + ".0");
			} else {
				aList.add(LH.Chat.CYAN + "This Stick is Empty");
			}
		}
		return aList;
	}
}
