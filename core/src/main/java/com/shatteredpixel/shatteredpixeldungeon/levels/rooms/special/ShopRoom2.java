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

package com.shatteredpixel.shatteredpixeldungeon.levels.rooms.special;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.NPC;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.Shopkeeper;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Heap;
import com.shatteredpixel.shatteredpixeldungeon.items.Honeypot;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.Torch;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.ClothArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.LeatherArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.MailArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.PlateArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.ScaleArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.TimekeepersHourglass;
import com.shatteredpixel.shatteredpixeldungeon.items.bombs.Bomb;
import com.shatteredpixel.shatteredpixeldungeon.items.food.SmallRation;
import com.shatteredpixel.shatteredpixeldungeon.items.newitem.ammo.Ammo;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfHealing;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfIdentify;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfMagicMapping;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRemoveCurse;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.Alchemize;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.StoneOfAugmentation;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Gun;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.darts.TippedDart;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.levels.painters.Painter;
import com.watabou.utils.Point;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class ShopRoom2 extends SpecialRoom {

	private ArrayList<Item> itemsToSpawn;
	
	@Override
	public int minWidth() {
		return Math.max(9, (int)(Math.sqrt(itemCount())+3));
	}
	
	@Override
	public int minHeight() {
		return Math.max(9, (int)(Math.sqrt(itemCount())+3));
	}

	public int itemCount(){
		if (itemsToSpawn == null) itemsToSpawn = generateItems();
		return itemsToSpawn.size();
	}
	
	public void paint( Level level ) {
		
		Painter.fill( level, this, Terrain.WALL );
		Painter.fill( level, this, 1, Terrain.EMPTY_SP );

		placeShopkeeper( level );

		placeItems( level );
		
		for (Door door : connected.values()) {
			door.set( Door.Type.REGULAR );
		}

	}

	protected void placeShopkeeper( Level level ) {

		int pos = level.pointToCell(center());

		Mob shopkeeper = new Shopkeeper();
		shopkeeper.pos = pos;
		level.mobs.add( shopkeeper );

	}

	protected void placeItems( Level level ){

		if (itemsToSpawn == null){
			itemsToSpawn = generateItems();
		}

		Point itemPlacement = new Point(entrance());
		if (itemPlacement.y == top){
			itemPlacement.y++;
		} else if (itemPlacement.y == bottom) {
			itemPlacement.y--;
		} else if (itemPlacement.x == left){
			itemPlacement.x++;
		} else {
			itemPlacement.x--;
		}

		for (Item item : itemsToSpawn) {

			if (itemPlacement.x == left+1 && itemPlacement.y != top+1){
				itemPlacement.y--;
			} else if (itemPlacement.y == top+1 && itemPlacement.x != right-1){
				itemPlacement.x++;
			} else if (itemPlacement.x == right-1 && itemPlacement.y != bottom-1){
				itemPlacement.y++;
			} else {
				itemPlacement.x--;
			}

			int cell = level.pointToCell(itemPlacement);

			if (level.heaps.get( cell ) != null) {
				do {
					cell = level.pointToCell(random());
				} while (level.heaps.get( cell ) != null || level.findMob( cell ) != null);
			}

			level.drop( item, cell ).type = Heap.Type.FOR_SALE;
		}

	}
	
	protected static ArrayList<Item> generateItems() {

		ArrayList<Item> itemsToSpawn = new ArrayList<>();

		MeleeWeapon w;
		switch (Dungeon.depth) {
		default:
			w = (MeleeWeapon) Generator.random(Generator.wepTiers[1]);
			itemsToSpawn.add( Generator.random(Generator.misTiers[1]).quantity(2).identify() );
			itemsToSpawn.add( new ClothArmor().identify() );
			itemsToSpawn.add( new Gun().identify() );
			itemsToSpawn.add( new Torch() );
			itemsToSpawn.add( new Ammo.AmmoBox() );
			if(Random.Float()<=0.3f){
				itemsToSpawn.add( new Ammo.AmmoBox() );
			}
				break;
		case 6:
			w = (MeleeWeapon) Generator.random(Generator.wepTiers[1]);
			itemsToSpawn.add( Generator.random(Generator.misTiers[1]).quantity(2).identify() );
			itemsToSpawn.add( new LeatherArmor().identify() );
			itemsToSpawn.add( Generator.randomUsingDefaults( Generator.Category.ARTIFACT ) );
			itemsToSpawn.add( Generator.randomUsingDefaults( Generator.Category.RING ) );
			itemsToSpawn.add( new Ammo.AmmoBox() );
			if(Random.Float()<=0.3f){
				itemsToSpawn.add( new Ammo.AmmoBox() );
			}
			if(Random.Float()<=0.3f){
				itemsToSpawn.add( new Ammo.AmmoBox() );
			}
			break;
			
		case 11:
			w = (MeleeWeapon) Generator.random(Generator.wepTiers[2]);
			itemsToSpawn.add( Generator.random(Generator.misTiers[2]).quantity(2).identify() );
			itemsToSpawn.add( new MailArmor().identify() );
			itemsToSpawn.add( Generator.randomUsingDefaults( Generator.Category.ARTIFACT ) );
			itemsToSpawn.add( Generator.randomUsingDefaults( Generator.Category.RING ) );
			itemsToSpawn.add( new Ammo.AmmoBox() );
			if(Random.Float()<=0.3f){
				itemsToSpawn.add( new Ammo.AmmoBox() );
			}
			if(Random.Float()<=0.3f){
				itemsToSpawn.add( new Ammo.AmmoBox() );
			}
			break;
			
		case 16:
			w = (MeleeWeapon) Generator.random(Generator.wepTiers[3]);
			itemsToSpawn.add( Generator.random(Generator.misTiers[3]).quantity(2).identify() );
			itemsToSpawn.add( new ScaleArmor().identify() );
			itemsToSpawn.add( Generator.randomUsingDefaults( Generator.Category.ARTIFACT ) );
			itemsToSpawn.add( Generator.randomUsingDefaults( Generator.Category.RING ) );
			itemsToSpawn.add( new Ammo.AmmoBox() );
			if(Random.Float()<=0.3f){
				itemsToSpawn.add( new Ammo.AmmoBox() );
			}
			if(Random.Float()<=0.3f){
				itemsToSpawn.add( new Ammo.AmmoBox() );
			}
			break;

		case 20: case 21:
			w = (MeleeWeapon) Generator.random(Generator.wepTiers[4]);
			itemsToSpawn.add( Generator.random(Generator.misTiers[4]).quantity(2).identify() );
			itemsToSpawn.add( new PlateArmor().identify() );
			itemsToSpawn.add( new Torch() );
			itemsToSpawn.add( new Torch() );
			itemsToSpawn.add( new Torch() );
				itemsToSpawn.add( Generator.randomUsingDefaults( Generator.Category.ARTIFACT ) );
				itemsToSpawn.add( Generator.randomUsingDefaults( Generator.Category.RING ) );
				itemsToSpawn.add( new Ammo.AmmoBox() );
				if(Random.Float()<=0.3f){
					itemsToSpawn.add( new Ammo.AmmoBox() );
				}
				if(Random.Float()<=0.3f){
					itemsToSpawn.add( new Ammo.AmmoBox() );
				}
			break;
		}
		w.enchant(null);
		w.cursed = false;
		w.level(0);
		w.identify();
		itemsToSpawn.add(w);
		
		itemsToSpawn.add( TippedDart.randomTipped(2) );

		itemsToSpawn.add( new Alchemize().quantity(Random.IntRange(3, 4)));




		itemsToSpawn.add( new PotionOfHealing() );

		itemsToSpawn.add( Generator.randomUsingDefaults( Generator.Category.WAND ) );

		itemsToSpawn.add( new ScrollOfIdentify() );
		itemsToSpawn.add( new ScrollOfRemoveCurse() );
		itemsToSpawn.add( new ScrollOfMagicMapping() );

		//生成6件物品（药水或者卷轴）


		for (int i=0; i < (NPC.choose_num==2?8:4); i++)
			itemsToSpawn.add( Random.Int(2) == 0 ?
					Generator.randomUsingDefaults( Generator.Category.POTION ) :
					Generator.randomUsingDefaults( Generator.Category.SCROLL ) );


		itemsToSpawn.add( new SmallRation() );
		itemsToSpawn.add( new SmallRation() );
		
		switch (Random.Int(4)){
			case 0:
				itemsToSpawn.add( new Bomb() );
				break;
			case 1:
			case 2:
				itemsToSpawn.add( new Bomb.DoubleBomb() );
				break;
			case 3:
				itemsToSpawn.add( new Honeypot() );
				break;
		}

		itemsToSpawn.add( new StoneOfAugmentation() );

		TimekeepersHourglass hourglass = Dungeon.hero.belongings.getItem(TimekeepersHourglass.class);
		//hard limit is 63 items + 1 shopkeeper, as shops can't be bigger than 8x8=64 internally
		if (itemsToSpawn.size() > 63)
			throw new RuntimeException("Shop attempted to carry more than 63 items!");

		//use a new generator here to prevent items in shop stock affecting levelgen RNG (e.g. sandbags)
		Random.pushGenerator(Random.Long());
			Random.shuffle(itemsToSpawn);
		Random.popGenerator();

		return itemsToSpawn;
	}

}
