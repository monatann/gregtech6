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
import gregapi6.tileentity.data.ITileEntityWeight;
import gregapi6.tileentity.delegate.DelegatorTileEntity;
import gregapi6.tileentity.machines.MultiTileEntitySensorTE;
import gregapi6.util.OM;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.tileentity.TileEntity;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityWeightometerMedium extends MultiTileEntitySensorTE {
	static {LH.add("gt6.tooltip.sensor.mediumweightometer", "Measures the weight of an Inventory (in Kilogramme)");}
	@Override public String getSensorDescription() {return LH.get("gt6.tooltip.sensor.mediumweightometer");}
	
	public static final double MAX_WEIGHT = B[16]-1;
	
	@Override
	public long getCurrentValue(DelegatorTileEntity<TileEntity> aDelegator) {
		double rWeightKG = 0;
		if (aDelegator.mTileEntity instanceof ITileEntityWeight) {
			rWeightKG = ((ITileEntityWeight)aDelegator.mTileEntity).getWeightValue(aDelegator.mSideOfTileEntity);
		} else if (aDelegator.mTileEntity instanceof IInventory) {
			if (aDelegator.mTileEntity instanceof ISidedInventory) {
				for (int i : ((ISidedInventory)aDelegator.mTileEntity).getAccessibleSlotsFromSide(aDelegator.mSideOfTileEntity)) {
					rWeightKG += OM.weight(((IInventory)aDelegator.mTileEntity).getStackInSlot(i));
					if (rWeightKG >= MAX_WEIGHT) break;
				}
			} else for (int i = 0, j = ((IInventory)aDelegator.mTileEntity).getSizeInventory(); i < j; i++) {
				rWeightKG += OM.weight(((IInventory)aDelegator.mTileEntity).getStackInSlot(i));
				if (rWeightKG >= MAX_WEIGHT) break;
			}
		}
		if (rWeightKG >= MAX_WEIGHT) rWeightKG = MAX_WEIGHT;
		return (long)rWeightKG;
	}
	
	@Override
	public long getCurrentMax(DelegatorTileEntity<TileEntity> aDelegator) {
		return B[16]-1;
	}
	
	@Override public short[] getSymbolColor() {return CA_GRAY_192;}
	@Override public IIconContainer getSymbolIcon() {return BI.CHAR_KILOGRAMM;}
	@Override public IIconContainer getTextureFront() {return sTextureFront;}
	@Override public IIconContainer getTextureBack () {return sTextureBack;}
	@Override public IIconContainer getTextureSide () {return sTextureSide;}
	@Override public IIconContainer getOverlayFront() {return sOverlayFront;}
	@Override public IIconContainer getOverlayBack () {return sOverlayBack;}
	@Override public IIconContainer getOverlaySide () {return sOverlaySide;}
	
	public static IIconContainer
	sTextureFront   = new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/mediumweightometer/colored/front"),
	sTextureBack    = new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/mediumweightometer/colored/back"),
	sTextureSide    = new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/mediumweightometer/colored/side"),
	sOverlayFront   = new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/mediumweightometer/overlay/front"),
	sOverlayBack    = new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/mediumweightometer/overlay/back"),
	sOverlaySide    = new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/mediumweightometer/overlay/side");
	
	@Override public String getTileEntityName() {return "gt6.multitileentity.redstone.sensors.mediumweightometer";}
}
