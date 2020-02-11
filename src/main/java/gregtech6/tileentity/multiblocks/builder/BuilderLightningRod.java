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

package gregtech6.tileentity.multiblocks.builder;


import java.util.ArrayList;

import gregtech6.items.behaviors.Behavior_MultiblockBuilder;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 */

//to-do
public class BuilderLightningRod {
	public static int sizeX = 3;
	public static int sizeY = 4;
	public static int sizeZ = 3;
	public static int offset = -1;
	private static ArrayList<ItemStack> itemList = new ArrayList<ItemStack>();
	public static int[] blockId = {
		18037,
		18096,
		17998,
		18104
	};
	public static int[] structure = {
			blockId[0], blockId[0], blockId[0],
			blockId[0], blockId[0], blockId[0],
			blockId[0], blockId[0], blockId[0],

			blockId[1], blockId[1], blockId[1],
			blockId[1], blockId[1], blockId[1],
			blockId[1], blockId[1], blockId[1],

			blockId[1], blockId[1], blockId[1],
			blockId[1], blockId[1], blockId[1],
			blockId[1], blockId[1], blockId[1],

			blockId[0], blockId[0], blockId[0],
			blockId[0], blockId[2], blockId[0],
			blockId[0], blockId[0], blockId[0]
	};

	public static void getItemList(EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, boolean north, boolean south, boolean east, boolean west) {
		itemList.clear();
		for(int i=structure.length-1;i>-1;i--) {
			if(structure[i] != -1) {
				itemList.add(Behavior_MultiblockBuilder.getGT6Tile("gt6.multitileentity", structure[i]));
			}else {
				itemList.add(null);
			}
		}

		int x = aX;
		int y = aY + 1;
		int z = aZ;

		int factorX = 1;
		int factorZ = 1;
		int index = 0;
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
						if(xx == 1 && zz == 1 && yy == 0) {
							for(int i=0;i<100;i++) {
								Behavior_MultiblockBuilder.place(aPlayer, Behavior_MultiblockBuilder.getGT6Tile("gt6.multitileentity", blockId[3]), aWorld, x + xx*factorX, y + i + 4, z + zz*factorZ + offset*factorZ, aSide);
							}
						}
						Behavior_MultiblockBuilder.place(aPlayer, itemList.get(index), aWorld, x + xx*factorX, y + yy, z + zz*factorZ + offset*factorZ, aSide);
						index++;
					}
				}
			}
		}else {
			if(north) {
				factorX = -1;
				factorZ = 1;
			}else{
				factorX = 1;
				factorZ = -1;
			}

			for(int yy = 0; yy < sizeY; yy++) {
				for(int zz = 0; zz < sizeZ; zz++) {
					for(int xx = 0; xx < sizeX; xx++) {
						if(xx == 1 && zz == 1 && yy == 0) {
							for(int i=0;i<100;i++) {
								Behavior_MultiblockBuilder.place(aPlayer, Behavior_MultiblockBuilder.getGT6Tile("gt6.multitileentity", blockId[3]), aWorld, x + xx*factorX+ offset*factorX, y + i + 4, z + zz*factorZ, aSide);
							}
						}
						Behavior_MultiblockBuilder.place(aPlayer, itemList.get(index), aWorld, x + xx*factorX + offset*factorX, y + yy, z + zz*factorZ, aSide);
						index++;
					}
				}
			}
		}
	}
}
