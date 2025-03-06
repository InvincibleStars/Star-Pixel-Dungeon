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

package com.shatteredpixel.shatteredpixeldungeon.effects.particles.newparticle;

import com.shatteredpixel.shatteredpixeldungeon.effects.particles.ShadowParticle;
import com.watabou.noosa.particles.Emitter;
import com.watabou.noosa.particles.Emitter.Factory;
import com.watabou.noosa.particles.PixelParticle;

public class WitheredParticle extends PixelParticle.Shrinking {

	public static final Factory BLACK = new Factory() {
		@Override
		public void emit( Emitter emitter, int index, float x, float y ) {
			((WitheredParticle)emitter.recycle( WitheredParticle.class )).reset( x, y );
		}
		@Override
		public boolean lightMode() {
			return false;
		}
	};

	public static final Emitter.Factory UP = new Factory() {
		@Override
		public void emit( Emitter emitter, int index, float x, float y ) {
			((ShadowParticle)emitter.recycle( ShadowParticle.class )).resetUp( x, y );
		}
	};

	public WitheredParticle() {
		super();
		
		//color( 0x295894 );
		color(0);
		lifespan = 0.5F;
		
		acc.set( 0, -80 ); //80
	}
	
	public void reset( float x, float y ) {
		revive();
		
		this.x = x;
		this.y = y;
		
		left = lifespan;
		
		size = 4; //4
		speed.set( 0 ); //0
	}
	
	@Override
	public void update() {
		super.update();
		float p = left / lifespan;
		// alpha: 0 -> 1 -> 0; size: 6 -> 0; color: 0x660044 -> 0x000000
		//color( ColorMath.interpolate( 0x000000, 0x440044, p ) );
		am = p > 0.2f ? (1 - p) * 5 : 1 ;
		//am = p > 0.8f ? (1 - p) * 5 : 1;
	}
}