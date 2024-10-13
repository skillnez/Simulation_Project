package Entities;

public class Fox extends Predator {

    public Fox(int healthPoints, int speed, int attackPower) {
        super(healthPoints, speed, attackPower);
    }

    @Override
    public boolean isAbleToMove() {
        return true;
    }
}
