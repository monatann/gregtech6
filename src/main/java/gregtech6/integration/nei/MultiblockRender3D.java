package gregtech6.integration.nei;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;

public class MultiblockRender3D  implements ISimpleBlockRenderingHandler
{
	/*
	 * インベントリ内での描画に, TileEntitySpecialRendererを利用する場合のサンプル.
	 */
	public static TileEntity dummyTileEntity = Blocks.chest.createTileEntity(null, 0);

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
	{
		/*
		 * TileEntityRendererDispatcherからTileEntityに対応するSpecialRendererを取得し, renderTileEntityAt()を実行.
		 */
		System.out.println(block.getUnlocalizedName());
		//TileEntityRendererDispatcher.instance.getSpecialRenderer(dummyTileEntity).renderTileEntityAt(dummyTileEntity, -0.5D, -0.5D, -0.5D, 0.0F);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		/*
		 * Worldでの描画はTileEntitySpecialRendererを利用するためここでは何もしない.
		 */
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId)
	{
		return true;
	}

	@Override
	public int getRenderId()
	{
		return GT6NEICorePlugin.blockMobRenderType;
	}

}