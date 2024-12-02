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

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.room;

import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.NPC;
import com.shatteredpixel.shatteredpixeldungeon.sprites.PotSprite;
import com.watabou.utils.Random;

public class ExpCapsule extends NPC {

	{
		spriteClass = PotSprite.class;
		HP=HT=5;
		state = PASSIVE;
		baseSpeed = 0f;
        maxLvl=100;
		EXP=Random.Int(1,Random.Int(1,Random.Int(1,50)));

	}

	@Override
	public void beckon(int cell){}

	@Override
	public void damage(int dmg, Object src) {
		if (dmg != 1)	dmg = 1;
		super.damage(dmg, src);
	}

}