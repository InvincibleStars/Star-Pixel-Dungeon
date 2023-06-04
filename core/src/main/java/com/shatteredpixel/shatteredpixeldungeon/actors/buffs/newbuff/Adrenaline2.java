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

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;

public class Adrenaline2 extends FlavourBuff {
	
	{
		type = buffType.POSITIVE;
		
		announced = true;
	}
	
	public static final float DURATION	= 30f;



	public int A = 4;

	//public int CIURFGH = Integer.parseInt(dispTurns());
	

	/*
	@Override
	protected String dispTurns() {
		Integer.parseInt(String ,dispTurns());
		return dispTurns(visualcooldown());
	}

	 */





	@Override
	public int icon() {
		return BuffIndicator.AMOK;
	}

	@Override
	public float iconFadePercent() {
		return Math.max(0, (DURATION - visualcooldown()) / DURATION);
	}
	
	@Override
	public String toString() {
		return Messages.get(this, "name");
	}


	@Override
	public String desc() {
		return Messages.get(this, "desc")
				+"\n"
				//剩余回合计数（保留两位小数）
				+Messages.get( this,"time") +(int)visualcooldown() +Messages.get( this,"turn")
				//攻击速度加成值
				+Messages.get( this,"add")+(int)(visualcooldown()*10)+Messages.get( this,"speed");
	}
	
}
