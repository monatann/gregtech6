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

package gregtech6.items.behaviors;

import static gregapi6.data.CS.*;

import java.util.List;

import gregapi6.data.LH;
import gregapi6.item.multiitem.MultiItem;
import gregapi6.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi6.util.WD;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class Behavior_SensorKit extends AbstractBehaviorDefault {
	@Override
	public boolean onItemUseFirst(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float hitX, float hitY, float hitZ) {
		if (aPlayer instanceof EntityPlayerMP) {
			TileEntity tTileEntity = WD.te(aWorld, aX, aY, aZ, T);
			if (tTileEntity instanceof IInventory && !((IInventory)tTileEntity).isUseableByPlayer(aPlayer)) return F;/*
			if (tTileEntity instanceof IGregTechDeviceInformation && ((IGregTechDeviceInformation)tTileEntity).isGivingInformation()) {
				UT.Stacks.set(aStack, IL.NC_SensorCard.get(aStack.stackSize));
				NBTTagCompound tNBT = aStack.getTagCompound();
				if (tNBT == null) tNBT = new NBTTagCompound();
				tNBT.setInteger("x", aX);
				tNBT.setInteger("y", aY);
				tNBT.setInteger("z", aZ);
				aStack.setTagCompound(tNBT);
			}*/
			return T;
		}
		return F;
	}
	
	static {
		LH.add("gt6.behaviour.sensorkit.tooltip", "Used to display Information using the Mod Nuclear Control");
	}
	
	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		aList.add(LH.get("gt6.behaviour.sensorkit.tooltip"));
		return aList;
	}
}
