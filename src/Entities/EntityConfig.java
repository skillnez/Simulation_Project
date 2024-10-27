package Entities;

import WorldMap.GridMap;

public class EntityConfig {

    public final static int WOLF_HP = 100;
    public final static int WOLF_DAMAGE = 10;
    public final static int WOLF_SPEED = 1;

    public final static int FOX_HP = 70;
    public final static int FOX_DAMAGE = 8;
    public final static int FOX_SPEED = 2;

    public final static int HARE_HP = 30;
    public final static int HARE_SPEED = 2;

    public final static int COW_HP = 150;
    public final static int COW_SPEED = 1;

    public final static int GRASS_HEAL_POWER = 5;

    private int wolfQty;
    private int foxQty;
    private int hareQty;
    private int cowQty;
    private int rockQty;
    private int treeQty;
    private int grassQty;

    public void setCreaturesQuantity(GridMap gridMap) {
        wolfQty = gridMap.getTotalMapSize() / 30;
        foxQty = gridMap.getTotalMapSize() / 40;
        hareQty = gridMap.getTotalMapSize() / 30;
        cowQty = gridMap.getTotalMapSize() / 40;
        rockQty = gridMap.getTotalMapSize() / 10;
        treeQty = gridMap.getTotalMapSize() / 10;
        grassQty = gridMap.getTotalMapSize() / 30;
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
