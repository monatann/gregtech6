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

package gregtech6.blocks.tool;

import gregapi6.block.misc.BlockBaseBars;
import gregapi6.data.LH;
import gregapi6.data.MT;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBarsTitanium extends BlockBaseBars {
	public BlockBarsTitanium(String aNameInternal) {
		super(aNameInternal, MT.Ti, Material.iron, Block.soundTypeMetal);
		LH.add(getUnlocalizedName()+ ".0.name" , "Titanium Bars");
		LH.add(getUnlocalizedName()+ ".1.name" , "Titanium Bars");
		LH.add(getUnlocalizedName()+ ".2.name" , "Titanium Bars");
		LH.add(getUnlocalizedName()+ ".3.name" , "Titanium Bars");
		LH.add(getUnlocalizedName()+ ".4.name" , "Titanium Bars");
		LH.add(getUnlocalizedName()+ ".5.name" , "Titanium Bars");
		LH.add(getUnlocalizedName()+ ".6.name" , "Titanium Bars");
		LH.add(getUnlocalizedName()+ ".7.name" , "Titanium Bars");
		LH.add(getUnlocalizedName()+ ".8.name" , "Titanium Bars");
		LH.add(getUnlocalizedName()+ ".9.name" , "Titanium Bars");
		LH.add(getUnlocalizedName()+ ".10.name", "Titanium Bars");
		LH.add(getUnlocalizedName()+ ".11.name", "Titanium Bars");
		LH.add(getUnlocalizedName()+ ".12.name", "Titanium Bars");
		LH.add(getUnlocalizedName()+ ".13.name", "Titanium Bars");
		LH.add(getUnlocalizedName()+ ".14.name", "Titanium Bars");
		LH.add(getUnlocalizedName()+ ".15.name", "Titanium Bars");
	}
	
	@Override public float getExplosionResistance(int aMeta) {return 12;}
}
