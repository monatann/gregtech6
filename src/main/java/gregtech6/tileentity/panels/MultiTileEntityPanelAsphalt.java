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

import java.util.List;

import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_AddToolTips;
import gregapi6.data.LH;
import gregapi6.old.Textures;
import gregapi6.render.BlockTextureDefault;
import gregapi6.render.ITexture;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityPanelAsphalt extends MultiTileEntityPanelColored implements IMTE_AddToolTips {
	@Override
	public ITexture getTexture(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		return BlockTextureDefault.get(Textures.BlockIcons.ASPHALT, DYES[mColor]);
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(LH.Chat.CYAN + LH.get(LH.TOOLTIP_WALKSPEED));
	}
	
	@Override public String getTileEntityName() {return "gt6.multitileentity.panel.asphalt";}
}
