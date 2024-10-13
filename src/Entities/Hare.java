package Entities;

public class Hare extends Herbivore {

    public Hare(int healthPoints, int speed) {
        super(healthPoints, speed);
    }

    @Override
    public boolean isAbleToMove() {
        return true;
    }
}
