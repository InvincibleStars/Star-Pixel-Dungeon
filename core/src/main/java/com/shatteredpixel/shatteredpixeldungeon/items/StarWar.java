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

package com.shatteredpixel.shatteredpixeldungeon.items;

import static com.shatteredpixel.shatteredpixeldungeon.items.Torch.AC_LIGHT;

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.BuffWait;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Haste;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Vulnerable;
import com.shatteredpixel.shatteredpixeldungeon.items.quest.CorpseDust;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Light;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.FlameParticle;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.particles.Emitter;
import com.watabou.utils.Random;

import java.util.ArrayList;

//头部（公共，继承Item）
public class StarWar extends Item {
    //按钮的文本（应该？）
    public static final String AC_RAINBOWEGG ="AC_RAINBOWEGG";
    {
        //标签
        image = ItemSpriteSheet.SKULL;
        //默认动作
        defaultAction = AC_RAINBOWEGG;
    }
    @Override //按钮设定（存在调用）
    public ArrayList<String> actions(Hero hero ) {
        ArrayList<String> actions = super.actions( hero );
        actions.add( AC_RAINBOWEGG );
        return actions;
    }
    //定义效果的随即回合区间(浮点数)
   public float a = Random.Float(30,50);
    @Override //拾取动作
    public boolean doPickUp(Hero hero, int pos) {
        if (super.doPickUp(hero, pos)){
            //彩蛋消息提示（头顶）
            hero.sprite.showStatus(CharSprite.POSITIVE, "彩蛋");
            //给予效果（浮点数）
            Buff.affect(hero, Haste.class, Random.NormalFloat(30,50));
            return true;
        }
        return false;
    }

    public boolean doThrow(){
        return false;
    }

    @Override //升级设定
    public boolean isUpgradable() {
        return false;
    }

    @Override //物品价值
    public int value() {
        return 3000;
    }

}
