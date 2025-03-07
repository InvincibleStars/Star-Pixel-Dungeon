package com.shatteredpixel.shatteredpixeldungeon.items.potions.newexotic;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.sprites.HeroSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.noosa.audio.Sample;

import java.util.ArrayList;

public class ExoticPotionModel extends Item {
    public static final String AC_USE	= "USE";
    public static final float TIME = 1;
    {
        image = ItemSpriteSheet.EXOTIC_NONE;

        stackable = true;

        defaultAction = AC_USE;
    }

    @Override
    public ArrayList<String> actions(Hero hero ) {
        ArrayList<String> actions = super.actions( hero );
        actions.add( AC_USE );
        return actions;
    }

    @Override
    public void execute( Hero hero, String action ) {
        super.execute( hero, action );
        hero.busy();
        if (action.equals( AC_USE )) {
            hero.sprite.operate( hero.pos );
            hero.spend( TIME );
            Sample.INSTANCE.play( Assets.Sounds.DRINK );
            this.detach(hero.belongings.backpack);
            identify();
        }
    }
    @Override
    public boolean isUpgradable() {
        return false;
    }

    @Override
    public boolean isIdentified() {
        return true;
    }

    @Override
    public int value() {
        return 140 * quantity;
    }
}