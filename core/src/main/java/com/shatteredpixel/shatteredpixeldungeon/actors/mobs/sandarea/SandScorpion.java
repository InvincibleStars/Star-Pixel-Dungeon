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
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.MobLoot;
import com.shatteredpixel.shatteredpixeldungeon.items.bossloot.BossLoot;
import com.shatteredpixel.shatteredpixeldungeon.sprites.newsprite.sand.SandScorpionSprite;
import com.watabou.utils.Random;

public class SandScorpion extends Mob {

	{
		spriteClass = SandScorpionSprite.class;

		HP = HT = 15 + Random.Int(5+(BossLoot.infection*2));
		EXP =3;
		
		maxLvl = 6;
		defenseSkill = 5;

		//loot = Generator.Category.SCROLL;
		lootChance = 0.125f;
	}

	//行动逻辑
	public boolean act() {
		baseSpeed = Random.Float(1,1.25f);
		return super.act();
	}

	@Override
	public int attackSkill( Char target ) {
		return 13;
	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 1, 7+BossLoot.infection );
	}

	
	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, 2);
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