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

import com.shatteredpixel.shatteredpixeldungeon.items.Recipe;
import com.shatteredpixel.shatteredpixeldungeon.items.dust.FireDust;
import com.shatteredpixel.shatteredpixeldungeon.items.dust.WaterDust;
import com.shatteredpixel.shatteredpixeldungeon.items.dust.WeaponDust;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfLevitation;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfMirrorImage;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class WaterGem extends Gem {
	
	{
		image = ItemSpriteSheet.WATER_GEM;

	}

	public static class BlueGemtoPotion extends AlchemyGem.Recipe.SimpleRecipe {
		{
			inputs = new Class[]{WaterGem.class};
			inQuantity = new int[]{1};
			cost = 1;
			output = PotionOfLevitation.class;
			outQuantity = 1;
		}
	}

	public static class BlueGemtoScroll extends AlchemyGem.Recipe.SimpleRecipe {
		{
			inputs = new Class[]{WaterGem.class};
			inQuantity = new int[]{1};
			cost = 1;
			output = ScrollOfMirrorImage.class;
			outQuantity = 1;
		}
	}

	public static class WaterDustRecipe extends Recipe.SimpleRecipe {
		{
			inputs = new Class[]{WaterGem.class};
			inQuantity = new int[]{2};
			cost=1;
			outQuantity = 1;
			output = WaterDust.class;
		}
	}



}