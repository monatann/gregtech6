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

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi6.data.MD;
import gregapi6.old.Textures;
import gregapi6.util.ST;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

/**
 * @author Gregorius Techneticies
 */
public class ItemEmptySlot extends ItemBase {
	public ItemEmptySlot(String aUnlocalized) {
		super(MD.GAPI.mID, aUnlocalized, "Empty Slot", "This Slot has to be left Empty");
		ST.hide(this);
	}
	
	@Override
	public IIcon getIconFromDamage(int aMeta) {
		return Textures.ItemIcons.VOID.getIcon(0);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister aIconRegister) {
		// No Icons to register!
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public final void getSubItems(Item var1, CreativeTabs aCreativeTab, @SuppressWarnings("rawtypes") List aList) {
		//
	}
}
