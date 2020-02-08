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

package gregtech6.blocks.tool;

import java.util.List;

import gregapi6.block.misc.BlockBaseSpike;
import gregapi6.damage.DamageSources;
import gregapi6.data.ANY;
import gregapi6.data.LH;
import gregapi6.data.MT;
import gregapi6.util.UT;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockSpikeMetal extends BlockBaseSpike {
	public BlockSpikeMetal(String aNameInternal) {
		super(aNameInternal, ANY.Cu, MT.Pb);
		LH.add(getUnlocalizedName()+ ".0.name" , "Copper Wall Spike");
		LH.add(getUnlocalizedName()+ ".1.name" , "Copper Wall Spike");
		LH.add(getUnlocalizedName()+ ".2.name" , "Copper Wall Spike");
		LH.add(getUnlocalizedName()+ ".3.name" , "Copper Wall Spike");
		LH.add(getUnlocalizedName()+ ".4.name" , "Copper Wall Spike");
		LH.add(getUnlocalizedName()+ ".5.name" , "Copper Wall Spike");
		LH.add(getUnlocalizedName()+ ".6.name" , "Copper Block Spike");
		LH.add(getUnlocalizedName()+ ".7.name" , "Falling Copper Spike Block");
		LH.add(getUnlocalizedName()+ ".8.name" , "Lead Wall Spike");
		LH.add(getUnlocalizedName()+ ".9.name" , "Lead Wall Spike");
		LH.add(getUnlocalizedName()+ ".10.name", "Lead Wall Spike");
		LH.add(getUnlocalizedName()+ ".11.name", "Lead Wall Spike");
		LH.add(getUnlocalizedName()+ ".12.name", "Lead Wall Spike");
		LH.add(getUnlocalizedName()+ ".13.name", "Lead Wall Spike");
		LH.add(getUnlocalizedName()+ ".14.name", "Lead Block Spike");
		LH.add(getUnlocalizedName()+ ".15.name", "Falling Lead Spike Block");
	}
	
	@Override
	public void addInformation(ItemStack aStack, int aMeta, EntityPlayer aPlayer, List<String> aList, boolean aF3_H) {
		if (aMeta < 8) {
			aList.add(LH.Chat.ORANGE + "Deals huge Damage to any Slime touching it!");
			aList.add(LH.Chat.ORANGE + "Does very low Damage to anything else!");
			aList.add(LH.Chat.ORANGE + "Doesn't work on Skeletons and Iron Golems.");
		} else {
			aList.add(LH.Chat.ORANGE + "Deals huge Damage to any Arthropod touching it!");
			aList.add(LH.Chat.ORANGE + "Does very low Damage to anything else!");
			aList.add(LH.Chat.ORANGE + "Doesn't work on Skeletons, Slimes and Iron Golems.");
		}
		if ((aMeta & 7) >= 6) {
			aList.add(LH.Chat.CYAN + "Works in all Directions, but only does half the Wall Spikes Damage!");
		}
	}
	
	@Override
	public void onEntityCollidedWithBlock(World aWorld, int aX, int aY, int aZ, Entity aEntity) {
		int aMeta = aWorld.getBlockMetadata(aX, aY, aZ);
		if (aEntity instanceof EntityLivingBase) {
			if (aMeta < 8) {
				if (UT.Entities.isSlimeCreature((EntityLivingBase)aEntity))
				aEntity.attackEntityFrom(DamageSources.getSpikeDamage(), (aMeta & 7) < 6 ? 20.0F : 10.0F);
				else if (!(aEntity instanceof EntityIronGolem || aEntity instanceof EntitySkeleton))
				aEntity.attackEntityFrom(DamageSources.getSpikeDamage(), (aMeta & 7) < 6 ?  2.0F :  1.0F);
			} else {
				if (((EntityLivingBase)aEntity).getCreatureAttribute() == EnumCreatureAttribute.ARTHROPOD)
				aEntity.attackEntityFrom(DamageSources.getSpikeDamage(), (aMeta & 7) < 6 ? 20.0F : 10.0F);
				else if (!(aEntity instanceof EntityIronGolem || aEntity instanceof EntitySkeleton || aEntity instanceof EntitySlime))
				aEntity.attackEntityFrom(DamageSources.getSpikeDamage(), (aMeta & 7) < 6 ?  2.0F :  1.0F);
			}
		}
	}
}
