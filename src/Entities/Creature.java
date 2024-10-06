package Entities;

import Sprites.EntitySprite;

public abstract class Creature extends Entity {

    private final int healthPoints;
    private final int speed;
    private final EntitySprite sprite;

    public Creature(int healthPoints, int speed, EntitySprite sprite) {
        this.healthPoints = healthPoints;
        this.speed = speed;
        this.sprite = sprite;
    }

    public abstract void makeMove();

    public int getHealthPoints() {
        return healthPoints;
    }

    public int getSpeed() {
        return speed;
    }

    public EntitySprite getSprite() {
        return sprite;
    }

    @Override
    public String toString() {
        return sprite.toString();
    }
}
