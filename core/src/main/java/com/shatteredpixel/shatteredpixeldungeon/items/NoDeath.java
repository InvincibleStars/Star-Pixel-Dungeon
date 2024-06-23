package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Burning;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.Artifact;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.WaterHeart;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.elixirs.ElixirOfAquaticRejuvenation;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.utils.PathFinder;

import java.util.ArrayList;

public class NoDeath extends Item {

    {
        image = ItemSpriteSheet.ANKH;
        defaultAction=AC_USE;
    }

    public static final String AC_USE = "use";

    @Override	//不能被扔出或丢弃
    public ArrayList<String> actions(Hero hero ) {
        ArrayList<String> actions = super.actions( hero );
        actions.remove(AC_DROP);
        actions.remove(AC_THROW);
        actions.add(AC_USE);
        return actions;
    }

    public static int noDeath = 0;

    public static ItemSprite.Glowing WRITE = new ItemSprite.Glowing( 0xFFFFFF ,1f);

    public static ItemSprite.Glowing BLACK = new ItemSprite.Glowing( 0x000000 ,1f);

    public ItemSprite.Glowing glowing() {
        if(noDeath==1){
            return WRITE;
        }else {
            return BLACK;
        }
    }


    @Override
    public void execute( Hero hero, String action ) {

        super.execute( hero, action );

        if (action.equals( AC_USE )) {
            if(noDeath==0){
                noDeath=1;
                GLog.w(Messages.get(this, "on"));

            }else{
                noDeath=0;
                GLog.w(Messages.get(this, "off"));
            }
            updateQuickslot();
        }
    }






}