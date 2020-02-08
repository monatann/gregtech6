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

package gregtech6.tileentity.energy.converters;

import static gregapi6.data.CS.*;

import gregapi6.old.Textures;
import gregapi6.render.BlockTextureDefault;
import gregapi6.render.BlockTextureMulti;
import gregapi6.render.IIconContainer;
import gregapi6.render.ITexture;
import gregapi6.tileentity.energy.ITileEntityEnergyFluxHandler;
import gregapi6.tileentity.energy.TileEntityBase11Twotypes;
import net.minecraft.block.Block;

public class MultiTileEntityCoolerFlux extends TileEntityBase11Twotypes implements ITileEntityEnergyFluxHandler {
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		if (!aShouldSideBeRendered[aSide]) return null;
		int aIndex = aSide==mFacing?0:aSide==OPPOSITES[mFacing]?1:2;
		return BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[aIndex], mRGBa), BlockTextureDefault.get((mActivity.mState>0?sOverlaysActive:sOverlays)[aIndex]));
	}
	
	// Icons
	public static IIconContainer sColoreds[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/cooler/cryo_flux/colored/front"),
		new Textures.BlockIcons.CustomIcon("machines/cooler/cryo_flux/colored/back"),
		new Textures.BlockIcons.CustomIcon("machines/cooler/cryo_flux/colored/side"),
	}, sOverlays[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/cooler/cryo_flux/overlay/front"),
		new Textures.BlockIcons.CustomIcon("machines/cooler/cryo_flux/overlay/back"),
		new Textures.BlockIcons.CustomIcon("machines/cooler/cryo_flux/overlay/side"),
	}, sOverlaysActive[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/cooler/cryo_flux/overlay_active/front"),
		new Textures.BlockIcons.CustomIcon("machines/cooler/cryo_flux/overlay_active/back"),
		new Textures.BlockIcons.CustomIcon("machines/cooler/cryo_flux/overlay_active/side"),
	};
	
	@Override public String getTileEntityName() {return "gt6.multitileentity.cooler.cryo_flux";}
}
