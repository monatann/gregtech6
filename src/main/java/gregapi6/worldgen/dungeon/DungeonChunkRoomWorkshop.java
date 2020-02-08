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

import gregapi6.code.ArrayListNoNulls;
import gregapi6.data.FL;
import gregapi6.data.IL;
import gregapi6.data.MT;
import gregapi6.data.OP;
import gregapi6.fluid.FluidTankGT;
import gregapi6.util.ST;
import gregapi6.util.UT;
import gregtech6.tileentity.tools.MultiTileEntityMold;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author Gregorius Techneticies
 */
public class DungeonChunkRoomWorkshop extends DungeonChunkRoomEmpty {
	@Override
	public boolean generate(DungeonData aData) {
		super.generate(aData);
		aData.set   ( 5, 1,  1, SIDE_UNKNOWN, 32055, new FluidTankGT(FL.Propane.make(8000)).writeToNBT(UT.NBT.make(NBT_COLOR, DYE_INT_Red, NBT_PAINTED, T), NBT_TANK), T, T);

		aData.set   ( 3, 1,  1, SIDE_UNKNOWN,    11, UT.NBT.make(NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T, NBT_FACING, SIDE_Z_POS, "gt6.dungeonloot", ChestGenHooks.MINESHAFT_CORRIDOR      ), T, T);

		aData.set   ( 2, 1,  1, SIDE_UNKNOWN,    11, UT.NBT.make(NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T, NBT_FACING, SIDE_Z_POS, "gt6.dungeonloot", ChestGenHooks.STRONGHOLD_CROSSING     ), T, T);

		aData.set   ( 1, 1,  1, Blocks.crafting_table, 0, 2);
		aData.set   ( 1, 2,  1, SIDE_UNKNOWN, 32735, UT.NBT.make(), T, T);

		aData.set   ( 1, 1,  2, SIDE_UNKNOWN,    11, UT.NBT.make(NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T, NBT_FACING, SIDE_X_POS, "gt6.dungeonloot", ChestGenHooks.DUNGEON_CHEST           ), T, T);


		NBTTagList
		tInventory = new NBTTagList();
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.stick          .mat(MT.StainlessSteel  , 32+aData.mRandom.nextInt(33))), "s", (short)     aData.mRandom.nextInt(36) ));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.ingot          .mat(MT.StainlessSteel  , 32+aData.mRandom.nextInt(33))), "s", (short)     aData.mRandom.nextInt(36) ));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.plate          .mat(MT.StainlessSteel  , 32+aData.mRandom.nextInt(33))), "s", (short)     aData.mRandom.nextInt(36) ));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.plateCurved    .mat(MT.StainlessSteel  , 16+aData.mRandom.nextInt(49))), "s", (short)     aData.mRandom.nextInt(36) ));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.screw          .mat(MT.StainlessSteel  , 16+aData.mRandom.nextInt(49))), "s", (short)     aData.mRandom.nextInt(36) ));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.ring           .mat(MT.StainlessSteel  ,  8+aData.mRandom.nextInt(25))), "s", (short)     aData.mRandom.nextInt(36) ));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.gearGt         .mat(MT.StainlessSteel  ,  1+aData.mRandom.nextInt( 4))), "s", (short)     aData.mRandom.nextInt(36) ));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.gearGtSmall    .mat(MT.StainlessSteel  ,  8+aData.mRandom.nextInt(25))), "s", (short)     aData.mRandom.nextInt(36) ));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.stick          .mat(MT.Bronze          , 32+aData.mRandom.nextInt(33))), "s", (short)( 36+aData.mRandom.nextInt(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.ingot          .mat(MT.Bronze          , 32+aData.mRandom.nextInt(33))), "s", (short)( 36+aData.mRandom.nextInt(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.plate          .mat(MT.Bronze          , 32+aData.mRandom.nextInt(33))), "s", (short)( 36+aData.mRandom.nextInt(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.plateCurved    .mat(MT.Bronze          , 16+aData.mRandom.nextInt(49))), "s", (short)( 36+aData.mRandom.nextInt(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.screw          .mat(MT.Bronze          , 16+aData.mRandom.nextInt(49))), "s", (short)( 36+aData.mRandom.nextInt(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.ring           .mat(MT.Bronze          ,  8+aData.mRandom.nextInt(25))), "s", (short)( 36+aData.mRandom.nextInt(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.gearGt         .mat(MT.Bronze          ,  1+aData.mRandom.nextInt( 4))), "s", (short)( 36+aData.mRandom.nextInt(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.gearGtSmall    .mat(MT.Bronze          ,  8+aData.mRandom.nextInt(25))), "s", (short)( 36+aData.mRandom.nextInt(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.stick          .mat(MT.Invar           , 32+aData.mRandom.nextInt(33))), "s", (short)( 72+aData.mRandom.nextInt(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.ingot          .mat(MT.Invar           , 32+aData.mRandom.nextInt(33))), "s", (short)( 72+aData.mRandom.nextInt(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.plate          .mat(MT.Invar           , 32+aData.mRandom.nextInt(33))), "s", (short)( 72+aData.mRandom.nextInt(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.plateCurved    .mat(MT.Invar           , 16+aData.mRandom.nextInt(49))), "s", (short)( 72+aData.mRandom.nextInt(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.screw          .mat(MT.Invar           , 16+aData.mRandom.nextInt(49))), "s", (short)( 72+aData.mRandom.nextInt(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.ring           .mat(MT.Invar           ,  8+aData.mRandom.nextInt(25))), "s", (short)( 72+aData.mRandom.nextInt(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.gearGt         .mat(MT.Invar           ,  1+aData.mRandom.nextInt( 4))), "s", (short)( 72+aData.mRandom.nextInt(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.gearGtSmall    .mat(MT.Invar           ,  8+aData.mRandom.nextInt(25))), "s", (short)( 72+aData.mRandom.nextInt(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.stick          .mat(MT.Brass           , 32+aData.mRandom.nextInt(33))), "s", (short)(108+aData.mRandom.nextInt(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.ingot          .mat(MT.Brass           , 32+aData.mRandom.nextInt(33))), "s", (short)(108+aData.mRandom.nextInt(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.plate          .mat(MT.Brass           , 32+aData.mRandom.nextInt(33))), "s", (short)(108+aData.mRandom.nextInt(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.plateCurved    .mat(MT.Brass           , 16+aData.mRandom.nextInt(49))), "s", (short)(108+aData.mRandom.nextInt(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.screw          .mat(MT.Brass           , 16+aData.mRandom.nextInt(49))), "s", (short)(108+aData.mRandom.nextInt(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.ring           .mat(MT.Brass           ,  8+aData.mRandom.nextInt(25))), "s", (short)(108+aData.mRandom.nextInt(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.gearGt         .mat(MT.Brass           ,  1+aData.mRandom.nextInt( 4))), "s", (short)(108+aData.mRandom.nextInt(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.gearGtSmall    .mat(MT.Brass           ,  8+aData.mRandom.nextInt(25))), "s", (short)(108+aData.mRandom.nextInt(36))));
		aData.set   ( 1, 1,  3, SIDE_UNKNOWN, (short) 4011, UT.NBT.make(NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T, NBT_FACING, SIDE_X_POS, NBT_INV_LIST, tInventory), T, T);
		aData.set   ( 1, 2,  3, SIDE_UNKNOWN, (short) 2010, UT.NBT.make(NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T, NBT_FACING, SIDE_X_POS, "gt6.dungeonloot", ChestGenHooks.PYRAMID_JUNGLE_CHEST    ), T, T);
		aData.coins ( 1, 3,  3);


		tInventory = new NBTTagList();
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.stick          .mat(MT.Steel, 32+aData.mRandom.nextInt(33))), "s", (short)aData.mRandom.nextInt(16)));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.ingot          .mat(MT.Steel, 32+aData.mRandom.nextInt(33))), "s", (short)aData.mRandom.nextInt(16)));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.plate          .mat(MT.Steel, 32+aData.mRandom.nextInt(33))), "s", (short)aData.mRandom.nextInt(16)));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.plateCurved    .mat(MT.Steel, 16+aData.mRandom.nextInt(49))), "s", (short)aData.mRandom.nextInt(16)));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.screw          .mat(MT.Steel, 16+aData.mRandom.nextInt(49))), "s", (short)aData.mRandom.nextInt(16)));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.ring           .mat(MT.Steel,  8+aData.mRandom.nextInt(25))), "s", (short)aData.mRandom.nextInt(16)));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.gearGt         .mat(MT.Steel,  1+aData.mRandom.nextInt( 4))), "s", (short)aData.mRandom.nextInt(16)));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.gearGtSmall    .mat(MT.Steel,  8+aData.mRandom.nextInt(25))), "s", (short)aData.mRandom.nextInt(16)));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(aData.mRandom.nextBoolean()?IL.Tool_Lighter_Invar_Full.get(1):IL.Tool_Lighter_Invar_Empty.get(1)), "s", (short)(35+aData.mRandom.nextInt(36))));
		aData.set   ( 1, 1,  4, SIDE_UNKNOWN,  5011, UT.NBT.make(NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T, NBT_FACING, SIDE_X_POS, NBT_INV_LIST, tInventory), T, T);
		aData.set   ( 1, 2,  4, SIDE_UNKNOWN, 32738, UT.NBT.make(NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), T, T);


		tInventory = new NBTTagList();
		tInventory.appendTag(UT.NBT.makeShort(ST.save(ST.make(aData.mPrimary    , 10001+aData.mRandom.nextInt(90000), 1)), "s", (short)1));
		aData.set   ( 4, 1,  1, SIDE_UNKNOWN,  6011, UT.NBT.make(NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T, NBT_FACING, SIDE_Z_POS, NBT_INV_LIST, tInventory), T, T);
		tInventory = new NBTTagList();
		tInventory.appendTag(UT.NBT.makeShort(ST.save(ST.make(aData.mSecondary  ,  1001+aData.mRandom.nextInt( 9000), 1)), "s", (short)1));
		aData.set   ( 4, 2,  1, SIDE_UNKNOWN,  6011, UT.NBT.make(NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T, NBT_FACING, SIDE_Z_POS, NBT_INV_LIST, tInventory), T, T);
		tInventory = new NBTTagList();
		tInventory.appendTag(UT.NBT.makeShort(ST.save(ST.book("Manual_Elements"     )), "s", (short)0));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(ST.book("Manual_Alloys"       )), "s", (short)1));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(ST.book("Manual_Smeltery"     )), "s", (short)2));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(ST.book("Manual_Random"       )), "s", (short)3));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(ST.book("Manual_Extenders"    )), "s", (short)4));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(ST.book("Manual_Steam"        )), "s", (short)5));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(ST.book("Manual_Tools"        )), "s", (short)6));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(ST.book("Manual_Printer"      )), "s", (short)7));
		int tKeyIndex = aData.mRandom.nextInt(aData.mGeneratedKeys.length * 2);
		if (tKeyIndex < aData.mGeneratedKeys.length) {
			aData.mGeneratedKeys[tKeyIndex] = T;
			tInventory.appendTag(UT.NBT.makeShort(ST.save(aData.mKeyStacks[tKeyIndex]), "s", (short)(8+aData.mRandom.nextInt(20))));
		}
		aData.set   ( 4, 3,  1, SIDE_UNKNOWN,  7111, UT.NBT.make(NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T, NBT_FACING, SIDE_Z_POS, NBT_INV_LIST, tInventory, "gt6.dungeonloot.front", ChestGenHooks.VILLAGE_BLACKSMITH), T, T);


		//-----

		ArrayListNoNulls<Integer> tMoldShapes = new ArrayListNoNulls<>(MultiTileEntityMold.MOLD_RECIPES.keySet());
		int tCrucibleType = aData.mRandom.nextInt(3);

		aData.set   (14, 1,  1, SIDE_UNKNOWN,    11, UT.NBT.make("gt6.dungeonloot", ChestGenHooks.VILLAGE_BLACKSMITH , NBT_FACING, SIDE_X_NEG), T, T);
		aData.smooth(14, 1,  2);
		aData.set   (14, 1,  3, SIDE_UNKNOWN, (1102+tCrucibleType), UT.NBT.make(NBT_FACING, SIDE_X_NEG), T, T);
		aData.smooth(14, 1,  4);
		aData.set   (11, 1,  1, Blocks.anvil, 3 | (aData.mRandom.nextInt(3) << 2), 0);
		aData.set   (12, 1,  1, SIDE_UNKNOWN, 32703, UT.NBT.make(NBT_FACING, SIDE_Z_POS, NBT_STATE, 1+aData.mRandom.nextInt(4)), T, T);
		aData.set   (11, 1,  4, SIDE_UNKNOWN, (32034+aData.mRandom.nextInt(4)), null, T, T);

		aData.set   (14, 2,  2, SIDE_UNKNOWN, (1070+tCrucibleType), UT.NBT.make("gt6.mold", tMoldShapes.isEmpty()?0:tMoldShapes.get(aData.mRandom.nextInt(tMoldShapes.size()))), T, T);
		aData.set   (14, 2,  3, SIDE_UNKNOWN, (1020+tCrucibleType), UT.NBT.make(NBT_FACING, SIDE_X_NEG), T, T);
		aData.set   (14, 2,  4, SIDE_UNKNOWN, (1070+tCrucibleType), UT.NBT.make("gt6.mold", tMoldShapes.isEmpty()?0:tMoldShapes.get(aData.mRandom.nextInt(tMoldShapes.size()))), T, T);

		//-----

		aData.set   (13, 1, 14, SIDE_UNKNOWN, 32705, null, T, T);
		aData.smooth(14, 1, 14);
		aData.set   (14, 1, 13, Blocks.cauldron, aData.mRandom.nextInt(4), 0);
		aData.set   (14, 1, 11, SIDE_UNKNOWN, 32707, null, T, T);

		aData.set   (13, 2, 14, SIDE_UNKNOWN, 32730, UT.NBT.make(NBT_FACING, SIDE_X_POS), T, T);
		aData.set   (14, 2, 14, SIDE_UNKNOWN, 32716, new FluidTankGT(FL.Water.make(64000)).writeToNBT(UT.NBT.make(), NBT_TANK), T, T);
		aData.set   (14, 2, 13, SIDE_UNKNOWN, 32730, UT.NBT.make(NBT_FACING, SIDE_Z_POS), T, T);

		aData.set   (14, 3, 14, SIDE_UNKNOWN, 32725, UT.NBT.make(NBT_FACING, SIDE_BOTTOM), T, T);

		//-----

		int tAmount = (aData.mRandom.nextInt(3) == 0 ? 32000 : 16000);
		short tID = (short)(tAmount > 16000 ? 32734 : 32714);
		FluidStack[] tDrinks = FL.array(FL.Purple_Drink.make(tAmount), FL.Purple_Drink.make(tAmount), FL.Purple_Drink.make(tAmount), FL.Vodka.make(tAmount), FL.Mead.make(tAmount), FL.Whiskey_GlenMcKenner.make(tAmount), FL.Wine_Grape_Purple.make(tAmount));

		for (int i = 0; i < 2; i++) for (int j = 0; j < 2; j++) {
			if (aData.mRandom.nextBoolean()) for (int k = 0; k < 3; k++) {
				aData.set(1+i, 1+k, 12+j, SIDE_UNKNOWN, tID, new FluidTankGT(UT.Code.select(NF, tDrinks)).writeToNBT(UT.NBT.make(), NBT_TANK), T, T);
				if (aData.mRandom.nextBoolean()) break;
			} else if (aData.mRandom.nextBoolean()) {
				if (aData.mRandom.nextBoolean()) {
					aData.set(1+i, 1, 12+j, SIDE_UNKNOWN, 32055, new FluidTankGT(FL.Propane.make(8000)).writeToNBT(UT.NBT.make(NBT_COLOR, DYE_INT_Red, NBT_PAINTED, T), NBT_TANK), T, T);
				} else {
					aData.set(1+i, 1, 12+j, SIDE_UNKNOWN, 32056, new FluidTankGT(FL.Helium.make(8000)).writeToNBT(UT.NBT.make(NBT_COLOR, DYE_INT_Yellow, NBT_PAINTED, T), NBT_TANK), T, T);
				}
			}
		}

		return T;
	}
}
