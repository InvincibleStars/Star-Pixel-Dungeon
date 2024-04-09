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

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.BuffWait;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Vulnerable;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Monk;
import com.shatteredpixel.shatteredpixeldungeon.items.Gold;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.DM100Sprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.GnollSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.MimicSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.newsprite.tree.LifePlantSprite;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class Gnoll extends Mob {
	
	{

		spriteClass = LifePlantSprite.class;

		
		HP = HT = 15+(Random.Int(4));
		defenseSkill = 7;
		
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

//	public void activate(){
//		if(armor==false){
//			((MimicSprite)sprite).activate();
//
//		}
//	}

	//protected float AttackPower = 1;

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
			ATTACKPOWER += 0.15f;
		}
		super.move( step, travelling);
	}
	//在攻击效果里重置AttackPower
	@Override
	public int attackProc(Char enemy, int damage) {
		damage = super.attackProc( enemy, damage );
		ATTACKPOWER=1f;
		return damage;
	}



	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 2, (int)(ATTACKPOWER*6) );
	}
	
	@Override
	public int attackSkill( Char target ) {
		return 16;
	}
	
	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, 2);
	}


}