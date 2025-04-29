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

import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.areaitem.Tooth;
import com.shatteredpixel.shatteredpixeldungeon.sprites.newsprite.sand.WormSprite;
import com.watabou.utils.Random;

public class Worm extends Mob {

	{
		spriteClass = WormSprite.class;

		HP=HT=10;

		EXP=1;
		maxLvl=7;

		lootChance = 1/6f;

		state = WANDERING = new Waiting();
	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange(1,6);
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
		if(Random.Float()<0.25){
			loot = new Tooth();
		} else {
			loot = Generator.random(Generator.Category.POTION);
		}
		return loot;
	}
}