package com.shatteredpixel.shatteredpixeldungeon.levels;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.newmob.treearea.WoodenCross;
import com.shatteredpixel.shatteredpixeldungeon.items.Heap;
import com.shatteredpixel.shatteredpixeldungeon.items.Torch;
import com.shatteredpixel.shatteredpixeldungeon.items.keys.CrystalKey;
import com.shatteredpixel.shatteredpixeldungeon.items.keys.IronKey;
import com.shatteredpixel.shatteredpixeldungeon.items.keys.Key;
import com.watabou.utils.Random;

public class Level1 extends Level {

    //private static final int SIZE = 20;


    {
        color1 = 0x534f3e;
        color2 = 0xb9d661;
    }

    //定义地块
 private int mapToTerrain(int code){
        switch (code){
            default:
                return Terrain.EMPTY;
            case 1: //地面
                return Terrain.EMPTY;
            case 65:    //墙壁
                return Terrain.WALL;
            case 21:    //台子
                return Terrain.PEDESTAL;
            case 98:    //雕像
                return Terrain.STATUE;
            case 83:    //锁
                return Terrain.LOCKED_DOOR;
                // return Terrain.CRYSTAL_DOOR;
            case 17:
                return Terrain.ENTRANCE;
            case 18:
                return Terrain.EXIT;



        }
    }

    //地形布局（硬编码布局？）
    private static final int[] pre_map = {
            65,65,65,65,65,65,65,65,65,65,65,65,65,65,65,65,65,65,65,
            65,65,65,65,65,65,98,1,1,1,1,1,98,65,65,65,65,65,65,
            65,65,65,65,65,65,98,1,1,18,1,1,98,65,65,65,65,65,65,
            65,65,65,65,65,65,98,1,1,1,1,1,98,65,65,65,65,65,65,
            65,65,65,65,65,65,65,65,1,1,1,65,65,65,65,65,65,65,65,
            65,65,65,65,1,1,1,65,1,1,1,65,1,1,1,65,65,65,65,
            65,65,65,65,1,21,1,83,1,1,1,83,1,21,1,65,65,65,65,
            65,65,65,65,1,1,1,65,1,1,1,65,1,1,1,65,65,65,65,
            65,65,65,65,65,65,65,65,1,1,1,65,65,65,65,65,65,65,65,
            65,65,65,65,65,65,65,65,1,1,1,65,65,65,65,65,65,65,65,
            65,65,65,65,1,1,1,65,1,1,1,65,1,1,1,65,65,65,65,
            65,65,65,65,1,21,1,83,1,1,1,83,1,21,1,65,65,65,65,
            65,65,65,65,1,1,1,65,1,1,1,65,1,1,1,65,65,65,65,
            65,65,65,65,65,65,65,65,1,1,1,65,65,65,65,65,65,65,65,
            65,65,65,65,65,1,1,1,1,1,1,1,1,1,65,65,65,65,65,
            65,1,1,1,65,1,21,1,1,21,1,1,21,1,65,1,1,1,65,
            65,1,21,1,83,1,1,1,1,1,1,1,1,1,83,1,21,1,65,
            65,1,1,1,65,1,21,1,1,17,1,1,21,1,65,1,1,1,65,
            65,65,65,65,65,1,1,1,1,1,1,1,1,1,65,65,65,65,65,
            65,65,65,65,65,65,65,65,65,65,65,65,65,65,65,65,65,65,65};


    //地块贴图
    @Override
    public String tilesTex() {
        return Assets.Environment.TILES_SEWERS;
    }
    //水体贴图
    @Override
    public String waterTex() {
        return Assets.Environment.WATER_SEWERS;
    }

    private static final int WIDTH = 19;
    private static final int HEIGHT = 20;

    //创建出入口
    @Override
    protected boolean build() {
        setSize(WIDTH, HEIGHT);
        //上楼梯
        entrance = WIDTH*17+9;
        //下楼梯
        exit =WIDTH*2+9 ;

        for (int map = 0; map < this.map.length; map++) this.map[map] = mapToTerrain(pre_map[map]);
        return true;
    };


    @Override
    public Mob createMob() {
        return null;
    }


    @Override
    protected void createMobs() {
    }

    public Actor addRespawner() {
        return null;
    }

    @Override
    protected void createItems() {
        drop(new IronKey(1), 294);
        drop(new CrystalKey(1), 293);
        drop(new IronKey(1), 295);
        drop(new Torch(), 162);
        drop(new Torch(), 162);
    }

    @Override
    public int randomRespawnCell( Char ch ) {
        return entrance-width();
    }




















}