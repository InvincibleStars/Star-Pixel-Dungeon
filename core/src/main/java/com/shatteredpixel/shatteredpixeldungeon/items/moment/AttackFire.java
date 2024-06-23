package com.shatteredpixel.shatteredpixeldungeon.items.moment;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FireImbue;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;

public class AttackFire extends Moment {
    {
        curCharges=5;
        maxCharges=5;
        image= ItemSpriteSheet.BURN_GEM_MAKER;
    }

    @Override
    public void execute( Hero hero, String action ) {
        super.execute( hero, action );
        if (action.equals( AC_USE )) {
            if (curCharges<1){
                GLog.w(Messages.get(Moment.class, "lesspos"));
            }else {
                curUser = hero;
                curItem = this;
                Buff.affect(hero, FireImbue.class).set(5f);
                curCharges--;
                Sample.INSTANCE.play(Assets.Sounds.BURNING);
            }
        }
        updateQuickslot();
    }


}