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

package gregtech6.tileentity.multiblocks;

import static gregapi6.data.CS.*;

import java.util.Collection;
import java.util.List;

import gregapi6.code.ArrayListNoNulls;
import gregapi6.code.TagData;
import gregapi6.data.CS.BlocksGT;
import gregapi6.data.CS.FluidsGT;
import gregapi6.data.IL;
import gregapi6.data.LH;
import gregapi6.data.LH.Chat;
import gregapi6.data.MT;
import gregapi6.data.OP;
import gregapi6.data.TD;
import gregapi6.fluid.FluidTankGT;
import gregapi6.oredict.OreDictMaterial;
import gregapi6.oredict.OreDictMaterialStack;
import gregapi6.tileentity.energy.ITileEntityEnergy;
import gregapi6.tileentity.energy.ITileEntityEnergyDataCapacitor;
import gregapi6.tileentity.multiblocks.IMultiBlockEnergy;
import gregapi6.tileentity.multiblocks.IMultiBlockFluidHandler;
import gregapi6.tileentity.multiblocks.ITileEntityMultiBlockController;
import gregapi6.tileentity.multiblocks.MultiTileEntityMultiBlockPart;
import gregapi6.tileentity.multiblocks.TileEntityBase10MultiBlockBase;
import gregapi6.util.ST;
import gregapi6.util.UT;
import gregapi6.util.WD;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityBedrockDrill extends TileEntityBase10MultiBlockBase implements ITileEntityEnergy, ITileEntityEnergyDataCapacitor, IMultiBlockEnergy, IMultiBlockFluidHandler, IFluidHandler {
	public long mEnergy = 0;
	public int mType = 0;
	public TagData mEnergyTypeAccepted = TD.Energy.RU;
	public FluidTankGT mTank = new FluidTankGT(16000);
	public final List<OreDictMaterial> mList = new ArrayListNoNulls<>();
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		mEnergy = aNBT.getLong(NBT_ENERGY);
		mType = aNBT.getInteger(NBT_VALUE);
		if (aNBT.hasKey(NBT_ENERGY_ACCEPTED)) mEnergyTypeAccepted = TagData.createTagData(aNBT.getString(NBT_ENERGY_ACCEPTED));
		mTank.readFromNBT(aNBT, NBT_TANK+"."+0);
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setNumber(aNBT, NBT_ENERGY, mEnergy);
		UT.NBT.setNumber(aNBT, NBT_VALUE, mType);
		mTank.writeToNBT(aNBT, NBT_TANK+"."+0);
	}
	
	@Override
	public boolean checkStructure2() {
		if (yCoord < 5) return F;
		mList.clear();
		boolean tSuccess = T, tBedrock = T;
		
		for (int i = -1; i < 2; i++) for (int j = -1; j < 2; j++) {
			Block tBlock = getBlockOffset(i, -5, j);
			if (tBlock == BlocksGT.oreBedrock) {
				OreDictMaterialStack tMaterial = BlocksGT.oreBedrock.getMaterialAtSide(worldObj, xCoord+i, yCoord-5, zCoord+j, SIDE_TOP);
				mList.add(tMaterial.mMaterial); mList.add(tMaterial.mMaterial);
			} else if (tBlock == BlocksGT.oreSmallBedrock) {
				OreDictMaterialStack tMaterial = BlocksGT.oreBedrock.getMaterialAtSide(worldObj, xCoord+i, yCoord-5, zCoord+j, SIDE_TOP);
				mList.add(tMaterial.mMaterial);
			} else if (!WD.bedrock(tBlock)) {
				tBedrock = F;
			}
			if (!ITileEntityMultiBlockController.Util.checkAndSetTargetOffset(this, i, -4, j, 18103, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTargetOffset(this, i, -3, j, 18026, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_FLUID_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTargetOffset(this, i, -2, j, 18026, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_FLUID_IN)) tSuccess = F;
			if ((i == 0) != (j == 0)) {
			if (!ITileEntityMultiBlockController.Util.checkAndSetTargetOffset(this, i, -1, j, 18026, getMultiTileEntityRegistryID(), 3, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN)) tSuccess = F;
			} else {
			if (!ITileEntityMultiBlockController.Util.checkAndSetTargetOffset(this, i, -1, j, 18026, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_FLUID_IN)) tSuccess = F;
			}
			if (!ITileEntityMultiBlockController.Util.checkAndSetTargetOffset(this, i,  0, j, 18026, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_FLUID_IN)) tSuccess = F;
		}
		return tSuccess && tBedrock;
	}
	
	static {
		LH.add("gt6.tooltip.multiblock.bedrockdrill.1", "3x3 Base of Bedrock Mining Drill Heads centered ontop Bedrock Ores");
		LH.add("gt6.tooltip.multiblock.bedrockdrill.2", "Full 3x4x3 of Dense Titanium Walls ontop");
		LH.add("gt6.tooltip.multiblock.bedrockdrill.3", "Main top-center inside the Titanium Tower facing upwards");
		LH.add("gt6.tooltip.multiblock.bedrockdrill.4", "Requires Lubricant, Power and a place on Bedrock to mine");
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get(LH.STRUCTURE) + ":");
		aList.add(Chat.WHITE    + LH.get("gt6.tooltip.multiblock.bedrockdrill.1"));
		aList.add(Chat.WHITE    + LH.get("gt6.tooltip.multiblock.bedrockdrill.2"));
		aList.add(Chat.WHITE    + LH.get("gt6.tooltip.multiblock.bedrockdrill.3"));
		aList.add(Chat.WHITE    + LH.get("gt6.tooltip.multiblock.bedrockdrill.4"));
		aList.add(Chat.GREEN    + LH.get(LH.ENERGY_INPUT) + ": " + Chat.WHITE + "1024 to 4096 " + mEnergyTypeAccepted.getChatFormat() + mEnergyTypeAccepted.getLocalisedNameShort() + Chat.WHITE + "/t");
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	@Override
	public boolean isInsideStructure(int aX, int aY, int aZ) {
		return aX >= xCoord - 1 && aY >= yCoord - 5 && aZ >= zCoord - 1 && aX <= xCoord + 1 && aY <= yCoord && aZ <= zCoord + 1;
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		super.onTick2(aTimer, aIsServerSide);
		if (aIsServerSide) {
			if (slotHas(0)) {
				ST.move(delegator(SIDE_TOP), getAdjacentInventory(SIDE_TOP));
			}
			if (mEnergy >= 32768 && !slotHas(0) && checkStructure(F) && mTank.drainAll(100)) {
				mEnergy -= 32768;
				if (rng(1000) == 0) mType = rng(BlocksGT.stones.length+1);
				int tSelector = rng(128);
				if (tSelector < mList.size()) {
					OreDictMaterial tMaterial = (rng(32) == 0 ? UT.Code.select(mList.get(tSelector), mList.get(tSelector).mByProducts) : mList.get(tSelector));
					if (worldObj.provider.dimensionId == DIM_NETHER) {
						slot(0, ST.make((Block)BlocksGT.oreBrokenNetherrack, 1, tMaterial.mID));
// TODO             } else if (WD.dimERE(worldObj)) {
// TODO                 slot(0, ST.make((Block)BlocksGT.oreBroken, 1, tMaterial.mID));
					} else if (WD.dimATUM(worldObj)) {
						slot(0, ST.make((Block)BlocksGT.oreBrokenAtumLimestone, 1, tMaterial.mID));
// TODO             } else if (WD.dimBTL(worldObj)) {
// TODO                 slot(0, ST.make((Block)BlocksGT.oreBroken, 1, tMaterial.mID));
					} else if (mType <= 0 || mType > BlocksGT.stones.length) {
						slot(0, ST.make((Block)BlocksGT.oreBroken, 1, tMaterial.mID));
					} else {
						slot(0, ST.make((Block)BlocksGT.ores_broken[mType-1], 1, tMaterial.mID));
					}
				} else {
					if (rng(1000) == 0) {
						slot(0, OP.dustImpure.mat(MT.Bedrock, 1));
					} else if (worldObj.provider.dimensionId == DIM_NETHER) {
						slot(0, ST.make(Blocks.netherrack, 1, 0));
					} else if (WD.dimERE(worldObj)) {
						slot(0, IL.ERE_Umbercobble.get(1));
					} else if (WD.dimATUM(worldObj)) {
						slot(0, ST.make(BlocksGT.Limestone, 1, 1));
					} else if (WD.dimBTL(worldObj)) {
						slot(0, (mType%2==0?IL.BTL_Pitstone:IL.BTL_Betweenstone).get(1));
					} else if (mType <= 0 || mType > BlocksGT.stones.length) {
						slot(0, ST.make(Blocks.cobblestone, 1, 0));
					} else {
						slot(0, ST.make(BlocksGT.stones[mType-1], 1, 1));
					}
				}
			}
		}
	}
	
	@Override
	public void onMagnifyingGlass2(List<String> aChatReturn) {
		aChatReturn.add(mTank.content("WARNING: NO LUBRICANT!!!"));
	}
	
	@Override public byte getDefaultSide() {return SIDE_UP;}
	@Override public boolean[] getValidSides() {return SIDES_TOP;}
	
	@Override
	public long doInject(TagData aEnergyType, byte aSide, long aSize, long aAmount, boolean aDoInject) {
		if (mEnergy > 40000) return 0;
		aSize = Math.abs(aSize);
		if (!aDoInject) return aAmount;
		if (aSize > getEnergySizeInputMax(aEnergyType, aSide)) {explode(6); return aAmount;}
		mEnergy += aAmount * aSize;
		return aAmount;
	}
	
	@Override public boolean isEnergyType(TagData aEnergyType, byte aSide, boolean aEmitting) {return !aEmitting && aEnergyType == mEnergyTypeAccepted;}
	@Override public boolean isEnergyAcceptingFrom(TagData aEnergyType, byte aSide, boolean aTheoretical) {return isEnergyType(aEnergyType, aSide, F);}
	@Override public boolean isEnergyCapacitorType(TagData aEnergyType, byte aSide) {return aEnergyType == mEnergyTypeAccepted;}
	@Override public long getEnergyDemanded(TagData aEnergyType, byte aSide, long aSize) {return 4096;}
	@Override public long getEnergySizeInputRecommended(TagData aEnergyType, byte aSide) {return 2048;}
	@Override public long getEnergySizeInputMin(TagData aEnergyType, byte aSide) {return 1024;}
	@Override public long getEnergySizeInputMax(TagData aEnergyType, byte aSide) {return 4096;}
	@Override public long getEnergyStored(TagData aEnergyType, byte aSide) {return aEnergyType == mEnergyTypeAccepted ? mEnergy : 0;}
	@Override public long getEnergyCapacity(TagData aEnergyType, byte aSide) {return aEnergyType == mEnergyTypeAccepted ? 40000 : 0;}
	@Override public Collection<TagData> getEnergyTypes(byte aSide) {return mEnergyTypeAccepted.AS_LIST;}
	@Override public Collection<TagData> getEnergyCapacitorTypes(byte aSide) {return mEnergyTypeAccepted.AS_LIST;}
	
	@Override protected IFluidTank getFluidTankFillable(MultiTileEntityMultiBlockPart aPart, byte aSide, FluidStack aFluidToFill) {return FluidsGT.LUBRICANT.contains(aFluidToFill.getFluid().getName()) ? mTank : null;}
	@Override protected IFluidTank getFluidTankDrainable(MultiTileEntityMultiBlockPart aPart, byte aSide, FluidStack aFluidToDrain) {return mTank;}
	@Override protected IFluidTank[] getFluidTanks(MultiTileEntityMultiBlockPart aPart, byte aSide) {return mTank.AS_ARRAY;}
	@Override protected IFluidTank getFluidTankFillable2(byte aSide, FluidStack aFluidToFill) {return FluidsGT.LUBRICANT.contains(aFluidToFill.getFluid().getName()) ? mTank : null;}
	@Override protected IFluidTank getFluidTankDrainable2(byte aSide, FluidStack aFluidToDrain) {return mTank;}
	@Override protected IFluidTank[] getFluidTanks2(byte aSide) {return mTank.AS_ARRAY;}
	
	// Inventory Stuff
	@Override public ItemStack[] getDefaultInventory(NBTTagCompound aNBT) {return new ItemStack[1];}
	@Override public boolean canDrop(int aInventorySlot) {return T;}
	private static final int[] ACCESSIBLE_SLOTS = new int[] {0};
	@Override public int[] getAccessibleSlotsFromSide2(byte aSide) {return ACCESSIBLE_SLOTS;}
	@Override public boolean canInsertItem2 (int aSlot, ItemStack aStack, byte aSide) {return F;}
	@Override public boolean canExtractItem2(int aSlot, ItemStack aStack, byte aSide) {return T;}
	
	@Override public String getTileEntityName() {return "gt6.multitileentity.multiblock.drill.bedrock";}
}
