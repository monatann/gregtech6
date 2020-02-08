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

import java.util.List;

import gregapi6.code.ArrayListNoNulls;
import gregapi6.data.LH;
import gregapi6.tileentity.ITileEntityInventoryGUI;
import gregapi6.util.ST;
import gregapi6.util.UT;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class Slot_Normal extends Slot {
	private String[] mToolTips = ZL_STRING;
	private String[] mToolTipColors = ZL_STRING;
	
	public final int mIndex;
	public final ITileEntityInventoryGUI mInventory;
	
	public boolean mCanTake = T, mCanPut = T;
	
	public Slot_Normal(ITileEntityInventoryGUI aInventory, int aIndex, int aX, int aY) {
		super(aInventory instanceof IInventory ? (IInventory)aInventory : null, aIndex, aX, aY);
		mInventory = aInventory;
		mIndex = aIndex;
	}
	
	public List<String> getTooltip(EntityPlayer aPlayer, boolean aF3_H) {
		ArrayListNoNulls<String> rList = new ArrayListNoNulls<>();
		for (int i = 0; i < mToolTips.length; i++) {
			if (mToolTipColors[i] == null) mToolTipColors[i] = LH.Chat.GRAY;
			rList.add(mToolTipColors[i] + LH.get(mToolTips[i]));
		}
		return rList;
	}
	
	public Slot_Normal setCanTake(boolean aCanTake) {
		mCanTake = aCanTake;
		return this;
	}
	
	public Slot_Normal setCanPut(boolean aCanPut) {
		mCanPut = aCanPut;
		return this;
	}
	
	public Slot_Normal setTooltip(String aTooltip, String aToolTipColor) {
		mToolTips = new String[] {aTooltip};
		mToolTipColors = new String[] {aToolTipColor};
		return this;
	}
	
	public Slot_Normal setTooltips(String[] aTooltips, String[] aToolTipColors) {
		mToolTips = aTooltips;
		mToolTipColors = (aToolTipColors.length < mToolTips.length ? mToolTipColors = new String[mToolTips.length] : aToolTipColors);
		return this;
	}
	
	@Override public boolean isItemValid(ItemStack aStack) {return mCanPut && mInventory.isItemValidForSlotGUI(mIndex, aStack);}
	@Override public boolean canTakeStack(EntityPlayer aPlayer) {return mInventory.canTakeOutOfSlotGUI(mIndex) && (UT.Entities.isCreative(aPlayer) || (mCanTake && !ST.debug(getStack())));}
	@Override public boolean isSlotInInventory(IInventory aInventory, int aIndex) {return aInventory == mInventory && aIndex == mIndex;}
	@Override public int getSlotStackLimit() {return mInventory.getInventoryStackLimitGUI(mIndex);}
	@Override public ItemStack getStack() {return mInventory.getStackInSlotGUI(mIndex);}
	@Override public void putStack(ItemStack aStack) {mInventory.setInventorySlotContentsGUI(mIndex, aStack); onSlotChanged();}
	@Override public ItemStack decrStackSize(int aAmount) {return mInventory.decrStackSizeGUI(mIndex, aAmount);}
	@Override public void onSlotChanged() {mInventory.markDirtyGUI();}
	@Override public boolean func_111238_b() {return T;}
	@Override public void onSlotChange(ItemStack aStack, ItemStack aStack2) {if (ST.equal(aStack, aStack2, T)) {int tDifference = aStack2.stackSize - aStack.stackSize; if (tDifference > 0) onCrafting(aStack, tDifference);}}
	@Override protected void onCrafting(ItemStack aStack, int aDifference) {/**/}
	@Override protected void onCrafting(ItemStack aStack) {/**/}
	@Override public void onPickupFromSlot(EntityPlayer aPlayer, ItemStack aStack) {onSlotChanged();}
	@Override public boolean getHasStack() {return getStack() != null;}
	@Override public int getSlotIndex() {return mIndex;}
}
