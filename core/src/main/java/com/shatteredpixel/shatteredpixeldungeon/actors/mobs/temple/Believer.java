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

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.temple;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Freezing;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.AnkhInvulnerability;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Blindness;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Burning;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Chill;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.newbuff.Adrenaline2;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.effects.Lightning;
import com.shatteredpixel.shatteredpixeldungeon.effects.Splash;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfFrost;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfLiquidFlame;
import com.shatteredpixel.shatteredpixeldungeon.items.quest.Embers;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRecharging;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfTransmutation;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.CursedWand;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Shocking;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.BelieverSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ElementalSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ThiefSprite;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

import java.util.ArrayList;

public abstract class Believer extends Mob {
	{
		spriteClass = ThiefSprite.class;
		hpPole=12;
	}

	protected int Cooldown = Random.NormalIntRange( 5, 10 );

	@Override
	protected boolean act() {
		skill();
		Cooldown--;
		return super.act();
	}

	//抽象方法需要在下面下面实现
	protected abstract void skill();

	public static class AnkhVariant extends Believer {
		
		{
			spriteClass= BelieverSprite.Ankh.class;
			hpPole=8;
		}

		public int skill = 2;

		public void skill(){
			if (Cooldown<=0) {
				Buff.affect(this, AnkhInvulnerability.class, 3f);
				Cooldown=Random.Int(8,10);
			}
		}

		@Override
		public void die(Object cause) {
			//super.die(cause);
			if(skill!=0){
				this.HT= (int) (this.HT*1.2f);
				HP=HT;
				attackPloe+=1;
				skill-=1;
				Buff.affect(this, AnkhInvulnerability.class, 3f);
				Cooldown=Random.Int(5,10);
			}else{
				super.die(true);
			}

		}


	}
	
	public static class FunVariant extends Believer {
		
		{
			//spriteClass = ElementalSprite.Shock.class;
			spriteClass= BelieverSprite.Fun.class;

			hpPole=12;
			attackPloe=Random.Int(4,11);


		}


		@Override
		protected void skill() {
			if(cooldown()<=0){
				Buff.prolong(this, Adrenaline2.class,200f);
			}
		}
	}
	
	public static class NormalVariant extends Believer {
		
		{
			//spriteClass = ElementalSprite.Chaos.class;
			spriteClass= BelieverSprite.Normal.class;

			hpPole=12;

		}

		@Override
		protected void skill() {

		}
	}
	
	public static Class<? extends Believer> random(){
		/*
		if (Random.Int( 50 ) == 0){
			return ChaosElemental.class;
		}
		 */
		
		float roll = Random.Float();
		if (roll < 0.4f){
			return FunVariant.class;
		} else if (roll < 0.8f){
			return AnkhVariant.class;
		} else {
			return NormalVariant.class;
		}
	}
}
