package Entities;

import Sprites.EntitySprite;

public abstract class Creature extends Entity {

    private final int healthPoints;
    private final int speed;

    public Creature(int healthPoints, int speed) {
        this.healthPoints = healthPoints;
        this.speed = speed;
    }

    public abstract void makeMove();

    public int getHealthPoints() {
        return healthPoints;
    }

    public int getSpeed() {
        return speed;
    }

}
