package Pathfinder;

import Entities.*;
import Entities.StaticObjects.ZAGLUSHKA;
import WorldMap.Coordinates;
import WorldMap.WorldMap;

import java.util.*;

public class PathFinder {
    Coordinates start;
    Coordinates goal;
    Entity entity = new ZAGLUSHKA();
    List<Coordinates> path = new ArrayList<>();
    List<Coordinates> coordinatesBuffer = new ArrayList<>();
    Set<Coordinates> visited = new HashSet<>();
    Map<Coordinates, Coordinates> parent = new HashMap<>();

    public List<Coordinates> findPath(WorldMap worldMap) {
        Queue<Coordinates> processingCells = new LinkedList<>();
        for (Map.Entry<Coordinates, Entity> entry : worldMap.getFlatMap().entrySet()) {
            if (entry.getValue() instanceof Creature) {
                start = entry.getKey();
            }
        }
        processingCells.add(start);
        visited.add(start);
        parent.put(start, null);
        while (!processingCells.isEmpty()) {
            Coordinates current = processingCells.poll();
            if (isGoal(worldMap, current)) {
                goal = current;
                return reconstructPath(worldMap);
            }
            getNeighbors(processingCells, current, worldMap);
        }
        return Collections.emptyList();
    }

    private List<Coordinates> reconstructPath(WorldMap worldMap) {
        for (Coordinates at = goal; at != null; at = parent.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        //возможно придется убрать, когда буду делать взаимодействия животных
        path.removeFirst();
        path.remove(goal);
        //добавлено для отладки пути
        pathDebugVisual(worldMap);
        //
        return path;
    }

    public void pathDebugVisual(WorldMap worldMap) {
        for (Coordinates at : path) {
            worldMap.getFlatMap().put(at, entity);
        }
    }

    public void getNeighbors(Queue<Coordinates> queue, Coordinates current, WorldMap worldMap) {
        int[] verticalDirection = {-1, 1, 0, 0};
        int[] horizontalDirection = {0, 0, 1, -1};
        for (int i = 0; i < 4; i++) {
            int horizontal = current.getHorizontal() + horizontalDirection[i];
            int vertical = current.getVertical() + verticalDirection[i];
            coordinatesBuffer.add(new Coordinates(horizontal, vertical));
            Coordinates inWatch = coordinatesBuffer.getLast();
            if (isNeighborValid(inWatch, worldMap)) {
                parent.put(inWatch, current);
                queue.add(inWatch);
                visited.add(inWatch);
            }
        }
    }

    private boolean isNeighborValid(Coordinates inWatch, WorldMap worldMap) {
        if (isInBounds(worldMap, inWatch) && !visited.contains(inWatch)) {
            return !isCellOccupied(worldMap, inWatch) || isGoal(worldMap, inWatch);
        }
        return false;
    }

    private boolean isInBounds(WorldMap worldMap, Coordinates inWatch) {
        return inWatch.getVertical() < worldMap.getVerticalMapSize() && inWatch.getHorizontal() < worldMap.getHorizontalMapSize() && inWatch.getVertical() >= 0 && inWatch.getHorizontal() >= 0;
    }

    private boolean isCellOccupied(WorldMap worldMap, Coordinates inWatch) {
        Map<Coordinates, Entity> cacheMap = worldMap.getFlatMap();
        return cacheMap.get(inWatch) != null;
    }

    private boolean isGoal(WorldMap worldMap, Coordinates inWatch) {
        Map<Coordinates, Entity> cacheMap = worldMap.getFlatMap();
        return (cacheMap.get(start) instanceof Predator && cacheMap.get(inWatch) instanceof Herbivore) || (cacheMap.get(start) instanceof Herbivore && cacheMap.get(inWatch) instanceof Consumable);
    }
}