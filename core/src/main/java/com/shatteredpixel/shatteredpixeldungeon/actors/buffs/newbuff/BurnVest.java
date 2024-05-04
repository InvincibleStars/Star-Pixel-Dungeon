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

package com.shatteredpixel.shatteredpixeldungeon.actors.buffs.newbuff;

import static com.badlogic.gdx.utils.Align.left;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.Rankings;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Shadows;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.WellFed;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.journal.Guidebook;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfChallenge;
import com.shatteredpixel.shatteredpixeldungeon.journal.Document;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

import java.util.ArrayList;

//public class ThisActive extends Buff implements Hero.Doom {

public class BurnVest extends Buff {

	//数据

	private static final float STEP	= 25f;

	public static int burnadd = 100;

	public static int cooladd = 100;

	public static float burndmg = (float)((Math.round(burnadd))/100)*2;

	public static float cooldmg = (float)((Math.round(cooladd))/100)*2;

	@Override
	public boolean act() {
		if (target.isAlive() && target instanceof Hero) {

			Hero hero = (Hero) target;
			if(burnadd>100){
				BurnVest.burnadd--;
			}else{
				BurnVest.burnadd++;
			}
			BurnVest.cooladd=200-BurnVest.burnadd;
			BurnVest.burndmg = (float)((Math.round(burnadd))/100)*2;
			BurnVest.cooldmg = (float)((Math.round(cooladd))/100)*2;
			spend(STEP);
		}else {
			diactivate();
		}
		return true;
	}

	//显示
	@Override
	public int icon() {
			return BuffIndicator.BURNVEST;
	}

	@Override
	public String toString() {
	return Messages.get(this, "title");
	}

	@Override
	public String desc() {
		String result;
		//result = Messages.get(this, "desc", burnadd , cooladd);
		result = Messages.get(this, "desc", burnadd , cooladd);
		return result;
	}


	//保存

	private static final String BURNADD = "burnadd";
	private static final String COOLADD = "cooladd";

	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle(bundle);
		bundle.put( BURNADD, burnadd );
		bundle.put( COOLADD, cooladd );
	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		burnadd = bundle.getInt(BURNADD);
		burnadd = bundle.getInt(COOLADD);
	}


}