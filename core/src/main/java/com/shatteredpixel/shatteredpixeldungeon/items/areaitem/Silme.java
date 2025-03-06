package com.shatteredpixel.shatteredpixeldungeon.items.areaitem;

import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class Silme extends AreaItemModel{
    {
        image= ItemSpriteSheet.SLIME;
    }

    @Override
    public int quantity() {
        return 50;
    }
}
