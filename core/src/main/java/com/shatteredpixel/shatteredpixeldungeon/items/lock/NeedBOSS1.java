package com.shatteredpixel.shatteredpixeldungeon.items.lock;

import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class NeedBOSS1 extends Item {

    {
        image= ItemSpriteSheet.LOCK;
    }

    @Override
    public String status() {
            return "lock-1";
    }

}