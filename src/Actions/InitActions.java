package Actions;

import Entities.*;
import WorldMap.Coordinates;
import WorldMap.WorldMap;

public class InitActions {

    private void placeEntity(Coordinates coordinates, Entity entity, WorldMap worldMap) {
        worldMap.getFlatMap().put(coordinates, entity);
        worldMap.getAvailableCoordinates().remove(coordinates);
    }

    public void mapSetup(WorldMap worldMap) {
        worldMap.setAvailableCells();
        spawnGrass(worldMap);
        spawnCow(worldMap);
        spawnFox(worldMap);
        spawnHare(worldMap);
        spawnWolf(worldMap);
        spawnRock(worldMap);
        spawnTree(worldMap);
    }

    private void spawnWolf(WorldMap worldMap) {
        EntityConfig entityConfig = new EntityConfig();
        entityConfig.setCreaturesQuantity(worldMap);
        for (int i = 0; i < entityConfig.getWolfQty(); i++) {
            placeEntity(worldMap.getRandomAvailableCell(),
                    new Wolf(EntityConfig.WOLF_HP, EntityConfig.WOLF_SPEED, EntityConfig.WOLF_DAMAGE), worldMap);
        }
    }

    private void spawnFox(WorldMap worldMap) {
        EntityConfig entityConfig = new EntityConfig();
        entityConfig.setCreaturesQuantity(worldMap);
        for (int i = 0; i < entityConfig.getFoxQty(); i++) {
            placeEntity(worldMap.getRandomAvailableCell(),
                    new Fox(EntityConfig.FOX_HP, EntityConfig.FOX_SPEED, EntityConfig.FOX_DAMAGE), worldMap);
        }
    }

    private void spawnHare(WorldMap worldMap) {
        EntityConfig entityConfig = new EntityConfig();
        entityConfig.setCreaturesQuantity(worldMap);
        for (int i = 0; i < entityConfig.getHareQty(); i++) {
            placeEntity(worldMap.getRandomAvailableCell(),
                    new Hare(EntityConfig.HARE_HP, EntityConfig.HARE_SPEED), worldMap);
        }
    }

    private void spawnCow(WorldMap worldMap) {
        EntityConfig entityConfig = new EntityConfig();
        entityConfig.setCreaturesQuantity(worldMap);
        for (int i = 0; i < entityConfig.getCowQty(); i++) {
            placeEntity(worldMap.getRandomAvailableCell(),
                    new Cow(EntityConfig.COW_HP, EntityConfig.COW_SPEED), worldMap);
        }
    }

    private void spawnGrass(WorldMap worldMap) {
        EntityConfig entityConfig = new EntityConfig();
        entityConfig.setCreaturesQuantity(worldMap);
        for (int i = 0; i < entityConfig.getGrassQty(); i++) {
            placeEntity(worldMap.getRandomAvailableCell(),
                    new Grass(), worldMap);
        }
    }

    private void spawnRock(WorldMap worldMap) {
        EntityConfig entityConfig = new EntityConfig();
        entityConfig.setCreaturesQuantity(worldMap);
        for (int i = 0; i < entityConfig.getRockQty(); i++) {
            placeEntity(worldMap.getRandomAvailableCell(),
                    new Rock(), worldMap);
        }
    }

    private void spawnTree(WorldMap worldMap) {
        EntityConfig entityConfig = new EntityConfig();
        entityConfig.setCreaturesQuantity(worldMap);
        for (int i = 0; i < entityConfig.getTreeQty(); i++) {
            placeEntity(worldMap.getRandomAvailableCell(),
                    new Tree(), worldMap);
        }
    }

//    private Predator createPredators (Predator predator, int quantity) {
//        return entity;
//    }
}