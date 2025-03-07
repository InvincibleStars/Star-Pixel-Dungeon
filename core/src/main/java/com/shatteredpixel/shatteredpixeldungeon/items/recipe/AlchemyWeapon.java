package com.shatteredpixel.shatteredpixeldungeon.items.recipe;

import com.shatteredpixel.shatteredpixeldungeon.Bones;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.LiquidMetal;
import com.shatteredpixel.shatteredpixeldungeon.items.Recipe;
import com.shatteredpixel.shatteredpixeldungeon.items.areaitem.Bone;
import com.shatteredpixel.shatteredpixeldungeon.items.areaitem.DeathBleed;
import com.shatteredpixel.shatteredpixeldungeon.items.areaitem.Shell;
import com.shatteredpixel.shatteredpixeldungeon.items.areaitem.Silme;
import com.shatteredpixel.shatteredpixeldungeon.items.areaitem.NoCruseWood;
import com.shatteredpixel.shatteredpixeldungeon.items.areaitem.Tooth;
import com.shatteredpixel.shatteredpixeldungeon.items.areaitem.shop.Burin;
import com.shatteredpixel.shatteredpixeldungeon.items.star.dust.FireDust;
import com.shatteredpixel.shatteredpixeldungeon.items.star.dust.GrassDust;
import com.shatteredpixel.shatteredpixeldungeon.items.star.dust.WaterDust;
import com.shatteredpixel.shatteredpixeldungeon.items.star.dust.WeaponDust;
import com.shatteredpixel.shatteredpixeldungeon.items.star.gem.ColorGem;
import com.shatteredpixel.shatteredpixeldungeon.items.star.gem.FireGem;
import com.shatteredpixel.shatteredpixeldungeon.items.star.gem.WeaponGem;
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
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.tier2.Shortsword;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.tier2.ToothNail;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.tier3.BoneSword;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.tier3.DeadBook;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.tier3.ShellHammer;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.tier3.SurgingWaves;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.tier3.Sword;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.tier3.Whip;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.tier4.BleedTooth;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.tier4.FireGlove;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.tier4.FlowingFire;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndBag;

import java.util.ArrayList;


public class AlchemyWeapon extends Item {

    //武器前置素材合成

    //
    public static class GemGloveRecipe extends Recipe.SimpleRecipe {
        {
            inputs = new Class[]{WeaponGem.class, WeaponDust.class};
            inQuantity = new int[]{2, 1};
            cost = 5;
            outQuantity = 1;
            output = GemGlove.class;
        }
    }

    public static class BurinRecipe extends Recipe.SimpleRecipe {
        {
            inputs = new Class[]{Burin.class, Bones.class};
            inQuantity = new int[]{2, 1};
            cost = 5;
            outQuantity = 1;
            output = GemGlove.class;
        }
    }

    //武器正式合成


    //牙钉（齿龈+强化结晶）
    public static class ToothNailRecipe extends Recipe.SimpleRecipe {
        {
            inputs = new Class[]{Tooth.class, WeaponGem.class};
            inQuantity = new int[]{1, 1};
            cost = 5;
            outQuantity = 1;
            output = ToothNail.class;
        }
    }

    //亵渎骨剑（骨头+刻刀+魂灵血液）
    public static class BoneSwordRecipe extends Recipe.SimpleRecipe {
        {
            inputs = new Class[]{Bone.class, DeathBleed.class, Burin.class};
            inQuantity = new int[]{1, 1, 1};
            cost = 5;
            outQuantity = 1;
            output = BoneSword.class;
        }
    }

    //嗜血尖牙（齿龈+不洁之血）
    public static class BleedToothRecipe extends Recipe.SimpleRecipe {
        {
            inputs = new Class[]{Tooth.class, WeaponGem.class};
            inQuantity = new int[]{1, 1};
            cost = 5;
            outQuantity = 1;
            output = BleedTooth.class;
        }
    }

    //齿龈镖（牙钉+液金）
    public static class LongToothRecipe extends Recipe.SimpleRecipe {
        {
            inputs = new Class[]{ToothNail.class, LiquidMetal.class};
            inQuantity = new int[]{1, 10};
            cost = 5;
            outQuantity = 1;
            output = ToothNail.class;
        }
    }

    //骨牙杖（牙钉+枯骨）
    public static class WandToothRecipe extends Recipe.SimpleRecipe {
        {
            inputs = new Class[]{Tooth.class, WeaponGem.class};
            inQuantity = new int[]{1, 1};
            cost = 5;
            outQuantity = 1;
            output = ToothNail.class;
        }
    }

    public static class ShellHammerRecipe extends Recipe.SimpleRecipe {
        {
            inputs = new Class[]{Shell.class, NoCruseWood.class, Silme.class};
            inQuantity = new int[]{1, 1, 1};
            cost = 5;
            outQuantity = 1;
            output = ShellHammer.class;
        }
    }

    public static class EleoveRecipe extends Recipe.SimpleRecipe {
        {
            inputs = new Class[]{GemGlove.class, ColorGem.class, WeaponGem.class, WeaponDust.class};
            inQuantity = new int[]{1, 1, 1, 1};
            cost = 8;
            outQuantity = 1;
            output = Eleove.class;
        }
    }

    public static class FireGloveRecipe extends Recipe.SimpleRecipe {
        {
            inputs = new Class[]{Eleove.class, FireGem.class, FireDust.class};
            inQuantity = new int[]{1, 2, 1};
            cost = 12;
            outQuantity = 1;
            output = FireGlove.class;
        }
    }

    public static class GreenRecipe extends Recipe.SimpleRecipe {
        {
            inputs = new Class[]{Shortsword.class, GrassDust.class};
            inQuantity = new int[]{1, 1};
            cost = 5;
            outQuantity = 1;
            output = Quiet.class;
        }
    }

    public static class RedRecipe extends Recipe.SimpleRecipe {
        {

            inputs = new Class[]{Sword.class, FireDust.class};
            inQuantity = new int[]{1, 1};
            cost = 5;
            outQuantity = 1;
            output = FlowingFire.class;
        }
    }

    public static class BlueRecipe extends Recipe.SimpleRecipe {
        {
            inputs = new Class[]{Whip.class, WaterDust.class};
            inQuantity = new int[]{1, 1};
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