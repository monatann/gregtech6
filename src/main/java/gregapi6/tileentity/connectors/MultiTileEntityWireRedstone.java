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

package gregapi6.tileentity.connectors;

import static gregapi6.data.CS.*;

import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_GetLightValue;
import gregapi6.data.OP;
import gregapi6.old.Textures;
import gregapi6.render.BlockTextureDefault;
import gregapi6.render.ITexture;
import gregapi6.util.UT;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityWireRedstone extends MultiTileEntityWireRedstoneInsulated implements IMTE_GetLightValue {
	public byte mState = 0;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_STATE)) mState = aNBT.getByte(NBT_STATE);
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		if (mState != 0) aNBT.setByte(NBT_STATE, mState);
	}
	
	@Override
	public boolean onTickCheck(long aTimer) {
		byte tOldState = mState;
		mState = UT.Code.bind4(mRedstone / MAX_RANGE);
		if (tOldState != mState) {
			if (mIsGlowing) updateLightValue();
			return T;
		}
		return super.onTickCheck(aTimer);
	}
	
	@Override
	public void setVisualData(byte aData) {
		if (aData != mState) {
			mState = aData;
			if (mIsGlowing) updateLightValue();
		}
	}
	
	@Override
	public byte getVisualData() {
		return mState;
	}
	
	@Override
	public int getLightOpacity() {
		return mIsGlowing ? LIGHT_OPACITY_NONE : super.getLightOpacity();
	}
	
	@Override public int getLightValue                      () {return mIsGlowing ? mState : 0;}
	
	@Override public ITexture getTextureSide                (byte aSide, byte aConnections, float aDiameter, int aRenderPass) {return BlockTextureDefault.get(mMaterial, getIconIndexSide       (aSide, aConnections, aDiameter, aRenderPass), mState > 0, mRGBa);}
	@Override public ITexture getTextureConnected           (byte aSide, byte aConnections, float aDiameter, int aRenderPass) {return BlockTextureDefault.get(mMaterial, getIconIndexConnected  (aSide, aConnections, aDiameter, aRenderPass), mState > 0, mRGBa);}
	@Override public ITexture getTextureCFoam               (byte aSide, byte aConnections, float aDiameter, int aRenderPass) {return BlockTextureDefault.get(Textures.BlockIcons.CFOAM_FRESH       , mRGBa, mIsGlowing && mState > 0);}
	@Override public ITexture getTextureCFoamDry            (byte aSide, byte aConnections, float aDiameter, int aRenderPass) {return BlockTextureDefault.get(Textures.BlockIcons.CFOAM_HARDENED    , mRGBa, mIsGlowing && mState > 0);}
	
	@Override public int getIconIndexSide                   (byte aSide, byte aConnections, float aDiameter, int aRenderPass) {return OP.wire.mIconIndexBlock;}
	@Override public int getIconIndexConnected              (byte aSide, byte aConnections, float aDiameter, int aRenderPass) {return OP.wire.mIconIndexBlock;}
	
	@Override public String getTileEntityName               () {return "gt6.multitileentity.connector.wire.redstone";}
}
