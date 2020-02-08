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

package gregtech6.tileentity.portals;

import static gregapi6.data.CS.*;

import java.util.List;

import gregapi6.code.ArrayListNoNulls;
import gregapi6.data.CS.BlocksGT;
import gregapi6.data.IL;
import gregapi6.data.LH;
import gregapi6.data.LH.Chat;
import gregapi6.data.MD;
import gregapi6.render.BlockTextureCopied;
import gregapi6.render.ITexture;
import gregapi6.util.ST;
import gregapi6.util.UT;
import gregapi6.util.WD;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityMiniPortalAtum extends MultiTileEntityMiniPortal {
	public static List<MultiTileEntityMiniPortalAtum>
	sListAtumSide = new ArrayListNoNulls<>(),
	sListWorldSide  = new ArrayListNoNulls<>();
	
	static {
		LH.add("gt6.tileentity.portal.atum.tooltip.1", "Only works between the Atum Desert and the Overworld!");
		LH.add("gt6.tileentity.portal.atum.tooltip.2", "Margin of Error to still work: 128 Meters.");
		LH.add("gt6.tileentity.portal.atum.tooltip.3", "Requires the Scarab for activation");
	}
	
	@Override
	public void addToolTips2(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get("gt6.tileentity.portal.atum.tooltip.1"));
		aList.add(Chat.CYAN     + LH.get("gt6.tileentity.portal.atum.tooltip.2"));
		aList.add(Chat.ORANGE   + LH.get("gt6.tileentity.portal.atum.tooltip.3"));
	}
	
	@Override
	public void findTargetPortal() {
		mTarget = null;
		if (MD.ATUM.mLoaded && worldObj != null && isServerSide()) {
			if (worldObj.provider.dimensionId == DIM_OVERWORLD) {
				long tShortestDistance = 128*128;
				for (MultiTileEntityMiniPortalAtum tTarget : sListAtumSide) if (tTarget != this && !tTarget.isDead()) {
					long tXDifference = xCoord-tTarget.xCoord, tZDifference = zCoord-tTarget.zCoord;
					long tTempDist = tXDifference * tXDifference + tZDifference * tZDifference;
					if (tTempDist < tShortestDistance) {
						tShortestDistance = tTempDist;
						mTarget = tTarget;
					} else if (tTempDist == tShortestDistance && (mTarget == null || Math.abs(tTarget.yCoord-yCoord) < Math.abs(mTarget.yCoord-yCoord))) {
						mTarget = tTarget;
					}
				}
			} else if (WD.dimATUM(worldObj)) {
				long tShortestDistance = 128*128;
				for (MultiTileEntityMiniPortalAtum tTarget : sListWorldSide) if (tTarget != this && !tTarget.isDead()) {
					long tXDifference = tTarget.xCoord-xCoord, tZDifference = tTarget.zCoord-zCoord;
					long tTempDist = tXDifference * tXDifference + tZDifference * tZDifference;
					if (tTempDist < tShortestDistance) {
						tShortestDistance = tTempDist;
						mTarget = tTarget;
					} else if (tTempDist == tShortestDistance && (mTarget == null || Math.abs(tTarget.yCoord-yCoord) < Math.abs(mTarget.yCoord-yCoord))) {
						mTarget = tTarget;
					}
				}
			}
		}
	}
	
	@Override
	public void addThisPortalToLists() {
		if (MD.ATUM.mLoaded && worldObj != null && isServerSide()) {
			if (worldObj.provider.dimensionId == DIM_OVERWORLD) {
				if (!sListWorldSide.contains(this)) sListWorldSide.add(this);
				for (MultiTileEntityMiniPortalAtum tPortal : sListAtumSide) tPortal.findTargetPortal();
				findTargetPortal();
			} else if (WD.dimATUM(worldObj)) {
				if (!sListAtumSide.contains(this)) sListAtumSide.add(this);
				for (MultiTileEntityMiniPortalAtum tPortal : sListWorldSide) tPortal.findTargetPortal();
				findTargetPortal();
			} else {
				setPortalInactive();
			}
		}
	}
	
	@Override
	public void removeThisPortalFromLists() {
		if (sListWorldSide.remove(this)) for (MultiTileEntityMiniPortal tPortal : sListAtumSide) if (tPortal.mTarget == this) tPortal.findTargetPortal();
		if (sListAtumSide.remove(this)) for (MultiTileEntityMiniPortal tPortal : sListWorldSide) if (tPortal.mTarget == this) tPortal.findTargetPortal();
	}
	
	@Override
	public boolean onBlockActivated2(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isServerSide()) {
			ItemStack aStack = aPlayer.inventory.getCurrentItem();
			if (ST.valid(aStack) && aStack.stackSize > 0 && IL.ATUM_Scarab.equal(aStack, F, T)) {
				setPortalActive();
				if (mTarget != null) UT.Entities.sendchat(aPlayer, "X: " + mTarget.xCoord + "   Y: " + mTarget.yCoord + "   Z: " + mTarget.zCoord);
				if (!UT.Entities.hasInfiniteItems(aPlayer)) aStack.stackSize--;
				
			}
		}
		return T;
	}
	
	@Override public float getBlockHardness() {return Blocks.stone.getBlockHardness(worldObj, xCoord, yCoord, zCoord);}
	@Override public float getExplosionResistance2() {return Blocks.stone.getExplosionResistance(null);}
	
	public ITexture sAtumPortal = BlockTextureCopied.get(ST.block(MD.ATUM, "tile.portal", Blocks.portal), SIDE_ANY, 0, UNCOLOURED, F, T, T), sAtumPortalFrame = BlockTextureCopied.get(ST.block(MD.ATUM, "tile.carvedBrick", BlocksGT.Limestone), SIDE_ANY, 0, UNCOLOURED, F, F, F);
	@Override public ITexture getPortalTexture() {return sAtumPortal;}
	@Override public ITexture getFrameTexture() {return sAtumPortalFrame;}
	
	
	@Override public String getTileEntityName() {return "gt6.multitileentity.portal.atum";}
}
