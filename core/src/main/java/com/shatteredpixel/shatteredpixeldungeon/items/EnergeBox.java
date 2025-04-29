package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Recharging;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.utils.Bundle;

import java.util.ArrayList;

public class EnergeBox extends Item {

    {
        identify();
        defaultAction=AC_USE;
    }
    public static final String AC_USE	= "ac_use";


    @Override
    public ArrayList<String> actions(Hero hero ) {
        ArrayList<String> actions = super.actions( hero );
        //actions.remove( AC_LOOK );
        if(this.energe>=100) {
            actions.add(AC_USE);
        }
        return actions;
    }

    public float energe=0;

    public void serLevel(){
        this.level=(int)this.energe;
        updateQuickslot();
    }

    @Override
    public void execute( Hero hero, String action ) {

        super.execute( hero, action );

        if (action.equals( AC_USE )) {
            if(this.energe>=100){
                this.energe-=100;
            GLog.i( Messages.get(this, "skill") );
            Buff.prolong(hero, Recharging.class, 90f);
        }else {
                GLog.i( Messages.get(this, "no") );
            }
        }
    }


    @Override
    public String info() {
        String info = desc();
        info += "\n\n" + Messages.get(this, "tips",energe);
        return info;
    }

    private static final String ENERGE         = "energe";

    @Override
    public void storeInBundle( Bundle bundle ) {
        super.storeInBundle(bundle);
        bundle.put( ENERGE, energe );
    }

    @Override
    public void restoreFromBundle( Bundle bundle ) {
        super.restoreFromBundle( bundle );
        energe = bundle.getFloat(ENERGE);
    }




}
