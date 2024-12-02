package com.shatteredpixel.shatteredpixeldungeon.items.areaitem;

import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class DeathBleed extends AreaItemModel {
    {
        image= ItemSpriteSheet.DEATH_BLEED;
    }

    @Override
    public int quantity() {
        return 50;
    }


}
