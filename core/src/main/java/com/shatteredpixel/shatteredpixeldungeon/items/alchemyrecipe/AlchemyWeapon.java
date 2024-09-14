package com.shatteredpixel.shatteredpixeldungeon.items.alchemyrecipe;

import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.Recipe;
import com.shatteredpixel.shatteredpixeldungeon.items.dust.BlueDust;
import com.shatteredpixel.shatteredpixeldungeon.items.dust.GreenDust;
import com.shatteredpixel.shatteredpixeldungeon.items.dust.RedDust;
import com.shatteredpixel.shatteredpixeldungeon.items.gem.BlueGem;
import com.shatteredpixel.shatteredpixeldungeon.items.gem.ColorGem;
import com.shatteredpixel.shatteredpixeldungeon.items.gem.GreenGem;
import com.shatteredpixel.shatteredpixeldungeon.items.gem.RedGem;
import com.shatteredpixel.shatteredpixeldungeon.items.gem.WeaponGem;
import com.shatteredpixel.shatteredpixeldungeon.items.material.Book;
import com.shatteredpixel.shatteredpixeldungeon.items.material.GemGlove;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.Potion;
import com.shatteredpixel.shatteredpixeldungeon.items.quest.GooBlob;
import com.shatteredpixel.shatteredpixeldungeon.items.quest.MetalShard;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.CurseInfusion;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.tier1.Quiet;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.tier2.Chain;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.tier2.Eleove;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.tier3.DeadBook;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.tier3.SurgingWaves;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.tier4.FireGlove;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.tier4.FlowingFire;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndBag;

import java.util.ArrayList;


public class AlchemyWeapon extends MeleeWeapon {

    //武器前置素材合成

    //
    public static class GemGloveRecipe extends Recipe.SimpleRecipe {
        {
            inputs = new Class[]{WeaponGem.class, WeaponGem.class};
            inQuantity = new int[]{2, 1};
            cost = 5;
            outQuantity = 1;
            output = GemGlove.class;
        }
    }

    public static class EleoveRecipe extends Recipe.SimpleRecipe {
        {
            inputs = new Class[]{GemGlove.class, ColorGem.class, WeaponGem.class, WeaponGem.class};
            inQuantity = new int[]{1, 1, 1, 1};
            cost = 8;
            outQuantity = 1;
            output = Eleove.class;
        }
    }

    public static class FireGloveRecipe extends Recipe.SimpleRecipe {
        {
            inputs = new Class[]{Eleove.class, RedGem.class, RedDust.class};
            inQuantity = new int[]{1, 2, 1};
            cost = 12;
            outQuantity = 1;
            output = FireGlove.class;
        }
    }

    public static class GreenRecipe extends Recipe.SimpleRecipe {
        {
            inputs = new Class[]{GreenGem.class, GreenDust.class};
            inQuantity = new int[]{2, 1};
            cost = 5;
            outQuantity = 1;
            output = Quiet.class;
        }
    }

    public static class RedRecipe extends Recipe.SimpleRecipe {
        {

            inputs = new Class[]{RedGem.class, RedDust.class};
            inQuantity = new int[]{2, 1};
            cost = 5;
            outQuantity = 1;
            output = FlowingFire.class;
        }
    }

    public static class BlueRecipe extends Recipe.SimpleRecipe {
        {
            inputs = new Class[]{BlueGem.class, BlueDust.class};
            inQuantity = new int[]{2, 1};
            cost = 5;
            outQuantity = 1;
            output = SurgingWaves.class;
        }
    }


        public static class ChainRecipe extends Recipe.SimpleRecipe {
        {
            inputs = new Class[]{CurseInfusion.class, MetalShard.class};
            inQuantity = new int[]{1,1,1};
            cost = 5;
            outQuantity = 1;
            output = Chain.class;
        }
    }

    public static class DeadBookRecipe extends Recipe.SimpleRecipe {
        {
            inputs = new Class[]{Book .class, CurseInfusion.class, GooBlob.class};
            inQuantity = new int[]{1,1,1};
            cost = 5;
            outQuantity = 1;
            output = DeadBook.class;
        }
    }

    public static class acxc1 extends Recipe.SimpleRecipe {
        {
            inputs = new Class[]{BlueGem.class};
            inQuantity = new int[]{1};
            cost = 5;
            outQuantity = 1;
            output = SurgingWaves.class;
        }
    }

    public Item sampleOutput(ArrayList<Item> ingredients) {
        return new WndBag.Placeholder(ItemSpriteSheet.POTION_HOLDER) {

            @Override
            public String name() {
                return Messages.get(Potion.SeedToPotion.class, "name");
            }

            @Override
            public String info() {
                return "";
            }
        };
    }




}