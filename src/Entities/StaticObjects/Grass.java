package Entities.StaticObjects;

import Entities.Consumable;
import Entities.Entity;
import Entities.Immovable;

public class Grass extends Entity implements Consumable, Immovable {

    public Grass() {
    }

    @Override
    public boolean isConsumable() {
        return true;
    }
}
