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

package gregtech6.tileentity.tools;

import static gregapi6.data.CS.*;

import java.util.List;

import gregapi6.data.CS.SFX;
import gregapi6.data.FL;
import gregapi6.data.LH;
import gregapi6.data.LH.Chat;
import gregapi6.old.Textures;
import gregapi6.oredict.OreDictMaterial;
import gregapi6.oredict.OreDictMaterialStack;
import gregapi6.render.BlockTextureDefault;
import gregapi6.render.BlockTextureMulti;
import gregapi6.render.IIconContainer;
import gregapi6.render.ITexture;
import gregapi6.tileentity.ITileEntityTapAccessible;
import gregapi6.tileentity.base.TileEntityBase10Attachment;
import gregapi6.tileentity.delegate.DelegatorTileEntity;
import gregapi6.util.ST;
import gregapi6.util.UT;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCauldron;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityFluidTap extends TileEntityBase10Attachment {
	public boolean mAcidProof = F;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_ACIDPROOF)) mAcidProof = aNBT.getBoolean(NBT_ACIDPROOF);
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.ORANGE + LH.get(LH.NO_GUI_CLICK_TO_TANK));
		aList.add(Chat.ORANGE + LH.get(LH.TOOLTIP_LIQUIDPROOF));
		if (mAcidProof) aList.add(Chat.ORANGE + LH.get(LH.TOOLTIP_ACIDPROOF));
	}
	
	@Override
	public boolean onBlockActivated3(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isServerSide()) {
			DelegatorTileEntity<TileEntity> tDelegator = getAdjacentTileEntity(mFacing);
			if (tDelegator.mTileEntity instanceof ITileEntityTapAccessible) {
				FluidStack tFluid = ((ITileEntityTapAccessible)tDelegator.mTileEntity).tapDrain(tDelegator.mSideOfTileEntity, Integer.MAX_VALUE, F);
				if (!FL.gas(tFluid, T) && tFluid.amount > 0 && (mAcidProof || !FL.acid(tFluid))) {
					ItemStack aStack = aPlayer.getCurrentEquippedItem();
					if (aStack == null) {
						DelegatorTileEntity<TileEntity> tDelegator2 = getAdjacentTileEntity(SIDE_BOTTOM);
						if (tDelegator2.mTileEntity == null) {
							if (tDelegator2.getBlock() instanceof BlockCauldron) {
								byte tMeta = tDelegator2.getMetaData();
								if (tMeta < 3 && FL.water(tFluid) && tFluid.amount >= 334) {
									if (tFluid.amount >= 1000 && tMeta <= 0) {
										((ITileEntityTapAccessible)tDelegator.mTileEntity).tapDrain(tDelegator.mSideOfTileEntity, 1000, T);
										tDelegator2.setMetaData((byte)(tMeta + 3));
									} else if (tFluid.amount >= 667 && tMeta <= 1) {
										((ITileEntityTapAccessible)tDelegator.mTileEntity).tapDrain(tDelegator.mSideOfTileEntity,  667, T);
										tDelegator2.setMetaData((byte)(tMeta + 2));
									} else if (tMeta <= 2) {
										((ITileEntityTapAccessible)tDelegator.mTileEntity).tapDrain(tDelegator.mSideOfTileEntity,  334, T);
										tDelegator2.setMetaData((byte)(tMeta + 1));
									}
									UT.Sounds.send(SFX.IC_SPRAY, 1.0F, 2.0F, this);
									UT.Sounds.send(SFX.MC_LIQUID_WATER, 1.0F, 1.0F, this);
								}
								return T;
							}
							return T;
						}
						if (tDelegator2.mTileEntity instanceof MultiTileEntityBathingPot || tDelegator2.mTileEntity instanceof MultiTileEntityMixingBowl) {
							OreDictMaterialStack tMaterial = OreDictMaterial.FLUID_MAP.get(tFluid.getFluid().getName());
							tFluid = tFluid.copy();
							tFluid.amount = Math.min(tFluid.amount, FL.lava(tFluid) ? 1000 : !FL.water(tFluid) && tMaterial != null && tMaterial.mAmount > 0 ? UT.Code.bindInt(tMaterial.mAmount) : 250);
							if (((ITileEntityTapAccessible)tDelegator.mTileEntity).tapDrain(tDelegator.mSideOfTileEntity, UT.Code.bindInt(FL.fill_(tDelegator2, tFluid, T)), T) != null) {
								UT.Sounds.send(SFX.IC_SPRAY, 1.0F, 2.0F, this);
								UT.Sounds.send(SFX.MC_LIQUID_WATER, 1.0F, 1.0F, this);
							}
							return T;
						}
						return T;
					}
					FluidStack tNewFluid = tFluid.copy();
					ItemStack tStack = FL.fill(tNewFluid, ST.amount(1, aStack), T, T, T, T);
					if (tFluid.amount > tNewFluid.amount && ((ITileEntityTapAccessible)tDelegator.mTileEntity).tapDrain(tDelegator.mSideOfTileEntity, tFluid.amount - tNewFluid.amount, T) != null) {
						UT.Sounds.send(SFX.IC_SPRAY, 1.0F, 2.0F, this);
						aStack.stackSize--;
						UT.Inventories.addStackToPlayerInventoryOrDrop(aPlayer, tStack, T);
						return T;
					}
				}
			}
		}
		return T;
	}
	
	@Override public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered) {return 3;}
	@Override public boolean usesRenderPass2(int aRenderPass, boolean[] aShouldSideBeRendered) {return T;}
	
	@Override
	public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		switch(aRenderPass) {
		case 0:
			switch(mFacing) {
			case SIDE_Z_NEG: box(aBlock, PX_P[ 6], PX_P[ 6], PX_P[ 2], PX_N[ 6], PX_N[ 9], PX_N[12]); return T;
			default        : box(aBlock, PX_P[ 6], PX_P[ 6], PX_P[12], PX_N[ 6], PX_N[ 9], PX_N[ 2]); return T;
			case SIDE_X_NEG: box(aBlock, PX_P[ 2], PX_P[ 6], PX_P[ 6], PX_N[12], PX_N[ 9], PX_N[ 6]); return T;
			case SIDE_X_POS: box(aBlock, PX_P[12], PX_P[ 6], PX_P[ 6], PX_N[ 2], PX_N[ 9], PX_N[ 6]); return T;
			}
		case 1:
			switch(mFacing) {
			case SIDE_Z_NEG: box(aBlock, PX_P[ 7], PX_P[ 4], PX_P[ 0], PX_N[ 7], PX_N[10], PX_N[12]); return T;
			default        : box(aBlock, PX_P[ 7], PX_P[ 4], PX_P[12], PX_N[ 7], PX_N[10], PX_N[ 0]); return T;
			case SIDE_X_NEG: box(aBlock, PX_P[ 0], PX_P[ 4], PX_P[ 7], PX_N[12], PX_N[10], PX_N[ 7]); return T;
			case SIDE_X_POS: box(aBlock, PX_P[12], PX_P[ 4], PX_P[ 7], PX_N[ 0], PX_N[10], PX_N[ 7]); return T;
			}
		case 2:
			switch(mFacing) {
			case SIDE_Z_NEG: box(aBlock, PX_P[ 7], PX_P[ 3], PX_P[ 4], PX_N[ 7], PX_N[10], PX_N[10]); return T;
			default        : box(aBlock, PX_P[ 7], PX_P[ 3], PX_P[10], PX_N[ 7], PX_N[10], PX_N[ 4]); return T;
			case SIDE_X_NEG: box(aBlock, PX_P[ 4], PX_P[ 3], PX_P[ 7], PX_N[10], PX_N[10], PX_N[ 7]); return T;
			case SIDE_X_POS: box(aBlock, PX_P[10], PX_P[ 3], PX_P[ 7], PX_N[ 4], PX_N[10], PX_N[ 7]); return T;
			}
		}
		return T;
	}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		return aRenderPass == 1 && (aSide == mFacing ? !aShouldSideBeRendered[aSide] : aSide == OPPOSITES[mFacing]) ? null : BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[FACES_TBS[aSide]], mRGBa), BlockTextureDefault.get(sOverlays[FACES_TBS[aSide]]));
	}
	
	// Icons
	public static IIconContainer sColoreds[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/tools/tap/colored/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/tools/tap/colored/top"),
		new Textures.BlockIcons.CustomIcon("machines/tools/tap/colored/side"),
	}, sOverlays[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/tools/tap/overlay/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/tools/tap/overlay/top"),
		new Textures.BlockIcons.CustomIcon("machines/tools/tap/overlay/side"),
	};
	
	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool() {
		switch(mFacing) {
		case SIDE_Z_NEG: return box(PX_P[ 6], PX_P[ 3], PX_P[ 0], PX_N[ 6], PX_N[ 9], PX_N[10]);
		default        : return box(PX_P[ 6], PX_P[ 3], PX_P[10], PX_N[ 6], PX_N[ 9], PX_N[ 0]);
		case SIDE_X_NEG: return box(PX_P[ 0], PX_P[ 3], PX_P[ 6], PX_N[10], PX_N[ 9], PX_N[ 6]);
		case SIDE_X_POS: return box(PX_P[10], PX_P[ 3], PX_P[ 6], PX_N[ 0], PX_N[ 9], PX_N[ 6]);
		}
	}
	
	@Override
	public void setBlockBoundsBasedOnState(Block aBlock) {
		switch(mFacing) {
		case SIDE_Z_NEG: box(aBlock, PX_P[ 6], PX_P[ 3], PX_P[ 0], PX_N[ 6], PX_N[ 9], PX_N[10]); break;
		default        : box(aBlock, PX_P[ 6], PX_P[ 3], PX_P[10], PX_N[ 6], PX_N[ 9], PX_N[ 0]); break;
		case SIDE_X_NEG: box(aBlock, PX_P[ 0], PX_P[ 3], PX_P[ 6], PX_N[10], PX_N[ 9], PX_N[ 6]); break;
		case SIDE_X_POS: box(aBlock, PX_P[10], PX_P[ 3], PX_P[ 6], PX_N[ 0], PX_N[ 9], PX_N[ 6]); break;
		}
	}
	
	@Override public boolean canDrop(int aInventorySlot) {return T;}
	
	@Override public String getTileEntityName() {return "gt6.multitileentity.tap";}
}
