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

public class ShopRoom extends SpecialRoom {
	private ArrayList<Item> itemsToSpawn;
	@Override
	public int minWidth() {
		return 7;
	}
	@Override
	public int minHeight() {
		return 7;
	}

	@Override
	public int maxWidth() {
		return 7;
	}
	@Override
	public int maxHeight() {
		return 7;
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

		switch (depth) {
		default:
			itemsToSpawn.add( new ClothArmor().identify() );
			break;
		case 6:
			itemsToSpawn.add( new LeatherArmor().identify() );
			break;
		case 11:
			itemsToSpawn.add( new MailArmor().identify() );
			break;
		case 16:
			itemsToSpawn.add( new ScaleArmor().identify() );
			break;
		case 20:
			itemsToSpawn.add( new PlateArmor().identify() );
			break;
		}

		//货物添加
		if(depth>15){
			itemsToSpawn.add(new Torch());
		}

		//药水设定（必定有一瓶治疗）
		for(int i =0; i<=Random.NormalIntRange(1,2);i++){
			Generator.randomUsingDefaults( Generator.Category.POTION );
		}
		itemsToSpawn.add( new PotionOfHealing() );

		//卷轴设定（必定有一张驱散或鉴定+探地）
		Generator.randomUsingDefaults( Generator.Category.SCROLL );
		if(Random.Int(1)==0) {
			itemsToSpawn.add(new ScrollOfIdentify());
		}else {
			itemsToSpawn.add(new ScrollOfRemoveCurse());
		}
		itemsToSpawn.add( new ScrollOfMagicMapping() );

		//生成近战和远程武器
		itemsToSpawn.add( Generator.random(Generator.wepTiers[Math.round(depth/5)+1]).identify());
		itemsToSpawn.add( Generator.random(Generator.misTiers[Math.round(depth/5)+1]).quantity(Random.Int(1,6)).identify());

		//其他生成
		if(Random.Int(1)==0) {
			itemsToSpawn.add(Generator.randomUsingDefaults(Generator.Category.WAND));
		}else{
			itemsToSpawn.add(Generator.randomUsingDefaults(Generator.Category.RING));
		}


		itemsToSpawn.add( TippedDart.randomTipped(2) );
		itemsToSpawn.add( new Alchemize().quantity(2));
		itemsToSpawn.add(ChooseBag(Dungeon.hero.belongings));


		itemsToSpawn.add( new SmallRation() );
		itemsToSpawn.add( new SmallRation() );
		itemsToSpawn.add( new SmallRation() );
		itemsToSpawn.add( new Bomb() );
		itemsToSpawn.add( new Bomb() );
		itemsToSpawn.add( new Honeypot() );
		itemsToSpawn.add( new StoneOfAugmentation() );
		TimekeepersHourglass hourglass = Dungeon.hero.belongings.getItem(TimekeepersHourglass.class);
		//固定包裹
		if (hourglass != null && hourglass.isIdentified() && !hourglass.cursed){
			int bags = 0;
			switch (depth) {
				case 6:
					bags = (int)Math.ceil(( 5-hourglass.sandBags) * 0.20f ); break;
				case 11:
					bags = (int)Math.ceil(( 5-hourglass.sandBags) * 0.25f ); break;
				case 16:
					bags = (int)Math.ceil(( 5-hourglass.sandBags) * 0.50f ); break;
				case 20: case 21:
					bags = (int)Math.ceil(( 5-hourglass.sandBags) * 0.80f ); break;
			}
			for(int i = 1; i <= bags; i++){
				itemsToSpawn.add( new TimekeepersHourglass.sandBag());
				hourglass.sandBags ++;
			}
		}


		if (itemsToSpawn.size() > 24)
			throw new RuntimeException("Shop attempted to carry more than 24 items!");
		Random.pushGenerator(Random.Long());
		Random.shuffle(itemsToSpawn);
		Random.popGenerator();
		return itemsToSpawn;
	}
	protected static Bag ChooseBag(Belongings pack){
		//generate a hashmap of all valid bags.
		HashMap<Bag, Integer> bags = new HashMap<>();
		if (!Dungeon.LimitedDrops.VELVET_POUCH.dropped()) bags.put(new VelvetPouch(), 0);
		if (!Dungeon.LimitedDrops.SCROLL_HOLDER.dropped()) bags.put(new ScrollHolder(), 0);
		if (!Dungeon.LimitedDrops.POTION_BANDOLIER.dropped()) bags.put(new PotionBandolier(), 0);
		if (!Dungeon.LimitedDrops.MAGICAL_HOLSTER.dropped()) bags.put(new MagicalHolster(), 0);
		if (bags.isEmpty()) return null;
		//count up items in the main bag
		for (Item item : pack.backpack.items) {
			for (Bag bag : bags.keySet()){
				if (bag.canHold(item)){
					bags.put(bag, bags.get(bag)+1);
				}
			}
		}
		//find which bag will result in most inventory savings, drop that.
		Bag bestBag = null;
		for (Bag bag : bags.keySet()){
			if (bestBag == null){
				bestBag = bag;
			} else if (bags.get(bag) > bags.get(bestBag)){
				bestBag = bag;
			}
		}
		if (bestBag instanceof VelvetPouch){
			Dungeon.LimitedDrops.VELVET_POUCH.drop();
		} else if (bestBag instanceof ScrollHolder){
			Dungeon.LimitedDrops.SCROLL_HOLDER.drop();
		} else if (bestBag instanceof PotionBandolier){
			Dungeon.LimitedDrops.POTION_BANDOLIER.drop();
		} else if (bestBag instanceof MagicalHolster){
			Dungeon.LimitedDrops.MAGICAL_HOLSTER.drop();
		}
		return bestBag;
	}
}