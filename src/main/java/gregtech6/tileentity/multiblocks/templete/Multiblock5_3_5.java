package gregtech6.tileentity.multiblocks.templete;

import static gregapi6.data.CS.*;

import gregapi6.tileentity.multiblocks.ITileEntityMultiBlockController;
import net.minecraft.world.World;

public class Multiblock5_3_5 {
	public static boolean checkStructure(boolean mStructureOkay, ITileEntityMultiBlockController _class, World worldObj, int tX, int tY, int tZ, int mFacing, int[] blockId, int regId, int[] inOutOption) {
		if (worldObj.blockExists(tX-2, tY, tZ-2) && worldObj.blockExists(tX+2, tY, tZ-2) && worldObj.blockExists(tX-2, tY, tZ+2) && worldObj.blockExists(tX+2, tY, tZ+2)) {
			boolean tSuccess = T;

			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX  , tY-1, tZ  , blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY-1, tZ  , blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+2, tY-1, tZ  , blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+3, tY-1, tZ  , blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+4, tY-1, tZ  , blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX  , tY-1, tZ+1, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY-1, tZ+1, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+2, tY-1, tZ+1, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+3, tY-1, tZ+1, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+4, tY-1, tZ+1, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX  , tY-1, tZ+2, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY-1, tZ+2, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+2, tY-1, tZ+2, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+3, tY-1, tZ+2, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+4, tY-1, tZ+2, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX  , tY-1, tZ+3, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY-1, tZ+3, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+2, tY-1, tZ+3, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+3, tY-1, tZ+3, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+4, tY-1, tZ+3, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX  , tY-1, tZ+4, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY-1, tZ+4, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+2, tY-1, tZ+4, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+3, tY-1, tZ+4, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+4, tY-1, tZ+4, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;

			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX  , tY  , tZ  , blockId[1], regId, 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY  , tZ  , blockId[1], regId, 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+2, tY  , tZ  , blockId[1], regId, SIDE_Z_POS == mFacing ? 7 : 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+3, tY  , tZ  , blockId[1], regId, 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+4, tY  , tZ  , blockId[1], regId, 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX  , tY  , tZ+1, blockId[1], regId, 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY  , tZ+1, blockId[1], regId, 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+2, tY  , tZ+1, blockId[1], regId, 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+3, tY  , tZ+1, blockId[1], regId, 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+4, tY  , tZ+1, blockId[1], regId, 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX  , tY  , tZ+2, blockId[1], regId, SIDE_X_POS == mFacing ? 7 : 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY  , tZ+2, blockId[1], regId, 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+2, tY  , tZ+2, blockId[1], regId, 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+3, tY  , tZ+2, blockId[1], regId, 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+4, tY  , tZ+2, blockId[1], regId, SIDE_X_NEG == mFacing ? 7 : 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX  , tY  , tZ+3, blockId[1], regId, 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY  , tZ+3, blockId[1], regId, 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+2, tY  , tZ+3, blockId[1], regId, 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+3, tY  , tZ+3, blockId[1], regId, 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+4, tY  , tZ+3, blockId[1], regId, 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX  , tY  , tZ+4, blockId[1], regId, 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY  , tZ+4, blockId[1], regId, 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+2, tY  , tZ+4, blockId[1], regId, SIDE_Z_NEG == mFacing ? 7 : 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+3, tY  , tZ+4, blockId[1], regId, 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+4, tY  , tZ+4, blockId[1], regId, 0, inOutOption[1])) tSuccess = F;

			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX  , tY+1, tZ  , blockId[1], regId, 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY+1, tZ  , blockId[1], regId, 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+2, tY+1, tZ  , blockId[1], regId, SIDE_Z_POS == mFacing ? 7 : 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+3, tY+1, tZ  , blockId[1], regId, 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+4, tY+1, tZ  , blockId[1], regId, 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX  , tY+1, tZ+1, blockId[1], regId, 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY+1, tZ+1, blockId[1], regId, 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+2, tY+1, tZ+1, blockId[1], regId, 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+3, tY+1, tZ+1, blockId[1], regId, 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+4, tY+1, tZ+1, blockId[1], regId, 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX  , tY+1, tZ+2, blockId[1], regId, SIDE_X_POS == mFacing ? 7 : 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY+1, tZ+2, blockId[1], regId, 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+2, tY+1, tZ+2, blockId[1], regId, 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+3, tY+1, tZ+2, blockId[1], regId, 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+4, tY+1, tZ+2, blockId[1], regId, SIDE_X_NEG == mFacing ? 7 : 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX  , tY+1, tZ+3, blockId[1], regId, 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY+1, tZ+3, blockId[1], regId, 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+2, tY+1, tZ+3, blockId[1], regId, 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+3, tY+1, tZ+3, blockId[1], regId, 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+4, tY+1, tZ+3, blockId[1], regId, 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX  , tY+1, tZ+4, blockId[1], regId, 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY+1, tZ+4, blockId[1], regId, 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+2, tY+1, tZ+4, blockId[1], regId, SIDE_Z_NEG == mFacing ? 7 : 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+3, tY+1, tZ+4, blockId[1], regId, 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+4, tY+1, tZ+4, blockId[1], regId, 0, inOutOption[1])) tSuccess = F;

			return tSuccess;
		} else {
			return mStructureOkay;
		}
	}
}
