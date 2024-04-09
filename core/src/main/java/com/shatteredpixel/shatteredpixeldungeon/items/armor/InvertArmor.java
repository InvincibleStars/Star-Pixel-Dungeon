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

package com.shatteredpixel.shatteredpixeldungeon.items.armor;

import static com.shatteredpixel.shatteredpixeldungeon.Dungeon.hero;

import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class InvertArmor extends Armor {

	{
		image = ItemSpriteSheet.ARMOR_INVERT;

		bones = false;
	}

	public InvertArmor() {
		super( 1 );
	}

	@Override
	public int DRMax(int lvl){return 0;}

	@Override
	public int DRMin(int lvl){return 0;}

	@Override
	public String desc() {
		String result;
		result = Messages.get(this, "desc", 20-level);
		return result;
	}

	@Override
	public String info() {
		String info = desc();
		info += "\n\n" + Messages.get(InvertArmor.class, "desc2", 20-level);
		return info;
	}







}