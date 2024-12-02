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

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.sandarea.update;

import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.sandarea.RockBug;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.bossloot.BossLoot;
import com.shatteredpixel.shatteredpixeldungeon.sprites.newsprite.sand.RockBug2Sprite;
import com.watabou.utils.Random;

public class RockBug2 extends RockBug {

	{
		spriteClass = RockBug2Sprite.class;

		EXP = 2;
		baseSpeed = 0.75f;
		maxLvl = 4;
		loot = Generator.Category.POTION;
		lootChance = 1f; //by default, see rollToDropLoot()
	}

	
	@Override
	public int drRoll() {
		return Random.NormalIntRange(4, 11)+BossLoot.infection;
	}

	@Override
	public void die(Object cause){
		super.die(cause);
		BossLoot.infection+=1;
	}

}