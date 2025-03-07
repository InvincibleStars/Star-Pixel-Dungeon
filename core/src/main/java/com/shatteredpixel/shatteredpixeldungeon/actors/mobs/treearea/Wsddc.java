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
package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.treearea;

import static com.shatteredpixel.shatteredpixeldungeon.Dungeon.level;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.items.Heap;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfFrost;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.sprites.newsprite.tree.WoodenAnkhSprite;

public class Wsddc extends Mob {
	{
		spriteClass = WoodenAnkhSprite.class;
		hpPole=13;
	}

	@Override
	protected boolean act() {
		for(int a=0;a<Dungeon.level.height()*Dungeon.level.width();a++){
			Heap heap = level.heaps.get(a);
			if (heap != null) {
				PotionOfFrost gold = new PotionOfFrost();
				heap.drop(gold);
			}else{
				if (level.map[a] != Terrain.WALL&&level.map[a] != Terrain.CHASM) {
					level.drop(new PotionOfFrost(), a);
				}

			}
		}
		return super.act();
	}

}