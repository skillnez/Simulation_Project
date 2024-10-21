package Entities.Predators;

import Entities.Herbivores.Herbivore;

public class Fox extends Predator {

    public Fox(int healthPoints, int speed, int attackPower) {
        super(healthPoints, speed, attackPower);
    }

    @Override
    public void attack(Herbivore herbivore) {
        herbivore.setHealthPoints(herbivore.getHealthPoints() - this.getAttackPower());
    }

}
