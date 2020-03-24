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

package gregtech6.tileentity.energy.reactors;

import static gregapi6.data.CS.*;

import java.util.List;

import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_AddToolTips;
import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_GetCollisionBoundingBoxFromPool;
import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_GetSelectedBoundingBoxFromPool;
import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_SetBlockBoundsBasedOnState;
import gregapi6.data.LH;
import gregapi6.data.MT;
import gregapi6.item.IItemReactorRod;
import gregapi6.old.Textures;
import gregapi6.render.BlockTextureDefault;
import gregapi6.render.BlockTextureMulti;
import gregapi6.render.IIconContainer;
import gregapi6.render.ITexture;
import gregapi6.tileentity.ITileEntityQuickObstructionCheck;
import gregapi6.tileentity.base.TileEntityBase07Paintable;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityReactorRodBase extends TileEntityBase07Paintable implements IItemReactorRod, ITileEntityQuickObstructionCheck, IMTE_AddToolTips, IMTE_SetBlockBoundsBasedOnState, IMTE_GetCollisionBoundingBoxFromPool, IMTE_GetSelectedBoundingBoxFromPool {
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(LH.Chat.CYAN + "Empty Reactor Rod, transparent to Neutrons.");
		aList.add(LH.Chat.DGRAY + "Used in Nuclear Reactor Core");
	}

	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		return SIDES_HORIZONTAL[aSide] ? getReactorRodTextureSides(null, 0, null, T) : getReactorRodTextureTop(null, 0, null, T);
	}

	public static IIconContainer sColoreds[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_rods/colored/top"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_rods/colored/sides")
	}, sOverlays[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_rods/overlay/top"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_rods/overlay/sides")
	};

	@Override public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {box(aBlock, PX_P[ 6], PX_P[ 0], PX_P[ 6], PX_N[ 6], PX_N[ 0], PX_N[ 6]); return T;}
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return box(PX_P[ 6], PX_P[ 0], PX_P[ 6], PX_N[ 6], PX_N[ 0], PX_N[ 6]);}
	@Override public AxisAlignedBB getSelectedBoundingBoxFromPool () {return box(PX_P[ 6], PX_P[ 0], PX_P[ 6], PX_N[ 6], PX_N[ 0], PX_N[ 6]);}
	@Override public void setBlockBoundsBasedOnState(Block aBlock)  {box(aBlock, PX_P[ 6], PX_P[ 0], PX_P[ 6], PX_N[ 6], PX_N[ 0], PX_N[ 6]);}

	@Override public int getLightOpacity() {return LIGHT_OPACITY_NONE;}

	@Override public float getSurfaceSize           (byte aSide) {return 0.0F;}
	@Override public float getSurfaceSizeAttachable (byte aSide) {return 0.0F;}
	@Override public float getSurfaceDistance       (byte aSide) {return SIDES_VERTICAL[aSide]?0.0F:PX_P[ 6];}
	@Override public boolean isSurfaceSolid         (byte aSide) {return F;}
	@Override public boolean isSurfaceOpaque2       (byte aSide) {return F;}
	@Override public boolean isSideSolid2           (byte aSide) {return F;}
	@Override public boolean allowCovers            (byte aSide) {return F;}
	@Override public boolean attachCoversFirst      (byte aSide) {return F;}
	@Override public boolean isObstructingBlockAt   (byte aSide) {return F;}

	@Override public boolean  isReactorRod(ItemStack aStack) {return T;}
	@Override public int      getReactorRodNeutronEmission  (MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack) {return 0;}
	@Override public boolean  getReactorRodNeutronReaction  (MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack) {return F;}
	@Override public int      getReactorRodNeutronReflection(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack, int aNeutrons) {return 0;}
	@Override public int      getReactorRodNeutronMaximum	(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack) {return 0;}

	@Override public ITexture getReactorRodTextureSides(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack, boolean aActive) {return BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[1], mRGBa, F), BlockTextureDefault.get(sOverlays[1], aActive ? UNCOLOURED : MT.Pb.fRGBaSolid));}
	@Override public ITexture getReactorRodTextureTop  (MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack, boolean aActive) {return BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[0], mRGBa, F), BlockTextureDefault.get(sOverlays[0], aActive ? UNCOLOURED : MT.Pb.fRGBaSolid));}

	@Override public boolean canDrop(int aSlot) {return F;}

	@Override public String getTileEntityName() {return "gt6.multitileentity.generator.reactor.rods.empty";}
}
