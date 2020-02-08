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
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */

//to-do
public class BuilderCrusher {
	public static int sizeX = 5;
	public static int sizeY = 3;
	public static int sizeZ = 5;
	public static int offset = 2;
	private static ArrayList<ItemStack> itemList = new ArrayList<ItemStack>();
	public static int[] blockId = {
		18103,
		18107,
		17108
	};
	public static int[] structure = {
			blockId[0], blockId[0], blockId[0], blockId[0], blockId[0],
			blockId[0], blockId[1], blockId[1], blockId[1], blockId[0],
			blockId[0], blockId[1], blockId[1], blockId[1], blockId[0],
			blockId[0], blockId[1], blockId[1], blockId[1], blockId[0],
			blockId[0], blockId[1], blockId[1], blockId[1], blockId[0],
			blockId[0], blockId[0], blockId[0], blockId[0], blockId[0],
			
			
			blockId[0], blockId[0], blockId[0], blockId[0], blockId[0],
			blockId[0], blockId[1], blockId[1], blockId[1], blockId[0],
			blockId[0], blockId[1], blockId[1], blockId[1], blockId[0],
			blockId[0], blockId[1], blockId[1], blockId[1], blockId[0],
			blockId[0], blockId[0], blockId[0], blockId[0], blockId[0],
			
			blockId[0], blockId[0], blockId[0], blockId[0], blockId[0],
			blockId[0], blockId[0], blockId[0], blockId[0], blockId[0],
			blockId[0], blockId[0], blockId[0], blockId[0], blockId[0],
			blockId[0], blockId[0], blockId[0], blockId[0], blockId[0],
			blockId[0], blockId[0], blockId[2], blockId[0], blockId[0],
	};
	
	public static ArrayList<ItemStack> getItemList() {
		itemList.clear();
		for(int i=structure.length-1;i>-1;i--) {
			if(structure[i] != -1) {
				itemList.add(Behavior_MultiblockBuilder.getGT6Tile("gt6.multitileentity", structure[i]));
			}else {
				itemList.add(null);
			}
		}
		return itemList;
	}
}
