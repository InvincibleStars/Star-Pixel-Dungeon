package com.shatteredpixel.shatteredpixeldungeon.items.lock;

import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class NeedKnow extends Item {

    {
        image= ItemSpriteSheet.LOCK;
    }

    @Override
    public String status() {
            return "lock!";
    }

}