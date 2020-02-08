package gregtech6.tileentity.multiblocks.templete;

import static gregapi6.data.CS.*;

import gregapi6.tileentity.multiblocks.ITileEntityMultiBlockController;
import net.minecraft.world.World;

public class Multiblock5_5_5_3_3_3_Air {
	public static boolean checkStructure(boolean mStructureOkay, ITileEntityMultiBlockController _class, World worldObj, int tX, int tY, int tZ, byte mFacing, int[] blockId, int regId, int[] inOutOption) {
		if (worldObj.blockExists(tX, tY, tZ) && worldObj.blockExists(tX+4, tY, tZ) && worldObj.blockExists(tX, tY, tZ+4) && worldObj.blockExists(tX+4, tY, tZ+4)) {
			boolean tSuccess = T;

			for(int i=0;i<5;i++) for(int j=0;j<5;j++) for(int k=0;k<5;k++) {

				if(i==2 && j==2 && k==2) {
					if (!worldObj.isAirBlock(tX+2, tY+2, tZ+2)) tSuccess = F;
				}else if(i != 0 && i !=4 && j != 0 && j != 4 && k != 0 && k != 4) {
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+j  , tY+i  , tZ+k  , blockId[0], regId, 0, inOutOption[0])) tSuccess = F;
				}else {
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(_class, tX+j  , tY+i  , tZ+k  , blockId[1], regId, 0, inOutOption[1])) tSuccess = F;
				}
			}

			return tSuccess;
		} else {
			return mStructureOkay;
		}
	}
}
