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

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.boss;

import static com.shatteredpixel.shatteredpixeldungeon.Dungeon.hero;
import static com.shatteredpixel.shatteredpixeldungeon.Dungeon.level;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.CorrosiveSludge;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Dying;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.LifeTap;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.LockedFloor;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Ooze;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.effects.Beam;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.DriedRose;
import com.shatteredpixel.shatteredpixeldungeon.items.keys.IronKey;
import com.shatteredpixel.shatteredpixeldungeon.items.keys.SkeletonKey;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.GooSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.GooUpdateSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.RatSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.BossHealthBar;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.Camera;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class GooUpdate extends Mob {

	{
		//基础数据
		HP=HT=200;
		spriteClass = GooUpdateSprite.class;
		properties.add(Property.BOSS);
	}

	private boolean stage2 = false;
	private boolean stage3 = false;

	private boolean gluuurp =false;
	private boolean enraged =false;

	private int summonSmileCooldown= 0;
	private int summonBleedCooldown= 0;
	private int smileCount=0;
	private int bleedCount=0;
	private int summonSmileMax=25;
	private int oozeCooldown = 0;
	private int oozeUp = 0;
	private int hitpoint= 0;

	@Override
	public int damageRoll() {
		return Random.NormalIntRange(5,18);
	}

	@Override
	public boolean act() {
		hitpoint = HT - HP;
		if(HP*2<HT){
			stage2=true;
		}
		if(HP*3<HT){
			stage3=true;
		}

		summonSmileCooldown--;
		summonBleedCooldown--;
		oozeCooldown--;

		//濒死状态不回血
		if (stage3==true && this.buffs(Dying.class)==null) {
			HP += 3;
			sprite.emitter().burst( Speck.factory( Speck.HEALING ), 1 );
			if (HP*2 > HT) {
				BossHealthBar.bleed(false);
				((GooSprite)sprite).spray(false);
				HP = Math.min(HP, HT);
			}
		}

		if(stage2==true){
			summonSmileMax = 300;
		}
		if(stage3==true){
			summonSmileMax = 400;
		}


			//生命分流状态
			if (summonSmileCooldown <= 0 && smileCount < summonSmileMax) {
				if (this.buffs(LifeTap.class) == null) {
					Buff.affect(this, LifeTap.class);
				}
				summonSmile();
			}

			if (summonBleedCooldown <= 0 && stage3 == true ) {
				if (this.buffs(LifeTap.class) == null) {
					Buff.affect(this, LifeTap.class);
				}
				summonBleed();
			}


			if (oozeCooldown < 0 && stage2 == true) {
				oozeUp++;
				oozeCooldown = Random.Int(5,12);
			}


		if (state != SLEEPING){
			Dungeon.level.seal();
		}


		//终止生命分流
		if(this.buffs(LifeTap.class)!=null){
			if(smileCount<=0 && bleedCount<=0){
				Buff.detach(this, LifeTap.class);

			}
		}

		return super.act();
	}

	public int afiesfb =0;

	private void summonSmile(){
		OozeSmile oozeSmile = new OozeSmile();
		afiesfb=0;
		if (smileCount<=summonSmileMax){
			if(Terrain.flags[this.pos+1]==Terrain.EMPTY){
				smileCount++;
				oozeSmile.pos = this.pos+1;
				GameScene.add(oozeSmile);
			}
			if(Terrain.flags[this.pos-1]==Terrain.EMPTY){
				smileCount++;
				oozeSmile.pos = this.pos-1;
				GameScene.add(oozeSmile);
			}
		}
		afiesfb=1;
		summonSmileCooldown=Random.Int(15,20);
	}


	private void summonBleed(){
		BleedCrystal bleedCrystal = new BleedCrystal();
		if (smileCount<=summonSmileMax){
			if(Terrain.flags[this.pos+1]==Terrain.EMPTY){
				smileCount++;
				bleedCrystal.pos = this.pos+1;
				GameScene.add(bleedCrystal);
			}
			if(Terrain.flags[this.pos-1]==Terrain.EMPTY){
				smileCount++;
				bleedCrystal.pos = this.pos-1;
				GameScene.add(bleedCrystal);
			}
		}
		summonBleedCooldown=Random.Int(15,25);
	}

	@Override
	public int attackProc( Char enemy, int damage ) {
		damage = super.attackProc( enemy, damage );

		if (Random.Int( 3 ) == 0) {
			Buff.affect( enemy, Ooze.class ).set( Ooze.DURATION );
			enemy.sprite.burst( 0x000000, 5 );
		}

		if(oozeUp>=3){
			Buff.affect( enemy, CorrosiveSludge.class );
			ShatteredPixelDungeon.scene().add(new Beam.DeathRay(this.sprite.center(), enemy.sprite.center()));
			Camera.main.shake( 3, 0.2f );
			oozeUp=0;
		}

		return damage;
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
			((GooSprite)sprite).spray(true);
			if(enraged==false) {
				sprite.showStatus(CharSprite.NEGATIVE, Messages.get(this, "enraged"));
				enraged=true;
			}
			if(gluuurp==false) {
				yell(Messages.get(this, "gluuurp"));
				gluuurp=true;
			}
		}
		//背水一战
		LockedFloor lock = Dungeon.hero.buff(LockedFloor.class);
		if (lock != null) lock.addTime(dmg*2);
	}


	public class OozeSmile extends Mob{

		{
			spriteClass = RatSprite.class;
			HT=HP=Random.Int(3,7);
		}

		@Override
		protected boolean act() {
			state = PASSIVE;
			if(afiesfb==0){
				die(true);
			}
			return super.act();
		}

		@Override
		public void damage(int dmg, Object src) {
			dmg=1;
			super.damage(dmg, src);
		}

		@Override
		public void die(Object cause) {
			smileCount--;
			super.die(cause);
		}
	}

	public class BleedCrystal extends Mob{

		{
			spriteClass = RatSprite.class;
			HT=HP=Random.Int(3,7);
		}

		private int strength = 0;

		@Override
		protected boolean act() {
			state = PASSIVE;
			if(enemy!=null) {
				if (Dungeon.level.distance(this.pos, enemy.pos) <= 5) {
					strength--;
				}
			}
			if(strength<=0){
				if(enemy!=null){
					enemy.damage(Random.Int(hitpoint/2),this);
					ShatteredPixelDungeon.scene().add(new Beam.DeathRay(this.sprite.center(), enemy.sprite.center()));
					strength=2;
				}

			}

			if(summonBleedCooldown<=0){
				for(Mob boss:level.mobs){
					if(boss instanceof GooUpdate){
						boss.HP+=3;
					}
				}
				die(true);

			}

			return super.act();
		}

		@Override
		public void damage(int dmg, Object src) {
			dmg=1;
			super.damage(dmg, src);
		}

		@Override
		public void die(Object cause) {
			bleedCount--;
			super.die(cause);
		}
	}


	@Override
	public void die( Object cause ) {
			super.die(cause);
			Dungeon.level.unseal();
			GameScene.bossSlain();
			//Dungeon.level.drop(new SkeletonKey(Dungeon.depth), pos).sprite.drop();
		Dungeon.level.drop(new IronKey(Dungeon.depth),pos).sprite.drop();
			yell(Messages.get(this, "defeated"));
	}

	@Override
	public void notice() {
		super.notice();
		BossHealthBar.assignBoss(this);
		GLog.n(Messages.get(this, "notice"));
		for (Char ch : Actor.chars()){
			if (ch instanceof DriedRose.GhostHero){
				((DriedRose.GhostHero) ch).sayBoss();
			}
		}
	}

	private final String SUMMON_SMILE_COOLDOWN = "summonsmilecooldown";
	private final String SUMMON_BLEED_COOLDOWN = "summonbleedsooldown";
	private final String SMILE_COUNT = "smilecount";
	private final String BLEED_COUNT = "bleedcount";
	private final String SUMMON_SMILE_MAX = "summonsmilemax";
	private final String OOZE_COOLDOWN = "oozecooldown";
	private final String OOZE_UP = "oozeup";

	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
		bundle.put( SUMMON_SMILE_COOLDOWN , summonSmileCooldown );
		bundle.put( SUMMON_BLEED_COOLDOWN , summonBleedCooldown );
		bundle.put( SMILE_COUNT , smileCount );
		bundle.put( SUMMON_SMILE_MAX , summonSmileMax );
		bundle.put( OOZE_COOLDOWN , oozeCooldown );
		bundle.put(BLEED_COUNT,bleedCount);
		bundle.put( OOZE_UP , oozeUp );
	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		summonSmileCooldown = bundle.getInt( SUMMON_SMILE_COOLDOWN );
		summonBleedCooldown = bundle.getInt( SUMMON_BLEED_COOLDOWN );
		smileCount = bundle.getInt( SMILE_COUNT );
		summonSmileMax = bundle.getInt( SUMMON_SMILE_MAX );
		oozeCooldown = bundle.getInt( OOZE_COOLDOWN );
		bleedCount = bundle.getInt(BLEED_COUNT);
		oozeUp = bundle.getInt( OOZE_UP );

		if (state != SLEEPING) BossHealthBar.assignBoss(this);
		if ((HP*2 <= HT)) BossHealthBar.bleed(true);
	}
}
