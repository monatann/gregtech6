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

package gregtech6.tileentity.extenders;

import gregapi6.tileentity.data.ITileEntityProgress;
import gregapi6.tileentity.machines.ITileEntityRunningSuccessfully;
import gregapi6.tileentity.machines.ITileEntitySwitchableMode;
import gregapi6.tileentity.machines.ITileEntitySwitchableOnOff;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityExtenderController extends MultiTileEntityExtender implements ITileEntityRunningSuccessfully, ITileEntityProgress, ITileEntitySwitchableMode, ITileEntitySwitchableOnOff {
	@Override public String getTileEntityName() {return "gt6.multitileentity.extender.controller";}
}
