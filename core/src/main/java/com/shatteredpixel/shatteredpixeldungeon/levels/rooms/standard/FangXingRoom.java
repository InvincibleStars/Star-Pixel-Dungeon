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

import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.levels.painters.Painter;
import com.watabou.utils.Point;
import com.watabou.utils.Random;

public class FangXingRoom extends StandardRoom {


	@Override
	public int minWidth() { return 12; }
	@Override
	public int minHeight() {
		return 12;
	}
	@Override
	public int maxWidth() { return 30; }
	@Override
	public int maxHeight() { return 23; }

	@Override
	public void paint(Level level) {


		//适应模块：房间的一些填充会随着房间的大小而改变

		if(width()<= 10 ) {
			Painter.fill( level, this, 0, Terrain.WALL );
			Painter.fill(level, this, 1, Terrain.GRASS);
		}
		if((width()>10 && width()<=16)) {
			Painter.fill( level, this, 0, Terrain.WALL );
			Painter.fill(level, this, 1, Terrain.GRASS);
			Painter.fill(level, this, 2, Terrain.CHASM);

			Painter.fill(level, this, 3, Terrain.CHASM);
		}
		if ((width() > 16 && width () <=20)) {
			Painter.fill( level, this, 0, Terrain.WALL );
			Painter.fill(level, this, 1, Terrain.GRASS);
			Painter.fill(level, this, 2, Terrain.CHASM);
			Painter.fill(level, this, 3, Terrain.CHASM);
			Painter.fill(level, this, 4, Terrain.WALL);
			Painter.fill(level, this, 5, Terrain.EMPTY);
		}
		if (width() > 20 ) {
			Painter.fill( level, this, 0, Terrain.WALL );
			Painter.fill(level, this, 1, Terrain.GRASS);
			Painter.fill(level, this, 2, Terrain.CHASM);
			Painter.fill(level, this, 3, Terrain.CHASM);
			Painter.fill(level, this, 4, Terrain.WALL);
			Painter.fill(level, this, 5, Terrain.EMPTY);
			Painter.fill(level, this, 7, Terrain.CHASM);
		}

		//Painter.fill(level, this, 2, (Random.Int(2)==1 ?Terrain.EMPTY :Terrain.GRASS));



		/*
		Painter.fill( level, this, Terrain.WALL );
		Painter.fill( level, this, 1, Terrain.EMPTY_DECO );
		Painter.fill( level, this, 2, Terrain.CHASM );
		Painter.fill( level, this, 3, Terrain.CHASM );
		Painter.fill( level, this, 4, Terrain.WALL);
		Painter.fill( level, this, 5, Terrain.EMPTY_DECO);
		Painter.fill( level, this, 7, Terrain.CHASM);

		 */

		//中心高度/宽度
		int centerX = left + width() / 2;
		int centerY = top + height() / 2;

		//锚定点
		int seedX = left + width() - 3;
		int seedY =	top + height() - 3;


		//下
		Painter.set(level, centerX, seedY - 2, Terrain. EMPTY_DECO);

		//上
		Painter.set(level, centerX, top + 4, Terrain. EMPTY_DECO);

		//右
		Painter.set(level, seedX-2, centerY, Terrain. EMPTY_DECO);

		//左
		Painter.set(level, left + 4, centerY, Terrain. EMPTY_DECO);




		for (Door door : connected.values()) {
			//Painter.drawInside(level, this, door, 2, Terrain.EMPTY_SP);
			door.set(Door.Type.REGULAR);
		}
	}

	}
