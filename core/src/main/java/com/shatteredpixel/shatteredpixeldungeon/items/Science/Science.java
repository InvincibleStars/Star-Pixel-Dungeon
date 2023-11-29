package com.shatteredpixel.shatteredpixeldungeon.items.Science;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ShamanSprite;

import java.util.ArrayList;

public class Science extends Item {


        //发光颜色，速度
        private static ItemSprite.Glowing BLUE = new ItemSprite.Glowing( 0x00FFFF ,0.5f);
        //使用发光颜色
        public ItemSprite.Glowing glowing() {return BLUE;}

    @Override //按钮设定（存在调用）
    public ArrayList<String> actions(Hero hero) {
        ArrayList<String> actions = super.actions(hero);
        actions.remove(AC_DROP);
        actions.remove(AC_THROW);
        return actions;
    }

    @Override //物品价值
    public int value() {
        return 0;
    }

    @Override
    public boolean isUpgradable() {
        return true;
    }
}
