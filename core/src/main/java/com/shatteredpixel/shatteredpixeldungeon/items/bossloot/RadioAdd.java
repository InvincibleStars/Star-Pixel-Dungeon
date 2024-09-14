package com.shatteredpixel.shatteredpixeldungeon.items.bossloot;

import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class RadioAdd extends Item {
    public static final String AC_USE	= "USE";

    public static final float TIME_TO_USE = 1;

    {
        image = ItemSpriteSheet.SKELETON_KEY;

        stackable = true;

        defaultAction = AC_USE;
    }

}
