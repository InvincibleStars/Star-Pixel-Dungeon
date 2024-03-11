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

import com.shatteredpixel.shatteredpixeldungeon.items.Item;

public class Gem extends Item {
	
	{
		//可堆叠
		stackable = true;
		//不可装备
		unique = true;
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

}
