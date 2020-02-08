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

package gregtech6.blocks.fluids;

import static gregapi6.data.CS.*;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import gregapi6.block.IBlockOnHeadInside;
import gregapi6.code.ArrayListNoNulls;
import gregapi6.data.FL;
import gregapi6.data.LH;
import gregapi6.render.RendererBlockFluid;
import gregapi6.tileentity.data.ITileEntitySurface;
import gregapi6.util.ST;
import gregapi6.util.UT;
import gregapi6.util.WD;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author Gregorius Techneticies
 */
public abstract class BlockWaterlike extends BlockFluidClassic implements IBlockOnHeadInside {
	public final Fluid mFluid;
	
	public BlockWaterlike(String aName, Fluid aFluid) {
		super(aFluid, Material.water);
		mFluid = aFluid;
		setResistance(30);
		setBlockName(aName);
		GameRegistry.registerBlock(this, ItemBlock.class, aName);
		if (COMPAT_IC2 != null) COMPAT_IC2.addToExplosionWhitelist(this);
		LH.add(getLocalizedName()+".name", getLocalizedName()); // WAILA is retarded...
		LH.add(getUnlocalizedName()+".name", getLocalizedName());
		LH.add(getUnlocalizedName(), getLocalizedName());
		setFluidStack(FL.make(aFluid, 1000));
		setTickRandomly(F);
		ST.hide(this);
	}
	
	@Override
	public FluidStack drain(World aWorld, int aX, int aY, int aZ, boolean aDoDrain) {
		if (aDoDrain) aWorld.setBlock(aX, aY, aZ, NB, 0, 2);
		return FL.make(getFluid(), 1000);
	}
	
	@Override
	public boolean canDrain(World aWorld, int aX, int aY, int aZ) {
		return aWorld.getBlockMetadata(aX, aY, aZ) == 0;
	}
	
	public void updateFlow(World aWorld, int aX, int aY, int aZ, Random aRandom) {
		int quantaRemaining = quantaPerBlock - aWorld.getBlockMetadata(aX, aY, aZ);
		int expQuanta = -101;
		// check adjacent block levels if non-source
		if (quantaRemaining < quantaPerBlock) {
			int y2 = aY - densityDir;
			if (aWorld.getBlock(aX  , y2, aZ  ) instanceof BlockWaterlike ||
				aWorld.getBlock(aX-1, y2, aZ  ) instanceof BlockWaterlike ||
				aWorld.getBlock(aX+1, y2, aZ  ) instanceof BlockWaterlike ||
				aWorld.getBlock(aX  , y2, aZ-1) instanceof BlockWaterlike ||
				aWorld.getBlock(aX  , y2, aZ+1) instanceof BlockWaterlike) {
				expQuanta = quantaPerBlock - 1;
			} else {
				int maxQuanta = -100;
				maxQuanta = getLargerQuanta(aWorld, aX-1, aY, aZ  , maxQuanta);
				maxQuanta = getLargerQuanta(aWorld, aX+1, aY, aZ  , maxQuanta);
				maxQuanta = getLargerQuanta(aWorld, aX  , aY, aZ-1, maxQuanta);
				maxQuanta = getLargerQuanta(aWorld, aX  , aY, aZ+1, maxQuanta);
				expQuanta = maxQuanta - 1;
			}
			if (expQuanta != quantaRemaining) {
				quantaRemaining = expQuanta;
				if (expQuanta <= 0) {
					aWorld.setBlockToAir(aX, aY, aZ);
				} else {
					aWorld.setBlockMetadataWithNotify(aX, aY, aZ, quantaPerBlock - expQuanta, 3);
					aWorld.scheduleBlockUpdate(aX, aY, aZ, this, tickRate);
					aWorld.notifyBlocksOfNeighborChange(aX, aY, aZ, this);
				}
			}
		}
		// Here was an else Block that only caused huge amounts of Network Lag with no purpose. Forge, just what the fuck, setting Metadata from 0 to 0 and updating that "change" to Clients? There was no change that needed to be updated!
		
		
		if (canDisplace(aWorld, aX, aY+densityDir, aZ)) {
			flowIntoBlock(aWorld, aX, aY+densityDir, aZ, 1);
			return;
		}
		
		int flowMeta = quantaPerBlock - quantaRemaining + 1;
		if (flowMeta >= quantaPerBlock) return;
		
		if (isSourceBlock(aWorld, aX, aY, aZ) || !isFlowingVertically(aWorld, aX, aY, aZ)) {
			if (aWorld.getBlock(aX, aY-densityDir, aZ) instanceof BlockWaterlike) flowMeta = 1;
			boolean flowTo[] = getOptimalFlowDirections(aWorld, aX, aY, aZ);
			if (flowTo[0]) flowIntoBlock(aWorld, aX-1, aY, aZ  , flowMeta);
			if (flowTo[1]) flowIntoBlock(aWorld, aX+1, aY, aZ  , flowMeta);
			if (flowTo[2]) flowIntoBlock(aWorld, aX  , aY, aZ-1, flowMeta);
			if (flowTo[3]) flowIntoBlock(aWorld, aX  , aY, aZ+1, flowMeta);
		}
	}
	
	@Override
	public Vec3 getFlowVector(IBlockAccess aWorld, int aX, int aY, int aZ) {
		Vec3 rVector = Vec3.createVectorHelper(0, 0, 0);
		int tDecay = quantaPerBlock - getQuantaValue(aWorld, aX, aY, aZ);
		for (byte tSide : ALL_SIDES_HORIZONTAL) {
			int tX = aX+OFFSETS_X[tSide], tZ = aZ+OFFSETS_Z[tSide];
			int tOtherDecay = quantaPerBlock - getQuantaValue(aWorld, tX, aY, tZ);
			if (tOtherDecay >= quantaPerBlock) {
				if (!aWorld.getBlock(tX, aY, tZ).getMaterial().blocksMovement()) {
					tOtherDecay = quantaPerBlock - getQuantaValue(aWorld, tX, aY-1, tZ);
					if (tOtherDecay >= 0) {
						int tPower = tOtherDecay - (tDecay - quantaPerBlock);
						rVector = rVector.addVector((tX - aX) * tPower, (aY - aY) * tPower, (tZ - aZ) * tPower);
					}
				}
			} else if (tOtherDecay >= 0) {
				int power = tOtherDecay - tDecay;
				rVector = rVector.addVector((tX - aX) * power, (aY - aY) * power, (tZ - aZ) * power);
			}
		}
		if (aWorld.getBlock(aX, aY+1, aZ) instanceof BlockWaterlike && (
			isBlockSolid(aWorld, aX  , aY  , aZ-1, SIDE_Z_NEG) ||
			isBlockSolid(aWorld, aX  , aY  , aZ+1, SIDE_Z_POS) ||
			isBlockSolid(aWorld, aX-1, aY  , aZ  , SIDE_X_NEG) ||
			isBlockSolid(aWorld, aX+1, aY  , aZ  , SIDE_X_POS) ||
			isBlockSolid(aWorld, aX  , aY+1, aZ-1, SIDE_Z_NEG) ||
			isBlockSolid(aWorld, aX  , aY+1, aZ+1, SIDE_Z_POS) ||
			isBlockSolid(aWorld, aX-1, aY+1, aZ  , SIDE_X_NEG) ||
			isBlockSolid(aWorld, aX+1, aY+1, aZ  , SIDE_X_POS))) {
			rVector = rVector.normalize().addVector(0, -6, 0);
		}
		return rVector.normalize();
	}
	
	@Override
	public int getQuantaValue(IBlockAccess aWorld, int aX, int aY, int aZ) {
		Block aBlock = aWorld.getBlock(aX, aY, aZ);
		if (aBlock == NB) return 0;
		if (aBlock instanceof BlockWaterlike) return quantaPerBlock - aWorld.getBlockMetadata(aX, aY, aZ);
		if (aBlock == Blocks.water || aBlock == Blocks.flowing_water) return 8-aWorld.getBlockMetadata(aX, aY, aZ);
		return -1;
	}
	
	@Override
	public boolean shouldSideBeRendered(IBlockAccess aWorld, int aX, int aY, int aZ, int aSide) {
		Block aBlock = aWorld.getBlock(aX, aY, aZ);
		if (aBlock == NB) return T;
		if (aBlock.getMaterial() == Material.water || WD.visOpq(aBlock)) return F;
		if (aBlock.isAir(aWorld, aX, aY, aZ)) return T;
		TileEntity tTileEntity = aWorld.getTileEntity(aX, aY, aZ);
		return !(tTileEntity instanceof ITileEntitySurface && !((ITileEntitySurface)tTileEntity).isSurfaceOpaque(OPPOSITES[aSide]));
	}
	
	@Override public boolean isSourceBlock(IBlockAccess aWorld, int aX, int aY, int aZ) {return aWorld.getBlock(aX, aY, aZ) instanceof BlockWaterlike && aWorld.getBlockMetadata(aX, aY, aZ) == 0;}
	@Override public final String getUnlocalizedName() {return FL.name(mFluid, F);}
	@Override public String getLocalizedName() {return FL.name(mFluid, T);}
	@Override public void registerBlockIcons(IIconRegister aIconRegister) {/**/}
	@Override public int getLightOpacity() {return 16;}
	@Override public IIcon getIcon(int aSide, int aMeta) {return Blocks.water.getIcon(aSide, aMeta);}
	@Override public boolean getTickRandomly() {return F;}
	@Override public boolean canDisplace(IBlockAccess aWorld, int aX, int aY, int aZ) {return !aWorld.getBlock(aX, aY, aZ).getMaterial().isLiquid() && super.canDisplace(aWorld, aX, aY, aZ);}
	@Override public boolean displaceIfPossible(World aWorld, int aX, int aY, int aZ) {return !aWorld.getBlock(aX, aY, aZ).getMaterial().isLiquid() && super.displaceIfPossible(aWorld, aX, aY, aZ);}
	@Override public int getRenderType() {return RendererBlockFluid.RENDER_ID;}
	
	public BlockWaterlike addEffect(int aEffectID, int aEffectDuration, int aEffectLevel) {
		mEffects.add(new int[] {aEffectID, aEffectDuration, aEffectLevel});
		return this;
	}
	
	public List<int[]> mEffects = new ArrayListNoNulls<>();
	
	@Override
	public void onHeadInside(EntityLivingBase aEntity, World aWorld, int aX, int aY, int aZ) {
		if (!mEffects.isEmpty() && (FL.gas(mFluid) ? !UT.Entities.isImmuneToBreathingGasses(aEntity) : !UT.Entities.isWearingFullChemHazmat(aEntity))) {
			for (int[] tEffects : mEffects) aEntity.addPotionEffect(new PotionEffect(tEffects[0], tEffects[1], tEffects[2], F));
			if (getMaterial() != Material.water && SERVER_TIME % 20 == 0) aEntity.attackEntityFrom(DamageSource.drown, 2.0F);
		}
	}
}
