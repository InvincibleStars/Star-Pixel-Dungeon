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
import com.shatteredpixel.shatteredpixeldungeon.items.dust.WeaponDust;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfUpgrade;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

import java.util.ArrayList;

public class WeaponGem extends Gem {
	
	{
		image = ItemSpriteSheet.WEAPON_GEM;

	}


	public static class WeaponDustRecipe extends Recipe.SimpleRecipe {
		{
			inputs = new Class[]{WeaponGem.class};
			inQuantity = new int[]{2};
			cost=1;
			outQuantity = 1;
			output = WeaponDust.class;
		}
	}

	public static class WeaponGemToUpdateRecipe extends Recipe.SimpleRecipe {
		{
			inputs = new Class[]{WeaponGem.class};
			inQuantity = new int[]{3};
			cost=1;
			outQuantity = 5;
			output = ScrollOfUpgrade.class;
		}
	}


	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe {

		@Override
		public boolean testIngredients(ArrayList<Item> ingredients) {
			for (Item i : ingredients){
				if (!(i instanceof MeleeWeapon)){
					return true;
				}
			}
			return !ingredients.isEmpty();
		}

		@Override
		public int cost(ArrayList<Item> ingredients) {
			return 0;
		}

		@Override
		public Item brew(ArrayList<Item> ingredients) {
			Item result = sampleOutput(ingredients);

			for (Item i : ingredients){
				i.quantity(0);
			}

			return result;
		}

		@Override
		public Item sampleOutput(ArrayList<Item> ingredients) {
			int metalQuantity = 0;

			for (Item i : ingredients){
				MeleeWeapon mw = (MeleeWeapon)i;
				float quantity = mw.quantity()-1;
				quantity += 1 + mw.level+mw.tier;
				metalQuantity += quantity;
			}

			return new WeaponGem().quantity(metalQuantity);
		}
	}


}