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

package gregtech6.blocks;

import static gregapi6.data.CS.*;

import java.util.ArrayList;
import java.util.Random;

import gregapi6.block.IBlockFoamable;
import gregapi6.block.metatype.BlockColored;
import gregapi6.block.metatype.BlockMetaType;
import gregapi6.block.metatype.ItemBlockMetaType;
import gregapi6.code.ArrayListNoNulls;
import gregapi6.data.CS.BlocksGT;
import gregapi6.data.MT;
import gregapi6.old.Textures;
import gregapi6.oredict.OreDictMaterial;
import gregapi6.render.IIconContainer;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCFoamFresh extends BlockColored implements IBlockFoamable {
	public BlockCFoamFresh(String aUnlocalised) {
		super(ItemBlockMetaType.class, Material.sponge, soundTypeCloth, aUnlocalised, "Fresh C-Foam", MT.ConstructionFoam, 1.0F, 0.0F, 0, Textures.BlockIcons.CFOAMS_FRESH);
	}
	
	@Override
	protected BlockMetaType makeSlab(Class<? extends ItemBlock> aItemClass, Material aVanillaMaterial, SoundType aVanillaSoundType, String aName, String aDefaultLocalised, OreDictMaterial aMaterial, float aResistanceMultiplier, float aHardnessMultiplier, int aHarvestLevel, int aCount, IIconContainer[] aIcons, byte aSlabType, BlockMetaType aBlock) {
		return new BlockCFoamFresh(aItemClass, aVanillaMaterial, aVanillaSoundType, aName, aDefaultLocalised, aMaterial, aResistanceMultiplier, aHardnessMultiplier, aHarvestLevel, aCount, aIcons, aSlabType, aBlock);
	}
	
	protected BlockCFoamFresh(Class<? extends ItemBlock> aItemClass, Material aVanillaMaterial, SoundType aVanillaSoundType, String aName, String aDefaultLocalised, OreDictMaterial aMaterial, float aResistanceMultiplier, float aHardnessMultiplier, int aHarvestLevel, int aCount, IIconContainer[] aIcons, byte aSlabType, BlockMetaType aBlock) {
		super(aItemClass, aVanillaMaterial, aVanillaSoundType, aName, aDefaultLocalised, aMaterial, aResistanceMultiplier, aHardnessMultiplier, aHarvestLevel, aCount, aIcons, aSlabType, aBlock);
	}
	
	@Override
	public void onBlockAdded2(World aWorld, int aX, int aY, int aZ) {
		if (!aWorld.isRemote) aWorld.scheduleBlockUpdate(aX, aY, aZ, this, 100+RNGSUS.nextInt(5900));
	}
	
	@Override
	public void updateTick2(World aWorld, int aX, int aY, int aZ, Random aRandom) {
		if (!aWorld.isRemote) dryFoam(aWorld, aX, aY, aZ, SIDE_ANY);
	}
	
	@Override
	public ArrayList<ItemStack> getDrops(World aWorld, int aX, int aY, int aZ, int aMeta, int aFortune) {
		return new ArrayListNoNulls<>();
	}
	
	@Override
	public boolean isOpaqueCube() {
		return F;
	}
	
	@Override
	public boolean isSideSolid(int aMeta, byte aSide) {
		return F;
	}
	
	@Override
	public boolean isSealable(int aMeta, byte aSide) {
		return F;
	}
	
	@Override
	public boolean isNormalCube(IBlockAccess aWorld, int aX, int aY, int aZ) {
		return T;
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World aWorld, int aX, int aY, int aZ) {
		return null;
	}
	
	@Override
	public boolean isBlockSolid(IBlockAccess aWorld, int aX, int aY, int aZ, int aSide) {
		return F;
	}
	
	@Override
	public boolean applyFoam(World aWorld, int aX, int aY, int aZ, byte aSide, short[] aCFoamRGB, byte aVanillaColor) {
		return F;
	}
	
	@Override
	public boolean dryFoam(World aWorld, int aX, int aY, int aZ, byte aSide) {
		return aWorld.setBlock(aX, aY, aZ, SIDES_VALID[mSide]?((BlockMetaType)BlocksGT.CFoam).mSlabs[mSide]:BlocksGT.CFoam, aWorld.getBlockMetadata(aX, aY, aZ), 3);
	}
	
	@Override
	public boolean removeFoam(World aWorld, int aX, int aY, int aZ, byte aSide) {
		return aWorld.setBlock(aX, aY, aZ, NB, 0, 3);
	}
	
	@Override
	public boolean hasFoam(World aWorld, int aX, int aY, int aZ, byte aSide) {
		return T;
	}
	
	@Override
	public boolean driedFoam(World aWorld, int aX, int aY, int aZ, byte aSide) {
		return F;
	}
}
