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

package gregtech6.tileentity.inventories;

import static gregapi6.data.CS.*;

import java.util.List;

import gregapi6.data.CS.SFX;
import gregapi6.data.LH;
import gregapi6.data.LH.Chat;
import gregapi6.network.INetworkHandler;
import gregapi6.network.IPacket;
import gregapi6.old.Textures;
import gregapi6.render.BlockTextureDefault;
import gregapi6.render.BlockTextureMulti;
import gregapi6.render.IIconContainer;
import gregapi6.render.ITexture;
import gregapi6.tileentity.ITileEntityKeyInteractable;
import gregapi6.tileentity.inventories.MultiTileEntitySafe;
import gregapi6.util.UT;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntitySafeKeyLocked extends MultiTileEntitySafe implements ITileEntityKeyInteractable {
	public long mID = 0;
	public boolean mOpened = F;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_KEY)) mID = aNBT.getLong(NBT_KEY);
		if (aNBT.hasKey(NBT_OPEN)) mOpened = aNBT.getBoolean(NBT_OPEN);
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setNumber(aNBT, NBT_KEY, mID);
		UT.NBT.setBoolean(aNBT, NBT_OPEN, mOpened);
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.ORANGE + LH.get(LH.KEY_CONTROLLED));
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	@Override
	public boolean allowInteraction(Entity aEntity) {
		return mOpened;
	}
	
	@Override
	public boolean useKey(EntityPlayer aPlayer, byte aSide, float hitX, float hitY, float hitZ, long... aKeys) {
		if (aKeys.length <= 0 || !isServerSide()) return F;
		for (long tID : aKeys) {
			if (mID == 0) mID = tID;
			if (mID != 0 && tID == mID) {
				mOpened = !mOpened;
				updateClientData();
				UT.Sounds.send(SFX.MC_CLICK, 1.0F, 0.25F, this);
				return T;
			}
		}
		return F;
	}
	
	@Override
	public IPacket getClientDataPacket(boolean aSendAll) {
		return aSendAll ? getClientDataPacketByteArray(aSendAll, (byte)UT.Code.getR(mRGBa), (byte)UT.Code.getG(mRGBa), (byte)UT.Code.getB(mRGBa), getDirectionData(), getVisualData()) : getClientDataPacketByte(aSendAll, getVisualData());
	}
	
	@Override
	public boolean receiveDataByteArray(byte[] aData, INetworkHandler aNetworkHandler) {
		mRGBa = UT.Code.getRGBInt(new short[] {UT.Code.unsignB(aData[0]), UT.Code.unsignB(aData[1]), UT.Code.unsignB(aData[2])});
		setDirectionData(aData[3]);
		setVisualData(aData[4]);
		return T;
	}
	
	@Override public byte getVisualData() {return (byte)(mOpened ? 1 : 0);}
	@Override public void setVisualData(byte aData) {mOpened = (aData != 0);}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		if (!aShouldSideBeRendered[aSide]) return null;
		int aIndex = aSide==mFacing?0:aSide==OPPOSITES[mFacing]?1:2;
		return BlockTextureMulti.get(BlockTextureDefault.get((mOpened?sColoredsOpen:sColoreds)[aIndex], mRGBa), BlockTextureDefault.get((mOpened?sOverlaysOpen:sOverlays)[aIndex]));
	}
	
	// Icons
	public static IIconContainer sColoreds[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/safes/keylocked/colored/front"),
		new Textures.BlockIcons.CustomIcon("machines/safes/keylocked/colored/back"),
		new Textures.BlockIcons.CustomIcon("machines/safes/keylocked/colored/side"),
	}, sOverlays[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/safes/keylocked/overlay/front"),
		new Textures.BlockIcons.CustomIcon("machines/safes/keylocked/overlay/back"),
		new Textures.BlockIcons.CustomIcon("machines/safes/keylocked/overlay/side"),
	}, sColoredsOpen[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/safes/keylocked/colored_open/front"),
		new Textures.BlockIcons.CustomIcon("machines/safes/keylocked/colored_open/back"),
		new Textures.BlockIcons.CustomIcon("machines/safes/keylocked/colored_open/side"),
	}, sOverlaysOpen[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/safes/keylocked/overlay_open/front"),
		new Textures.BlockIcons.CustomIcon("machines/safes/keylocked/overlay_open/back"),
		new Textures.BlockIcons.CustomIcon("machines/safes/keylocked/overlay_open/side"),
	};
	
	@Override public boolean allowCovers(byte aSide) {return aSide != mFacing;}
	
	@Override public String getTileEntityName() {return "gt6.multitileentity.safe.keylocked";}
}
