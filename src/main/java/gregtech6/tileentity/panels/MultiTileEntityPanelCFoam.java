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

package gregtech6.tileentity.panels;

import static gregapi6.data.CS.*;

import gregapi6.old.Textures;
import gregapi6.render.BlockTextureDefault;
import gregapi6.render.ITexture;
import net.minecraft.block.Block;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityPanelCFoam extends MultiTileEntityPanelColored {
	@Override
	public ITexture getTexture(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		return BlockTextureDefault.get(Textures.BlockIcons.CFOAM_HARDENED, DYES[mColor]);
	}
	
	@Override public String getTileEntityName() {return "gt6.multitileentity.panel.cfoam";}
}
