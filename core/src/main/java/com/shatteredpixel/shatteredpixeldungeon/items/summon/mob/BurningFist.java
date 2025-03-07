package com.shatteredpixel.shatteredpixeldungeon.items.summon.mob;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Blob;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Fire;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Burning;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.FistSprite;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class BurningFist extends SummonMob {

    {
        spriteClass = FistSprite.Burning.class;

        properties.add(Property.FIERY);
    }

    @Override
    public boolean act() {

        boolean result = super.act();

        if (Dungeon.level.map[pos] == Terrain.WATER){
            Level.set( pos, Terrain.EMPTY);
            GameScene.updateMap( pos );
            CellEmitter.get( pos ).burst( Speck.factory( Speck.STEAM ), 10 );
        }

        //1.67 evaporated tiles on average
        int evaporatedTiles = Random.chances(new float[]{0, 1, 2});

        for (int i = 0; i < evaporatedTiles; i++) {
            int cell = pos + PathFinder.NEIGHBOURS8[Random.Int(8)];
            if (Dungeon.level.map[cell] == Terrain.WATER){
                Level.set( cell, Terrain.EMPTY);
                GameScene.updateMap( cell );
                CellEmitter.get( cell ).burst( Speck.factory( Speck.STEAM ), 10 );
            }
        }

        for (int i : PathFinder.NEIGHBOURS9) {
            int vol = Fire.volumeAt(pos+i, Fire.class);
            if (vol < 4 && !Dungeon.level.water[pos + i] && !Dungeon.level.solid[pos + i]){
                GameScene.add( Blob.seed( pos + i, 4 - vol, Fire.class ) );
            }
        }

        return result;
    }

    //@Override
    protected void zap() {
        spend( 1f );

        if (Dungeon.level.map[enemy.pos] == Terrain.WATER){
            Level.set( enemy.pos, Terrain.EMPTY);
            GameScene.updateMap( enemy.pos );
            CellEmitter.get( enemy.pos ).burst( Speck.factory( Speck.STEAM ), 10 );
        } else {
            Buff.affect( enemy, Burning.class ).reignite( enemy );
        }

        for (int i : PathFinder.NEIGHBOURS9){
            if (!Dungeon.level.water[enemy.pos+i] && !Dungeon.level.solid[enemy.pos+i]){
                int vol = Fire.volumeAt(enemy.pos+i, Fire.class);
                if (vol < 4){
                    GameScene.add( Blob.seed( enemy.pos + i, 4 - vol, Fire.class ) );
                }
            }
        }

    }

}
