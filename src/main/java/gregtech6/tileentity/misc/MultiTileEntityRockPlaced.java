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

import gregapi6.code.ArrayListNoNulls;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityRockPlaced extends MultiTileEntityRock {
	@Override public ArrayListNoNulls<ItemStack> getDrops(int aFortune, boolean aSilkTouch) {return new ArrayListNoNulls<>(F, mRock);}
	
	@Override public ItemStack getDefaultRock(int aAmount) {return mRock;}
	
	@Override public String getTileEntityName() {return "gt6.multitileentity.rock.placed";}
}
