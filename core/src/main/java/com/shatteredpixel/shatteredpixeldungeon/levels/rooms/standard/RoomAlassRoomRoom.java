package com.shatteredpixel.shatteredpixeldungeon.levels.rooms.standard;

import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.RatKing;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.levels.painters.Painter;

public class RoomAlassRoomRoom extends StandardRoom {

    @Override
    public int maxHeight() { return 4; }
    public int maxWidth() { return 4; }

    public void paint(Level level ) {

        Painter.fill( level, this, Terrain.WALL );
        Painter.fill( level, this, 1 , Terrain.EMPTY );


        RatKing king = new RatKing();
        king.pos = level.pointToCell(random( 2 ));
        level.mobs.add( king );

       // for (Door door : connected.values()) {
        //    door.set(Door.Type.REGULAR);
        //}



        }


}
