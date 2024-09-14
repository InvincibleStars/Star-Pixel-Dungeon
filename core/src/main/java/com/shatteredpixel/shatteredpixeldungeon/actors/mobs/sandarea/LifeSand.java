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
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.BuffWait;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Cripple;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.MobLoot;
import com.shatteredpixel.shatteredpixeldungeon.items.bossloot.BossLoot;
import com.shatteredpixel.shatteredpixeldungeon.sprites.newsprite.sand.LifeSandSprite;
import com.watabou.utils.Random;

public class LifeSand extends Mob {
	
	{
		spriteClass = LifeSandSprite.class;

		HP = HT = 16 + Random.Int(4+(BossLoot.infection*2));
		
		EXP = 4;
		maxLvl = 5;


		defenseSkill = 4;

		//loot = Generator.Category.WEAPON;
		lootChance = 0.25f;
	}

	@Override
	public int attackSkill( Char target ) {
		return 12;
	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange(1, 8+BossLoot.infection);
	}

	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, 2);
	}
	@Override
	public int attackProc( Char enemy, int damage ) {
		damage = super.attackProc( enemy, damage );
		if (Random.Int( 2 ) == 0) {
			Buff.prolong( enemy, Cripple.class, BuffWait.T3 );

		}

		return damage;
	}

	@Override
	protected Item createLoot() {
		Item loot;
		float a = Random.Float();
		if(a<=(0.4f * ((1f - Dungeon.LimitedDrops.LIFESAND_LOOT.count) / 1f))){
			loot = Generator.random(Generator.Category.RING);
			Dungeon.LimitedDrops.LIFESAND_LOOT.count++;
		} else {
			loot = new MobLoot().quantity(Random.Int(1,4));
		}
		return loot;
	}

}