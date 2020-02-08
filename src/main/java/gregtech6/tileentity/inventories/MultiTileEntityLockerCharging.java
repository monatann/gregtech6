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

package gregtech6.tileentity.inventories;

import static gregapi6.data.CS.*;

import java.util.Collection;

import gregapi6.code.TagData;
import gregapi6.data.TD;
import gregapi6.item.IItemEnergy;
import gregapi6.old.Textures;
import gregapi6.render.BlockTextureDefault;
import gregapi6.render.BlockTextureMulti;
import gregapi6.render.IIconContainer;
import gregapi6.render.ITexture;
import gregapi6.tileentity.energy.ITileEntityEnergyElectricityAcceptor;
import gregapi6.tileentity.energy.ITileEntityEnergyFluxHandler;
import gregapi6.util.UT;
import net.minecraft.block.Block;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityLockerCharging extends MultiTileEntityLocker implements ITileEntityEnergyElectricityAcceptor, ITileEntityEnergyFluxHandler {
	@Override
	public long doInject(TagData aEnergyType, byte aSide, long aSize, long aAmount, boolean aDoInject) {
		long rReturn = 0;
		for (int i = 0; i < 4; i++) if (slotHas(i)) rReturn += IItemEnergy.Utility.inject(aEnergyType, slot(i), aSize, aAmount-rReturn, this, getWorld(), getX(), getY(), getZ(), aDoInject);
		return rReturn;
	}
	
	@Override public boolean isEnergyType                   (TagData aEnergyType, byte aSide, boolean aEmitting) {return !aEmitting;}
	@Override public long getEnergySizeInputMin             (TagData aEnergyType, byte aSide) {return 1;}
	@Override public long getEnergySizeInputMax             (TagData aEnergyType, byte aSide) {return Long.MAX_VALUE;}
	@Override public long getEnergySizeInputRecommended     (TagData aEnergyType, byte aSide) {return Long.MAX_VALUE;}
	@Override public Collection<TagData> getEnergyTypes(byte aSide) {return TD.Energy.ALL;}
	
	@Override public boolean getStateRunningPassively   () {return UT.Code.containsSomething(getInventory());}
	@Override public boolean getStateRunningPossible    () {return UT.Code.containsSomething(getInventory());}
	@Override public boolean getStateRunningActively    () {for (int i = 0; i < 4; i++) if (!IItemEnergy.Utility.full(slot(i), T)) return F; return UT.Code.containsSomething(getInventory());}
	@Override public boolean getStateRunningSuccessfully() {for (int i = 0; i < 4; i++) if (!IItemEnergy.Utility.full(slot(i), T)) return F; return UT.Code.containsSomething(getInventory());}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		if (!aShouldSideBeRendered[aSide]) return null;
		int aIndex = aSide<2?aSide:aSide==mFacing?2:aSide==OPPOSITES[mFacing]?3:4;
		return BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[aIndex], mRGBa), BlockTextureDefault.get(sOverlays[aIndex]));
	}
	
	// Icons
	@SuppressWarnings("hiding")
	public static IIconContainer sColoreds[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/lockers/charging/colored/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/lockers/charging/colored/top"),
		new Textures.BlockIcons.CustomIcon("machines/lockers/charging/colored/front"),
		new Textures.BlockIcons.CustomIcon("machines/lockers/charging/colored/back"),
		new Textures.BlockIcons.CustomIcon("machines/lockers/charging/colored/side"),
	}, sOverlays[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/lockers/charging/overlay/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/lockers/charging/overlay/top"),
		new Textures.BlockIcons.CustomIcon("machines/lockers/charging/overlay/front"),
		new Textures.BlockIcons.CustomIcon("machines/lockers/charging/overlay/back"),
		new Textures.BlockIcons.CustomIcon("machines/lockers/charging/overlay/side"),
	};
	
	@Override public String getTileEntityName() {return "gt6.multitileentity.locker.charging";}
}
