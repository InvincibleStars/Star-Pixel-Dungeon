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

package com.shatteredpixel.shatteredpixeldungeon.items.star.gem;

import com.shatteredpixel.shatteredpixeldungeon.items.Recipe;
import com.shatteredpixel.shatteredpixeldungeon.items.star.dust.GrassDust;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfHaste;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfLullaby;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class GrassGem extends Gem {
	
	{
		image = ItemSpriteSheet.GRASS_GEM;

	}

	public static class GreenGemtoPotion extends AlchemyGem.Recipe.SimpleRecipe {
		{
			inputs = new Class[]{GrassGem.class};
			inQuantity = new int[]{1};
			cost = 1;
			output = PotionOfHaste.class;
			outQuantity = 1;
		}
	}

	public static class GreenGemtoScroll extends AlchemyGem.Recipe.SimpleRecipe {
		{
			inputs = new Class[]{GrassGem.class};
			inQuantity = new int[]{1};
			cost = 1;
			output = ScrollOfLullaby.class;
			outQuantity = 1;
		}
	}

	public static class GrassDustRecipe extends Recipe.SimpleRecipe {
		{
			inputs = new Class[]{GrassGem.class};
			inQuantity = new int[]{2};
			cost=1;
			outQuantity = 1;
			output = GrassDust.class;
		}
	}




}