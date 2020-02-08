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

import gregapi6.data.OP;
import gregapi6.oredict.OreDictMaterial;
import gregapi6.render.IIconContainer;
import gregapi6.util.UT;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 */
public abstract class BlockBasePlanks extends BlockMetaType {
	public BlockBasePlanks(Class<? extends ItemBlock> aItemClass, Material aVanillaMaterial, SoundType aVanillaSoundType, String aNameInternal, String aDefaultLocalised, OreDictMaterial aMaterial, float aResistanceMultiplier, float aHardnessMultiplier, int aHarvestLevel, int aCount, IIconContainer[] aIcons) {
		super(aItemClass, aVanillaMaterial, aVanillaSoundType, aNameInternal, aDefaultLocalised, aMaterial, aResistanceMultiplier, aHardnessMultiplier, aHarvestLevel, aCount, aIcons);
	}
	
	protected BlockBasePlanks(Class<? extends ItemBlock> aItemClass, Material aVanillaMaterial, SoundType aVanillaSoundType, String aNameInternal, String aDefaultLocalised, OreDictMaterial aMaterial, float aResistanceMultiplier, float aHardnessMultiplier, int aHarvestLevel, int aCount, IIconContainer[] aIcons, byte aSlabType, BlockMetaType aBlock) {
		super(aItemClass, aVanillaMaterial, aVanillaSoundType, aNameInternal, aDefaultLocalised, aMaterial, aResistanceMultiplier, aHardnessMultiplier, aHarvestLevel, aCount, aIcons, aSlabType, aBlock);
	}
	
	@Override public String getHarvestTool(int aMeta) {return TOOL_axe;}
	@Override public float getBlockHardness(World aWorld, int aX, int aY, int aZ) {return Blocks.planks.getBlockHardness(aWorld, aX, aY, aZ) * mHardnessMultiplier;}
	@Override public float getExplosionResistance(int aMeta) {return Blocks.planks.getExplosionResistance(null) * mResistanceMultiplier;}
	@Override public int getItemStackLimit(ItemStack aStack) {return UT.Code.bindStack(OP.plank.mDefaultStackSize * (mBlock.mBlock == mBlock ? 1 : 2));}
	@Override public boolean canCreatureSpawn(int aMeta) {return F;}
	@Override public boolean isSealable(int aMeta, byte aSide) {return F;}
	@Override public boolean doesPistonPush(short aMeta) {return T;}
}
