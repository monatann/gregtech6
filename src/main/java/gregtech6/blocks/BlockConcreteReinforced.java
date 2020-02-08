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

package gregtech6.blocks;

import static gregapi6.data.CS.*;

import gregapi6.block.metatype.BlockColored;
import gregapi6.block.metatype.BlockMetaType;
import gregapi6.block.metatype.ItemBlockMetaType;
import gregapi6.data.ANY;
import gregapi6.data.MT;
import gregapi6.data.OP;
import gregapi6.old.Textures;
import gregapi6.oredict.OreDictItemData;
import gregapi6.oredict.OreDictMaterial;
import gregapi6.render.IIconContainer;
import gregapi6.util.OM;
import gregapi6.util.ST;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

public class BlockConcreteReinforced extends BlockColored {
	public BlockConcreteReinforced(String aUnlocalised) {
		super(ItemBlockMetaType.class, Material.rock, soundTypeStone, aUnlocalised, "Reinforced Concrete", null, 8.0F, 4.0F, 3, Textures.BlockIcons.CONCRETES_REINFORCED);
		OM.data(ST.make(this, 1, W), new OreDictItemData(MT.Concrete, U, ANY.Iron, OP.stick.mAmount));
	}
	
	@Override
	protected BlockMetaType makeSlab(Class<? extends ItemBlock> aItemClass, Material aVanillaMaterial, SoundType aVanillaSoundType, String aName, String aDefaultLocalised, OreDictMaterial aMaterial, float aResistanceMultiplier, float aHardnessMultiplier, int aHarvestLevel, int aCount, IIconContainer[] aIcons, byte aSlabType, BlockMetaType aBlock) {
		return new BlockConcreteReinforced(aItemClass, aVanillaMaterial, aVanillaSoundType, aName, aDefaultLocalised, aMaterial, aResistanceMultiplier, aHardnessMultiplier, aHarvestLevel, aCount, aIcons, aSlabType, aBlock);
	}
	
	protected BlockConcreteReinforced(Class<? extends ItemBlock> aItemClass, Material aVanillaMaterial, SoundType aVanillaSoundType, String aName, String aDefaultLocalised, OreDictMaterial aMaterial, float aResistanceMultiplier, float aHardnessMultiplier, int aHarvestLevel, int aCount, IIconContainer[] aIcons, byte aSlabType, BlockMetaType aBlock) {
		super(aItemClass, aVanillaMaterial, aVanillaSoundType, aName, aDefaultLocalised, aMaterial, aResistanceMultiplier, aHardnessMultiplier, aHarvestLevel, aCount, aIcons, aSlabType, aBlock);
		OM.data(ST.make(this, 1, W), new OreDictItemData(MT.Concrete, U2, ANY.Iron, OP.stick.mAmount/2));
	}
}
