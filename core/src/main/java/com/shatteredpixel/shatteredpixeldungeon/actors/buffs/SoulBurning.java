package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;

public class SoulBurning extends FlavourBuff{

    {
        type = buffType.NEGATIVE;
        announced = true;

    }

    @Override
    public int icon() {
        return BuffIndicator.FIRE;
    }

    @Override
    public void fx(boolean on) {
        if (on) target.sprite.add(CharSprite.State.BURNING);
        else target.sprite.remove(CharSprite.State.BURNING);
    }

    @Override
    public String toString() {
        return Messages.get(this, "name");
    }

    @Override
    public String desc() {
        return Messages.get(this, "desc", dispTurns());
    }
}
