package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.tree;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.Ratmogrify;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.areaitem.MobLoot;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.RatSprite;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class Rat extends Mob {

    {
        spriteClass = RatSprite.class;
        HP=HT=23;

        EXP=5;
        maxLvl=15;

        loot=Generator.random(Generator.Category.POTION);
        lootChance = 0.125f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(3, 12);
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
            float a = damageRoll();
            damage+=Random.NormalIntRange(1,(int)(a/2));
            this.sprite.showStatus(CharSprite.NEGATIVE, "!");
        }
        return damage;
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

    @Override
    public void die( Object cause ) {

        super.die( true );

        //Dungeon.LimitedDrops.TEST_NUM.count--;
    }


}