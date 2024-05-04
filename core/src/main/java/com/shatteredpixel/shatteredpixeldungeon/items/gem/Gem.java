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

package com.shatteredpixel.shatteredpixeldungeon.items.gem;

import static com.shatteredpixel.shatteredpixeldungeon.Dungeon.hero;

import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.plants.Plant;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class Gem extends Item {
	
	{
		stackable = true;
		unique = true;
	}
	@Override
	public String info() {
		String info = desc();
		info += "\n\n" + Messages.get(Gem.class, "know");
		return info;
	}

	//不会被爆炸摧毁
	public boolean unique() {
		return false;
	}
	//不能被升级
	@Override
	public boolean isUpgradable() {
		return false;
	}
	//始终是被鉴定的
	@Override
	public boolean isIdentified() {
		return true;
	}

	public static class PlaceHolder extends Gem {

		{
			image = ItemSpriteSheet.RED_GEM;
		}

		@Override
		public boolean isSimilar(Item item) {
			return item instanceof Gem;
		}

		@Override
		public String info() {
			return "";
		}
	}

}