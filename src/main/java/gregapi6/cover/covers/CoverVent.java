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

import java.util.List;

import gregapi6.cover.CoverData;
import gregapi6.data.FL;
import gregapi6.data.LH;
import gregapi6.data.MD;
import gregapi6.render.BlockTextureDefault;
import gregapi6.render.ITexture;
import gregapi6.util.WD;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.IFluidHandler;

/**
 * @author Gregorius Techneticies
 */
public class CoverVent extends AbstractCoverAttachment {
	@Override public boolean interceptCoverPlacement(byte aCoverSide, CoverData aData, Entity aPlayer) {return !(aData.mTileEntity.canTick() && aData.mTileEntity instanceof IFluidHandler);}
	
	@Override
	public void onTickPre(byte aSide, CoverData aData, long aTimer, boolean aIsServerSide, boolean aReceivedBlockUpdate, boolean aReceivedInventoryUpdate) {
		if (aIsServerSide && !aData.mStopped && aData.mTileEntity instanceof IFluidHandler && SERVER_TIME % 320 == 5) {
			if (WD.collectable_air(aData.mTileEntity.getWorld(), aData.mTileEntity.getOffsetX(aSide), aData.mTileEntity.getOffsetY(aSide), aData.mTileEntity.getOffsetZ(aSide))) {
				switch(aData.mTileEntity.getWorld().provider.dimensionId) {
				case  0: FL.fill_((IFluidHandler)aData.mTileEntity, ALL_SIDES_THIS_AND_ANY[aSide], FL.Air       .make(16000), T); return;
				case -1: FL.fill_((IFluidHandler)aData.mTileEntity, ALL_SIDES_THIS_AND_ANY[aSide], FL.Air_Nether.make(16000), T); return;
				case +1: FL.fill_((IFluidHandler)aData.mTileEntity, ALL_SIDES_THIS_AND_ANY[aSide], FL.Air_End   .make(16000), T); return;
				}
				String tBiome = aData.mTileEntity.getBiome(aData.mTileEntity.getOffsetX(aSide), aData.mTileEntity.getOffsetZ(aSide)).biomeName;
				if (BIOMES_SPACE.contains(tBiome)) return;
				if (BIOMES_END.contains(tBiome)) {
					FL.fill_((IFluidHandler)aData.mTileEntity, ALL_SIDES_THIS_AND_ANY[aSide], FL.Air_End.make(16000), T);
					return;
				}
				if (BIOMES_NETHER.contains(tBiome)) {
					FL.fill_((IFluidHandler)aData.mTileEntity, ALL_SIDES_THIS_AND_ANY[aSide], FL.Air_Nether.make(16000), T);
					return;
				}
				FL.fill_((IFluidHandler)aData.mTileEntity, ALL_SIDES_THIS_AND_ANY[aSide], FL.Air.make(16000), T);
			}
		}
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		super.addToolTips(aList, aStack, aF3_H);
		if (MD.GC.mLoaded) aList.add(LH.Chat.ORANGE + "Doesn't work on other Planets!");
	}
	
	@Override public boolean isOpaque(byte aSide, CoverData aData) {return T;}
	@Override public boolean showsConnectorFront(byte aCoverSide, CoverData aData) {return F;}
	
	@Override public ITexture getCoverTextureSurface(byte aSide, CoverData aData) {return sTextureFront;}
	@Override public ITexture getCoverTextureAttachment(byte aSide, CoverData aData, byte aTextureSide) {return aSide == aTextureSide ? sTextureFront : aSide == OPPOSITES[aTextureSide] ? sTextureBack : sTextureSides;}
	@Override public ITexture getCoverTextureHolder(byte aSide, CoverData aData, byte aTextureSide) {return sTextureSides;}
	
	public static final ITexture
	sTextureSides = BlockTextureDefault.get("machines/covers/vent/sides"),
	sTextureFront = BlockTextureDefault.get("machines/covers/vent/front"),
	sTextureBack = BlockTextureDefault.get("machines/covers/vent/back");
}
