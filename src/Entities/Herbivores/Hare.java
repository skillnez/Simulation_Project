package Entities.Herbivores;

import Entities.Consumable;
import Entities.EntityConfig;

public class Hare extends Herbivore {

    public Hare(int healthPoints, int speed) {
        super(healthPoints, speed);
    }

    @Override
    public String toString() {
        return "Заяц";
    }
}
