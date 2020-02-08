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

package gregapi6.item.multiitem.behaviors;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi6.data.LH;
import gregapi6.item.multiitem.MultiItem;
import gregapi6.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi6.util.ST;
import gregapi6.util.UT;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Behavior_WrittenBook extends AbstractBehaviorDefault {
	public static final Behavior_WrittenBook INSTANCE = new Behavior_WrittenBook();
	
	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack onItemRightClick(MultiItem aItem, ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
		String aMapping = UT.NBT.getBookMapping(aStack);
		if (UT.Code.stringValid(aMapping)) {
			aPlayer.displayGUIBook(UT.Books.getWrittenBook(aMapping, ST.make(Items.written_book, 1, 0)));
		} else {
			if (UT.Code.stringValid(UT.NBT.getBookTitle(aStack))) aPlayer.displayGUIBook(ST.make(Items.written_book, 1, 0, aStack.getTagCompound()));
		}
		return super.onItemRightClick(aItem, aStack, aWorld, aPlayer);
	}
	
	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		String tTitle = UT.NBT.getBookTitle(aStack);
		if (UT.Code.stringValid(tTitle)) {
			aList.add(LH.Chat.CYAN + tTitle);
			aList.add(LH.Chat.CYAN + "by " + UT.NBT.getBookAuthor(aStack));
		} else {
			aList.add(LH.Chat.CYAN + "This Book is Empty");
		}
		return aList;
	}
}
