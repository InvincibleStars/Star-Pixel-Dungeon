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

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.sell;

import static com.shatteredpixel.shatteredpixeldungeon.Dungeon.hero;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.NPC;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.newitem.ammo.Ammo;
import com.shatteredpixel.shatteredpixeldungeon.items.quest.Embers2;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.newsprite.tree.BustSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.Icons;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndOptions;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndOptionsNo;
import com.watabou.noosa.Game;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;

public class ElementalWell extends NPC {

	{
		spriteClass = BustSprite.class;
	}


	@Override
	public int defenseSkill(Char enemy) {return INFINITE_EVASION;}

	@Override
	public void damage( int dmg, Object src ) {}

	@Override
	protected boolean act() {
		alerted = false;
		return super.act();
	}

	@Override
	public boolean interact(Char c) {
		if(choose==false) {
			Game.runOnRenderThread(new Callback() {
				@Override
				public void call() {
					ShatteredPixelDungeon.scene().add(new WndOptionsNo(Icons.get(Icons.WARNING),
							Messages.get(ElementalWell.class, "warm"),
							Messages.get(ElementalWell.class, "tip"),
							Messages.get(ElementalWell.class, TAG_HP),
							Messages.get(ElementalWell.class, "yes"),
							Messages.get(ElementalWell.class, "no")) {
						@Override
						protected void onSelect(int index) {
							Embers2 embers2 = hero.belongings.getItem( Embers2.class );
							if (index == 0) {
								if(embers2.quantity()>=2){
									for (int i=0;i<4;i++){
										embers2.detach(hero.belongings.backpack);
									}
								}
							} else if (index == 1) {
								hide();
							}
						}
					});
				}
			});

		}else {
			GLog.i(Messages.get(ElementalWell.class, "complete"));
		}
		return true;
	}


	@Override
	public void beckon(int cell){
	}



	public String desc() {
		return Messages.get(this, "desc", 75542);
	}






}