/**
 * Copyright (c) 2019 Gregorius Techneticies
 *
 * This file is part of GregTech.
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
 * along with GregTech. If not, see <http://www.gnu.org/licenses/>.
 */

package gregtech6.tileentity.energy.reactors;

import static gregapi6.data.CS.*;

import java.util.List;

import gregapi6.data.LH;
import gregapi6.util.ST;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityReactorRodProduct extends MultiTileEntityReactorRodBase {
	public short mBreeding = -1;
	public String mBreedingName = "";

	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_VALUE        	)) 	mBreeding      	 = aNBT.getShort(NBT_VALUE);
	}

	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(LH.Chat.DGRAY + "Used in Nuclear Reactor Core");
		if (mBreedingName.equals(""))  mBreedingName = ST.meta(aStack.copy(), mBreeding).getDisplayName();
		aList.add(LH.Chat.YELLOW + "Breed from " + mBreedingName);
	}

	@Override public String getTileEntityName() {return "gt6.multitileentity.generator.reactor.rods.product";}
}