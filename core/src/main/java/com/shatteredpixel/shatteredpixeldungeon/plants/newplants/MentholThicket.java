package com.shatteredpixel.shatteredpixeldungeon.plants.newplants;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.gem.WaterGem;
import com.shatteredpixel.shatteredpixeldungeon.plants.Plant;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class MentholThicket extends Plant {

    {
        image = 23;
    }

    public static class Seed extends Plant.Seed {
        {
            image = ItemSpriteSheet.SEED_MENTHOLTHICKET;

            plantClass = MentholThicket.class;
        }
    }

    @Override
    public void activate( Char ch ) {

        int nSeeds = Random.NormalIntRange(1, 3);

        WaterGem sword = new WaterGem();

        ArrayList<Integer> candidates = new ArrayList<>();
        for (int i : PathFinder.NEIGHBOURS9){
            if (Dungeon.level.passable[pos+i]
                    && pos+i != Dungeon.level.entrance
                    && pos+i != Dungeon.level.exit){
                candidates.add(pos+i);
            }
        }

        for (int i = 0; i < nSeeds && !candidates.isEmpty(); i++){
            Integer c = Random.element(candidates);
            //Dungeon.level.drop(Generator.random(Generator.Category.SEED), c).sprite.drop(pos);
            Dungeon.level.drop(sword, c).sprite.drop(pos);
            candidates.remove(c);
        }

    }

}