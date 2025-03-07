package com.shatteredpixel.shatteredpixeldungeon.items.star.gem;

import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;

import java.util.ArrayList;

public class AlchemyGem {
    //TODO 炼金继承或许可以考虑炼金工具箱
    //TODO 炼金武器/物品 的合成配方
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
            return new WeaponGem().quantity(level+1);
        }
    }








}