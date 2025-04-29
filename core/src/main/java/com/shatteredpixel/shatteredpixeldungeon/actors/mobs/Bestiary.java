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

import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.publicmob.GemElemental;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.sand.ArmorBeetle;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.sand.BoneHead;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.sand.Cube;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.sand.EmptyShell;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.sand.Beetle;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.sand.RedCrystal;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.sand.Spider;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.sand.WitheredWorm;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.sand.Worm;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.temple.Believer;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.temple.Bone;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.temple.Scavenger;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.tree.Albino;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.tree.Bat;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.tree.BleedRattan;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.tree.Gnoll;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.tree.GnollThrow;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.tree.Rattan;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.tree.Rat;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.tree.Slime;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.tree.Stake;
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

		//MobCount(mobs);

		return mobs;
	}

	private static ArrayList<Class<? extends Mob>> standardMobRotation( int depth ) {
		switch (depth) {
			default:
				return new ArrayList<>(Arrays.asList(
						//Sheep.class
						));

			case 1:
				return new ArrayList<>(Arrays.asList(
						Cube.class,
						EmptyShell.class,EmptyShell.class
						));
			case 2:
				return new ArrayList<>(Arrays.asList(
						Cube.class,
						EmptyShell.class,
						Worm.class, Worm.class
						));
			case 3:
				return new ArrayList<>(Arrays.asList(
						Worm.class,
						Beetle.class, Beetle.class,
						BoneHead.class,
						RedCrystal.class
						));
			case 4:
				return new ArrayList<>(Arrays.asList(
						RedCrystal.class,
						Spider.class,Spider.class,
						BoneHead.class
						));


			case 6:
				return new ArrayList<>(Arrays.asList(
						Rattan.class, Rattan.class,
						Rat.class
						));
			case 7:
				return new ArrayList<>(Arrays.asList(
						Rattan.class,
						Rat.class,Rat.class,
						Gnoll.class,

						Stake.class
						));
			case 8:
				return new ArrayList<>(Arrays.asList(
						Gnoll.class, Gnoll.class,
						Slime.class,
						DM100.class,
						Bat.class,

						Stake.class
						));
			case 9:
				return new ArrayList<>(Arrays.asList(
						DM100.class,
						Gnoll.class, Gnoll.class,
                        Bat.class,Bat.class,

						Stake.class
						));


				
            case 11:
				return new ArrayList<>(Arrays.asList(
						Bat.class,
						Elemental.random(),
						Believer.random()));
			case 12:
				return new ArrayList<>(Arrays.asList(
						Elemental.random(),Elemental.random(),
						Believer.random(),Believer.random()));
			case 13:
				return new ArrayList<>(Arrays.asList(
						Scavenger.class,
						Bone.class,
						Elemental.random(),Elemental.random(),
						Believer.random()));
			case 14:
				return new ArrayList<>(Arrays.asList(
						ArmoredBrute.class,
						Bone.class,
						Scavenger.class,Scavenger.class,
						Believer.random()));



			case 16:
				return new ArrayList<>(Arrays.asList(
						Ghoul.class, Ghoul.class,
						Elemental.random(), Elemental.random(),
						Warlock.class));
			case 17:
				return new ArrayList<>(Arrays.asList(
						Ghoul.class,
						Elemental.random(), Elemental.random(),
						Warlock.class,
						Monk.class));
			case 18:
				return new ArrayList<>(Arrays.asList(
						Ghoul.class,
						Elemental.random(),
						Warlock.class, Warlock.class,
						Monk.class, Monk.class,
						Golem.class));
			case 19: case 20:
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
						Scorpio.class, Scorpio.class, Scorpio.class

						));
		}
		
	}
	
	//has a chance to add a rarely spawned mobs to the rotation
	public static void addRareMobs( int depth, ArrayList<Class<?extends Mob>> rotation ){
		
		switch (depth){

			//TODO 在区域生成上个区域的生物
			
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
	
	//TODO 精英怪生成（3%）
	private static void swapMobAlts(ArrayList<Class<?extends Mob>> rotation){
		for (int i = 0; i < rotation.size(); i++){
			if (Random.Float() <= 1/20f) {
				Class<? extends Mob> cl = rotation.get(i);
				if (cl == Beetle.class) {
				    cl = ArmorBeetle.class;
				} else if (cl == Worm.class) {
					cl = WitheredWorm.class;

				} else if (cl == Gnoll.class) {
					cl = GnollThrow.class;
				} else if (cl == Rattan.class) {
					cl = BleedRattan.class;
				} else if (cl == Rat.class) {
					cl = Albino.class;
				} else if (cl == com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Slime.class) {
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
				} else if (cl == Scorpio.class) {
					cl = Acidic.class;
				}
				rotation.set(i, cl);
			}
		}
	}


	//TODO 元素生物全局替换（1/16%）
	private static void RandomMob(ArrayList<Class<? extends Mob>> rotation) {
		for (int i = 0; i < rotation.size(); i++) {
			Class<? extends Mob> cl = rotation.get(i);
            if (Random.Float() < 0.06f) {
				cl = GemElemental.random();
			}
			rotation.set(i, cl);
        }
	}


	/*
	//精英怪物限制生成
	private static void MobCount(ArrayList<Class<?extends Mob>> rotation){
			for (int i = 0; i < rotation.size(); i++) {
				Class<? extends Mob> cl = rotation.get(i);
				if (cl == GooMob.class&&Dungeon.LimitedDrops.GOO_KILL.count<2 &&Dungeon.LimitedDrops.GOO_ALL.count<1) {
					cl = GooMob.class;
					Dungeon.LimitedDrops.GOO_ALL.count++;
				} else if (cl == GooMob.class&&Dungeon.LimitedDrops.GOO_ALL.count>=1) {
					cl = Air.class;
				}
				rotation.set(i, cl);
			}
		}
	 */


}