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

package com.shatteredpixel.shatteredpixeldungeon.ui.changelist;

import com.shatteredpixel.shatteredpixeldungeon.items.Ankh;
import com.shatteredpixel.shatteredpixeldungeon.items.food.Blandfruit;
import com.shatteredpixel.shatteredpixeldungeon.services.updates.Updates;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.ui.Icons;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;

import java.util.ArrayList;

public class Star_v0_1_X_Changes {
	
	public static void addAllChanges( ArrayList<ChangeInfo> changeInfos ){
		
		ChangeInfo changes = new ChangeInfo( "Star v0.1.2", true, "");
		changes.hardlight( Window.TITLE_COLOR);
		changeInfos.add(changes);
		
		add_v0_1_1_Changes(changeInfos);
		add_v0_1_0_Changes(changeInfos);
	}
	
	public static void add_v0_1_1_Changes( ArrayList<ChangeInfo> changeInfos ){
		
		ChangeInfo changes = new ChangeInfo("数据调整", false, "");
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);
		
		changes.addButton( new ChangeButton(Icons.get(Icons.STAR), "前言",
				"总而言之，这个地牢还是开始更新了，尽管玩法可能会与之前大相径庭\n" +
				"总而言之，祝游玩愉快"));

		changes.addButton( new ChangeButton(Icons.get(Icons.DEPTH), "楼层调整",
			"_-_ 移除了BOSS层，原BOSS层被替换为了普通房间\n" +
				"_-_ 在每层的开始位置都会生成一个补给箱，打开后可以获得随机药水/卷轴\n" +
				"(打开补给箱不消耗回合数)\n"+
				"_-_ 商店现在会在1/6/11/16/21层生成，同时商人将会售卖更多的物品，但不会再销售武器以及护甲"));
		
		changes.addButton( new ChangeButton(Icons.get(Icons.BUFFS), "数据调整",
				"商品价格下降，但同时玩家获得的金币也会相对应的变少\n" +
				"_-_ 固定生成调整，从此以后玩家每层只会获得一个强化符文以及2瓶力量药水\n" +
				"_-_ 玩家的初始力量调整为0，同时武器和护甲的力量需求也与英雄的初始力量进行的同步\n" +
				"_-_ 从现在开始，强化符文每次升级可以直接降低2点装备的力量需求，同时将更加显著的提高武器的伤害，护甲的防护能力以及戒指和法杖的强度\n" +
				"_-_ 玩家和生物的攻防能力均调整为10点，除此以外，若没有额外说明，生物和玩家的攻防能力将不再额外更改\n" +
				"_-_ 提高了伏击造成额外伤害的武器的伏击加成，现在使用这类武器进行伏击的伤害将会提升的更加明显\n" +
				"_-_ 露珠瓶现在只需要10点露珠即可恢复全部的生命值，不过装满后再次捡取露珠其治疗量会变为原本的1/2"));
		
		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.TOMAHAWK, null), "投掷武器调整",
				"_-_ 投掷武器现在也会带有等级和附魔效果，不过不会携带诅咒\n" +
				"_-_ 效果类投掷武器的效果时常将会提高，投掷锤和原力方石现在命中敌人后会产生晕眩\n" +
				"_-_ 涂药飞镖现在拥有3次使用次数，同时基于玩家的力量属性造成伤害"));
	}
	
	public static void add_v0_1_0_Changes( ArrayList<ChangeInfo> changeInfos ){
		
		ChangeInfo changes = new ChangeInfo("数据调整", false, "");
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.POTION_AMBER, null), "治疗药水",
				"_-_ 急救制剂在使用后的第一回合回复量由总回复量的25%降低为总回复量的15%，并增加2点回复量\n" +
				"_-_ 急救制剂现在会在使用者使用时给予一个等同于玩家最大生命的魔法护盾"));
		
		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.SEED_EARTHROOT, null), "Seed Changes",
				"_-_ Blindweed buffed, now cripples as well as blinds.\n\n" +
				"_-_ Sungrass nerfed, heal scales up over time, total heal reduced by combat.\n\n" +
				"_-_ Earthroot nerfed, damage absorb down to 50% from 100%, total shield unchanged.\n\n" +
				"_-_ Icecap buffed, freeze effect is now much stronger in water."));
		
		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.POTION_SILVER, null), "Potion Changes",
				"_-_ Potion of Purity buffed, immunity duration increased to 10 turns from 5, clear effect radius increased.\n\n" +
				"_-_ Potion of Frost buffed, freeze effect is now much stronger in water."));
		
		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.SCROLL_BERKANAN, null), "Scroll Changes",
				"_-_ Scroll of Psionic blast reworked, now rarer and much stronger, but deals damage to the hero.\n\n" +
				"_-_ Scroll of Challenge renamed to Scroll of Rage, now amoks nearby enemies."));
		
	}
	
}
