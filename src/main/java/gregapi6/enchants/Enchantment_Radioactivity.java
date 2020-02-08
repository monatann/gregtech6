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

package gregapi6.enchants;

import gregapi6.config.Config;
import gregapi6.config.ConfigCategories;
import gregapi6.data.LH;
import gregapi6.data.MT;
import gregapi6.util.UT;
import net.minecraft.enchantment.EnchantmentDamage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class Enchantment_Radioactivity extends EnchantmentDamage {
	public static Enchantment_Radioactivity INSTANCE;
	
	public Enchantment_Radioactivity() {
		super(Config.addIDConfig(ConfigCategories.IDs.enchantments, "Radioactivity", 14), 0, -1);
		LH.add(getName(), "Radioactivity");
		MT.Cyanite          .addEnchantmentForTools(this, 1).addEnchantmentForArmors(this, 1);
		MT.Yellorium        .addEnchantmentForTools(this, 1).addEnchantmentForArmors(this, 1);
		MT.Blutonium        .addEnchantmentForTools(this, 2).addEnchantmentForArmors(this, 2);
		MT.Ludicrite        .addEnchantmentForTools(this, 2).addEnchantmentForArmors(this, 4);
		MT.Pu               .addEnchantmentForTools(this, 1).addEnchantmentForArmors(this, 1);
		MT.U_235            .addEnchantmentForTools(this, 2).addEnchantmentForArmors(this, 2);
		MT.Co_60            .addEnchantmentForTools(this, 2).addEnchantmentForArmors(this, 2);
		MT.Pu_241           .addEnchantmentForTools(this, 3).addEnchantmentForArmors(this, 3);
		MT.Pu_243           .addEnchantmentForTools(this, 3).addEnchantmentForArmors(this, 3);
		MT.Am_241           .addEnchantmentForTools(this, 4).addEnchantmentForArmors(this, 4);
		MT.Nq_528           .addEnchantmentForTools(this, 4).addEnchantmentForArmors(this, 4);
		MT.Nq_522           .addEnchantmentForTools(this, 5).addEnchantmentForArmors(this, 5);
		MT.CosmicNeutronium .addEnchantmentForTools(this,10).addEnchantmentForArmors(this,10);
		INSTANCE = this;
	}
	
	@Override
	public int getMinEnchantability(int aLevel) {
		return Integer.MAX_VALUE;
	}
	
	@Override
	public int getMaxEnchantability(int aLevel) {
		return 0;
	}
	
	@Override
	public int getMaxLevel() {
		return 5;
	}
	
	@Override
	public boolean canApply(ItemStack par1ItemStack) {
		return false;
	}
	
	@Override
	public boolean isAllowedOnBooks() {
		return false;
	}
	
	@Override
	public void func_151367_b(EntityLivingBase aHurtEntity, Entity aDamagingEntity, int aLevel) {
		UT.Entities.applyRadioactivity(aHurtEntity, aLevel, 1);
	}
	
	@Override
	public String getName() {
		return "enchantment.damage.radioactivity";
	}
}
