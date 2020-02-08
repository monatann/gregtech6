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

import gregapi6.block.IBlockFoamable;
import gregapi6.block.metatype.BlockMetaType;
import gregapi6.data.CS.SFX;
import gregapi6.data.IL;
import gregapi6.data.LH;
import gregapi6.item.multiitem.MultiItem;
import gregapi6.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi6.tileentity.ITileEntityFoamable;
import gregapi6.tileentity.delegate.DelegatorTileEntity;
import gregapi6.util.ST;
import gregapi6.util.UT;
import gregapi6.util.WD;
import gregtech6.blocks.BlockCFoamFresh;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class Behavior_Spray_Foam_Hardener extends AbstractBehaviorDefault {
	private final ItemStack mEmpty, mUsed, mFull;
	private final long mUses;
	
	public Behavior_Spray_Foam_Hardener(ItemStack aEmpty, ItemStack aUsed, ItemStack aFull, long aUses) {
		mEmpty = aEmpty;
		mUsed = aUsed;
		mFull = aFull;
		mUses = aUses * 10;
	}
	
	@Override
	public boolean onItemUseFirst(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float hitX, float hitY, float hitZ) {
		if (aWorld.isRemote || aStack.stackSize != 1 || !aPlayer.canPlayerEdit(aX, aY, aZ, aSide, aStack)) return F;
		
		boolean rOutput = F;
		
		NBTTagCompound tNBT = aStack.getTagCompound();
		if (tNBT == null) tNBT = UT.NBT.make();
		long tUses = tNBT.getLong("gt6.remaining");
		
		if (ST.equal(aStack, mFull, T)) {
			aStack.func_150996_a(mUsed.getItem());
			ST.meta_(aStack, ST.meta_(mUsed));
			tUses = mUses;
		}
		if (ST.equal(aStack, mUsed, T)) {
			long tHardened = harden(aWorld, aX, aY, aZ, aSide, UT.Entities.hasInfiniteItems(aPlayer)?mUses:tUses, aPlayer, aStack);
			if (tHardened > 0) {
				UT.Sounds.send(aWorld, SFX.IC_SPRAY, 1.0F, -1.0F, aX, aY, aZ);
				if (!UT.Entities.hasInfiniteItems(aPlayer)) tUses -= tHardened;
				rOutput = T;
			}
		}
		tNBT.removeTag("gt6.remaining");
		if (tUses > 0) UT.NBT.setNumber(tNBT, "gt6.remaining", tUses);
		UT.NBT.set(aStack, tNBT);
		
		if (tUses <= 0) {
			if (mEmpty == null) {
				aStack.stackSize--;
			} else {
				aStack.func_150996_a(mEmpty.getItem());
				ST.meta_(aStack, ST.meta_(mEmpty));
			}
		}
		return rOutput;
	}
	
	public long harden(World aWorld, int aX, int aY, int aZ, byte aSide, long aUses, EntityPlayer aPlayer, ItemStack aStack) {
		if (aUses < 1) return 0;
		
		DelegatorTileEntity<TileEntity> aTileEntity = WD.te(aWorld, aX, aY, aZ, aSide, T);
		if (aTileEntity.mTileEntity instanceof ITileEntityFoamable) return ((ITileEntityFoamable)aTileEntity.mTileEntity).dryFoam(aTileEntity.mSideOfTileEntity, aPlayer) ? 10 : 0;
		Block aBlock = aTileEntity.getBlock(); aWorld = aTileEntity.mWorld; aX = aTileEntity.mX; aY = aTileEntity.mY; aZ = aTileEntity.mZ;
		if (aBlock instanceof IBlockFoamable) return ((IBlockFoamable)aBlock).dryFoam(aWorld, aX, aY, aZ, aTileEntity.mSideOfTileEntity) ? aBlock instanceof BlockCFoamFresh && SIDES_VALID[((BlockMetaType)aBlock).mSide] ? 5 : 10 : 0;
		
		try {
			if (UT.Reflection.getClassName(aTileEntity.mTileEntity).startsWith("TileEntityCable")) {
				if (aUses >= 10 && UT.Reflection.getPublicField(aTileEntity.mTileEntity, "foamed").getByte(aTileEntity.mTileEntity) == 1) {
					UT.Reflection.callPublicMethod(aTileEntity.mTileEntity, "changeFoam", (byte)2);
					return 10;
				}
				return 0;
			}
		} catch(Throwable e) {if (D1) e.printStackTrace(ERR);}
		
		if (IL.IC2_Foam             .block() == aBlock) return aUses >= 10 && aWorld.setBlock(aX, aY, aZ, IL.IC2_Wall               .block(), 7, 3) ? 10 : 0;
		if (IL.IC2_Foam_Reinforced  .block() == aBlock) return aUses >= 10 && aWorld.setBlock(aX, aY, aZ, IL.IC2_Wall_Reinforced    .block(), 0, 3) ? 10 : 0;
		return 0;
	}
	
	static {
		LH.add("gt6.behaviour.foamhardenerspray.tooltip", "Can harden C-Foam");
		LH.add("gt6.behaviour.hardenerspray.uses", "Remaining Uses:");
		LH.add("gt6.behaviour.unstackable", "Not usable when stacked!");
	}
	
	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		aList.add(LH.get("gt6.behaviour.foamhardenerspray.tooltip"));
		NBTTagCompound tNBT = aStack.getTagCompound();
		long tRemaining = (ST.equal(aStack, mFull, T)?mUses:tNBT==null?0:tNBT.getLong("gt6.remaining"));
		aList.add(LH.get("gt6.behaviour.hardenerspray.uses") + " " + (tRemaining / 10) + "." + (tRemaining % 10));
		aList.add(LH.get("gt6.behaviour.unstackable"));
		return aList;
	}
}
