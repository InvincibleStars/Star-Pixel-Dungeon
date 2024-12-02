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
		
		ChangeInfo changes = new ChangeInfo("改动", false, "");
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);
		
		changes.addButton( new ChangeButton(Icons.get(Icons.DEPTH), "作者",
				"总而言之，这个地牢还是开始更新了，尽管玩法可能会与之前大相径庭\n" +
				"我会尽量在我的能力范围内做一些改动，尽管这可能会导致与常规地牢的玩法大相径庭\n"+
				"地牢数据可能会极端不均衡，非常抱歉\n"+
				"PS:所有的更新内容基本都会写在这一页，越往下的代表越新的内容\n"+
				""));

		changes.addButton( new ChangeButton(Icons.get(Icons.DEPTH), "楼层",
				"_-_ 1层现在有商店了\n" +
				"_-_ 每层的开始位置会生成一个补给箱"+
				"_-_补给箱的物品会随楼层产生变化(一般是每5层)"));
		
		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.GOLD, null), "生成",
				"_-_ 强化符文的生成调整\n" +
				"_-_ 武器和护甲升级时的力量减少公式进行了调整\n" +
				"_-_ 玩家和生物的攻击能力调整至固定值\n"));

		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.POTION_HOLDER, null), "药水",
				"_-_ 急救制剂使用会获得最大生命的魔法护盾，同时缓慢回复生命力\n" +
						"_-_ 火焰存在时间变为5回合\n" +
						"_-_ 毒气存在时间延长50%\n"));

		changes.addButton( new ChangeButton(Icons.get(Icons.BUFFS), "升级",
				"_-_ 物品在+5后不能继续升级)\n" +
						"_-_ 投掷武器在第一次升级的时候会错误地变成+2，不过数据不变\n"+
						"_-_ 武器/投掷武器升级会获得附魔，不过存在附魔时无法覆盖"));

		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.RING_AGATE, null), "戒指改动",
				"_-_ 暂时移除了财富戒指\n" +
						"_-_ 戒指的升级收益得到了调整"));

	}
	
	public static void add_v0_1_0_Changes( ArrayList<ChangeInfo> changeInfos ){
		
		ChangeInfo changes = new ChangeInfo("新内容", false, "");
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.RING_NONE, null), "戒指",
				"_-_ 加入了可以嬗变成其他戒指的“原初戒指”，角色初始携带\n" +
						"_-_ 戒指的升级收益得到了调整"));

		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.RING_NONE, null), "原初戒指",
				"_-_ 初始携带的物品，可以通过嬗变转换成其他戒指"));

		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.GRASS_DUST, null), "结晶力量",
				"_-_ 具有独特用途和合成素材的结晶将加入游戏\n"));

		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.GRASS_DUST, null), "星尘系统",
				"_-_ 星尘是一种蕴含了某种特定能量的粉尘\n" +
						"星尘通常具有强大的效用性，尽管你并不能直观地感受到"));

		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.BLACK_GEM, null), "枪械/子弹",
				"_-_ 枪械作为地牢中强大的远程武器，本身需要子弹来装填，你可以在投掷武器房间内找到子弹，也可以在商店购买弹药"));

		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.WHITE_GEM, null), "强化晶体",
				"_-_ 现在一些武器可以被拆解成为强化晶体了"));

		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.WEAPON_HOLDER, null), "新武器",
				"_-_ 更多的新武器\n"+
						"_-_ 更多的投掷武器"));

		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.POTION_HOLDER, null), "科技树",
				"_-_ 从现在开始，各项可消耗品均可以被指定的科技树所升级\n"+
						"_-_ 升级通常情况下可以显著提高其消耗品的效用，但前提是你肯投入足量的升级"));

	}
	
}