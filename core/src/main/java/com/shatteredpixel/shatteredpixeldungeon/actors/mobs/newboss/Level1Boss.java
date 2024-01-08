package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.newboss;

import static com.shatteredpixel.shatteredpixeldungeon.Dungeon.hero;
import static com.shatteredpixel.shatteredpixeldungeon.Dungeon.level;

import java.util.ArrayList;
import java.util.List;
//import java.util.Random;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Challenges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.Statistics;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Amok;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.BuffWait;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Burning;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Dread;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.LockedFloor;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Paralysis;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Sleep;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Terror;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Vertigo;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Weakness;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.DwarfKing;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Monk;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Thief;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.YogDzewa;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.Ghost;
import com.shatteredpixel.shatteredpixeldungeon.effects.Pushing;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.ShadowParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.DriedRose;
import com.shatteredpixel.shatteredpixeldungeon.items.food.ChargrilledMeat;
import com.shatteredpixel.shatteredpixeldungeon.items.keys.SkeletonKey;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfHealing;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.GooSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.RatSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.WraithSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.BossHealthBar;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.Image;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;
import com.watabou.utils.Reflection;

import java.util.ArrayList;

public class Level1Boss extends Mob {

    //基础数据
    {

        //properties.add(Property.NOHP);
        spriteClass = RatSprite.class;
        HT = HP = 120;
        EXP = 175;
        //BOSS词条
        properties.add(Property.BOSS);
        attackskill=100;
        defenseSkill = 0;
        maxLvl=7;

        baseSpeed=0.5f;

        loot = new PotionOfHealing();
        lootChance = 1f; //by default, see rollToDropLoot()
       // createLoot();


    }

    //召唤物
    private Class<?extends Mob> summon;
    protected float SkillCollDown = 0;

    private int summonCooldown = 7;

    //召唤计数
    public static int SummonCount = 0;

    //召唤物组
    public static class Call1 extends BossCall {
        {

            spriteClass = WraithSprite.class;
            HT = HP = 10 + (Level1Boss.SummonCount * 5);
            state = HUNTING;
            attackskill = 500;
            defenseSkill = 0;

        }

        @Override
        public int damageRoll() {
            return Random.NormalIntRange( 9, 16 );
        }

        /*
        public void detach(){
         super.detach();
         for ( Mob boss : Dungeon.level.mobs){
             if( boss instanceof Level1Boss){
                 boss.HP +=5;
             }

         }
        }

         */
    }


    //动作逻辑
    @Override
    public boolean act() {
        //背水一战
        LockedFloor lock = hero.buff(LockedFloor.class);

//        while (summonCooldown <= 0){
//
//            Class<?extends Mob> cls = regularSummons.remove(0);
//            Mob summon = Reflection.newInstance(cls);
//            regularSummons.add(cls);
//
//            int spawnPos = -1;
//            for (int i : PathFinder.NEIGHBOURS8){
//                if (Actor.findChar(pos+i) == null){
//                    if (spawnPos == -1 || Dungeon.level.trueDistance(Dungeon.hero.pos, spawnPos) > Dungeon.level.trueDistance(Dungeon.hero.pos, pos+i)){
//                        spawnPos = pos + i;
//                    }
//                }
//            }
//
//            if (spawnPos != -1 && this.BOSSCOUNT<=1) {
//                summon.pos = spawnPos;
//                GameScene.add( summon );
//                Actor.addDelayed( new Pushing( summon, pos, summon.pos ), -1 );
//                summon.beckon(Dungeon.hero.pos);
//                this.BOSSCOUNT+=1;
//
//                summonCooldown = 5;
//
//            } else {
//                break;
//            }
//        }

//        //格挡攻击
//        if (buff(Monk.Focus.class) == null && state == HUNTING && SkillCollDown <= 0) {
//            Buff.affect(this, Monk.Focus.class);
//        }



        //未睡眠前
//        if (state != SLEEPING) {
//            Dungeon.level.seal();
//        }
        return super.act();
    }

    //每回合进行的效果
    @Override
    protected void spend(float time) {

//        if(this.HP<1){
//            super.die(true);
//        }
//
//        if(Dungeon.level.trueDistance(Dungeon.hero.pos, this.pos) >=5 ){
//            Buff.affect(this, Weakness.class, BuffWait.T3);
//        }
//
//        summonCooldown -= 1 ;

        super.spend(time);
    }

    @Override
    public int attackSkill(Char target) {
        return 0;
    }


    //private static String FOCUS_COOLDOWN = "focus_cooldown";

    @Override
    public void storeInBundle(Bundle bundle) {
        super.storeInBundle(bundle);
        //bundle.put(FOCUS_COOLDOWN, SkillCollDown);

    }

    @Override
    public void restoreFromBundle(Bundle bundle) {
        super.restoreFromBundle(bundle);
        //SkillCollDown = bundle.getInt(FOCUS_COOLDOWN);
    }

//    //内置技能
//    public static class Focus extends Buff {
//
//        {
//            type = buffType.POSITIVE;
//            announced = true;
//        }
//
//        @Override
//        public int icon() {
//            return BuffIndicator.MIND_VISION;
//        }
//
//        @Override
//        public void tintIcon(Image icon) {
//            icon.hardlight(0.25f, 1.5f, 1f);
//        }
//
//        @Override
//        public String toString() {
//            return Messages.get(this, "name");
//        }
//
//        @Override
//        public String desc() {
//            return Messages.get(this, "desc");
//        }
//    }

    {

        //immunities.add( Burning.class );
    }

    //产生单位
//    private ArrayList<Class> regularSummons = new ArrayList<>();
//
//    {
//        for (int i = 0; i < 6; i++) {
//            if (i >= Statistics.spawnersAlive) {
//                regularSummons.add(Level1Boss.Call1.class);
//            } else {
//                regularSummons.add(Level1Boss.Call1.class);
//            }
//        }
//        Random.shuffle(regularSummons);
//    }

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
        public void detach() {
            super.detach();
            for (Mob m : Dungeon.level.mobs){
                if (m instanceof Level1Boss){
                    int damage = m.HT/12;
                    m.damage(damage, this);
                }
            }
        }
    }

    @Override
    public void die(Object cause){
        super.die(cause);
        Dungeon.level.unseal();
        GameScene.bossSlain();
        Dungeon.level.drop( new SkeletonKey( Dungeon.depth ), pos ).sprite.drop();

        Badges.validateBossSlain();
        GLog.n( Messages.get(this, "defeated") );
        GLog.n( Messages.get(this, "defeated") );

        GLog.n( Messages.get(this, "die"));
        for(int i=0;i<=Random.Int(3,10);i=i+1){
            level.drop(Generator.randomUsingDefaults(Generator.Category.POTION), pos).sprite.drop();
        }

        Badges.validateBossSlain();
    }


    @Override
    public void notice() {
        super.notice();
        //if (!BossHealthBar.isAssigned()) {
            BossHealthBar.assignBoss(this);
            GLog.n(Messages.get(this, "notice"));
            for (Char ch : Actor.chars()){
                if (ch instanceof DriedRose.GhostHero){
                    ((DriedRose.GhostHero) ch).sayBoss();
                //}
            }
        }
    }

}
