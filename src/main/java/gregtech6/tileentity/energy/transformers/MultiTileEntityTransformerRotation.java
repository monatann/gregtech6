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

package gregtech6.tileentity.energy.transformers;

import static gregapi6.data.CS.*;

import gregapi6.data.LH;
import gregapi6.old.Textures;
import gregapi6.render.BlockTextureDefault;
import gregapi6.render.BlockTextureMulti;
import gregapi6.render.IIconContainer;
import gregapi6.render.ITexture;
import gregapi6.tileentity.energy.ITileEntityEnergyElectricityAcceptor;
import gregapi6.tileentity.energy.TileEntityBase11Bidirectional;
import net.minecraft.block.Block;

public class MultiTileEntityTransformerRotation extends TileEntityBase11Bidirectional implements ITileEntityEnergyElectricityAcceptor {
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		if (!aShouldSideBeRendered[aSide]) return null;
		int aIndex = aSide==mFacing?mNegativeInput?0:1:aSide==OPPOSITES[mFacing]?mNegativeInput?1:0:2;
		return BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[mActivity.mState == 1 ? 1 : 0][aIndex], mRGBa), BlockTextureDefault.get(sOverlays[mActivity.mState == 1 ? 1 : 0][aIndex]));
	}
	
	@Override public boolean isInput (byte aSide) {return mReversed ? aSide == OPPOSITES[mFacing] : aSide == mFacing;}
	@Override public boolean isOutput(byte aSide) {return mReversed ? aSide == mFacing : aSide == OPPOSITES[mFacing];}
	@Override public String getLocalisedInputSide () {return LH.get(LH.FACE_FRONT);}
	@Override public String getLocalisedOutputSide() {return LH.get(LH.FACE_BACK);}
	
	// Icons
	public static IIconContainer sColoreds[][] = new IIconContainer[][] {{
		new Textures.BlockIcons.CustomIcon("machines/transformers/transformer_rotation/colored/front"),
		new Textures.BlockIcons.CustomIcon("machines/transformers/transformer_rotation/colored/back"),
		new Textures.BlockIcons.CustomIcon("machines/transformers/transformer_rotation/colored/side"),
	}, {
		new Textures.BlockIcons.CustomIcon("machines/transformers/transformer_rotation/colored_active/front"),
		new Textures.BlockIcons.CustomIcon("machines/transformers/transformer_rotation/colored_active/back"),
		new Textures.BlockIcons.CustomIcon("machines/transformers/transformer_rotation/colored_active/side"),
	}}, sOverlays[][] = new IIconContainer[][] {{
		new Textures.BlockIcons.CustomIcon("machines/transformers/transformer_rotation/overlay/front"),
		new Textures.BlockIcons.CustomIcon("machines/transformers/transformer_rotation/overlay/back"),
		new Textures.BlockIcons.CustomIcon("machines/transformers/transformer_rotation/overlay/side"),
	}, {
		new Textures.BlockIcons.CustomIcon("machines/transformers/transformer_rotation/overlay_active/front"),
		new Textures.BlockIcons.CustomIcon("machines/transformers/transformer_rotation/overlay_active/back"),
		new Textures.BlockIcons.CustomIcon("machines/transformers/transformer_rotation/overlay_active/side"),
	}};
	
	@Override public String getTileEntityName() {return "gt6.multitileentity.transformers.transformer_rotation";}
}
