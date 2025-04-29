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

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.room;

import static com.shatteredpixel.shatteredpixeldungeon.Dungeon.depth;
import static com.shatteredpixel.shatteredpixeldungeon.Dungeon.level;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.Statistics;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Blob;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.StormCloud;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.RotLasher;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.NPC;
import com.shatteredpixel.shatteredpixeldungeon.effects.Pushing;
import com.shatteredpixel.shatteredpixeldungeon.items.EnergyCrystal;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Gold;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfMagicMapping;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.PotSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.RotHeartSprite;
import com.watabou.utils.Bundle;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;
import com.watabou.utils.Reflection;

import java.util.ArrayList;

public class ItemBox extends NPC {

	{
		spriteClass = PotSprite.class;
		HP=HT=100;
		state = PASSIVE;
		baseSpeed = 0f;

		properties.add(Property.NOHP);

	}

	@Override
	public boolean act(){
		//super.act();
		state=PASSIVE;
		baseSpeed=0;
		return super.act();
	}

	@Override //交互死亡
	public boolean interact(Char c) {
		die(true);
		return true;
	}

	@Override  //屏蔽指示器
	public void beckon(int cell){
	}

	private float chance_one = 0.5f;

	@Override
	public void die( Object cause ) {
		super.die(cause);
			switch (depth) {
				default:
					level.drop(new Gold().quantity(1), pos).sprite.drop();
					break;
				case 1: case 2: case 3: case 4: case 5:
					level.drop(new EnergyCrystal().quantity(Random.Int(1,3)), pos).sprite.drop();
					level.drop(new Gold().quantity(Random.Int(50)), pos).sprite.drop();
					break;
				case 6: case 7: case 8: case 9: case 10:
					level.drop(new ScrollOfMagicMapping().identify(), pos).sprite.drop();
					break;
		}
	}

	public static class VineDerived extends Mob {

		{
			spriteClass = RotHeartSprite.class;


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
					if (Actor.findChar(pos+i) == null&& level.map[pos+i]!= Terrain.WALL){
						if (spawnPos == -1 ||
			level.trueDistance(Dungeon.hero.pos, spawnPos) > level.trueDistance(this.pos, pos+i)){
							spawnPos = pos + i;
						}}}
				//生物不会生成在虚空/墙壁内
				if (spawnPos != -1 && (level.map[spawnPos]!= Terrain.WALL|| level.map[spawnPos]!= Terrain.CHASM)) {
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
				level.seal();}return super.act();
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
}