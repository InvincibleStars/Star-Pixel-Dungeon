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

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
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

import java.util.ArrayList;

//public class ThisActive extends Buff implements Hero.Doom {

public class BurnVest extends Buff {

	//结算间隔（每STEP回合结算一次）
	private static final float ADDTIME	= 1f;
	float  b   =  (float)(Math.round(burnadd*100))/100;

	//燃烧增幅
	public static float burnadd = 1.25f;

	//字符串储存
	private static final String BURNADD			= "burnadd";


	@Override
	public boolean act() {
		if (target.isAlive() && target instanceof Hero) {

			Hero hero = (Hero) target;
			/*
			if (burnadd > 1) {
				burnadd -= 0.07f;
				b   =  (float)(Math.round(burnadd*100))/100;
				//GLog.n(Messages.get(this, "onstarving"));
			}else

			 */
				if(burnadd<=1){
				burnadd +=0.12f;
				b   =  (float)(Math.round(burnadd*100))/100;
				//GLog.n(Messages.get(this, "onstarving"));
			}
			spend(ADDTIME);
		}else {
			diactivate();
		}
		return true;
	}



	public void burnaddOver(float energy ){
		burnaddOver( energy, false );
	}

	public void burnaddOver(float energy, boolean overrideLimits ) {
		ArrayList<Item> items = new ArrayList<>();

		//数据达到5时不再增长
		// if (burnadd > 5) {
			//burnadd = 5;
		//}
		BuffIndicator.refreshHero();
	}

	//图标
	@Override
	public int icon() {
			return BuffIndicator.FIRE;
	}

	//状态标题
	@Override
	public String toString() {
	return Messages.get(this, "title");
	}

	//状态信息
	@Override
	public String desc() {
		String result;
		result = Messages.get(this, "desc")
		+burnadd
		+"x"
		;
		return result;
	}

	//保存
	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle(bundle);
		bundle.put( BURNADD, burnadd );
	}

	//读取
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		burnadd = bundle.getFloat(BURNADD);
	}


}
