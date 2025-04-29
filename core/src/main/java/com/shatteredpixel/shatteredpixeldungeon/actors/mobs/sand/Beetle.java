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

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.sand;

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourArmor;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.items.areaitem.Shell;
import com.shatteredpixel.shatteredpixeldungeon.sprites.newsprite.sand.ArmorBeetleSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.newsprite.sand.BeetleSprite;
import com.watabou.utils.Random;

public class Beetle extends Mob {

	{
		spriteClass = BeetleSprite.class;
		HP=HT=15;
		loot = new Shell();
		lootChance = 0.06f;

		EXP=2;
		maxLvl=7;
	}

	@Override
	protected boolean act() {
		Buff.affect(this, FlavourArmor.class);
		return super.act();
	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange(2,7);
	}

	@Override
	public void damage(int dmg, Object src) {
		dmg*=0.75f;
		super.damage(dmg, src);
	}
}