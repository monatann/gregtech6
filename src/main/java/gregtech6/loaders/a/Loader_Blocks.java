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

package gregtech6.loaders.a;

import static gregapi6.data.CS.*;

import cpw.mods.fml.common.event.FMLInterModComms;
import gregapi6.block.MaterialGas;
import gregapi6.block.MaterialOil;
import gregapi6.block.fluid.BlockBaseFluid;
import gregapi6.data.ANY;
import gregapi6.data.CS.BlocksGT;
import gregapi6.data.CS.ItemsGT;
import gregapi6.data.FL;
import gregapi6.data.MD;
import gregapi6.data.MT;
import gregapi6.data.OP;
import gregapi6.data.TC;
import gregapi6.old.Textures;
import gregapi6.util.CR;
import gregapi6.util.ST;
import gregapi6.util.UT;
import gregtech6.blocks.BlockAsphalt;
import gregtech6.blocks.BlockBaleCrop;
import gregtech6.blocks.BlockBaleGrass;
import gregtech6.blocks.BlockCFoam;
import gregtech6.blocks.BlockCFoamFresh;
import gregtech6.blocks.BlockConcrete;
import gregtech6.blocks.BlockConcreteReinforced;
import gregtech6.blocks.BlockDiggable;
import gregtech6.blocks.BlockGlassClear;
import gregtech6.blocks.BlockGlassGlow;
import gregtech6.blocks.BlockGrass;
import gregtech6.blocks.BlockPath;
import gregtech6.blocks.BlockRockOres;
import gregtech6.blocks.BlockSands;
import gregtech6.blocks.fluids.BlockOcean;
import gregtech6.blocks.fluids.BlockRiver;
import gregtech6.blocks.fluids.BlockSwamp;
import gregtech6.blocks.plants.BlockFlowersA;
import gregtech6.blocks.plants.BlockFlowersB;
import gregtech6.blocks.plants.BlockGlowtus;
import gregtech6.blocks.tool.BlockBarsAdamantium;
import gregtech6.blocks.tool.BlockBarsSteel;
import gregtech6.blocks.tool.BlockBarsTitanium;
import gregtech6.blocks.tool.BlockBarsTungstenSteel;
import gregtech6.blocks.tool.BlockBarsWood;
import gregtech6.blocks.tool.BlockLongDistPipe;
import gregtech6.blocks.tool.BlockLongDistWire;
import gregtech6.blocks.tool.BlockSpikeFancy;
import gregtech6.blocks.tool.BlockSpikeMetal;
import gregtech6.blocks.tool.BlockSpikeSharp;
import gregtech6.blocks.tool.BlockSpikeSuper;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.potion.Potion;

public class Loader_Blocks implements Runnable {
	@Override
	public void run() {
		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.CFoam                               = new BlockCFoam                ("gt6.block.cfoam"));
		BlocksGT.CFoamFresh                                                     = new BlockCFoamFresh           ("gt6.block.cfoam.fresh");
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.CFoam, 1, W), F, TC.stack(TC.TERRA, 1), TC.stack(TC.TUTAMEN, 1));
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.CFoamFresh, 1, W), F, TC.stack(TC.TERRA, 1), TC.stack(TC.TUTAMEN, 1));

		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.Asphalt                             = new BlockAsphalt              ("gt6.block.asphalt"));
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.Asphalt, 1, W), F, TC.stack(TC.TERRA, 1), TC.stack(TC.ITER, 1));
		ItemsGT.addNEIRedirects(BlocksGT.Asphalt);

		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.Concrete                            = new BlockConcrete             ("gt6.block.concrete"));
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.Concrete, 1, W), F, TC.stack(TC.TERRA, 1), TC.stack(TC.FABRICO, 1));
		ItemsGT.addNEIRedirects(BlocksGT.Concrete);

		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.ConcreteReinforced                  = new BlockConcreteReinforced   ("gt6.block.concrete.reinforced"));
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.ConcreteReinforced, 1, W), F, TC.stack(TC.TERRA, 1), TC.stack(TC.FABRICO, 1), TC.stack(TC.TUTAMEN, 1));
		ItemsGT.addNEIRedirects(BlocksGT.ConcreteReinforced);
		for (byte i = 0; i < 16; i++) CR.shaped(ST.make(BlocksGT.ConcreteReinforced, 1, i), CR.DEF_MIR, "Se", "X ", 'X', ST.make(BlocksGT.Concrete, 1, i), 'S', OP.stick.dat(ANY.Iron));

		BlocksGT.Glass                                                          = new BlockGlassClear           ("gt6.block.glass");
		BlocksGT.GlowGlass                                                      = new BlockGlassGlow            ("gt6.block.glass.glow");
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.Glass    , 1, W), F, TC.stack(TC.VITREUS, 2));
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.GlowGlass, 1, W), F, TC.stack(TC.VITREUS, 2), TC.stack(TC.LUX, 2));

		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.Diggables                           = new BlockDiggable             ("gt6.block.diggable"));
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.Diggables, 1, W), F, TC.stack(TC.TERRA, 2));

		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.Grass                               = new BlockGrass                ("gt6.block.grass"));
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.Grass, 1, W), F, TC.stack(TC.TERRA, 2), TC.stack(TC.HERBA, 2));

		BlocksGT.Paths                                                          = new BlockPath                 ("gt6.block.paths");
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.Paths, 1, W), F, TC.stack(TC.TERRA, 2));

		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.RockOres                            = new BlockRockOres             ("gt6.block.rockores"));

		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.Sands                               = new BlockSands                ("gt6.block.sands"));
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.Sands, 1, W), F, TC.stack(TC.TERRA, 1));


		BlocksGT.Spikes_Sharp                                                   = new BlockSpikeSharp           ("gt6.block.spikes.sharp");
		BlocksGT.Spikes_Super                                                   = new BlockSpikeSuper           ("gt6.block.spikes.super");
		BlocksGT.Spikes_Metal                                                   = new BlockSpikeMetal           ("gt6.block.spikes.metal");
		BlocksGT.Spikes_Fancy                                                   = new BlockSpikeFancy           ("gt6.block.spikes.fancy");
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.Spikes_Sharp, 1, W), F, TC.stack(TC.VINCULUM, 4), TC.stack(TC.METALLUM, 4));
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.Spikes_Super, 1, W), F, TC.stack(TC.VINCULUM, 8), TC.stack(TC.METALLUM, 4));
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.Spikes_Metal, 1, W), F, TC.stack(TC.VINCULUM, 4), TC.stack(TC.VENENUM, 4));
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.Spikes_Fancy, 1, W), F, TC.stack(TC.VINCULUM, 4), TC.stack(TC.LUCRUM, 4));


		BlocksGT.Bars_Wood                                                      = new BlockBarsWood             ("gt6.block.bars.wood");
		BlocksGT.Bars_Steel                                                     = new BlockBarsSteel            ("gt6.block.bars.steel");
		BlocksGT.Bars_Titanium                                                  = new BlockBarsTitanium         ("gt6.block.bars.titanium");
		BlocksGT.Bars_TungstenSteel                                             = new BlockBarsTungstenSteel    ("gt6.block.bars.tungstensteel");
		BlocksGT.Bars_Adamantium                                                = new BlockBarsAdamantium       ("gt6.block.bars.adamantium");
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.Bars_Wood         , 1, W), F, TC.stack(TC.VINCULUM, 2), TC.stack(TC.ARBOR, 4));
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.Bars_Steel        , 1, W), F, TC.stack(TC.VINCULUM, 4), TC.stack(TC.METALLUM, 3));
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.Bars_Titanium     , 1, W), F, TC.stack(TC.VINCULUM, 6), TC.stack(TC.METALLUM, 5));
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.Bars_TungstenSteel, 1, W), F, TC.stack(TC.VINCULUM, 8), TC.stack(TC.METALLUM, 7));
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.Bars_Adamantium   , 1, W), F, TC.stack(TC.VINCULUM,10), TC.stack(TC.METALLUM,10));


		BlocksGT.FlowersA                                                       = new BlockFlowersA             ("gt6.block.flower.a");
		BlocksGT.FlowersB                                                       = new BlockFlowersB             ("gt6.block.flower.b");
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make((Block)BlocksGT.FlowersA, 1, W), F, TC.stack(TC.HERBA, 2));
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make((Block)BlocksGT.FlowersB, 1, W), F, TC.stack(TC.HERBA, 2));

		BlocksGT.Glowtus                                                        = new BlockGlowtus              ("gt6.block.lilypad.glowtus");
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.Glowtus, 1, W), F, TC.stack(TC.HERBA, 2), TC.stack(TC.LUX, 2));

		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.BalesGrass                          = new BlockBaleGrass            ("gt6.block.bale.grass"));
		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.BalesCrop                           = new BlockBaleCrop             ("gt6.block.bale.crop"));
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.BalesGrass, 1, W), F, TC.stack(TC.MESSIS, 4));
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.BalesCrop , 1, W), F, TC.stack(TC.MESSIS, 4));

		BlocksGT.River                                                          = new BlockRiver                ("gt6.block.river", FL.River_Water.fluid());
		BlocksGT.Ocean                                                          = new BlockOcean                ("gt6.block.ocean", FL.Ocean      .fluid());
		BlocksGT.Swamp                                                          = new BlockSwamp                ("gt6.block.swamp", FL.Dirty_Water.fluid()).addEffect(Potion.hunger.id, 300, 0).addEffect(Potion.confusion.id, 120, 0);
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.Ocean         , 1, W), F, TC.stack(TC.AQUA, 3), TC.stack(TC.TEMPESTAS, 3));
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.Swamp         , 1, W), F, TC.stack(TC.AQUA, 3), TC.stack(TC.VENENUM, 1));
		NBTTagList tNBTList = new NBTTagList();
		tNBTList.appendTag(new NBTTagString(ST.regName(BlocksGT.River)));
		tNBTList.appendTag(new NBTTagString(ST.regName(BlocksGT.Ocean)));
		FMLInterModComms.sendMessage(MD.IC2C.mID, "watergen", UT.NBT.make("blocks", tNBTList));

		BlocksGT.OilExtraHeavy                                                  = new BlockBaseFluid            ("gt6.block.fluid.oil.extraheavy"    , FL.Oil_ExtraHeavy.fluid(), 1000, MaterialOil.instance).addEffect(Potion.poison.id, 300, 0).addEffect(Potion.confusion.id, 120, 0).addEffect(Potion.blindness.id, 60, 1);
		BlocksGT.OilHeavy                                                       = new BlockBaseFluid            ("gt6.block.fluid.oil.heavy"         , FL.Oil_Heavy     .fluid(), 1000, MaterialOil.instance).addEffect(Potion.poison.id, 300, 0).addEffect(Potion.confusion.id, 120, 0).addEffect(Potion.blindness.id, 60, 1);
		BlocksGT.OilMedium                                                      = new BlockBaseFluid            ("gt6.block.fluid.oil.medium"        , FL.Oil_Medium    .fluid(), 1000, MaterialOil.instance).addEffect(Potion.poison.id, 300, 0).addEffect(Potion.confusion.id, 120, 0).addEffect(Potion.blindness.id, 60, 1);
		BlocksGT.OilLight                                                       = new BlockBaseFluid            ("gt6.block.fluid.oil.light"         , FL.Oil_Light     .fluid(), 1000, MaterialOil.instance).addEffect(Potion.poison.id, 300, 0).addEffect(Potion.confusion.id, 120, 0).addEffect(Potion.blindness.id, 60, 1);
		BlocksGT.GasNatural                                                     = new BlockBaseFluid            ("gt6.block.fluid.gas.natural"       , FL.Gas_Natural   .fluid(), 1000, MaterialGas.instance).addEffect(Potion.poison.id, 300, 0).addEffect(Potion.confusion.id, 120, 0);
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.OilExtraHeavy , 1, W), F, TC.stack(TC.AQUA, 1), TC.stack(TC.IGNIS, 1), TC.stack(TC.POTENTIA, 3));
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.OilHeavy      , 1, W), F, TC.stack(TC.AQUA, 1), TC.stack(TC.IGNIS, 1), TC.stack(TC.POTENTIA, 2));
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.OilMedium     , 1, W), F, TC.stack(TC.AQUA, 1), TC.stack(TC.IGNIS, 1), TC.stack(TC.POTENTIA, 1));
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.OilLight      , 1, W), F, TC.stack(TC.AQUA, 1), TC.stack(TC.IGNIS, 1), TC.stack(TC.LUX, 1));
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.GasNatural    , 1, W), F, TC.stack(TC.AER , 1), TC.stack(TC.IGNIS, 1), TC.stack(TC.LUX, 1));

		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.LongDistWire01                      = new BlockLongDistWire         ("gt6.block.longdistwire.01", Textures.BlockIcons.LONG_DIST_WIRES_01, new byte[] {4, 4, 5, 6, 6, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8, 8}));

		CR.shaped(ST.make(BlocksGT.LongDistWire01, 1, 0), CR.DEF_REV_NCC, "RSR", "PWP", "RSR", 'R', OP.plate.dat(MT.Rubber), 'P', OP.plateCurved.dat(ANY.Cu), 'S', OP.plateCurved.dat(MT.Al), 'W', OP.wireGt16.dat(MT.Sn));
		CR.shaped(ST.make(BlocksGT.LongDistWire01, 1, 1), CR.DEF_REV_NCC, "RSR", "PWP", "RSR", 'R', OP.plate.dat(MT.Rubber), 'P', OP.plateCurved.dat(ANY.Cu), 'S', OP.plateCurved.dat(MT.Al), 'W', OP.wireGt16.dat(MT.Pb));
		CR.shaped(ST.make(BlocksGT.LongDistWire01, 1, 2), CR.DEF_REV_NCC, "RSR", "PWP", "RSR", 'R', OP.plate.dat(MT.Rubber), 'P', OP.plateCurved.dat(ANY.Cu), 'S', OP.plateCurved.dat(MT.Al), 'W', OP.wireGt16.dat(ANY.Cu));
		CR.shaped(ST.make(BlocksGT.LongDistWire01, 1, 3), CR.DEF_REV_NCC, "RSR", "PWP", "RSR", 'R', OP.plate.dat(MT.Rubber), 'P', OP.plateCurved.dat(ANY.Cu), 'S', OP.plateCurved.dat(MT.Al), 'W', OP.wireGt16.dat(MT.Ag));
		CR.shaped(ST.make(BlocksGT.LongDistWire01, 1, 4), CR.DEF_REV_NCC, "RSR", "PWP", "RSR", 'R', OP.plate.dat(MT.Rubber), 'P', OP.plateCurved.dat(ANY.Cu), 'S', OP.plateCurved.dat(MT.Al), 'W', OP.wireGt16.dat(MT.Au));
		CR.shaped(ST.make(BlocksGT.LongDistWire01, 1, 5), CR.DEF_REV_NCC, "RSR", "PWP", "RSR", 'R', OP.plate.dat(MT.Rubber), 'P', OP.plateCurved.dat(ANY.Cu), 'S', OP.plateCurved.dat(MT.Al), 'W', OP.wireGt16.dat(MT.Electrum));
		CR.shaped(ST.make(BlocksGT.LongDistWire01, 1, 6), CR.DEF_REV_NCC, "RSR", "PWP", "RSR", 'R', OP.plate.dat(MT.Rubber), 'P', OP.plateCurved.dat(ANY.Cu), 'S', OP.plateCurved.dat(MT.Al), 'W', OP.wireGt16.dat(MT.BlueAlloy));
		CR.shaped(ST.make(BlocksGT.LongDistWire01, 1, 7), CR.DEF_REV_NCC, "RSR", "PWP", "RSR", 'R', OP.plate.dat(MT.Rubber), 'P', OP.plateCurved.dat(ANY.Cu), 'S', OP.plateCurved.dat(MT.Al), 'W', OP.wireGt16.dat(MT.ElectrotineAlloy));
		CR.shaped(ST.make(BlocksGT.LongDistWire01, 1, 8), CR.DEF_REV_NCC, "RSR", "PWP", "RSR", 'R', OP.plate.dat(MT.Rubber), 'P', OP.plateCurved.dat(ANY.Cu), 'S', OP.plateCurved.dat(MT.Al), 'W', OP.wireGt16.dat(ANY.Steel));
		CR.shaped(ST.make(BlocksGT.LongDistWire01, 1, 9), CR.DEF_REV_NCC, "RSR", "PWP", "RSR", 'R', OP.plate.dat(MT.Rubber), 'P', OP.plateCurved.dat(ANY.Cu), 'S', OP.plateCurved.dat(MT.Al), 'W', OP.wireGt16.dat(MT.Al));
		CR.shaped(ST.make(BlocksGT.LongDistWire01, 1,10), CR.DEF_REV_NCC, "RSR", "PWP", "RSR", 'R', OP.plate.dat(MT.Rubber), 'P', OP.plateCurved.dat(ANY.Cu), 'S', OP.plateCurved.dat(MT.Al), 'W', OP.wireGt16.dat(ANY.W));
		CR.shaped(ST.make(BlocksGT.LongDistWire01, 1,11), CR.DEF_REV_NCC, "RSR", "PWP", "RSR", 'R', OP.plate.dat(MT.Rubber), 'P', OP.plateCurved.dat(ANY.Cu), 'S', OP.plateCurved.dat(MT.Al), 'W', OP.wireGt16.dat(MT.TungstenSteel));
		CR.shaped(ST.make(BlocksGT.LongDistWire01, 1,12), CR.DEF_REV_NCC, "RSR", "PWP", "RSR", 'R', OP.plate.dat(MT.Rubber), 'P', OP.plateCurved.dat(ANY.Cu), 'S', OP.plateCurved.dat(MT.Al), 'W', OP.wireGt16.dat(MT.Os));
		CR.shaped(ST.make(BlocksGT.LongDistWire01, 1,13), CR.DEF_REV_NCC, "RSR", "PWP", "RSR", 'R', OP.plate.dat(MT.Rubber), 'P', OP.plateCurved.dat(ANY.Cu), 'S', OP.plateCurved.dat(MT.Al), 'W', OP.wireGt16.dat(MT.Pt));
		CR.shaped(ST.make(BlocksGT.LongDistWire01, 1,14), CR.DEF_REV_NCC, "RSR", "PWP", "RSR", 'R', OP.plate.dat(MT.Rubber), 'P', OP.plateCurved.dat(ANY.Cu), 'S', OP.plateCurved.dat(MT.Al), 'W', OP.wireGt16.dat(MT.Nq));
		CR.shaped(ST.make(BlocksGT.LongDistWire01, 1,15), CR.DEF_REV_NCC, "RSR", "PWP", "RSR", 'R', OP.plate.dat(MT.Rubber), 'P', OP.plateCurved.dat(ANY.Cu), 'S', OP.plateCurved.dat(MT.Al), 'W', OP.wireGt16.dat(MT.Graphene));

		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.LongDistPipe01                      = new BlockLongDistPipe         ("gt6.block.longdistpipe.01", Textures.BlockIcons.LONG_DIST_PIPES_01, new long[] {-1, MT.StainlessSteel.mMeltingPoint, MT.W.mMeltingPoint, MT.Ad.mMeltingPoint, MT.Draconium.mMeltingPoint, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}));

		CR.shaped(ST.make(BlocksGT.LongDistPipe01, 1, 0), CR.DEF_REV_NCC, "SPS", "PwP", "SPS", 'P', OP.pipeMedium.dat(MT.Electrum       ), 'S', OP.plate.dat(MT.Plastic));
		CR.shaped(ST.make(BlocksGT.LongDistPipe01, 1, 1), CR.DEF_REV_NCC, "SPS", "PwP", "SPS", 'P', OP.pipeMedium.dat(MT.StainlessSteel ), 'S', OP.plate.dat(MT.Plastic));
		CR.shaped(ST.make(BlocksGT.LongDistPipe01, 1, 2), CR.DEF_REV_NCC, "SPS", "PwP", "SPS", 'P', OP.pipeMedium.dat(ANY.W             ), 'S', OP.plate.dat(MT.Plastic));
		CR.shaped(ST.make(BlocksGT.LongDistPipe01, 1, 3), CR.DEF_REV_NCC, "SPS", "PwP", "SPS", 'P', OP.pipeMedium.dat(MT.Ad             ), 'S', OP.plate.dat(MT.Plastic));
		CR.shaped(ST.make(BlocksGT.LongDistPipe01, 1, 4), CR.DEF_REV_NCC, "SPS", "PwP", "SPS", 'P', OP.pipeMedium.dat(MT.Draconium      ), 'S', OP.plate.dat(MT.Plastic));

		/*
		OM.reg(OP.blockGlass    .toString()                     , ST.make(BlocksGT.Glass, 1, W));
		for (byte i = 0; i < 16; i++) {
		OM.reg(OP.stainedGlass  .toString()+DYE_OREDICTS_POST[i], ST.make(BlocksGT.Glass, 1, i));
		OM.reg(OP.blockGlass    .toString()+DYE_OREDICTS_POST[i], ST.make(BlocksGT.Glass, 1, i));
		}*/
	}
}
