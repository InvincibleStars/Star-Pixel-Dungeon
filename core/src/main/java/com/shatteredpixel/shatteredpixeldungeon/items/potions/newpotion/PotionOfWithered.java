package com.shatteredpixel.shatteredpixeldungeon.items.potions.newpotion;

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.newbuff.UnityWithered;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.Potion;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class PotionOfWithered extends Potion {


    {
        identify();
        image=ItemSpriteSheet.SWORD;
    }

    @Override
    public void apply( Hero hero ) {
        Buff.affect( hero, UnityWithered.class).set(1000000);
    }

    @Override
    public int value() {
        return isKnown() ? 30 * quantity : super.value();
    }
}
