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

package gregapi6.block.metatype;

import static gregapi6.data.CS.*;

import gregapi6.block.ItemBlockBase;
import gregapi6.item.IItemUpdatable;
import gregapi6.util.ST;
import gregapi6.util.UT;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 */
public class ItemBlockMetaType extends ItemBlockBase implements IItemUpdatable {
	public ItemBlockMetaType(Block aBlock) {
		super(aBlock);
		setMaxDamage(0);
		setHasSubtypes(T);
		setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public boolean placeBlockAt(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float aHitX, float aHitY, float aHitZ, int aMetaData) {
		if (((BlockMetaType)mPlaceable).mBlock == mPlaceable) return aWorld.setBlock(aX, aY, aZ, field_150939_a, aMetaData, 3);
		byte tSide = UT.Code.getSideWrenching((byte)aSide, aHitX, aHitY, aHitZ);
		if (tSide == aSide || tSide == OPPOSITES[aSide]) tSide = OPPOSITES[tSide];
		return aWorld.setBlock(aX, aY, aZ, ((BlockMetaType)mPlaceable).mBlock.mSlabs[tSide], aMetaData, 3);
	}
	
	@Override
	public void updateItemStack(ItemStack aStack) {
		if (((BlockMetaType)mPlaceable).mBlock.mSlabs[1] == mPlaceable || ((BlockMetaType)mPlaceable).mBlock.mSlabs[2] == mPlaceable || ((BlockMetaType)mPlaceable).mBlock.mSlabs[3] == mPlaceable || ((BlockMetaType)mPlaceable).mBlock.mSlabs[4] == mPlaceable || ((BlockMetaType)mPlaceable).mBlock.mSlabs[5] == mPlaceable) {
			ST.set(aStack, ST.make(((BlockMetaType)mPlaceable).mBlock.mSlabs[0], 1, ST.meta_(aStack)), F, F);
		}
	}
	@Override
	public void updateItemStack(ItemStack aStack, World aWorld, int aX, int aY, int aZ) {
		updateItemStack(aStack);
	}
	
	/* This shit just doesn't want to work.
	
	private boolean mInterrupt = false;
	
	@Override
	public boolean onItemUseFirst(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float aHitX, float aHitY, float aHitZ) {
		if (aStack.stackSize > 0 && SIDES_VALID[mBlock.mSide]) {
			switch(aSide) {
			case 0: if (aHitY <= 0.01) aY--; break;
			case 1: if (aHitY >= 0.99) aY++; break;
			case 2: if (aHitZ <= 0.01) aZ--; break;
			case 3: if (aHitZ >= 0.99) aZ++; break;
			case 4: if (aHitX <= 0.01) aX--; break;
			case 5: if (aHitX >= 0.99) aX++; break;
			}
			
			Block aBlock = aWorld.getBlock(aX, aY, aZ);
			byte aMetaData = (byte)aWorld.getBlockMetadata(aX, aY, aZ);
			
			if (aBlock instanceof GT_Block_Stones)
			if (aPlayer.canPlayerEdit(aX, aY, aZ, aSide, aStack))
			if (mBlock.mBlock == ((GT_Block_Stones)aBlock).mBlock)
			if (SIDES_VALID[((GT_Block_Stones)aBlock).mSide])
			if (ST.equal(aStack, new ItemStack(mBlock.mBlock.mSlabs[0], 1, aMetaData), true))
			if (aWorld.checkNoEntityCollision(mBlock.mBlock.getCollisionBoundingBoxFromPool(aWorld, aX, aY, aZ))) {
				aWorld.setBlock(aX, aY, aZ, mBlock.mBlock, aMetaData, 3);
				aWorld.playSoundEffect(aX+0.5F, aY+0.5F, aZ+0.5F, mBlock.mBlock.stepSound.func_150496_b(), (mBlock.mBlock.stepSound.getVolume() + 1.0F) / 2.0F, mBlock.mBlock.stepSound.getPitch() * 0.8F);
				if (!aPlayer.capabilities.isCreativeMode) aStack.stackSize--;
				mInterrupt = true;
				return false;
			}
		}
		
		return false;
	}
	
	@Override
	public boolean onItemUse(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float aHitX, float aHitY, float aHitZ) {
		if (mInterrupt) {
			mInterrupt = false;
			return false;
		}
		return super.onItemUse(aStack, aPlayer, aWorld, aX, aY, aZ, aSide, aHitX, aHitY, aHitZ);
	}
	*/
}
