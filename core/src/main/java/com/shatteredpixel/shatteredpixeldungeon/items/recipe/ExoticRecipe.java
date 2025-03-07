package com.shatteredpixel.shatteredpixeldungeon.items.recipe;

import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.Recipe;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfHaste;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.PotionOfSnapFreeze;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.newexotic.FlyMindExotic;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.newexotic.PowerExotic;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfMagicMapping;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRage;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfPsionicBlast;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.newexotic.FrostExotic;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.newexotic.TearExotic;
import com.shatteredpixel.shatteredpixeldungeon.items.star.dust.FireDust;
import com.shatteredpixel.shatteredpixeldungeon.items.star.dust.GrassDust;
import com.shatteredpixel.shatteredpixeldungeon.items.star.dust.WaterDust;
import com.shatteredpixel.shatteredpixeldungeon.items.star.gem.GrassGem;
import com.shatteredpixel.shatteredpixeldungeon.items.star.gem.WaterGem;
import com.shatteredpixel.shatteredpixeldungeon.items.star.gem.WeaponGem;

public class ExoticRecipe extends Item {

    public static class FrostExoticRecipe extends Recipe.SimpleRecipe {
        {
            inputs = new Class[]{PotionOfSnapFreeze.class, ScrollOfMagicMapping.class, WaterDust.class};
            inQuantity = new int[]{1,1,1};
            cost = 8;
            outQuantity = 1;
            output = FrostExotic.class;
        }
    }

    public static class TearExoticRecipe extends Recipe.SimpleRecipe {
        {
            inputs = new Class[]{ScrollOfPsionicBlast.class, FireDust.class};
            inQuantity = new int[]{1,2};
            cost = 8;
            outQuantity = 1;
            output = TearExotic.class;
        }
    }

    public static class PowerExoticRecipe extends Recipe.SimpleRecipe {
        {
            inputs = new Class[]{PotionOfHaste.class, GrassDust.class};
            inQuantity = new int[]{1,2};
            cost = 8;
            outQuantity = 1;
            output = PowerExotic.class;
        }
    }

    public static class FlyMindExoticRecipe extends Recipe.SimpleRecipe {
        {
            inputs = new Class[]{ScrollOfRage.class, ScrollOfMagicMapping.class, GrassDust.class};
            inQuantity = new int[]{1,1,2};
            cost = 8;
            outQuantity = 1;
            output = FlyMindExotic.class;
        }
    }

}
