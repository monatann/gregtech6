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

package gregapi6.gui;

import static gregapi6.data.CS.*;

import gregapi6.code.ItemStackContainer;
import gregapi6.code.ItemStackSet;
import gregapi6.tileentity.ITileEntityInventoryGUI;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class Slot_Whitelist extends Slot_Normal {
	private ItemStackSet<ItemStackContainer> mWhiteList = new ItemStackSet<>();
	
	public Slot_Whitelist(ITileEntityInventoryGUI aInventory, int aIndex, int aX, int aY, ItemStack... aValidStacks) {
		super(aInventory, aIndex, aX, aY);
		for (ItemStack aStack : aValidStacks) mWhiteList.add(aStack);
	}
	
	@Override
	public boolean isItemValid(ItemStack aStack) {
		return super.isItemValid(aStack) && mWhiteList.contains(aStack, T);
	}
}
