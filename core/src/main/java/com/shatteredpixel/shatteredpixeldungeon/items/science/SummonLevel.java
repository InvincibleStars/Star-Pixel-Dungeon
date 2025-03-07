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

package com.shatteredpixel.shatteredpixeldungeon.items.science;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

import java.util.ArrayList;

public class SummonLevel extends Item {


    {
        image = ItemSpriteSheet.GREATSHIELD;
    }

    @Override //按钮设定（存在调用）
    public ArrayList<String> actions(Hero hero) {
        ArrayList<String> actions = super.actions(hero);
        actions.remove(AC_DROP);
        actions.remove(AC_THROW);
        return actions;
    }

    @Override //物品价值
    public int value() {
        boolean pasty = false;
        return 3000;
    }

    @Override
    public boolean isUpgradable() {
    return  true;
    }

//    @Override //升级设定
//    public boolean isUpgradable() {
//        boolean pasty = false;
//        boolean ration = false;
//
//        Sword a = Dungeon.hero.belongings.getItem(Sword.class);
//        WarHammer b = Dungeon.hero.belongings.getItem(WarHammer.class);
//        Hero hero = new Hero();
//
//        if(a!=null&&b!=null){
//        for (Heap heap: Dungeon.level.heaps.valueList()) {
//            for (Item i : heap.items) {
//                if (i instanceof Sword) {
//                    pasty = true;
//                }
//                if (i instanceof WarHammer) {
//                    ration = true;
//                }
//            }
//        }
//        }if(pasty && ration){
//            return true;
//        }else {
//            return false;
//        }
//    }

}
