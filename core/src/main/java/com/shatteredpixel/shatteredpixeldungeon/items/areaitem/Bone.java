package com.shatteredpixel.shatteredpixeldungeon.items.areaitem;

import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class Bone extends AreaItemModel{

    {
        image= ItemSpriteSheet.BONE;
    }

    @Override
    public int quantity() {
        return 50;
    }
}
