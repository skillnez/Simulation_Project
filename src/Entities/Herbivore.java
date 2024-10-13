package Entities;

public abstract class Herbivore extends Creature implements Movable {

    public Herbivore(int healthPoints, int speed) {
        super(healthPoints, speed);
    }

    @Override
    public void makeMove() {
        //двигается чтобы найти траву
    }

    private void consumeGrass() {

    }

}
