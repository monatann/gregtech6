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

package gregtech6.tileentity.multiblocks;

import static gregapi6.data.CS.*;

import java.util.List;

import gregapi6.data.LH;
import gregapi6.data.LH.Chat;
import gregapi6.tileentity.ITileEntityUnloadable;
import gregapi6.tileentity.machines.ITileEntitySwitchableOnOff;
import gregapi6.tileentity.multiblocks.ITileEntityMultiBlockController;
import gregapi6.tileentity.multiblocks.MultiTileEntityMultiBlockPart;
import gregapi6.tileentity.multiblocks.TileEntityBase11MultiBlockConverter;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityLargeDynamo extends TileEntityBase11MultiBlockConverter implements ITileEntitySwitchableOnOff {
	public short mDynamoWalls = 18022;

	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_DESIGN)) mDynamoWalls = aNBT.getShort(NBT_DESIGN);
	}

	@Override
	public boolean checkStructure2() {
		int
		tMinX = xCoord-(SIDE_X_NEG==mFacing?0:SIDE_X_POS==mFacing?3:1),
		tMinY = yCoord-(SIDE_Y_NEG==mFacing?0:SIDE_Y_POS==mFacing?3:1),
		tMinZ = zCoord-(SIDE_Z_NEG==mFacing?0:SIDE_Z_POS==mFacing?3:1),
		tMaxX = xCoord+(SIDE_X_POS==mFacing?0:SIDE_X_NEG==mFacing?3:1),
		tMaxY = yCoord+(SIDE_Y_POS==mFacing?0:SIDE_Y_NEG==mFacing?3:1),
		tMaxZ = zCoord+(SIDE_Z_POS==mFacing?0:SIDE_Z_NEG==mFacing?3:1),
		tOutX = getOffsetXN(mFacing, 3),
		tOutY = getOffsetYN(mFacing, 3),
		tOutZ = getOffsetZN(mFacing, 3);

		if (worldObj.blockExists(tMinX, tMinY, tMinZ) && worldObj.blockExists(tMaxX, tMaxY, tMaxZ)) {
			mEmitter = null;
			boolean tSuccess = T;
			int blockId = 18095;
			for (int tX = tMinX; tX <= tMaxX; tX++) for (int tY = tMinY; tY <= tMaxY; tY++) for (int tZ = tMinZ; tZ <= tMaxZ; tZ++) if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX, tY, tZ, (SIDES_AXIS_X[mFacing]?tX!=tMinX&&tX!=tMaxX:SIDES_AXIS_Z[mFacing]?tZ!=tMinZ&&tZ!=tMaxZ:tY!=tMinY&&tY!=tMaxY) ? blockId : mDynamoWalls, getMultiTileEntityRegistryID(), tX == tOutX && tY == tOutY && tZ == tOutZ ? 2 : 0, tX == tOutX && tY == tOutY && tZ == tOutZ ? MultiTileEntityMultiBlockPart.ONLY_ENERGY_OUT : MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
			return tSuccess;
		}
		return mStructureOkay;
	}

	@Override
	public boolean isInsideStructure(int aX, int aY, int aZ) {
		return
		aX >= xCoord-(SIDE_X_NEG==mFacing?0:SIDE_X_POS==mFacing?3:1) &&
		aY >= yCoord-(SIDE_Y_NEG==mFacing?0:SIDE_Y_POS==mFacing?3:1) &&
		aZ >= zCoord-(SIDE_Z_NEG==mFacing?0:SIDE_Z_POS==mFacing?3:1) &&
		aX <= xCoord+(SIDE_X_POS==mFacing?0:SIDE_X_NEG==mFacing?3:1) &&
		aY <= yCoord+(SIDE_Y_POS==mFacing?0:SIDE_Y_NEG==mFacing?3:1) &&
		aZ <= zCoord+(SIDE_Z_POS==mFacing?0:SIDE_Z_NEG==mFacing?3:1);
	}

	static {
		LH.add("gt6.tooltip.multiblock.dynamo.1", "Two 3x3s with 2m inbetween made of the Block you crafted this of");
		LH.add("gt6.tooltip.multiblock.dynamo.2", "a 3x3x2 of 18 Large Copper Coils inbetween");
		LH.add("gt6.tooltip.multiblock.dynamo.3", "Main centered on one of the 3x3s facing outwards");
	}

	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get(LH.STRUCTURE) + ":");
		aList.add(Chat.WHITE    + LH.get("gt6.tooltip.multiblock.dynamo.1"));
		aList.add(Chat.WHITE    + LH.get("gt6.tooltip.multiblock.dynamo.2"));
		aList.add(Chat.WHITE    + LH.get("gt6.tooltip.multiblock.dynamo.3"));
		super.addToolTips(aList, aStack, aF3_H);
	}

	public ITileEntityUnloadable mEmitter = null;

	@Override public TileEntity getEmittingTileEntity() {if (mEmitter == null || mEmitter.isDead()) {mEmitter = null; TileEntity tTileEntity = getTileEntityAtSideAndDistance(OPPOSITES[mFacing], 3); if (tTileEntity instanceof ITileEntityUnloadable) mEmitter = (ITileEntityUnloadable)tTileEntity;} return mEmitter == null ? this : (TileEntity)mEmitter;}
	@Override public byte getEmittingSide() {return OPPOSITES[mFacing];}
	@Override public boolean isInput (byte aSide) {return aSide == mFacing;}
	@Override public boolean isOutput(byte aSide) {return aSide == OPPOSITES[mFacing];}

	@Override public byte getDefaultSide() {return SIDE_FRONT;}
	@Override public boolean[] getValidSides() {return SIDES_VALID;}

	@Override public String getTileEntityName() {return "gt6.multitileentity.multiblock.dynamo";}
}
