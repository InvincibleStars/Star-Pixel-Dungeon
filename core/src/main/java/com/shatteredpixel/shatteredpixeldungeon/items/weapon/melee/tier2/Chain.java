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

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Bleeding;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.BuffWait;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Cripple;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.Random;

public class Chain extends MeleeWeapon {

	{
		image = ItemSpriteSheet.CHAIN;

		tier = 2;
		RCH = 3;    //lots of extra reach
		DLY = 1.5f; //DLY计算公式：1/DLY=攻击速度（一回合X次）
	}


	@Override
	public int min(int lvl) {
		return tier + 2 +					//基础
				lvl +						//成长
				(masteryPotionBonus*2);		//附加
	}

	@Override
	public int max(int lvl) {
		return Math.round(9.5f*(tier+1))+lvl*(tier+1)+masteryPotionBonus*2;   	//附加
	}



	@Override
	public int proc(Char attacker, Char defender, int damage ) {
		if (Random.Int(4) == 0) {
			Buff.affect(defender, Bleeding.class).set(Math.round(damage * 0.2f));
		}
		else if(Random.Int(4) == 1) {
			Buff.prolong(attacker, Cripple.class, BuffWait.T5);
		}
		return super.proc(attacker, defender, damage);
	}

}