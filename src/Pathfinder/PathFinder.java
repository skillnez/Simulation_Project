package Pathfinder;

import Entities.*;
import Entities.Herbivores.Herbivore;
import Entities.Predators.Predator;
import WorldMap.Coordinates;
import WorldMap.WorldMap;

import java.util.*;

public class PathFinder {
    Coordinates goal;
    List<Coordinates> path = new ArrayList<>();
    List<Coordinates> coordinatesBuffer = new ArrayList<>();
    Set<Coordinates> visited = new HashSet<>();
    Map<Coordinates, Coordinates> parent = new HashMap<>();

    public List<Coordinates> findPath(WorldMap worldMap, Coordinates start) {
        clearAllConditions();

        Queue<Coordinates> processingCells = new LinkedList<>();
        processingCells.add(start);
        visited.add(start);
        parent.put(start, null);
        while (!processingCells.isEmpty()) {
            Coordinates current = processingCells.poll();
            if (isGoal(worldMap, current, start)) {
                goal = current;
                return reconstructPath(worldMap);
            }
            getNeighbors(processingCells, current, worldMap, start);
        }
        return Collections.emptyList();
    }

    private void clearAllConditions () {
        goal = null;
        path.clear();
        visited.clear();
        parent.clear();
    }

    private List<Coordinates> reconstructPath(WorldMap worldMap) {
        for (Coordinates at = goal; at != null; at = parent.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        path.removeFirst();
        return path;
    }

    public void getNeighbors(Queue<Coordinates> queue, Coordinates current, WorldMap worldMap, Coordinates start) {
        coordinatesBuffer.clear();
        int[] verticalDirection = {-1, 1, 0, 0};
        int[] horizontalDirection = {0, 0, 1, -1};
        for (int i = 0; i < 4; i++) {
            int horizontal = current.getHorizontal() + horizontalDirection[i];
            int vertical = current.getVertical() + verticalDirection[i];
            coordinatesBuffer.add(new Coordinates(horizontal, vertical));
            Coordinates inWatch = coordinatesBuffer.getLast();
            if (isNeighborValid(inWatch, worldMap, start)) {
                parent.put(inWatch, current);
                queue.add(inWatch);
                visited.add(inWatch);
            }
        }
    }

    private boolean isNeighborValid(Coordinates inWatch, WorldMap worldMap, Coordinates start) {
        return isInBounds(worldMap, inWatch) && !visited.contains(inWatch) &&
                (!isCellOccupied(worldMap, inWatch) || isGoal(worldMap, inWatch, start));
    }

    private boolean isInBounds(WorldMap worldMap, Coordinates inWatch) {
        return inWatch.getVertical() < worldMap.getVerticalMapSize() && inWatch.getHorizontal() <
                worldMap.getHorizontalMapSize() && inWatch.getVertical() >= 0 && inWatch.getHorizontal() >= 0;
    }

    private boolean isCellOccupied(WorldMap worldMap, Coordinates inWatch) {
        return worldMap.getEntityByCoordinate(inWatch) != null;
    }

    private boolean isGoal(WorldMap worldMap, Coordinates inWatch, Coordinates start) {
        Entity target = worldMap.getEntityByCoordinate(inWatch);
        Entity seeker = worldMap.getEntityByCoordinate(start);
        return (seeker instanceof Predator && target instanceof Herbivore)
                || (seeker instanceof Herbivore && target instanceof Consumable);
    }
}