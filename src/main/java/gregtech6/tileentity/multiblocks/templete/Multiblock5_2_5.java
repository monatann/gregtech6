package gregtech6.tileentity.multiblocks.templete;

import static gregapi6.data.CS.*;

import gregapi6.tileentity.multiblocks.ITileEntityMultiBlockController;
import net.minecraft.world.World;

public class Multiblock5_2_5 {
	public static boolean checkStructure(boolean mStructureOkay, ITileEntityMultiBlockController _class, World worldObj, int tX, int tY, int tZ, int[] blockId, int regId, int[] inOutOption) {
		if (worldObj.blockExists(tX-2, tY, tZ-2) && worldObj.blockExists(tX+2, tY, tZ-2) && worldObj.blockExists(tX-2, tY, tZ+2) && worldObj.blockExists(tX+2, tY, tZ+2)) {
			boolean tSuccess = T;

			for (int i = 0; i <= 5; i++) for (int j = 0; j <= 2; j++) for (int k = 0; k <= 5; k++) {
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+i, tY+j, tZ+k  , blockId[j], regId, 0, inOutOption[j])) tSuccess = F;
			}

			return tSuccess;
		}else {
			return mStructureOkay;
		}
	}
}
