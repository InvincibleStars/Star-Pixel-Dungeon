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

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.EnhancedRings;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.KindofMisc;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.watabou.utils.Bundle;

//import java.util.Random;
import com.watabou.utils.Random;

public class RingStar extends KindofMisc {

	{
		identify();
	}
	
	protected Buff buff;
	
	private String gem;

	public RingStar() {
		super();
		reset();
	}
	
	public void activate( Char ch ) {
		buff = buff();
		buff.attachTo( ch );
	}

	@Override
	public boolean doUnequip( Hero hero, boolean collect, boolean single ) {
		if (super.doUnequip( hero, collect, single )) {
			hero.remove( buff );
			buff = null;
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isKnown() {
		return true;}
	
	@Override
	public String info(){
		
		String desc = isKnown() ? super.desc() : Messages.get(this, "unknown_desc");
		
		if (cursed && isEquipped( Dungeon.hero )) {
			desc += "\n\n" + Messages.get(RingStar.class, "cursed_worn");
			
		} else if (cursed && cursedKnown) {
			desc += "\n\n" + Messages.get(RingStar.class, "curse_known");
			
		} else if (!isIdentified() && cursedKnown){
			desc += "\n\n" + Messages.get(RingStar.class, "not_cursed");
			
		}
		
		if (isKnown()) {
			desc += "\n\n" + statsInfo();
		}
		
		return desc;
	}
	
	protected String statsInfo(){
		return "";
	}



	//初始buff不存在
	protected RingBuff buff() {return null;}



//-----------------------------------随机化效果和属性--------------------------------------------------



//----------------------------------存储和读取--------------------------------------------------------


	@Override
	public int buffedLvl() {
		int lvl = super.buffedLvl();
		if (Dungeon.hero.buff(EnhancedRings.class) != null){
			lvl++;
		}
		return lvl;
	}

	//同时显示两个
	//Messages.get(xxx, "xxx", soloBonus(),xxx, soloBuffedBonus()) xxx)));

	public static int getBonus(Char target, Class<?extends RingBuff> type){
		int bonus = 0;
		for (RingBuff buff : target.buffs(type)) {
			bonus += buff.level();
		}
		return bonus;
	}

	//只显示一个函数
	//Messages.get(xxx, "xxx", new xxx("#.##").format(Math.pow(0.775f, soloBuffedBonus())));
	public static int getBuffedBonus(Char target, Class<?extends RingBuff> type){
		int bonus = 0;
		for (RingBuff buff : target.buffs(type)) {
			bonus += buff.buffedLvl();
		}
		return bonus;
	}


	
	public int soloBonus(){
		return RingStar.this.level()+1;
	}

	public int soloBuffedBonus(){
		return RingStar.this.buffedLvl()+1;

	}

	public class RingBuff extends Buff {

		
		@Override
		public boolean act() {

			spend( TICK );
			return true;
		}

		public int level(){
			return RingStar.this.soloBonus();
		}

		public int buffedLvl(){
			return RingStar.this.soloBuffedBonus();
		}

	}
}
