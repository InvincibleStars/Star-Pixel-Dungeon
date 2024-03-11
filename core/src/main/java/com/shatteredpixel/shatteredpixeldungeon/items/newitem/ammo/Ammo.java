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

package com.shatteredpixel.shatteredpixeldungeon.items.newitem.ammo;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.SPDSettings;
import com.shatteredpixel.shatteredpixeldungeon.Statistics;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.effects.SpellSprite;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.bombs.Bomb;
import com.shatteredpixel.shatteredpixeldungeon.messages.Languages;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class Ammo extends Item {

	public static final String AC_LOOK	= "ac_look";

	{
		image = ItemSpriteSheet.AMMO;
		
		stackable = true;
		unique = true;
		//默认动作
		defaultAction = AC_LOOK;
	}

	@Override
	public ArrayList<String> actions(Hero hero ) {
		ArrayList<String> actions = super.actions( hero );
		actions.remove( AC_LOOK );
		return actions;
	}

	@Override
	public void execute( Hero hero, String action ) {

		super.execute( hero, action );

		if (action.equals( AC_LOOK )) {
			GLog.i( Messages.get(this, "ac_look") );
		}
	}

	@Override
	public boolean isUpgradable() {
		return false;
	}
	
	@Override
	public boolean isIdentified() {
		return true;
	}

	public boolean isEquipped( Hero hero ) {
		return false;
	}

	public boolean defaultaction( Hero hero ) {
		return false;
	}


	//defaultaction


	public static class AmmoBox extends Ammo {

		{
			image = ItemSpriteSheet.AMMO_BOX;
			stackable = false;
		}

		@Override
		public int value() {
			return super.value();
		}

		@Override
		public boolean doPickUp(Hero hero, int pos) {
			Ammo ammo = new Ammo();
			new AmmoBox();
			//随机掉落10-20颗弹药的弹药盒
			ammo.quantity(Random.Int(10,20));
			if (ammo.doPickUp(hero, pos)) {	return true; }
			return false;
		}

	}


	@Override
	public int value() {
		return 20;
	}

}
