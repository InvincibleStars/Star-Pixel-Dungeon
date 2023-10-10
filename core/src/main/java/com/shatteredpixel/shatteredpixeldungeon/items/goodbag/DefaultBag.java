package com.shatteredpixel.shatteredpixeldungeon.items.goodbag;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
//import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Perks;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Perks;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class DefaultBag extends BagSuper {
    {
        image = ItemSpriteSheet.WEAPON_HOLDER;
    }

    @Override
    protected ArrayList<Item> items() {
        ArrayList<Item> items = new ArrayList<>();
        int amount = Random.Int(600, 1500);
        if (Dungeon.hero.perks.contains(Perks.Perk.MORE_BAG)) amount *= 1.5f;
        //for(int i = 0; i < amount; i++) items.add(Generator.random());
        for(int i = 0; i < amount; i++) items.add(Generator.random());
        //Generator.Category.SCROLL
        return items;
    }

    @Override
    public int value() {
        return 175 * quantity;
    }
}
