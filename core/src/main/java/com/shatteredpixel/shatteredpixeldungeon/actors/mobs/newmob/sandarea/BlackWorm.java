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

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Burning;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Ooze;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.levels.features.Chasm;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.newsprite.sand.BlackWormSprite;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.Camera;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class BlackWorm extends Mob {


	{
		spriteClass = BlackWormSprite.class;
		
		HP = HT =  4;

		EXP = 0;
		
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
	/*
	@Override
	public int attackProc(Char enemy, int damage) {
		damage = super.attackProc( enemy, damage );
			HP = Math.min(HT, HP - 1);
			if (HP <= 0) {
				die(true);
			}
		return damage;
	}

	 */

	@Override
	public void die( Object cause ) {

		super.die( cause );
		Camera.main.shake( 1, 245654654f );

	}

}
