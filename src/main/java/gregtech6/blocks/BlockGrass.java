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

import java.util.ArrayList;
import java.util.List;

import gregapi6.block.BlockBaseMeta;
import gregapi6.code.ArrayListNoNulls;
import gregapi6.data.CS.BlocksGT;
import gregapi6.data.LH;
import gregapi6.data.RM;
import gregapi6.old.Textures;
import gregapi6.render.IconContainerCopied;
import gregapi6.util.CR;
import gregapi6.util.ST;
import gregapi6.util.WD;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockGrass extends BlockBaseMeta {
	public BlockGrass(String aUnlocalised) {
		super(null, aUnlocalised, Material.grass, soundTypeGrass, 4, Textures.BlockIcons.GRASSES_TOP);
		LH.add(getUnlocalizedName()+ ".0.name", "Grass");
		LH.add(getUnlocalizedName()+ ".1.name", "Grass");
		LH.add(getUnlocalizedName()+ ".2.name", "Grass");
		LH.add(getUnlocalizedName()+ ".3.name", "Grass");
		LH.add(getUnlocalizedName()+ ".4.name", "Grass");
		LH.add(getUnlocalizedName()+ ".5.name", "Grass");
		LH.add(getUnlocalizedName()+ ".6.name", "Grass");
		LH.add(getUnlocalizedName()+ ".7.name", "Grass");
		LH.add(getUnlocalizedName()+ ".8.name", "Grass");
		LH.add(getUnlocalizedName()+ ".9.name", "Grass");
		LH.add(getUnlocalizedName()+ ".10.name", "Grass");
		LH.add(getUnlocalizedName()+ ".11.name", "Grass");
		LH.add(getUnlocalizedName()+ ".12.name", "Grass");
		LH.add(getUnlocalizedName()+ ".13.name", "Grass");
		LH.add(getUnlocalizedName()+ ".14.name", "Grass");
		LH.add(getUnlocalizedName()+ ".15.name", "Grass");
		
		BlocksGT.harvestableSpade.add(this);
		BlocksGT.plantableGreens.add(this);
		BlocksGT.plantableTrees.add(this);
		BlocksGT.plantableGrass.add(this);
		
		RM.generify(ST.make(this, 1, W), ST.make(Blocks.grass, 1, 0));
		CR.shapeless(ST.make(Blocks.grass, 1, 0), new Object[] {this});
		
		CR.shapeless(ST.make(this, 8, 0), new Object[] {Blocks.grass, Blocks.grass, Blocks.grass, DYE_OREDICTS[DYE_INDEX_Green    ], Blocks.grass, Blocks.grass, Blocks.grass, Blocks.grass, Blocks.grass});
		CR.shapeless(ST.make(this, 8, 1), new Object[] {Blocks.grass, Blocks.grass, Blocks.grass, DYE_OREDICTS[DYE_INDEX_Lime     ], Blocks.grass, Blocks.grass, Blocks.grass, Blocks.grass, Blocks.grass});
		CR.shapeless(ST.make(this, 8, 2), new Object[] {Blocks.grass, Blocks.grass, Blocks.grass, DYE_OREDICTS[DYE_INDEX_Black    ], Blocks.grass, Blocks.grass, Blocks.grass, Blocks.grass, Blocks.grass});
		CR.shapeless(ST.make(this, 8, 3), new Object[] {Blocks.grass, Blocks.grass, Blocks.grass, DYE_OREDICTS[DYE_INDEX_LightGray], Blocks.grass, Blocks.grass, Blocks.grass, Blocks.grass, Blocks.grass});
	}
	
	static {
		LH.add("gt6.grass.tooltip", "Does not spread, get eaten, change color or need light");
	}
	
	@Override
	public void addInformation(ItemStack aStack, int aMeta, EntityPlayer aPlayer, List<String> aList, boolean aF3_H) {
		aList.add(LH.Chat.CYAN + LH.get("gt6.grass.tooltip"));
	}
	
	@Override
	public boolean canSustainPlant(IBlockAccess aWorld, int aX, int aY, int aZ, ForgeDirection aSide, IPlantable aPlant) {
		EnumPlantType tType = aPlant.getPlantType(aWorld, aX, aY+1, aZ);
		return tType == EnumPlantType.Plains || (tType == EnumPlantType.Beach && (WD.anywater(aWorld, aX+1, aY, aZ) || WD.anywater(aWorld, aX-1, aY, aZ) || WD.anywater(aWorld, aX, aY, aZ+1) || WD.anywater(aWorld, aX, aY, aZ-1)));
	}
	
	public static final IconContainerCopied DIRT = new IconContainerCopied(Blocks.dirt, 0, SIDE_BOTTOM);
	
	@Override public IIcon getIcon(int aSide, int aMeta) {return (SIDES_BOTTOM[aSide]?DIRT:(SIDES_TOP[aSide]?Textures.BlockIcons.GRASSES_TOP:Textures.BlockIcons.GRASSES_SIDE)[aMeta % 16]).getIcon(0);}
	@Override public ArrayList<ItemStack> getDrops(World aWorld, int aX, int aY, int aZ, int aMeta, int aFortune) {return new ArrayListNoNulls<>(F, ST.make(Blocks.dirt, 1, 0));}
	@Override public boolean doesPistonPush  (short aMeta) {return T;}
	@Override public boolean canCreatureSpawn(int   aMeta) {return F;}
	@Override public boolean isSealable      (int   aMeta, byte aSide) {return F;}
	@Override public String getHarvestTool   (int   aMeta) {return TOOL_shovel;}
	@Override public int getHarvestLevel     (int   aMeta) {return 0;}
	@Override public float getBlockHardness(World aWorld, int aX, int aY, int aZ) {return Blocks.grass.getBlockHardness(aWorld, aX, aY, aZ);}
	@Override public float getExplosionResistance(int aMeta) {return Blocks.grass.getExplosionResistance(null);}
}
