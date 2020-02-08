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

import gregapi6.code.HashSetNoNulls;
import gregapi6.code.TagData;
import gregapi6.data.LH;
import gregapi6.data.LH.Chat;
import gregapi6.data.MT;
import gregapi6.data.TD;
import gregapi6.old.Textures;
import gregapi6.oredict.OreDictMaterial;
import gregapi6.render.BlockTextureDefault;
import gregapi6.render.BlockTextureMulti;
import gregapi6.render.ITexture;
import gregapi6.tileentity.ITileEntityQuickObstructionCheck;
import gregapi6.tileentity.delegate.DelegatorTileEntity;
import gregapi6.tileentity.energy.ITileEntityEnergy;
import gregapi6.tileentity.energy.ITileEntityEnergyDataConductor;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityWireLaser extends TileEntityBase10ConnectorRendered implements ITileEntityQuickObstructionCheck, ITileEntityEnergy, ITileEntityEnergyDataConductor {
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN + LH.get(LH.WIRE_STATS_LOSSLESS) + " (" + TD.Energy.LU.getChatFormat()+TD.Energy.LU.getLocalisedNameShort()+Chat.CYAN + ")");
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	public long transferLaser(byte aSide, long aVoltage, long aAmperage, long aChannel, HashSetNoNulls<TileEntity> aAlreadyPassed) {
		long rUsedAmperes = 0;
		
		for (byte tSide : ALL_SIDES_VALID_BUT[aSide]) if (canEmitEnergyTo(tSide)) {
			if (aAmperage <= rUsedAmperes) break;
			DelegatorTileEntity<TileEntity> tDelegator = getAdjacentTileEntity(tSide);
			if (aAlreadyPassed.add(tDelegator.mTileEntity)) {
				if (tDelegator.mTileEntity instanceof MultiTileEntityWireLaser) {
					if (((MultiTileEntityWireLaser)tDelegator.mTileEntity).isEnergyAcceptingFrom(TD.Energy.LU, tDelegator.mSideOfTileEntity, F)) {
						rUsedAmperes += ((MultiTileEntityWireLaser)tDelegator.mTileEntity).transferLaser(tDelegator.mSideOfTileEntity, aVoltage, aAmperage-rUsedAmperes, aChannel, aAlreadyPassed);
					}
				} else {
					rUsedAmperes += ITileEntityEnergy.Util.insertEnergyInto(TD.Energy.LU, aVoltage, aAmperage-rUsedAmperes, this, tDelegator);
				}
			}
		}
		
		return rUsedAmperes;
	}
	
	@Override
	public boolean canConnect(byte aSide, DelegatorTileEntity<TileEntity> aDelegator) {
		if (aDelegator.mTileEntity instanceof ITileEntityEnergy) return ((ITileEntityEnergy)aDelegator.mTileEntity).isEnergyAcceptingFrom(TD.Energy.LU, aDelegator.mSideOfTileEntity, T) || ((ITileEntityEnergy)aDelegator.mTileEntity).isEnergyEmittingTo(TD.Energy.LU, aDelegator.mSideOfTileEntity, T);
		return F;
	}
	
	@Override public boolean isEnergyType(TagData aEnergyType, byte aSide, boolean aEmitting) {return aEnergyType == TD.Energy.LU;}
	@Override public Collection<TagData> getEnergyTypes(byte aSide) {return TD.Energy.LU.AS_LIST;}
	
	@Override public boolean isEnergyEmittingTo   (TagData aEnergyType, byte aSide, boolean aTheoretical) {return isEnergyType(aEnergyType, aSide, T) && canEmitEnergyTo    (aSide);}
	@Override public boolean isEnergyAcceptingFrom(TagData aEnergyType, byte aSide, boolean aTheoretical) {return isEnergyType(aEnergyType, aSide, F) && canAcceptEnergyFrom(aSide);}
	@Override public synchronized long doEnergyExtraction(TagData aEnergyType, byte aSide, long aSize, long aAmount, boolean aDoExtract) {return 0;}
	@Override public synchronized long doEnergyInjection (TagData aEnergyType, byte aSide, long aSize, long aAmount, boolean aDoInject ) {return aSize != 0 && isEnergyAcceptingFrom(aEnergyType, aSide, F) ?  aDoInject ? transferLaser(aSide, aSize, aAmount, -1, new HashSetNoNulls<TileEntity>(F, this)) : aAmount : 0;}
	@Override public long getEnergySizeOutputRecommended(TagData aEnergyType, byte aSide) {return Long.MAX_VALUE;}
	@Override public long getEnergySizeOutputMin(TagData aEnergyType, byte aSide) {return 0;}
	@Override public long getEnergySizeOutputMax(TagData aEnergyType, byte aSide) {return Long.MAX_VALUE;}
	@Override public long getEnergySizeInputRecommended(TagData aEnergyType, byte aSide) {return Long.MAX_VALUE;}
	@Override public long getEnergySizeInputMin(TagData aEnergyType, byte aSide) {return 0;}
	@Override public long getEnergySizeInputMax(TagData aEnergyType, byte aSide) {return Long.MAX_VALUE;}
	
	@Override public boolean canDrop(int aInventorySlot) {return F;}
	@Override public boolean isObstructingBlockAt(byte aSide) {return F;} // Btw, Wires have this but Pipes don't. This is because Wires are flexible, while Pipes aren't.
	
	@Override public boolean isEnergyConducting(TagData aEnergyType) {return aEnergyType == TD.Energy.LU;}
	@Override public long getEnergyMaxSize(TagData aEnergyType) {return aEnergyType == TD.Energy.LU ? Long.MAX_VALUE : 0;}
	@Override public long getEnergyMaxPackets(TagData aEnergyType) {return aEnergyType == TD.Energy.LU ? Long.MAX_VALUE : 0;}
	@Override public long getEnergyLossPerMeter(TagData aEnergyType) {return 0;}
	@Override public OreDictMaterial getEnergyConductorMaterial() {return mMaterial;}
	@Override public OreDictMaterial getEnergyConductorInsulation() {return MT.NULL;}
	
	public boolean canEmitEnergyTo                          (byte aSide) {return connected(aSide);}
	public boolean canAcceptEnergyFrom                      (byte aSide) {return connected(aSide);}
	
	@Override public ITexture getTextureSide                (byte aSide, byte aConnections, float aDiameter, int aRenderPass) {return BlockTextureMulti.get(BlockTextureDefault.get(Textures.BlockIcons.FIBER_WIRE, mRGBa), BlockTextureDefault.get(Textures.BlockIcons.FIBER_WIRE_OVERLAY));}
	@Override public ITexture getTextureConnected           (byte aSide, byte aConnections, float aDiameter, int aRenderPass) {return BlockTextureMulti.get(BlockTextureDefault.get(Textures.BlockIcons.FIBER_WIRE, mRGBa), BlockTextureDefault.get(Textures.BlockIcons.FIBER_WIRE_OVERLAY));}
	
	@Override public Collection<TagData> getConnectorTypes  (byte aSide) {return TD.Connectors.WIRE_LASER.AS_LIST;}
	
	@Override public String getFacingTool                   () {return TOOL_cutter;}
	
	@Override public String getTileEntityName               () {return "gt6.multitileentity.connector.wire.laser";}
}
