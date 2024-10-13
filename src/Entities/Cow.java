package Entities;

public class Cow extends Herbivore {

    public Cow(int healthPoints, int speed) {
        super(healthPoints, speed);
    }

    @Override
    public boolean isAbleToMove() {
        return true;
    }
}
