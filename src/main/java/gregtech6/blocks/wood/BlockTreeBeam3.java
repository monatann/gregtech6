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

package gregtech6.blocks.wood;

import gregapi6.block.tree.BlockBaseBeamFlammable;
import gregapi6.data.LH;
import gregapi6.old.Textures;
import net.minecraft.block.material.Material;

public class BlockTreeBeam3 extends BlockBaseBeamFlammable {
	public BlockTreeBeam3(String aUnlocalised) {
		super(null, aUnlocalised, Material.wood, soundTypeWood, Textures.BlockIcons.BEAMS_3);
		
		LH.add(getUnlocalizedName()+ ".0.name", "Greatwood Beam");
		LH.add(getUnlocalizedName()+ ".4.name", "Greatwood Beam");
		LH.add(getUnlocalizedName()+ ".8.name", "Greatwood Beam");
		LH.add(getUnlocalizedName()+".12.name", "Greatwood Beam");
		
		LH.add(getUnlocalizedName()+ ".1.name", "Silverwood Beam");
		LH.add(getUnlocalizedName()+ ".5.name", "Silverwood Beam");
		LH.add(getUnlocalizedName()+ ".9.name", "Silverwood Beam");
		LH.add(getUnlocalizedName()+".13.name", "Silverwood Beam");
		
		LH.add(getUnlocalizedName()+ ".2.name", "Skyroot Beam");
		LH.add(getUnlocalizedName()+ ".6.name", "Skyroot Beam");
		LH.add(getUnlocalizedName()+".10.name", "Skyroot Beam");
		LH.add(getUnlocalizedName()+".14.name", "Skyroot Beam");
		
		LH.add(getUnlocalizedName()+ ".3.name", "Darkwood Beam");
		LH.add(getUnlocalizedName()+ ".7.name", "Darkwood Beam");
		LH.add(getUnlocalizedName()+".11.name", "Darkwood Beam");
		LH.add(getUnlocalizedName()+".15.name", "Darkwood Beam");
	}
}
