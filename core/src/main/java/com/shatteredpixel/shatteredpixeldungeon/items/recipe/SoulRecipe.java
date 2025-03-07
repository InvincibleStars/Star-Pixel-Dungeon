package com.shatteredpixel.shatteredpixeldungeon.items.recipe;

import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.Recipe;
import com.shatteredpixel.shatteredpixeldungeon.items.areaitem.NoCruseWood;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.SoulBox;
import com.shatteredpixel.shatteredpixeldungeon.items.summon.item.Soul;
import com.shatteredpixel.shatteredpixeldungeon.items.summon.item.SoulBadge;

public class SoulRecipe extends Item {

    public static class SoulBoxRecipe extends Recipe.SimpleRecipe {
        {
            inputs = new Class[]{Soul.class};
            inQuantity = new int[]{5};
            cost = 5;
            outQuantity = 1;
            output = SoulBadge.class;
        }
    }

    public static class SoulBadgeRecipe extends Recipe.SimpleRecipe {
        {
            inputs = new Class[]{Soul.class, NoCruseWood.class};
            inQuantity = new int[]{5,2};
            cost = 5;
            outQuantity = 1;
            output = SoulBox.class;
        }
    }





}
