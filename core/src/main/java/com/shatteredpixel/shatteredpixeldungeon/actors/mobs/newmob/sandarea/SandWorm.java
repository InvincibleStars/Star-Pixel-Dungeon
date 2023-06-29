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

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.sprites.newsprite.sand.SandWormSprite;
import com.watabou.utils.Random;

public class SandWorm extends Mob {

	{
		spriteClass = SandWormSprite.class;

		HP = HT = 12 + Random.Int(5);
		defenseSkill = 0;
		EXP = 1;
		maxLvl = 3;
		loot = Generator.Category.SEED;
		lootChance = 0.15f;
		state = WANDERING = new Waiting();
	}

	@Override
	public int attackSkill( Char target ) {
		return 10;
	}
/*
	@Override
	public int attackProc(Char enemy, int damage) {
		if(HP<=1){
			super.die(true);
		}else {
			damage = super.attackProc(enemy, damage);
			HP = Math.min(HT, HP - 1);
		}
			return damage;
	}
	@Override
	protected boolean act() {
		if (enemy == null || !Dungeon.level.adjacent(pos, enemy.pos)) {
			HP = Math.min(HT, HP + 1);
		}

		return super.act();
	}

	@Override
	public boolean reset() {
		return true;
	}
 */

	@Override
	protected boolean getCloser(int target) {
		return true;
	}

	@Override
	protected boolean getFurther(int target) { return true; }

	@Override
	public int damageRoll() { return Random.NormalIntRange(2, 6); }

	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, 1);
	}

	//{ immunities.add( ToxicGas.class ); }

	private class Waiting extends Wandering{}
}
