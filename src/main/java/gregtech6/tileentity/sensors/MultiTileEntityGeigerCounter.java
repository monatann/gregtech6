/**
 * Copyright (c) 2019 Gregorius Techneticies
 *
 * This file is part of GregTech.
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
 * along with GregTech. If not, see <http://www.gnu.org/licenses/>.
 */

package gregtech6.tileentity.sensors;

import static gregapi6.data.CS.*;

import gregapi6.data.BI;
import gregapi6.data.FL;
import gregapi6.data.LH;
import gregapi6.item.IItemReactorRod;
import gregapi6.old.Textures;
import gregapi6.render.IIconContainer;
import gregapi6.tileentity.delegate.DelegatorTileEntity;
import gregapi6.tileentity.machines.MultiTileEntitySensorTE;
import gregapi6.util.ST;
import gregapi6.util.UT;
import gregtech6.tileentity.energy.reactors.MultiTileEntityReactorCore;
import net.minecraft.tileentity.TileEntity;

/**
 * @author Gregorius Techneticies, Erik3003
 */
public class MultiTileEntityGeigerCounter extends MultiTileEntitySensorTE {
		static {LH.add("gt6.tooltip.sensor.geigercounter", "Measures Neutron Energy Levels");}
		@Override public String getSensorDescription() {return LH.get("gt6.tooltip.sensor.geigercounter");}


	@Override
	public long getCurrentValue(DelegatorTileEntity<TileEntity> aDelegator) {
		if (aDelegator.mTileEntity instanceof MultiTileEntityReactorCore) {
			return UT.Code.sum(((MultiTileEntityReactorCore)aDelegator.mTileEntity).oNeutronCounts);
		}
		return 0;
	}

	@Override
	public long getCurrentMax(DelegatorTileEntity<TileEntity> aDelegator) {
		if (aDelegator.mTileEntity instanceof MultiTileEntityReactorCore) {
			MultiTileEntityReactorCore TE = (MultiTileEntityReactorCore)aDelegator.mTileEntity;
			if (FL.Coolant_IC2.is(TE.mTanks[0])) return TE.mTanks[0].getCapacity();
			int tMaximum = 0;
			tMaximum += TE.slotHas(0) && ST.item(TE.slot(0)) instanceof IItemReactorRod ? ((IItemReactorRod) ST.item(TE.slot(0))).getReactorRodNeutronMaximum(TE, 0, TE.slot(0)) : 0;
			tMaximum += TE.slotHas(1) && ST.item(TE.slot(1)) instanceof IItemReactorRod ? ((IItemReactorRod) ST.item(TE.slot(1))).getReactorRodNeutronMaximum(TE, 1, TE.slot(1)) : 0;
			tMaximum += TE.slotHas(2) && ST.item(TE.slot(2)) instanceof IItemReactorRod ? ((IItemReactorRod) ST.item(TE.slot(2))).getReactorRodNeutronMaximum(TE, 2, TE.slot(2)) : 0;
			tMaximum += TE.slotHas(3) && ST.item(TE.slot(3)) instanceof IItemReactorRod ? ((IItemReactorRod) ST.item(TE.slot(3))).getReactorRodNeutronMaximum(TE, 3, TE.slot(3)) : 0;
			return tMaximum;

		}
		return 0;
	}

	@Override public short[] getSymbolColor() {return CA_GREEN_255;}
	@Override public IIconContainer getSymbolIcon() {return BI.CHAR_KELVIN;}
	@Override public IIconContainer getTextureFront() {return sTextureFront;}
	@Override public IIconContainer getTextureBack () {return sTextureBack;}
	@Override public IIconContainer getTextureSide () {return sTextureSide;}
	@Override public IIconContainer getOverlayFront() {return sOverlayFront;}
	@Override public IIconContainer getOverlayBack () {return sOverlayBack;}
	@Override public IIconContainer getOverlaySide () {return sOverlaySide;}

	public static IIconContainer
	sTextureFront   = new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/geigercounter/colored/front"),
	sTextureBack    = new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/geigercounter/colored/back"),
	sTextureSide    = new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/geigercounter/colored/side"),
	sOverlayFront   = new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/geigercounter/overlay/front"),
	sOverlayBack    = new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/geigercounter/overlay/back"),
	sOverlaySide    = new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/geigercounter/overlay/side");

	@Override public String getTileEntityName() {return "gt6.multitileentity.redstone.sensors.geigercounter";}
}