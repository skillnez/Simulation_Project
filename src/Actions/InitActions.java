package Actions;

import Entities.*;
import WorldMap.Coordinates;
import WorldMap.WorldMap;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class InitActions {

    private void placeEntity(Coordinates coordinates, Entity entity, WorldMap worldMap) {
        worldMap.getFlatMap().put(coordinates, entity);
        worldMap.getAvailableCoordinates().remove(coordinates);
    }

    public void mapSetup(WorldMap worldMap) {
        worldMap.setAvailableCells();
        EntityConfig entityConfig = new EntityConfig();
        entityConfig.setCreaturesQuantity(worldMap);
        Map<Class<? extends Entity>, Integer> entityToSpawn = new HashMap<>();
        entityToSpawn.put(Wolf.class, entityConfig.getWolfQty());
        entityToSpawn.put(Fox.class, entityConfig.getFoxQty());
        entityToSpawn.put(Hare.class, entityConfig.getHareQty());
        entityToSpawn.put(Cow.class, entityConfig.getCowQty());
        entityToSpawn.put(Grass.class, entityConfig.getGrassQty());
        entityToSpawn.put(Rock.class, entityConfig.getRockQty());
        entityToSpawn.put(Tree.class, entityConfig.getTreeQty());

        for (Map.Entry<Class<? extends Entity>, Integer> entry : entityToSpawn.entrySet()) {
            Class<? extends Entity> entityClass = entry.getKey();
            int quantity = entry.getValue();
            spawnEntities(worldMap, entityClass, quantity);
        }
    }

    private void spawnEntities(WorldMap worldMap, Class<? extends Entity>  entityClass, int quantity) {
        for (int i = 0; i < quantity; i++) {
                Entity entity = createEntity(entityClass);
                placeEntity(worldMap.getRandomAvailableCell(), entity, worldMap);
        }
    }

    private Entity createEntity (Class<? extends Entity> entities) {
        return switch (entities.getSimpleName()) {
            case "Wolf" -> new Wolf(EntityConfig.WOLF_HP, EntityConfig.WOLF_SPEED, EntityConfig.WOLF_DAMAGE);
            case "Fox" -> new Fox(EntityConfig.FOX_HP, EntityConfig.FOX_SPEED, EntityConfig.FOX_DAMAGE);
            case "Hare" -> new Hare(EntityConfig.HARE_HP, EntityConfig.HARE_SPEED);
            case "Cow" -> new Cow(EntityConfig.COW_HP, EntityConfig.COW_SPEED);
            case "Rock" -> new Rock();
            case "Grass" -> new Grass();
            case "Tree" -> new Tree();
            default ->
                    throw new NoSuchElementException("Передан недопустимый элемент на карту" + entities.getSimpleName());
        };
    }
}