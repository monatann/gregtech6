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

package gregapi6.compat.buildcraft;

import buildcraft.api.statements.IStatementContainer;
import buildcraft.api.statements.IStatementParameter;
import gregapi6.code.TagData;
import gregapi6.data.MD;
import gregapi6.tileentity.energy.ITileEntityEnergyDataCapacitor;
import net.minecraft.tileentity.TileEntity;

public class TriggerBC_Energy_Capacity_Empty extends TriggerBC {
	public final TagData mEnergyType;
	
	public TriggerBC_Energy_Capacity_Empty(TagData aEnergyType) {
		super(MD.GAPI.mID, aEnergyType.mName.toLowerCase()+".capacity.empty", "Is Empty (" + aEnergyType.getLocalisedNameShort() + ")");
		mEnergyType = aEnergyType;
	}
	
	@Override
	public boolean isActive(TileEntity aTarget, byte aSideOfTileEntity, IStatementContainer aSource, IStatementParameter[] aParameters) {
		return ((ITileEntityEnergyDataCapacitor)aTarget).getEnergyStored(mEnergyType, aSideOfTileEntity) <= 0;
	}
	
	@Override
	public boolean isApplicable(TileEntity aTarget, byte aSideOfTileEntity) {
		return aTarget instanceof ITileEntityEnergyDataCapacitor && ((ITileEntityEnergyDataCapacitor)aTarget).isEnergyCapacitorType(mEnergyType, aSideOfTileEntity);
	}
}
