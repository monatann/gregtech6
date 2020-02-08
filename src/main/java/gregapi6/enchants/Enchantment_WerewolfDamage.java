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

import static gregapi6.data.CS.*;

import gregapi6.config.Config;
import gregapi6.config.ConfigCategories;
import gregapi6.data.CS.SFX;
import gregapi6.data.LH;
import gregapi6.data.MT;
import gregapi6.util.ST;
import gregapi6.util.UT;
import net.minecraft.enchantment.EnchantmentDamage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

/**
 * @author Gregorius Techneticies
 */
public class Enchantment_WerewolfDamage extends EnchantmentDamage {
	public static Enchantment_WerewolfDamage INSTANCE;
	
	public Enchantment_WerewolfDamage() {
		super(Config.addIDConfig(ConfigCategories.IDs.enchantments, "Werebane", 12), 2, -1);
		LH.add(getName(), "Werebane");
		MT.Ir               .addEnchantmentForTools(this, 6);
		MT.Osmiridium       .addEnchantmentForTools(this, 6);
		MT.HSSS             .addEnchantmentForTools(this, 6);
		MT.Ag               .addEnchantmentForTools(this, 4);
		MT.Electrum         .addEnchantmentForTools(this, 3);
		MT.BlackBronze      .addEnchantmentForTools(this, 2);
		MT.BlackSteel       .addEnchantmentForTools(this, 2);
		MT.RedSteel         .addEnchantmentForTools(this, 3);
		MT.BlueSteel        .addEnchantmentForTools(this, 1);
		MT.SterlingSilver   .addEnchantmentForTools(this, 4);
		MT.AstralSilver     .addEnchantmentForTools(this, 5);
		MT.VibraniumSilver  .addEnchantmentForTools(this,10);
		MT.Craponite        .addEnchantmentForTools(this,10);
		MT.Tc               .addEnchantmentForTools(this,10);
		MT.Infinity         .addEnchantmentForTools(this,10);
		MT.DiamondPink      .addEnchantmentForTools(this,10);
		INSTANCE = this;
	}
	
	@Override
	public int getMinEnchantability(int aLevel) {
		return 5 + (aLevel - 1) * 8;
	}
	
	@Override
	public int getMaxEnchantability(int aLevel) {
		return this.getMinEnchantability(aLevel) + 20;
	}
	
	@Override
	public int getMaxLevel() {
		return 5;
	}
	
	@Override
	public void func_151367_b(EntityLivingBase aHurtEntity, Entity aDamagingEntity, int aLevel) {
		if (UT.Entities.isWereCreature(aHurtEntity)) {
			// Anti Bear Damage now works through the Quantum Suit too, just in a different way. XD
			if (!aHurtEntity.worldObj.isRemote && aHurtEntity instanceof EntityPlayer && "Bear989Sr".equalsIgnoreCase(aHurtEntity.getCommandSenderName())) {
				UT.Sounds.send(SFX.MC_FIREWORK_LARGE, aHurtEntity);
				for (int i = -1; i < aLevel; i++) {
					int tSlot = RNGSUS.nextInt(((EntityPlayer)aHurtEntity).inventory.mainInventory.length);
					ItemStack tStack = ((EntityPlayer)aHurtEntity).inventory.mainInventory[tSlot];
					if (ST.valid(tStack)) {
						EntityItem tEntity = ST.drop(aHurtEntity, ST.copy_(tStack));
						if (tEntity != null) {
							tEntity.delayBeforeCanPickup = 40;
							((EntityPlayer)aHurtEntity).inventory.mainInventory[tSlot] = null;
						}
					}
				}
			}
			aHurtEntity.addPotionEffect(new PotionEffect(Potion.wither.id, aLevel * 200, (int)UT.Code.bind(1, 5, (10*aLevel) / 7)));
			aHurtEntity.addPotionEffect(new PotionEffect(Potion.poison.id, aLevel * 200, (int)UT.Code.bind(1, 5, (10*aLevel) / 7)));
		}
	}
	
	@Override
	public String getName() {
		return "enchantment.damage.werewolf";
	}
}
