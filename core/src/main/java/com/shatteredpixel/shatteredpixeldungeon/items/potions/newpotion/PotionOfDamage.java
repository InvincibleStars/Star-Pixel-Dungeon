package com.shatteredpixel.shatteredpixeldungeon.items.potions.newpotion;

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.newbuff.DamagePotion;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.Potion;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;

public class PotionOfDamage extends Potion {


    {
        identify();
        icon = ItemSpriteSheet.Icons.POTION_MINDVIS;
    }

    @Override
    public void apply( Hero hero ) {
        identify();
        Buff.affect( hero, DamagePotion.class, DamagePotion.DURATION+5*level );
        GLog.i( Messages.get(this, "msg_1") );
    }

    @Override
    public int value() {
        return isKnown() ? 30 * quantity : super.value();
    }
}
