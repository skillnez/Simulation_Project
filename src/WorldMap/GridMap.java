package WorldMap;

import Entities.Entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GridMap implements BaseMap {

    public static final int MIN_MAP_SIZE = 20;
    private final int horizontalMapSize;
    private final int verticalMapSize;
    private final int totalMapSize;
    private final List<Coordinates> availableCell = new ArrayList<>();
    private final ConcurrentHashMap<Coordinates, Entity> flatMap = new ConcurrentHashMap<>();

    public GridMap(int horizontalMapSize, int verticalMapSize) {
        this.horizontalMapSize = horizontalMapSize;
        this.verticalMapSize = verticalMapSize;
        this.totalMapSize = horizontalMapSize * verticalMapSize;
        if (totalMapSize < MIN_MAP_SIZE) {
            System.out.println("""
                    Карта слишком мала, минимальный размер - 20 клеток\s
                    Но ты все еще можешь насладиться пустой картой ;)\s
                    """);
        }
    }

    //проходимся по карте и создаем пустые ячейки там где не занято
    public void initMapCells() {
        availableCell.clear();
        for (int i = 0; i < horizontalMapSize; i++) {
            for (int j = 0; j < verticalMapSize; j++) {
                Coordinates cell = new Coordinates(i, j);
                if (isEmpty(cell)) {
                    availableCell.add(cell);
                }
            }
        }
    }

    //Берем любую пустую ячейку
    public Coordinates getRandomAvailableCell() {
        if (availableCell.isEmpty()) {
            System.out.println("Нет свободных ячеек, карта переполнена или отсутствует");
        }
        Collections.shuffle(availableCell);
        return availableCell.removeFirst();
    }

    @Override
    public Coordinates getCoordinates(Entity entity) {
        try {
            for (Map.Entry<Coordinates, Entity> entry : flatMap.entrySet()) {
                if (entry.getValue().equals(entity)) {
                    return entry.getKey();
                }
            }
        } catch (Exception e) {
            System.out.println("Существа по этой координате не обнаружено" + e.getMessage());
        }
        return null;
    }

    @Override
    public void removeEntity(Entity entity) {
        for (Map.Entry<Coordinates, Entity> entry : flatMap.entrySet()) {
            if (entry.getValue().equals(entity)) {
                flatMap.remove(entry.getKey());
            }
        }
    }

    @Override
    public void removeEntity(Coordinates coordinates) {
        for (Map.Entry<Coordinates, Entity> entry : flatMap.entrySet()) {
            if (entry.getKey().equals(coordinates)) {
                flatMap.remove(entry.getKey());
            }
        }
    }

    @Override
    public Entity getEntity(Coordinates coordinates) {
        try {
            for (Map.Entry<Coordinates, Entity> entry : flatMap.entrySet()) {
                if (entry.getKey().equals(coordinates)) {
                    return entry.getValue();
                }
            }
        } catch (Exception e) {
            System.out.println("Существа по этой координате не обнаружено" + e.getMessage());
        }
        return null;
    }

    @Override
    public void placeEntity(Coordinates coordinates, Entity entity) {
        flatMap.put(coordinates, entity);
    }

    public List<Entity> getEntitiesList() {
        return new ArrayList<>(flatMap.values());
    }

    public List<Coordinates> getCoordinatesList() {
        return new ArrayList<>(flatMap.keySet());
    }

    private boolean isEmpty(Coordinates cell) {
        return flatMap.get(cell) == null;
    }

    public int getHorizontalMapSize() {
        return horizontalMapSize;
    }

    public int getVerticalMapSize() {
        return verticalMapSize;
    }

    public int getTotalMapSize() {
        return totalMapSize;
    }
}