package Pathfinder;
import Entities.Creature;
import Entities.Entity;
import Entities.Herbivore;
import Entities.Predator;
import Entities.StaticObjects.Grass;
import Entities.StaticObjects.ZAGLUSHKA;
import WorldMap.Coordinates;
import WorldMap.WorldMap;
import java.util.*;
public class PathFinder {
    Coordinates start;
    Coordinates goal;
    Entity entity = new ZAGLUSHKA();
    public List<Coordinates> bfs(WorldMap worldMap) {
        Map<Coordinates, Entity> cacheMap = worldMap.getFlatMap();
        Queue<Coordinates> queue = new LinkedList<>();
        Set<Coordinates> visited = new HashSet<>();
        Map<Coordinates, Coordinates> parent = new HashMap<>();
        for (Map.Entry<Coordinates, Entity> entry : worldMap.getFlatMap().entrySet()) {
            if (entry.getValue() instanceof Creature) {
                start = entry.getKey();
            }
        }
        queue.add(start);
        visited.add(start);
        parent.put(start, null);
        while (!queue.isEmpty()) {
            Coordinates current = queue.poll();
            if (cacheMap.get(current) instanceof Herbivore && cacheMap.get(start) instanceof Predator) {
                goal = current;
                List<Coordinates> path = reconstructPath(parent, start, goal, worldMap, entity);
                return reconstructPath(parent, start, goal, worldMap, entity);
            }
            if (cacheMap.get(current) instanceof Grass && cacheMap.get(start) instanceof Herbivore) {
                goal = current;
                List<Coordinates> path = reconstructPath(parent, start, goal, worldMap, entity);
                return reconstructPath(parent, start, goal, worldMap, entity);
            }
            getNeighbors(queue, current, worldMap, visited, parent);
        }
        int i = 123;
        return Collections.emptyList();
    }
    private List<Coordinates> reconstructPath(Map<Coordinates, Coordinates> parent, Coordinates start, Coordinates goal, WorldMap worldMap, Entity entity) {
        List<Coordinates> path = new ArrayList<>();
        for (Coordinates at = goal; at != null; at = parent.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        path.remove(start);
        path.remove(goal);
        for (Coordinates at : path) {
            worldMap.getFlatMap().put(at, entity);
        }
        return path;
    }
    //это хорошо работает, протестировано
    public void getNeighbors(Queue<Coordinates> queue, Coordinates coordinates, WorldMap worldMap, Set<Coordinates> visited, Map<Coordinates, Coordinates> parent) {
        int[] verticalDirection = {-1, 1, 0, 0};
        int[] horizontalDirection = {0, 0, 1, -1};
        for (int i = 0; i < 4; i++) {
            int horizontal = coordinates.getHorizontal() + horizontalDirection[i];
            int vertical = coordinates.getVertical() + verticalDirection[i];
            Coordinates inWatch = new Coordinates(horizontal, vertical);
            if (isInBounds(worldMap, inWatch) && !visited.contains(inWatch)) {
                parent.put(inWatch, coordinates);
                queue.add(inWatch);
                visited.add(inWatch);
            }
        }
    }
    private boolean isInBounds(WorldMap worldMap, Coordinates inWatch) {
        return inWatch.getVertical() < worldMap.getVerticalMapSize() &&
                inWatch.getHorizontal() < worldMap.getHorizontalMapSize() &&
                inWatch.getVertical() >= 0 &&
                inWatch.getHorizontal() >= 0;
        // работает, но пока идет сквозь животных
    }
    private boolean isVisited(Set<Coordinates> visited, Coordinates coordinates) {
        return visited.contains(coordinates);
    }
}
//Пока на всякий оставлю проверку границ
//             if (horizontal < 0 || vertical < 0) {
//                continue;
//            }
//            if (horizontal >= worldMap.getHorizontalMapSize() || vertical >= worldMap.getVerticalMapSize()) {
//                continue;
//            }