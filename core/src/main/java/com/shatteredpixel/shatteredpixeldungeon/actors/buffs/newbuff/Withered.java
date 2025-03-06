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

package com.shatteredpixel.shatteredpixeldungeon.actors.buffs.newbuff;

import static com.shatteredpixel.shatteredpixeldungeon.Dungeon.depth;
import static com.shatteredpixel.shatteredpixeldungeon.Dungeon.level;

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.newpotion.PotionOfDamage;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.levels.painters.Painter;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.watabou.utils.Random;

public class Withered extends Buff {

	//灾荒
	
	{
		type = buffType.NEGATIVE;
		announced = true;
	}

	@Override
	public void detach() {
		super.detach();
		//二区才会出现
		if(depth>5&&depth<=10) {
			int b;
			//
			if (level.map[target.pos] == Terrain.DEADEMPTY) {
				b = 20;
			} else {
				b = 10;
			}
			for (int a = 0; a < b; a++) {
				int c = com.watabou.utils.Random.Int(level.width(), level.height() * (level.width() - 1));

				if (level.map[c] == Terrain.EMPTY) {
					Painter.set(level, c, Terrain.DEADEMPTY);
					GameScene.updateMap(c);
				}
			}

			if(Random.Float()<=2f){
				level.drop(new PotionOfDamage(),target.pos);
			}
		}
	}

	@Override
	public void fx(boolean on) {
		if (on) target.sprite.add( CharSprite.State.WITHERED );
		else if (target.invisible == 0) target.sprite.remove( CharSprite.State.WITHERED );
	}
	
	@Override
	public int icon() {
		return BuffIndicator.CORRUPT;
	}
	
	@Override
	public String toString() {
		return Messages.get(this, "name");
	}
	
	@Override
	public String desc() {
		return Messages.get(this, "desc");
	}
}
