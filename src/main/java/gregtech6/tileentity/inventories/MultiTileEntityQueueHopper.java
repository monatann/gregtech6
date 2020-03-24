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

import gregapi6.block.multitileentity.MultiTileEntityContainer;
import gregapi6.data.LH;
import gregapi6.data.LH.Chat;
import gregapi6.data.TD;
import gregapi6.gui.ContainerClientDefault;
import gregapi6.gui.ContainerCommonDefault;
import gregapi6.old.Textures;
import gregapi6.render.BlockTextureDefault;
import gregapi6.render.BlockTextureMulti;
import gregapi6.render.IIconContainer;
import gregapi6.render.ITexture;
import gregapi6.tileentity.ITileEntityAdjacentInventoryUpdatable;
import gregapi6.tileentity.base.TileEntityBase09FacingSingle;
import gregapi6.tileentity.connectors.ITileEntityConnector;
import gregapi6.tileentity.delegate.DelegatorTileEntity;
import gregapi6.util.ST;
import gregapi6.util.UT;
import gregapi6.util.WD;
import gregtech6.tileentity.tools.MultiTileEntityAnvil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRailBase;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityQueueHopper extends TileEntityBase09FacingSingle implements ITileEntityAdjacentInventoryUpdatable {
	public boolean mCheckNextTick = T, mMovedLastTick = T;
	public byte mMode = 64;

	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		mMode = aNBT.getByte(NBT_MODE);
		if (mMode <= 0) mMode = 64;
	}

	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		if (mMode != 64) aNBT.setByte(NBT_MODE, mMode);
	}

	@Override
	public NBTTagCompound writeItemNBT2(NBTTagCompound aNBT) {
		aNBT = super.writeItemNBT2(aNBT);
		if (mMode != 64) aNBT.setByte(NBT_MODE, mMode);
		return aNBT;
	}

	static {
		LH.add("gt6.multitileentity.hopper.tooltip.1", "Slot Count: ");
		LH.add("gt6.multitileentity.hopper.tooltip.2", "Slot Size: ");
	}

	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get("gt6.multitileentity.hopper.tooltip.1") + getSizeInventory());
		aList.add(Chat.CYAN     + LH.get("gt6.multitileentity.hopper.tooltip.2") + mMode);
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_TOGGLE_SCREWDRIVER));
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_DETAIL_MAGNIFYINGGLASS));
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_RESET_SOFT_HAMMER));
		super.addToolTips(aList, aStack, aF3_H);
	}

	@Override
	public boolean onBlockActivated3(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isServerSide() && isUseableByPlayerGUI(aPlayer)) openGUI(aPlayer);
		return T;
	}

	@Override
	public boolean onPlaced(ItemStack aStack, EntityPlayer aPlayer, MultiTileEntityContainer aMTEContainer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ) {
		super.onPlaced(aStack, aPlayer, aMTEContainer, aWorld, aX, aY, aZ, aSide, aHitX, aHitY, aHitZ);
		if (isServerSide() && SIDES_BOTTOM_HORIZONTAL[mFacing]) {
			DelegatorTileEntity<TileEntity> tDelegator = getAdjacentTileEntity(mFacing);
			if (tDelegator.mTileEntity instanceof ITileEntityConnector && SIDES_VALID[tDelegator.mSideOfTileEntity] && ((ITileEntityConnector)tDelegator.mTileEntity).allowInteraction(aPlayer) && UT.Code.haveOneCommonElement(((ITileEntityConnector)tDelegator.mTileEntity).getConnectorTypes(tDelegator.mSideOfTileEntity), TD.Connectors.ALL_ITEM_TRANSPORT)) {
				((ITileEntityConnector)tDelegator.mTileEntity).connect(tDelegator.mSideOfTileEntity, T);
			}
		}
		return T;
	}

	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isClientSide()) return super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
		if (aTool.equals(TOOL_screwdriver)) {
			if (aPlayer != null && aPlayer.isSneaking()) {
				if (--mMode <  1) mMode = 64;
			} else {
				if (++mMode > 64) mMode =  1;
			}
			if (aChatReturn != null) aChatReturn.add("Max Stacksize: " + mMode);
			return 200;
		}
		if (aTool.equals(TOOL_softhammer)) {
			mMode = 64;
			if (aChatReturn != null) aChatReturn.add("Max Stacksize: " + mMode);
			return 10000;
		}
		if (aTool.equals(TOOL_magnifyingglass)) {
			if (aChatReturn != null) aChatReturn.add("Max Stacksize: " + mMode);
			return 1;
		}
		return super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
	}

	@Override
	public void onWalkOver2(EntityLivingBase aEntity) {
		if (isServerSide() && (aEntity.getClass() == EntitySnowman.class || "EntityNewSnowGolem".equalsIgnoreCase(UT.Reflection.getLowercaseClass(aEntity)))) {
			addStackToSlot(getSizeInventory()-1, ST.make(Items.snowball, 1, 0));
		}
	}

	@Override
	@SuppressWarnings("rawtypes")
	public void onTick2(long aTimer, boolean aIsServerSide) {
		super.onTick2(aTimer, aIsServerSide);
		if (aIsServerSide) {
			int tMovedItems = 0;
			if (mMovedLastTick) {
				mMovedLastTick = F;
			} else if ((mInventoryChanged || mCheckNextTick || mBlockUpdated || aTimer % 64 == 32) && !hasRedstoneIncoming()) {
				mCheckNextTick = F;
				if (!SIDES_TOP[mFacing] && !invempty()) {
					DelegatorTileEntity tDelegator = getAdjacentTileEntity(mFacing);
					if (tDelegator.getBlock() instanceof BlockRailBase) {
						List tList = worldObj.getEntitiesWithinAABBExcludingEntity(null, tDelegator.box(0, 0, 0, 1, 1, 1), IEntitySelector.selectInventories);
						if (tList != null && !tList.isEmpty()) tDelegator = new DelegatorTileEntity<>((IInventory)tList.get(0), tDelegator);
					}
					while (tMovedItems < mMode) {
						int tMoved = ST.move(delegator(mFacing), tDelegator, null, F, F, F, T, mMode, 1, mMode-tMovedItems, 1);
						if (tMoved <= 0) break;
						tMovedItems += tMoved;
					}
				}
				DelegatorTileEntity tDelegator = getAdjacentTileEntity(SIDE_TOP);
				if (tDelegator.getBlock() instanceof BlockRailBase) {
					List tList = worldObj.getEntitiesWithinAABBExcludingEntity(null, tDelegator.box(0, 0, 0, 1, 1, 1), IEntitySelector.selectInventories);
					if (tList != null && !tList.isEmpty()) tDelegator = new DelegatorTileEntity<>((IInventory)tList.get(0), tDelegator);
				}
				if (tDelegator.mTileEntity != null && !(tDelegator.mTileEntity instanceof MultiTileEntityAnvil)) {
					tMovedItems += ST.move(tDelegator, delegator(SIDE_TOP));
				} else {
					if (!WD.visOpq(tDelegator.getWorld(), tDelegator.getX(), tDelegator.getY(), tDelegator.getZ(), F, T)) {
						int i = getSizeInventory()-1;
						if (!slotHas(i)) {
							slot(i, WD.suck(tDelegator));
							if (slotHas(i)) {
								tMovedItems += slot(i).stackSize;
								updateInventory();
							}
						}
					}
				}
				if (tMovedItems > 0) {
					mMovedLastTick = T;
				}
			}

			if (mInventoryChanged) {
				int oMovedItems = -1;
				while (oMovedItems != tMovedItems) {
					oMovedItems = tMovedItems;
					for (int i = 1, j = getSizeInventory(); i < j; i++) {
						tMovedItems += ST.move(this, i-1, i);
					}
				}
			}
			if (tMovedItems > 0) {
				mCheckNextTick = T;
				for (byte tSide : ALL_SIDES_BUT_TOP) if (tSide != mFacing) {
					DelegatorTileEntity<TileEntity> tDelegatorUpdate = getAdjacentTileEntity(tSide);
					if (tDelegatorUpdate.mTileEntity instanceof ITileEntityAdjacentInventoryUpdatable) {
						((ITileEntityAdjacentInventoryUpdatable)tDelegatorUpdate.mTileEntity).adjacentInventoryUpdated(tDelegatorUpdate.mSideOfTileEntity, this);
					}
				}
			}
		}
	}

	@Override public float getSurfaceDistance       (byte aSide) {return 0.0F;}
	@Override public float getSurfaceSize           (byte aSide) {return SIDES_TOP[aSide]?PX_N[0]:PX_N[8];}
	@Override public float getSurfaceSizeAttachable (byte aSide) {return SIDES_TOP[aSide]?PX_N[2]:PX_N[8];}
	@Override public boolean isSurfaceSolid         (byte aSide) {return SIDES_TOP[aSide];}
	@Override public boolean isSurfaceOpaque2       (byte aSide) {return SIDES_TOP[aSide];}
	@Override public boolean isSideSolid2           (byte aSide) {return SIDES_TOP[aSide];}
	@Override public boolean allowCovers            (byte aSide) {return SIDES_TOP[aSide];}

	@Override public int[] getAccessibleSlotsFromSide2(byte aSide) {return new int[] {0, getSizeInventory() - 1};}
	@Override public boolean canInsertItem2(int aSlot, ItemStack aStack, byte aSide) {return aSlot == 0;}
	@Override public boolean canExtractItem2(int aSlot, ItemStack aStack, byte aSide) {return aSlot == getSizeInventory() - 1;}
	@Override public int getInventoryStackLimit() {return mMode;}
	@Override public int getInventoryStackLimitGUI(int aSlot) {return mMode;}
	@Override public boolean canDrop(int aInventorySlot) {return T;}

	@Override public void adjacentInventoryUpdated(byte aSide, IInventory aTileEntity) {if (SIDES_TOP[aSide] || aSide == mFacing) mCheckNextTick = T;}

	@Override public byte getDefaultSide() {return SIDE_BOTTOM;}
	@Override public boolean[] getValidSides() {return SIDES_VALID;}
	@Override public boolean useSidePlacementRotation       () {return T;}
	@Override public boolean useInversePlacementRotation    () {return T;}

	@Override public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered) {return SIDES_TOP[mFacing] ? 2 : 3;}
	@Override public boolean usesRenderPass2(int aRenderPass, boolean[] aShouldSideBeRendered) {return T;}

	@Override
	public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		switch(aRenderPass) {
		case 0: box(aBlock, PX_P[ 0], PX_P[10], PX_P[ 0], PX_N[ 0], PX_N[ 0], PX_N[ 0]); return T;
		case 1: box(aBlock, PX_P[ 4], PX_P[ 4], PX_P[ 4], PX_N[ 4], PX_N[ 6], PX_N[ 4]); return T;
		case 2:
			switch(mFacing) {
			case SIDE_Y_NEG: box(aBlock, PX_P[ 6], PX_P[ 0], PX_P[ 6], PX_N[ 6], PX_N[12], PX_N[ 6]); return T;
			case SIDE_Z_NEG: box(aBlock, PX_P[ 6], PX_P[ 4], PX_P[ 0], PX_N[ 6], PX_N[ 8], PX_N[12]); return T;
			case SIDE_Z_POS: box(aBlock, PX_P[ 6], PX_P[ 4], PX_P[12], PX_N[ 6], PX_N[ 8], PX_N[ 0]); return T;
			case SIDE_X_NEG: box(aBlock, PX_P[ 0], PX_P[ 4], PX_P[ 6], PX_N[12], PX_N[ 8], PX_N[ 6]); return T;
			case SIDE_X_POS: box(aBlock, PX_P[12], PX_P[ 4], PX_P[ 6], PX_N[ 0], PX_N[ 8], PX_N[ 6]); return T;
			}
		}
		return T;
	}

	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		return (aRenderPass == 1 ? !SIDES_TOP[aSide] : aShouldSideBeRendered[aSide] || (aRenderPass == 0 && SIDES_BOTTOM[aSide]) || (aRenderPass == 2 && aSide != mFacing)) ? BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[FACES_TBS[aSide]], mRGBa), BlockTextureDefault.get(sOverlays[FACES_TBS[aSide]])) : null;
	}

	// Icons
	public static IIconContainer sColoreds[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/automation/queuehopper/colored/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/automation/queuehopper/colored/top"),
		new Textures.BlockIcons.CustomIcon("machines/automation/queuehopper/colored/side"),
	}, sOverlays[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/automation/queuehopper/overlay/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/automation/queuehopper/overlay/top"),
		new Textures.BlockIcons.CustomIcon("machines/automation/queuehopper/overlay/side"),
	};

	@Override public String getTileEntityName() {return "gt6.multitileentity.queuehopper";}

	@Override public int getLightOpacity() {return LIGHT_OPACITY_WATER;}

	@Override public Object getGUIClient2(int aGUIID, EntityPlayer aPlayer) {return new ContainerClientDefault(aPlayer.inventory, this, aGUIID);}
	@Override public Object getGUIServer2(int aGUIID, EntityPlayer aPlayer) {return new ContainerCommonDefault(aPlayer.inventory, this, aGUIID);}
}
