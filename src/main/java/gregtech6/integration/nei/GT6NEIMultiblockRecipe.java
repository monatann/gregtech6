package gregtech6.integration.nei;

import java.util.ArrayList;
import java.util.List;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.PositionedStack;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;

public class GT6NEIMultiblockRecipe extends GT6NEIPluginBase {

    public class CachedMultiblockRecipe extends CachedRecipe {
        public String veinName;
        List<PositionedStack> itemlist = new ArrayList<PositionedStack>();

        public CachedMultiblockRecipe(String veinName, List<ItemStack> stack) {
            this.veinName = veinName;
            /*
            positionedStackPrimary = new PositionedStack(stackListPrimary, 2, 0);
            positionedStackSecondary = new PositionedStack(stackListSecondary, 22, 0);
            positionedStackBetween = new PositionedStack(stackListBetween, 42, 0);
            positionedStackSporadic = new PositionedStack(stackListSporadic, 62, 0);
            */
        }

        @Override
        public List<PositionedStack> getIngredients() {
            List<PositionedStack> ingredientsList = new ArrayList<PositionedStack>();

            /*
            positionedStackPrimary.setPermutationToRender((cycleticks / 20) % positionedStackPrimary.items.length);;
            positionedStackSecondary.setPermutationToRender((cycleticks / 20) % positionedStackPrimary.items.length);;
            positionedStackBetween.setPermutationToRender((cycleticks / 20) % positionedStackPrimary.items.length);;
            positionedStackSporadic.setPermutationToRender((cycleticks / 20) % positionedStackPrimary.items.length);;
            ingredientsList.add(positionedStackPrimary);
            ingredientsList.add(positionedStackSecondary);
            ingredientsList.add(positionedStackBetween);
            ingredientsList.add(positionedStackSporadic);
            */

            return ingredientsList;
        }

        @Override
        public PositionedStack getResult() {
            return null;
        }

    }

    @Override
    public void loadCraftingRecipes(String outputId, Object... results) {
        if (outputId.equals("GT6UnofficialNEIPlugin")) {
        	 //this.arecipes.add(new CachedMultiblockRecipe("Test", new ItemStack(Blocks.cobblestone), new ItemStack(Blocks.cobblestone), new ItemStack(Blocks.cobblestone), new ItemStack(Blocks.cobblestone)));
        	/*
            OreLayerWrapper oreLayerWrapper;
            for (String veinName: GT6OreLayerHelper.mapOreLayerWrapper.keySet()) {
                oreLayerWrapper = GT6OreLayerHelper.mapOreLayerWrapper.get(veinName);
                List<ItemStack> stackListPrimary = new ArrayList<ItemStack>();
                List<ItemStack> stackListSecondary = new ArrayList<ItemStack>();
                List<ItemStack> stackListBetween = new ArrayList<ItemStack>();
                List<ItemStack> stackListSporadic = new ArrayList<ItemStack>();
                for (PrefixBlock prefixBlock: GT6OreLayerHelper.setOreNormalBasicTypes) {
                    stackListPrimary.add(new ItemStack(prefixBlock, 1, oreLayerWrapper.primaryMeta));
                    stackListSecondary.add(new ItemStack(prefixBlock, 1, oreLayerWrapper.secondaryMeta));
                    stackListBetween.add(new ItemStack(prefixBlock, 1, oreLayerWrapper.betweenMeta));
                    stackListSporadic.add(new ItemStack(prefixBlock, 1, oreLayerWrapper.sporadicMeta));
                }
                this.arecipes.add(new CachedVeinStatRecipe(veinName, stackListPrimary, stackListSecondary, stackListBetween, stackListSporadic));
            }
            */
        } else {
            super.loadCraftingRecipes(outputId, results);
        }
    }

    @Override
    public void loadCraftingRecipes(ItemStack stack) {
        String unlocalizedName = stack.getUnlocalizedName();
        //this.arecipes.add(new CachedMultiblockRecipe("Test", new ItemStack(Blocks.cobblestone), new ItemStack(Blocks.cobblestone), new ItemStack(Blocks.cobblestone), new ItemStack(Blocks.cobblestone)));

        /*
        if (unlocalizedName.startsWith("oredict.ore") && !unlocalizedName.startsWith("oredict.oreSmall") && !unlocalizedName.startsWith("oredict.oreBedrock")) {
            short meta = (short)stack.getItemDamage();
            for (OreLayerWrapper worldGen: GT6OreLayerHelper.mapOreLayerWrapper.values()) {
                if (meta==worldGen.primaryMeta || meta==worldGen.secondaryMeta || meta==worldGen.betweenMeta || meta==worldGen.sporadicMeta) {
                    List<ItemStack> stackListPrimary = new ArrayList<ItemStack>();
                    List<ItemStack> stackListSecondary = new ArrayList<ItemStack>();
                    List<ItemStack> stackListBetween = new ArrayList<ItemStack>();
                    List<ItemStack> stackListSporadic = new ArrayList<ItemStack>();
                    for (PrefixBlock prefixBlock: GT6OreLayerHelper.setOreNormalBasicTypes) {
                        stackListPrimary.add(new ItemStack(prefixBlock, 1, worldGen.primaryMeta));
                        stackListSecondary.add(new ItemStack(prefixBlock, 1, worldGen.secondaryMeta));
                        stackListBetween.add(new ItemStack(prefixBlock, 1, worldGen.betweenMeta));
                        stackListSporadic.add(new ItemStack(prefixBlock, 1, worldGen.sporadicMeta));
                    }
                    this.arecipes.add(new CachedVeinStatRecipe(worldGen.veinName, stackListPrimary, stackListSecondary, stackListBetween, stackListSporadic));
                }
            }
        }
        else
            super.loadCraftingRecipes(stack);
            */
    }

    @Override
    public void drawExtras(int recipe) {
    	GuiDraw.drawString("test-extra", 2, 31, 0x404040, false);

    	TileEntityRendererDispatcher.instance.getSpecialRenderer(MultiblockRender3D.dummyTileEntity).renderTileEntityAt(MultiblockRender3D.dummyTileEntity, -0.5D, -0.5D, -0.5D, 0.0F);

    	/*
        CachedVeinStatRecipe crecipe = (CachedVeinStatRecipe) this.arecipes.get(recipe);
        OreLayerWrapper oreLayer = GT6OreLayerHelper.mapOreLayerWrapper.get(crecipe.veinName);
        GuiDraw.drawString(I18n.format("gtnop.gui.nei.veinName") + ": " + getLocalizedVeinName(oreLayer.veinName).substring(31,32).toUpperCase() + getLocalizedVeinName(oreLayer.veinName).substring(32,getLocalizedVeinName(oreLayer.veinName).length() - 5), 2, 18, 0x404040, false);
        GuiDraw.drawString(I18n.format("gtnop.gui.nei.primaryOre") + ": " + oreLayer.primaryMetaName, 2, 31, 0x404040, false);
        GuiDraw.drawString(I18n.format("gtnop.gui.nei.secondaryOre") + ": " + oreLayer.secondaryMetaName, 2, 44, 0x404040, false);
        GuiDraw.drawString(I18n.format("gtnop.gui.nei.betweenOre") + ": " + oreLayer.betweenMetaName, 2, 57, 0x404040, false);
        GuiDraw.drawString(I18n.format("gtnop.gui.nei.sporadicOre") + ": " + oreLayer.sporadicMetaName, 2, 70, 0x404040, false);
        GuiDraw.drawString(I18n.format("gtnop.gui.nei.genHeight") + ": " + oreLayer.worldGenHeightRange, 2, 83, 0x404040, false);
        GuiDraw.drawString(I18n.format("gtnop.gui.nei.worldNames") + ": " + getWorldNameTranslated(oreLayer.genOverworld, oreLayer.genNether, oreLayer.genEnd, oreLayer.genGt, oreLayer.genPfaa, oreLayer.genMoon, oreLayer.genMars, oreLayer.genTwilight, oreLayer.genErebus, oreLayer.genBetweenlands, oreLayer.genAtum, oreLayer.genEnvm, oreLayer.genDeepdark, oreLayer.genAether, oreLayer.genPlanets, oreLayer.genAsteroids, oreLayer.genAlfheim, oreLayer.genTropics, oreLayer.genCandy, false, false), 2, 96, 0x404040, false);
        GuiDraw.drawString(I18n.format("gtnop.gui.nei.weightedChance") + ": " + getWeightedChance(oreLayer.randomWeight, oreLayer.genOverworld, oreLayer.genNether, oreLayer.genEnd, oreLayer.genGt, oreLayer.genPfaa, oreLayer.genMoon, oreLayer.genMars, oreLayer.genTwilight, oreLayer.genErebus, oreLayer.genBetweenlands, oreLayer.genAtum, oreLayer.genEnvm, oreLayer.genDeepdark, oreLayer.genAether, oreLayer.genPlanets, oreLayer.genAsteroids, oreLayer.genAlfheim, oreLayer.genTropics, oreLayer.genCandy), 2, 109, 0x404040, false);
        GuiDraw.drawStringR(EnumChatFormatting.BOLD + I18n.format("gtnop.gui.nei.seeAll"), getGuiWidth()-3, 5, 0x404040, false);
   		*/
    }
    /*

    public String getLocalizedVeinName(String unlocalizedName) {
        if (unlocalizedName.startsWith("ore.mix.custom."))
            return I18n.format("gtnop.ore.custom.name") + I18n.format("gtnop.ore.vein.name") + unlocalizedName.substring(15);
        else
            return I18n.format("gtnop." + unlocalizedName) + I18n.format("gtnop.ore.vein.name");
    }

    public String getWeightedChance(int randomWeight, boolean genOverworld, boolean genNether, boolean genEnd, boolean genGt, boolean genPfaa, boolean genMoon, boolean genMars, boolean genTwilight, boolean genErebus, boolean genBetweenlands, boolean genAtum, boolean genEnvm, boolean genDeepdark, boolean genAether, boolean genPlanets, boolean genAsteroids, boolean genAlfheim, boolean genTropics, boolean genCandy) {
        String weightedChance = "";
        if (genOverworld && GT6OreLayerHelper.weightPerWorld[0] != 0) {
            if (!weightedChance.isEmpty())
                weightedChance += ", ";
            weightedChance += String.format("%.2f%%", (100.0f*randomWeight)/GT6OreLayerHelper.weightPerWorld[0]);
        }
        if (genNether && GT6OreLayerHelper.weightPerWorld[1] != 0) {
            if (!weightedChance.isEmpty())
                weightedChance += ", ";
            weightedChance += String.format("%.2f%%", (100.0f*randomWeight)/GT6OreLayerHelper.weightPerWorld[1]);
        }
        if (genEnd && GT6OreLayerHelper.weightPerWorld[2] != 0) {
            if (!weightedChance.isEmpty())
                weightedChance += ", ";
            weightedChance += String.format("%.2f%%", (100.0f*randomWeight)/GT6OreLayerHelper.weightPerWorld[2]);
        }
        if (genPfaa && GT6OreLayerHelper.weightPerWorld[3] != 0) {
            if (!weightedChance.isEmpty())
                weightedChance += ", ";
            weightedChance += String.format("%.2f%%", (100.0f*randomWeight)/GT6OreLayerHelper.weightPerWorld[3]);
        }
        if (genMoon && GT6OreLayerHelper.weightPerWorld[4] != 0) {
            if (!weightedChance.isEmpty())
                weightedChance += ", ";
            weightedChance += String.format("%.2f%%", (100.0f*randomWeight)/GT6OreLayerHelper.weightPerWorld[4]);
        }
        if (genMars && GT6OreLayerHelper.weightPerWorld[5] != 0) {
            if (!weightedChance.isEmpty())
                weightedChance += ", ";
            weightedChance += String.format("%.2f%%", (100.0f*randomWeight)/GT6OreLayerHelper.weightPerWorld[5]);
        }
        if (genTwilight && GT6OreLayerHelper.weightPerWorld[6] != 0) {
            if (!weightedChance.isEmpty())
                weightedChance += ", ";
            weightedChance += String.format("%.2f%%", (100.0f*randomWeight)/GT6OreLayerHelper.weightPerWorld[6]);
        }
        if (genErebus && GT6OreLayerHelper.weightPerWorld[7] != 0) {
            if (!weightedChance.isEmpty())
                weightedChance += ", ";
            weightedChance += String.format("%.2f%%", (100.0f*randomWeight)/GT6OreLayerHelper.weightPerWorld[7]);
        }
        if (genBetweenlands && GT6OreLayerHelper.weightPerWorld[8] != 0) {
            if (!weightedChance.isEmpty())
                weightedChance += ", ";
            weightedChance += String.format("%.2f%%", (100.0f*randomWeight)/GT6OreLayerHelper.weightPerWorld[8]);
        }
        if (genAtum && GT6OreLayerHelper.weightPerWorld[9] != 0) {
            if (!weightedChance.isEmpty())
                weightedChance += ", ";
            weightedChance += String.format("%.2f%%", (100.0f*randomWeight)/GT6OreLayerHelper.weightPerWorld[9]);
        }
        if (genEnvm && GT6OreLayerHelper.weightPerWorld[10] != 0) {
            if (!weightedChance.isEmpty())
                weightedChance += ", ";
            weightedChance += String.format("%.2f%%", (100.0f*randomWeight)/GT6OreLayerHelper.weightPerWorld[10]);
        }
        if (genDeepdark && GT6OreLayerHelper.weightPerWorld[11] != 0) {
            if (!weightedChance.isEmpty())
                weightedChance += ", ";
            weightedChance += String.format("%.2f%%", (100.0f*randomWeight)/GT6OreLayerHelper.weightPerWorld[11]);
        }
        if (genAether && GT6OreLayerHelper.weightPerWorld[12] != 0) {
            if (!weightedChance.isEmpty())
                weightedChance += ", ";
            weightedChance += String.format("%.2f%%", (100.0f*randomWeight)/GT6OreLayerHelper.weightPerWorld[12]);
        }
        if (genPlanets && GT6OreLayerHelper.weightPerWorld[13] != 0) {
            if (!weightedChance.isEmpty())
                weightedChance += ", ";
            weightedChance += String.format("%.2f%%", (100.0f*randomWeight)/GT6OreLayerHelper.weightPerWorld[13]);
        }
        if (genAsteroids && GT6OreLayerHelper.weightPerWorld[14] != 0) {
            if (!weightedChance.isEmpty())
                weightedChance += ", ";
            weightedChance += String.format("%.2f%%", (100.0f*randomWeight)/GT6OreLayerHelper.weightPerWorld[14]);
        }
        if (genAlfheim && GT6OreLayerHelper.weightPerWorld[15] != 0) {
            if (!weightedChance.isEmpty())
                weightedChance += ", ";
            weightedChance += String.format("%.2f%%", (100.0f*randomWeight)/GT6OreLayerHelper.weightPerWorld[15]);
        }
        if (genTropics && GT6OreLayerHelper.weightPerWorld[16] != 0) {
            if (!weightedChance.isEmpty())
                weightedChance += ", ";
            weightedChance += String.format("%.2f%%", (100.0f*randomWeight)/GT6OreLayerHelper.weightPerWorld[16]);
        }
        if (genCandy && GT6OreLayerHelper.weightPerWorld[17] != 0) {
            if (!weightedChance.isEmpty())
                weightedChance += ", ";
            weightedChance += String.format("%.2f%%", (100.0f*randomWeight)/GT6OreLayerHelper.weightPerWorld[17]);
        }
        return weightedChance;
    }

    @Override
    public String getOutputId() {
        return "GTOrePluginVein";
    }
    */

    @Override
    public String getRecipeName() {
        return I18n.format("gtnop.gui.veinStat.name");
    }

}
