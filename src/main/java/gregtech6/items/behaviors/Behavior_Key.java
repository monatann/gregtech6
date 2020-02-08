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
import gregapi6.item.multiitem.behaviors.IBehavior;
import gregapi6.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi6.tileentity.ITileEntityKeyInteractable;
import gregapi6.tileentity.delegate.DelegatorTileEntity;
import gregapi6.util.UT;
import gregapi6.util.WD;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class Behavior_Key extends AbstractBehaviorDefault {
	public static final IBehavior<MultiItem> INSTANCE = new Behavior_Key();
	
	@Override
	public boolean onItemUseFirst(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float hitX, float hitY, float hitZ) {
		if (aWorld.isRemote) return F;
		
		DelegatorTileEntity<TileEntity> aTileEntity = WD.te(aWorld, aX, aY, aZ, aSide, T);
		if (aTileEntity.mTileEntity instanceof ITileEntityKeyInteractable) {
			NBTTagCompound tNBT = aStack.getTagCompound();
			if (tNBT == null) tNBT = UT.NBT.make();
			if (!tNBT.hasKey(NBT_KEY)) tNBT.setLong(NBT_KEY, System.nanoTime());
			UT.NBT.set(aStack, tNBT);
			return ((ITileEntityKeyInteractable)aTileEntity.mTileEntity).useKey(aPlayer, aSide, hitX, hitY, hitZ, tNBT.getLong(NBT_KEY));
		}
		return F;
	}
	
	static {LH.add("gt6.behaviour.key", "Can open certain regular Locks");}
	
	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		aList.add(LH.get("gt6.behaviour.key"));
		NBTTagCompound tNBT = aStack.getTagCompound();
		if (tNBT != null && tNBT.hasKey(NBT_KEY)) aList.add("Key ID: " + tNBT.getLong(NBT_KEY)); else aList.add("*BLANK*");
		return aList;
	}
}
