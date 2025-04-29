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

package com.shatteredpixel.shatteredpixeldungeon.items.summon.mob;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.items.star.gem.Gem;
import com.shatteredpixel.shatteredpixeldungeon.items.science.PotionLevel;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;

public class SummonMob extends Mob {
	
	{
		alignment = Alignment.ALLY;
		state = HUNTING;
	}



	public int akef(){
		int truehppole=0;
		if(Dungeon.hero.belongings.getItem(PotionLevel.class)!=null){
			//truehppole=hpPole+ Dungeon.hero.belongings.getItem(PotionLevel.class).level;
		}
		return truehppole;
	}

	@Override
	protected boolean canAttack(Char enemy) {
		return super.canAttack(enemy);
	}

	//@Override
	public String desc() {
		String info = info();
		info += "\n\n" + Messages.get(Gem.class, "know");
		return info;
	}

	public String description() {
		//介绍尾部加入的东西
		String desc = Messages.get(SummonMob.class, "tips");
		desc += "\n\n" + Messages.get(this, "desc");
		return desc;
	}

}