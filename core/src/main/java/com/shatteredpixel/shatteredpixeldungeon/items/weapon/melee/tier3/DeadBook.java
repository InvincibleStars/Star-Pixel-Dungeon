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

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.tier3;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.BuffWait;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Poison;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Vertigo;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Grim;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.Random;

public class DeadBook extends MeleeWeapon {

	{
		image = ItemSpriteSheet.DEADBOOK;
		hitSound = Assets.Sounds.HIT_SLASH;
		hitSoundPitch = 1.1f;

		tier = 3;

	}

	@Override
	public int max(int lvl) {
		return Math.round(2f * (tier * 1)) +
				lvl * Math.round(1f + (tier * 2)) + 1;
	}



	@Override
	public int proc(Char attacker, Char defender, int damage) {
		switch (Random.NormalIntRange(0, 4)) {

			case 2: //死神效果
				int dmg;
				dmg = (new Grim()).proc(this, attacker, defender, damage);
				damage = dmg;
				break;
			case 3: //中毒效果
				Buff.affect(defender, Poison.class).set(3);
				break;
			case 4: //击晕（不是麻痹）
				Buff.affect(defender, Vertigo.class, BuffWait.T6);
				break;
			default://空掷
				break;
		}
		return super.proc(attacker, defender, damage);
	}
	}