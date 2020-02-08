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

import gregapi6.tileentity.ITileEntityInventoryGUI;
import net.minecraft.entity.player.InventoryPlayer;

/**
 * @author Gregorius Techneticies
 */
@invtweaks.api.container.ChestContainer(isLargeChest = true)
public class ContainerCommonChest extends ContainerCommon {
	public ContainerCommonChest(InventoryPlayer aInventoryPlayer, ITileEntityInventoryGUI aTileEntity, int aGUIID) {
		super(aInventoryPlayer, aTileEntity, aGUIID);
	}
	
	public ContainerCommonChest(InventoryPlayer aInventoryPlayer, ITileEntityInventoryGUI aTileEntity, int aGUIID, int aOffset, int aSlotCount) {
		super(aInventoryPlayer, aTileEntity, aGUIID, aOffset, aSlotCount);
	}
	
	@Override
	public int addSlots(InventoryPlayer aInventoryPlayer) {
		int tSize = mTileEntity.getSizeInventoryGUI(), tRows = tSize/9 + (tSize%9==0?0:1);
		for (int y = 0, i = 0; y < tRows; y++) for (int x = 0; x < 9 && i < tSize; x++) addSlotToContainer(new Slot_Normal(mTileEntity, mOffset+i++, 8 + x * 18, 18 + y * 18));
		return 103+(tRows-4)*18;
	}
	
	@Override public int getSlotCount() {return mSlotCount;}
	@Override public int getShiftClickSlotCount() {return mSlotCount;}
}
