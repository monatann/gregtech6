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

import gregapi6.block.IBlockToolable;
import gregapi6.code.ArrayListNoNulls;
import gregapi6.damage.DamageSources;
import gregapi6.data.CS.SFX;
import gregapi6.data.LH;
import gregapi6.item.multiitem.MultiItem;
import gregapi6.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi6.util.ST;
import gregapi6.util.UT;
import gregapi6.util.WD;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;

public class Behavior_Spray_Extinguisher extends AbstractBehaviorDefault {
	private final ItemStack mEmpty, mUsed, mFull;
	private final long mUses;
	
	public Behavior_Spray_Extinguisher(ItemStack aEmpty, ItemStack aUsed, ItemStack aFull, long aUses) {
		mEmpty = aEmpty;
		mUsed = aUsed;
		mFull = aFull;
		mUses = aUses * 10;
	}
	
	@Override
	public boolean onItemUseFirst(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ) {
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
			long tExtinguished = extinguish(aWorld, aX, aY, aZ, aSide, UT.Entities.hasInfiniteItems(aPlayer)?mUses:tUses, aPlayer, aStack, aHitX, aHitY, aHitZ);
			if (tExtinguished > 0) {
				UT.Sounds.send(aWorld, SFX.IC_SPRAY, 1.0F, 2.0F, aX, aY, aZ);
				UT.Sounds.send(aWorld, SFX.MC_FIZZ, 1.0F, 1.5F, aX, aY, aZ);
				if (!UT.Entities.hasInfiniteItems(aPlayer)) tUses -= tExtinguished;
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
	
	public long extinguish(World aWorld, int aX, int aY, int aZ, byte aSide, long aUses, EntityPlayer aPlayer, ItemStack aStack, float aHitX, float aHitY, float aHitZ) {
		if (aPlayer == null || SIDES_INVALID[aSide] || aPlayer instanceof FakePlayer || !WD.obstructed(aWorld, aX, aY, aZ, aSide)) {
			List<String> tChatReturn = new ArrayListNoNulls<>();
			long tDamage = IBlockToolable.Util.onToolClick(TOOL_extinguisher, aUses*1000, 1, aPlayer, tChatReturn, aPlayer==null?null:aPlayer.inventory, aPlayer!=null&&aPlayer.isSneaking(), aStack, aWorld, aSide, aX, aY, aZ, aHitX, aHitY, aHitZ);
			UT.Entities.sendchat(aPlayer, tChatReturn, F);
			if (tDamage > 0) return Math.min(10, tDamage / 1000);
		}
		
		if (aUses < 10) return 0;
		
		long rUses = 0;
		
		aX += OFFSETS_X[aSide];
		aY += OFFSETS_Y[aSide];
		aZ += OFFSETS_Z[aSide];
		
		for (int i = -1; i < 2; i++) for (int j = -1; j < 2; j++) for (int k = -1; k < 2; k++) {
			if (rUses + 10 > aUses) return rUses;
			if (aWorld.getBlock(aX+i, aY+j, aZ+k) == Blocks.fire && aWorld.setBlock(aX+i, aY+j, aZ+k, NB, 0, 3)) rUses += 10;
		}
		
		for (Object tEntity : aWorld.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getBoundingBox(aX-2, aY-2, aZ-2, aX+3, aY+3, aZ+3))) {
			if (rUses + 10 > aUses) return rUses;
			if (tEntity.getClass() == EntityBlaze.class) {
				((EntityBlaze)tEntity).attackEntityFrom(DamageSources.getCombatDamage("player", aPlayer, null), 10);
				rUses += 10;
			} else {
				if (((Entity)tEntity).isBurning()) {
					((Entity)tEntity).extinguish();
					rUses += 10;
				}
			}
		}
		
		return rUses;
	}
	
	static {
		LH.add("gt6.behaviour.extinguisher.tooltip", "Extinguishes Fire");
		LH.add("gt6.behaviour.extinguisher.uses", "Remaining Uses:");
		LH.add("gt6.behaviour.unstackable", "Not usable when stacked!");
	}
	
	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		aList.add(LH.get("gt6.behaviour.extinguisher.tooltip"));
		NBTTagCompound tNBT = aStack.getTagCompound();
		long tRemaining = (ST.equal(aStack, mFull, T)?mUses:tNBT==null?0:tNBT.getLong("gt6.remaining"));
		aList.add(LH.get("gt6.behaviour.extinguisher.uses") + " " + (tRemaining / 10) + "." + (tRemaining % 10));
		aList.add(LH.get("gt6.behaviour.unstackable"));
		return aList;
	}
}
