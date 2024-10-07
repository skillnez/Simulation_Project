package WorldMap;

import Entities.Entity;
import Entities.Wolf;

import java.util.*;

public class WorldMap {

    private final int horizontalMapSize;
    private final int verticalMapSize;
    Map<Coordinates, Entity> flatMap = new HashMap<>();
    Set<Coordinates> availableCoordinates = new HashSet<>();
    Random random = new Random();

    public WorldMap(int horizontalMapSize, int verticalMapSize) {
        this.horizontalMapSize = horizontalMapSize;
        this.verticalMapSize = verticalMapSize;
    }

    private void setAvailableCoordinates(int horizontalMapSize, int verticalMapSize) {
        for (int i = 0; i < horizontalMapSize; i++) {
            for (int j = 0; j < verticalMapSize; j++) {
                availableCoordinates.add(new Coordinates(i, j));
            }
        }
    }

    private void placeObjects(Coordinates coordinates, Entity entity) {
        flatMap.put(coordinates, entity);
        availableCoordinates.remove(coordinates);
    }

    public void mapSetup() {
        setAvailableCoordinates(horizontalMapSize, verticalMapSize);
        for (int i = 0; i < 3; i++) {
            placeObjects(getRandomCoordinateFromSet(), new Wolf(100, 3, 50));

        }
    }

    private Coordinates getRandomCoordinateFromSet() {
        int index = random.nextInt(availableCoordinates.size());
        Iterator<Coordinates> iterator = availableCoordinates.iterator();
        for (int i = 0; i < index; i++) {
            iterator.next();
        }
        return iterator.next();
    }

    public void moveEntity() {

    }

    protected int getHorizontalMapSize() {
        return horizontalMapSize;
    }

    protected int getVerticalMapSize() {
        return verticalMapSize;
    }

    protected Map<Coordinates, Entity> getFlatMap() {
        return flatMap;
    }
}
