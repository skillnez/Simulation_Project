package Actions;

import Entities.Consumable;
import Entities.Entity;
import Entities.EntityConfig;
import Entities.Herbivores.Cow;
import Entities.Herbivores.Hare;
import Entities.StaticObjects.Grass;
import WorldMap.GridMap;

import java.util.List;

public class EntityRestorer extends TurnActions {

    EntityConfig entityConfig = new EntityConfig();

    @Override
    public void perform(GridMap gridMap) {
        restoreHare(gridMap);
        restoreCow(gridMap);
        restoreGrass(gridMap);
    }

    private void restoreHare(GridMap gridMap) {
        int actualHareCount = getHareCount(gridMap);
        entityConfig.setCreaturesQuantity(gridMap);
        while (actualHareCount < entityConfig.getHareQty()) {
            gridMap.initMapCells();
            Entity hare = new Hare(EntityConfig.HARE_HP, EntityConfig.HARE_SPEED);
            gridMap.placeEntity(gridMap.getRandomAvailableCell(), hare);
            actualHareCount++;
        }
    }

    private void restoreGrass(GridMap gridMap) {
        int actualConsumableCount = getConsumableCount(gridMap);
        entityConfig.setCreaturesQuantity(gridMap);
        while (actualConsumableCount < entityConfig.getGrassQty()) {
            gridMap.initMapCells();
            Entity grass = new Grass();
            gridMap.placeEntity(gridMap.getRandomAvailableCell(), grass);
            actualConsumableCount++;
        }
    }

    private void restoreCow(GridMap gridMap) {
        int actualCowCount = getCowCount(gridMap);
        entityConfig.setCreaturesQuantity(gridMap);
        while (actualCowCount < entityConfig.getCowQty()) {
            gridMap.initMapCells();
            Entity cow = new Cow(EntityConfig.COW_HP, EntityConfig.COW_SPEED);
            gridMap.placeEntity(gridMap.getRandomAvailableCell(), cow);
            actualCowCount++;
        }
    }

    private int getHareCount(GridMap gridMap) {
        int hareCount = 0;
        List<Entity> allEntities = gridMap.getEntitiesList();
        for (Entity entity : allEntities) {
            if (entity instanceof Hare) {
                hareCount++;
            }
        }
        return hareCount;
    }

    private int getCowCount(GridMap gridMap) {
        int cowCount = 0;
        List<Entity> allEntities = gridMap.getEntitiesList();
        for (Entity entity : allEntities) {
            if (entity instanceof Cow) {
                cowCount++;
            }
        }
        return cowCount;
    }

    private int getConsumableCount(GridMap gridMap) {
        int consumableCount = 0;
        List<Entity> allEntities = gridMap.getEntitiesList();
        for (Entity entity : allEntities) {
            if (entity instanceof Consumable) {
                consumableCount++;
            }
        }
        return consumableCount;
    }
}
