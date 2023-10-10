package com.shatteredpixel.shatteredpixeldungeon.items.goodbag;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;

import java.util.ArrayList;

public abstract class BagSuper extends Item {
    private static final String AC_OPEN = "OPEN";

    {
        defaultAction = AC_OPEN;
        stackable = true;
    }

    @Override
    public ArrayList<String> actions(Hero hero) {
        ArrayList<String> actions = super.actions(hero);
        actions.add(AC_OPEN);
        return actions;
    }

    @Override
    public boolean isUpgradable() {
        return false;
    }

    @Override
    public boolean isIdentified() {
        return true;
    }

    @Override
    public int value() {
        return quantity * 3000;
    }

    protected abstract ArrayList<Item> items();

    @Override
    public void execute(Hero hero, String action) {
        super.execute(hero, action);
        if (action.equals(AC_OPEN)) {
            detach(hero.belongings.backpack);
            ArrayList<Item> items = items();
            for (Item item : items) {
                if (item != null) {

                    if (!item.doPickUp(hero, hero.pos, 0f)) {
                        Dungeon.level.drop(item, hero.pos).sprite.drop();
                    } else {

                        GLog.i(Messages.get(Hero.class, "you_now_have", item.name()));
                    }
                }
            }
            hero.spendAndNext(Actor.TICK);
        }
    }
}


