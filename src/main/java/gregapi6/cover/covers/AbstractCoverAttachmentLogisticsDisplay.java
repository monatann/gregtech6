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
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public abstract class AbstractCoverAttachmentLogisticsDisplay extends AbstractCoverAttachmentLogistics {
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		super.addToolTips(aList, aStack, aF3_H);
		aList.add(LH.Chat.DGRAY + "Emits Redstone and Displays Status of Logistics Core.");
	}
	
	@Override
	public byte getRedstoneOutStrong(byte aCoverSide, CoverData aData, byte aDefaultRedstone) {
		return UT.Code.bind4(aData.mValues[aCoverSide]);
	}
	
	@Override
	public byte getRedstoneOutWeak(byte aCoverSide, CoverData aData, byte aDefaultRedstone) {
		return UT.Code.bind4(aData.mValues[aCoverSide]);
	}
	
	@Override public boolean showsConnectorFront(byte aCoverSide, CoverData aData) {return F;}
	@Override public boolean needsVisualsSaved(byte aSide, CoverData aData) {return T;}
	@Override public boolean useTargetStackSize() {return F;}
	@Override public boolean usePriorities() {return F;}
}
