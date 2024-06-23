package com.shatteredpixel.shatteredpixeldungeon.items.gem;

import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.Recipe;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.Potion;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfHaste;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfLevitation;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfLiquidFlame;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.Scroll;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfLullaby;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfMirrorImage;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRage;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfUpgrade;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndBag;
import com.watabou.utils.Random;
import com.watabou.utils.Reflection;

import java.util.ArrayList;
import java.util.HashMap;

public class AlchemyGem {

    //最简单的合成表
    public static class YellowGemToUpdateScroll extends Recipe.SimpleRecipe {
        {
            inputs = new Class[]{YellowGem.class};
            inQuantity = new int[]{3};

            cost = 4;

            output = ScrollOfUpgrade.class;
            outQuantity = 1;
        }
    }

    public static class GemtoPotion extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe {

        public static HashMap<Class<?extends Gem>, Class<?extends Potion>> types = new HashMap<>();
        static {
            types.put(BlueGem.class,PotionOfLevitation.class);
            types.put(RedGem.class,PotionOfLiquidFlame.class);
            types.put(GreenGem.class,PotionOfHaste.class);
        }

        @Override
        public boolean testIngredients(ArrayList<Item> ingredients) {
            if (ingredients.size() != 1) {
                return false;
            }

            for (Item ingredient : ingredients){
                if (!(ingredient instanceof Gem
                        && ingredient.quantity() >= 1
                        && types.containsKey(ingredient.getClass()))){
                    return false;
                }
            }
            return true;
        }

        @Override
        public int cost(ArrayList<Item> ingredients) {
            return 1;
        }

        @Override
        public Item brew(ArrayList<Item> ingredients) {
            if (!testIngredients(ingredients)) return null;

            for (Item ingredient : ingredients){
                ingredient.quantity(ingredient.quantity() - 1);
            }

            ArrayList<Class<?extends Gem>> gem = new ArrayList<>();
            for (Item i : ingredients) {
                if (!gem.contains(i.getClass())) {
                    gem.add((Class<? extends Gem>) i.getClass());
                }
            }

            Potion result;

            if (gem.size() == 1){
                result = (Potion) Generator.randomUsingDefaults( Generator.Category.POTION );
            } else {
                result = Reflection.newInstance(types.get(Random.element(ingredients).getClass()));
            }

            result = Reflection.newInstance(types.get(Random.element(ingredients).getClass()));

            result.identify();

            return result;
        }

        @Override
        public Item sampleOutput(ArrayList<Item> ingredients) {
            return new WndBag.Placeholder(ItemSpriteSheet.POTION_HOLDER){

                @Override
                public String name() {

                    return Messages.get(this, "potion");
                }



                @Override
                public String info() {
                    return "";
                }
            };
        }
    }

    public static class GemtoScroll extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe {

        public static HashMap<Class<?extends Gem>, Class<?extends Scroll>> types = new HashMap<>();
        static {
            types.put(BlueGem.class,     ScrollOfMirrorImage.class);
            types.put(RedGem.class,    ScrollOfRage .class);
            types.put(GreenGem.class,     ScrollOfLullaby.class);
        }

        @Override
        public boolean testIngredients(ArrayList<Item> ingredients) {
            if (ingredients.size() != 1) {
                return false;
            }

            for (Item ingredient : ingredients){
                if (!(ingredient instanceof Gem
                        && ingredient.quantity() >= 1
                        && types.containsKey(ingredient.getClass()))){
                    return false;
                }
            }
            return true;
        }

        @Override
        public int cost(ArrayList<Item> ingredients) {
            return 1;
        }

        @Override
        public Item brew(ArrayList<Item> ingredients) {
            if (!testIngredients(ingredients)) return null;

            for (Item ingredient : ingredients){
                ingredient.quantity(ingredient.quantity() - 1);
            }

            ArrayList<Class<?extends Gem>> gem = new ArrayList<>();
            for (Item i : ingredients) {
                if (!gem.contains(i.getClass())) {
                    gem.add((Class<? extends Gem>) i.getClass());
                }
            }

            Scroll result;

            if (gem.size() == 1){
                result = (Scroll) Generator.randomUsingDefaults( Generator.Category.SCROLL );
            } else {
                result = Reflection.newInstance(types.get(Random.element(ingredients).getClass()));
            }

            result = Reflection.newInstance(types.get(Random.element(ingredients).getClass()));

            result.identify();

            return result;
        }

        @Override
        public Item sampleOutput(ArrayList<Item> ingredients) {
            return new WndBag.Placeholder(ItemSpriteSheet.SCROLL_HOLDER){

                @Override
                public String name() {
                    return Messages.get(this, "scroll");
                }

                @Override
                public  String desc(){
                    return Messages.get(this,"desc");
                }

                @Override
                public String info() {
                    return "";
                }
            };
        }
    }







    public static class BlueGemtoPotion extends Recipe.SimpleRecipe {
        {
            inputs = new Class[]{BlueGem.class};
            inQuantity = new int[]{1};
            cost = 1;
            output = PotionOfLevitation.class;
            outQuantity = 1;
        }
    }
    public static class RedGemtoPotion extends Recipe.SimpleRecipe {
        {
            inputs = new Class[]{RedGem.class};
            inQuantity = new int[]{1};
            cost = 1;
            output = PotionOfLiquidFlame.class;
            outQuantity = 1;
        }
    }
    public static class GreenGemtoPotion extends Recipe.SimpleRecipe {
        {
            inputs = new Class[]{GreenGem.class};
            inQuantity = new int[]{1};
            cost = 1;
            output = PotionOfHaste.class;
            outQuantity = 1;
        }
    }

    public static class BlueGemtoScroll extends Recipe.SimpleRecipe {
        {
            inputs = new Class[]{BlueGem.class};
            inQuantity = new int[]{1};
            cost = 1;
            output = ScrollOfMirrorImage.class;
            outQuantity = 1;
        }
    }
    public static class RedGemtoScroll extends Recipe.SimpleRecipe {
        {
            inputs = new Class[]{RedGem.class};
            inQuantity = new int[]{1};
            cost = 1;
            output = ScrollOfRage.class;
            outQuantity = 1;
        }
    }
    public static class GreenGemtoScroll extends Recipe.SimpleRecipe {
        {
            inputs = new Class[]{GreenGem.class};
            inQuantity = new int[]{1};
            cost = 1;
            output = ScrollOfLullaby.class;
            outQuantity = 1;
        }
    }




}