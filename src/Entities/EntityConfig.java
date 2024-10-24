package Entities;

import WorldMap.WorldMap;

public class EntityConfig {

    public final static int WOLF_HP = 100;
    public final static int WOLF_DAMAGE = 10;
    public final static int WOLF_SPEED = 1;

    public final static int FOX_HP = 70;
    public final static int FOX_DAMAGE = 8;
    public final static int FOX_SPEED = 2;

    public final static int HARE_HP = 30;
    public final static int HARE_SPEED = 3;

    public final static int COW_HP = 180;
    public final static int COW_SPEED = 1;

    public final static int GRASS_HEAL_POWER = 5;

    private int wolfQty;
    private int foxQty;
    private int hareQty;
    private int cowQty;
    private int rockQty;
    private int treeQty;
    private int grassQty;

    public void setCreaturesQuantity(WorldMap worldMap) {
        wolfQty = worldMap.getTotalMapSize() / 20;
        foxQty = worldMap.getTotalMapSize() / 20;
        hareQty = worldMap.getTotalMapSize() / 20;
        cowQty = worldMap.getTotalMapSize() / 40;
        rockQty = worldMap.getTotalMapSize() / 30;
        treeQty = worldMap.getTotalMapSize() / 20;
        grassQty = worldMap.getTotalMapSize() / 20;
    }

    public int getHareQty() {
        return hareQty;
    }

    public int getCowQty() {
        return cowQty;
    }

    public int getRockQty() {
        return rockQty;
    }

    public int getTreeQty() {
        return treeQty;
    }

    public int getGrassQty() {
        return grassQty;
    }

    public int getFoxQty() {
        return foxQty;
    }

    public int getWolfQty() {
        return wolfQty;
    }
}
