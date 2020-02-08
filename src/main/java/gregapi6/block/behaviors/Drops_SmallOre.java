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

package gregapi6.block.behaviors;

import static gregapi6.data.CS.*;

import java.util.ArrayList;
import java.util.Random;

import gregapi6.block.prefixblock.PrefixBlock;
import gregapi6.code.ArrayListNoNulls;
import gregapi6.data.MT;
import gregapi6.data.OP;
import gregapi6.oredict.OreDictMaterial;
import gregapi6.util.ST;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 */
public class Drops_SmallOre extends Drops {
	private final OreDictMaterial mSecondaryDrop;
	
	public Drops_SmallOre(OreDictMaterial aSecondaryDrop) {
		super((Item)null);
		mSecondaryDrop = aSecondaryDrop;
	}
	
	@Override
	public ArrayList<ItemStack> getDrops(PrefixBlock aBlock, World aWorld, int aX, int aY, int aZ, short aMetaData, TileEntity aTileEntity, int aFortune, boolean aSilkTouch) {
		ArrayList<ItemStack> rList = new ArrayListNoNulls<>();
		OreDictMaterial aMaterial = aBlock.getMetaMaterial(aMetaData);
		if (aMaterial != null) aMaterial = aMaterial.mTargetCrushing.mMaterial;
		
		if (aWorld.isRemote) {
			rList.add(aBlock.getItemStackFromBlock(aWorld, aX, aY, aZ, SIDE_ANY));
		} else if (aMaterial != null) {
			Random tRandom = new Random(aX ^ aY ^ aZ);
			for (int i = 0; i < 16; i++) tRandom.nextInt(10000);
			if (aMaterial == MT.Gneiss || aMaterial == MT.PetrifiedWood) {
				ItemStack tStack = OP.rockGt.mat(aMaterial, 1);
				for (int i = 0, j = Math.max(1, aMaterial.mOreMultiplier*aMaterial.mOreProcessingMultiplier+(aFortune>0?(tRandom.nextInt((1+aFortune)*aMaterial.mOreMultiplier*aMaterial.mOreProcessingMultiplier)):0)/2+tRandom.nextInt(2)); i < j; i++) {
					rList.add(ST.copy(tStack));
				}
			} else {
				ItemStack tStack = OP.gemLegendary.mat(aMaterial, 1);
				if (tStack != null && tRandom.nextInt(aSilkTouch?5000:10000) <= aFortune) {
					rList.add(tStack);
				} else {
					ArrayList<ItemStack> tSelector = new ArrayListNoNulls<>();
					tStack = OP.gemExquisite.mat(aMaterial, OP.gem.mat(aMaterial, 1), 1);
					if (tStack != null) for (int i = 0, j = (aSilkTouch? 3: 1); i < j; i++) tSelector.add(tStack);
					tStack = OP.gemFlawless.mat(aMaterial, OP.gem.mat(aMaterial, 1), 1);
					if (tStack != null) for (int i = 0, j = (aSilkTouch? 6: 2); i < j; i++) tSelector.add(tStack);
					tStack = OP.gem.mat(aMaterial, 1);
					if (tStack != null) for (int i = 0, j = (aSilkTouch? 6:12); i < j; i++) tSelector.add(tStack);
					
					tStack = OP.gemFlawed.mat(aMaterial, 2);
					if (tStack != null){for (int i = 0, j = (aSilkTouch?10: 5); i < j; i++) tSelector.add(tStack);
					tStack = OP.crushed.mat(aMaterial, 1);
					if (tStack != null) for (int i = 0, j = (aSilkTouch? 5:10); i < j; i++) tSelector.add(tStack);
					} else {
					tStack = OP.crushed.mat(aMaterial, 1);
					if (tStack != null) for (int i = 0, j = 15; i < j; i++) tSelector.add(tStack);
					}
					
					tStack = OP.gemChipped.mat(aMaterial, 4);
					if (tStack != null){for (int i = 0, j = (aSilkTouch?10: 5); i < j; i++) tSelector.add(tStack);
					tStack = OP.crushed.mat(aMaterial, OP.dust.mat(aMaterial, 1), 1);
					if (tStack != null) for (int i = 0, j = (aSilkTouch? 5:10); i < j; i++) tSelector.add(tStack);
					} else {
					tStack = OP.crushed.mat(aMaterial, 1);
					if (tStack != null) for (int i = 0, j = 15; i < j; i++) tSelector.add(tStack);
					}
					
					if (tSelector.size() > 0) {
						for (int i = 0, j = Math.max(1, aMaterial.mOreMultiplier*aMaterial.mOreProcessingMultiplier+(aFortune>0?(tRandom.nextInt((1+aFortune)*aMaterial.mOreMultiplier*aMaterial.mOreProcessingMultiplier)):0)/2); i < j; i++) {
							rList.add(ST.copy(tSelector.get(tRandom.nextInt(tSelector.size()))));
						}
					}
				}
			}
			if (mSecondaryDrop != null && tRandom.nextInt(3+aFortune)>1) rList.add(OP.dust.mat(mSecondaryDrop.mTargetCrushing.mMaterial, 1));
		}
		return rList;
	}
}
