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

package com.shatteredpixel.shatteredpixeldungeon.items.newitem.stone;

import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfUpgrade;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.MissileWeapon;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

import java.util.ArrayList;

public class UpdateStone extends Item {
	
	{
		image = ItemSpriteSheet.UPDATE_STONE;
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
	//物品的价值
	@Override
	public int value() {
		return quantity * 80;
	}

	//合成配方（关联QuickRecipe.java和Recipe.java）
	public static class Scroll extends Recipe.SimpleRecipe {
		{
			inputs = new Class[]{UpdateStone.class};
			inQuantity = new int[]{3};

			cost = 4;

			output = ScrollOfUpgrade.class;
			outQuantity = 1;
		}
	}

	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe {

		@Override
		public boolean testIngredients(ArrayList<Item> ingredients) {
			return ingredients.size() == 1
					//&& (ingredients.get(0) instanceof MissileWeapon||ingredients.get(0) instanceof MeleeWeapon)
					&& ingredients.get(0) instanceof MeleeWeapon
					&& ingredients.get(0).isIdentified()
					&& !ingredients.get(0).cursed;
		}
		//进行本次合成的水晶消耗
		@Override
		public int cost(ArrayList<Item> ingredients) {
			return 5;
		}

		@Override
		public Item brew(ArrayList<Item> ingredients) {
			Item result = sampleOutput(ingredients);

			ingredients.get(0).quantity(0);

			return result;
		}

		@Override
		public Item sampleOutput(ArrayList<Item> ingredients) {
			Weapon w = (Weapon)ingredients.get(0);
			int level = w.level();
			return new UpdateStone().quantity(level+1);
		}
	}

}
