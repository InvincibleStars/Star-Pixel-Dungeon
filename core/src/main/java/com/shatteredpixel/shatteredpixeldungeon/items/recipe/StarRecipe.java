package com.shatteredpixel.shatteredpixeldungeon.items.recipe;

import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.Recipe;
import com.shatteredpixel.shatteredpixeldungeon.items.areaitem.NoCruseWood;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.SoulBox;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfUpgrade;
import com.shatteredpixel.shatteredpixeldungeon.items.star.gem.ColorGem;
import com.shatteredpixel.shatteredpixeldungeon.items.star.gem.FireGem;
import com.shatteredpixel.shatteredpixeldungeon.items.star.gem.GrassGem;
import com.shatteredpixel.shatteredpixeldungeon.items.star.gem.RuleGem;
import com.shatteredpixel.shatteredpixeldungeon.items.star.gem.WaterGem;
import com.shatteredpixel.shatteredpixeldungeon.items.star.gem.WhiteGem;
import com.shatteredpixel.shatteredpixeldungeon.items.summon.item.Soul;
import com.shatteredpixel.shatteredpixeldungeon.items.summon.item.SoulBadge;

public class StarRecipe extends Item {

    public static class ColorGemRecipe extends Recipe.SimpleRecipe {
        {
            inputs = new Class[]{FireGem.class, WaterGem.class, GrassGem.class, ScrollOfUpgrade.class};
            inQuantity = new int[]{1,1,1,1};
            cost = 15;
            outQuantity = 1;
            output = ColorGem.class;
        }
    }

    public static class RuleGemRecipe extends Recipe.SimpleRecipe {
        {
            inputs = new Class[]{WhiteGem.class, ColorGem.class};
            inQuantity = new int[]{2,1};
            cost = 5;
            outQuantity = 1;
            output = RuleGem.class;
        }
    }





}
