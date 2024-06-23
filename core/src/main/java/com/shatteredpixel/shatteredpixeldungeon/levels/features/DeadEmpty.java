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
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Weakness;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.ArmoredStatue;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.LeafParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Camouflage;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.DriedRose;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;

public class DeadEmpty {

	//prevents items dropped from grass, from trampling that same grass.
	//yes this is a bit ugly, oh well.
	private static boolean freezeTrample = false;

	public static void trample( Level level, int pos ) {



		Char ch = Actor.findChar(pos);

		if (level.map[pos] == Terrain.DEADEMPTY){
			//生物不会破坏植被
			if ( ch instanceof Hero){
				if (ch.buff( Weakness.class ) == null) {
					GLog.n( Messages.get(DeadEmpty.class, "warm") );
				}
				Buff.affect(ch,Weakness.class,10.0f);
			} else if (ch instanceof Mob) {
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

		if (ShatteredPixelDungeon.scene() instanceof GameScene) {
			GameScene.updateMap(pos);

			CellEmitter.get(pos).burst(LeafParticle.LEVEL_SPECIFIC, 4);
			if (Dungeon.level.heroFOV[pos]) Dungeon.observe();
		}
	}
}