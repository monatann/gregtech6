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

package gregtech6.items.tools.electric;

import static gregapi6.data.CS.*;

import gregapi6.data.CS.SFX;
import gregapi6.item.multiitem.MultiItemTool;
import gregapi6.item.multiitem.behaviors.Behavior_Switch_Metadata;
import gregapi6.item.multiitem.behaviors.Behavior_Tool;

public class GT_Tool_MonkeyWrench_LV extends GT_Tool_Wrench_LV {
	
	public GT_Tool_MonkeyWrench_LV(int aSwitchIndex) {
		super(aSwitchIndex);
	}
	
	@Override
	public void onStatsAddedToTool(MultiItemTool aItem, int aID) {
		aItem.addItemBehavior(aID, new Behavior_Tool(TOOL_monkeywrench, SFX.GT_WRENCH, 100, !canBlock()));
		aItem.addItemBehavior(aID, new Behavior_Switch_Metadata(mSwitchIndex));
	}
}
