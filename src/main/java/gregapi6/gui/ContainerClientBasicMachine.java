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

package gregapi6.gui;

import static gregapi6.data.CS.*;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi6.data.LH;
import gregapi6.recipes.Recipe.RecipeMap;
import gregapi6.tileentity.ITileEntityInventoryGUI;
import gregapi6.util.UT;
import net.minecraft.entity.player.InventoryPlayer;

@SideOnly(Side.CLIENT)
public class ContainerClientBasicMachine extends ContainerClient {
	private RecipeMap mRecipes;
	
	public ContainerClientBasicMachine(InventoryPlayer aInventoryPlayer, ITileEntityInventoryGUI aTileEntity, RecipeMap aRecipes, int aGUIID, String aGUITexture) {
		super(new ContainerCommonBasicMachine(aInventoryPlayer, aTileEntity, aRecipes, aGUIID), aGUITexture);
		mRecipes = aRecipes;
		mNEI = mRecipes.mNameNEI;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		fontRendererObj.drawString(mContainer.mTileEntity.hasCustomInventoryNameGUI()?mContainer.mTileEntity.getInventoryNameGUI():LH.get(mRecipes.mNameInternal), 8,  4, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer2(float par1, int par2, int par3) {
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
		if (mContainer instanceof ContainerCommonBasicMachine) {
			if (((ContainerCommonBasicMachine)mContainer).mProgressBar >= 0) {
				int tSize = (mRecipes.mProgressBarDirection % 4 < 2 ? 20 : 18), tProgress = (int)UT.Code.scale(((ContainerCommonBasicMachine)mContainer).mProgressBar, 0, Short.MAX_VALUE, tSize * UT.Code.unsignB(mRecipes.mProgressBarAmount), F) % (tSize+1);
				
				switch (mRecipes.mProgressBarDirection) {
				case 0:                             drawTexturedModalRect(x + 78                    , y + 24                    , 176                   , 0                 , tProgress , 18        ); break;
				case 1:                             drawTexturedModalRect(x + 78 + 20 - tProgress   , y + 24                    , 176 + 20 - tProgress  , 0                 , tProgress , 18        ); break;
				case 2:                             drawTexturedModalRect(x + 78                    , y + 24                    , 176                   , 0                 , 20        , tProgress ); break;
				case 3:                             drawTexturedModalRect(x + 78                    , y + 24 + 18 - tProgress   , 176                   , 18 - tProgress    , 20        , tProgress ); break;
				case 4: tProgress = 20 - tProgress; drawTexturedModalRect(x + 78                    , y + 24                    , 176                   , 0                 , tProgress , 18        ); break;
				case 5: tProgress = 20 - tProgress; drawTexturedModalRect(x + 78 + 20 - tProgress   , y + 24                    , 176 + 20 - tProgress  , 0                 , tProgress , 18        ); break;
				case 6: tProgress = 18 - tProgress; drawTexturedModalRect(x + 78                    , y + 24                    , 176                   , 0                 , 20        , tProgress ); break;
				case 7: tProgress = 18 - tProgress; drawTexturedModalRect(x + 78                    , y + 24 + 18 - tProgress   , 176                   , 18 - tProgress    , 20        , tProgress ); break;
				}
			}
		}
	}
}
