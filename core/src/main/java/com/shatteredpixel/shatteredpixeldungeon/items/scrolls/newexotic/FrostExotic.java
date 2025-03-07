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

package com.shatteredpixel.shatteredpixeldungeon.items.scrolls.newexotic;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.newbuff.Frost2;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.effects.SpellSprite;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.HeroSprite;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;

public class FrostExotic extends ExoticScrollModel {

	@Override
	public void execute( Hero hero, String action ) {

		super.execute( hero, action );

		if (action.equals( AC_READ )) {
			((HeroSprite)curUser.sprite).read();
			hero.spend( TIME );
			hero.busy();
			for (Mob mob : Dungeon.level.mobs.toArray( new Mob[0] )) {
				//mob.beckon( curUser.pos );
				if (mob.alignment != Char.Alignment.ALLY && Dungeon.level.heroFOV[mob.pos]) {
					Buff.prolong(mob, Frost2.class, 18f);

				}
			}
			this.detach(hero.belongings.backpack);

			GLog.w( Messages.get(this, "roar") );
			identify();

			curUser.sprite.centerEmitter().start( Speck.factory( Speck.SCREAM ), 0.3f, 3 );
			Sample.INSTANCE.play( Assets.Sounds.CHALLENGE );



			int length = Dungeon.level.length();
			int[] map = Dungeon.level.map;
			boolean[] mapped = Dungeon.level.mapped;
			boolean[] discoverable = Dungeon.level.discoverable;

			boolean noticed = false;

			for (int i=0; i < length; i++) {

				int terr = map[i];

				if (discoverable[i]) {

					mapped[i] = true;
					if ((Terrain.flags[terr] & Terrain.SECRET) != 0) {

						Dungeon.level.discover( i );

						if (Dungeon.level.heroFOV[i]) {
							GameScene.discoverTile( i, terr );

							noticed = true;
						}
					}
				}
			}
			GameScene.updateFog();

			GLog.i( Messages.get(this, "layout") );
			if (noticed) {
				Sample.INSTANCE.play( Assets.Sounds.SECRET );
			}

			SpellSprite.show( curUser, SpellSprite.MAP );
			Sample.INSTANCE.play( Assets.Sounds.READ );

			identify();



		}
	}

}