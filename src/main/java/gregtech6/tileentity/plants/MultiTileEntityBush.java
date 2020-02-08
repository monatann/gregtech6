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

package gregtech6.tileentity.plants;

import static gregapi6.data.CS.*;

import java.util.List;

import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_CanPlace;
import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_GetSelectedBoundingBoxFromPool;
import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_IsLeaves;
import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_OnOxygenRemoved;
import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_SetBlockBoundsBasedOnState;
import gregapi6.block.multitileentity.MultiTileEntityContainer;
import gregapi6.data.CS.BlocksGT;
import gregapi6.data.CS.BushesGT;
import gregapi6.data.IL;
import gregapi6.data.LH;
import gregapi6.data.OP;
import gregapi6.network.INetworkHandler;
import gregapi6.network.IPacket;
import gregapi6.old.Textures;
import gregapi6.oredict.OreDictItemData;
import gregapi6.render.BlockTextureDefault;
import gregapi6.render.BlockTextureMulti;
import gregapi6.render.IIconContainer;
import gregapi6.render.ITexture;
import gregapi6.tileentity.ITileEntityQuickObstructionCheck;
import gregapi6.tileentity.base.TileEntityBase09FacingSingle;
import gregapi6.util.OM;
import gregapi6.util.ST;
import gregapi6.util.UT;
import gregapi6.util.WD;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityBush extends TileEntityBase09FacingSingle implements ITileEntityQuickObstructionCheck, IMTE_IsLeaves, IMTE_OnOxygenRemoved, IMTE_CanPlace, IMTE_GetSelectedBoundingBoxFromPool, IMTE_SetBlockBoundsBasedOnState {
	public ItemStack mBerry;
	public byte oStage = 0, mStage = 0, mGrowth = 0, mSpeed = 0;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		mBerry = ST.load(aNBT, NBT_VALUE);
		mStage = aNBT.getByte(NBT_STATE);
		mGrowth = aNBT.getByte(NBT_PROGRESS);
		super.readFromNBT2(aNBT);
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		aNBT.setByte(NBT_STATE, mStage);
		aNBT.setByte(NBT_PROGRESS, mGrowth);
		ST.save(aNBT, NBT_VALUE, mBerry);
	}
	
	@Override
	public NBTTagCompound writeItemNBT2(NBTTagCompound aNBT) {
		super.writeItemNBT2(aNBT);
		ST.save(aNBT, NBT_VALUE, mBerry);
		return aNBT;
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(LH.Chat.CYAN + (ST.valid(mBerry)?mBerry.getDisplayName():"Rightclick with a Berry to set Output"));
	}
	
	@Override
	public void onOxygenRemoved() {
		if (isServerSide() && !WD.oxygen(worldObj, xCoord, yCoord, zCoord)) setToAir();
	}
	
	@Override
	public void onTickFirst2(boolean aIsServerSide) {
		super.onTickFirst2(aIsServerSide);
		if (getBlockAtSide(SIDE_UP) == Blocks.snow_layer) worldObj.setBlockToAir(xCoord, yCoord+1, zCoord);
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		super.onTick2(aTimer, aIsServerSide);
		if (aIsServerSide) {
			if (SERVER_TIME % 128 == 0) {
				if (WD.oxygen(worldObj, xCoord, yCoord, zCoord)) {
					if (SIDES_VALID[mFacing]) {
						TileEntity tTileEntity = getTileEntityAtSideAndDistance(mFacing, 1);
						if (tTileEntity instanceof MultiTileEntityBush) {
							if (!ST.equal(((MultiTileEntityBush)tTileEntity).mBerry, mBerry, F)) {
								mBerry = ((MultiTileEntityBush)tTileEntity).mBerry;
								updateClientData();
							}
							mSpeed = ((MultiTileEntityBush)tTileEntity).mSpeed;
						} else {
							mSpeed = 0;
						}
					} else {
						Block tBlock = getBlockAtSide(SIDE_BOTTOM);
						mSpeed = (byte)(IL.AETHER_Grass_Enchanted.equal(tBlock) || IL.AETHER_Grass_Enchanted_Vanilla.equal(tBlock) ? 2 : BlocksGT.plantableGreens.contains(tBlock) || tBlock.canSustainPlant(worldObj, xCoord, yCoord-1, zCoord, FORGE_DIR[SIDE_UP], Blocks.yellow_flower) ? 1 : 0);
					}
					if (mSpeed > 0 && mStage < 3 && ST.valid(mBerry)) {
						// Yes I know I should have chosen a better type of Timer than a byte overflow Timer.
						if (getSkyOffset(0, 1, 0)) {
							for (int i = 0; i < mSpeed; i++) if (++mGrowth == 0) mStage++;
							if (worldObj.isRaining() && getRainOffset(OFFSETS_X[mFacing], SIDES_TOP[mFacing]?1:2, OFFSETS_Z[mFacing])) for (int i = 0; i < mSpeed; i++) if (++mGrowth == 0) mStage++;
						} else {
							if (getLightLevelOffset(OFFSETS_X[mFacing], SIDES_TOP[mFacing]?1:2, OFFSETS_Z[mFacing]) > 9) for (int i = 0; i < mSpeed; i++) if (++mGrowth == 0) mStage++;
						}
					}
				} else {
					setToAir();
				}
			}
		}
	}
	
	@Override
	public boolean onTickCheck(long aTimer) {
		return super.onTickCheck(aTimer) || mStage != oStage;
	}
	
	@Override
	public void onTickResetChecks(long aTimer, boolean aIsServerSide) {
		super.onTickResetChecks(aTimer, aIsServerSide);
		oStage = mStage;
	}
	
	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isClientSide()) return super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
		if (aTool.equals(TOOL_magnifyingglass)) {
			if (aChatReturn != null && ST.valid(mBerry)) aChatReturn.add("Grows " + mBerry.getDisplayName());
			return 1;
		}
		return super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
	}
	
	@Override
	public boolean onBlockActivated3(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isClientSide()) return T;
		if (ST.valid(mBerry)) {
			if (mStage < 3) return F;
			UT.Inventories.addStackToPlayerInventoryOrDrop(aPlayer, ST.amount(1+rng(2), mBerry), T, worldObj, getOffsetX(aSide), getOffsetY(aSide), getOffsetZ(aSide));
			mStage = 0;
			return T;
		}
		ItemStack aStack = aPlayer.getCurrentEquippedItem();
		if (OP.plantGtBerry.contains(aStack) || BushesGT.get(aStack) != null) {
			mBerry = ST.amount(1, aStack);
			updateClientData();
			return T;
		}
		return F;
	}
	
	@Override
	public boolean canPlace(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ) {
		TileEntity tTileEntity = aWorld.getTileEntity(aX-OFFSETS_X[aSide], aY-OFFSETS_Y[aSide], aZ-OFFSETS_Z[aSide]);
		if (tTileEntity instanceof MultiTileEntityBush && SIDES_INVALID[((MultiTileEntityBush)tTileEntity).mFacing] && (ST.invalid(mBerry) || ST.equal(((MultiTileEntityBush)tTileEntity).mBerry, mBerry, F))) {
			mFacing = OPPOSITES[aSide];
			mBerry = ((MultiTileEntityBush)tTileEntity).mBerry;
			updateClientData();
			return T;
		}
		Block tBlock = aWorld.getBlock(aX, aY-1, aZ);
		if (BlocksGT.plantableGreens.contains(tBlock) || tBlock.canSustainPlant(aWorld, aX, aY-1, aZ, FORGE_DIR[SIDE_UP], Blocks.yellow_flower)) {
			mFacing = SIDE_UNDEFINED;
			mSpeed = 1;
			return T;
		}
		return F;
	}
	
	@Override
	public boolean onPlaced(ItemStack aStack, EntityPlayer aPlayer, MultiTileEntityContainer aMTEContainer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ) {
		return T;
	}
	
	public static final IIconContainer
	sTextureBush        = new Textures.BlockIcons.CustomIcon("machines/plants/bush/colored/bush"),
	sTextureBerry       = new Textures.BlockIcons.CustomIcon("machines/plants/bush/colored/berries"),
	sTextureImmature    = new Textures.BlockIcons.CustomIcon("machines/plants/bush/colored/berries_immature"),
	sOverlayBush        = new Textures.BlockIcons.CustomIcon("machines/plants/bush/overlay/bush"),
	sOverlayBerry       = new Textures.BlockIcons.CustomIcon("machines/plants/bush/overlay/berries"),
	sOverlayImmature    = new Textures.BlockIcons.CustomIcon("machines/plants/bush/overlay/berries_immature");
	
	private ITexture mTexture;
	
	@Override
	public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered) {
		if (ST.valid(mBerry)) {
			OreDictItemData tData = OM.anydata(mBerry);
			if (tData != null && tData.mPrefix == OP.plantGtBerry) {
				if (aShouldSideBeRendered == SIDES_ITEM_RENDER) {
					mTexture = BlockTextureMulti.get(BlockTextureDefault.get(sTextureBush, 0x009000), BlockTextureDefault.get(sOverlayBush), BlockTextureDefault.get(sTextureBerry, tData.mMaterial.mMaterial.fRGBaSolid), BlockTextureDefault.get(sOverlayBerry));
				} else {
					switch(mStage) {
					case 0: mTexture = BlockTextureMulti.get(BlockTextureDefault.get(sTextureBush, 0x009000), BlockTextureDefault.get(sOverlayBush)); break;
					case 1: mTexture = BlockTextureMulti.get(BlockTextureDefault.get(sTextureBush, 0x009000), BlockTextureDefault.get(sOverlayBush), BlockTextureDefault.get(sTextureImmature   , 0xff9090), BlockTextureDefault.get(sOverlayImmature)); break;
					case 2: mTexture = BlockTextureMulti.get(BlockTextureDefault.get(sTextureBush, 0x009000), BlockTextureDefault.get(sOverlayBush), BlockTextureDefault.get(sTextureBerry      , 0x80ff80), BlockTextureDefault.get(sOverlayBerry)); break;
					case 3: mTexture = BlockTextureMulti.get(BlockTextureDefault.get(sTextureBush, 0x009000), BlockTextureDefault.get(sOverlayBush), BlockTextureDefault.get(sTextureBerry      , tData.mMaterial.mMaterial.fRGBaSolid), BlockTextureDefault.get(sOverlayBerry)); break;
					}
				}
			} else {
				int[] tBerryColor = BushesGT.get(mBerry);
				if (tBerryColor == null) tBerryColor = new int[] {0x0000ff, 0xff00ff, 0xff00ff, 0xff00ff};
				if (aShouldSideBeRendered == SIDES_ITEM_RENDER) {
					mTexture = BlockTextureMulti.get(BlockTextureDefault.get(sTextureBush, tBerryColor[0]), BlockTextureDefault.get(sOverlayBush), BlockTextureDefault.get(sTextureBerry, tBerryColor[2]), BlockTextureDefault.get(sOverlayBerry));
				} else {
					switch(mStage) {
					case 0: mTexture = BlockTextureMulti.get(BlockTextureDefault.get(sTextureBush, tBerryColor[0]), BlockTextureDefault.get(sOverlayBush)); break;
					case 1: mTexture = BlockTextureMulti.get(BlockTextureDefault.get(sTextureBush, tBerryColor[0]), BlockTextureDefault.get(sOverlayBush), BlockTextureDefault.get(sTextureImmature , tBerryColor[1]), BlockTextureDefault.get(sOverlayImmature)); break;
					case 2: mTexture = BlockTextureMulti.get(BlockTextureDefault.get(sTextureBush, tBerryColor[0]), BlockTextureDefault.get(sOverlayBush), BlockTextureDefault.get(sTextureBerry    , tBerryColor[2]), BlockTextureDefault.get(sOverlayBerry)); break;
					case 3: mTexture = BlockTextureMulti.get(BlockTextureDefault.get(sTextureBush, tBerryColor[0]), BlockTextureDefault.get(sOverlayBush), BlockTextureDefault.get(sTextureBerry    , tBerryColor[3]), BlockTextureDefault.get(sOverlayBerry)); break;
					}
				}
			}
		} else {
			mTexture = BlockTextureMulti.get(BlockTextureDefault.get(sTextureBush, 0xff00ff), BlockTextureDefault.get(sOverlayBush));
		}
		return 1;
	}
	
	@Override
	public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		switch(mFacing) {
		case SIDE_X_POS: box(aBlock, PX_P[12], PX_P[ 2], PX_P[ 2], PX_N[ 0], PX_N[ 2], PX_N[ 2]); return T;
		case SIDE_Y_POS: box(aBlock, PX_P[ 2], PX_P[12], PX_P[ 2], PX_N[ 2], PX_N[ 0], PX_N[ 2]); return T;
		case SIDE_Z_POS: box(aBlock, PX_P[ 2], PX_P[ 2], PX_P[12], PX_N[ 2], PX_N[ 2], PX_N[ 0]); return T;
		case SIDE_X_NEG: box(aBlock, PX_P[ 0], PX_P[ 2], PX_P[ 2], PX_N[12], PX_N[ 2], PX_N[ 2]); return T;
		case SIDE_Y_NEG: box(aBlock, PX_P[ 2], PX_P[ 0], PX_P[ 2], PX_N[ 2], PX_N[12], PX_N[ 2]); return T;
		case SIDE_Z_NEG: box(aBlock, PX_P[ 2], PX_P[ 2], PX_P[ 0], PX_N[ 2], PX_N[ 2], PX_N[12]); return T;
		}
		return F;
	}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		return aShouldSideBeRendered[aSide] || SIDES_VALID[mFacing] ? mTexture : null;
	}
	
	@Override
	public IPacket getClientDataPacket(boolean aSendAll) {
		short tID = ST.id(mBerry), tMeta = tID>0?ST.meta_(mBerry):0;
		return aSendAll ? getClientDataPacketByteArray(aSendAll, getDirectionData(), getVisualData(), UT.Code.toByteS(tID, 0), UT.Code.toByteS(tID, 1), UT.Code.toByteS(tMeta, 0), UT.Code.toByteS(tMeta, 1)) : getClientDataPacketByte(aSendAll, getVisualData());
	}
	
	@Override
	public boolean receiveDataByteArray(byte[] aData, INetworkHandler aNetworkHandler) {
		setDirectionData(aData[0]);
		setVisualData(aData[1]);
		mBerry = ST.make(UT.Code.combine(aData[2], aData[3]), 1, UT.Code.combine(aData[4], aData[5]));
		return T;
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool() {
		switch(mFacing) {
		case SIDE_X_POS: return box(PX_P[14], PX_P[ 2], PX_P[ 2], PX_N[ 0], PX_N[ 2], PX_N[ 2]);
		case SIDE_Y_POS: return box(PX_P[ 2], PX_P[14], PX_P[ 2], PX_N[ 2], PX_N[ 0], PX_N[ 2]);
		case SIDE_Z_POS: return box(PX_P[ 2], PX_P[ 2], PX_P[14], PX_N[ 2], PX_N[ 2], PX_N[ 0]);
		case SIDE_X_NEG: return box(PX_P[ 0], PX_P[ 2], PX_P[ 2], PX_N[14], PX_N[ 2], PX_N[ 2]);
		case SIDE_Y_NEG: return box(PX_P[ 2], PX_P[ 0], PX_P[ 2], PX_N[ 2], PX_N[14], PX_N[ 2]);
		case SIDE_Z_NEG: return box(PX_P[ 2], PX_P[ 2], PX_P[ 0], PX_N[ 2], PX_N[ 2], PX_N[14]);
		}
		return box();
	}
	
	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool() {
		switch(mFacing) {
		case SIDE_X_POS: return box(PX_P[12], PX_P[ 2], PX_P[ 2], PX_N[ 0], PX_N[ 2], PX_N[ 2]);
		case SIDE_Y_POS: return box(PX_P[ 2], PX_P[12], PX_P[ 2], PX_N[ 2], PX_N[ 0], PX_N[ 2]);
		case SIDE_Z_POS: return box(PX_P[ 2], PX_P[ 2], PX_P[12], PX_N[ 2], PX_N[ 2], PX_N[ 0]);
		case SIDE_X_NEG: return box(PX_P[ 0], PX_P[ 2], PX_P[ 2], PX_N[12], PX_N[ 2], PX_N[ 2]);
		case SIDE_Y_NEG: return box(PX_P[ 2], PX_P[ 0], PX_P[ 2], PX_N[ 2], PX_N[12], PX_N[ 2]);
		case SIDE_Z_NEG: return box(PX_P[ 2], PX_P[ 2], PX_P[ 0], PX_N[ 2], PX_N[ 2], PX_N[12]);
		}
		return box();
	}
	
	@Override
	public void setBlockBoundsBasedOnState(Block aBlock) {
		switch(mFacing) {
		case SIDE_X_POS: box(aBlock, PX_P[12], PX_P[ 2], PX_P[ 2], PX_N[ 0], PX_N[ 2], PX_N[ 2]); return;
		case SIDE_Y_POS: box(aBlock, PX_P[ 2], PX_P[12], PX_P[ 2], PX_N[ 2], PX_N[ 0], PX_N[ 2]); return;
		case SIDE_Z_POS: box(aBlock, PX_P[ 2], PX_P[ 2], PX_P[12], PX_N[ 2], PX_N[ 2], PX_N[ 0]); return;
		case SIDE_X_NEG: box(aBlock, PX_P[ 0], PX_P[ 2], PX_P[ 2], PX_N[12], PX_N[ 2], PX_N[ 2]); return;
		case SIDE_Y_NEG: box(aBlock, PX_P[ 2], PX_P[ 0], PX_P[ 2], PX_N[ 2], PX_N[12], PX_N[ 2]); return;
		case SIDE_Z_NEG: box(aBlock, PX_P[ 2], PX_P[ 2], PX_P[ 0], PX_N[ 2], PX_N[ 2], PX_N[12]); return;
		}
		box(aBlock, PX_P[0], PX_P[0], PX_P[0], PX_N[0], PX_N[0], PX_N[0]);
	}
	
	@Override public boolean isSurfaceSolid         (byte aSide) {return F;}
	@Override public boolean isSurfaceOpaque2       (byte aSide) {return F;}
	@Override public boolean isSideSolid2           (byte aSide) {return F;}
	@Override public boolean isSealable2            (byte aSide) {return F;}
	@Override public boolean allowCovers            (byte aSide) {return F;}
	@Override public boolean attachCoversFirst      (byte aSide) {return F;}
	@Override public boolean isObstructingBlockAt   (byte aSide) {return SIDES_INVALID[mFacing];}
	@Override public boolean checkObstruction(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {return F;}
	@Override public boolean isLeaves() {return T;}
	
	@Override public int getLightOpacity() {return SIDES_INVALID[mFacing] ? LIGHT_OPACITY_LEAVES : LIGHT_OPACITY_NONE;}
	@Override public byte getVisualData() {return mStage;}
	@Override public void setVisualData(byte aData) {mStage = (byte)(aData & 3);}
	@Override public boolean unpaint() {return F;}
	@Override public boolean isPainted() {return F;}
	@Override public boolean paint(int aRGB) {return F;}
	@Override public int getPaint() {return UNCOLORED;}
	@Override public boolean canRecolorItem(ItemStack aStack) {return F;}
	@Override public boolean canDecolorItem(ItemStack aStack) {return F;}
	@Override public boolean recolorItem(ItemStack aStack, int aRGB) {return F;}
	@Override public boolean decolorItem(ItemStack aStack) {return F;}
	@Override public String getFacingTool() {return null;}
	@Override public byte getDefaultSide() {return SIDE_UNDEFINED;}
	@Override public boolean[] getValidSides() {return SIDES_ALL;}
	@Override public float getExplosionResistance2() {return 0.2F;}
	@Override public int getFireSpreadSpeed(byte aSide, boolean aDefault) {return 300;}
	@Override public int getFlammability(byte aSide, boolean aDefault) {return 300;}
	@Override public boolean canDrop(int aSlot) {return F;}
	@Override public String getTileEntityName() {return "gt6.multitileentity.plant.bush";}
}
