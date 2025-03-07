package com.shatteredpixel.shatteredpixeldungeon.items.scrolls.newexotic;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.sprites.HeroSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.noosa.audio.Sample;

import java.util.ArrayList;

public class ExoticScrollModel extends Item {
    public static final String AC_READ	= "READ";
    public static final float TIME = 1;

    {
        image = ItemSpriteSheet.EXOTIC_NONE;
        stackable = true;
        defaultAction = AC_READ;
    }

    @Override
    public ArrayList<String> actions(Hero hero ) {
        ArrayList<String> actions = super.actions( hero );
        actions.add( AC_READ );
        return actions;
    }

    @Override
    public void execute( Hero hero, String action ) {
        super.execute( hero, action );
        hero.busy();
        if (action.equals( AC_READ )) {
            ((HeroSprite)curUser.sprite).read();
            hero.spend( TIME );
            Sample.INSTANCE.play( Assets.Sounds.READ );
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