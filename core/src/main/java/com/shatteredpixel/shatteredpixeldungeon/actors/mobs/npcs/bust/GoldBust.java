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

import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.NPC;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.newsprite.tree.BustSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.Icons;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndOptions;
import com.watabou.noosa.Game;
import com.watabou.utils.Callback;

public class GoldBust extends NPC {

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
			this.sprite.add(CharSprite.State.CUTOFFBUFF);
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
										   ShatteredPixelDungeon.scene().add(new WndOptions(Icons.get(Icons.WARNING),
												   Messages.get(KillBust.class, "warm"),
												   Messages.get(KillBust.class, "tip"),
												   Messages.get(KillBust.class, "yes"),
												   Messages.get(KillBust.class, "no")) {
											   @Override
											   protected void onSelect(int index) {
													   NPC.choose=true;
													   NPC.choose_num=2;
											   }
										   });


									   }
								   }
			);

		}else {
			GLog.i(Messages.get(KillBust.class, "complete"));
		}
				return true;
	}

	@Override
	public void beckon(int cell){
	}






}