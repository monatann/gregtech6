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
import gregapi6.render.ITexture;

/**
 * @author Gregorius Techneticies
 */
public class CoverLogisticsGenericExport extends AbstractCoverAttachmentLogistics {
	public static final CoverLogisticsGenericExport INSTANCE = new CoverLogisticsGenericExport();
	
	public CoverLogisticsGenericExport() {}
	
	@Override public ITexture getCoverTextureSurface(byte aCoverSide, CoverData aData) {return sTexture;}
	
	@Override public boolean useTargetStackSize() {return T;}
	
	public static final ITexture sTexture = BlockTextureDefault.get("machines/covers/logistics/generic/export");
}
