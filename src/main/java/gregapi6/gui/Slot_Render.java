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

import gregapi6.tileentity.ITileEntityInventoryGUI;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

/**
 * @author Gregorius Techneticies
 */
public class Slot_Render extends Slot_Holo {
	public Slot_Render(ITileEntityInventoryGUI aInventory, int aIndex, int aX, int aY) {
		super(aInventory, aIndex, aX, aY, F, F, 0);
	}
	
	/**
	 * NEI has a nice and "useful" Delete-All Function, which would delete the Content of this Slot. This is here to prevent that.
	 */
	@Override
	public void putStack(ItemStack aStack) {
		if (inventory instanceof TileEntity && ((TileEntity)inventory).getWorldObj().isRemote) {
			inventory.setInventorySlotContents(getSlotIndex(), aStack);
		}
		onSlotChanged();
	}
}
