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

import gregapi6.render.ITexture;
import gregtech6.tileentity.energy.reactors.MultiTileEntityReactorCore;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public interface IItemReactorRod {
	/** @return if a Reactor would accept this Item as a Part of it. */
	public boolean  isReactorRod(ItemStack aStack);
	/** @return the amount of Neutrons emitted by the Rod to each of the 4 surrounding Rods. */
	public int      getReactorRodNeutronEmission  (MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack);
	/** @return if the Reactor would be considered "Active" after calculating the Ticking of the Rod itself. */
	public boolean  getReactorRodNeutronReaction  (MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack);
	/** @return the Amount of reflected Neutrons after this Rod has been hit with aNeutrons. */
	public int      getReactorRodNeutronReflection(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack, int aNeutrons);
	/** @return the Texture used for rendering the Rod in a Reactor. Note: only Item ID and Metadata are available in aStack, not NBT! */
	public ITexture getReactorRodTextureSides     (MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack, boolean aActive);
	/** @return the Texture used for rendering the Rod in a Reactor. Note: only Item ID and Metadata are available in aStack, not NBT! */
	public ITexture getReactorRodTextureTop       (MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack, boolean aActive);
}
