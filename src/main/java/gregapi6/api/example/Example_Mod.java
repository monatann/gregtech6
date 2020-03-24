/**
 * Copyright (c) 2019 Gregorius Techneticies
 *
 * This file is part of gregtech6.
 *
 * GregTech is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GregTech is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with gregtech6. If not, see <http://www.gnu.org/licenses/>.
 */

package gregapi6.api.example;

/**
 * @author Max Mustermann
 *
 * An example implementation for a Mod using my System. Copy and rename this File into your source Directory.
 *
 * If you have ANY Problems with the examples here, then you can contact me on the Forums or IRC.
 *
 * You may ask yourself why there are no imports on this File.
 * I decided to do that, so Beginners cannot mess up by choosing wrong imports when they copy and paste Stuff.
 * Also I avoided creating Variables, because people tend to copy them over for no reason, because they don't understand what they were for, and that they could be removed easily.
 *
 * Note: it is important to load after "gregapi_post".
 *
 * Note: There are NO TEXTURES contained in GT that correspond to the Examples. Those you will have to do or copy them yourself.
 *
 * uncomment the @cpw.mods.fml.common.Mod-Annotation for actual usage.
 */
//@cpw.mods.fml.common.Mod(modid=Example_Mod.MOD_ID, name=Example_Mod.MOD_NAME, version=Example_Mod.VERSION, dependencies="required-after:gregapi_post")
public final class Example_Mod extends gregapi6.api.Abstract_Mod {
	/** Your Mod-ID has to be LOWERCASE and without Spaces. Uppercase Chars and Spaces can create problems with Resource Packs. This is a vanilla forge "Issue". */
	public static final String MOD_ID = "insert_your_modid_here"; // <-- TODO: you need to change this to the ID of your own Mod, and then remove this Comment after you did that.
	/** This is your Mods Name */
	public static final String MOD_NAME = "Insert_your_Mod_Name_here"; // <-- TODO: you need to change this to the Name of your own Mod, and then remove this Comment after you did that.
	/** This is your Mods Version */
	public static final String VERSION = "EXAMPLE-MC1710"; // <-- TODO: you need to change this to the Version of your own Mod, and then remove this Comment after you did that.
	/** Contains a ModData Object for ID and Name. Doesn't have to be changed. */
	public static gregapi6.code.ModData MOD_DATA = new gregapi6.code.ModData(MOD_ID, MOD_NAME);

	@cpw.mods.fml.common.SidedProxy(modId = MOD_ID, clientSide = "gregapi6.api.example.Example_Proxy_Client", serverSide = "gregapi6.api.example.Example_Proxy_Server")
	public static gregapi6.api.Abstract_Proxy PROXY;

	@Override public String getModID() {return MOD_ID;}
	@Override public String getModName() {return MOD_NAME;}
	@Override public String getModNameForLog() {return "Example_Mod";}
	@Override public gregapi6.api.Abstract_Proxy getProxy() {return PROXY;}

	// Do not change these 7 Functions. Just keep them this way.
	@cpw.mods.fml.common.Mod.EventHandler public final void onPreLoad           (cpw.mods.fml.common.event.FMLPreInitializationEvent    aEvent) {onModPreInit(aEvent);}
	@cpw.mods.fml.common.Mod.EventHandler public final void onLoad              (cpw.mods.fml.common.event.FMLInitializationEvent       aEvent) {onModInit(aEvent);}
	@cpw.mods.fml.common.Mod.EventHandler public final void onPostLoad          (cpw.mods.fml.common.event.FMLPostInitializationEvent   aEvent) {onModPostInit(aEvent);}
	@cpw.mods.fml.common.Mod.EventHandler public final void onServerStarting    (cpw.mods.fml.common.event.FMLServerStartingEvent       aEvent) {onModServerStarting(aEvent);}
	@cpw.mods.fml.common.Mod.EventHandler public final void onServerStarted     (cpw.mods.fml.common.event.FMLServerStartedEvent        aEvent) {onModServerStarted(aEvent);}
	@cpw.mods.fml.common.Mod.EventHandler public final void onServerStopping    (cpw.mods.fml.common.event.FMLServerStoppingEvent       aEvent) {onModServerStopping(aEvent);}
	@cpw.mods.fml.common.Mod.EventHandler public final void onServerStopped     (cpw.mods.fml.common.event.FMLServerStoppedEvent        aEvent) {onModServerStopped(aEvent);}

	@Override
	public void onModPreInit2(cpw.mods.fml.common.event.FMLPreInitializationEvent aEvent) {
		// If you want to make yourself a new OreDict Prefix for your Component Items or similar.
		final gregapi6.oredict.OreDictPrefix tExamplePrefix = gregapi6.oredict.OreDictPrefix.createPrefix("exampleprefix"); // This newly created OreDict Prefix is named "exampleprefix", so an Aluminium Item with this Prefix would be named "exampleprefixAluminium" in the OreDict.
		tExamplePrefix.setCategoryName("Examples"); // That is what the Creative Tab of it would be named.
		tExamplePrefix.setLocalItemName("Small ", " Example"); // Generic Items will follow this naming Guideline, so for example "Small Aluminium Example" for an Aluminium Item with that Prefix.
		tExamplePrefix.setCondition(gregapi6.code.ICondition.TRUE); // The Condition under which Items of this Prefix should generate in general. In this case TRUE to have ALL the Items.
		tExamplePrefix.add(gregapi6.data.TD.Prefix.UNIFICATABLE); // OreDict Unification can apply to this Prefix.
		tExamplePrefix.add(gregapi6.data.TD.Prefix.RECYCLABLE); // Items of this can be recycled for Resources.
		tExamplePrefix.setMaterialStats(gregapi6.data.CS.U); // Any Item of this example Prefix has the value of 1 Material Unit (U), this is exactly equal to one Ingot/Dust/Gem.
		tExamplePrefix.aspects(gregapi6.data.TC.FABRICO, 1); // Thaumcraft Aspects related to this Prefix.
		tExamplePrefix.setStacksize(16, 8); // Sets the Maximum ItemStack Size of this Prefix to 16, and allows the Config to go as far down as 8 when people manually select a StackSize using it.

		// If you want to make yourself a new OreDict Material. Please look up proper IDs. So replace 32766 with a Number inside YOUR own ID Range. (you can look that up in gregapi6.oredict.OreDictMaterial.java)
		final gregapi6.oredict.OreDictMaterial tExamplium = gregapi6.oredict.OreDictMaterial.createMaterial(32766, "Examplium", "Examplium"); // Creates a Material called "Examplium".
		tExamplium.setTextures(gregapi6.render.TextureSet.SET_METALLIC); // gives this Material the Metallic Texture Set.
		tExamplium.setRGBa(100, 100, 200,   0); // Sets the RGBa Color of the Material. In this case some random blue Color.
		tExamplium.put(gregapi6.data.TD.Processing.SMITHABLE); // This Material is smithable like regular Metal things.
		tExamplium.put(gregapi6.data.TD.Processing.MELTING); // This Material can melt.
		tExamplium.put(gregapi6.data.TD.Processing.FURNACE); // This Material can be molten in a regular Furnace.
		tExamplium.put(gregapi6.data.TD.Processing.CENTRIFUGE); // This Material can be centrifuged to recycle it.
		tExamplium.put(gregapi6.data.TD.Compounds.DECOMPOSABLE); // This Material can be decomposed in general.
		tExamplium.put(gregapi6.data.TD.ItemGenerator.G_INGOT_MACHINE_ORES); // This Material is a typical Ingot, that can be used for Machine Parts, and the Material can be found as Ore too.
		tExamplium.heat(2000, 4000); // This Material melts at 2000 Kelvin and Boils at 4000 Kelvin.
		tExamplium.qual(3 // Type 3 = The Material can be used for every Type of Tool
					, 6.0 // Speed is 6.0 what is as fast as Steel at mining stuff
					, 512 // Durability is 512 what equals Steel too
					, 3); // Quality is 3 for Diamond Tool Level
		tExamplium.setMcfg(0, gregapi6.data.MT.Steel, 1*gregapi6.data.CS.U); // This Material consists out of one Unit of Steel.
		tExamplium.setOriginalMod(MOD_DATA); // Gives your Mod the credit for creating this Material.
		tExamplium.aspects(gregapi6.data.TC.METALLUM, 3); // Thaumcraft Aspects related to this Material.

		// If you want to make your Prefix an Item
		// Creates the generic Item for the new Prefix. Assets go into "/assets/insert_your_modid_here/textures/items/materialicons". And yes, every TextureSet for every Material Type has its own Folder.
		new gregapi6.item.prefixitem.PrefixItem(MOD_ID, MOD_ID, "example.meta.item.exampleprefix", tExamplePrefix, gregapi6.oredict.OreDictMaterial.MATERIAL_ARRAY);

		// If you want to make your Prefix a Block
		// Creates the generic Block for the new Prefix. Assets go into "/assets/insert_your_modid_here/textures/blocks/materialicons". And yes, every TextureSet for every Material Type has its own Folder.
		new gregapi6.block.prefixblock.PrefixBlock_(MOD_ID, MOD_ID, "example.meta.block.exampleprefix", tExamplePrefix, null, null, null, null, net.minecraft.block.material.Material.rock, net.minecraft.block.Block.soundTypeStone, gregapi6.data.CS.TOOL_pickaxe, 1.5F, 4.5F,   0,   0, 999, 0, 0, 0, 1, 1, 1, false, false, false, false, true, true, true, true, true, true, false, true, true, true, gregapi6.oredict.OreDictMaterial.MATERIAL_ARRAY);

		// You may think that you don't want to add all the PrefixItems for all the Materials, since you only need certain ones yourself and don't want a clutter like the one GregTech itself causes.
		// No Problem, you can add single Items too, if you just need those.
		// Assets go into "/assets/insert_your_modid_here/textures/items/example.multiitem.resources/..."
		// The Textures themselves are just the IDs you insert down there. So "0.png" for the Tiny Pile of Examplium Dust.
		new gregapi6.item.multiitem.MultiItemRandom(MOD_ID, "example.multiitem.resources") {@Override public void addItems() {
		// Did you know that you can use a variable from outside this Block by just making it "final"? I didn't, but now I know more and use tExamplium, even though it wouldn't be accessible otherwise.
		// And yes you can use all the 32766 possible Meta-IDs of this Item.
		addItem(    0, "Tiny Pile of Examplium Dust"    , "", gregapi6.data.OP.dustTiny  .dat(tExamplium));
		addItem(    1, "Small Pile of Examplium Dust"   , "", gregapi6.data.OP.dustSmall .dat(tExamplium));
		addItem(    2, "Examplium Dust"                 , "", gregapi6.data.OP.dust      .dat(tExamplium));
		addItem(    3, "Examplium Nugget"               , "", gregapi6.data.OP.nugget    .dat(tExamplium));
		addItem(    4, "Examplium Chunk"                , "", gregapi6.data.OP.chunkGt   .dat(tExamplium));
		addItem(    5, "Examplium Ingot"                , "", gregapi6.data.OP.ingot     .dat(tExamplium));
		addItem(    6, "Examplium Plate"                , "", gregapi6.data.OP.plate     .dat(tExamplium));
		addItem(    7, "Examplium Rod"                  , "", gregapi6.data.OP.stick     .dat(tExamplium));
		addItem(    8, "Examplium Ring"                 , "", gregapi6.data.OP.ring      .dat(tExamplium));

		// Here would be a right place to add Crafting Recipes or Machine Recipes using your new Items.

		// Crafting the Dusts together.
		gregapi6.util.CR.shapeless(gregapi6.data.OP.dust.mat(tExamplium, 1), gregapi6.util.CR.DEF, new Object[] {gregapi6.data.OP.dustTiny.dat(tExamplium), gregapi6.data.OP.dustTiny.dat(tExamplium), gregapi6.data.OP.dustTiny.dat(tExamplium), gregapi6.data.OP.dustTiny.dat(tExamplium), gregapi6.data.OP.dustTiny.dat(tExamplium), gregapi6.data.OP.dustTiny.dat(tExamplium), gregapi6.data.OP.dustTiny.dat(tExamplium), gregapi6.data.OP.dustTiny.dat(tExamplium), gregapi6.data.OP.dustTiny.dat(tExamplium)});
		gregapi6.util.CR.shapeless(gregapi6.data.OP.dust.mat(tExamplium, 1), gregapi6.util.CR.DEF, new Object[] {gregapi6.data.OP.dustSmall.dat(tExamplium), gregapi6.data.OP.dustSmall.dat(tExamplium), gregapi6.data.OP.dustSmall.dat(tExamplium), gregapi6.data.OP.dustSmall.dat(tExamplium)});

		// Smelting the Dusts into Ingots/Nuggets
		gregapi6.data.RM.add_smelting(gregapi6.util.ST.make(this, 1, 0), gregapi6.util.ST.make(this, 1, 3));
		gregapi6.data.RM.add_smelting(gregapi6.util.ST.make(this, 1, 1), gregapi6.util.ST.make(this, 1, 4));
		gregapi6.data.RM.add_smelting(gregapi6.util.ST.make(this, 1, 2), gregapi6.util.ST.make(this, 1, 5));
		}};

		// This gives you your very own 32767 Machine IDs.
		new gregapi6.block.multitileentity.MultiTileEntityRegistry("example.multitileentity");

		// Every Machine can have another Block, vanilla-material, vanilla-step-sound or Harvest Tool
		gregapi6.block.multitileentity.MultiTileEntityBlock.getOrCreate(MOD_ID, "iron"       , net.minecraft.block.material.Material.iron    , net.minecraft.block.Block.soundTypeMetal  , gregapi6.data.CS.TOOL_pickaxe  , 0, 0, 15, false, false);
		gregapi6.block.multitileentity.MultiTileEntityBlock.getOrCreate(MOD_ID, "machine"    , gregapi6.block.MaterialMachines.instance       , net.minecraft.block.Block.soundTypeMetal  , gregapi6.data.CS.TOOL_cutter   , 0, 0, 15, false, false);
		gregapi6.block.multitileentity.MultiTileEntityBlock.getOrCreate(MOD_ID, "machine"    , gregapi6.block.MaterialMachines.instance       , net.minecraft.block.Block.soundTypeMetal  , gregapi6.data.CS.TOOL_wrench   , 0, 0, 15, false, false);
	}

	@Override
	public void onModInit2(cpw.mods.fml.common.event.FMLInitializationEvent aEvent) {
		// Gets your initialised Registry.
		gregapi6.block.multitileentity.MultiTileEntityRegistry tExampleRegistry = gregapi6.block.multitileentity.MultiTileEntityRegistry.getRegistry("example.multitileentity");

		// Gets your Examplium by Name if you initialised it above.
		gregapi6.oredict.OreDictMaterial tExamplium = gregapi6.oredict.OreDictMaterial.get("Examplium");

		// YES this Registry Stuff can and should be in @Init. That way, all the OreDict Items needed for Crafting Recipes are available and registered.

		// Take the Metal Block from the Set, that you initialised above in @PreInit.
		gregapi6.block.multitileentity.MultiTileEntityBlock tMetalBlock = gregapi6.block.multitileentity.MultiTileEntityBlock.getOrCreate(MOD_ID, "iron", net.minecraft.block.material.Material.iron, net.minecraft.block.Block.soundTypeMetal, gregapi6.data.CS.TOOL_pickaxe, 0, 0, 15, false, false);
		// Makes an Examplium Chest out of your Example Material.
		// Note: the Crafting Recipe only works in conjunction with GT since you don't have the Stick, Ring and Plate PrefixItems.
		tExampleRegistry.add(tExamplium.getLocal() + " Chest", "Chests", 0, 0, gregapi6.block.multitileentity.example.MultiTileEntityChest.class, 0, 16, tMetalBlock, gregapi6.util.UT.NBT.make(gregapi6.data.CS.NBT_MATERIAL, tExamplium, gregapi6.data.CS.NBT_INV_SIZE, 54, gregapi6.data.CS.NBT_TEXTURE, "metalchest", gregapi6.data.CS.NBT_HARDNESS, 6.0F, gregapi6.data.CS.NBT_RESISTANCE, 6.0F, gregapi6.data.CS.NBT_COLOR, gregapi6.util.UT.Code.getRGBInt(tExamplium.fRGBaSolid)), "sPw", "RSR", "PPP", 'P', gregapi6.data.OP.plate.dat(tExamplium), 'R', gregapi6.data.OP.ring.dat(tExamplium), 'S', gregapi6.data.OP.stick.dat(tExamplium));

		// Take the Machine Block from the Set, that you initialised above in @PreInit.
		gregapi6.block.multitileentity.MultiTileEntityBlock tMachineBlock = gregapi6.block.multitileentity.MultiTileEntityBlock.getOrCreate(MOD_ID, "machine", gregapi6.block.MaterialMachines.instance, net.minecraft.block.Block.soundTypeMetal, gregapi6.data.CS.TOOL_wrench, 0, 0, 15, false, false);
		// Makes a vanilla Furnace out of your Example Material.
		// Note: the Crafting Recipe only works in conjunction with GT since you don't have all the PrefixItems.
		// GUI has to be at: "/assets/insert_your_modid_here/textures/gui/machines/Oven.png"
		// Block Textures have to be at: "/assets/gregtech/textures/blocks/machines/basicmachines/oven/..." Yes that is not a Typo, it is actually the GregTech Mod-ID in that path. I noticed that flaw way too late to fix it. And look at how GT has the Textures for its Oven for Details.
		tExampleRegistry.add("Oven ("+tExamplium.getLocal()+")", "Example Mod", 1, 0, gregapi6.tileentity.machines.MultiTileEntityBasicMachine.class, tExamplium.mToolQuality, 16, tMachineBlock, gregapi6.util.UT.NBT.make(gregapi6.data.CS.NBT_MATERIAL, tExamplium, gregapi6.data.CS.NBT_HARDNESS, 6.0F, gregapi6.data.CS.NBT_RESISTANCE, 6.0F, gregapi6.data.CS.NBT_COLOR, gregapi6.util.UT.Code.getRGBInt(tExamplium.fRGBaSolid), gregapi6.data.CS.NBT_INPUT, 128, gregapi6.data.CS.NBT_TEXTURE, "oven", gregapi6.data.CS.NBT_GUI, MOD_ID+":textures/gui/machines/Oven", gregapi6.data.CS.NBT_ENERGY_ACCEPTED, gregapi6.data.TD.Energy.HU, gregapi6.data.CS.NBT_RECIPEMAP, gregapi6.data.RM.Furnace, gregapi6.data.CS.NBT_EFFICIENCY, 10000, gregapi6.data.CS.NBT_INV_SIDE_IN, gregapi6.data.CS.SBIT_L, gregapi6.data.CS.NBT_INV_SIDE_AUTO_IN, gregapi6.data.CS.SIDE_LEFT, gregapi6.data.CS.NBT_INV_SIDE_OUT, gregapi6.data.CS.SBIT_R, gregapi6.data.CS.NBT_INV_SIDE_AUTO_OUT, gregapi6.data.CS.SIDE_RIGHT, gregapi6.data.CS.NBT_ENERGY_ACCEPTED_SIDES, gregapi6.data.CS.SBIT_D), "wMh", "BCB", 'M', gregapi6.data.OP.casingMachine.dat(tExamplium), 'B', gregapi6.util.ST.make(net.minecraft.init.Blocks.brick_block, 1, gregapi6.data.CS.W), 'C', gregapi6.data.OP.plateDouble.dat(gregapi6.data.ANY.Cu));

		// The above just makes a vanilla Furnace that is made of Examplium, you cannot obtain the Examplium right now. For that you will need your own Recipe Handler.
		gregapi6.recipes.Recipe.RecipeMap tRecipeMap = new gregapi6.recipes.Recipe.RecipeMap(null, "example.recipe.exampliumfurnace", "Examplium Furnace", null, 0, 1, MOD_ID+":textures/gui/machines/ExampliumFurnace",/*IN-OUT-MIN-ITEM=*/ 1, 1, 1,/*IN-OUT-MIN-FLUID=*/ 0, 0, 0,/*MIN*/ 0,/*AMP=*/ 1, "", 1, "", true, true, true, true, false, 0);		// This Recipe will turn an Iron Ingot into an Examplium Ingot at 64 Units per Tick for 128 Ticks.
		tRecipeMap.addRecipe1(true, 64, 128, gregapi6.util.OM.ingot(gregapi6.data.MT.Fe           ), gregapi6.util.OM.ingot(tExamplium));
		// This Recipe will turn a Wrought Iron Ingot into an Examplium Ingot at 64 Units per Tick for 96 Ticks. So a slightly cheaper variant
		tRecipeMap.addRecipe1(true, 64,  96, gregapi6.util.OM.ingot(gregapi6.data.MT.WroughtIron  ), gregapi6.util.OM.ingot(tExamplium));
		// This Recipe will turn a Steel Ingot into an Examplium Ingot at 64 Units per Tick for 64 Ticks. So a cheaper variant
		tRecipeMap.addRecipe1(true, 64,  64, gregapi6.util.OM.ingot(gregapi6.data.MT.Steel        ), gregapi6.util.OM.ingot(tExamplium));

		// Makes an Examplium Furnace out of Iron.
		// Note: the Crafting Recipe only works in conjunction with GT since you don't have all the PrefixItems.
		// GUI has to be at: "/assets/insert_your_modid_here/textures/gui/machines/Oven.png"
		// Block Textures have to be at: "/assets/gregtech/textures/blocks/machines/basicmachines/exampliumfurnace/..." Yes that is not a Typo, it is actually the GregTech Mod-ID in that path. I noticed that flaw way too late to fix it. And look at how GT has the Textures for its Oven for Details.
		tExampleRegistry.add("Examplium Furnace"            , "Example Mod", 2, 0, gregapi6.tileentity.machines.MultiTileEntityBasicMachine          .class, gregapi6.data.MT.Fe      .mToolQuality, 16, tMachineBlock, gregapi6.util.UT.NBT.make(gregapi6.data.CS.NBT_MATERIAL, gregapi6.data.MT.Fe   , gregapi6.data.CS.NBT_HARDNESS, 6.0F, gregapi6.data.CS.NBT_RESISTANCE, 6.0F, gregapi6.data.CS.NBT_COLOR, gregapi6.util.UT.Code.getRGBInt(gregapi6.data.MT.Fe    .fRGBaSolid), gregapi6.data.CS.NBT_INPUT, 128, gregapi6.data.CS.NBT_TEXTURE, "exampliumfurnace", gregapi6.data.CS.NBT_GUI, MOD_ID+":textures/gui/machines/ExampliumFurnace", gregapi6.data.CS.NBT_ENERGY_ACCEPTED, gregapi6.data.TD.Energy.HU, gregapi6.data.CS.NBT_RECIPEMAP, tRecipeMap, gregapi6.data.CS.NBT_EFFICIENCY, 10000, gregapi6.data.CS.NBT_INV_SIDE_IN, gregapi6.data.CS.SBIT_L, gregapi6.data.CS.NBT_INV_SIDE_AUTO_IN, gregapi6.data.CS.SIDE_LEFT, gregapi6.data.CS.NBT_INV_SIDE_OUT, gregapi6.data.CS.SBIT_R, gregapi6.data.CS.NBT_INV_SIDE_AUTO_OUT, gregapi6.data.CS.SIDE_RIGHT, gregapi6.data.CS.NBT_ENERGY_ACCEPTED_SIDES, gregapi6.data.CS.SBIT_D), "wMh", "BCB", 'M', gregapi6.data.OP.casingMachineDouble.dat(gregapi6.data.MT.Fe        ), 'B', gregapi6.util.ST.make(net.minecraft.init.Blocks.brick_block, 1, gregapi6.data.CS.W), 'C', gregapi6.data.OP.plateDouble.dat(gregapi6.data.ANY.Cu));

		// Makes an electric Examplium Furnace out of Steel.
		// Note: the Crafting Recipe only works in conjunction with GT since you don't have all the PrefixItems.
		tExampleRegistry.add("Electric Examplium Furnace"   , "Example Mod", 3, 0, gregapi6.tileentity.machines.MultiTileEntityBasicMachineElectric  .class, gregapi6.data.MT.Steel   .mToolQuality, 16, tMachineBlock, gregapi6.util.UT.NBT.make(gregapi6.data.CS.NBT_MATERIAL, gregapi6.data.MT.Steel    , gregapi6.data.CS.NBT_HARDNESS, 6.0F, gregapi6.data.CS.NBT_RESISTANCE, 6.0F, gregapi6.data.CS.NBT_COLOR, gregapi6.util.UT.Code.getRGBInt(gregapi6.data.MT.Steel .fRGBaSolid), gregapi6.data.CS.NBT_INPUT, 128, gregapi6.data.CS.NBT_TEXTURE, "exampliumfurnace", gregapi6.data.CS.NBT_GUI, MOD_ID+":textures/gui/machines/ExampliumFurnace", gregapi6.data.CS.NBT_ENERGY_ACCEPTED, gregapi6.data.TD.Energy.EU, gregapi6.data.CS.NBT_RECIPEMAP, tRecipeMap, gregapi6.data.CS.NBT_EFFICIENCY, 10000, gregapi6.data.CS.NBT_INV_SIDE_IN, gregapi6.data.CS.SBIT_L, gregapi6.data.CS.NBT_INV_SIDE_AUTO_IN, gregapi6.data.CS.SIDE_LEFT, gregapi6.data.CS.NBT_INV_SIDE_OUT, gregapi6.data.CS.SBIT_R, gregapi6.data.CS.NBT_INV_SIDE_AUTO_OUT, gregapi6.data.CS.SIDE_RIGHT, gregapi6.data.CS.NBT_ENERGY_ACCEPTED_SIDES, gregapi6.data.CS.SBIT_D), "wMh", "BCB", 'M', gregapi6.data.OP.casingMachineDouble.dat(gregapi6.data.MT.Steel ), 'B', gregapi6.util.ST.make(net.minecraft.init.Blocks.brick_block, 1, gregapi6.data.CS.W), 'C', gregapi6.data.MT.DATA.CIRCUITS[1]);

		// Makes a Flux Examplium Furnace out of Invar.
		// Note: the Crafting Recipe only works in conjunction with GT since you don't have all the PrefixItems.
		// Note: Since it takes RF, the Input has to be 4 times larger.
		tExampleRegistry.add("Flux Examplium Furnace"       , "Example Mod", 4, 0, gregapi6.tileentity.machines.MultiTileEntityBasicMachineFlux      .class, gregapi6.data.MT.Invar   .mToolQuality, 16, tMachineBlock, gregapi6.util.UT.NBT.make(gregapi6.data.CS.NBT_MATERIAL, gregapi6.data.MT.Invar    , gregapi6.data.CS.NBT_HARDNESS, 6.0F, gregapi6.data.CS.NBT_RESISTANCE, 6.0F, gregapi6.data.CS.NBT_COLOR, gregapi6.util.UT.Code.getRGBInt(gregapi6.data.MT.Invar .fRGBaSolid), gregapi6.data.CS.NBT_INPUT, 512, gregapi6.data.CS.NBT_TEXTURE, "exampliumfurnace", gregapi6.data.CS.NBT_GUI, MOD_ID+":textures/gui/machines/ExampliumFurnace", gregapi6.data.CS.NBT_ENERGY_ACCEPTED, gregapi6.data.TD.Energy.RF, gregapi6.data.CS.NBT_RECIPEMAP, tRecipeMap, gregapi6.data.CS.NBT_EFFICIENCY, 10000, gregapi6.data.CS.NBT_INV_SIDE_IN, gregapi6.data.CS.SBIT_L, gregapi6.data.CS.NBT_INV_SIDE_AUTO_IN, gregapi6.data.CS.SIDE_LEFT, gregapi6.data.CS.NBT_INV_SIDE_OUT, gregapi6.data.CS.SBIT_R, gregapi6.data.CS.NBT_INV_SIDE_AUTO_OUT, gregapi6.data.CS.SIDE_RIGHT, gregapi6.data.CS.NBT_ENERGY_ACCEPTED_SIDES, gregapi6.data.CS.SBIT_D), "wMh", "BCB", 'M', gregapi6.data.OP.casingMachineDouble.dat(gregapi6.data.MT.Invar ), 'B', gregapi6.util.ST.make(net.minecraft.init.Blocks.brick_block, 1, gregapi6.data.CS.W), 'C', gregapi6.data.MT.DATA.CIRCUITS[3]);

		// Makes Examplium Fluid Pipes, which are as good as Stainless Steel ones of GT.
		gregapi6.tileentity.connectors.MultiTileEntityPipeFluid.addFluidPipes(30, 0, 250, true, true, false, true, false, true, true, tExampleRegistry, tMachineBlock, gregapi6.tileentity.connectors.MultiTileEntityPipeFluid.class, (long)(tExamplium.mMeltingPoint * 1.25), tExamplium);

		// Makes Iron Item Pipes, which are as good as Brass ones. Why not Examplium? Because the Recipes would conflict with the Fluid Pipe above in that case.
		gregapi6.tileentity.connectors.MultiTileEntityPipeItem.addItemPipes(100, 0, 32768, 1, true, true, tExampleRegistry, tMetalBlock, gregapi6.tileentity.connectors.MultiTileEntityPipeItem.class, gregapi6.data.MT.Fe);

		// Take the Wire Block from the Set, that you initialised above in @PreInit.
		gregapi6.block.multitileentity.MultiTileEntityBlock tWireBlock = gregapi6.block.multitileentity.MultiTileEntityBlock.getOrCreate(MOD_ID, "machine", gregapi6.block.MaterialMachines.instance, net.minecraft.block.Block.soundTypeMetal, gregapi6.data.CS.TOOL_cutter, 0, 0, 15, false, false);

		// Makes Examplium Wires, which can carry twice the Voltage of Steel and have a lower loss.
		gregapi6.tileentity.connectors.MultiTileEntityWireElectric.addElectricWires(50, 0, gregapi6.data.CS.VMAX[4], 1, 2, 1, true, false, true, tExampleRegistry, tWireBlock, gregapi6.tileentity.connectors.MultiTileEntityWireElectric.class, tExamplium);
	}

	@Override
	public void onModPostInit2(cpw.mods.fml.common.event.FMLPostInitializationEvent aEvent) {
		// Insert your PostInit Code here and not above
	}

	@Override
	public void onModServerStarting2(cpw.mods.fml.common.event.FMLServerStartingEvent aEvent) {
		// Insert your ServerStarting Code here and not above
	}

	@Override
	public void onModServerStarted2(cpw.mods.fml.common.event.FMLServerStartedEvent aEvent) {
		// Insert your ServerStarted Code here and not above
	}

	@Override
	public void onModServerStopping2(cpw.mods.fml.common.event.FMLServerStoppingEvent aEvent) {
		// Insert your ServerStopping Code here and not above
	}

	@Override
	public void onModServerStopped2(cpw.mods.fml.common.event.FMLServerStoppedEvent aEvent) {
		// Insert your ServerStopped Code here and not above
	}
}
