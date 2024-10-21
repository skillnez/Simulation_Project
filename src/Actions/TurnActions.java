package Actions;

import Entities.Creature;
import Entities.Entity;
import Entities.Herbivores.Herbivore;
import Entities.Predators.Predator;
import Pathfinder.PathFinder;
import WorldMap.Coordinates;
import WorldMap.WorldMap;

import java.util.*;

public class TurnActions {

    private Set<Coordinates> predators;
    private Set<Coordinates> herbivores;

    public void moveEntities2 (WorldMap worldMap) {
        //есть баг, существа ходят больше чем надо, потому что в процессе итерирования попадаются 2 раза
        //из-за изменений карты.
        //баг устранен
        Set<Entity> proceededEntities = new HashSet<>();
        for (Map.Entry<Coordinates, Entity> entry : worldMap.getFlatMap().entrySet()) {
            if (entry.getValue() instanceof Predator) {
                List<Coordinates> predatorPath = new PathFinder().findPath(worldMap, entry.getKey());
                if (!proceededEntities.contains(entry.getValue())) {
                    if (predatorPath.size() > 1) {
                        worldMap.getFlatMap().put(predatorPath.getFirst(), entry.getValue());
                        worldMap.getFlatMap().remove(entry.getKey());
                    }
                    if (predatorPath.size() == 1) {
                        //типа аттакует (удаляет свою крайнюю точку пути(цель) и становится на ее место)
                        worldMap.getFlatMap().remove(predatorPath.getLast());
                        worldMap.getFlatMap().put(predatorPath.getLast(), entry.getValue());
                        worldMap.getFlatMap().remove(entry.getKey());
                    }
                }
                proceededEntities.add(entry.getValue());
            }
            if (entry.getValue() instanceof Herbivore) {
                List<Coordinates> herbivorePath = new PathFinder().findPath(worldMap, entry.getKey());
                if (!proceededEntities.contains(entry.getValue())) {
                    if (herbivorePath.size() > 1) {
                        worldMap.getFlatMap().put(herbivorePath.getFirst(), entry.getValue());
                        worldMap.getFlatMap().remove(entry.getKey());
                    }
                    if (herbivorePath.size() == 1) {
                        //типа аттакует (удаляет свою крайнюю точку пути(цель) и становится на ее место)
                        worldMap.getFlatMap().remove(herbivorePath.getLast());
                        worldMap.getFlatMap().put(herbivorePath.getLast(), entry.getValue());
                        worldMap.getFlatMap().remove(entry.getKey());
                    }
                }
                proceededEntities.add(entry.getValue());
            }
        }
    }

    //есть баг, что существа наступают друг на друга
    public void moveEntities(WorldMap worldMap) {
        Map<Coordinates, Entity> cacheMap = worldMap.getFlatMap();
        Map<Coordinates, Entity> cacheMap2 = new HashMap<>();
        Set<Coordinates> occupiedCoordinates = new HashSet<>();
        Set<Coordinates> targets = new HashSet<>(); // Множество для отслеживания целей хищников

        for (Map.Entry<Coordinates, Entity> entry : cacheMap.entrySet()) {
            if (entry.getValue() instanceof Creature) {
                List<Coordinates> movePath = new PathFinder().findPath(worldMap, entry.getKey());
                int i = 123;
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
