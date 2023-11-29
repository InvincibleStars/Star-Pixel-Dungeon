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
package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.newmob.treearea;
import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.effects.Beam;
import com.shatteredpixel.shatteredpixeldungeon.items.Heap;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.sprites.RatSprite;
import com.shatteredpixel.shatteredpixeldungeon.tiles.DungeonTilemap;
import com.watabou.noosa.audio.Sample;
import java.util.HashSet;
public class WoodenCross extends Mob {
	{
		spriteClass = RatSprite.class;
		HP = HT = 100;
		maxLvl = 5;
		properties.add(Property.IMMOVABLE);
		state = PASSIVE;
	}

	private static HashSet<Char> chars = new HashSet<>();

	public static synchronized Char findChar( int pos ) {
		for (Char ch : chars){
			if (ch.pos == pos)
				return ch;
		}
		return null;
	}

	public void activate() {
		//Char target = Actor.findChar(pos);
		Char target = WoodenCross.findChar(pos);

		//find the closest char that can be aimed at
		if (target == null) {
			float closestDist = Float.MAX_VALUE;
			for (Char ch : Actor.chars()) {

				if (!(ch instanceof WoodenCross) || Dungeon.level.trueDistance(ch.pos,this.pos)>=5) {

				float curDist = Dungeon.level.trueDistance(pos, ch.pos);
				Ballistica bolt = new Ballistica(pos, ch.pos, Ballistica.PROJECTILE);
				if (ch.invisible > 0) {
					curDist += 1000;
					}
				if (bolt.collisionPos == ch.pos && curDist < closestDist) {
					target = ch;
					closestDist = curDist;
					}
				}
			}
		}
		Heap heap = Dungeon.level.heaps.get(pos);
		if (heap != null) heap.explode();
		if (target != null && target.HP!=target.HT && this.HP>=6 && Dungeon.level.trueDistance(this.pos,target.pos)<=3 ) {
			//if (Dungeon.level.heroFOV[pos] || Dungeon.level.heroFOV[target.pos]) {
				//Sample.INSTANCE.play(Assets.Sounds.RAY);
				//ShatteredPixelDungeon.scene().add(new Beam.DeathRay(DungeonTilemap.tileCenterToWorld(pos), target.sprite.center()));
			//}
			target.HP+=1;
			this.HP-=1;
		}
	}
	@Override
	protected boolean act() {
		alerted = false;
		activate();
		return super.act();
	}

	@Override
	public void beckon(int cell) {
		//do nothing
	}
}
