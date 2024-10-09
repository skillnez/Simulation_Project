package Actions;

import Entities.Entity;
import Entities.Predator;
import Entities.Wolf;
import WorldMap.Coordinates;
import WorldMap.WorldMap;

public class InitActions {

    private void placeEntity(Coordinates coordinates, Entity entity, WorldMap worldMap) {
        worldMap.getFlatMap().put(coordinates, entity);
        worldMap.getAvailableCoordinates().remove(coordinates);
    }

    public void mapSetup(WorldMap worldMap) {
        worldMap.setAvailableCells();
        for (int i = 0; i < 3; i++) {
            // создание объектов на карту раздели в отдельный метод, чтобы не было создание волчков сразу тут
            placeEntity(worldMap.getRandomAvailableCell(), new Wolf(100, 3, 50), worldMap);
        }
    }

//    private Predator createPredators (Predator predator, int quantity) {
//        return entity;
//    }

}