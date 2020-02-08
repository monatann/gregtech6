package gregtech6.tileentity.multiblocks.templete;

import static gregapi6.data.CS.*;

import gregapi6.tileentity.multiblocks.ITileEntityMultiBlockController;
import net.minecraft.world.World;

public class Multiblock5_5_5_Air {
	public static boolean checkStructure(boolean mStructureOkay, ITileEntityMultiBlockController _class, World worldObj, int tX, int tY, int tZ, int[] blockId, int regId, int[] inOutOption) {
		if (worldObj.blockExists(tX-2, tY, tZ-2) && worldObj.blockExists(tX+2, tY, tZ-2) && worldObj.blockExists(tX-2, tY, tZ+2) && worldObj.blockExists(tX+2, tY, tZ+2)) {
			boolean tSuccess = T;
			for (int i = -2; i <= 2; i++) for (int j = -2; j <= 2; j++) for (int k = -2; k <= 2; k++) {
				if (i == 0 && j == 0 && k == 0) {
					if (!worldObj.isAirBlock(tX, tY, tZ)) {
						tSuccess = F;
					}
				} else {
					switch (j) {
					case -2:
						if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+i, tY+j, tZ+k  , blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
					    break;
					case -1:
						if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+i, tY+j, tZ+k  , blockId[1], regId, 0, inOutOption[1])) tSuccess = F;
					    break;
					case 0:
						if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+i, tY+j, tZ+k  , blockId[2], regId, 0, inOutOption[2])) tSuccess = F;
						break;
					case 1:
						if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+i, tY+j, tZ+k  , blockId[3], regId, 0, inOutOption[3])) tSuccess = F;
						break;
					case 2:
						if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+i, tY+j, tZ+k  , blockId[4], regId, 0, inOutOption[4])) tSuccess = F;
						break;
					default:
					    return mStructureOkay;
					}
				}
			}

			return tSuccess;
		}else {
			return mStructureOkay;
		}
	}
}
