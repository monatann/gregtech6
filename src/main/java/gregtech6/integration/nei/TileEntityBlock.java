package gregtech6.integration.nei;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityBlock extends BlockContainer
{
	public TileEntityBlock(Material material)
	{
		super(material);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int par2)
	{
		return new TileEntityDummy();
	}

	@Override
	public int getRenderType()
	{
		return GT6NEICorePlugin.blockMobRenderType;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
}
