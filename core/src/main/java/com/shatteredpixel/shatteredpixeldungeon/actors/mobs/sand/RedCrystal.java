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

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.effects.Beam;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.food.MysteryMeat;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CrabSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.newsprite.sand.RedCrystalSprite;
import com.watabou.noosa.audio.Sample;

public class RedCrystal extends Mob {
	
	{
		spriteClass = RedCrystalSprite.class;
		HP=HT=17;

		EXP=3;
		maxLvl=7;

		lootChance = 0.125f;

		state = WANDERING = new Waiting();

	}




	private int skillcolldown = -1;

	@Override
	protected boolean getCloser(int target) {
		return true;
	}

	@Override
	protected boolean getFurther(int target) { return true; }

	private class Waiting extends Wandering{}


	@Override
	protected boolean canAttack( Char enemy ) {
		if(Dungeon.level.distance(this.pos,enemy.pos)<=5 && skillcolldown<=0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public int attackProc(Char enemy, int damage) {
		if(Dungeon.level.distance(this.pos,enemy.pos)<=5){
			ShatteredPixelDungeon.scene().add(new Beam.DeathRay(this.sprite.center(), enemy.sprite.center()));
			Sample.INSTANCE.play( Assets.Sounds.ZAP );
			enemy.damage(3,this);
			skillcolldown=2;
		}
		return super.attackProc(enemy, damage);
	}

	@Override
	protected boolean act() {
		if(isAlive()){
			skillcolldown--;
		}
		return super.act();
	}

	@Override
	protected Item createLoot() {
		Item loot;
		if(Dungeon.LimitedDrops.SANDCRAB_LOOT.count!=0){
			loot = Generator.random(Generator.Category.WAND);
			Dungeon.LimitedDrops.SANDCRAB_LOOT.count++;
		}else{
			loot = new MysteryMeat();
		}
		return loot;
	}

}