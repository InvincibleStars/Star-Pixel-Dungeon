package com.shatteredpixel.shatteredpixeldungeon.items.gemdust;


import com.shatteredpixel.shatteredpixeldungeon.items.Item;

public class GemHeap extends Item {

    {
        stackable = true;
        unique = true;
    }

    //不会被爆炸摧毁
    public boolean unique() {
        return false;
    }

    //不能被升级
    @Override
    public boolean isUpgradable() {
        return false;
    }

    //始终是被鉴定的
    @Override
    public boolean isIdentified() {
        return true;
    }

}