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

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.tier4;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class Nemesis extends MeleeWeapon {
	
	{
		image = ItemSpriteSheet.GOLD;
		hitSound = Assets.Sounds.HIT_SLASH;
		hitSoundPitch = 1f;

		tier = 4;
	}
	int a;
	public int a(int lvl){
		if(lvl>=7){
			a=1;
		}else{
			a=7-lvl;
		}
		return  a;
	}

	@Override
	public int max(int lvl) { return a*(tier+1) + lvl * (tier+1) + (masteryPotionBonus*2); }



	//随着升级距离越来越短
	@Override
	public int image() {
		if(this.level() ==3){
			image = ItemSpriteSheet.GOLD;
			RCH=1;
		}else if(this.level() ==2){
			image = ItemSpriteSheet.GOLD;
			RCH=2;
		}else if(this.level() ==1){
			image = ItemSpriteSheet.GOLD;
			RCH=3;
		}else{
			image = ItemSpriteSheet.GOLD;
			RCH=4;
		}
		return image;
	}

	@Override
	public String desc() {
		String desc = super.desc();

		if (isEquipped (Dungeon.hero)){
			desc += "\n\n";
			if (cursed)
				desc += Messages.get(this, "desc_cursed");
			else if (level() == 4)
				desc += Messages.get(this, "desc_1");
			else if (level() == 3)
				desc += Messages.get(this, "desc_1");
			else if (level() == 2)
				desc += Messages.get(this, "desc_1");
			else if (level() == 1)
				desc += Messages.get(this, "desc_2");
			else
				desc += Messages.get(this, "desc_3");
		}

		return desc;
	}
}