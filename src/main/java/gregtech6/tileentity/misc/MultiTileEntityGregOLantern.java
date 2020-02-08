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

package gregtech6.tileentity.misc;

import static gregapi6.data.CS.*;

import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_GetLightValue;
import gregapi6.old.Textures;
import gregapi6.render.BlockTextureCopied;
import gregapi6.render.BlockTextureDefault;
import gregapi6.render.ITexture;
import gregapi6.tileentity.base.TileEntityBase09FacingSingle;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityGregOLantern extends TileEntityBase09FacingSingle implements IMTE_GetLightValue {
	@Override public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {return aShouldSideBeRendered[aSide] ? aSide == mFacing ? BlockTextureDefault.get(Textures.BlockIcons.GREG_O_LANTERN, mRGBa, F, T, T, F) : BlockTextureCopied.get(Blocks.lit_pumpkin, aSide, 4, mRGBa, F, T, T) : null;}
	
	@Override public int getLightOpacity() {return LIGHT_OPACITY_NONE;}
	@Override public int getLightValue() {return 15;}
	
	@Override public float getExplosionResistance2(Entity aExploder, double aExplosionX, double aExplosionY, double aExplosionZ) {return Blocks.lit_pumpkin.getExplosionResistance(aExploder);}
	@Override public float getExplosionResistance2() {return Blocks.lit_pumpkin.getExplosionResistance(null);}
	@Override public float getBlockHardness() {return Blocks.lit_pumpkin.getBlockHardness(null, 0, 0, 0);}
	
	@Override public boolean isSurfaceSolid         (byte aSide) {return T;}
	@Override public boolean isSurfaceOpaque2       (byte aSide) {return T;}
	@Override public boolean isSideSolid2           (byte aSide) {return T;}
	
	@Override public boolean canDrop(int aSlot) {return F;}
	@Override public boolean[] getValidSides() {return SIDES_HORIZONTAL;}
	@Override public byte getDefaultSide() {return SIDE_FRONT;}
	
	@Override public String getTileEntityName() {return "gt6.multitileentity.greg.o.lantern";}
}
