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

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.Artifact;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.Ring;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndOptions;


public abstract class KindofMisc extends EquipableItem {

	private static final float TIME_TO_EQUIP = 1f;

	@Override
	public boolean doEquip(final Hero hero) {

		boolean equipFull = false;

		if ( this instanceof Artifact && hero.belongings.artifact != null){
			equipFull = true;
		}

		if (this instanceof Ring && hero.belongings.ring != null){
			equipFull = true;
		}
		if (equipFull) {

			final KindofMisc[] miscs = new KindofMisc[2];
			miscs[0] = hero.belongings.artifact;
			miscs[1] = hero.belongings.ring;

			final boolean[] enabled = new boolean[2];
			enabled[0] = miscs[0] != null;
			enabled[1] = miscs[1] != null;

			KindofMisc equipped;
			if(this instanceof Artifact){
				equipped = miscs[0];
			}else if(this instanceof Ring){
				equipped = miscs[1];
			}else{
				equipped = null;
			}

			int slot = Dungeon.quickslot.getSlot(KindofMisc.this);
			detach(hero.belongings.backpack);

			if (equipped.doUnequip(hero, true, false)) {
				if (equipped == miscs[0]){
					GLog.p(Messages.get(KindofMisc.class,"tips", this.toString(), miscs[0].toString()));
					hero.belongings.artifact = null;
				} else {
					GLog.p(Messages.get(KindofMisc.class,"tips", this.toString(), miscs[1].toString()));
					hero.belongings.ring = null;
				}
				doEquip(hero);
			} else {
				collect();
			}
			if (slot != -1) Dungeon.quickslot.setSlot(slot, KindofMisc.this);
			updateQuickslot();


			return false;
		} else {
			if (this instanceof Artifact){
				if (hero.belongings.artifact == null){
					hero.belongings.artifact = (Artifact) this;
				}
			} else if (this instanceof Ring){
				if (hero.belongings.ring == null){
					hero.belongings.ring = (Ring) this;
				}
			}

			detach( hero.belongings.backpack );

			Talent.onItemEquipped(hero, this);
			activate( hero );

			cursedKnown = true;
			if (cursed) {
				equipCursed( hero );
				GLog.n( Messages.get(this, "equip_cursed", this) );
			}

			hero.spendAndNext( TIME_TO_EQUIP );
			return true;

		}

	}

	@Override
	public boolean doUnequip(Hero hero, boolean collect, boolean single) {
		if (super.doUnequip(hero, collect, single)){

			if (hero.belongings.artifact == this) {
				hero.belongings.artifact = null;
			} else if (hero.belongings.ring == this){
				hero.belongings.ring = null;
			}
			return true;
		} else {
			return false;

		}
	}

	@Override
	public boolean isEquipped( Hero hero ) {
		return hero.belongings.artifact() == this
				|| hero.belongings.ring() == this;
	}

}
