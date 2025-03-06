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
package com.shatteredpixel.shatteredpixeldungeon.items.weapon;
import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.huntress.NaturesPower;
import com.shatteredpixel.shatteredpixeldungeon.effects.SpellSprite;
import com.shatteredpixel.shatteredpixeldungeon.effects.Splash;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.LeafParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.newitem.ammo.Ammo;
import com.shatteredpixel.shatteredpixeldungeon.items.quest.DarkGold;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfFuror;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.MissileWeapon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.CellSelector;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.sprites.MissileSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.QuickSlotButton;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.particles.Emitter;
import com.watabou.utils.Bundle;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

import java.util.ArrayList;
public class Gun extends Weapon {
	public static final String AC_SHOOT = "SHOOT";
	public static final String AC_LOAD = "LOAD";
	//初始无弹药
	public int bowammo = 0;
	//装弹时间
	public float spend = 1f;
	//定义弹药物品，供90行使用
	Item AmmoItem;

	{
		image = ItemSpriteSheet.GUN;
		//默认动作：射击
		defaultAction = AC_SHOOT;
		unique = true;
		bones = false;

	}

	public boolean sniperSpecial = false;

	@Override
	public ArrayList<String> actions(Hero hero) {
		ArrayList<String> actions = super.actions(hero);
		//隐藏装备动作（无法被装备）
		actions.remove(AC_EQUIP);
		//增加射击动作
		actions.add(AC_SHOOT);
		//actions.add(AC_LOAD);
		return actions;
	}

	@Override
	public void execute(Hero hero, String action) {
		super.execute(hero, action);
		//防止装弹时产生瞄准标记
		usesTargeting = false;
		//指定弹药
		this.AmmoItem = hero.belongings.getItem(Ammo.class);
		//动作
		if (action.equals(AC_SHOOT)) {
			//检查子弹
			if (bowammo < 1 && AmmoItem != null) {
				//装弹
				action.equals(AC_LOAD);
				//锁定操作并执行特效
				hero.busy();
				GLog.p(Messages.get(this, "nobowammo"));
				hero.sprite.operate(hero.pos);
				hero.spend(1f);
				SpellSprite.show(hero, SpellSprite.CHARGE);
				Sample.INSTANCE.play(Assets.Sounds.ATK_SPIRITBOW);
				//装弹并移除一个弹药
				bowammo = 1;
				AmmoItem.detach(hero.belongings.backpack);
				//有子弹
			} else if (bowammo == 1) {
				usesTargeting = true;
				curUser = hero;
				curItem = this;
				GameScene.selectCell(shooter);
				//子弹超出范围
			} else if (bowammo > 1||bowammo < 0) {
				bowammo = 1;
				GLog.p(Messages.get(this, "addbowammo"));
				curUser = hero;
				curItem = this;
			}else {
				//没子弹了
				GLog.w(Messages.get(this, "lessammo"));
			}
		}
	}

	@Override
	public int proc(Char attacker, Char defender, int damage) {

		if (attacker.buff(NaturesPower.naturesPowerTracker.class) != null && !sniperSpecial) {

			Actor.add(new Actor() {
				{
					actPriority = VFX_PRIO;
				}

				@Override
				protected boolean act() {

					if (!defender.isAlive()) {
						NaturesPower.naturesPowerTracker tracker = attacker.buff(NaturesPower.naturesPowerTracker.class);
					}

					Actor.remove(this);
					return true;
				}
			});

		}

		return super.proc(attacker, defender, damage);
	}

	@Override
	public String info() {
		String info = desc();

		info += "\n\n" + Messages.get(Gun.class, "stats",
				Math.round(augment.damageFactor(min())),
				Math.round(augment.damageFactor(max())),
				STRReq());

		if (STRReq() > Dungeon.hero.STR()) {
			info += " " + Messages.get(Weapon.class, "too_heavy");
		} else if (Dungeon.hero.STR() > STRReq()) {
			info += " " + Messages.get(Weapon.class, "excess_str", Dungeon.hero.STR() - STRReq());
		}

		switch (augment) {
			case SPEED:
				info += "\n\n" + Messages.get(Weapon.class, "faster");
				break;
			case DAMAGE:
				info += "\n\n" + Messages.get(Weapon.class, "stronger");
				break;
			case NONE:
		}

		if (enchantment != null && (cursedKnown || !enchantment.curse())) {
			info += "\n\n" + Messages.get(Weapon.class, "enchanted", enchantment.name());
			info += " " + Messages.get(enchantment, "desc");
		}

		if (cursed && isEquipped(Dungeon.hero)) {
			info += "\n\n" + Messages.get(Weapon.class, "cursed_worn");
		} else if (cursedKnown && cursed) {
			info += "\n\n" + Messages.get(Weapon.class, "cursed");
		} else if (!isIdentified() && cursedKnown) {
			info += "\n\n" + Messages.get(Weapon.class, "not_cursed");
		}

		info += "\n\n" + Messages.get(MissileWeapon.class, "distance");

		return info;
	}

	@Override
	public int STRReq(int lvl) {
		return 10;
	}

	@Override
	public int min(int lvl) {
		return 5+(lvl*3);
	}

	@Override
	public int max(int lvl) {
		return 15+(lvl*8);
	}


	@Override
	public int targetingPos(Hero user, int dst) {
		return knockArrow().targetingPos(user, dst);
	}

	private int targetPos;

	@Override
	public int damageRoll(Char owner) {
		int MAX = max();
		int MIN = min();
		int damage = Random.Int(MIN, MAX);

		if (owner instanceof Hero) {
			int exStr = ((Hero) owner).STR() - STRReq();
			if (exStr > 0) {
				damage += Random.IntRange(0, exStr);
			}
		}


		if (sniperSpecial) {
			damage = Random.Int(MIN, MAX);

			switch (augment) {
				case NONE:
					damage = Random.Int(MIN, MAX);
					break;
				case SPEED:
					damage = Random.Int(MIN, MAX);
					break;
				case DAMAGE:
					int distance = Dungeon.level.distance(owner.pos, targetPos) - 1;
					damage = Random.Int(MIN, MAX);
					break;
			}
		}

		return damage;
	}

	@Override
	protected float baseDelay(Char owner) {
		if (sniperSpecial) {
			switch (augment) {
				case NONE:
				default:
					return 0f;
				case SPEED:
					return 1f * RingOfFuror.attackSpeedMultiplier(owner);
				case DAMAGE:
					return 2f * RingOfFuror.attackSpeedMultiplier(owner);
			}
		} else {
			return super.baseDelay(owner);
		}
	}

	@Override
	protected float speedMultiplier(Char owner) {
		float speed = super.speedMultiplier(owner);
		/*if (owner.buff(NaturesPower.naturesPowerTracker.class) != null){
			// +33% speed to +50% speed, depending on talent points
			speed += ((8 + ((Hero)owner).pointsInTalent(Talent.GROWING_POWER)) / 24f);
		}*/
		return speed;
	}

	@Override //等级
	public int level() {
		return level;
	}

	//抛射物图像
	public SpiritAmmo knockArrow() {
		return new SpiritAmmo();
	}

	public class SpiritAmmo extends MissileWeapon {

		{
			image = ItemSpriteSheet.AMMO_MINI;

			hitSound = Assets.Sounds.HIT_ARROW;
		}

		@Override
		public Emitter emitter() {
			if (Dungeon.hero.buff(NaturesPower.naturesPowerTracker.class) != null && !sniperSpecial) {
				Emitter e = new Emitter();
				e.pos(5, 5);
				e.fillTarget = false;
				e.pour(LeafParticle.GENERAL, 0.01f);
				return e;
			} else {
				return super.emitter();
			}
		}

		@Override
		public int damageRoll(Char owner) {
			return Gun.this.damageRoll(owner);
		}

		@Override
		public boolean hasEnchant(Class<? extends Enchantment> type, Char owner) {
			return Gun.this.hasEnchant(type, owner);
		}

		@Override
		public int proc(Char attacker, Char defender, int damage) {
			return Gun.this.proc(attacker, defender, damage);
		}

		@Override
		public float delayFactor(Char user) {
			return Gun.this.delayFactor(user);
		}
		
		/*

		@Override
		public float accuracyFactor(Char owner) {
			if (sniperSpecial && CrossBow.this.augment == Augment.DAMAGE){
				return Float.POSITIVE_INFINITY;
			} else {
				return super.accuracyFactor(owner);
			}
		}

		 */

		@Override
		public float accuracyFactor(Char owner) {
			return 10000;
		}

		@Override
		public int STRReq(int lvl) {
			return Gun.this.STRReq(lvl);
		}

		@Override
		protected void onThrow(int cell) {
			Char enemy = Actor.findChar(cell);
			if (enemy == null || enemy == curUser) {
				parent = null;
				Splash.at(cell, 0xCC99FFFF, 1);
			} else {
				if (!curUser.shoot(enemy, this)) {
					Splash.at(cell, 0xCC99FFFF, 1);
				}
				if (sniperSpecial && Gun.this.augment != Augment.SPEED) sniperSpecial = false;
			}
		}

		@Override
		public void throwSound() {
			Sample.INSTANCE.play(Assets.Sounds.ATK_SPIRITBOW, 1, Random.Float(0.87f, 1.15f));
		}

		int flurryCount = -1;

		@Override
		public void cast(final Hero user, final int dst) {
			final int cell = throwPos(user, dst);
			Gun.this.targetPos = cell;
			if (sniperSpecial && Gun.this.augment == Augment.SPEED) {
				if (flurryCount == -1) flurryCount = 3;

				final Char enemy = Actor.findChar(cell);

				if (enemy == null) {
					user.spendAndNext(castDelay(user, dst));
					sniperSpecial = false;
					flurryCount = -1;
					return;
				}
				QuickSlotButton.target(enemy);

				final boolean last = flurryCount == 1;

				user.busy();

				throwSound();

				((MissileSprite) user.sprite.parent.recycle(MissileSprite.class)).reset(user.sprite, cell, this, new Callback() {
									@Override
									public void call() {
										if (enemy.isAlive()) {
											curUser = user;
											onThrow(cell);
										}

										if (last) {
											user.spendAndNext(castDelay(user, dst));
											sniperSpecial = false;
											flurryCount = -1;
										}
									}
								});

				user.sprite.zap(cell, new Callback() {
					@Override
					public void call() {
						flurryCount--;
						if (flurryCount > 0) {
							cast(user, dst);
						}
					}
				});

			} else {

				/*if (user.hasTalent(Talent.SEER_SHOT)
						&& user.buff(Talent.SeerShotCooldown.class) == null){
					int shotPos = throwPos(user, dst);
					if (Actor.findChar(shotPos) == null) {
						RevealedArea a = Buff.affect(user, RevealedArea.class, 5 * user.pointsInTalent(Talent.SEER_SHOT));
						a.depth = Dungeon.depth;
						a.pos = shotPos;
						Buff.affect(user, Talent.SeerShotCooldown.class, 20f);
					}
				} */

				super.cast(user, dst);
			}
		}
	}

	private CellSelector.Listener shooter = new CellSelector.Listener() {
		@Override
		public void onSelect(Integer target) {
			if (target != null) {
				knockArrow().cast(curUser, target);
				bowammo = bowammo - 1;
			}
		}

		@Override
		public String prompt() {
			return Messages.get(Gun.class, "prompt");
		}
	};

	//将整数数据转化为字符串
	private static final String BOWAMMO/*全大写*/ = "bowammo"/*全小写*/;

	@Override //将变量转换为字符串后储存（防止重置）
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(BOWAMMO, bowammo);
	}

	@Override //读取游戏后将其取出
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		bowammo = bundle.getInt(BOWAMMO);
	}

	@Override //物品价值（参与价值和售价运算）
	public int value() {
		return 150;
	}


	public boolean isUpgradable() {
		return true;
	}
}