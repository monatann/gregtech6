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

package gregtech6.tileentity.plants;

import static gregapi6.data.CS.*;

import gregapi6.code.ArrayListNoNulls;
import gregapi6.data.CS.BlocksGT;
import gregapi6.data.FL;
import gregapi6.data.IL;
import gregapi6.old.Textures;
import gregapi6.render.BlockTextureCopied;
import gregapi6.render.BlockTextureDefault;
import gregapi6.render.ITexture;
import gregapi6.tileentity.misc.MultiTileEntityTreeHole;
import gregapi6.util.ST;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityResinHoleRubber extends MultiTileEntityTreeHole {
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		super.onTick2(aTimer, aIsServerSide);
		if (aIsServerSide) {
			if (!mHasResin && aTimer % 600 == 0) {
				int tTreeHeight = yCoord+1, tLeavesCount = 0;
				for (int i = 1; i < 10; i++) {
					if (getBlockAtSideAndDistance(SIDE_TOP, i) != BlocksGT.LogA && getMetaDataAtSideAndDistance(SIDE_TOP, i) != 0) break;
					tTreeHeight++;
				}
				if (checkLeaves(xCoord, tTreeHeight  , zCoord)) tLeavesCount++;
				if (checkLeaves(xCoord, tTreeHeight+1, zCoord)) tLeavesCount++;
				for (int i = -1; i <= 1; i++) for (int j = -1; j <= 1; j++) if (i != 0 || j != 0) {
					if (checkLeaves(xCoord+i, tTreeHeight-1, zCoord+j)) tLeavesCount++;
				}
				for (int i = -2; i <= 2; i++) for (int j = -2; j <= 2; j++) if (i != 0 || j != 0) {
					if (Math.abs(i*j) < 2) if (checkLeaves(xCoord+i, tTreeHeight-2, zCoord+j)) tLeavesCount++;
					if (Math.abs(i*j) < 4) if (checkLeaves(xCoord+i, tTreeHeight-3, zCoord+j)) tLeavesCount++;
					if (Math.abs(i*j) < 4) if (checkLeaves(xCoord+i, tTreeHeight-4, zCoord+j)) tLeavesCount++;
					if (checkLeaves(xCoord+i, tTreeHeight-5, zCoord+j)) tLeavesCount++;
				}
				// 86 Leaves if Ideal. So a Chance of 10% every 30 seconds for a healthy Tree.
				if (tLeavesCount > 60 && rng(260) < tLeavesCount - 60) {
					mHasResin = T;
					updateClientData();
				}
			}
		}
	}
	
	private boolean checkLeaves(int aX, int aY, int aZ) {return getBlock(aX, aY, aZ) == BlocksGT.Leaves && getMetaData(aX, aY, aZ) == 8;}
	
	@Override public ArrayListNoNulls<ItemStack> getDrops(int aFortune, boolean aSilkTouch) {return isClientSide() ? super.getDrops(aFortune, aSilkTouch) : new ArrayListNoNulls<>(F, ST.make(BlocksGT.LogA, 1, 0));}
	@Override public ItemStack getResinItem(byte aSide) {return IL.IC2_Resin.get(1, IL.Resin.get(1));}
	@Override public FluidStack getResinFluid(byte aSide) {return FL.make("fluidrubbertreesap", 250);}
	@Override public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {return aShouldSideBeRendered[aSide] ? aSide != mFacing ? BlockTextureCopied.get(BlocksGT.LogA, SIDE_ANY, 0) : BlockTextureDefault.get(mHasResin?Textures.BlockIcons.LOG_RESIN_RUBBER:Textures.BlockIcons.LOG_HOLE_RUBBER) : null;}
	@Override public float getExplosionResistance2() {return BlocksGT.LogA.getExplosionResistance(0);}
	@Override public String getTileEntityName() {return "gt6.multitileentity.tree.rubber.resinhole";}
}
