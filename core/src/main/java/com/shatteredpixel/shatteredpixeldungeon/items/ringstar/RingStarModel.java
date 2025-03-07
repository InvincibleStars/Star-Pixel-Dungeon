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

package com.shatteredpixel.shatteredpixeldungeon.items.ringstar;

import static com.shatteredpixel.shatteredpixeldungeon.Dungeon.level;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.EnhancedRings;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Haste;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.KindofMisc;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.levels.painters.Painter;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

import java.text.DecimalFormat;

public class RingStarModel extends RingStar {
//------------------------------------------随机分布定义----------------------------------------------
	public int depth1 = 0;
	public int depth2 = 0;
	public float getDepth1 = 0;
	public float getDepth2 = 0;

	public boolean slot1=false;
	public boolean slot2=false;

	@Override //拾取动作
	public boolean doPickUp(Hero hero, int pos) {
		if (super.doPickUp(hero, pos)){
			if(slot1!=true){
				depth1=Random.Int(1,5);
				getDepth1=Random.Float(0.3f,6f);
				slot1=true;
			}

			if(this.level>=3&&slot2!=true){
				depth2=Random.Int(1,5);
				getDepth2=Random.Float(0.3f,6f);
				slot2=true;
			}
			return true;
		}
		return false;
	}

	@Override
	public Item upgrade() {
		super.upgrade();
		return this;
	}


	//定义一个外部指向自己的指针
	public RingStarModel waibulei =this;



//------------------------------------------BUFF效果定义----------------------------------------------

	@Override
	protected RingBuff buff( ){
		return new RandomStarBuff();
	}

	public class RandomStarBuff extends RingBuff {
		//定义外部类数据通道（将负责被实例化的rsm指向外部类）
		public RingStarModel rsm = waibulei;
	}

	//调用方法
	//pevasion *= RingOfEvasion.evasionMultiplier( this );

	public float outSlot1(Char target){
		return (float) Math.pow( getDepth1, getBuffedBonus(target, RandomStarBuff.class));
	}

	public float outSlot2(Char target){
		return (float) Math.pow( getDepth2, getBuffedBonus(target, RandomStarBuff.class));
	}

	public String statsInfo() {
		return Messages.get(this, "stats",
				new DecimalFormat("#.##").format(100f * (Math.pow(1.25f, soloBuffedBonus()) - 1f)),
				new DecimalFormat("#.##").format(100f * (Math.pow(1.25f, soloBuffedBonus()) - 1f))
		);
	}

//-----------------------------------------数据存储--------------------------------------------------

	private static final String DEPTH1    = "depth1";
	private static final String DEPTH2    = "depth2";

	private static final String GETDEPTH1    = "getdepth1";
	private static final String GETDEPTH2    = "getdepth2";

	private static final String SLOT1    = "slot";
	private static final String SLOT2    = "slot";

	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
		bundle.put( DEPTH1, depth1 );
		bundle.put( DEPTH2, depth2 );
		bundle.put( GETDEPTH1, getDepth1 );
		bundle.put( GETDEPTH2, getDepth2 );
		bundle.put( SLOT1, slot1 );
		bundle.put( SLOT2, slot2 );

	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		depth1 = bundle.getInt( DEPTH1 );
		depth2 = bundle.getInt( DEPTH2);
		getDepth1 = bundle.getFloat( GETDEPTH1 );
		getDepth2 = bundle.getFloat( GETDEPTH2);
		slot1 = bundle.getBoolean( SLOT1 );
		slot2 = bundle.getBoolean( SLOT2 );

	}
}
