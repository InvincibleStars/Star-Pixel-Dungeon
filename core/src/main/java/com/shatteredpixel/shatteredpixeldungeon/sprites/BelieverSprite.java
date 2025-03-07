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

package com.shatteredpixel.shatteredpixeldungeon.sprites;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Elemental;
import com.shatteredpixel.shatteredpixeldungeon.effects.Beam;
import com.shatteredpixel.shatteredpixeldungeon.effects.MagicMissile;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.ElmoParticle;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.FlameParticle;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.RainbowParticle;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.SparkParticle;
import com.shatteredpixel.shatteredpixeldungeon.tiles.DungeonTilemap;
import com.watabou.noosa.TextureFilm;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.particles.Emitter;
import com.watabou.utils.Callback;

public abstract class BelieverSprite extends MobSprite {

	protected int boltType;

	protected abstract int texOffset();

	public BelieverSprite() {
		super();

		//所有生物的起始
		int c = texOffset();

		texture( Assets.Sprites.BELIEVER );
		TextureFilm film = new TextureFilm( texture, 12, 13 );

		//在下方通过c来确定不同的同类单位使用一个动作
		idle = new Animation( 1, true );
		idle.frames( film, c, c, c, c+1, c, c, c, c, c+1 );

		run = new Animation( 15, true );
		run.frames( film, c+0, c+0, c+2, c+3, c+3, c+4 );

		die = new Animation( 10, false );
		die.frames( film, c+5, c+6, c+7, c+8, c+9 );

		attack = new Animation( 12, false );
		attack.frames( film, c+10, c+11, c+12, c );

		idle();

	}

	@Override
	public int blood() {
		return 0xFFFF0000;
	}
	
	public static class Normal extends BelieverSprite {
		
		{
			boltType = MagicMissile.FIRE;
		}
		
		@Override
		protected int texOffset() {
			return 0;
		}
	}
	
	public static class Ankh extends BelieverSprite {
		
		{
			boltType = MagicMissile.FIRE;
		}
		
		@Override
		protected int texOffset() {
			return 13;
		}
		

	}
	
	public static class Fun extends BelieverSprite {
		
		{
			boltType = MagicMissile.FROST;
		}
		
		@Override
		protected int texOffset() {
			return 26;
		}
	}
}
