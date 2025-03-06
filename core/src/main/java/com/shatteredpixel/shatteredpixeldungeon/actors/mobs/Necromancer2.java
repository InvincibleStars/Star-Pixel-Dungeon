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

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Adrenaline;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfHealing;
import com.shatteredpixel.shatteredpixeldungeon.sprites.NecromancerSprite;
import com.watabou.utils.Bundle;

public class Necromancer2 extends Mob {

	// 技能参数
	private static final int RANGE = 6;            // 技能射程
	private static final int COOLDOWN = 20;        // 冷却回合
	private static final float ADRENALINE_DURATION = 4f; // 延长持续时间

	private int cooldown = 0;       // 当前冷却
	private boolean skillActive;    // 技能发动状态

	{
		spriteClass = NecromancerSprite.class;

		HP = HT = 40;
		defenseSkill = 14;

		EXP = 7;
		maxLvl = 14;

		loot = new PotionOfHealing();
		lootChance = 0.2f;

		properties.add(Property.UNDEAD);

		HUNTING = new Hunting();
	}

	// 近战攻击判断
	@Override
	protected boolean canAttack(Char enemy) {
		return Dungeon.level.adjacent(pos, enemy.pos);
	}

	// 主要行为逻辑
	@Override
	protected boolean act() {
		if (cooldown > 0) cooldown--;
		return super.act();
	}

	// 新增技能攻击方法
	private void useRangedSkill(Char target) {
		spend(TICK); // 消耗时间单位
		skillActive = true;

		if (sprite != null && sprite.visible) {
			sprite.zap(target.pos);
		} else {
			onSkillComplete(target.pos);
		}
	}

	public void onSkillComplete(int targetPos) {
		Char target = Actor.findChar(targetPos);
		if (target != null) {
			// 施加肾上腺素效果
			Buff.affect(target, Adrenaline.class, ADRENALINE_DURATION);

			// 显示光束效果
			/*
			if (sprite.parent != null) {
				sprite.parent.add(new Beam.AdrenalineRay(
						sprite.center(),
						DungeonTilemap.raisedTileCenterToWorld(targetPos)
				));
			}

			 */
		}

		cooldown = COOLDOWN;
		skillActive = false;
		next();
	}

	// AI逻辑
	private class Hunting extends Mob.Hunting {

		@Override
		public boolean act(boolean enemyInFOV, boolean justAlerted) {
			// 优先使用技能
			if (cooldown <= 0 && enemyInFOV) {
				Char target = findSkillTarget();
				if (target != null) {
					useRangedSkill(target);
					return true;
				}
			}

			// 否则执行近战攻击
			return super.act(enemyInFOV, justAlerted);
		}

		// 寻找技能目标
		private Char findSkillTarget() {
			for (Char ch : Actor.chars()) {
				if (ch.alignment == Alignment.ENEMY &&
						Dungeon.level.distance(pos, ch.pos) <= RANGE &&
						fieldOfView[ch.pos]) {
					return ch;
				}
			}
			return null;
		}
	}

	// 数据持久化
	private static final String COOLDOWN_KEY = "cooldown";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(COOLDOWN_KEY, cooldown);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		cooldown = bundle.getInt(COOLDOWN_KEY);
	}

	// 其他原有方法保持不变...
}