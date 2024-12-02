package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.tier3;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class BoneSword extends MeleeWeapon {

    {
        image = ItemSpriteSheet.BONE_SWORD;
        hitSound = Assets.Sounds.HIT_SLASH;
        hitSoundPitch = 1f;

        tier = 3;
    }

    @Override
    public int proc(Char attacker, Char defender, int damage) {
        return super.proc(attacker, defender, damage);
    }
}
