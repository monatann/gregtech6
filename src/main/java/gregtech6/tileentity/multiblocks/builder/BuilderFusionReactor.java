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
public class BuilderFusionReactor {
	public static int sizeX = 19;
	public static int sizeY = 5;
	public static int sizeZ = 19;
	public static int offset = 9;

	public static int[] blockId = {
			18200,//Versatile 0
			18201,//12 Logic 1
			18202,//Control 2
			18012,//galva.steel 3
			18299,//vent 4
			18012,//galva.steel 5
			18036,//tung wall 6
			18076,//Ir coil 7
			18035,//stain wall 8
			-1,//air 9
			17198//main
	};

	private static ArrayList<ItemStack> itemList = new ArrayList<ItemStack>();
	private static int[] structure = {
		blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9],
		blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9],
		blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9],
		blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9],
		blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9],
		blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9],
		blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9],
		blockId[9], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[3], blockId[3], blockId[3], blockId[3], blockId[3], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[9],
		blockId[9], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[3], blockId[4], blockId[4], blockId[4], blockId[3], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[9],
		blockId[9], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[3], blockId[4], blockId[4], blockId[4], blockId[3], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[9],
		blockId[9], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[3], blockId[4], blockId[4], blockId[4], blockId[3], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[9],
		blockId[9], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[3], blockId[3], blockId[3], blockId[3], blockId[3], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[9],
		blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9],
		blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9],
		blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9],
		blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9],
		blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9],
		blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9],
		blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9],

		blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9],
		blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9],
		blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[7], blockId[7], blockId[7], blockId[7], blockId[7], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9],
		blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[7], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[7], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9],
		blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[7], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[7], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9],
		blockId[9], blockId[9], blockId[6], blockId[6], blockId[7], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[7], blockId[6], blockId[6], blockId[9], blockId[9],
		blockId[9], blockId[6], blockId[6], blockId[7], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[7], blockId[6], blockId[6], blockId[9],
		blockId[6], blockId[6], blockId[7], blockId[6], blockId[6], blockId[9], blockId[9], blockId[3], blockId[4], blockId[4], blockId[4], blockId[3], blockId[9], blockId[9], blockId[6], blockId[6], blockId[7], blockId[6], blockId[6],
		blockId[6], blockId[6], blockId[7], blockId[6], blockId[6], blockId[9], blockId[9], blockId[4], blockId[1], blockId[2], blockId[1], blockId[4], blockId[9], blockId[9], blockId[6], blockId[6], blockId[7], blockId[6], blockId[6],
		blockId[6], blockId[6], blockId[7], blockId[6], blockId[6], blockId[9], blockId[9], blockId[4], blockId[2], blockId[0], blockId[2], blockId[4], blockId[9], blockId[9], blockId[6], blockId[6], blockId[7], blockId[6], blockId[6],
		blockId[6], blockId[6], blockId[7], blockId[6], blockId[6], blockId[9], blockId[9], blockId[4], blockId[1], blockId[2], blockId[1], blockId[4], blockId[9], blockId[9], blockId[6], blockId[6], blockId[7], blockId[6], blockId[6],
		blockId[6], blockId[6], blockId[7], blockId[6], blockId[6], blockId[9], blockId[9], blockId[3], blockId[4], blockId[4], blockId[4], blockId[3], blockId[9], blockId[9], blockId[6], blockId[6], blockId[7], blockId[6], blockId[6],
		blockId[9], blockId[6], blockId[6], blockId[7], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[7], blockId[6], blockId[6], blockId[9],
		blockId[9], blockId[9], blockId[6], blockId[6], blockId[7], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[7], blockId[6], blockId[6], blockId[9], blockId[9],
		blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[7], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[7], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9],
		blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[7], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[7], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9],
		blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[7], blockId[7], blockId[7], blockId[7], blockId[7], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9],
		blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9],
		blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9],

		blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9],
		blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[7], blockId[7], blockId[7], blockId[7], blockId[7], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9],
		blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[7], blockId[8], blockId[8], blockId[8], blockId[8], blockId[8], blockId[7], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9],
		blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[7], blockId[8], blockId[7], blockId[7], blockId[7], blockId[7], blockId[7], blockId[8], blockId[7], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9],
		blockId[9], blockId[9], blockId[9], blockId[6], blockId[7], blockId[8], blockId[7], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[7], blockId[8], blockId[7], blockId[6], blockId[9], blockId[9], blockId[9],
		blockId[9], blockId[9], blockId[6], blockId[7], blockId[8], blockId[7], blockId[6], blockId[9], blockId[9], blockId[3], blockId[9], blockId[9], blockId[6], blockId[7], blockId[8], blockId[7], blockId[6], blockId[9], blockId[9],
		blockId[9], blockId[6], blockId[7], blockId[8], blockId[7], blockId[6], blockId[9], blockId[9], blockId[9], blockId[3], blockId[9], blockId[9], blockId[9], blockId[6], blockId[7], blockId[8], blockId[7], blockId[6], blockId[9],
		blockId[6], blockId[7], blockId[8], blockId[7], blockId[6], blockId[9], blockId[9], blockId[3], blockId[4], blockId[3], blockId[4], blockId[3], blockId[9], blockId[9], blockId[6], blockId[7], blockId[8], blockId[7], blockId[6],
		blockId[6], blockId[7], blockId[8], blockId[7], blockId[6], blockId[9], blockId[9], blockId[4], blockId[2], blockId[1], blockId[2], blockId[4], blockId[9], blockId[9], blockId[6], blockId[7], blockId[8], blockId[7], blockId[6],
		blockId[6], blockId[7], blockId[8], blockId[7], blockId[6], blockId[3], blockId[3], blockId[3], blockId[1], blockId[0], blockId[1], blockId[3], blockId[3], blockId[3], blockId[6], blockId[7], blockId[8], blockId[7], blockId[6],
		blockId[6], blockId[7], blockId[8], blockId[7], blockId[6], blockId[9], blockId[9], blockId[4], blockId[2], blockId[1], blockId[2], blockId[4], blockId[9], blockId[9], blockId[6], blockId[7], blockId[8], blockId[7], blockId[6],
		blockId[6], blockId[7], blockId[8], blockId[7], blockId[6], blockId[9], blockId[9], blockId[3], blockId[4], blockId[10], blockId[4], blockId[3], blockId[9], blockId[9], blockId[6], blockId[7], blockId[8], blockId[7], blockId[6],
		blockId[9], blockId[6], blockId[7], blockId[8], blockId[7], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[7], blockId[8], blockId[7], blockId[6], blockId[9],
		blockId[9], blockId[9], blockId[6], blockId[7], blockId[8], blockId[7], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[7], blockId[8], blockId[7], blockId[6], blockId[9], blockId[9],
		blockId[9], blockId[9], blockId[9], blockId[6], blockId[7], blockId[8], blockId[7], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[7], blockId[8], blockId[7], blockId[6], blockId[9], blockId[9], blockId[9],
		blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[7], blockId[8], blockId[7], blockId[7], blockId[7], blockId[7], blockId[7], blockId[8], blockId[7], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9],
		blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[7], blockId[8], blockId[8], blockId[8], blockId[8], blockId[8], blockId[7], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9],
		blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[7], blockId[7], blockId[7], blockId[7], blockId[7], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9],
		blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9],
		
		blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9],
		blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9],
		blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[7], blockId[7], blockId[7], blockId[7], blockId[7], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9],
		blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[7], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[7], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9],
		blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[7], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[7], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9],
		blockId[9], blockId[9], blockId[6], blockId[6], blockId[7], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[7], blockId[6], blockId[6], blockId[9], blockId[9],
		blockId[9], blockId[6], blockId[6], blockId[7], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[7], blockId[6], blockId[6], blockId[9],
		blockId[6], blockId[6], blockId[7], blockId[6], blockId[6], blockId[9], blockId[9], blockId[3], blockId[4], blockId[4], blockId[4], blockId[3], blockId[9], blockId[9], blockId[6], blockId[6], blockId[7], blockId[6], blockId[6],
		blockId[6], blockId[6], blockId[7], blockId[6], blockId[6], blockId[9], blockId[9], blockId[4], blockId[1], blockId[2], blockId[1], blockId[4], blockId[9], blockId[9], blockId[6], blockId[6], blockId[7], blockId[6], blockId[6],
		blockId[6], blockId[6], blockId[7], blockId[6], blockId[6], blockId[9], blockId[9], blockId[4], blockId[2], blockId[0], blockId[2], blockId[4], blockId[9], blockId[9], blockId[6], blockId[6], blockId[7], blockId[6], blockId[6],
		blockId[6], blockId[6], blockId[7], blockId[6], blockId[6], blockId[9], blockId[9], blockId[4], blockId[1], blockId[2], blockId[1], blockId[4], blockId[9], blockId[9], blockId[6], blockId[6], blockId[7], blockId[6], blockId[6],
		blockId[6], blockId[6], blockId[7], blockId[6], blockId[6], blockId[9], blockId[9], blockId[3], blockId[4], blockId[4], blockId[4], blockId[3], blockId[9], blockId[9], blockId[6], blockId[6], blockId[7], blockId[6], blockId[6],
		blockId[9], blockId[6], blockId[6], blockId[7], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[7], blockId[6], blockId[6], blockId[9],
		blockId[9], blockId[9], blockId[6], blockId[6], blockId[7], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[7], blockId[6], blockId[6], blockId[9], blockId[9],
		blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[7], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[7], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9],
		blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[7], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[7], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9],
		blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[7], blockId[7], blockId[7], blockId[7], blockId[7], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9],
		blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9],
		blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9],

		blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9],
		blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9],
		blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9],
		blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9],
		blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9],
		blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9],
		blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9],
		blockId[9], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[3], blockId[3], blockId[3], blockId[3], blockId[3], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[9],
		blockId[9], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[3], blockId[4], blockId[4], blockId[4], blockId[3], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[9],
		blockId[9], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[3], blockId[4], blockId[4], blockId[4], blockId[3], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[9],
		blockId[9], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[3], blockId[4], blockId[4], blockId[4], blockId[3], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[9],
		blockId[9], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[3], blockId[3], blockId[3], blockId[3], blockId[3], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[9],
		blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9],
		blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9],
		blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9],
		blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9],
		blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9],
		blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[6], blockId[6], blockId[6], blockId[6], blockId[6], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9],
		blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9], blockId[9],
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
