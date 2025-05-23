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

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Challenges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.LockedFloor;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Ooze;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.DriedRose;
import com.shatteredpixel.shatteredpixeldungeon.items.keys.SkeletonKey;
import com.shatteredpixel.shatteredpixeldungeon.items.quest.GooBlob;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.GooSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.BossHealthBar;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.Camera;
import com.watabou.utils.Bundle;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class Goo extends Mob {

	{
		//基础数据
		//IF
		EXP = 10;
		defenseSkill = 8;
		spriteClass = GooSprite.class;

		//生物词条
		properties.add(Property.BOSS);
		properties.add(Property.DEMONIC);
		properties.add(Property.ACIDIC);
	}

	//蓄力层数
	private int pumpedUp = 0;
	//回复量
	private int healInc = 1;

	//伤害
	@Override
	public int damageRoll() {
		//int
		int min = 1;
		int max = (HP*2 <= HT) ? 12 : 8;
		//蓄力
		if (pumpedUp > 0) {
			pumpedUp = 0;
			return Random.NormalIntRange( min*3, max*3 );
		} else {
			//normal
			return Random.NormalIntRange( min, max );
		}
	}

	//命中
	@Override
	public int attackSkill( Char target ) {
		int attack = 10;
		//损伤
		if (HP*2 <= HT) attack = 15;
		//蓄力
		if (pumpedUp > 0) attack *= 2;
		return attack;
	}

	//闪避
	@Override
	public int defenseSkill(Char enemy) {
		//IF
		return (int)(super.defenseSkill(enemy) * ((HP*2 <= HT)? 1.5 : 1));
	}

	//防御
	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, 2);
	}

	//动作逻辑
	@Override
	public boolean act() {
		//回血
		if (Dungeon.level.water[pos] && HP < HT) {
			//执行
			HP += healInc;
			//背水一战
			LockedFloor lock = Dungeon.hero.buff(LockedFloor.class);
			if (lock != null) lock.removeTime(healInc*2);
			//粒子
			if (Dungeon.level.heroFOV[pos] ){
				sprite.emitter().burst( Speck.factory( Speck.HEALING ), healInc );
			}
			//挑战
			if (Dungeon.isChallenged(Challenges.STRONGER_BOSSES) && healInc < 3) {
				healInc++;
			}
			//血条
			if (HP*2 > HT) {
				BossHealthBar.bleed(false);
				((GooSprite)sprite).spray(false);
				HP = Math.min(HP, HT);
			}
		} else {
			healInc = 1;
		}
		//未睡眠前
		if (state != SLEEPING){
			Dungeon.level.seal();
		}

		return super.act();
	}

	//攻击范围
	@Override
	protected boolean canAttack( Char enemy ) {
		if (pumpedUp > 0){
			//we check both from and to in this case as projectile logic isn't always symmetrical.
			//this helps trim out BS edge-cases
			return Dungeon.level.distance(enemy.pos, pos) <= 2
					//获取敌人位置
						&& new Ballistica( pos, enemy.pos, Ballistica.PROJECTILE).collisionPos == enemy.pos
					//获取自身位置
						&& new Ballistica( enemy.pos, pos, Ballistica.PROJECTILE).collisionPos == pos;
		} else {
			return super.canAttack(enemy);
		}
	}
	//附伤
	@Override
	public int attackProc( Char enemy, int damage ) {
		damage = super.attackProc( enemy, damage );
		//BUFF
		if (Random.Int( 3 ) == 0) {
			Buff.affect( enemy, Ooze.class ).set( Ooze.DURATION );
			enemy.sprite.burst( 0x000000, 5 );
		}

		if (pumpedUp > 0) {
			//视角震动
			Camera.main.shake( 3, 0.2f );
		}

		return damage;
	}

	@Override
	public void updateSpriteState() {
		super.updateSpriteState();

		if (pumpedUp > 0){
			//粒子扩散
			((GooSprite)sprite).pumpUp( pumpedUp );
		}
	}


	@Override
	protected boolean doAttack( Char enemy ) {
		if (pumpedUp == 1) {
			//加强蓄力
			pumpedUp++;
			//
			((GooSprite)sprite).pumpUp( pumpedUp );

			spend( attackDelay() );

			return true;
        //蓄力完毕
		} else if (pumpedUp >= 2 || Random.Int( (HP*2 <= HT) ? 2 : 5 ) > 0) {

			boolean visible = Dungeon.level.heroFOV[pos];

			if (visible) {

				if (pumpedUp >= 2) {
					//动画
					((GooSprite) sprite).pumpAttack();
				} else {
					//ATK
					sprite.attack(enemy.pos);
				}
			} else {
				if (pumpedUp >= 2){
					//动画
					((GooSprite)sprite).triggerEmitters();
				}
				attack( enemy );
				spend( attackDelay() );
			}

			return !visible;

		} else {
			//增加蓄力范围
			pumpedUp++;
			//检测到挑战后瞬间蓄力完成（再增加一次蓄力）
			if (Dungeon.isChallenged(Challenges.STRONGER_BOSSES)){
				pumpedUp++;
			}

			((GooSprite)sprite).pumpUp( pumpedUp );

			//在玩家的视野内时播放动作
			if (Dungeon.level.heroFOV[pos]) {
				sprite.showStatus( CharSprite.NEGATIVE, Messages.get(this, "!!!") );
				GLog.n( Messages.get(this, "pumpup") );
			}

			spend( attackDelay() );

			return true;
		}
	}

	@Override
	public boolean attack( Char enemy, float dmgMulti, float dmgBonus, float accMulti ) {
		boolean result = super.attack( enemy, dmgMulti, dmgBonus, accMulti );
		pumpedUp = 0;
		return result;
	}

	//清除粒子效果
	@Override
	protected boolean getCloser( int target ) {
		if (pumpedUp != 0) {
			pumpedUp = 0;
			sprite.idle();
		}
		return super.getCloser( target );
	}

	@Override
	public void damage(int dmg, Object src) {
		if (!BossHealthBar.isAssigned()){
			BossHealthBar.assignBoss( this );
		}
		//激怒 粒子效果
		boolean bleeding = (HP*2 <= HT);
		super.damage(dmg, src);
		if ((HP*2 <= HT) && !bleeding){
			BossHealthBar.bleed(true);
			sprite.showStatus(CharSprite.NEGATIVE, Messages.get(this, "enraged"));
			((GooSprite)sprite).spray(true);
			yell(Messages.get(this, "gluuurp"));
		}
		//背水一战
		LockedFloor lock = Dungeon.hero.buff(LockedFloor.class);
		if (lock != null) lock.addTime(dmg*2);
	}

	//死亡效果
	@Override
	public void die( Object cause ) {
		
		super.die( cause );

		Dungeon.LimitedDrops.GOO_ALL.count--;
		Dungeon.LimitedDrops.GOO_KILL.count++;
		
		Dungeon.level.unseal();
		
		GameScene.bossSlain();
		Dungeon.level.drop( new SkeletonKey( Dungeon.depth ), pos ).sprite.drop();
		
		//60% chance of 2 blobs, 30% chance of 3, 10% chance for 4. Average of 2.5
		int blobs = Random.chances(new float[]{0, 0, 6, 3, 1});
		for (int i = 0; i < blobs; i++){
			int ofs;
			do {
				ofs = PathFinder.NEIGHBOURS8[Random.Int(8)];
			} while (!Dungeon.level.passable[pos + ofs]);
			Dungeon.level.drop( new GooBlob(), pos + ofs ).sprite.drop( pos );
		}
		
		Badges.validateBossSlain();
		
		yell( Messages.get(this, "defeated") );
	}
	
	@Override
	public void notice() {
		super.notice();
		if (!BossHealthBar.isAssigned()) {
			BossHealthBar.assignBoss(this);
			yell(Messages.get(this, "notice"));
			for (Char ch : Actor.chars()){
				if (ch instanceof DriedRose.GhostHero){
					((DriedRose.GhostHero) ch).sayBoss();
				}
			}
		}
	}

	private final String PUMPEDUP = "pumpedup";
	private final String HEALINC = "healinc";

	@Override
	public void storeInBundle( Bundle bundle ) {

		super.storeInBundle( bundle );

		bundle.put( PUMPEDUP , pumpedUp );
		bundle.put( HEALINC, healInc );
	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {

		super.restoreFromBundle( bundle );

		pumpedUp = bundle.getInt( PUMPEDUP );
		if (state != SLEEPING) BossHealthBar.assignBoss(this);
		if ((HP*2 <= HT)) BossHealthBar.bleed(true);

		//if check is for pre-0.9.3 saves
		healInc = bundle.getInt(HEALINC);

	}
	
}
