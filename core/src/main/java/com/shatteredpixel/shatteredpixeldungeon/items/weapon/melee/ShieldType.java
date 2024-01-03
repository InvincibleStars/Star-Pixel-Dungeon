package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Bleeding;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Vertigo;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.utils.Random;

public class ShieldType extends MeleeWeapon {
    {

    }
    //参与运算的int
    public int morestr= Dungeon.hero.STR;

    public int tieradd=tier/2;

    //预留参数
    public int int1=0;
    public float float1=0;
    public boolean boolean1=true;
    int maxint = 0;


        //计数上限阈
        public  int maxint() {
            //如果存在0阶武器且不存在力量差，则拥有1/11的概率




            if(Dungeon.hero!=null){
                int maxint = 10-(Dungeon.hero.STR+tieradd);
            }else{
                int maxint = 10-tieradd;
            }
            //如果两个计数差达到理论最大值，则拥有1/3的概率
            if (maxint < 2) {
                maxint = 2;
         }
                return  maxint;
    }

   // @Override
    public int proc(Char attacker, Char defender, int damage, Hero hero ) {
        if(
                //随着力量的增加，概率逐渐由5%提高至40%
                Random.Int(0,maxint())==0) {
            Buff.prolong(defender, Vertigo.class, 1f);

            hero.sprite.showStatus(CharSprite.POSITIVE, "撞击");
        }
        return super.proc(attacker, defender, damage);
    }
}

