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

import java.util.Set;

import gregapi6.code.HashSetNoNulls;
import gregapi6.data.MT;
import gregapi6.data.OP;
import gregapi6.oredict.OreDictMaterial;
import gregapi6.util.ST;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class WoodEntry {
	public final Set<SaplingEntry> mSaplingEntries = new HashSetNoNulls<>();
	public ItemStack mLog, mBark, mStick;
	public int mPlankCountHand, mPlankCountSaw, mPlankCountBuzz, mStickCountSaw, mStickCountLathe, mCharcoalCount, mCreosoteAmount;
	public BeamEntry mBeamEntry;
	public PlankEntry mPlankEntry;
	public OreDictMaterial mMaterialWood = MT.Wood, mMaterialBark = MT.Bark;

	public WoodEntry(ItemStack aLog) {
		this(aLog, 1, 250);
	}
	public WoodEntry(ItemStack aLog, ItemStack aBark, OreDictMaterial aMaterialBark) {
		this(aLog, 1, 250, aBark, aMaterialBark);
	}
	public WoodEntry(ItemStack aLog, int aCharcoalCount, int aCreosoteAmount) {
		this(aLog, aCharcoalCount, aCreosoteAmount, 2, 4, 6);
	}
	public WoodEntry(ItemStack aLog, int aCharcoalCount, int aCreosoteAmount, ItemStack aBark, OreDictMaterial aMaterialBark) {
		this(aLog, aCharcoalCount, aCreosoteAmount, 2, 4, 6, aBark, MT.Wood, aMaterialBark);
	}
	public WoodEntry(ItemStack aLog, int aCharcoalCount, int aCreosoteAmount, int aPlankCountHand, int aPlankCountSaw, int aPlankCountBuzz) {
		this(aLog, aCharcoalCount, aCreosoteAmount, aPlankCountHand, aPlankCountSaw, aPlankCountBuzz, OP.dust.mat(MT.Bark, 1));
	}
	public WoodEntry(ItemStack aLog, int aCharcoalCount, int aCreosoteAmount, int aPlankCountHand, int aPlankCountSaw, int aPlankCountBuzz, ItemStack aBark) {
		this(aLog, aCharcoalCount, aCreosoteAmount, aPlankCountHand, aPlankCountSaw, aPlankCountBuzz, aBark, MT.Wood, MT.Bark);
	}
	public WoodEntry(ItemStack aLog, int aCharcoalCount, int aCreosoteAmount, int aPlankCountHand, int aPlankCountSaw, int aPlankCountBuzz, ItemStack aBark, OreDictMaterial aMaterialWood, OreDictMaterial aMaterialBark) {
		this(aLog, aCharcoalCount, aCreosoteAmount, aPlankCountHand, aPlankCountSaw, aPlankCountBuzz, aBark, aMaterialWood, aMaterialBark, OP.stickLong.mat(aMaterialWood, 1));
	}
	public WoodEntry(ItemStack aLog, int aCharcoalCount, int aCreosoteAmount, int aPlankCountHand, int aPlankCountSaw, int aPlankCountBuzz, ItemStack aBark, OreDictMaterial aMaterialWood, OreDictMaterial aMaterialBark, ItemStack aStick) {
		this(aLog, aCharcoalCount, aCreosoteAmount, aPlankCountHand, aPlankCountSaw, aPlankCountBuzz, aBark, aMaterialWood, aMaterialBark, aStick, 2, 4);
	}
	public WoodEntry(ItemStack aLog, int aCharcoalCount, int aCreosoteAmount, int aPlankCountHand, int aPlankCountSaw, int aPlankCountBuzz, ItemStack aBark, OreDictMaterial aMaterialWood, OreDictMaterial aMaterialBark, ItemStack aStick, int aStickCountSaw, int aStickCountLathe) {
		this(aLog, WoodDictionary.DEFAULT_PLANK, aCharcoalCount, aCreosoteAmount, aPlankCountHand, aPlankCountSaw, aPlankCountBuzz, aBark, aMaterialWood, aMaterialBark, aStick, aStickCountSaw, aStickCountLathe);
	}
	public WoodEntry(ItemStack aLog, BeamEntry aBeam) {
		this(aLog, aBeam, 1, 250);
	}
	public WoodEntry(ItemStack aLog, BeamEntry aBeam, ItemStack aBark, OreDictMaterial aMaterialBark) {
		this(aLog, aBeam, 1, 250, aBark, aMaterialBark);
	}
	public WoodEntry(ItemStack aLog, BeamEntry aBeam, int aCharcoalCount, int aCreosoteAmount) {
		this(aLog, aBeam, aCharcoalCount, aCreosoteAmount, 2, 4, 6);
	}
	public WoodEntry(ItemStack aLog, BeamEntry aBeam, int aCharcoalCount, int aCreosoteAmount, ItemStack aBark, OreDictMaterial aMaterialBark) {
		this(aLog, aBeam, aCharcoalCount, aCreosoteAmount, 2, 4, 6, aBark, aBeam.mMaterialBeam, aMaterialBark);
	}
	public WoodEntry(ItemStack aLog, BeamEntry aBeam, int aCharcoalCount, int aCreosoteAmount, int aPlankCountHand, int aPlankCountSaw, int aPlankCountBuzz) {
		this(aLog, aBeam, aCharcoalCount, aCreosoteAmount, aPlankCountHand, aPlankCountSaw, aPlankCountBuzz, OP.dust.mat(MT.Bark, 1));
	}
	public WoodEntry(ItemStack aLog, BeamEntry aBeam, int aCharcoalCount, int aCreosoteAmount, int aPlankCountHand, int aPlankCountSaw, int aPlankCountBuzz, ItemStack aBark) {
		this(aLog, aBeam, aCharcoalCount, aCreosoteAmount, aPlankCountHand, aPlankCountSaw, aPlankCountBuzz, aBark, aBeam.mMaterialBeam, MT.Bark);
	}
	public WoodEntry(ItemStack aLog, BeamEntry aBeam, int aCharcoalCount, int aCreosoteAmount, int aPlankCountHand, int aPlankCountSaw, int aPlankCountBuzz, ItemStack aBark, OreDictMaterial aMaterialWood, OreDictMaterial aMaterialBark) {
		this(aLog, aBeam, aCharcoalCount, aCreosoteAmount, aPlankCountHand, aPlankCountSaw, aPlankCountBuzz, aBark, aMaterialWood, aMaterialBark, OP.stickLong.mat(aMaterialWood, 1));
	}
	public WoodEntry(ItemStack aLog, BeamEntry aBeam, int aCharcoalCount, int aCreosoteAmount, int aPlankCountHand, int aPlankCountSaw, int aPlankCountBuzz, ItemStack aBark, OreDictMaterial aMaterialWood, OreDictMaterial aMaterialBark, ItemStack aStick) {
		this(aLog, aBeam, aCharcoalCount, aCreosoteAmount, aPlankCountHand, aPlankCountSaw, aPlankCountBuzz, aBark, aMaterialWood, aMaterialBark, aStick, 2, 4);
	}
	public WoodEntry(ItemStack aLog, BeamEntry aBeam, int aCharcoalCount, int aCreosoteAmount, int aPlankCountHand, int aPlankCountSaw, int aPlankCountBuzz, ItemStack aBark, OreDictMaterial aMaterialWood, OreDictMaterial aMaterialBark, ItemStack aStick, int aStickCountSaw, int aStickCountLathe) {
		this(aLog, aBeam, aBeam.mPlankEntry, aCharcoalCount, aCreosoteAmount, aPlankCountHand, aPlankCountSaw, aPlankCountBuzz, aBark, aMaterialWood, aMaterialBark, aStick, aStickCountSaw, aStickCountLathe);
	}
	public WoodEntry(ItemStack aLog, PlankEntry aPlank) {
		this(aLog, aPlank, 1, 250);
	}
	public WoodEntry(ItemStack aLog, PlankEntry aPlank, ItemStack aBark, OreDictMaterial aMaterialBark) {
		this(aLog, aPlank, 1, 250, aBark, aMaterialBark);
	}
	public WoodEntry(ItemStack aLog, PlankEntry aPlank, int aCharcoalCount, int aCreosoteAmount) {
		this(aLog, aPlank, aCharcoalCount, aCreosoteAmount, 2, 4, 6);
	}
	public WoodEntry(ItemStack aLog, PlankEntry aPlank, int aCharcoalCount, int aCreosoteAmount, ItemStack aBark, OreDictMaterial aMaterialBark) {
		this(aLog, aPlank, aCharcoalCount, aCreosoteAmount, 2, 4, 6, aBark, aPlank.mMaterialPlank, aMaterialBark);
	}
	public WoodEntry(ItemStack aLog, PlankEntry aPlank, int aCharcoalCount, int aCreosoteAmount, int aPlankCountHand, int aPlankCountSaw, int aPlankCountBuzz) {
		this(aLog, aPlank, aCharcoalCount, aCreosoteAmount, aPlankCountHand, aPlankCountSaw, aPlankCountBuzz, OP.dust.mat(MT.Bark, 1));
	}
	public WoodEntry(ItemStack aLog, PlankEntry aPlank, int aCharcoalCount, int aCreosoteAmount, int aPlankCountHand, int aPlankCountSaw, int aPlankCountBuzz, ItemStack aBark) {
		this(aLog, aPlank, aCharcoalCount, aCreosoteAmount, aPlankCountHand, aPlankCountSaw, aPlankCountBuzz, aBark, aPlank.mMaterialPlank, MT.Bark);
	}
	public WoodEntry(ItemStack aLog, PlankEntry aPlank, int aCharcoalCount, int aCreosoteAmount, int aPlankCountHand, int aPlankCountSaw, int aPlankCountBuzz, ItemStack aBark, OreDictMaterial aMaterialWood, OreDictMaterial aMaterialBark) {
		this(aLog, aPlank, aCharcoalCount, aCreosoteAmount, aPlankCountHand, aPlankCountSaw, aPlankCountBuzz, aBark, aMaterialWood, aMaterialBark, OP.stickLong.mat(aMaterialWood, 1));
	}
	public WoodEntry(ItemStack aLog, PlankEntry aPlank, int aCharcoalCount, int aCreosoteAmount, int aPlankCountHand, int aPlankCountSaw, int aPlankCountBuzz, ItemStack aBark, OreDictMaterial aMaterialWood, OreDictMaterial aMaterialBark, ItemStack aStick) {
		this(aLog, aPlank, aCharcoalCount, aCreosoteAmount, aPlankCountHand, aPlankCountSaw, aPlankCountBuzz, aBark, aMaterialWood, aMaterialBark, aStick, 2, 4);
	}
	public WoodEntry(ItemStack aLog, PlankEntry aPlank, int aCharcoalCount, int aCreosoteAmount, int aPlankCountHand, int aPlankCountSaw, int aPlankCountBuzz, ItemStack aBark, OreDictMaterial aMaterialWood, OreDictMaterial aMaterialBark, ItemStack aStick, int aStickCountSaw, int aStickCountLathe) {
		this(aLog, WoodDictionary.DEFAULT_BEAM, aPlank, aCharcoalCount, aCreosoteAmount, aPlankCountHand, aPlankCountSaw, aPlankCountBuzz, aBark, aMaterialWood, aMaterialBark, aStick, aStickCountSaw, aStickCountLathe);
	}
	public WoodEntry(ItemStack aLog, BeamEntry aBeam, PlankEntry aPlank) {
		this(aLog, aBeam, aPlank, 1, 250);
	}
	public WoodEntry(ItemStack aLog, BeamEntry aBeam, PlankEntry aPlank, ItemStack aBark, OreDictMaterial aMaterialBark) {
		this(aLog, aBeam, aPlank, 1, 250, aBark, aMaterialBark);
	}
	public WoodEntry(ItemStack aLog, BeamEntry aBeam, PlankEntry aPlank, int aCharcoalCount, int aCreosoteAmount) {
		this(aLog, aBeam, aPlank, aCharcoalCount, aCreosoteAmount, 2, 4, 6);
	}
	public WoodEntry(ItemStack aLog, BeamEntry aBeam, PlankEntry aPlank, int aCharcoalCount, int aCreosoteAmount, ItemStack aBark, OreDictMaterial aMaterialBark) {
		this(aLog, aBeam, aPlank, aCharcoalCount, aCreosoteAmount, 2, 4, 6, aBark, aPlank.mMaterialPlank, aMaterialBark);
	}
	public WoodEntry(ItemStack aLog, BeamEntry aBeam, PlankEntry aPlank, int aCharcoalCount, int aCreosoteAmount, int aPlankCountHand, int aPlankCountSaw, int aPlankCountBuzz) {
		this(aLog, aBeam, aPlank, aCharcoalCount, aCreosoteAmount, aPlankCountHand, aPlankCountSaw, aPlankCountBuzz, OP.dust.mat(MT.Bark, 1));
	}
	public WoodEntry(ItemStack aLog, BeamEntry aBeam, PlankEntry aPlank, int aCharcoalCount, int aCreosoteAmount, int aPlankCountHand, int aPlankCountSaw, int aPlankCountBuzz, ItemStack aBark) {
		this(aLog, aBeam, aPlank, aCharcoalCount, aCreosoteAmount, aPlankCountHand, aPlankCountSaw, aPlankCountBuzz, aBark, aPlank.mMaterialPlank, MT.Bark);
	}
	public WoodEntry(ItemStack aLog, BeamEntry aBeam, PlankEntry aPlank, int aCharcoalCount, int aCreosoteAmount, int aPlankCountHand, int aPlankCountSaw, int aPlankCountBuzz, ItemStack aBark, OreDictMaterial aMaterialWood, OreDictMaterial aMaterialBark) {
		this(aLog, aBeam, aPlank, aCharcoalCount, aCreosoteAmount, aPlankCountHand, aPlankCountSaw, aPlankCountBuzz, aBark, aMaterialWood, aMaterialBark, OP.stickLong.mat(aMaterialWood, 1));
	}
	public WoodEntry(ItemStack aLog, BeamEntry aBeam, PlankEntry aPlank, int aCharcoalCount, int aCreosoteAmount, int aPlankCountHand, int aPlankCountSaw, int aPlankCountBuzz, ItemStack aBark, OreDictMaterial aMaterialWood, OreDictMaterial aMaterialBark, ItemStack aStick) {
		this(aLog, aBeam, aPlank, aCharcoalCount, aCreosoteAmount, aPlankCountHand, aPlankCountSaw, aPlankCountBuzz, aBark, aMaterialWood, aMaterialBark, aStick, 2, 4);
	}
	public WoodEntry(ItemStack aLog, BeamEntry aBeam, PlankEntry aPlank, int aCharcoalCount, int aCreosoteAmount, int aPlankCountHand, int aPlankCountSaw, int aPlankCountBuzz, ItemStack aBark, OreDictMaterial aMaterialWood, OreDictMaterial aMaterialBark, ItemStack aStick, int aStickCountSaw, int aStickCountLathe) {
		if (ST.invalid(aLog)) return;
		
		mLog = ST.amount(1, aLog);
		mBark = aBark;
		mBeamEntry = aBeam;
		mStick = ST.amount(1, aStick);
		mStickCountSaw = aStickCountSaw;
		mStickCountLathe = aStickCountLathe;
		mMaterialWood = aMaterialWood;
		mMaterialBark = aMaterialBark;
		mCharcoalCount = aCharcoalCount;
		mCreosoteAmount = aCreosoteAmount;
		mPlankCountHand = aPlankCountHand;
		mPlankCountSaw = aPlankCountSaw;
		mPlankCountBuzz = aPlankCountBuzz;
		mPlankEntry = aPlank;
		mPlankEntry.mWoodEntries.add(this);
		
		WoodDictionary.WOODS.put(mLog, this);
		WoodDictionary.IGNORED_OREDICT_REGISTRATIONS.add(ST.item_(mLog));
	}
}
