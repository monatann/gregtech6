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

import static gregapi6.data.CS.*;

import gregapi6.block.MaterialMachines;
import gregapi6.block.misc.BlockBaseMachineUpdate;
import gregapi6.data.LH;
import gregapi6.render.IIconContainer;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class BlockLongDistPipe extends BlockBaseMachineUpdate {
	public final long[] mTemperatures;
	
	public BlockLongDistPipe(String aUnlocalised, IIconContainer[] aIcons, long[] aTemperatures) {
		super(null, aUnlocalised, MaterialMachines.instance, soundTypeMetal, 5, aIcons, ~0);
		mTemperatures = aTemperatures;
		LH.add(aUnlocalised+".0.name" , "Long Distance Item Pipeline");
		for (int i = 1; i < mMaxMeta; i++) LH.add(aUnlocalised+"."+i+".name" , "Long Distance Fluid Pipeline ("+mTemperatures[i]+" K)");
	}
	
	@Override public String getHarvestTool(int aMeta) {return TOOL_wrench;}
	@Override public int getHarvestLevel(int aMeta) {return 3;}
	@Override public boolean isSealable(int aMeta, byte aSide) {return T;}
	@Override public float getBlockHardness(World aWorld, int aX, int aY, int aZ) {return Blocks.iron_block.getBlockHardness(aWorld, aX, aY, aZ);}
	@Override public float getExplosionResistance(Entity aEntity, World aWorld, int aX, int aY, int aZ, double eX, double eY, double eZ) {return 20;}
	@Override public float getExplosionResistance(Entity aEntity) {return 20;}
	@Override public float getExplosionResistance(int aMeta) {return 20;}
}
