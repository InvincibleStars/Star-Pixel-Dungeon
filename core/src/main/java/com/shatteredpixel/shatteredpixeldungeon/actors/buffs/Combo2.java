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

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.ui.ActionIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.AttackIndicator;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndCombo2;
import com.watabou.noosa.Image;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;

public class Combo2 extends Buff implements ActionIndicator.Action {

	private int count = 0;
	public static final float TICK	= 1f;

	public int getComboCount(){
		return count;
	}



	@Override
	public Image getIcon() {
		Image icon;
		icon = new ItemSprite(new Item(){ {image = ItemSpriteSheet.AMULET; }});
		return icon;
	}

	@Override
	public void detach() {
		super.detach();
		ActionIndicator.clearAction(this);
	}

	@Override
	public boolean act() {
		ActionIndicator.setAction( this );
		count++;
		spend(TICK);
		return true;
	}

	public static class RiposteTracker extends Buff{
		{ actPriority = VFX_PRIO;}

		public Char enemy;

		@Override
		public boolean act() {
			if (target.buff(Combo2.class) != null) {
				target.sprite.attack(enemy.pos, new Callback() {
					@Override
					public void call() {
						target.buff(Combo2.class).doAttack(enemy);
						next();
					}
				});
				detach();
				return false;
			} else {
				detach();
				return true;
			}
		}
	}

	@Override
	public void doAction() {
		GameScene.show(new WndCombo2(this));
	}

	public enum StarSkill {
		SKILL1	(2, 0xFF00FF00),
		SKILL2  (4, 0xFFCCFF00),
		SKILL3  (6, 0xFFFFFF00),
		SKILL4  (8, 0xFFFFCC00),
		SKILL5  (10, 0xFFFF0000);

		public int useNeed, tintColor;

		StarSkill(int useNeed, int tintColor){
			this.useNeed = useNeed;
			this.tintColor = tintColor;
		}

		public String desc(int count){
			switch (this){
				default:
					return Messages.get(this, name()+"_desc");
				case SKILL1:
					return Messages.get(this, name()+"_desc", count*20);
				case SKILL2:
					return Messages.get(this, name()+"_desc", count*25);
				case SKILL3:
					return Messages.get(this, name()+"_desc", count*25);
			}
		}
	}

	private static StarSkill skilluesd;

	private void doAttack(final Char enemy) {

		AttackIndicator.target(enemy);
		Invisibility.dispel();

		//Post-attack behaviour
		switch(skilluesd){
			case SKILL1:
				count--;
					target.HP=Math.max(target.HP+5,target.HT);
					Sample.INSTANCE.play(Assets.Sounds.BURNING);
				break;

			case SKILL2:
				count-=2;
					Buff.affect(enemy,Blindness.class,15f);
					Sample.INSTANCE.play(Assets.Sounds.DEBUFF);
				break;

			case SKILL3:
					count--;
					Sample.INSTANCE.play( Assets.Sounds.CHARGEUP );
				break;

			default:
				detach();
				break;
		}
	}
}