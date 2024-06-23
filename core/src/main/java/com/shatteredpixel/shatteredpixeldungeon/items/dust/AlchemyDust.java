package com.shatteredpixel.shatteredpixeldungeon.items.dust;

import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.Recipe;
import com.shatteredpixel.shatteredpixeldungeon.items.gem.Gem;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.Potion;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.ExoticPotion;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.PotionOfCleansing;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.PotionOfDragonsBreath;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.PotionOfStormClouds;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.Scroll;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ExoticScroll;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfMysticalEnergy;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfPrismaticImage;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfPsionicBlast;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndBag;
import com.watabou.utils.Random;
import com.watabou.utils.Reflection;

import java.util.ArrayList;
import java.util.HashMap;

public class AlchemyDust {


    public static class DusttoExoticPotion extends Recipe {

        public static HashMap<Class<?extends Dust>, Class<?extends ExoticPotion>> types = new HashMap<>();
        static {
            types.put(BlueDust.class, PotionOfStormClouds.class);
            types.put(RedDust.class, PotionOfDragonsBreath.class);
            types.put(GreenDust.class, PotionOfCleansing.class);
        }

        @Override
        public boolean testIngredients(ArrayList<Item> ingredients) {
            if (ingredients.size() != 1) {
                return false;
            }

            for (Item ingredient : ingredients){
                if (!(ingredient instanceof Dust
                        && ingredient.quantity() >= 1
                        && types.containsKey(ingredient.getClass()))){
                    return false;
                }
            }
            return true;
        }

        @Override
        public int cost(ArrayList<Item> ingredients) {
            return 2;
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

    public static class DusttoExoticScroll extends Recipe {

        public static HashMap<Class<?extends Dust>, Class<?extends ExoticScroll>> types = new HashMap<>();
        static {
            types.put(BlueDust.class,     ScrollOfPrismaticImage.class);
            types.put(RedDust.class,    ScrollOfPsionicBlast .class);
            types.put(GreenDust.class,     ScrollOfMysticalEnergy.class);
        }
        @Override
        public boolean testIngredients(ArrayList<Item> ingredients) {
            if (ingredients.size() != 1) {
                return false;
            }

            for (Item ingredient : ingredients){
                if (!(ingredient instanceof Dust
                        && ingredient.quantity() >= 1
                        && types.containsKey(ingredient.getClass()))){
                    return false;
                }
            }
            return true;
        }

        @Override
        public int cost(ArrayList<Item> ingredients) {
            return 2;
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

    public static class BlueDusttoPotion extends Recipe.SimpleRecipe {
        {
            inputs = new Class[]{BlueDust.class};
            inQuantity = new int[]{1};
            cost = 2;
            output = PotionOfStormClouds.class;
            outQuantity = 1;
        }
    }
    public static class RedDusttoPotion extends Recipe.SimpleRecipe {
        {
            inputs = new Class[]{RedDust.class};
            inQuantity = new int[]{1};
            cost = 2;
            output = PotionOfDragonsBreath.class;
            outQuantity = 1;
        }
    }
    public static class GreenDusttoPotion extends Recipe.SimpleRecipe {
        {
            inputs = new Class[]{GreenDust.class};
            inQuantity = new int[]{1};
            cost = 2;
            output = PotionOfCleansing.class;
            outQuantity = 1;
        }
    }

    public static class BlueDusttoScroll extends Recipe.SimpleRecipe {
        {
            inputs = new Class[]{BlueDust.class};
            inQuantity = new int[]{1};
            cost = 2;
            output = ScrollOfPrismaticImage.class;
            outQuantity = 1;
        }
    }
    public static class RedDusttoScroll extends Recipe.SimpleRecipe {
        {
            inputs = new Class[]{RedDust.class};
            inQuantity = new int[]{1};
            cost = 2;
            output = ScrollOfPsionicBlast.class;
            outQuantity = 1;
        }
    }
    public static class GreenDusttoScroll extends Recipe.SimpleRecipe {
        {
            inputs = new Class[]{GreenDust.class};
            inQuantity = new int[]{1};
            cost = 2;
            output = ScrollOfMysticalEnergy.class;
            outQuantity = 1;
        }
    }


}