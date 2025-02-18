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
import com.watabou.utils.Random;

public class FangXingRoom extends StandardRoom {
	private static final Random random = new Random();

	@Override
	public float[] sizeCatProbs() {
		return new float[]{6, 3, 1}; // 默认尺寸分类概率
	}

	@Override
	public void paint(Level level) {
		// 填充墙壁
		Painter.fill(level, this, Terrain.WALL);

		// 填充内部为空地
		Painter.fill(level, this, 1, Terrain.EMPTY);

		// 在房间中间放置 GRASS
		placeGrass(level);

		// 随机放置装饰物
		placeDecorations(level);

		// 将门的位置改为两格的 EMPTY
		for (Door door : connected.values()) {
			// 获取门的位置
			int doorX = door.x;
			int doorY = door.y;

			// 将门的位置改为两格的 EMPTY
			Painter.set(level, doorX, doorY, Terrain.EMPTY);
		}
	}

	private void placeGrass(Level level) {
		// 计算房间的中心位置
		int centerX = (left + right) / 2;
		int centerY = (top + bottom) / 2;

		// 如果宽度为偶数，则放置 2x2 的 GRASS
		if ((right - left + 1) % 2 == 0) {
			Painter.set(level, centerX - 1, centerY - 1, Terrain.GRASS);
			Painter.set(level, centerX, centerY - 1, Terrain.GRASS);
			Painter.set(level, centerX - 1, centerY, Terrain.GRASS);
			Painter.set(level, centerX, centerY, Terrain.GRASS);
		} else {
			// 如果宽度为奇数，则只放置一个 GRASS
			Painter.set(level, centerX, centerY, Terrain.GRASS);
		}
	}

	private void placeDecorations(Level level) {
		// 随机选择一些位置放置装饰物
		int numDecorations = Random.Int(width()*height()/5); // 随机放置 3-7 个装饰物
		for (int i = 0; i < numDecorations; i++) {
			int x = Random.Int(width() - 2) + left + 1;
			int y = Random.Int(height() - 2) + top + 1;

			// 随机选择装饰物类型
			int terrain = Random.Int(20);
			switch (terrain) {
				default:
					Painter.set(level, x, y, Terrain.CHASM);
					break;
			}
		}
	}
}