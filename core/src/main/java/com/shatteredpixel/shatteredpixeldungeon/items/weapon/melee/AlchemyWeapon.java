package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee;

import com.shatteredpixel.shatteredpixeldungeon.items.Recipe;
import com.shatteredpixel.shatteredpixeldungeon.items.dust.BlueDust;
import com.shatteredpixel.shatteredpixeldungeon.items.dust.GreenDust;
import com.shatteredpixel.shatteredpixeldungeon.items.dust.RedDust;
import com.shatteredpixel.shatteredpixeldungeon.items.dust.WhiteDust;
import com.shatteredpixel.shatteredpixeldungeon.items.gem.BlueGem;
import com.shatteredpixel.shatteredpixeldungeon.items.gem.ColorGem;
import com.shatteredpixel.shatteredpixeldungeon.items.gem.GreenGem;
import com.shatteredpixel.shatteredpixeldungeon.items.gem.RedGem;
import com.shatteredpixel.shatteredpixeldungeon.items.gem.WhiteGem;
import com.shatteredpixel.shatteredpixeldungeon.items.gem.YellowGem;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.tier2.Eleove;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.tier4.FireGlove;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.tier2.GemGlove;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.tier4.LiuHuo;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.tier1.Quiet;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.tier3.SurgingWaves;

public class AlchemyWeapon extends MeleeWeapon {
//拳套升级--------------------------------------------------------------------------------------------------
    public static class GemGloveRecipe extends Recipe.SimpleRecipe{
        {
            inputs =  new Class[]{WhiteGem.class, WhiteDust.class};
            inQuantity = new int[]{2,1};

            cost = 5;

            output = GemGlove.class;
            outQuantity = 1;
        }
    }

    public static class EleoveRecipe extends Recipe.SimpleRecipe{
        {
            inputs =  new Class[]{GemGlove.class, ColorGem.class, YellowGem.class, WhiteDust.class};
            inQuantity = new int[]{1,1,1,1};

            cost = 8;

            output = Eleove.class;
            outQuantity = 1;
        }
    }

    public static class FireGloveRecipe extends Recipe.SimpleRecipe{
        {
            inputs =  new Class[]{Eleove.class, RedGem.class, RedDust.class};
            inQuantity = new int[]{1,2,1};

            cost = 12;

            output = FireGlove.class;
            outQuantity = 1;
        }
    }
//--------------------------------------------------------------------------------------------------
    public static class GreenRecipe extends Recipe.SimpleRecipe{
      {
         inputs =  new Class[]{GreenGem.class, GreenDust.class};
            inQuantity = new int[]{2,1};

            cost = 5;

            output = Quiet.class;
            outQuantity = 1;
        }
    }

    public static class RedRecipe extends Recipe.SimpleRecipe{
        {
            inputs =  new Class[]{RedGem.class, RedDust.class};
            inQuantity = new int[]{2,1};

            cost = 5;

            output = LiuHuo.class;
            outQuantity = 1;
        }
    }

    public static class BlueRecipe extends Recipe.SimpleRecipe{
        {
            inputs =  new Class[]{BlueGem.class, BlueDust.class};
            inQuantity = new int[]{2,1};

            cost = 5;

            output = SurgingWaves.class;
            outQuantity = 1;
        }
    }
//--------------------------------------------------------------------------------------------------

//--------------------------------------------------------------------------------------------------

//--------------------------------------------------------------------------------------------------

//--------------------------------------------------------------------------------------------------

//--------------------------------------------------------------------------------------------------

//--------------------------------------------------------------------------------------------------

//--------------------------------------------------------------------------------------------------

//--------------------------------------------------------------------------------------------------

//--------------------------------------------------------------------------------------------------

//--------------------------------------------------------------------------------------------------

//--------------------------------------------------------------------------------------------------

//--------------------------------------------------------------------------------------------------
}