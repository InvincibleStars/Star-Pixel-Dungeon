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

import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.newsprite.sand.CubeSprite;
import com.watabou.utils.Random;

public class Cube extends Mob {

	//生命低于一半时降低自身伤害

	{
		spriteClass = CubeSprite.class;
		HP=HT=12;
		EXP=2;
		maxLvl=7;
	}

	@Override
	public int damageRoll() {
		int max = 6;
		if(HP*2<HT){
			max-=2;
		}
		return Random.NormalIntRange(3, max);
	}

	@Override
	public boolean act() {
		if(HP*2<HT){
			//不稳定状态（伤害降低）
			sprite.showStatus(CharSprite.NEGATIVE, Messages.get(this, "enraged"));
		}
		return super.act();
	}
}