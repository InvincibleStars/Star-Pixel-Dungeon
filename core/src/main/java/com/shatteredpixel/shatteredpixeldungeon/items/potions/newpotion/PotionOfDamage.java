package com.shatteredpixel.shatteredpixeldungeon.items.potions.newpotion;

import static com.shatteredpixel.shatteredpixeldungeon.items.potions.Potion.AC_DRINK;

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.newbuff.DamagePotion;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;

import java.util.ArrayList;

public class PotionOfDamage extends Item {

    {
        stackable = true;
        defaultAction = AC_DRINK;
        identify();
        icon = ItemSpriteSheet.POTION_HOLDER;
    }

    @Override
    public ArrayList<String> actions(Hero hero ) {
        ArrayList<String> actions = super.actions( hero );
        actions.add( AC_DRINK );
        return actions;
    }

    @Override
    public void execute( final Hero hero, String action ) {
        super.execute( hero, action );
        if (action.equals( AC_DRINK )) {
            identify();
            Buff.affect( hero, DamagePotion.class, DamagePotion.DURATION+5*level );
            GLog.i( Messages.get(this, "msg_1") );
        }
    }


    @Override
    public int value() {
        return 30;
    }
}
