package Entities;

public abstract class Creature extends Entity {

    protected int healthPoints;
    protected int speed;

    public Creature(int healthPoints, int speed) {
        this.healthPoints = healthPoints;
        this.speed = speed;
    }

   public abstract void makeMove();
}
