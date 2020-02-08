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

package gregapi6.wooddict;

import static gregapi6.data.CS.*;

import java.util.Set;

import gregapi6.code.HashSetNoNulls;
import gregapi6.code.ItemStackContainer;
import gregapi6.data.CS.PlankData;
import gregapi6.data.IL;
import gregapi6.data.MT;
import gregapi6.data.OP;
import gregapi6.oredict.OreDictItemData;
import gregapi6.oredict.OreDictManager;
import gregapi6.oredict.OreDictMaterial;
import gregapi6.render.IconContainerCopied;
import gregapi6.util.ST;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class PlankEntry {
	public final Set<WoodEntry> mWoodEntries = new HashSetNoNulls<>();
	public final Set<BeamEntry> mBeamEntries = new HashSetNoNulls<>();
	public ItemStack mPlank, mSlab, mStair, mStick;
	public OreDictMaterial mMaterialPlank = MT.Wood;
	public int mPlankIconIndex, mStickCountHand = 1, mStickCountSaw = 2, mStickCountLathe = 2;
	
	public PlankEntry(ItemStack aPlank) {
		this(aPlank, 0);
	}
	public PlankEntry(ItemStack aPlank, int aPlankIconIndex) {
		this(aPlank, IL.Plank_Slab.get(1, ST.make(Blocks.wooden_slab, 1, 0)), aPlankIconIndex);
	}
	public PlankEntry(ItemStack aPlank, ItemStack aSlab) {
		this(aPlank, aSlab, 0);
	}
	public PlankEntry(ItemStack aPlank, ItemStack aSlab, int aPlankIconIndex) {
		this(aPlank, aSlab, IL.Plank_Stairs.get(1, ST.make(Blocks.oak_stairs, 1, 0)), aPlankIconIndex);
	}
	public PlankEntry(ItemStack aPlank, ItemStack aSlab, ItemStack aStair) {
		this(aPlank, aSlab, aStair, 0);
	}
	public PlankEntry(ItemStack aPlank, ItemStack aSlab, ItemStack aStair, int aPlankIconIndex) {
		this(aPlank, aSlab, aStair, MT.Wood, aPlankIconIndex);
	}
	public PlankEntry(ItemStack aPlank, OreDictMaterial aMaterialPlank) {
		this(aPlank, aMaterialPlank, 0);
	}
	public PlankEntry(ItemStack aPlank, OreDictMaterial aMaterialPlank, int aPlankIconIndex) {
		this(aPlank, IL.Plank_Slab.get(1, ST.make(Blocks.wooden_slab, 1, 0)), aMaterialPlank, aPlankIconIndex, OP.stick.mat(aMaterialPlank, 1));
	}
	public PlankEntry(ItemStack aPlank, ItemStack aSlab, OreDictMaterial aMaterialPlank) {
		this(aPlank, aSlab, aMaterialPlank, 0);
	}
	public PlankEntry(ItemStack aPlank, ItemStack aSlab, OreDictMaterial aMaterialPlank, int aPlankIconIndex) {
		this(aPlank, aSlab, aMaterialPlank, aPlankIconIndex, OP.stick.mat(aMaterialPlank, 1));
	}
	public PlankEntry(ItemStack aPlank, ItemStack aSlab, OreDictMaterial aMaterialPlank, int aPlankIconIndex, ItemStack aStick) {
		this(aPlank, aSlab, aMaterialPlank, aPlankIconIndex, aStick, 1, 2, 2);
	}
	public PlankEntry(ItemStack aPlank, ItemStack aSlab, OreDictMaterial aMaterialPlank, int aPlankIconIndex, ItemStack aStick, int aStickCountHand, int aStickCountSaw, int aStickCountLathe) {
		this(aPlank, aSlab, IL.Plank_Stairs.get(1, ST.make(Blocks.oak_stairs, 1, 0)), aMaterialPlank, aPlankIconIndex, aStick, aStickCountHand, aStickCountSaw, aStickCountLathe);
	}
	public PlankEntry(ItemStack aPlank, ItemStack aSlab, ItemStack aStair, OreDictMaterial aMaterialPlank) {
		this(aPlank, aSlab, aStair, aMaterialPlank, 0);
	}
	public PlankEntry(ItemStack aPlank, ItemStack aSlab, ItemStack aStair, OreDictMaterial aMaterialPlank, int aPlankIconIndex) {
		this(aPlank, aSlab, aStair, aMaterialPlank, aPlankIconIndex, OP.stick.mat(aMaterialPlank, 1));
	}
	public PlankEntry(ItemStack aPlank, ItemStack aSlab, ItemStack aStair, OreDictMaterial aMaterialPlank, int aPlankIconIndex, ItemStack aStick) {
		this(aPlank, aSlab, aStair, aMaterialPlank, aPlankIconIndex, aStick, 1, 2, 2);
	}
	public PlankEntry(ItemStack aPlank, ItemStack aSlab, ItemStack aStair, OreDictMaterial aMaterialPlank, int aPlankIconIndex, ItemStack aStick, int aStickCountHand, int aStickCountSaw, int aStickCountLathe) {
		if (ST.invalid(aPlank)) return;
		mPlank = ST.amount(1, aPlank);
		mStair = ST.amount(1, aStair);
		mSlab = ST.amount(1, aSlab);
		mStick = ST.amount(1, aStick);
		mStickCountHand = aStickCountHand;
		mStickCountSaw = aStickCountSaw;
		mStickCountLathe = aStickCountLathe;
		mMaterialPlank = aMaterialPlank;
		mPlankIconIndex = aPlankIconIndex;
		
		if (mPlankIconIndex > 0 && ST.invalid(PlankData.PLANKS[mPlankIconIndex])) {
			PlankData.PLANKS[mPlankIconIndex] = ST.amount(1, mPlank);
			PlankData.PLANK_ICONS[mPlankIconIndex] = new IconContainerCopied(ST.block(mPlank), ST.meta_(mPlank), SIDE_ANY);
		}
		
		if (ST.valid(mPlank) && !WoodDictionary.PLANKS.containsKey(new ItemStackContainer(mPlank))) {
			OreDictManager.INSTANCE.setItemData_(mPlank, new OreDictItemData(mMaterialPlank, U));
			WoodDictionary.PLANKS_ANY.put(mPlank, this);
			WoodDictionary.PLANKS.put(mPlank, this);
			WoodDictionary.IGNORED_OREDICT_REGISTRATIONS.add(ST.item_(mPlank));
		}
		if (ST.valid(mStair) && !WoodDictionary.STAIRS.containsKey(new ItemStackContainer(mStair))) {
			OreDictManager.INSTANCE.setItemData_(mStair, new OreDictItemData(mMaterialPlank, U4*3));
			WoodDictionary.PLANKS_ANY.put(mStair, this);
			WoodDictionary.STAIRS.put(mStair, this);
			WoodDictionary.IGNORED_OREDICT_REGISTRATIONS.add(ST.item_(mStair));
		}
		if (ST.valid(mSlab) && !WoodDictionary.SLABS.containsKey(new ItemStackContainer(mSlab))) {
			OreDictManager.INSTANCE.setItemData_(mSlab , new OreDictItemData(mMaterialPlank, U2));
			WoodDictionary.PLANKS_ANY.put(mSlab, this);
			WoodDictionary.SLABS.put(mSlab, this);
			WoodDictionary.IGNORED_OREDICT_REGISTRATIONS.add(ST.item_(mSlab));
		}
	}
}
