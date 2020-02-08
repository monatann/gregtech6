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

import gregapi6.block.tree.BlockBaseBeam;
import gregapi6.data.LH;
import gregapi6.old.Textures;
import net.minecraft.block.material.Material;

public class BlockTreeBeam1FireProof extends BlockBaseBeam {
	public BlockTreeBeam1FireProof(String aUnlocalised) {
		super(null, aUnlocalised, Material.wood, soundTypeWood, 4, Textures.BlockIcons.BEAMS_1);
		
		LH.add(getUnlocalizedName()+ ".0.name", "Oak Beam (Fireproof)");
		LH.add(getUnlocalizedName()+ ".4.name", "Oak Beam (Fireproof)");
		LH.add(getUnlocalizedName()+ ".8.name", "Oak Beam (Fireproof)");
		LH.add(getUnlocalizedName()+".12.name", "Oak Beam (Fireproof)");
		
		LH.add(getUnlocalizedName()+ ".1.name", "Spruce Beam (Fireproof)");
		LH.add(getUnlocalizedName()+ ".5.name", "Spruce Beam (Fireproof)");
		LH.add(getUnlocalizedName()+ ".9.name", "Spruce Beam (Fireproof)");
		LH.add(getUnlocalizedName()+".13.name", "Spruce Beam (Fireproof)");
		
		LH.add(getUnlocalizedName()+ ".2.name", "Birch Beam (Fireproof)");
		LH.add(getUnlocalizedName()+ ".6.name", "Birch Beam (Fireproof)");
		LH.add(getUnlocalizedName()+".10.name", "Birch Beam (Fireproof)");
		LH.add(getUnlocalizedName()+".14.name", "Birch Beam (Fireproof)");
		
		LH.add(getUnlocalizedName()+ ".3.name", "Jungle Beam (Fireproof)");
		LH.add(getUnlocalizedName()+ ".7.name", "Jungle Beam (Fireproof)");
		LH.add(getUnlocalizedName()+".11.name", "Jungle Beam (Fireproof)");
		LH.add(getUnlocalizedName()+".15.name", "Jungle Beam (Fireproof)");
	}
}
