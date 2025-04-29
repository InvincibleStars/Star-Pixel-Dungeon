package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.temple;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Cripple;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.newbuff.RchAddBy2;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.sprites.GnollTricksterSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.GuardSprite;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class Scavenger extends Mob {
    {
        spriteClass = GuardSprite.class;
        baseSpeed = 1.0f;

    }

    boolean skill = true;
    boolean zap = false;

    @Override
    protected boolean act() {
        //拥有两格攻击距离
        Buff.affect(this, RchAddBy2.class);
        if(ATTACKPOWER>=2f){
            ATTACKPOWER=2f;
        }
        return super.act();
    }



    @Override
    protected boolean canAttack( Char enemy ) {

        Ballistica attack = new Ballistica(this.pos, enemy.pos, Ballistica.PROJECTILE);
        if(skill==true && Dungeon.level.trueDistance(this.pos,enemy.pos)>=1) {
            Sample.INSTANCE.play( Assets.Sounds.ATK_SPIRITBOW, 1, Random.Float(0.87f, 1.15f) );
            zap = true;
            skill = false;
            enemy.damage( Random.Int(damageRoll()),attack);
            return true;
        }else if(skill == false && Dungeon.level.trueDistance(this.pos,enemy.pos)<2) {
            return true;
        }else{
            return super.canAttack(enemy);
        }

    }


    @Override
    public int attackProc( Char enemy, int damage ) {
        ATTACKPOWER+=0.2f;
        damage = super.attackProc( enemy, damage );
        if(zap == true){
            zap = false;
            damage=Random.Int(19);
        }
        //重伤加致残
        if(ATTACKPOWER>=2){
            damage*=1.3f;
            Buff.affect(enemy, Cripple.class,5f);
        }

        return damage;
    }

}

