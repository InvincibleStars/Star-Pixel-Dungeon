package com.shatteredpixel.shatteredpixeldungeon.items.dust;

import com.shatteredpixel.shatteredpixeldungeon.items.Recipe;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.PotionOfCleansing;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.PotionOfDragonsBreath;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.PotionOfStormClouds;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfMysticalEnergy;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfPrismaticImage;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfPsionicBlast;

public class AlchemyDust {
    public static class BlueDusttoPotion extends Recipe.SimpleRecipe {
        {
            inputs = new Class[]{WaterDust.class};
            inQuantity = new int[]{1};
            cost = 2;
            output = PotionOfStormClouds.class;
            outQuantity = 1;
        }
    }
    public static class RedDusttoPotion extends Recipe.SimpleRecipe {
        {
            inputs = new Class[]{FireDust.class};
            inQuantity = new int[]{1};
            cost = 2;
            output = PotionOfDragonsBreath.class;
            outQuantity = 1;
        }
    }
    public static class GreenDusttoPotion extends Recipe.SimpleRecipe {
        {
            inputs = new Class[]{GrassDust.class};
            inQuantity = new int[]{1};
            cost = 2;
            output = PotionOfCleansing.class;
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
    public static class RedDusttoScroll extends Recipe.SimpleRecipe {
        {
            inputs = new Class[]{FireDust.class};
            inQuantity = new int[]{1};
            cost = 2;
            output = ScrollOfPsionicBlast.class;
            outQuantity = 1;
        }
    }
    public static class GreenDusttoScroll extends Recipe.SimpleRecipe {
        {
            inputs = new Class[]{GrassDust.class};
            inQuantity = new int[]{1};
            cost = 2;
            output = ScrollOfMysticalEnergy.class;
            outQuantity = 1;
        }
    }


}