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

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.AlchemistsToolkit;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.ChangesScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.ui.Icons;
import com.shatteredpixel.shatteredpixeldungeon.ui.TalentIcon;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;
import com.watabou.noosa.Image;

import java.util.ArrayList;

public class Star_v0_1_X_Changes {

	public static void addAllChanges( ArrayList<ChangeInfo> changeInfos ){
		add_v0_0_0_Changes(changeInfos);
		add_v0_2_3_Changes(changeInfos);
		add_v0_1_1_Changes(changeInfos);
	}

	public static void add_v0_0_0_Changes( ArrayList<ChangeInfo> changeInfos ){

		ChangeInfo changes = new ChangeInfo("即将到来&鸣谢", true, "");
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

		changes.addButton( new ChangeButton( new ItemSprite(ItemSpriteSheet.MASTERY, null),  "鸣谢",
				"感谢ColdMint所制作的新错误界面\n" +
						"_-_ 感谢Ling、坚果、沐沐、泰勒的代码教学、支持和答疑解惑\n" +
						"_-_ 感谢_长末_提供的实例化调用解决方案\n"+
						"_-_ 感谢各位游玩者对本游戏的支持"));
		changes.addButton( new ChangeButton( new ItemSprite(ItemSpriteSheet.ARTIFACT_TOOLKIT, null),  "生物灵魂",
				"_-_ 玩家可以在之后合成多个生物来进行战斗\n" +
						"_-_ 由于生物面板更改，因此生物会随着你的深入和辐射强度的深入而逐渐强化\n"+
						"_-_ 一些特殊生物（例如黏咕）则需要在击杀他后才能获得，当然其他特殊生物也一样\n"+
						"_-_ 特殊生物的灵魂徽记不会随炼金消耗掉"));
		changes.addButton(new ChangeButton(new Image(Assets.Sprites.BUST, 0, 0, 16, 16),"地形交互",
				"_-_ 0层的元素提取井，以及更多可交互性NPC将会加入\n" +
						"_-_ 一般而言，与其交互的方式类似于以物易物\n"+
						"_-_ 这些NPC通常会在一个特定房间内出现\n"+
						"_-_ 例如：一区会存在一个储水阀门，打爆既可以获得一部分水源"));
		changes.addButton( new ChangeButton(Icons.get(Icons.DEPTH), "子层",
				"_-_ 目前已经实现了简陋的子层设定\n" +
						"_-_ 玩家可以通过子层前往别的区域，不过楼层数会发生变化\n"+
						"_-_ 为了不影响和正常楼层的设定，因此子层被设置在100层起始或更深的地方"));

	}

	public static void add_v0_2_3_Changes( ArrayList<ChangeInfo> changeInfos ){

		ChangeInfo changes = new ChangeInfo("v0.2-bug", true, "");
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

		changes = new ChangeInfo(Messages.get(ChangesScene.class, "new"), false, null);
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

		changes.addButton(new ChangeButton(new Image(Assets.Sprites.CUBE, 0, 0, 16, 16), "生物",
				"_-_ 加入了新的生物“碎晶元素”，相较于普通元素而言没有远程能力，且会掉落碎晶等合成素材\n" +
						"_-_ 二区现在会生成黏咕，但最多生成两个且同时最多存在一个\n"+
						"_-_ 二区现在会生成DM-100了，不过概率较小\n"+
						"_-_ 三区加入了信徒组合，拥有三种完全不同的信徒\n"+
						"_-_ 加入了元素提取井作为npc，可以在一层见到"));

		changes.addButton( new ChangeButton(Icons.get(Icons.DEPTH), "楼层改动",
				"_-_ 二区现在采用了超大的新地形，且初始玩法得到了改动\n" +
						"_-_ 补给箱在二层将会给予探地卷轴了\n"+
						"_-_ 部分区域地形设置更改"));

		changes.addButton( new ChangeButton( new ItemSprite(ItemSpriteSheet.SKULL, null),  "灾荒",
				"_-_ 灾荒现在会影响2区域的生物，感染了灾荒的生物在死后会扩散感染范围\n" +
						"_-_ 感染范围现在会呈现红色，玩家踩上去将会有提示消息"));

		changes.addButton( new ChangeButton( new ItemSprite(ItemSpriteSheet.EXOTIC_NONE, null),  "新的炼金配方",
				"_-_ 星尘附加需要用到多种消耗品和星尘，造价较贵\n" +
						"_-_ 星尘附加的产出物通常十分强大，和他们的造价相对应\n" +
						"_-_ 科技树现在可以被合成了，当然目前仅限药水科技树"));

		changes.addButton(new ChangeButton(new Image(Assets.Sprites.GNOLL, 0, 0, 12, 15), "豺狼商店",
				"_-_ 豺狼商店现在有10%概率声称在任意常规楼层\n" +
						"_-_ 小店豺狼不会收购物品，他们只会卖出物品\n"+
						"_-_ 拥有和商店老板相同的逃跑逻辑\n"+
						"_-_ 最多售卖总计三瓶药水，一个蜂蜜罐，一个炸弹，一个飞镖，一个火把（10层以后）\n"+
						""));

		changes = new ChangeInfo(Messages.get(ChangesScene.class, "changes"), false, null);
		changes.hardlight(CharSprite.WARNING);
		changeInfos.add(changes);

		changes.addButton( new ChangeButton(Icons.get(Icons.BUFFS), "效果改动",
				"_-_ 血腥狂热现在会给予2x的移动速度加成\n" +
						"_-_ 血腥狂热的出血量现在不会随buff效果绑定，而是和攻击效果绑定\n"+
						"_-_ 子层基本逻辑已经确定，不过暂时没有实装"));

		changes.addButton( new ChangeButton( new ItemSprite(ItemSpriteSheet.ARTIFACT_TOOLKIT, null),  "炼金配方改动",
				"_-_ 多数新炼金的能量要求减少，这使得他们更容易被制造\n" +
						"_-_ 消耗品转换现在将同类碎晶/星尘的可转化物归类在了一起\n" +
						"_-_ 修改了部分炼金书页的描述"
		));

		changes.addButton( new ChangeButton( new ItemSprite(ItemSpriteSheet.BANSHOU, null),  "杂项修正",
				"_-_ 修复了商店进入会报错的问题，优化了商店物品数量\n" +
						"_-_ 生物的素材现在可以堆叠了（例如甲壳）\n" +
						"_-_ 极速的速度加成由错误的20000x修正为2x\n"+
						"_-_ 修复了生物在创建时因为血量为0而自动死亡的Bug"
		));

		changes.addButton( new ChangeButton(new TalentIcon(Talent.QUIET_ELEMENT.icon()), "元素浓度修正",
				"_燃烧_\n_-_ 燃烧持续时间由0-16T调整至5-11T，基础为8和破碎一致\n"+
				"_-_ 燃烧持续时间由4回合降低到了0-3回合，基础和破碎一致\n"+
				"_-_ 星辰的火焰适应性现在最后计算并生效\n"+
				"_-_ 修复了生物不会受到火焰伤害的bug\n\n"+
				"_冰霜_\n_-_ 冰霜持续时间由10-20回合降低至5-15回合，基础为10\n"+
				"_-_ 星辰的冰霜适应性现在对冰霜速度计算整体生效\n"+
				"_-_ 现在元素浓度不会主动下降，必须需要学习天赋才可以下降\n"+
				"_-_ 元素浓度下降所需的回合减少25回合，现在变为50/25回合缩减一次\n"+
				"_-_ 元素平衡量由每一定回合回复1%提升到每一定回合回复2%"
		));

		changes.addButton(new ChangeButton(new Image(Assets.Sprites.SPINNER, 144, 0, 16, 16), Messages.get(ChangesScene.class, "bugfixes"),

				"_-_ 修复了一些奇奇怪怪的bug\n" +
						""));

		changes = new ChangeInfo(Messages.get(ChangesScene.class, "buffs"), false, null);
		changes.hardlight(CharSprite.POSITIVE);
		changeInfos.add(changes);

		changes = new ChangeInfo(Messages.get(ChangesScene.class, "nerfs"), false, null);
		changes.hardlight(CharSprite.NEGATIVE);
		changeInfos.add(changes);

		changes.addButton( new ChangeButton(Icons.get(Icons.BUFFS), "效果削弱",
				"_-_ 时间气泡现在时间剩余0回合会消失了\n" +
						"_-_ 血腥狂热现在只会提供30%固定攻击速度加成\n" +
				"_-_ 血腥狂热现在攻击速度增加由300%下调至30%\n"
				));

		changes.addButton( new ChangeButton( new ItemSprite(ItemSpriteSheet.BACKPACK, null),  "玩家削弱",
				"_-_ 移除了初始状态栏介绍，移除了星辰的特殊技能\n" +
						"_-_ 法杖的初始能量统一为2，即使该法杖初始携带等级\n"+
						"_-_ 初始的元素碎晶和药水科技树被移除"));
	}

//-----------------------------------------以完成日志-------------------------------------------------
	
	public static void add_v0_1_1_Changes( ArrayList<ChangeInfo> changeInfos ){

		ChangeInfo changes = new ChangeInfo("v0.2", true, "");
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

		changes = new ChangeInfo(Messages.get(ChangesScene.class, "new"), false, null);
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

		changes.addButton( new ChangeButton( new ItemSprite(ItemSpriteSheet.GRASS_GEM, null), "星尘/碎晶",
				"_-_ 星辰和碎晶作为独特的物品被加入到地牢里\n" +
						"_-_ 目前，星尘和碎晶可以用来制造独特的消耗品\n"+
						"_-_ 他们也可以用来参与炼金武器或者特殊物品的合成\n"+
						"_-_ 有一定概率自然生成，但多数会在_一些怪物死亡后_掉落\n"+
						"_-_ 你也可以直接使用他们来快速获得一些常规的消耗品\n"+
						"_-_ 可以负责科技树的升级\n"+
						""));
		changes.addButton( new ChangeButton(new TalentIcon(Talent.QUIET_ELEMENT.icon()), "霜焰元素",
				"_-_ 地牢中的两种基础元素，本身并无什么特别之处\n" +
						"_-_ 然而，一旦处理不好，将会对玩家探索造成巨大阻碍\n"+
						"_-_ 任何的燃烧/冷冻操作都会使得对应元素的数值提高，你可以_通过查看左上角的青蓝色状态条（通常是一半）_来粗略估算其元素浓度\n"+
						"_-_ 焰元素将会影响火焰的伤害能力，而霜元素则会影响减缓速度和持续时间\n"+
						"_-_ 显而易见的是，当你提升其中一种元素的时候，另一种元素也会下降，二者此消彼长\n"+
						"_-_ 你也可以通过天赋来调节元素浓度，_它通常在第二层天赋内_\n"+
						""));
		changes.addButton( new ChangeButton( new ItemSprite(ItemSpriteSheet.BLEED_TOOTH, null), "炼金武器",
				"_-_ 炼金武器作为可以被合成的武器被加入到了地牢中\n" +
						"_-_ 炼金武器通常拥有攻击造成效果的能力，也有部分是手持时给予玩家不同的效果\n"+
						"_-_ 炼金武器输出即鉴定，你无需使用鉴定卷轴即可得知他们的一切信息\n"+
						"_-_ 炼金武器可以被转换成同阶的普通武器，然而你无法通过嬗变来转换出任何的炼金武器\n"+
						"_-_ 多数炼金武器的输出是要偏低的，因为他们的效果可以很好的弥补他们自身的伤害不足\n"+
						"_-_ 炼金武器和其他武器一样，转换成炼金结晶的数量是一样的\n"+
						""));
		changes.addButton(new ChangeButton(new Image(Assets.Sprites.BUST, 0, 0, 16, 16),"雕像效果",
				"_-_ 零层现在加入了雕像，玩家可以通过开启雕像来体验一场别样的旅途\n" +
						"_-_ 多数雕像开启后其实是更加危险的，例如杀戮\n"+
						"_-_ 部分雕像也会改变一些现有的玩法，不过他们仍在开发中\n"+
						"_-_ 雕像存在成就检测，一般会检测和雕像效果相同的成就来进行判断\n"+
						"_-_ 雕像有不同颜色的粒子以方便区分\n"+
						"_-_ 使用你的放大镜来查看雕像，通过点击来选择雕像，不过雕像只能选择一次\n"+
						""));
		changes.addButton( new ChangeButton(new TalentIcon(Talent.STAR_UPDATE.icon()), "辐射",
				"_-_ 辐射，顾名思义，由最深处向地牢外散发着源源不断地魔法能量\n" +
						"_-_ 多数生物都可以被辐射所强化，部分生物在辐射的作用下甚至会产生突变（精英强敌）\n"+
						"_-_ 通常情况下，你在杀死boss/使用特定物品后都会使得辐射的强度提升\n"+
						"_-_ 不过很庆幸，辐射并没有那么致命，它更多地只是对地牢内的生物产生影响\n"+
						"_-_ 因此，辐射也会变相的提高你的生存难度，除了某位地牢原住民？\n"+
						"_-_ 星辰作为地牢原住民也同样能接收到来自辐射的强化能力，它通常在星辰的第二层天赋内\n"+
						""));
		changes.addButton( new ChangeButton( new ItemSprite(ItemSpriteSheet.TWO_SWORD, null),  "架势条",
				"_-_ 架势条一般只会在未命中且静止的时候才会积攒，因此对于到处跑的玩家收益极低\n" +
						"_-_ 架势条越高，你造成的伤害也会越高，最高可以造成1.5倍的基础伤害\n"+
						"_-_ 在造成伤害或者移动后，架势条将被清空，不过，星辰的天赋可以使得它在行动后仍然保持架势\n"+
						"_-_ 战士可以通过自身的天赋来加强架势条的收益，他通常在第二层天赋中，星辰的天赋则在第一层天赋中\n"+
						"_-_ 架势条也可以提高法术伤害或者远程伤害，只要他们的伤害来源是玩家角色\n"+
						"_-_ 游戏内的每回合增长的青色条即为玩家的架势条\n"+
						""));
		changes.addButton( new ChangeButton( new ItemSprite(ItemSpriteSheet.POTION_HOLDER, null),  "科技树",				"\n" +
						"_-_ 科技树可以显著提高消耗性物品的效果，当等级达到一定程度后会有全新的效果\n"+
						"_-_ 药水科技树可以使得玩家在饮用药水后获取到不同的能力\n"+
						"_-_ 符文科技树则会在玩家使用符文后获得足量的法术充能效果\n"+
						"_-_ 食物科技树则可以在单次进食内提供给玩家更多的饱腹度，可以更多地调整续航\n"+
						"_-_ 更多的科技树仍在制作中……\n"+
						""));
		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.SANDBAG, null), "生物遗物&素材",
				"_-_ 遗物和素材一样都又生物掉落，不过前者比后者更频繁地出现\n" +
						"_-_ 遗物可以等价的视为金币，你可以收集生物的遗物来卖钱，不过这通常要等到商店所在层\n"+
						"_-_ 不同的生物掉落的遗物数量也不尽相同，生物价值越高/楼层越深，则掉落数量也会越多\n"+
						"_-_ 生物遗物的掉落是有上限的，因为一种生物身上独特的东西是有限的\n"+
						"_-_ 素材则是生物掉下来的自身物品，相较于遗物，素材的保存更加完整\n"+
						"_-_ 你可以在前期获取到沙虫的牙齿作为炼金素材，可以通过打开合成表来查看他能合成的物品\n"+
						""));
		changes = new ChangeInfo(Messages.get(ChangesScene.class, "changes"), false, null);
		changes.hardlight(CharSprite.WARNING);
		changeInfos.add(changes);

		changes.addButton( new ChangeButton(Icons.get(Icons.DEPTH), "楼层改动",
				"_-_ 商店楼层现在会一次生成两个商店了，两个商店会分别售卖不同的物品\n" +
						"_-_ 现在，每层的开始位置都会生成一个补给箱，补给箱的物品是随机抽取的，同时也和玩家所在区域有关\n"+
						"_-_多数区域现在采用全新的布局逻辑，例如，沙地的楼层整体缺水且均为枯草"));

		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.NORMALFOOD, null), "生成改动",
				"_-_ 每层现在将会生成一个口粮和两个小包口粮"));

		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.HOLDER, null), "生成改动",
				"_-_ 现在，强化符文和力量药水的生成不会再选取零层作为生成点了\n" +
						"_-_ 碎晶现在有一定概率自然生成，但星尘不会自然生成\n" +
						"_-_ 移除了部分武器的生成权重，现在强力武器的出现概率变得更小了\n"+
						"_-_ 装备生成得到了些许调整"));

		changes.addButton( new ChangeButton(Icons.get(Icons.BUFFS), "升级生成",
				"_-_ 物品在+5后不能继续升级)\n" +
						"_-_ 投掷武器在第一次升级的时候会错误地变成+2，不过数据不变\n"+
						"_-_ 武器/投掷武器升级会获得附魔，不过存在附魔时无法覆盖"));

		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.RING_DIAMOND, null), "戒指调整",
				"_-_ 暂时移除了财富戒指\n" +
						"_-_ 部分戒指的升级收益得到了调整"));
		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.ARMOR_LEATHER, null), "武器/护甲",
				"_-_ 护甲使用了随机数值，现在即使是相同护甲的防护能力也不尽相同\n" +
						"_-_ 护甲和武器力量降低的要求等级略微提高，整体提高一级\n"+
						"_-_ 武器也同样拥有护甲的随机数值，不过相较于护甲的随机数值，武器的随机数据更加倾向于其中间值\n"+
						"_-_ 增加了大量全新的武器，现在斧类/锤类/盾类将作为三个武器族系出现\n"+
						"_-_ 增加了一件新的护甲，你可以在游玩星辰时体验这件护甲\n"+
						""));
		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.SWORD, null), "生物数据",
				"_-_ 采用了全新的膨胀系数，确保生物的属性始终相较于原版没有强的过分\n" +
						"_-_ 多数生物现在在一层的数值多为8-15点生命值，不过也有更为极端的情况出现\n"+
						"_-_ 修改了生物的赋值逻辑，现在生物的面板更加不均等且随机\n"+
						"_-_ 移除生物的随机移动速度,生物的随机攻速调整至0.75x-1.20x\n"+
						"_-_ 新的生物词条出现，修改生物的睡眠率（现在更多生物时开局醒来的了）\n"+
						""));
		changes.addButton(new ChangeButton(new Image(Assets.Sprites.SPINNER, 144, 0, 16, 16), Messages.get(ChangesScene.class, "bugfixes"),
				"_-_ 修复了一些奇奇怪怪的bug\n" +
						""));
		changes = new ChangeInfo(Messages.get(ChangesScene.class, "buffs"), false, null);
		changes.hardlight(CharSprite.POSITIVE);
		changeInfos.add(changes);
		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.WAR_HAMMER, null), "武器",
				"_-_ 战锤、硬头锤的伤害提高，不过攻速略微降低\n" +
						"_-_ 短棍、铁头棍的防御能力加强\n"+
						"_-_ 双钗的伤害提高，双手剑的攻击由一次造成两次伤害改为和双钗等价的攻速\n"+
						"_-_ 暗杀之刃、长匕首的伏击伤害提高\n"+
						"_-_ 多数武器现在有概率造成多点的伤害\n"+
						""));
		changes.addButton( new ChangeButton(Icons.get(Icons.BUFFS), "职业加强",
				"_-_ 角色现在拥有了更多的一阶和二阶天赋天赋\n" +
						"_-_ 角色现在拥有了部分转职的能力，且得到了不同程度的加强\n"+
						"_-_ 角色的血量得到了提高，同时每级的成长也略微提高\n"+
						"_-_ 多数职业现在在自己擅长的伤害方面更加强大\n"+
						"_-_ 职业的二阶天赋现在包含职业强化，将显著提高角色的输出能力\n"+
						""));
		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.POTION_AZURE, null), "药水/气体/消耗品",
						"_-_ 火焰产生后的存在时间由一回合变为5回合\n" +
						"_-_ 毒气药水的毒气存在时间延长20%\n"+
						"_-_ 现在阅读符文会清楚使用者的失明状态了\n"+
						""));
		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.GOLD, null), "掉落相关",
				"_-_ 掉落物现在会更加频繁的生成，数量更多\n" +
						"_-_ 背包的空间扩大，拥有了更多的格子和更大的显示效果\n"+
						"\n"+
						""));
		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.WAND_CORRUPTION, null), "法杖",
				"_-_ 法杖充能上限由10点调整为12点\n" +
						"_-_ 法杖的充能速度加快一个回合\n"+
						"\n"+
						""));
		changes = new ChangeInfo(Messages.get(ChangesScene.class, "nerfs"), false, null);
		changes.hardlight(CharSprite.NEGATIVE);
		changeInfos.add(changes);
		changes.addButton( new ChangeButton(new TalentIcon(Talent.WARRIOR_EAT1.icon()), "续航削弱",
				"_-_ 自然回复速度由5点/10回合降低为2点/10回合\n" +
						"_-_ 治疗药水现在回复量将变得十分缓慢，但会给予护盾作为弥补\n"+
						"_-_ 特殊房间的数量减少，出现无意义的跳楼房间\n"+
						"_-_ 蓄血圣杯，丰饶之角的回复速度和恢复效果略微降低\n"+
						"_-_ 露珠提供的治疗效果减少1，露珠ping容量下降\n"+
						""));
	}
	
}