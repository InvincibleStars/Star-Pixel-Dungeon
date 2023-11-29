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

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.newmob.sandarea.BlackWorm;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.newmob.sandarea.SandScorpion;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.newmob.sandarea.RockBug;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.newmob.sandarea.e.RockBug2;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.newmob.sandarea.LifeSand;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.newmob.sandarea.SandCrab;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.newmob.sandarea.SandWorm;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.newmob.sandarea.e.SandWorm2;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.newmob.treearea.Gnoll;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.Arrays;

public class Bestiary {
	
	public static ArrayList<Class<? extends Mob>> getMobRotation( int depth ){
		ArrayList<Class<? extends Mob>> mobs = standardMobRotation( depth );
		addRareMobs(depth, mobs);
		swapMobAlts(mobs);
		RandomMob(mobs);
		Random.shuffle(mobs);
		return mobs;
	}
	
	//returns a rotation of standard mobs, unshuffled.
	private static ArrayList<Class<? extends Mob>> standardMobRotation( int depth ){
		switch(depth){
			
			// 沙暴大地
			case 0: default:
				return new ArrayList<>(Arrays.asList());
			case 1:
				return new ArrayList<>(Arrays.asList(
						SandWorm.class, SandWorm.class,SandWorm.class,
						BlackWorm.class,BlackWorm.class,
						SandCrab.class, SandCrab.class
						));
			case 2:
				return new ArrayList<>(Arrays.asList(
						SandWorm.class, SandWorm.class,
						BlackWorm.class,
						RockBug.class,RockBug.class
						));
			case 3:
				return new ArrayList<>(Arrays.asList(
						SandWorm.class,
						RockBug.class,RockBug.class,
						BlackWorm.class,
						SandScorpion.class,
						SandCrab.class));
			case 4:
				return new ArrayList<>(Arrays.asList(
						SandWorm.class, RockBug.class,
						SandCrab.class, SandCrab.class,
						SandScorpion.class, SandScorpion.class,
						LifeSand.class));
			case 5:
				return new ArrayList<>(Arrays.asList());
				
			// Prison
			case 6:
				//3x skeleton, 1x thief, 1x swarm
				return new ArrayList<>(Arrays.asList(
						Skeleton.class, Skeleton.class, Skeleton.class,
						Thief.class,
						Swarm.class));
			case 7:
				//3x skeleton, 1x thief, 1x DM-100, 1x guard
				return new ArrayList<>(Arrays.asList(
						Skeleton.class, Skeleton.class, Skeleton.class,
						Thief.class,
						DM100.class,
						Guard.class));
			case 8:
				//2x skeleton, 1x thief, 2x DM-100, 2x guard, 1x necromancer
				return new ArrayList<>(Arrays.asList(
						Skeleton.class, Skeleton.class,
						Thief.class,
						DM100.class, DM100.class,
						Guard.class, Guard.class,
						Necromancer.class));
			case 9: case 10:
				//1x skeleton, 1x thief, 2x DM-100, 2x guard, 2x necromancer
				return new ArrayList<>(Arrays.asList(
						Skeleton.class,
						Thief.class,
						DM100.class, DM100.class,
						Guard.class, Guard.class,
						Necromancer.class, Necromancer.class));
				
			// Caves
			case 11:
				//3x bat, 1x brute, 1x shaman
				return new ArrayList<>(Arrays.asList(
						Bat.class, Bat.class, Bat.class,
						Brute.class,
						Shaman.random()));
			case 12:
				//2x bat, 2x brute, 1x shaman, 1x spinner
				return new ArrayList<>(Arrays.asList(
						Bat.class, Bat.class,
						Brute.class, Brute.class,
						Shaman.random(),
						Spinner.class));
			case 13:
				//1x bat, 2x brute, 2x shaman, 2x spinner, 1x DM-200
				return new ArrayList<>(Arrays.asList(
						Bat.class,
						Brute.class, Brute.class,
						Shaman.random(), Shaman.random(),
						Spinner.class, Spinner.class,
						DM200.class));
			case 14: case 15:
				//1x bat, 1x brute, 2x shaman, 2x spinner, 2x DM-300
				return new ArrayList<>(Arrays.asList(
						Bat.class,
						Brute.class,
						Shaman.random(), Shaman.random(),
						Spinner.class, Spinner.class,
						DM200.class, DM200.class));
				
			// City
			case 16:
				//2x ghoul, 2x elemental, 1x warlock
				return new ArrayList<>(Arrays.asList(
						Ghoul.class, Ghoul.class,
						Elemental.random(), Elemental.random(),
						Warlock.class));
			case 17:
				//1x ghoul, 2x elemental, 1x warlock, 1x monk
				return new ArrayList<>(Arrays.asList(
						Ghoul.class,
						Elemental.random(), Elemental.random(),
						Warlock.class,
						Monk.class));
			case 18:
				//1x ghoul, 1x elemental, 2x warlock, 2x monk, 1x golem
				return new ArrayList<>(Arrays.asList(
						Ghoul.class,
						Elemental.random(),
						Warlock.class, Warlock.class,
						Monk.class, Monk.class,
						Golem.class));
			case 19: case 20:
				//1x elemental, 2x warlock, 2x monk, 3x golem
				return new ArrayList<>(Arrays.asList(
						Elemental.random(),
						Warlock.class, Warlock.class,
						Monk.class, Monk.class,
						Golem.class, Golem.class, Golem.class));
				
			// Halls
			case 21:
				//2x succubus, 1x evil eye
				return new ArrayList<>(Arrays.asList(
						Succubus.class, Succubus.class,
						Eye.class));
			case 22:
				//1x succubus, 1x evil eye
				return new ArrayList<>(Arrays.asList(
						Succubus.class,
						Eye.class));
			case 23:
				//1x succubus, 2x evil eye, 1x scorpio
				return new ArrayList<>(Arrays.asList(
						Succubus.class,
						Eye.class, Eye.class,
						Scorpio.class));
			case 24: case 25: case 26:
				//1x succubus, 2x evil eye, 3x scorpio
				return new ArrayList<>(Arrays.asList(
						Succubus.class,
						Eye.class, Eye.class,
						Scorpio.class, Scorpio.class, Scorpio.class));
		}
		
	}
	
	//has a chance to add a rarely spawned mobs to the rotation
	public static void addRareMobs( int depth, ArrayList<Class<?extends Mob>> rotation ){
		
		switch (depth){
			
			// Sewers
			default:
				return;
			case 4:
				if (Random.Float() < 0.025f) rotation.add(Gnoll.class);
				return;
				
			// Prison
			case 9:
				if (Random.Float() < 0.025f) rotation.add(Bat.class);
				return;
				
			// Caves
			case 14:
				if (Random.Float() < 0.025f) rotation.add(Ghoul.class);
				return;
				
			// City
			case 19:
				if (Random.Float() < 0.025f) rotation.add(Succubus.class);
				return;
		}
	}
	
	//精英怪生成（5%）
	private static void swapMobAlts(ArrayList<Class<?extends Mob>> rotation){
		for (int i = 0; i < rotation.size(); i++){
			if (Random.Int( 19 ) == 0) {
				Class<? extends Mob> cl = rotation.get(i);
				if (cl == RockBug.class) {
				    cl = RockBug2.class;
				} else if (cl == SandWorm.class) {
					cl = SandWorm2.class;
				} else if (cl == Slime.class) {
				    cl = CausticSlime.class;
				} else if (cl == Thief.class) {
				    cl = Bandit.class;
				} else if (cl == Necromancer.class){
				    cl = SpectralNecromancer.class;
				} else if (cl == Brute.class) {
				    cl = ArmoredBrute.class;
				} else if (cl == DM200.class) {
				    cl = DM201.class;
				} else if (cl == Monk.class) {
				    cl = Senior.class;
				} else if (cl == Scorpio.class)
				{ cl = Acidic.class;
				}
				rotation.set(i, cl);
			}
		}
	}

	//替代生成（针对多色灌木等）
	private static void RandomMob(ArrayList<Class<?extends Mob>> rotation){
		for (int i = 0; i < rotation.size(); i++){
			if (Random.Int( 2 ) == 0) {
				Class<? extends Mob> cl = rotation.get(i);
				if (cl == RockBug.class) {
					if (Random.Int( 1 ) == 0) {
						cl = RockBug.class;
					}
				} else if (cl == Scorpio.class)
				{ cl = Scorpio.class;
				}
				rotation.set(i, cl);
			}
		}
	}
}
