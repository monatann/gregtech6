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
import gregapi6.render.BlockTextureFluid;
import gregapi6.render.BlockTextureMulti;
import gregapi6.render.IIconContainer;
import gregapi6.render.ITexture;
import gregapi6.tileentity.tank.TileEntityBase10FluidContainerSyncSmall;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityCell extends TileEntityBase10FluidContainerSyncSmall {
	public ITexture[] mTextures = new ITexture[3];
	
	@Override
	public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered) {
		mTextures[0] = BlockTextureMulti.get(BlockTextureDefault.get(sTextureBottom, mRGBa), BlockTextureDefault.get(sOverlayBottom));
		mTextures[1] = BlockTextureMulti.get(BlockTextureDefault.get(sTextureTop, mRGBa), BlockTextureDefault.get(sOverlayTop));
		mTextures[2] = BlockTextureMulti.get(BlockTextureDefault.get(sTextureInsides, mRGBa), BlockTextureDefault.get(sOverlayInsides), BlockTextureFluid.get(mTank), BlockTextureDefault.get(sTextureSides, mRGBa), BlockTextureDefault.get(sOverlaySides));
		return 3;
	}
	
	@Override
	public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		switch(aRenderPass) {
		case 0: box(aBlock, PX_P[ 5], PX_P[ 1], PX_P[ 6], PX_N[ 5], PX_N[ 5], PX_N[ 6]); return T;
		case 1: box(aBlock, PX_P[ 6], PX_P[ 1], PX_P[ 5], PX_N[ 6], PX_N[ 5], PX_N[ 5]); return T;
		case 2: box(aBlock, PX_P[ 6], PX_P[ 0], PX_P[ 6], PX_N[ 6], PX_N[ 4], PX_N[ 6]); return T;
		}
		return F;
	}
	
	public static IIconContainer
	sTextureSides       = new Textures.BlockIcons.CustomIcon("machines/tanks/cell/colored/sides"),
	sTextureInsides     = new Textures.BlockIcons.CustomIcon("machines/tanks/cell/colored/insides"),
	sTextureTop         = new Textures.BlockIcons.CustomIcon("machines/tanks/cell/colored/top"),
	sTextureBottom      = new Textures.BlockIcons.CustomIcon("machines/tanks/cell/colored/bottom"),
	sOverlaySides       = new Textures.BlockIcons.CustomIcon("machines/tanks/cell/overlay/sides"),
	sOverlayInsides     = new Textures.BlockIcons.CustomIcon("machines/tanks/cell/overlay/insides"),
	sOverlayTop         = new Textures.BlockIcons.CustomIcon("machines/tanks/cell/overlay/top"),
	sOverlayBottom      = new Textures.BlockIcons.CustomIcon("machines/tanks/cell/overlay/bottom");
	
	@Override public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {return mTextures[FACES_TBS[aSide]];}
	
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return box(PX_P[ 5], PX_P[ 0], PX_P[ 5], PX_N[ 5], PX_N[ 4], PX_N[ 5]);}
	@Override public AxisAlignedBB getSelectedBoundingBoxFromPool () {return box(PX_P[ 5], PX_P[ 0], PX_P[ 5], PX_N[ 5], PX_N[ 4], PX_N[ 5]);}
	@Override public void setBlockBoundsBasedOnState(Block aBlock) {box(aBlock, PX_P[ 5], PX_P[ 0], PX_P[ 5], PX_N[ 5], PX_N[ 4], PX_N[ 5]);}
	
	@Override public float getSurfaceDistance(byte aSide) {return SIDES_VERTICAL[aSide]?0.0F:PX_P[ 5];}
	
	@Override public byte getMaxStackSize(ItemStack aStack, byte aDefault) {return aDefault;}
	
	@Override public String getTileEntityName() {return "gt6.multitileentity.cell";}
}
