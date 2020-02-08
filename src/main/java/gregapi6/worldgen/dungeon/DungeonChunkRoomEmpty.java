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

/**
 * @author Gregorius Techneticies
 * 
 * This is the Empty Room Implementation
 * It is a good Idea to just extend this Class and do a super Call in order to have an Empty Room generated before placing your Interior Design.
 */
public class DungeonChunkRoomEmpty extends DungeonChunkPillar {
	@Override
	public boolean generate(DungeonData aData) {
		super.generate(aData);
		
		for (int tX = 0; tX < 16; tX++) for (int tZ = 0; tZ < 16; tZ++) for (int tY = 0; tY <= 7; tY++) {
			if (tX == 0 || tX == 15 || tZ == 0 || tZ == 15 || tY == 0 || tY == 7) {
				if ((tX == 3 || tX == 6 || tX == 9 || tX == 12) && (tZ == 3 || tZ == 6 || tZ == 9 || tZ == 12)) {
					if (tY == 0) {
						aData.chiseled(tX, tY, tZ);
					} else if (tY == 7) {
						aData.lamp(tX, tY, tZ, +1);
					} else {
						aData.bricks(tX, tY, tZ);
					}
				} else {
					if (tY == 0) {
						aData.tiles(tX, tY, tZ);
					} else if (tY == 7) {
						aData.smalltiles(tX, tY, tZ);
						aData.tiles(tX, tY+1, tZ);
					} else {
						aData.bricks(tX, tY, tZ);
					}
				}
			} else {
				aData.air(tX, tY, tZ);
			}
		}
		
		if (aData.mRoomLayout[aData.mRoomX+1][aData.mRoomZ] != 0) {
			aData.chiseled(15, 0,  5);
			aData.smooth  (15, 0,  6);
			aData.smooth  (15, 0,  7);
			aData.smooth  (15, 0,  8);
			aData.smooth  (15, 0,  9);
			aData.chiseled(15, 0, 10);
			aData.smooth  (15, 1,  5);
			aData.air     (15, 1,  6);
			aData.air     (15, 1,  7);
			aData.air     (15, 1,  8);
			aData.air     (15, 1,  9);
			aData.smooth  (15, 1, 10);
			aData.smooth  (15, 2,  5);
			aData.air     (15, 2,  6);
			aData.air     (15, 2,  7);
			aData.air     (15, 2,  8);
			aData.air     (15, 2,  9);
			aData.smooth  (15, 2, 10);
			aData.smooth  (15, 3,  5);
			aData.air     (15, 3,  6);
			aData.air     (15, 3,  7);
			aData.air     (15, 3,  8);
			aData.air     (15, 3,  9);
			aData.smooth  (15, 3, 10);
			aData.chiseled(15, 4,  5);
			aData.smooth  (15, 4,  6);
			aData.smooth  (15, 4,  7);
			aData.smooth  (15, 4,  8);
			aData.smooth  (15, 4,  9);
			aData.chiseled(15, 4, 10);
		}
		if (aData.mRoomLayout[aData.mRoomX-1][aData.mRoomZ] != 0) {
			aData.chiseled( 0, 0,  5);
			aData.smooth  ( 0, 0,  6);
			aData.smooth  ( 0, 0,  7);
			aData.smooth  ( 0, 0,  8);
			aData.smooth  ( 0, 0,  9);
			aData.chiseled( 0, 0, 10);
			aData.smooth  ( 0, 1,  5);
			aData.air     ( 0, 1,  6);
			aData.air     ( 0, 1,  7);
			aData.air     ( 0, 1,  8);
			aData.air     ( 0, 1,  9);
			aData.smooth  ( 0, 1, 10);
			aData.smooth  ( 0, 2,  5);
			aData.air     ( 0, 2,  6);
			aData.air     ( 0, 2,  7);
			aData.air     ( 0, 2,  8);
			aData.air     ( 0, 2,  9);
			aData.smooth  ( 0, 2, 10);
			aData.smooth  ( 0, 3,  5);
			aData.air     ( 0, 3,  6);
			aData.air     ( 0, 3,  7);
			aData.air     ( 0, 3,  8);
			aData.air     ( 0, 3,  9);
			aData.smooth  ( 0, 3, 10);
			aData.chiseled( 0, 4,  5);
			aData.smooth  ( 0, 4,  6);
			aData.smooth  ( 0, 4,  7);
			aData.smooth  ( 0, 4,  8);
			aData.smooth  ( 0, 4,  9);
			aData.chiseled( 0, 4, 10);
		}
		if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ+1] != 0) {
			aData.chiseled( 5, 0, 15);
			aData.smooth  ( 6, 0, 15);
			aData.smooth  ( 7, 0, 15);
			aData.smooth  ( 8, 0, 15);
			aData.smooth  ( 9, 0, 15);
			aData.chiseled(10, 0, 15);
			aData.smooth  ( 5, 1, 15);
			aData.air     ( 6, 1, 15);
			aData.air     ( 7, 1, 15);
			aData.air     ( 8, 1, 15);
			aData.air     ( 9, 1, 15);
			aData.smooth  (10, 1, 15);
			aData.smooth  ( 5, 2, 15);
			aData.air     ( 6, 2, 15);
			aData.air     ( 7, 2, 15);
			aData.air     ( 8, 2, 15);
			aData.air     ( 9, 2, 15);
			aData.smooth  (10, 2, 15);
			aData.smooth  ( 5, 3, 15);
			aData.air     ( 6, 3, 15);
			aData.air     ( 7, 3, 15);
			aData.air     ( 8, 3, 15);
			aData.air     ( 9, 3, 15);
			aData.smooth  (10, 3, 15);
			aData.chiseled( 5, 4, 15);
			aData.smooth  ( 6, 4, 15);
			aData.smooth  ( 7, 4, 15);
			aData.smooth  ( 8, 4, 15);
			aData.smooth  ( 9, 4, 15);
			aData.chiseled(10, 4, 15);
		}
		if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ-1] != 0) {
			aData.chiseled( 5, 0,  0);
			aData.smooth  ( 6, 0,  0);
			aData.smooth  ( 7, 0,  0);
			aData.smooth  ( 8, 0,  0);
			aData.smooth  ( 9, 0,  0);
			aData.chiseled(10, 0,  0);
			aData.smooth  ( 5, 1,  0);
			aData.air     ( 6, 1,  0);
			aData.air     ( 7, 1,  0);
			aData.air     ( 8, 1,  0);
			aData.air     ( 9, 1,  0);
			aData.smooth  (10, 1,  0);
			aData.smooth  ( 5, 2,  0);
			aData.air     ( 6, 2,  0);
			aData.air     ( 7, 2,  0);
			aData.air     ( 8, 2,  0);
			aData.air     ( 9, 2,  0);
			aData.smooth  (10, 2,  0);
			aData.smooth  ( 5, 3,  0);
			aData.air     ( 6, 3,  0);
			aData.air     ( 7, 3,  0);
			aData.air     ( 8, 3,  0);
			aData.air     ( 9, 3,  0);
			aData.smooth  (10, 3,  0);
			aData.chiseled( 5, 4,  0);
			aData.smooth  ( 6, 4,  0);
			aData.smooth  ( 7, 4,  0);
			aData.smooth  ( 8, 4,  0);
			aData.smooth  ( 9, 4,  0);
			aData.chiseled(10, 4,  0);
		}
		return T;
	}
}
