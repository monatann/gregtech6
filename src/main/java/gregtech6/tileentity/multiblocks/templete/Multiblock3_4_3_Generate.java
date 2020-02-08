package gregtech6.tileentity.multiblocks.templete;

import static gregapi6.data.CS.*;

import gregapi6.tileentity.multiblocks.ITileEntityMultiBlockController;
import net.minecraft.world.World;

public class Multiblock3_4_3_Generate {
	public static boolean checkStructure(boolean mStructureOkay, ITileEntityMultiBlockController _class, World worldObj, int tX, int tY, int tZ, int[] blockId, int regId, int[] inOutOption) {
		if (worldObj.blockExists(tX-1, tY, tZ-1) && worldObj.blockExists(tX+1, tY, tZ-1) && worldObj.blockExists(tX-1, tY, tZ+1) && worldObj.blockExists(tX+1, tY, tZ+1)) {
			boolean tSuccess = T;
			
			if (worldObj.isAirBlock(tX, tY+1, tZ)) tSuccess = F;
			
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX-1, tY-1, tZ-1, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX  , tY-1, tZ-1, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY-1, tZ-1, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX-1, tY-1, tZ  , blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX  , tY-1, tZ  , blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY-1, tZ  , blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX-1, tY-1, tZ+1, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX  , tY-1, tZ+1, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY-1, tZ+1, blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
			
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX-1, tY  , tZ-1, blockId[1], regId, 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX  , tY  , tZ-1, blockId[1], regId, 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY  , tZ-1, blockId[1], regId, 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX-1, tY  , tZ  , blockId[1], regId, 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX  , tY  , tZ  , blockId[1], regId, 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY  , tZ  , blockId[1], regId, 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX-1, tY  , tZ+1, blockId[1], regId, 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX  , tY  , tZ+1, blockId[1], regId, 0, inOutOption[1])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY  , tZ+1, blockId[1], regId, 0, inOutOption[1])) tSuccess = F;
			
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX  , tY+2, tZ  , blockId[1], regId, 1, inOutOption[2])) tSuccess = F;
			for (int i = 1; i < 3; i++) {
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX-1, tY+i, tZ-1, blockId[1], regId,                0, inOutOption[2])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX  , tY+i, tZ-1, blockId[1], regId,   i == 1 ? 1 : 0, inOutOption[2])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY+i, tZ-1, blockId[1], regId,                0, inOutOption[2])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX-1, tY+i, tZ  , blockId[1], regId,   i == 1 ? 1 : 0, inOutOption[2])) tSuccess = F;
			
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY+i, tZ  , blockId[1], regId,   i == 1 ? 1 : 0, inOutOption[2])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX-1, tY+i, tZ+1, blockId[1], regId,                0, inOutOption[2])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX  , tY+i, tZ+1, blockId[1], regId,   i == 1 ? 1 : 0, inOutOption[2])) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+1, tY+i, tZ+1, blockId[1], regId,                0, inOutOption[2])) tSuccess = F;
			}
			
			return tSuccess;
		}else {
			return mStructureOkay;
		}
	}
}
