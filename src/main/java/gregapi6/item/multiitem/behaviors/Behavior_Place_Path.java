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

package gregapi6.item.multiitem.behaviors;

import static gregapi6.data.CS.*;

import java.util.List;

import gregapi6.data.CS.BlocksGT;
import gregapi6.data.CS.SFX;
import gregapi6.data.IL;
import gregapi6.data.LH;
import gregapi6.item.multiitem.MultiItem;
import gregapi6.item.multiitem.MultiItemTool;
import gregapi6.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi6.util.UT;
import gregapi6.util.WD;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Behavior_Place_Path extends AbstractBehaviorDefault {
	private final int mCosts;
	
	public Behavior_Place_Path(int aCosts) {
		mCosts = aCosts;
	}
	
	@Override
	public boolean onItemUse(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (aWorld.isRemote || aPlayer == null || !aPlayer.canPlayerEdit(aX, aY, aZ, aSide, aStack)) return F;
		Block aBlock = WD.block(aWorld, aX, aY, aZ);
		
		if (aBlock == Blocks.grass || aBlock == BlocksGT.Grass || IL.BoP_Grass_Long.equal(aBlock) || IL.BoP_Grass_Origin.equal(aBlock) || IL.AETHER_Grass_Enchanted_Vanilla.equal(aBlock)) {
			if (BlocksGT.Paths != null) {
				if (UT.Entities.hasInfiniteItems(aPlayer) || ((MultiItemTool)aItem).doDamage(aStack, mCosts, aPlayer)) {
					UT.Sounds.send(aWorld, SFX.MC_DIG_GRAVEL, 1.0F, 1.25F, aX, aY, aZ);
					aWorld.setBlock(aX, aY, aZ, BlocksGT.Paths, 0, 3);
					return T;
				}
				return F;
			}
			if (IL.EtFu_Path.exists()) {
				if (UT.Entities.hasInfiniteItems(aPlayer) || ((MultiItemTool)aItem).doDamage(aStack, mCosts, aPlayer)) {
					UT.Sounds.send(aWorld, SFX.MC_DIG_GRAVEL, 1.0F, 1.25F, aX, aY, aZ);
					aWorld.setBlock(aX, aY, aZ, IL.EtFu_Path.block());
					return T;
				}
				return F;
			}
		}
		if (IL.AETHER_Grass.equal(aBlock) || IL.AETHER_Grass_Enchanted.equal(aBlock)) {
			if (BlocksGT.Paths != null) {
				if (UT.Entities.hasInfiniteItems(aPlayer) || ((MultiItemTool)aItem).doDamage(aStack, mCosts, aPlayer)) {
					UT.Sounds.send(aWorld, SFX.MC_DIG_GRAVEL, 1.0F, 1.25F, aX, aY, aZ);
					aWorld.setBlock(aX, aY, aZ, BlocksGT.Paths, 1, 3);
					return T;
				}
				return F;
			}
		}
		
		byte aMeta = WD.meta(aWorld, aX, aY, aZ);
		
		if (IL.BoP_Grass_Loamy.equal(aBlock) && aMeta == 0) {
			if (BlocksGT.Paths != null) {
				if (UT.Entities.hasInfiniteItems(aPlayer) || ((MultiItemTool)aItem).doDamage(aStack, mCosts, aPlayer)) {
					UT.Sounds.send(aWorld, SFX.MC_DIG_GRAVEL, 1.0F, 1.25F, aX, aY, aZ);
					aWorld.setBlock(aX, aY, aZ, BlocksGT.Paths, 2, 3);
					return T;
				}
				return F;
			}
		}
		if (IL.BoP_Grass_Sandy.equal(aBlock) && aMeta == 1) {
			if (BlocksGT.Paths != null) {
				if (UT.Entities.hasInfiniteItems(aPlayer) || ((MultiItemTool)aItem).doDamage(aStack, mCosts, aPlayer)) {
					UT.Sounds.send(aWorld, SFX.MC_DIG_GRAVEL, 1.0F, 1.25F, aX, aY, aZ);
					aWorld.setBlock(aX, aY, aZ, BlocksGT.Paths, 3, 3);
					return T;
				}
				return F;
			}
		}
		if (IL.BoP_Grass_Silty.equal(aBlock) && aMeta == 2) {
			if (BlocksGT.Paths != null) {
				if (UT.Entities.hasInfiniteItems(aPlayer) || ((MultiItemTool)aItem).doDamage(aStack, mCosts, aPlayer)) {
					UT.Sounds.send(aWorld, SFX.MC_DIG_GRAVEL, 1.0F, 1.25F, aX, aY, aZ);
					aWorld.setBlock(aX, aY, aZ, BlocksGT.Paths, 4, 3);
					return T;
				}
				return F;
			}
		}
		if (IL.EB_Grass_Alfisol.equal(aBlock) && aMeta == 0) {
			if (BlocksGT.Paths != null) {
				if (UT.Entities.hasInfiniteItems(aPlayer) || ((MultiItemTool)aItem).doDamage(aStack, mCosts, aPlayer)) {
					UT.Sounds.send(aWorld, SFX.MC_DIG_GRAVEL, 1.0F, 1.25F, aX, aY, aZ);
					aWorld.setBlock(aX, aY, aZ, BlocksGT.Paths, 5, 3);
					return T;
				}
				return F;
			}
		}
		if (IL.EB_Grass_Andisol.equal(aBlock) && aMeta == 1) {
			if (BlocksGT.Paths != null) {
				if (UT.Entities.hasInfiniteItems(aPlayer) || ((MultiItemTool)aItem).doDamage(aStack, mCosts, aPlayer)) {
					UT.Sounds.send(aWorld, SFX.MC_DIG_GRAVEL, 1.0F, 1.25F, aX, aY, aZ);
					aWorld.setBlock(aX, aY, aZ, BlocksGT.Paths, 6, 3);
					return T;
				}
				return F;
			}
		}
		if (IL.EB_Grass_Gelisol.equal(aBlock) && aMeta == 3) {
			if (BlocksGT.Paths != null) {
				if (UT.Entities.hasInfiniteItems(aPlayer) || ((MultiItemTool)aItem).doDamage(aStack, mCosts, aPlayer)) {
					UT.Sounds.send(aWorld, SFX.MC_DIG_GRAVEL, 1.0F, 1.25F, aX, aY, aZ);
					aWorld.setBlock(aX, aY, aZ, BlocksGT.Paths, 7, 3);
					return T;
				}
				return F;
			}
		}
		if (IL.EB_Grass_Histosol.equal(aBlock) && aMeta == 4) {
			if (BlocksGT.Paths != null) {
				if (UT.Entities.hasInfiniteItems(aPlayer) || ((MultiItemTool)aItem).doDamage(aStack, mCosts, aPlayer)) {
					UT.Sounds.send(aWorld, SFX.MC_DIG_GRAVEL, 1.0F, 1.25F, aX, aY, aZ);
					aWorld.setBlock(aX, aY, aZ, BlocksGT.Paths, 8, 3);
					return T;
				}
				return F;
			}
		}
		if (IL.EB_Grass_Inceptisol.equal(aBlock) && aMeta == 5) {
			if (BlocksGT.Paths != null) {
				if (UT.Entities.hasInfiniteItems(aPlayer) || ((MultiItemTool)aItem).doDamage(aStack, mCosts, aPlayer)) {
					UT.Sounds.send(aWorld, SFX.MC_DIG_GRAVEL, 1.0F, 1.25F, aX, aY, aZ);
					aWorld.setBlock(aX, aY, aZ, BlocksGT.Paths, 9, 3);
					return T;
				}
				return F;
			}
		}
		if (IL.EB_Grass_Mollisol.equal(aBlock) && aMeta == 6) {
			if (BlocksGT.Paths != null) {
				if (UT.Entities.hasInfiniteItems(aPlayer) || ((MultiItemTool)aItem).doDamage(aStack, mCosts, aPlayer)) {
					UT.Sounds.send(aWorld, SFX.MC_DIG_GRAVEL, 1.0F, 1.25F, aX, aY, aZ);
					aWorld.setBlock(aX, aY, aZ, BlocksGT.Paths,10, 3);
					return T;
				}
				return F;
			}
		}
		if (IL.EB_Grass_Oxisol.equal(aBlock) && aMeta == 7) {
			if (BlocksGT.Paths != null) {
				if (UT.Entities.hasInfiniteItems(aPlayer) || ((MultiItemTool)aItem).doDamage(aStack, mCosts, aPlayer)) {
					UT.Sounds.send(aWorld, SFX.MC_DIG_GRAVEL, 1.0F, 1.25F, aX, aY, aZ);
					aWorld.setBlock(aX, aY, aZ, BlocksGT.Paths,11, 3);
					return T;
				}
				return F;
			}
		}
		return F;
	}
	
	static {
		LH.add("gt6.behaviour.path", "Creates Paths in Grass on Rightclick");
	}
	
	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		if (IL.EtFu_Path.exists() || BlocksGT.Paths != null) aList.add(LH.get("gt6.behaviour.path"));
		return aList;
	}
}
