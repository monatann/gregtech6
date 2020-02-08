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

import gregapi6.block.IBlockDecolorable;
import gregapi6.data.CS.SFX;
import gregapi6.data.LH;
import gregapi6.item.multiitem.MultiItem;
import gregapi6.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi6.tileentity.ITileEntityDecolorable;
import gregapi6.tileentity.delegate.DelegatorTileEntity;
import gregapi6.util.ST;
import gregapi6.util.UT;
import gregapi6.util.WD;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class Behavior_Spray_Color_Remover extends AbstractBehaviorDefault {
	private final ItemStack mEmpty, mUsed, mFull;
	private final long mUses;
	
	public Behavior_Spray_Color_Remover(ItemStack aEmpty, ItemStack aUsed, ItemStack aFull, long aUses) {
		mEmpty = aEmpty;
		mUsed = aUsed;
		mFull = aFull;
		mUses = aUses * 10;
	}
	
	@Override
	public boolean onItemUseFirst(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float hitX, float hitY, float hitZ) {
		if (aWorld.isRemote || aStack.stackSize != 1) return F;
		
		boolean rOutput = F;
		
		if (!aPlayer.canPlayerEdit(aX, aY, aZ, aSide, aStack)) return F;
		
		NBTTagCompound tNBT = aStack.getTagCompound();
		if (tNBT == null) tNBT = UT.NBT.make();
		long tUses = tNBT.getLong("gt6.remaining");
		
		if (ST.equal(aStack, mFull, T)) {
			aStack.func_150996_a(mUsed.getItem());
			ST.meta_(aStack, ST.meta_(mUsed));
			tUses = mUses;
		}
		if (ST.equal(aStack, mUsed, T)) {
			if (decolorize(aWorld, aX, aY, aZ, aSide)) {
				UT.Sounds.send(aWorld, SFX.IC_SPRAY, 1.0F, 1.0F, aX, aY, aZ);
				if (!UT.Entities.hasInfiniteItems(aPlayer)) tUses-=10;
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
	
	private static boolean decolorize(World aWorld, int aX, int aY, int aZ, byte aSide) {
		DelegatorTileEntity<TileEntity> aDelegator = WD.te(aWorld, aX, aY, aZ, aSide, T);
		if (aDelegator.mTileEntity instanceof ITileEntityDecolorable) return ((ITileEntityDecolorable)aDelegator.mTileEntity).removePaint(aDelegator.mSideOfTileEntity);
		Block aBlock = aDelegator.getBlock();
		if (aBlock instanceof IBlockDecolorable) return ((IBlockDecolorable)aBlock).removePaint(aWorld, aDelegator.mX, aDelegator.mY, aDelegator.mZ, aDelegator.mSideOfTileEntity);
		if (aBlock == Blocks.stained_hardened_clay) return aDelegator.setBlock(Blocks.hardened_clay);
		if (aBlock == Blocks.stained_glass_pane) return aDelegator.setBlock(Blocks.glass_pane);
		if (aBlock == Blocks.stained_glass) return aDelegator.setBlock(Blocks.glass);
		return F;
	}
	
	static {
		LH.add("gt6.behaviour.paintremoverspray.tooltip", "Can Decolor things");
		LH.add("gt6.behaviour.paintremoverspray.uses", "Remaining Uses:");
		LH.add("gt6.behaviour.unstackable", "Not usable when stacked!");
	}
	
	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		aList.add(LH.get("gt6.behaviour.paintremoverspray.tooltip"));
		NBTTagCompound tNBT = aStack.getTagCompound();
		long tRemaining = (ST.equal(aStack, mFull, T)?mUses:tNBT==null?0:tNBT.getLong("gt6.remaining"));
		aList.add(LH.get("gt6.behaviour.paintremoverspray.uses") + " " + (tRemaining / 10) + "." + (tRemaining % 10));
		aList.add(LH.get("gt6.behaviour.unstackable"));
		return aList;
	}
}
