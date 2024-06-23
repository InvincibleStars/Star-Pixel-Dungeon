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
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.newboss.Level1Boss;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;

public class SandAreaBossLevel2 extends Level {
	{
		color1 = 0x48763c;
		color2 = 0x59994a;
	}
	private int mapToTerrain(int code){switch (code){
			case 1: default: return Terrain.EMPTY;
			case 65: return Terrain.WALL;
			case 83: return Terrain.LOCKED_DOOR;
			case 81: return Terrain.DOOR;
			case 17: return Terrain.ENTRANCE;
			case 18: return Terrain.EXIT;}}
	private static final int[] pre_map = {
			65,65,65,65,65,65,65,65,65,65,65,65,65,65,65,
			65,65,65,65,65,1,1,18,1,1,65,65,65,65,65,
			65,65,65,65,65,1,1,1,1,1,65,65,65,65,65,
			65,65,65,65,65,65,1,1,1,65,65,65,65,65,65,
			65,65,65,1,1,1,1,1,1,1,1,1,65,65,65,
			65,65,65,1,1,1,1,1,1,1,1,1,65,65,65,
			65,1,1,1,1,65,1,1,1,65,1,1,1,1,65,
			65,1,1,1,1,1,1,1,1,1,1,1,1,1,65,
			65,1,1,65,1,1,1,65,1,1,1,65,1,1,65,
			65,1,1,1,1,1,1,1,1,1,1,1,1,1,65,
			65,1,1,1,1,65,1,1,1,65,1,1,1,1,65,
			65,65,65,1,1,1,1,1,1,1,1,1,65,65,65,
			65,65,65,1,1,1,1,1,1,1,1,1,65,65,65,
			65,65,65,65,65,65,65,81,65,65,65,65,65,65,65,
			65,65,65,65,65,1,1,1,1,1,65,65,65,65,65,
			65,65,65,65,65,1,1,17,1,1,65,65,65,65,65,
			65,65,65,65,65,65,65,65,65,65,65,65,65,65,65};
	@Override
	public String tilesTex() {return Assets.Environment.TILES_SAND;}
	@Override
	public String waterTex() {return Assets.Environment.WATER_SEWERS;}
	private static final int WIDTH = 15;
	private static final int HEIGHT = 17;
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
            Level1Boss i= new Level1Boss();		i.pos = 85;		mobs.add(i);}
	public Actor addRespawner() {return null;}
	@Override
	protected void createItems() {}
	@Override
	public int randomRespawnCell( Char ch ) {return entrance-width();}
}