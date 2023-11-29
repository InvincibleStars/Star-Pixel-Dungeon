package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee;

import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class HeartShield extends  ShieldType {

    {
        image = ItemSpriteSheet.GREATSHIELD;

        tier = 2;
    }

    //加成域
    public  static  int healthboost = 0;

    public  int maxboost(int lvl) {
        //基础加成
        int healthboost =3+level();
        //如果两个计数差达到理论最大值，则拥有1/3的概率
        if (healthboost < 2) {
            healthboost = 2;
        }
        return  healthboost;
    }











}
