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

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.room;

import static com.shatteredpixel.shatteredpixeldungeon.Dungeon.depth;
import static com.shatteredpixel.shatteredpixeldungeon.Dungeon.level;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.NPC;
import com.shatteredpixel.shatteredpixeldungeon.items.EnergyCrystal;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Gold;
import com.shatteredpixel.shatteredpixeldungeon.levels.rooms.Room;
import com.shatteredpixel.shatteredpixeldungeon.sprites.PotSprite;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class Pot extends NPC {

	{
		spriteClass = PotSprite.class;
		HP=HT=1;
		state = PASSIVE;
		baseSpeed = 0f;
        maxLvl=100;

	}

	public static ArrayList<Room> spawnRoom(ArrayList<Room> rooms) { return rooms; }

	@Override //交互死亡
	public boolean interact(Char c) {
		die(true);
		return true;
	}

	@Override  //屏蔽指示器
	public void beckon(int cell){
	}





	private float chance_one = 0.5f;

	@Override
	public void die( Object cause ) {
		super.die(cause);
			switch (depth) {
				default:
					level.drop(new Gold().quantity(1), pos).sprite.drop();
					break;
				case 1: case 2: case 3: case 4: case 5:
					level.drop(new EnergyCrystal().quantity(Random.Int(1,3)), pos).sprite.drop();
					level.drop(new Gold().quantity(Random.Int(50)), pos).sprite.drop();
					break;
				case 6: case 7: case 8: case 9: case 10:
					level.drop(Generator.randomUsingDefaults(Generator.Category.SEED), pos).sprite.drop();
					break;

		}

	}}