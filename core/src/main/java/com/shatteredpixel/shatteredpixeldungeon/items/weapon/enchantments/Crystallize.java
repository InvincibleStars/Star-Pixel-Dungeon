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

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Bleeding;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Weakness;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite.Glowing;
import com.watabou.utils.Random;

public class Crystallize extends Weapon.Enchantment {

	private static Glowing ORANGE = new Glowing( 0xFF4400 );
	
	@Override
	public int proc( Weapon weapon, Char attacker, Char defender, int damage ) {
		int level = Math.max( 0, weapon.buffedLvl() );

		// lvl 0 - 33%
		// lvl 1 - 50%
		// lvl 2 - 60%
		float procChance = 0.3f;
		if (Random.Float() < procChance) {

			/*
			if (defender.buff(Burning.class) != null){
				Buff.affect(defender, Burning.class).reignite(defender, 8f);
				int burnDamage = Random.NormalIntRange( 1, 3 + Dungeon.depth/4 );
				defender.damage( Math.round(burnDamage * 0.67f), this );
			} else {
				Buff.affect(defender, Burning.class).reignite(defender, 8f);
			}
			
			defender.sprite.emitter().burst( FlameParticle.FACTORY, level + 1 );

			 */

			if (defender.buff(Bleeding.class)!=null) {
				//Buff.affect(defender, Bleeding.class).set(3f);
				Buff.affect(defender, Weakness.class, 5f);
			}else{
				Buff.affect(defender, Bleeding.class).set(3f);
			}


			
		}

		return damage;

	}
/*
	public int proc(Char attacker, Char defender, int damage) {
		if (defender.buff(Bleeding.class)!=null) {
			Buff.affect(defender, Bleeding.class).set(2f);
			Buff.affect(defender, Cripple.class, BuffWait.T2);
			GLog.n( Messages.get(this, "warm") );
		}
		return super.proc(attacker, defender, damage);
	}

 */


	
	@Override
	public Glowing glowing() {
		return ORANGE;
	}
}
