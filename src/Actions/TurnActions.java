package Actions;

import Entities.Creature;
import Entities.Entity;
import Entities.Herbivore;
import Entities.Predator;
import Pathfinder.PathFinder;
import WorldMap.Coordinates;
import WorldMap.WorldMap;

import java.util.*;

public class TurnActions {
    //есть баг, что существа наступают друг на друга
    public void moveEntities(WorldMap worldMap) {
        Map<Coordinates, Entity> cacheMap = worldMap.getFlatMap();
        Map<Coordinates, Entity> cacheMap2 = new HashMap<>();
        Set<Coordinates> occupiedCoordinates = new HashSet<>();
        Set<Coordinates> targets = new HashSet<>(); // Множество для отслеживания целей хищников

        for (Map.Entry<Coordinates, Entity> entry : cacheMap.entrySet()) {
            if (entry.getValue() instanceof Creature) {
                List<Coordinates> movePath = new PathFinder().findPath(worldMap, entry.getKey());
                if (!movePath.isEmpty()) {
                    Coordinates newCoordinate = movePath.get(0);
                    Entity newCellEntity = cacheMap.get(newCoordinate);

                    // Проверка на возможность поедания
                    if (entry.getValue() instanceof Predator && newCellEntity instanceof Herbivore) {
                        if (!targets.contains(newCoordinate)) {
                            cacheMap2.put(newCoordinate, entry.getValue());
                            targets.add(newCoordinate);
                        } else {
                            cacheMap2.put(entry.getKey(), entry.getValue());
                        }
                    } else if ((entry.getValue() instanceof Herbivore && newCellEntity instanceof Predator) ||
                            (entry.getValue() instanceof Predator && newCellEntity instanceof Predator)) {
                        // Herbivore не может наступить на Predator, и Predator не может заменить другого Predator
                        cacheMap2.put(entry.getKey(), entry.getValue());
                        occupiedCoordinates.add(entry.getKey());
                    } else if (!occupiedCoordinates.contains(newCoordinate)) {
                        // Любое другое движение
                        cacheMap2.put(newCoordinate, entry.getValue());
                        occupiedCoordinates.add(newCoordinate);
                    } else {
                        cacheMap2.put(entry.getKey(), entry.getValue());
                        occupiedCoordinates.add(entry.getKey());
                    }
                } else {
                    cacheMap2.put(entry.getKey(), entry.getValue());
                    occupiedCoordinates.add(entry.getKey());
                }
            } else {
                cacheMap2.put(entry.getKey(), entry.getValue());
            }
        }

        worldMap.getFlatMap().clear();
        worldMap.getFlatMap().putAll(cacheMap2);
    }
}
