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

import gregapi6.data.LH;
import gregapi6.old.Textures;
import gregapi6.render.BlockTextureDefault;
import gregapi6.render.BlockTextureMulti;
import gregapi6.render.IIconContainer;
import gregapi6.render.ITexture;
import gregapi6.tileentity.energy.TileEntityBase10EnergyConverter;
import gregapi6.tileentity.machines.ITileEntityAdjacentOnOff;
import net.minecraft.block.Block;

public class MultiTileEntityDynamoElectric extends TileEntityBase10EnergyConverter implements ITileEntityAdjacentOnOff {
	@Override public boolean isInput (byte aSide) {return mFacing == OPPOSITES[aSide];}
	@Override public boolean isOutput(byte aSide) {return mFacing == aSide;}
	@Override public String getLocalisedInputSide () {return LH.get(LH.FACE_BACK);}
	@Override public String getLocalisedOutputSide() {return LH.get(LH.FACE_FRONT);}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		if (!aShouldSideBeRendered[aSide]) return null;
		int aIndex = aSide==mFacing?0:aSide==OPPOSITES[mFacing]?1:2;
		return BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[aIndex], mRGBa), BlockTextureDefault.get((mActivity.mState>0?sOverlaysActive:sOverlays)[aIndex]));
	}
	
	// Icons
	public static IIconContainer sColoreds[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/dynamos/electric_rotation/colored/front"),
		new Textures.BlockIcons.CustomIcon("machines/dynamos/electric_rotation/colored/back"),
		new Textures.BlockIcons.CustomIcon("machines/dynamos/electric_rotation/colored/side"),
	}, sOverlays[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/dynamos/electric_rotation/overlay/front"),
		new Textures.BlockIcons.CustomIcon("machines/dynamos/electric_rotation/overlay/back"),
		new Textures.BlockIcons.CustomIcon("machines/dynamos/electric_rotation/overlay/side"),
	}, sOverlaysActive[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/dynamos/electric_rotation/overlay_active/front"),
		new Textures.BlockIcons.CustomIcon("machines/dynamos/electric_rotation/overlay_active/back"),
		new Textures.BlockIcons.CustomIcon("machines/dynamos/electric_rotation/overlay_active/side"),
	};
	
	@Override public String getTileEntityName() {return "gt6.multitileentity.dynamo.electric_rotation";}
}
