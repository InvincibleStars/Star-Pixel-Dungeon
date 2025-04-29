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

package com.shatteredpixel.shatteredpixeldungeon.levels.rooms.newrooms.equipspecials;


import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Challenges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.NPC;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.room.ItemBox;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Gold;
import com.shatteredpixel.shatteredpixeldungeon.items.Heap;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.bombs.Bomb;
import com.shatteredpixel.shatteredpixeldungeon.items.keys.IronKey;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.levels.painters.Painter;
import com.shatteredpixel.shatteredpixeldungeon.levels.rooms.special.SpecialRoom;
import com.shatteredpixel.shatteredpixeldungeon.levels.rooms.standard.StandardRoom;
import com.watabou.utils.Point;
import com.watabou.utils.Random;

import java.util.HashMap;

public class EquipRoom extends StandardRoom {


	@Override
	public int minHeight() { return 7; }
	public int maxHeight() { return 7; }
	public int minWidth() { return 7; }
	public int maxWidth() { return 7; }

	private static final int[] pre_map = {
			Terrain.STATUE_SP,Terrain.EMPTY_SP,Terrain.EMPTY_SP,Terrain.EMPTY_SP,Terrain.STATUE_SP, Terrain.EMPTY_SP,Terrain.EMPTY_SP,Terrain.EMPTY_SP,Terrain.EMPTY_SP,Terrain.EMPTY_SP,
			Terrain.EMPTY_SP,Terrain.EMPTY_SP,Terrain.EMPTY_SP,Terrain.EMPTY_SP,Terrain.EMPTY_SP,
			Terrain.EMPTY_SP,Terrain.EMPTY_SP,Terrain.EMPTY_SP,Terrain.EMPTY_SP,Terrain.EMPTY_SP,
	};


	@Override
	public void paint(Level level) {

		// 填充外围墙体
		for (int x = left; x <= right; x++) {
			for (int y = top; y <= bottom; y++) {
				// 如果是边缘则设置为墙
				if (x == left || x == right || y == top || y == bottom) {
					set(level, x, y, Terrain.WALL);
				}
			}
		}


		// 填充中心区域（3x3）
		for (int rx = left + 1; rx <= right - 1; rx++) {
			for (int ry = top + 1; ry <= bottom - 1; ry++) {
				// 计算数组索引（将房间坐标映射到pre_map）
				int dx = rx - (left + 1);
				int dy = ry - (top + 1);
				int index = dy * 3 + dx;

				if (index >= 0 && index < pre_map.length) {
					set(level, rx, ry, pre_map[index]);
				} else {
					set(level, rx, ry, Terrain.EMPTY);
				}
			}
		}

		int n = Random.IntRange( 2, 3 );
		prizeCats = new float[]{1,1,1,1};
		/*
		for (int i=0; i < n; i++) {
			int pos = level.pointToCell(random());
			if(Terrain.flags[pos]==Terrain.EMPTY_SP){
				level.drop( prize( level ), pos );
			}

		}

		 */

    }

	public String tilesTex() {
		return Assets.Environment.TILES_SAND;
	}
	//水体贴图

	public String waterTex() {
		return Assets.Environment.WATER_SEWERS;
	}


	private static float[] prizeCats;
	private static Item prize( Level level ) {
		int index = Random.chances(prizeCats);
		prizeCats[index] = 0;
		switch (index){
			case 1:
				return Generator.randomWeapon();
			case 2:
				return Generator.randomArmor();
			default:
				return Generator.randomMissile();
		}
	}

	private void set(Level level, int x, int y, int terrain) {
		level.map[x + y * level.width()] = terrain;
	}


}
