package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.treearea;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.sprites.BatSprite;
import com.watabou.utils.Random;

public class Bat222 extends Mob {

    {
        spriteClass = BatSprite.class;
        HP = HT = 1;
        EXP = 0;
        maxLvl = 1;
        lootChance = 0.23f; //by default, see rollToDropLoot()
    }
    /*
    @Override
    public void rollToDropLoot() {
        lootChance *= ((6f - Dungeon.LimitedDrops.BAT_HP.count) / 6f);
        super.rollToDropLoot();
    }
     */
    @Override
    protected Item createLoot() {
        Item loot;
        float a = Random.Float();
        if(a<=(0.2f * ((6f - Dungeon.LimitedDrops.BAT2_LOOT.count) / 6f))){
            loot = Generator.random(Generator.Category.STONE);
            Dungeon.LimitedDrops.BAT2_LOOT.count++;
        } else {
            loot = Generator.random(Generator.Category.SEED);
        }
        return loot;
    }
}


