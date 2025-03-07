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

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.newboss;

import static com.shatteredpixel.shatteredpixeldungeon.Dungeon.hero;
import static com.shatteredpixel.shatteredpixeldungeon.Dungeon.level;
import static com.shatteredpixel.shatteredpixeldungeon.actors.Char.Alignment.ALLY2;
import static com.shatteredpixel.shatteredpixeldungeon.actors.Char.Alignment.ENEMY;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.mobtag.InvisibleHP;
import com.shatteredpixel.shatteredpixeldungeon.sprites.WraithSprite;
import com.watabou.utils.Random;

public class BossCall extends InvisibleHP {

	{
		spriteClass = WraithSprite.class;
		HP = HT = 1;
		properties.add(Property.NOHP);
		attackskill = 50;
		defenseSkill = 0;
		baseSpeed = 1f;
		flying = true;

	}

	@Override
	public boolean act() {
				if (Dungeon.level.distance(this.pos, hero.pos) >= 5) {
					alignment = ALLY2;
				}else{
					alignment = ENEMY;
				}
				return super.act();}
	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 0, 0 );
	}
	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, 0);
	}
	public int RE = 80;
	@Override
	public void die ( Object cause ) {
		super.die(cause);
		for(Mob boss:level.mobs){
			if(boss instanceof Level1Boss){if(boss.HP<=5){
				boss.HP=Math.min(boss.HP,boss.HP-5);
				if(boss.BOSSCOUNT<=1){
					boss.BOSSCOUNT=0;
				}else{
					boss.BOSSCOUNT-=1;
				}
			}else{
				if(boss.BOSSCOUNT<=1){
					boss.BOSSCOUNT=0;
				}else{
					boss.BOSSCOUNT-=1;
				}
			}}
		}
	}
}