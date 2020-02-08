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

package gregtech6.tileentity.misc;

import static gregapi6.data.CS.*;

import gregapi6.data.OP;
import gregapi6.data.TD;
import gregapi6.old.Textures;
import gregapi6.render.BlockTextureDefault;
import gregapi6.render.IIconContainer;
import gregapi6.tileentity.misc.MultiTileEntityPlaceable;
import gregapi6.util.UT;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityPlateGem extends MultiTileEntityPlaceable {
	public static IIconContainer
	sTextureSides       = new Textures.BlockIcons.CustomIcon("machines/placeables/plateGem/sides"),
	sTextureTop         = new Textures.BlockIcons.CustomIcon("machines/placeables/plateGem/top");
	
	@Override
	public int getRenderPasses(Block aBlock, boolean[] aShouldSideBeRendered) {
		mTextureSides = BlockTextureDefault.get(sTextureSides, mMaterial.fRGBaSolid, F, mMaterial.contains(TD.Properties.GLOWING), F, F);
		mTextureTop   = BlockTextureDefault.get(sTextureTop  , mMaterial.fRGBaSolid, F, mMaterial.contains(TD.Properties.GLOWING), F, T);
		return (int)UT.Code.bind(mSize, 0, 4);
	}
	
	@Override
	public boolean setBlockBounds(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		switch (aRenderPass) {
		case  0: return box(aBlock, 0.03125, PX_P[ 0], 0.03125, 0.46875, PX_P[mSize / 4 + (mSize % 4 > 0 ? 1 : 0)], 0.46875);
		case  1: return box(aBlock, 0.03125, PX_P[ 0], 0.53125, 0.46875, PX_P[mSize / 4 + (mSize % 4 > 1 ? 1 : 0)], 0.96875);
		case  2: return box(aBlock, 0.53125, PX_P[ 0], 0.03125, 0.96875, PX_P[mSize / 4 + (mSize % 4 > 2 ? 1 : 0)], 0.46875);
		case  3: return box(aBlock, 0.53125, PX_P[ 0], 0.53125, 0.96875, PX_P[mSize / 4                          ], 0.96875);
		}
		return T;
	}
	
	@Override public ItemStack getPickBlock(MovingObjectPosition aTarget) {return OP.plateGem.mat(mMaterial, 1);}
	@Override public ItemStack getStackFromBlock(byte aSide) {return OP.plateGem.mat(mMaterial, 1);}
	
	@Override public void setBlockBoundsBasedOnState(Block aBlock) {box(aBlock, 0, 0, 0, 1, UT.Code.divup(mSize, 4) / 16.0F, 1);}
	@Override public AxisAlignedBB getSelectedBoundingBoxFromPool () {return box(0, 0, 0, 1, UT.Code.divup(mSize, 4) / 16.0F, 1);}
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return mSize < 4 ? null : box(0, 0, 0, 1, (mSize / 4) / 16.0F, 1);}
	
	@Override public String getTileEntityName() {return "gt6.multitileentity.plategem";}
}
