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

import gregapi6.block.BlockBaseMeta;
import gregapi6.block.IBlockOnWalkOver;
import gregapi6.code.ArrayListNoNulls;
import gregapi6.data.IL;
import gregapi6.data.LH;
import gregapi6.old.Textures;
import gregapi6.render.BlockTextureDefault;
import gregapi6.render.BlockTextureMulti;
import gregapi6.render.IRenderedBlock;
import gregapi6.render.IRenderedBlockObject;
import gregapi6.render.ITexture;
import gregapi6.render.RendererBlockTextured;
import gregapi6.util.ST;
import gregapi6.util.WD;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPath extends BlockBaseMeta implements IBlockOnWalkOver, IRenderedBlock {
	public BlockPath(String aUnlocalised) {
		super(null, aUnlocalised, Material.grass, soundTypeGrass, 12, Textures.BlockIcons.DIRTS);
		LH.add(getUnlocalizedName()+  ".0.name", "Grass Path");
		LH.add(getUnlocalizedName()+  ".1.name", "Aether Grass Path");
		LH.add(getUnlocalizedName()+  ".2.name", "Loamy Grass Path");
		LH.add(getUnlocalizedName()+  ".3.name", "Sandy Grass Path");
		LH.add(getUnlocalizedName()+  ".4.name", "Silty Grass Path");
		LH.add(getUnlocalizedName()+  ".5.name", "Alfisol Grass Path");
		LH.add(getUnlocalizedName()+  ".6.name", "Andisol Grass Path");
		LH.add(getUnlocalizedName()+  ".7.name", "Gelisol Grass Path");
		LH.add(getUnlocalizedName()+  ".8.name", "Histosol Grass Path");
		LH.add(getUnlocalizedName()+  ".9.name", "Inceptisol Grass Path");
		LH.add(getUnlocalizedName()+ ".10.name", "Mollisol Grass Path");
		LH.add(getUnlocalizedName()+ ".11.name", "Oxisol Grass Path");
		LH.add(getUnlocalizedName()+ ".12.name", "Grass Path");
		LH.add(getUnlocalizedName()+ ".13.name", "Grass Path");
		LH.add(getUnlocalizedName()+ ".14.name", "Grass Path");
		LH.add(getUnlocalizedName()+ ".15.name", "Grass Path");
		setBlockBounds(0, 0, 0, 1, PIXELS_NEG[1], 1);
	}
	
	@Override
	public ArrayList<ItemStack> getDrops(World aWorld, int aX, int aY, int aZ, int aMeta, int aFortune) {
		switch(aMeta) {
		case  1: return new ArrayListNoNulls<>(F, IL.AETHER_Dirt.get(1));
		case  2: return new ArrayListNoNulls<>(F, IL.BoP_Dirt_Loamy.get(1));
		case  3: return new ArrayListNoNulls<>(F, IL.BoP_Dirt_Sandy.get(1));
		case  4: return new ArrayListNoNulls<>(F, IL.BoP_Dirt_Silty.get(1));
		case  5: return new ArrayListNoNulls<>(F, IL.EB_Dirt_Alfisol.get(1));
		case  6: return new ArrayListNoNulls<>(F, IL.EB_Dirt_Andisol.get(1));
		case  7: return new ArrayListNoNulls<>(F, IL.EB_Dirt_Gelisol.get(1));
		case  8: return new ArrayListNoNulls<>(F, IL.EB_Dirt_Histosol.get(1));
		case  9: return new ArrayListNoNulls<>(F, IL.EB_Dirt_Inceptisol.get(1));
		case 10: return new ArrayListNoNulls<>(F, IL.EB_Dirt_Mollisol.get(1));
		case 11: return new ArrayListNoNulls<>(F, IL.EB_Dirt_Oxisol.get(1));
		default: return new ArrayListNoNulls<>(F, ST.make(Blocks.dirt, 1, 0));
		}
	}
	
	@Override
	public boolean shouldSideBeRendered(IBlockAccess aWorld, int aX, int aY, int aZ, int aSide) {
		if (SIDES_TOP[aSide]) return T;
		Block tBlock = aWorld.getBlock(aX, aY, aZ);
		return tBlock != this && tBlock != Blocks.farmland && !WD.visOpq(tBlock);
	}
	
	@Override public int getRenderType() {return RendererBlockTextured.INSTANCE==null?0:RendererBlockTextured.INSTANCE.mRenderID;}
	
	@Override
	public ITexture getTexture(int aRenderPass, byte aSide, ItemStack aStack) {
		if (SIDES_TOP[aSide]) return BlockTextureDefault.get(Textures.BlockIcons.PATH_TOP);
		ITexture tDirt = BlockTextureDefault.get(mIcons[ST.meta_(aStack) % 16]);
		return SIDES_BOTTOM[aSide]?tDirt:BlockTextureMulti.get(tDirt, BlockTextureDefault.get(Textures.BlockIcons.PATH_SIDE));
	}
	
	@Override
	public ITexture getTexture(int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered, IBlockAccess aWorld, int aX, int aY, int aZ) {
		if (SIDES_TOP[aSide]) return BlockTextureDefault.get(Textures.BlockIcons.PATH_TOP);
		ITexture tDirt = BlockTextureDefault.get(mIcons[aWorld.getBlockMetadata(aX, aY, aZ) % 16]);
		return SIDES_BOTTOM[aSide]?tDirt:BlockTextureMulti.get(tDirt, BlockTextureDefault.get(Textures.BlockIcons.PATH_SIDE));
	}
	
	@Override public boolean usesRenderPass(int aRenderPass, ItemStack aStack                                                                     ) {return T;}
	@Override public boolean usesRenderPass(int aRenderPass, IBlockAccess aWorld, int aX, int aY, int aZ, boolean[] aShouldSideBeRendered         ) {return T;}
	@Override public boolean setBlockBounds(int aRenderPass, ItemStack aStack                                                                     ) {setBlockBounds(0, 0, 0, 1, PIXELS_NEG[1], 1); return T;}
	@Override public boolean setBlockBounds(int aRenderPass, IBlockAccess aWorld, int aX, int aY, int aZ, boolean[] aShouldSideBeRendered         ) {setBlockBounds(0, 0, 0, 1, PIXELS_NEG[1], 1); return T;}
	@Override public int getRenderPasses(ItemStack aStack                                                                                         ) {return 1;}
	@Override public int getRenderPasses(IBlockAccess aWorld, int aX, int aY, int aZ, boolean[] aShouldSideBeRendered                             ) {return 1;}
	@Override public IRenderedBlockObject passRenderingToObject(ItemStack aStack                                                                  ) {return null;}
	@Override public IRenderedBlockObject passRenderingToObject(IBlockAccess aWorld, int aX, int aY, int aZ                                       ) {return null;}
	
	@Override public void onWalkOver(EntityLivingBase aEntity, World aWorld, int aX, int aY, int aZ) {aEntity.motionX *= 1.1; aEntity.motionZ *= 1.1;}
	@Override public IIcon getIcon(int aSide, int aMeta) {return (SIDES_TOP[aSide]?Textures.BlockIcons.PATH_TOP:Textures.BlockIcons.DIRTS[aMeta % 16]).getIcon(0);}
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool(World aWorld, int aX, int aY, int aZ) {return AxisAlignedBB.getBoundingBox(aX, aY, aZ, aX+1, aY+1, aZ+1);}
	@Override public AxisAlignedBB getSelectedBoundingBoxFromPool (World aWorld, int aX, int aY, int aZ) {return AxisAlignedBB.getBoundingBox(aX, aY, aZ, aX+1, aY+1, aZ+1);}
	@Override public boolean doesWalkSpeed(short aMeta) {return T;}
	@Override public boolean doesPistonPush(short aMeta) {return T;}
	@Override public boolean canCreatureSpawn(int aMeta) {return F;}
	@Override public boolean canSilkHarvest() {return F;}
	@Override public boolean isSealable(int aMeta, byte aSide) {return F;}
	@Override public String getHarvestTool(int aMeta) {return TOOL_shovel;}
	@Override public int getHarvestLevel(int aMeta) {return 0;}
	@Override public float getBlockHardness(World aWorld, int aX, int aY, int aZ) {return Blocks.grass.getBlockHardness(aWorld, aX, aY, aZ) * 2;}
	@Override public float getExplosionResistance(int aMeta) {return Blocks.grass.getExplosionResistance(null) * 1.5F;}
	@Override public boolean isSideSolid(int aMeta, byte aSide) {return SIDES_BOTTOM_HORIZONTAL[aSide];}
	@Override public boolean isNormalCube(IBlockAccess aWorld, int aX, int aY, int aZ)  {return F;}
	@Override public boolean isNormalCube() {return F;}
	@Override public boolean isOpaqueCube() {return F;}
	@Override public boolean renderAsNormalBlock() {return F;}
}
