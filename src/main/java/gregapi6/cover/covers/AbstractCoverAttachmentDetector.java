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
import gregapi6.data.LH;
import gregapi6.util.UT;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public abstract class AbstractCoverAttachmentDetector extends AbstractCoverAttachment {
	@Override
	public long onToolClick(byte aSide, CoverData aData, String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSideClicked, float aHitX, float aHitY, float aHitZ) {
		if (aTool.equals(TOOL_cutter)) {
			aData.visual(aSide, (short)(aData.mVisuals[aSide] ^ B[0]), T);
			if (aChatReturn != null) aChatReturn.add((aData.mVisuals[aSide] & B[0]) != 0 ? "Emits strong Redstone" : "Emits weak Redstone");
			return 1000;
		}
		if (aTool.equals(TOOL_screwdriver)) {
			aData.visual(aSide, (short)(aData.mVisuals[aSide] ^ B[1]), T);
			if (aChatReturn != null) aChatReturn.add((aData.mVisuals[aSide] & B[1]) != 0 ? "Emits when Condition FALSE" : "Emits when Condition TRUE");
			return 1000;
		}
		if (aTool.equals(TOOL_magnifyingglass)) {
			if (aChatReturn != null) {
				aChatReturn.add((aData.mVisuals[aSide] & B[0]) != 0 ? "Emits strong Redstone" : "Emits weak Redstone");
				aChatReturn.add((aData.mVisuals[aSide] & B[1]) != 0 ? "Emits when Condition FALSE" : "Emits when Condition TRUE");
			}
			return 1;
		}
		return aData.mTileEntity.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSideClicked, aHitX, aHitY, aHitZ);
	}
	
	@Override
	public byte getRedstoneOutStrong(byte aCoverSide, CoverData aData, byte aDefaultRedstone) {
		return (aData.mVisuals[aCoverSide] & B[0]) != 0 ? getRedstoneOutWeak(aCoverSide, aData, aDefaultRedstone) : 0;
	}
	
	@Override
	public byte getRedstoneOutWeak(byte aCoverSide, CoverData aData, byte aDefaultRedstone) {
		return (aData.mVisuals[aCoverSide] & B[1]) != 0 ? UT.Code.bind4(15-aData.mValues[aCoverSide]) : UT.Code.bind4(aData.mValues[aCoverSide]);
	}
	
	@Override public boolean needsVisualsSaved(byte aSide, CoverData aData) {return T;}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		super.addToolTips(aList, aStack, aF3_H);
		aList.add(LH.Chat.DGRAY + LH.get(LH.TOOL_TO_TOGGLE_SCREWDRIVER));
		aList.add(LH.Chat.DGRAY + LH.get(LH.TOOL_TO_TOGGLE_CUTTER));
		aList.add(LH.Chat.DGRAY + LH.get(LH.TOOL_TO_DETAIL_MAGNIFYINGGLASS));
	}
}
