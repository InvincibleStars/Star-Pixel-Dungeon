package com.shatteredpixel.shatteredpixeldungeon.items.areaitem.shop;

import com.shatteredpixel.shatteredpixeldungeon.items.areaitem.AreaItemModel;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class Burin extends AreaItemModel {
    {
        image= ItemSpriteSheet.BURIN;
    }

    @Override
    public int value() {
        return 50;
    }
}
