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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_GetCollisionBoundingBoxFromPool;
import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_GetDebugInfo;
import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_OnEntityCollidedWithBlock;
import gregapi6.block.multitileentity.MultiTileEntityBlock;
import gregapi6.block.multitileentity.MultiTileEntityRegistry;
import gregapi6.code.ArrayListNoNulls;
import gregapi6.code.HashSetNoNulls;
import gregapi6.code.TagData;
import gregapi6.data.LH;
import gregapi6.data.LH.Chat;
import gregapi6.data.MT;
import gregapi6.data.OP;
import gregapi6.data.TD;
import gregapi6.old.Textures;
import gregapi6.oredict.OreDictManager;
import gregapi6.oredict.OreDictMaterial;
import gregapi6.render.BlockTextureDefault;
import gregapi6.render.BlockTextureMulti;
import gregapi6.render.ITexture;
import gregapi6.tileentity.ITileEntityQuickObstructionCheck;
import gregapi6.tileentity.data.ITileEntityProgress;
import gregapi6.tileentity.delegate.DelegatorTileEntity;
import gregapi6.tileentity.energy.ITileEntityEnergy;
import gregapi6.tileentity.energy.ITileEntityEnergyDataConductor;
import gregapi6.util.UT;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityOpticalFiber extends TileEntityBase10ConnectorRendered implements ITileEntityQuickObstructionCheck, ITileEntityEnergy, ITileEntityEnergyDataConductor, ITileEntityProgress, IMTE_GetDebugInfo, IMTE_GetCollisionBoundingBoxFromPool, IMTE_OnEntityCollidedWithBlock {
	public long mTransferredAmperes = 0, mTransferredWattage = 0, mWattageLast = 0, mLoss = 0, mAmperage = 1, mVoltage = 32;
	public byte mRenderType = 0, mBurnCounter = 0;

	/**
	 * Utility to quickly add a whole set of Electric Wires.
	 * May use up to 50 IDs, even if it is just 11 right now!
	 */
	public static void addElectricWires(int aID, int aCreativeTabID, long aVoltage, long aAmperage, long aLossWire, long aLossCable, boolean aContactDamageWire, boolean aContactDamageCable, boolean aCable, MultiTileEntityRegistry aRegistry, MultiTileEntityBlock aBlock, Class<? extends TileEntity> aClass, OreDictMaterial aMat) {
		OreDictManager.INSTANCE.setTarget_(OP.cableLXGt01             , aMat, aRegistry.add( "1x " + aMat.getLocal() + " Optical Fiber"                    , "Electric Wires"          , aID   , aCreativeTabID, aClass, aMat.mToolQuality, 64, aBlock     , UT.NBT.make(NBT_MATERIAL, aMat, NBT_HARDNESS,   2.0F, NBT_RESISTANCE,   2.0F, NBT_COLOR, UT.Code.getRGBInt(aMat.fRGBaSolid), NBT_PIPERENDER,   0, NBT_DIAMETER, PX_P[ 2], NBT_PIPESIZE, aVoltage, NBT_PIPEBANDWIDTH, aAmperage   , NBT_CONTACTDAMAGE, aContactDamageWire, NBT_PIPELOSS, aLossWire)), T, F, T);
		OreDictManager.INSTANCE.setTarget_(OP.cableLXGt02             , aMat, aRegistry.add( "2x " + aMat.getLocal() + " Optical Fiber"                   , "Electric Wires"          , aID+ 1, aCreativeTabID, aClass, aMat.mToolQuality, 32, aBlock     , UT.NBT.make(NBT_MATERIAL, aMat, NBT_HARDNESS,   2.0F, NBT_RESISTANCE,   2.0F, NBT_COLOR, UT.Code.getRGBInt(aMat.fRGBaSolid), NBT_PIPERENDER,   0, NBT_DIAMETER, PX_P[ 4], NBT_PIPESIZE, aVoltage, NBT_PIPEBANDWIDTH, aAmperage* 2, NBT_CONTACTDAMAGE, aContactDamageWire, NBT_PIPELOSS, aLossWire)), T, F, T);
		OreDictManager.INSTANCE.setTarget_(OP.cableLXGt04             , aMat, aRegistry.add( "4x " + aMat.getLocal() + " Optical Fiber"                    , "Electric Wires"          , aID+ 2, aCreativeTabID, aClass, aMat.mToolQuality, 16, aBlock     , UT.NBT.make(NBT_MATERIAL, aMat, NBT_HARDNESS,   2.0F, NBT_RESISTANCE,   2.0F, NBT_COLOR, UT.Code.getRGBInt(aMat.fRGBaSolid), NBT_PIPERENDER,   0, NBT_DIAMETER, PX_P[ 6], NBT_PIPESIZE, aVoltage, NBT_PIPEBANDWIDTH, aAmperage* 4, NBT_CONTACTDAMAGE, aContactDamageWire, NBT_PIPELOSS, aLossWire)), T, F, T);
		OreDictManager.INSTANCE.setTarget_(OP.cableLXGt08             , aMat, aRegistry.add( "8x " + aMat.getLocal() + " Optical Fiber"                    , "Electric Wires"          , aID+ 3, aCreativeTabID, aClass, aMat.mToolQuality,  8, aBlock     , UT.NBT.make(NBT_MATERIAL, aMat, NBT_HARDNESS,   2.0F, NBT_RESISTANCE,   2.0F, NBT_COLOR, UT.Code.getRGBInt(aMat.fRGBaSolid), NBT_PIPERENDER,   0, NBT_DIAMETER, PX_P[ 8], NBT_PIPESIZE, aVoltage, NBT_PIPEBANDWIDTH, aAmperage* 8, NBT_CONTACTDAMAGE, aContactDamageWire, NBT_PIPELOSS, aLossWire)), T, F, T);
		OreDictManager.INSTANCE.setTarget_(OP.cableLXGt12             , aMat, aRegistry.add("12x " + aMat.getLocal() + " Optical Fiber"                    , "Electric Wires"          , aID+ 4, aCreativeTabID, aClass, aMat.mToolQuality,  4, aBlock     , UT.NBT.make(NBT_MATERIAL, aMat, NBT_HARDNESS,   2.0F, NBT_RESISTANCE,   2.0F, NBT_COLOR, UT.Code.getRGBInt(aMat.fRGBaSolid), NBT_PIPERENDER,   0, NBT_DIAMETER, PX_P[12], NBT_PIPESIZE, aVoltage, NBT_PIPEBANDWIDTH, aAmperage*12, NBT_CONTACTDAMAGE, aContactDamageWire, NBT_PIPELOSS, aLossWire)), T, F, T);
		OreDictManager.INSTANCE.setTarget_(OP.cableLXGt16             , aMat, aRegistry.add("16x " + aMat.getLocal() + " Optical Fiber"                    , "Electric Wires"          , aID+ 5, aCreativeTabID, aClass, aMat.mToolQuality,  4, aBlock     , UT.NBT.make(NBT_MATERIAL, aMat, NBT_HARDNESS,   2.0F, NBT_RESISTANCE,   2.0F, NBT_COLOR, UT.Code.getRGBInt(aMat.fRGBaSolid), NBT_PIPERENDER,   0, NBT_DIAMETER, PX_P[16], NBT_PIPESIZE, aVoltage, NBT_PIPEBANDWIDTH, aAmperage*16, NBT_CONTACTDAMAGE, aContactDamageWire, NBT_PIPELOSS, aLossWire)), T, F, T);
	}

	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_PIPELOSS)) mLoss = Math.max(1, aNBT.getLong(NBT_PIPELOSS));
		if (aNBT.hasKey(NBT_PIPESIZE)) mVoltage = Math.max(1, aNBT.getLong(NBT_PIPESIZE));
		if (aNBT.hasKey(NBT_PIPEBANDWIDTH)) mAmperage = Math.max(1, aNBT.getLong(NBT_PIPEBANDWIDTH));
		if (aNBT.hasKey(NBT_PIPERENDER)) mRenderType = aNBT.getByte(NBT_PIPERENDER);
	}

	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
	}

	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get(LH.WIRE_STATS_VOLTAGE) + mVoltage + " " + TD.Energy.LX.getLocalisedNameShort() + " (" + VN[UT.Code.tierMin(mVoltage)] + ")");
		aList.add(Chat.CYAN     + LH.get(LH.WIRE_STATS_AMPERAGE) + mAmperage);
		aList.add(Chat.CYAN     + LH.get(LH.WIRE_STATS_LOSS) + mLoss + " " + TD.Energy.LX.getLocalisedNameShort() + "/m");
		if (mContactDamage) aList.add(Chat.DRED     + LH.get(LH.HAZARD_CONTACT));
		super.addToolTips(aList, aStack, aF3_H);
	}

	@Override
	@SuppressWarnings("deprecation")
	public void onTick2(long aTimer, boolean aIsServerSide) {
		super.onTick2(aTimer, aIsServerSide);

		if (aIsServerSide) {
			if (mBurnCounter >= 16) {
				setToFire();
			} else {
				if (aTimer % 512 == 2 && mBurnCounter > 0) mBurnCounter--;
				mWattageLast = mTransferredWattage;
				mTransferredWattage = 0;
				mTransferredAmperes = 0;
				/*
				if (EnergyCompat.IC_ENERGY) for (byte tSide : ALL_SIDES_VALID) if (canAcceptEnergyFrom(tSide)) {
					DelegatorTileEntity<TileEntity> tDelegator = getAdjacentTileEntity(tSide);
					if (!(tDelegator.mTileEntity instanceof ITileEntityEnergy) && !(tDelegator.mTileEntity instanceof gregapi6.tileentity.ITileEntityEnergy)) {
						TileEntity tEmitter = tDelegator.mTileEntity instanceof IEnergyTile || EnergyNet.instance == null ? tDelegator.mTileEntity : EnergyNet.instance.getTileEntity(tDelegator.mWorld, tDelegator.mX, tDelegator.mY, tDelegator.mZ);
						if (tEmitter instanceof IEnergySource && ((IEnergySource)tEmitter).emitsEnergyTo(this, tDelegator.getForgeSideOfTileEntity())) {
							long tEU = (long)((IEnergySource)tEmitter).getOfferedEnergy();
							if (transferElectricity(tSide, tEU, 1, -1, new HashSetNoNulls<TileEntity>(F, this)) > 0) ((IEnergySource)tEmitter).drawEnergy(tEU);
						}
					}
				}
				*/
			}
		}
	}

	public long transferElectricity(byte aSide, long aVoltage, long aAmperage, long aChannel, HashSetNoNulls<TileEntity> aAlreadyPassed) {
		if (mTimer < 1 || Math.abs(aVoltage) <= mLoss) return 0;
		if (aVoltage > 0) aVoltage -= mLoss; else aVoltage += mLoss;

		long rUsedAmperes = 0;
		for (byte tSide : ALL_SIDES_VALID_BUT[aSide]) if (canEmitEnergyTo(tSide)) {
			if (aAmperage <= rUsedAmperes) break;
			DelegatorTileEntity<TileEntity> tDelegator = getAdjacentTileEntity(tSide);
			if (aAlreadyPassed.add(tDelegator.mTileEntity)) {
				if (tDelegator.mTileEntity instanceof MultiTileEntityOpticalFiber) {
					if (((MultiTileEntityOpticalFiber)tDelegator.mTileEntity).isEnergyAcceptingFrom(TD.Energy.LX, tDelegator.mSideOfTileEntity, F)) {
						rUsedAmperes += ((MultiTileEntityOpticalFiber)tDelegator.mTileEntity).transferElectricity(tDelegator.mSideOfTileEntity, aVoltage, aAmperage-rUsedAmperes, aChannel, aAlreadyPassed);
					}
				} else {
					rUsedAmperes += ITileEntityEnergy.Util.insertEnergyInto(TD.Energy.LX, aVoltage, aAmperage-rUsedAmperes, this, tDelegator);
				}
			}
		}
		return rUsedAmperes > 0 ? addToEnergyTransferred(aVoltage, rUsedAmperes) ? rUsedAmperes : aAmperage : 0;
	}

	public boolean addToEnergyTransferred(long aVoltage, long aAmperage) {
		mTransferredAmperes += aAmperage;
		mTransferredWattage += Math.abs(aVoltage * aAmperage);
		if (Math.abs(aVoltage) > mVoltage || mTransferredAmperes > mAmperage) {
			if (mBurnCounter < 16) mBurnCounter++;
			return F;
		}
		return T;
	}

	@Override public boolean canConnect(byte aSide, DelegatorTileEntity<TileEntity> aDelegator) {
		if (aDelegator.mTileEntity instanceof ITileEntityEnergy) return ((ITileEntityEnergy)aDelegator.mTileEntity).isEnergyAcceptingFrom(TD.Energy.LX, aDelegator.mSideOfTileEntity, T) || ((ITileEntityEnergy)aDelegator.mTileEntity).isEnergyEmittingTo(TD.Energy.LX, aDelegator.mSideOfTileEntity, T);
		return F;

		//return EnergyCompat.canConnectElectricity(this, aDelegator.mTileEntity, aDelegator.mSideOfTileEntity);
	}

	@Override public void onEntityCollidedWithBlock(Entity aEntity) {if (mContactDamage && !mFoamDried) UT.Entities.applyElectricityDamage(aEntity, mWattageLast);}

	@Override public boolean isEnergyType(TagData aEnergyType, byte aSide, boolean aEmitting) {return aEnergyType == TD.Energy.LX;}
	@Override public Collection<TagData> getEnergyTypes(byte aSide) {return TD.Energy.LX.AS_LIST;}

	@Override public boolean isEnergyEmittingTo   (TagData aEnergyType, byte aSide, boolean aTheoretical) {return isEnergyType(aEnergyType, aSide, T) && canEmitEnergyTo    (aSide);}
	@Override public boolean isEnergyAcceptingFrom(TagData aEnergyType, byte aSide, boolean aTheoretical) {return isEnergyType(aEnergyType, aSide, F) && canAcceptEnergyFrom(aSide);}
	@Override public synchronized long doEnergyExtraction(TagData aEnergyType, byte aSide, long aSize, long aAmount, boolean aDoExtract) {return 0;}
	@Override public synchronized long doEnergyInjection (TagData aEnergyType, byte aSide, long aSize, long aAmount, boolean aDoInject ) {return aSize != 0 && isEnergyAcceptingFrom(aEnergyType, aSide, F) ?  aDoInject ? transferElectricity(aSide, aSize, aAmount, -1, new HashSetNoNulls<TileEntity>(F, this)) : aAmount : 0;}
	@Override public long getEnergySizeOutputRecommended(TagData aEnergyType, byte aSide) {return mVoltage;}
	@Override public long getEnergySizeOutputMin(TagData aEnergyType, byte aSide) {return 0;}
	@Override public long getEnergySizeOutputMax(TagData aEnergyType, byte aSide) {return mVoltage;}
	@Override public long getEnergySizeInputRecommended(TagData aEnergyType, byte aSide) {return mVoltage;}
	@Override public long getEnergySizeInputMin(TagData aEnergyType, byte aSide) {return 0;}
	@Override public long getEnergySizeInputMax(TagData aEnergyType, byte aSide) {return mVoltage;}

	@Override public boolean canDrop(int aInventorySlot) {return F;}
	@Override public boolean isObstructingBlockAt(byte aSide) {return F;} // Btw, Wires have this but Pipes don't. This is because Wires are flexible, while Pipes aren't.

	@Override public boolean isEnergyConducting(TagData aEnergyType) {return aEnergyType == TD.Energy.LX;}
	@Override public long getEnergyMaxSize(TagData aEnergyType) {return aEnergyType == TD.Energy.LX ? mVoltage : 0;}
	@Override public long getEnergyMaxPackets(TagData aEnergyType) {return aEnergyType == TD.Energy.LX ? mAmperage : 0;}
	@Override public long getEnergyLossPerMeter(TagData aEnergyType) {return aEnergyType == TD.Energy.LX ? mLoss : 0;}
	@Override public OreDictMaterial getEnergyConductorMaterial() {return mMaterial;}
	@Override public OreDictMaterial getEnergyConductorInsulation() {switch(mRenderType) {case 1: case 2: return MT.Rubber; default: return MT.NULL;}}

	public boolean canEmitEnergyTo                          (byte aSide) {return connected(aSide);}
	public boolean canAcceptEnergyFrom                      (byte aSide) {return connected(aSide);}

	@Override public long getProgressValue                  (byte aSide) {return mTransferredAmperes;}
	@Override public long getProgressMax                    (byte aSide) {return mAmperage;}

	@Override public ArrayList<String> getDebugInfo(int aScanLevel) {return aScanLevel > 0 ? new ArrayListNoNulls<>(F, "Transferred Power: " + mTransferredWattage) : null;}

	@Override public ITexture getTextureSide                (byte aSide, byte aConnections, float aDiameter, int aRenderPass) {return mRenderType == 1 || mRenderType == 2 ? BlockTextureDefault.get(Textures.BlockIcons.INSULATION_FULL, isPainted()?mRGBa:UT.Code.getRGBInt(64, 64, 64)) : BlockTextureDefault.get(mMaterial, getIconIndexSide(aSide, aConnections, aDiameter, aRenderPass), F, mRGBa);}
	@Override public ITexture getTextureConnected           (byte aSide, byte aConnections, float aDiameter, int aRenderPass) {return mRenderType == 1 || mRenderType == 2 ? BlockTextureMulti.get(BlockTextureDefault.get(mMaterial, getIconIndexConnected(aSide, aConnections, aDiameter, aRenderPass), mIsGlowing), BlockTextureDefault.get(mRenderType==2?Textures.BlockIcons.INSULATION_BUNDLED:aDiameter<0.37F?Textures.BlockIcons.INSULATION_TINY:aDiameter<0.49F?Textures.BlockIcons.INSULATION_SMALL:aDiameter<0.74F?Textures.BlockIcons.INSULATION_MEDIUM:aDiameter<0.99F?Textures.BlockIcons.INSULATION_LARGE:Textures.BlockIcons.INSULATION_HUGE, isPainted()?mRGBa:UT.Code.getRGBInt(64, 64, 64))) : BlockTextureDefault.get(mMaterial, getIconIndexConnected(aSide, aConnections, aDiameter, aRenderPass), mIsGlowing, mRGBa);}

	@Override public int getIconIndexSide                   (byte aSide, byte aConnections, float aDiameter, int aRenderPass) {return OP.wire.mIconIndexBlock;}
	@Override public int getIconIndexConnected              (byte aSide, byte aConnections, float aDiameter, int aRenderPass) {return OP.wire.mIconIndexBlock;}

	@Override public Collection<TagData> getConnectorTypes  (byte aSide) {return TD.Connectors.WIRE_OpticalFiber.AS_LIST;}

	@Override public String getFacingTool                   () {return TOOL_cutter;}

	@Override public String getTileEntityName               () {return "gt6.multitileentity.connector.wire.opticalfiber";}
}
