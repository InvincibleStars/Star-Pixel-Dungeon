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

package com.shatteredpixel.shatteredpixeldungeon.levels.rooms.newrooms;
import static com.shatteredpixel.shatteredpixeldungeon.Dungeon.level;
import static com.shatteredpixel.shatteredpixeldungeon.levels.Terrain.DOOR;
import static com.shatteredpixel.shatteredpixeldungeon.levels.Terrain.EMPTY;
import static com.shatteredpixel.shatteredpixeldungeon.levels.Terrain.EMPTY_SP;
import static com.shatteredpixel.shatteredpixeldungeon.levels.Terrain.EXIT;
import static com.shatteredpixel.shatteredpixeldungeon.levels.Terrain.LOCKED_DOOR;
import static com.shatteredpixel.shatteredpixeldungeon.levels.Terrain.WALL;

import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.levels.painters.Painter;
import com.shatteredpixel.shatteredpixeldungeon.levels.rooms.standard.StandardRoom;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.watabou.utils.Random;

public class MachineryRoom extends StandardRoom {

	@Override
	public void paint(Level level) {
		//Painter.fill(level, this, Terrain.DOOR, 8);
		//Painter.drawVerticalLine(level, center(), 18, Terrain.EMPTY_SP);
		Painter.drawCircle(level, center(), height()/2, Terrain.EMPTY_SP);
		randomTerrain(level);
		randomGrass(level);
		randomWall(level);
	}

	private void randomTerrain(Level level) {
		for(int a =0;a<level.width()*level.height();a++){
			int c = com.watabou.utils.Random.Int(level.width(),level.height()* (level.width()));
			if (level.map[a] == WALL||level.map[a] == DOOR||level.map[a] == LOCKED_DOOR) {
				Painter.set(level, a, Terrain.EMPTY);
			}
		}
		GameScene.updateMap();
	}

	private void randomGrass(Level level) {
		for(int b =0;b<level.width()*level.height();b++){
			int c = com.watabou.utils.Random.Int(level.width(),level.height()* (level.width()));
			if (level.map[b] == EMPTY) {
				Painter.set(level, b, EMPTY);
			}
		}
		GameScene.updateMap();
	}

	private void randomWall(Level level) {
		for(int c =0;c<level.width()*level.height();c++){
			int d = Random.Int(1,30);
			if ((c<level.width()||c>level.width()*level.height()-level.width()||c%d==0)&&level.map[c] != EXIT) {
					Painter.set(level, c, WALL);
			}
		}
		GameScene.updateMap();
	}



}
