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

package gregapi6.block.multitileentity.example;

import static gregapi6.data.CS.*;

import java.util.List;
import java.util.UUID;

import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_AddToolTips;
import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_GetPlayerRelativeBlockHardness;
import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_OnPlaced;
import gregapi6.block.multitileentity.MultiTileEntityContainer;
import gregapi6.data.LH;
import gregapi6.data.LH.Chat;
import gregapi6.old.Textures;
import gregapi6.render.BlockTextureDefault;
import gregapi6.render.BlockTextureMulti;
import gregapi6.render.IIconContainer;
import gregapi6.render.ITexture;
import gregapi6.tileentity.notick.TileEntityBase05Paintable;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 * 
 * An example implementation of a private Block.
 */
public class MultiTileEntityBunkerBlock extends TileEntityBase05Paintable implements IMTE_GetPlayerRelativeBlockHardness, IMTE_AddToolTips, IMTE_OnPlaced {
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_OWNER)) mOwner = UUID.fromString(aNBT.getString(NBT_OWNER));
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		if (mOwner != null) aNBT.setString(NBT_OWNER, mOwner.toString());
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void addToolTips(@SuppressWarnings("rawtypes") List aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.ORANGE + LH.get(LH.OWNER_CONTROLLED));
	}
	
	@Override
	public boolean onPlaced(ItemStack aStack, EntityPlayer aPlayer, MultiTileEntityContainer aMTEContainer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (aPlayer != null) mOwner = aPlayer.getUniqueID();
		return T;
	}
	
	public static IIconContainer sColored = new Textures.BlockIcons.CustomIcon("bunker/block/colored"), sOverlay = new Textures.BlockIcons.CustomIcon("bunker/block/overlay");
	
	@Override public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {return aShouldSideBeRendered[aSide] ? BlockTextureMulti.get(BlockTextureDefault.get(sColored, mRGBa), BlockTextureDefault.get(sOverlay)) : null;}
	@Override public String getTileEntityName() {return "gt6.multitileentity.bunker.block";}
}
