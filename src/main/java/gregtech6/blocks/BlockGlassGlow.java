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

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi6.block.metatype.BlockColored;
import gregapi6.block.metatype.BlockMetaType;
import gregapi6.block.metatype.ItemBlockMetaType;
import gregapi6.code.ArrayListNoNulls;
import gregapi6.data.ANY;
import gregapi6.data.CS.BlocksGT;
import gregapi6.data.MT;
import gregapi6.data.OP;
import gregapi6.old.Textures;
import gregapi6.oredict.OreDictItemData;
import gregapi6.oredict.OreDictMaterial;
import gregapi6.render.IIconContainer;
import gregapi6.util.OM;
import gregapi6.util.ST;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockGlassGlow extends BlockColored {
	public BlockGlassGlow(String aUnlocalised) {
		super(ItemBlockMetaType.class, Material.glass, soundTypeGlass, aUnlocalised, "Glow Glass", null, 0.5F, 0.5F, 0, Textures.BlockIcons.GLASSES_CLEAR);
		for (int i = 0; i < 16; i++) OM.data(ST.make(this, 1, i), new OreDictItemData(MT.Glass, U, ANY.Glowstone, U));
		setLightLevel(1.0F);
		BlocksGT.breakableGlass.add(this);
	}
	
	@Override
	protected BlockMetaType makeSlab(Class<? extends ItemBlock> aItemClass, Material aVanillaMaterial, SoundType aVanillaSoundType, String aName, String aDefaultLocalised, OreDictMaterial aMaterial, float aResistanceMultiplier, float aHardnessMultiplier, int aHarvestLevel, int aCount, IIconContainer[] aIcons, byte aSlabType, BlockMetaType aBlock) {
		return new BlockGlassGlow(aItemClass, aVanillaMaterial, aVanillaSoundType, aName, aDefaultLocalised, aMaterial, aResistanceMultiplier, aHardnessMultiplier, aHarvestLevel, aCount, aIcons, aSlabType, aBlock);
	}
	
	protected BlockGlassGlow(Class<? extends ItemBlock> aItemClass, Material aVanillaMaterial, SoundType aVanillaSoundType, String aName, String aDefaultLocalised, OreDictMaterial aMaterial, float aResistanceMultiplier, float aHardnessMultiplier, int aHarvestLevel, int aCount, IIconContainer[] aIcons, byte aSlabType, BlockMetaType aBlock) {
		super(aItemClass, aVanillaMaterial, aVanillaSoundType, aName, aDefaultLocalised, aMaterial, aResistanceMultiplier, aHardnessMultiplier, aHarvestLevel, aCount, aIcons, aSlabType, aBlock);
		for (int i = 0; i < 16; i++) OM.data(ST.make(this, 1, i), new OreDictItemData(MT.Glass, U2, ANY.Glowstone, U2));
		setLightLevel(1.0F);
		BlocksGT.breakableGlass.add(this);
	}
	
	@Override public int getLightOpacity() {return LIGHT_OPACITY_NONE;}
	@Override public int getRenderBlockPass() {return 1;}
	@Override public boolean isOpaqueCube() {return F;}
	@Override public boolean renderAsNormalBlock() {return F;}
	@Override public boolean isSealable(int aMeta, byte aSide) {return mBlock == this || mSide == aSide;}
	@Override public boolean isBlockSolid(IBlockAccess aWorld, int aX, int aY, int aZ, int aSide) {return F;}
	@Override public ArrayList<ItemStack> getDrops(World aWorld, int aX, int aY, int aZ, int aMeta, int aFortune) {return new ArrayListNoNulls<>(F, OP.scrapGt.mat(MT.Glass, mBlock == this ? 9 : 4));}
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess aWorld, int aX, int aY, int aZ, int aSide) {
		if (aSide == OPPOSITES[mSide]) return T;
		Block aBlock = aWorld.getBlock(aX, aY, aZ);
		return aBlock instanceof BlockMetaType && ((BlockMetaType)aBlock).mBlock == mBlock ? aWorld.getBlockMetadata(aX, aY, aZ) != aWorld.getBlockMetadata(aX - OFFSETS_X[aSide], aY - OFFSETS_Y[aSide], aZ - OFFSETS_Z[aSide]) || ((((BlockMetaType)aBlock).mSide != mSide || aSide == mSide) && ((BlockMetaType)aBlock).mSide != OPPOSITES[aSide] && ((BlockMetaType)aBlock).mSide != SIDE_ANY) : super.shouldSideBeRendered(aWorld, aX, aY, aZ, aSide);
	}
}
