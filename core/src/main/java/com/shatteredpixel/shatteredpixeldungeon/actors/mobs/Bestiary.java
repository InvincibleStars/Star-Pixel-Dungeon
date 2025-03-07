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

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.Air;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.Sheep;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.publicmob.GemElemental;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.sandarea.BlackCube;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.sandarea.BlackWorm;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.sandarea.LifeSand;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.sandarea.RockBug;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.sandarea.SandCrab;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.sandarea.SandScorpion;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.sandarea.SandWorm;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.sandarea.TestBug;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.sandarea.update.RockBug2;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.sandarea.update.SandWorm2;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.temple.Believer;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.treearea.Bat;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.treearea.Gnoll;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.treearea.GnollThrow;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.treearea.Rattan;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.treearea.Rat;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.treearea.SplitSlime;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.treearea.VineDerived;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.treearea.WoodenCross;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.treearea.Wsddc;
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

		MobCount(mobs);

		return mobs;
	}
	
	//returns a rotation of standard mobs, unshuffled.
	private static ArrayList<Class<? extends Mob>> standardMobRotation( int depth ) {
		switch (depth) {

			//TODO 地牢区域生物设置

			// 荒漠
			default:
				return new ArrayList<>(Arrays.asList(
						WoodenCross.class,
						TestBug.class
				));

			case 1:
				//2x黑晶虫，1x沙虫
				return new ArrayList<>(Arrays.asList(
						BlackCube.class, BlackWorm.class, SandWorm.class
						//Guard.class
						//Rat.class, Rat.class,Rat.class, Rat.class
						));
			case 2:
				//2x黑晶虫，2x沙虫，1x甲虫
				return new ArrayList<>(Arrays.asList(
						BlackCube.class,
						BlackWorm.class,BlackWorm.class,
						SandWorm.class, SandWorm.class,
						RockBug.class
						));
			case 3:
				//2x黑晶虫，1x沙虫，1x甲虫，1x激光蟹，1x流沙
				return new ArrayList<>(Arrays.asList(
						SandWorm.class,
						BlackCube.class,BlackCube.class,
						BlackWorm.class,
						RockBug.class,RockBug.class,
						LifeSand.class,
						SandCrab.class));
			case 4:
				//1x甲虫，1x沙虫，1x激光蟹，1x蝎子
				return new ArrayList<>(Arrays.asList(
						SandWorm.class,
						BlackCube.class,
						RockBug.class,
						SandCrab.class,
						SandScorpion.class));
			case 5:
				return new ArrayList<>(Arrays.asList());
				
			// 林地
			case 6:
				//1x 老鼠, 2x 活化植物, 1x 十字架
				return new ArrayList<>(Arrays.asList(
						Goo.class,
						Rat.class,
						Rattan.class, Rattan.class,
						WoodenCross.class));
			case 7:
				//1x 活化植物, 2x 老鼠, 1x 近战豺狼，1x 十字架
				return new ArrayList<>(Arrays.asList(
						Goo.class,
						Rattan.class,
						DM100.class,
						Rat.class,Rat.class,
						WoodenCross.class));
			case 8:
				//1x 活化植物, 1x 老鼠, 2x 近战豺狼, 1x 豺狼飞槌手, 1x 十字架
				return new ArrayList<>(Arrays.asList(
						Goo.class,
						VineDerived.class,
						Rat.class,
						DM100.class,
						Gnoll.class, Gnoll.class,
						GnollThrow.class,
						WoodenCross.class));
			case 9: case 10:
				//2x 豺狼, 2x 豺狼飞槌手, 1x 丛林史莱姆, 1x 十字架, 1x 蝙蝠
				return new ArrayList<>(Arrays.asList(
						Goo.class,
						VineDerived.class,
						Gnoll.class,
						DM100.class,
						GnollThrow.class,
						SplitSlime.class,
						WoodenCross.class,
                        Bat.class));
				
			// Caves
			case 11:
				//3x bat, 1x brute, 1x shaman
				return new ArrayList<>(Arrays.asList(
						Bat.class, Bat.class, Bat.class,
						GemElemental.random(),
						Believer.random()));
			case 12:
				//2x bat, 2x brute, 1x shaman, 1x spinner
				return new ArrayList<>(Arrays.asList(
						GemElemental.random(),
						Believer.random(),Believer.random()));
			case 13:
				//1x bat, 2x brute, 2x shaman, 2x spinner, 1x DM-200
				return new ArrayList<>(Arrays.asList(
						DM100.class,
						ArmoredBrute.class,
						Believer.random()));
			case 14: case 15:
				//1x bat, 1x brute, 2x shaman, 2x spinner, 2x DM-300
				return new ArrayList<>(Arrays.asList(
						Guard.class,
						ArmoredBrute.class,
						Believer.random()));
				
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
						TestBug.class
						/*
						Succubus.class,
						Eye.class, Eye.class,
						Scorpio.class, Scorpio.class, Scorpio.class
						*/
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
	
	//TODO 精英怪生成（2%）
	private static void swapMobAlts(ArrayList<Class<?extends Mob>> rotation){
		for (int i = 0; i < rotation.size(); i++){
			if (Random.Int( 30 ) == 0) {
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

	//TODO 元素生物全局替换（9%）
	private static void RandomMob(ArrayList<Class<? extends Mob>> rotation) {
		for (int i = 0; i < rotation.size(); i++) {
			Class<? extends Mob> cl = rotation.get(i);
            if (Random.Float() < 0.09f) {
				cl = GemElemental.random();
			}
			rotation.set(i, cl);
        }
	}

	//精英怪物限制生成
	private static void MobCount(ArrayList<Class<?extends Mob>> rotation){
			for (int i = 0; i < rotation.size(); i++) {
				Class<? extends Mob> cl = rotation.get(i);
				if (cl == Goo.class&&Dungeon.LimitedDrops.GOO_KILL.count<2&&Dungeon.LimitedDrops.GOO_ALL.count<1) {
					cl = Goo.class;
					Dungeon.LimitedDrops.GOO_ALL.count++;
				} else if (cl == Goo.class&&Dungeon.LimitedDrops.GOO_ALL.count>=1) {
					cl = Air.class;
				}
				rotation.set(i, cl);
			}
		}


}