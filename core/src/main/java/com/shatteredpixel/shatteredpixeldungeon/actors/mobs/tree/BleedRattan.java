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

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.tree;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Weakness;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.areaitem.MobLoot;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfHealing;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.newsprite.tree.AngryVineSprite;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.Camera;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class BleedRattan extends Rattan {

	{
		spriteClass = AngryVineSprite.class;
		
		HP = HT = 20;
		EXP = 4;


		lootChance = 1f;
	}

	private int cooldown = 10;

	@Override
	protected boolean act() {
		if(isAlive()){
			cooldown--;
		}
		if(cooldown<=0 && enemy!=null){
			if(Dungeon.level.distance(this.pos,enemy.pos)<=5){
				hit();
			}
		}
		return super.act();
	}

	private void hit(){
		cooldown=Random.Int(5,12);
		int a = damageRoll();
		a*=1.5f;
		enemy.damage(a,this);
		Camera.main.shake( 3, 0.2f );
		this.sprite.showStatus(CharSprite.NEGATIVE, "重击！");
		GLog.n(Messages.get( this,"hit"));
		Sample.INSTANCE.play( Assets.Sounds.HIT );
	}
	
	@Override
	public int attackProc( Char enemy, int damage ) {
		damage = super.attackProc( enemy, damage );
		HP=Math.min(HT,HP+damage/3);
		
		return damage;
	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 1, 12 );
	}

	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, 3);
	}

	@Override
	protected Item createLoot() {
		Item loot;
		float a = Random.Float();
		if(a<=(1f * ((6f - Dungeon.LimitedDrops.BLEEDRATTAN_LOOT.count) / 6f))){
			loot = new PotionOfHealing();
			Dungeon.LimitedDrops.BLEEDRATTAN_LOOT.count++;
		} else {
			loot = new MobLoot().quantity(Random.Int(1,8));
		}
		return loot;
	}
}