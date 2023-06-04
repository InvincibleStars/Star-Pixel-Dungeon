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

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.newmob.treearea;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.AnkhInvulnerability;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Bleeding;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.BuffWait;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Paralysis;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Recharging;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Vulnerable2;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.items.food.MysteryMeat;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.AlbinoSprite;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.utils.Random;

public class PerfectReplicator extends Mob {

	{
		spriteClass = AlbinoSprite.class;

		HP = HT = 1;
		EXP = 2;

		loot = new MysteryMeat();
		lootChance = 1f;
	}

	private boolean invulnWarned = false;

	public boolean cmaa =false;

	@Override
	public int attackProc(Char enemy, int damage) {
		damage = super.attackProc(enemy, damage);
		if (Random.Int(2) == 0) {
			Buff.affect(enemy, Bleeding.class).set(damage);
		}

		return damage;
	}

	@Override
	public boolean isInvulnerable(Class effect) {
		if (cmaa==true){
			invulnWarned = true;
			GLog.w(Messages.get(this, "charging_hint"));
		}
		return invulnWarned;
	}

	@Override
	public void damage(int dmg, Object src) {
		//int preHP = HP;
		super.damage(dmg, src);
		if (isInvulnerable(src.getClass())) {
			return;
		}
	}




	@Override
	public void die(Object cause) {
		super.die(cause);
		//实例一个原有单位
		cause =new PerfectReplicator();
		//位置
		((PerfectReplicator)cause).pos =pos;
		//添加
		GameScene.add(((Mob)(cause)));
		//状态
		//Buff.prolong(((Mob)(cause)), AnkhInvulnerability.class, BuffWait.T10);
		Buff.prolong(((Mob)(cause)), Vulnerable2.class, BuffWait.T10);
		Buff.prolong(((Mob)(cause)), Paralysis.class, BuffWait.T10);

		cmaa=true;
		invulnWarned = true;


	}
}

