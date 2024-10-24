package Entities;

import java.util.Objects;

public abstract class Creature extends Entity{

    private int healthPoints;
    private final int speed;
    private final int maxHealthPoints;

    public Creature(int healthPoints, int speed) {
        super();
        this.healthPoints = healthPoints;
        this.speed = speed;
        this.maxHealthPoints = healthPoints;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public int getSpeed() {
        return speed;
    }

    public int getMaxHealthPoints() {
        return maxHealthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public boolean isAlive() {
        return healthPoints > 0;
    }

}
