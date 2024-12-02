package com.shatteredpixel.shatteredpixeldungeon.items.areaitem;

import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class Shell extends AreaItemModel {
    {
        image= ItemSpriteSheet.SHELL;
    }

    @Override
    public int quantity() {
        return 50;
    }
}
