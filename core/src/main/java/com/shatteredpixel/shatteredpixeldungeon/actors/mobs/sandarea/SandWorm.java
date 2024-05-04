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

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.sandarea;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.bossloot.BossLoot;
import com.shatteredpixel.shatteredpixeldungeon.sprites.newsprite.sand.SandWormSprite;
import com.watabou.utils.Random;

public class SandWorm extends Mob {

	{
		spriteClass = SandWormSprite.class;

		HP = HT = 6 + Random.Int(4+(BossLoot.infection*2)) + Dungeon.depth;
		defenseSkill = 3;
		EXP = 2;
		maxLvl = 3;

		loot = Generator.Category.SEED;
		lootChance = 0.25f;

		state = WANDERING = new Waiting();
	}



	public int cooldown = 5;

	@Override
	public int attackSkill( Char target ) {
		return 10;
	}


	@Override
	protected boolean act() {
		if(cooldown<1){
			HP = Math.min(HT, HP + Random.Int(4));
			cooldown=Random.Int(9);
		}else{
			cooldown-=1;
		}
		return super.act();
	}

	@Override
	protected boolean getCloser(int target) {
		return true;
	}

	@Override
	protected boolean getFurther(int target) { return true; }

	@Override
	public int damageRoll() { return Random.NormalIntRange(2, 6+BossLoot.infection); }

	@Override
	public int drRoll() {
		return Random.NormalIntRange(0,2);
	}

	//{ immunities.add( ToxicGas.class ); }

	private class Waiting extends Wandering{}
}