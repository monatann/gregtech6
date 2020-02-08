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

import gregapi6.cover.CoverData;
import gregapi6.render.BlockTextureDefault;
import gregapi6.render.BlockTextureMulti;
import gregapi6.render.ITexture;
import gregapi6.tileentity.machines.ITileEntityRunningPassively;
import gregapi6.util.UT;
import net.minecraft.entity.Entity;

/**
 * @author Gregorius Techneticies
 */
public class CoverDetectorRunningPassively extends AbstractCoverAttachmentDetector {
	@Override public boolean interceptCoverPlacement(byte aCoverSide, CoverData aData, Entity aPlayer) {return !(aData.mTileEntity.canTick() && aData.mTileEntity instanceof ITileEntityRunningPassively);}
	
	@Override
	public void onTickPost(byte aSide, CoverData aData, long aTimer, boolean aIsServerSide, boolean aReceivedBlockUpdate, boolean aReceivedInventoryUpdate) {
		if (aIsServerSide && aData.mTileEntity instanceof ITileEntityRunningPassively) {
			aData.value(aSide, UT.Code.bind4(((ITileEntityRunningPassively)aData.mTileEntity).getStateRunningPassively() ? 15 : 0), T);
		}
	}
	
	@Override public ITexture getCoverTextureSurface(byte aSide, CoverData aData) {return sTextureForeground;}
	@Override public ITexture getCoverTextureAttachment(byte aSide, CoverData aData, byte aTextureSide) {return aSide != aTextureSide ? BACKGROUND_COVER : BlockTextureMulti.get(BACKGROUND_COVER, sTextureForeground);}
	@Override public ITexture getCoverTextureHolder(byte aSide, CoverData aData, byte aTextureSide) {return BACKGROUND_COVER;}
	
	public static final ITexture sTextureForeground = BlockTextureDefault.get("machines/covers/detectorrunningpassively/circuit");
}
