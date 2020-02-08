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

package gregtech6.tileentity.misc;

import static gregapi6.data.CS.*;

import java.io.File;
import java.util.List;

import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_GetCollisionBoundingBoxFromPool;
import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_GetLifeSpan;
import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_GetSelectedBoundingBoxFromPool;
import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_OnDespawn;
import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_OnRegistration;
import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_OnServerLoad;
import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_OnServerSave;
import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_SetBlockBoundsBasedOnState;
import gregapi6.block.multitileentity.MultiTileEntityRegistry;
import gregapi6.code.ArrayListNoNulls;
import gregapi6.data.LH;
import gregapi6.data.LH.Chat;
import gregapi6.old.Textures;
import gregapi6.render.BlockTextureDefault;
import gregapi6.render.IIconContainer;
import gregapi6.render.ITexture;
import gregapi6.tileentity.base.TileEntityBase09FacingSingle;
import gregapi6.util.UT;
import gregtech6.GT6_Main;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityCertificate extends TileEntityBase09FacingSingle implements IMTE_OnRegistration, IMTE_OnDespawn, IMTE_GetLifeSpan, IMTE_OnServerLoad, IMTE_OnServerSave, IMTE_SetBlockBoundsBasedOnState, IMTE_GetCollisionBoundingBoxFromPool, IMTE_GetSelectedBoundingBoxFromPool {
	public static final ArrayListNoNulls<String> ALREADY_RECEIVED = new ArrayListNoNulls<>();

	@Override
	public void onServerSave(File aSaveLocation) {
		File aTargetFile = new File(new File(aSaveLocation, "gregtech6"), "certificates.support.dat");
		if (!aTargetFile.exists()) {try {aTargetFile.createNewFile();} catch (Throwable e) {e.printStackTrace(ERR);}}
		NBTTagCompound aNBT = UT.NBT.make();
		for (int i = 0; i < ALREADY_RECEIVED.size(); i++) aNBT.setString(""+i, ALREADY_RECEIVED.get(i));
		try {CompressedStreamTools.write(aNBT, aTargetFile);} catch (Throwable e) {e.printStackTrace(ERR);}
	}

	@Override
	public void onServerLoad(File aSaveLocation) {
		ALREADY_RECEIVED.clear();
		File aTargetFile = new File(new File(aSaveLocation, "gregtech6"), "certificates.support.dat");
		if (aTargetFile.exists()) {
			NBTTagCompound aNBT = null;
			try {aNBT = CompressedStreamTools.read(aTargetFile);} catch (Throwable e) {e.printStackTrace(ERR);}
			if (aNBT != null) for (int i = 0; i < Integer.MAX_VALUE; i++) {
				if (!aNBT.hasKey(""+i)) break;
				ALREADY_RECEIVED.add(aNBT.getString(""+i));
			}
		}
	}

	public static MultiTileEntityRegistry MTE_REGISTRY = null;
	public static MultiTileEntityCertificate INSTANCE;

	@Override
	public void onRegistration(MultiTileEntityRegistry aRegistry, short aID) {
		INSTANCE = this;
		MTE_REGISTRY = aRegistry;
	}

	public static ItemStack getCertificate(int aAmount, String aName) {
		NBTTagCompound tNBT = UT.NBT.make();
		if (UT.Code.stringValid(aName)) tNBT.setTag("display", UT.NBT.makeString(tNBT.getCompoundTag("display"), "Name", aName));
		return MTE_REGISTRY.getItem(INSTANCE.getMultiTileEntityID(), aAmount, tNBT);
	}

	public boolean mSilver = F, mGold = F;

	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		String tName = getCustomName();
		if (UT.Code.stringValid(tName)) {
			ALREADY_RECEIVED.add(tName);
			ALREADY_RECEIVED.add(tName.toLowerCase());
			mGold = GT6_Main.gt_proxy.mSupporterListGold.contains(tName.toLowerCase());
			mSilver = GT6_Main.gt_proxy.mSupporterListSilver.contains(tName.toLowerCase());
		}
	}

	@Override
	public void setCustomName(String aName) {
		super.setCustomName(aName);
		if (UT.Code.stringValid(aName)) {
			mGold = GT6_Main.gt_proxy.mSupporterListGold.contains(aName.toLowerCase());
			mSilver = GT6_Main.gt_proxy.mSupporterListSilver.contains(aName.toLowerCase());
		}
	}

	static {LH.add("gt6.tooltip.certificate", "Certified Support of GregTech");}

	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.DGRAY + LH.get("gt6.tooltip.certificate"));
		super.addToolTips(aList, aStack, aF3_H);
	}

	@Override
	public boolean onBlockActivated3(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isServerSide()) {
			if (mGold) {UT.Entities.sendchat(aPlayer, "This Certificate is owned by " + LH.Chat.YELLOW + getCustomName()); return T;}
			if (mSilver) {UT.Entities.sendchat(aPlayer, "This Certificate is owned by " + LH.Chat.CYAN + getCustomName()); return T;}
			UT.Entities.sendchat(aPlayer, "This Certificate is invalid!");
			return T;
		}
		return T;
	}

	@Override
	public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		box(aBlock, PX_P[SIDE_X_NEG==mFacing?15:SIDE_X_POS==mFacing?0:1], PX_P[SIDE_Y_NEG==mFacing?15:SIDE_Y_POS==mFacing?0:1], PX_P[SIDE_Z_NEG==mFacing?15:SIDE_Z_POS==mFacing?0:1], PX_N[SIDE_X_POS==mFacing?15:SIDE_X_NEG==mFacing?0:1], PX_N[SIDE_Y_POS==mFacing?15:SIDE_Y_NEG==mFacing?0:1], PX_N[SIDE_Z_POS==mFacing?15:SIDE_Z_NEG==mFacing?0:1]);
		return T;
	}

	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		if (aSide == OPPOSITES[mFacing]) return aShouldSideBeRendered[aSide]?BlockTextureDefault.get(sTextureBack):null;
		return BlockTextureDefault.get(mGold?sTextureGold:mSilver?sTextureSilver:sTextureBack);
	}

	@Override
	public boolean checkObstruction(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		return F;
	}

	@Override public int getLightOpacity() {return LIGHT_OPACITY_NONE;}

	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return box(PX_P[SIDE_X_NEG==mFacing?15:SIDE_X_POS==mFacing?0:1], PX_P[SIDE_Y_NEG==mFacing?15:SIDE_Y_POS==mFacing?0:1], PX_P[SIDE_Z_NEG==mFacing?15:SIDE_Z_POS==mFacing?0:1], PX_N[SIDE_X_POS==mFacing?15:SIDE_X_NEG==mFacing?0:1], PX_N[SIDE_Y_POS==mFacing?15:SIDE_Y_NEG==mFacing?0:1], PX_N[SIDE_Z_POS==mFacing?15:SIDE_Z_NEG==mFacing?0:1]);}
	@Override public AxisAlignedBB getSelectedBoundingBoxFromPool () {return box(PX_P[SIDE_X_NEG==mFacing?15:SIDE_X_POS==mFacing?0:1], PX_P[SIDE_Y_NEG==mFacing?15:SIDE_Y_POS==mFacing?0:1], PX_P[SIDE_Z_NEG==mFacing?15:SIDE_Z_POS==mFacing?0:1], PX_N[SIDE_X_POS==mFacing?15:SIDE_X_NEG==mFacing?0:1], PX_N[SIDE_Y_POS==mFacing?15:SIDE_Y_NEG==mFacing?0:1], PX_N[SIDE_Z_POS==mFacing?15:SIDE_Z_NEG==mFacing?0:1]);}
	@Override public void setBlockBoundsBasedOnState(Block aBlock) {box(aBlock, PX_P[SIDE_X_NEG==mFacing?15:SIDE_X_POS==mFacing?0:1], PX_P[SIDE_Y_NEG==mFacing?15:SIDE_Y_POS==mFacing?0:1], PX_P[SIDE_Z_NEG==mFacing?15:SIDE_Z_POS==mFacing?0:1], PX_N[SIDE_X_POS==mFacing?15:SIDE_X_NEG==mFacing?0:1], PX_N[SIDE_Y_POS==mFacing?15:SIDE_Y_NEG==mFacing?0:1], PX_N[SIDE_Z_POS==mFacing?15:SIDE_Z_NEG==mFacing?0:1]);}

	@Override public float getSurfaceSize           (byte aSide) {return ALONG_AXIS[aSide][mFacing]?PX_P[15]:0.0F;}
	@Override public float getSurfaceSizeAttachable (byte aSide) {return ALONG_AXIS[aSide][mFacing]?PX_P[15]:0.0F;}
	@Override public float getSurfaceDistance       (byte aSide) {return aSide==mFacing?PX_N[1]:0.0F;}
	@Override public boolean isSurfaceSolid         (byte aSide) {return F;}
	@Override public boolean isSurfaceOpaque2       (byte aSide) {return F;}
	@Override public boolean isSideSolid2           (byte aSide) {return F;}
	@Override public boolean allowCovers            (byte aSide) {return F;}

	@Override public boolean canDrop(int aInventorySlot) {return F;}

	@Override public boolean unpaint() {return F;}
	@Override public boolean isPainted() {return F;}
	@Override public boolean paint(int aRGB) {return F;}
	@Override public int getPaint() {return UNCOLORED;}
	@Override public boolean canRecolorItem(ItemStack aStack) {return F;}
	@Override public boolean canDecolorItem(ItemStack aStack) {return F;}
	@Override public boolean recolorItem(ItemStack aStack, int aRGB) {return F;}
	@Override public boolean decolorItem(ItemStack aStack) {return F;}

	@Override public void onExploded(Explosion aExplosion) {/* Doesn't get removed after Explosions. */}

	@Override public int getLifeSpan(World aWorld, ItemStack aStack) {return 1728000;}
	@Override public int onDespawn(EntityItem aEntity, ItemStack aStack) {return 1728000;}

	@Override public byte getDefaultSide() {return SIDE_FRONT;}

	@Override public byte getVisualData() {return (byte)(mGold?2:mSilver?1:0);}
	@Override public void setVisualData(byte aData) {if (aData==2) mGold=T; if (aData==1) mSilver=T;}

	public static IIconContainer sTextureBack = new Textures.BlockIcons.CustomIcon("certificate/BACK"), sTextureSilver = new Textures.BlockIcons.CustomIcon("certificate/SILVER"), sTextureGold = new Textures.BlockIcons.CustomIcon("certificate/GOLD");

	@Override public String getTileEntityName() {return "gt6.multitileentity.certificate";}
}
