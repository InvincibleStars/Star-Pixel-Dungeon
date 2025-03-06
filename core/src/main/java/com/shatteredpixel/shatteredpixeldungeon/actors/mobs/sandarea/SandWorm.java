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
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.MobLoot;
import com.shatteredpixel.shatteredpixeldungeon.items.bossloot.BossLoot;
import com.shatteredpixel.shatteredpixeldungeon.sprites.newsprite.sand.SandWormSprite;
import com.watabou.utils.Random;

public class SandWorm extends Mob {

	{
		spriteClass = SandWormSprite.class;

		hpPole=8;
		attackPloe=8;

		//loot = new MobLoot();
		lootChance = 0.125f;

		state = WANDERING = new Waiting();
	}



	public int cooldown = 5;



	@Override
	protected boolean act() {
		if(BossLoot.infection>=2){
			HP=Math.min(HP+=1,HT);
			spend(1f);
		}

		return super.act();
	}

	@Override
	protected boolean getCloser(int target) {
		return true;
	}

	@Override
	protected boolean getFurther(int target) { return true; }

	private class Waiting extends Wandering{}

	@Override
	protected Item createLoot() {
		Item loot;
		float a = Random.Float();
		if(a<=(1f * ((6f - Dungeon.LimitedDrops.SANDWORM_LOOT.count) / 6f))){
			loot = Generator.random(Generator.Category.POTION);
			Dungeon.LimitedDrops.SANDWORM_LOOT.count++;
		} else {
			loot = new MobLoot().quantity(Random.Int(1,2));
		}
		return loot;
	}
}