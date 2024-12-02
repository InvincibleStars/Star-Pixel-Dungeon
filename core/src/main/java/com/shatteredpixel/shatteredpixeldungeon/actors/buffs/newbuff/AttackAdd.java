/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2020 Evan Debenham
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

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.watabou.noosa.Image;
import com.watabou.utils.Bundle;

public class AttackAdd extends Buff {

	{
		type = buffType.POSITIVE;
	}

	@Override
	public int icon() {
		return BuffIndicator.WEAPON;
	}

	@Override
	public void tintIcon(Image icon) {
		icon.hardlight(1, 0, 0);
	}

	@Override
	public float iconFadePercent() {
		return Math.max(0, (30-left) / 30f);
	}

	@Override
	public String toString() {
		return Messages.get(this, "name");
	}

	@Override
	public String desc() {
		return Messages.get(this, "desc", left);
	}

	public int dmgBoost = 1;
	public int left;

	public void set(int shots){
		left = Math.max(left, shots);
	}

	private static final String BOOST = "boost";
	private static final String LEFT = "left";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put( BOOST, dmgBoost );
		bundle.put( LEFT, left );
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		dmgBoost = bundle.getInt( BOOST );
		left = bundle.getInt( LEFT );
	}

}
