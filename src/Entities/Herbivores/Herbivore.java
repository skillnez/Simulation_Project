package Entities.Herbivores;

import Entities.Consumable;
import Entities.Creature;

public abstract class Herbivore extends Creature {

    public Herbivore(int healthPoints, int speed) {
        super(healthPoints, speed);
    }

    public void consume(Consumable consumable) {
        int damageTaken = getMaxHealthPoints() - this.getHealthPoints();
        if (consumable.getStateEffect() < damageTaken) {
            this.setHealthPoints(this.getHealthPoints() + consumable.getStateEffect());
        } else {
            this.setHealthPoints(getMaxHealthPoints());
        }
    }

}
