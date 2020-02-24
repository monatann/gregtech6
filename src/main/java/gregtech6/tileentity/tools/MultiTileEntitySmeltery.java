/**
 * Copyright (c) 2020 Gregorius Techneticies
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

package gregtech6.tileentity.tools;

import static gregapi6.data.CS.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import gregapi6.GT_API_Proxy;
import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_AddToolTips;
import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_GetCollisionBoundingBoxFromPool;
import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_OnEntityCollidedWithBlock;
import gregapi6.block.multitileentity.IMultiTileEntity.IMTE_OnPlaced;
import gregapi6.block.multitileentity.MultiTileEntityContainer;
import gregapi6.code.ArrayListNoNulls;
import gregapi6.code.HashSetNoNulls;
import gregapi6.code.TagData;
import gregapi6.data.CS.GarbageGT;
import gregapi6.data.CS.IconsGT;
import gregapi6.data.CS.SFX;
import gregapi6.data.FL;
import gregapi6.data.LH;
import gregapi6.data.LH.Chat;
import gregapi6.data.MT;
import gregapi6.data.OP;
import gregapi6.data.TD;
import gregapi6.network.INetworkHandler;
import gregapi6.network.IPacket;
import gregapi6.oredict.OreDictItemData;
import gregapi6.oredict.OreDictMaterial;
import gregapi6.oredict.OreDictMaterialStack;
import gregapi6.oredict.configurations.IOreDictConfigurationComponent;
import gregapi6.render.BlockTextureCopied;
import gregapi6.render.BlockTextureDefault;
import gregapi6.render.ITexture;
import gregapi6.tileentity.ITileEntityServerTickPost;
import gregapi6.tileentity.base.TileEntityBase07Paintable;
import gregapi6.tileentity.data.ITileEntityTemperature;
import gregapi6.tileentity.data.ITileEntityWeight;
import gregapi6.tileentity.energy.ITileEntityEnergy;
import gregapi6.tileentity.machines.ITileEntityCrucible;
import gregapi6.tileentity.machines.ITileEntityMold;
import gregapi6.util.OM;
import gregapi6.util.ST;
import gregapi6.util.UT;
import gregapi6.util.WD;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntitySmeltery extends TileEntityBase07Paintable implements ITileEntityCrucible, ITileEntityEnergy, ITileEntityWeight, ITileEntityTemperature, ITileEntityMold, ITileEntityServerTickPost, IMTE_OnEntityCollidedWithBlock, IMTE_GetCollisionBoundingBoxFromPool, IMTE_AddToolTips, IMTE_OnPlaced {
	private static int GAS_RANGE = 3, FLAME_RANGE = 3;
	private static long MAX_AMOUNT = 16*U, KG_PER_ENERGY = 100;
	private static double HEAT_RESISTANCE_BONUS = 1.25;
	
	private static long temperature = 0;
	
	protected boolean mAcidProof = F;
	protected byte mDisplayedHeight = 0, oDisplayedHeight = 0, mCooldown = 100;
	protected short mDisplayedFluid = -1, oDisplayedFluid = -1;
	protected long mEnergy = 0, mTemperature = DEF_ENV_TEMP, oTemperature = 0;
	protected List<OreDictMaterialStack> mContent = new ArrayListNoNulls<>();
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		mEnergy = aNBT.getLong(NBT_ENERGY);
		if (aNBT.hasKey(NBT_ACIDPROOF)) mAcidProof = aNBT.getBoolean(NBT_ACIDPROOF);
		if (aNBT.hasKey(NBT_TEMPERATURE)) mTemperature = aNBT.getLong(NBT_TEMPERATURE);
		if (aNBT.hasKey(NBT_TEMPERATURE+".old")) oTemperature = aNBT.getLong(NBT_TEMPERATURE+".old");
		if (aNBT.hasKey(NBT_MAXTEMPERATURE)) temperature = aNBT.getLong(NBT_MAXTEMPERATURE);
		mContent = OreDictMaterialStack.loadList(NBT_MATERIALS, aNBT);
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setNumber(aNBT, NBT_ENERGY, mEnergy);
		UT.NBT.setNumber(aNBT, NBT_TEMPERATURE, mTemperature);
		UT.NBT.setNumber(aNBT, NBT_TEMPERATURE+".old", oTemperature);
		OreDictMaterialStack.saveList(NBT_MATERIALS, aNBT, mContent);
	}
	
	static {
		LH.add("gt6.tooltip.crucible.1", "KU Input will turn into Air for Steelmaking");
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get(LH.CONVERTS_FROM_X) + " 1 " + TD.Energy.HU.getLocalisedNameShort() + " " + LH.get(LH.CONVERTS_TO_Y) + " +1 K " + LH.get(LH.CONVERTS_PER_Z) + " "+ KG_PER_ENERGY + "kg (at least "+getEnergySizeInputMin(TD.Energy.HU, SIDE_ANY)+" Units per Tick required!)");
		aList.add(Chat.WHITE    + LH.get("gt6.tooltip.crucible.1"));
		aList.add(Chat.DRED     + LH.get(LH.HAZARD_MELTDOWN) + " (" + getTemperatureMax(SIDE_ANY) + " K)");
		if (mAcidProof) aList.add(Chat.ORANGE + LH.get(LH.TOOLTIP_ACIDPROOF));
		aList.add(Chat.DRED     + LH.get(LH.HAZARD_FIRE) + " ("+(FLAME_RANGE+1)+"m)");
		aList.add(Chat.DRED     + LH.get(LH.HAZARD_CONTACT));
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_REMOVE_SHOVEL));
	}
	
	private boolean mHasToAddTimer = T;
	
	@Override public void onUnregisterPost() {mHasToAddTimer = T;}
	
	@Override
	public void onCoordinateChange() {
		super.onCoordinateChange();
		GT_API_Proxy.SERVER_TICK_POST.remove(this);
		onUnregisterPost();
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		if (aIsServerSide && mHasToAddTimer) {
			GT_API_Proxy.SERVER_TICK_POST.add(this);
			mHasToAddTimer = F;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void onServerTickPost(boolean aFirst) {
		if (!slotHas(0)) slot(0, WD.suck(worldObj, xCoord+PX_P[2], yCoord+PX_P[2], zCoord+PX_P[2], PX_N[4], 1, PX_N[4]));
		
		ItemStack tStack = slot(0);
		
		long tTemperature = WD.envTemp(worldObj, xCoord, yCoord, zCoord);
		
		if (ST.valid(tStack)) {
			OreDictItemData tData = OM.anydata_(tStack);
			if (tData == null) {
				GarbageGT.trash(decrStackSize(0, 1));
				UT.Sounds.send(SFX.MC_FIZZ, this);
			} else {
				List<OreDictMaterialStack> tList = new ArrayListNoNulls<>();
				for (OreDictMaterialStack tMaterial : tData.getAllMaterialStacks()) if (tMaterial.mAmount > 0) tList.add(tMaterial.clone());
				if (addMaterialStacks(tList, tTemperature)) decrStackSize(0, 1);
			}
		}
		
		Set<OreDictMaterial> tAlreadyCheckedAlloys = new HashSetNoNulls<>();
		
		OreDictMaterial tPreferredAlloy = null;
		IOreDictConfigurationComponent tPreferredRecipe = null;
		long tMaxConversions = 0;
		
		for (OreDictMaterialStack tMaterial : mContent) {
			if (mTemperature >= tMaterial.mMaterial.mMeltingPoint) {
				for (OreDictMaterial tAlloy : tMaterial.mMaterial.mAlloyComponentReferences) if (tAlreadyCheckedAlloys.add(tAlloy) && mTemperature >= tAlloy.mMeltingPoint) {
					for (IOreDictConfigurationComponent tAlloyRecipe : tAlloy.mAlloyCreationRecipes) {
						List<OreDictMaterialStack> tNeededStuff = new ArrayListNoNulls<>();
						for (OreDictMaterialStack tComponent : tAlloyRecipe.getUndividedComponents()) {
							tNeededStuff.add(OM.stack(tComponent.mMaterial, Math.max(1, tComponent.mAmount / U)));
						}
						
						if (!tNeededStuff.isEmpty()) {
							int tNonMolten = 0;
							
							boolean tBreak = F;
							long tConversions = Long.MAX_VALUE;
							for (OreDictMaterialStack tComponent : tNeededStuff) {
								if (mTemperature < tComponent.mMaterial.mMeltingPoint) tNonMolten++;
								
								tBreak = T;
								for (OreDictMaterialStack tContent : mContent) {
									if (tContent.mMaterial == tComponent.mMaterial) {
										tConversions = Math.min(tConversions, tContent.mAmount / tComponent.mAmount);
										tBreak = F;
										break;
									}
								}
								if (tBreak) break;
							}
							
							if (!tBreak && tNonMolten <= 1 && tConversions > 0) {
								if (tPreferredAlloy == null || tPreferredRecipe == null || tConversions * tAlloyRecipe.getCommonDivider() > tMaxConversions * tPreferredRecipe.getCommonDivider()) {
									tMaxConversions = tConversions;
									tPreferredRecipe = tAlloyRecipe;
									tPreferredAlloy = tAlloy;
								}
							}
						}
					}
				}
			}
		}
		
		if (tPreferredAlloy != null && tPreferredRecipe != null) {
			for (OreDictMaterialStack tComponent : tPreferredRecipe.getUndividedComponents()) {
				for (OreDictMaterialStack tContent : mContent) {
					if (tContent.mMaterial == tComponent.mMaterial) {
						tContent.mAmount -= UT.Code.units_(tMaxConversions, U, tComponent.mAmount, T);
						break;
					}
				}
			}
			OM.stack(tPreferredAlloy, tPreferredRecipe.getCommonDivider() * tMaxConversions).addToList(mContent);
		}
		
		List<OreDictMaterialStack> tToBeAdded = new ArrayListNoNulls<>();
		for (int i = 0; i < mContent.size(); i++) {
			OreDictMaterialStack tMaterial = mContent.get(i);
			if (tMaterial == null || tMaterial.mMaterial == MT.NULL || tMaterial.mMaterial == MT.Air || tMaterial.mAmount <= 0) {
				GarbageGT.trash(mContent.remove(i--));
			} else if (tMaterial.mMaterial.mGramPerCubicCentimeter <= WEIGHT_AIR_G_PER_CUBIC_CENTIMETER) {
				GarbageGT.trash(mContent.remove(i--));
				UT.Sounds.send(SFX.MC_FIZZ, this);
			} else if (mTemperature >= tMaterial.mMaterial.mBoilingPoint || (mTemperature > C + 40 && tMaterial.mMaterial.contains(TD.Properties.FLAMMABLE) && !tMaterial.mMaterial.contains(TD.Processing.MELTING))) {
				GarbageGT.trash(mContent.remove(i--));
				UT.Sounds.send(SFX.MC_FIZZ, this);
				if (tMaterial.mMaterial.mBoilingPoint >=  320) try {for (EntityLivingBase tLiving : (List<EntityLivingBase>)worldObj.getEntitiesWithinAABB(EntityLivingBase.class, box(-GAS_RANGE, -1, -GAS_RANGE, GAS_RANGE+1, GAS_RANGE+1, GAS_RANGE+1))) UT.Entities.applyHeatDamage(tLiving, (tMaterial.mMaterial.mBoilingPoint - 300) / 25.0F);} catch(Throwable e) {e.printStackTrace(ERR);}
				if (tMaterial.mMaterial.mBoilingPoint >= 2000) for (int j = 0, k = Math.max(1, UT.Code.bindInt((9 * tMaterial.mAmount) / U)); j < k; j++) WD.fire(worldObj, xCoord-FLAME_RANGE+rng(2*FLAME_RANGE+1), yCoord-1+rng(2+FLAME_RANGE), zCoord-FLAME_RANGE+rng(2*FLAME_RANGE+1), rng(3) != 0);
				if (tMaterial.mMaterial.contains(TD.Properties.EXPLOSIVE)) explode(UT.Code.scale(tMaterial.mAmount, MAX_AMOUNT, 6, F));
				return;
			} else if (!mAcidProof && tMaterial.mMaterial.contains(TD.Properties.ACID)) {
				GarbageGT.trash(mContent.remove(i--));
				UT.Sounds.send(SFX.MC_FIZZ, this);
				setToAir();
				return;
			} else if (mTemperature >= tMaterial.mMaterial.mMeltingPoint && oTemperature <  tMaterial.mMaterial.mMeltingPoint) {
				mContent.remove(i--);
				OM.stack(tMaterial.mMaterial.mTargetSmelting.mMaterial, UT.Code.units_(tMaterial.mAmount, U, tMaterial.mMaterial.mTargetSmelting.mAmount, F)).addToList(tToBeAdded);
			} else if (mTemperature <  tMaterial.mMaterial.mMeltingPoint && oTemperature >= tMaterial.mMaterial.mMeltingPoint) {
				mContent.remove(i--);
				OM.stack(tMaterial.mMaterial.mTargetSolidifying.mMaterial, UT.Code.units_(tMaterial.mAmount, U, tMaterial.mMaterial.mTargetSolidifying.mAmount, F)).addToList(tToBeAdded);
			}
		}
		for (int i = 0; i < tToBeAdded.size(); i++) {
			OreDictMaterialStack tMaterial = tToBeAdded.get(i);
			if (tMaterial == null || tMaterial.mMaterial == MT.NULL || tMaterial.mMaterial == MT.Air || tMaterial.mAmount <= 0) {
				GarbageGT.trash(tToBeAdded.remove(i--));
			} else {
				tMaterial.addToList(mContent);
			}
		}
		
		double tWeight = mMaterial.getWeight(U*7);
		long tTotal = 0;
		OreDictMaterialStack tLightest = null;
		
		for (OreDictMaterialStack tMaterial : mContent) {
			if (tLightest == null || tMaterial.mMaterial.mGramPerCubicCentimeter < tLightest.mMaterial.mGramPerCubicCentimeter) tLightest = tMaterial;
			tWeight += tMaterial.weight();
			tTotal += tMaterial.mAmount;
		}
		
		oTemperature = mTemperature;
		
		mDisplayedHeight = (byte)UT.Code.scale(tTotal, MAX_AMOUNT, 255, F);
		mDisplayedFluid = (tLightest == null || tLightest.mMaterial.mMeltingPoint > mTemperature ? -1 : tLightest.mMaterial.mID);
		
		long tRequiredEnergy = 1 + (long)(tWeight / KG_PER_ENERGY), tConversions = mEnergy / tRequiredEnergy;
		
		if (mCooldown > 0) mCooldown--;
		
		if (tConversions != 0) {
			mEnergy -= tConversions * tRequiredEnergy;
			mTemperature += tConversions;
			mCooldown = 100;
		}
		
		if (mCooldown <= 0) {mCooldown = 10; if (mTemperature > tTemperature) mTemperature--; if (mTemperature < tTemperature) mTemperature++;}
		
		mTemperature = Math.max(mTemperature, Math.min(200, tTemperature));
		
		if (mTemperature > getTemperatureMax(SIDE_INSIDE)) {
			UT.Sounds.send(SFX.MC_FIZZ, this);
			if (mTemperature >=  320) try {for (EntityLivingBase tLiving : (List<EntityLivingBase>)worldObj.getEntitiesWithinAABB(EntityLivingBase.class, box(-GAS_RANGE, -1, -GAS_RANGE, GAS_RANGE+1, GAS_RANGE+1, GAS_RANGE+1))) UT.Entities.applyHeatDamage(tLiving, (mTemperature - 300) / 25.0F);} catch(Throwable e) {e.printStackTrace(ERR);}
			for (int j = 0, k = UT.Code.bindInt(mTemperature / 25); j < k; j++) WD.fire(worldObj, xCoord-FLAME_RANGE+rng(2*FLAME_RANGE+1), yCoord-1+rng(2+FLAME_RANGE), zCoord-FLAME_RANGE+rng(2*FLAME_RANGE+1), rng(3) != 0);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.flowing_lava, 1, 3);
			return;
		}
	}
	
	public boolean addMaterialStacks(List<OreDictMaterialStack> aList, long aTemperature) {
		if (OM.total(mContent)+OM.total(aList) <= MAX_AMOUNT) {
			double tWeight1 = OM.weight(mContent)+mMaterial.getWeight(U*7), tWeight2 = OM.weight(aList);
			if (tWeight1+tWeight2 > 0) mTemperature = aTemperature + (mTemperature>aTemperature?+1:-1)*UT.Code.units(Math.abs(mTemperature - aTemperature), (long)(tWeight1+tWeight2), (long)tWeight1, F);
			for (OreDictMaterialStack tMaterial : aList) {
				if (mTemperature >= tMaterial.mMaterial.mMeltingPoint) {
					if (aTemperature <  tMaterial.mMaterial.mMeltingPoint) {
						OM.stack(tMaterial.mMaterial.mTargetSmelting.mMaterial, UT.Code.units_(tMaterial.mAmount, U, tMaterial.mMaterial.mTargetSmelting.mAmount, F)).addToList(mContent);
					} else {
						tMaterial.addToList(mContent);
					}
				} else {
					if (aTemperature >= tMaterial.mMaterial.mMeltingPoint) {
						OM.stack(tMaterial.mMaterial.mTargetSolidifying.mMaterial, UT.Code.units_(tMaterial.mAmount, U, tMaterial.mMaterial.mTargetSolidifying.mAmount, F)).addToList(mContent);
					} else {
						tMaterial.addToList(mContent);
					}
				}
			}
			return T;
		}
		return F;
	}
	
	@Override
	public long getTemperatureValue(byte aSide) {
		return mTemperature;
	}
	
	@Override
	public long getTemperatureMax(byte aSide) {
		return temperature;
		//return (long)(mMaterial.mMeltingPoint * HEAT_RESISTANCE_BONUS);
	}
	
	@Override
	public boolean isMoldInputSide(byte aSide) {
		return SIDES_TOP[aSide];
	}
	
	@Override
	public long getMoldMaxTemperature() {
		return getTemperatureMax(SIDE_INSIDE);
	}
	
	@Override
	public long getMoldRequiredMaterialUnits() {
		return 1;
	}
	
	@Override
	public long fillMold(OreDictMaterialStack aMaterial, long aTemperature, byte aSide) {
		if (isMoldInputSide(aSide)) {
			if (addMaterialStacks(Arrays.asList(aMaterial), aTemperature)) return aMaterial.mAmount;
			if (aMaterial.mAmount > U && addMaterialStacks(Arrays.asList(OM.stack(aMaterial.mMaterial, U)), aTemperature)) return U;
		}
		return 0;
	}
	
	@Override public double getWeightValue(byte aSide) {return OM.weight(mContent);}
	
	@Override
	public boolean breakBlock() {
		while (!mContent.isEmpty()) GarbageGT.trash(mContent.remove(0));
		return super.breakBlock();
	}
	
	@Override public boolean attachCoversFirst(byte aSide) {return F;}
	
	@Override
	public boolean onBlockActivated3(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (SIDES_TOP[aSide]) {
			if (isServerSide() && aPlayer != null) {
				ItemStack aStack = aPlayer.getCurrentEquippedItem();
				OreDictMaterialStack tLightest = null;
				for (OreDictMaterialStack tMaterial : mContent) if (tLightest == null || tMaterial.mMaterial.mGramPerCubicCentimeter < tLightest.mMaterial.mGramPerCubicCentimeter) tLightest = tMaterial;
				
				if (slotHas(0)) {
					if (aStack == null) {
						aPlayer.inventory.setInventorySlotContents(aPlayer.inventory.currentItem, slotTake(0));
						if (mTemperature > 40 + C) UT.Entities.applyHeatDamage(aPlayer, Math.min(10.0F, mTemperature / 100.0F));
						return T;
					}
				} else {
					if (tLightest != null && mTemperature < tLightest.mMaterial.mMeltingPoint) {
						ItemStack tOutputStack = OP.scrapGt.mat(tLightest.mMaterial, 1);
						if (tOutputStack == null || tLightest.mAmount < OP.scrapGt.mAmount) {
							tLightest.mAmount = 0;
							aPlayer.addExhaustion(0.1F);
							if (mTemperature > 40 + C) UT.Entities.applyHeatDamage(aPlayer, Math.min(10.0F, mTemperature / 100.0F));
							return T;
						}
						if (aStack == null) {
							aPlayer.inventory.setInventorySlotContents(aPlayer.inventory.currentItem, tOutputStack);
							tLightest.mAmount-=OP.scrapGt.mAmount;
							aPlayer.addExhaustion(0.1F);
							if (mTemperature > 40 + C) UT.Entities.applyHeatDamage(aPlayer, Math.min(10.0F, mTemperature / 100.0F));
							return T;
						}
						if (ST.equal(aStack, tOutputStack) && aStack.stackSize < aStack.getMaxStackSize()) {
							aStack.stackSize++;
							tLightest.mAmount-=OP.scrapGt.mAmount;
							aPlayer.addExhaustion(0.1F);
							if (mTemperature > 40 + C) UT.Entities.applyHeatDamage(aPlayer, Math.min(10.0F, mTemperature / 100.0F));
							return T;
						}
					}
				}
				if (aStack != null) {
					FluidStack tFluid = FL.getFluid(ST.amount(1, aStack), T);
					if (tFluid == null) {
						if (tLightest != null && tLightest.mMaterial.mLiquid != null) {
							long tTemperature = FL.temperature(tLightest.mMaterial.mLiquid);
							if (mTemperature >= tLightest.mMaterial.mMeltingPoint && (tTemperature < 320 || mTemperature >= tTemperature)) {
								tFluid = tLightest.mMaterial.liquid(tLightest.mAmount, F);
								if (!FL.Error.is(tFluid)) {
									int tAmount = tFluid.amount;
									ItemStack tStack = FL.fill(tFluid, ST.amount(1, aStack), T, T, T, T);
									if (ST.valid(tStack)) {
										tLightest.mAmount -= UT.Code.units(tAmount - tFluid.amount, tLightest.mMaterial.mLiquid.amount, tLightest.mMaterial.mLiquidUnit, T);
										aStack.stackSize--;
										UT.Inventories.addStackToPlayerInventoryOrDrop(aPlayer, tStack, T);
										return T;
									}
								}
							}
						}
					} else {
						if (!FL.gas(tFluid, T) && !FL.acid(tFluid)) {
							ItemStack tStack = ST.container(ST.amount(1, aStack), T);
							OreDictMaterialStack tFluidData = OreDictMaterial.FLUID_MAP.get(tFluid.getFluid().getName());
							if (tFluidData != null) {
								if (FL.equal(tFluidData.mMaterial.mLiquid, tFluid)) {
									if (addMaterialStacks(new ArrayListNoNulls<>(F, OM.stack(tFluidData.mMaterial, UT.Code.units(tFluid.amount, tFluidData.mMaterial.mLiquid.amount, tFluidData.mMaterial.mLiquidUnit, F))), UT.Code.bind(FL.temperature(tFluid), tFluidData.mMaterial.mMeltingPoint+25, tFluidData.mMaterial.mBoilingPoint-1))) {
										aStack.stackSize--;
										UT.Inventories.addStackToPlayerInventoryOrDrop(aPlayer, tStack, T);
										return T;
									}
								} else {
									if (addMaterialStacks(new ArrayListNoNulls<>(F, OM.stack(tFluidData.mMaterial, UT.Code.units(tFluid.amount, tFluidData.mAmount, U, F))), UT.Code.bind(FL.temperature(tFluid), tFluidData.mMaterial.mMeltingPoint+25, tFluidData.mMaterial.mBoilingPoint-1))) {
										aStack.stackSize--;
										UT.Inventories.addStackToPlayerInventoryOrDrop(aPlayer, tStack, T);
										return T;
									}
								}
							}
						}
					}
				}
			}
			return T;
		}
		return F;
	}
	
	@Override
	public boolean fillMoldAtSide(ITileEntityMold aMold, byte aSide, byte aSideOfMold) {
		for (OreDictMaterialStack tContent : mContent) if (tContent != null && mTemperature >= tContent.mMaterial.mMeltingPoint) {
			long tAmount = aMold.fillMold(tContent, mTemperature, aSideOfMold);
			if (tAmount > 0) {
				tContent.mAmount -= tAmount;
				return T;
			}
		}
		return F;
	}
	
	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isClientSide()) return super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
		if (aTool.equals(TOOL_thermometer)) {if (aChatReturn != null) aChatReturn.add("Temperature: " + mTemperature + "K"); return 10000;}
		if (aTool.equals(TOOL_shovel) && SIDES_TOP[aSide] && aPlayer instanceof EntityPlayer) {
			OreDictMaterialStack tLightest = null;
			for (OreDictMaterialStack tMaterial : mContent) if (tLightest == null || tMaterial.mMaterial.mGramPerCubicCentimeter < tLightest.mMaterial.mGramPerCubicCentimeter) tLightest = tMaterial;
			if (tLightest != null && mTemperature < tLightest.mMaterial.mMeltingPoint) {
				if (tLightest.mAmount < OP.scrapGt.mAmount) {
					tLightest.mAmount = 0;
					((EntityPlayer)aPlayer).addExhaustion(0.1F);
					return 500;
				}
				ItemStack tOutputStack = OP.scrapGt.mat(tLightest.mMaterial, UT.Code.bindStack(tLightest.mAmount / OP.scrapGt.mAmount));
				if (tOutputStack == null) {
					tLightest.mAmount = 0;
					((EntityPlayer)aPlayer).addExhaustion(0.1F);
					return 500;
				}
				if (UT.Inventories.addStackToPlayerInventory((EntityPlayer)aPlayer, tOutputStack)) {
					((EntityPlayer)aPlayer).addExhaustion(0.1F * tOutputStack.stackSize);
					tLightest.mAmount -= OP.scrapGt.mAmount * tOutputStack.stackSize;
					return 1000 * tOutputStack.stackSize;
				}
				return 0;
			}
		}
		return super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
	}
	
	@Override
	public boolean onPlaced(ItemStack aStack, EntityPlayer aPlayer, MultiTileEntityContainer aMTEContainer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ) {
		mTemperature = WD.envTemp(worldObj, xCoord, yCoord, zCoord);
		return T;
	}
	
	@Override
	public boolean onTickCheck(long aTimer) {
		return super.onTickCheck(aTimer) || mDisplayedHeight != oDisplayedHeight || mDisplayedFluid != oDisplayedFluid;
	}
	
	@Override
	public void onTickResetChecks(long aTimer, boolean aIsServerSide) {
		super.onTickResetChecks(aTimer, aIsServerSide);
		oDisplayedFluid = mDisplayedFluid;
		oDisplayedHeight = mDisplayedHeight;
	}
	
	@Override
	public IPacket getClientDataPacket(boolean aSendAll) {
		if (aSendAll) return getClientDataPacketByteArray(T, mDisplayedHeight, UT.Code.toByteS(mDisplayedFluid, 0), UT.Code.toByteS(mDisplayedFluid, 1), (byte)UT.Code.getR(mRGBa), (byte)UT.Code.getG(mRGBa), (byte)UT.Code.getB(mRGBa));
		if (mDisplayedFluid != oDisplayedFluid) return getClientDataPacketByteArray(F, mDisplayedHeight, UT.Code.toByteS(mDisplayedFluid, 0), UT.Code.toByteS(mDisplayedFluid, 1));
		return getClientDataPacketByteArray(F, mDisplayedHeight);
	}
	
	@Override
	public boolean receiveDataByteArray(byte[] aData, INetworkHandler aNetworkHandler) {
		mDisplayedHeight = aData[0];
		if (aData.length >= 3) mDisplayedFluid = UT.Code.combine(aData[1], aData[2]);
		if (aData.length >= 6) mRGBa = UT.Code.getRGBInt(new short[] {UT.Code.unsignB(aData[3]), UT.Code.unsignB(aData[4]), UT.Code.unsignB(aData[5])});
		return T;
	}
	
	@Override
	public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered) {
		mTexture = BlockTextureDefault.get(mMaterial, OP.blockSolid, UT.Code.getRGBaArray(mRGBa), mMaterial.contains(TD.Properties.GLOWING));
		if (UT.Code.exists(mDisplayedFluid, OreDictMaterial.MATERIAL_ARRAY)) {
			OreDictMaterial tMaterial = OreDictMaterial.MATERIAL_ARRAY[mDisplayedFluid];
			if (tMaterial == MT.Lava) {
				mTextureMolten = BlockTextureCopied.get(Blocks.lava);
			} else if (tMaterial == MT.H2O) {
				mTextureMolten = BlockTextureCopied.get(Blocks.water);
			} else if (tMaterial == MT.Glowstone) {
				mTextureMolten = BlockTextureCopied.get(Blocks.glowstone);
			} else {
				mTextureMolten = BlockTextureDefault.get(tMaterial, IconsGT.INDEX_BLOCK_MOLTEN, STATE_LIQUID, T, F);
			}
		} else {
			mTextureMolten = BlockTextureDefault.get(MT.NULL, OP.blockDust, CA_GRAY_64, F);
		}
		return 6;
	}
	
	@Override
	public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		switch(aRenderPass) {
		case  0: box(aBlock, PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[14], PX_N[ 0], PX_N[ 0]); return T;
		case  1: box(aBlock, PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[ 0], PX_N[14]); return T;
		case  2: box(aBlock, PX_P[14], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[ 0], PX_N[ 0]); return T;
		case  3: box(aBlock, PX_P[ 0], PX_P[ 0], PX_P[14], PX_N[ 0], PX_N[ 0], PX_N[ 0]); return T;
		case  4: box(aBlock, PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[14], PX_N[ 0]); return T;
		case  5: box(aBlock, PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[ 0], 0.125F+(UT.Code.unsignB(mDisplayedHeight) / 292.571428F), PX_N[ 0]); return T;
		}
		return F;
	}
	
	private ITexture mTexture, mTextureMolten;
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		switch(aRenderPass) {
		case  0: case  2: return SIDES_AXIS_Z[aSide]||aSide==SIDE_BOTTOM?null:mTexture;
		case  1: case  3: return SIDES_AXIS_X[aSide]||aSide==SIDE_BOTTOM?null:mTexture;
		case  4: return SIDES_VERTICAL[aSide]?mTexture:null;
		case  5: return mDisplayedHeight != 0 && SIDES_TOP[aSide]?mTextureMolten:null;
		}
		return mTexture;
	}
	
	@Override
	public void onEntityCollidedWithBlock(Entity aEntity) {
		if (mTemperature > 320 && UT.Entities.applyHeatDamage(aEntity, Math.min(10.0F, mTemperature / 100.0F))) {
			if (aEntity instanceof EntityLivingBase && !((EntityLivingBase)aEntity).isEntityAlive()) {
				if (aEntity instanceof EntityVillager || aEntity instanceof EntityWitch) {
					addMaterialStacks(new ArrayListNoNulls<>(F, OM.stack(2*U, MT.SoylentGreen)), C+37);
				} else if (aEntity instanceof EntitySnowman) {
					addMaterialStacks(new ArrayListNoNulls<>(F, OM.stack(4*U, MT.Snow)), C-10);
				} else if (aEntity instanceof EntityIronGolem) {
					addMaterialStacks(new ArrayListNoNulls<>(F, OM.stack(4*U, MT.Fe)), WD.envTemp(worldObj, xCoord, yCoord, zCoord));
				} else if (aEntity instanceof EntitySkeleton) {
					addMaterialStacks(new ArrayListNoNulls<>(F, OM.stack(1*U, MT.Bone), ((EntitySkeleton)aEntity).getSkeletonType() == 1 ? OM.stack(1*U, MT.Coal) : null), WD.envTemp(worldObj, xCoord, yCoord, zCoord));
				} else if (aEntity instanceof EntityZombie) {
					addMaterialStacks(new ArrayListNoNulls<>(F, OM.stack(1*U, MT.MeatRotten)), WD.envTemp(worldObj, xCoord, yCoord, zCoord));
				} else if (aEntity instanceof EntityMooshroom || aEntity instanceof EntityCow || aEntity instanceof EntityHorse) {
					addMaterialStacks(new ArrayListNoNulls<>(F, OM.stack(3*U, MT.MeatRaw)), C+37);
				} else if (aEntity instanceof EntityPig || aEntity instanceof EntitySheep || aEntity instanceof EntityWolf || aEntity instanceof EntitySquid) {
					addMaterialStacks(new ArrayListNoNulls<>(F, OM.stack(2*U, MT.MeatRaw)), C+37);
				} else if (aEntity instanceof EntityChicken || aEntity instanceof EntityOcelot || aEntity instanceof EntitySpider || aEntity instanceof EntitySilverfish) {
					addMaterialStacks(new ArrayListNoNulls<>(F, OM.stack(1*U, MT.MeatRaw)), C+37);
				} else if (aEntity instanceof EntityCreeper) {
					addMaterialStacks(new ArrayListNoNulls<>(F, OM.stack(1*U, MT.Gunpowder)), C+20);
				} else if (aEntity instanceof EntityEnderman) {
					addMaterialStacks(new ArrayListNoNulls<>(F, OM.stack(1*U, MT.EnderPearl)), C+20);
				} else if (aEntity instanceof EntityPlayer) {
					if ("GregoriusT".equalsIgnoreCase(aEntity.getCommandSenderName())) for (int i = 0; i < 16; i++) addMaterialStacks(new ArrayListNoNulls<>(F, OM.stack(1*U, MT.Tc)), C+20);
				}
			}
		}
	}
	
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return box(0.125, 0.125, 0.125, 0.875, 0.875, 0.875);}
	@Override public boolean addDefaultCollisionBoxToList() {return F;}
	
	@Override
	public void addCollisionBoxesToList2(AxisAlignedBB aAABB, List<AxisAlignedBB> aList, Entity aEntity) {
		box(aAABB, aList, PX_P[14], PX_P[ 1], PX_P[ 1], PX_N[ 1], PX_N[ 1], PX_N[ 1]);
		box(aAABB, aList, PX_P[ 1], PX_P[ 1], PX_P[14], PX_N[ 1], PX_N[ 1], PX_N[ 1]);
		box(aAABB, aList, PX_P[ 1], PX_P[ 1], PX_P[ 1], PX_N[14], PX_N[ 1], PX_N[ 1]);
		box(aAABB, aList, PX_P[ 1], PX_P[ 1], PX_P[ 1], PX_N[ 1], PX_N[ 1], PX_N[14]);
		box(aAABB, aList, PX_P[ 1], PX_P[ 1], PX_P[ 1], PX_N[ 1], PX_N[14], PX_N[ 1]);
	}
	
	@Override
	public boolean checkObstruction(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		return SIDES_BOTTOM_HORIZONTAL[aSide] && super.checkObstruction(aPlayer, aSide, aHitX, aHitY, aHitZ);
	}
	
	@Override public float getSurfaceSize           (byte aSide) {return 1.0F;}
	@Override public float getSurfaceSizeAttachable (byte aSide) {return 1.0F;}
	@Override public float getSurfaceDistance       (byte aSide) {return 0.0F;}
	@Override public boolean isSurfaceSolid         (byte aSide) {return !SIDES_TOP[aSide];}
	@Override public boolean isSurfaceOpaque2       (byte aSide) {return !SIDES_TOP[aSide];}
	@Override public boolean isSideSolid2           (byte aSide) {return !SIDES_TOP[aSide];}
	
	@Override public boolean canDrop(int aInventorySlot) {return T;}
	@Override public boolean allowCovers(byte aSide) {return F;}
	
	@Override public ItemStack[] getDefaultInventory(NBTTagCompound aNBT) {return new ItemStack[1];}
	@Override public int[] getAccessibleSlotsFromSide2(byte aSide) {return UT.Code.getAscendingArray(1);}
	@Override public boolean canInsertItem2(int aSlot, ItemStack aStack, byte aSide) {return SIDES_TOP[aSide] && !slotHas(0);}
	@Override public boolean canExtractItem2(int aSlot, ItemStack aStack, byte aSide) {return F;}
	@Override public int getInventoryStackLimit() {return 64;}
	
	public static final List<TagData> ENERGYTYPES = new ArrayListNoNulls<>(F, TD.Energy.KU, TD.Energy.HU, TD.Energy.CU, TD.Energy.VIS_IGNIS);
	
	@Override public boolean isEnergyType(TagData aEnergyType, byte aSide, boolean aEmitting) {return !aEmitting && ENERGYTYPES.contains(aEnergyType);}
	@Override public boolean isEnergyCapacitorType(TagData aEnergyType, byte aSide) {return ENERGYTYPES.contains(aEnergyType);}
	@Override public boolean isEnergyAcceptingFrom(TagData aEnergyType, byte aSide, boolean aTheoretical) {return ENERGYTYPES.contains(aEnergyType);}
	@Override public long doInject(TagData aEnergyType, byte aSide, long aSize, long aAmount, boolean aDoInject) {if (aDoInject) {if (aEnergyType == TD.Energy.KU) {if (aSize*aAmount > 0 && WD.oxygen(worldObj, xCoord, yCoord+1, zCoord)) addMaterialStacks(new ArrayListNoNulls<>(F, OM.stack(aSize*aAmount*U1000, MT.Air)), mTemperature);} else if (aEnergyType == TD.Energy.CU) mEnergy -= Math.abs(aAmount * aSize); else mEnergy += Math.abs(aAmount * aSize);} return aAmount;}
	@Override public long getEnergyDemanded(TagData aEnergyType, byte aSide, long aSize) {return Long.MAX_VALUE - mEnergy;}
	@Override public long getEnergySizeInputMin(TagData aEnergyType, byte aSide) {return 1;}
	@Override public long getEnergySizeInputRecommended(TagData aEnergyType, byte aSide) {return 2048;}
	@Override public long getEnergySizeInputMax(TagData aEnergyType, byte aSide) {return Long.MAX_VALUE;}
	@Override public Collection<TagData> getEnergyTypes(byte aSide) {return ENERGYTYPES;}
	
	@Override public String getTileEntityName() {return "gt6.multitileentity.smeltery";}
}
