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

package com.shatteredpixel.shatteredpixeldungeon.levels.arealevel;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.Ghost;
import com.shatteredpixel.shatteredpixeldungeon.effects.Ripple;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.levels.RegularLevel;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.levels.painters.Painter;
import com.shatteredpixel.shatteredpixeldungeon.levels.painters.SewerPainter;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.AlarmTrap;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.ToxicTrap;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.tiles.DungeonTilemap;
import com.watabou.noosa.Game;
import com.watabou.noosa.Group;
import com.watabou.noosa.particles.Emitter;
import com.watabou.noosa.particles.PixelParticle;
import com.watabou.utils.ColorMath;
import com.watabou.utils.PointF;
import com.watabou.utils.Random;

public class SandAreaLevel extends RegularLevel {

	{
		color1 = 0x48763c;
		color2 = 0x59994a;
	}
	
	@Override
	protected int standardRooms(boolean forceMax) {
		if (forceMax) return 6;
		//3-6
		return 7+Random.chances(new float[]{1, 1, 1});
	}
	
	@Override
	protected int specialRooms(boolean forceMax) {
		if (forceMax) return 2;
		//1-4
		return 2+Random.chances(new float[]{1, 1, 1});
	}
	
	@Override
	protected Painter painter() {
		return new SewerPainter()
				.setWater(feeling == Feeling.WATER ? 0.05f : 0.03f, 0)
				.setGrass(feeling == Feeling.GRASS ? 0.12f : 0.09f, 0)
				.setTraps(nTraps(), trapClasses(), trapChances());
	}
	
	@Override
	public String tilesTex() {
		return Assets.Environment.TILES_SEWERS;
	}
	
	@Override
	public String waterTex() {
		return Assets.Environment.WATER_SEWERS;
	}

	@Override //陷阱生成
	protected Class<?>[] trapClasses() {
		return Dungeon.depth == 1 ?
				new Class<?>[]{ ToxicTrap.class } :
				new Class<?>[]{ToxicTrap.class, AlarmTrap.class,};
	}

	@Override //生成权重
	protected float[] trapChances() {
		return Dungeon.depth == 1 ?
				new float[]{7} :
				new float[]{7, 7};
	}
	
	@Override
	protected void createItems() {
		Ghost.Quest.spawn( this );
		
		super.createItems();
	}
	
	@Override
	public Group addVisuals() {
		super.addVisuals();
		addSewerVisuals(this, visuals);
		return visuals;
	}
	
	public static void addSewerVisuals(Level level, Group group ) {
		for (int i=0; i < level.length(); i++) {
			if (level.map[i] == Terrain.WALL_DECO) {
				group.add( new Sink( i ) );
			}
		}
	}
	
	@Override
	public String tileName( int tile ) {
		switch (tile) {
			case Terrain.WATER:
				return Messages.get(SandAreaLevel.class, "water_name");
			case Terrain.DOOR:
				return Messages.get(SandAreaLevel.class, "door_name");
			case Terrain.ENTRANCE:
				return Messages.get(SandAreaLevel.class, "entrance_name");
			case Terrain.EXIT:
				return Messages.get(SandAreaLevel.class, "exit_name");
			case Terrain.GRASS:
				return Messages.get(SandAreaLevel.class, "grass_name");
			case Terrain.HIGH_GRASS:
				return Messages.get(SandAreaLevel.class, "high_grass_name");
			case Terrain.WALL:
				return Messages.get(SandAreaLevel.class, "wall_name");
			default:
				return super.tileName( tile );
		}
	}
	
	@Override
	public String tileDesc(int tile) {
		switch (tile) {
			case Terrain.EMPTY_DECO:
				return Messages.get(SandAreaLevel.class, "empty_deco_desc");
			case Terrain.BOOKSHELF:
				return Messages.get(SandAreaLevel.class, "bookshelf_desc");
			case Terrain.DOOR:
				return Messages.get(SandAreaLevel.class, "door_desc");
			case Terrain.WATER:
				return Messages.get(SandAreaLevel.class, "water_desc");
			case Terrain.ENTRANCE:
				return Messages.get(SandAreaLevel.class, "entrance_desc");
			case Terrain.EXIT:
				return Messages.get(SandAreaLevel.class, "exit_desc");
			case Terrain.GRASS:
				return Messages.get(SandAreaLevel.class, "grass_desc");
			case Terrain.HIGH_GRASS:
				return Messages.get(SandAreaLevel.class, "high_grass_desc");
			case Terrain.WALL:
				return Messages.get(SandAreaLevel.class, "wall_desc");
			default:
				return super.tileDesc( tile );
		}
	}
	
	private static class Sink extends Emitter {
		
		private int pos;
		private float rippleDelay = 0;
		
		private static final Emitter.Factory factory = new Factory() {
			
			@Override
			public void emit( Emitter emitter, int index, float x, float y ) {
				WaterParticle p = (WaterParticle)emitter.recycle( WaterParticle.class );
				p.reset( x, y );
			}
		};
		
		public Sink( int pos ) {
			super();
			
			this.pos = pos;
			
			PointF p = DungeonTilemap.tileCenterToWorld( pos );
			pos( p.x - 2, p.y + 3, 4, 0 );
			
			pour( factory, 0.1f );
		}
		
		@Override
		public void update() {
			if (visible = (pos < Dungeon.level.heroFOV.length && Dungeon.level.heroFOV[pos])) {
				
				super.update();
				
				if (!isFrozen() && (rippleDelay -= Game.elapsed) <= 0) {
					Ripple ripple = GameScene.ripple( pos + Dungeon.level.width() );
					if (ripple != null) {
						ripple.y -= DungeonTilemap.SIZE / 2;
						rippleDelay = Random.Float(0.4f, 0.6f);
					}
				}
			}
		}
	}
	
	public static final class WaterParticle extends PixelParticle {
		
		public WaterParticle() {
			super();
			
			acc.y = 50;
			am = 0.5f;
			
			color( ColorMath.random( 0xb6ccc2, 0x3b6653 ) );
			size( 2 );
		}
		
		public void reset( float x, float y ) {
			revive();
			
			this.x = x;
			this.y = y;
			
			speed.set( Random.Float( -2, +2 ), 0 );
			
			left = lifespan = 0.4f;
		}
	}
}
