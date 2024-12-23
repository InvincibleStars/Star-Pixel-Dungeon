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
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Cripple;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.items.Gold;
import com.shatteredpixel.shatteredpixeldungeon.items.bossloot.BossLoot;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.GnollSprite;
import com.watabou.utils.Random;

public class Gnoll extends Mob {
	
	{

		spriteClass = GnollSprite.class;

		
		HT = 85 + + Random.Int(-14,14) + BossLoot.infection*2;
		EXP = 5;
		maxLvl = 11;
		
		loot = Gold.class;
		lootChance = 1f;
	}

	public boolean armor = true;

	public boolean act(){
		GameScene.updateMap(pos);
		return super.act();

	}

	//检测AttackPower
	@Override
	protected void spend( float time ) {
		if(ATTACKPOWER>=2f){
			ATTACKPOWER=2f;
		}
		super.spend( time );
	}

	//AttackPower来源
	@Override
	public void move( int step, boolean travelling) {
		if (travelling&&ATTACKPOWER<2){
			ATTACKPOWER += 0.2f;
		}
		super.move( step, travelling);
	}
	//在攻击效果里重置AttackPower
	@Override
	public int attackProc(Char enemy, int damage) {
		damage = super.attackProc( enemy, damage );
		ATTACKPOWER=1f;
		if (Random.Int(4) == 0) {
			Buff.affect(enemy, Cripple.class, Random.Float(1f,4f) );
			HP = Math.min(HT, HP + 1);
		}
		return damage;
	}



	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 6, 23 )+ BossLoot.infection;
	}

	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, 17);
	}


}