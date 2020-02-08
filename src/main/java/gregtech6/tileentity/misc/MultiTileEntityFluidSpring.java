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

package gregtech6.tileentity.misc;

import static gregapi6.data.CS.*;

import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_GetBlockHardness;
import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_GetExplosionResistance;
import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_GetLightOpacity;
import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_IsSideSolid;
import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_OnRegistration;
import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_SyncDataShort;
import gregapi6.block.multitileentity.MultiTileEntityRegistry;
import gregapi6.data.FL;
import gregapi6.network.INetworkHandler;
import gregapi6.network.IPacket;
import gregapi6.old.Textures;
import gregapi6.render.BlockTextureDefault;
import gregapi6.render.BlockTextureFluid;
import gregapi6.render.BlockTextureMulti;
import gregapi6.render.ITexture;
import gregapi6.tileentity.base.TileEntityBase04MultiTileEntities;
import gregapi6.tileentity.data.ITileEntitySurface;
import gregapi6.util.UT;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidFinite;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityFluidSpring extends TileEntityBase04MultiTileEntities implements IMTE_OnRegistration, ITileEntitySurface, IMTE_IsSideSolid, IMTE_GetExplosionResistance, IMTE_GetBlockHardness, IMTE_GetLightOpacity, IMTE_SyncDataShort {
	public FluidStack mFluid = FL.Water.make(1);
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey("gt6.spring")) mFluid = FL.load(aNBT, "gt6.spring");
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		FL.save(aNBT, "gt6.spring", mFluid);
	}
	
	@Override
	public final NBTTagCompound writeItemNBT(NBTTagCompound aNBT) {
		aNBT = super.writeItemNBT(aNBT);
		FL.save(aNBT, "gt6.spring", mFluid);
		return aNBT;
	}
	
	public static MultiTileEntityRegistry MTE_REGISTRY = null;
	public static MultiTileEntityFluidSpring INSTANCE;
	
	public static boolean setBlock(World aWorld, int aX, int aY, int aZ, FluidStack aSpring) {
		return MTE_REGISTRY.mBlock.placeBlock(aWorld, aX, aY, aZ, SIDE_UP, INSTANCE.getMultiTileEntityID(), UT.NBT.make("gt6.spring", aSpring), T, F);
	}
	
	@Override
	public IPacket getClientDataPacket(boolean aSendAll) {
		return getClientDataPacketShort(aSendAll, (short)mFluid.getFluid().getID());
	}
	
	@Override
	public boolean receiveDataShort(short aData, INetworkHandler aNetworkHandler) {
		mFluid = FL.make(FL.fluid(aData), 600);
		return T;
	}
	
	@Override
	public void onRegistration(MultiTileEntityRegistry aRegistry, short aID) {
		INSTANCE = this;
		MTE_REGISTRY = aRegistry;
	}
	
	@Override
	public void onTick(long aTimer, boolean aIsServerSide) {
		super.onTick(aTimer, aIsServerSide);
		if (aIsServerSide) {
			if (rng(mFluid.amount) == 0) {
				Block tBlock = mFluid.getFluid().getBlock(), tAbove = getBlockAtSide(SIDE_UP);
				if (tBlock instanceof BlockFluidFinite) {
					if (tAbove == tBlock) {
						worldObj.setBlock(xCoord, yCoord+1, zCoord, tBlock, UT.Code.bind4(getMetaDataAtSide(SIDE_UP)+8), 3);
					} else if (tAbove.isAir(worldObj, xCoord, yCoord+1, zCoord)) {
						worldObj.setBlock(xCoord, yCoord+1, zCoord, tBlock, 7, 3);
					}
				} else {
					if (tAbove == tBlock) {
						if (getMetaDataAtSide(SIDE_UP) == 0) {
							for (byte tSide : ALL_SIDES_HORIZONTAL) {
								tAbove = getBlock(xCoord+OFFSETS_X[tSide], yCoord+1, zCoord+OFFSETS_Z[tSide]);
								if (tAbove == tBlock) {
									if (0 != getMetaData(xCoord+OFFSETS_X[tSide], yCoord+1, zCoord+OFFSETS_Z[tSide])) {
										worldObj.setBlock(xCoord+OFFSETS_X[tSide], yCoord+1, zCoord+OFFSETS_Z[tSide], tBlock, 0, 3);
										break;
									}
								} else if (tAbove.isAir(worldObj, xCoord+OFFSETS_X[tSide], yCoord+1, zCoord+OFFSETS_Z[tSide])) {
									worldObj.setBlock(xCoord+OFFSETS_X[tSide], yCoord+1, zCoord+OFFSETS_Z[tSide], tBlock, 0, 3);
									break;
								}
							}
						} else {
							worldObj.setBlock(xCoord, yCoord+1, zCoord, tBlock, 0, 3);
						}
					} else if (tAbove.isAir(worldObj, xCoord, yCoord+1, zCoord)) {
						worldObj.setBlock(xCoord, yCoord+1, zCoord, tBlock, 0, 3);
					}
				}
			}
		}
	}
	
	@Override public boolean setBlockBounds(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {return F;}
	@Override public int getRenderPasses(Block aBlock, boolean[] aShouldSideBeRendered) {return 1;}
	@Override public ITexture getTexture(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {return aShouldSideBeRendered[aSide] ? BlockTextureMulti.get(BlockTextureFluid.get(mFluid), BlockTextureDefault.get(Textures.BlockIcons.FLUID_SPRING)) : null;}
	
	@Override public int getLightOpacity() {return LIGHT_OPACITY_MAX;}
	
	@Override public float getExplosionResistance2() {return Blocks.bedrock.getExplosionResistance(null);}
	@Override public float getBlockHardness() {return -1;}
	
	@Override public boolean isSurfaceSolid         (byte aSide) {return T;}
	@Override public boolean isSurfaceOpaque        (byte aSide) {return T;}
	@Override public boolean isSideSolid            (byte aSide) {return T;}
	
	@Override public String getTileEntityName() {return "gt6.multitileentity.fluid.spring";}
}
