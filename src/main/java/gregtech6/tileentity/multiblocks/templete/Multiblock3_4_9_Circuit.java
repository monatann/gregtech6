package gregtech6.tileentity.multiblocks.templete;

import static gregapi6.data.CS.*;

import gregapi6.tileentity.multiblocks.ITileEntityMultiBlockController;
import net.minecraft.world.World;

public class Multiblock3_4_9_Circuit {
	public static boolean checkStructure(boolean mStructureOkay, ITileEntityMultiBlockController _class, World worldObj, int tX, int tY, int tZ, boolean mActive, byte mFacing, int[] blockId, int regId, int[] inOutOption) {
		if (worldObj.blockExists(tX-1, tY, tZ-1) && worldObj.blockExists(tX+1, tY, tZ-1) && worldObj.blockExists(tX-1, tY, tZ+1) && worldObj.blockExists(tX+1, tY, tZ+1)) {
			boolean tSuccess = T;

			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX  , tY  , tZ  , blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY  , tZ  , blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+2, tY  , tZ  , blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX  , tY  , tZ+1, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY  , tZ+1, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+2, tY  , tZ+1, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX  , tY  , tZ+2, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY  , tZ+2, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+2, tY  , tZ+2, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX  , tY  , tZ+3, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY  , tZ+3, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+2, tY  , tZ+3, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX  , tY  , tZ+4, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY  , tZ+4, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+2, tY  , tZ+4, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX  , tY  , tZ+5, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY  , tZ+5, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+2, tY  , tZ+5, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX  , tY  , tZ+6, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY  , tZ+6, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+2, tY  , tZ+6, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX  , tY  , tZ+7, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY  , tZ+7, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+2, tY  , tZ+7, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX  , tY  , tZ+8, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY  , tZ+8, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+2, tY  , tZ+8, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;

			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX  , tY+1, tZ  , blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY+1, tZ  , blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+2, tY+1, tZ  , blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX  , tY+1, tZ+1, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY+1, tZ+1, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+2, tY+1, tZ+1, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX  , tY+1, tZ+2, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY+1, tZ+2, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+2, tY+1, tZ+2, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX  , tY+1, tZ+3, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY+1, tZ+3, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+2, tY+1, tZ+3, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX  , tY+1, tZ+4, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY+1, tZ+4, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+2, tY+1, tZ+4, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX  , tY+1, tZ+5, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY+1, tZ+5, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+2, tY+1, tZ+5, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX  , tY+1, tZ+6, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY+1, tZ+6, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+2, tY+1, tZ+6, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX  , tY+1, tZ+7, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY+1, tZ+7, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+2, tY+1, tZ+7, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX  , tY+1, tZ+8, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY+1, tZ+8, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+2, tY+1, tZ+8, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;

            if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX  , tY+2, tZ  , blockId[1], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY+2, tZ  , blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+2, tY+2, tZ  , blockId[1], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX  , tY+2, tZ+1, blockId[1], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY+2, tZ+1, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+2, tY+2, tZ+1, blockId[1], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX  , tY+2, tZ+2, blockId[1], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY+2, tZ+2, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+2, tY+2, tZ+2, blockId[1], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX  , tY+2, tZ+3, blockId[1], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY+2, tZ+3, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+2, tY+2, tZ+3, blockId[1], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX  , tY+2, tZ+4, blockId[1], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY+2, tZ+4, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+2, tY+2, tZ+4, blockId[1], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX  , tY+2, tZ+5, blockId[1], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY+2, tZ+5, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+2, tY+2, tZ+5, blockId[1], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX  , tY+2, tZ+6, blockId[1], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY+2, tZ+6, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+2, tY+2, tZ+6, blockId[1], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX  , tY+2, tZ+7, blockId[1], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY+2, tZ+7, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+2, tY+2, tZ+7, blockId[1], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX  , tY+2, tZ+8, blockId[1], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY+2, tZ+8, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+2, tY+2, tZ+8, blockId[1], regId, 0, inOutOption[0])) tSuccess = F;

			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY+3, tZ  , blockId[2], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY+3, tZ+1, blockId[2], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY+3, tZ+2, blockId[2], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY+3, tZ+3, blockId[2], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY+3, tZ+4, blockId[2], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY+3, tZ+5, blockId[2], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY+3, tZ+6, blockId[2], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY+3, tZ+7, blockId[2], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY+3, tZ+8, blockId[2], regId, 0, inOutOption[0])) tSuccess = F;

			return tSuccess;
		} else {
			return mStructureOkay;
		}
	}
}
