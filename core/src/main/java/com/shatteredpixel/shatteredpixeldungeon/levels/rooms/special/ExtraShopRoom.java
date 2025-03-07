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

import static com.shatteredpixel.shatteredpixeldungeon.Dungeon.depth;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Belongings;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.ExtraShopKeeper;
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
import com.shatteredpixel.shatteredpixeldungeon.items.bags.Bag;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.MagicalHolster;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.PotionBandolier;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.ScrollHolder;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.VelvetPouch;
import com.shatteredpixel.shatteredpixeldungeon.items.bombs.Bomb;
import com.shatteredpixel.shatteredpixeldungeon.items.food.SmallRation;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfHealing;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfIdentify;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfMagicMapping;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRemoveCurse;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.Alchemize;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.StoneOfAugmentation;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.darts.TippedDart;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.levels.painters.Painter;
import com.watabou.utils.Point;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.HashMap;

public class ExtraShopRoom extends SpecialRoom {
	private ArrayList<Item> itemsToSpawn;
	@Override
	public int minWidth() {
		return 5;
	}
	@Override
	public int minHeight() {
		return 5;
	}
	@Override
	public int maxWidth() {
		return 5;
	}
	@Override
	public int maxHeight() {
		return 5;
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
		Mob extraShopKeeper = new ExtraShopKeeper();
		extraShopKeeper.pos = pos;
		level.mobs.add( extraShopKeeper );
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

		for(int i =0; i<=Random.NormalIntRange(1,3);i++){
			if(Random.Int(2)==0){
				itemsToSpawn.add(Generator.randomUsingDefaults( Generator.Category.POTION ));
			}else {
				itemsToSpawn.add(Generator.randomUsingDefaults( Generator.Category.SCROLL ));
			}
		}

		if(depth>=10 && Random.Float()<0.6f)	itemsToSpawn.add(new Torch());
		if(Random.Float()<=0.6f)	itemsToSpawn.add( TippedDart.randomTipped(1) );
		if(Random.Float()<=0.6f)	itemsToSpawn.add( new Bomb());
		if(Random.Float()<=0.6f)	itemsToSpawn.add( new Honeypot());



		if (itemsToSpawn.size() > 8)
			throw new RuntimeException("Shop attempted to carry more than 8 items!");
		Random.pushGenerator(Random.Long());
		Random.shuffle(itemsToSpawn);
		Random.popGenerator();
		return itemsToSpawn;
	}
}