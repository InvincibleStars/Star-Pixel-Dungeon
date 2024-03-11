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

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Blob;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.StormCloud;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.ToxicGas;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.newsprite.sand.SandWormSprite;

public class Shrub extends Mob {

	{
		spriteClass = SandWormSprite.class;

		HP = HT = 1;
		defenseSkill = 0;

		EXP = 2;

		loot = Generator.Category.SEED;
		lootChance = 0.1f;

		state = PASSIVE;

	}


	@Override
	public int defenseProc(Char enemy, int damage) {
		GameScene.add(Blob.seed(pos, 800, StormCloud.class));

		return super.defenseProc(enemy, 15);
	}


	{ immunities.add( ToxicGas.class ); }

	@Override
	public int attackSkill( Char target ) {
		return 9;
	}

	//private class Waiting extends Wandering{}
}
