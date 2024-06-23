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

package com.shatteredpixel.shatteredpixeldungeon.items.artifacts;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Burning;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.LockedFloor;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.ElmoParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.Bag;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.ScrollHolder;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.elixirs.ElixirOfAquaticRejuvenation;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfEnergy;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.Scroll;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndBag;
import com.watabou.noosa.Game;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Callback;
import com.watabou.utils.PathFinder;

import java.util.ArrayList;

public class WaterHeart extends Artifact {

	{
		image = ItemSpriteSheet.ARTIFACT_SPELLBOOK;

		levelCap = 10;

		//永久非诅咒物品
		cursed=false;

		charge = level+1;
		partialCharge = 0;
		chargeCap = level+1;

		defaultAction = AC_USE;
	}

	public static final String AC_USE = "use";

	@Override
	public ArrayList<String> actions( Hero hero ) {
		ArrayList<String> actions = super.actions( hero );
		if (isEquipped( hero ) && charge > 0 ) {
			actions.add(AC_USE);
		}
		return actions;
	}

	@Override
	public void execute( Hero hero, String action ) {

		super.execute( hero, action );

		if (action.equals( AC_USE )) {


			if (!isEquipped( hero )) {
				GLog.i( Messages.get(Artifact.class, "need_to_equip") );
			}else if (charge <= 0) {
				GLog.i( Messages.get(this, "no_charge") );
			} else if (cursed==true) {
				GLog.i( Messages.get(this, "cursed") );
			} else {
				charge--;
				int pos = hero.pos;
				int lesspos = 0;
				//for (int i : PathFinder.NEIGHBOURS9){
				for (int i : (level > 5 ? PathFinder.NEIGHBOURS24 : PathFinder.NEIGHBOURS8)) {
					if (hero.buff( Burning.class ) != null && (Dungeon.level.map[pos + i]) == Terrain.WATER) {
						Level.set(pos + i, Terrain.EMPTY);
						Buff.detach(hero, Burning.class);
						GLog.w(Messages.get(this, "fire_buff"));
					}else if (hero.buff( Burning.class ) == null && (Dungeon.level.map[pos + i]) == Terrain.WATER) {
						Buff.affect(hero, ElixirOfAquaticRejuvenation.AquaHealing.class).set((level>5?5:4));
						Level.set(pos + i, Terrain.EMPTY);
						GameScene.updateMap();
					} else {
						lesspos += 1;
					}
				}

				if (lesspos == 8 && level <= 5 || lesspos == 24 && level > 5) {
					charge++;
					GLog.w(Messages.get(WaterHeart.class, "lesspos"));
				}
			}
				updateQuickslot();

		}
	}

	//forces the reading of a regular scroll if the player tried to exploit by quitting the game when the menu was up
	public static class ExploitHandler extends Buff {
		{ actPriority = VFX_PRIO; }

		public Scroll scroll;

		@Override
		public boolean act() {
			curUser = Dungeon.hero;
			curItem = scroll;
			scroll.anonymize();
			Game.runOnRenderThread(new Callback() {
				@Override
				public void call() {
					scroll.doRead();
				}
			});
			detach();
			return true;
		}

		@Override
		public void storeInBundle(Bundle bundle) {
			super.storeInBundle(bundle);
			bundle.put( "scroll", scroll );
		}

		@Override
		public void restoreFromBundle(Bundle bundle) {
			super.restoreFromBundle(bundle);
			scroll = (Scroll)bundle.get("scroll");
		}
	}

	@Override
	protected ArtifactBuff passiveBuff() {
		return new bookRecharge();
	}
	
	@Override
	public void charge(Hero target, float amount) {
		if (charge < chargeCap){
			partialCharge += 0.1f*amount;
			if (partialCharge >= 1){
				partialCharge--;
				charge++;
				updateQuickslot();
			}
		}
	}

	@Override
	public Item upgrade() {
		chargeCap = (int)((level()+1)*0.6f)+2;
		return super.upgrade();
	}

	@Override
	public String desc() {
		String desc = super.desc();

		if (isEquipped(Dungeon.hero)) {
			if (cursed) {
				desc += "\n\n" + Messages.get(this, "desc_cursed");
			}
			/*
			if (level() < levelCap && scrolls.size() > 0) {
				desc += "\n\n" + Messages.get(this, "desc_index");
				desc += "\n" + "_" + Messages.get(scrolls.get(0), "name") + "_";
				if (scrolls.size() > 1)
					desc += "\n" + "_" + Messages.get(scrolls.get(1), "name") + "_";
			}

			 */
		}
		
		if (level() > 0) {
			desc += "\n\n" + Messages.get(this, "desc_empowered");
		}

		return desc;
	}

	private static final String SCROLLS =   "scrolls";

	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle(bundle);
		//bundle.put( SCROLLS, scrolls.toArray(new Class[scrolls.size()]) );
	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle(bundle);
		//scrolls.clear();
		//Collections.addAll(scrolls, bundle.getClassArray(SCROLLS));
	}

	public class bookRecharge extends ArtifactBuff{
		@Override
		public boolean act() {
			LockedFloor lock = target.buff(LockedFloor.class);
			if (charge < chargeCap && !cursed && (lock == null || lock.regenOn())) {
				//120 turns to charge at full, 80 turns to charge at 0/8
				float chargeGain = 1 / (120f - (chargeCap - charge)*5f);
				chargeGain *= RingOfEnergy.artifactChargeMultiplier(target);
				partialCharge += chargeGain;

				if (partialCharge >= 1) {
					partialCharge --;
					charge ++;

					if (charge == chargeCap){
						partialCharge = 0;
					}
				}
			}

			updateQuickslot();

			spend( TICK );

			return true;
		}
	}

	protected WndBag.ItemSelector itemSelector = new WndBag.ItemSelector() {

		@Override
		public String textPrompt() {
			return Messages.get(WaterHeart.class, "prompt");
		}

		@Override
		public Class<?extends Bag> preferredBag(){
			return ScrollHolder.class;
		}

		@Override
		public boolean itemSelectable(Item item) {
			return item instanceof Scroll && item.isIdentified() ;
		}

		@Override
		public void onSelect(Item item) {
			if (item != null && item instanceof Scroll && item.isIdentified()){
				Hero hero = Dungeon.hero;
				for (int i = 0; ( i <= 1 ); i++){
						hero.sprite.operate( hero.pos );
						hero.busy();
						hero.spend( 2f );
						Sample.INSTANCE.play(Assets.Sounds.BURNING);
						hero.sprite.emitter().burst( ElmoParticle.FACTORY, 12 );

						item.detach(hero.belongings.backpack);

						upgrade();
						GLog.i( Messages.get(WaterHeart.class, "infuse_scroll") );
						return;

				}
				GLog.w( Messages.get(WaterHeart.class, "unable_scroll") );
			} else if (item instanceof Scroll && !item.isIdentified()) {
				GLog.w( Messages.get(WaterHeart.class, "unknown_scroll") );
			}
		}
	};
}