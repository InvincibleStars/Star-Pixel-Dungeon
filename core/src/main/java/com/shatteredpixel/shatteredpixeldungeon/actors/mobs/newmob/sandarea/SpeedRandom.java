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

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.newmob.sandarea;

import static com.shatteredpixel.shatteredpixeldungeon.Dungeon.hero;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.LockedFloor;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Monk;
import com.shatteredpixel.shatteredpixeldungeon.sprites.newsprite.sand.BlackWormSprite;
import com.watabou.utils.Random;

public class SpeedRandom extends Mob {


	{

		spriteClass = BlackWormSprite.class;
		
		HP = HT =  Random.Int(5,9);

		EXP = 2100000000;
		
		maxLvl = 1;

		defenseSkill = 2;


	}

	@Override
	public int attackSkill( Char target ) {
		return 10;
	}

	@Override
	public int damageRoll() { return Random.NormalIntRange( 0, 1 ); }

	@Override
	public int drRoll() {
		return Random.NormalIntRange( 0, 1);
	}

	public boolean act() {
		//baseSpeed = Random.Float(1,2);
		//baseSpeed = Random.Float(1,2);
		return super.act();
	}

}
