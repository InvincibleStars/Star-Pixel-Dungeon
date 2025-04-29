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

package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfChallenge;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.utils.Bundle;

import java.util.ArrayList;

//public class ThisActive extends Buff implements Hero.Doom {

public class Reactivity extends Buff {



	//结算间隔（每STEP回合结算一次）
	private static final float STEP	= 10f;


	//活性等级
	public static final float SPEED		= 0f;
	public static final float POWER		= 300f;
	public static final float NONE		= 600f;
	public static final float LEVEL1	= 900f;
	public static final float LEVEL2	= 1200f;
	public static final float LEVEL3	= 1500f;

	//默认初始数量为600f
	private float level = 600f;


	//private float partialDamage;

	//定义LEVEL字符串存level
	private static final String LEVEL			= "level";


	//private static final String PARTIALDAMAGE 	= "partialDamage";

	//保存的数组
	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle(bundle);
		bundle.put( LEVEL, level );


		//bundle.put( PARTIALDAMAGE, partialDamage );
	}

	//读取的数组
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		level = bundle.getFloat( LEVEL );


		//partialDamage = bundle.getFloat(PARTIALDAMAGE);
	}

	@Override
	public boolean act() {
		//什么时候停止扣除活性
		//背水一战/饱腹/魔法免疫/0层
		if (Dungeon.level.locked
				|| target.buff(WellFed.class) != null
				|| target.buff(ScrollOfChallenge.ChallengeArena.class) != null
				|| Dungeon.depth==0){
			spend(STEP);
			return true;
		}
		//活性范围判断
		if (target.isAlive() && target instanceof Hero) {

			Hero hero = (Hero)target;
			//判断重度饥饿
			if (isStarving()) {


				/*
				//伤害指定域（移除）
				//partialDamage += STEP * target.HT/1000f;

				if (partialDamage > 1){
					target.damage( (int)partialDamage, this);
					partialDamage -= (int)partialDamage;
				}
				
			} else {
				 */


				//状态等级(NONE,LEVLE1-5)
				float newLevel = level + STEP;


				//进入LEVEL3的提示
				if (newLevel >= LEVEL3) {

					//提示消息
					GLog.n( Messages.get(this, "onstarving") );

					//GLog.n( Messages.get(this, "onstarving") );

					//回复阀
					//hero.resting = false;

					//伤害阀
					//hero.damage( 1, this );

					hero.interrupt();

				//进入LEVEL2时候的提示（只提示一次）
				} else if (newLevel >= LEVEL2 && level < LEVEL2) {

					GLog.w( Messages.get(this, "onhungry") );
					/*
					//指南书页
					if (!Document.ADVENTURERS_GUIDE.isPageRead(Document.GUIDE_FOOD)){
						GLog.p(Messages.get(Guidebook.class, "hint"));
						GameScene.flashForDocument(Document.GUIDE_FOOD);
					}
					 */

				//进入LEVEL1时候的提示
				}else if (newLevel >= LEVEL1 && level < LEVEL1) {

					GLog.w( Messages.get(this, "onhungry") );
				}
				level = newLevel;

			}
			//在暗影庇护下STEP为标准的10回合，如果不处于这个状态则增加50%的回合
			spend( target.buff( Shadows.class ) == null ? STEP : STEP * 1.5f );

		} else {

			diactivate();

		}

		return true;
	}


	/*
	//被诅咒的号角
	public void satisfy( float energy ) {

		Artifact.ArtifactBuff buff = target.buff( HornOfPlenty.hornRecharge.class );
		if (buff != null && buff.isCursed()){
			energy *= 0.67f;
			GLog.n( Messages.get(this, "cursedhorn") );
		}

		affectHunger( energy, false );
	}

	 */

	public void affectHunger(float energy ){
		affectHunger( energy, false );
	}

	public void affectHunger(float energy, boolean overrideLimits ) {
		ArrayList<Item> items = new ArrayList<>();
		//饱腹
		/*
		if (energy < 0 && target.buff(WellFed.class) != null){
			target.buff(WellFed.class).left += energy;
			BuffIndicator.refreshHero();

		return;
		}

		 */

		//最大结算上限(buff最大值)
		//开始扣除
		level -= energy;
		if (level < 0 && !overrideLimits) {
			level = 0;
		//数据达到最高值时不再增长
		} else if (level > LEVEL3) {
			float excess = level - LEVEL3;
			//强制最大值为LEVEL3数值（900f）
			level = LEVEL3;
			//伤害数值（去除）
			//partialDamage += excess * (target.HT/1000f);
		}
		BuffIndicator.refreshHero();
	}


	//状态强度
	public boolean isStarving() {
		return level >= LEVEL1;
	}

	/*
	丰饶之角
	public int hunger() {
		return (int)Math.ceil(level);
	}

	 */


	//图标结算
	@Override
	public int icon() {
		if (level < LEVEL3) {
			return BuffIndicator.NONE;
		} else if (level < LEVEL2) {
			return BuffIndicator.HUNGER;
		} else if (level < LEVEL1) {
			return BuffIndicator.HUNGER;
		} else {
			return BuffIndicator.STARVATION;
		}
	}

	//状态标题
	@Override
	public String toString() {
		if (level < LEVEL3 ) {
			if (level < LEVEL2) {
				if (level < LEVEL1) {
				return Messages.get(this, "health");
				}
			return Messages.get(this, "level1");
			}
		return Messages.get(this, "level2");
	} else {
	return Messages.get(this, "level3");
	}
	}

	//状态信息
	@Override
	public String desc() {
		String result;
		if (level < LEVEL3) {
			if (level < LEVEL2) {
				if (level < LEVEL1) {
				result = Messages.get(this, "health_desc");
				}
			result = Messages.get(this, "level1_desc");
			}
		result = Messages.get(this, "level2_desc");
				} else {
				result = Messages.get(this, "level3_desc");
		}
		result += Messages.get(this, "desc");

		return result;
	}

	//角色因为这个状态死去
	/*
	@Override
	public void onDeath() {

		Badges.validateDeathFromHunger();

		Dungeon.fail( getClass() );
		GLog.n( Messages.get(this, "ondeath") );
	}
	 */

}
