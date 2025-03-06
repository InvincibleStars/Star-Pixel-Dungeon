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

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.tier2;


import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Burning;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.MissileWeapon;

public class FractalLight extends MissileWeapon {
	
	{
		tier = 2;
		//直接消失
		baseUses = 1;
		stackable = false;

	}
	public int truebaseUses = 5;

	@Override
	public int proc(Char attacker, Char defender, int damage ) {
		Buff.affect( defender, FractalLightMark.class).set(truebaseUses);
		return super.proc( attacker, defender, damage );
	}

	public static class FractalLightMark extends Buff {
		public int level;
		public boolean drop = true;

		public void set(int truebaseuses ) {
			level=truebaseuses;
			spend(0);
		}

		public void detach() {
			super.detach();
			if(target.buff(Burning.class) == null&&level>=1){
				FractalLight fractalLight = new FractalLight();
				fractalLight.truebaseUses=level-1;
				Dungeon.level.drop( fractalLight, target.pos ).sprite.drop();
			}
		}


		@Override
		public boolean act() {
			super.act();
			if(target.buff(Burning.class) != null && drop!=false){
				drop = false;
			}
            return true;
        }
	}

}
