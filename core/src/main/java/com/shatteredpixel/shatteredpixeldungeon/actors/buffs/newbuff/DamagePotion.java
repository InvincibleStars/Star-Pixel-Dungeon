package com.shatteredpixel.shatteredpixeldungeon.actors.buffs.newbuff;

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;

public class DamagePotion extends FlavourBuff {

    public static final float DURATION = 20f;

    {
        type = buffType.POSITIVE;
    }

    @Override
    public int icon() {
        return BuffIndicator.WEAPON;
    }

    @Override
    public String toString() {
        return Messages.get(this, "name");
    }

    @Override
    public float iconFadePercent() {
        return Math.max(0, (DURATION - visualcooldown()) / DURATION);
    }

    @Override
    public String desc() {
        return Messages.get(this, "desc", dispTurns());
    }


}
