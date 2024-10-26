package Pathfinder;

import Entities.Consumable;
import Entities.Entity;
import Entities.Herbivores.Herbivore;
import Entities.Predators.Predator;
import WorldMap.BaseMap;
import WorldMap.Coordinates;
import WorldMap.GridMap;

import java.util.*;

public class PathFinder {
    Coordinates goal;
    List<Coordinates> path = new ArrayList<>();
    List<Coordinates> coordinatesBuffer = new ArrayList<>();
    Set<Coordinates> visited = new HashSet<>();
    Map<Coordinates, Coordinates> parent = new HashMap<>();

    public List<Coordinates> findPath(GridMap gridMap, Coordinates start) {
        clearAllConditions();

        Queue<Coordinates> processingCells = new LinkedList<>();
        processingCells.add(start);
        visited.add(start);
        parent.put(start, null);
        while (!processingCells.isEmpty()) {
            Coordinates current = processingCells.poll();
            if (isGoal(gridMap, current, start)) {
                goal = current;
                return reconstructPath();
            }
            getNeighbors(processingCells, current, gridMap, start);
        }
        return Collections.emptyList();
    }

    private void clearAllConditions() {
        goal = null;
        path.clear();
        visited.clear();
        parent.clear();
    }

    private List<Coordinates> reconstructPath() {
        for (Coordinates at = goal; at != null; at = parent.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        path.removeFirst();
        return path;
    }

    public void getNeighbors(Queue<Coordinates> queue, Coordinates current, GridMap gridMap, Coordinates start) {
        coordinatesBuffer.clear();
        int[] verticalDirection = {-1, 1, 0, 0};
        int[] horizontalDirection = {0, 0, 1, -1};
        for (int i = 0; i < 4; i++) {
            int horizontal = current.getHorizontal() + horizontalDirection[i];
            int vertical = current.getVertical() + verticalDirection[i];
            coordinatesBuffer.add(new Coordinates(horizontal, vertical));
            Coordinates inWatch = coordinatesBuffer.getLast();
            if (isNeighborValid(inWatch, gridMap, start)) {
                parent.put(inWatch, current);
                queue.add(inWatch);
                visited.add(inWatch);
            }
        }
    }

    private boolean isNeighborValid(Coordinates inWatch, GridMap gridMap, Coordinates start) {
        return isInBounds(gridMap, inWatch) && !visited.contains(inWatch) &&
                (!isCellOccupied(gridMap, inWatch) || isGoal(gridMap, inWatch, start));
    }

    private boolean isInBounds(GridMap gridMap, Coordinates inWatch) {
        return inWatch.getVertical() < gridMap.getVerticalMapSize() &&
                inWatch.getHorizontal() < gridMap.getHorizontalMapSize() &&
                inWatch.getVertical() >= 0 && inWatch.getHorizontal() >= 0;
    }

    private boolean isCellOccupied(BaseMap baseMap, Coordinates inWatch) {
        return baseMap.getEntity(inWatch) != null;
    }

    private boolean isGoal(BaseMap baseMap, Coordinates inWatch, Coordinates start) {
        Entity target = baseMap.getEntity(inWatch);
        Entity seeker = baseMap.getEntity(start);
        return (seeker instanceof Predator && target instanceof Herbivore) ||
                (seeker instanceof Herbivore && target instanceof Consumable);
    }
}