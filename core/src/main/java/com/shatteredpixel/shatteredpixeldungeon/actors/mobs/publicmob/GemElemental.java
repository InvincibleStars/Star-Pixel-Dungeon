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

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.publicmob;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Freezing;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Blindness;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Burning;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Chill;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Roots;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.effects.Lightning;
import com.shatteredpixel.shatteredpixeldungeon.effects.Splash;
import com.shatteredpixel.shatteredpixeldungeon.items.star.gem.FireGem;
import com.shatteredpixel.shatteredpixeldungeon.items.star.gem.GrassGem;
import com.shatteredpixel.shatteredpixeldungeon.items.star.gem.WaterGem;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Shocking;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ElementalSprite;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

import java.util.ArrayList;

public abstract class GemElemental extends Mob {

	{
		flying = true;
		state=HUNTING;
		HP = HT = 8 + Dungeon.depth * 2;
	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 1, 4 + Dungeon.depth * 2);
	}

	@Override
	public int attackProc( Char enemy, int damage ) {
		damage = super.attackProc( enemy, damage );
		meleeProc( enemy, damage );
		return damage;
	}

	@Override
	public void add( Buff buff ) {
		if (harmfulBuffs.contains( buff.getClass() )) {
			damage( Random.NormalIntRange( HT/2, HT * 3/5 ), buff );
		} else {
			super.add( buff );
		}
	}
	
	protected abstract void meleeProc( Char enemy, int damage );
	
	protected ArrayList<Class<? extends Buff>> harmfulBuffs = new ArrayList<>();

	
	public static class FireElemental extends GemElemental {
		
		{
			spriteClass = ElementalSprite.Fire.class;
			
			loot = new FireGem();
			lootChance = 1f;
			
			properties.add( Property.FIERY );
			
			harmfulBuffs.add( com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Frost.class );
			harmfulBuffs.add( Chill.class );
		}
		
		@Override
		protected void meleeProc( Char enemy, int damage ) {
			if (Random.Int( 2 ) == 0 && !Dungeon.level.water[enemy.pos]) {
				Buff.affect( enemy, Burning.class ).reignite( enemy );
				if (enemy.sprite.visible) Splash.at( enemy.sprite.center(), sprite.blood(), 5);
			}
		}

	}
	
	public static class WaterElemental extends GemElemental {
		
		{
			spriteClass = ElementalSprite.Frost.class;
			
			loot = new WaterGem();
			lootChance = 1f;
			
			properties.add( Property.ICY );
			
			harmfulBuffs.add( Burning.class );
		}
		
		@Override
		protected void meleeProc( Char enemy, int damage ) {
			if (Random.Int( 3 ) == 0 || Dungeon.level.water[enemy.pos]) {
				Freezing.freeze( enemy.pos );
				if (enemy.sprite.visible) Splash.at( enemy.sprite.center(), sprite.blood(), 5);
			}
		}

	}
	
	public static class GrassElemental extends GemElemental {
		
		{
			spriteClass = ElementalSprite.Shock.class;
			
			loot = new GrassGem();
			lootChance = 1f;
			
			properties.add( Property.ELECTRIC );
		}

		@Override
		protected void meleeProc( Char enemy, int damage ) {
			if (Random.Int( 2 ) == 0 && !Dungeon.level.water[enemy.pos]) {
				Buff.affect( enemy, Roots.class, 4f );
				if (enemy.sprite.visible) Splash.at( enemy.sprite.center(), sprite.blood(), 5);
			}
		}

	}

	
	public static Class<? extends GemElemental> random() {
		int a = Random.Int(3);
		if(a==0){
			return FireElemental.class;
		}else if(a==1){
			return WaterElemental.class;
		}else{
			return GrassElemental.class;
		}
	}
}
