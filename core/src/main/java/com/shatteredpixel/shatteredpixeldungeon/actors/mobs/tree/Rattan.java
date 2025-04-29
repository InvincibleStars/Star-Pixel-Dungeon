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

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.tree;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.areaitem.MobLoot;
import com.shatteredpixel.shatteredpixeldungeon.items.areaitem.Silme;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.tier5.Glaive;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.newsprite.tree.LifePlantSprite;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.Camera;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class Rattan extends Rat {

	{
		spriteClass = LifePlantSprite.class;
		HP=HT=20;

		EXP=4;
		maxLvl=15;
		loot = Generator.Category.SEED;
		lootChance = 0.125f;
	}

	private int cooldown = 10;

	@Override
	protected boolean act() {
		if(isAlive()){
			cooldown--;
		}
		if(cooldown<=0 && enemy!=null){
			if(Dungeon.level.distance(this.pos,enemy.pos)<=5){
				hit();
			}
		}
		return super.act();
	}

	private void hit(){
		cooldown=Random.Int(5,12);
		enemy.damage(damageRoll(),this);
		Camera.main.shake( 3, 0.2f );
		GLog.n(Messages.get( this,"hit"));
		Sample.INSTANCE.play( Assets.Sounds.HIT );
	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange(1, 11);
	}

}