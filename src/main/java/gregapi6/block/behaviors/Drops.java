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

import gregapi6.block.prefixblock.PrefixBlock;
import gregapi6.block.prefixblock.PrefixBlockTileEntity;
import gregapi6.code.ArrayListNoNulls;
import gregapi6.util.ST;
import gregapi6.util.WD;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 */
public class Drops {
	private final Item mDropNormal, mDropSilkTouch;
	
	public Drops(Item  aDropNormal) {this(aDropNormal, aDropNormal);}
	public Drops(Block aDropNormal) {this(Item.getItemFromBlock(aDropNormal));}
	public Drops(Block aDropNormal, Block aDropSilkTouch) {this(Item.getItemFromBlock(aDropNormal), Item.getItemFromBlock(aDropSilkTouch));}
	public Drops(Item  aDropNormal, Block aDropSilkTouch) {this(aDropNormal, Item.getItemFromBlock(aDropSilkTouch));}
	public Drops(Block aDropNormal, Item  aDropSilkTouch) {this(Item.getItemFromBlock(aDropNormal), aDropSilkTouch);}
	
	public Drops(Item aDropNormal, Item aDropSilkTouch) {
		mDropNormal = aDropNormal;
		mDropSilkTouch = aDropSilkTouch;
	}
	
	public ArrayList<ItemStack> getDrops(PrefixBlock aBlock, World aWorld, int aX, int aY, int aZ, int aFortune, boolean aSilkTouch) {
		TileEntity aTileEntity = WD.te(aWorld, aX, aY, aZ, T);
		if (aTileEntity instanceof PrefixBlockTileEntity) return getDrops(aBlock, aWorld, aX, aY, aZ, aBlock.getMetaDataValue(aTileEntity), aTileEntity, aFortune, aSilkTouch);
		return new ArrayListNoNulls<>();
	}
	
	public ArrayList<ItemStack> getDrops(PrefixBlock aBlock, World aWorld, int aX, int aY, int aZ, short aMetaData, TileEntity aTileEntity, int aFortune, boolean aSilkTouch) {
		ArrayListNoNulls<ItemStack> rList = new ArrayListNoNulls<>();
		rList.add(ST.make(aSilkTouch?mDropSilkTouch:mDropNormal, 1, aMetaData, aTileEntity instanceof PrefixBlockTileEntity?((PrefixBlockTileEntity)aTileEntity).mItemNBT:null));
		return rList;
	}
}
