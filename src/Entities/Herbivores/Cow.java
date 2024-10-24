package Entities.Herbivores;

import Entities.Consumable;
import Entities.EntityConfig;

public class Cow extends Herbivore {

    public Cow(int healthPoints, int speed) {
        super(healthPoints, speed);
    }

    @Override
    public String toString() {
        return "Корова";
    }
}
