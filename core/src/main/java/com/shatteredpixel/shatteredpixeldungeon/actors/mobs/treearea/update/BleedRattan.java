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

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.treearea.update;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Weakness;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.treearea.Rattan;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.MobLoot;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfHealing;
import com.shatteredpixel.shatteredpixeldungeon.sprites.newsprite.tree.AngryVineSprite;
import com.watabou.utils.Random;

public class BleedRattan extends Rattan {

	{
		spriteClass = AngryVineSprite.class;
		
		HP = HT = 20+Random.Int(2);
		EXP = 4;


		lootChance = 1f;
	}
	
	@Override
	public int attackProc( Char enemy, int damage ) {
		damage = super.attackProc( enemy, damage );
		if (Random.Int( 2 ) == 0) {
			Buff.affect( enemy, Weakness.class,4f );
		}
		
		return damage;
	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 1, 12 );
	}

	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, 3);
	}

	@Override
	protected Item createLoot() {
		Item loot;
		float a = Random.Float();
		if(a<=(1f * ((6f - Dungeon.LimitedDrops.BLEEDRATTAN_LOOT.count) / 6f))){
			loot = new PotionOfHealing();
			Dungeon.LimitedDrops.BLEEDRATTAN_LOOT.count++;
		} else {
			loot = new MobLoot().quantity(Random.Int(1,8));
		}
		return loot;
	}
}