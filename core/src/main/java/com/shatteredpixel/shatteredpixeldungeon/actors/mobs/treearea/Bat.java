package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.treearea;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Cripple;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Haste;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.newbuff.HuntingMarkers;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.items.bossloot.BossLoot;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfHealing;
import com.shatteredpixel.shatteredpixeldungeon.sprites.BatSprite;
import com.watabou.utils.Random;

public class Bat extends Mob {

    {
        spriteClass = BatSprite.class;

        HP = HT = 20+Random.Int(2+(BossLoot.infection*2));
        defenseSkill = 5;
        baseSpeed = 2f;

        EXP = 7;
        maxLvl = 15;

        flying = true;

        loot = new PotionOfHealing();
        lootChance = 0.23f; //by default, see rollToDropLoot()
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange( 3, 14 );
    }

    @Override
    public int attackSkill( Char target ) {
        return 16;
    }

    @Override
    public int drRoll() {
        return Random.NormalIntRange(0, 4);
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



}