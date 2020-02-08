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

import java.util.ArrayList;
import java.util.List;

import gregapi6.code.ArrayListNoNulls;
import gregapi6.data.CS.SFX;
import gregapi6.data.LH;
import gregapi6.data.TD;
import gregapi6.item.multiitem.MultiItem;
import gregapi6.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi6.util.UT;
import gregapi6.util.WD;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Behavior_Scanner extends AbstractBehaviorDefault {
	public final int mScanLevel;
	
	public Behavior_Scanner(int aScanLevel) {
		mScanLevel = aScanLevel;
	}
	
	@Override
	public boolean onItemUseFirst(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float hitX, float hitY, float hitZ) {
		if (aPlayer instanceof EntityPlayerMP) {
			ArrayList<String> tList = new ArrayListNoNulls<>();
			if (aItem.useEnergy(TD.Energy.EU, aStack, WD.scan(tList, aPlayer, aWorld, mScanLevel, aX, aY, aZ, aSide, hitX, hitY, hitZ), aPlayer, aPlayer.inventory, aWorld, aX, aY, aZ, T)) UT.Entities.sendchat(aPlayer, tList, F);
			return T;
		}
		UT.Sounds.play(SFX.IC_SCANNER, 20, 1.0F, aX, aY, aZ);
		return aPlayer instanceof EntityPlayerMP;
	}
	
	@Override
	public boolean onLeftClickEntity(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, Entity aEntity) {
		if (mScanLevel > 100) {
			UT.Entities.sendchat(aPlayer, aEntity.getClass().getName());
			UT.Sounds.play(SFX.IC_SCANNER, 20, 1.0F, aEntity);
		}
		return T;
	}
	
	@Override
	public boolean onRightClickEntity(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, Entity aEntity) {
		if (mScanLevel > 100) {
			UT.Entities.sendchat(aPlayer, aEntity.getClass().getName());
			UT.Sounds.play(SFX.IC_SCANNER, 20, 1.0F, aEntity);
		}
		return T;
	}
	
	static {
		LH.add("gt6.behaviour.scanning", "Can scan Blocks in World");
	}
	
	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		aList.add(LH.get("gt6.behaviour.scanning"));
		return aList;
	}
}
