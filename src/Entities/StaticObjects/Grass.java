package Entities.StaticObjects;

import Entities.Consumable;
import Entities.Entity;

public class Grass extends Entity implements Consumable {

    public Grass() {
    }

    @Override
    public boolean isConsumable() {
        return true;
    }
}
