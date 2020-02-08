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

package gregtech6.tileentity.tanks;

import static gregapi6.data.CS.*;

import gregapi6.old.Textures;
import gregapi6.render.BlockTextureDefault;
import gregapi6.render.BlockTextureMulti;
import gregapi6.render.IIconContainer;
import gregapi6.render.ITexture;
import gregapi6.tileentity.tank.TileEntityBase08Barrel;
import net.minecraft.block.Block;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityBarrelPlastic extends TileEntityBase08Barrel {
	@Override public boolean allowCovers(byte aSide) {return F;}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		return aShouldSideBeRendered[aSide] ? BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[FACES_TBS[aSide]], mRGBa), BlockTextureDefault.get(sOverlays[FACES_TBS[aSide]])) : null;
	}
	
	public static IIconContainer sColoreds[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/tanks/plasticcan/colored/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/tanks/plasticcan/colored/top"),
		new Textures.BlockIcons.CustomIcon("machines/tanks/plasticcan/colored/side"),
	}, sOverlays[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/tanks/plasticcan/overlay/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/tanks/plasticcan/overlay/top"),
		new Textures.BlockIcons.CustomIcon("machines/tanks/plasticcan/overlay/side"),
	};
	
	@Override public String getTileEntityName() {return "gt6.multitileentity.tank.barrel.plastic";}
}
