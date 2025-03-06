package com.shatteredpixel.shatteredpixeldungeon.items.areaitem;

import com.shatteredpixel.shatteredpixeldungeon.items.Item;

public class AreaItemModel extends Item {

    {
        unique = true;
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
