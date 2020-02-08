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
import gregapi6.data.LH;
import gregapi6.data.LH.Chat;
import gregapi6.render.BlockTextureCopied;
import gregapi6.render.ITexture;
import gregapi6.util.OM;
import gregapi6.util.ST;
import gregapi6.util.UT;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityMiniPortalEnd extends MultiTileEntityMiniPortal {
	public static List<MultiTileEntityMiniPortalEnd>
	sListEndSide = new ArrayListNoNulls<>(),
	sListWorldSide  = new ArrayListNoNulls<>();
	
	static {
		LH.add("gt6.tileentity.portal.end.tooltip.1", "Only works between the End and the Overworld with a x128 Distance Factor!");
		LH.add("gt6.tileentity.portal.end.tooltip.2", "Margin of Error to still work: 512 Meters.");
		LH.add("gt6.tileentity.portal.end.tooltip.3", "Requires Ender Eye for activation");
	}
	
	@Override
	public void addToolTips2(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get("gt6.tileentity.portal.end.tooltip.1"));
		aList.add(Chat.CYAN     + LH.get("gt6.tileentity.portal.end.tooltip.2"));
		aList.add(Chat.ORANGE   + LH.get("gt6.tileentity.portal.end.tooltip.3"));
	}
	
	@Override
	public void findTargetPortal() {
		mTarget = null;
		if (worldObj != null && isServerSide()) {
			if (worldObj.provider.dimensionId == DIM_OVERWORLD) {
				long tShortestDistance = 512*512;
				for (MultiTileEntityMiniPortalEnd tTarget : sListEndSide) if (tTarget != this && !tTarget.isDead()) {
					long tXDifference = xCoord-tTarget.xCoord*128, tZDifference = zCoord-tTarget.zCoord*128;
					long tTempDist = tXDifference * tXDifference + tZDifference * tZDifference;
					if (tTempDist < tShortestDistance) {
						tShortestDistance = tTempDist;
						mTarget = tTarget;
					} else if (tTempDist == tShortestDistance && (mTarget == null || Math.abs(tTarget.yCoord-yCoord) < Math.abs(mTarget.yCoord-yCoord))) {
						mTarget = tTarget;
					}
				}
			} else if (worldObj.provider.dimensionId == DIM_END) {
				long tShortestDistance = 512*512;
				for (MultiTileEntityMiniPortalEnd tTarget : sListWorldSide) if (tTarget != this && !tTarget.isDead()) {
					long tXDifference = tTarget.xCoord-xCoord*128, tZDifference = tTarget.zCoord-zCoord*128;
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
		if (worldObj != null && isServerSide()) {
			if (worldObj.provider.dimensionId == DIM_OVERWORLD) {
				if (!sListWorldSide.contains(this)) sListWorldSide.add(this);
				for (MultiTileEntityMiniPortalEnd tPortal : sListEndSide) tPortal.findTargetPortal();
				findTargetPortal();
			} else if (worldObj.provider.dimensionId == DIM_END) {
				if (!sListEndSide.contains(this)) sListEndSide.add(this);
				for (MultiTileEntityMiniPortalEnd tPortal : sListWorldSide) tPortal.findTargetPortal();
				findTargetPortal();
			} else {
				setPortalInactive();
			}
		}
	}
	
	@Override
	public void removeThisPortalFromLists() {
		if (sListWorldSide.remove(this)) for (MultiTileEntityMiniPortal tPortal : sListEndSide) if (tPortal.mTarget == this) tPortal.findTargetPortal();
		if (sListEndSide.remove(this)) for (MultiTileEntityMiniPortal tPortal : sListWorldSide) if (tPortal.mTarget == this) tPortal.findTargetPortal();
	}
	
	@Override
	public boolean onBlockActivated2(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isServerSide()) {
			ItemStack aStack = aPlayer.inventory.getCurrentItem();
			if (ST.valid(aStack) && aStack.stackSize > 0 && OM.is_("gemEnderEye", aStack)) {
				setPortalActive();
				if (mTarget != null) UT.Entities.sendchat(aPlayer, "X: " + mTarget.xCoord + "   Y: " + mTarget.yCoord + "   Z: " + mTarget.zCoord);
				if (!UT.Entities.hasInfiniteItems(aPlayer)) aStack.stackSize--;
				
			}
		}
		return T;
	}
	
	@Override public float getBlockHardness() {return Blocks.end_stone.getBlockHardness(worldObj, xCoord, yCoord, zCoord);}
	@Override public float getExplosionResistance2() {return Blocks.end_stone.getExplosionResistance(null);}
	
	public ITexture sEndPortal = BlockTextureCopied.get(Blocks.portal, SIDE_ANY, 0, DYE_Black, F, T, T), sEndPortalFrame = BlockTextureCopied.get(Blocks.end_portal_frame, SIDE_TOP, 0);
	@Override public ITexture getPortalTexture() {return sEndPortal;}
	@Override public ITexture getFrameTexture() {return sEndPortalFrame;}
	
	
	@Override public String getTileEntityName() {return "gt6.multitileentity.portal.end";}
}
