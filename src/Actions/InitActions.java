package Actions;

import Entities.*;
import Entities.Herbivores.Cow;
import Entities.Herbivores.Hare;
import Entities.Predators.Fox;
import Entities.Predators.Wolf;
import Entities.StaticObjects.Grass;
import Entities.StaticObjects.Rock;
import Entities.StaticObjects.Tree;
import WorldMap.Coordinates;
import WorldMap.WorldMap;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public abstract class InitActions implements Actions{

    @Override
    public void perform(WorldMap worldMap) {

    }
}