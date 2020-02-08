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

package gregtech6.tileentity.inventories;

import static gregapi6.data.CS.*;

import java.util.List;

import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_AddToolTips;
import gregapi6.code.ArrayListNoNulls;
import gregapi6.data.CS.GarbageGT;
import gregapi6.data.FL;
import gregapi6.data.LH;
import gregapi6.data.LH.Chat;
import gregapi6.fluid.FluidTankGT;
import gregapi6.old.Textures;
import gregapi6.render.BlockTextureDefault;
import gregapi6.render.BlockTextureMulti;
import gregapi6.render.IIconContainer;
import gregapi6.render.ITexture;
import gregapi6.tileentity.ITileEntityTapAccessible;
import gregapi6.tileentity.base.TileEntityBase07Paintable;
import gregapi6.tileentity.delegate.DelegatorTileEntity;
import gregapi6.util.ST;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityEnderGarbageDump extends TileEntityBase07Paintable implements IFluidHandler, ITileEntityTapAccessible, IMTE_AddToolTips {
	static {
		LH.add("gt6.multitileentity.ender.garbage.dump.tooltip.1", "This is where all the Trash Bin Items & Fluids go to");
		LH.add("gt6.multitileentity.ender.garbage.dump.tooltip.2", "Accesses the Garbage Dimension to retrieve Trash.");
		LH.add("gt6.multitileentity.ender.garbage.dump.tooltip.3", "If used properly, people could for example 'donate' Resources.");
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN + LH.get("gt6.multitileentity.ender.garbage.dump.tooltip.1"));
		aList.add(Chat.CYAN + LH.get("gt6.multitileentity.ender.garbage.dump.tooltip.2"));
		aList.add(Chat.CYAN + LH.get("gt6.multitileentity.ender.garbage.dump.tooltip.3"));
		aList.add(Chat.RED + LH.get(LH.ADMIN_ONLY_CREATION));
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		super.onTick2(aTimer, aIsServerSide);
		if (aIsServerSide) {
			DelegatorTileEntity<TileEntity> tDelegate = getAdjacentTileEntity(SIDE_BOTTOM, T, F);
			if (!(tDelegate.mTileEntity instanceof MultiTileEntityEnderGarbageBin)) {
				if (!GarbageGT.GARBAGE_ITEMS.isEmpty()) ST.move(delegator(SIDE_BOTTOM), tDelegate);
				if (!GarbageGT.GARBAGE_FLUIDS.isEmpty()) if (tDelegate.mTileEntity instanceof IFluidHandler) FL.move_(GarbageGT.GARBAGE_FLUIDS, tDelegate);
			}
		}
	}
	
	@Override
	protected IFluidTank getFluidTankFillable2(byte aSide, FluidStack aFluidToFill) {
		return null;
	}
	
	@Override
	protected IFluidTank getFluidTankDrainable2(byte aSide, FluidStack aFluidToDrain) {
		if (aFluidToDrain == null) {
			for (int i = 0; i < GarbageGT.GARBAGE_FLUIDS.size(); i++) if (GarbageGT.GARBAGE_FLUIDS.get(i).has()) return GarbageGT.GARBAGE_FLUIDS.get(i);
		} else {
			for (int i = 0; i < GarbageGT.GARBAGE_FLUIDS.size(); i++) if (GarbageGT.GARBAGE_FLUIDS.get(i).contains(aFluidToDrain)) return GarbageGT.GARBAGE_FLUIDS.get(i);
		}
		return null;
	}
	
	@Override
	protected IFluidTank[] getFluidTanks2(byte aSide) {
		return GarbageGT.GARBAGE_FLUIDS.isEmpty() ? new IFluidTank[] {new FluidTankGT(Integer.MAX_VALUE)} : GarbageGT.GARBAGE_FLUIDS.toArray(new IFluidTank[GarbageGT.GARBAGE_FLUIDS.size()]);
	}
	
	@Override
	public ArrayListNoNulls<ItemStack> getDrops(int aFortune, boolean aSilkTouch) {
		return new ArrayListNoNulls<>();
	}
	
	@Override
	public ItemStack decrStackSize(int aSlot, int aDecrement) {
		ItemStack tStack = GarbageGT.GARBAGE_ITEMS.get(aSlot);
		if (tStack == null) return null;
		aDecrement = Math.min(tStack.stackSize, aDecrement);
		if (aDecrement <= 0) return null;
		ItemStack rStack = ST.amount(aDecrement, tStack);
		tStack.stackSize -= aDecrement;
		updateInventory();
		return rStack;
	}
	
	@Override
	public ItemStack getStackInSlot(int aSlot) {
		return GarbageGT.GARBAGE_ITEMS.get(aSlot);
	}
	
	@Override
	public int getSizeInventory() {
		return GarbageGT.GARBAGE_ITEMS.size();
	}
	
	@Override
	public void setInventorySlotContents(int aSlot, ItemStack aStack) {
		GarbageGT.GARBAGE_ITEMS.get(aSlot).stackSize = (aStack == null ? 0 : aStack.stackSize);
		updateInventory();
	}
	
	@Override public boolean canInsertItem2(int aSlot, ItemStack aStack, byte aSide) {return F;}
	@Override public boolean canExtractItem2(int aSlot, ItemStack aStack, byte aSide) {return GarbageGT.GARBAGE_ITEMS.get(aSlot).stackSize > 0;}
	
	@Override
	public FluidStack tapDrain(byte aSide, int aMaxDrain, boolean aDoDrain) {
		for (FluidTankGT tTank : GarbageGT.GARBAGE_FLUIDS) if (tTank.has() && !FL.gas(tTank)) return tTank.drain(aMaxDrain, aDoDrain);
		for (FluidTankGT tTank : GarbageGT.GARBAGE_FLUIDS) if (tTank.has()) return tTank.drain(aMaxDrain, aDoDrain);
		return NF;
	}
	
	@Override
	public FluidStack nozzleDrain(byte aSide, int aMaxDrain, boolean aDoDrain) {
		for (FluidTankGT tTank : GarbageGT.GARBAGE_FLUIDS) if (tTank.has() &&  FL.gas(tTank)) return tTank.drain(aMaxDrain, aDoDrain);
		for (FluidTankGT tTank : GarbageGT.GARBAGE_FLUIDS) if (tTank.has()) return tTank.drain(aMaxDrain, aDoDrain);
		return NF;
	}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		return aShouldSideBeRendered[aSide] ? BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[FACES_TBS[aSide]], mRGBa), BlockTextureDefault.get(sOverlays[FACES_TBS[aSide]])) : null;
	}
	
	// Icons
	public static IIconContainer sColoreds[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/endergarbage/dump/colored/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/endergarbage/dump/colored/top"),
		new Textures.BlockIcons.CustomIcon("machines/endergarbage/dump/colored/side"),
	}, sOverlays[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/endergarbage/dump/overlay/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/endergarbage/dump/overlay/top"),
		new Textures.BlockIcons.CustomIcon("machines/endergarbage/dump/overlay/side"),
	};
	
	@Override public String getTileEntityName() {return "gt6.multitileentity.ender.garbage.dump";}
	
	@Override public int getInventoryStackLimit() {return Integer.MAX_VALUE;}
	@Override public ItemStack[] getDefaultInventory(NBTTagCompound aNBT) {return ZL_IS;}
	@Override public boolean canDrop(int aInventorySlot) {return F;}
}
