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

package com.shatteredpixel.shatteredpixeldungeon.sprites;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.watabou.noosa.TextureFilm;

public class ItemSpriteSheet {

	private static final int WIDTH = 32;
	public static final int SIZE = 16;

	public static TextureFilm film = new TextureFilm(Assets.Sprites.ITEMS, SIZE, SIZE);

	private static int xy(int x, int y) {
		x -= 1;
		y -= 1;
		return x + WIDTH * y;
	}

	private static void assignItemRect(int item, int width, int height) {
		int x = (item % WIDTH) * SIZE;
		int y = (item / WIDTH) * SIZE;
		film.add(item, x, y, x + width, y + height);
	}
	//-------------------------------------------------------------------------------------
//占位符/科技树图标
	private static final int PLACEHOLDERS = xy(1, 1);
	public static final int SOMETHING = PLACEHOLDERS + 0;
	public static final int WEAPON_HOLDER = PLACEHOLDERS + 1;
	public static final int ARMOR_HOLDER = PLACEHOLDERS + 2;
	public static final int MISSILE_HOLDER = PLACEHOLDERS + 3;
	public static final int WAND_HOLDER = PLACEHOLDERS + 4;
	public static final int RING_HOLDER = PLACEHOLDERS + 5;
	public static final int ARTIFACT_HOLDER = PLACEHOLDERS + 6;
	public static final int FOOD_HOLDER = PLACEHOLDERS + 7;
	public static final int BOMB_HOLDER = PLACEHOLDERS + 8;
	public static final int POTION_HOLDER = PLACEHOLDERS + 9;
	public static final int SCROLL_HOLDER = PLACEHOLDERS + 11;
	public static final int SEED_HOLDER = PLACEHOLDERS + 10;
	public static final int STONE_HOLDER = PLACEHOLDERS + 12;
	public static final int CATA_HOLDER = PLACEHOLDERS + 13;
	public static final int ELIXIR_HOLDER = PLACEHOLDERS + 14;
	public static final int SPELL_HOLDER = PLACEHOLDERS + 15;
	public static final int CRYSTAL_HOLDER = PLACEHOLDERS + 16;
	public static final int DUST_HOLDER = PLACEHOLDERS + 17;
	public static final int OTHER_HOLDER = PLACEHOLDERS + 18;

	static {
		assignItemRect(SOMETHING, 8, 13);
		assignItemRect(WEAPON_HOLDER, 14, 14);
		assignItemRect(ARMOR_HOLDER, 14, 12);
		assignItemRect(MISSILE_HOLDER, 15, 15);
		assignItemRect(WAND_HOLDER, 14, 14);
		assignItemRect(RING_HOLDER, 8, 10);
		assignItemRect(ARTIFACT_HOLDER, 15, 15);
		assignItemRect(FOOD_HOLDER, 15, 11);
		assignItemRect(BOMB_HOLDER, 10, 13);
		assignItemRect(POTION_HOLDER, 11, 11);
		assignItemRect(SEED_HOLDER, 10, 10);
		assignItemRect(SCROLL_HOLDER, 7, 11);
		assignItemRect(STONE_HOLDER, 14, 12);
		assignItemRect(CATA_HOLDER, 6, 15);
		assignItemRect(ELIXIR_HOLDER, 12, 14);
		assignItemRect(SPELL_HOLDER, 8, 16);
		assignItemRect(CRYSTAL_HOLDER, 11, 11);
		assignItemRect(DUST_HOLDER, 9, 9);
		assignItemRect(OTHER_HOLDER, 13, 13);
	}
	//-------------------------------------------------------------------------------------
	private static final int UNCOLLECTIBLE = xy(1, 3);
	public static final int GOLD = UNCOLLECTIBLE + 0;
	public static final int ENERGY = UNCOLLECTIBLE + 1;

	public static final int DEWDROP = UNCOLLECTIBLE + 3;
	public static final int PETAL = UNCOLLECTIBLE + 4;
	public static final int SANDBAG = UNCOLLECTIBLE + 5;
	public static final int SPIRIT_ARROW = UNCOLLECTIBLE + 6;

	public static final int GUIDE_PAGE = UNCOLLECTIBLE + 8;
	public static final int ALCH_PAGE = UNCOLLECTIBLE + 9;

	public static final int TENGU_BOMB = UNCOLLECTIBLE + 11;
	public static final int TENGU_SHOCKER = UNCOLLECTIBLE + 12;

	static {
		assignItemRect(GOLD, 15, 13);
		assignItemRect(ENERGY, 16, 16);

		assignItemRect(DEWDROP, 16, 16);
		assignItemRect(PETAL, 8, 8);
		assignItemRect(SANDBAG, 10, 10);
		assignItemRect(SPIRIT_ARROW, 11, 11);

		assignItemRect(GUIDE_PAGE, 10, 11);
		assignItemRect(ALCH_PAGE, 10, 11);

		assignItemRect(TENGU_BOMB, 10, 10);
		assignItemRect(TENGU_SHOCKER, 10, 10);
	}
	//-------------------------------------------------------------------------------------
	private static final int CONTAINERS = xy(1, 4);   //16 slots
	public static final int BONES = CONTAINERS + 0;
	public static final int REMAINS = CONTAINERS + 1;
	public static final int TOMB = CONTAINERS + 2;
	public static final int GRAVE = CONTAINERS + 3;
	public static final int CHEST = CONTAINERS + 4;
	public static final int LOCKED_CHEST = CONTAINERS + 5;
	public static final int CRYSTAL_CHEST = CONTAINERS + 6;
	public static final int EBONY_CHEST = CONTAINERS + 7;

	static {
		assignItemRect(BONES, 14, 11);
		assignItemRect(REMAINS, 14, 11);
		assignItemRect(TOMB, 14, 15);
		assignItemRect(GRAVE, 14, 15);
		assignItemRect(CHEST, 16, 14);
		assignItemRect(LOCKED_CHEST, 16, 14);
		assignItemRect(CRYSTAL_CHEST, 16, 14);
		assignItemRect(EBONY_CHEST, 16, 14);
	}
	//-------------------------------------------------------------------------------------
	private static final int MISC_CONSUMABLE = xy(1, 5);   //16 slots
	public static final int ANKH = MISC_CONSUMABLE + 0;
	public static final int STYLUS = MISC_CONSUMABLE + 1;
	public static final int SEAL = MISC_CONSUMABLE + 2;
	public static final int TORCH = MISC_CONSUMABLE + 3;
	public static final int BEACON = MISC_CONSUMABLE + 4;
	public static final int HONEYPOT = MISC_CONSUMABLE + 5;
	public static final int SHATTPOT = MISC_CONSUMABLE + 6;
	public static final int IRON_KEY = MISC_CONSUMABLE + 7;
	public static final int GOLDEN_KEY = MISC_CONSUMABLE + 8;
	public static final int CRYSTAL_KEY = MISC_CONSUMABLE + 9;
	public static final int SKELETON_KEY = MISC_CONSUMABLE + 10;
	public static final int MASK = MISC_CONSUMABLE + 11;
	public static final int CROWN = MISC_CONSUMABLE + 12;
	public static final int AMULET = MISC_CONSUMABLE + 13;
	public static final int MASTERY = MISC_CONSUMABLE + 14;
	public static final int KIT = MISC_CONSUMABLE + 15;

	static {
		assignItemRect(ANKH, 10, 16);
		assignItemRect(STYLUS, 12, 13);

		assignItemRect(SEAL, 9, 15);
		assignItemRect(TORCH, 12, 15);
		assignItemRect(BEACON, 16, 15);

		assignItemRect(HONEYPOT, 14, 12);
		assignItemRect(SHATTPOT, 14, 12);
		assignItemRect(IRON_KEY, 8, 14);
		assignItemRect(GOLDEN_KEY, 8, 14);
		assignItemRect(CRYSTAL_KEY, 8, 14);
		assignItemRect(SKELETON_KEY, 8, 14);
		assignItemRect(MASK, 11, 9);
		assignItemRect(CROWN, 13, 7);
		assignItemRect(AMULET, 16, 16);
		assignItemRect(MASTERY, 13, 16);
		assignItemRect(KIT, 16, 15);
	}
	//炸弹-------------------------------------------------------------------------------------
	private static final int BOMBS = xy(1, 6);   //16 slots
	public static final int BOMB = BOMBS + 0;
	public static final int DBL_BOMB = BOMBS + 1;
	public static final int FIRE_BOMB = BOMBS + 2;
	public static final int FROST_BOMB = BOMBS + 3;
	public static final int REGROWTH_BOMB = BOMBS + 4;
	public static final int FLASHBANG = BOMBS + 5;
	public static final int SHOCK_BOMB = BOMBS + 6;
	public static final int HOLY_BOMB = BOMBS + 7;
	public static final int WOOLY_BOMB = BOMBS + 8;
	public static final int NOISEMAKER = BOMBS + 9;
	public static final int ARCANE_BOMB = BOMBS + 10;
	public static final int SHRAPNEL_BOMB = BOMBS + 11;

	static {
		assignItemRect(BOMB, 10, 13);
		assignItemRect(DBL_BOMB, 14, 13);
		assignItemRect(FIRE_BOMB, 13, 12);
		assignItemRect(FROST_BOMB, 13, 12);
		assignItemRect(REGROWTH_BOMB, 13, 12);
		assignItemRect(FLASHBANG, 13, 12);
		assignItemRect(SHOCK_BOMB, 10, 13);
		assignItemRect(HOLY_BOMB, 10, 13);
		assignItemRect(WOOLY_BOMB, 10, 13);
		assignItemRect(NOISEMAKER, 10, 13);
		assignItemRect(ARCANE_BOMB, 10, 13);
		assignItemRect(SHRAPNEL_BOMB, 10, 13);
	}
	//一阶武器-------------------------------------------------------------------------------------
	private static final int WEP_TIER1 = xy(1, 7);
	public static final int WORN_SHORTSWORD = WEP_TIER1 + 0;
	public static final int GLOVES = WEP_TIER1 + 1;
	public static final int DAGGER = WEP_TIER1 + 2;
	public static final int MAGES_STAFF = WEP_TIER1 + 3;
	public static final int SCALPEL = WEP_TIER1 + 4;
	public static final int KUNCKLES = WEP_TIER1 + 5;
	public static final int STICK = WEP_TIER1 + 6;
	public static final int GREENDAGGER = WEP_TIER1 + 7;

	static {
		assignItemRect(WORN_SHORTSWORD, 13, 13);
		assignItemRect(GLOVES, 12, 16);
		assignItemRect(DAGGER, 12, 13);
		assignItemRect(MAGES_STAFF, 15, 16);
		assignItemRect(SCALPEL, 11, 12);
		assignItemRect(KUNCKLES, 13, 9);
		assignItemRect(STICK, 15, 14);
		assignItemRect(GREENDAGGER, 15, 15);
	}
	//二阶武器-------------------------------------------------------------------------------------
	private static final int WEP_TIER2 = xy(1, 8);
	public static final int SHORTSWORD = WEP_TIER2 + 0;
	public static final int HAND_AXE = WEP_TIER2 + 1;
	public static final int SPEAR = WEP_TIER2 + 2;
	public static final int QUARTERSTAFF = WEP_TIER2 + 3;
	public static final int DIRK = WEP_TIER2 + 4;
	public static final int IROM_LEAVE   = WEP_TIER2 +5;
	public static final int CUTOFF   = WEP_TIER2 +6;
	public static final int CHAIN   = WEP_TIER2 +7;
	public static final int ELEOVE   = WEP_TIER2 +8;
	public static final int GEMGLOVE   = WEP_TIER2 +9;
	public static final int BOOK   = WEP_TIER2 +10;
	public static final int TOOTH_NAIL   = WEP_TIER2 +11;

	static {
		assignItemRect(SHORTSWORD, 13, 13);
		assignItemRect(HAND_AXE, 12, 14);
		assignItemRect(SPEAR, 16, 16);
		assignItemRect(QUARTERSTAFF, 16, 16);
		assignItemRect(DIRK, 13, 14);
		assignItemRect(IROM_LEAVE,   15, 15);
		assignItemRect(CUTOFF,   13, 13);
		assignItemRect(CHAIN,   15, 16);
		assignItemRect(ELEOVE,   13, 15);
		assignItemRect(GEMGLOVE,   13, 15);
		assignItemRect(BOOK,   13, 16);
		assignItemRect(TOOTH_NAIL,12,9);
	}
	//三阶武器-------------------------------------------------------------------------------------
	private static final int WEP_TIER3 = xy(1, 9);
	public static final int SWORD = WEP_TIER3 + 0;
	public static final int MACE = WEP_TIER3 + 1;
	public static final int SCIMITAR = WEP_TIER3 + 2;
	public static final int ROUND_SHIELD = WEP_TIER3 + 3;
	public static final int SAI = WEP_TIER3 + 4;
	public static final int WHIP = WEP_TIER3 + 5;
	public static final int  CANG_LAN  =WEP_TIER3 + 6;
	public static final int  SICKLE  = WEP_TIER3 + 7;
	public static final int  TWO_SWORD  =WEP_TIER3 + 8;
	public static final int  KNIFE_TURN  = WEP_TIER3 + 9;
	public static final int  TWO_KNIFE  = WEP_TIER3 + 10;
	public static final int DEADBOOK   = WEP_TIER3+11;
	public static final int BLUE_GEMWEAPON   = WEP_TIER3+12;
	public static final int BONE_SWORD	= WEP_TIER3+13;
	public static final int SHELL_HAMMER	= WEP_TIER3+14;

	static {
		assignItemRect(SWORD, 14, 14);
		assignItemRect(MACE, 15, 15);
		assignItemRect(SCIMITAR, 13, 16);
		assignItemRect(ROUND_SHIELD, 16, 16);
		assignItemRect(SAI, 16, 16);
		assignItemRect(WHIP, 14, 14);
		assignItemRect(CANG_LAN,   14, 14);
		assignItemRect(SICKLE,   16, 16);
		assignItemRect(TWO_SWORD,   14, 14);
		assignItemRect(KNIFE_TURN,   16, 16);
		assignItemRect(TWO_KNIFE,   16, 16);
		assignItemRect(DEADBOOK,   13, 16);
		assignItemRect(BLUE_GEMWEAPON,   14, 16);
		assignItemRect(BONE_SWORD,16,16);
		assignItemRect(SHELL_HAMMER,16,15);
	}
	//四阶武器-------------------------------------------------------------------------------------
	private static final int WEP_TIER4 = xy(1, 10);
	public static final int LONGSWORD = WEP_TIER4 + 0;
	public static final int BATTLE_AXE = WEP_TIER4 + 1;
	public static final int FLAIL = WEP_TIER4 + 2;
	public static final int RUNIC_BLADE = WEP_TIER4 + 3;
	public static final int ASSASSINS_BLADE = WEP_TIER4 + 4;
	public static final int CROSSBOW = WEP_TIER4 + 5;
	public static final int  STAB_SWORD  = WEP_TIER4 + 6;
	public static final int  FIRE_GLOVE  = WEP_TIER4 + 7;
	public static final int  THORN  = WEP_TIER4 + 8;
	public static final int  NEMESIS  =WEP_TIER4 + 9;
	public static final int  FIRESWORD  = WEP_TIER4 + 10;
	public static final int  BLEED_TOOTH = WEP_TIER4+11;

	static {
		assignItemRect(LONGSWORD, 15, 15);
		assignItemRect(BATTLE_AXE, 16, 16);
		assignItemRect(FLAIL, 14, 14);
		assignItemRect(RUNIC_BLADE, 14, 14);
		assignItemRect(ASSASSINS_BLADE, 14, 15);
		assignItemRect(CROSSBOW, 15, 15);
		assignItemRect(STAB_SWORD,   15, 15);
		assignItemRect(FIRE_GLOVE,   13, 15);
		assignItemRect(THORN,   14, 16);
		assignItemRect(NEMESIS,   15, 15);
		assignItemRect(FIRESWORD, 16, 16);
		assignItemRect(BLEED_TOOTH,15,14);
	}
//五阶武器-------------------------------------------------------------------------------------

	private static final int WEP_TIER5 = xy(1, 11);   //8 slots
	public static final int GREATSWORD = WEP_TIER5 + 0;
	public static final int WAR_HAMMER = WEP_TIER5 + 1;
	public static final int GLAIVE = WEP_TIER5 + 2;
	public static final int GREATAXE = WEP_TIER5 + 3;
	public static final int GREATSHIELD = WEP_TIER5 + 4;
	public static final int GAUNTLETS = WEP_TIER5 + 5;
	public static final int  DRAGON_AXE  = WEP_TIER5 + 6;
	public static final int  BROAD_SWORD  = WEP_TIER5 + 7;

	static {
		assignItemRect(GREATSWORD, 16, 16);
		assignItemRect(WAR_HAMMER, 16, 16);
		assignItemRect(GLAIVE, 16, 16);
		assignItemRect(GREATAXE, 12, 16);
		assignItemRect(GREATSHIELD, 12, 16);
		assignItemRect(GAUNTLETS, 13, 15);
		assignItemRect(DRAGON_AXE,   16, 16);
		assignItemRect(BROAD_SWORD,   16, 16);
	}

	//护甲-------------------------------------------------------------------------------------
	private static final int ARMOR = xy(1, 12);
	public static final int ARMOR_CLOTH = ARMOR + 0;
	public static final int ARMOR_LEATHER = ARMOR + 1;
	public static final int ARMOR_MAIL = ARMOR + 2;
	public static final int ARMOR_SCALE = ARMOR + 3;
	public static final int ARMOR_PLATE = ARMOR + 4;
	public static final int ARMOR_WARRIOR = ARMOR + 5;
	public static final int ARMOR_MAGE = ARMOR + 6;
	public static final int ARMOR_ROGUE = ARMOR + 7;
	public static final int ARMOR_HUNTRESS = ARMOR + 8;
	public static final int ARMOR_INVERT = ARMOR + 9;

	static {
		assignItemRect(ARMOR_CLOTH, 15, 12);
		assignItemRect(ARMOR_LEATHER, 14, 13);
		assignItemRect(ARMOR_MAIL, 14, 12);
		assignItemRect(ARMOR_SCALE, 14, 11);
		assignItemRect(ARMOR_PLATE, 12, 12);
		assignItemRect(ARMOR_WARRIOR, 12, 12);
		assignItemRect(ARMOR_MAGE, 15, 15);
		assignItemRect(ARMOR_ROGUE, 14, 12);
		assignItemRect(ARMOR_HUNTRESS, 13, 15);
		assignItemRect(ARMOR_INVERT, 14, 11);
	}
	//一阶投掷武器-------------------------------------------------------------------------------------
	private static final int MISSILE_WEP_TIER1 = xy(1, 13);
	public static final int SPIRIT_BOW = MISSILE_WEP_TIER1 + 0;
	public static final int THROWING_KNIFE = MISSILE_WEP_TIER1 + 1;
	public static final int THROWING_STONE = MISSILE_WEP_TIER1 + 2;
	static{
		assignItemRect(SPIRIT_BOW, 16, 16);
		assignItemRect(THROWING_KNIFE, 12, 13);
		assignItemRect(THROWING_STONE, 12, 10);
	}
	//二阶投掷武器-------------------------------------------------------------------------------------
	private static final int MISSILE_WEP_TIER2 = xy(1, 14);
	public static final int FISHING_SPEAR = MISSILE_WEP_TIER2 + 0;
	public static final int SHURIKEN = MISSILE_WEP_TIER2 + 1;
	public static final int THROWING_CLUB = MISSILE_WEP_TIER2 + 2;
	static{
		assignItemRect(FISHING_SPEAR, 11, 11);
		assignItemRect(SHURIKEN, 12, 12);
		assignItemRect(THROWING_CLUB, 12, 12);
	}
	//三阶投掷武器-------------------------------------------------------------------------------------
	private static final int MISSILE_WEP_TIER3 = xy(1, 15);
	public static final int THROWING_SPEAR = MISSILE_WEP_TIER3 + 0;
	public static final int BOLAS = MISSILE_WEP_TIER3 + 1;
	public static final int KUNAI = MISSILE_WEP_TIER3 + 2;
	static{
		assignItemRect(THROWING_SPEAR, 13, 13);
		assignItemRect(BOLAS, 15, 14);
		assignItemRect(KUNAI, 15, 15);
	}
	//四阶投掷武器-------------------------------------------------------------------------------------
	private static final int MISSILE_WEP_TIER4 = xy(1, 16);
	public static final int JAVELIN = MISSILE_WEP_TIER4 + 0;
	public static final int TOMAHAWK = MISSILE_WEP_TIER4 + 1;
	public static final int BOOMERANG = MISSILE_WEP_TIER4 + 2;
	static{
		assignItemRect(JAVELIN, 16, 16);
		assignItemRect(TOMAHAWK, 13, 13);
		assignItemRect(BOOMERANG, 14, 14);
	}
	//五阶投掷武器-------------------------------------------------------------------------------------
	private static final int MISSILE_WEP_TIER5 = xy(1, 17);
	public static final int TRIDENT = MISSILE_WEP_TIER5 + 0;
	public static final int THROWING_HAMMER = MISSILE_WEP_TIER5 + 1;
	public static final int FORCE_CUBE = MISSILE_WEP_TIER5 + 2;
	static{
		assignItemRect(TRIDENT, 16, 16);
		assignItemRect(THROWING_HAMMER, 12, 12);
		assignItemRect(FORCE_CUBE, 11, 12);
	}
	//飞镖/涂药飞镖-------------------------------------------------------------------------------------
	public static final int TIPPED_DARTS = xy(1, 18);
	public static final int DART = MISSILE_WEP_TIER1 + 0;
	public static final int ROT_DART = TIPPED_DARTS + 1;
	public static final int INCENDIARY_DART = TIPPED_DARTS + 2;
	public static final int ADRENALINE_DART = TIPPED_DARTS + 3;
	public static final int HEALING_DART = TIPPED_DARTS + 4;
	public static final int CHILLING_DART = TIPPED_DARTS + 5;
	public static final int SHOCKING_DART = TIPPED_DARTS + 6;
	public static final int POISON_DART = TIPPED_DARTS + 7;
	public static final int SLEEP_DART = TIPPED_DARTS + 8;
	public static final int PARALYTIC_DART = TIPPED_DARTS + 9;
	public static final int HOLY_DART = TIPPED_DARTS + 10;
	public static final int DISPLACING_DART = TIPPED_DARTS + 11;
	public static final int BLINDING_DART = TIPPED_DARTS + 12;

	static {
		for (int i = TIPPED_DARTS; i < TIPPED_DARTS + 20; i++)
			assignItemRect(i, 15, 15);
	}
	//法杖-------------------------------------------------------------------------------------
	private static final int WANDS = xy(1, 19);
	public static final int WAND_MAGIC_MISSILE = WANDS + 0;
	public static final int WAND_FIREBOLT = WANDS + 1;
	public static final int WAND_FROST = WANDS + 2;
	public static final int WAND_LIGHTNING = WANDS + 3;
	public static final int WAND_DISINTEGRATION = WANDS + 4;
	public static final int WAND_PRISMATIC_LIGHT = WANDS + 5;
	public static final int WAND_CORROSION = WANDS + 6;
	public static final int WAND_LIVING_EARTH = WANDS + 7;
	public static final int WAND_BLAST_WAVE = WANDS + 8;
	public static final int WAND_CORRUPTION = WANDS + 9;
	public static final int WAND_WARDING = WANDS + 10;
	public static final int WAND_REGROWTH = WANDS + 11;
	public static final int WAND_TRANSFUSION = WANDS + 12;
	public static final int TOOTH_WAND = WANDS + 13;

	static {
		for (int i = WANDS; i < WANDS + 16; i++)
			assignItemRect(i, 14, 14);
		assignItemRect(TOOTH_WAND,16,16);
	}
	//戒指-------------------------------------------------------------------------------------
	private static final int RINGS = xy(1, 20);
	public static final int RING_NONE = RINGS + 0;
	public static final int RING_GARNET = RINGS + 1;
	public static final int RING_RUBY = RINGS + 2;
	public static final int RING_TOPAZ = RINGS + 3;
	public static final int RING_EMERALD = RINGS + 4;
	public static final int RING_ONYX = RINGS + 5;
	public static final int RING_OPAL = RINGS + 6;
	public static final int RING_TOURMALINE = RINGS + 7;
	public static final int RING_SAPPHIRE = RINGS + 8;
	public static final int RING_AMETHYST = RINGS + 9;
	public static final int RING_QUARTZ = RINGS + 10;
	public static final int RING_AGATE = RINGS + 11;
	public static final int RING_DIAMOND = RINGS + 12;

	static {
		for (int i = RINGS; i < RINGS + 16; i++)
			assignItemRect(i, 8, 10);
		assignItemRect(RING_NONE, 8, 8);
	}
	//神器-------------------------------------------------------------------------------------
	private static final int ARTIFACTS = xy(1, 21);
	public static final int ARTIFACT_CLOAK = ARTIFACTS + 0;
	public static final int ARTIFACT_ARMBAND = ARTIFACTS + 1;
	public static final int ARTIFACT_CAPE = ARTIFACTS + 2;
	public static final int ARTIFACT_TALISMAN = ARTIFACTS + 3;
	public static final int ARTIFACT_HOURGLASS = ARTIFACTS + 4;
	public static final int ARTIFACT_TOOLKIT = ARTIFACTS + 5;
	public static final int ARTIFACT_SPELLBOOK = ARTIFACTS + 6;
	public static final int ARTIFACT_BEACON = ARTIFACTS + 7;
	public static final int ARTIFACT_CHAINS = ARTIFACTS + 8;
	public static final int ARTIFACT_HORN1 = ARTIFACTS + 9;
	public static final int ARTIFACT_HORN2 = ARTIFACTS + 10;
	public static final int ARTIFACT_HORN3 = ARTIFACTS + 11;
	public static final int ARTIFACT_HORN4 = ARTIFACTS + 12;
	public static final int ARTIFACT_CHALICE1 = ARTIFACTS + 13;
	public static final int ARTIFACT_CHALICE2 = ARTIFACTS + 14;
	public static final int ARTIFACT_CHALICE3 = ARTIFACTS + 15;
	public static final int ARTIFACT_SANDALS = ARTIFACTS + 16;
	public static final int ARTIFACT_SHOES = ARTIFACTS + 17;
	public static final int ARTIFACT_BOOTS = ARTIFACTS + 18;
	public static final int ARTIFACT_GREAVES = ARTIFACTS + 19;
	public static final int ARTIFACT_ROSE1 = ARTIFACTS + 20;
	public static final int ARTIFACT_ROSE2 = ARTIFACTS + 21;
	public static final int ARTIFACT_ROSE3 = ARTIFACTS + 22;

	static {
		assignItemRect(ARTIFACT_CLOAK, 9, 15);
		assignItemRect(ARTIFACT_ARMBAND, 16, 13);
		assignItemRect(ARTIFACT_CAPE, 16, 14);
		assignItemRect(ARTIFACT_TALISMAN, 15, 13);
		assignItemRect(ARTIFACT_HOURGLASS, 13, 16);
		assignItemRect(ARTIFACT_TOOLKIT, 15, 13);
		assignItemRect(ARTIFACT_SPELLBOOK, 13, 16);
		assignItemRect(ARTIFACT_BEACON, 16, 16);
		assignItemRect(ARTIFACT_CHAINS, 16, 16);
		assignItemRect(ARTIFACT_HORN1, 15, 15);
		assignItemRect(ARTIFACT_HORN2, 15, 15);
		assignItemRect(ARTIFACT_HORN3, 15, 15);
		assignItemRect(ARTIFACT_HORN4, 15, 15);
		assignItemRect(ARTIFACT_CHALICE1, 12, 15);
		assignItemRect(ARTIFACT_CHALICE2, 12, 15);
		assignItemRect(ARTIFACT_CHALICE3, 12, 15);
		assignItemRect(ARTIFACT_SANDALS, 16, 6);
		assignItemRect(ARTIFACT_SHOES, 16, 6);
		assignItemRect(ARTIFACT_BOOTS, 16, 9);
		assignItemRect(ARTIFACT_GREAVES, 16, 14);
		assignItemRect(ARTIFACT_ROSE1, 14, 14);
		assignItemRect(ARTIFACT_ROSE2, 14, 14);
		assignItemRect(ARTIFACT_ROSE3, 14, 14);
	}
	//符文-------------------------------------------------------------------------------------
	private static final int SCROLLS = xy(1, 23);
	public static final int SCROLL_KAUNAN = SCROLLS + 0;
	public static final int SCROLL_SOWILO = SCROLLS + 1;
	public static final int SCROLL_LAGUZ = SCROLLS + 2;
	public static final int SCROLL_YNGVI = SCROLLS + 3;
	public static final int SCROLL_GYFU = SCROLLS + 4;
	public static final int SCROLL_RAIDO = SCROLLS + 5;
	public static final int SCROLL_ISAZ = SCROLLS + 6;
	public static final int SCROLL_MANNAZ = SCROLLS + 7;
	public static final int SCROLL_NAUDIZ = SCROLLS + 8;
	public static final int SCROLL_BERKANAN = SCROLLS + 9;
	public static final int SCROLL_ODAL = SCROLLS + 10;
	public static final int SCROLL_TIWAZ = SCROLLS + 11;
	public static final int SCROLL_CATALYST = SCROLLS + 12;
	public static final int ARCANE_RESIN = SCROLLS + 13;

	static {
		assignItemRect(SCROLL_KAUNAN, 6, 8);
		assignItemRect(SCROLL_SOWILO, 6, 8);
		assignItemRect(SCROLL_LAGUZ, 5, 8);
		assignItemRect(SCROLL_YNGVI, 6, 6);
		assignItemRect(SCROLL_GYFU, 7, 8);
		assignItemRect(SCROLL_RAIDO, 6, 8);
		assignItemRect(SCROLL_ISAZ, 3, 8);
		assignItemRect(SCROLL_MANNAZ, 7, 8);
		assignItemRect(SCROLL_NAUDIZ, 7, 9);
		assignItemRect(SCROLL_BERKANAN, 6, 8);
		assignItemRect(SCROLL_ODAL, 5, 8);
		assignItemRect(SCROLL_TIWAZ, 7, 8);
		assignItemRect(SCROLL_CATALYST, 12, 11);
		assignItemRect(ARCANE_RESIN, 12, 11);
	}
	//卷轴-------------------------------------------------------------------------------------
	private static final int EXOTIC_SCROLLS = xy(1, 24);
	public static final int EXOTIC_NONE = EXOTIC_SCROLLS + 0;
	public static final int EXOTIC_KAUNAN = EXOTIC_SCROLLS + 1;
	public static final int EXOTIC_SOWILO = EXOTIC_SCROLLS + 2;
	public static final int EXOTIC_LAGUZ = EXOTIC_SCROLLS + 3;
	public static final int EXOTIC_YNGVI = EXOTIC_SCROLLS + 4;
	public static final int EXOTIC_GYFU = EXOTIC_SCROLLS + 5;
	public static final int EXOTIC_RAIDO = EXOTIC_SCROLLS + 6;
	public static final int EXOTIC_ISAZ = EXOTIC_SCROLLS + 7;
	public static final int EXOTIC_MANNAZ = EXOTIC_SCROLLS + 8;
	public static final int EXOTIC_NAUDIZ = EXOTIC_SCROLLS + 9;
	public static final int EXOTIC_BERKANAN = EXOTIC_SCROLLS + 10;
	public static final int EXOTIC_ODAL = EXOTIC_SCROLLS + 11;
	public static final int EXOTIC_TIWAZ = EXOTIC_SCROLLS + 12;

	static {
		for (int i = EXOTIC_SCROLLS; i < EXOTIC_SCROLLS + 20; i++)
			assignItemRect(i, 15, 14);
	}
	//符石-------------------------------------------------------------------------------------
	private static final int STONES = xy(1, 25);
	public static final int STONE_NONE = STONES + 0;
	public static final int STONE_AGGRESSION = STONES + 1;
	public static final int STONE_AUGMENTATION = STONES + 2;
	public static final int STONE_FEAR = STONES + 3;
	public static final int STONE_BLAST = STONES + 4;
	public static final int STONE_BLINK = STONES + 5;
	public static final int STONE_CLAIRVOYANCE = STONES + 6;
	public static final int STONE_SLEEP = STONES + 7;
	public static final int STONE_DISARM = STONES + 8;
	public static final int STONE_ENCHANT = STONES + 9;
	public static final int STONE_FLOCK = STONES + 10;
	public static final int STONE_INTUITION = STONES + 11;
	public static final int STONE_SHOCK = STONES + 12;

	static {
		for (int i = STONES; i < STONES + 16; i++)
			assignItemRect(i, 14, 12);
	}
	//种子-------------------------------------------------------------------------------------
	private static final int SEEDS = xy(1, 26);
	public static final int SEED_ROTBERRY = SEEDS + 0;
	public static final int SEED_FIREBLOOM = SEEDS + 1;
	public static final int SEED_SWIFTTHISTLE = SEEDS + 2;
	public static final int SEED_SUNGRASS = SEEDS + 3;
	public static final int SEED_ICECAP = SEEDS + 4;
	public static final int SEED_STORMVINE = SEEDS + 5;
	public static final int SEED_SORROWMOSS = SEEDS + 6;
	public static final int SEED_DREAMFOIL = SEEDS + 7;
	public static final int SEED_EARTHROOT = SEEDS + 8;
	public static final int SEED_STARFLOWER = SEEDS + 9;
	public static final int SEED_FADELEAF = SEEDS + 10;
	public static final int SEED_BLINDWEED = SEEDS + 11;
	public static final int SEED_PEPPERBUSHES = SEEDS + 15;
	public static final int SEED_CRYSTALFLOWER = SEEDS + 16;
	public static final int SEED_BLEEDFIRE = SEEDS + 17;
	public static final int SEED_LIGHTCOMOSUM = SEEDS + 18;
	public static final int SEED_POWER = SEEDS + 19;
	public static final int SEED_WATER = SEEDS + 20;
	public static final int SEED_FOG = SEEDS + 21;
	public static final int SEED_ICE = SEEDS + 22;
	public static final int SEED_MENTHOLTHICKET = SEEDS + 23;

	static {
		for (int i = SEEDS; i < SEEDS + 30; i++)
			assignItemRect(i, 10, 10);
	}
	//药水-------------------------------------------------------------------------------------
	private static final int POTIONS = xy(1, 27);
	public static final int POTION_CRIMSON = POTIONS + 0;
	public static final int POTION_AMBER = POTIONS + 1;
	public static final int POTION_GOLDEN = POTIONS + 2;
	public static final int POTION_JADE = POTIONS + 3;
	public static final int POTION_TURQUOISE = POTIONS + 4;
	public static final int POTION_AZURE = POTIONS + 5;
	public static final int POTION_INDIGO = POTIONS + 6;
	public static final int POTION_MAGENTA = POTIONS + 7;
	public static final int POTION_BISTRE = POTIONS + 8;
	public static final int POTION_CHARCOAL = POTIONS + 9;
	public static final int POTION_SILVER = POTIONS + 10;
	public static final int POTION_IVORY = POTIONS + 11;
	public static final int POTION_CATALYST = POTIONS + 13;
	public static final int LIQUID_METAL = POTIONS + 14;
	public static final int LIQUID_METAL2 = POTIONS + 15;

	static {
		for (int i = POTIONS; i < POTIONS + 32; i++)
			assignItemRect(i, 16, 16);
		assignItemRect(POTION_CATALYST, 6, 15);
		assignItemRect(LIQUID_METAL, 8, 15);
	}
	//合剂-------------------------------------------------------------------------------------
	private static final int EXOTIC_POTIONS =                               xy(1, 0);  //16 slots
	public static final int EXOTIC_CRIMSON  = EXOTIC_POTIONS+0;
	public static final int EXOTIC_AMBER    = EXOTIC_POTIONS+1;
	public static final int EXOTIC_GOLDEN   = EXOTIC_POTIONS+2;
	public static final int EXOTIC_JADE     = EXOTIC_POTIONS+3;
	public static final int EXOTIC_TURQUOISE= EXOTIC_POTIONS+4;
	public static final int EXOTIC_AZURE    = EXOTIC_POTIONS+5;
	public static final int EXOTIC_INDIGO   = EXOTIC_POTIONS+6;
	public static final int EXOTIC_MAGENTA  = EXOTIC_POTIONS+7;
	public static final int EXOTIC_BISTRE   = EXOTIC_POTIONS+8;
	public static final int EXOTIC_CHARCOAL = EXOTIC_POTIONS+9;
	public static final int EXOTIC_SILVER   = EXOTIC_POTIONS+10;
	public static final int EXOTIC_IVORY    = EXOTIC_POTIONS+11;
	static {
		for (int i = EXOTIC_POTIONS; i < EXOTIC_POTIONS+16; i++)
			assignItemRect(i, 12, 13);
	}
	//圆瓶-------------------------------------------------------------------------------------
	private static final int ROUNDPOTION = xy(1, 29);
	public static final int ROUNDPOTION_CRIMSON = ROUNDPOTION + 0;
	public static final int BREW_INFERNAL = ROUNDPOTION + 1;
	public static final int ROUNDPOTION_GOLDEN = ROUNDPOTION + 2;
	public static final int ROUNDPOTION_JADE = ROUNDPOTION + 3;
	public static final int BREW_BLIZZARD = ROUNDPOTION + 4;
	public static final int ROUNDPOTION_AZURE = ROUNDPOTION + 5;
	public static final int ROUNDPOTION_INDIGO = ROUNDPOTION + 6;
	public static final int ROUNDPOTION_MAGENTA = ROUNDPOTION + 7;
	public static final int ROUNDPOTION_BISTRE = ROUNDPOTION + 8;
	public static final int ROUNDPOTION_CHARCOAL = ROUNDPOTION + 9;
	public static final int ROUNDPOTION_SILVER = ROUNDPOTION + 10;
	public static final int ROUNDPOTION_IVORY = ROUNDPOTION + 11;

	static {
		for (int i = ROUNDPOTION; i < ROUNDPOTION + 16; i++)
			assignItemRect(i, 12, 14);
	}
	//锥瓶-------------------------------------------------------------------------------------
	private static final int CONEPOTION = xy(1, 30);
	public static final int CONEPOTION_CRIMSON = CONEPOTION + 0;
	public static final int BREW_SHOCKING = CONEPOTION + 1;
	public static final int ELIXIR_HONEY = CONEPOTION + 2;
	public static final int CONEPOTION_JADE = CONEPOTION + 3;
	public static final int ELIXIR_AQUA = CONEPOTION + 4;
	public static final int CONEPOTION_AZURE = CONEPOTION + 5;
	public static final int CONEPOTION_INDIGO = CONEPOTION + 6;
	public static final int CONEPOTION_MAGENTA = CONEPOTION + 7;
	public static final int CONEPOTION_BISTRE = CONEPOTION + 8;
	public static final int BREW_CAUSTIC = CONEPOTION + 9;
	public static final int CONEPOTION_SILVER = CONEPOTION + 10;
	public static final int CONEPOTION_IVORY = CONEPOTION + 11;

	static {
		for (int i = CONEPOTION; i < CONEPOTION + 16; i++)
			assignItemRect(i, 12, 14);
	}
	//方瓶-------------------------------------------------------------------------------------
	private static final int BUBEPOTION = xy(1, 31);
	public static final int ELIXIR_MIGHT = BUBEPOTION + 0;
	public static final int ELIXIR_DRAGON = BUBEPOTION + 1;
	public static final int BUBEPOTION_GOLDEN = BUBEPOTION + 2;
	public static final int ELIXIR_TOXIC = BUBEPOTION + 3;
	public static final int ELIXIR_ICY = BUBEPOTION + 4;
	public static final int BUBEPOTION_AZURE = BUBEPOTION + 5;
	public static final int BUBEPOTION_INDIGO = BUBEPOTION + 6;
	public static final int BUBEPOTION_MAGENTA = BUBEPOTION + 7;
	public static final int BUBEPOTION_BISTRE = BUBEPOTION + 8;
	public static final int ELIXIR_ARCANE = BUBEPOTION + 9;
	public static final int BUBEPOTION_SILVER = BUBEPOTION + 10;
	public static final int BUBEPOTION_IVORY = BUBEPOTION + 11;

	static {
		for (int i = BUBEPOTION; i < BUBEPOTION + 16; i++)
			assignItemRect(i, 12, 14);
	}
	//方晶-------------------------------------------------------------------------------------
	private static final int BLOCKCRYSTAL = xy(1, 32);
	public static final int BLOCKCRYSTAL_CRIMSON = BLOCKCRYSTAL + 0;
	public static final int BLOCKCRYSTAL_AMBER = BLOCKCRYSTAL + 1;
	public static final int BLOCKCRYSTAL_GOLDEN = BLOCKCRYSTAL + 2;
	public static final int BLOCKCRYSTAL_JADE = BLOCKCRYSTAL + 3;
	public static final int BLOCKCRYSTAL_TURQUOISE = BLOCKCRYSTAL + 4;
	public static final int BLOCKCRYSTAL_AZURE = BLOCKCRYSTAL + 5;
	public static final int PHASE_SHIFT = BLOCKCRYSTAL + 6;
	public static final int BLOCKCRYSTAL_MAGENTA = BLOCKCRYSTAL + 7;
	public static final int MAGIC_PORTER = BLOCKCRYSTAL + 8;
	public static final int BLOCKCRYSTAL_CHARCOAL = BLOCKCRYSTAL + 9;
	public static final int BLOCKCRYSTAL_SILVER = BLOCKCRYSTAL + 10;
	public static final int TELE_GRAB = BLOCKCRYSTAL + 11;

	static {
		for (int i = BLOCKCRYSTAL; i < BLOCKCRYSTAL + 16; i++)
			assignItemRect(i, 12, 11);
	}
	//晶核-------------------------------------------------------------------------------------
	private static final int ROUNDCRYSTAL = xy(1, 33);
	public static final int ROUNDCRYSTAL_CRIMSON = ROUNDCRYSTAL + 0;
	public static final int ROUNDCRYSTAL_AMBER = ROUNDCRYSTAL + 1;
	public static final int ROUNDCRYSTAL_GOLDEN = ROUNDCRYSTAL + 2;
	public static final int ROUNDCRYSTAL_JADE = ROUNDCRYSTAL + 3;
	public static final int AQUA_BLAST = ROUNDCRYSTAL + 4;
	public static final int ROUNDCRYSTAL_AZURE = ROUNDCRYSTAL + 5;
	public static final int ROUNDCRYSTAL_INDIGO = ROUNDCRYSTAL + 6;
	public static final int ROUNDCRYSTAL_MAGENTA = ROUNDCRYSTAL + 7;
	public static final int ROUNDCRYSTAL_BISTRE = ROUNDCRYSTAL + 8;
	public static final int RECLAIM_TRAP = ROUNDCRYSTAL + 9;
	public static final int ROUNDCRYSTAL_SILVER = ROUNDCRYSTAL + 10;
	public static final int FEATHER_FALL = ROUNDCRYSTAL + 11;

	static {
		for (int i = ROUNDCRYSTAL; i < ROUNDCRYSTAL + 16; i++)
			assignItemRect(i, 11, 11);
	}
	//晶柱-------------------------------------------------------------------------------------
	private static final int COLUMNCRYSTAL = xy(1, 34);
	public static final int COLUMNCRYSTAL_CRIMSON = COLUMNCRYSTAL + 0;
	public static final int SUMMON_ELE = COLUMNCRYSTAL + 1;
	public static final int COLUMNCRYSTAL_GOLDEN = COLUMNCRYSTAL + 2;
	public static final int COLUMNCRYSTAL_JADE = COLUMNCRYSTAL + 3;
	public static final int COLUMNCRYSTAL_TURQUOISE = COLUMNCRYSTAL + 4;
	public static final int COLUMNCRYSTAL_AZURE = COLUMNCRYSTAL + 5;
	public static final int RETURN_BEACON = COLUMNCRYSTAL + 6;
	public static final int COLUMNCRYSTAL_MAGENTA = COLUMNCRYSTAL + 7;
	public static final int COLUMNCRYSTAL_BISTRE = COLUMNCRYSTAL + 8;
	public static final int WILD_ENERGY = COLUMNCRYSTAL + 9;
	public static final int COLUMNCRYSTAL_SILVER = COLUMNCRYSTAL + 10;
	public static final int COLUMNCRYSTAL_IVORY = COLUMNCRYSTAL + 11;

	static {
		for (int i = COLUMNCRYSTAL; i < COLUMNCRYSTAL + 16; i++)
			assignItemRect(i, 8, 16);
	}
	//菱晶-------------------------------------------------------------------------------------
	private static final int LOZECRYSTAL = xy(1, 35);
	public static final int LOZECRYSTAL_CRIMSON = LOZECRYSTAL + 0;
	public static final int LOZECRYSTAL_AMBER = LOZECRYSTAL + 1;
	public static final int MAGIC_INFUSE = LOZECRYSTAL + 2;
	public static final int ALCHEMIZE = LOZECRYSTAL + 3;
	public static final int LOZECRYSTAL_TURQUOISE = LOZECRYSTAL + 4;
	public static final int LOZECRYSTAL_AZURE = LOZECRYSTAL + 5;
	public static final int LOZECRYSTAL_INDIGO = LOZECRYSTAL + 6;
	public static final int LOZECRYSTAL_MAGENTA = LOZECRYSTAL + 7;
	public static final int LOZECRYSTAL_BISTRE = LOZECRYSTAL + 8;
	public static final int CURSE_INFUSE = LOZECRYSTAL + 9;
	public static final int RECYCLE = LOZECRYSTAL + 10;
	public static final int LOZECRYSTAL_IVORY = LOZECRYSTAL + 11;

	static {
		for (int i = LOZECRYSTAL; i < LOZECRYSTAL + 16; i++)
			assignItemRect(i, 10, 15);
	}
	//碎晶-------------------------------------------------------------------------------------
	private static final int GEM = xy(1, 36);
	public static final int FIRE_GEM = GEM + 0;
	public static final int WEAPON_GEM = GEM + 2;
	public static final int GRASS_GEM = GEM + 3;
	public static final int WATER_GEM = GEM + 5;
	public static final int BLACK_GEM = GEM + 9;
	public static final int WHITE_GEM = GEM + 11;
	public static final int COLOR_GEM = GEM + 12;

	static {
		assignItemRect(FIRE_GEM,12,10);
		assignItemRect(WEAPON_GEM,13,12);
		assignItemRect(GRASS_GEM,14,12);
		assignItemRect(WATER_GEM,11,11);

		assignItemRect(WHITE_GEM,15,14);
		assignItemRect(COLOR_GEM,10,12);

	}

	//星尘-------------------------------------------------------------------------------------
	private static final int STAR_DUST = xy(1, 37);
	public static final int FIRE_DUST = STAR_DUST + 0;
	public static final int WEAPON_DUST = STAR_DUST + 2;
	public static final int GRASS_DUST = STAR_DUST + 3;
	public static final int WATER_DUST = STAR_DUST + 5;
	public static final int WHITE_DUST = STAR_DUST + 11;
	public static final int COLOR_DUST = STAR_DUST + 12;

	static {
		for (int i = STAR_DUST; i < STAR_DUST + 16; i++)
			assignItemRect(i, 16, 16);
	}
	//食物-------------------------------------------------------------------------------------
	private static final int FOOD = xy(1, 38);
	//生肉
	public static final int MYSTERY_MEAT = FOOD + 0;
	//烤肉
	public static final int CHARGRILLED_MEAT = FOOD + 1;
	//炖肉
	public static final int STEWED_MEAT = FOOD + 2;
	//小包口粮
	public static final int SMALLRATION = FOOD + 3;
	//冻肉
	public static final int FROZEN_CARPACCIO = FOOD + 4;
	//口粮
	public static final int NORMALFOOD = FOOD + 5;
	//肉饼
	public static final int PASTY = FOOD + 6;
	//南瓜派
	public static final int PUMPKIN_PIE = FOOD + 7;
	//拐杖糖
	public static final int CANDY_CANE = FOOD + 8;
	//全肉大饼
	public static final int MEAT_PIE = FOOD + 9;
	public static final int BLANDFRUIT = FOOD + 10;
	public static final int BLAND_CHUNKS = FOOD + 11;
	public static final int BERRY = FOOD + 12;

	static {
		assignItemRect(MYSTERY_MEAT, 15, 11);
		assignItemRect(CHARGRILLED_MEAT, 15, 11);
		assignItemRect(STEWED_MEAT, 15, 11);
		assignItemRect(SMALLRATION, 14, 11);
		assignItemRect(FROZEN_CARPACCIO, 15, 11);
		assignItemRect(NORMALFOOD, 16, 12);
		assignItemRect(PASTY, 16, 11);
		assignItemRect(PUMPKIN_PIE, 16, 12);
		assignItemRect(CANDY_CANE, 13, 16);
		assignItemRect(MEAT_PIE, 16, 12);
		assignItemRect(BLANDFRUIT, 9, 12);
		assignItemRect(BLAND_CHUNKS, 14, 6);
		assignItemRect(BERRY, 9, 11);
	}
	//包裹-------------------------------------------------------------------------------------
	private static final int BAGS = xy(1, 39);
	public static final int WATERSKIN = BAGS + 0;
	public static final int BACKPACK = BAGS + 1;
	public static final int POUCH = BAGS + 2;
	public static final int HOLDER = BAGS + 3;
	public static final int BANDOLIER = BAGS + 4;
	public static final int HOLSTER = BAGS + 5;
	public static final int VIAL = BAGS + 6;

	static {
		assignItemRect(WATERSKIN, 8, 14);
		assignItemRect(BACKPACK, 16, 16);
		assignItemRect(POUCH, 14, 15);
		assignItemRect(HOLDER, 16, 16);
		assignItemRect(BANDOLIER, 15, 16);
		assignItemRect(HOLSTER, 15, 16);
		assignItemRect(VIAL, 12, 12);
	}
	//任务物品-------------------------------------------------------------------------------------
	private static final int QUEST = xy(1, 40);
	public static final int SKULL = QUEST + 0;
	public static final int DUST = QUEST + 1;
	public static final int CANDLE = QUEST + 2;
	public static final int EMBER = QUEST + 3;
	public static final int PICKAXE = QUEST + 4;
	public static final int ORE = QUEST + 5;
	public static final int TOKEN = QUEST + 6;
	public static final int BLOB = QUEST + 7;
	public static final int SHARD = QUEST + 8;

	static {
		assignItemRect(SKULL, 16, 11);
		assignItemRect(DUST, 12, 11);
		assignItemRect(CANDLE, 12, 12);
		assignItemRect(EMBER, 12, 11);
		assignItemRect(PICKAXE, 14, 14);
		assignItemRect(ORE, 15, 15);
		assignItemRect(TOKEN, 12, 12);
		assignItemRect(BLOB, 10, 9);
		assignItemRect(SHARD, 8, 10);
	}
	//杂项/未分类-------------------------------------------------------------------------------------
	private static final int OTHER = xy(1, 41);
	public static final int AMMO_MINI = OTHER + 0;
	public static final int AMMO_BOX = OTHER + 1;
	public static final int GUN= OTHER + 2;
	public static final int BANSHOU= OTHER + 3;
	public static final int COLOR= OTHER + 4;
	public static final int LOCK = OTHER + 5;
	static {
		assignItemRect(AMMO_MINI, 7, 16);
		assignItemRect(AMMO_BOX, 13, 10);
		assignItemRect(GUN, 12, 16);
		assignItemRect(BANSHOU,   13, 13);
		assignItemRect(COLOR,   12, 11);
		assignItemRect(LOCK,   10, 11);
	}
//植物魔法-------------------------------------------------------------------------------------

//-------------------------------------------------------------------------------------
private static final int OTHER1 = xy(1, 44);
	public static final int BURN_GEM_MAKER = OTHER1 + 1;
	static {
		assignItemRect(BURN_GEM_MAKER, 12, 10);
	}

//区域特殊物品-------------------------------------------------------------------------------------
	private static final int AREA_ITEM = xy(1, 45);
	public static final int TOOTH = AREA_ITEM + 0;
	public static final int BURIN = AREA_ITEM + 1;
	public static final int BONE = AREA_ITEM + 2;
	public static final int SLIME = AREA_ITEM + 3;
	public static final int SOUL = AREA_ITEM + 4;
	public static final int SOUL_BLEED = AREA_ITEM + 5;
	public static final int SHELL = AREA_ITEM + 6;
	public static final int DEATH_BLEED = AREA_ITEM + 7;
	static {
		assignItemRect(TOOTH, 11, 14);
		assignItemRect(BURIN,12,13);
		assignItemRect(BONE,13,13);
		assignItemRect(SLIME,10,10);
		assignItemRect(SOUL,10,10);
		assignItemRect(SOUL_BLEED,6,15);
		assignItemRect(SHELL,12,9);
		assignItemRect(DEATH_BLEED,8,15);
	}

//物品角标栏-------------------------------------------------------------------------------------
	public static class Icons {

		private static final int WIDTH = 16;
		public static final int SIZE = 8;

		public static TextureFilm film = new TextureFilm(Assets.Sprites.ITEM_ICONS, SIZE, SIZE);

		private static int xy(int x, int y) {
			x -= 1;
			y -= 1;
			return x + WIDTH * y;
		}

		private static void assignIconRect(int item, int width, int height) {
			int x = (item % WIDTH) * SIZE;
			int y = (item / WIDTH) * SIZE;
			film.add(item, x, y, x + width, y + height);
		}

		private static final int RINGS = xy(1, 1);  //16 slots
		public static final int RING_ACCURACY = RINGS + 0;
		public static final int RING_ELEMENTS = RINGS + 1;
		public static final int RING_ENERGY = RINGS + 2;
		public static final int RING_EVASION = RINGS + 3;
		public static final int RING_FORCE = RINGS + 4;
		public static final int RING_FUROR = RINGS + 5;
		public static final int RING_HASTE = RINGS + 6;
		public static final int RING_MIGHT = RINGS + 7;
		public static final int RING_SHARPSHOOT = RINGS + 8;
		public static final int RING_TENACITY = RINGS + 9;
		public static final int RING_WEALTH = RINGS + 10;
		public static final int RING_UNUSED = RINGS + 11;

		static {
			assignIconRect(RING_ACCURACY, 7, 7);
			assignIconRect(RING_ELEMENTS, 7, 7);
			assignIconRect(RING_ENERGY, 7, 5);
			assignIconRect(RING_EVASION, 7, 7);
			assignIconRect(RING_FORCE, 5, 6);
			assignIconRect(RING_FUROR, 7, 6);
			assignIconRect(RING_HASTE, 6, 6);
			assignIconRect(RING_MIGHT, 7, 7);
			assignIconRect(RING_SHARPSHOOT, 7, 7);
			assignIconRect(RING_TENACITY, 6, 6);
			assignIconRect(RING_WEALTH, 7, 6);
		}

		//16 free slots

		private static final int SCROLLS = xy(1, 3);  //16 slots
		public static final int SCROLL_UPGRADE = SCROLLS + 0;
		public static final int SCROLL_IDENTIFY = SCROLLS + 1;
		public static final int SCROLL_REMCURSE = SCROLLS + 2;
		public static final int SCROLL_MIRRORIMG = SCROLLS + 3;
		public static final int SCROLL_RECHARGE = SCROLLS + 4;
		public static final int SCROLL_TELEPORT = SCROLLS + 5;
		public static final int SCROLL_LULLABY = SCROLLS + 6;
		public static final int SCROLL_MAGICMAP = SCROLLS + 7;
		public static final int SCROLL_RAGE = SCROLLS + 8;
		public static final int SCROLL_RETRIB = SCROLLS + 9;
		public static final int SCROLL_TERROR = SCROLLS + 10;
		public static final int SCROLL_TRANSMUTE = SCROLLS + 11;

		static {
			assignIconRect(SCROLL_UPGRADE, 7, 7);
			assignIconRect(SCROLL_IDENTIFY, 4, 7);
			assignIconRect(SCROLL_REMCURSE, 7, 7);
			assignIconRect(SCROLL_MIRRORIMG, 7, 5);
			assignIconRect(SCROLL_RECHARGE, 7, 5);
			assignIconRect(SCROLL_TELEPORT, 7, 7);
			assignIconRect(SCROLL_LULLABY, 7, 6);
			assignIconRect(SCROLL_MAGICMAP, 7, 7);
			assignIconRect(SCROLL_RAGE, 6, 6);
			assignIconRect(SCROLL_RETRIB, 5, 6);
			assignIconRect(SCROLL_TERROR, 5, 7);
			assignIconRect(SCROLL_TRANSMUTE, 7, 7);
		}

		private static final int EXOTIC_SCROLLS = xy(1, 4);  //16 slots
		public static final int SCROLL_ENCHANT = EXOTIC_SCROLLS + 0;
		public static final int SCROLL_DIVINATE = EXOTIC_SCROLLS + 1;
		public static final int SCROLL_ANTIMAGIC = EXOTIC_SCROLLS + 2;
		public static final int SCROLL_PRISIMG = EXOTIC_SCROLLS + 3;
		public static final int SCROLL_MYSTENRG = EXOTIC_SCROLLS + 4;
		public static final int SCROLL_PASSAGE = EXOTIC_SCROLLS + 5;
		public static final int SCROLL_SIREN = EXOTIC_SCROLLS + 6;
		public static final int SCROLL_FORESIGHT = EXOTIC_SCROLLS + 7;
		public static final int SCROLL_CHALLENGE = EXOTIC_SCROLLS + 8;
		public static final int SCROLL_PSIBLAST = EXOTIC_SCROLLS + 9;
		public static final int SCROLL_DREAD = EXOTIC_SCROLLS + 10;
		public static final int SCROLL_METAMORPH = EXOTIC_SCROLLS + 11;

		static {
			assignIconRect(SCROLL_ENCHANT, 7, 7);
			assignIconRect(SCROLL_DIVINATE, 7, 6);
			assignIconRect(SCROLL_ANTIMAGIC, 7, 7);
			assignIconRect(SCROLL_PRISIMG, 5, 7);
			assignIconRect(SCROLL_MYSTENRG, 7, 5);
			assignIconRect(SCROLL_PASSAGE, 5, 7);
			assignIconRect(SCROLL_SIREN, 7, 6);
			assignIconRect(SCROLL_FORESIGHT, 7, 5);
			assignIconRect(SCROLL_CHALLENGE, 7, 7);
			assignIconRect(SCROLL_PSIBLAST, 5, 6);
			assignIconRect(SCROLL_DREAD, 5, 7);
			assignIconRect(SCROLL_METAMORPH, 7, 7);
		}

		//16 free slots

		private static final int POTIONS = xy(1, 6);  //16 slots
		public static final int POTION_STRENGTH = POTIONS + 0;
		public static final int POTION_HEALING = POTIONS + 1;
		public static final int POTION_MINDVIS = POTIONS + 2;
		public static final int POTION_FROST = POTIONS + 3;
		public static final int POTION_LIQFLAME = POTIONS + 4;
		public static final int POTION_TOXICGAS = POTIONS + 5;
		public static final int POTION_HASTE = POTIONS + 6;
		public static final int POTION_INVIS = POTIONS + 7;
		public static final int POTION_LEVITATE = POTIONS + 8;
		public static final int POTION_PARAGAS = POTIONS + 9;
		public static final int POTION_PURITY = POTIONS + 10;
		public static final int POTION_EXP = POTIONS + 11;
		public static final int POTION_DAMAGE = POTIONS + 12;

		static {
			assignIconRect(POTION_STRENGTH, 7, 7);
			assignIconRect(POTION_HEALING, 6, 7);
			assignIconRect(POTION_MINDVIS, 7, 5);
			assignIconRect(POTION_FROST, 7, 7);
			assignIconRect(POTION_LIQFLAME, 5, 7);
			assignIconRect(POTION_TOXICGAS, 7, 7);
			assignIconRect(POTION_HASTE, 6, 6);
			assignIconRect(POTION_INVIS, 5, 7);
			assignIconRect(POTION_LEVITATE, 6, 7);
			assignIconRect(POTION_PARAGAS, 7, 7);
			assignIconRect(POTION_PURITY, 5, 7);
			assignIconRect(POTION_EXP, 7, 7);
			assignIconRect(POTION_DAMAGE, 7, 7);
		}

		private static final int EXOTIC_POTIONS = xy(1, 7);  //16 slots
		public static final int POTION_MASTERY = EXOTIC_POTIONS + 0;
		public static final int POTION_SHIELDING = EXOTIC_POTIONS + 1;
		public static final int POTION_MAGISIGHT = EXOTIC_POTIONS + 2;
		public static final int POTION_SNAPFREEZ = EXOTIC_POTIONS + 3;
		public static final int POTION_DRGBREATH = EXOTIC_POTIONS + 4;
		public static final int POTION_CORROGAS = EXOTIC_POTIONS + 5;
		public static final int POTION_STAMINA = EXOTIC_POTIONS + 6;
		public static final int POTION_SHROUDFOG = EXOTIC_POTIONS + 7;
		public static final int POTION_STRMCLOUD = EXOTIC_POTIONS + 8;
		public static final int POTION_EARTHARMR = EXOTIC_POTIONS + 9;
		public static final int POTION_CLEANSE = EXOTIC_POTIONS + 10;
		public static final int POTION_DIVINE = EXOTIC_POTIONS + 11;

		static {
			assignIconRect(POTION_MASTERY, 7, 7);
			assignIconRect(POTION_SHIELDING, 6, 6);
			assignIconRect(POTION_MAGISIGHT, 7, 5);
			assignIconRect(POTION_SNAPFREEZ, 7, 7);
			assignIconRect(POTION_DRGBREATH, 7, 7);
			assignIconRect(POTION_CORROGAS, 7, 7);
			assignIconRect(POTION_STAMINA, 6, 6);
			assignIconRect(POTION_SHROUDFOG, 7, 7);
			assignIconRect(POTION_STRMCLOUD, 7, 7);
			assignIconRect(POTION_EARTHARMR, 6, 6);
			assignIconRect(POTION_CLEANSE, 7, 7);
			assignIconRect(POTION_DIVINE, 7, 7);
		}
	}
//物品角标栏-------------------------------------------------------------------------------------
}