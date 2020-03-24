/**
 * Copyright (c) 2020 GregTech-6 Team
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
import gregapi6.util.UT;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityReactorRodBreeder extends MultiTileEntityReactorRodBase {
	public long mDurability = 0;
	public short mProduct = -1;
	public String mProductName = "";

	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		mDurability = aNBT.getLong(aNBT.hasKey(NBT_DURABILITY) ? NBT_DURABILITY : NBT_MAXDURABILITY);
		if (aNBT.hasKey(NBT_VALUE        	)) 	mProduct      	 = aNBT.getShort(NBT_VALUE);
	}

	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setNumber(aNBT, NBT_DURABILITY, mDurability);
	}

	@Override
	public NBTTagCompound writeItemNBT2(NBTTagCompound aNBT) {
		UT.NBT.setNumber(aNBT, NBT_DURABILITY, mDurability);
		return super.writeItemNBT2(aNBT);
	}

	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(LH.Chat.CYAN + "Absorbs Neutrons to breed into a new Material and emits less Heat to Coolant");
		aList.add(LH.Chat.DGRAY + "Used in Nuclear Reactor Core");
		aList.add(LH.Chat.CYAN   + "Needed: " + LH.Chat.WHITE + mDurability            + LH.Chat.CYAN   + " Neutrons");
		if (mProductName.equals(""))  mProductName = ST.meta(aStack.copy(), mProduct).getDisplayName();
		aList.add(LH.Chat.YELLOW   + "Turns into: " + LH.Chat.WHITE + mProductName);
	}

	@Override
	public int getReactorRodNeutronEmission(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack) {
		return 0;
	}

	@Override
	public boolean getReactorRodNeutronReaction(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack) {
		aReactor.mEnergy += aReactor.oNeutronCounts[aSlot] / 2;
		mDurability -= UT.Code.roundUp(aReactor.oNeutronCounts[aSlot] * Math.pow(1.5, aReactor.oNeutronCounts[aSlot] / 500.0));
		if (mDurability <= 0) {
			ST.meta(aStack, mProduct);
			ST.nbt(aStack, null);
			mDurability = 0;
			aReactor.updateClientData();
		}
		UT.NBT.set(aStack, writeItemNBT(aStack.hasTagCompound() ? aStack.getTagCompound() : UT.NBT.make()));
		return T;
	}

	@Override
	public int getReactorRodNeutronReflection(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack, int aNeutrons) {
		aReactor.mNeutronCounts[aSlot] += aNeutrons;
		return 0;
	}

	@Override public String getTileEntityName() {return "gt6.multitileentity.generator.reactor.rods.breeder";}
}