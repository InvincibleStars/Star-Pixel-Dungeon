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
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.boss.GooUpdate;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;

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
				return Terrain.EMPTY;
			case 102:
				return Terrain.GRASS;
			case 20:
				return Terrain.EMPTY_WELL;
			case 113:
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
			113,113,113,113,113,113,113,113,113,113,113,113,113,113,113,113,113,113,113,113,113,113,113,113,113,113,113,113,113,113,
			113,5,5,5,113,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,101,5,5,5,113,
			113,5,18,5,83,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,113,113,1,1,101,5,5,5,113,
			113,113,113,113,113,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,113,113,1,1,101,5,5,5,113,
			113,1,1,1,1,1,1,113,113,1,1,1,1,1,1,113,113,1,1,1,1,1,1,1,1,101,101,101,101,113,
			113,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,113,
			113,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,113,1,1,1,1,1,1,1,1,1,1,113,
			113,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,113,1,1,1,1,113,113,1,1,1,1,113,
			113,1,1,1,1,113,113,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,113,113,1,1,1,1,113,
			113,1,1,1,1,113,113,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,113,
			113,1,1,1,1,1,1,1,1,1,1,113,113,113,21,113,113,113,1,1,1,1,1,1,1,1,1,1,1,113,
			113,1,1,1,1,1,1,1,1,1,1,113,5,5,5,5,20,113,1,1,5,5,113,5,5,5,1,1,1,113,
			113,1,1,1,1,1,1,1,1,1,1,113,5,5,5,5,5,113,1,1,5,113,113,113,257,5,1,1,1,113,
			113,113,113,113,1,1,1,1,1,1,1,21,5,5,17,5,5,21,1,1,5,257,113,257,257,5,5,1,1,113,
			113,257,257,5,1,1,1,1,1,1,1,113,5,5,5,5,5,113,1,1,5,257,257,257,257,257,5,1,1,113,
			113,257,257,5,1,1,1,1,1,1,1,113,5,5,5,5,5,113,1,1,5,257,257,257,257,257,5,1,1,113,
			113,257,257,5,1,1,1,1,1,1,1,113,113,113,21,113,113,113,1,1,5,5,257,257,113,257,5,1,1,113,
			113,113,113,113,1,1,1,1,1,1,1,1,1,1,1,1,113,113,1,1,1,5,257,113,113,113,5,1,1,113,
			113,1,1,1,1,1,113,113,1,1,1,1,1,1,1,1,1,1,1,1,1,5,5,5,113,5,5,1,1,113,
			113,1,1,1,1,1,113,113,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,113,
			113,1,1,5,5,5,5,5,1,1,1,1,113,113,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,113,
			113,1,5,5,257,257,257,5,5,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,113,
			113,1,5,257,257,5,257,257,5,1,1,1,1,1,1,1,1,1,1,113,113,1,1,1,1,1,1,1,1,113,
			113,1,5,257,5,20,5,257,5,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,113,
			113,1,5,257,257,5,257,257,5,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,113,
			113,1,5,5,257,257,257,5,5,1,1,1,1,1,113,1,1,1,1,1,1,1,113,1,1,1,1,1,1,113,
			113,1,1,5,5,5,5,5,1,1,1,1,1,1,113,1,1,1,1,1,1,1,113,1,1,1,1,1,1,113,
			113,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,113,
			113,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,113,
			113,113,113,113,113,113,113,113,113,113,113,113,113,113,113,113,113,113,113,113,113,113,113,113,113,113,113,113,113,113};
	@Override
	public String tilesTex() {return Assets.Environment.TILES_TREE;}
	@Override
	public String waterTex() {return Assets.Environment.WATER_PRISON;}
	private static final int WIDTH = 30;
	private static final int HEIGHT = 30;
	@Override
	protected boolean build() {setSize(WIDTH, HEIGHT);
		entrance = WIDTH*14+14;
		exit =WIDTH*2+2 ;
		for (int map = 0; map < this.map.length; map++) this.map[map] = mapToTerrain(pre_map[map]);return true;};
	@Override
	public Mob createMob() {
		return null;
	}
	@Override
	protected void createMobs() {
            GooUpdate i= new GooUpdate();
			i.pos = 83;
			mobs.add(i);
		}
	public Actor addRespawner() {return null;}
	@Override
	protected void createItems() {}
	@Override
	public int randomRespawnCell( Char ch ) {return entrance-width();}
}