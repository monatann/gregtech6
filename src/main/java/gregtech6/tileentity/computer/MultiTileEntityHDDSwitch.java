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

package gregtech6.tileentity.computer;

import static gregapi6.data.CS.*;

import java.util.List;

import gregapi6.data.LH;
import gregapi6.data.LH.Chat;
import gregapi6.gui.ContainerClientDefault;
import gregapi6.gui.ContainerCommonDefault;
import gregapi6.old.Textures;
import gregapi6.render.BlockTextureDefault;
import gregapi6.render.BlockTextureMulti;
import gregapi6.render.IIconContainer;
import gregapi6.render.ITexture;
import gregapi6.tileentity.computer.TileEntityBase08DataSwitch;
import gregapi6.util.OM;
import gregapi6.util.UT;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityHDDSwitch extends TileEntityBase08DataSwitch {
	static {
		LH.add("gt6.multitileentity.hdd.switch.tooltip", "Switches between the 16 Data Slots using Selector Covers");
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN + LH.get("gt6.multitileentity.hdd.switch.tooltip"));
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	@Override
	public NBTTagCompound getUSBData(byte aSide, int aUSBTier) {
		ItemStack tDrive = slot(0);
		if (OM.is(OD_USB_DRIVES[aUSBTier], tDrive) && tDrive.hasTagCompound()) {
			NBTTagCompound tDriveData = tDrive.getTagCompound().getCompoundTag(NBT_USB_DRIVE);
			if (tDriveData.getByte(NBT_USB_TIER+mMode) <= aUSBTier) return tDriveData.hasKey(NBT_USB_DATA+mMode) ? tDriveData.getCompoundTag(NBT_USB_DATA+mMode) : null;
		}
		return null;
	}
	
	@Override
	public boolean setUSBData(byte aSide, int aUSBTier, NBTTagCompound aData) {
		ItemStack tDrive = slot(0);
		if (OM.is(OD_USB_DRIVES[aUSBTier], tDrive)) {
			if (!tDrive.hasTagCompound()) tDrive.setTagCompound(UT.NBT.make());
			NBTTagCompound tDriveData = tDrive.getTagCompound().getCompoundTag(NBT_USB_DRIVE);
			if (aData == null || aData.hasNoTags()) {
				tDriveData.removeTag(NBT_USB_DATA+mMode);
				tDriveData.removeTag(NBT_USB_TIER+mMode);
			} else {
				tDriveData.setTag(NBT_USB_DATA+mMode, aData);
				tDriveData.setByte(NBT_USB_TIER+mMode, (byte)aUSBTier);
			}
			if (tDriveData.hasNoTags()) {
				tDrive.getTagCompound().removeTag(NBT_USB_DRIVE);
			} else {
				tDrive.getTagCompound().setTag(NBT_USB_DRIVE, tDriveData);
			}
			if (tDrive.getTagCompound().hasNoTags()) tDrive.setTagCompound(null);
			return T;
		}
		return F;
	}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		return aShouldSideBeRendered[aSide] ? BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[(int)UT.Code.bind_(0, 2, aSide)], mRGBa), BlockTextureDefault.get(sOverlays[(int)UT.Code.bind_(0, 2, aSide)])) : null;
	}
	
	// Icons
	public static IIconContainer sColoreds[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/hdd/switch/colored/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/hdd/switch/colored/top"),
		new Textures.BlockIcons.CustomIcon("machines/hdd/switch/colored/side"),
	}, sOverlays[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/hdd/switch/overlay/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/hdd/switch/overlay/top"),
		new Textures.BlockIcons.CustomIcon("machines/hdd/switch/overlay/side"),
	};
	
	@Override public String getTileEntityName() {return "gt6.multitileentity.hdd.switch";}
	
	@Override public ItemStack[] getDefaultInventory(NBTTagCompound aNBT) {return new ItemStack[1];}
	@Override public boolean canInsertItem2(int aSlot, ItemStack aStack, byte aSide) {return OM.is(OD_USB_DRIVES[0], aStack);}
	
	@Override public Object getGUIClient2(int aGUIID, EntityPlayer aPlayer) {return new ContainerClientDefault(aPlayer.inventory, this, aGUIID, RES_PATH_GUI + "machines/HDDSwitch.png");}
	@Override public Object getGUIServer2(int aGUIID, EntityPlayer aPlayer) {return new ContainerCommonDefault(aPlayer.inventory, this, aGUIID);}
}
