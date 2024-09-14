package com.shatteredpixel.shatteredpixeldungeon.items.material;

import com.shatteredpixel.shatteredpixeldungeon.items.Item;

public class Material extends Item {

    public boolean unique() {
        return false;
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
