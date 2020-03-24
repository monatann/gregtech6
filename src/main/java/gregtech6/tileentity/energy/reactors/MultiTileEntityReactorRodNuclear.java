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

import gregapi6.data.FL;
import gregapi6.data.LH;
import gregapi6.data.MT;
import gregapi6.render.BlockTextureDefault;
import gregapi6.render.BlockTextureMulti;
import gregapi6.render.ITexture;
import gregapi6.util.ST;
import gregapi6.util.UT;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityReactorRodNuclear extends MultiTileEntityReactorRodBase {
	public long mDurability = 0;
	public int mNeutronSelf = 128, mNeutronOther = 128, mNeutronDiv = 8, mNeutronMax = 128;
	public short mDepleted = -1;

	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		mDurability = aNBT.getLong(aNBT.hasKey(NBT_DURABILITY) ? NBT_DURABILITY : NBT_MAXDURABILITY);
		if (aNBT.hasKey(NBT_NUCLEAR_SELF )) mNeutronSelf    = aNBT.getInteger(NBT_NUCLEAR_SELF );
		if (aNBT.hasKey(NBT_NUCLEAR_OTHER)) mNeutronOther   = aNBT.getInteger(NBT_NUCLEAR_OTHER);
		if (aNBT.hasKey(NBT_NUCLEAR_DIV  )) mNeutronDiv     = aNBT.getInteger(NBT_NUCLEAR_DIV  );
		if (aNBT.hasKey(NBT_NUCLEAR_MAX	 )) mNeutronMax 	= aNBT.getInteger(NBT_NUCLEAR_MAX);
		if (aNBT.hasKey(NBT_VALUE        )) mDepleted       = aNBT.getShort(NBT_VALUE);
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
		aList.add(LH.Chat.DGRAY  + "Used in Nuclear Reactor Core");
		aList.add(LH.Chat.CYAN   + "Remaining: " + LH.Chat.WHITE + (mDurability / 120000) + LH.Chat.CYAN   + " Minutes");
		// Tooltip Cycles every 10 Seconds with this commented in. For use when Tooltip becomes too large later on. Change %2 to amount of Cases.
//      switch ((int)((CLIENT_TIME / 200) % 2)) {
//      case 0:
			aList.add(LH.Chat.CYAN   + "When used with Distilled Water:");
			aList.add(LH.Chat.GREEN  + "Emission: "  + LH.Chat.WHITE + mNeutronOther          + LH.Chat.PURPLE + " Neutrons/t");
			aList.add(LH.Chat.GREEN  + "Self: "      + LH.Chat.WHITE + mNeutronSelf           + LH.Chat.PURPLE + " Neutrons/t");
			aList.add(LH.Chat.GREEN  + "Maximum: "   + LH.Chat.WHITE + mNeutronMax		      + LH.Chat.PURPLE + " Neutrons/t");
			aList.add(LH.Chat.YELLOW + "Factor: "    + LH.Chat.WHITE + "1/" + mNeutronDiv     + LH.Chat.GRAY);
			if (mNeutronDiv <= 4) aList.add(LH.Chat.RED + "This Fuel is" + LH.Chat.BLINKING_RED + " Critical");
//          break;
//      case 1:
			aList.add(LH.Chat.CYAN   + "When used with IC2 Coolant:");
			aList.add(LH.Chat.GREEN  + "Emission: "  + LH.Chat.WHITE + mNeutronOther*4        + LH.Chat.PURPLE + " Neutrons/t");
			aList.add(LH.Chat.GREEN  + "Self: "      + LH.Chat.WHITE + mNeutronSelf*4         + LH.Chat.PURPLE + " Neutrons/t");
			aList.add(LH.Chat.YELLOW + "Factor: "    + LH.Chat.WHITE + "1/" + mNeutronDiv*2   + LH.Chat.GRAY);
//          break;
//      }
	}

	@Override
	// Gets called every 20 Ticks.
	public int getReactorRodNeutronEmission(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack) {
		if (FL.Coolant_IC2.is(aReactor.mTanks[0])) {
			// TODO: Change Loss of Durability to be changed once the System below this legacy case has been proven.
			mDurability -= 2000;
			if (mDurability <= 0) mDurability = -1;
			UT.NBT.set(aStack, writeItemNBT(aStack.hasTagCompound() ? aStack.getTagCompound() : UT.NBT.make()));
			aReactor.mNeutronCounts[aSlot] += mNeutronSelf*4;
			return mNeutronOther * 4 + (int)UT.Code.divup(aReactor.oNeutronCounts[aSlot]-mNeutronSelf * 4, mNeutronDiv * 2);
		}
		if (FL.distw(aReactor.mTanks[0])) {
			aReactor.mNeutronCounts[aSlot] += mNeutronSelf;
			long tEmission = mNeutronOther + UT.Code.divup(aReactor.oNeutronCounts[aSlot]-mNeutronSelf, mNeutronDiv);
			long tDurabilityLoss = (tEmission * 4 + mNeutronSelf) < mNeutronMax ? 2000 : UT.Code.divup(8000 * (tEmission * 4 + mNeutronSelf), mNeutronMax);
			mDurability = tDurabilityLoss > mDurability ? -1 : mDurability - tDurabilityLoss;
			UT.NBT.set(aStack, writeItemNBT(aStack.hasTagCompound() ? aStack.getTagCompound() : UT.NBT.make()));
			return UT.Code.bindInt(tEmission);
		}
		return 0;
	}

	@Override
	// Gets called every Tick.
	public boolean getReactorRodNeutronReaction(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack) {
		aReactor.mEnergy += aReactor.oNeutronCounts[aSlot];
		if (mDurability <= 0) {
			ST.meta(aStack, mDepleted);
			ST.nbt(aStack, null);
		}
		return T;
	}

	@Override
	// Gets called every 20 Ticks.
	public int getReactorRodNeutronReflection(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack, int aNeutrons) {
		aReactor.mNeutronCounts[aSlot] += aNeutrons;
		return 0;
	}

	@Override
	public int getReactorRodNeutronMaximum(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack) {
		return mNeutronMax;
	}

	@Override public ITexture getReactorRodTextureSides(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack, boolean aActive) {return BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[1], mRGBa, T), BlockTextureDefault.get(sOverlays[1], aActive ? UNCOLOURED : MT.Pb.fRGBaSolid));}
	@Override public ITexture getReactorRodTextureTop  (MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack, boolean aActive) {return BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[0], mRGBa, T), BlockTextureDefault.get(sOverlays[0], aActive ? UNCOLOURED : MT.Pb.fRGBaSolid));}

	@Override public String getTileEntityName() {return "gt6.multitileentity.generator.reactor.rods.nuclear";}
}
