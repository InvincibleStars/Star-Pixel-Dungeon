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

package com.shatteredpixel.shatteredpixeldungeon.levels.arealevel.bosslevel;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Goo;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.newboss.Level1Boss;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.watabou.utils.Random;

public class TreeAreaBossLevel2 extends Level {
	{
		color1 = 0x534f3e;
		color2 = 0xb9d661;
	}
	private int mapToTerrain(int code){
		switch (code){
			default:
				return Terrain.WATER;
			case 49:
				return Terrain.CHASM;
			case 1:
				if(Random.Int(50)==0){
					return Terrain.EMPTY_DECO;
				}else {
					return Terrain.EMPTY;
				}
			case 102:
				return Terrain.GRASS;
			case 20:
				return Terrain.EMPTY_WELL;
			case 65:
				return Terrain.WALL;
			case 81:
				return Terrain.DOOR;
			case 82:
				return Terrain.OPEN_DOOR;
			case 17:
				return Terrain.ENTRANCE;
			case 18:
				return Terrain.EXIT;
			case 4:
				return Terrain.EMBERS;
			case 83:
				return Terrain.LOCKED_DOOR;
			case 21:
				return Terrain.PEDESTAL;
			case 66:
				return Terrain.WALL_DECO;
			case 101:
				return Terrain.BARRICADE;
		case 5:
			return Terrain.EMPTY_SP;
		case 166:
			return Terrain.HIGH_GRASS;
		case 103:
			return Terrain.FURROWED_GRASS;
		case 2:
			return Terrain.EMPTY_DECO;
		case 86:
			return Terrain.LOCKED_EXIT;
		case 87:
			return Terrain.UNLOCKED_EXIT;
		case 97:
			return Terrain.SIGN;
		case 19:
			return Terrain.WELL;
		case 98:
			return Terrain.STATUE;
		case 99:
			return Terrain.STATUE_SP;
		case 67:
			return Terrain.BOOKSHELF;
		case 100:
			return Terrain.ALCHEMY;
		}
	}
	private static final int[] pre_map = {
			65,65,65,65,65,65,65,65,65,65,65,65,65,65,65,65,65,65,65,
			65,65,65,65,65,65,65,65,49,49,49,65,65,65,65,65,65,65,65,
			65,65,65,65,4,4,1,1,257,257,257,1,1,4,4,65,65,65,65,
			65,65,65,4,4,4,1,1,1,1,1,1,1,4,4,4,65,65,65,
			65,65,4,4,21,1,1,1,1,1,1,1,1,1,21,4,4,65,65,
			65,65,4,4,1,1,1,1,1,1,1,1,1,1,1,4,4,65,65,
			65,65,1,1,1,1,98,1,1,1,1,1,98,1,1,1,1,65,65,
			65,65,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,65,65,
			65,49,257,1,1,1,1,1,1,1,1,1,1,1,1,1,257,49,65,
			65,49,257,1,1,1,1,1,1,1,1,1,1,1,1,1,257,49,65,
			65,49,257,1,1,1,1,1,1,1,1,1,1,1,1,1,257,49,65,
			65,65,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,65,65,
			65,65,1,1,1,1,98,1,1,1,1,1,98,1,1,1,1,65,65,
			65,65,4,4,1,1,1,1,1,1,1,1,1,1,1,4,4,65,65,
			65,65,4,4,21,1,1,1,1,1,1,1,1,1,21,4,4,65,65,
			65,65,65,4,4,4,1,1,1,1,1,1,1,4,4,4,65,65,65,
			65,65,65,65,4,4,1,1,1,1,1,1,1,4,4,65,65,65,65,
			65,65,65,65,65,65,65,65,65,81,65,65,65,65,65,65,65,65,65,
			65,65,65,65,65,65,1,1,1,1,1,1,1,65,65,65,65,65,65,
			65,65,65,65,65,65,1,18,1,1,1,17,1,65,65,65,65,65,65,
			65,65,65,65,65,65,1,1,1,1,1,1,1,65,65,65,65,65,65,
			65,65,65,65,65,65,65,65,65,65,65,65,65,65,65,65,65,65,65};
	@Override
	public String tilesTex() {return Assets.Environment.TILES_PRISON;}
	@Override
	public String waterTex() {return Assets.Environment.WATER_PRISON;}
	private static final int WIDTH = 19;
	private static final int HEIGHT = 22;
	@Override
	protected boolean build() {setSize(WIDTH, HEIGHT);
		entrance = WIDTH*15+8;
		exit =WIDTH*15+9 ;
		for (int map = 0; map < this.map.length; map++) this.map[map] = mapToTerrain(pre_map[map]);return true;};
	@Override
	public Mob createMob() {
		return null;
	}
	@Override
	protected void createMobs() {
            Goo i= new Goo();
			i.pos = 85;
			mobs.add(i);
		}
	public Actor addRespawner() {return null;}
	@Override
	protected void createItems() {}
	@Override
	public int randomRespawnCell( Char ch ) {return entrance-width();}
}