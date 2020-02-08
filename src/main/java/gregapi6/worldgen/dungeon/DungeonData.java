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

package gregapi6.worldgen.dungeon;

import static gregapi6.data.CS.*;

import java.util.Random;

import gregapi6.block.IBlockPlacable;
import gregapi6.block.metatype.BlockStones;
import gregapi6.block.multitileentity.MultiTileEntityRegistry;
import gregapi6.code.HashSetNoNulls;
import gregapi6.code.TagData;
import gregapi6.data.CS.BlocksGT;
import gregapi6.data.FL;
import gregapi6.fluid.FluidTankGT;
import gregapi6.random.WorldAndCoords;
import gregapi6.util.UT;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFlowerPot;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.fluids.Fluid;

/**
 * @author Gregorius Techneticies
 */
public class DungeonData extends WorldAndCoords {
	public final MultiTileEntityRegistry mMTERegistryGT;
	public final BlockStones mPrimary, mSecondary;
	public final byte mColor, mColorInversed, mRoomLayout[][];
	public final int mRoomX, mRoomZ, mConnectionCount;
	public final long mKeyIDs[];
	public final ItemStack mKeyStacks[];
	public final boolean mGeneratedKeys[];
	public final HashSetNoNulls<ChunkCoordinates> mLightUpdateCoords;
	public final HashSetNoNulls<TagData> mTags;
	public final WorldgenDungeonGT mStructure;
	public final NBTTagCompound mCoin;
	public final Random mRandom;
	
	public DungeonData(World aWorld, int aX, int aY, int aZ, WorldgenDungeonGT aStructure, BlockStones aPrimaryBlock, BlockStones aSecondaryBlock, MultiTileEntityRegistry aRegistry, HashSetNoNulls<ChunkCoordinates> aLightUpdateCoords, HashSetNoNulls<TagData> aTags, long[] aKeyIDs, ItemStack[] aKeyStacks, boolean[] aGeneratedKeys, byte[][] aRoomLayout, int aRoomX, int aRoomZ, int aConnectionCount, int aColor, Random aRandom, NBTTagCompound aCoin) {
		super(aWorld, aX, aY, aZ);
		mStructure = aStructure;
		mPrimary = aPrimaryBlock;
		mSecondary = aSecondaryBlock;
		mMTERegistryGT = aRegistry;
		mRoomLayout = aRoomLayout;
		mRoomX = aRoomX;
		mRoomZ = aRoomZ;
		mConnectionCount = aConnectionCount;
		mKeyIDs = aKeyIDs;
		mKeyStacks = aKeyStacks;
		mGeneratedKeys = aGeneratedKeys;
		mLightUpdateCoords = aLightUpdateCoords;
		mTags = aTags;
		mColor = UT.Code.bind4(aColor);
		mColorInversed = UT.Code.bind4(15-aColor);
		mCoin = aCoin;
		mRandom = aRandom;
	}
	
	public boolean bricks     (int aX, int aY, int aZ, Block aPrimary, Block aSecondary) {return set(aX, aY, aZ, aY == 2 ? aSecondary : aPrimary, 3+mRandom.nextInt(3), 2);}
	public boolean brick      (int aX, int aY, int aZ, Block aPrimary, Block aSecondary) {return set(aX, aY, aZ, aY == 2 ? aSecondary : aPrimary, BlockStones.BRICK, 2);}
	public boolean redstoned  (int aX, int aY, int aZ, Block aPrimary, Block aSecondary) {return set(aX, aY, aZ, aY == 2 ? aSecondary : aPrimary, BlockStones.RSTBR, 3);}
	public boolean cracked    (int aX, int aY, int aZ, Block aPrimary, Block aSecondary) {return set(aX, aY, aZ, aY == 2 ? aSecondary : aPrimary, BlockStones.CRACK, 2);}
	public boolean mossy      (int aX, int aY, int aZ, Block aPrimary, Block aSecondary) {return set(aX, aY, aZ, aY == 2 ? aSecondary : aPrimary, BlockStones.MBRIK, 2);}
	public boolean chiseled   (int aX, int aY, int aZ, Block aPrimary, Block aSecondary) {return set(aX, aY, aZ, aY == 2 ? aSecondary : aPrimary, BlockStones.CHISL, 2);}
	public boolean tiles      (int aX, int aY, int aZ, Block aPrimary, Block aSecondary) {return set(aX, aY, aZ, aY == 2 ? aSecondary : aPrimary, BlockStones.TILES, 2);}
	public boolean smalltiles (int aX, int aY, int aZ, Block aPrimary, Block aSecondary) {return set(aX, aY, aZ, aY == 2 ? aSecondary : aPrimary, BlockStones.STILE, 2);}
	public boolean smallbricks(int aX, int aY, int aZ, Block aPrimary, Block aSecondary) {return set(aX, aY, aZ, aY == 2 ? aSecondary : aPrimary, BlockStones.SBRIK, 2);}
	public boolean smooth     (int aX, int aY, int aZ, Block aPrimary, Block aSecondary) {return set(aX, aY, aZ, aY == 2 ? aSecondary : aPrimary, BlockStones.SMOTH, 2);}
	public boolean setAirBlock(int aX, int aY, int aZ, Block aPrimary, Block aSecondary) {return set(aX, aY, aZ, NB, 0, 2);}
	
	public boolean bricks     (int aX, int aY, int aZ) {return set(aX, aY, aZ, aY == 2 ? mSecondary : mPrimary, 3+mRandom.nextInt(3), 2);}
	public boolean brick      (int aX, int aY, int aZ) {return set(aX, aY, aZ, aY == 2 ? mSecondary : mPrimary, BlockStones.BRICK, 2);}
	public boolean redstoned  (int aX, int aY, int aZ) {return set(aX, aY, aZ, aY == 2 ? mSecondary : mPrimary, BlockStones.RSTBR, 3);}
	public boolean cracked    (int aX, int aY, int aZ) {return set(aX, aY, aZ, aY == 2 ? mSecondary : mPrimary, BlockStones.CRACK, 2);}
	public boolean mossy      (int aX, int aY, int aZ) {return set(aX, aY, aZ, aY == 2 ? mSecondary : mPrimary, BlockStones.MBRIK, 2);}
	public boolean chiseled   (int aX, int aY, int aZ) {return set(aX, aY, aZ, aY == 2 ? mSecondary : mPrimary, BlockStones.CHISL, 2);}
	public boolean tiles      (int aX, int aY, int aZ) {return set(aX, aY, aZ, aY == 2 ? mSecondary : mPrimary, BlockStones.TILES, 2);}
	public boolean smalltiles (int aX, int aY, int aZ) {return set(aX, aY, aZ, aY == 2 ? mSecondary : mPrimary, BlockStones.STILE, 2);}
	public boolean smallbricks(int aX, int aY, int aZ) {return set(aX, aY, aZ, aY == 2 ? mSecondary : mPrimary, BlockStones.SBRIK, 2);}
	public boolean smooth     (int aX, int aY, int aZ) {return set(aX, aY, aZ, aY == 2 ? mSecondary : mPrimary, BlockStones.SMOTH, 2);}
	public boolean air        (int aX, int aY, int aZ) {return set(aX, aY, aZ, NB, 0, 2);}
	
	public boolean glass      (int aX, int aY, int aZ) {return set(aX, aY, aZ, BlocksGT.Glass, mColor, 2);}
	public boolean glassglow  (int aX, int aY, int aZ) {return set(aX, aY, aZ, BlocksGT.GlowGlass, mColor, 2);}
	public boolean colored    (int aX, int aY, int aZ) {return set(aX, aY, aZ, BlocksGT.Concrete, mColor, 2);}
	
	public boolean lamp(int aX, int aY, int aZ, Block aPrimary, Block aSecondary, int aGenerateRedstoneBrick) {
		mLightUpdateCoords.add(new ChunkCoordinates(mX+aX, mY+aY, mZ+aZ));
		if (aGenerateRedstoneBrick != 0) redstoned(aX, aY+aGenerateRedstoneBrick, aZ);
		return set(aX, aY, aZ, aGenerateRedstoneBrick == 0 ? Blocks.redstone_lamp : Blocks.lit_redstone_lamp, 0, 2);
	}
	
	public boolean lamp(int aX, int aY, int aZ, int aGenerateRedstoneBrick) {
		mLightUpdateCoords.add(new ChunkCoordinates(mX+aX, mY+aY, mZ+aZ));
		if (aGenerateRedstoneBrick != 0) redstoned(aX, aY+aGenerateRedstoneBrick, aZ);
		return set(aX, aY, aZ, aGenerateRedstoneBrick == 0 ? Blocks.redstone_lamp : Blocks.lit_redstone_lamp, 0, 2);
	}
	
	public boolean coins(int aX, int aY, int aZ) {
		for (int i = 0; i < 16; i++) mCoin.setByte("gt6.coin.stacksize."+i, (byte)(mRandom.nextInt(3) == 0 ? mRandom.nextInt(8) : 0));
		mCoin.setByte("gt6.coin.stacksize."+mRandom.nextInt(16), (byte)(1+mRandom.nextInt(8)));
		return mMTERegistryGT.mBlock.placeBlock(mWorld, mX+aX, mY+aY, mZ+aZ, SIDE_UNKNOWN, (short)32700, mCoin, T, T);
	}
	
	public boolean set(int aX, int aY, int aZ, long aMeta) {
		return mMTERegistryGT.mBlock.placeBlock(mWorld, mX+aX, mY+aY, mZ+aZ, SIDE_UNKNOWN, (short)aMeta, null, T, T);
	}
	public boolean set(int aX, int aY, int aZ, byte aSide, long aMeta) {
		return mMTERegistryGT.mBlock.placeBlock(mWorld, mX+aX, mY+aY, mZ+aZ, aSide, (short)aMeta, null, T, T);
	}
	public boolean set(int aX, int aY, int aZ, long aMeta, boolean aCauseBlockUpdates, boolean aForcePlacement) {
		return mMTERegistryGT.mBlock.placeBlock(mWorld, mX+aX, mY+aY, mZ+aZ, SIDE_UNKNOWN, (short)aMeta, null, aCauseBlockUpdates, aForcePlacement);
	}
	public boolean set(int aX, int aY, int aZ, byte aSide, long aMeta, boolean aCauseBlockUpdates, boolean aForcePlacement) {
		return mMTERegistryGT.mBlock.placeBlock(mWorld, mX+aX, mY+aY, mZ+aZ, aSide, (short)aMeta, null, aCauseBlockUpdates, aForcePlacement);
	}
	public boolean set(int aX, int aY, int aZ, long aMeta, NBTTagCompound aNBT) {
		return mMTERegistryGT.mBlock.placeBlock(mWorld, mX+aX, mY+aY, mZ+aZ, SIDE_UNKNOWN, (short)aMeta, aNBT, T, T);
	}
	public boolean set(int aX, int aY, int aZ, byte aSide, long aMeta, NBTTagCompound aNBT) {
		return mMTERegistryGT.mBlock.placeBlock(mWorld, mX+aX, mY+aY, mZ+aZ, aSide, (short)aMeta, aNBT, T, T);
	}
	public boolean set(int aX, int aY, int aZ, long aMeta, NBTTagCompound aNBT, boolean aCauseBlockUpdates, boolean aForcePlacement) {
		return mMTERegistryGT.mBlock.placeBlock(mWorld, mX+aX, mY+aY, mZ+aZ, SIDE_UNKNOWN, (short)aMeta, aNBT, aCauseBlockUpdates, aForcePlacement);
	}
	public boolean set(int aX, int aY, int aZ, byte aSide, long aMeta, NBTTagCompound aNBT, boolean aCauseBlockUpdates, boolean aForcePlacement) {
		return mMTERegistryGT.mBlock.placeBlock(mWorld, mX+aX, mY+aY, mZ+aZ, aSide, (short)aMeta, aNBT, aCauseBlockUpdates, aForcePlacement);
	}
	
	public boolean set(IBlockPlacable aBlock, int aX, int aY, int aZ, long aMeta) {
		return aBlock.placeBlock(mWorld, mX+aX, mY+aY, mZ+aZ, SIDE_UNKNOWN, (short)aMeta, null, T, T);
	}
	public boolean set(IBlockPlacable aBlock, int aX, int aY, int aZ, byte aSide, long aMeta) {
		return aBlock.placeBlock(mWorld, mX+aX, mY+aY, mZ+aZ, aSide, (short)aMeta, null, T, T);
	}
	public boolean set(IBlockPlacable aBlock, int aX, int aY, int aZ, long aMeta, boolean aCauseBlockUpdates, boolean aForcePlacement) {
		return aBlock.placeBlock(mWorld, mX+aX, mY+aY, mZ+aZ, SIDE_UNKNOWN, (short)aMeta, null, aCauseBlockUpdates, aForcePlacement);
	}
	public boolean set(IBlockPlacable aBlock, int aX, int aY, int aZ, byte aSide, long aMeta, boolean aCauseBlockUpdates, boolean aForcePlacement) {
		return aBlock.placeBlock(mWorld, mX+aX, mY+aY, mZ+aZ, aSide, (short)aMeta, null, aCauseBlockUpdates, aForcePlacement);
	}
	public boolean set(IBlockPlacable aBlock, int aX, int aY, int aZ, long aMeta, NBTTagCompound aNBT) {
		return aBlock.placeBlock(mWorld, mX+aX, mY+aY, mZ+aZ, SIDE_UNKNOWN, (short)aMeta, aNBT, T, T);
	}
	public boolean set(IBlockPlacable aBlock, int aX, int aY, int aZ, byte aSide, long aMeta, NBTTagCompound aNBT) {
		return aBlock.placeBlock(mWorld, mX+aX, mY+aY, mZ+aZ, aSide, (short)aMeta, aNBT, T, T);
	}
	public boolean set(IBlockPlacable aBlock, int aX, int aY, int aZ, long aMeta, NBTTagCompound aNBT, boolean aCauseBlockUpdates, boolean aForcePlacement) {
		return aBlock.placeBlock(mWorld, mX+aX, mY+aY, mZ+aZ, SIDE_UNKNOWN, (short)aMeta, aNBT, aCauseBlockUpdates, aForcePlacement);
	}
	public boolean set(IBlockPlacable aBlock, int aX, int aY, int aZ, byte aSide, long aMeta, NBTTagCompound aNBT, boolean aCauseBlockUpdates, boolean aForcePlacement) {
		return aBlock.placeBlock(mWorld, mX+aX, mY+aY, mZ+aZ, aSide, (short)aMeta, aNBT, aCauseBlockUpdates, aForcePlacement);
	}
	
	public boolean flower(int aX, int aY, int aZ) {
		int tIndex = mRandom.nextInt(BlocksGT.FLOWER_TILES.length);
		return set(aX, aY, aZ, BlocksGT.FLOWER_TILES[tIndex], BlocksGT.FLOWER_METAS[tIndex], 2);
	}
	
	public boolean pot(int aX, int aY, int aZ) {
		int tIndex = mRandom.nextInt(BlocksGT.POT_FLOWER_TILES.length);
		set(aX, aY, aZ, Blocks.flower_pot, 0, 2);
		TileEntity tTileEntity = mWorld.getTileEntity(mX+aX, mY+aY, mZ+aZ);
		if (tTileEntity instanceof TileEntityFlowerPot) ((TileEntityFlowerPot)tTileEntity).func_145964_a(Item.getItemFromBlock(BlocksGT.POT_FLOWER_TILES[tIndex]), BlocksGT.POT_FLOWER_METAS[tIndex]);
		return T;
	}
	
	public boolean shelf(int aX, int aY, int aZ, long aMeta, byte aFacing) {
		return set(aX, aY, aZ, aMeta, UT.NBT.make(NBT_FACING, aFacing));
	}
	public boolean shelf(int aX, int aY, int aZ, long aMeta, byte aFacing, String aLootFront) {
		return set(aX, aY, aZ, aMeta, UT.NBT.make(NBT_FACING, aFacing, "gt6.dungeonloot.front", aLootFront));
	}
	public boolean shelf(int aX, int aY, int aZ, long aMeta, byte aFacing, String aLootFront, NBTTagList aInventory) {
		return set(aX, aY, aZ, aMeta, UT.NBT.make(NBT_FACING, aFacing, "gt6.dungeonloot.front", aLootFront, NBT_INV_LIST, aInventory));
	}
	public boolean shelf(int aX, int aY, int aZ, long aMeta, byte aFacing, String aLootFront, String aLootBack) {
		return set(aX, aY, aZ, aMeta, UT.NBT.make(NBT_FACING, aFacing, "gt6.dungeonloot.front", aLootFront, "gt6.dungeonloot.back", aLootBack));
	}
	public boolean shelf(int aX, int aY, int aZ, long aMeta, byte aFacing, String aLootFront, String aLootBack, NBTTagList aInventory) {
		return set(aX, aY, aZ, aMeta, UT.NBT.make(NBT_FACING, aFacing, "gt6.dungeonloot.front", aLootFront, "gt6.dungeonloot.back", aLootBack, NBT_INV_LIST, aInventory));
	}
	public boolean shelf(int aX, int aY, int aZ, long aMeta, byte aFacing, String[] aLootFront) {
		return set(aX, aY, aZ, aMeta, UT.NBT.make(NBT_FACING, aFacing, "gt6.dungeonloot.front", UT.Code.select(ChestGenHooks.STRONGHOLD_LIBRARY, aLootFront)));
	}
	public boolean shelf(int aX, int aY, int aZ, long aMeta, byte aFacing, String[] aLootFront, NBTTagList aInventory) {
		return set(aX, aY, aZ, aMeta, UT.NBT.make(NBT_FACING, aFacing, "gt6.dungeonloot.front", UT.Code.select(ChestGenHooks.STRONGHOLD_LIBRARY, aLootFront), NBT_INV_LIST, aInventory));
	}
	public boolean shelf(int aX, int aY, int aZ, long aMeta, byte aFacing, String[] aLootFront, String[] aLootBack) {
		return set(aX, aY, aZ, aMeta, UT.NBT.make(NBT_FACING, aFacing, "gt6.dungeonloot.front", UT.Code.select(ChestGenHooks.STRONGHOLD_LIBRARY, aLootFront), "gt6.dungeonloot.back", UT.Code.select(ChestGenHooks.STRONGHOLD_LIBRARY, aLootBack)));
	}
	public boolean shelf(int aX, int aY, int aZ, long aMeta, byte aFacing, String[] aLootFront, String[] aLootBack, NBTTagList aInventory) {
		return set(aX, aY, aZ, aMeta, UT.NBT.make(NBT_FACING, aFacing, "gt6.dungeonloot.front", UT.Code.select(ChestGenHooks.STRONGHOLD_LIBRARY, aLootFront), "gt6.dungeonloot.back", UT.Code.select(ChestGenHooks.STRONGHOLD_LIBRARY, aLootBack), NBT_INV_LIST, aInventory));
	}
	
	public boolean zpm(int aX, int aY, int aZ) {
		return mStructure.mZPM && set(aX, aY, aZ, 14999, UT.NBT.make(NBT_ACTIVE_ENERGY, mRandom.nextBoolean()));
	}
	public boolean zpm(int aX, int aY, int aZ, boolean aActive) {
		return mStructure.mZPM && set(aX, aY, aZ, 14999, UT.NBT.make(NBT_ACTIVE_ENERGY, aActive));
	}
	
	public boolean cup(int aX, int aY, int aZ, FL aFluid) {
		return set(aX, aY, aZ, 32739, FluidTankGT.writeToNBT(UT.NBT.make(NBT_COLOR, DYES_INT[mColor], NBT_PAINTED, T), NBT_TANK, aFluid.make(250)));
	}
	public boolean cup(int aX, int aY, int aZ, Fluid aFluid) {
		return set(aX, aY, aZ, 32739, FluidTankGT.writeToNBT(UT.NBT.make(NBT_COLOR, DYES_INT[mColor], NBT_PAINTED, T), NBT_TANK, FL.make(aFluid, 250)));
	}
	
	public boolean set(int aX, int aY, int aZ, Block aBlock) {
		return mWorld.setBlock(mX+aX, mY+aY, mZ+aZ, aBlock, 0, 2);
	}
	public boolean set(int aX, int aY, int aZ, Block aBlock, int aMeta) {
		return mWorld.setBlock(mX+aX, mY+aY, mZ+aZ, aBlock, aMeta, 2);
	}
	public boolean set(int aX, int aY, int aZ, Block aBlock, int aMeta, int aFlags) {
		return mWorld.setBlock(mX+aX, mY+aY, mZ+aZ, aBlock, aMeta, aFlags);
	}
	public boolean set(int aX, int aY, int aZ, Block aBlock, int aMeta, int aFlags, int aRotationCount) {
		if (!set(aX, aY, aZ, aBlock, aMeta, aFlags)) return F;
		while (aRotationCount-->0) aBlock.rotateBlock(mWorld, mX+aX, mY+aY, mZ+aZ, FORGE_DIR[SIDE_Y_POS]);
		return T;
	}
}
