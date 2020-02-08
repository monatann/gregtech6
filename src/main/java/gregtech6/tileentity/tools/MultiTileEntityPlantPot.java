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

package gregtech6.tileentity.tools;

import static gregapi6.data.CS.*;

import java.util.List;

import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_AddToolTips;
import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_CanSustainPlant;
import gregapi6.data.LH;
import gregapi6.data.LH.Chat;
import gregapi6.old.Textures;
import gregapi6.render.BlockTextureDefault;
import gregapi6.render.BlockTextureMulti;
import gregapi6.render.IIconContainer;
import gregapi6.render.ITexture;
import gregapi6.tileentity.base.TileEntityBase07Paintable;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IPlantable;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityPlantPot extends TileEntityBase07Paintable implements IMTE_AddToolTips, IMTE_CanSustainPlant {
	static {
		LH.add("gt6.multitileentity.plantpot.tooltip.1", "Can grow any Plants ontop of it!");
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN + LH.get("gt6.multitileentity.plantpot.tooltip.1"));
	}
	
	@Override public float getSurfaceDistance       (byte aSide) {return SIDES_TOP[aSide]?0.0F:PX_P[ 1];}
	@Override public float getSurfaceSize           (byte aSide) {return SIDES_TOP[aSide]?1.0F:PX_P[14];}
	@Override public float getSurfaceSizeAttachable (byte aSide) {return getSurfaceSize(aSide);}
	@Override public boolean isSurfaceSolid         (byte aSide) {return SIDES_TOP[aSide];}
	@Override public boolean isSurfaceOpaque2       (byte aSide) {return SIDES_TOP[aSide];}
	@Override public boolean isSideSolid2           (byte aSide) {return SIDES_TOP[aSide];}
	@Override public boolean allowCovers            (byte aSide) {return F;}
	@Override public boolean canSustainPlant        (byte aSide, IPlantable aPlantable) {return SIDES_TOP[aSide];}
	
	@Override public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered) {return 2;}
	
	@Override
	public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		if (aRenderPass == 0) {
			box(aBlock, PX_P[ 0], PX_P[10], PX_P[ 0], PX_N[ 0], PX_N[ 0], PX_N[ 0]);
		} else {
			box(aBlock, PX_P[ 1], PX_P[ 0], PX_P[ 1], PX_N[ 1], PX_N[ 6], PX_N[ 1]);
		}
		return T;
	}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		return (aRenderPass == 0 ? (aSide == SIDE_BOTTOM || aShouldSideBeRendered[aSide]) : aSide != SIDE_TOP && (aSide != SIDE_BOTTOM || aShouldSideBeRendered[aSide])) ? BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[FACES_TBS[aSide]], mRGBa), BlockTextureDefault.get(sOverlays[FACES_TBS[aSide]])) : null;
	}
	
	// Icons
	public static IIconContainer sColoreds[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/plantpot/colored/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/plantpot/colored/top"),
		new Textures.BlockIcons.CustomIcon("machines/plantpot/colored/side"),
	}, sOverlays[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/plantpot/overlay/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/plantpot/overlay/top"),
		new Textures.BlockIcons.CustomIcon("machines/plantpot/overlay/side"),
	};
	
	@Override public String getTileEntityName() {return "gt6.multitileentity.plantpot";}
	
	@Override public boolean canDrop(int aInventorySlot) {return T;}
	@Override public int getLightOpacity() {return LIGHT_OPACITY_WATER;}
}
