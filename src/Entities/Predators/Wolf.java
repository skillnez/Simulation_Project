package Entities.Predators;

import Entities.Herbivores.Herbivore;

public class Wolf extends Predator {

    public Wolf(int healthPoints, int speed, int attackPower) {
        super(healthPoints, speed, attackPower);
    }

    @Override
    public void attack(Herbivore herbivore) {
        herbivore.setHealthPoints(herbivore.getHealthPoints() - this.getAttackPower());
    }

    @Override
    public String toString() {
        return "Волк";
    }
}
