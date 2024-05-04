package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.treearea;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Cripple;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.Ratmogrify;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.bossloot.BossLoot;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.RatSprite;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class Rat extends Mob {

    {
        spriteClass = RatSprite.class;

        HP = HT = 17+Random.Int(2+(BossLoot.infection*2));
        defenseSkill = 6;
        EXP = 3;
        maxLvl = 11;

        loot = Generator.Category.POTION;
        lootChance = 0.25f;
    }

    @Override
    protected boolean act() {
        if (Dungeon.level.heroFOV[pos] && Dungeon.hero.armorAbility instanceof Ratmogrify){
            alignment = Alignment.ALLY;
            if (state == SLEEPING) state = WANDERING;
        }
        return super.act();
    }




    @Override
    public int attackProc(Char enemy, int damage) {
        damage = super.attackProc( enemy, damage );
        if (Random.Int(5) == 0) {
            damage+=2;
            this.sprite.showStatus(CharSprite.NEGATIVE, "!");
        }
        return damage;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange( 1, 10 + BossLoot.infection );
    }

    @Override
    public int attackSkill( Char target ) {
        return 14;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 3);
    }


    private static final String RAT_ALLY = "rat_ally";

    @Override
    public void storeInBundle(Bundle bundle) {
        super.storeInBundle(bundle);
        if (alignment == Alignment.ALLY) bundle.put(RAT_ALLY, true);
    }

    @Override
    public void restoreFromBundle(Bundle bundle) {
        super.restoreFromBundle(bundle);
        if (bundle.contains(RAT_ALLY)) alignment = Alignment.ALLY;
    }
}