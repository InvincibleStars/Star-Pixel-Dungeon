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

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Cripple;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.areaitem.Shell;
import com.shatteredpixel.shatteredpixeldungeon.sprites.newsprite.sand.SpiderSprite;
import com.watabou.utils.Random;

public class Spider extends Mob {

	{
		spriteClass = SpiderSprite.class;
		HP=HT=19;
		EXP=4;
		maxLvl=7;
		lootChance = 0.12f;

		baseSpeed=2.0f;
	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange(3, 9);
	}

	@Override
	public int attackProc(Char enemy, int damage) {
		if(Random.Int(4)==0) {
			Buff.prolong(enemy, Cripple.class, 4f);
		}
		return super.attackProc(enemy, damage);
	}

	@Override
	protected Item createLoot() {
		Item loot;
		if(Random.Float()<0.75f){
			loot = Generator.random(Generator.Category.SCROLL);
		} else {
			loot = new Shell();
		}
		return loot;
	}

}