package Actions;

import Entities.Entity;
import WorldMap.WorldMap;
import WorldMap.Coordinates;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TurnActions {

    public void moveEntities(WorldMap worldMap) {
        Map<Coordinates, Entity> wordMap = worldMap.getFlatMap();
        Map<Coordinates, Entity> moveCache = new HashMap<>();
        Iterator<Map.Entry<Coordinates, Entity>> iterator = wordMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Coordinates, Entity> entry = iterator.next();

            if (isAbleToMove(worldMap, coordinateMove(entry.getKey()), entry.getValue())) {
                Coordinates coordinates = coordinateMove(entry.getKey());
                moveCache.put(coordinates, entry.getValue());
            } else {
                moveCache.put(entry.getKey(), entry.getValue());
            }
        }
        worldMap.getFlatMap().clear();
        worldMap.getFlatMap().putAll(moveCache);
    }

    private Coordinates coordinateMove(Coordinates coordinates) {
        Coordinates newCoordinates = new Coordinates(coordinates.getHorizontal(), coordinates.getVertical()+1);
        return newCoordinates;
    }

    private boolean isAbleToMove(WorldMap worldMap, Coordinates coordinates, Entity entity) {
        return entity.isAbleToMove() &&
                coordinates.getVertical() < worldMap.getVerticalMapSize() &&
                coordinates.getHorizontal() < worldMap.getHorizontalMapSize() &&
                coordinates.getVertical() >= 0 &&
                coordinates.getHorizontal() >= 0 &&
                !worldMap.getFlatMap().containsKey(coordinates);


    }
}
