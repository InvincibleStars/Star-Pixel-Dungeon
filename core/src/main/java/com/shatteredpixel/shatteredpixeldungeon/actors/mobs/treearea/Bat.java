package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.treearea;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.newbuff.HuntingMarkers;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.areaitem.DeathBleed;
import com.shatteredpixel.shatteredpixeldungeon.items.bossloot.BossLoot;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfHealing;
import com.shatteredpixel.shatteredpixeldungeon.sprites.BatSprite;
import com.watabou.utils.Random;

public class Bat extends Mob {

    {
        spriteClass = BatSprite.class;

        HP = HT = 65 + Random.Int(-11,11) + BossLoot.infection*2;
        baseSpeed = 2f;

        EXP = 7;
        maxLvl = 15;

        flying = true;

        loot = new PotionOfHealing();
        lootChance = 0.23f; //by default, see rollToDropLoot()
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange( 3, 24 ) + BossLoot.infection;
    }


    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 15);
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
/*
    protected Item createLoot() {
        Item loot;
        switch(Random.Int(4)){
            case 0: case 1: default:
                loot = new Dewdrop();
                int ofs;
                do {
                    ofs = PathFinder.NEIGHBOURS8[Random.Int(8)];
                } while (Dungeon.level.solid[pos + ofs] && !Dungeon.level.passable[pos + ofs]);
                if (Dungeon.level.heaps.get(pos+ofs) == null) {
                    Dungeon.level.drop(new Dewdrop(), pos + ofs).sprite.drop(pos);
                } else {
                    Dungeon.level.drop(new Dewdrop(), pos + ofs).sprite.drop(pos + ofs);
                }
                break;
            case 2:
                loot = Generator.random(Generator.Category.SEED);
                break;
            case 3:
                loot = Generator.random(Generator.Category.STONE);
                break;
        }
        return loot;
    }
 */











}