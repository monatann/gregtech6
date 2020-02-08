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

package gregtech6.tileentity.energy.generators;

import static gregapi6.data.CS.*;

import java.util.Collection;
import java.util.List;

import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_GetCollisionBoundingBoxFromPool;
import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_OnEntityCollidedWithBlock;
import gregapi6.code.TagData;
import gregapi6.data.FM;
import gregapi6.data.LH;
import gregapi6.data.LH.Chat;
import gregapi6.data.TD;
import gregapi6.old.Textures;
import gregapi6.recipes.Recipe;
import gregapi6.recipes.Recipe.RecipeMap;
import gregapi6.render.BlockTextureDefault;
import gregapi6.render.BlockTextureMulti;
import gregapi6.render.IIconContainer;
import gregapi6.render.ITexture;
import gregapi6.tileentity.base.TileEntityBase09FacingSingle;
import gregapi6.tileentity.energy.ITileEntityEnergy;
import gregapi6.tileentity.machines.ITileEntityRunningActively;
import gregapi6.util.ST;
import gregapi6.util.UT;
import gregapi6.util.WD;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityGeneratorSolid extends TileEntityBase09FacingSingle implements ITileEntityEnergy, ITileEntityRunningActively, IMTE_GetCollisionBoundingBoxFromPool, IMTE_OnEntityCollidedWithBlock {
	private static int FLAME_RANGE = 3;
	
	protected short mEfficiency = 10000;
	protected long mEnergy = 0, mRate = 1;
	protected boolean mBurning = F, oBurning = F;
	protected TagData mEnergyTypeEmitted = TD.Energy.HU;
	protected RecipeMap mRecipes = FM.Furnace;
	protected Recipe mLastRecipe = null;
	protected ItemStack mOutput1 = null;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		mEnergy = aNBT.getLong(NBT_ENERGY);
		mBurning = aNBT.getBoolean(NBT_ACTIVE);
		mOutput1 = ST.load(aNBT, NBT_INV_OUT + ".1");
		if (aNBT.hasKey(NBT_OUTPUT)) mRate = aNBT.getLong(NBT_OUTPUT);
		if (aNBT.hasKey(NBT_FUELMAP)) mRecipes = RecipeMap.RECIPE_MAPS.get(aNBT.getString(NBT_FUELMAP));
		if (aNBT.hasKey(NBT_EFFICIENCY)) mEfficiency = (short)UT.Code.bind_(0, 10000, aNBT.getShort(NBT_EFFICIENCY));
		if (aNBT.hasKey(NBT_ENERGY_EMITTED)) mEnergyTypeEmitted = TagData.createTagData(aNBT.getString(NBT_ENERGY_EMITTED));
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setNumber(aNBT, NBT_ENERGY, mEnergy);
		UT.NBT.setBoolean(aNBT, NBT_ACTIVE, mBurning);
		ST.save(aNBT, NBT_INV_OUT + ".1", mOutput1);
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get(LH.RECIPES)        + ": " + Chat.WHITE + LH.get(mRecipes.mNameInternal));
		aList.add(LH.getToolTipEfficiency(mEfficiency));
		LH.addEnergyToolTips(this, aList, null, mEnergyTypeEmitted, null, LH.get(LH.FACE_TOP));
		aList.add(Chat.ORANGE   + LH.get(LH.REQUIREMENT_AIR_IN_FRONT));
		aList.add(Chat.ORANGE   + LH.get(LH.REQUIREMENT_EMPTY_ASHES) + " (" + LH.get(LH.FACE_FRONT) + ")");
		aList.add(Chat.ORANGE   + LH.get(LH.REQUIREMENT_IGNITE_FIRE) + " (" + LH.get(LH.FACE_FRONT) + ")");
		aList.add(Chat.ORANGE   + LH.get(LH.NO_GUI_CLICK_TO_INVENTORY) + " (" + LH.get(LH.FACE_FRONT) + ")");
		aList.add(Chat.DRED     + LH.get(LH.HAZARD_FIRE) + " ("+(FLAME_RANGE+1)+"m)");
		aList.add(Chat.DRED     + LH.get(LH.HAZARD_CONTACT) + " (" + LH.get(LH.FACE_TOP) + ")");
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_REMOVE_SHOVEL));
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		if (aIsServerSide) {
			if (mBurning) {
				if (mEnergy >= mRate) {
					ITileEntityEnergy.Util.emitEnergyToNetwork(mEnergyTypeEmitted, 1, Math.min(mRate, mEnergy), this);
					mEnergy -= mRate;
					if (mEfficiency < 1 || rng(mEfficiency) == 0) {
						WD.fire(worldObj, xCoord-FLAME_RANGE+rng(2*FLAME_RANGE+1), yCoord-1+rng(2+FLAME_RANGE), zCoord-FLAME_RANGE+rng(2*FLAME_RANGE+1), T);
					}
				}
				if (mEnergy < mRate * 2) {
					WD.fire(worldObj, getOffset(mFacing, 1), T);
					if (addStackToSlot(1, mOutput1)) mOutput1 = null;
					if (mOutput1 == null && !WD.hasCollide(worldObj, getOffsetX(mFacing), getOffsetY(mFacing), getOffsetZ(mFacing)) && !getBlockAtSide(mFacing).getMaterial().isLiquid() && WD.oxygen(worldObj, getOffsetX(mFacing), getOffsetY(mFacing), getOffsetZ(mFacing))) {
						Recipe tRecipe = mRecipes.findRecipe(this, mLastRecipe, T, Long.MAX_VALUE, null, ZL_FS, slot(0));
						if (tRecipe != null && tRecipe.isRecipeInputEqual(T, F, ZL_FS, slot(0))) {
							mLastRecipe = tRecipe;
							ItemStack[] tOutputs = tRecipe.getOutputs();
							if (tOutputs.length > 0) mOutput1 = ST.copy(tOutputs[0]);
							mEnergy += UT.Code.units(Math.abs(tRecipe.mEUt * tRecipe.mDuration), 10000, mEfficiency, F);
							removeAllDroppableNullStacks();
						}
					}
				}
			}
			if (mEnergy <     0) mEnergy = 0;
			if (mEnergy < mRate) mBurning = F;
		} else {
			if (mBurning && rng(4) == 0) spawnBurningParticles(xCoord+0.5+OFFSETS_X[mFacing]*0.55+(SIDES_AXIS_X[mFacing]?0:RNGSUS.nextFloat()*0.6F-0.3F), yCoord+RNGSUS.nextFloat()*0.375F, zCoord+0.5+OFFSETS_Z[mFacing]*0.55+(SIDES_AXIS_Z[mFacing]?0:RNGSUS.nextFloat()*0.6F-0.3F));
		}
	}
	
	@Override public boolean attachCoversFirst(byte aSide) {return F;}
	
	@Override
	public boolean onBlockActivated3(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (aSide != mFacing) return F;
		if (isServerSide()) {
			ItemStack aStack = aPlayer.getCurrentEquippedItem();
			if (aStack == null) {
				if (slotHas(1)) {
					aPlayer.inventory.setInventorySlotContents(aPlayer.inventory.currentItem, slot(1));
					slotKill(1);
					if (mBurning) UT.Entities.applyHeatDamage(aPlayer, Math.max(1.0F, Math.min(5.0F, mRate / 20.0F)));
					return T;
				}
				if (!mBurning && slotHas(0)) {
					aPlayer.inventory.setInventorySlotContents(aPlayer.inventory.currentItem, slot(0));
					slotKill(0);
					return T;
				}
			} else if (!slotHas(0)) {
				if (canInsertItem(0, aStack, SIDE_INSIDE)) {
					slot(0, aStack);
					aPlayer.inventory.setInventorySlotContents(aPlayer.inventory.currentItem, null);
					return T;
				}
			} else if (ST.equal(aStack, slot(0))) {
				int tDifference = Math.min(aStack.stackSize, slot(0).getMaxStackSize() - slot(0).stackSize);
				aStack.stackSize-=tDifference;
				slot(0).stackSize+=tDifference;
				return T;
			} else if (ST.equal(aStack, slot(1))) {
				int tDifference = Math.min(slot(1).stackSize, aStack.getMaxStackSize() - aStack.stackSize);
				aStack.stackSize+=tDifference;
				slot(1).stackSize-=tDifference;
				removeAllDroppableNullStacks();
				if (mBurning) UT.Entities.applyHeatDamage(aPlayer, Math.max(1.0F, Math.min(5.0F, mRate / 20.0F)));
				return T;
			}
		}
		return T;
	}
	
	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		long rReturn = super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
		if (rReturn > 0) return rReturn;
		
		if (isClientSide()) return 0;
		
		if (aTool.equals(TOOL_igniter       ) && (aSide == mFacing || aPlayer == null)) {mBurning = T; return 10000;}
		if (aTool.equals(TOOL_extinguisher  ) && (aSide == mFacing || aPlayer == null)) {mBurning = F; return 10000;}
		if (aTool.equals(TOOL_shovel        ) &&  aSide == mFacing && slotHas(1)) {
			long rDamage = 1000 * slot(1).stackSize;
			UT.Inventories.addStackToPlayerInventoryOrDrop(aPlayer instanceof EntityPlayer ? (EntityPlayer)aPlayer : null, slot(1), worldObj, xCoord, yCoord, zCoord);
			slotKill(1);
			return rDamage;
		}
		
		return 0;
	}
	
	@Override
	public boolean onTickCheck(long aTimer) {
		return mBurning != oBurning || super.onTickCheck(aTimer);
	}
	
	@Override
	public void onTickResetChecks(long aTimer, boolean aIsServerSide) {
		super.onTickResetChecks(aTimer, aIsServerSide);
		oBurning = mBurning;
	}
	
	@Override
	public void setVisualData(byte aData) {
		mBurning = ((aData & 1) != 0);
	}
	
	@Override public byte getVisualData() {return (byte)(mBurning?1:0);}
	@Override public byte getDefaultSide() {return SIDE_FRONT;}
	@Override public boolean[] getValidSides() {return SIDES_HORIZONTAL;}
	
	@Override public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {return aShouldSideBeRendered[aSide] ? BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[FACING_ROTATIONS[mFacing][aSide]], mRGBa), BlockTextureDefault.get((mBurning?sOverlaysActive:sOverlays)[FACING_ROTATIONS[mFacing][aSide]])): null;}
	
	@Override public void onEntityCollidedWithBlock(Entity aEntity) {if (mBurning) UT.Entities.applyHeatDamage(aEntity, Math.min(10.0F, mRate / 10.0F));}
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return box(0, 0, 0, 1, 0.875, 1);}
	
	// Inventory Stuff
	private static final int[] ACCESSABLE_SLOTS = new int[] {0, 1};
	@Override public int[] getAccessibleSlotsFromSide2(byte aSide) {return aSide == mFacing ? ZL_INTEGER : ACCESSABLE_SLOTS;}
	@Override public boolean canInsertItem2 (int aSlot, ItemStack aStack, byte aSide) {return aStack != null && aSlot == 0 && aSide != mFacing && mRecipes.containsInput(aStack, this, NI);}
	@Override public boolean canExtractItem2(int aSlot, ItemStack aStack, byte aSide) {return aStack != null && aSlot == 1 && aSide != mFacing;}
	@Override public boolean canDrop(int aInventorySlot) {return T;}
	@Override public ItemStack[] getDefaultInventory(NBTTagCompound aNBT) {return new ItemStack[2];}
	
	@Override public boolean isEnergyType(TagData aEnergyType, byte aSide, boolean aEmitting) {return aEmitting && aEnergyType == mEnergyTypeEmitted;}
	@Override public boolean isEnergyEmittingTo(TagData aEnergyType, byte aSide, boolean aTheoretical) {return SIDES_TOP[aSide] && super.isEnergyEmittingTo(aEnergyType, aSide, aTheoretical);}
	@Override public long getEnergyOffered(TagData aEnergyType, byte aSide, long aSize) {return Math.min(mRate, mEnergy);}
	@Override public long getEnergySizeOutputRecommended(TagData aEnergyType, byte aSide) {return mRate;}
	@Override public long getEnergySizeOutputMin(TagData aEnergyType, byte aSide) {return mRate;}
	@Override public long getEnergySizeOutputMax(TagData aEnergyType, byte aSide) {return mRate;}
	@Override public Collection<TagData> getEnergyTypes(byte aSide) {return mEnergyTypeEmitted.AS_LIST;}
	
	@Override public boolean getStateRunningPassively() {return mBurning;}
	@Override public boolean getStateRunningPossible() {return mBurning;}
	@Override public boolean getStateRunningActively() {return mBurning;}
	
	protected void spawnBurningParticles(double aX, double aY, double aZ) {
		worldObj.spawnParticle("smoke", aX, aY, aZ, 0, 0, 0);
		worldObj.spawnParticle("flame", aX, aY, aZ, 0, 0, 0);
	}
	
	// Icons
	public static IIconContainer[] sColoreds = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_solid/colored/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_solid/colored/top"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_solid/colored/left"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_solid/colored/front"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_solid/colored/right"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_solid/colored/back")
	}, sOverlays = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_solid/overlay/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_solid/overlay/top"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_solid/overlay/left"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_solid/overlay/front"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_solid/overlay/right"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_solid/overlay/back")
	}, sOverlaysActive = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_solid/overlay_active/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_solid/overlay_active/top"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_solid/overlay_active/left"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_solid/overlay_active/front"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_solid/overlay_active/right"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_solid/overlay_active/back")
	};
	
	@Override public String getTileEntityName() {return "gt6.multitileentity.generator.burning_solid";}
}
