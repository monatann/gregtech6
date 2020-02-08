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

import gregapi6.block.metatype.BlockMetaType;
import gregapi6.data.ANY;
import gregapi6.data.CS.BlocksGT;
import gregapi6.data.IL;
import gregapi6.data.MT;
import gregapi6.data.OD;
import gregapi6.data.OP;
import gregapi6.data.TC;
import gregapi6.oredict.OreDictManager;
import gregapi6.util.CR;
import gregapi6.util.OM;
import gregapi6.util.ST;
import gregtech6.blocks.tree.BlockTreeLeaves;
import gregtech6.blocks.tree.BlockTreeLog1;
import gregtech6.blocks.tree.BlockTreeLog1FireProof;
import gregtech6.blocks.tree.BlockTreeLogA;
import gregtech6.blocks.tree.BlockTreeLogAFireProof;
import gregtech6.blocks.tree.BlockTreeLogB;
import gregtech6.blocks.tree.BlockTreeLogBFireProof;
import gregtech6.blocks.tree.BlockTreeSapling;
import gregtech6.blocks.wood.*;
import net.minecraft.init.Blocks;

public class Loader_Woods implements Runnable {
	@Override
	public void run() {
		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.Log1            = new BlockTreeLog1             ("gt6.block.log.1"));
		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.Log1FireProof   = new BlockTreeLog1FireProof    ("gt6.block.log.1.fireproof"));
		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.LogA            = new BlockTreeLogA             ("gt6.block.log.a"));
		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.LogAFireProof   = new BlockTreeLogAFireProof    ("gt6.block.log.a.fireproof"));
		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.LogB            = new BlockTreeLogB             ("gt6.block.log.b"));
		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.LogBFireProof   = new BlockTreeLogBFireProof    ("gt6.block.log.b.fireproof"));
		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.BeamA           = new BlockTreeBeamA            ("gt6.block.beam.a"));
		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.BeamAFireProof  = new BlockTreeBeamAFireProof   ("gt6.block.beam.a.fireproof"));
		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.BeamB           = new BlockTreeBeamB            ("gt6.block.beam.b"));
		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.BeamBFireProof  = new BlockTreeBeamBFireProof   ("gt6.block.beam.b.fireproof"));
		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.Beam1           = new BlockTreeBeam1            ("gt6.block.beam.1"));
		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.Beam1FireProof  = new BlockTreeBeam1FireProof   ("gt6.block.beam.1.fireproof"));
		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.Beam2           = new BlockTreeBeam2            ("gt6.block.beam.2"));
		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.Beam2FireProof  = new BlockTreeBeam2FireProof   ("gt6.block.beam.2.fireproof"));
		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.Beam3           = new BlockTreeBeam3            ("gt6.block.beam.3"));
		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.Beam3FireProof  = new BlockTreeBeam3FireProof   ("gt6.block.beam.3.fireproof"));
		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.Planks          = new BlockTreePlanks           ("gt6.block.planks"));
		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.PlanksFireProof = new BlockTreePlanksFireProof  ("gt6.block.planks.fireproof"));
		
		BlocksGT.Sapling    = new BlockTreeSapling  ("gt6.block.sapling");
		BlocksGT.Leaves     = new BlockTreeLeaves   ("gt6.block.leaves", BlocksGT.Sapling);
		
		IL.Plank_Stairs       .set(ST.make(Blocks.oak_stairs, 1, 0));
		IL.Plank_Slab         .set(ST.make(((BlockMetaType)BlocksGT.Planks).mSlabs[0], 1, 9));
		IL.Plank              .set(ST.make(BlocksGT.Planks, 1,  9));
		IL.Treated_Planks_Slab.set(ST.make(((BlockMetaType)BlocksGT.Planks).mSlabs[0], 1, 10));
		IL.Treated_Planks     .set(ST.make(BlocksGT.Planks, 1,  10));
		IL.Beam .set(ST.make(BlocksGT.Beam2 , 1,  3));
		IL.Crate.set(ST.make(BlocksGT.Planks, 1, 11));
		IL.Crate_Fireproof.set(ST.make(BlocksGT.PlanksFireProof, 1, 11));
		
		CR.shaped(IL.Crate.get(1), CR.DEF_NCC, "Ts", "Pd", 'P', OD.plankAnyWood, 'T', OP.screw.dat(ANY.Iron));
		
		OM.reg(OP.plate, MT.WoodSealed, ST.make(BlocksGT.PlanksFireProof, 1, 10));
		OreDictManager.INSTANCE.setTarget(OP.plate, MT.Wood         , IL.Plank.get(1));
		OreDictManager.INSTANCE.setTarget(OP.plate, MT.WoodSealed   , ST.make(BlocksGT.Planks, 1, 10));
		
		for (int i = 0; i < 16; i++) {
			if (i != 10) {
				OM.reg(ST.make(BlocksGT.Planks          , 1, i), OD.plankWood);
				OM.reg(ST.make(BlocksGT.PlanksFireProof , 1, i), OD.plankWood);
			}
			for (byte tSide : ALL_SIDES_VALID) {
				OM.reg(ST.make(((BlockMetaType)BlocksGT.Planks          ).mSlabs[tSide], 1, i), OD.slabWood);
				OM.reg(ST.make(((BlockMetaType)BlocksGT.PlanksFireProof ).mSlabs[tSide], 1, i), OD.slabWood);
			}
		}
		
		if (COMPAT_TC != null) {
		COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.Log1             , 1, W), F, TC.stack(TC.ARBOR, 2));
		COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.Log1FireProof    , 1, W), F, TC.stack(TC.ARBOR, 2), TC.stack(TC.GELUM, 1));
		COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.Beam1            , 1, W), F, TC.stack(TC.ARBOR, 4));
		COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.Beam1FireProof   , 1, W), F, TC.stack(TC.ARBOR, 4), TC.stack(TC.GELUM, 1));
		COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.Beam2            , 1, W), F, TC.stack(TC.ARBOR, 4));
		COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.Beam2FireProof   , 1, W), F, TC.stack(TC.ARBOR, 4), TC.stack(TC.GELUM, 1));
		COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.Beam3            , 1, W), F, TC.stack(TC.ARBOR, 4));
		COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.Beam3FireProof   , 1, W), F, TC.stack(TC.ARBOR, 4), TC.stack(TC.GELUM, 1));
		COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.BeamA            , 1, W), F, TC.stack(TC.ARBOR, 4));
		COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.BeamAFireProof   , 1, W), F, TC.stack(TC.ARBOR, 4), TC.stack(TC.GELUM, 1));
		COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.BeamB            , 1, W), F, TC.stack(TC.ARBOR, 4));
		COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.BeamBFireProof   , 1, W), F, TC.stack(TC.ARBOR, 4), TC.stack(TC.GELUM, 1));
		}
		
		OM.data(ST.make(BlocksGT.Log1           , 1, W), ANY.Wood, U*4);
		OM.data(ST.make(BlocksGT.Log1FireProof  , 1, W), ANY.Wood, U*4);
		OM.data(ST.make(BlocksGT.Beam1          , 1, W), ANY.Wood, U*8);
		OM.data(ST.make(BlocksGT.Beam1FireProof , 1, W), ANY.Wood, U*8);
		OM.data(ST.make(BlocksGT.Beam2          , 1, W), ANY.Wood, U*8);
		OM.data(ST.make(BlocksGT.Beam2FireProof , 1, W), ANY.Wood, U*8);
		OM.data(ST.make(BlocksGT.Beam3          , 1, W), ANY.Wood, U*8);
		OM.data(ST.make(BlocksGT.Beam3FireProof , 1, W), ANY.Wood, U*8);
		OM.data(ST.make(BlocksGT.BeamA          , 1, W), ANY.Wood, U*8);
		OM.data(ST.make(BlocksGT.BeamAFireProof , 1, W), ANY.Wood, U*8);
		OM.data(ST.make(BlocksGT.BeamB          , 1, W), ANY.Wood, U*8);
		OM.data(ST.make(BlocksGT.BeamBFireProof , 1, W), ANY.Wood, U*8);
	}
}
