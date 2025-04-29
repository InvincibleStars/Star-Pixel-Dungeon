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

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.temple;

import static com.shatteredpixel.shatteredpixeldungeon.Dungeon.hero;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Freezing;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Burning;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Chill;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Healing;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Ooze;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Roots;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Brute;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.effects.Splash;
import com.shatteredpixel.shatteredpixeldungeon.items.star.gem.FireGem;
import com.shatteredpixel.shatteredpixeldungeon.items.star.gem.GrassGem;
import com.shatteredpixel.shatteredpixeldungeon.items.star.gem.WaterGem;
import com.shatteredpixel.shatteredpixeldungeon.levels.features.Chasm;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ElementalSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.GhostSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.WraithSprite;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;

public abstract class Ahriman extends Mob {

	{
		flying = true;

	}

	protected ArrayList<Class<? extends Buff>> harmfulBuffs = new ArrayList<>();

	
	public static class EnemyGhost extends Ahriman {
		{
			spriteClass = WraithSprite.class;
		}

	}
	
	public static class NeturalGhost extends Ahriman {
		{
			spriteClass = WraithSprite.class;
			state = PASSIVE;
			HP=1;
		}

		@Override
		public void die( Object cause ) {
			super.die( cause );
			for (int i = 0; i < PathFinder.NEIGHBOURS8.length; i++) {
				Char ch = findChar( pos + PathFinder.NEIGHBOURS8[i] );
				if (ch != null && ch.isAlive()) {
					int damage = Random.NormalIntRange(damageRoll(), damageRoll()*2);
					damage = Math.max( 0,  damage - (ch.drRoll() + ch.drRoll()) );
					ch.damage( damage, this );
				}
			}
		}

	}
	
	public static class AllyGhost extends Ahriman {
		{
			spriteClass = GhostSprite.class;
			alignment=Alignment.ALLY;
		}

		@Override
		protected boolean act() {
			health();
			GLog.p(Messages.get(this,"speak"));
			die(true);
			return super.act();
		}

		public void health(){
			Buff.affect(hero, Healing.class).setHeal((int)(hero.HT*0.08f), 15f, 2);
		}
	}

	
	public static Class<? extends Ahriman> random() {
		switch (Random.Int(3)) {
			default:
				return AllyGhost.class;
			case 2:
				return NeturalGhost.class;
			case 3:
				return EnemyGhost.class;
		}
	}
}
