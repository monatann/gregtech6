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
import gregapi6.data.CS.SFX;
import gregapi6.data.LH;
import gregapi6.item.multiitem.MultiItem;
import gregapi6.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi6.util.ST;
import gregapi6.util.UT;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class Behavior_Lighter extends AbstractBehaviorDefault {
	private final ItemStack mEmptyLighter, mUsedLighter, mFullLighter;
	private final long mFuelAmount, mChance;
	
	public Behavior_Lighter(long aChance) {
		mFullLighter = null;
		mUsedLighter = null;
		mEmptyLighter = null;
		mFuelAmount = 1;
		mChance = UT.Code.bind(0, 10000, aChance);
	}
	
	public Behavior_Lighter(ItemStack aEmptyLighter, ItemStack aUsedLighter, ItemStack aFullLighter, long aFuelAmount, long aChance) {
		mFullLighter = aFullLighter;
		mUsedLighter = aUsedLighter;
		mEmptyLighter = aEmptyLighter;
		mFuelAmount = aFuelAmount;
		mChance = UT.Code.bind(0, 10000, aChance);
	}
	
	@Override
	public boolean onLeftClickEntity(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, Entity aEntity) {
		if (aPlayer.worldObj.isRemote || (aStack.stackSize != 1 && (mFuelAmount != 1 || mEmptyLighter != null))) return F;
		
		boolean rOutput = F;
		
		if (aEntity instanceof EntityCreeper) {
			prepare(aStack);
			long tFuelAmount = UT.NBT.getLighterFuel(aStack);
			if (ST.equal(aStack, mUsedLighter, T)) {
				UT.Sounds.send(aPlayer.worldObj, SFX.MC_IGNITE, 1.0F, 1.0F, UT.Code.roundDown(aEntity.posX), UT.Code.roundDown(aEntity.posY), UT.Code.roundDown(aEntity.posZ));
				((EntityCreeper)aEntity).func_146079_cb();
				if (!UT.Entities.hasInfiniteItems(aPlayer)) tFuelAmount--;
				rOutput = T;
			}
			UT.NBT.setLighterFuel(aStack, tFuelAmount);
			if (tFuelAmount <= 0) useUp(aStack);
		}
		return rOutput;
	}
	
	@Override
	public boolean onItemUseFirst(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (aWorld.isRemote || (aStack.stackSize != 1 && (mFuelAmount != 1 || mEmptyLighter != null))) return F;
		
		prepare(aStack);
		if (ST.invalid(mUsedLighter)) {
			UT.Sounds.send(aWorld, SFX.MC_IGNITE, 1.0F, 1.0F, aX, aY, aZ);
			long tDamage = 0;
			if (RNGSUS.nextInt(10000) < mChance) {
				List<String> tChatReturn = new ArrayListNoNulls<>();
				tDamage = IBlockToolable.Util.onToolClick(TOOL_igniter, Long.MAX_VALUE, 3, aPlayer, tChatReturn, aPlayer.inventory, aPlayer.isSneaking(), aStack, aWorld, aSide, aX, aY, aZ, aHitX, aHitY, aHitZ);
				UT.Entities.sendchat(aPlayer, tChatReturn, F);
			} else {
				tDamage = 10000;
			}
			if (tDamage != 0) {
				if (!UT.Entities.hasInfiniteItems(aPlayer)) useUp(aStack);
				return T;
			}
		} else if (ST.equal(aStack, mUsedLighter, T)) {
			UT.Sounds.send(aWorld, SFX.MC_IGNITE, 1.0F, 1.0F, aX, aY, aZ);
			long tDamage = 0;
			if (RNGSUS.nextInt(10000) < mChance) {
				List<String> tChatReturn = new ArrayListNoNulls<>();
				tDamage = IBlockToolable.Util.onToolClick(TOOL_igniter, Long.MAX_VALUE, 3, aPlayer, tChatReturn, aPlayer.inventory, aPlayer.isSneaking(), aStack, aWorld, aSide, aX, aY, aZ, aHitX, aHitY, aHitZ);
				UT.Entities.sendchat(aPlayer, tChatReturn, F);
			} else {
				tDamage = 10000;
			}
			if (tDamage != 0) {
				if (!UT.Entities.hasInfiniteItems(aPlayer)) {
					long tFuelAmount = UT.NBT.getLighterFuel(aStack) - UT.Code.units(tDamage, 10000, 1, T);
					UT.NBT.setLighterFuel(aStack, tFuelAmount);
					if (tFuelAmount <= 0) useUp(aStack);
				}
				return T;
			}
		}
		return F;
	}
	
	private void prepare(ItemStack aStack) {
		if (ST.valid(mFullLighter) && ST.equal(aStack, mFullLighter, T)) {
			aStack.func_150996_a(mUsedLighter.getItem());
			ST.meta_(aStack, ST.meta_(mUsedLighter));
			UT.NBT.setLighterFuel(aStack, mFuelAmount);
		}
	}
	
	private void useUp(ItemStack aStack) {
		if (ST.invalid(mEmptyLighter)) {
			aStack.stackSize--;
		} else {
			aStack.func_150996_a(mEmptyLighter.getItem());
			ST.meta_(aStack, ST.meta_(mEmptyLighter));
		}
	}
	
	static {
		LH.add("gt6.behaviour.lighter.tooltip", "Can light things on Fire");
		LH.add("gt6.behaviour.lighter.uses", "Remaining Uses:");
		LH.add("gt6.behaviour.lighter.chance", "Chance:");
		LH.add("gt6.behaviour.singleuse", "Single Use");
	}
	
	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		aList.add(LH.get("gt6.behaviour.lighter.tooltip"));
		NBTTagCompound tNBT = aStack.getTagCompound();
		if (mFuelAmount > 1) {
			long tFuelAmount = (ST.invalid(mFullLighter)?1:tNBT==null?ST.equal(aStack, mFullLighter, T)?mFuelAmount:0:UT.NBT.getLighterFuel(aStack));
			aList.add(LH.get("gt6.behaviour.lighter.uses") + " " + tFuelAmount);
		} else {
			aList.add(LH.get("gt6.behaviour.singleuse"));
		}
		if (mChance < 10000) aList.add(LH.get("gt6.behaviour.lighter.chance") + " " + (mChance / 100) + "." + (mChance % 100) + "%");
		if (aStack.stackSize != 1 && (mFuelAmount != 1 || ST.valid(mEmptyLighter))) aList.add(LH.Chat.RED + LH.get(LH.REQUIREMENT_UNSTACKED));
		return aList;
	}
}
