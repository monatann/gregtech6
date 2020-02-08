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
import gregapi6.data.ANY;
import gregapi6.data.LH;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBarsWood extends BlockBaseBars {
	public BlockBarsWood(String aNameInternal) {
		super(aNameInternal, ANY.Wood, Material.wood, Block.soundTypeWood);
		LH.add(getUnlocalizedName()+ ".0.name" , "Wood Bars");
		LH.add(getUnlocalizedName()+ ".1.name" , "Wood Bars");
		LH.add(getUnlocalizedName()+ ".2.name" , "Wood Bars");
		LH.add(getUnlocalizedName()+ ".3.name" , "Wood Bars");
		LH.add(getUnlocalizedName()+ ".4.name" , "Wood Bars");
		LH.add(getUnlocalizedName()+ ".5.name" , "Wood Bars");
		LH.add(getUnlocalizedName()+ ".6.name" , "Wood Bars");
		LH.add(getUnlocalizedName()+ ".7.name" , "Wood Bars");
		LH.add(getUnlocalizedName()+ ".8.name" , "Wood Bars");
		LH.add(getUnlocalizedName()+ ".9.name" , "Wood Bars");
		LH.add(getUnlocalizedName()+ ".10.name", "Wood Bars");
		LH.add(getUnlocalizedName()+ ".11.name", "Wood Bars");
		LH.add(getUnlocalizedName()+ ".12.name", "Wood Bars");
		LH.add(getUnlocalizedName()+ ".13.name", "Wood Bars");
		LH.add(getUnlocalizedName()+ ".14.name", "Wood Bars");
		LH.add(getUnlocalizedName()+ ".15.name", "Wood Bars");
	}
	
	@Override public float getExplosionResistance(int aMeta) {return 3;}
}
