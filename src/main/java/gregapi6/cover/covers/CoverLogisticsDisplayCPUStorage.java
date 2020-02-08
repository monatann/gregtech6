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
import gregapi6.util.UT;

/**
 * @author Gregorius Techneticies
 */
public class CoverLogisticsDisplayCPUStorage extends AbstractCoverAttachmentLogisticsDisplay {
	public static final CoverLogisticsDisplayCPUStorage INSTANCE = new CoverLogisticsDisplayCPUStorage();
	
	public CoverLogisticsDisplayCPUStorage() {}
	
	@Override public ITexture getCoverTextureSurface(byte aSide, CoverData aData) {return BlockTextureMulti.get(sTexturesBase, sTextures[(int)UT.Code.bind_(0, 10, aData.mVisuals[aSide])]);}
	
	public static final ITexture[] sTextures = new ITexture[] {
		  BlockTextureDefault.get("machines/covers/logistics/display/cpu_storage/0", T)
		, BlockTextureDefault.get("machines/covers/logistics/display/cpu_storage/1", T)
		, BlockTextureDefault.get("machines/covers/logistics/display/cpu_storage/2", T)
		, BlockTextureDefault.get("machines/covers/logistics/display/cpu_storage/3", T)
		, BlockTextureDefault.get("machines/covers/logistics/display/cpu_storage/4", T)
		, BlockTextureDefault.get("machines/covers/logistics/display/cpu_storage/5", T)
		, BlockTextureDefault.get("machines/covers/logistics/display/cpu_storage/6", T)
		, BlockTextureDefault.get("machines/covers/logistics/display/cpu_storage/7", T)
		, BlockTextureDefault.get("machines/covers/logistics/display/cpu_storage/8", T)
		, BlockTextureDefault.get("machines/covers/logistics/display/cpu_storage/9", T)
		, BlockTextureDefault.get("machines/covers/logistics/display/cpu_storage/10", T)
	};
	
	public static final ITexture sTexturesBase = BlockTextureDefault.get("machines/covers/logistics/display/cpu_storage/underlay");
}
