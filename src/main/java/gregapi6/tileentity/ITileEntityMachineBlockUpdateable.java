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

import static gregapi6.data.CS.*;

import java.util.HashMap;
import java.util.Map;

import gregapi6.code.HashSetNoNulls;
import gregapi6.random.IHasWorldAndCoords;
import gregapi6.util.WD;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 */
public interface ITileEntityMachineBlockUpdateable {
	/** Called whenever a Machine Block adjacent to this Block got updated. The Coords are the Coords of the Block that changed. */
	public void onMachineBlockUpdate(ChunkCoordinates aCoords, Block aBlock, byte aMeta, boolean aRemoved);
	
	/** Utility for the Multi-Block-Updates */
	public static class Util {
		/** The List of Blocks, which can conduct Machine Block Updates */
		public static final Map<Block, Integer> MACHINE_BLOCKS = new HashMap<>();
		
		/**
		 * Causes a Machineblock Update
		 * This update will cause surrounding MultiBlock Machines to update their Configuration.
		 * You should call this Function in @Block.breakBlock and in @Block.onBlockAdded of your Machine.
		 */
		public static boolean causeMachineUpdate(IHasWorldAndCoords aTileEntity, boolean aRemoved) {
			if (aTileEntity.isServerSide()) new Thread(new MachineBlockUpdateRunnable(aTileEntity.getWorld(), aTileEntity.getCoords(), aTileEntity.getBlockOffset(0, 0, 0), aTileEntity.getMetaDataOffset(0, 0, 0), aRemoved), "Machine Block Updating").start();
			return T;
		}
		/**
		 * Causes a Machineblock Update
		 * This update will cause surrounding MultiBlock Machines to update their Configuration.
		 * You should call this Function in @Block.breakBlock and in @Block.onBlockAdded of your Machine.
		 */
		public static boolean causeMachineUpdate(World aWorld, int aX, int aY, int aZ, Block aBlock, byte aMeta, boolean aRemoved) {
			if (!aWorld.isRemote) new Thread(new MachineBlockUpdateRunnable(aWorld, new ChunkCoordinates(aX, aY, aZ), aBlock, aMeta, aRemoved), "Machine Block Updating").start();
			return T;
		}
		/**
		 * Causes a Machineblock Update
		 * This update will cause surrounding MultiBlock Machines to update their Configuration.
		 * You should call this Function in @Block.breakBlock and in @Block.onBlockAdded of your Machine.
		 */
		public static boolean causeMachineUpdate(World aWorld, ChunkCoordinates aCoords, Block aBlock, byte aMeta, boolean aRemoved) {
			if (!aWorld.isRemote) new Thread(new MachineBlockUpdateRunnable(aWorld, aCoords, aBlock, aMeta, aRemoved), "Machine Block Updating").start();
			return T;
		}
		
		
		/**
		 * Adds a Multi-Machine Block, like my Machine Casings for example.
		 * You should call @causeMachineUpdate in @Block.breakBlock and in @Block.onBlockAdded of your registered Block.
		 * You don't need to register TileEntities which implement @IMachineBlockUpdateable
		 * @param aMeta the Metadata of the Blocks as Bitmask! -1 or ~0 for all Metavalues
		 */
		public static boolean registerMachineBlock(Block aBlock, int aMeta) {
			if (aBlock == null) return F;
			if (COMPAT_TC != null) COMPAT_TC.registerPortholeBlacklistedBlock(aBlock);
			MACHINE_BLOCKS.put(aBlock, aMeta);
			return T;
		}
		/**
		 * Like above but with boolean Parameters instead of a BitMask
		 */
		public static boolean registerMachineBlock(Block aBlock, boolean... aMeta) {
			if (aBlock == null || aMeta == null || aMeta.length == 0) return F;
			if (COMPAT_TC != null) COMPAT_TC.registerPortholeBlacklistedBlock(aBlock);
			int rMeta = 0;
			for (byte i = 0; i < aMeta.length && i < 16; i++) if (aMeta[i]) rMeta |= B[i];
			MACHINE_BLOCKS.put(aBlock, rMeta);
			return T;
		}
		
		/**
		 * if this Block is a Machine Update Conducting Block
		 */
		public static boolean isMachineBlock(Block aBlock, int aMeta) {
			if (aBlock == Blocks.air) return F;
			Integer tNumber = MACHINE_BLOCKS.get(aBlock);
			return tNumber != null && (tNumber & B[aMeta]) != 0;
		}
		
		private static class MachineBlockUpdateRunnable implements Runnable {
			private final ChunkCoordinates mCoords;
			private final World mWorld;
			private final Block mBlock;
			private final byte mMeta;
			private final boolean mRemoved;
			
			public MachineBlockUpdateRunnable(World aWorld, ChunkCoordinates aCoords, Block aBlock, byte aMeta, boolean aRemoved) {
				mWorld = aWorld; mCoords = aCoords; mBlock = aBlock; mMeta = aMeta; mRemoved = aRemoved;
			}
			
			@Override
			public void run() {
				try {stepToUpdateMachine(mWorld, mCoords, new HashSetNoNulls<>(F, mCoords));} catch(Throwable e) {/**/}
			}
			
			private void stepToUpdateMachine(World aWorld, ChunkCoordinates aCoords, HashSetNoNulls<ChunkCoordinates> aSet) {
				TileEntity tTileEntity = WD.te(aWorld, aCoords, T);
				if (tTileEntity != null && tTileEntity instanceof ITileEntityMachineBlockUpdateable) ((ITileEntityMachineBlockUpdateable)tTileEntity).onMachineBlockUpdate(mCoords, mBlock, mMeta, mRemoved);
				if (aSet.size() < 5 || (tTileEntity != null && tTileEntity instanceof ITileEntityMachineBlockUpdateable) || isMachineBlock(aWorld.getBlock(aCoords.posX, aCoords.posY, aCoords.posZ), aWorld.getBlockMetadata(aCoords.posX, aCoords.posY, aCoords.posZ))) {
					ChunkCoordinates tCoords;
					if (aSet.add(tCoords = new ChunkCoordinates(aCoords.posX + 1, aCoords.posY, aCoords.posZ))) stepToUpdateMachine(aWorld, tCoords, aSet);
					if (aSet.add(tCoords = new ChunkCoordinates(aCoords.posX - 1, aCoords.posY, aCoords.posZ))) stepToUpdateMachine(aWorld, tCoords, aSet);
					if (aSet.add(tCoords = new ChunkCoordinates(aCoords.posX, aCoords.posY + 1, aCoords.posZ))) stepToUpdateMachine(aWorld, tCoords, aSet);
					if (aSet.add(tCoords = new ChunkCoordinates(aCoords.posX, aCoords.posY - 1, aCoords.posZ))) stepToUpdateMachine(aWorld, tCoords, aSet);
					if (aSet.add(tCoords = new ChunkCoordinates(aCoords.posX, aCoords.posY, aCoords.posZ + 1))) stepToUpdateMachine(aWorld, tCoords, aSet);
					if (aSet.add(tCoords = new ChunkCoordinates(aCoords.posX, aCoords.posY, aCoords.posZ - 1))) stepToUpdateMachine(aWorld, tCoords, aSet);
				}
			}
		}
	}
}
