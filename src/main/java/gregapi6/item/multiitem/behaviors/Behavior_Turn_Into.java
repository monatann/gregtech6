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

package gregapi6.item.multiitem.behaviors;

import static gregapi6.data.CS.*;

import gregapi6.code.IItemContainer;
import gregapi6.item.multiitem.MultiItem;
import gregapi6.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi6.util.ST;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.IFluidContainerItem;

public class Behavior_Turn_Into extends AbstractBehaviorDefault {
	public final IItemContainer mTurnInto;
	
	public Behavior_Turn_Into(IItemContainer aTurnInto) {
		mTurnInto = aTurnInto;
	}
	
	@Override
	public boolean isItemStackUsable(MultiItem aItem, ItemStack aStack) {
		if (mTurnInto == null || !mTurnInto.exists() || (aStack.getItem() instanceof IFluidContainerItem && ((IFluidContainerItem)aStack.getItem()).getFluid(aStack) != null)) return T;
		ST.set(aStack, mTurnInto.get(1), F, F);
		return T;
	}
}
