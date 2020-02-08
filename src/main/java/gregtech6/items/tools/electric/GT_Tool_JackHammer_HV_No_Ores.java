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

package gregtech6.items.tools.electric;

import static gregapi6.data.CS.*;

import gregapi6.block.IPrefixBlock;
import gregapi6.block.metatype.BlockStones;
import gregapi6.data.CS.BlocksGT;
import gregapi6.util.WD;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;

public class GT_Tool_JackHammer_HV_No_Ores extends GT_Tool_JackHammer_HV {
	public GT_Tool_JackHammer_HV_No_Ores(int aSwitchIndex) {
		super(aSwitchIndex);
	}
	
	@Override
	public boolean isMinableBlock(Block aBlock, byte aMetaData) {
		if (aBlock instanceof IPrefixBlock) return F;
		if (aBlock instanceof BlockStones) return aMetaData < 3;
		return (WD.stone(aBlock, aMetaData) && aBlock.getMaterial() == Material.rock) || aBlock == BlocksGT.RockOres || aBlock == Blocks.sandstone || aBlock == Blocks.stone || aBlock == Blocks.cobblestone || aBlock == Blocks.mossy_cobblestone || aBlock == Blocks.monster_egg;
	}
}
