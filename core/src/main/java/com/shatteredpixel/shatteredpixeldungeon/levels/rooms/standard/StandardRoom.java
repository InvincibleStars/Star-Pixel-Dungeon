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

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.levels.rooms.Room;
import com.shatteredpixel.shatteredpixeldungeon.levels.rooms.newrooms.equipspecials.EquipRoom;
import com.shatteredpixel.shatteredpixeldungeon.levels.rooms.special.MassGraveRoom;
import com.watabou.utils.Point;
import com.watabou.utils.Random;
import com.watabou.utils.Reflection;

import java.util.ArrayList;

public abstract class StandardRoom extends Room {

	public enum SizeCategory {

		NORMAL(4, 10, 1),
		LARGE(10, 14, 2),
		GIANT(14, 18, 3);

		public final int minDim, maxDim;
		public final int roomValue;

		SizeCategory(int min, int max, int val){
			minDim = min;
			maxDim = max;
			roomValue = val;
		}

		public int connectionWeight(){
			return roomValue*roomValue;
		}

	}

	public SizeCategory sizeCat;
	{ setSizeCat(); }

	//Note that if a room wishes to allow itself to be forced to a certain size category,
	//but would (effectively) never roll that size category, consider using Float.MIN_VALUE
	public float[] sizeCatProbs(){
		//always normal by default
		return new float[]{1, 0, 0};
	}

	public boolean setSizeCat(){
		return setSizeCat(0, SizeCategory.values().length-1);
	}

	//assumes room value is always ordinal+1
	public boolean setSizeCat( int maxRoomValue ){
		return setSizeCat(0, maxRoomValue-1);
	}

	//returns false if size cannot be set
	public boolean setSizeCat( int minOrdinal, int maxOrdinal ) {
		float[] probs = sizeCatProbs();
		SizeCategory[] categories = SizeCategory.values();

		if (probs.length != categories.length) return false;

		for (int i = 0; i < minOrdinal; i++)                    probs[i] = 0;
		for (int i = maxOrdinal+1; i < categories.length; i++)  probs[i] = 0;

		int ordinal = Random.chances(probs);

		if (ordinal != -1){
			sizeCat = categories[ordinal];
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int minWidth() { return sizeCat.minDim; }
	public int maxWidth() { return sizeCat.maxDim; }

	@Override
	public int minHeight() { return sizeCat.minDim; }
	public int maxHeight() { return sizeCat.maxDim; }

	@Override
	public boolean canMerge(Level l, Point p, int mergeTerrain) {
		int cell = l.pointToCell(pointInside(p, 1));
		return (Terrain.flags[l.map[cell]] & Terrain.SOLID) == 0;
	}

	//FIXME this is a very messy way of handing variable standard rooms
	private static ArrayList<Class<?extends StandardRoom>> rooms = new ArrayList<>();
	static {
		rooms.add(EmptyRoom.class);				//空房间（权重锁定10）


		rooms.add(SewerPipeRoom.class);			//管道房间（中间有水）
		rooms.add(RingRoom.class);				//巨型支撑柱房间(中间通常有一个大型的支撑柱
		rooms.add(CircleBasinRoom.class);		//环形房间，但中间有十字木地板以及支撑柱作为支撑（小房间没有）

		rooms.add(SegmentedRoom.class);			//分段房间
		rooms.add(PillarsRoom.class);			//带有支撑柱的房间（大型房间四个大的小型房间两个小的）
		rooms.add(CellBlockRoom.class);			//监狱牢房

		rooms.add(CaveRoom.class);				//矿洞房间
		rooms.add(CavesFissureRoom.class);		//矿洞-分割成三块且有木板连接的房间
		rooms.add(CirclePitRoom.class);			//矿洞-圆形中空（规则）房间

		rooms.add(HallwayRoom.class);			//矮人-有地毯连接出口的房间（中间有雕像）
		rooms.add(StatuesRoom.class);			//矮人-雕像房间
		rooms.add(SegmentedLibraryRoom.class);	//矮人-迷宫型图书馆

		rooms.add(RuinsRoom.class);				//废墟房间
		rooms.add(ChasmRoom.class);				//充满裂隙的普通房间
		rooms.add(SkullsRoom.class);			//5区-的头骨柱房间


		rooms.add(PlantsRoom.class);			//植物园（田字形的）
		rooms.add(AquariumRoom.class);			//食人鱼房间
		rooms.add(PlatformRoom.class);			//木地板房间
		rooms.add(BurnedRoom.class);			//燃烧陷阱房间
		rooms.add(FissureRoom.class);			//中间有裂隙的房间
		rooms.add(GrassyGraveRoom.class);		//墓碑房间
		rooms.add(StripedRoom.class);			//条纹/环形植被（有木地板间隔的）
		rooms.add(StudyRoom.class);				//图书馆（中间有基座）
		rooms.add(SuspiciousChestRoom.class);	//带基座的房间（上有宝箱
		rooms.add(MinefieldRoom.class);			//爆炸陷阱房

		rooms.add(MassGraveRoom.class);
		//rooms.add(EquipRoom.class);

	}

	private static float[][] chances = new float[27][];
	static {
		chances[1] =  new float[]{10,	0,0,0,	10,0,2,		0,0,0,		0,0,0,		0,0,0,		0,0,1,0,1,0,0,0,10,0	,0};
		chances[5] =  chances[4] =  chances[3] = chances[2] = chances[1];

		chances[6] =  new float[]{0,	0,0,2,	0,0,0,		0,10,0,		0,0,0,		10,0,0,		1,1,0,0,0,0,0,1,1,0		,0};
		chances[10] = chances[9] = chances[8] = chances[7] = chances[6];

		chances[11] = new float[]{0,  	10,0,0, 	0,10,0, 	0,0,0, 	0,0,2, 		0,0,1, 		0,0,0,0,0,1,0,1,0,0		,1};
		chances[15] = chances[14] = chances[13] = chances[12] = chances[11];

		chances[16] = new float[]{10,  	0,0,0, 	0,0,0, 		0,0,0, 		10,10,5, 	0,0,0,  	1,1,1,1,1,1,1,1,1,1		,0};
		chances[20] = chances[19] = chances[18] = chances[17] = chances[16];

		chances[21] = new float[]{10,  	0,0,0, 	0,0,0, 		0,0,0, 		0,0,0, 		10,10,5,  	1,1,1,1,1,1,1,1,1,1		,0};
		chances[26] = chances[25] = chances[24] = chances[23] = chances[22] = chances[21];
	}
	
	
	public static StandardRoom createRoom(){
		return Reflection.newInstance(rooms.get(Random.chances(chances[Dungeon.depth])));
	}
	
}