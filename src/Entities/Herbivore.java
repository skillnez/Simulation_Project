package Entities;

import Sprites.EntitySprite;

public abstract class Herbivore extends Creature {

    public Herbivore(int healthPoints, int speed, EntitySprite sprite) {
        super(healthPoints, speed, sprite);
    }

    @Override
    public void makeMove(){
        //двигается чтобы найти траву
    }

    private void consumeGrass(){

    }

}
