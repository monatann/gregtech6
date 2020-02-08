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

import gregapi6.oredict.OreDictManager;

/**
 * @author Gregorius Techneticies
 */
public enum OD {
	  craftingWorkBench
	, craftingFurnace
	, craftingChest
	, craftingAnvil
	, craftingBook
	, craftingFeather
	, craftingFur
	, craftingHardenedClay
	, craftingLeather
	, craftingPistonIngot
	, craftingPistonGlue
	, craftingPiston
	, craftingRedstoneTorch
	, craftingQuartz
	, craftingFirestarter
	, craftingWireCopper
	, craftingWireGold
	, craftingWireIron
	, craftingWireTin
	, craftingDuctTape
	, listAllpropolis
	, listAllmushroom
	, container1000water
	, container250water
	, container1000lava
	, container250lava
	, container1000milk
	, container250milk
	, container1000soymilk
	, container250soymilk
	, container1000honey
	, container250honey
	, container1000creosote
	, container250creosote
	, container1000rubbertreesap
	, container250rubbertreesap
	, container1000spruceresin
	, container250spruceresin
	, container1000rainbowsap
	, container250rainbowsap
	, container1000maplesap
	, container250maplesap
	, container1000latex
	, container250latex
	, container1000lubricant
	, container250lubricant
	, container1000glue
	, container250glue
	, enderChest
	, pestleAndMortar
	, materialPressedwax
	, materialWaxcomb
	, materialHoneycomb
	, plankSkyroot
	, plankWeedwood
	, plankAnyWood
	, plankWood
	, paperEmpty
	, stairWood
	, slabWood
	, beamWood
	, logWood
	, logRubber
	, flower
	, bamboo
	, record
	, beeComb
	, beeCombCrossbred
	, hardenedClay
	, fiberCarbon
	, slimeball
	, slimeballPink
	, slimeballRice
	, slimeballSwet
	, itemGrassTall
	, itemGrass
	, itemGrassDry
	, itemGrassMoldy
	, itemGrassRotten
	, baleGrass
	, baleGrassDry
	, baleGrassMoldy
	, baleGrassRotten
	, itemKey
	, itemMud
	, itemTar
	, itemMoss
	, itemSlag
	, itemGlue
	, itemBarkDry
	, itemLubricant
	, itemLubricantEarly
	, itemResin
	, itemRubber
	, itemSalt
	, itemFlint
	, itemPearl
	, itemString
	, itemCompass
	, itemRedstone
	, itemCompressedCarbon
	, itemClay
	, itemFeather
	, itemLeather
	, itemLeatherTreated
	, itemLeatherHardened
	, itemSkin
	, itemFur
	, itemPelt
	, itemFertilizer
	, itemPlantRemains
	, itemGhastTear
	, itemSilicon
	, itemCertusQuartz
	, itemNetherQuartz
	, fruitBait
	, grainBait
	, veggieBait
	, fishtrapBait
	;
	
	OD() {
		OreDictManager.INSTANCE.addKnownName(name());
	}
}
