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

package com.shatteredpixel.shatteredpixeldungeon.items.star.dust;

import com.shatteredpixel.shatteredpixeldungeon.items.star.gem.WaterGem;
import com.shatteredpixel.shatteredpixeldungeon.items.Recipe;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.PotionOfStormClouds;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfPrismaticImage;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class WaterDust extends Dust {
	
	{
		
		stackable = true;
		unique = true;
		image = ItemSpriteSheet.WATER_DUST;
	}
	
	@Override
	public boolean isUpgradable() {
		return false;
	}
	
	@Override
	public boolean isIdentified() {
		return true;
	}

	public static class twoWaterGem extends Recipe.SimpleRecipe{
		{
			inputs =  new Class[]{WaterGem.class};
			inQuantity = new int[]{2};

			cost = 2;

			output = WaterDust.class;
			outQuantity = 2;
		}
	}

	public static class BlueDusttoPotion extends Recipe.SimpleRecipe {
		{
			inputs = new Class[]{WaterDust.class};
			inQuantity = new int[]{1};
			cost = 2;
			output = PotionOfStormClouds.class;
			outQuantity = 1;
		}
	}

	public static class BlueDusttoScroll extends Recipe.SimpleRecipe {
		{
			inputs = new Class[]{WaterDust.class};
			inQuantity = new int[]{1};
			cost = 2;
			output = ScrollOfPrismaticImage.class;
			outQuantity = 1;
		}
	}
}