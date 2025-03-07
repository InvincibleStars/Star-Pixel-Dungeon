package com.shatteredpixel.shatteredpixeldungeon.items.recipe;

import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.Recipe;
import com.shatteredpixel.shatteredpixeldungeon.items.food.MeatPie;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.AlchemicalCatalyst;
import com.shatteredpixel.shatteredpixeldungeon.items.science.FoodLevel;
import com.shatteredpixel.shatteredpixeldungeon.items.science.PotionLevel;
import com.shatteredpixel.shatteredpixeldungeon.items.science.SummonLevel;
import com.shatteredpixel.shatteredpixeldungeon.items.star.gem.RuleGem;
import com.shatteredpixel.shatteredpixeldungeon.items.summon.item.Soul;
import com.shatteredpixel.shatteredpixeldungeon.items.summon.item.SoulBadge;

public class ScienceRecipe extends Item {

    public static class PotionLevelRecipe extends Recipe.SimpleRecipe {
        {
            inputs = new Class[]{RuleGem.class, AlchemicalCatalyst.class};
            inQuantity = new int[]{1,1};
            cost = 20;
            outQuantity = 1;
            output = PotionLevel.class;
        }
    }

    public static class FoodLevelRecipe extends Recipe.SimpleRecipe {
        {
            inputs = new Class[]{RuleGem.class, MeatPie.class};
            inQuantity = new int[]{1,1};
            cost = 20;
            outQuantity = 1;
            output = FoodLevel.class;
        }
    }

    public static class SummonLevelRecipe extends Recipe.SimpleRecipe {
        {
            inputs = new Class[]{RuleGem.class, SoulBadge.class};
            inQuantity = new int[]{1,1};
            cost = 20;
            outQuantity = 1;
            output = SummonLevel.class;
        }
    }


}
