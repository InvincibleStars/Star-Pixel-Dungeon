package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee;

import static com.shatteredpixel.shatteredpixeldungeon.Dungeon.hero;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.Heap;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class HammerType extends MeleeWeapon {

    {
        DLY = 1.30f;
        ACC = 1.20f;
    }
    //锤类武器对周围一圈造成伤害
    //但是速度很慢
    @Override
    public int proc(Char attacker, Char defender, int damage ) {
        Char ch;
        damage = damage* 3/4;
        int dam =Random.Int(min()/2,max()/2);

        for( int i: PathFinder.NEIGHBOURS8){
            Heap.burnFX( hero.pos+i );
            if ((ch = Char.findChar(hero.pos +i))!= null){
                ch.damage(dam, this);
            }

        }
        return super.proc(attacker, defender, damage);
    }

    @Override
    public String info() {

        String info = desc();

        info += "\n\n" + Messages.get(MeleeWeapon.class, "hammer", (Dungeon.hero.STR - (tier*2)));

        if (levelKnown) {
            info += "\n\n" + Messages.get(MeleeWeapon.class, "stats_known", tier, augment.damageFactor(min()), augment.damageFactor(max()), STRReq());
            if (STRReq() > hero.STR()) {
                info += " " + Messages.get(Weapon.class, "too_heavy");
            } else if (hero.STR() > STRReq()){
                info += " " + Messages.get(Weapon.class, "excess_str", hero.STR() - STRReq());
            }
        } else {
            info += "\n\n" + Messages.get(MeleeWeapon.class, "stats_unknown", tier, min(0), max(0), STRReq(0));
            if (STRReq(0) > hero.STR()) {
                info += " " + Messages.get(MeleeWeapon.class, "probably_too_heavy");
            }
        }

        String statsInfo = statsInfo();
        if (!statsInfo.equals("")) info += "\n\n" + statsInfo;

        switch (augment) {
            case SPEED:
                info += " " + Messages.get(Weapon.class, "faster");
                break;
            case DAMAGE:
                info += " " + Messages.get(Weapon.class, "stronger");
                break;
            case NONE:
        }

        if (enchantment != null && (cursedKnown || !enchantment.curse())){
            info += "\n\n" + Messages.get(Weapon.class, "enchanted", enchantment.name());
            info += " " + Messages.get(enchantment, "desc");
        }

        if (cursed && isEquipped( hero )) {
            info += "\n\n" + Messages.get(Weapon.class, "cursed_worn");
        } else if (cursedKnown && cursed) {
            info += "\n\n" + Messages.get(Weapon.class, "cursed");
        } else if (!isIdentified() && cursedKnown){
            info += "\n\n" + Messages.get(Weapon.class, "not_cursed");
        }

        return info;
    }



}

