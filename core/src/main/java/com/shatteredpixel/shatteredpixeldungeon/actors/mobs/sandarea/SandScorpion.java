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
import com.shatteredpixel.shatteredpixeldungeon.sprites.newsprite.sand.SandScorpionSprite;
import com.watabou.utils.Random;

public class SandScorpion extends Mob {

	{
		spriteClass = SandScorpionSprite.class;

		hpPole=8;
		attackPloe=6;
		
		maxLvl = 6;

		//loot = Generator.Category.SCROLL;
		lootChance = 0.125f;
	}

	//行动逻辑
	public boolean act() {
		baseSpeed = Random.Float(1,1.25f);
		return super.act();
	}

	@Override
	protected Item createLoot() {
		Item loot;
		float a = Random.Float();
		if(a<=(1f * ((6f - Dungeon.LimitedDrops.SANDSCORPION_LOOT.count) / 6f))){
			loot = Generator.random(Generator.Category.SCROLL);
			Dungeon.LimitedDrops.SANDSCORPION_LOOT.count++;
		} else {
			loot = new MobLoot().quantity(Random.Int(1,4));
		}
		return loot;
	}

}