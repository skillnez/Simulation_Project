package Entities;

import WorldMap.WorldMap;

public class EntityConfig {
    //Допущение, размер карты не менее 25
    //Можно сделать как размер карты, умноженный на коэфф, коэфф будет обозначать плотность существ на кол-во клеток.
    //Wolf mapsize*0.2
    //Fox
    //Cow
    //Hare
    //Rock
    //Three
    //Grass

    public final static int WOLF_HP = 100;
    public final static int WOLF_DAMAGE = 10;
    public final static int WOLF_SPEED = 2;

    public final static int FOX_HP = 70;
    public final static int FOX_DAMAGE = 8;
    public final static int FOX_SPEED = 4;

    public final static int HARE_HP = 30;
    public final static int HARE_SPEED = 5;

    public final static int COW_HP = 180;
    public final static int COW_SPEED = 1;

    private long wolfQty;
    private long foxQty;
    private long hareQty;
    private long cowQty;
    private long rockQty;
    private long treeQty;
    private long grassQty;

    public void setCreaturesQuantity (WorldMap worldMap) {
        wolfQty = Math.round(worldMap.getTotalMapSize()/20);
        foxQty = Math.round(worldMap.getTotalMapSize()/20);
        hareQty = Math.round(worldMap.getTotalMapSize()/20);
        cowQty = Math.round(worldMap.getTotalMapSize()/40);
        rockQty = Math.round(worldMap.getTotalMapSize()/30);
        treeQty = Math.round(worldMap.getTotalMapSize()/20);
        grassQty = Math.round(worldMap.getTotalMapSize()/20);
    }

    public long getHareQty() {
        return hareQty;
    }

    public long getCowQty() {
        return cowQty;
    }

    public long getRockQty() {
        return rockQty;
    }

    public long getTreeQty() {
        return treeQty;
    }

    public long getGrassQty() {
        return grassQty;
    }

    public long getFoxQty() {
        return foxQty;
    }

    public long getWolfQty() {
        return wolfQty;
    }
}
