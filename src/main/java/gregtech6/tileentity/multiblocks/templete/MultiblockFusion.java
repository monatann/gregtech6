package gregtech6.tileentity.multiblocks.templete;

import static gregapi6.data.CS.*;

import gregapi6.tileentity.multiblocks.ITileEntityMultiBlockController;
import net.minecraft.world.World;

public class MultiblockFusion {
	public static boolean checkStructure(boolean mStructureOkay, ITileEntityMultiBlockController _class, World worldObj, int tX, int tY, int tZ, byte mFacing, boolean mActive, int[] blockId, int regId, int[] inOutOption) {
		if (worldObj.blockExists(tX-9, tY, tZ-9) && worldObj.blockExists(tX+9, tY, tZ-9) && worldObj.blockExists(tX-9, tY, tZ+9) && worldObj.blockExists(tX+9, tY, tZ+9)) {
			boolean tSuccess = T;
			
			int tVersatile = 3, tLogic = 12, tControl = 12;
			
			for (int i = -2; i <= 2; i++) for (int j = -2; j <= 2; j++) for (int k = -2; k <= 2; k++) {
				if (i*i + j*j + k*k < 4) {
					if (ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+i, tY+j, tZ+k, blockId[0], regId, 0, inOutOption[0])) {
						tVersatile--;
					} else if (ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+i, tY+j, tZ+k, blockId[1], regId, 0, inOutOption[0])) {
						tLogic--;
					} else if (ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+i, tY+j, tZ+k, blockId[2], regId, 0, inOutOption[0])) {
						tControl--;
					} else {
						tSuccess = F;
					}
				} else if (i*i + j*j + k*k > 6 || (j == 0 && (((i == -2 || i == 2) && k == 0) || (((k == -2 || k == 2) && i == 0))))) {
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+i, tY+j, tZ+k, blockId[3], regId, 0, inOutOption[0])) tSuccess = F;
				} else {
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+i, tY+j, tZ+k, blockId[4], regId, 0, inOutOption[0])) tSuccess = F;
				}
			}
			
			if (tVersatile > 0 || tLogic > 0 || tControl > 0) tSuccess = F;
			
			if (mFacing != SIDE_X_NEG) {
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX-3, tY, tZ  , blockId[5], regId, 0, inOutOption[0])) tSuccess = F;
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX-4, tY, tZ  , blockId[5], regId, 0, inOutOption[0])) tSuccess = F;
			}
			if (mFacing != SIDE_X_POS) {
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+3, tY, tZ  , blockId[5], regId, 0, inOutOption[0])) tSuccess = F;
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+4, tY, tZ  , blockId[5], regId, 0, inOutOption[0])) tSuccess = F;
			}
			if (mFacing != SIDE_Z_NEG) {
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX  , tY, tZ-3, blockId[5], regId, 0, inOutOption[0])) tSuccess = F;
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX  , tY, tZ-4, blockId[5], regId, 0, inOutOption[0])) tSuccess = F;
			}
			if (mFacing != SIDE_Z_POS) {
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX  , tY, tZ+3, blockId[5], regId, 0, inOutOption[0])) tSuccess = F;
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX  , tY, tZ+4, blockId[5], regId, 0, inOutOption[0])) tSuccess = F;
			}
			
			tX -= 9; tZ -= 9;
			
			for (int i = 0; i < 19; i++) for (int j = 0; j < 19; j++) {
				if (OCTAGONS[0][i][j]) {
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+i, tY-1, tZ+j, blockId[6], regId, 0, inOutOption[1])) tSuccess = F;
					if ((i == 9 && (j == 0 || j == 18)) || (j == 9 && (i == 0 || i == 18))) {
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+i, tY  , tZ+j, blockId[6], regId, 2, inOutOption[2])) tSuccess = F;
					} else {
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+i, tY  , tZ+j, blockId[6], regId, mActive ? 6 : 5, inOutOption[3])) tSuccess = F;
					}
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+i, tY+1, tZ+j, blockId[6], regId, 0, inOutOption[1])) tSuccess = F;
				}
				if (OCTAGONS[1][i][j]) {
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+i, tY-2, tZ+j, blockId[6], regId, 0, inOutOption[1])) tSuccess = F;
					
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+i, tY-1, tZ+j, blockId[6], regId, 0, inOutOption[0])) tSuccess = F;
					
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+i, tY  , tZ+j, blockId[7], regId, 0, inOutOption[0])) tSuccess = F;
					
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+i, tY+1, tZ+j, blockId[6], regId, 0, inOutOption[0])) tSuccess = F;
					
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+i, tY+2, tZ+j, blockId[6], regId, 0, inOutOption[1])) tSuccess = F;
				}
				if (OCTAGONS[2][i][j]) {
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+i, tY-2, tZ+j, blockId[6], regId, 0, inOutOption[1])) tSuccess = F;
					
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+i, tY-1, tZ+j, blockId[7], regId, 0, inOutOption[0])) tSuccess = F;
					
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+i, tY  , tZ+j, blockId[8], regId, 0, inOutOption[0])) tSuccess = F;
					
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+i, tY+1, tZ+j, blockId[7], regId, 0, inOutOption[0])) tSuccess = F;
					
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+i, tY+2, tZ+j, blockId[6], regId, 0, inOutOption[1])) tSuccess = F;
				}
			}
			return tSuccess;
		}
		return mStructureOkay;
	}
	
	public static boolean[][][] OCTAGONS = {{
		{F,F,F,F,F,F,F,T,T,T,T,T,F,F,F,F,F,F,F},
		{F,F,F,F,F,F,T,F,F,F,F,F,T,F,F,F,F,F,F},
		{F,F,F,F,F,T,F,F,F,F,F,F,F,T,F,F,F,F,F},
		{F,F,F,F,T,F,F,F,F,F,F,F,F,F,T,F,F,F,F},
		{F,F,F,T,F,F,F,T,T,T,T,T,F,F,F,T,F,F,F},
		{F,F,T,F,F,F,T,F,F,F,F,F,T,F,F,F,T,F,F},
		{F,T,F,F,F,T,F,F,F,F,F,F,F,T,F,F,F,T,F},
		{T,F,F,F,T,F,F,F,F,F,F,F,F,F,T,F,F,F,T},
		{T,F,F,F,T,F,F,F,F,F,F,F,F,F,T,F,F,F,T},
		{T,F,F,F,T,F,F,F,F,F,F,F,F,F,T,F,F,F,T},
		{T,F,F,F,T,F,F,F,F,F,F,F,F,F,T,F,F,F,T},
		{T,F,F,F,T,F,F,F,F,F,F,F,F,F,T,F,F,F,T},
		{F,T,F,F,F,T,F,F,F,F,F,F,F,T,F,F,F,T,F},
		{F,F,T,F,F,F,T,F,F,F,F,F,T,F,F,F,T,F,F},
		{F,F,F,T,F,F,F,T,T,T,T,T,F,F,F,T,F,F,F},
		{F,F,F,F,T,F,F,F,F,F,F,F,F,F,T,F,F,F,F},
		{F,F,F,F,F,T,F,F,F,F,F,F,F,T,F,F,F,F,F},
		{F,F,F,F,F,F,T,F,F,F,F,F,T,F,F,F,F,F,F},
		{F,F,F,F,F,F,F,T,T,T,T,T,F,F,F,F,F,F,F},
	}, {
		{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
		{F,F,F,F,F,F,F,T,T,T,T,T,F,F,F,F,F,F,F},
		{F,F,F,F,F,F,T,F,F,F,F,F,T,F,F,F,F,F,F},
		{F,F,F,F,F,T,F,T,T,T,T,T,F,T,F,F,F,F,F},
		{F,F,F,F,T,F,T,F,F,F,F,F,T,F,T,F,F,F,F},
		{F,F,F,T,F,T,F,F,F,F,F,F,F,T,F,T,F,F,F},
		{F,F,T,F,T,F,F,F,F,F,F,F,F,F,T,F,T,F,F},
		{F,T,F,T,F,F,F,F,F,F,F,F,F,F,F,T,F,T,F},
		{F,T,F,T,F,F,F,F,F,F,F,F,F,F,F,T,F,T,F},
		{F,T,F,T,F,F,F,F,F,F,F,F,F,F,F,T,F,T,F},
		{F,T,F,T,F,F,F,F,F,F,F,F,F,F,F,T,F,T,F},
		{F,T,F,T,F,F,F,F,F,F,F,F,F,F,F,T,F,T,F},
		{F,F,T,F,T,F,F,F,F,F,F,F,F,F,T,F,T,F,F},
		{F,F,F,T,F,T,F,F,F,F,F,F,F,T,F,T,F,F,F},
		{F,F,F,F,T,F,T,F,F,F,F,F,T,F,T,F,F,F,F},
		{F,F,F,F,F,T,F,T,T,T,T,T,F,T,F,F,F,F,F},
		{F,F,F,F,F,F,T,F,F,F,F,F,T,F,F,F,F,F,F},
		{F,F,F,F,F,F,F,T,T,T,T,T,F,F,F,F,F,F,F},
		{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
	}, {
		{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
		{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
		{F,F,F,F,F,F,F,T,T,T,T,T,F,F,F,F,F,F,F},
		{F,F,F,F,F,F,T,F,F,F,F,F,T,F,F,F,F,F,F},
		{F,F,F,F,F,T,F,F,F,F,F,F,F,T,F,F,F,F,F},
		{F,F,F,F,T,F,F,F,F,F,F,F,F,F,T,F,F,F,F},
		{F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F},
		{F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F},
		{F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F},
		{F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F},
		{F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F},
		{F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F},
		{F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F},
		{F,F,F,F,T,F,F,F,F,F,F,F,F,F,T,F,F,F,F},
		{F,F,F,F,F,T,F,F,F,F,F,F,F,T,F,F,F,F,F},
		{F,F,F,F,F,F,T,F,F,F,F,F,T,F,F,F,F,F,F},
		{F,F,F,F,F,F,F,T,T,T,T,T,F,F,F,F,F,F,F},
		{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
		{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
	}};
}
