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

package gregapi6.tileentity.base;

import static gregapi6.data.CS.*;

import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_GetCollisionBoundingBoxFromPool;
import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_GetSelectedBoundingBoxFromPool;
import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_SetBlockBoundsBasedOnState;
import gregapi6.tileentity.ITileEntityQuickObstructionCheck;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;

/**
 * @author Gregorius Techneticies
 */
public abstract class TileEntityBase10Attachment extends TileEntityBase09FacingSingle implements ITileEntityQuickObstructionCheck, IMTE_GetCollisionBoundingBoxFromPool, IMTE_SetBlockBoundsBasedOnState, IMTE_GetSelectedBoundingBoxFromPool {
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return null;}
	
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
	@Override public boolean useSidePlacementRotation       () {return T;}
	@Override public boolean useInversePlacementRotation    () {return T;}
	@Override public int getLightOpacity() {return LIGHT_OPACITY_NONE;}
	@Override public byte getDefaultSide() {return SIDE_BACK;}
	@Override public boolean[] getValidSides() {return SIDES_HORIZONTAL;}
}
