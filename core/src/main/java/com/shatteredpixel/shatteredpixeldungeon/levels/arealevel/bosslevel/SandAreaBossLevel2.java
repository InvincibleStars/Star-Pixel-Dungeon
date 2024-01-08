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
import com.shatteredpixel.shatteredpixeldungeon.Bones;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.newboss.Level1Boss;
import com.shatteredpixel.shatteredpixeldungeon.items.Heap;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.Torch;
import com.shatteredpixel.shatteredpixeldungeon.items.keys.CrystalKey;
import com.shatteredpixel.shatteredpixeldungeon.items.keys.IronKey;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.levels.arealevel.SandAreaLevel;
import com.shatteredpixel.shatteredpixeldungeon.levels.arealevel.TreeAreaLevel;
import com.shatteredpixel.shatteredpixeldungeon.levels.builders.Builder;
import com.shatteredpixel.shatteredpixeldungeon.levels.builders.FigureEightBuilder;
import com.shatteredpixel.shatteredpixeldungeon.levels.painters.Painter;
import com.shatteredpixel.shatteredpixeldungeon.levels.painters.SewerPainter;
import com.shatteredpixel.shatteredpixeldungeon.levels.rooms.Room;
import com.shatteredpixel.shatteredpixeldungeon.levels.rooms.secret.RatKingRoom;
import com.shatteredpixel.shatteredpixeldungeon.levels.rooms.sewerboss.GooBossRoom;
import com.shatteredpixel.shatteredpixeldungeon.levels.rooms.sewerboss.SewerBossEntranceRoom;
import com.shatteredpixel.shatteredpixeldungeon.levels.rooms.sewerboss.SewerBossExitRoom;
import com.shatteredpixel.shatteredpixeldungeon.levels.rooms.standard.StandardRoom;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.watabou.noosa.Group;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class SandAreaBossLevel2 extends SandAreaLevel {

	{
		color1 = 0x48763c;
		color2 = 0x59994a;
	}
	private int mapToTerrain(int code){
		switch (code){
			default:
				return Terrain.EMPTY;
			case 1: //地面
				return Terrain.EMPTY;
			case 65:    //墙壁
				return Terrain.WALL;
			case 21:    //台子
				return Terrain.PEDESTAL;
			case 98:    //雕像
				return Terrain.STATUE;
			case 83:    //锁
				return Terrain.CRYSTAL_DOOR;
			// return Terrain.CRYSTAL_DOOR;
			case 84:    //锁
				return Terrain.DOOR;
			// return Terrain.CRYSTAL_DOOR;
			case 17:
				return Terrain.ENTRANCE;
			case 18:
				return Terrain.EXIT;



		}
	}

	//地形布局（硬编码布局？）
	private static final int[] pre_map = {
			65,65,65,65,65,65,65,65,65,65,65,65,65,65,65,65,65,65,65,
			65,65,65,65,65,65,98,1,1,1,1,1,98,65,65,65,65,65,65,
			65,65,65,65,65,65,98,1,1,18,1,1,98,65,65,65,65,65,65,
			65,65,65,65,65,65,98,1,1,1,1,1,98,65,65,65,65,65,65,
			65,65,65,65,65,65,65,65,65,84,65,65,65,65,65,65,65,65,65,
			65,65,65,65,1,1,1,65,1,1,1,65,1,1,1,65,65,65,65,
			65,65,65,65,1,21,1,83,1,1,1,83,1,21,1,65,65,65,65,
			65,65,65,65,1,1,1,65,1,1,1,65,1,1,1,65,65,65,65,
			65,65,65,65,65,65,65,65,1,1,1,65,65,65,65,65,65,65,65,
			65,65,65,65,65,65,65,65,1,1,1,65,65,65,65,65,65,65,65,
			65,65,65,65,1,1,1,65,1,1,1,65,1,1,1,65,65,65,65,
			65,65,65,65,1,21,1,83,1,1,1,83,1,21,1,65,65,65,65,
			65,65,65,65,1,1,1,65,1,1,1,65,1,1,1,65,65,65,65,
			65,65,65,65,65,65,65,65,65,84,65,65,65,65,65,65,65,65,65,
			65,65,65,65,65,1,1,1,1,1,1,1,1,1,65,65,65,65,65,
			65,1,1,1,65,1,21,1,1,21,1,1,21,1,65,1,1,1,65,
			65,1,21,1,83,1,1,1,1,1,1,1,1,1,83,1,21,1,65,
			65,1,1,1,65,1,21,1,1,17,1,1,21,1,65,1,1,1,65,
			65,65,65,65,65,1,1,1,1,1,1,1,1,1,65,65,65,65,65,
			65,65,65,65,65,65,65,65,65,65,65,65,65,65,65,65,65,65,65};


	//地块贴图
	@Override
	public String tilesTex() {
		return Assets.Environment.TILES_SEWERS;
	}
	//水体贴图
	@Override
	public String waterTex() {
		return Assets.Environment.WATER_SEWERS;
	}

	private static final int WIDTH = 19;
	private static final int HEIGHT = 20;

	//创建出入口
	@Override
	protected boolean build() {
		setSize(WIDTH, HEIGHT);
		//上楼梯
		entrance = WIDTH*17+9;
		//下楼梯
		exit =WIDTH*2+9 ;

		for (int map = 0; map < this.map.length; map++) this.map[map] = mapToTerrain(pre_map[map]);
		return true;
	};


	@Override
	public Mob createMob() {
		return null;
	}


	//创建生物

	@Override
	protected void createMobs() {

           // Level1Boss i= new Level1Boss();
          //  i.pos = 85;
            //mobs.add(i);


	}

	public Actor addRespawner() {
		return null;
	}






	@Override
	protected void createItems() {
//        drop(new Torch(),137).type = Heap.Type.FOR_SALE;
//        if(Badges.isUnlocked(Badges.Badge.ALL_ARMOR_IDENTIFIED)){
//            drop(new Torch(), 312);
//        }
//        drop(new Torch(), 162);
	}

	@Override
	public int randomRespawnCell( Char ch ) {
		return entrance-width();
	}







}