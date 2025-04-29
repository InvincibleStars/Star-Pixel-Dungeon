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

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.newbuff.Withered;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.bossloot.BossLoot;
import com.shatteredpixel.shatteredpixeldungeon.sprites.newsprite.sand.WithredWormSprite;
import com.watabou.utils.Random;

public class WitheredWorm extends Worm {

	{
		spriteClass = WithredWormSprite.class;
		EXP = 2;

		loot = Generator.Category.SEED;
		lootChance = 0.25f;
	}

	@Override
	protected boolean act() {
		Buff.affect(this, Withered.class);
		return super.act();
	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange(3,7);
	}

	@Override
	public void die(Object cause){
		super.die(cause);
		BossLoot.infection+=1;
	}
}