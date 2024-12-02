package com.shatteredpixel.shatteredpixeldungeon.items.alchemyrecipe;

import com.shatteredpixel.shatteredpixeldungeon.items.Recipe;
import com.shatteredpixel.shatteredpixeldungeon.items.dust.FireDust;
import com.shatteredpixel.shatteredpixeldungeon.items.dust.GrassDust;
import com.shatteredpixel.shatteredpixeldungeon.items.dust.WaterDust;
import com.shatteredpixel.shatteredpixeldungeon.items.dust.WeaponDust;
import com.shatteredpixel.shatteredpixeldungeon.items.gem.FireGem;
import com.shatteredpixel.shatteredpixeldungeon.items.gem.GrassGem;
import com.shatteredpixel.shatteredpixeldungeon.items.gem.WaterGem;
import com.shatteredpixel.shatteredpixeldungeon.items.gem.WeaponGem;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;


public class GemToDust extends MeleeWeapon {

    public static class GrassDustRecipe extends Recipe.SimpleRecipe {
        {
            inputs = new Class[]{GrassGem.class, GrassGem.class};
            inQuantity = new int[]{1, 1};
            cost=1;
            outQuantity = 1;
            output = GrassDust.class;
        }
    }

    public static class WaterDustRecipe extends Recipe.SimpleRecipe {
        {
            inputs = new Class[]{WaterGem.class, WaterGem.class};
            inQuantity = new int[]{1, 1};
            cost=1;
            outQuantity = 1;
            output = WaterDust.class;
        }
    }

    public static class FireDustRecipe extends Recipe.SimpleRecipe {
        {
            inputs = new Class[]{FireGem.class, FireGem.class};
            inQuantity = new int[]{1, 1};
            cost=1;
            outQuantity = 1;
            output = FireDust.class;
        }
    }

    public static class WeaponDustRecipe extends Recipe.SimpleRecipe {
        {
            inputs = new Class[]{WeaponGem.class, WeaponGem.class};
            inQuantity = new int[]{1, 1};
            cost=1;
            outQuantity = 1;
            output = WeaponDust.class;
        }
    }

}