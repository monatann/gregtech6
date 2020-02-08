package gregtech6.integration.nei;

import codechicken.nei.api.API;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi6.render.RendererBlockTextured;
import net.minecraft.block.Block;

public class GT6NEICorePlugin {
	public static Block blockMob;

	@SideOnly(Side.CLIENT)
	public static int blockMobRenderType = RendererBlockTextured.INSTANCE.mRenderID;

	public String getName() {
		return "GregTech6 Unofficial NEI Plugin";
	}

	public String getVersion() {
		return "1.0.0";
	}

	public static void loadConfig() {
    	//MultiblockRender3D.renderID = RenderingRegistry.getNextAvailableRenderId();
    	RenderingRegistry.registerBlockHandler(new MultiblockRender3D());

		GT6NEIMultiblockRecipe pluginGT6VeinStat = new GT6NEIMultiblockRecipe();
		API.registerRecipeHandler(pluginGT6VeinStat);
		API.registerUsageHandler(pluginGT6VeinStat);
	}
}