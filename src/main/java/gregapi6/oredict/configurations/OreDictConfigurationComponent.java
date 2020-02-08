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

package gregapi6.oredict.configurations;

import static gregapi6.data.CS.*;

import gregapi6.code.ArrayListNoNulls;
import gregapi6.oredict.OreDictMaterialStack;
import gregapi6.util.OM;

/**
 * @author Gregorius Techneticies
 */
public class OreDictConfigurationComponent implements IOreDictConfigurationComponent {
	private final ArrayListNoNulls<OreDictMaterialStack> mList;
	private final ArrayListNoNulls<OreDictMaterialStack> mDividedList;
	public final long mCommonDivider;
	
	public OreDictConfigurationComponent(long aCommonDivider, OreDictMaterialStack... aComponents) {
		mCommonDivider = aCommonDivider;
		mList = new ArrayListNoNulls<>(F, aComponents);
		mDividedList = new ArrayListNoNulls<>();
		for (OreDictMaterialStack tMaterial : mList) mDividedList.add(OM.stack(tMaterial.mMaterial, tMaterial.mAmount / mCommonDivider));
	}
	
	@Override
	public ArrayListNoNulls<OreDictMaterialStack> getComponents() {
		return mDividedList;
	}
	
	@Override
	public ArrayListNoNulls<OreDictMaterialStack> getUndividedComponents() {
		return mList;
	}
	
	@Override
	public long getCommonDivider() {
		return mCommonDivider;
	}
}
