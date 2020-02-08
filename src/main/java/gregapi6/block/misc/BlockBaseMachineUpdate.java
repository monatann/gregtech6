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

package gregapi6.block.misc;

import static gregapi6.data.CS.*;

import gregapi6.block.BlockBaseMeta;
import gregapi6.render.IIconContainer;
import gregapi6.tileentity.ITileEntityMachineBlockUpdateable;
import gregapi6.util.UT;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 */
public abstract class BlockBaseMachineUpdate extends BlockBaseMeta {
	public BlockBaseMachineUpdate(Class<? extends ItemBlock> aItemClass, String aNameInternal, Material aMaterial, SoundType aSoundType, long aMaxMeta, IIconContainer[] aIcons, int aBitMask) {
		super(aItemClass, aNameInternal, aMaterial, aSoundType, aMaxMeta, aIcons);
		ITileEntityMachineBlockUpdateable.Util.registerMachineBlock(this, aBitMask);
	}
	
	@Override public void onBlockAdded2(World aWorld, int aX, int aY, int aZ)                           {if (ITileEntityMachineBlockUpdateable.Util.isMachineBlock(this, aWorld.getBlockMetadata(aX, aY, aZ)    )) ITileEntityMachineBlockUpdateable.Util.causeMachineUpdate(aWorld, aX, aY, aZ, this, UT.Code.bind4(aWorld.getBlockMetadata(aX, aY, aZ)), F);}
	@Override public void breakBlock(World aWorld, int aX, int aY, int aZ, Block aBlock, int aMetaData) {if (ITileEntityMachineBlockUpdateable.Util.isMachineBlock(this, aMetaData                              )) ITileEntityMachineBlockUpdateable.Util.causeMachineUpdate(aWorld, aX, aY, aZ, this, UT.Code.bind4(aMetaData), T);}
	@Override public int getMobilityFlag() {return 2;}
}
