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

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs;

import static com.shatteredpixel.shatteredpixeldungeon.Dungeon.depth;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Rat;
import com.shatteredpixel.shatteredpixeldungeon.items.Dewdrop;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.food.Berry;
import com.shatteredpixel.shatteredpixeldungeon.items.food.Food;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.MissileWeapon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.plants.BlandfruitBush;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.PotSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.RatSprite;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class Pot extends NPC {

	{
		spriteClass = PotSprite.class;
		HP=HT=1;
		defenseSkill=0;
		state = PASSIVE;
		baseSpeed = 0f;
        maxLvl=100;

	}

    //屏蔽生物指示器
	@Override
	public void beckon(int cell){

	}

	//掉落的概率与机会
	private float chance_one = 0.2f; //20%

	@Override
	public void die( Object cause ) {

		super.die(cause);

        //60% chance of 2 blobs, 30% chance of 3, 10% chance for 4. Average of 2.5
		int blobs = Random.chances(new float[]{0, 1});
		for (int i = 0; i < blobs; i++) {
			int ofs;
			do {
				ofs = PathFinder.NEIGHBOURS8[Random.Int(8)];
			} while (!Dungeon.level.passable[pos + ofs]);

			switch (depth) {
				case 1: default:
					if (Random.Float() >= chance_one) {
						Dungeon.level.drop(new BlandfruitBush.Seed(), pos).sprite.drop();
					} else {
						Dungeon.level.drop(new Food(), pos).sprite.drop();
					}
				case 6: case 7: case 8: case 9:
					if (Random.Float() >= chance_one) {
						Dungeon.level.drop(new BlandfruitBush.Seed(), pos).sprite.drop();
					} else {
						Dungeon.level.drop(new Food(), pos).sprite.drop();
					}
				case 11: case 12: case 13: case 14:
					if (Random.Float() >= chance_one) {
						Dungeon.level.drop(new BlandfruitBush.Seed(), pos).sprite.drop();
					} else {
						Dungeon.level.drop(new Food(), pos).sprite.drop();
					}
				case 16: case 17:case 18:case 19:
					if (Random.Float() >= chance_one) {
						Dungeon.level.drop(new BlandfruitBush.Seed(), pos).sprite.drop();
					} else {
						Dungeon.level.drop(new Food(), pos).sprite.drop();
					}
				case 21: case 22:case 23:case 24:
					if (Random.Float() >= chance_one) {
						Dungeon.level.drop(new BlandfruitBush.Seed(), pos).sprite.drop();
					} else {
						Dungeon.level.drop(new Food(), pos).sprite.drop();
					}
			}
		}


	}}
