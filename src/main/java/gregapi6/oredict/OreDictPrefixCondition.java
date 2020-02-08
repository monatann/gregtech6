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

package gregapi6.oredict;

import static gregapi6.data.CS.*;

import gregapi6.code.ICondition;
import gregapi6.code.TagData;

/**
 * @author Gregorius Techneticies
 * 
 * A Collection of Classes, which check for certain Prefix Conditions.
 */
public class OreDictPrefixCondition {
	public static ICondition<OreDictPrefix> tag     (TagData... aTags)      {return new TagDataContainsAll(aTags);}
	public static ICondition<OreDictPrefix> tagnor  (TagData... aTags)      {return new TagDataContainsNone(aTags);}
	
	private static class TagDataContainsAll implements ICondition<OreDictPrefix> {
		private final TagData[] mTags;
		public TagDataContainsAll(TagData... aTags) {mTags = aTags;}
		@Override public boolean isTrue(OreDictPrefix aPrefix) {return aPrefix.containsAll(mTags);}
	}
	
	private static class TagDataContainsNone implements ICondition<OreDictPrefix> {
		private final TagData[] mTags;
		public TagDataContainsNone(TagData... aTags) {mTags = aTags;}
		@Override public boolean isTrue(OreDictPrefix aPrefix) {for (TagData tTag : mTags) if (aPrefix.contains(tTag)) return F; return T;}
	}
}
