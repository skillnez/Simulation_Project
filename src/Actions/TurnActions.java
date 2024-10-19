package Actions;

import Entities.Creature;
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
            Coordinates coordinateToMove = coordinateMove(entry.getKey(), 1, 0);
            if (isAbleToMove(worldMap, coordinateToMove, entry.getValue())) {
                moveCache.put(coordinateToMove, entry.getValue());
            } else {
                moveCache.put(entry.getKey(), entry.getValue());
            }
        }
        worldMap.getFlatMap().clear();
        worldMap.getFlatMap().putAll(moveCache);
    }

    private Coordinates coordinateMove(Coordinates coordinates, int verticalMove, int horizontalMove) {
        return new Coordinates(coordinates.getHorizontal()+horizontalMove,
                coordinates.getVertical() + verticalMove);
    }


    //Перенести в BFS потом!!!
    public boolean isAbleToMove(WorldMap worldMap, Coordinates coordinates, Entity entity) {
        return entity instanceof Creature &&
                coordinates.getVertical() < worldMap.getVerticalMapSize() &&
                coordinates.getHorizontal() < worldMap.getHorizontalMapSize() &&
                coordinates.getVertical() >= 0 &&
                coordinates.getHorizontal() >= 0 &&
                !worldMap.getFlatMap().containsKey(coordinates);
    }
}
