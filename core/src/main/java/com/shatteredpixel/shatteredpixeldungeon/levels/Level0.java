package com.shatteredpixel.shatteredpixeldungeon.levels;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.newmob.sandarea.BlackWorm;
import com.shatteredpixel.shatteredpixeldungeon.items.Heap;
import com.shatteredpixel.shatteredpixeldungeon.items.Torch;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.PotionOfCleansing;
import com.shatteredpixel.shatteredpixeldungeon.levels.painters.HallsPainter;
import com.shatteredpixel.shatteredpixeldungeon.levels.painters.Painter;
import com.sun.tools.javac.jvm.Items;
import com.watabou.utils.Random;

public class Level0 extends Level {

    private static final int SIZE = 25;


    {
        color1 = 0x534f3e;
        color2 = 0xb9d661;
    }

    //定义地块
 private int mapToTerrain(int code){
        switch (code){
            case 1:
            default:
                return Terrain.EMPTY;
                //墙壁
            case 64:
                return Terrain.WALL;
            case 68:
                return Terrain.WALL;
            case 21:
                return Terrain.PEDESTAL;
                //水分
            case 257:
                return Terrain.WATER;
                //书架
            case 66:
                return Terrain.BOOKSHELF;
                //上楼梯
            case 85:
                return Terrain.ENTRANCE;
                //下楼梯
            case 17:
                return Terrain.EXIT;
                //门
            case 80:
                return Terrain.DOOR;
                //井
            case 18:
                return Terrain.EMPTY_WELL;
                //地板
            case 4:
                return Terrain.EMPTY_SP;
                //雕像
            case 97:
                return Terrain.STATUE;
                //地毯上的雕像
            case 98:
                return Terrain.STATUE_SP;
                //炼金锅
            case 99:
                return Terrain.ALCHEMY;
                //高草
            case 104:
                return Terrain.HIGH_GRASS;
                //矮草
            case 2:
                    return Terrain.GRASS;
            case 1017:
                return Terrain.SIGN;
        }
    }

    //地形布局（硬编码布局？）
    private static final int[] pre_map = {
            64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 66, 66, 66, 66, 66, 64,
            64, 0, 0, 0, 0, 0, 64, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 64, 0, 0, 0, 0, 0, 64,
            64, 0, 85, 0, 0, 0, 64, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 64, 0, 0, 0, 0, 0, 64,
            64, 0, 0, 0, 1017, 0, 80, 4, 4, 4, 4, 4, 4, 0, 0, 0, 0, 0, 64, 0, 0, 0, 0, 0, 64,
            64, 0, 0, 0, 0, 0, 64, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 64, 64, 64, 80, 64, 64, 64,
            64, 0, 0, 0, 0, 0, 64, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 64,
            64, 64, 64, 64, 64, 64, 64, 0, 0, 0, 0, 0, 4, 64, 64, 64, 80, 64, 64, 64, 0, 4, 0, 0, 64,
            64, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 64, 0, 0, 0, 0, 0, 64, 0, 4, 0, 0, 64,
            64, 64, 64, 64, 0, 0, 0, 0, 0, 0, 0, 0, 4, 64, 0, 0, 0, 0, 0, 64, 0, 4, 0, 0, 64,
            64, 100, 100, 64, 0, 0, 0, 0, 0, 0, 0, 0, 4, 64, 0, 0, 0, 0, 0, 64, 0, 4, 0, 0, 64,
            64, 18, 0, 80, 0, 0, 0, 0, 0, 0, 0, 0, 4, 64, 64, 64, 64, 64, 64, 64, 0, 4, 0, 0, 64,
            64, 100, 100, 64, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4, 4, 0, 0, 64, 64, 64, 64, 4, 64, 64, 64,
            64, 64, 64, 64, 0, 0, 0, 0, 0, 0, 4, 98, 4, 98, 4, 0, 0, 64, 0, 0, 0, 4, 0, 66, 64,
            64, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 17, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0, 66, 64,
            64, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 98, 4, 98, 4, 0, 0, 64, 0, 0, 0, 4, 0, 66, 64,
            64, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4, 4, 0, 0, 64, 64, 64, 64, 4, 64, 64, 64,
            64, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 64,
            64, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 64,
            64, 68, 68, 68, 68, 68, 68, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 64, 64, 64, 80, 64, 64, 64,
            64, 4, 4, 4, 4, 4, 68, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 64, 104, 104, 104, 104, 104, 64,
            64, 4, 4, 4, 4, 4, 68, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 64, 104, 2, 2, 2, 104, 64,
            64, 4, 4, 99, 4, 4, 80, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 64, 104, 2, 97, 2, 104, 64,
            64, 4, 4, 4, 4, 4, 68, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 64, 104, 2, 2, 2, 104, 64,
            64, 4, 4, 4, 4, 4, 68, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 64, 104, 104, 104, 104, 104, 64,
            64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64};


    //地块贴图
    @Override
    public String tilesTex() {
        return Assets.Environment.TILES_CITY;
    }
    //水体贴图
    @Override
    public String waterTex() {
        return Assets.Environment.WATER_CITY;
    }

    private static final int WIDTH = 25;
    private static final int HEIGHT = 25;

    //创建出入口
    @Override
    protected boolean build() {
        setSize(WIDTH, HEIGHT);
        //上楼梯
        entrance = WIDTH*2+2;
        //下楼梯
        exit =WIDTH*13+12 ;

        for (int map = 0; map < this.map.length; map++) this.map[map] = mapToTerrain(pre_map[map]);
        return true;
    };


    @Override
    public Mob createMob() {
        return null;
    }


    //创建生物
    @Override
    protected void createMobs() {
        //
        BlackWorm n= new BlackWorm();
        n.pos = (this.width *3+3);
        mobs.add(n);

       // for(int c=3; c<15; c+=1){
           // BlackWorm i= new BlackWorm();
           // i.pos = (this.width * c +8);
          //  mobs.add(i);
     //   }
    }

    public Actor addRespawner() {
        return null;
    }

    @Override
    protected void createItems() {
        drop(new Torch(),0).type = Heap.Type.FOR_SALE;
        //Dungeon.level.drop(new Torch(),0).type = Heap.Type.FOR_SALE;
    }

    @Override
    public int randomRespawnCell( Char ch ) {
        return entrance-width();
    }

}