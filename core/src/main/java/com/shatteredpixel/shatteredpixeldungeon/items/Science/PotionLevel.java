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

package com.shatteredpixel.shatteredpixeldungeon.items.Science;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.Heap;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.Potion;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Sword;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.WarHammer;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

import java.util.ArrayList;

public class PotionLevel extends Science {


    {
        image = ItemSpriteSheet.UPDATE6;
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
//        //for (Heap heap: Dungeon.level.heaps.valueList()) {
//            for (Item ac: Dungeon.hero.belongings) {
//            for (Item i :hero.belongings ) {
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

    public boolean isUpgradable() {
        return true;
    }

    @Override
    public int level(){
        //int level = 0;
        Potion.ABBM=level();
        return level;
    }


}
