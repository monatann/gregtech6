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

package gregapi6.old;

import static gregapi6.data.CS.*;

import java.util.List;

import gregapi6.data.CS.OreDictToolNames;
import gregapi6.lang.LanguageHandler;
import gregapi6.util.OM;
import gregapi6.util.ST;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GT_SolderingTool_Item extends GT_Tool_Item {
	public GT_SolderingTool_Item(String aUnlocalized, String aEnglish, int aMaxDamage, int aEntityDamage, int aDischargedGTID) {
		super(aUnlocalized, aEnglish, "To repair and construct Circuitry", aMaxDamage, aEntityDamage, true, -1, aDischargedGTID);
		OM.reg(OreDictToolNames.solderingiron, ST.make(this, 1, W));
//      GregTech_API.registerSolderingTool(ST.make(this, 1, W));
//      setCraftingSound(GregTech_API.sSoundList.get(103));
//      setBreakingSound(GregTech_API.sSoundList.get(103));
//      setEntityHitSound(GregTech_API.sSoundList.get(103));
//      setUsageAmounts(1, 1, 1);
	}
	
	@Override
	public void addAdditionalToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(LanguageHandler.get(getUnlocalizedName() + ".tooltip_1", "Sets the Strength of outputted Redstone"));
		aList.add(LanguageHandler.get(getUnlocalizedName() + ".tooltip_2", "Needs Soldering Metal in Inventory!"));
	}
	
	@Override
	public boolean onItemUseFirst(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float hitX, float hitY, float hitZ) {
		super.onItemUseFirst(aStack, aPlayer, aWorld, aX, aY, aZ, aSide, hitX, hitY, hitZ);
		if (aWorld.isRemote) {
			return false;
		}
		return false;
	}
}
