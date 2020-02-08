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
import gregapi6.item.multiitem.MultiItemTool;
import gregapi6.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi6.util.UT;
import gregapi6.util.WD;
import gregtech6.GT6_Main;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;

public class Behavior_FlintAndTinder extends AbstractBehaviorDefault {
	@Override
	public boolean onItemUseFirst(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (aPlayer != null && SIDES_VALID[aSide] && !(aPlayer instanceof FakePlayer) && WD.obstructed(aWorld, aX, aY, aZ, aSide)) return F;
		List<String> tChatReturn = new ArrayListNoNulls<>();
		long tDamage = 10000;
		if (RNGSUS.nextInt(100)<GT6_Main.gt_proxy.mFlintChance) tDamage = IBlockToolable.Util.onToolClick(TOOL_igniter, Long.MAX_VALUE, 1, aPlayer, tChatReturn, aPlayer==null?null:aPlayer.inventory, aPlayer != null && aPlayer.isSneaking(), aStack, aWorld, aSide, aX, aY, aZ, aHitX, aHitY, aHitZ);
		UT.Entities.sendchat(aPlayer, tChatReturn, F);
		if (aWorld.isRemote) return F;
		if (aPlayer == null || !UT.Entities.hasInfiniteItems(aPlayer)) ((MultiItemTool)aItem).doDamage(aStack, UT.Code.units(Math.max(10000, tDamage), 10000, 100, T), aPlayer);
		UT.Sounds.send(aWorld, SFX.MC_IGNITE, 1.0F, 1.0F, aX, aY, aZ);
		return T;
	}
	
	@Override
	public boolean onItemUse(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float hitX, float hitY, float hitZ) {
		return T;
	}
	
	@Override
	public boolean onLeftClickEntity(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, Entity aEntity) {
		if (aPlayer.worldObj.isRemote) return F;
		if (aEntity instanceof EntityCreeper) {
			if (!UT.Entities.hasInfiniteItems(aPlayer)) ((MultiItemTool)aItem).doDamage(aStack, 100, aPlayer);
			UT.Sounds.send(aPlayer.worldObj, SFX.MC_IGNITE, 1.0F, 1.0F, UT.Code.roundDown(aEntity.posX), UT.Code.roundDown(aEntity.posY), UT.Code.roundDown(aEntity.posZ));
			((EntityCreeper)aEntity).func_146079_cb();
			return T;
		}
		return F;
	}
	
	static {
		LH.add("gt6.behaviour.flintandtinder", "Ignites a Fire with a Chance of ");
	}
	
	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		aList.add(LH.get("gt6.behaviour.flintandtinder") + GT6_Main.gt_proxy.mFlintChance + "%");
		return aList;
	}
}
