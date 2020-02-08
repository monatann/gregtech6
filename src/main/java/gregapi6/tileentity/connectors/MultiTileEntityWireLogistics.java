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

package gregapi6.tileentity.connectors;

import static gregapi6.data.CS.*;

import java.util.Collection;
import java.util.List;

import gregapi6.code.TagData;
import gregapi6.data.TD;
import gregapi6.old.Textures;
import gregapi6.render.BlockTextureDefault;
import gregapi6.render.BlockTextureMulti;
import gregapi6.render.ITexture;
import gregapi6.tileentity.ITileEntityQuickObstructionCheck;
import gregapi6.tileentity.delegate.DelegatorTileEntity;
import gregapi6.tileentity.logistics.ITileEntityLogistics;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityWireLogistics extends TileEntityBase10ConnectorRendered implements ITileEntityQuickObstructionCheck, ITileEntityLogistics {
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	@Override
	public boolean canConnect(byte aSide, DelegatorTileEntity<TileEntity> aDelegator) {
		if (aDelegator.mTileEntity instanceof ITileEntityLogistics) return ((ITileEntityLogistics)aDelegator.mTileEntity).canLogistics(aDelegator.mSideOfTileEntity);
		return F;
	}
	
	@Override public boolean canLogistics(byte aSide) {return connected(aSide) || SIDES_INVALID[aSide];}
	
	@Override public boolean canDrop(int aInventorySlot) {return F;}
	@Override public boolean isObstructingBlockAt(byte aSide) {return F;} // Btw, Wires have this but Pipes don't. This is because Wires are flexible, while Pipes aren't.
	
	@Override public ITexture getTextureSide                (byte aSide, byte aConnections, float aDiameter, int aRenderPass) {return BlockTextureMulti.get(BlockTextureDefault.get(Textures.BlockIcons.LOGISTICS_WIRE, mRGBa), BlockTextureDefault.get(Textures.BlockIcons.LOGISTICS_WIRE_OVERLAY));}
	@Override public ITexture getTextureConnected           (byte aSide, byte aConnections, float aDiameter, int aRenderPass) {return BlockTextureMulti.get(BlockTextureDefault.get(Textures.BlockIcons.LOGISTICS_WIRE, mRGBa), BlockTextureDefault.get(Textures.BlockIcons.LOGISTICS_WIRE_OVERLAY));}
	
	@Override public Collection<TagData> getConnectorTypes  (byte aSide) {return TD.Connectors.WIRE_LOGISTICS.AS_LIST;}
	
	@Override public String getFacingTool                   () {return TOOL_cutter;}
	
	@Override public String getTileEntityName               () {return "gt6.multitileentity.connector.wire.logistics";}
}
