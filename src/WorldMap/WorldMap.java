package WorldMap;

import Entities.Entity;
import Entities.Herbivores.Herbivore;
import Entities.Predators.Predator;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class WorldMap {

    private final int horizontalMapSize;
    private final int verticalMapSize;
    private final int totalMapSize;
    private final Set<Coordinates> availableCoordinates = new HashSet<>();
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

    public void setAvailableCells() {
        for (int i = 0; i < horizontalMapSize; i++) {
            for (int j = 0; j < verticalMapSize; j++) {
                availableCoordinates.add(new Coordinates(i, j));
            }
        }
    }

    public Coordinates getRandomAvailableCell() {
        if (availableCoordinates.isEmpty()) {
            System.out.println("Программа не может быть запущена:" +
                    "\nНет свободных ячеек, карта переполнена или отсутствует");
            System.exit(0);
        }
        Random random = new Random();
        int index = random.nextInt(availableCoordinates.size());
        Iterator<Coordinates> iterator = availableCoordinates.iterator();
        for (int i = 0; i < index; i++) {
            iterator.next();
        }
        return iterator.next();
    }

    public Set<Coordinates> getPredators () {
        Set<Coordinates> predators = new HashSet<>();
        for (Map.Entry<Coordinates, Entity> entry : flatMap.entrySet()) {
            if (entry instanceof Predator) {
                predators.add(entry.getKey());
            }
        }
        return predators;
    }

    public Set<Coordinates> getHerbivores () {
        Set<Coordinates> herbivores = new HashSet<>();
        for (Map.Entry<Coordinates, Entity> entry : flatMap.entrySet()) {
            if (entry instanceof Herbivore) {
                herbivores.add(entry.getKey());
            }
        }
        return herbivores;
    }

    public int getHorizontalMapSize() {
        return horizontalMapSize;
    }

    public int getVerticalMapSize() {
        return verticalMapSize;
    }

    public Set<Coordinates> getAvailableCoordinates() {
        return availableCoordinates;
    }

    public ConcurrentHashMap<Coordinates, Entity> getFlatMap() {
        return flatMap;
    }

    public int getTotalMapSize() {
        return totalMapSize;
    }
}
