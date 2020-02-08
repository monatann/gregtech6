/**
 * Copyright (c) 2020 Gregorius Techneticies
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

package gregtech6.tileentity.tools;

import static gregapi6.data.CS.*;

import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_GetCollisionBoundingBoxFromPool;
import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_GetSelectedBoundingBoxFromPool;
import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_SetBlockBoundsBasedOnState;
import gregapi6.data.OP;
import gregapi6.data.TD;
import gregapi6.render.BlockTextureDefault;
import gregapi6.render.ITexture;
import gregapi6.tileentity.ITileEntityQuickObstructionCheck;
import gregapi6.tileentity.base.TileEntityBase07Paintable;
import gregapi6.tileentity.delegate.DelegatorTileEntity;
import gregapi6.tileentity.machines.ITileEntityCrucible;
import gregapi6.tileentity.machines.ITileEntityMold;
import gregapi6.util.UT;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityCrossing extends TileEntityBase07Paintable implements ITileEntityCrucible, ITileEntityQuickObstructionCheck, IMTE_GetCollisionBoundingBoxFromPool, IMTE_SetBlockBoundsBasedOnState, IMTE_GetSelectedBoundingBoxFromPool {
	public boolean mLock = F;

	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		if (aIsServerSide) mLock = F;
	}

	@Override
	public boolean fillMoldAtSide(ITileEntityMold aMold, byte aSide, byte aSideOfMold) {
		if (mLock) return F;
		mLock = T;
		boolean rReturn = F;
		for (byte tSide : ALL_SIDES_HORIZONTAL) if (tSide != aSide) {
			DelegatorTileEntity<TileEntity> tDelegator = getAdjacentTileEntity(tSide);
			if (tDelegator.mTileEntity instanceof ITileEntityCrucible) {
				rReturn = ((ITileEntityCrucible)tDelegator.mTileEntity).fillMoldAtSide(aMold, tDelegator.mSideOfTileEntity, aSideOfMold);
				if (rReturn) break;
			}
		}
		mLock = F;
		return rReturn;
	}

	@Override
	public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered) {
		mTexture = BlockTextureDefault.get(mMaterial, OP.blockSolid, UT.Code.getRGBaArray(mRGBa), mMaterial.contains(TD.Properties.GLOWING));
		return 11;
	}

	@Override public boolean usesRenderPass2(int aRenderPass, boolean[] aShouldSideBeRendered) {return T;}

	@Override
	public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		switch(aRenderPass) {
		default: return box(aBlock, PX_P[ 6], PX_P[ 1], PX_P[ 0], PX_N[ 6], PX_N[14], PX_N[ 0]);
		case  1: return box(aBlock, PX_P[ 0], PX_P[ 1], PX_P[ 6], PX_N[10], PX_N[14], PX_N[ 6]);
		case  2: return box(aBlock, PX_P[10], PX_P[ 1], PX_P[ 6], PX_N[ 0], PX_N[14], PX_N[ 6]);
		case  3: return box(aBlock, PX_P[ 5], PX_P[ 2], PX_P[ 0], PX_N[10], PX_N[10], PX_N[11]);
		case  4: return box(aBlock, PX_P[ 5], PX_P[ 2], PX_P[11], PX_N[10], PX_N[10], PX_N[ 0]);
		case  5: return box(aBlock, PX_P[ 0], PX_P[ 2], PX_P[ 5], PX_N[10], PX_N[10], PX_N[10]);
		case  6: return box(aBlock, PX_P[10], PX_P[ 2], PX_P[ 5], PX_N[ 0], PX_N[10], PX_N[10]);
		case  7: return box(aBlock, PX_P[10], PX_P[ 2], PX_P[ 0], PX_N[ 5], PX_N[10], PX_N[11]);
		case  8: return box(aBlock, PX_P[10], PX_P[ 2], PX_P[11], PX_N[ 5], PX_N[10], PX_N[ 0]);
		case  9: return box(aBlock, PX_P[ 0], PX_P[ 2], PX_P[10], PX_N[10], PX_N[10], PX_N[ 5]);
		case 10: return box(aBlock, PX_P[10], PX_P[ 2], PX_P[10], PX_N[ 0], PX_N[10], PX_N[ 5]);
		}
	}

	private ITexture mTexture;

	@Override public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {return mTexture;}

	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return box(PX_P[ 0], PX_P[ 1], PX_P[ 0], PX_N[ 0], PX_N[10], PX_N[ 0]);}
	@Override public AxisAlignedBB getSelectedBoundingBoxFromPool () {return box(PX_P[ 0], PX_P[ 1], PX_P[ 0], PX_N[ 0], PX_N[10], PX_N[ 0]);}
	@Override public void setBlockBoundsBasedOnState(Block aBlock)  {box(aBlock, PX_P[ 0], PX_P[ 1], PX_P[ 0], PX_N[ 0], PX_N[10], PX_N[ 0]);}

	@Override public float getSurfaceSize           (byte aSide) {return 0;}
	@Override public float getSurfaceSizeAttachable (byte aSide) {return 0;}
	@Override public float getSurfaceDistance       (byte aSide) {return 0;}
	@Override public boolean isSurfaceSolid         (byte aSide) {return F;}
	@Override public boolean isSurfaceOpaque2       (byte aSide) {return F;}
	@Override public boolean isSideSolid2           (byte aSide) {return F;}
	@Override public boolean allowCovers            (byte aSide) {return F;}
	@Override public boolean attachCoversFirst      (byte aSide) {return F;}
	@Override public boolean isObstructingBlockAt   (byte aSide) {return F;}
	@Override public boolean checkObstruction(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {return F;}
	@Override public int getLightOpacity() {return LIGHT_OPACITY_NONE;}

	@Override public boolean canDrop(int aInventorySlot) {return T;}

	@Override public String getTileEntityName() {return "gt6.multitileentity.smeltery.crossing";}
}
