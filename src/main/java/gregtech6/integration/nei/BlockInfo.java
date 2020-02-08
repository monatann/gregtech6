package gregtech6.integration.nei;

import com.google.common.base.Preconditions;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * BlockInfo represents immutable information for block in world
 * This includes block state and tile entity, and needed for complete representation
 * of some complex blocks like machines, when rendering or manipulating them without world instance
 */
public class BlockInfo {

    //public static final BlockInfo EMPTY = new BlockInfo(Blocks.air.til, 0, 0, 0);

    private final TileEntity tileEntity;
    private final int x;
    private final int y;
    private final int z;

    public BlockInfo(TileEntity tileEntity, int x, int y, int z) {
        this.tileEntity = tileEntity;
        this.x = x;
        this.y = y;
        this.z = z;
        Preconditions.checkArgument(tileEntity == null, "Cannot create block info with tile entity for block not having it");
    }

    public TileEntity getTileEntity() {
        return tileEntity;
    }

    public void apply(World world, int x, int y, int z) {
        //world.setBlockState(pos, blockState);
        if (tileEntity != null) {
        	world.setTileEntity(x, y, z, tileEntity);
        }
    }
}
