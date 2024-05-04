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

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.bust;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.NPC;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.newsprite.tree.BustSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.Icons;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndOptions;
import com.watabou.noosa.Game;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;

public class WealthBust extends NPC {

	{
		spriteClass = BustSprite.class;
	}


	@Override
	public int defenseSkill(Char enemy) {return INFINITE_EVASION;}
	
	@Override
	public void damage( int dmg, Object src ) {}




	int start = 0;
	@Override
	protected boolean act() {
		if(start==0){
			this.sprite.add(CharSprite.State.KILLBUST);
			start=1;
		}
		alerted = false;
		return super.act();
	}

	public void die ( Object cause ) {
		super.die(cause);
		this.sprite.remove(CharSprite.State.KILLBUST);
	}

	@Override
	public boolean interact(Char c) {
		if(choose==false) {
			Game.runOnRenderThread(new Callback() {
									   @Override
									   public void call() {
										   //写这里


										   ShatteredPixelDungeon.scene().add(new WndOptions(Icons.get(Icons.WARNING),
												   Messages.get(WealthBust.class, "warm"),
												   Messages.get(WealthBust.class, "tip"),
												   Messages.get(WealthBust.class, "yes"),
												   Messages.get(WealthBust.class, "no")) {
											   @Override
											   protected void onSelect(int index) {
												   //声明武器
												   Weapon i;
												   if (index == 0) {
													   if(!Badges.isUnlocked(Badges.Badge.MONSTERS_SLAIN_1)){
														   GLog.n(Messages.get(WealthBust.class, "empty")+"\n");
													   }

													   if (Badges.isUnlocked(Badges.Badge.MONSTERS_SLAIN_1)) {
														   i = (Weapon) Generator.random(Generator.Category.WEP_T2);
														   i.identify().collect();
														   Sample.INSTANCE.play(Assets.Sounds.ITEM);
														   GLog.i(Messages.get(WealthBust.class, "weapon")+"\n");
													   }
													   if (Badges.isUnlocked(Badges.Badge.MONSTERS_SLAIN_2)) {
														   i = (Weapon) Generator.random(Generator.Category.WEP_T3);
														   i.identify().collect();
													   }
													   if (Badges.isUnlocked(Badges.Badge.MONSTERS_SLAIN_3)) {
														   i = (Weapon) Generator.random(Generator.Category.WEP_T4);
														   i.identify().collect();
													   }
													   if (Badges.isUnlocked(Badges.Badge.MONSTERS_SLAIN_4)) {
														   i = (Weapon) Generator.random(Generator.Category.WEP_T5);
														   i.identify().collect();
													   }
													   //给予完毕后才会锁定
													   GLog.i(Messages.get(WealthBust.class, "killopen")+"\n");
													   NPC.choose=true;
													   NPC.choose_num=1;
												   } else {
													   GLog.i(Messages.get(WealthBust.class, "noselect"));

												   }
											   }
										   });


									   }
								   }
			);

		}else {
			GLog.i(Messages.get(WealthBust.class, "complete"));
		}
				return true;
	}

	@Override
	public void beckon(int cell){
	}






}