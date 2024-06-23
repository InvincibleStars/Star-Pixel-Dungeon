package com.shatteredpixel.shatteredpixeldungeon.items.cube;

import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.gem.Gem;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;

public class Cube extends Item {

    {
        stackable = true;
        unique = true;
    }

    @Override
    public String info() {
        String info = desc();
        info += "\n\n" + Messages.get(Cube.class, "know");
        return info;
    }

    @Override
    public boolean isUpgradable() {
        return false;
    }
    @Override
    public boolean isIdentified() {
        return true;
    }


}