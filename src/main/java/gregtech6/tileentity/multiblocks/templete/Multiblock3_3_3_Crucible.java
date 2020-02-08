package gregtech6.tileentity.multiblocks.templete;

import static gregapi6.data.CS.*;

import gregapi6.tileentity.multiblocks.ITileEntityMultiBlockController;
import gregapi6.tileentity.multiblocks.MultiTileEntityMultiBlockPart;
import net.minecraft.world.World;

public class Multiblock3_3_3_Crucible {
	public static boolean checkStructure(ITileEntityMultiBlockController _class, World worldObj, int tX, int tY, int tZ, int mWalls, int regId) {
		boolean tSuccess = T;

		if (!worldObj.isAirBlock(tX, tY+1, tZ)) worldObj.setBlockToAir(tX, tY+1, tZ); else tSuccess = F;
		if (!worldObj.isAirBlock(tX, tY+2, tZ)) worldObj.setBlockToAir(tX, tY+2, tZ); else tSuccess = F;

		for (int i = -1; i < 2; i++) for (int j = -1; j < 2; j++) if (i != 0 || j != 0) {
			if (!ITileEntityMultiBlockController.Util.checkAndSetTargetOffset(_class, i, 0, j, mWalls, regId, 0, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTargetOffset(_class, i, 1, j, mWalls, regId, 0, MultiTileEntityMultiBlockPart.ONLY_CRUCIBLE)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTargetOffset(_class, i, 2, j, mWalls, regId, 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
		}

		if (tSuccess) for (int i = -1; i < 2; i++) for (int j = -1; j < 2; j++) if (i != 0 || j != 0) {
			if (!ITileEntityMultiBlockController.Util.checkAndSetTargetOffset(_class, i, 0, j, mWalls, regId, 4, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTargetOffset(_class, i, 1, j, mWalls, regId, 4, MultiTileEntityMultiBlockPart.ONLY_CRUCIBLE)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTargetOffset(_class, i, 2, j, mWalls, regId, 4, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
		}

		return tSuccess;
	}
}
