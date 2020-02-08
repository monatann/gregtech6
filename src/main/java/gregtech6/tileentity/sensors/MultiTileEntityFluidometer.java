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

package gregtech6.tileentity.sensors;

import static gregapi6.data.CS.*;

import gregapi6.data.BI;
import gregapi6.data.LH;
import gregapi6.old.Textures;
import gregapi6.render.IIconContainer;
import gregapi6.tileentity.delegate.DelegatorTileEntity;
import gregapi6.tileentity.machines.MultiTileEntitySensorTE;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fluids.IFluidHandler;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityFluidometer extends MultiTileEntitySensorTE {
	static {LH.add("gt6.tooltip.sensor.fluidometer", "Measures Fluids (In Liters)");}
	@Override public String getSensorDescription() {return LH.get("gt6.tooltip.sensor.fluidometer");}
	
	@Override
	public long getCurrentValue(DelegatorTileEntity<TileEntity> aDelegator) {
		if (aDelegator.mTileEntity instanceof IFluidHandler) {
			FluidTankInfo[] tInfo = ((IFluidHandler)aDelegator.mTileEntity).getTankInfo(FORGE_DIR[aDelegator.mSideOfTileEntity]);
			if (tInfo != null) {
				long rFluid = 0;
				for (FluidTankInfo tTank : tInfo) if (tTank != null && tTank.fluid != null) rFluid += tTank.fluid.amount;
				return rFluid;
			}
		}
		Block tBlock = aDelegator.getBlock();
		if (tBlock == Blocks.water || tBlock == Blocks.flowing_water || tBlock == Blocks.lava || tBlock == Blocks.flowing_lava) {
			return aDelegator.getMetaData() == 0 ? 1000 : 0;
		}
		if (tBlock instanceof IFluidBlock) {
			FluidStack tFluid = ((IFluidBlock)tBlock).drain(aDelegator.mWorld, aDelegator.mX, aDelegator.mY, aDelegator.mZ, F);
			return tFluid == null ? 0 : tFluid.amount;
		}
		return 0;
	}
	
	@Override
	public long getCurrentMax(DelegatorTileEntity<TileEntity> aDelegator) {
		if (aDelegator.mTileEntity instanceof IFluidHandler) {
			FluidTankInfo[] tInfo = ((IFluidHandler)aDelegator.mTileEntity).getTankInfo(FORGE_DIR[aDelegator.mSideOfTileEntity]);
			if (tInfo != null) {
				long rCapacity = 0;
				for (FluidTankInfo tTank : tInfo) if (tTank != null) rCapacity += tTank.capacity;
				return rCapacity;
			}
		}
		return 0;
	}
	
	@Override public short[] getSymbolColor() {return CA_BLUE_255;}
	@Override public IIconContainer getSymbolIcon() {return BI.CHAR_LITER;}
	@Override public IIconContainer getTextureFront() {return sTextureFront;}
	@Override public IIconContainer getTextureBack () {return sTextureBack;}
	@Override public IIconContainer getTextureSide () {return sTextureSide;}
	@Override public IIconContainer getOverlayFront() {return sOverlayFront;}
	@Override public IIconContainer getOverlayBack () {return sOverlayBack;}
	@Override public IIconContainer getOverlaySide () {return sOverlaySide;}
	
	public static IIconContainer
	sTextureFront   = new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/fluidometer/colored/front"),
	sTextureBack    = new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/fluidometer/colored/back"),
	sTextureSide    = new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/fluidometer/colored/side"),
	sOverlayFront   = new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/fluidometer/overlay/front"),
	sOverlayBack    = new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/fluidometer/overlay/back"),
	sOverlaySide    = new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/fluidometer/overlay/side");
	
	@Override public String getTileEntityName() {return "gt6.multitileentity.redstone.sensors.fluidometer";}
}
