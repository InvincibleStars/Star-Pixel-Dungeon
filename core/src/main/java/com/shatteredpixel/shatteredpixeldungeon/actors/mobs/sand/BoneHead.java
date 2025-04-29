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

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.sand;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.levels.features.Chasm;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.BoneSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.newsprite.sand.BoneHeadSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.newsprite.sand.RedCrystalSprite;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class BoneHead extends Mob {
	
	{
		spriteClass = BoneHeadSprite.class;
		HP=HT=20;

		EXP=4;
		maxLvl=7;

		lootChance = 0.06f;
	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange(3, 11);
	}

	@Override
	public int attackProc( Char enemy, int damage ) {
		damage = super.attackProc( enemy, damage );
		if (Random.Int( 4 ) == 0) {
			GLog.n(Messages.get(this,"talk"));
			damage++;
		}
		return damage;
	}

	@Override
	protected Item createLoot() {
		Item loot;
		if(Dungeon.LimitedDrops.LIFESAND_LOOT.count!=0){
			loot = Generator.random(Generator.Category.RING);
			Dungeon.LimitedDrops.LIFESAND_LOOT.count++;
		}else{
			loot=null;
		}
		return loot;
	}

	@Override
	public void die( Object cause ) {

		super.die( cause );

		if (cause == Chasm.class) return;

		boolean heroKilled = false;
		for (int i = 0; i < PathFinder.NEIGHBOURS8.length; i++) {
			Char ch = findChar( pos + PathFinder.NEIGHBOURS8[i] );
			if (ch != null && ch.isAlive()) {
				int damage = Random.NormalIntRange(3, 6);
				ch.damage( damage, this );
				if (ch == Dungeon.hero && !ch.isAlive()) {
					heroKilled = true;
				}
			}
		}

		if (Dungeon.level.heroFOV[pos]) {
			Sample.INSTANCE.play( Assets.Sounds.BONES );
		}

		if (heroKilled) {
			Dungeon.fail( getClass() );
			GLog.n( Messages.get(this, "explo_kill") );
		}
	}

}