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
import gregapi6.data.CS.SFX;
import gregapi6.data.LH;
import gregapi6.data.MT;
import gregapi6.data.TD;
import gregapi6.item.multiitem.MultiItem;
import gregapi6.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi6.util.UT;
import gregtech6.tileentity.multiblocks.builder.BuilderAutoclave;
import gregtech6.tileentity.multiblocks.builder.BuilderBath;
import gregtech6.tileentity.multiblocks.builder.BuilderBedrockDrill;
import gregtech6.tileentity.multiblocks.builder.BuilderBoiler;
import gregtech6.tileentity.multiblocks.builder.BuilderCentrifuge;
import gregtech6.tileentity.multiblocks.builder.BuilderCoagulator;
import gregtech6.tileentity.multiblocks.builder.BuilderCokeOven;
import gregtech6.tileentity.multiblocks.builder.BuilderCrackingTower;
import gregtech6.tileentity.multiblocks.builder.BuilderCrucible;
import gregtech6.tileentity.multiblocks.builder.BuilderCrusher;
import gregtech6.tileentity.multiblocks.builder.BuilderCryoDistillationTower;
import gregtech6.tileentity.multiblocks.builder.BuilderCrystallisationRoom;
import gregtech6.tileentity.multiblocks.builder.BuilderDistillationTower;
import gregtech6.tileentity.multiblocks.builder.BuilderElectricCokeOven;
import gregtech6.tileentity.multiblocks.builder.BuilderElectrolyzer;
import gregtech6.tileentity.multiblocks.builder.BuilderExtruder;
import gregtech6.tileentity.multiblocks.builder.BuilderFermenter;
import gregtech6.tileentity.multiblocks.builder.BuilderFusionReactor;
import gregtech6.tileentity.multiblocks.builder.BuilderImplosionCompressor;
import gregtech6.tileentity.multiblocks.builder.BuilderLargeCircuit;
import gregtech6.tileentity.multiblocks.builder.BuilderLargeCompressor;
import gregtech6.tileentity.multiblocks.builder.BuilderLargeDynamo;
import gregtech6.tileentity.multiblocks.builder.BuilderLargeHeatExchanger;
import gregtech6.tileentity.multiblocks.builder.BuilderLargeSluice;
import gregtech6.tileentity.multiblocks.builder.BuilderLargeTurbine;
import gregtech6.tileentity.multiblocks.builder.BuilderLightningRod;
import gregtech6.tileentity.multiblocks.builder.BuilderLogistics;
import gregtech6.tileentity.multiblocks.builder.BuilderMatterFabricator;
import gregtech6.tileentity.multiblocks.builder.BuilderMixer;
import gregtech6.tileentity.multiblocks.builder.BuilderOven;
import gregtech6.tileentity.multiblocks.builder.BuilderShredder;
import gregtech6.tileentity.multiblocks.builder.BuilderSqueezer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Behavior_MultiblockBuilder extends AbstractBehaviorDefault {
	public final int mScanLevel;

	public Behavior_MultiblockBuilder(int aScanLevel) {
		mScanLevel = aScanLevel;
	}

	@Override
	public boolean onItemUseFirst(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float hitX, float hitY, float hitZ) {
		if (aPlayer instanceof EntityPlayerMP) {
			if (aItem.useEnergy(TD.Energy.EU, aStack, 1000, aPlayer, aPlayer.inventory, aWorld, aX, aY, aZ, T)) {
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
		LH.add("gt6.behaviour.multiblockbuilder", "Place blocks on the world");
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

		if(aStack.getTagCompound().hasKey("scan")) {
			int recipe = aStack.getTagCompound().getInteger("scan");

			ArrayList<ItemStack> itemList = new ArrayList<ItemStack>();
			int sizeX = 0;
			int sizeY = 0;
			int sizeZ = 0;
			int offset = 0;

			if(recipe == 17112) {
				itemList = BuilderAutoclave.getItemList();
				sizeX = BuilderAutoclave.sizeX;
				sizeY = BuilderAutoclave.sizeY;
				sizeZ = BuilderAutoclave.sizeZ;
				offset = BuilderAutoclave.offset;
			}else if(recipe == 17104) {
				itemList = BuilderBath.getItemList();
				sizeX = BuilderBath.sizeX;
				sizeY = BuilderBath.sizeY;
				sizeZ = BuilderBath.sizeZ;
				offset = BuilderBath.offset;
			}else if(recipe == 17999) {
				itemList = BuilderBedrockDrill.getItemList();
				sizeX = BuilderBedrockDrill.sizeX;
				sizeY = BuilderBedrockDrill.sizeY;
				sizeZ = BuilderBedrockDrill.sizeZ;
				offset = BuilderBedrockDrill.offset;
			}else if(recipe >= 17290 && recipe < 17290 + MT.DATA.Heat_T.length) {
				itemList = BuilderBoiler.getItemList(recipe);
				sizeX = BuilderBoiler.sizeX;
				sizeY = BuilderBoiler.sizeY;
				sizeZ = BuilderBoiler.sizeZ;
				offset = BuilderBoiler.offset;
			}else if(recipe == 17100) {
				itemList = BuilderCentrifuge.getItemList();
				sizeX = BuilderCentrifuge.sizeX;
				sizeY = BuilderCentrifuge.sizeY;
				sizeZ = BuilderCentrifuge.sizeZ;
				offset = BuilderCentrifuge.offset;
			}else if(recipe == 17105) {
				itemList = BuilderCentrifuge.getItemList();
				sizeX = BuilderCoagulator.sizeX;
				sizeY = BuilderCoagulator.sizeY;
				sizeZ = BuilderCoagulator.sizeZ;
				offset = BuilderCoagulator.offset;
			}else if(recipe == 17000) {
				itemList = BuilderCokeOven.getItemList();
				sizeX = BuilderCokeOven.sizeX;
				sizeY = BuilderCokeOven.sizeY;
				sizeZ = BuilderCokeOven.sizeZ;
				offset = BuilderCokeOven.offset;
			}else if(recipe == 17099) {
				itemList = BuilderCrackingTower.getItemList();
				sizeX = BuilderCrackingTower.sizeX;
				sizeY = BuilderCrackingTower.sizeY;
				sizeZ = BuilderCrackingTower.sizeZ;
				offset = BuilderCrackingTower.offset;
			}else if(recipe >= 17800 && recipe < 17800 + MT.DATA.Crucible_T.length -1) {
				itemList = BuilderCrucible.getItemList(recipe);
				sizeX = BuilderCrucible.sizeX;
				sizeY = BuilderCrucible.sizeY;
				sizeZ = BuilderCrucible.sizeZ;
				offset = BuilderCrucible.offset;
			}else if(recipe == 17108) {
				itemList = BuilderCrusher.getItemList();
				sizeX = BuilderCrusher.sizeX;
				sizeY = BuilderCrusher.sizeY;
				sizeZ = BuilderCrusher.sizeZ;
				offset = BuilderCrusher.offset;
			}else if(recipe == 17111) {
				itemList = BuilderCryoDistillationTower.getItemList();
				sizeX = BuilderCryoDistillationTower.sizeX;
				sizeY = BuilderCryoDistillationTower.sizeY;
				sizeZ = BuilderCryoDistillationTower.sizeZ;
				offset = BuilderCryoDistillationTower.offset;
			}else if(recipe == 18301) {
				itemList = BuilderCrystallisationRoom.getItemList();
				sizeX = BuilderCrystallisationRoom.sizeX;
				sizeY = BuilderCrystallisationRoom.sizeY;
				sizeZ = BuilderCrystallisationRoom.sizeZ;
				offset = BuilderCrystallisationRoom.offset;
			}else if(recipe == 17101) {
				itemList = BuilderDistillationTower.getItemList();
				sizeX = BuilderDistillationTower.sizeX;
				sizeY = BuilderDistillationTower.sizeY;
				sizeZ = BuilderDistillationTower.sizeZ;
				offset = BuilderDistillationTower.offset;
			}else if(recipe == 17106) {
				itemList = BuilderElectricCokeOven.getItemList();
				sizeX = BuilderElectricCokeOven.sizeX;
				sizeY = BuilderElectricCokeOven.sizeY;
				sizeZ = BuilderElectricCokeOven.sizeZ;
				offset = BuilderElectricCokeOven.offset;
			}else if(recipe == 18103) {
				itemList = BuilderElectrolyzer.getItemList();
				sizeX = BuilderElectrolyzer.sizeX;
				sizeY = BuilderElectrolyzer.sizeY;
				sizeZ = BuilderElectrolyzer.sizeZ;
				offset = BuilderElectrolyzer.offset;
			}else if(recipe == 18302) {
				itemList = BuilderExtruder.getItemList();
				sizeX = BuilderExtruder.sizeX;
				sizeY = BuilderExtruder.sizeY;
				sizeZ = BuilderExtruder.sizeZ;
				offset = BuilderExtruder.offset;
			}else if(recipe == 18101) {
				itemList = BuilderFermenter .getItemList();
				sizeX = BuilderFermenter .sizeX;
				sizeY = BuilderFermenter .sizeY;
				sizeZ = BuilderFermenter .sizeZ;
				offset = BuilderFermenter .offset;
			}else if(recipe == 17198) {
				itemList = BuilderFusionReactor.getItemList();
				sizeX = BuilderFusionReactor.sizeX;
				sizeY = BuilderFusionReactor.sizeY;
				sizeZ = BuilderFusionReactor.sizeZ;
				offset = BuilderFusionReactor.offset;
			}else if(recipe == 17100) {
				itemList = BuilderImplosionCompressor.getItemList();
				sizeX = BuilderImplosionCompressor.sizeX;
				sizeY = BuilderImplosionCompressor.sizeY;
				sizeZ = BuilderImplosionCompressor.sizeZ;
				offset = BuilderImplosionCompressor.offset;
			}else if(recipe == 18304) {
				itemList = BuilderLargeCircuit.getItemList();
				sizeX = BuilderLargeCircuit.sizeX;
				sizeY = BuilderLargeCircuit.sizeY;
				sizeZ = BuilderLargeCircuit.sizeZ;
				offset = BuilderLargeCircuit.offset;
			}else if(recipe == 18303) {
				itemList = BuilderLargeCompressor.getItemList();
				sizeX = BuilderLargeCompressor.sizeX;
				sizeY = BuilderLargeCompressor.sizeY;
				sizeZ = BuilderLargeCompressor.sizeZ;
				offset = BuilderLargeCompressor.offset;
			}else if(recipe >= 17260 && recipe < 17260 + MT.DATA.Heat_T.length -1) {
				itemList = BuilderLargeDynamo.getItemList(recipe);
				sizeX = BuilderLargeDynamo.sizeX;
				sizeY = BuilderLargeDynamo.sizeY;
				sizeZ = BuilderLargeDynamo.sizeZ;
				offset = BuilderLargeDynamo.offset;
			}else if(recipe == 17197) {
				itemList = BuilderLargeHeatExchanger.getItemList();
				sizeX = BuilderLargeHeatExchanger.sizeX;
				sizeY = BuilderLargeHeatExchanger.sizeY;
				sizeZ = BuilderLargeHeatExchanger.sizeZ;
				offset = BuilderLargeHeatExchanger.offset;
			}else if(recipe == 17107) {
				itemList = BuilderLargeSluice.getItemList();
				sizeX = BuilderLargeSluice.sizeX;
				sizeY = BuilderLargeSluice.sizeY;
				sizeZ = BuilderLargeSluice.sizeZ;
				offset = BuilderLargeSluice.offset;
			}else if(recipe >= 17230 && recipe < 17230 + MT.DATA.Heat_T.length -1) {
				itemList = BuilderLargeTurbine.getItemList(recipe);
				sizeX = BuilderLargeTurbine.sizeX;
				sizeY = BuilderLargeTurbine.sizeY;
				sizeZ = BuilderLargeTurbine.sizeZ;
				offset = BuilderLargeTurbine.offset;
			}else if(recipe == 17998) {
				BuilderLightningRod.getItemList(aPlayer, aWorld, aX, aY, aZ, aSide, north, south, east, west);
				return;
			}else if(recipe == 17997) {
				itemList = BuilderLogistics.getItemList();
				sizeX = BuilderLogistics.sizeX;
				sizeY = BuilderLogistics.sizeY;
				sizeZ = BuilderLogistics.sizeZ;
				offset = BuilderLogistics.offset;
			}else if(recipe == 17199) {
				itemList = BuilderMatterFabricator.getItemList();
				sizeX = BuilderMatterFabricator.sizeX;
				sizeY = BuilderMatterFabricator.sizeY;
				sizeZ = BuilderMatterFabricator.sizeZ;
				offset = BuilderMatterFabricator.offset;
			}else if(recipe == 17102) {
				itemList = BuilderMixer.getItemList();
				sizeX = BuilderMixer.sizeX;
				sizeY = BuilderMixer.sizeY;
				sizeZ = BuilderMixer.sizeZ;
				offset = BuilderMixer.offset;
			}else if(recipe == 17106) {
				itemList = BuilderOven.getItemList();
				sizeX = BuilderOven.sizeX;
				sizeY = BuilderOven.sizeY;
				sizeZ = BuilderOven.sizeZ;
				offset = BuilderOven.offset;
			}else if(recipe == 17109) {
				itemList = BuilderShredder.getItemList();
				sizeX = BuilderShredder.sizeX;
				sizeY = BuilderShredder.sizeY;
				sizeZ = BuilderShredder.sizeZ;
				offset = BuilderShredder.offset;
			}else if(recipe == 17114) {
				itemList = BuilderSqueezer.getItemList();
				sizeX = BuilderSqueezer.sizeX;
				sizeY = BuilderSqueezer.sizeY;
				sizeZ = BuilderSqueezer.sizeZ;
				offset = BuilderSqueezer.offset;
			}

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
		}

		UT.Entities.sendchat(aPlayer, errorPos, F);
	}

	public static ItemStack[] inventory;

	public static void place(EntityPlayer aPlayer, ItemStack item, World aWorld, int x, int y, int z, byte aSide) {
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
