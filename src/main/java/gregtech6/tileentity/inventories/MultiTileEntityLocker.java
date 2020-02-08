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

import gregapi6.data.CS.SFX;
import gregapi6.old.Textures;
import gregapi6.render.BlockTextureDefault;
import gregapi6.render.BlockTextureMulti;
import gregapi6.render.IIconContainer;
import gregapi6.render.ITexture;
import gregapi6.tileentity.ITileEntityAdjacentInventoryUpdatable;
import gregapi6.tileentity.base.TileEntityBase09FacingSingle;
import gregapi6.tileentity.delegate.DelegatorTileEntity;
import gregapi6.tileentity.machines.ITileEntityRunningSuccessfully;
import gregapi6.util.ST;
import gregapi6.util.UT;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityLocker extends TileEntityBase09FacingSingle implements ITileEntityRunningSuccessfully {
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		super.onTick2(aTimer, aIsServerSide);
		if (aIsServerSide && mInventoryChanged) for (byte tSide : ALL_SIDES_VALID) {
			DelegatorTileEntity<TileEntity> tDelegator = getAdjacentTileEntity(tSide);
			if (tDelegator.mTileEntity instanceof ITileEntityAdjacentInventoryUpdatable) ((ITileEntityAdjacentInventoryUpdatable)tDelegator.mTileEntity).adjacentInventoryUpdated(tDelegator.mSideOfTileEntity, this);
		}
	}
	
	@Override
	public boolean onBlockActivated3(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (aSide != mFacing) return F;
		if (UT.Entities.isPlayer(aPlayer) && isServerSide()) {
			boolean temp = F;
			for (int i = 0; i < 4; i++) {
				ItemStack tStack = slot(i);
				if (tStack == null || tStack.getItem().isValidArmor(tStack, 3-i, aPlayer)) {
					slot(i, aPlayer.inventory.armorInventory[i]);
					aPlayer.inventory.armorInventory[i] = tStack;
					temp = T;
				}
			}
			if (temp) {
				if (aPlayer.openContainer != null) aPlayer.openContainer.detectAndSendChanges();
				updateInventory();
				UT.Sounds.send(SFX.MC_CLICK, aPlayer);
			}
		}
		return T;
	}
	
	@Override public boolean canDrop(int aSlot) {return T;}
	@Override public ItemStack[] getDefaultInventory(NBTTagCompound aNBT) {return new ItemStack[4];}
	@Override public int[] getAccessibleSlotsFromSide2(byte aSide) {return UT.Code.getAscendingArray(getSizeInventory());}
	@Override public boolean canInsertItem2 (int aSlot, ItemStack aStack, byte aSide) {if (ST.valid(aStack)) try {return aStack.getItem().isValidArmor(aStack, 3-aSlot, null);} catch(Throwable e) {e.printStackTrace(ERR);} return F;}
	@Override public boolean canExtractItem2(int aSlot, ItemStack aStack, byte aSide) {return T;}
	@Override public boolean getStateRunningPassively   () {return UT.Code.containsSomething(getInventory());}
	@Override public boolean getStateRunningPossible    () {return UT.Code.containsSomething(getInventory());}
	@Override public boolean getStateRunningActively    () {return UT.Code.containsSomething(getInventory());}
	@Override public boolean getStateRunningSuccessfully() {return UT.Code.containsSomething(getInventory());}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		if (!aShouldSideBeRendered[aSide]) return null;
		int aIndex = aSide<2?aSide:aSide==mFacing?2:aSide==OPPOSITES[mFacing]?3:4;
		return BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[aIndex], mRGBa), BlockTextureDefault.get(sOverlays[aIndex]));
	}
	
	// Icons
	public static IIconContainer sColoreds[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/lockers/normal/colored/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/lockers/normal/colored/top"),
		new Textures.BlockIcons.CustomIcon("machines/lockers/normal/colored/front"),
		new Textures.BlockIcons.CustomIcon("machines/lockers/normal/colored/back"),
		new Textures.BlockIcons.CustomIcon("machines/lockers/normal/colored/side"),
	}, sOverlays[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/lockers/normal/overlay/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/lockers/normal/overlay/top"),
		new Textures.BlockIcons.CustomIcon("machines/lockers/normal/overlay/front"),
		new Textures.BlockIcons.CustomIcon("machines/lockers/normal/overlay/back"),
		new Textures.BlockIcons.CustomIcon("machines/lockers/normal/overlay/side"),
	};
	
	@Override public String getTileEntityName() {return "gt6.multitileentity.locker.normal";}
}
