package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.boss;

import static com.shatteredpixel.shatteredpixeldungeon.Dungeon.hero;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Blindness;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Healing;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.RemoteImmunization;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.LockedFloor;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Slow;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.SoulBurning;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.DriedRose;
import com.shatteredpixel.shatteredpixeldungeon.items.keys.IronKey;
import com.shatteredpixel.shatteredpixeldungeon.items.keys.SkeletonKey;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.WraithSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.BossHealthBar;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class GateKeeper extends Mob {
    {
        HP=HT=85;
        spriteClass = WraithSprite.class;
        properties.add(Property.BOSS);
    }

    protected int burningCooldown = 15;
    protected int blindnessCooldown = Random.Int(20,30);
    protected boolean enraged = false;

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(3,12);
    }

    @Override
    public boolean act() {
        LockedFloor lock = hero.buff(LockedFloor.class);
        if (lock != null) {
            lock.removeTime(1f);
        }
        //半血免疫远程伤害
        if(HP*2<HT){
            Buff.affect(this, RemoteImmunization.class);
        }

        blindnessCooldown--;
        burningCooldown--;

        if(blindnessCooldown<=0){
            Buff.affect(hero, Blindness.class,8f);
            GLog.n(Messages.get(this,"blindness"));
            blindnessCooldown=Random.Int(20,30);
        }

        if(burningCooldown<=0){
            Buff.affect(this,SoulBurning.class,10f);
            burningCooldown=Random.Int(30,40);
        }

        if(enemy!=null) {
            if (Dungeon.level.distance(this.pos, enemy.pos) >= 4) {
                Buff.prolong(enemy, Slow.class, 3f);
                GLog.n(Messages.get(this, "far"));
            }
        }

        return super.act();
    }

    @Override
    public int defenseProc(Char enemy, int damage) {
        if (this.buffs(RemoteImmunization.class)!=null&&Dungeon.level.distance(this.pos,enemy.pos)>1&&enraged==false) {
            sprite.showStatus(CharSprite.NEGATIVE, Messages.get(this, "enraged"));
            enraged = true;
        }
        return super.defenseProc(enemy, damage);
    }

    @Override
    public void die( Object cause ) {
        super.die( cause );
        Dungeon.level.unseal();
        GameScene.bossSlain();
        Dungeon.level.drop(new IronKey(Dungeon.depth),pos).sprite.drop();
        yell( Messages.get(this, "defeated") );
    }
    @Override
    public void notice() {
        super.notice();
        BossHealthBar.assignBoss(this);
        GLog.n(Messages.get(this, "notice"));
        for (Char ch : Actor.chars()){
            if (ch instanceof DriedRose.GhostHero){
                ((DriedRose.GhostHero) ch).sayBoss();
            }
        }
    }


    private static String BURNING_CD = "burningCooldown";
    private static String BLINDNESS_CD = "blindnesscooldown";
    @Override
    public void storeInBundle(Bundle bundle) {
        super.storeInBundle(bundle);
        bundle.put(BLINDNESS_CD,blindnessCooldown);
        bundle.put(BURNING_CD,burningCooldown);
    }
    @Override
    public void restoreFromBundle(Bundle bundle) {
        super.restoreFromBundle(bundle);
        burningCooldown = bundle.getInt( BURNING_CD );
        blindnessCooldown = bundle.getInt( BLINDNESS_CD );
    }
}