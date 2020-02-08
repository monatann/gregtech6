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

package gregapi6.item;

import static gregapi6.data.CS.*;

import java.util.Collection;
import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi6.GT_API;
import gregapi6.api.Abstract_Mod;
import gregapi6.config.ConfigCategories;
import gregapi6.data.CS.ConfigsGT;
import gregapi6.data.CS.FluidsGT;
import gregapi6.data.CS.GarbageGT;
import gregapi6.data.CS.ItemsGT;
import gregapi6.data.FL;
import gregapi6.data.FM;
import gregapi6.data.LH;
import gregapi6.data.MD;
import gregapi6.fluid.FluidGT;
import gregapi6.oredict.OreDictMaterial;
import gregapi6.oredict.OreDictMaterialStack;
import gregapi6.recipes.Recipe;
import gregapi6.util.ST;
import gregapi6.util.UT;
import gregapi6.util.WD;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidContainerItem;

/**
 * @author Gregorius Techneticies
 */
public class ItemFluidDisplay extends Item implements IFluidContainerItem, IItemUpdatable, IItemGT {
	protected IIcon mIcon;
	private final String mName;
	
	public ItemFluidDisplay() {
		super();
		mName = "gt6.display.fluid";
		LH.add(mName + ".name", "Fluid Display");
		GameRegistry.registerItem(this, mName, MD.GAPI.mID);
		if (ConfigsGT.CLIENT.get(ConfigCategories.visibility, "HiddenGTFluidDisplay", F)) ST.hide(this);
		ItemsGT.DEBUG_ITEMS.add(this);
		ItemsGT.ILLEGAL_DROPS.add(this);
		GarbageGT.BLACKLIST.add(this);
	}
	
	@Override
	public boolean onItemUseFirst(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float hitX, float hitY, float hitZ) {
		for (byte tSide : ALL_SIDES_VALID) if (FL.fill(WD.te(aWorld, aX, aY, aZ, tSide, T), FL.make(FluidRegistry.getFluid(ST.meta_(aStack)), Integer.MAX_VALUE), T) > 0) return T;
		return F;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void addInformation(ItemStack aStack, EntityPlayer aPlayer, @SuppressWarnings("rawtypes") List aList, boolean aF3_H) {
		NBTTagCompound aNBT = aStack.getTagCompound();
		Fluid aFluid = FluidRegistry.getFluid(ST.meta_(aStack));
		if (aFluid == null) {
			aList.add(LH.Chat.BLINKING_RED + "CLIENTSIDE FLUID IS NULL!!!" + LH.Chat.GRAY);
		} else if (FL.Error.is(aFluid)) {
			aList.add(LH.Chat.BLINKING_RED + "THIS IS AN ERROR AND SHOULD NEVER BE OBTAINABLE!!!");
		} else {
			String aName = aFluid.getName();
			
			if (D1) aList.add("Registry: " + aName);
			if (FluidsGT.FLUID_RENAMINGS.containsKey(aName) || FluidsGT.NONSTANDARD.contains(aName)) aList.add(LH.Chat.BLINKING_RED + "NON-STANDARD FLUID!");
			
			long tAmount = 0, tTemperature = DEF_ENV_TEMP;
			FluidStack tFluid = NF;
			boolean tGas = F;
			
			if (aNBT == null) {
				tAmount = 0;
				tFluid = FL.make(aFluid, (int)tAmount);
				tGas = FL.gas(tFluid);
				tTemperature = FL.temperature(tFluid);
			} else {
				tAmount = aNBT.getLong("a");
				tFluid = FL.make(aFluid, (int)tAmount);
				tGas = aNBT.getBoolean("s");
				tTemperature = aNBT.getLong("h");
			}
			
			if (tAmount > 0) {
				aList.add(LH.Chat.BLUE + "Amount: " + tAmount + " L" + LH.Chat.GRAY);
			}
			OreDictMaterialStack tMaterial = OreDictMaterial.FLUID_MAP.get(aName);
			if (tMaterial != null) {
				if (tMaterial.mAmount > 0 && tAmount > 0) {
					long tMatAmount = UT.Code.units(tAmount, tMaterial.mAmount, U, F);
					if (tMatAmount > 0) {
						int tDigits = (int)(((tMatAmount % U) / UD) * 1000);
						aList.add(LH.Chat.BLUE + "Worth: " + (tMatAmount / U) + "." + (tDigits<1?"000":tDigits<10?"00"+tDigits:tDigits<100?"0"+tDigits:tDigits) + " Units of " + tMaterial.mMaterial.getLocal() + LH.Chat.GRAY);
					}
				}
				if (UT.Code.stringValid(tMaterial.mMaterial.mTooltipChemical)) aList.add(LH.Chat.YELLOW + tMaterial.mMaterial.mTooltipChemical);
			}
			
			aList.add(LH.Chat.RED + "Temperature: " + tTemperature + " K (" + (tTemperature-C) + "°C)" + LH.Chat.GRAY);
			
			if (FL.plasma(tFluid)) {
				aList.add(LH.Chat.GREEN + "State: " + LH.Chat.YELLOW + "Plasma" + (!aFluid.isGaseous(tFluid) ? LH.Chat.RED + " (Warning: Considered a Liquid by Mods other than GT!)" : LH.Chat.ORANGE + " (Note: Considered a Gas by Mods other than GT!)") + LH.Chat.GRAY);
			} else if (tGas) {
				aList.add(LH.Chat.GREEN + "State: " + LH.Chat.CYAN + "Gas" + (!aFluid.isGaseous(tFluid) ? LH.Chat.RED + " (Warning: Considered a Liquid by Mods other than GT!)" : "") + LH.Chat.GRAY);
			} else {
				aList.add(LH.Chat.GREEN + "State: " + LH.Chat.BLUE + "Liquid" + (aFluid.isGaseous(tFluid) ? LH.Chat.RED + " (Warning: Considered a Gas by Mods other than GT!)" : "") + LH.Chat.GRAY);
			}
			
			float tDensity = aFluid.getDensity(tFluid);
			if (tDensity > 0) {
				aList.add(LH.Chat.GREEN + "Heavier than Air (moves down)" + LH.Chat.GRAY);
			} else if (tDensity < 0) {
				aList.add(LH.Chat.GREEN + "Lighter than Air (moves up)" + LH.Chat.GRAY);
			} else {
				aList.add(LH.Chat.GREEN + "As dense as Air (still moves down)" + LH.Chat.GRAY);
			}
			
			if (FL.powerconducting(aFluid)) {
				aList.add(LH.Chat.DGREEN + "This is a Power Conducting Fluid" + LH.Chat.GRAY);
				aList.add(LH.Chat.ORANGE + "Cannot be stored in regular GT6 Tanks" + LH.Chat.GRAY);
			}
			if (FL.simple(aFluid)) {
				aList.add(LH.Chat.DGREEN + "This is a simple Fluid" + LH.Chat.GRAY);
			}
			if (FL.acid(aFluid)) {
				aList.add(LH.Chat.ORANGE + "Acidic! Handle with Care!" + LH.Chat.GRAY);
			}
			if (FL.Lubricant.is(aFluid) || FL.LubRoCant.is(aFluid)) {
				aList.add(LH.Chat.ORANGE + "Industrial Use ONLY!" + LH.Chat.GRAY);
				aList.add(LH.Chat.RED + "Not Motherf***ing Flammable!" + LH.Chat.GRAY);
			} else {
				Collection<Recipe>
				tRecipes = FM.Burn.mRecipeFluidMap.get(aName);
				if (tRecipes != null && !tRecipes.isEmpty()) {
					long tFuelValue = 0;
					for (Recipe tRecipe : tRecipes) if (tRecipe.mEnabled && tRecipe.mFluidInputs[0] != null) tFuelValue = Math.max(tFuelValue, (tRecipe.mDuration * -tRecipe.mEUt * U) / tRecipe.mFluidInputs[0].amount);
					if (tFuelValue > 0) {
						if (tAmount > 0) {
							aList.add(LH.Chat.RED + "Burning: " + LH.Chat.WHITE + (tFuelValue / U) + LH.Chat.YELLOW + " GU/L; " + LH.Chat.WHITE + ((tFuelValue * tAmount) / U) + LH.Chat.YELLOW + " GU total" + LH.Chat.GRAY);
						} else {
							aList.add(LH.Chat.RED + "Burning: " + LH.Chat.WHITE + (tFuelValue / U) + LH.Chat.YELLOW + " GU/L " + LH.Chat.GRAY);
						}
					}
				}
				tRecipes = FM.Engine.mRecipeFluidMap.get(aName);
				if (tRecipes != null && !tRecipes.isEmpty()) {
					long tFuelValue = 0;
					for (Recipe tRecipe : tRecipes) if (tRecipe.mEnabled && tRecipe.mFluidInputs[0] != null) tFuelValue = Math.max(tFuelValue, (tRecipe.mDuration * -tRecipe.mEUt * U) / tRecipe.mFluidInputs[0].amount);
					if (tFuelValue > 0) {
						if (tAmount > 0) {
							aList.add(LH.Chat.RED + "Engine: " + LH.Chat.WHITE + (tFuelValue / U) + LH.Chat.YELLOW + " GU/L; " + LH.Chat.WHITE + ((tFuelValue * tAmount) / U) + LH.Chat.YELLOW + " GU total" + LH.Chat.GRAY);
						} else {
							aList.add(LH.Chat.RED + "Engine: " + LH.Chat.WHITE + (tFuelValue / U) + LH.Chat.YELLOW + " GU/L " + LH.Chat.GRAY);
						}
					}
				}
				tRecipes = FM.Gas.mRecipeFluidMap.get(aName);
				if (tRecipes != null && !tRecipes.isEmpty()) {
					long tFuelValue = 0;
					for (Recipe tRecipe : tRecipes) if (tRecipe.mEnabled && tRecipe.mFluidInputs[0] != null) tFuelValue = Math.max(tFuelValue, (tRecipe.mDuration * -tRecipe.mEUt * U) / tRecipe.mFluidInputs[0].amount);
					if (tFuelValue > 0) {
						if (tAmount > 0) {
							aList.add(LH.Chat.RED + "Turbine: " + LH.Chat.WHITE + (tFuelValue / U) + LH.Chat.YELLOW + " GU/L; " + LH.Chat.WHITE + ((tFuelValue * tAmount) / U) + LH.Chat.YELLOW + " GU total" + LH.Chat.GRAY);
						} else {
							aList.add(LH.Chat.RED + "Turbine: " + LH.Chat.WHITE + (tFuelValue / U) + LH.Chat.YELLOW + " GU/L " + LH.Chat.GRAY);
						}
					}
				}
				tRecipes = FM.Hot.mRecipeFluidMap.get(aName);
				if (tRecipes != null && !tRecipes.isEmpty()) {
					long tFuelValue = 0;
					for (Recipe tRecipe : tRecipes) if (tRecipe.mEnabled && tRecipe.mFluidInputs[0] != null) tFuelValue = Math.max(tFuelValue, (tRecipe.mDuration * -tRecipe.mEUt * U) / tRecipe.mFluidInputs[0].amount);
					if (tFuelValue > 0) {
						if (tAmount > 0) {
							aList.add(LH.Chat.RED + "Heat Exchanger: " + LH.Chat.WHITE + (tFuelValue / U) + LH.Chat.YELLOW + " GU/L; " + LH.Chat.WHITE + ((tFuelValue * tAmount) / U) + LH.Chat.YELLOW + " GU total" + LH.Chat.GRAY);
						} else {
							aList.add(LH.Chat.RED + "Heat Exchanger: " + LH.Chat.WHITE + (tFuelValue / U) + LH.Chat.YELLOW + " GU/L " + LH.Chat.GRAY);
						}
					}
				}
			}
			
			if (aFluid instanceof FluidGT) {
				aList.add(LH.Chat.DGRAY + "Fluid owned by GT6" + LH.Chat.GRAY);
			} else {
				aList.add(LH.Chat.DGRAY + "Fluid NOT owned by GT6" + LH.Chat.GRAY);
			}
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister aIconRegister) {
		// Useful hack to register Block Icons. That is why the Fluid Display Item has to always exist.
		if (Abstract_Mod.sFinalized >= Abstract_Mod.sModCountUsingGTAPI) {
			OUT.println("GT_Client: Setting up and loading Icon Register for Blocks");
			GT_API.sBlockIcons = aIconRegister;
			for (Runnable tRunnable : GT_API.sBlockIconload) {
				try {
					tRunnable.run();
				} catch(Throwable e) {
					e.printStackTrace(ERR);
				}
			}
			if (MD.IC2.mLoaded) {
				try {
					for (gregapi6.old.GT_BaseCrop tCrop : gregapi6.old.GT_BaseCrop.sCropList) tCrop.registerSprites(aIconRegister);
				} catch(Throwable e) {
					e.printStackTrace(ERR);
				}
			}
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int aMeta) {
		Fluid aFluid = FluidRegistry.getFluid(aMeta);
		if (aFluid == null) return FluidRegistry.WATER.getStillIcon();
		Block tBlock = aFluid.getBlock();
		return tBlock != null && tBlock != NB ? tBlock.getIcon(0, 0) : aFluid.getStillIcon();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack aStack, int aRenderPass) {
		Fluid aFluid = FluidRegistry.getFluid(ST.meta_(aStack));
		if (aFluid == null) return 16777215;
		Block tBlock = aFluid.getBlock();
		return tBlock != null && tBlock != NB ? tBlock.getRenderColor(0) : aFluid.getColor();
	}
	
	@Override
	public int getSpriteNumber() {
		return 0;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack aStack) {
		if (aStack != null) return FL.name(FluidRegistry.getFluid(ST.meta_(aStack)), F);
		return "";
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack aStack) {
		if (aStack != null) return FL.name(FluidRegistry.getFluid(ST.meta_(aStack)), T);
		return "";
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack aStack, int aRenderPass) {
		Fluid aFluid = FluidRegistry.getFluid(ST.meta_(aStack));
		return aFluid != null && FluidsGT.ENCHANTED_EFFECT.contains(aFluid.getName());
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings("unchecked")
	public void getSubItems(Item aItem, CreativeTabs aTab, @SuppressWarnings("rawtypes") List aList) {
		for (int i = 0, j = FluidRegistry.getMaxID(); i <= j; i++) {
			Fluid tFluid = FL.fluid(i);
			if (tFluid != null && !FluidsGT.FLUID_RENAMINGS.containsKey(tFluid.getName()) && !FL.Error.is(tFluid)) {
				ItemStack tStack = FL.display(tFluid);
				if (tStack != null) aList.add(tStack);
			}
		}
		for (String tName : UT.Books.BOOK_LIST) aList.add(ST.book(tName));
	}
	
	@Override public final Item setUnlocalizedName(String aName) {return this;}
	@Override public final String getUnlocalizedName() {return mName;}
	
	@Override
	public boolean doesSneakBypassUse(World aWorld, int aX, int aY, int aZ, EntityPlayer aPlayer) {
		return T;
	}
	
	@Override
	public ItemStack getContainerItem(ItemStack aStack) {
		return null;
	}
	
	@Override
	public final boolean hasContainerItem(ItemStack aStack) {
		return F;
	}
	
	@Override
	public void updateItemStack(ItemStack aStack) {
		NBTTagCompound aNBT = aStack.getTagCompound();
		if (aNBT != null && aNBT.hasKey("f")) {
			String aName = aNBT.getString("f");
			if (UT.Code.stringInvalid(aName)) return;
			String tName = FluidsGT.FLUID_RENAMINGS.get(aName);
			if (UT.Code.stringValid(tName)) aName = tName;
			Fluid tFluid = FL.fluid_(aName);
			if (tFluid != null) ST.meta_(aStack, tFluid.getID());
			return;
		}
		Fluid tFluid = FL.fluid(ST.meta_(aStack));
		if (tFluid == null) ST.meta_(aStack, W); else {aStack.setTagCompound(UT.NBT.makeString("f", tFluid.getName()));}
	}
	@Override
	public void updateItemStack(ItemStack aStack, World aWorld, int aX, int aY, int aZ) {
		updateItemStack(aStack);
	}
	
	@Override
	public FluidStack getFluid(ItemStack aStack) {
		Fluid tFluid = FluidRegistry.getFluid(ST.meta_(aStack));
		if (tFluid == null) return null;
		FluidStack rFluid = null;
		NBTTagCompound aNBT = aStack.getTagCompound();
		if (aNBT != null) {
			long tAmount = aNBT.getLong("a");
			if (tAmount > 0) rFluid = FL.make(tFluid, tAmount);
		}
		return rFluid == null ? FL.make(tFluid, 0) : rFluid;
	}
	
	@Override
	public int getCapacity(ItemStack aStack) {
		return Integer.MAX_VALUE;
	}
	
	@Override
	public int fill(ItemStack aStack, FluidStack aFluid, boolean aDoFill) {
		return 0;
	}
	
	@Override
	public FluidStack drain(ItemStack aStack, int aDrain, boolean aDoDrain) {
		return getFluid(aStack);
	}
}
