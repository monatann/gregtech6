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

package gregapi6.data;

import static gregapi6.data.CS.*;
import static gregapi6.data.TD.Processing.*;
import static gregapi6.data.TD.Properties.*;

import gregapi6.oredict.OreDictMaterial;
import net.minecraft.stats.AchievementList;

/**
 * @author Gregorius Techneticies
 */
public class ANY {
	private static OreDictMaterial any(String aNameOreDict) {return OreDictMaterial.createMaterial(-1, aNameOreDict, aNameOreDict).put(UNUSED_MATERIAL, INVALID_MATERIAL, IGNORE_IN_COLOR_LOG);}
	
	/** Technical Materials, which are only there for Recipes and such. */
	public static final OreDictMaterial
	Glowstone       = any("Any Glowstone"       ),
	Diamond         = any("Any Diamond"         ),
	Sapphire        = any("Any Sapphire"        ),
	Emerald         = any("Any Emerald"         ),
	Garnet          = any("Any Garnet"          ),
	Jasper          = any("Any Jasper"          ),
	TigerEye        = any("Any Tiger Eye"       ),
	Aventurine      = any("Any Aventurine"      ),
	Fluorite        = any("Any Fluorite"        ), CaF2 = Fluorite,
	Prismarine      = any("Any Prismarine"      ),
	Grains          = any("Any Grains"          ),
	Flour           = any("Any Flour"           ),
	FlourGrains     = any("Any Flour Or Grains" ),
	Wax             = any("Any Wax"             ),
	Stone           = any("Any Stone"           ),
	Calcite         = any("Any Calcite"         ),
	Clay            = any("Any Clay"            ),
	Salt            = any("Any Salt"            ),
	Fe              = any("Any Iron"            ),
	Iron            = any("Any Iron Or Steel"   ),
	Steel           = any("Any Iron-Steel"      ),
	Cu              = any("Any Copper"          ),
	Ash             = any("Any Ashes"           ),
	C               = any("Any Carbon"          ),
	Coal            = any("Any Coal/Carbon"     ),
	Si              = any("Any Silicon"         ),
	SiO2            = any("Any Silicon Dioxide" ),
	Quartz          = any("Any Quartz"          ),
	W               = any("Any Tungsten"        ),
	ThaumCrystal    = any("Any Thaumic Crystal" ),
	Wood            = any("Any Wood"            ),
	WoodDefault     = any("Any Default Wood"    ),
	WoodNormal      = any("Any Normal Wood"     ),
	WoodMagical     = any("Any Magical Wood"    ),
	WoodTreated     = any("Any Treated Wood"    ),
	WoodUntreated   = any("Any Untreated Wood"  ),
	WoodPlastic     = any("Any Wood Or Plastic" ),
	
	_Steel          = any("Any Steel"           ),
	_Bronze         = any("Any Bronze"          ),
	_Metal          = any("Any Metal"           );
	
	protected static void init() {
		Glowstone       .stealLooks(MT.Glowstone      ).steal(MT.Glowstone      ).setLocal("Glowstone"          ).setAllToTheOutputOf(MT.Glowstone      ).put(CRYSTAL, GLOWING, LIGHTING             );
		Diamond         .stealLooks(MT.Diamond        ).steal(MT.Diamond        ).setLocal("Diamond"            ).setAllToTheOutputOf(MT.Diamond        ).put(CRYSTAL, VALUABLE                      ).addReRegistrationToThis(MT.Diamantine);
		Sapphire        .stealLooks(MT.BlueSapphire   ).steal(MT.Sapphire       ).setLocal("Sapphire"           ).setAllToTheOutputOf(MT.Sapphire       ).put(CRYSTAL, VALUABLE                      );
		Emerald         .stealLooks(MT.Emerald        ).steal(MT.Emerald        ).setLocal("Emerald"            ).setAllToTheOutputOf(MT.Emerald        ).put(CRYSTAL, VALUABLE                      ).addReRegistrationToThis(MT.Emeradic);
		Garnet          .stealLooks(MT.Spessartine    ).steal(MT.Spessartine    ).setLocal("Garnet"                                                     ).put(CRYSTAL, VALUABLE                      );
		Jasper          .stealLooks(MT.Jasper         ).steal(MT.Jasper         ).setLocal("Jasper"                                                     ).put(CRYSTAL, VALUABLE                      );
		TigerEye        .stealLooks(MT.TigerEyeYellow ).steal(MT.TigerEyeYellow ).setLocal("Tiger Eye"                                                  ).put(CRYSTAL, VALUABLE                      );
		Aventurine      .stealLooks(MT.AventurineGreen).steal(MT.AventurineGreen).setLocal("Aventurine"                                                 ).put(CRYSTAL, VALUABLE                      );
		Fluorite        .stealLooks(MT.CaF2           ).steal(MT.CaF2           ).setLocal("Fluorite"           ).setAllToTheOutputOf(MT.CaF2           ).put(CRYSTAL, MORTAR, MELTING, BRITTLE, ACID);
		Prismarine      .stealLooks(MT.PrismarineLight).steal(MT.PrismarineLight).setLocal("Prismarine"         ).setAllToTheOutputOf(MT.PrismarineLight).put(CRYSTAL                                ).addReRegistrationToThis(MT.PrismarineLight, MT.PrismarineDark);
		Grains          .stealLooks(MT.Wheat          ).steal(MT.Wheat          ).setLocal("Grains"             ).setAllToTheOutputOf(MT.Wheat          ).put(FOOD, MORTAR, FLAMMABLE                );
		Flour           .stealLooks(MT.Wheat          ).steal(MT.Wheat          ).setLocal("Flour"              ).setAllToTheOutputOf(MT.Wheat          ).put(FOOD, MORTAR, FLAMMABLE                ).addReRegistrationToThis(MT.Wheat, MT.Rye, MT.Oat, MT.Barley, MT.Potato);
		FlourGrains     .stealLooks(MT.Wheat          ).steal(MT.Wheat          ).setLocal("Flour and Grains"   ).setAllToTheOutputOf(MT.Wheat          ).put(FOOD, MORTAR, FLAMMABLE                ).addReRegistrationToThis(MT.Potato);
		Wax             .stealLooks(MT.Wax            ).steal(MT.Wax            ).setLocal("Wax"                ).setAllToTheOutputOf(MT.Wax            ).put(                                       );
		Stone           .stealLooks(MT.Stone          ).steal(MT.Stone          ).setLocal("Stone"              ).setAllToTheOutputOf(MT.Stone          ).put(STONE, BRITTLE, UNRECYCLABLE           ).addReRegistrationToThis(MT.Gravel);
		Calcite         .stealLooks(MT.CaCO3          ).steal(MT.CaCO3          ).setLocal("Calcite"            ).setAllToTheOutputOf(MT.CaCO3          ).put(STONE, BRITTLE, UNRECYCLABLE           ).addReRegistrationToThis(MT.CaCO3, MT.Marble, MT.Chalk, MT.Limestone, MT.Dolomite);
		Clay            .stealLooks(MT.ClayBrown      ).steal(MT.Clay           ).setLocal("Clay"               ).setAllToTheOutputOf(MT.Clay           ).put(                                       ).addReRegistrationToThis(MT.Clay, MT.ClayBrown);
		Salt            .stealLooks(MT.NaCl           ).steal(MT.NaCl           ).setLocal("Salt"               ).setAllToTheOutputOf(MT.NaCl           ).put(BRITTLE                                ).addReRegistrationToThis(MT.NaCl, MT.KCl, MT.LiCl, MT.MgCl2, MT.CaCl2);
		Fe              .stealLooks(MT.Fe             ).steal(MT.Fe             ).setLocal("Iron"               ).setAllToTheOutputOf(MT.Fe             ).put(SMITHABLE, MELTING                     ).addReRegistrationToThis(MT.Fe, MT.WroughtIron, MT.PigIron, MT.MeteoricIron, MT.Meteorite, MT.Enori);
		Iron            .stealLooks(MT.Fe             ).steal(MT.Fe             ).setLocal("Iron"               ).setAllToTheOutputOf(MT.Fe             ).put(SMITHABLE, MELTING                     ).addReRegistrationToThis(MT.Fe, MT.WroughtIron, MT.PigIron, MT.MeteoricIron, MT.Meteorite, MT.Enori, MT.Steel, MT.HSLA, MT.Knightmetal, MT.MeteoricSteel);
		Steel           .stealLooks(MT.Steel          ).steal(MT.Steel          ).setLocal("Steel"              ).setAllToTheOutputOf(MT.Steel          ).put(SMITHABLE, MELTING                     ).addReRegistrationToThis(MT.Steel, MT.HSLA, MT.Knightmetal, MT.MeteoricSteel);
		Cu              .stealLooks(MT.Cu             ).steal(MT.Cu             ).setLocal("Copper"             ).setAllToTheOutputOf(MT.Cu             ).put(SMITHABLE, MELTING                     ).addReRegistrationToThis(MT.Cu, MT.AnnealedCopper);
		Ash             .stealLooks(MT.Ash            ).steal(MT.Ash            ).setLocal("Ashes"              ).setAllToTheOutputOf(MT.Ash            ).put(BRITTLE                                ).addReRegistrationToThis(MT.Ash, MT.DarkAsh, MT.VolcanicAsh);
		C               .stealLooks(MT.C              ).steal(MT.C              ).setLocal("Carbon"             ).setAllToTheOutputOf(MT.C              ).put(                                       ).addReRegistrationToThis(MT.C, MT.Graphite, MT.Graphene);
		Coal            .stealLooks(MT.C              ).steal(MT.C              ).setLocal("Carbon"             ).setAllToTheOutputOf(MT.C              ).put(                                       ).addReRegistrationToThis(MT.C, MT.Graphite, MT.Graphene, MT.CoalCoke, MT.Coal, MT.Charcoal);
		Si              .stealLooks(MT.Si             ).steal(MT.Si             ).setLocal("Silicon"            ).setAllToTheOutputOf(MT.Si             ).put(SMITHABLE, MELTING                     ).addReRegistrationToThis(MT.Si);
		SiO2            .stealLooks(MT.SiO2           ).steal(MT.SiO2           ).setLocal("Silicon Dioxide"    ).setAllToTheOutputOf(MT.SiO2           ).put(BRITTLE, MELTING                       ).addReRegistrationToThis(MT.Quartzite, MT.SiO2, MT.Glass, MT.Flint);
		Quartz          .stealLooks(MT.MilkyQuartz    ).steal(MT.MilkyQuartz    ).setLocal("Quartz"             ).setAllToTheOutputOf(MT.SiO2           ).put(BRITTLE, MELTING, QUARTZ               ).addReRegistrationToThis(MT.Quartzite);
		W               .stealLooks(MT.W              ).steal(MT.W              ).setLocal("Tungsten"           ).setAllToTheOutputOf(MT.W              ).put(SMITHABLE, MELTING                     ).addReRegistrationToThis(MT.W, MT.TungstenSintered);
		ThaumCrystal    .stealLooks(MT.InfusedBalance ).steal(MT.InfusedDull                                                                            ).put(DONT_SHOW_THIS_COMPONENT               );
		WoodDefault     .stealLooks(MT.Wood           ).steal(MT.Wood           ).setLocal("Normal Wood"        ).setAllToTheOutputOf(MT.Wood           ).put(WOOD, FLAMMABLE                        ).addReRegistrationToThis(MT.Wood, MT.Peanutwood).setFurnaceBurnTime(TICKS_PER_SMELT/ 2);
		WoodNormal      .stealLooks(MT.Wood           ).steal(MT.Wood           ).setLocal("Normal Wood"        ).setAllToTheOutputOf(MT.Wood           ).put(WOOD, FLAMMABLE                        ).addReRegistrationToThis(ANY.WoodDefault.mToThis.toArray(ZL_MT)).addReRegistrationToThis(MT.Weedwood, MT.Skyroot, MT.Bamboo).setFurnaceBurnTime(TICKS_PER_SMELT/ 2);
		WoodMagical     .stealLooks(MT.Greatwood      ).steal(MT.Wood           ).setLocal("Magical Wood"       ).setAllToTheOutputOf(MT.Wood           ).put(WOOD, FLAMMABLE, MAGICAL               ).addReRegistrationToThis(MT.Greatwood, MT.Silverwood, MT.Livingwood, MT.Dreamwood, MT.Shimmerwood).setFurnaceBurnTime(TICKS_PER_SMELT* 2);
		WoodTreated     .stealLooks(MT.WoodSealed     ).steal(MT.Wood           ).setLocal("Treated Wood"       ).setAllToTheOutputOf(MT.Wood           ).put(WOOD, FLAMMABLE                        ).addReRegistrationToThis(MT.WoodSealed, MT.WoodPolished).setFurnaceBurnTime(TICKS_PER_SMELT/ 2);
		WoodUntreated   .stealLooks(MT.Wood           ).steal(MT.Wood           ).setLocal("Untreated Wood"     ).setAllToTheOutputOf(MT.Wood           ).put(WOOD, FLAMMABLE                        ).addReRegistrationToThis(ANY.WoodMagical.mToThis.toArray(ZL_MT)).addReRegistrationToThis(ANY.WoodNormal.mToThis.toArray(ZL_MT)).setFurnaceBurnTime(TICKS_PER_SMELT/ 2);
		Wood            .stealLooks(MT.Wood           ).steal(MT.Wood           ).setLocal("Wood"               ).setAllToTheOutputOf(MT.Wood           ).put(WOOD, FLAMMABLE                        ).setFurnaceBurnTime(TICKS_PER_SMELT/ 2);
		WoodPlastic     .stealLooks(MT.Wood           ).steal(MT.Wood                                                                                   ).put(DONT_SHOW_THIS_COMPONENT               ).addReRegistrationToThis(MT.Plastic);
		
		_Steel          .stealLooks(MT.Steel          ).put(DONT_SHOW_THIS_COMPONENT);
		_Bronze         .stealLooks(MT.Bronze         ).put(DONT_SHOW_THIS_COMPONENT);
		_Metal          .stealLooks(MT.Fe             ).put(DONT_SHOW_THIS_COMPONENT);
		
		for (OreDictMaterial tMaterial : ANY.Iron.mToThis) tMaterial.put(AchievementList.openInventory, AchievementList.mineWood, AchievementList.buildWorkBench, AchievementList.buildPickaxe, AchievementList.buildFurnace, AchievementList.acquireIron);
	}
}
