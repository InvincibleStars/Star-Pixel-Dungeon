package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.tier3;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class ShellHammer extends MeleeWeapon {
    {
        image = ItemSpriteSheet.SHELL_HAMMER;
        hitSound = Assets.Sounds.HIT_SLASH;
        hitSoundPitch = 1f;

        tier = 3;
    }
    @Override
    public int proc(Char attacker, Char defender, int damage) {
        return super.proc(attacker, defender, damage);
    }
    @Override
    public int defenseFactor( Char owner ) {
        return 5;    //6 extra defence, plus 3 per level;
    }
}
