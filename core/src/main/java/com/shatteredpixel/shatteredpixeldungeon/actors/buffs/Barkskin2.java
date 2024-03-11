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

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.watabou.utils.Bundle;

public class Barkskin2 extends Buff {
	
	{
		type = buffType.POSITIVE;
	}

	private int level = 0;
	private int interval = 1;

	private  int time = 10;
	
	@Override
	public boolean act() {
		if (target.isAlive()) {
			spend( 1);
			if (time-- <= 1){
				detach();
			}
		} else {
			detach();
		}

		return true;
	}
	
	public int level() {
		return level;
	}
	
	public void set( int value ) {

			level = value;
			time = 10;
		//spend(1);
	}

	@Override
	public int icon() {return BuffIndicator.BARKSKIN;}
	@Override
	public String toString() {return Messages.get(this, "name");}
	@Override
	public String desc() {
		return Messages.get(this, "desc", level, time,"暂未实装");}
	private static final String LEVEL	    = "level";
	private static final String INTERVAL    = "interval";
	
	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
		bundle.put( INTERVAL, interval );
		bundle.put( LEVEL, level );
	}
	
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		interval = bundle.getInt( INTERVAL );
		level = bundle.getInt( LEVEL );
	}
}
