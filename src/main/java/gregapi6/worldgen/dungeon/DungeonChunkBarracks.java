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

import gregapi6.data.FL;
import gregapi6.fluid.FluidTankGT;
import gregapi6.util.ST;
import gregapi6.util.UT;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author Gregorius Techneticies
 */
public class DungeonChunkBarracks extends DungeonChunkRoomEmpty {
	@Override
	public boolean generate(DungeonData aData) {
		super.generate(aData);

		for (int tX = 1; tX <= 14; tX++) for (int tZ = 1; tZ <= 14; tZ++) if ((tX <= 4 || tX >= 11) && (tZ <= 4 || tZ >= 11)) {
			aData.set(tX, 1, tZ, Blocks.carpet, aData.mColorInversed, 2);
		}
		for (int tY = 1; tY <=  6; tY++) {
			for (int tX = 1; tX <= 14; tX++) if (tX <= 3 || tX >= 12) {
				aData.bricks(tX, tY,  5, aData.mPrimary.mSlabs[SIDE_Z_NEG], aData.mSecondary.mSlabs[SIDE_Z_NEG]);
				aData.bricks(tX, tY, 10, aData.mPrimary.mSlabs[SIDE_Z_POS], aData.mSecondary.mSlabs[SIDE_Z_POS]);
			}
			for (int tZ = 1; tZ <= 14; tZ++) if (tZ <= 3 || tZ >= 12) {
				aData.bricks( 5, tY, tZ, aData.mPrimary.mSlabs[SIDE_X_NEG], aData.mSecondary.mSlabs[SIDE_X_NEG]);
				aData.bricks(10, tY, tZ, aData.mPrimary.mSlabs[SIDE_X_POS], aData.mSecondary.mSlabs[SIDE_X_POS]);
			}

			aData.smooth( 4, tY,  5);
			aData.smooth( 5, tY,  4);
			aData.smooth( 5, tY,  5);
			aData.smooth( 4, tY, 10);
			aData.smooth( 5, tY, 10);
			aData.smooth( 5, tY, 11);
			aData.smooth(10, tY,  4);
			aData.smooth(10, tY,  5);
			aData.smooth(11, tY,  5);
			aData.smooth(10, tY, 10);
			aData.smooth(10, tY, 11);
			aData.smooth(11, tY, 10);

			aData.set( 3, 1,  5, Blocks.iron_door, 1, 2);
			aData.set( 3, 2,  5, Blocks.iron_door, 8, 2);
			aData.set(12, 1,  5, Blocks.iron_door, 1, 2);
			aData.set(12, 2,  5, Blocks.iron_door, 9, 2);
			aData.set( 3, 1, 10, Blocks.iron_door, 3, 2);
			aData.set( 3, 2, 10, Blocks.iron_door, 9, 2);
			aData.set(12, 1, 10, Blocks.iron_door, 3, 2);
			aData.set(12, 2, 10, Blocks.iron_door, 8, 2);
			aData.set( 4, 2,  6, Blocks.stone_button, 3, 2);
			aData.set(11, 2,  6, Blocks.stone_button, 3, 2);
			aData.set( 4, 2,  9, Blocks.stone_button, 4, 2);
			aData.set(11, 2,  9, Blocks.stone_button, 4, 2);
			aData.set( 3, 1,  4, Blocks.stone_pressure_plate, 0, 2);
			aData.set(12, 1,  4, Blocks.stone_pressure_plate, 0, 2);
			aData.set( 3, 1, 11, Blocks.stone_pressure_plate, 0, 2);
			aData.set(12, 1, 11, Blocks.stone_pressure_plate, 0, 2);
			aData.set( 1, 1,  1, Blocks.bed,10, 2);
			aData.set( 1, 1,  2, Blocks.bed, 2, 2);
			aData.set( 1, 1, 13, Blocks.bed, 0, 2);
			aData.set( 1, 1, 14, Blocks.bed, 8, 2);
			aData.set(14, 1,  1, Blocks.bed,10, 2);
			aData.set(14, 1,  2, Blocks.bed, 2, 2);
			aData.set(14, 1, 13, Blocks.bed, 0, 2);
			aData.set(14, 1, 14, Blocks.bed, 8, 2);
			aData.set( 1, 1,  4, Blocks.crafting_table, 0, 2);
			aData.set( 1, 1, 11, Blocks.crafting_table, 0, 2);
			aData.set(14, 1,  4, Blocks.crafting_table, 0, 2);
			aData.set(14, 1, 11, Blocks.crafting_table, 0, 2);
		}

		FluidStack[] tDrinks = FL.array(NF, NF, NF, NF, NF, FL.Purple_Drink.make(250), FL.Purple_Drink.make(250), FL.Purple_Drink.make(250), FL.Vodka.make(250), FL.Mead.make(250), FL.Whiskey_GlenMcKenner.make(250), FL.Wine_Grape_Purple.make(250));

		aData.set( 1, 2,  4, SIDE_UNKNOWN, 32739, new FluidTankGT(UT.Code.select(NF, tDrinks)).writeToNBT(UT.NBT.make(NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), NBT_TANK), T, T);
		aData.set( 1, 2, 11, SIDE_UNKNOWN, 32739, new FluidTankGT(UT.Code.select(NF, tDrinks)).writeToNBT(UT.NBT.make(NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), NBT_TANK), T, T);
		aData.set(14, 2,  4, SIDE_UNKNOWN, 32739, new FluidTankGT(UT.Code.select(NF, tDrinks)).writeToNBT(UT.NBT.make(NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), NBT_TANK), T, T);
		aData.set(14, 2, 11, SIDE_UNKNOWN, 32739, new FluidTankGT(UT.Code.select(NF, tDrinks)).writeToNBT(UT.NBT.make(NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), NBT_TANK), T, T);


		String[] tLoots = new String[] {ChestGenHooks.STRONGHOLD_LIBRARY, ChestGenHooks.STRONGHOLD_CORRIDOR, ChestGenHooks.STRONGHOLD_CROSSING, ChestGenHooks.PYRAMID_DESERT_CHEST, ChestGenHooks.PYRAMID_JUNGLE_CHEST, ChestGenHooks.VILLAGE_BLACKSMITH, ChestGenHooks.MINESHAFT_CORRIDOR, ChestGenHooks.DUNGEON_CHEST, ChestGenHooks.BONUS_CHEST};

		NBTTagList
		tList = new NBTTagList();
		if (!aData.mGeneratedKeys[0]) {aData.mGeneratedKeys[0] = T; tList.appendTag(UT.NBT.makeShort(ST.save(aData.mKeyStacks[0]), "s", (short)aData.mRandom.nextInt(28)));}
		aData.set( 4, 1,  1, SIDE_UNKNOWN,  3010, UT.NBT.make("gt6.dungeonloot"      , UT.Code.select("", tLoots), NBT_FACING, SIDE_Z_POS, NBT_KEY, aData.mKeyIDs[0]), T, T);
		aData.set( 3, 1,  1, SIDE_UNKNOWN,  7110, UT.NBT.make("gt6.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_Z_POS, NBT_INV_LIST, tList), T, T);
		if (aData.mRandom.nextBoolean()) aData.coins( 4, 2,  1);

		tList = new NBTTagList();
		if (!aData.mGeneratedKeys[1]) {aData.mGeneratedKeys[1] = T; tList.appendTag(UT.NBT.makeShort(ST.save(aData.mKeyStacks[1]), "s", (short)aData.mRandom.nextInt(28)));}
		aData.set( 4, 1, 14, SIDE_UNKNOWN,  3010, UT.NBT.make("gt6.dungeonloot"      , UT.Code.select("", tLoots), NBT_FACING, SIDE_Z_NEG, NBT_KEY, aData.mKeyIDs[1]), T, T);
		aData.set( 3, 1, 14, SIDE_UNKNOWN,  7110, UT.NBT.make("gt6.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_Z_NEG, NBT_INV_LIST, tList), T, T);
		if (aData.mRandom.nextBoolean()) aData.coins( 4, 2, 14);

		tList = new NBTTagList();
		if (!aData.mGeneratedKeys[3]) {aData.mGeneratedKeys[3] = T; tList.appendTag(UT.NBT.makeShort(ST.save(aData.mKeyStacks[3]), "s", (short)aData.mRandom.nextInt(28)));}
		aData.set(11, 1,  1, SIDE_UNKNOWN,  3010, UT.NBT.make("gt6.dungeonloot"      , UT.Code.select("", tLoots), NBT_FACING, SIDE_Z_POS, NBT_KEY, aData.mKeyIDs[3]), T, T);
		aData.set(12, 1,  1, SIDE_UNKNOWN,  7110, UT.NBT.make("gt6.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_Z_POS, NBT_INV_LIST, tList), T, T);
		if (aData.mRandom.nextBoolean()) aData.coins(11, 2,  1);

		tList = new NBTTagList();
		if (!aData.mGeneratedKeys[4]) {aData.mGeneratedKeys[4] = T; tList.appendTag(UT.NBT.makeShort(ST.save(aData.mKeyStacks[4]), "s", (short)aData.mRandom.nextInt(28)));}
		aData.set(11, 1, 14, SIDE_UNKNOWN,  3010, UT.NBT.make("gt6.dungeonloot"      , UT.Code.select("", tLoots), NBT_FACING, SIDE_Z_NEG, NBT_KEY, aData.mKeyIDs[4]), T, T);
		aData.set(12, 1, 14, SIDE_UNKNOWN,  7110, UT.NBT.make("gt6.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_Z_NEG, NBT_INV_LIST, tList), T, T);
		if (aData.mRandom.nextBoolean()) aData.coins(11, 2, 14);

		return T;
	}
}
