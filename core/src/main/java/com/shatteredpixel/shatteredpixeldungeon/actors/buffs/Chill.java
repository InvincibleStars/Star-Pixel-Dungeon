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

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.newbuff.BurnVest;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;

import java.text.DecimalFormat;

public class Chill extends FlavourBuff {

	public static final float DURATION = 10f+ BurnVest.cooldmg*5;

	{
		type = buffType.NEGATIVE;
		announced = true;
	}

	@Override
	public boolean attachTo(Char target) {
		Buff.detach( target, Burning.class );
		if(BurnVest.cooladd>=199){
			BurnVest.burnadd=0;	BurnVest.cooladd=200;
		}else{
			BurnVest.burnadd-=1;	BurnVest.cooladd+=1;
		}
		return super.attachTo(target);
	}

	//reduces speed by 10% for every turn remaining, capping at 50%
	public float speedFactor(){
		Hero hero =	new Hero();
		return Math.max(0.5f-0.125f*(float)(hero.pointsInTalent(Talent.COLD_PROTECT)) , 1 - cooldown()*(0.1f-0.025f*(float)(hero.pointsInTalent(Talent.COLD_PROTECT))));
	}

	@Override
	public int icon() {
		return BuffIndicator.FROST;
	}

	@Override
	public float iconFadePercent() {
		return Math.max(0, (DURATION - visualcooldown()) / DURATION);
	}

	@Override
	public void fx(boolean on) {
		if (on) target.sprite.add(CharSprite.State.CHILLED);
		else target.sprite.remove(CharSprite.State.CHILLED);
	}

	@Override
	public String toString() {
		return Messages.get(this, "name");
	}

	@Override
	public String desc() {
		return Messages.get(this, "desc", dispTurns(), new DecimalFormat("#.##").format((1f-speedFactor())*100f));
	}
}