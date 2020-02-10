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

package gregtech6.items.behaviors;

import static gregapi6.data.CS.*;

import java.util.ArrayList;
import java.util.List;

import gregapi6.block.multitileentity.MultiTileEntityRegistry;
import gregapi6.code.ArrayListNoNulls;
import gregapi6.data.CS.SFX;
import gregapi6.data.LH;
import gregapi6.data.TD;
import gregapi6.item.multiitem.MultiItem;
import gregapi6.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi6.util.UT;
import gregapi6.util.WD;
import gregtech6.tileentity.multiblocks.builder.BuilderCokeOven;
import gregtech6.tileentity.multiblocks.builder.BuilderFusionReactor;
import gregtech6.tileentity.multiblocks.builder.BuilderLargeCircuit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class Behavior_MultiblockBuilder extends AbstractBehaviorDefault {
	public final int mScanLevel;

	public Behavior_MultiblockBuilder(int aScanLevel) {
		mScanLevel = aScanLevel;
	}

	@Override
	public boolean onItemUseFirst(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float hitX, float hitY, float hitZ) {
		if (aPlayer instanceof EntityPlayerMP) {
			ArrayList<String> tList = new ArrayListNoNulls<>();
			if (aItem.useEnergy(TD.Energy.EU, aStack, WD.scan(tList, aPlayer, aWorld, mScanLevel, aX, aY, aZ, aSide, hitX, hitY, hitZ), aPlayer, aPlayer.inventory, aWorld, aX, aY, aZ, T)) {
				//UT.Entities.sendchat(aPlayer, tList, F);
				setMultiblock(aItem, aStack, aPlayer, aWorld, aX, aY, aZ, aSide, hitX, hitY, hitZ);
			}
			return T;
		}
		UT.Sounds.play(SFX.IC_SCANNER, 20, 1.0F, aX, aY, aZ);
		return aPlayer instanceof EntityPlayerMP;
	}

	@Override
	public boolean onLeftClickEntity(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, Entity aEntity) {
		if (mScanLevel > 100) {
			//UT.Entities.sendchat(aPlayer, aEntity.getClass().getName());
			UT.Sounds.play(SFX.IC_SCANNER, 20, 1.0F, aEntity);
		}
		return T;
	}

	@Override
	public boolean onRightClickEntity(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, Entity aEntity) {
		if (mScanLevel > 100) {
			//UT.Entities.sendchat(aPlayer, aEntity.getClass().getName());
			UT.Sounds.play(SFX.IC_SCANNER, 20, 1.0F, aEntity);
		}
		return T;
	}

	static {
		LH.add("gt6.behaviour.multiblockbuilder", "Place blocks on the world.");
	}

	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		aList.add(LH.get("gt6.behaviour.multiblockbuilder"));
		return aList;
	}

	private static ArrayList<String> errorPos = new ArrayList<String>();

	public static void setMultiblock(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float hitX, float hitY, float hitZ) {
		errorPos.clear();
		/*
		 * https://www.minecraftforge.net/forum/topic/6425-get-player-facing-direction/
		0 = North
		1 = North East
		2 = East
		3 = South East
		4 = South
		5 = South West
		6 = West
		7 = North West
		*/
		//World world = aWorld;

		int yaw = (int) aPlayer.rotationYaw;
		if (yaw<0) {
			yaw+=360;
		}
		yaw+=22;    //centers coordinates you may want to drop this line
		yaw%=360;  //and this one if you want a strict interpretation of the zones
		int facing = yaw/45;  //  360degrees divided by 45 == 8 zones

		boolean north = false;
		boolean east = false;
		boolean south = false;
		boolean west = false;

		if(facing == 0 || facing == 7) { //North
			north = true;
		}else if(facing == 1 || facing == 2) { //East
			east = true;
		}else if(facing == 3 || facing == 4) { //South
			south = true;
		}else { //West
			west = true;
		}

		NBTTagCompound nbt = new NBTTagCompound();
		//nbt.setString("scan", "CokeOven");
		nbt.setInteger("scan", 18304);
		aStack.setTagCompound(nbt);
		if(aStack.getTagCompound().hasKey("scan")) {
			int recipe = aStack.getTagCompound().getInteger("scan");

			ArrayList<ItemStack> itemList = new ArrayList<ItemStack>();
			int sizeX = 0;
			int sizeY = 0;
			int sizeZ = 0;
			int offset = 0;

			switch(recipe) {
			case 0:
				itemList = BuilderCokeOven.getItemList();
				sizeX = BuilderCokeOven.sizeX;
				sizeY = BuilderCokeOven.sizeY;
				sizeZ = BuilderCokeOven.sizeZ;
				offset = BuilderCokeOven.offset;
				break;
			case 2:
				itemList = BuilderFusionReactor.getItemList();
				sizeX = BuilderFusionReactor.sizeX;
				sizeY = BuilderFusionReactor.sizeY;
				sizeZ = BuilderFusionReactor.sizeZ;
				offset = BuilderFusionReactor.offset;
				break;
			case 18304:
				itemList = BuilderLargeCircuit.getItemList();
				sizeX = BuilderLargeCircuit.sizeX;
				sizeY = BuilderLargeCircuit.sizeY;
				sizeZ = BuilderLargeCircuit.sizeZ;
				offset = BuilderLargeCircuit.offset;
				break;
			}
			//if(recipe.equals("CokeOven")) {
				int x = aX;
				int y = aY + 1;
				int z = aZ;

				int factorX = 1;
				int factorZ = 1;
				int index = 0;
				offset = -1 * offset;
				if(west || east) {
					int temp = sizeX;
					sizeX = sizeZ;
					sizeZ = temp;
					if(west){
						factorX = 1;
						factorZ = -1;
					}else {
						factorX = -1;
						factorZ = 1;
					}
					for(int yy = 0; yy < sizeY; yy++) {
						for(int xx = 0; xx < sizeX; xx++) {
							for(int zz = 0; zz < sizeZ; zz++) {
								place(aPlayer, itemList.get(index), aWorld, x + xx*factorX, y + yy, z + zz*factorZ + offset*factorZ, aSide);
								index++;
							}
						}
					}
				}else {
					if(north) {
						factorX = 1;
						factorZ = 1;
					}else{
						factorX = -1;
						factorZ = -1;
					}

					for(int yy = 0; yy < sizeY; yy++) {
						for(int zz = 0; zz < sizeZ; zz++) {
							for(int xx = 0; xx < sizeX; xx++) {
								place(aPlayer, itemList.get(index), aWorld, x + xx*factorX + offset*factorX, y + yy, z + zz*factorZ, aSide);
								index++;
							}
						}
					}
				}
			//}
		}

		UT.Entities.sendchat(aPlayer, errorPos, F);
	}

	public static ItemStack[] inventory;

	public static void place(EntityPlayer aPlayer, ItemStack item, World aWorld, int x, int y, int z, byte aSide) {
		/*
		if(item == null) {
			return;
		}
		*/
		EntityPlayerMP player = (EntityPlayerMP) aPlayer;

		if(inventory == null) {
			inventory = new ItemStack[((List<Slot>) aPlayer.openContainer.inventorySlots).size()];
		}
		for(int i=0;i<((List<Slot>) aPlayer.openContainer.inventorySlots).size();i++) {
			inventory[i] = ((List<Slot>) aPlayer.openContainer.inventorySlots).get(i).getStack();
		}

		if(aWorld.isAirBlock(x, y, z)) {
			try {
				for(int i=0;i<inventory.length;i++) {
					if(inventory[i] != null) {
						if(inventory[i].isItemEqual(item)) {
							System.out.println(inventory[i].stackSize);
							boolean success = inventory[i].tryPlaceItemIntoWorld(aPlayer, aWorld, x, y, z, aSide, 0, 0, 0);
							if(success) {
								if(inventory[i].stackSize != 1) {
									((List<Slot>) aPlayer.openContainer.inventorySlots).get(i).putStack(inventory[i].splitStack(inventory[i].stackSize));
									player.sendContainerAndContentsToPlayer(player.openContainer, player.openContainer.getInventory());
									System.out.println("Server: Placed " + inventory[i].getDisplayName() + " on (" + x + "," + y + "," + z + ")");
									errorPos.add("Placed " + inventory[i].getDisplayName() + " on (" + x + "," + y + "," + z + ")");
									break;
								}else {
									((List<Slot>) aPlayer.openContainer.inventorySlots).get(i).putStack(null);
									errorPos.add("Placed " + inventory[i].getDisplayName() + " on (" + x + "," + y + "," + z + ")");
									break;
								}
							}else {
								errorPos.add("Can't place the block on (" + x + "," + y + "," + z + ") by something error.");
							}
						}
					}
					if(inventory.length == i + 1) {
						errorPos.add("You don't have " + item.getDisplayName() + ", need place on (" + x + "," + y + "," + z + ")");
					}
				}
			}catch(Exception e) {}
		}else {
			errorPos.add("Can't place the block on (" + x + "," + y + "," + z + "), there is (" + aWorld.getBlock(x, y, z).getLocalizedName() + ")");
		}
	}

	public static ItemStack getGT6Tile(String id, int damage) {
		if(damage != -1) {
			return MultiTileEntityRegistry.getRegistry(id).getItem(damage);
		}else {
			return new ItemStack(Blocks.air);
		}
		//return MultiTileEntityRegistry.getRegistry(id).getItem(damage);
	}
}
