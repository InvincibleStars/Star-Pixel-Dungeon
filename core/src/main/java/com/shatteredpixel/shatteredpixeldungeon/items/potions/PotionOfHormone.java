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

package com.shatteredpixel.shatteredpixeldungeon.items.potions;

import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Blindness;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.BuffWait;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.newbuff.CutoffSpeed;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.newbuff.ShortPower;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;

public class PotionOfHormone extends Potion {

	{
		icon = ItemSpriteSheet.Icons.POTION_STRENGTH;

		unique = true;

		identify();

	}
	
//	@Override
//	public void apply( Hero hero ) {
//		/*
//			//ban掉了失明对于符文的影响
//			else if (hero.buff( Blindness.class ) != null) {
//				Buff.detach( hero, Blindness.class );
//				GLog.w( Messages.get(this, "blinded") );
//			*/
//
//		hero.STR++;
//		GLog.p( Messages.get(this, "msg_2") );
//
//	}

	@Override
	public int value() {
		return isKnown() ? 50 * quantity : super.value();
	}

	@Override
	public int energyVal() {
		return isKnown() ? 8 * quantity : super.energyVal();
	}




	@Override
	public void apply( Hero hero ) {

			//只有不存在buff的情况下才会提升力量
			if (hero.buff( ShortPower.class ) == null) {
				hero.STR++;
				Buff.affect(hero, ShortPower.class, 300f);
				GLog.p( Messages.get(this, "msg_1") );
			}else{
				//只延长buff时间
				Buff.affect(hero, ShortPower.class, 300f);
				GLog.p( Messages.get(this, "msg_2") );
			}




	}







}