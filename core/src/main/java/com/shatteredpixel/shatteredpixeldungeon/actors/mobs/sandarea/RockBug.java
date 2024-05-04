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
import com.shatteredpixel.shatteredpixeldungeon.sprites.newsprite.sand.RockBugSprite;
import com.watabou.utils.Random;

public class RockBug extends Mob {

	{
		spriteClass = RockBugSprite.class;

		HP = HT = 8 + Random.Int(4+(BossLoot.infection*2)) + Dungeon.depth;
		EXP = 2;
		maxLvl = 3;
		loot = Generator.Category.ARMOR;
		lootChance = 0.18f; //by default, see rollToDropLoot()
		defenseSkill = 4;

		loot = Generator.Category.POTION;
		lootChance = 0.125f;
	}

	@Override
	public int attackSkill( Char target ) {
		return 11;
	}


	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 1, 4 +BossLoot.infection);
	}

	
	@Override
	public int drRoll() {
		return Random.NormalIntRange(2, 2);
	}

}