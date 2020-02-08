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

import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_GetCollisionBoundingBoxFromPool;
import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_GetSelectedBoundingBoxFromPool;
import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_SetBlockBoundsBasedOnState;
import gregapi6.data.BI;
import gregapi6.data.CS.SFX;
import gregapi6.data.LH;
import gregapi6.data.LH.Chat;
import gregapi6.data.RM;
import gregapi6.data.TD;
import gregapi6.old.Textures;
import gregapi6.recipes.Recipe;
import gregapi6.recipes.Recipe.RecipeMap;
import gregapi6.render.BlockTextureDefault;
import gregapi6.render.BlockTextureMulti;
import gregapi6.render.IIconContainer;
import gregapi6.render.ITexture;
import gregapi6.tileentity.base.TileEntityBase09FacingSingle;
import gregapi6.util.ST;
import gregapi6.util.UT;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityGrindStone extends TileEntityBase09FacingSingle implements IMTE_SetBlockBoundsBasedOnState, IMTE_GetCollisionBoundingBoxFromPool, IMTE_GetSelectedBoundingBoxFromPool {
	protected byte mClickCount = 0, mStone = 0, oStone = 0;
	protected RecipeMap mRecipes = RM.Sharpening;
	protected Recipe mLastRecipe = null;

	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_STATE)) mStone = aNBT.getByte(NBT_STATE);
		if (aNBT.hasKey(NBT_RECIPEMAP)) mRecipes = RecipeMap.RECIPE_MAPS.get(aNBT.getString(NBT_RECIPEMAP));
	}

	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		aNBT.setByte(NBT_STATE, mStone);
	}

	@Override
	public NBTTagCompound writeItemNBT2(NBTTagCompound aNBT) {
		aNBT.setByte(NBT_STATE, mStone);
		return aNBT;
	}

	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get(LH.RECIPES) + ": " + Chat.WHITE + LH.get(mRecipes.mNameInternal));
		aList.add(Chat.CYAN     + LH.get(LH.RECIPES_GRINDSTONE_INIT));
		aList.add(Chat.CYAN     + LH.get(LH.RECIPES_GRINDSTONE_USAGE));
		aList.add(Chat.ORANGE   + LH.get(LH.NO_GUI_CLICK_TO_INTERACT) + " (" + LH.get(LH.FACE_ANYBUT_SIDES) + ")");
		super.addToolTips(aList, aStack, aF3_H);
	}

	@Override public boolean attachCoversFirst(byte aSide) {return F;}

	@Override
	public boolean onBlockActivated3(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (aSide != mFacing && !SIDES_TOP[aSide]) return F;
		if (isServerSide()) {
			if (SIDES_TOP[aSide]) {
				float[] tCoords = UT.Code.getFacingCoordsClicked(aSide, aHitX, aHitY, aHitZ);
				if (tCoords[0] <= PX_P[SIDES_AXIS_Z[mFacing]?8:4] && tCoords[1] <= PX_P[SIDES_AXIS_X[mFacing]?8:4]) return T;
			}
			if (SIDES_VERTICAL[aSide] || ALONG_AXIS[mFacing][aSide]) {
				ItemStack aStack = aPlayer.getCurrentEquippedItem();
				if (aStack == null || mRecipes == null || !UT.Entities.isPlayer(aPlayer)) {
					mClickCount = 0;
				} else if (mStone <= 0) {
					if (ST.equal(aStack, Blocks.sandstone) && aStack.stackSize > 0) {
						mClickCount = 0;
						mStone = 4;
						if (!UT.Entities.hasInfiniteItems(aPlayer)) aStack.stackSize--;
					}
				} else if (UT.Entities.hasInfiniteItems(aPlayer) || ++mClickCount >= 20) {
					mClickCount = 0;
					Recipe tRecipe = mRecipes.findRecipe(this, mLastRecipe, F, V[1], null, ZL_FS, aStack);
					if (tRecipe != null) {
						mLastRecipe = tRecipe;
						if (tRecipe.isRecipeInputEqual(T, F, ZL_FS, ST.array(aStack))) {
							for (ItemStack tStack : tRecipe.getOutputs()) UT.Inventories.addStackToPlayerInventoryOrDrop(aPlayer, tStack, F);
							if (!UT.Entities.hasInfiniteItems(aPlayer)) mStone--;
							aPlayer.addExhaustion((tRecipe.mEUt * tRecipe.mDuration) / 10000.0F);
						}
					}
				}
			}
		} else {
			if (SIDES_TOP[aSide] && mStone != 0) {
				float[] tCoords = UT.Code.getFacingCoordsClicked(aSide, aHitX, aHitY, aHitZ);
				if (tCoords[0] <= PX_P[SIDES_AXIS_Z[mFacing]?8:4] && tCoords[1] <= PX_P[SIDES_AXIS_X[mFacing]?8:4]) {
					mRecipes.openNEI();
					return T;
				}
			}
			if (SIDES_VERTICAL[aSide] || ALONG_AXIS[mFacing][aSide]) {
				// TODO SOUND
				if (mStone != 0) UT.Sounds.play(SFX.MC_DIG_SAND, 5, 1.0F, 1.0F, getCoords());
			}
		}
		return T;
	}

	@Override
	public boolean onTickCheck(long aTimer) {
		return super.onTickCheck(aTimer) || mStone != oStone;
	}

	@Override
	public void onTickResetChecks(long aTimer, boolean aIsServerSide) {
		super.onTickResetChecks(aTimer, aIsServerSide);
		oStone = mStone;
	}

	@Override
	public void setVisualData(byte aData) {
		mStone = aData;
	}

	@Override public byte getVisualData() {return mStone;}
	@Override public byte getDefaultSide() {return SIDE_FRONT;}
	@Override public boolean[] getValidSides() {return SIDES_HORIZONTAL;}

	@Override
	public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered) {
		return 6;
	}

	@Override
	public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		switch(aRenderPass) {
		case  0: box(aBlock, PX_P[SIDES_AXIS_Z[mFacing]?6:2], PX_P[ 3], PX_P[SIDES_AXIS_X[mFacing]?6:2], PX_P[SIDES_AXIS_Z[mFacing]?8:4], PX_N[ 1]+0.0001F, PX_P[SIDES_AXIS_X[mFacing]?8:4]); return T;
		case  1: box(aBlock, PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[14], PX_N[ 0]); return T;
		case  2: box(aBlock, PX_P[ 5], PX_P[ 0], PX_P[ 5], SIDES_AXIS_Z[mFacing]?PX_P[ 6]:PX_N[ 5], PX_N[ 5], SIDES_AXIS_X[mFacing]?PX_P[ 6]:PX_N[ 5]); return T;
		case  3: box(aBlock, SIDES_AXIS_Z[mFacing]?PX_N[ 6]:PX_P[ 5], PX_P[ 0], SIDES_AXIS_X[mFacing]?PX_N[ 6]:PX_P[ 5], PX_N[ 5], PX_N[ 5], PX_N[ 5]); return T;
		case  4: box(aBlock, PX_P[SIDES_AXIS_X[mFacing]?7:3], PX_P[ 8], PX_P[SIDES_AXIS_Z[mFacing]?7:3], PX_N[SIDES_AXIS_X[mFacing]?7:3], PX_N[ 6], PX_N[SIDES_AXIS_Z[mFacing]?7:3]); return T;
		case  5: box(aBlock, PX_P[SIDES_AXIS_Z[mFacing]?6:2], PX_P[ 3], PX_P[SIDES_AXIS_X[mFacing]?6:2], PX_N[SIDES_AXIS_Z[mFacing]?6:2], PX_N[ 1], PX_N[SIDES_AXIS_X[mFacing]?6:2]); return T;
		}
		return F;
	}

	public static IIconContainer
	sTextureLegs    = new Textures.BlockIcons.CustomIcon("machines/tools/grindstone/colored/legs"),
	sTextureAxle    = new Textures.BlockIcons.CustomIcon("machines/tools/grindstone/colored/axle"),
	sTextureStone   = new Textures.BlockIcons.CustomIcon("machines/tools/grindstone/colored/stone"),
	sTextureBottom  = new Textures.BlockIcons.CustomIcon("machines/tools/grindstone/colored/bottom"),
	sOverlayLegs    = new Textures.BlockIcons.CustomIcon("machines/tools/grindstone/overlay/legs"),
	sOverlayAxle    = new Textures.BlockIcons.CustomIcon("machines/tools/grindstone/overlay/axle"),
	sOverlayStone   = new Textures.BlockIcons.CustomIcon("machines/tools/grindstone/overlay/stone"),
	sOverlayBottom  = new Textures.BlockIcons.CustomIcon("machines/tools/grindstone/overlay/bottom");

	private ITexture mTextureLegs, mTextureAxle, mTextureStone, mTextureBottom;

	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		if (aRenderPass == 0 && aSide == 0) {
			boolean tGlow = mMaterial.contains(TD.Properties.GLOWING);

			mTextureLegs        = BlockTextureMulti.get(BlockTextureDefault.get(sTextureLegs  , mRGBa, F, tGlow, F, F), BlockTextureDefault.get(sOverlayLegs));
			mTextureAxle        = BlockTextureMulti.get(BlockTextureDefault.get(sTextureAxle  , mRGBa, F, tGlow, F, F), BlockTextureDefault.get(sOverlayAxle));
			mTextureStone       = BlockTextureMulti.get(BlockTextureDefault.get(sTextureStone , mRGBa, F, tGlow, F, F), BlockTextureDefault.get(sOverlayStone));
			mTextureBottom      = BlockTextureMulti.get(BlockTextureDefault.get(sTextureBottom, mRGBa, F, tGlow, F, F), BlockTextureDefault.get(sOverlayBottom));
		}

		if (aRenderPass == 0) return mStone!=0&&SIDES_TOP[aSide]?BI.nei():null;
		if (aRenderPass == 1) return mTextureBottom;
		if (aRenderPass == 4) return mTextureAxle;
		if (aRenderPass == 5) return mStone!=0?mTextureStone:null;
		return mTextureLegs;
	}

	@Override public int getLightOpacity() {return LIGHT_OPACITY_WATER;}

	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return box(PX_P[2], PX_P[0], PX_P[2], PX_N[2], PX_N[1], PX_N[2]);}
	@Override public AxisAlignedBB getSelectedBoundingBoxFromPool () {return box(PX_P[2], PX_P[0], PX_P[2], PX_N[2], PX_N[1], PX_N[2]);}
	@Override public void setBlockBoundsBasedOnState(Block aBlock) {box(aBlock, PX_P[2], PX_P[0], PX_P[2], PX_N[2], PX_N[1], PX_N[2]);}

	@Override public float getSurfaceSize           (byte aSide) {return SIDES_BOTTOM[aSide]?1.0F:0.0F;}
	@Override public float getSurfaceSizeAttachable (byte aSide) {return SIDES_BOTTOM[aSide]?1.0F:0.0F;}
	@Override public float getSurfaceDistance       (byte aSide) {return 0.0F;}
	@Override public boolean isSurfaceSolid         (byte aSide) {return SIDES_BOTTOM[aSide];}
	@Override public boolean isSurfaceOpaque2       (byte aSide) {return SIDES_BOTTOM[aSide];}
	@Override public boolean isSideSolid2           (byte aSide) {return SIDES_BOTTOM[aSide];}
	@Override public boolean allowCovers            (byte aSide) {return F;}

	@Override public boolean canDrop(int aInventorySlot) {return F;}

	@Override public String getTileEntityName() {return "gt6.multitileentity.sharpener.grindstone";}
}
