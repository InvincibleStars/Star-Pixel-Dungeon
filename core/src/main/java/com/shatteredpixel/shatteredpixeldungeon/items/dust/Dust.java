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

package com.shatteredpixel.shatteredpixeldungeon.items.dust;

import com.shatteredpixel.shatteredpixeldungeon.Challenges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.Recipe;
import com.shatteredpixel.shatteredpixeldungeon.items.gem.BlueGem;
import com.shatteredpixel.shatteredpixeldungeon.items.gem.ColorGem;
import com.shatteredpixel.shatteredpixeldungeon.items.gem.Gem;
import com.shatteredpixel.shatteredpixeldungeon.items.gem.RedGem;
import com.shatteredpixel.shatteredpixeldungeon.items.gem.GreenGem;
import com.shatteredpixel.shatteredpixeldungeon.items.gem.WhiteGem;
import com.shatteredpixel.shatteredpixeldungeon.items.gem.YellowGem;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.Potion;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfHealing;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.plants.Plant;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndBag;
import com.watabou.utils.Random;
import com.watabou.utils.Reflection;

import java.util.ArrayList;
import java.util.HashMap;

public class Dust extends Item {
	
	{
		stackable = true;
		unique = true;
	}
	
	@Override
	public boolean isUpgradable() {
		return false;
	}
	
	@Override
	public boolean isIdentified() {
		return true;
	}

	public static class GemToDust extends Recipe {
		public static HashMap<Class<?extends Gem>, Class<?extends Dust>> types = new HashMap<>();
		static {
			types.put(GreenGem.class,	GreenDust.class);
			types.put(RedGem.class,		RedDust.class);
			types.put(BlueGem.class,	BlueDust.class);
			types.put(YellowGem.class,	YellowDust.class);
			types.put(WhiteGem.class,	WhiteDust.class);
			types.put(ColorGem.class,	ColorDust.class);
		}
		@Override
		public boolean testIngredients(ArrayList<Item> ingredients) {
			if (ingredients.size() != 2) {return false;}
			for (Item ingredient : ingredients){
				if (!(ingredient instanceof Gem && types.containsKey(ingredient.getClass()))){
					return false;
				}
			}
			return true;
		}
		@Override
		public int cost(ArrayList<Item> ingredients) {
			return 0;
		}
		@Override
		public Item brew(ArrayList<Item> ingredients) {
			if (!testIngredients(ingredients)) {
				return null;
			}
			for (Item ingredient : ingredients){
				ingredient.quantity(ingredient.quantity() - 1);
			}
			ArrayList<Class<?extends Gem>> gems = new ArrayList<>();
			for (Item i : ingredients) {
				if (!gems.contains(i.getClass())) {
					gems.add((Class<? extends Gem>) i.getClass());
				}
			}
			Dust result;

			if ( (gems.size() == 1 && Random.Int(4) == 0)
					|| (gems.size() == 2 && Random.Int(2) == 0)) {
				result = (Dust) Generator.randomUsingDefaults( Generator.Category.DUST );
			} else {
				result = Reflection.newInstance(types.get(Random.element(ingredients).getClass()));
			}
			if (gems.size() == 1){
				result.identify();
			}
			return result;
		}


		@Override
		public Item sampleOutput(ArrayList<Item> ingredients) {
			return new WndBag.Placeholder(ItemSpriteSheet.DUST_HOLDER){
				@Override
				public String name() {
					return Messages.get(Dust.GemToDust.class, "name");
				}
				@Override
				public String info() {
					return "";
				}
			};
		}
	}

	public static class DustHolder extends Dust {

		{
			image = ItemSpriteSheet.SEED_HOLDER;
		}

		@Override
		public boolean isSimilar(Item item) {
			return item instanceof Dust;
		}

		@Override
		public String info() {
			return "";
		}
	}
}