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
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Burning;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Healing;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class Quiet extends MeleeWeapon {
	
	{
		image = ItemSpriteSheet.GREENDAGGER;
		hitSound = Assets.Sounds.HIT_SLASH;
		hitSoundPitch = 1f;

		tier = 1;
	}

	@Override
	public int min(int lvl) { return 1; }

	@Override
	public int max(int lvl) { return (tier+1) + lvl*(tier+1) + masteryPotionBonus; }


/*
	@Override
	public int image() {
		if(this.level() >8){
			image = ItemSpriteSheet.LONGSWORD;
			RCH+=2;
		}else if(this.level() >7){
			image = ItemSpriteSheet.SAI;
		}


		else{
			image = ItemSpriteSheet.LONGSWORD;
		}
		return super.level();
	}
 */

	@Override
	public int proc(Char attacker, Char defender, int damage ) {
		Buff.affect(defender, Healing.class).setHeal((int)(0.10f*defender.HT),0.15f,2);
		return super.proc(attacker, defender, damage);
	}


	@Override
	public String statsInfo(){
		if(this.level() >4){
			return "yes";
		}else{
			return "null";
		}
	}


}