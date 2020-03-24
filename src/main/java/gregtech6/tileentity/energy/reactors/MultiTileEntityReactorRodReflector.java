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

package gregtech6.tileentity.energy.reactors;

import static gregapi6.data.CS.*;

import java.util.List;

import gregapi6.data.LH;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityReactorRodReflector extends MultiTileEntityReactorRodBase {
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(LH.Chat.CYAN + "Reflects Neutrons back to their Source, boosting the Reaction");
		aList.add(LH.Chat.DGRAY + "Used in Nuclear Reactor Core");
	}

	@Override
	public int getReactorRodNeutronEmission(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack) {
		return 0;
	}

	@Override
	public boolean getReactorRodNeutronReaction(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack) {
		return F;
	}

	@Override
	public int getReactorRodNeutronReflection(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack, int aNeutrons) {
		return aNeutrons;
	}

	@Override public String getTileEntityName() {return "gt6.multitileentity.generator.reactor.rods.reflector";}
}
