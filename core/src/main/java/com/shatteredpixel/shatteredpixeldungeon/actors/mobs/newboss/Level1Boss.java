package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.newboss;

import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.Statistics;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.BuffWait;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Weakness;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.effects.Pushing;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.DriedRose;
import com.shatteredpixel.shatteredpixeldungeon.items.bossloot.BossLoot;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfHealing;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.WraithSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.BossHealthBar;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.utils.Bundle;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;
import com.watabou.utils.Reflection;

import java.util.ArrayList;

public class Level1Boss extends Mob {
    {
        spriteClass = WraithSprite.class;
        HT = HP = 85;
        EXP = 175;
        properties.add(Property.BOSS);
        defenseSkill = 5;
        maxLvl=7;
        baseSpeed=1f;
        loot = new PotionOfHealing();
        lootChance = 1f;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange( 1, 9 );
    }

    public static int[] InvincibleNode = new int[]{125,129,157,};
    protected float SkillCollDown = 15;
    private int summonCooldown = 10;
    //召唤计数
    //召唤物组
    public static int SummonCount = 0;
    public static class Call1 extends BossCall {
        {
            spriteClass = WraithSprite.class;
            HT = HP = 10 + (Level1Boss.SummonCount * 5);
            EXP=0;
            state = HUNTING;
            attackskill = 14;
            defenseSkill = 0;
        }
        @Override
        public int damageRoll() {
            return Random.NormalIntRange( 1, 6 );
        }
    }

    @Override
    public int attackSkill( Char target ) {
        return 14;
    }


    @Override
    public boolean act() {
        //背水一战
        //LockedFloor lock = Dungeon.hero.buff(LockedFloor.class);
        //if (lock != null) lock.removeTime(1f);
        while (summonCooldown <= 0){
            Class<?extends Mob> cls = regularSummons.remove(0);
            Mob summon = Reflection.newInstance(cls);
            regularSummons.add(cls);
            int spawnPos = -1;
            for (int i : PathFinder.NEIGHBOURS8){
                if (Actor.findChar(pos+i) == null){
                    if (spawnPos == -1 || Dungeon.level.trueDistance(Dungeon.hero.pos, spawnPos) > Dungeon.level.trueDistance(Dungeon.hero.pos, pos+i)){
                        spawnPos = pos + i;
                    }}}
            if (spawnPos != -1 && this.BOSSCOUNT<=1) {
                summon.pos = spawnPos;  GameScene.add( summon );
                Actor.addDelayed( new Pushing( summon, pos, summon.pos ), -1 );
                summon.beckon(Dungeon.hero.pos);
                this.BOSSCOUNT+=1;  summonCooldown = 15;}
            else {break;}
        }
        if (state != SLEEPING) {
            Dungeon.level.seal();}return super.act();
    }
    @Override
    protected void spend(float time) {
        if(this.HP<0){
            super.die(true);
        }
        if(Dungeon.level.trueDistance(Dungeon.hero.pos, this.pos) >=5 ){
            Buff.affect(this, Weakness.class, BuffWait.T3);
        }
        summonCooldown -= 1;
        super.spend(time);}
    //寄存和读取
    private static String FOCUS_COOLDOWN = "focus_cooldown";
    @Override
    public void storeInBundle(Bundle bundle) {
        super.storeInBundle(bundle);
        bundle.put(FOCUS_COOLDOWN, SkillCollDown);
    }
    @Override
    public void restoreFromBundle(Bundle bundle) {
        super.restoreFromBundle(bundle);
        SkillCollDown = bundle.getInt(FOCUS_COOLDOWN);
    }
    //单位
    private ArrayList<Class> regularSummons = new ArrayList<>();
           {for (int i = 0; i < 6; i++) {
            if (i >= Statistics.spawnersAlive) {
                regularSummons.add(Level1Boss.Call1.class);
            }else {
                regularSummons.add(Level1Boss.Call1.class);
            }
        }
        Random.shuffle(regularSummons);
           }
    public static class Level1BOSSDamager extends Buff {
        @Override
        public boolean act() {
            if (target.alignment != Alignment.ENEMY){
                detach();
            }
            spend( TICK );
            return true;
        }

        @Override
        public void detach(){super.detach();
            for (Mob m : Dungeon.level.mobs){
                if (m instanceof Level1Boss){
                    int damage = m.HT/12;m.damage(damage, this);
                }
            }
        }
           }
    @Override
    public void die(Object cause){
        super.die(cause);
        Dungeon.level.unseal();
        GameScene.bossSlain();
        //Dungeon.level.drop( new SkeletonKey( Dungeon.depth ), pos ).sprite.drop();
        //Dungeon.level.drop( new SandBossLoot(), pos ).sprite.drop();
        //Dungeon.level.drop( new IronKey( Dungeon.depth ), pos ).sprite.drop();
        //GLog.n( Messages.get(this, "defeated") );
        BossLoot.infection+=6;
        GLog.n( Messages.get(this, "die"));
        GLog.n( Messages.get(this, "none_kill"));
        Badges.validateBossSlain();
    }
    @Override
    public void notice() {
        //super.notice();
            BossHealthBar.assignBoss(this);
            GLog.n(Messages.get(this, "notice"));
            for (Char ch : Actor.chars()){
                if (ch instanceof DriedRose.GhostHero){
                    ((DriedRose.GhostHero) ch).sayBoss();
                }
            }
           }
}