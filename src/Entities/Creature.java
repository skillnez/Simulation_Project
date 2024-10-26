package Entities;

public abstract class Creature extends Entity {

    private final int speed;
    private final int maxHealthPoints;
    private int healthPoints;

    public Creature(int healthPoints, int speed) {
        super();
        this.healthPoints = healthPoints;
        this.speed = speed;
        this.maxHealthPoints = healthPoints;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getSpeed() {
        return speed;
    }

    public int getMaxHealthPoints() {
        return maxHealthPoints;
    }

    public boolean isAlive() {
        return healthPoints > 0;
    }

}
