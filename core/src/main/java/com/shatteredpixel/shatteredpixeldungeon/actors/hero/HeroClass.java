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

package com.shatteredpixel.shatteredpixeldungeon.actors.hero;

import static com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass.BATTLEMAGE;
import static com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass.FREERUNNER;
import static com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass.GLADIATOR;
import static com.shatteredpixel.shatteredpixeldungeon.items.Generator.randomWeapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Challenges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.QuickSlot;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.ArmorAbility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.huntress.NaturesPower;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.huntress.SpiritHawk;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.huntress.SpectralBlades;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.mage.WildMagic;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.mage.WarpBeacon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.mage.ElementalBlast;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.rogue.DeathMark;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.rogue.ShadowClone;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.rogue.SmokeBomb;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.warrior.HeroicLeap;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.warrior.Shockwave;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.warrior.Endure;
import com.shatteredpixel.shatteredpixeldungeon.items.BrokenSeal;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.Torch;
import com.shatteredpixel.shatteredpixeldungeon.items.Waterskin;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.ClothArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.CloakOfShadows;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.PotionBandolier;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.ScrollHolder;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.TechTree;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.VelvetPouch;
import com.shatteredpixel.shatteredpixeldungeon.items.food.Food;
import com.shatteredpixel.shatteredpixeldungeon.items.Science.PotionLevel;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfHealing;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfInvisibility;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfLiquidFlame;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfMindVision;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfIdentify;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfLullaby;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfMagicMapping;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRage;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfUpgrade;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfEnchantment;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfMagicMissile;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.SpiritBow;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Dagger;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Sword;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.WarHammer;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Gloves;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MagesStaff;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.WornShortsword;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.ThrowingKnife;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.ThrowingStone;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.watabou.utils.DeviceCompat;

public enum HeroClass {

	WARRIOR(HeroSubClass.BERSERKER, HeroSubClass.GLADIATOR),
	MAGE(HeroSubClass.BATTLEMAGE, HeroSubClass.WARLOCK),
	ROGUE(HeroSubClass.ASSASSIN, HeroSubClass.FREERUNNER),
	HUNTRESS(HeroSubClass.SNIPER, HeroSubClass.WARDEN),
	STAR(HeroSubClass.SNIPER, HeroSubClass.WARDEN);

	private HeroSubClass[] subClasses;

	HeroClass(HeroSubClass... subClasses) {
		this.subClasses = subClasses;
	}

	public void initHero(Hero hero) {
		hero.heroClass = this;
		Talent.initClassTalents(hero);
		Dungeon.gold += 500; //初始金钱

		//初始物品2
		new PotionOfHealing().quantity(3).identify().collect();
		//火把
		new Torch().quantity(5).identify().collect();
		//口粮
		new Food().quantity(5).identify().collect();

		//new RingLevel().quantity(1).identify().collect();

		new Sword().quantity(1).identify().collect();

		new WarHammer().quantity(1).identify().collect();

		new PotionLevel().quantity(1).identify().collect();

		new TechTree().quantity(1).identify().collect();

		/*
		//定义一个随机武器
		Weapon randomWeapon;
		//改变武器的阶数
		randomWeapon = (Weapon) Generator.random(Generator.Category.WEP_T2);
		randomWeapon.quantity();
		//武器的初始等级
		randomWeapon.level(6);
		//是否诅咒和附魔
		randomWeapon.cursed=false;
		randomWeapon.enchantment=null;
		//给予武器
		randomWeapon.identify().collect();

		Weapon randomWeapon2;
		//修改这里的Category来改变武器的等级
		randomWeapon2 = (Weapon) Generator.random(Generator.Category.WEP_T2);
		randomWeapon2.quantity();
		randomWeapon2.level(-1);
		randomWeapon2.cursed=false;
		randomWeapon2.enchantment=null;
		//如果不想要鉴定，那就去掉.identify()
		randomWeapon2.identify().collect();
		randomWeapon2.identify().collect();

		 */

		//原初戒指
		//new RingOfNone().quantity(1).identify().collect();

		new ScrollOfUpgrade().quantity(10).identify().collect();

		//new RingOfElements().quantity(1).identify().collect();

		Item i = new ClothArmor().identify();
		if (!Challenges.isItemBlocked(i)) hero.belongings.armor = (ClothArmor) i;

		//检测到开启一个指定挑战后给予一个东西

		/*if (Dungeon.isChallenged(NO_FOOD)){

			i = new Chain();
			if (!Challenges.isItemBlocked(i)) i.collect();

		}*/

		Dungeon.LimitedDrops.VELVET_POUCH.drop();

		//这些物品是为了给快捷栏用的
		Waterskin waterskin = new Waterskin();
		waterskin.collect();

		//KingsCrown kingscrown = new KingsCrown();
		//kingscrown.collect();
		//初始鉴定真知符文
		new ScrollOfIdentify().identify();

		switch (this) {
			case WARRIOR:
				initWarrior(hero);
				//战士拥有额外的力量
				Dungeon.hero.STR++;
				break;

			case MAGE:
				initMage(hero);
				break;

			case ROGUE:
				initRogue(hero);
				break;

			case HUNTRESS:
				initHuntress(hero);
				break;

			case STAR:
				initStar(hero);
				break;

		}

		for (int s = 0; s < QuickSlot.SIZE; s++) {
			//快捷栏
			if (Dungeon.quickslot.getItem(s) == null) {
				Dungeon.quickslot.setSlot(s++, waterskin);
				//Dungeon.quickslot.setSlot(s++, kingscrown);
				break;
			}
		}

	}

	public Badges.Badge masteryBadge() {
		switch (this) {
			case WARRIOR:
				return Badges.Badge.MASTERY_WARRIOR;
			case MAGE:
				return Badges.Badge.MASTERY_MAGE;
			case ROGUE:
				return Badges.Badge.MASTERY_ROGUE;
			case HUNTRESS:
				return Badges.Badge.MASTERY_HUNTRESS;
			case STAR:
				return Badges.Badge.MASTERY_HUNTRESS;
		}
		return null;
	}


    //职业初始物品 战士
	private static void initWarrior(Hero hero) {
		(hero.belongings.weapon = new WornShortsword()).identify();
		ThrowingStone stones = new ThrowingStone();
		stones.quantity(3).collect();
		new PotionBandolier().quantity(1).collect();
		Dungeon.quickslot.setSlot(0, stones);

		//hero.subClass=GLADIATOR;




		if (hero.belongings.armor != null) {
			hero.belongings.armor.affixSeal(new BrokenSeal());
		}

		new PotionOfHealing().identify();
		new ScrollOfUpgrade().identify();
		new ScrollOfRage().identify();
	}
	//职业初始物品 法师
	private static void initMage(Hero hero) {
		MagesStaff staff;

		staff = new MagesStaff(new WandOfMagicMissile());
		new ScrollHolder().quantity(1).collect();

		(hero.belongings.weapon = staff).identify();
		hero.belongings.weapon.activate(hero);

		Dungeon.quickslot.setSlot(0, staff);

		new ScrollOfUpgrade().identify();
		new PotionOfLiquidFlame().identify();
		new ScrollOfEnchantment().identify();

		//法师职业
		hero.subClass=BATTLEMAGE;
	}
	//职业初始物品 盗贼
	private static void initRogue(Hero hero) {
		(hero.belongings.weapon = new Dagger()).identify();
		new VelvetPouch().quantity(1).collect();

		CloakOfShadows cloak = new CloakOfShadows();
		/*
		(hero.belongings.artifact1 = cloak).identify();
		hero.belongings.artifact1.activate(hero);

		 */

		ThrowingKnife knives = new ThrowingKnife();
		knives.quantity(3).collect();


		Dungeon.quickslot.setSlot(0, cloak);
		Dungeon.quickslot.setSlot(1, knives);

		new ScrollOfMagicMapping().identify();
		new ScrollOfUpgrade().identify();
		new PotionOfInvisibility().identify();

		hero.subClass=FREERUNNER;
	}
	//职业初始物品 女猎手
	private static void initHuntress(Hero hero) {

		(hero.belongings.weapon = new Gloves()).identify();
		SpiritBow bow = new SpiritBow();
		bow.identify().collect();
		new VelvetPouch().quantity(1).collect();

		Dungeon.quickslot.setSlot(0, bow);

		new PotionOfMindVision().identify();
		new ScrollOfUpgrade().identify();
		new ScrollOfLullaby().identify();

		//hero.subClass=SNIPER;
	}

	private static void initStar(Hero hero) {

		(hero.belongings.weapon = new Gloves()).identify();
		SpiritBow bow = new SpiritBow();
		bow.identify().collect();
		new VelvetPouch().quantity(1).collect();

		Dungeon.quickslot.setSlot(0, bow);

		new PotionOfMindVision().identify();
		new ScrollOfUpgrade().identify();
		new ScrollOfLullaby().identify();

		//hero.subClass=SNIPER;
	}


	public String title() {
		return Messages.get(HeroClass.class, name());
	}

	public String desc() {
		return Messages.get(HeroClass.class, name() + "_desc");
	}

	public HeroSubClass[] subClasses() {
		return subClasses;
	}


	//护甲特技
	public ArmorAbility[] armorAbilities() {
		switch (this) {
			case WARRIOR:
			default:
				return new ArmorAbility[]{new HeroicLeap(), new Shockwave(), new Endure()};
			case MAGE:
				return new ArmorAbility[]{new ElementalBlast(), new WildMagic(), new WarpBeacon()};
			case ROGUE:
				return new ArmorAbility[]{new SmokeBomb(), new DeathMark(), new ShadowClone()};
			case HUNTRESS:
				return new ArmorAbility[]{new SpectralBlades(), new NaturesPower(), new SpiritHawk()};
			case STAR:
				return new ArmorAbility[]{new SpectralBlades(), new NaturesPower(), new SpiritHawk()};
		}
	}

	public String spritesheet() {
		switch (this) {
			case WARRIOR:
			default:
				return Assets.Sprites.WARRIOR;
			case MAGE:
				return Assets.Sprites.MAGE;
			case ROGUE:
				return Assets.Sprites.ROGUE;
			case HUNTRESS:
				return Assets.Sprites.HUNTRESS;
			case STAR:
				return Assets.Sprites.HUNTRESS;
		}
	}

	public String splashArt() {
		switch (this) {
			case WARRIOR:
			default:
				return Assets.Splashes.WARRIOR;
			case MAGE:
				return Assets.Splashes.MAGE;
			case ROGUE:
				return Assets.Splashes.ROGUE;
			case HUNTRESS:
				return Assets.Splashes.HUNTRESS;
			case STAR:
				return Assets.Splashes.HUNTRESS;
		}
	}
	//职业介绍
	public String[] perks() {
		switch (this) {
			case WARRIOR:
			default:
				return new String[]{
						Messages.get(HeroClass.class, "warrior_perk1"),
						Messages.get(HeroClass.class, "warrior_perk2"),
						Messages.get(HeroClass.class, "warrior_perk3"),
						Messages.get(HeroClass.class, "warrior_perk4"),
						Messages.get(HeroClass.class, "warrior_perk5"),
				};
			case MAGE:
				return new String[]{
						Messages.get(HeroClass.class, "mage_perk1"),
						Messages.get(HeroClass.class, "mage_perk2"),
						Messages.get(HeroClass.class, "mage_perk3"),
						Messages.get(HeroClass.class, "mage_perk4"),
						Messages.get(HeroClass.class, "mage_perk5"),
				};
			case ROGUE:
				return new String[]{
						Messages.get(HeroClass.class, "rogue_perk1"),
						Messages.get(HeroClass.class, "rogue_perk2"),
						Messages.get(HeroClass.class, "rogue_perk3"),
						Messages.get(HeroClass.class, "rogue_perk4"),
						Messages.get(HeroClass.class, "rogue_perk5"),
				};
			case HUNTRESS:
				return new String[]{
						Messages.get(HeroClass.class, "huntress_perk1"),
						Messages.get(HeroClass.class, "huntress_perk2"),
						Messages.get(HeroClass.class, "huntress_perk3"),
						Messages.get(HeroClass.class, "huntress_perk4"),
						Messages.get(HeroClass.class, "huntress_perk5"),
				};
			case STAR:
				return new String[]{
						Messages.get(HeroClass.class, "huntress_perk1"),
						Messages.get(HeroClass.class, "huntress_perk2"),
						Messages.get(HeroClass.class, "huntress_perk3"),
						Messages.get(HeroClass.class, "huntress_perk4"),
						Messages.get(HeroClass.class, "huntress_perk5"),
				};
		}
	}

	public boolean isUnlocked() {
		//always unlock on debug builds
		if (DeviceCompat.isDebug()) return true;

		switch (this) {
			case WARRIOR:
			default:
				return true;
			case MAGE:
				return Badges.isUnlocked(Badges.Badge.UNLOCK_MAGE);
			case ROGUE:
				return Badges.isUnlocked(Badges.Badge.UNLOCK_ROGUE);
			case HUNTRESS:
				return Badges.isUnlocked(Badges.Badge.UNLOCK_HUNTRESS);
			case STAR:
				return Badges.isUnlocked(Badges.Badge.UNLOCK_HUNTRESS);
		}
	}

	public String unlockMsg() {
		switch (this) {
			/*
			case WARRIOR:
			default:
				return "";
			case MAGE:
				return Messages.get(HeroClass.class, "mage_unlock");
			case ROGUE:
				return Messages.get(HeroClass.class, "rogue_unlock");
			case HUNTRESS:
				return Messages.get(HeroClass.class, "huntress_unlock");
		}

			 */

			case WARRIOR:
			default:
				return "";
			case MAGE:
				return "";
			case ROGUE:
				return "";
			case HUNTRESS:
				return "";
		}
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////







}
