package WorldMap;

import Entities.Entity;
import Entities.Wolf;
import Sprites.EntitySprite;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class WorldMap {

    private int horizontalMapSize;
    private int verticalMapSize;

    Map<Coordinates, Entity> flatMap = new HashMap<>();

    Random random = new Random();

    public void placeObjects (Coordinates coordinates, Entity entity) {
        flatMap.put(coordinates, entity);
    }

    public void mapSetup(int horizontal, int vertical) {
        horizontalMapSize = horizontal;
        verticalMapSize = vertical;
        for (int i = 0; i < 3; i++) {
            placeObjects(new Coordinates(random.nextInt(horizontal), random.nextInt(vertical)),
                    new Wolf(100, 3, 50, EntitySprite.WOLF));
        }
    }

    public int getHorizontalMapSize() {
        return horizontalMapSize;
    }

    public int getVerticalMapSize() {
        return verticalMapSize;
    }

    public Map<Coordinates, Entity> getFlatMap() {
        return flatMap;
    }
}
