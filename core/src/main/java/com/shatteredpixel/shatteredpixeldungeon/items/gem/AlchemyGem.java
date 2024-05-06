package com.shatteredpixel.shatteredpixeldungeon.items.gem;

import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfUpgrade;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;

import java.util.ArrayList;

public class AlchemyGem {

    public static class Scroll extends Recipe.SimpleRecipe {
        {
            inputs = new Class[]{YellowGem.class};
            inQuantity = new int[]{3};

            cost = 4;

            output = ScrollOfUpgrade.class;
            outQuantity = 1;
        }
    }

    public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe {

        @Override
        public boolean testIngredients(ArrayList<Item> ingredients) {
            return ingredients.size() == 1 && ingredients.get(0) instanceof MeleeWeapon && ingredients.get(0).isIdentified() && !ingredients.get(0).cursed;
        }

        @Override
        public int cost(ArrayList<Item> ingredients) {
            Weapon w = (Weapon)ingredients.get(0);
            int level = w.level();
            return level*5+5;
        }

        @Override
        public Item brew(ArrayList<Item> ingredients) {
            Item result = sampleOutput(ingredients);
            ingredients.get(0).quantity(0);
            return result;
        }

        @Override
        public Item sampleOutput(ArrayList<Item> ingredients) {
            Weapon w = (Weapon)ingredients.get(0);
            int level = w.level();
            return new YellowGem().quantity(level+1);
        }
    }
}