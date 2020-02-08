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
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.WorldServer;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityPlayerCounter extends MultiTileEntitySensorTE {
	static {LH.add("gt6.tooltip.sensor.playercounter", "Measures Player Count of the whole Server");}
	@Override public String getSensorDescription() {return LH.get("gt6.tooltip.sensor.playercounter");}
	
	@Override
	public long getCurrentValue(DelegatorTileEntity<TileEntity> aDelegator) {
		return ((WorldServer)worldObj).func_73046_m().getCurrentPlayerCount();
	}
	
	@Override
	public long getCurrentMax(DelegatorTileEntity<TileEntity> aDelegator) {
		return ((WorldServer)worldObj).func_73046_m().getMaxPlayers();
	}
	
	@Override public short[] getSymbolColor() {return CA_LIGHT_BLUE_255;}
	@Override public IIconContainer getSymbolIcon() {return BI.CHAR_GREG;}
	@Override public IIconContainer getTextureFront() {return sTextureFront;}
	@Override public IIconContainer getTextureBack () {return sTextureBack;}
	@Override public IIconContainer getTextureSide () {return sTextureSide;}
	@Override public IIconContainer getOverlayFront() {return sOverlayFront;}
	@Override public IIconContainer getOverlayBack () {return sOverlayBack;}
	@Override public IIconContainer getOverlaySide () {return sOverlaySide;}
	
	public static IIconContainer
	sTextureFront   = new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/playercounter/colored/front"),
	sTextureBack    = new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/playercounter/colored/back"),
	sTextureSide    = new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/playercounter/colored/side"),
	sOverlayFront   = new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/playercounter/overlay/front"),
	sOverlayBack    = new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/playercounter/overlay/back"),
	sOverlaySide    = new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/playercounter/overlay/side");
	
	@Override public String getTileEntityName() {return "gt6.multitileentity.redstone.sensors.playercounter";}
}
