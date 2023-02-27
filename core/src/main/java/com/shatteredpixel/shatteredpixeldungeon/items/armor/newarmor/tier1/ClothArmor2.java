/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2021 Evan Debenham
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.shatteredpixel.shatteredpixeldungeon.items.armor.newarmor.tier1;

import com.shatteredpixel.shatteredpixeldungeon.Challenges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.sun.tools.sjavac.comp.dependencies.PublicApiCollector;

public class ClothArmor2 extends Armor {

	{
		image = ItemSpriteSheet.ARMOR_CLOTH;

		bones = false; //Finding them in bones would be semi-frequent and disappointing.
	}

	public ClothArmor2() {
		super( 1 );
	}

	/*@Override*/
	public int STRReq(int lvl) {
		return (8 + Math.round(tier * 2)) - (int)(Math.sqrt(8 * lvl + 1) - 1)/2 - 100;
	}//*/

	public int DRMax(int lvl){
		//先判断是否开启了信念护体挑战
		if (Dungeon.isChallenged(Challenges.NO_ARMOR)){
			return 1 + tier + lvl + augment.defenseFactor(lvl);
		}

		int max = 1 + tier * (2 + lvl) + augment.defenseFactor(lvl) + 1000;
		if (lvl > max){
			return ((lvl - max)+1)/2 + 1000;
		} else {
			return max;
		}
	}

	public int DRMin(int lvl){
		//先判断是否开启了信念护体挑战
		if (Dungeon.isChallenged(Challenges.NO_ARMOR)){
			return 0;
		}

		int max = DRMax(lvl);
		if (lvl >= max){
			return (lvl - max) + 1000;
		} else {
			return lvl + 1000;
		}
	}

}
