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
import gregapi6.tileentity.machines.ITileEntityRunningActively;
import gregapi6.tileentity.machines.ITileEntitySwitchableOnOff;

/**
 * @author Gregorius Techneticies
 */
public class CoverControllerAutoTimer extends AbstractCoverAttachmentController {
	public final int mTime;
	public final ITexture mTextureForeground;
	
	public CoverControllerAutoTimer(int aTime) {
		mTime = Math.max(11, aTime);
		mTextureForeground = BlockTextureDefault.get("machines/covers/autotimerswitch/"+mTime+"/circuit");
	}
	
	@Override public ITexture getCoverTextureSurface(byte aSide, CoverData aData) {return mTextureForeground;}
	@Override public ITexture getCoverTextureAttachment(byte aSide, CoverData aData, byte aTextureSide) {return aSide != aTextureSide ? BACKGROUND_COVER : BlockTextureMulti.get(BACKGROUND_COVER, mTextureForeground);}
	@Override public ITexture getCoverTextureHolder(byte aSide, CoverData aData, byte aTextureSide) {return BACKGROUND_COVER;}
	
	@Override
	public void onTickPre(byte aSide, CoverData aData, long aTimer, boolean aIsServerSide, boolean aReceivedBlockUpdate, boolean aReceivedInventoryUpdate) {
		if (aIsServerSide && aData.mTileEntity instanceof ITileEntitySwitchableOnOff) ((ITileEntitySwitchableOnOff)aData.mTileEntity).setStateOnOff((aData.mTileEntity instanceof ITileEntityRunningActively && ((ITileEntityRunningActively)aData.mTileEntity).getStateRunningActively()) || aTimer % mTime >= mTime - 10);
	}
	
	@Override
	public boolean getStateOnOff(byte aSide, CoverData aData) {
		return T;
	}
}
