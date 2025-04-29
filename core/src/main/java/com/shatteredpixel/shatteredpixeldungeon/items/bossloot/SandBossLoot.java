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

package com.shatteredpixel.shatteredpixeldungeon.items.bossloot;

import com.shatteredpixel.shatteredpixeldungeon.SPDSettings;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.ui.Icons;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndOptions;
import com.watabou.noosa.Game;
import com.watabou.utils.Callback;

public class SandBossLoot extends BossLoot {
	
	{
		image = ItemSpriteSheet.BLOB;
	}


	int a =0;

	@Override
	public boolean doPickUp(Hero hero, int pos) {
		if(a!=1){
			//SPDSettings.kuaijielanNum(5000);
			Game.runOnRenderThread(new Callback() {
				@Override
				public void call() {
					ShatteredPixelDungeon.scene().add(new WndOptions(Icons.get(Icons.WARNING),
							Messages.get(SandBossLoot.class, "warm"),
							Messages.get(SandBossLoot.class, "tip"),
							Messages.get(SandBossLoot.class, "yes"),
							Messages.get(SandBossLoot.class, "no")) {
						@Override
						protected void onSelect(int index) {
							if (index == 0) {
								GLog.i(Messages.get(SandBossLoot.class, "lootyes"));
								a=1;
							}}});}});
			return false;
		}else{
			super.doPickUp(hero, pos);
			BossLoot.infection+=2;
			GLog.i(Messages.get(SandBossLoot.class, "loot"));
			return true;
		}

	}
}