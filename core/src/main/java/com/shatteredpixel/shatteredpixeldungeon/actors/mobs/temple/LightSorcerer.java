package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.temple;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.newbuff.RchAddBy2;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.sprites.GnollTricksterSprite;

public class LightSorcerer extends Mob {
    {
        spriteClass = GnollTricksterSprite.class;
        HT=HP = 100;
        baseSpeed = 1.0f;

    }

    @Override
    protected boolean act() {
        //拥有两格攻击距离
        Buff.affect(this, RchAddBy2.class);
        if(ATTACKPOWER>=2f){
            ATTACKPOWER=2f;
        }
        return super.act();
    }

    @Override
    protected boolean canAttack( Char enemy ) {
        return new Ballistica( pos, enemy.pos, Ballistica.MAGIC_BOLT).collisionPos == enemy.pos;
    }

}

