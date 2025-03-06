/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2021 Evan Debenham
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.shatteredpixel.shatteredpixeldungeon.items.star;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class StarType extends Item {

    {
        image = ItemSpriteSheet.SKULL;
        defaultAction = AC_USE;
    }

    public static final String AC_USE ="AC_USE";

    //控制是否被绑定
    public int use = 0;

    public int slot1 = 0;
    public int slot2 = 0;



    @Override
    //激活后无法被取消
    public ArrayList<String> actions(Hero hero ) {
        ArrayList<String> actions = super.actions( hero );
        actions.add(AC_USE);
        if(use==1){
            actions.remove(AC_USE);
            actions.remove(AC_DROP);
            actions.remove(AC_THROW);
        }
        return actions;
    }
    //Roll随机词条
    public int roll = 0;
    @Override
    public boolean doPickUp(Hero hero, int pos) {
        if (super.doPickUp(hero, pos)){
            if(roll!=1) {
                roll = 1;
                slot1 = Random.Int(1, 10);
                slot2 = Random.Int(1, 10);
                return true;
            }
        }
        return false;
    }

    @Override //升级设定
    public boolean isUpgradable() {
        return true;
    }

    @Override //物品价值
    public int value() {
        return 400;
    }

}
