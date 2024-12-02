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

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.treearea;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.Statistics;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Blob;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.StormCloud;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.RotLasher;
import com.shatteredpixel.shatteredpixeldungeon.effects.Pushing;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.bossloot.BossLoot;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.RotHeartSprite;
import com.watabou.utils.Bundle;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;
import com.watabou.utils.Reflection;

import java.util.ArrayList;

public class VineDerived extends Mob {

	{
		spriteClass = RotHeartSprite.class;

		HP = HT = 30+ Random.Int(2+(BossLoot.infection*2));
		defenseSkill = 0;

		EXP = 0;

		loot = Generator.Category.SEED;
		lootChance = 0.2f;

		state = PASSIVE;

	}


	@Override
	public int defenseProc(Char enemy, int damage) {
		GameScene.add(Blob.seed(pos, 200-20*NUM, StormCloud.class));
		return super.defenseProc(enemy, 15);
	}
	public static int NUM=0;

	public int summonCooldown =0;

	@Override
	public boolean act() {
		while (summonCooldown <= 0&&NUM<3){
			//定义召唤出的生物summon=
			Class<?extends Mob> cls = regularSummons.remove(0);
			Mob summon = Reflection.newInstance(cls);
			regularSummons.add(cls);
			int spawnPos = -1;
			for (int i : PathFinder.NEIGHBOURS8){
				if (Actor.findChar(pos+i) == null&&Dungeon.level.map[pos+i]!=Terrain.WALL){
					if (spawnPos == -1 ||
		Dungeon.level.trueDistance(Dungeon.hero.pos, spawnPos) > Dungeon.level.trueDistance(this.pos, pos+i)){
						spawnPos = pos + i;
					}}}
			//生物不会生成在虚空/墙壁内
			if (spawnPos != -1 && (Dungeon.level.map[spawnPos]!= Terrain.WALL||Dungeon.level.map[spawnPos]!= Terrain.CHASM)) {
				summon.pos = spawnPos;
				GameScene.add( summon );
				Actor.addDelayed( new Pushing( summon, pos, summon.pos ), -1 );
				summon.beckon(Dungeon.hero.pos);
				summonCooldown = 15;
			} else {
				break;
			}
			NUM+=1;
		}
		summonCooldown-=1;
		if (state != SLEEPING) {
			Dungeon.level.seal();}return super.act();
	}

	private ArrayList<Class> regularSummons = new ArrayList<>();{
		for (int i = 0; i < 6; i++) {
			if (i >= Statistics.spawnersAlive) {
				regularSummons.add(RotLasher.class);
			}else {
				regularSummons.add(RotLasher.class);
			}
		}
		Random.shuffle(regularSummons);
	}

	private final String NUMA = "num";

	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
		bundle.put( NUMA , NUM  );
	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		NUM = bundle.getInt( NUMA );
	}


	//private class Waiting extends Wandering{}
}