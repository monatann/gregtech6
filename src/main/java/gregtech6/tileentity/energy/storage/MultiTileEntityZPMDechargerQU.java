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

package gregtech6.tileentity.energy.storage;

import static gregapi6.data.CS.*;

import gregapi6.data.IL;
import gregapi6.old.Textures;
import gregapi6.old.Textures.BlockIcons;
import gregapi6.render.BlockTextureDefault;
import gregapi6.render.BlockTextureMulti;
import gregapi6.render.IIconContainer;
import gregapi6.render.ITexture;
import gregapi6.tileentity.energy.TileEntityBase10EnergyBatBox;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class MultiTileEntityZPMDechargerQU extends TileEntityBase10EnergyBatBox {
	@Override public boolean isItemValidForSlot(int aSlot, ItemStack aStack) {return IL.ZPM.equal(aStack, F, T);}
	@Override public boolean canInsertItem2(int aSlot, ItemStack aStack, byte aSide) {return IL.ZPM.equal(aStack, F, T);}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		if (!aShouldSideBeRendered[aSide]) return null;
		if (aSide == OPPOSITES[mFacing]) return BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[1], mRGBa), BlockTextureDefault.get(sOverlays[mActiveState & 3][1]), (mActiveState & 4) == 0 ? null : BlockTextureDefault.get(BlockIcons.ZPM_TOP, (mActiveState & 3) == 0 ? 0x804000 : 0xffdd00, (mActiveState & 3) != 0));
		return BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[aSide==mFacing?0:2], mRGBa), BlockTextureDefault.get(sOverlays[mActiveState & 3][aSide==mFacing?0:2]));
	}
	
	// Icons
	private static IIconContainer sColoreds[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/energystorages/zpm_quantum/colored/front"),
		new Textures.BlockIcons.CustomIcon("machines/energystorages/zpm_quantum/colored/back"),
		new Textures.BlockIcons.CustomIcon("machines/energystorages/zpm_quantum/colored/side"),
	}, sOverlays[][] = new IIconContainer[][] {{
		new Textures.BlockIcons.CustomIcon("machines/energystorages/zpm_quantum/overlay/front"),
		new Textures.BlockIcons.CustomIcon("machines/energystorages/zpm_quantum/overlay/back"),
		new Textures.BlockIcons.CustomIcon("machines/energystorages/zpm_quantum/overlay/side"),
	}, {
		new Textures.BlockIcons.CustomIcon("machines/energystorages/zpm_quantum/overlay_active/front"),
		new Textures.BlockIcons.CustomIcon("machines/energystorages/zpm_quantum/overlay_active/back"),
		new Textures.BlockIcons.CustomIcon("machines/energystorages/zpm_quantum/overlay_active/side"),
	}, {
		new Textures.BlockIcons.CustomIcon("machines/energystorages/zpm_quantum/overlay_blinking/front"),
		new Textures.BlockIcons.CustomIcon("machines/energystorages/zpm_quantum/overlay_blinking/back"),
		new Textures.BlockIcons.CustomIcon("machines/energystorages/zpm_quantum/overlay_blinking/side"),
	}};
	
	@Override public String getTileEntityName() {return "gt6.multitileentity.energystorages.zpm_quantum";}
}
