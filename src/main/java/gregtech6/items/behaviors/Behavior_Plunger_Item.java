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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Behavior_Plunger_Item extends AbstractBehaviorDefault {
//  private final int mCosts;
	
	public Behavior_Plunger_Item(int aCosts) {
//      mCosts = aCosts;
	}
	
	@Override
	public boolean onItemUseFirst(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float hitX, float hitY, float hitZ) {
		if (aWorld.isRemote) return F;/*
		TileEntity aTileEntity = UT.Worlds.getTileEntity(aWorld, aX, aY, aZ, true);
		if (aTileEntity instanceof IGregTechTileEntity) {
			IMetaTileEntity tMetaTileEntity = ((IGregTechTileEntity)aTileEntity).getMetaTileEntity();
			if (tMetaTileEntity instanceof IMetaTileEntityItemPipe) {
				for (IMetaTileEntityItemPipe tTileEntity : UT.Code.sortByValuesAcending(IMetaTileEntityItemPipe.Util.scanPipes((IMetaTileEntityItemPipe)tMetaTileEntity, new HashMap<IMetaTileEntityItemPipe, Long>(), 0, false, true)).keySet()) {
					for (int i = 0, j = tTileEntity.getSizeInventory(); i < j; i++) if (tTileEntity.isValidSlot(i)) {
						if (tTileEntity.getStackInSlot(i) != null) {
							if (aPlayer.capabilities.isCreativeMode || ((ItemMetaTool)aItem).doDamage(aStack, mCosts)) {
								ItemStack tStack = tTileEntity.decrStackSize(i, 64);
								if (tStack != null) {
									EntityItem tEntity = new EntityItem(aWorld, ((IGregTechTileEntity)aTileEntity).getOffsetX((byte)aSide, 1) + 0.5, ((IGregTechTileEntity)aTileEntity).getOffsetY((byte)aSide, 1) + 0.5, ((IGregTechTileEntity)aTileEntity).getOffsetZ((byte)aSide, 1) + 0.5, tStack);
									tEntity.motionX = 0; tEntity.motionY = 0; tEntity.motionZ = 0;
									aWorld.spawnEntityInWorld(tEntity);
									UT.Sounds.send(aWorld, GregTech_API.sSoundList.get(101), 1.0F, -1, aX, aY, aZ);
								}
								return T;
							}
						}
					}
				}
			}
		}*/
		return F;
	}
	
	static {
		LH.add("gt6.behaviour.plunger.item", "Clears Items from Pipes");
	}
	
	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		aList.add(LH.get("gt6.behaviour.plunger.item"));
		return aList;
	}
}
