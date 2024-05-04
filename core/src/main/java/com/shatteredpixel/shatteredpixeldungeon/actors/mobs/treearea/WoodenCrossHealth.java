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
package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.treearea;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.BuffWait;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.newbuff.AnkhBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.items.Ankh;
import com.shatteredpixel.shatteredpixeldungeon.items.Heap;
import com.shatteredpixel.shatteredpixeldungeon.items.bossloot.BossLoot;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.newsprite.tree.WoodenAnkhSprite;

import java.util.HashSet;

public class WoodenCrossHealth extends Mob {
	{
		spriteClass = WoodenAnkhSprite.class;
		HP = HT = 11+ BossLoot.infection*2 ;
		maxLvl = 0;
		EXP=0;
		properties.add(Property.IMMOVABLE);
		state = PASSIVE;

		loot = Ankh.class;
		lootChance = 0.039f;

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
		Char target = WoodenCrossHealth.findChar(pos);

		//find the closest char that can be aimed at
		if (target == null) {
			float closestDist = Float.MAX_VALUE;
			for (Char ch : Actor.chars()) {

				if (!(ch instanceof WoodenCrossHealth) || Dungeon.level.trueDistance(ch.pos,this.pos)>=5) {

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
		if (target != null && target.HP!=target.HT && this.HP>1 && Dungeon.level.trueDistance(this.pos,target.pos)<=3 ) {
			//if (Dungeon.level.heroFOV[pos] || Dungeon.level.heroFOV[target.pos]) {
				//Sample.INSTANCE.play(Assets.Sounds.RAY);
				//ShatteredPixelDungeon.scene().add(new Beam.DeathRay(DungeonTilemap.tileCenterToWorld(pos), target.sprite.center()));
			//}
			//GameScene.add( Blob.seed( target.pos, 4, Fire.class ) );
			Buff.prolong(target, AnkhBuff.class, BuffWait.T1);
			target.HP+=1;
			this.HP-=1;
		}
	}
	int start = 0;
	@Override
	protected boolean act() {
		this.HP=Math.max(this.HP,this.HP+1);
		if(start==0){
			this.sprite.add(CharSprite.State.ANKHBUFF);
			start=1;
		}
		alerted = false;
		activate();
		return super.act();
	}

	public void die ( Object cause ) {
		super.die(cause);
		this.sprite.remove(CharSprite.State.ANKHBUFF);
	}

	@Override
	public void beckon(int cell) {
		//do nothing
	}
}