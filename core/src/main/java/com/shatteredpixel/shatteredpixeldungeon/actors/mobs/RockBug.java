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

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.Ratmogrify;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.sprites.RatSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.RockBugSprite;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class RockBug extends Mob {

	{
		spriteClass = RockBugSprite.class;
		
		HP = HT = 14;
		EXP = 1;
		
		maxLvl = 6;
		loot = Generator.Category.ARMOR;
		lootChance = 0.18f; //by default, see rollToDropLoot()
	}


	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 1, 3 );
	}

	
	@Override
	public int drRoll() {
		return Random.NormalIntRange(1, 2);
	}

}
