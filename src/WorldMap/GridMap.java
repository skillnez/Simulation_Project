package WorldMap;

import Entities.Entity;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class GridMap implements BaseMap {

    public static final int MIN_MAP_SIZE = 30;
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
                    Карта слишком мала, минимальный размер - 30 клеток\s
                    Но ты все еще можешь насладиться пустой картой ;)\s
                    """);
        }
    }

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

    public Coordinates getRandomAvailableCell() {
        if (availableCell.isEmpty()) {
            System.out.println("Нет свободных ячеек, карта переполнена или отсутствует");
        }
        Collections.shuffle(availableCell);
        return availableCell.removeFirst();
    }

    @Override
    public Coordinates getCoordinates(Entity entity) throws NoSuchElementException {
        for (Map.Entry<Coordinates, Entity> entry : flatMap.entrySet()) {
            if (entry.getValue().equals(entity)) {
                return entry.getKey();
                }
        }
        throw new NoSuchElementException();
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
    public Entity getEntity(Coordinates coordinates) throws NoSuchElementException {
        if (!flatMap.containsKey(coordinates)) {
            throw new NoSuchElementException();
        } else {
            return flatMap.get(coordinates);
        }
    }

    @Override
    public void placeEntity(Coordinates coordinates, Entity entity) {
        if (coordinates.getHorizontal() <= getHorizontalMapSize() && coordinates.getVertical() <= getVerticalMapSize()) {
            flatMap.put(coordinates, entity);
        } else {
            System.out.println("Вы пытаетесь поместить существо вне границ карты, действие не выполнено");
        }
    }

    public List<Entity> getEntitiesList() {
        return new ArrayList<>(flatMap.values());
    }

    public List<Coordinates> getCoordinatesList() {
        return new ArrayList<>(flatMap.keySet());
    }

    public boolean isEmpty(Coordinates cell) {
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
