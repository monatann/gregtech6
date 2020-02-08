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

package gregtech6.items;

import static gregapi6.data.CS.*;

import java.util.List;
import java.util.Random;

import gregapi6.code.ItemStackContainer;
import gregapi6.data.CS.BooksGT;
import gregapi6.data.IL;
import gregapi6.data.LH;
import gregapi6.data.MD;
import gregapi6.data.MT;
import gregapi6.data.OD;
import gregapi6.data.TC;
import gregapi6.data.TD;
import gregapi6.item.CreativeTab;
import gregapi6.item.multiitem.MultiItemRandom;
import gregapi6.oredict.OreDictItemData;
import gregapi6.util.CR;
import gregapi6.util.OM;
import gregapi6.util.ST;
import gregapi6.util.UT;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraftforge.common.ChestGenHooks;

public class MultiItemBooks extends MultiItemRandom {
	public MultiItemBooks() {
		super(MD.GT.mID, "gt6.multiitem.books");
		OM.reg(OD.craftingBook, ST.make(this, 1, W));
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(this, 1, W), (byte)3);
		setCreativeTab(new CreativeTab(getUnlocalizedName(), "GregTech: Books", this, (short)32000));
	}
	
	@Override
	public void addItems() {
		@SuppressWarnings("unused")
		int tLastID = 0;
		for (int i = 0; i < 8; i++) {
			BooksGT.BOOK_REGISTER.put(new ItemStackContainer(addItem(tLastID =      i, "Book"       , "", "bookWritten", TC.stack(TC.COGNITO, 2), TICKS_PER_SMELT  , new OreDictItemData(MT.Paper, U * 3))), (byte)(3+i));
			BooksGT.BOOK_REGISTER.put(new ItemStackContainer(addItem(tLastID = 1000+i, "Large Book" , "", "bookWritten", TC.stack(TC.COGNITO, 4), TICKS_PER_SMELT*2, new OreDictItemData(MT.Paper, U * 6))), (byte)(3+i));
		}
		
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(addItem(tLastID = 32000, "Book"                    , "With a Bronze Emblem on it"      , "bookWritten", TD.Creative.HIDDEN, TC.stack(TC.COGNITO, 2), TICKS_PER_SMELT  , new OreDictItemData(MT.Paper, U * 3, MT.Bronze, U9))), (byte)12);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(addItem(tLastID = 32001, "Large Book"              , "With a Bronze Emblem on it"      , "bookWritten", TD.Creative.HIDDEN, TC.stack(TC.COGNITO, 4), TICKS_PER_SMELT*2, new OreDictItemData(MT.Paper, U * 6, MT.Bronze, U9))), (byte)12);
		
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(addItem(tLastID = 32002, "Material Dictionary"     , "Contains Data about a Material"  , "bookWritten", TD.Creative.HIDDEN, TC.stack(TC.COGNITO, 2), TICKS_PER_SMELT  , new OreDictItemData(MT.Paper, U * 3))), (byte)11);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(addItem(tLastID = 32003, "Material Dictionary"     , "Contains Data about a Material"  , "bookWritten", TD.Creative.HIDDEN, TC.stack(TC.COGNITO, 4), TICKS_PER_SMELT*2, new OreDictItemData(MT.Paper, U * 6))), (byte)11);
		
		CR.shapeless(ST.make(this, 1,     0), CR.DEF_NCC | CR.KEEPNBT, new Object[] {IL.Paper_Printed_Pages.get(1), OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Black]});
		CR.shapeless(ST.make(this, 1,     1), CR.DEF_NCC | CR.KEEPNBT, new Object[] {IL.Paper_Printed_Pages.get(1), OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_White]});
		CR.shapeless(ST.make(this, 1,     2), CR.DEF_NCC | CR.KEEPNBT, new Object[] {IL.Paper_Printed_Pages.get(1), OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Red]});
		CR.shapeless(ST.make(this, 1,     3), CR.DEF_NCC | CR.KEEPNBT, new Object[] {IL.Paper_Printed_Pages.get(1), OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Green]});
		CR.shapeless(ST.make(this, 1,     4), CR.DEF_NCC | CR.KEEPNBT, new Object[] {IL.Paper_Printed_Pages.get(1), OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Blue]});
		CR.shapeless(ST.make(this, 1,     5), CR.DEF_NCC | CR.KEEPNBT, new Object[] {IL.Paper_Printed_Pages.get(1), OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Cyan]});
		CR.shapeless(ST.make(this, 1,     6), CR.DEF_NCC | CR.KEEPNBT, new Object[] {IL.Paper_Printed_Pages.get(1), OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Magenta]});
		CR.shapeless(ST.make(this, 1,     7), CR.DEF_NCC | CR.KEEPNBT, new Object[] {IL.Paper_Printed_Pages.get(1), OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Yellow]});
		
		CR.shapeless(ST.make(this, 1,  1000), CR.DEF_NCC | CR.KEEPNBT, new Object[] {IL.Paper_Printed_Pages_Many.get(1), OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Black]});
		CR.shapeless(ST.make(this, 1,  1001), CR.DEF_NCC | CR.KEEPNBT, new Object[] {IL.Paper_Printed_Pages_Many.get(1), OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_White]});
		CR.shapeless(ST.make(this, 1,  1002), CR.DEF_NCC | CR.KEEPNBT, new Object[] {IL.Paper_Printed_Pages_Many.get(1), OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Red]});
		CR.shapeless(ST.make(this, 1,  1003), CR.DEF_NCC | CR.KEEPNBT, new Object[] {IL.Paper_Printed_Pages_Many.get(1), OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Green]});
		CR.shapeless(ST.make(this, 1,  1004), CR.DEF_NCC | CR.KEEPNBT, new Object[] {IL.Paper_Printed_Pages_Many.get(1), OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Blue]});
		CR.shapeless(ST.make(this, 1,  1005), CR.DEF_NCC | CR.KEEPNBT, new Object[] {IL.Paper_Printed_Pages_Many.get(1), OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Cyan]});
		CR.shapeless(ST.make(this, 1,  1006), CR.DEF_NCC | CR.KEEPNBT, new Object[] {IL.Paper_Printed_Pages_Many.get(1), OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Magenta]});
		CR.shapeless(ST.make(this, 1,  1007), CR.DEF_NCC | CR.KEEPNBT, new Object[] {IL.Paper_Printed_Pages_Many.get(1), OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Yellow]});
		
		CR.shapeless(ST.make(this, 1,     0), CR.DEF_NCC, new Object[] {OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Black]});
		CR.shapeless(ST.make(this, 1,     1), CR.DEF_NCC, new Object[] {OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_White]});
		CR.shapeless(ST.make(this, 1,     2), CR.DEF_NCC, new Object[] {OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Red]});
		CR.shapeless(ST.make(this, 1,     3), CR.DEF_NCC, new Object[] {OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Green]});
		CR.shapeless(ST.make(this, 1,     4), CR.DEF_NCC, new Object[] {OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Blue]});
		CR.shapeless(ST.make(this, 1,     5), CR.DEF_NCC, new Object[] {OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Cyan]});
		CR.shapeless(ST.make(this, 1,     6), CR.DEF_NCC, new Object[] {OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Magenta]});
		CR.shapeless(ST.make(this, 1,     7), CR.DEF_NCC, new Object[] {OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Yellow]});
		
		CR.shapeless(ST.make(this, 1,  1000), CR.DEF_NCC, new Object[] {OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Black]});
		CR.shapeless(ST.make(this, 1,  1001), CR.DEF_NCC, new Object[] {OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_White]});
		CR.shapeless(ST.make(this, 1,  1002), CR.DEF_NCC, new Object[] {OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Red]});
		CR.shapeless(ST.make(this, 1,  1003), CR.DEF_NCC, new Object[] {OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Green]});
		CR.shapeless(ST.make(this, 1,  1004), CR.DEF_NCC, new Object[] {OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Blue]});
		CR.shapeless(ST.make(this, 1,  1005), CR.DEF_NCC, new Object[] {OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Cyan]});
		CR.shapeless(ST.make(this, 1,  1006), CR.DEF_NCC, new Object[] {OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Magenta]});
		CR.shapeless(ST.make(this, 1,  1007), CR.DEF_NCC, new Object[] {OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Yellow]});
	}
	
	@Override
	public WeightedRandomChestContent getChestGenBase(ChestGenHooks aChestGenHook, Random aRandom, WeightedRandomChestContent aOriginal) {
		if (aOriginal.theItemId.hasTagCompound()) return aOriginal;
		if (ST.meta_(aOriginal.theItemId) == 32002 || ST.meta_(aOriginal.theItemId) == 32003) return new WeightedRandomChestContent(ST.book(UT.Books.MATERIAL_DICTIONARIES.get(aRandom.nextInt(UT.Books.MATERIAL_DICTIONARIES.size()))), aOriginal.theMinimumChanceToGenerateItem, aOriginal.theMaximumChanceToGenerateItem, aOriginal.itemWeight);
		return aOriginal;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
		String aMapping = UT.NBT.getBookMapping(aStack);
		if (UT.Code.stringValid(aMapping)) {
			aPlayer.displayGUIBook(UT.Books.getWrittenBook(aMapping, ST.make(Items.written_book, 1, 0)));
		} else {
			if (UT.Code.stringValid(UT.NBT.getBookTitle(aStack))) aPlayer.displayGUIBook(ST.make(Items.written_book, 1, 0, aStack.getTagCompound()));
		}
		return super.onItemRightClick(aStack, aWorld, aPlayer);
	}
	
	@Override
	public void addAdditionalToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		super.addAdditionalToolTips(aList, aStack, aF3_H);
		String tTitle = UT.NBT.getBookTitle(aStack);
		if (UT.Code.stringValid(tTitle)) {
			aList.add(LH.Chat.CYAN + tTitle);
			aList.add(LH.Chat.CYAN + "by " + UT.NBT.getBookAuthor(aStack));
		} else {
			aList.add(LH.Chat.CYAN + "This Book is Empty");
		}
	}
}
