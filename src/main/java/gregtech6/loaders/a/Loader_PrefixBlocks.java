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

import gregapi6.block.prefixblock.PrefixBlock_;
import gregapi6.data.CS.BlocksGT;
import gregapi6.data.CS.ConfigsGT;
import gregapi6.data.MD;
import gregapi6.data.MT;
import gregapi6.data.OP;
import gregapi6.old.Textures;
import gregapi6.oredict.OreDictMaterial;
import gregapi6.render.BlockTextureDefault;
import gregapi6.util.OM;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class Loader_PrefixBlocks implements Runnable {
	@Override
	public void run() {
		BlocksGT.blockGem                   = new PrefixBlock_(MD.GT, "gt6.meta.storage.gem"         , OP.blockGem               , null                  , null, null, null                                                  , Material.rock , Block.soundTypeStone  , TOOL_pickaxe  , 1.5F, 4.5F,   0,   0, 999, 0,0,0,1,1,1, F,T,F,F,T,T,T,T,T,T,T,T,T,T, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.blockDust                  = new PrefixBlock_(MD.GT, "gt6.meta.storage.dust"        , OP.blockDust              , null                  , null, null, null                                                  , Material.sand , Block.soundTypeSand   , TOOL_shovel   , 0.5F, 4.5F,  -2,   0, 999, 0,0,0,1,1,1, T,F,F,F,T,T,T,T,T,T,T,T,T,T, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.blockIngot                 = new PrefixBlock_(MD.GT, "gt6.meta.storage.ingot"       , OP.blockIngot             , null                  , null, null, null                                                  , Material.iron , Block.soundTypeMetal  , TOOL_pickaxe  , 1.0F, 3.0F,   0,   0, 999, 0,0,0,1,1,1, F,T,F,F,T,T,T,T,T,T,T,T,T,T, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.blockPlate                 = new PrefixBlock_(MD.GT, "gt6.meta.storage.plate"       , OP.blockPlate             , null                  , null, null, null                                                  , Material.iron , Block.soundTypeMetal  , TOOL_pickaxe  , 1.0F, 3.0F,   0,   0, 999, 0,0,0,1,1,1, F,T,F,F,T,T,T,T,T,T,T,T,T,T, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.blockPlateGem              = new PrefixBlock_(MD.GT, "gt6.meta.storage.plateGem"    , OP.blockPlateGem          , null                  , null, null, null                                                  , Material.iron , Block.soundTypeMetal  , TOOL_pickaxe  , 1.0F, 3.0F,   0,   0, 999, 0,0,0,1,1,1, F,T,F,F,T,T,T,T,T,T,T,T,T,T, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.blockSolid                 = new PrefixBlock_(MD.GT, "gt6.meta.storage.solid"       , OP.blockSolid             , null                  , null, null, null                                                  , Material.iron , Block.soundTypeMetal  , TOOL_pickaxe  , 1.7F, 5.0F,   0,   0, 999, 0,0,0,1,1,1, F,T,F,F,T,T,T,T,T,T,T,T,T,T, OreDictMaterial.MATERIAL_ARRAY);
		
		BlocksGT.casingMachine              = new PrefixBlock_(MD.GT, "gt6.meta.machine"             , OP.casingMachine          , null                  , null, null, null                                                  , Material.iron , Block.soundTypeMetal  , TOOL_wrench   , 1.0F, 3.0F,   0,   0, 999, 0,0,0,1,1,1, F,T,F,F,T,T,T,T,T,T,T,T,T,T, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.casingMachineDouble        = new PrefixBlock_(MD.GT, "gt6.meta.machine.double"      , OP.casingMachineDouble    , null                  , null, null, null                                                  , Material.iron , Block.soundTypeMetal  , TOOL_wrench   , 2.0F, 6.0F,   0,   0, 999, 0,0,0,1,1,1, F,T,F,F,T,T,T,T,T,T,T,T,T,T, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.casingMachineQuadruple     = new PrefixBlock_(MD.GT, "gt6.meta.machine.quadruple"   , OP.casingMachineQuadruple , null                  , null, null, null                                                  , Material.iron , Block.soundTypeMetal  , TOOL_wrench   , 4.0F,12.0F,   0,   0, 999, 0,0,0,1,1,1, F,T,F,F,T,T,T,T,T,T,T,T,T,T, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.casingMachineDense         = new PrefixBlock_(MD.GT, "gt6.meta.machine.dense"       , OP.casingMachineDense     , null                  , null, null, null                                                  , Material.iron , Block.soundTypeMetal  , TOOL_wrench   , 9.0F,18.0F,   0,   0, 999, 0,0,0,1,1,1, F,T,F,F,T,T,T,T,T,T,T,T,T,T, OreDictMaterial.MATERIAL_ARRAY);
		
		boolean b = ConfigsGT.CLIENT.get("blocks.crates", "UseOverlayIcon", F);
		
		BlocksGT.crateGtGem                 = new PrefixBlock_(MD.GT, "gt6.meta.crate.gem"           , OP.crateGtGem             , OM.stack(MT.Wood, U)  , null, null, BlockTextureDefault.get(Textures.BlockIcons.CRATE)    , Material.wood , Block.soundTypeWood   , TOOL_crowbar  , 1.0F, 0.2F,   0,   0,   0, 0,0,0,1,1,1, T,F,F,F,T,T,F,T,T,T,b,F,F,T, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.crateGtDust                = new PrefixBlock_(MD.GT, "gt6.meta.crate.dust"          , OP.crateGtDust            , OM.stack(MT.Wood, U)  , null, null, BlockTextureDefault.get(Textures.BlockIcons.CRATE)    , Material.wood , Block.soundTypeWood   , TOOL_crowbar  , 1.0F, 0.2F,   0,   0,   0, 0,0,0,1,1,1, T,F,F,F,T,T,F,T,T,T,b,F,F,T, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.crateGtIngot               = new PrefixBlock_(MD.GT, "gt6.meta.crate.ingot"         , OP.crateGtIngot           , OM.stack(MT.Wood, U)  , null, null, BlockTextureDefault.get(Textures.BlockIcons.CRATE)    , Material.wood , Block.soundTypeWood   , TOOL_crowbar  , 1.0F, 0.2F,   0,   0,   0, 0,0,0,1,1,1, T,F,F,F,T,T,F,T,T,T,b,F,F,T, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.crateGtPlate               = new PrefixBlock_(MD.GT, "gt6.meta.crate.plate"         , OP.crateGtPlate           , OM.stack(MT.Wood, U)  , null, null, BlockTextureDefault.get(Textures.BlockIcons.CRATE)    , Material.wood , Block.soundTypeWood   , TOOL_crowbar  , 1.0F, 0.2F,   0,   0,   0, 0,0,0,1,1,1, T,F,F,F,T,T,F,T,T,T,b,F,F,T, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.crateGtPlateGem            = new PrefixBlock_(MD.GT, "gt6.meta.crate.plateGem"      , OP.crateGtPlateGem        , OM.stack(MT.Wood, U)  , null, null, BlockTextureDefault.get(Textures.BlockIcons.CRATE)    , Material.wood , Block.soundTypeWood   , TOOL_crowbar  , 1.0F, 0.2F,   0,   0,   0, 0,0,0,1,1,1, T,F,F,F,T,T,F,T,T,T,b,F,F,T, OreDictMaterial.MATERIAL_ARRAY);
		
		BlocksGT.crateGt64Gem               = new PrefixBlock_(MD.GT, "gt6.meta.crate.64.gem"        , OP.crateGt64Gem           , OM.stack(MT.Wood, U)  , null, null, BlockTextureDefault.get(Textures.BlockIcons.CRATE)    , Material.wood , Block.soundTypeWood   , TOOL_crowbar  , 1.0F, 0.2F,   0,   0,   0, 0,0,0,1,1,1, T,F,F,F,T,T,F,T,T,T,b,F,F,T, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.crateGt64Dust              = new PrefixBlock_(MD.GT, "gt6.meta.crate.64.dust"       , OP.crateGt64Dust          , OM.stack(MT.Wood, U)  , null, null, BlockTextureDefault.get(Textures.BlockIcons.CRATE)    , Material.wood , Block.soundTypeWood   , TOOL_crowbar  , 1.0F, 0.2F,   0,   0,   0, 0,0,0,1,1,1, T,F,F,F,T,T,F,T,T,T,b,F,F,T, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.crateGt64Ingot             = new PrefixBlock_(MD.GT, "gt6.meta.crate.64.ingot"      , OP.crateGt64Ingot         , OM.stack(MT.Wood, U)  , null, null, BlockTextureDefault.get(Textures.BlockIcons.CRATE)    , Material.wood , Block.soundTypeWood   , TOOL_crowbar  , 1.0F, 0.2F,   0,   0,   0, 0,0,0,1,1,1, T,F,F,F,T,T,F,T,T,T,b,F,F,T, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.crateGt64Plate             = new PrefixBlock_(MD.GT, "gt6.meta.crate.64.plate"      , OP.crateGt64Plate         , OM.stack(MT.Wood, U)  , null, null, BlockTextureDefault.get(Textures.BlockIcons.CRATE)    , Material.wood , Block.soundTypeWood   , TOOL_crowbar  , 1.0F, 0.2F,   0,   0,   0, 0,0,0,1,1,1, T,F,F,F,T,T,F,T,T,T,b,F,F,T, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.crateGt64PlateGem          = new PrefixBlock_(MD.GT, "gt6.meta.crate.64.plateGem"   , OP.crateGt64PlateGem      , OM.stack(MT.Wood, U)  , null, null, BlockTextureDefault.get(Textures.BlockIcons.CRATE)    , Material.wood , Block.soundTypeWood   , TOOL_crowbar  , 1.0F, 0.2F,   0,   0,   0, 0,0,0,1,1,1, T,F,F,F,T,T,F,T,T,T,b,F,F,T, OreDictMaterial.MATERIAL_ARRAY);
	}
}
