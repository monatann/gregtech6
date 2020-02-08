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

import gregapi6.data.CS.SFX;
import gregapi6.data.FL;
import gregapi6.item.multiitem.MultiItem;
import gregapi6.item.multiitem.behaviors.IBehavior;
import gregapi6.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi6.util.UT;
import gregapi6.util.WD;
import ic2.api.crops.ICropTile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidContainerItem;

public class Behavior_Watering_Crops extends AbstractBehaviorDefault {
	public static final IBehavior<MultiItem> INSTANCE = new Behavior_Watering_Crops();
	
	@Override
	public boolean onItemUseFirst(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float hitX, float hitY, float hitZ) {
		if (aWorld.isRemote || aPlayer == null || !aPlayer.canPlayerEdit(aX, aY, aZ, aSide, aStack)) return F;
		FluidStack mFluid = ((IFluidContainerItem)aItem).getFluid(aStack);
		if (FL.water(mFluid)) {
			TileEntity tTileEntity = WD.te(aWorld, aX, aY, aZ, F);
			try {if (tTileEntity instanceof ICropTile) {
				int tHydration = ((ICropTile)tTileEntity).getHydrationStorage();
				int tDrained = Math.min((200-tHydration)/10, mFluid.amount);
				if (tDrained > 0) {
					((IFluidContainerItem)aItem).drain(aStack, tDrained, T);
					((ICropTile)tTileEntity).setHydrationStorage(tHydration + tDrained*10);
					UT.Sounds.send(aWorld, SFX.MC_LIQUID_WATER, 1.0F, 1.0F, aX, aY, aZ);
				}
				return T;
			}} catch(Throwable e) {/**/}
		}
		return F;
	}
}
