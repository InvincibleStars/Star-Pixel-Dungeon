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

package com.shatteredpixel.shatteredpixeldungeon.levels.features;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Barkskin;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.ArmoredStatue;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.LeafParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.Dewdrop;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Wafer;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Camouflage;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.DriedRose;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.SandalsOfNature;
import com.shatteredpixel.shatteredpixeldungeon.items.food.Berry;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.watabou.utils.Random;

public class HighGrass {

	//prevents items dropped from grass, from trampling that same grass.
	//yes this is a bit ugly, oh well.
	private static boolean freezeTrample = false;

	public static void trample( Level level, int pos ) {

		if (freezeTrample) return;

		Char ch = Actor.findChar(pos);
/*
		//植被交互动作
		if (level.map[pos] == Terrain.FURROWED_GRASS){
			//经过枯草无事发生
				freezeTrample = true;
			GameScene.updateMap(pos);
		} else {
			//经过普通高草将其变为枯草
			//Level.set(pos, Terrain.FURROWED_GRASS);
			Level.set(pos, Terrain.FURROWED_GRASS);
			GameScene.updateMap(pos);
			freezeTrample = true;
		*/

		if (level.map[pos] == Terrain.FURROWED_GRASS){
			//生物不会破坏植被
			if ( ch instanceof Hero|| ch instanceof Mob){
				//Do nothing
				freezeTrample = true;
			} else {
				Level.set(pos, Terrain.GRASS);
			}

		} else {
			if (ch instanceof Hero|| ch instanceof Mob) {
				Level.set(pos, Terrain.FURROWED_GRASS);
				freezeTrample = true;
			} else {
				Level.set(pos, Terrain.GRASS);
			}


			if (level.map[pos] == Terrain.HIGH_GRASS
					&& Dungeon.level.heroFOV[pos]
					&& Dungeon.hero.hasTalent(Talent.GRASS_ARMOR)
					&&Dungeon.hero.pointsInTalent(Talent.GRASS_ARMOR)==1){
				// 3/5 turns based on talent points spent
				Buff.affect(Dungeon.hero, Barkskin.class).set(4, 1 + 2*(Dungeon.hero.pointsInTalent(Talent.GRASS_ARMOR)));
			}
			if (level.map[pos] == Terrain.FURROWED_GRASS
					&& Dungeon.level.heroFOV[pos]
					&& Dungeon.hero.hasTalent(Talent.GRASS_ARMOR)
					&&Dungeon.hero.pointsInTalent(Talent.GRASS_ARMOR)==2){
				// 3/5 turns based on talent points spent
				Buff.affect(Dungeon.hero, Barkskin.class).set(4, 1 + 2*(Dungeon.hero.pointsInTalent(Talent.GRASS_ARMOR)));
			}







			int naturalismLevel = 0;

			if (ch != null) {
				SandalsOfNature.Naturalism naturalism = ch.buff( SandalsOfNature.Naturalism.class );
				if (naturalism != null) {
					if (!naturalism.isCursed()) {
						naturalismLevel = naturalism.itemLevel() + 1;
						naturalism.charge(1);
					} else {
						naturalismLevel = -1;
					}
				}

				//berries try to drop on floors 2/3/4/6/7/8, to a max of 4/6
				Talent.NatureBerriesAvailable berries = ch.buff(Talent.NatureBerriesAvailable.class);
				if (berries != null) {
					int targetFloor = 4 + ((Hero)ch).pointsInTalent(Talent.NATURES_BOUNTY);
					targetFloor -= berries.count();
					targetFloor += (targetFloor >= 5) ? 3 : 2;

					//If we're behind: 1/10, if we're on page: 1/30, if we're ahead: 1/90
					boolean droppingBerry = false;
					if (Dungeon.depth > targetFloor)        droppingBerry = Random.Int(10) == 0;
					else if (Dungeon.depth == targetFloor)  droppingBerry = Random.Int(30) == 0;
					else if (Dungeon.depth < targetFloor)   droppingBerry = Random.Int(90) == 0;

					if (droppingBerry){
						berries.countDown(1);
						level.drop(new Berry(), pos).sprite.drop();
						if (berries.count() <= 0){
							berries.detach();
						}
					}

				}
			}

			if (naturalismLevel >= 0) {
				if (Random.Int(7) == 0) {
					level.drop(Generator.random(Generator.Category.SEED), pos).sprite.drop();
				}
				if (Random.Int(7 ) == 0) {
					level.drop(new Dewdrop(), pos).sprite.drop();
				}
				if (Random.Int(9 ) == 0) {
					level.drop(new Wafer(), pos).sprite.drop();
				}
			}

			//Camouflage
			if (ch instanceof Hero) {
				Hero hero = (Hero) ch;
				if (hero.belongings.armor() != null && hero.belongings.armor().hasGlyph(Camouflage.class, hero)) {
					Camouflage.activate(hero, hero.belongings.armor.buffedLvl());
				}
			} else if (ch instanceof DriedRose.GhostHero){
				DriedRose.GhostHero ghost = (DriedRose.GhostHero) ch;
				if (ghost.armor() != null && ghost.armor().hasGlyph(Camouflage.class, ghost)){
					Camouflage.activate(ghost, ghost.armor().buffedLvl());
				}
			} else if (ch instanceof ArmoredStatue){
				ArmoredStatue statue = (ArmoredStatue) ch;
				if (statue.armor() != null && statue.armor().hasGlyph(Camouflage.class, statue)){
					Camouflage.activate(statue, statue.armor().buffedLvl());
				}
			}

		}

		freezeTrample = false;

		if (ShatteredPixelDungeon.scene() instanceof GameScene) {
			GameScene.updateMap(pos);

			CellEmitter.get(pos).burst(LeafParticle.LEVEL_SPECIFIC, 4);
			if (Dungeon.level.heroFOV[pos]) Dungeon.observe();
		}
	}
}