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

import gregapi6.data.CS.SFX;
import gregapi6.data.LH;
import gregapi6.item.multiitem.MultiItem;
import gregapi6.item.multiitem.MultiItemTool;
import gregapi6.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi6.util.UT;
import gregapi6.util.WD;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.IEssentiaTransport;

public class Behavior_Plunger_Essentia extends AbstractBehaviorDefault {
	private final int mCosts;
	
	public Behavior_Plunger_Essentia(int aCosts) {
		mCosts = aCosts;
	}
	
	@Override
	public boolean onItemUseFirst(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float hitX, float hitY, float hitZ) {
		if (aWorld.isRemote) return F;
		TileEntity aTileEntity = WD.te(aWorld, aX, aY, aZ, T);
		if (aTileEntity instanceof IEssentiaTransport) {
			if (UT.Entities.hasInfiniteItems(aPlayer) || ((MultiItemTool)aItem).doDamage(aStack, mCosts, aPlayer)) {
				UT.Sounds.send(aWorld, SFX.IC_TRAMPOLINE, 1.0F, -1, aX, aY, aZ);
				for (ForgeDirection tDirection : ForgeDirection.VALID_DIRECTIONS) ((IEssentiaTransport)aTileEntity).takeEssentia(((IEssentiaTransport)aTileEntity).getEssentiaType(tDirection), ((IEssentiaTransport)aTileEntity).getEssentiaAmount(tDirection), tDirection);
				return T;
			}
		}
		return F;
	}
	
	static {
		LH.add("gt6.behaviour.plunger.essentia", "Clears Essentia from Containers and Tubes");
	}
	
	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		aList.add(LH.get("gt6.behaviour.plunger.essentia"));
		return aList;
	}
}
