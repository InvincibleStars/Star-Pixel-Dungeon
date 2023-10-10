package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.newboss;

import static com.shatteredpixel.shatteredpixeldungeon.Dungeon.hero;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.BuffWait;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Burning;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.LockedFloor;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Monk;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.RatSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.watabou.noosa.Image;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class ModelBoss extends Mob {

   //基础数据
    {
        spriteClass= RatSprite.class;
        HT=HP=80;
        EXP=50;
        //BOSS词条
        properties.add(Property.BOSS);
        defenseSkill = 0;


    }

    //格挡，12-15回合
    protected float SkillCollDown = 0;
    //回血，15-20回合
    protected float SkillCollDown2 = 0;
    //点燃，20-25回合
    protected float SkillCollDown3 = 0;

    //动作逻辑
    @Override
    public boolean act() {
        //背水一战
        LockedFloor lock = hero.buff(LockedFloor.class);

        //格挡攻击
        if (buff(Monk.Focus.class) == null && state == HUNTING && SkillCollDown <= 0) {
        Buff.affect( this, Monk.Focus.class );
        }

        //额外回血
        if (SkillCollDown2 <= 0 && this.HP<=HT-8 ) {
            this.HP+=8;
            SkillCollDown2=Random.IntRange(12,15);
        }else if (HP>=HT-8 && SkillCollDown2 <= 0){
            this.HP=this.HT;
            SkillCollDown2=Random.IntRange(12,15);

        }

        if (SkillCollDown3 <=0) {
           // Buff.affect(this, Burning.class );
           // hero.buff(Burning.class).reignite(this);
            SkillCollDown3=Random.IntRange(12,15);
        }


         //未睡眠前
         if (state != SLEEPING){
             Dungeon.level.seal();
         }
         return super.act();
    }

    //每回合进行的效果
    @Override
    protected void spend( float time ) {
        this.HP+=1;
        if (Dungeon.level.heroFOV[pos] ){
            Buff.affect( hero, Burning.class).reignite(this);
        }
        //Buff.affect( hero, Burning.class).reignite(this);
        Buff.affect( this, Invisibility.class, BuffWait.T10);
        SkillCollDown -= time;
        SkillCollDown2 -= time;
        SkillCollDown3 -= time;
        super.spend( time );
    }

    @Override
    public int attackSkill( Char target ) {
        return 0;
    }

    @Override
    public int defenseSkill( Char enemy ) {
        //判断存在效果即为无限闪避
        if (buff(Monk.Focus.class) != null && paralysed == 0 && state != SLEEPING){
            return INFINITE_EVASION;
        }
        return super.defenseSkill( enemy );
    }

    //招架动作
    @Override
    public String defenseVerb() {
        Monk.Focus f = buff(Monk.Focus.class);
        if (f == null) {
            return super.defenseVerb();
        } else {
            f.detach();
            if (sprite != null && sprite.visible) {
                Sample.INSTANCE.play(Assets.Sounds.HIT_PARRY, 1, Random.Float(0.96f, 1.05f));
            }
            //修改冷却
            SkillCollDown = Random.NormalFloat( 6, 7 );
            return Messages.get(this, "parried");
        }
    }

    private static String FOCUS_COOLDOWN = "focus_cooldown";
    private static String FOCUS_COOLDOWN3 = "focus_cooldown";
    private static String FOCUS_COOLDOWN2 = "focus_cooldown";

    @Override
    public void storeInBundle( Bundle bundle ) {
        super.storeInBundle( bundle );
        bundle.put( FOCUS_COOLDOWN, SkillCollDown );
        bundle.put( FOCUS_COOLDOWN2, SkillCollDown2 );
        bundle.put( FOCUS_COOLDOWN3, SkillCollDown3 );

    }

    @Override
    public void restoreFromBundle( Bundle bundle ) {
        super.restoreFromBundle( bundle );
        SkillCollDown = bundle.getInt( FOCUS_COOLDOWN );
        SkillCollDown2 = bundle.getInt( FOCUS_COOLDOWN2 );
        SkillCollDown3 = bundle.getInt( FOCUS_COOLDOWN3 );

    }

    //内置技能
    public static class Focus extends Buff {

        {
            type = buffType.POSITIVE;
            announced = true;
        }

        @Override
        public int icon() {
            return BuffIndicator.MIND_VISION;
        }

        @Override
        public void tintIcon(Image icon) {
            icon.hardlight(0.25f, 1.5f, 1f);
        }

        @Override
        public String toString() {
            return Messages.get(this, "name");
        }

        @Override
        public String desc() {
            return Messages.get(this, "desc");
        }
    }

    {

        //immunities.add( Burning.class );
    }





}
