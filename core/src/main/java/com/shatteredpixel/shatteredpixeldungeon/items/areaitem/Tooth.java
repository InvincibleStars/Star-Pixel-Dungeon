package com.shatteredpixel.shatteredpixeldungeon.items.areaitem;

import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class Tooth extends AreaItemModel {
    {
        image= ItemSpriteSheet.TOOTH;
    }

    @Override
    public int quantity() {
        return 50;
    }
}
