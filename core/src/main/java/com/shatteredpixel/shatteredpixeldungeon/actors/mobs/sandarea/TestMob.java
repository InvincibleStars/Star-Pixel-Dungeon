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

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.sprites.newsprite.sand.BlackWormSprite;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class TestMob extends Mob {


	{
		spriteClass = BlackWormSprite.class;
		HP = HT =  20;

	}

	boolean skill = true;

	@Override
	protected boolean canAttack( Char enemy ) {
		Ballistica attack = new Ballistica(this.pos, enemy.pos, Ballistica.PROJECTILE);
		if(skill==true && Dungeon.level.trueDistance(this.pos,enemy.pos)>=1) {
			(this.sprite).attack(enemy.pos);
			skill=false;
			Sample.INSTANCE.play( Assets.Sounds.ATK_SPIRITBOW, 1, Random.Float(0.87f, 1.15f) );
			enemy.damage( Random.NormalIntRange( 3, 0 ), new SandCrab.DeathGaze() );
			return attack.collisionPos == null;
		}else if(Dungeon.level.trueDistance(this.pos,enemy.pos)<2) {
				return attack.collisionPos == enemy.pos;
		}else {
				return attack.collisionPos == null;
		}
	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 0, 0 );
	}

}