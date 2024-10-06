package Entities;

import Sprites.EntitySprite;

public abstract class Predator extends Creature {

    private final int attackPower;

    public Predator(int healthPoints, int speed, int attackPower, EntitySprite sprite) {
        super(healthPoints, speed, sprite);
        this.attackPower = attackPower;
    }

    @Override
    public void makeMove(){
        //чтобы попасть к травоядному
    }

    public void attack(Herbivore herbivore) {

    }

    public int getAttackPower() {
        return attackPower;
    }
}
