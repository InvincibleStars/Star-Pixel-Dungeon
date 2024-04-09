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

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.tier1;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.SpellSprite;
import com.shatteredpixel.shatteredpixeldungeon.items.newitem.ammo.Ammo;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;

public class Knuckle extends MeleeWeapon {

	public static final String AC_REPLACE	= "ac_replace";

	{
		image = ItemSpriteSheet.KUNCKLES;
		hitSound = Assets.Sounds.HIT_SLASH;
		hitSoundPitch = 1.1f;
		tier = 1;
		RCH=1;
		ACC = 1f;
		DLY=0.5f;

		//切换战斗模式
		defaultAction = AC_REPLACE;
	}

	int mode = 1;

	@Override
	public void execute(Hero hero, String action) {
		super.execute(hero, action);
		//防止装弹时产生瞄准标记
		usesTargeting = false;
		//动作
		if (action.equals(AC_REPLACE)) {
			if(mode==1){

			}

		}
	}




}