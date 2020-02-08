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

package gregapi6.tileentity;

/**
 * @author Gregorius Techneticies
 */
public interface ITileEntityUnloadable {
	/**
	 * Checks if the TileEntity is Invalid or Unloaded. Stupid Minecraft cannot do that for the Unloaded Check btw.
	 * Implementing this Function properly is very important, and should be required for every TileEntity.
	 * That is why I made it a separate Interface and forced it as super Interface to all my other Interfaces.
	 * 
	 * To do it properly just add a true Boolean Flag to your Member Variables ("mIsDead=true" for example) and set it to false when "onChunkUnload" is called. Then return the following:
	 * 
	 * @return mIsDead || isInvalid()
	 */
	public boolean isDead();
}
