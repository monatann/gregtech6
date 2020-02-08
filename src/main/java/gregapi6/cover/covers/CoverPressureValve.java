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

package gregapi6.cover.covers;

import static gregapi6.data.CS.*;

import java.util.ArrayList;
import java.util.List;

import gregapi6.cover.CoverData;
import gregapi6.data.CS.GarbageGT;
import gregapi6.data.CS.SFX;
import gregapi6.data.FL;
import gregapi6.data.LH;
import gregapi6.fluid.FluidTankGT;
import gregapi6.render.BlockTextureDefault;
import gregapi6.render.ITexture;
import gregapi6.tileentity.connectors.MultiTileEntityPipeFluid;
import gregapi6.tileentity.delegate.DelegatorTileEntity;
import gregapi6.util.UT;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.IFluidHandler;

/**
 * @author Gregorius Techneticies
 */
public class CoverPressureValve extends AbstractCoverAttachment {
	@Override public boolean interceptCoverPlacement(byte aCoverSide, CoverData aData, Entity aPlayer) {return !(aData.mTileEntity instanceof MultiTileEntityPipeFluid) || ((MultiTileEntityPipeFluid)aData.mTileEntity).mTanks.length != 1;}
	
	@Override
	@SuppressWarnings("unchecked")
	public void onTickPost(byte aSide, CoverData aData, long aTimer, boolean aIsServerSide, boolean aReceivedBlockUpdate, boolean aReceivedInventoryUpdate) {
		if (aIsServerSide && !aData.mStopped && aTimer > 2 && aData.mTileEntity instanceof MultiTileEntityPipeFluid) {
			FluidTankGT tTank = ((MultiTileEntityPipeFluid)aData.mTileEntity).mTanks[0];
			if (tTank.isFull()) {
				DelegatorTileEntity<IFluidHandler> tDelegator = aData.mTileEntity.getAdjacentTank(aSide);
				if (tDelegator.mTileEntity != null) {
					FL.move(tTank, tDelegator);
				} else if (FL.gas(tTank) && !tDelegator.hasCollisionBox()) {
					UT.Sounds.send(aData.mTileEntity.getWorld(), SFX.MC_FIZZ, 1.0F, 1.0F, aData.mTileEntity.getCoords());
					try {for (Entity tEntity : (ArrayList<Entity>)aData.mTileEntity.getWorld().getEntitiesWithinAABB(Entity.class, aData.box(-2, -2, -2, +3, +3, +3))) UT.Entities.applyTemperatureDamage(tEntity, FL.temperature(tTank.getFluid()), 2.0F);} catch(Throwable e) {if (D1) e.printStackTrace(ERR);}
					GarbageGT.trash(tTank);
				}
			}
		}
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		super.addToolTips(aList, aStack, aF3_H);
		aList.add(LH.Chat.CYAN + "Releases Fluids when Pipe is full.");
		aList.add(LH.Chat.ORANGE + "Liquids require Tank in front!");
		aList.add(LH.Chat.ORANGE + "Gasses require Air or Tank in front!");
	}
	
	@Override public float[] getCoverBounds (byte aCoverSide, CoverData aData) {return BOXES_VALVES[aCoverSide];}
	@Override public float[] getHolderBounds(byte aCoverSide, CoverData aData) {return BOXES_VALVES[aCoverSide];}
	
	public static final float[][] BOXES_VALVES = new float[][] {{PX_P[ 6], PX_P[ 0], PX_P[ 6], PX_N[ 6], PX_N[ 8], PX_N[ 6]}, {PX_P[ 6], PX_P[ 8], PX_P[ 6], PX_N[ 6], PX_N[ 0], PX_N[ 6]}, {PX_P[ 6], PX_P[ 6], PX_P[ 0], PX_N[ 6], PX_N[ 6], PX_N[ 8]}, {PX_P[ 6], PX_P[ 6], PX_P[ 8], PX_N[ 6], PX_N[ 6], PX_N[ 0]}, {PX_P[ 0], PX_P[ 6], PX_P[ 6], PX_N[ 8], PX_N[ 6], PX_N[ 6]}, {PX_P[ 8], PX_P[ 6], PX_P[ 6], PX_N[ 0], PX_N[ 6], PX_N[ 6]}};
	
	@Override public ITexture getCoverTextureSurface   (byte aSide, CoverData aData) {return sTextureFront;}
	@Override public ITexture getCoverTextureAttachment(byte aSide, CoverData aData, byte aTextureSide) {return aSide == aTextureSide ? sTextureFront : null;}
	@Override public ITexture getCoverTextureHolder    (byte aSide, CoverData aData, byte aTextureSide) {return aSide == aTextureSide ? null : sTextureSide;}
	
	@Override public boolean isSolid(byte aSide, CoverData aData) {return F;}
	@Override public boolean isOpaque(byte aSide, CoverData aData) {return F;}
	@Override public boolean isFullTexture(byte aCoverSide, CoverData aData) {return F;}
	@Override public boolean showsConnectorFront(byte aCoverSide, CoverData aData) {return F;}
	
	public static final ITexture
	sTextureFront = BlockTextureDefault.get("machines/covers/pressurevalve/front"),
	sTextureSide = BlockTextureDefault.get("machines/covers/pressurevalve/side");
}
