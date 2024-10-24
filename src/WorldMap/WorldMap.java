package WorldMap;

import Entities.Entity;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class WorldMap {

    private final int horizontalMapSize;
    private final int verticalMapSize;
    private final int totalMapSize;
    private final List<Coordinates> availableCoordinates = new ArrayList<>();
    private final ConcurrentHashMap<Coordinates, Entity> flatMap = new ConcurrentHashMap<>();
    public static final int MIN_MAP_SIZE = 20;

    public WorldMap(int horizontalMapSize, int verticalMapSize) {
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

    //проходимся по карте и создаем координаты
    public void setMapCells() {
        for (int i = 0; i < horizontalMapSize; i++) {
            for (int j = 0; j < verticalMapSize; j++) {
                Coordinates cell = new Coordinates(i, j);
                if (flatMap.get(cell) == null){
                    availableCoordinates.add(cell);
                }
            }
        }
    }

    public Coordinates getRandomAvailableCell() {
        if (availableCoordinates.isEmpty()) {
            System.out.println("Программа не может быть запущена:" +
                    "\nНет свободных ячеек, карта переполнена или отсутствует");
            System.exit(0);
        }
        Collections.shuffle(availableCoordinates);
        return availableCoordinates.getFirst();
    }

    public List<Entity> getEntitiesList() {
        return new ArrayList<>(flatMap.values());
    }

    public Entity getEntityByCoordinate (Coordinates coordinates) {
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

    public Coordinates getCoordinatesByEntity (Entity entity) {
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

    public int getHorizontalMapSize() {
        return horizontalMapSize;
    }

    public int getVerticalMapSize() {
        return verticalMapSize;
    }

    public List<Coordinates> getRandomAvailableCoordinates() {
        return availableCoordinates;
    }

    public ConcurrentHashMap<Coordinates, Entity> getFlatMap() {
        return flatMap;
    }

    public int getTotalMapSize() {
        return totalMapSize;
    }
}
