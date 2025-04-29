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

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.sand;

import com.shatteredpixel.shatteredpixeldungeon.sprites.newsprite.sand.ArmorBeetleSprite;
import com.watabou.utils.Random;

public class ArmorBeetle extends Beetle {

	{
		spriteClass = ArmorBeetleSprite.class;
		HP=HT=15;
		baseSpeed=0.8f;
	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange(2,8);
	}

	@Override
	public void damage(int dmg, Object src) {
		dmg*=0.5f;
		super.damage(dmg, src);
	}
}