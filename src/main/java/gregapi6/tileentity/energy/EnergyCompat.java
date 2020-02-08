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

package gregapi6.tileentity.energy;

import static gregapi6.data.CS.*;

import gregapi6.code.TagData;
import gregapi6.data.MD;
import gregapi6.data.TD;
import gregapi6.util.UT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 *
 * For mostly Internal Use.
 */
public class EnergyCompat {
	public static boolean RF_ENERGY = F, AE_ENERGY = F, IC_ENERGY = F, BB_ENERGY = F, GC_ENERGY = F, BC_LASER = F, GT5U_ENERGY = F;

	/** Gets Called once during postInit to see which Interfaces are there and Classloaded. */
	public static void checkAvailabilities() {
		try {
			cofh.api.energy.IEnergyHandler                               .class.getCanonicalName();
			cofh.api.energy.IEnergyReceiver                              .class.getCanonicalName();
			cofh.api.energy.IEnergyConnection                            .class.getCanonicalName();
			RF_ENERGY = T;
		} catch(Throwable e) {/**/}
		try {
			appeng.tile.powersink.IC2                                    .class.getCanonicalName();
			AE_ENERGY = T;
		} catch(Throwable e) {/**/}
		try {
			ic2.api.energy.tile.IEnergySink                              .class.getCanonicalName();
			ic2.api.energy.tile.IEnergySource                            .class.getCanonicalName();
			ic2.api.energy.tile.IEnergyConductor                         .class.getCanonicalName();
			IC_ENERGY = T;
		} catch(Throwable e) {/**/}
		try {
			com.builtbroken.mc.api.energy.IEnergyBufferProvider          .class.getCanonicalName();
			com.builtbroken.mc.api.energy.IEnergyBuffer                  .class.getCanonicalName();
			BB_ENERGY = T;
		} catch(Throwable e) {/**/}
		try {
			micdoodle8.mods.galacticraft.api.power.IEnergyHandlerGC      .class.getCanonicalName();
			micdoodle8.mods.galacticraft.api.power.EnergySource          .class.getCanonicalName();
			micdoodle8.mods.galacticraft.api.transmission.tile.IConnector.class.getCanonicalName();
			micdoodle8.mods.galacticraft.api.transmission.NetworkType    .class.getCanonicalName();
			micdoodle8.mods.galacticraft.core.energy.EnergyConfigHandler .class.getCanonicalName();
			GC_ENERGY = T;
		} catch(Throwable e) {/**/}
		try {
			buildcraft.api.power.ILaserTarget                            .class.getCanonicalName();
			BC_LASER = T;
		} catch(Throwable e) {/**/}

		try {
			//gregtech.api.interfaces.tileentity.IEnergyConductor                            .class.getCanonicalName();
			GT5U_ENERGY = T;
		} catch(Throwable e) {/**/}
	}

	public static boolean isElectricRFReceiver(TileEntity aReceiver) {
		if (aReceiver == null) return F;
		if (MD.FUNK.mLoaded && aReceiver.getClass().getName().startsWith("com.rwtema.funkylocomotion"     )) return T;
		if (MD.OMT .mLoaded && aReceiver.getClass().getName().startsWith("openmodularturrets"             )) return T;
		if (MD.TG  .mLoaded && aReceiver.getClass().getName().startsWith("techguns"                       )) return T;
		if (MD.IE  .mLoaded && aReceiver.getClass().getName().startsWith("blusunrize.immersiveengineering")) return T;
		return F;
	}

	@SuppressWarnings("deprecation")
	public static boolean canConnectElectricity(TileEntity aThis, TileEntity aTarget, byte aSide) {
		if (aTarget == null) return F;
		if (aTarget instanceof ITileEntityEnergy                                      ) return ((ITileEntityEnergy                                               )aTarget).isEnergyAcceptingFrom(TD.Energy.EU, aSide, T) || ((ITileEntityEnergy                                  )aTarget).isEnergyEmittingTo(TD.Energy.EU, aSide, T);
		if (aTarget instanceof gregapi6.tileentity.ITileEntityEnergy                   ) return ((gregapi6.tileentity.ITileEntityEnergy                            )aTarget).isEnergyAcceptingFrom(TD.Energy.EU, aSide, T) || ((gregapi6.tileentity.ITileEntityEnergy               )aTarget).isEnergyEmittingTo(TD.Energy.EU, aSide, T);
		if (aTarget instanceof gregtech6.api.interfaces.tileentity.IEnergyConnected    ) return T; // return ((gregtech6.api.interfaces.tileentity.IEnergyConnected)aTarget).inputEnergyFrom      (aSide                 ) || ((gregtech6.api.interfaces.tileentity.IEnergyConnected)aTarget).outputsEnergyTo(aSide);
		if (AE_ENERGY && aThis != null && aTarget instanceof appeng.tile.powersink.IC2) return ((appeng.tile.powersink.IC2                                       )aTarget).acceptsEnergyFrom    (aThis, FORGE_DIR[aSide]);

		if (GC_ENERGY && aTarget instanceof micdoodle8.mods.galacticraft.api.power.IEnergyHandlerGC && (!(aTarget instanceof micdoodle8.mods.galacticraft.api.transmission.tile.IConnector) || ((micdoodle8.mods.galacticraft.api.transmission.tile.IConnector)aTarget).canConnect(FORGE_DIR[aSide], micdoodle8.mods.galacticraft.api.transmission.NetworkType.POWER))) return T;

		if (BB_ENERGY && aTarget instanceof com.builtbroken.mc.api.energy.IEnergyBufferProvider && ((com.builtbroken.mc.api.energy.IEnergyBufferProvider)aTarget).getEnergyBuffer(FORGE_DIR[aSide]) != null) return T;

		if (IC_ENERGY && aThis != null) {
			TileEntity tConnected = (aTarget instanceof ic2.api.energy.tile.IEnergyTile || ic2.api.energy.EnergyNet.instance == null ? aTarget : ic2.api.energy.EnergyNet.instance.getTileEntity(aTarget.getWorldObj(), aTarget.xCoord, aTarget.yCoord, aTarget.zCoord));
			if (tConnected instanceof ic2.api.energy.tile.IEnergySink   && ((ic2.api.energy.tile.IEnergySink  )tConnected).acceptsEnergyFrom(aThis, FORGE_DIR[aSide])) return T;
			if (tConnected instanceof ic2.api.energy.tile.IEnergySource && ((ic2.api.energy.tile.IEnergySource)tConnected).emitsEnergyTo    (aThis, FORGE_DIR[aSide])) return T;
		}

		/*
		if (GT5U_ENERGY && aThis != null) {
			TileEntity tConnected = (aTarget instanceof gregtech.api.interfaces.tileentity.IEnergyConductor || ic2.api.energy.EnergyNet.instance == null ? aTarget : ic2.api.energy.EnergyNet.instance.getTileEntity(aTarget.getWorldObj(), aTarget.xCoord, aTarget.yCoord, aTarget.zCoord));
			if (tConnected instanceof gregtech.api.interfaces.tileentity.IEnergyConductor   && ((gregtech.api.interfaces.tileentity.IEnergyConductor  )tConnected).inputEnergyFrom(aSide)) return T;
			if (tConnected instanceof gregtech.api.interfaces.tileentity.IEnergyConductor   && ((gregtech.api.interfaces.tileentity.IEnergyConductor  )tConnected).outputsEnergyTo(aSide)) return T;
		}
		*/

		if (RF_ENERGY && (EMIT_EU_AS_RF || isElectricRFReceiver(aTarget))) {
			if (aTarget instanceof cofh.api.energy.IEnergyReceiver && (!(aTarget instanceof cofh.api.energy.IEnergyConnection) || ((cofh.api.energy.IEnergyConnection)aTarget).canConnectEnergy(FORGE_DIR[aSide]))) return T;
			if (aTarget instanceof cofh.api.energy.IEnergyHandler  && (!(aTarget instanceof cofh.api.energy.IEnergyConnection) || ((cofh.api.energy.IEnergyConnection)aTarget).canConnectEnergy(FORGE_DIR[aSide]))) return T;
		}

		return F;
	}

	public static boolean checkOverCharge(long aSize, TileEntity aReceiver) {
		if (aSize > VMAX[3]) {
			World tWorld = aReceiver.getWorldObj();
			tWorld.setBlockToAir(aReceiver.xCoord, aReceiver.yCoord, aReceiver.zCoord);
			tWorld.newExplosion(null, aReceiver.xCoord, aReceiver.yCoord, aReceiver.zCoord, 5, F, T);
			return T;
		}
		return F;
	}

	@SuppressWarnings("deprecation")
	public static long insertEnergyInto(TagData aEnergyType, byte aSide, long aSize, long aAmount, Object aEmitter, TileEntity aReceiver) {
		if (aAmount <= 0 || aSize == 0 || aReceiver == null) return 0;

		if (aEnergyType == TD.Energy.EU) {
			// Nothing here needs the Negative Part of this, so it is gonna be skipped.
			aSize = Math.abs(aSize);

			// Applied Energistics gets a special case.
			if (AE_ENERGY && aReceiver instanceof appeng.tile.powersink.IC2) {
				if (((appeng.tile.powersink.IC2)aReceiver).acceptsEnergyFrom(aEmitter instanceof TileEntity ? (TileEntity)aEmitter : null, FORGE_DIR[aSide])) {
					if (checkOverCharge(aSize, aReceiver)) return aAmount;
					long rUsedAmount = 0;
					while (aAmount > rUsedAmount && ((appeng.tile.powersink.IC2)aReceiver).getDemandedEnergy() >= aSize && ((appeng.tile.powersink.IC2)aReceiver).injectEnergy(FORGE_DIR[aSide], aSize, aSize) < aSize) rUsedAmount++;
					return rUsedAmount;
				}
				return 0;
			}

			// GalactiCraft and its Addons
			if (GC_ENERGY && COMPAT_GC != null) {
				if (aReceiver instanceof micdoodle8.mods.galacticraft.api.power.IEnergyHandlerGC) {
					if (!(aReceiver instanceof micdoodle8.mods.galacticraft.api.transmission.tile.IConnector) || ((micdoodle8.mods.galacticraft.api.transmission.tile.IConnector)aReceiver).canConnect(FORGE_DIR[aSide], micdoodle8.mods.galacticraft.api.transmission.NetworkType.POWER)) {
						if (checkOverCharge(aSize, aReceiver)) return aAmount;
						float tSizeToReceive = aSize * micdoodle8.mods.galacticraft.core.energy.EnergyConfigHandler.IC2_RATIO, tStored = ((micdoodle8.mods.galacticraft.api.power.IEnergyHandlerGC)aReceiver).getEnergyStoredGC((micdoodle8.mods.galacticraft.api.power.EnergySource)COMPAT_GC.dir(aSide));
						if (tSizeToReceive >= tStored || tSizeToReceive <= ((micdoodle8.mods.galacticraft.api.power.IEnergyHandlerGC)aReceiver).getMaxEnergyStoredGC((micdoodle8.mods.galacticraft.api.power.EnergySource)COMPAT_GC.dir(aSide)) - tStored) {
							float tReceived = ((micdoodle8.mods.galacticraft.api.power.IEnergyHandlerGC)aReceiver).receiveEnergyGC((micdoodle8.mods.galacticraft.api.power.EnergySource)COMPAT_GC.dir(aSide), tSizeToReceive, F);
							if (tReceived > 0) {
								tSizeToReceive -= tReceived;
								while (tSizeToReceive > 0) {
									tReceived = ((micdoodle8.mods.galacticraft.api.power.IEnergyHandlerGC)aReceiver).receiveEnergyGC((micdoodle8.mods.galacticraft.api.power.EnergySource)COMPAT_GC.dir(aSide), tSizeToReceive, F);
									if (tReceived < 1) break;
									tSizeToReceive -= tReceived;
								}
								return 1;
							}
						}
					}
					return 0;
				}
			}

			// Voltz Stuff
			if (BB_ENERGY && aReceiver instanceof com.builtbroken.mc.api.energy.IEnergyBufferProvider) {
				Object tEnergyBuffer = ((com.builtbroken.mc.api.energy.IEnergyBufferProvider)aReceiver).getEnergyBuffer(FORGE_DIR[aSide]);
				if (tEnergyBuffer != null) return checkOverCharge(aSize, aReceiver) ? aAmount : UT.Code.divup(((com.builtbroken.mc.api.energy.IEnergyBuffer)tEnergyBuffer).addEnergyToStorage(UT.Code.bind31(aSize * aAmount) * J_PER_EU, T), aSize * J_PER_EU);
			}

			// Electricity alike RF Receivers that are whitelisted for my Power System.
			if (RF_ENERGY && isElectricRFReceiver(aReceiver)) {
				if (!(aReceiver instanceof cofh.api.energy.IEnergyConnection) || ((cofh.api.energy.IEnergyConnection)aReceiver).canConnectEnergy(FORGE_DIR[aSide])) {
					if (aReceiver instanceof cofh.api.energy.IEnergyReceiver) return checkOverCharge(aSize, aReceiver) ? aAmount : UT.Code.divup(((cofh.api.energy.IEnergyReceiver)aReceiver).receiveEnergy(FORGE_DIR[aSide], UT.Code.bind31(aAmount * aSize * RF_PER_EU), F), aSize * RF_PER_EU);
					if (aReceiver instanceof cofh.api.energy.IEnergyHandler ) return checkOverCharge(aSize, aReceiver) ? aAmount : UT.Code.divup(((cofh.api.energy.IEnergyHandler )aReceiver).receiveEnergy(FORGE_DIR[aSide], UT.Code.bind31(aAmount * aSize * RF_PER_EU), F), aSize * RF_PER_EU);
				}
				return 0;
			}

			// Since GT5U is still basically an IC2-Addon, I don't want the IC2 Power System to come before this by accident.
			/*
			if (aReceiver instanceof gregtech.api.interfaces.tileentity.IEnergyConnected) {
				return ((gregtech.api.interfaces.tileentity.IEnergyConnected)aReceiver).injectEnergyUnits(aSide, aSize, aAmount);
			}
			*/

			// IC2 Power at last, because special cases should always override the very "compatible" IC2 Stuff.
			if (IC_ENERGY) {
				TileEntity tReceiver = (aReceiver instanceof ic2.api.energy.tile.IEnergyTile || ic2.api.energy.EnergyNet.instance == null ? aReceiver : ic2.api.energy.EnergyNet.instance.getTileEntity(aReceiver.getWorldObj(), aReceiver.xCoord, aReceiver.yCoord, aReceiver.zCoord));
				if (tReceiver instanceof ic2.api.energy.tile.IEnergySink && ((ic2.api.energy.tile.IEnergySink)tReceiver).acceptsEnergyFrom(aEmitter instanceof TileEntity ? (TileEntity)aEmitter : null, FORGE_DIR[aSide])) {
					long rUsedAmount = 0;
					while (aAmount > rUsedAmount && ((ic2.api.energy.tile.IEnergySink)tReceiver).getDemandedEnergy() >= aSize && ((ic2.api.energy.tile.IEnergySink)tReceiver).injectEnergy(FORGE_DIR[aSide], aSize, aSize) < aSize) rUsedAmount++;
					if (rUsedAmount > 0) {
						int tTier = ((ic2.api.energy.tile.IEnergySink)tReceiver).getSinkTier();
						if (tTier >= 0 && tTier < VMAX.length-1 && aSize > VMAX[tTier]) {
							World tWorld = tReceiver.getWorldObj();
							tWorld.setBlockToAir(tReceiver.xCoord, tReceiver.yCoord, tReceiver.zCoord);
							tWorld.newExplosion(null, tReceiver.xCoord, tReceiver.yCoord, tReceiver.zCoord, tTier+1, F, T);
							return aAmount;
						}
					}
					return rUsedAmount;
				}
			}
		}

		if (RF_ENERGY && aSize > 0) {
			long tSizeToReceive = 0;
			// GT KineticUnits auto-convert to RF, but only in the Push Phase, so when they are postive!
			if (aEnergyType == TD.Energy.KU) tSizeToReceive = aSize * RF_PER_EU; else
			// MJ auto-convert to RF too. And yes I do know that BuildCraft and other Mods moved away from MJ to use RF.
			if (aEnergyType == TD.Energy.MJ) tSizeToReceive = aSize * RF_PER_MJ; else
			// RF is RF and therefore doesn't really need to be converted, therefore 1:1 Ratio
			if (aEnergyType == TD.Energy.RF) tSizeToReceive = aSize; else
			// If the usage of EU from GT6 counts as RF for Consumers then allow this
			if (aEnergyType == TD.Energy.EU && EMIT_EU_AS_RF) tSizeToReceive = aSize * RF_PER_EU;

			if (tSizeToReceive > 0) {
				if (!(aReceiver instanceof cofh.api.energy.IEnergyConnection) || ((cofh.api.energy.IEnergyConnection)aReceiver).canConnectEnergy(FORGE_DIR[aSide])) {
					if (aReceiver instanceof cofh.api.energy.IEnergyReceiver) return UT.Code.divup(((cofh.api.energy.IEnergyReceiver)aReceiver).receiveEnergy(FORGE_DIR[aSide], UT.Code.bind31(aAmount * tSizeToReceive), F), tSizeToReceive);
					if (aReceiver instanceof cofh.api.energy.IEnergyHandler ) return UT.Code.divup(((cofh.api.energy.IEnergyHandler )aReceiver).receiveEnergy(FORGE_DIR[aSide], UT.Code.bind31(aAmount * tSizeToReceive), F), tSizeToReceive);
				}
			}
		}
		return 0;
	}
}
