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

package gregapi6.lang;

import static gregapi6.data.CS.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import cpw.mods.fml.common.registry.LanguageRegistry;
import gregapi6.data.MT;
import gregapi6.data.OP;
import gregapi6.data.TD;
import gregapi6.oredict.OreDictMaterial;
import gregapi6.oredict.OreDictPrefix;
import gregapi6.util.ST;
import gregapi6.util.UT;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

/**
 * @author Gregorius Techneticies
 */
public class LanguageHandler {

	public static ArrayList<Configuration> sLangFileArray = new ArrayList<Configuration>();

	private static final HashMap<String, String> TEMPMAP = new HashMap<>(), BUFFERMAP = new HashMap<>();
	private static boolean mWritingEnabled = F, mUseFile = F;

	public static void save() {
		if (sLangFileArray != null) {
			mWritingEnabled = T;
			File tFile = new File("resources/gregtech6/lang/en_US.lang");
			Configuration enFile = new Configuration(tFile);
			enFile.save();
		}
	}

	public static synchronized void add(String aKey, String aEnglish) {
		if (aKey == null) return;
		aKey = aKey.trim();
		if (aKey.length() <= 0) return;
		if (sLangFileArray.size()==0) {
			BUFFERMAP.put(aKey, aEnglish);
		} else {
			for(int i=0;i<sLangFileArray.size();i++) {
				Configuration sLangFile = sLangFileArray.get(i);
				String localeName = sLangFile.getConfigFile().getName().split(".lang")[0];
				if (!BUFFERMAP.isEmpty()) {
					mUseFile = sLangFile.get("EnableLangFile", "UseThisFileAsLanguageFile", F).getBoolean(F);
					for (Entry<String, String> tEntry : BUFFERMAP.entrySet()) {
						Property tProperty = sLangFile.get("LanguageFile", tEntry.getKey(), tEntry.getValue());
						TEMPMAP.put(tEntry.getKey(), mUseFile?tProperty.getString():tEntry.getValue());
						LanguageRegistry.instance().injectLanguage(localeName, TEMPMAP);
						TEMPMAP.clear();
					}
					if (mWritingEnabled) sLangFile.save();
					BUFFERMAP.clear();
				}
				Property tProperty = sLangFile.get("LanguageFile", aKey, aEnglish);
				if (!tProperty.wasRead() && mWritingEnabled) sLangFile.save();
				TEMPMAP.put(aKey, mUseFile?tProperty.getString():aEnglish);
				LanguageRegistry.instance().injectLanguage(localeName, TEMPMAP);
				TEMPMAP.clear();
			}
		}
	}

	public static String get(String aKey, String aDefault) {
		add(aKey, aDefault);
		return translate(aKey, aDefault);
	}

	public static String translate(String aKey) {
		return translate(aKey, aKey);
	}

	public static String translate(String aKey, String aDefault) {
		if (aKey == null) return "";
		aKey = aKey.trim();
		if (aKey.length() <= 0) return "";
		String rTranslation = LanguageRegistry.instance().getStringLocalization(aKey);
		if (UT.Code.stringValid(rTranslation)) return rTranslation;
		rTranslation = StatCollector.translateToLocal(aKey);
		if (UT.Code.stringValid(rTranslation) && !aKey.equals(rTranslation)) return rTranslation;
		if (aKey.endsWith(".name")) {
			rTranslation = StatCollector.translateToLocal(aKey = aKey.substring(0, aKey.length() - 5));
			if (UT.Code.stringInvalid(rTranslation) || aKey.equals(rTranslation)) return aDefault;
			return rTranslation;
		}
		rTranslation = StatCollector.translateToLocal(aKey = aKey + ".name");
		if (UT.Code.stringInvalid(rTranslation) || (aKey).equals(rTranslation)) return aDefault;
		return rTranslation;
	}

	public static String separate(String aKey, String aSeparator) {
		if (aKey == null) return "";
		String rTranslation = "";
		for (String tString : aKey.split(aSeparator)) {
			rTranslation += get(tString, tString);
		}
		return rTranslation;
	}

	public static String getTranslateableItemStackName(ItemStack aStack) {
		if (ST.invalid(aStack)) return "null";
		NBTTagCompound tNBT = aStack.getTagCompound();
		if (tNBT != null && tNBT.hasKey("display")) {
			String tName = tNBT.getCompoundTag("display").getString("Name");
			if (UT.Code.stringValid(tName)) {
				return tName;
			}
		}
		return aStack.getUnlocalizedName() + ".name";
	}

	public static String getLocalName(OreDictPrefix aPrefix, OreDictMaterial aMaterial) {
		// Certain Materials have slightly different default Localisations.
		if (aPrefix == OP.crateGtGem      || aPrefix == OP.crateGt64Gem      || aPrefix == OP.blockGem     ) return aPrefix.mMaterialPre + getLocalName(OP.gem     , aMaterial);
		if (aPrefix == OP.crateGtDust     || aPrefix == OP.crateGt64Dust     || aPrefix == OP.blockDust    ) return aPrefix.mMaterialPre + getLocalName(OP.dust    , aMaterial);
		if (aPrefix == OP.crateGtIngot    || aPrefix == OP.crateGt64Ingot    || aPrefix == OP.blockIngot   ) return aPrefix.mMaterialPre + getLocalName(OP.ingot   , aMaterial);
		if (aPrefix == OP.crateGtPlate    || aPrefix == OP.crateGt64Plate    || aPrefix == OP.blockPlate   ) return aPrefix.mMaterialPre + getLocalName(OP.plate   , aMaterial);
		if (aPrefix == OP.crateGtPlateGem || aPrefix == OP.crateGt64PlateGem || aPrefix == OP.blockPlateGem) return aPrefix.mMaterialPre + getLocalName(OP.plateGem, aMaterial);

		if (aMaterial == MT.Empty) {
			if (aPrefix == OP.chemtube)                                     return "Empty Glass Tube";
			if (aPrefix == OP.arrowGtWood)                                  return "Headless Wood Arrow";
			if (aPrefix == OP.arrowGtPlastic)                               return "Headless Plastic Arrow";
			if (aPrefix == OP.bulletGtSmall)                                return "Small Bullet Casing";
			if (aPrefix == OP.bulletGtMedium)                               return "Medium Bullet Casing";
			if (aPrefix == OP.bulletGtLarge)                                return "Large Bullet Casing";
			if (aPrefix.contains(TD.Prefix.ORE))                            return "Unknown Ore";
		} else
		if (aMaterial == MT.Stone) {
			if (aPrefix == OP.rockGt)                                       return "Rock";
		} else
		if (aMaterial == MT.Netherrack) {
			if (aPrefix == OP.rockGt)                                       return "Nether Rock";
		} else
		if (aMaterial == MT.Endstone) {
			if (aPrefix == OP.rockGt)                                       return "End Rock";
		} else
		if (aMaterial == MT.MeteoricIron || aMaterial == MT.Meteorite) {
			if (aPrefix == OP.rockGt)                                       return "Meteorite";
		} else
		if (aMaterial == MT.SpaceRock) {
			if (aPrefix == OP.rockGt)                                       return "Space Rock";
		} else
		if (aMaterial == MT.MoonRock) {
			if (aPrefix == OP.rockGt)                                       return "Moon Rock";
		} else
		if (aMaterial == MT.MarsRock) {
			if (aPrefix == OP.rockGt)                                       return "Mars Rock";
		} else
		if (aMaterial == MT.MoonTurf) {
			if (aPrefix.mNameInternal.startsWith("dust"))                   return aPrefix.mMaterialPre + "Moon Turf";
			if (aPrefix.mNameInternal.startsWith("crushed"))                return aPrefix.mMaterialPre + "Moon Turf";
			if (aPrefix == OP.rockGt)                                       return "Moon Surface Rock";
		} else
		if (aMaterial == MT.MarsSand) {
			if (aPrefix.mNameInternal.startsWith("dust"))                   return aPrefix.mMaterialPre + "Mars Turf";
			if (aPrefix.mNameInternal.startsWith("crushed"))                return aPrefix.mMaterialPre + "Mars Turf";
			if (aPrefix == OP.rockGt)                                       return "Mars Surface Rock";
		} else
		if (aMaterial == MT.Holystone) {
			if (aPrefix == OP.rockGt)                                       return "Holy Rock";
		} else
		if (aMaterial == MT.Umber) {
			if (aPrefix == OP.rockGt)                                       return "Umber Rock";
		} else
		if (aMaterial == MT.Betweenstone) {
			if (aPrefix == OP.rockGt)                                       return "Betweenrock";
		} else
		if (aMaterial == MT.Pitstone) {
			if (aPrefix == OP.rockGt)                                       return "Pit Rock";
		} else
		if (aMaterial == MT.Gneiss) {
			if (aPrefix == OP.rockGt)                                       return "Gneiss";
		} else
		if (aMaterial == MT.Glass) {
			if (aPrefix == OP.scrapGt)                                      return "Glass Shards";
			if (aPrefix == OP.round)                                        return "Glass Marble";
			if (aPrefix == OP.plateGem)                                     return "Cast Glass Pane";
			if (aPrefix == OP.plateGemTiny)                                 return "Tiny Cast Glass Pane";
			if (aPrefix.mNameInternal.startsWith("gem"))                    return aPrefix.mMaterialPre + "Glass Crystal";
			if (aPrefix.mNameInternal.startsWith("plate"))                  return aPrefix.mMaterialPre + "Glass Pane";
		} else
		if (aMaterial == MT.PrismarineLight || aMaterial == MT.PrismarineDark) {
			if (aPrefix == OP.rockGt)                                       return aMaterial.mNameLocal + " Shard";
			if (aPrefix == OP.scrapGt)                                      return aMaterial.mNameLocal + " Shards";
			if (aPrefix == OP.round)                                        return aMaterial.mNameLocal + " Marble";
			if (aPrefix.mNameInternal.startsWith("gem"))                    return aPrefix.mMaterialPre + aMaterial.mNameLocal + " Crystal";
		} else
		if (aMaterial == MT.Frezarite || aMaterial == MT.Fluix || aMaterial == MT.Redstonia || aMaterial == MT.Palis || aMaterial == MT.Diamantine || aMaterial == MT.VoidCrystal || aMaterial == MT.Emeradic || aMaterial == MT.Enori) {
			if (aPrefix.mNameInternal.startsWith("gem"))                    return aPrefix.mMaterialPre + aMaterial.mNameLocal + " Crystal";
		} else
		if (aMaterial == MT.InfusedAir || aMaterial == MT.InfusedDull || aMaterial == MT.InfusedEarth || aMaterial == MT.InfusedEntropy || aMaterial == MT.InfusedFire || aMaterial == MT.InfusedOrder || aMaterial == MT.InfusedVis || aMaterial == MT.InfusedWater || aMaterial == MT.InfusedBalance) {
			if (aPrefix.mNameInternal.startsWith("ore"))                    return aMaterial.mNameLocal + " Crystals";
			if (aPrefix.mNameInternal.startsWith("gem"))                    return aPrefix.mMaterialPre + "Shard of " + aMaterial.mNameLocal;
			if (aPrefix.mNameInternal.startsWith("crystal"))                return aPrefix.mMaterialPre + "Shard of " + aMaterial.mNameLocal;
			if (aPrefix.mNameInternal.startsWith("plate"))                  return aPrefix.mMaterialPre + aMaterial.mNameLocal + " Crystal Plate";
			if (aPrefix.mNameInternal.startsWith("dust"))                   return aPrefix.mMaterialPre + aMaterial.mNameLocal + " Crystal Powder";
			if (aPrefix.mNameInternal.startsWith("crushed"))                return aPrefix.mMaterialPre + aMaterial.mNameLocal + " Crystals";
		} else
		if (aMaterial == MT.Wheat) {
			if (aPrefix.mNameInternal.startsWith("dust"))                   return aPrefix.mMaterialPre + "Flour";
		} else
		if (aMaterial == MT.Oat) {
			if (aPrefix.mNameInternal.startsWith("dust"))                   return aPrefix.mMaterialPre + "Oatmeal";
		} else
		if (aMaterial == MT.Rye) {
			if (aPrefix.mNameInternal.startsWith("dust"))                   return aPrefix.mMaterialPre + "Rye Flour";
		} else
		if (aMaterial == MT.Barley) {
			if (aPrefix.mNameInternal.startsWith("dust"))                   return aPrefix.mMaterialPre + "Barley Flour";
		} else
		if (aMaterial == MT.Corn) {
			if (aPrefix.mNameInternal.startsWith("dust"))                   return aPrefix.mMaterialPre + "Cornmeal";
		} else
		if (aMaterial == MT.Rice) {
			if (aPrefix.mNameInternal.startsWith("dust"))                   return aPrefix.mMaterialPre + "Rice";
		} else
		if (aMaterial == MT.Ice) {
			if (aPrefix == OP.gemChipped)                                   return "Ice Cubes";
			if (aPrefix == OP.gemFlawed)                                    return "Medium Ice Cube";
			if (aPrefix == OP.gem)                                          return "Large Ice Cube";
			if (aPrefix.mNameInternal.startsWith("dust"))                   return aPrefix.mMaterialPre + "Crushed Ice";
		} else
		if (aMaterial == MT.WoodSealed) {
			if (aPrefix == OP.rockGt)                                       return aMaterial.mNameLocal;
			if (aPrefix == OP.scrapGt)                                      return aMaterial.mNameLocal + " Splinters";
			if (aPrefix.mNameInternal.startsWith("bolt"))                   return "Short Treated Stick";
			if (aPrefix.mNameInternal.startsWith("stick"))                  return aPrefix.mMaterialPre + "Treated Stick";
			if (aPrefix.mNameInternal.startsWith("dust"))                   return aPrefix.mMaterialPre + aMaterial.mNameLocal + " Pulp";
			if (aPrefix.mNameInternal.startsWith("nugget"))                 return aPrefix.mMaterialPre + aMaterial.mNameLocal + " Chip";
			if (aPrefix.mNameInternal.startsWith("plate"))                  return aPrefix.mMaterialPre + "Treated Plank";
		} else
		if (aMaterial == MT.Plastic || aMaterial == MT.Rubber) {
			if (aPrefix.mNameInternal.startsWith("dust"))                   return aPrefix.mMaterialPre + aMaterial.mNameLocal + " Pulp";
			if (aPrefix.mNameInternal.startsWith("plate"))                  return aPrefix.mMaterialPre + aMaterial.mNameLocal + " Sheet";
			if (aPrefix.mNameInternal.startsWith("ingot"))                  return aPrefix.mMaterialPre + aMaterial.mNameLocal + " Bar";
			if (aPrefix.mNameInternal.startsWith("nugget"))                 return aPrefix.mMaterialPre + aMaterial.mNameLocal + " Chip";
			if (aPrefix.mNameInternal.startsWith("foil"))                   return "Thin " + aMaterial.mNameLocal + " Sheet";
		} else
		if (aMaterial == MT.FierySteel) {
			if (aPrefix.contains(TD.Prefix.IS_CONTAINER))                   return aPrefix.mMaterialPre + "Fiery Blood" + aPrefix.mMaterialPost;
		} else
		if (aMaterial == MT.Steeleaf) {
			if (aPrefix == OP.plantGtBlossom)                               return "Steeleaf Leaf";
			if (aPrefix.mNameInternal.startsWith("ingot"))                  return aPrefix.mMaterialPre + aMaterial.mNameLocal;
		} else
		if (aMaterial == MT.Bone) {
			if (aPrefix.mNameInternal.startsWith("dust"))                   return aPrefix.mMaterialPre + "Bonemeal";
		} else
		if (aMaterial == MT.Bark) {
			if (aPrefix.mNameInternal.startsWith("dust"))                   return aPrefix.mMaterialPre + "Tree Bark";
		} else
		if (aMaterial == MT.Tea || aMaterial == MT.Mint) {
			if (aPrefix == OP.plantGtBlossom)                               return aMaterial.mNameLocal + " Leaf";
			if (aPrefix.mNameInternal.startsWith("dust"))                   return aPrefix.mMaterialPre + aMaterial.mNameLocal + " Powder";
		} else
		if (aMaterial == MT.Pyrite) {
			if (aPrefix.contains(TD.Prefix.ORE))                            return aPrefix.mMaterialPre + MT.Au.mNameLocal + aPrefix.mMaterialPost;
		} else
		if (aMaterial == MT.Asbestos) {
			if (aPrefix.containsAny(TD.Prefix.ORE, TD.Prefix.ORE_PROCESSING_BASED)) return aPrefix.mMaterialPre + "Chrysotile" + aPrefix.mMaterialPost;
		} else
		if (aMaterial == MT.Au) {
			if (aPrefix == OP.plantGtBlossom)                               return "Aurelia Leaf";
		} else
		if (aMaterial == MT.Fe) {
			if (aPrefix == OP.plantGtBlossom)                               return "Ferru Leaf";
		} else
		if (aMaterial == MT.Pb) {
			if (aPrefix == OP.plantGtBlossom)                               return "Plumbilia Leaf";
		} else
		if (aMaterial == MT.Ag) {
			if (aPrefix == OP.plantGtBlossom)                               return "Argentia Leaf";
		} else
		if (aMaterial == MT.Sn) {
			if (aPrefix == OP.plantGtTwig)                                  return "Tine Twig";
		} else
		if (aMaterial == MT.Cu) {
			if (aPrefix == OP.plantGtFiber)                                 return "Coppon Fiber";
		} else
		if (aMaterial == MT.Emerald) {
			if (aPrefix == OP.plantGtBerry)                                 return "Bobs-Yer-Uncle-Berry";
		} else
		if (aMaterial == MT.Milk) {
			if (aPrefix == OP.plantGtWart)                                  return "Milkwart";
			if (aPrefix.mNameInternal.startsWith("dust"))                   return aPrefix.mMaterialPre + aMaterial.mNameLocal + " Powder";
		} else
		if (aMaterial == MT.Chocolate || aMaterial == MT.Cheese) {
			if (aPrefix.mNameInternal.startsWith("dust"))                   return aPrefix.mMaterialPre + aMaterial.mNameLocal + " Powder";
			if (aPrefix.mNameInternal.startsWith("ingot"))                  return aPrefix.mMaterialPre + aMaterial.mNameLocal + " Bar";
		} else
		if (aMaterial == MT.Butter || aMaterial == MT.ButterSalted) {
			if (aPrefix.mNameInternal.startsWith("ingot"))                  return aPrefix.mMaterialPre + aMaterial.mNameLocal;
		} else
		if (aMaterial == MT.Indigo || aMaterial == MT.Blaze || aMaterial == MT.ConstructionFoam || aMaterial == MT.Cocoa || aMaterial == MT.Curry || aMaterial == MT.Chocolate || aMaterial == MT.Coffee || aMaterial == MT.Chili || aMaterial == MT.Cheese || aMaterial == MT.Snow) {
			if (aPrefix.mNameInternal.startsWith("dust"))                   return aPrefix.mMaterialPre + aMaterial.mNameLocal + " Powder";
		} else
		if (aMaterial == MT.Potato || aMaterial == MT.Hazelnut || aMaterial == MT.Pistachio || aMaterial == MT.Almond || aMaterial == MT.Peanut || aMaterial == MT.Nutmeg || aMaterial == MT.Cinnamon || aMaterial == MT.Vanilla || aMaterial == MT.PepperBlack) {
			if (aPrefix.mNameInternal.startsWith("dust"))                   return aPrefix.mMaterialPre + "Ground " + aMaterial.mNameLocal;
		} else
		if (aMaterial == MT.Paper) {
			if (aPrefix.mNameInternal.startsWith("dust"))                   return aPrefix.mMaterialPre + "Chad";
			if (aPrefix == OP.scrapGt)                                      return "Shredded " + aMaterial.mNameLocal;
			if (aPrefix == OP.plateTiny)                                    return "Tiny piece of Paper";
			if (aPrefix == OP.plate)                                        return "Sheet of Paper";
			if (aPrefix == OP.plateDouble)                                  return "Paperboard";
			if (aPrefix == OP.plateTriple)                                  return "Carton";
			if (aPrefix == OP.plateQuadruple)                               return "Cardboard";
			if (aPrefix == OP.plateQuintuple)                               return "Thick Cardboard";
			if (aPrefix == OP.plateDense)                                   return "Strong Cardboard";
		} else
		if (aMaterial == MT.FishRaw) {
			if (aPrefix.mNameInternal.startsWith("dust"))                   return aPrefix.mMaterialPre + "Fishmeal";
		} else
		if (aMaterial == MT.FishCooked) {
			if (aPrefix.mNameInternal.startsWith("dust"))                   return aPrefix.mMaterialPre + "Cooked Fishmeal";
		} else
		if (aMaterial == MT.FishRotten) {
			if (aPrefix.mNameInternal.startsWith("dust"))                   return aPrefix.mMaterialPre + "Rotten Fishmeal";
		} else
		if (aMaterial == MT.MeatRaw) {
			if (aPrefix.mNameInternal.startsWith("dust"))                   return aPrefix.mMaterialPre + "Mince Meat";
		} else
		if (aMaterial == MT.MeatCooked) {
			if (aPrefix.mNameInternal.startsWith("dust"))                   return aPrefix.mMaterialPre + "Cooked Mince Meat";
		} else
		if (aMaterial == MT.MeatRotten) {
			if (aPrefix.mNameInternal.startsWith("dust"))                   return aPrefix.mMaterialPre + "Rotten Mince Meat";
		} else
		if (aMaterial == MT.SoylentGreen || aMaterial == MT.Tofu) {
			if (aPrefix.mNameInternal.startsWith("dust"))                   return aPrefix.mMaterialPre + "Silken " + aMaterial.mNameLocal;
			if (aPrefix.mNameInternal.startsWith("ingot"))                  return aPrefix.mMaterialPre + aMaterial.mNameLocal + " Bar";
		} else
		if (aMaterial == MT.Peat || aMaterial == MT.PeatBituminous) {
			if (aPrefix.mNameInternal.startsWith("dust"))                   return aPrefix.mMaterialPre + aMaterial.mNameLocal;
			if (aPrefix.mNameInternal.startsWith("ingot"))                  return aPrefix.mMaterialPre + aMaterial.mNameLocal + " Brick";
		} else
		if (aMaterial == MT.Lignite || aMaterial == MT.LigniteCoke || aMaterial == MT.Charcoal || aMaterial == MT.Coal || aMaterial == MT.CoalCoke || aMaterial == MT.Anthracite || aMaterial == MT.Prismane || aMaterial == MT.Lonsdaleite || aMaterial == MT.PetCoke || aMaterial == MT.HydratedCoal) {
			if (aPrefix.mNameInternal.startsWith("ingot"))                  return aPrefix.mMaterialPre + aMaterial.mNameLocal + " Brick";
			if (aPrefix.mNameInternal.startsWith("chunkGt"))                return aPrefix.mMaterialPre + aMaterial.mNameLocal + " Chunk";
			if (aPrefix.mNameInternal.startsWith("nugget"))                 return aPrefix.mMaterialPre + aMaterial.mNameLocal + " Pellet";
		} else
		if (aMaterial == MT.Sugar) {
			if (aPrefix == OP.gemChipped)                                   return "Sugar Cubes";
		} else
		if (aMaterial == MT.Dilithium) {
			if (aPrefix.mNameInternal.startsWith("gem"))                    return aPrefix.mMaterialPre + aMaterial.mNameLocal + " Crystal";
		} else
		if (aMaterial == MT.Ectoplasm || aMaterial == MT.Tallow || aMaterial == MT.Gravel || aMaterial == MT.Gunpowder || aMaterial == MT.NaCl || aMaterial == MT.KCl || aMaterial == MT.KIO3 || aMaterial == MT.Asphalt) {
			if (aPrefix.mNameInternal.startsWith("dust"))                   return aPrefix.mMaterialPre + aMaterial.mNameLocal;
		} else
		if (aMaterial == MT.Black || aMaterial == MT.Red || aMaterial == MT.Green || aMaterial == MT.Brown || aMaterial == MT.Blue || aMaterial == MT.Purple || aMaterial == MT.Cyan || aMaterial == MT.LightGray || aMaterial == MT.Gray || aMaterial == MT.Pink || aMaterial == MT.Lime || aMaterial == MT.Yellow || aMaterial == MT.LightBlue || aMaterial == MT.Magenta || aMaterial == MT.Orange || aMaterial == MT.White) {
			if (aPrefix == OP.plantGtFiber)                                 return aMaterial.mNameLocal + " String";
			if (aPrefix.mNameInternal.startsWith("dust"))                   return aPrefix.mMaterialPre + aMaterial.mNameLocal + " Dye";
		} else
		if (aMaterial == MT.Wax || aMaterial == MT.WaxMagic || aMaterial == MT.WaxAmnesic || aMaterial == MT.WaxSoulful || aMaterial == MT.WaxBee || aMaterial == MT.WaxRefractory || aMaterial == MT.WaxPlant || aMaterial == MT.WaxParaffin || aMaterial == MT.Ash || aMaterial == MT.DarkAsh || aMaterial == MT.VolcanicAsh || aMaterial == MT.ArcaneAsh || aMaterial == MT.ArcaneCompound || aMaterial == MT.OREMATS.Vermiculite || aMaterial == MT.OREMATS.Bentonite || aMaterial == MT.OREMATS.Kaolinite || aMaterial == MT.Talc || aMaterial == MT.OREMATS.BasalticMineralSand || aMaterial == MT.OREMATS.GraniticMineralSand || aMaterial == MT.OREMATS.GlauconiteSand || aMaterial == MT.OREMATS.CassiteriteSand || aMaterial == MT.OREMATS.GarnetSand || aMaterial == MT.SluiceSand || aMaterial == MT.OREMATS.QuartzSand || aMaterial == MT.OREMATS.Pitchblende || aMaterial == MT.OREMATS.FullersEarth || aMaterial == MT.RareEarth || aMaterial == MT.Oilsands) {
			if (aPrefix.mNameInternal.startsWith("ore"))                    return aPrefix.mMaterialPre + aMaterial.mNameLocal;
			if (aPrefix.mNameInternal.startsWith("dust"))                   return aPrefix.mMaterialPre + aMaterial.mNameLocal;
			if (aPrefix == OP.crushed)                                      return "Ground " + aMaterial.mNameLocal;
			if (aPrefix == OP.crushedTiny)                                  return "Tiny Ground " + aMaterial.mNameLocal;
			if (aPrefix.mNameInternal.startsWith("crushed"))                return aPrefix.mMaterialPre + aMaterial.mNameLocal;
		}

		if (aMaterial.contains(TD.Properties.WOOD)) {
			if (aPrefix == OP.rockGt)                                       return aMaterial.mNameLocal;
			if (aPrefix == OP.scrapGt)                                      return aMaterial.mNameLocal + " Splinters";
			if (aPrefix.mNameInternal.startsWith("bolt"))                   return "Short " + aMaterial.mNameLocal + " Stick";
			if (aPrefix.mNameInternal.startsWith("stick"))                  return aPrefix.mMaterialPre + aMaterial.mNameLocal + " Stick";
			if (aPrefix.mNameInternal.startsWith("dust"))                   return aPrefix.mMaterialPre + aMaterial.mNameLocal + " Pulp";
			if (aPrefix.mNameInternal.startsWith("nugget"))                 return aPrefix.mMaterialPre + aMaterial.mNameLocal + " Chip";
			if (aPrefix.mNameInternal.startsWith("plate"))                  return aPrefix.mMaterialPre + aMaterial.mNameLocal + " Plank";
		}
		if (aMaterial.contains(TD.Properties.STONE)) {
			if (aPrefix == OP.rockGt) return aMaterial.mNameLocal + " Rock";
			if (aPrefix == OP.scrapGt) return aMaterial.mNameLocal + " Pebbles";
		}
		// Use Standard Localisation
		return aPrefix.mMaterialPre + aMaterial.mNameLocal + aPrefix.mMaterialPost;
	}
}
