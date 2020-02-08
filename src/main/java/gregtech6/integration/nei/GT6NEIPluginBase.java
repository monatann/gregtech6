package gregtech6.integration.nei;

import codechicken.nei.recipe.TemplateRecipeHandler;

public class GT6NEIPluginBase extends TemplateRecipeHandler {
	
    @Override
    public int recipiesPerPage() {
        return 1;
    }

    @Override
    public String getRecipeName() {
        return "GT6 Multiblock Structure";
    }

    @Override
    public String getGuiTexture() {
        return "gtneioreplugin:textures/gui/nei/guiBase.png";
    }

    @Override
    public void loadTransferRects() {
        //int stringLength = GuiDraw.getStringWidth(EnumChatFormatting.BOLD + I18n.format("gui.nei.seeAll"));
        //transferRects.add(new RecipeTransferRect(new Rectangle(getGuiWidth()-stringLength-3, 5, stringLength, 9), getOutputId()));
    }
}