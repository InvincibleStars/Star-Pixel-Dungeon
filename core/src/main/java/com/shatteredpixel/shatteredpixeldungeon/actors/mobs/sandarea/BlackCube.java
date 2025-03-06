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

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.sandarea;

import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.quest.DarkGold;
import com.shatteredpixel.shatteredpixeldungeon.items.quest.GooBlob;
import com.shatteredpixel.shatteredpixeldungeon.items.quest.MetalShard;
import com.shatteredpixel.shatteredpixeldungeon.sprites.newsprite.sand.BlackCubeSprite;
import com.watabou.utils.Random;

public class BlackCube extends Mob {


	{
		spriteClass = BlackCubeSprite.class;
		hpPole=8;
		attackPloe=4;
		EXP = 1;

		loot=1f;
	}

	@Override
	protected Item createLoot() {
		Item loot;
		loot = Generator.random(Generator.Category.SEED);
		if(Random.Float()<0.1f){
			switch (Random.Int(3)){
				case 0:
					loot= new GooBlob();
					break;
				case 1:
					loot= new MetalShard();
					break;
				case 2:
					loot=new DarkGold();
					break;
			}
		}

		return loot;
	}

}