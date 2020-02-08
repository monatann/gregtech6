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

import java.util.List;

import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_GetBlockHardness;
import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_GetExplosionResistance;
import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_GetLightOpacity;
import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_GetSubItems;
import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_SyncDataByte;
import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_SyncDataByteArray;
import gregapi6.block.multitileentity.MultiTileEntityBlockInternal;
import gregapi6.data.MT;
import gregapi6.item.IItemColorableRGB;
import gregapi6.network.INetworkHandler;
import gregapi6.network.IPacket;
import gregapi6.oredict.OreDictMaterial;
import gregapi6.tileentity.ITileEntityDecolorable;
import gregapi6.util.UT;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author Gregorius Techneticies
 */
public abstract class TileEntityBase07Paintable extends TileEntityBase06Covers implements IItemColorableRGB, ITileEntityDecolorable, IMTE_GetSubItems, IMTE_GetExplosionResistance, IMTE_GetBlockHardness, IMTE_GetLightOpacity, IMTE_SyncDataByte, IMTE_SyncDataByteArray {
	protected boolean mIsPainted = F;
	protected int mRGBa = UNCOLORED, mFlammability = 0;
	protected float mHardness = 3.0F, mResistance = 3.0F;
	protected OreDictMaterial mMaterial = MT.NULL;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_COLOR)) mRGBa = aNBT.getInteger(NBT_COLOR);
		if (aNBT.hasKey(NBT_PAINTED)) mIsPainted = aNBT.getBoolean(NBT_PAINTED);
		if (aNBT.hasKey(NBT_HARDNESS)) mHardness = aNBT.getFloat(NBT_HARDNESS);
		if (aNBT.hasKey(NBT_RESISTANCE)) mResistance = aNBT.getFloat(NBT_RESISTANCE);
		if (aNBT.hasKey(NBT_FLAMMABILITY)) mFlammability = aNBT.getInteger(NBT_FLAMMABILITY);
		if (aNBT.hasKey(NBT_MATERIAL)) mMaterial = OreDictMaterial.get(aNBT.getString(NBT_MATERIAL));
	}
	
	@Override
	public IPacket getClientDataPacket(boolean aSendAll) {
		return aSendAll ? getClientDataPacketByteArray(aSendAll, (byte)UT.Code.getR(mRGBa), (byte)UT.Code.getG(mRGBa), (byte)UT.Code.getB(mRGBa), getVisualData()) : getClientDataPacketByte(aSendAll, getVisualData());
	}
	
	@Override
	public boolean receiveDataByte(byte aData, INetworkHandler aNetworkHandler) {
		setVisualData(aData);
		return T;
	}
	
	@Override
	public boolean receiveDataByteArray(byte[] aData, INetworkHandler aNetworkHandler) {
		mRGBa = UT.Code.getRGBInt(new short[] {UT.Code.unsignB(aData[0]), UT.Code.unsignB(aData[1]), UT.Code.unsignB(aData[2])});
		setVisualData(aData[3]);
		return T;
	}
	
	@Override public boolean unpaint() {if (mIsPainted) {mIsPainted=F; mRGBa=UT.Code.getRGBInt(mMaterial.fRGBaSolid); updateClientData(); return T;} return F;}
	@Override public boolean isPainted() {return mIsPainted || (worldObj != null && isClientSide() && UT.Code.getRGBInt(mMaterial.fRGBaSolid) != mRGBa);}
	@Override public boolean paint(int aRGB) {if (aRGB!=mRGBa) {mRGBa=aRGB; mIsPainted=T; return T;} return F;}
	@Override public int getPaint() {return mRGBa;}
	@Override public boolean canRecolorItem(ItemStack aStack) {return T;}
	@Override public boolean canDecolorItem(ItemStack aStack) {return mIsPainted;}
	@Override public boolean recolorItem(ItemStack aStack, int aRGB) {if (paint((isPainted() ? UT.Code.mixRGBInt(aRGB, getPaint()) : aRGB) & ALL_NON_ALPHA_COLOR)) {UT.NBT.set(aStack, writeItemNBT(aStack.hasTagCompound() ? aStack.getTagCompound() : UT.NBT.make())); return T;} return F;}
	
	@Override
	public boolean decolorItem(ItemStack aStack) {
		if (unpaint()) {
			if (aStack.hasTagCompound()) {
				aStack.getTagCompound().removeTag(NBT_PAINTED);
				aStack.getTagCompound().removeTag(NBT_COLOR);
				UT.NBT.set(aStack, writeItemNBT(aStack.getTagCompound()));
			} else {
				UT.NBT.set(aStack, writeItemNBT(UT.NBT.make()));
			}
			return T;
		}
		return F;
	}
	
	@Override public int getLightOpacity() {return LIGHT_OPACITY_MAX;}
	@Override public int getFireSpreadSpeed(byte aSide, boolean aDefault) {return mFlammability;}
	@Override public int getFlammability(byte aSide, boolean aDefault) {return mFlammability;}
	@Override public float getBlockHardness() {return mHardness;}
	@Override public float getExplosionResistance2() {return mResistance;}
	@Override public boolean getSubItems(MultiTileEntityBlockInternal aBlock, Item aItem, CreativeTabs aTab, List<ItemStack> aList, short aID) {return showInCreative();}
	
	// Stuff to Override
	public byte getVisualData() {return 0;}
	public void setVisualData(byte aData) {/**/}
	public boolean showInCreative() {return SHOW_HIDDEN_MATERIALS || !mMaterial.mHidden;}
}
