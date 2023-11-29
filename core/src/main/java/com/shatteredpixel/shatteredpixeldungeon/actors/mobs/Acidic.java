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
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Ooze;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.newbuff.RchAddBy3;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfExperience;
import com.shatteredpixel.shatteredpixeldungeon.sprites.AcidicSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.RatSprite;
import com.watabou.utils.Random;

public class Acidic extends Scorpio {

	{
		spriteClass = AcidicSprite.class;
		
		properties.add(Property.ACIDIC);

		loot = new PotionOfExperience();
		lootChance = 1f;
	}
	@Override
	public int attackProc(Char enemy, int damage) {
		Buff.affect(enemy, Ooze.class).set( Ooze.DURATION );
		return super.attackProc(enemy, damage);
	}

	@Override
	public int defenseProc( Char enemy, int damage ) {
		if (Dungeon.level.adjacent(pos, enemy.pos)){
			Buff.affect(enemy, Ooze.class).set( Ooze.DURATION );
		}
		return super.defenseProc( enemy, damage );
	}

	@Override
	protected Item createLoot() {
		return new PotionOfExperience();
	}

    public static class Rat extends Mob {

        {
            spriteClass = RatSprite.class;

            HP = HT = 8;
            defenseSkill = 2;

            Buff.affect(this, RchAddBy3.class);

            maxLvl = 5;
        }


        /*
        @Override
        protected boolean act() {
            if (Dungeon.level.heroFOV[pos] && Dungeon.hero.armorAbility instanceof Ratmogrify){
                alignment = Alignment.ALLY;
                if (state == SLEEPING) state = WANDERING;
            }
            return super.act();
        }

         */

        @Override
        public int damageRoll() {
            return Random.NormalIntRange( 2, 5 );
        }

        @Override
        public int attackSkill( Char target ) {
            return 8;
        }

        @Override
        public int drRoll() {
            return Random.NormalIntRange(0, 1);
        }

        /*
        private static final String RAT_ALLY = "rat_ally";

        @Override
        public void storeInBundle(Bundle bundle) {
            super.storeInBundle(bundle);
            if (alignment == Alignment.ALLY) bundle.put(RAT_ALLY, true);
        }

        @Override
        public void restoreFromBundle(Bundle bundle) {
            super.restoreFromBundle(bundle);
            if (bundle.contains(RAT_ALLY)) alignment = Alignment.ALLY;
        }

         */
    }
}
