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

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.treearea;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.MobLoot;
import com.shatteredpixel.shatteredpixeldungeon.items.areaitem.Silme;
import com.shatteredpixel.shatteredpixeldungeon.items.bossloot.BossLoot;
import com.shatteredpixel.shatteredpixeldungeon.sprites.newsprite.tree.LifePlantSprite;
import com.watabou.utils.Random;

public class Rattan extends Rat {

	{
		spriteClass = LifePlantSprite.class;
		
		HP = HT = 75 + Random.Int(-13,13) + BossLoot.infection*2;
		EXP = 4;
		maxLvl=11;
		
		//loot = Generator.Category.POTION;
		lootChance = 0.25f;
	}



	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 4, 20 )+ BossLoot.infection;
	}

	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, 15);
	}

	@Override
	protected Item createLoot() {
		Item loot;
		float a = Random.Float();
		float b = Random.Float();
		if(a<=(1f * ((8f - Dungeon.LimitedDrops.RATTAN_LOOT.count) / 8f))){
			loot = Generator.randomUsingDefaults(Generator.Category.SEED);
			Dungeon.LimitedDrops.RATTAN_LOOT.count++;
		} else {
			if(b<=0.35f){
				loot = new Silme();
			}else {
				loot = new MobLoot().quantity(Random.Int(2, 5));
			}
		}
		return loot;
	}
}