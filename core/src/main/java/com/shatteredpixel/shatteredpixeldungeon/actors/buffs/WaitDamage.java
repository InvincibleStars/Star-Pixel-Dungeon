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

package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;

public class WaitDamage extends Buff {

	{
		type = buffType.POSITIVE;
	}

	public int pos = -1;

	public int waittime;
	public static int WAITMAX = 5;

	@Override
	public boolean act() {
		if (pos == -1){
			pos = target.pos;
		};
		if (pos != target.pos) {
			if(Dungeon.hero.hasTalent(Talent.SLOW_BLUE)){
				waittime=Dungeon.hero.pointsInTalent(Talent.SLOW_BLUE);
			}else {
				waittime = 0;
			}
			pos = target.pos;
		} else {
			waittime+=1;
			if( waittime >= WAITMAX )waittime = WAITMAX;
		}

		spend(TICK);

		return true;
	}

	public int outint() {
		return (int)Math.ceil(waittime);
	}

}