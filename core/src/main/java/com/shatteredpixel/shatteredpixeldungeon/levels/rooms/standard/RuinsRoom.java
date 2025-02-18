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

package com.shatteredpixel.shatteredpixeldungeon.levels.rooms.standard;

import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.levels.painters.Painter;
import com.watabou.utils.Point;

public class RuinsRoom extends PatchRoom {
	@Override
	public float[] sizeCatProbs() {
		// 增大房间尺寸的概率
		return new float[]{1, 2, 3}; // 更倾向于生成大房间
	}

	@Override
	public boolean canMerge(Level l, Point p, int mergeTerrain) {
		return true;
	}

	@Override
	public void paint(Level level) {
		// 填充墙壁
		Painter.fill(level, this, Terrain.WALL);
		// 填充内部为空地
		Painter.fill(level, this, 1, Terrain.EMPTY);

		// 增加内部混乱度
		// fill scales from ~30% at 4x4, to ~60% at 18x18
		float fill = 0.30f + (width() * height()) / 512f;

		setupPatch(level, fill, 0, true);
		cleanDiagonalEdges();

		for (int i = top + 1; i < bottom; i++) {
			for (int j = left + 1; j < right; j++) {
				if (patch[xyToPatchCoords(j, i)]) {
					int cell = i * level.width() + j;
					level.map[cell] = Terrain.WALL;
				}
			}
		}

		// 去掉所有门
		for (Door door : connected.values()) {
			// 将门的位置改为 EMPTY
			Painter.set(level, door.x, door.y, Terrain.EMPTY);
			// 如果门是水平的，删除两个格子
			if (door.x == left || door.x == right) {
				Painter.set(level, door.x, door.y + 1, Terrain.EMPTY);
			} else {
				Painter.set(level, door.x + 1, door.y, Terrain.EMPTY);
			}
		}
	}
}
