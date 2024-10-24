package Entities.StaticObjects;

import Entities.Consumable;
import Entities.Entity;
import Entities.EntityConfig;

public class Grass extends Inanimate implements Consumable {

    @Override
    public int getStateEffect() {
        return EntityConfig.GRASS_HEAL_POWER;
    }

    @Override
    public String toString() {
        return "Трава";
    }
}
