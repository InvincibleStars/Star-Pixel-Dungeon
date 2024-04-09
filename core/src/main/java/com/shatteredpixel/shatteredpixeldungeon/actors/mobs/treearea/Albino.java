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

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.treearea;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Bleeding;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.food.MysteryMeat;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfHealing;
import com.shatteredpixel.shatteredpixeldungeon.sprites.AlbinoSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.BatSprite;
import com.watabou.utils.Random;

public class Albino extends Rat {

	{
		spriteClass = AlbinoSprite.class;
		
		HP = HT = 20+Random.Int(2);
		EXP = 4;
		
		loot = new MysteryMeat();
		lootChance = 1f;
	}
	
	@Override
	public int attackProc( Char enemy, int damage ) {
		damage = super.attackProc( enemy, damage );
		if (Random.Int( 2 ) == 0) {
			Buff.affect( enemy, Bleeding.class ).set( Random.Int(1,3) );
		}
		
		return damage;
	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 1, 11 );
	}

	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, 3);
	}

    public static class Bat extends Mob {

        {
            spriteClass = BatSprite.class;

            HP = HT = 26+Random.Int(4);
            defenseSkill = 8;
            baseSpeed = 2f;

            EXP = 7;
            maxLvl = 15;

            flying = true;

            loot = new PotionOfHealing();
            lootChance = 0.23f; //by default, see rollToDropLoot()
        }

        @Override
        public int damageRoll() {
            return Random.NormalIntRange( 3, 14 );
        }

        @Override
        public int attackSkill( Char target ) {
            return 16;
        }

        @Override
        public int drRoll() {
            return Random.NormalIntRange(0, 4);
        }

    //	@Override
    //	public int attackProc( Char enemy, int damage ) {
    //		damage = super.attackProc( enemy, damage );
    //		int reg = Math.min( damage - 4, HT - HP );
    //
    //		if (reg > 0) {
    //			HP += reg;
    //			sprite.emitter().burst( Speck.factory( Speck.HEALING ), 1 );
    //		}
    //
    //		return damage;
    //	}



    }
}