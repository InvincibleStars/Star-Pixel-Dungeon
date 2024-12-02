package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.tier4;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Bleeding;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.Random;

public class BleedTooth extends MeleeWeapon {
    {
        image = ItemSpriteSheet.BLEED_TOOTH;
         hitSound = Assets.Sounds.HIT_SLASH;
         hitSoundPitch = 1f;
         tier=4;
    }

    @Override
    public int max(int lvl) {
        return  Math.round(5.5f*(tier+1) + lvl*(tier+1) + masteryPotionBonus*2);
    }

    @Override
    public int proc(Char attacker, Char defender, int damage) {
        if(Random.Float(1)<=0.15f){
            Buff.affect(defender, Bleeding.class).set(3f);
        }
        if (defender.buff(Bleeding.class)!=null) {
            attacker.HP=Math.max(attacker.HT, attacker.HP + 4);
        }
        return super.proc(attacker, defender, damage);
    }
}
