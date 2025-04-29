package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.tree;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.newbuff.HuntingMarkers;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.areaitem.DeathBleed;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfHealing;
import com.shatteredpixel.shatteredpixeldungeon.sprites.BatSprite;
import com.watabou.utils.Random;

public class Bat extends Mob {

    {
        spriteClass = BatSprite.class;

        HP=HT=32;

        EXP=8;
        maxLvl=15;

        flying = true;

        loot = new PotionOfHealing();
        lootChance = 0.23f; //by default, see rollToDropLoot()

    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(3, 16);
    }


    	@Override
    	public int attackProc(Char enemy,int damage){
    		damage=super.attackProc( enemy, damage );
            //标记存在
            if(enemy.buff(HuntingMarkers.class)!= null){
                int reg=Math.min(damage-4,HT-HP);
                if(reg>0){
                    HP+=reg;
                    sprite.emitter().burst(Speck.factory(Speck.HEALING), 1);
                }
            }
            //只有对无标记的单位才会上标记
            if(enemy.buff( HuntingMarkers.class ) == null
              && Random.Int(5) == 0) {
                Buff.affect(enemy, HuntingMarkers.class, Random.Float(3f,8f) );
            }
    		return damage;
    	}
    @Override
    protected Item createLoot() {
        Item loot;
        float a = Random.Float();
        float b = Random.Float();
        if(a<=(0.2f * ((6f - Dungeon.LimitedDrops.BAT2_LOOT.count) / 6f))){
            loot = Generator.random(Generator.Category.STONE);
            Dungeon.LimitedDrops.BAT2_LOOT.count++;
        } else {
            if(b<=0.4f){
                loot = new DeathBleed();
            }else {
                loot = Generator.random(Generator.Category.SEED);
            }
        }
        return loot;
    }










}