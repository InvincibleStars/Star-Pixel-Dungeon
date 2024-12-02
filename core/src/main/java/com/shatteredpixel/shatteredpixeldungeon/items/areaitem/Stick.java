package com.shatteredpixel.shatteredpixeldungeon.items.areaitem;

import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class Stick extends AreaItemModel {

    {
        image= ItemSpriteSheet.STICK;
    }

    @Override
    public int quantity() {
        return 50;
    }
}
