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

import java.util.List;

import gregapi6.code.TagData;
import gregapi6.lang.LanguageHandler;
import gregapi6.tileentity.behavior.TE_Behavior_Energy_Converter;
import gregapi6.tileentity.behavior.TE_Behavior_Energy_Stats;
import gregapi6.tileentity.energy.ITileEntityEnergy;
import gregapi6.util.UT;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

/**
 * @author Gregorius Techneticies
 *
 * Contains common translatable Strings.
 */
public class LH {
	public static final String
	  EFFICIENCY = "gt6.lang.efficiency"
	, RECIPE = "gt6.lang.recipe"
	, RECIPES = "gt6.lang.recipes"
	, RECIPES_MOLD = "gt6.lang.recipes.mold"
	, RECIPES_MOLD_SELECT = "gt6.lang.recipes.mold.select"
	, RECIPES_MOLD_COINAGE = "gt6.lang.recipes.mold.coinage"
	, RECIPES_ANVIL_USAGE = "gt6.lang.recipes.anvil.usage"
	, RECIPES_SIFTER_USAGE = "gt6.lang.recipes.sifter.usage"
	, RECIPES_JUICER_USAGE = "gt6.lang.recipes.juicer.usage"
	, RECIPES_MORTAR_USAGE = "gt6.lang.recipes.mortar.usage"
	, RECIPES_MIXINGBOWL_USAGE = "gt6.lang.recipes.mixingbowl.usage"
	, RECIPES_BATHINGPOT_USAGE = "gt6.lang.recipes.bathingsink.usage"
	, RECIPES_GRINDSTONE_USAGE = "gt6.lang.recipes.grindstone.usage"
	, RECIPES_GRINDSTONE_INIT = "gt6.lang.recipes.grindstone.init"
	, RECIPES_DUSTFUNNEL = "gt6.lang.recipes.dustfunnel"
	, RECIPES_AUTOHAMMER = "gt6.lang.recipes.autohammer"
	, RECIPES_IGNITER = "gt6.lang.recipes.igniter"
	, RECIPES_QUALITY = "gt6.lang.recipes.quality"
	, STRUCTURE = "gt6.lang.structure"
	, ENERGY_CONTAINED = "gt6.lang.energy.contained"
	, ENERGY_CAPACITY = "gt6.lang.energy.capacity"
	, ENERGY_OUTPUT = "gt6.lang.energy.output"
	, ENERGY_INPUT = "gt6.lang.energy.input"
	, ITEM_OUTPUT = "gt6.lang.item.output"
	, ITEM_INPUT = "gt6.lang.item.input"
	, FLUID_OUTPUT = "gt6.lang.fluid.output"
	, FLUID_INPUT = "gt6.lang.fluid.input"
	, CONVERTS_FROM_X = "gt6.lang.energy.convert.from"
	, CONVERTS_TO_Y = "gt6.lang.energy.convert.to"
	, CONVERTS_USING_Z = "gt6.lang.energy.convert.using"
	, CONVERTS_PER_Z = "gt6.lang.energy.convert.per"
	, FACE_ANY = "gt6.lang.face.any"
	, FACE_BOTTOM = "gt6.lang.face.bottom"
	, FACE_TOP = "gt6.lang.face.top"
	, FACE_LEFT = "gt6.lang.face.left"
	, FACE_FRONT = "gt6.lang.face.front"
	, FACE_RIGHT = "gt6.lang.face.right"
	, FACE_BACK = "gt6.lang.face.back"
	, FACE_NONE = "gt6.lang.face.none"
	, FACES[] = {FACE_BOTTOM, FACE_TOP, FACE_LEFT, FACE_FRONT, FACE_RIGHT, FACE_BACK, FACE_NONE}
	, FACE_SIDES = "gt6.lang.face.sides"
	, FACE_FRONT_BACK = "gt6.lang.face.front.back"
	, FACE_ANYBUT_FRONT_BACK = "gt6.lang.face.any.but.front.back"
	, FACE_ANYBUT_BOTTOM = "gt6.lang.face.any.but.bottom"
	, FACE_ANYBUT_TOP = "gt6.lang.face.any.but.top"
	, FACE_ANYBUT_LEFT = "gt6.lang.face.any.but.left"
	, FACE_ANYBUT_FRONT = "gt6.lang.face.any.but.front"
	, FACE_ANYBUT_RIGHT = "gt6.lang.face.any.but.right"
	, FACE_ANYBUT_BACK = "gt6.lang.face.any.but.back"
	, FACE_ANYBUT_SIDES = "gt6.lang.face.any.but.sides"
	, REQUIREMENT_AIR_IN_FRONT = "gt6.lang.requirement.air.front"
	, REQUIREMENT_EMPTY_ASHES = "gt6.lang.requirement.empty.ashes"
	, REQUIREMENT_IGNITE_FIRE = "gt6.lang.requirement.ignite.fire"
	, REQUIREMENT_MOLTEN_CALCITE = "gt6.lang.requirement.molten.calcite"
	, REQUIREMENT_WATER = "gt6.lang.requirement.water"
	, REQUIREMENT_WATER_ANY = "gt6.lang.requirement.water.any"
	, REQUIREMENT_WATER_PURE = "gt6.lang.requirement.water.pure"
	, REQUIREMENT_DISTILLED_WATER = "gt6.lang.requirement.water.distilled"
	, REQUIREMENT_CHUNKLOADER = "gt6.lang.requirement.chunk.loader"
	, REQUIREMENT_UNSTACKED = "gt6.lang.requirement.unstacked"
	, EMITS_USED_STEAM = "gt6.lang.emits.used.steam"
	, EMITS_REDSTONE_FLUX = "gt6.lang.emits.redstoneflux.lossless"
	, EMITS_REDSTONE_FLUX_LOSS = "gt6.lang.emits.redstoneflux.lossy"
	, ACCEPTS_REDSTONE_FLUX = "gt6.lang.accepts.redstoneflux.lossless"
	, ACCEPTS_REDSTONE_FLUX_LOSS = "gt6.lang.accepts.redstoneflux.lossy"
	, NO_GUI_CLICK_TO_LIMIT = "gt6.lang.nogui.rightclick.tank.limit"
	, NO_GUI_CLICK_TO_INTERACT = "gt6.lang.nogui.rightclick.interact"
	, NO_GUI_CLICK_TO_INVENTORY = "gt6.lang.nogui.rightclick.inventory"
	, NO_GUI_CLICK_TO_TANK = "gt6.lang.nogui.rightclick.tank"
	, NO_GUI_FUNNEL_TAP_TO_TANK = "gt6.lang.nogui.funnel.tap.tank"
	, NO_GUI_FUNNEL_TO_TANK = "gt6.lang.nogui.funnel.tank"
	, NO_GUI_TAP_TO_TANK = "gt6.lang.nogui.tap.tank"
	, NO_POWER_CONDUCTING_FLUIDS = "gt6.lang.no.powerconducting.fluids"
	, OWNER_CONTROLLED = "gt6.lang.owner.controlled"
	, CHEAP_OVERCLOCKING = "gt6.lang.cheap.overclocking"
	, KEY_CONTROLLED = "gt6.lang.key.controlled"
	, COVER_TOOLTIP = "gt6.lang.cover.tooltip"
	, TOOL_TO_SET_FACING_PRE = "gt6.lang.use.x.to.toggle.facing.pre"
	, TOOL_TO_SET_FACING_POST = "gt6.lang.use.x.to.toggle.facing.post"
	, TOOL_TO_SET_CONNECTIONS_PRE = "gt6.lang.use.x.to.toggle.connection.pre"
	, TOOL_TO_SET_CONNECTIONS_POST = "gt6.lang.use.x.to.toggle.connection.post"
	, TOOL_TO_SET_DIRECTION_MONKEY_WRENCH = "gt6.lang.use.monkey.wrench.to.toggle.direction"
	, TOOL_TO_SET_INPUT_MONKEY_WRENCH = "gt6.lang.use.monkey.wrench.to.set.input.side"
	, TOOL_TO_SET_OUTPUT_MONKEY_WRENCH = "gt6.lang.use.monkey.wrench.to.set.output.side"
	, TOOL_TO_TOGGLE_INPUTS_MONKEY_WRENCH = "gt6.lang.use.monkey.wrench.to.toggle.inputs"
	, TOOL_TO_TOGGLE_OUTPUTS_MONKEY_WRENCH = "gt6.lang.use.monkey.wrench.to.toggle.outputs"
	, TOOL_TO_TOGGLE_AUTO_INPUTS_MONKEY_WRENCH = "gt6.lang.use.monkey.wrench.to.toggle.auto.inputs"
	, TOOL_TO_TOGGLE_AUTO_OUTPUTS_MONKEY_WRENCH = "gt6.lang.use.monkey.wrench.to.toggle.auto.outputs"
	, TOOL_TO_TOGGLE_SCREWDRIVER = "gt6.lang.use.screwdriver.to.toggle"
	, TOOL_TO_TOGGLE_MONKEY_WRENCH = "gt6.lang.use.monkey.wrench.to.toggle"
	, TOOL_TO_TOGGLE_CUTTER = "gt6.lang.use.cutter.to.toggle"
	, TOOL_TO_TOGGLE_SOFT_HAMMER = "gt6.lang.use.soft.hammer.to.toggle"
	, TOOL_TO_RESET_SOFT_HAMMER = "gt6.lang.use.soft.hammer.to.reset"
	, TOOL_TO_TAPE = "gt6.lang.use.tape"
	, TOOL_TO_UNTAPE = "gt6.lang.use.untape"
	, TOOL_TO_OPEN_CROWBAR = "gt6.lang.use.crowbar.to.open"
	, TOOL_TO_UNCOVER_CROWBAR = "gt6.lang.use.crowbar.to.uncover"
	, TOOL_TO_DECALCIFY_CHISEL = "gt6.lang.use.chisel.to.decalcify"
	, TOOL_TO_DETAIL_MAGNIFYINGGLASS = "gt6.lang.use.magnifyingglass.to.detail"
	, TOOL_TO_MEASURE_GEIGER_COUNTER = "gt6.lang.use.geigercoutner.to.measure"
			, TOOL_TO_MEASURE_THERMOMETER = "gt6.lang.use.thermometer.to.measure"
			, TOOL_TO_ACCESS_SCOOP = "gt6.lang.use.scoop.to.access"
	, TOOL_TO_REMOVE_SHOVEL = "gt6.lang.use.shovel.to.empty"
	, TOOL_TO_CHANGE_DESIGN_CHISEL = "gt6.lang.use.chisel.to.switch.design"
	, TOOL_TO_HARVEST = "gt6.lang.tool.to.harvest"
	, TOOL_TO_TAKE_PINCERS = "gt6.lang.use.pincers.to.take"
	, TOOL_HINT_USE_SNEAK = "gt6.lang.tool.hint.use.sneak"
	, WEAPON_SNEAK_RIGHTCLICK_TO_RELOAD = "gt6.weapon.sneak.rightclick.reload"
	, WIRE_STATS_LOSSLESS = "gt6.lang.wire.stats.lossless"
	, WIRE_STATS_LOSS = "gt6.lang.wire.stats.loss"
	, WIRE_STATS_VOLTAGE = "gt6.lang.wire.stats.voltage"
	, WIRE_STATS_AMPERAGE = "gt6.lang.wire.stats.amperage"
	, PIPE_STATS_LOSS = "gt6.lang.pipe.stats.loss"
	, PIPE_STATS_RANGE = "gt6.lang.pipe.stats.range"
	, PIPE_STATS_STEPSIZE = "gt6.lang.pipe.stats.stepsize"
	, PIPE_STATS_BANDWIDTH = "gt6.lang.pipe.stats.bandwidth"
	, PIPE_STATS_CAPACITY = "gt6.lang.pipe.stats.capacity"
	, PIPE_STATS_AMOUNT = "gt6.lang.pipe.stats.amount"
	, AXLE_STATS_SPEED = "gt6.lang.axle.stats.speed"
	, AXLE_STATS_POWER = "gt6.lang.axle.stats.power"
	, HAZARD_FIRE = "gt6.lang.hazard.fire"
	, HAZARD_EXPLOSION_STEAM = "gt6.lang.hazard.explosion.steam"
	, HAZARD_MELTDOWN = "gt6.lang.hazard.meltdown"
	, HAZARD_CONTACT = "gt6.lang.hazard.contact"
	, HAZARD_LEAKING_GAS = "gt6.lang.hazard.leak.gas"
	, TOOLTIP_GASPROOF = "gt6.lang.proof.gas"
	, TOOLTIP_ACIDPROOF = "gt6.lang.proof.acid"
	, TOOLTIP_LIQUIDPROOF = "gt6.lang.proof.liquid"
	, TOOLTIP_PLASMAPROOF = "gt6.lang.proof.plasma"
	, TOOLTIP_HEATPROOF = "gt6.lang.proof.heat"
	, TOOLTIP_SEALABLE_ANY = "gt6.lang.sealable.any"
	, TOOLTIP_SEALABLE_SOME = "gt6.lang.sealable.some"
	, TOOLTIP_SEALABLE_BUGGED = "gt6.lang.sealable.bug"
	, TOOLTIP_PISTONPUSHABLE = "gt6.lang.pistonpush"
	, TOOLTIP_SPAWNPROOF = "gt6.lang.spawnproof"
	, TOOLTIP_BLASTPOWER = "gt6.lang.blastpower"
	, TOOLTIP_BLASTRANGE = "gt6.lang.blastrange"
	, TOOLTIP_BLASTFORTUNE = "gt6.lang.blastfortune"
	, TOOLTIP_BLASTRESISTANCE = "gt6.lang.blastresistance"
	, TOOLTIP_RAILSPEED = "gt6.lang.railspeed"
	, TOOLTIP_WALKSPEED = "gt6.lang.walkspeed"
	, TOOLTIP_GRAVITY = "gt6.lang.gravity"
	, TOOLTIP_NEEDS_HANDLE = "gt6.lang.needs.handle"
	, TOOLTIP_NEEDS_SHARPENING = "gt6.lang.needs.sharpening"
	, TOOLTIP_SHAPELESS_CRAFT = "gt6.lang.has.shapeless"
	, TOOLTIP_AUTOCOLLECT = "gt6.lang.autocollect"
	, TOOLTIP_BEACON_PAYMENT = "gt6.lang.beacon.payment"
	, TOOLTIP_SHELFABLE = "gt6.lang.shelfable"
	, TOOLTIP_POSSIBLE_TOOL_ENCHANTS = "gt6.lang.tool.enchants"
	, TOOLTIP_POSSIBLE_ARMOR_ENCHANTS = "gt6.lang.armor.enchants"
	, TOOLTIP_TOO_MANY_TOOL_ENCHANTS = "gt6.lang.tool.enchants.too.many"
	, TOOLTIP_TOO_MANY_ARMOR_ENCHANTS = "gt6.lang.armor.enchants.too.many"
	, TOOLTIP_CONTAINED_MATERIALS = "gt6.lang.contained.materials"
	, TOOLTIP_FLAMMABLE_AND_EXPLOSIVE = "gt6.lang.flammable.explosive"
	, TOOLTIP_FLAMMABLE = "gt6.lang.flammable"
	, TOOLTIP_EXPLOSIVE = "gt6.lang.explosive"
	, TOOLTIP_BLAST_RESISTANCE_TERRIBLE = "gt6.lang.blast.resist.terrible"
	, TOOLTIP_BLAST_RESISTANCE_GHAST = "gt6.lang.blast.resist.ghast.proof"
	, TOOLTIP_BLAST_RESISTANCE_CREEPER = "gt6.lang.blast.resist.creeper.proof"
	, TOOLTIP_BLAST_RESISTANCE_TNT = "gt6.lang.blast.resist.tnt.proof"
			, TOOLTIP_BLAST_RESISTANCE_DYNAMITE = "gt6.lang.blast.resist.dynamite.proof"
	, TOOLTIP_BLAST_RESISTANCE_NOT_NUKE = "gt6.lang.blast.resist.nuke.not"
	, TOOLTIP_BETWEENLANDS_RESISTANCE = "gt6.lang.betweenlands.resist"
	, PROSPECTING_LAVA = "gt6.lang.prospecting.lava"
	, PROSPECTING_LIQUID = "gt6.lang.prospecting.liquid"
	, PROSPECTING_AIR = "gt6.lang.prospecting.air"
	, PROSPECTING_CHANGE = "gt6.lang.prospecting.change"
	, PROSPECTING_TRACES = "gt6.lang.prospecting.traces"
	, PROSPECTING_NOTHING = "gt6.lang.prospecting.nothing"
	, AUTOCRAFTING_INSERT_BLUEPRINT = "gt6.autocrafting.insert.blueprint"
	, ADVCRAFTING_INSERT_BLUEPRINT = "gt6.advcrafting.insert.blueprint"
	, ADVCRAFTING_PUT_TO_STORAGE = "gt6.advcrafting.put.to.storage"
	, ADVCRAFTING_AUTOMATION_ACCESS = "gt6.advcrafting.automation.access"
	, ADVCRAFTING_NEUTRAL_SLOT = "gt6.advcrafting.neutral.slot"
	, ADVCRAFTING_DROP_SLOT = "gt6.advcrafting.drop.slot"
	, TIME_TICK = "gt6.lang.time.tick"
	, TIME_SEC = "gt6.lang.time.second"
	, TIME_MIN = "gt6.lang.time.minute"
	, TIME_HOUR = "gt6.lang.time.hour"
	, TIME_DAY = "gt6.lang.time.day"
	, TIME_WEEK = "gt6.lang.time.week"
	, TIME_TICKS = "gt6.lang.time.ticks"
	, TIME_SECS = "gt6.lang.time.seconds"
	, TIME_MINS = "gt6.lang.time.minutes"
	, TIME_HOURS = "gt6.lang.time.hours"
	, TIME_DAYS = "gt6.lang.time.days"
	, TIME_WEEKS = "gt6.lang.time.weeks"
	, ADMIN_ONLY_CREATION = "gt6.lang.admin.only.creation"
	, WIP = "gt6.lang.work.in.progress"
	;

	public static final String add(String aKey, String aEnglish) {LanguageHandler.add(aKey, aEnglish); return aKey;}
	public static final String get(String aKey) {return LanguageHandler.translate(aKey);}
	public static final String get(String aKey, String aDefault) {return LanguageHandler.translate(aKey, aDefault);}


	public static final String percent(long aNumber) {return (aNumber/100) + ((aNumber%100)>9?"."+aNumber%100:".0"+(aNumber%100));}

	public static final String getToolTipBlastResistance(Block aBlock, double aResistance) {return Chat.WHITE + get(LH.TOOLTIP_BLASTRESISTANCE) + Chat.ORANGE + ((int)aResistance) + "." + (((int)(aResistance * 10)) % 10) + (aResistance < 4 ? Chat.BLINKING_RED + " " + get(LH.TOOLTIP_BLAST_RESISTANCE_TERRIBLE) : aResistance < 12 ? Chat.RED + " " + get(LH.TOOLTIP_BLAST_RESISTANCE_GHAST) : aResistance < 16 ? Chat.YELLOW + " " + get(LH.TOOLTIP_BLAST_RESISTANCE_CREEPER) : aResistance <= 40 ? Chat.GREEN + " " + get(LH.TOOLTIP_BLAST_RESISTANCE_TNT) : aResistance < 3330 || (aBlock != NB && aBlock != null && COMPAT_IC2 != null && COMPAT_IC2.isExplosionWhitelisted(aBlock)) ? Chat.GREEN + " " + get(LH.TOOLTIP_BLAST_RESISTANCE_DYNAMITE) : Chat.BLINKING_CYAN + " " + get(LH.TOOLTIP_BLAST_RESISTANCE_NOT_NUKE));}

	public static final String getToolTipEfficiency(long aEfficiency) {aEfficiency = Math.abs(aEfficiency); return Chat.YELLOW + get(EFFICIENCY) + ": " + Chat.WHITE + percent(aEfficiency) + "%";}

	public static final void addToolTipsEfficiency(List<String> aList, ItemStack aStack, boolean aF3_H, TE_Behavior_Energy_Converter aConverter) {
		addToolTipsEfficiency(aList, aStack, aF3_H, aConverter.mEnergyIN, aConverter.mEnergyOUT, aConverter.mMultiplier);
	}
	public static final void addToolTipsEfficiency(List<String> aList, ItemStack aStack, boolean aF3_H, TE_Behavior_Energy_Stats aEnergyIN, TE_Behavior_Energy_Stats aEnergyOUT, long aMultiplier) {
		if (TD.Energy.ALL_EU.contains(aEnergyIN.mType)) {
			if (TD.Energy.ALL_EU.contains(aEnergyOUT.mType)) {
				aList.add(LH.getToolTipEfficiency(UT.Code.units(10000, aEnergyIN.mRec, aEnergyOUT.mRec*aMultiplier, F)));
			} else {
				if (aEnergyOUT.mType == TD.Energy.RF  ) aList.add(LH.getToolTipEfficiency(UT.Code.units(10000, aEnergyIN.mRec*RF_PER_EU, aEnergyOUT.mRec*aMultiplier, F)));
		}
		} else {
			if (TD.Energy.ALL_EU.contains(aEnergyOUT.mType)) {
				if (aEnergyIN.mType == TD.Energy.RF   ) aList.add(LH.getToolTipEfficiency(UT.Code.units(10000, aEnergyIN.mRec, aEnergyOUT.mRec*aMultiplier*RF_PER_EU, F)));
				if (aEnergyIN.mType == TD.Energy.STEAM) aList.add(LH.getToolTipEfficiency(UT.Code.units(10000, aEnergyIN.mRec, aEnergyOUT.mRec*aMultiplier*STEAM_PER_EU, F)));
		}
		}
	}

	public static final void addToolTipsEfficiency(List<String> aList, ItemStack aStack, boolean aF3_H, TE_Behavior_Energy_Stats aEnergyIN, TE_Behavior_Energy_Stats aEnergyOUT, TE_Behavior_Energy_Stats aEnergyOUT2, long aMultiplier) {
		if (TD.Energy.ALL_EU.contains(aEnergyIN.mType)) {
			if (TD.Energy.ALL_EU.contains(aEnergyOUT.mType)) {
				aList.add(LH.getToolTipEfficiency(UT.Code.units(10000, aEnergyIN.mRec, aEnergyOUT.mRec, F)));
			} else {
				if (aEnergyOUT.mType == TD.Energy.RF) aList.add(LH.getToolTipEfficiency(UT.Code.units(10000, aEnergyIN.mRec*RF_PER_EU, aEnergyOUT.mRec, F)));
			}
			if (TD.Energy.ALL_EU.contains(aEnergyOUT2.mType)) {
				aList.add(LH.getToolTipEfficiency(UT.Code.units(10000, aEnergyIN.mRec, aEnergyOUT.mRec, F)));
			} else {
				if (aEnergyOUT2.mType == TD.Energy.RF) aList.add(LH.getToolTipEfficiency(UT.Code.units(10000, aEnergyIN.mRec*RF_PER_EU, aEnergyOUT.mRec, F)));
			}
		} else {
			if (TD.Energy.ALL_EU.contains(aEnergyOUT.mType) && aEnergyIN.mType == TD.Energy.RF) aList.add(LH.getToolTipEfficiency(UT.Code.units(10000, aEnergyIN.mRec, aEnergyOUT.mRec*8, F)));
			if (TD.Energy.ALL_EU.contains(aEnergyOUT2.mType) && aEnergyIN.mType == TD.Energy.RF) aList.add(LH.getToolTipEfficiency(UT.Code.units(10000, aEnergyIN.mRec, aEnergyOUT.mRec*8, F)));
		}
	}

	public static final void addEnergyToolTips(ITileEntityEnergy aTileEntity, List<String> aToolTips, TagData aEnergyTypeIN, TagData aEnergyTypeOUT, String aSidesIN, String aSidesOUT) {
		if (aEnergyTypeIN != null) {
			long tMin = aTileEntity.getEnergySizeInputMin(aEnergyTypeOUT, SIDE_ANY), tRec = aTileEntity.getEnergySizeInputRecommended(aEnergyTypeOUT, SIDE_ANY), tMax = aTileEntity.getEnergySizeInputMax(aEnergyTypeOUT, SIDE_ANY);
			aToolTips.add(Chat.GREEN + LH.get(LH.ENERGY_INPUT ) + ": " + Chat.WHITE + tRec + " " + aEnergyTypeIN .getChatFormat() + aEnergyTypeIN .getLocalisedNameShort() + Chat.WHITE + (tRec == tMin && tRec == tMax ? "/t" : (tMin <= 1 ? "/t (up to " : "/t ("+tMin+" to ")+tMax+(UT.Code.stringInvalid(aSidesIN )?"":", "+aSidesIN )+")"));
			aToolTips.add(getToolTipRedstoneFluxAccept(aEnergyTypeIN));
		}
		if (aEnergyTypeOUT != null) {
			long tMin = aTileEntity.getEnergySizeOutputMin(aEnergyTypeOUT, SIDE_ANY), tRec = aTileEntity.getEnergySizeOutputRecommended(aEnergyTypeOUT, SIDE_ANY), tMax = aTileEntity.getEnergySizeOutputMax(aEnergyTypeOUT, SIDE_ANY);
			aToolTips.add(Chat.RED   + LH.get(LH.ENERGY_OUTPUT) + ": " + Chat.WHITE + tRec + " " + aEnergyTypeOUT.getChatFormat() + aEnergyTypeOUT.getLocalisedNameShort() + Chat.WHITE + (tRec == tMin && tRec == tMax ? "/t" : (tMin <= 1 ? "/t (up to " : "/t ("+tMin+" to ")+tMax+(UT.Code.stringInvalid(aSidesOUT)?"":", "+aSidesOUT)+")"));
			aToolTips.add(getToolTipRedstoneFluxEmit(aEnergyTypeOUT));
		}
	}

	public static final String getToolTipRedstoneFluxEmit(TagData aEnergyType) {
		if (aEnergyType == TD.Energy.KU) return Chat.ORANGE + LH.get(LH.EMITS_REDSTONE_FLUX_LOSS)+" 50%";
		if (aEnergyType == TD.Energy.RF) return Chat.ORANGE + LH.get(LH.EMITS_REDSTONE_FLUX);
		if (aEnergyType == TD.Energy.MJ) return Chat.ORANGE + LH.get(LH.EMITS_REDSTONE_FLUX);
		return null;
	}

	public static final String getToolTipRedstoneFluxAccept(TagData aEnergyType) {
//      if (aEnergyType == TD.Energy.KU) return Chat.ORANGE + LH.get(LH.ACCEPTS_REDSTONE_FLUX_LOSS)+" 50%";
		if (aEnergyType == TD.Energy.RF) return Chat.ORANGE + LH.get(LH.ACCEPTS_REDSTONE_FLUX);
		if (aEnergyType == TD.Energy.MJ) return Chat.ORANGE + LH.get(LH.ACCEPTS_REDSTONE_FLUX);
		return null;
	}

	static {
		add(EFFICIENCY                                  , "Efficiency");
		add(RECIPE                                      , "Recipe");
		add(RECIPES                                     , "Recipes");
		add(RECIPES_MOLD                                , "This Mold produces");
		add(RECIPES_MOLD_SELECT                         , "Use a Chisel in order to select the Shape of the Mold");
		add(RECIPES_MOLD_COINAGE                        , "Place tiny Metal Plate ontop, hammer it and retrieve Coin");
		add(RECIPES_ANVIL_USAGE                         , "Place Input on Top and use the Hammer on either the Top or the Sides");
		add(RECIPES_MORTAR_USAGE                        , "Rightclick with the Item you want to turn into Dust");
		add(RECIPES_JUICER_USAGE                        , "Rightclick with the Item you want to get Juice from");
		add(RECIPES_SIFTER_USAGE                        , "Place Input on Top and rightclick it");
		add(RECIPES_MIXINGBOWL_USAGE                    , "Place Input on Top and rightclick it");
		add(RECIPES_BATHINGPOT_USAGE                    , "Place Input on Top and rightclick it");
		add(RECIPES_GRINDSTONE_USAGE                    , "Click multiple times with the Object you want to sharpen");
		add(RECIPES_GRINDSTONE_INIT                     , "Add Sandstone Block in order to be able to use this");
		add(RECIPES_DUSTFUNNEL                          , "Turns all differently sized Dusts into the specified Size");
		add(RECIPES_AUTOHAMMER                          , "Performs Hammer Rightclicks and crushes Blocks");
		add(RECIPES_IGNITER                             , "Performs Ignition Rightclicks and incinerates Blocks");
		add(RECIPES_QUALITY                             , "This has a Tool Quality of");
		add(STRUCTURE                                   , "Structure");
		add(ENERGY_CONTAINED                            , "Stored Energy");
		add(ENERGY_CAPACITY                             , "Capacity");
		add(ENERGY_OUTPUT                               , "Energy OUT");
		add(ENERGY_INPUT                                , "Energy IN");
		add(ITEM_OUTPUT                                 , "Items OUT");
		add(ITEM_INPUT                                  , "Items IN");
		add(FLUID_OUTPUT                                , "Fluids OUT");
		add(FLUID_INPUT                                 , "Fluids IN");
		add(CONVERTS_FROM_X                             , "Converts");
		add(CONVERTS_TO_Y                               , "into");
		add(CONVERTS_USING_Z                            , "using");
		add(CONVERTS_PER_Z                              , "per");
		add(FACE_ANY                                    , "Any Side");
		add(FACE_BOTTOM                                 , "Bottom");
		add(FACE_TOP                                    , "Top");
		add(FACE_LEFT                                   , "Left");
		add(FACE_FRONT                                  , "Front");
		add(FACE_RIGHT                                  , "Right");
		add(FACE_BACK                                   , "Back");
		add(FACE_NONE                                   , "None");
		add(FACE_SIDES                                  , "Sides");
		add(FACE_FRONT_BACK                             , "Front and Back");
		add(FACE_ANYBUT_FRONT_BACK                      , "Any but Front and Back");
		add(FACE_ANYBUT_BOTTOM                          , "Any but Bottom");
		add(FACE_ANYBUT_TOP                             , "Any but Top");
		add(FACE_ANYBUT_LEFT                            , "Any but Left");
		add(FACE_ANYBUT_FRONT                           , "Any but Front");
		add(FACE_ANYBUT_RIGHT                           , "Any but Right");
		add(FACE_ANYBUT_BACK                            , "Any but Back");
		add(FACE_ANYBUT_SIDES                           , "Any but Sides");
		add(REQUIREMENT_AIR_IN_FRONT                    , "Requires Air in front to work!");
		add(REQUIREMENT_EMPTY_ASHES                     , "Requires Ashes to be extracted regularly!");
		add(REQUIREMENT_IGNITE_FIRE                     , "Requires ignition by Flint and Tinder or similar!");
		add(REQUIREMENT_MOLTEN_CALCITE                  , "Requires to be filled with Molten Calcite!");
		add(REQUIREMENT_WATER                           , "Requires input of Water!");
		add(REQUIREMENT_WATER_ANY                       , "Requires input of any Water!");
		add(REQUIREMENT_WATER_PURE                      , "Requires any Water. Use distilled Water for best efficiency!");
		add(REQUIREMENT_DISTILLED_WATER                 , "Requires input of distilled Water!");
		add(REQUIREMENT_CHUNKLOADER                     , "Needs to be in a loaded Chunk to work properly!");
		add(REQUIREMENT_UNSTACKED                       , "Does not work when stacked!");
		add(EMITS_USED_STEAM                            , "Emits used Steam");
		add(EMITS_REDSTONE_FLUX                         , "Can emit Redstone Flux (RF) losslessly");
		add(EMITS_REDSTONE_FLUX_LOSS                    , "Can emit Redstone Flux (RF) with a Loss of");
		add(ACCEPTS_REDSTONE_FLUX                       , "Can accept Redstone Flux (RF) losslessly");
		add(ACCEPTS_REDSTONE_FLUX_LOSS                  , "Can accept Redstone Flux (RF) with a Loss of");
		add(NO_GUI_CLICK_TO_LIMIT                       , "Click on Side with Empty Hand to set Limit (Height and Sneak Sensitive)");
		add(NO_GUI_CLICK_TO_INTERACT                    , "No GUI. Click to interact!");
		add(NO_GUI_CLICK_TO_INVENTORY                   , "No GUI. Click to insert/extract Items!");
		add(NO_GUI_CLICK_TO_TANK                        , "No GUI. Click with Fluid Container to interact!");
		add(NO_GUI_FUNNEL_TAP_TO_TANK                   , "No GUI. Use Tiny Funnels and Taps to interact!");
		add(NO_GUI_FUNNEL_TO_TANK                       , "No GUI. Use Tiny Funnels to interact!");
		add(NO_GUI_TAP_TO_TANK                          , "No GUI. Use Taps to interact!");
		add(NO_POWER_CONDUCTING_FLUIDS                  , "All entering Power Conductor Fluids will be voided!");
		add(OWNER_CONTROLLED                            , "This Block can only be interacted with by its Owner!");
		add(CHEAP_OVERCLOCKING                          , "Can be overclocked without additional Energy Loss");
		add(KEY_CONTROLLED                              , "This Block can only be opened with a Key!");
		add(COVER_TOOLTIP                               , "This Item can be used as Cover");
		add(TOOL_TO_REMOVE_SHOVEL                       , "Use Shovel to empty this");
		add(TOOL_TO_CHANGE_DESIGN_CHISEL                , "Use Chisel to change Design");
		add(TOOL_TO_TOGGLE_SCREWDRIVER                  , "Use Screwdriver to toggle Modes");
		add(TOOL_TO_TOGGLE_MONKEY_WRENCH                , "Use Monkey Wrench to toggle Modes");
		add(TOOL_TO_TOGGLE_CUTTER                       , "Use Cutter to toggle Modes");
		add(TOOL_TO_OPEN_CROWBAR                        , "Use Crowbar to open this");
		add(TOOL_TO_UNCOVER_CROWBAR                     , "Use Crowbar to remove Covers");
		add(TOOL_TO_DECALCIFY_CHISEL                    , "Use Chisel to decalcify");
		add(TOOL_TO_DETAIL_MAGNIFYINGGLASS              , "Use Magnifying Glass to see Details");
		add(TOOL_TO_MEASURE_GEIGER_COUNTER              , "Use Geiger Counter to Measure");
		add(TOOL_TO_MEASURE_THERMOMETER                 , "Use Thermometer to Measure");
		add(TOOL_TO_ACCESS_SCOOP                        , "Use Scoop to extract (Bumble-)Bees");
		add(TOOL_TO_TOGGLE_INPUTS_MONKEY_WRENCH         , "Use Monkey Wrench to toggle Inputs");
		add(TOOL_TO_TOGGLE_OUTPUTS_MONKEY_WRENCH        , "Use Monkey Wrench to toggle Outputs");
		add(TOOL_TO_TOGGLE_AUTO_INPUTS_MONKEY_WRENCH    , "Use Monkey Wrench to toggle automatic Inputs");
		add(TOOL_TO_TOGGLE_AUTO_OUTPUTS_MONKEY_WRENCH   , "Use Monkey Wrench to toggle automatic Outputs");
		add(TOOL_TO_TOGGLE_SOFT_HAMMER                  , "Use Soft Hammer to toggle States");
		add(TOOL_TO_RESET_SOFT_HAMMER                   , "Use Soft Hammer to Reset");
		add(TOOL_TO_TAPE                                , "Use Duct Tape to do anything Duct Tape can do!");
		add(TOOL_TO_UNTAPE                              , "Use Scissors or Knives to remove Tape");
		add(TOOL_TO_SET_INPUT_MONKEY_WRENCH             , "Use Monkey Wrench to set Input Side");
		add(TOOL_TO_SET_OUTPUT_MONKEY_WRENCH            , "Use Monkey Wrench to set Output Side");
		add(TOOL_TO_SET_DIRECTION_MONKEY_WRENCH         , "Use Monkey Wrench to set Direction");
		add(TOOL_TO_SET_FACING_PRE                      , "Use ");
		add(TOOL_TO_SET_FACING_POST                     , " to set Facing");
		add(TOOL_TO_SET_CONNECTIONS_PRE                 , "Use ");
		add(TOOL_TO_SET_CONNECTIONS_POST                , " to set Connections");
		add(TOOL_TO_HARVEST                             , "Tool to Harvest");
		add(TOOL_TO_TAKE_PINCERS                        , "Use Pincers to extract Items");
		add(TOOL_HINT_USE_SNEAK                         , "Use Tool and Sneak for more options");
		add(WEAPON_SNEAK_RIGHTCLICK_TO_RELOAD           , "Sneak Rightclick to Reload");
		add(WIRE_STATS_LOSSLESS                         , "Transfers Power losslessly");
		add(WIRE_STATS_LOSS                             , "Loss: ");
		add(WIRE_STATS_VOLTAGE                          , "Voltage: ");
		add(WIRE_STATS_AMPERAGE                         , "Amperage: ");
		add(PIPE_STATS_LOSS                             , "Loss: ");
		add(PIPE_STATS_RANGE                            , "Range: ");
		add(PIPE_STATS_STEPSIZE                         , "Stepsize: ");
		add(PIPE_STATS_BANDWIDTH                        , "Bandwidth: ");
		add(PIPE_STATS_CAPACITY                         , "Capacity: ");
		add(PIPE_STATS_AMOUNT                           , "Amount of Pipes: ");
		add(AXLE_STATS_SPEED                            , "Speed: ");
		add(AXLE_STATS_POWER                            , "Power: ");
		add(HAZARD_FIRE                                 , "Can put Blocks around it on Fire!");
		add(HAZARD_EXPLOSION_STEAM                      , "Explodes when Steam Pressure is too high!");
		add(HAZARD_MELTDOWN                             , "Melts down when stored Heat is too much!");
		add(HAZARD_CONTACT                              , "Causes Damage when touched while active!");
		add(HAZARD_LEAKING_GAS                          , "Leaks when used with Gasses!");
		add(TOOLTIP_GASPROOF                            , "Can handle Gasses");
		add(TOOLTIP_ACIDPROOF                           , "Can handle Acids");
		add(TOOLTIP_LIQUIDPROOF                         , "Can handle Liquids");
		add(TOOLTIP_PLASMAPROOF                         , "Can handle Plasma");
		add(TOOLTIP_HEATPROOF                           , "Can handle Temperatures up to: ");
		add(TOOLTIP_SEALABLE_ANY                        , "This Block can seal Air at any Side");
		add(TOOLTIP_SEALABLE_SOME                       , "This Block can seal Air at some Sides");
		add(TOOLTIP_SEALABLE_BUGGED                     , "Shouldn't seal Air, but sometimes does because opaque");
		add(TOOLTIP_PISTONPUSHABLE                      , "Pistons can push this Block");
		add(TOOLTIP_SPAWNPROOF                          , "Mobs cannot Spawn on this Block");
		add(TOOLTIP_BLASTPOWER                          , "Blast Power: ");
		add(TOOLTIP_BLASTRANGE                          , "Blast Range: ");
		add(TOOLTIP_BLASTFORTUNE                        , "Blast Fortune Level: ");
		add(TOOLTIP_BLASTRESISTANCE                     , "Blast Resistance: ");
		add(TOOLTIP_RAILSPEED                           , "Rail Speed: ");
		add(TOOLTIP_WALKSPEED                           , "This Block alters the Walk Speed");
		add(TOOLTIP_GRAVITY                             , "This Block is affected by Gravity");
		add(TOOLTIP_NEEDS_HANDLE                        , "Requires Handle made of: ");
		add(TOOLTIP_NEEDS_SHARPENING                    , "Needs to be sharpened before use");
		add(TOOLTIP_SHAPELESS_CRAFT                     , "Has Shapeless Recipes with Amounts: ");
		add(TOOLTIP_AUTOCOLLECT                         , "Can Auto-Collect Items when harvesting Block");
		add(TOOLTIP_BEACON_PAYMENT                      , "Can be used as a Beacon Payment");
		add(TOOLTIP_SHELFABLE                           , "Can be placed inside a GT Bookshelf");
		add(TOOLTIP_POSSIBLE_TOOL_ENCHANTS              , "Possible Tool Enchantments:");
		add(TOOLTIP_POSSIBLE_ARMOR_ENCHANTS             , "Possible Armor Enchantments:");
		add(TOOLTIP_TOO_MANY_TOOL_ENCHANTS              , "Too Many Tool Enchantments to List");
		add(TOOLTIP_TOO_MANY_ARMOR_ENCHANTS             , "Too Many Armor Enchantments to List");
		add(TOOLTIP_CONTAINED_MATERIALS                 , "Contained Materials:");
		add(TOOLTIP_FLAMMABLE_AND_EXPLOSIVE             , "Flammable and Explosive!");
		add(TOOLTIP_FLAMMABLE                           , "Flammable!");
		add(TOOLTIP_EXPLOSIVE                           , "Explosive!");
		add(TOOLTIP_BLAST_RESISTANCE_TERRIBLE           , "(Terrible)");
		add(TOOLTIP_BLAST_RESISTANCE_GHAST              , "(Ghast Proof)");
		add(TOOLTIP_BLAST_RESISTANCE_CREEPER            , "(Creeper Proof)");
		add(TOOLTIP_BLAST_RESISTANCE_TNT                , "(TNT Proof)");
		add(TOOLTIP_BLAST_RESISTANCE_DYNAMITE           , "(Strong Dynamite Proof)");
		add(TOOLTIP_BLAST_RESISTANCE_NOT_NUKE           , "(IC2 Nukes still go right through!)");
		add(TOOLTIP_BETWEENLANDS_RESISTANCE             , "Resistant to the Effects of the Betweenlands");
		add(PROSPECTING_LAVA                            , "There is Lava behind this Rock.");
		add(PROSPECTING_LIQUID                          , "There is a Liquid behind this Rock.");
		add(PROSPECTING_AIR                             , "There is an Air Pocket behind this Rock.");
		add(PROSPECTING_CHANGE                          , "Material is changing behind this Rock.");
		add(PROSPECTING_TRACES                          , "Found traces of ");
		add(PROSPECTING_NOTHING                         , "No traces of Ore found.");
		add(AUTOCRAFTING_INSERT_BLUEPRINT               , "Insert an autocraftable Blueprint here");
		add(ADVCRAFTING_INSERT_BLUEPRINT                , "Insert a Blueprint here");
		add(ADVCRAFTING_PUT_TO_STORAGE                  , "Move Crafting Grid content to Storage Slots");
		add(ADVCRAFTING_AUTOMATION_ACCESS               , "Allow Automation to extract from the Crafting Grid");
		add(ADVCRAFTING_NEUTRAL_SLOT                    , "This Slot does ABSOLUTELY NOTHING to its content");
		add(ADVCRAFTING_DROP_SLOT                       , "Automation can extract Items you drop into this Slot");
		add(TIME_TICK                                   , "Tick");
		add(TIME_SEC                                    , "Second");
		add(TIME_MIN                                    , "Minute");
		add(TIME_HOUR                                   , "Hour");
		add(TIME_DAY                                    , "Day");
		add(TIME_WEEK                                   , "Week");
		add(TIME_TICKS                                  , "Ticks");
		add(TIME_SECS                                   , "Seconds");
		add(TIME_MINS                                   , "Minutes");
		add(TIME_HOURS                                  , "Hours");
		add(TIME_DAYS                                   , "Days");
		add(TIME_WEEKS                                  , "Weeks");
		add(ADMIN_ONLY_CREATION                         , "Admins have to spawn this in. (or you MineTweaker a Recipe in)");
		add(WIP                                         , Chat.RESET + Chat.WHITE + Chat.BOLD + "WIP" + Chat.RESET_TOOLTIP + ", This may not be as functional as you expect it to be!");
	}

	public static class Chat {
		public static final String
		  BLACK = EnumChatFormatting.BLACK.toString()
		, DBLUE = EnumChatFormatting.DARK_BLUE.toString()
		, DGREEN = EnumChatFormatting.DARK_GREEN.toString()
		, DCYAN = EnumChatFormatting.DARK_AQUA.toString()
		, DRED = EnumChatFormatting.DARK_RED.toString()
		, PURPLE = EnumChatFormatting.DARK_PURPLE.toString()
		, ORANGE = EnumChatFormatting.GOLD.toString()
		, GOLD = EnumChatFormatting.GOLD.toString()
		, GRAY = EnumChatFormatting.GRAY.toString()
		, DGRAY = EnumChatFormatting.DARK_GRAY.toString()
		, BLUE = EnumChatFormatting.BLUE.toString()
		, GREEN = EnumChatFormatting.GREEN.toString()
		, CYAN = EnumChatFormatting.AQUA.toString()
		, RED = EnumChatFormatting.RED.toString()
		, PINK = EnumChatFormatting.LIGHT_PURPLE.toString()
		, YELLOW = EnumChatFormatting.YELLOW.toString()
		, WHITE = EnumChatFormatting.WHITE.toString()
		, OBFUSCATED = EnumChatFormatting.OBFUSCATED.toString()
		, BOLD = EnumChatFormatting.BOLD.toString()
		, STRIKETHROUGH = EnumChatFormatting.STRIKETHROUGH.toString()
		, UNDERLINE = EnumChatFormatting.UNDERLINE.toString()
		, ITALIC = EnumChatFormatting.ITALIC.toString()
		, RESET = EnumChatFormatting.RESET.toString()
		, RESET_TOOLTIP = RESET + GRAY
		;

		public static String
		  RAINBOW_FAST = BLACK
		, RAINBOW = BLACK
		, RAINBOW_SLOW = BLACK
		, BLINKING_CYAN = CYAN
		, BLINKING_RED = RED
		;
	}
}
