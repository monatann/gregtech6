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

package gregapi6.block.multitileentity;

import static gregapi6.data.CS.*;

import gregapi6.block.IBlockPlacable;
import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_HasMultiBlockMachineRelevantData;
import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_RegisterIcons;
import gregapi6.render.IRenderedBlock;
import gregapi6.render.IRenderedBlockObject;
import gregapi6.render.ITexture;
import gregapi6.render.RendererBlockTextured;
import gregapi6.tileentity.ITileEntity;
import gregapi6.tileentity.ITileEntityMachineBlockUpdateable;
import gregapi6.util.WD;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityBlockInternal extends Block implements IRenderedBlock, IBlockPlacable {
	public MultiTileEntityRegistry mMultiTileEntityRegistry;
	
	public MultiTileEntityBlockInternal() {
		super(Material.anvil);
	}
	
	@Override public ITexture getTexture(int aRenderPass, byte aSide, ItemStack aStack) {return null;}
	@Override public ITexture getTexture(int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered, IBlockAccess aWorld, int aX, int aY, int aZ) {return null;}
	@Override public boolean setBlockBounds(int aRenderPass, ItemStack aStack) {return F;}
	@Override public boolean setBlockBounds(int aRenderPass, IBlockAccess aWorld, int aX, int aY, int aZ, boolean[] aShouldSideBeRendered) {return F;}
	@Override public int getRenderPasses(ItemStack aStack) {return 0;}
	@Override public int getRenderPasses(IBlockAccess aWorld, int aX, int aY, int aZ, boolean[] aShouldSideBeRendered) {return 0;}
	@Override public boolean usesRenderPass(int aRenderPass, ItemStack aStack) {return T;}
	@Override public boolean usesRenderPass(int aRenderPass, IBlockAccess aWorld, int aX, int aY, int aZ, boolean[] aShouldSideBeRendered) {return T;}
	
	@Override
	public IRenderedBlockObject passRenderingToObject(IBlockAccess aWorld, int aX, int aY, int aZ) {
		return null;
	}
	
	@Override
	public IRenderedBlockObject passRenderingToObject(ItemStack aStack) {
		TileEntity tTileEntity = mMultiTileEntityRegistry.getNewTileEntity(aStack);
		return tTileEntity instanceof IRenderedBlockObject ? (IRenderedBlockObject)tTileEntity : null;
	}
	
	@Override
	public void registerBlockIcons(IIconRegister aIconRegister) {
		for (MultiTileEntityClassContainer tClassContainer : mMultiTileEntityRegistry.mRegistry.values()) if (tClassContainer.mCanonicalTileEntity instanceof IMTE_RegisterIcons) ((IMTE_RegisterIcons)tClassContainer.mCanonicalTileEntity).registerIcons(aIconRegister);
	}
	
	@Override public final int getRenderBlockPass() {return ITexture.Util.MC_ALPHA_BLENDING?1:0;}
	@Override public final int getRenderType() {return RendererBlockTextured.INSTANCE==null?super.getRenderType():RendererBlockTextured.INSTANCE.mRenderID;}
	@Override public final String getUnlocalizedName() {return mMultiTileEntityRegistry.mNameInternal;}
	@Override public final String getLocalizedName() {return StatCollector.translateToLocal(mMultiTileEntityRegistry.mNameInternal + ".name");}
	
	@Override
	public boolean placeBlock(World aWorld, int aX, int aY, int aZ, byte aSide, short aMetaData, NBTTagCompound aNBT, boolean aCauseBlockUpdates, boolean aForcePlacement) {
		Block tReplacedBlock = aWorld.getBlock(aX, aY, aZ);
		MultiTileEntityContainer aMTEContainer = mMultiTileEntityRegistry.getNewTileEntityContainer(aWorld, aX, aY, aZ, aMetaData, aNBT);
		if (aMTEContainer != null && aWorld.setBlock(aX, aY, aZ, aMTEContainer.mBlock, 15-aMTEContainer.mBlockMetaData, 2)) {
			((IMultiTileEntity)aMTEContainer.mTileEntity).setShouldRefresh(F);
			WD.te(aWorld, aX, aY, aZ, aMTEContainer.mTileEntity, F);
			WD.set(aWorld, aX, aY, aZ, aMTEContainer.mBlock, aMTEContainer.mBlockMetaData, 0, F);
			((IMultiTileEntity)aMTEContainer.mTileEntity).setShouldRefresh(T);
			WD.te(aWorld, aX, aY, aZ, aMTEContainer.mTileEntity, aCauseBlockUpdates);
			try {
				if (aMTEContainer.mTileEntity instanceof IMTE_HasMultiBlockMachineRelevantData) {
					if (((IMTE_HasMultiBlockMachineRelevantData)aMTEContainer.mTileEntity).hasMultiBlockMachineRelevantData()) ITileEntityMachineBlockUpdateable.Util.causeMachineUpdate(aWorld, aX, aY, aZ, aMTEContainer.mBlock, aMTEContainer.mBlockMetaData, F);
				}
			} catch(Throwable e) {e.printStackTrace(ERR);}
			try {
				if (!aWorld.isRemote && aCauseBlockUpdates) {
					aWorld.notifyBlockChange(aX, aY, aZ, tReplacedBlock);
					aWorld.func_147453_f(aX, aY, aZ, aMTEContainer.mBlock);
				}
			} catch(Throwable e) {e.printStackTrace(ERR);}
			try {
				if (aMTEContainer.mTileEntity instanceof ITileEntity) {
					((ITileEntity)aMTEContainer.mTileEntity).onTileEntityPlaced();
				}
			} catch(Throwable e) {e.printStackTrace(ERR);}
			try {
				aWorld.func_147451_t(aX, aY, aZ);
			} catch(Throwable e) {e.printStackTrace(ERR);}
			return T;
		}
		return F;
	}
}
