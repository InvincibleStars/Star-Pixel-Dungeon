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

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.tier2;

import static com.shatteredpixel.shatteredpixeldungeon.Dungeon.hero;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class KnifeAoe extends MeleeWeapon {

	{
		image = ItemSpriteSheet.DIRK;
		hitSound = Assets.Sounds.HIT_STAB;
		hitSoundPitch = 1f;

		tier = 2;
		RCH=2;
	}

	@Override
	public int min(int lvl) {
		return tier + lvl + masteryPotionBonus;		//附加
	}

	@Override
	public int max(int lvl) {
		return  2*(tier+1) + lvl * tier + masteryPotionBonus * 2 + 2;   	//附加
	}


	@Override
	public int damageRoll(Char owner) {
		return super.damageRoll(owner);
	}


	@Override
	public int proc(Char attacker, Char defender, int damage ) {
		Char ch;
		int dam =Random.Int(min()/2,max()/2);

		for( int i: PathFinder.NEIGHBOURS8){
			if ((ch = Char.findChar(hero.pos +i))!= null){
				ch.damage(dam, this);
			}
		}
		return super.proc(attacker, defender, damage);
	}



}