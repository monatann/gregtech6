package gregtech6.tileentity.multiblocks.templete;

import static gregapi6.data.CS.*;

import gregapi6.tileentity.multiblocks.ITileEntityMultiBlockController;
import net.minecraft.world.World;

public class Multiblock3_3_3_Air {
	public static boolean checkStructure(boolean mStructureOkay, ITileEntityMultiBlockController _class, World worldObj, int tX, int tY, int tZ, int[] blockId, int regId, int[] inOutOption) {
		if (worldObj.blockExists(tX-1, tY, tZ-1) && worldObj.blockExists(tX+1, tY, tZ-1) && worldObj.blockExists(tX-1, tY, tZ+1) && worldObj.blockExists(tX+1, tY, tZ+1)) {
			boolean tSuccess = T;
			for (int i = -1; i <= 1; i++) for (int j = -1; j <= 1; j++) for (int k = -1; k <= 1; k++) {
				if (i == 0 && j == 0 && k == 0) {
					if (!worldObj.isAirBlock(tX, tY, tZ)) {
						tSuccess = F;
					}
				} else {
					switch (j) {
					case -1:
						if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+i, tY+j, tZ+k  , blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
					    break;
					case 0:
						if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+i, tY+j, tZ+k  , blockId[1], regId, 0, inOutOption[1])) tSuccess = F;
						break;
					case 1:
						if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+i, tY+j, tZ+k  , blockId[2], regId, 0, inOutOption[2])) tSuccess = F;
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
