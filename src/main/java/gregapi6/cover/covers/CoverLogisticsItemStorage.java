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

package gregapi6.cover.covers;

import static gregapi6.data.CS.*;

import java.util.List;

import gregapi6.cover.CoverData;
import gregapi6.data.CS.SFX;
import gregapi6.data.LH;
import gregapi6.render.BlockTextureDefault;
import gregapi6.render.ITexture;
import gregapi6.util.ST;
import gregapi6.util.UT;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class CoverLogisticsItemStorage extends AbstractCoverAttachmentLogistics {
	public static final CoverLogisticsItemStorage INSTANCE = new CoverLogisticsItemStorage();
	
	public CoverLogisticsItemStorage() {}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		ItemStack tStack = ST.load(aStack.getTagCompound(), "gt6.filter.item");
		if (ST.valid(tStack)) aList.add(LH.Chat.CYAN + tStack.getDisplayName());
		aList.add(LH.Chat.ORANGE + "Not NBT sensitive!");
		super.addToolTips(aList, aStack, aF3_H);
		aList.add(LH.Chat.DGRAY + LH.get(LH.TOOL_TO_RESET_SOFT_HAMMER));
	}
	
	@Override
	public long onToolClick(byte aCoverSide, CoverData aData, String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSideClicked, float aHitX, float aHitY, float aHitZ) {
		if (aTool.equals(TOOL_softhammer)) {
			if (aData.mNBTs[aCoverSide] != null) aData.mNBTs[aCoverSide].removeTag("gt6.filter.item");
			return 10000;
		}
		if (aTool.equals(TOOL_magnifyingglass)) {
			if (aChatReturn != null) {
				if (aData.mNBTs[aCoverSide] == null) {
					aChatReturn.add("No Filter Set! (Priority: " + aData.mValues[aCoverSide] + ")");
					aData.mNBTs[aCoverSide] = null;
				} else {
					ItemStack tStack = ST.load(aData.mNBTs[aCoverSide], "gt6.filter.item");
					if (ST.invalid(tStack)) {
						aChatReturn.add("No Filter Set! (Priority: " + aData.mValues[aCoverSide] + ")");
						aData.mNBTs[aCoverSide] = null;
					} else {
						aChatReturn.add("Stores: " + LH.Chat.CYAN + ST.regName(tStack) + LH.Chat.GRAY + " ; " + LH.Chat.CYAN + ST.meta_(tStack) + " (Priority: " + aData.mValues[aCoverSide] + ")");
					}
				}
			}
			return 1;
		}
		return super.onToolClick(aCoverSide, aData, aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSideClicked, aHitX, aHitY, aHitZ);
	}
	
	@Override
	public boolean onCoverClickedRight(byte aCoverSide, CoverData aData, Entity aPlayer, byte aSideClicked, float aHitX, float aHitY, float aHitZ) {
		if (aPlayer instanceof EntityPlayer && aData.mTileEntity.isServerSide()) {
			if (aData.mNBTs[aCoverSide] == null || !aData.mNBTs[aCoverSide].hasKey("gt6.filter.item")) {
				ItemStack tStack = ST.make(((EntityPlayer)aPlayer).getCurrentEquippedItem(), null, null);
				if (ST.valid(tStack)) {
					aData.mNBTs[aCoverSide] = ST.save(null, "gt6.filter.item", tStack);
					UT.Sounds.send(aData.mTileEntity.getWorld(), SFX.MC_CLICK, 1, 1, aData.mTileEntity.getCoords());
					UT.Entities.sendchat(aPlayer, "Stores: " + LH.Chat.CYAN + ST.regName(tStack) + LH.Chat.GRAY + " ; " + LH.Chat.CYAN + ST.meta_(tStack));
				}
			}
		}
		return T;
	}
	
	@Override public ITexture getCoverTextureSurface(byte aCoverSide, CoverData aData) {return sTexture;}
	
	public static final ITexture sTexture = BlockTextureDefault.get("machines/covers/logistics/item/storage");
}
