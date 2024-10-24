package Actions;

import Entities.Entity;
import Entities.EntityConfig;
import Entities.Herbivores.Cow;
import Entities.Herbivores.Hare;
import Entities.StaticObjects.Grass;
import WorldMap.WorldMap;
import WorldMap.Coordinates;

import java.util.Map;

public class Restorer extends TurnActions {


    //надо доделывать / переделывать / возможно следует идти к рефакторингу карты
    private int hareQuantity;
    private int cowQuantity;
    private int grassQuantity;

    @Override
    public void perform(WorldMap worldMap) {
        hareQuantity = 0;
        cowQuantity = 0;
        grassQuantity = 0;

//        restoreGrass(worldMap);
//        restoreHerbivores(worldMap);
    }

//    private void restoreGrass(WorldMap worldMap) {
//        EntityConfig entityConfig = new EntityConfig();
//        entityConfig.setCreaturesQuantity(worldMap);
//        setActualGrassQuantity(worldMap);
//
//        if (entityConfig.getGrassQty() > grassQuantity) {
//            MapFiller mapFiller = new MapFiller();
//            int grassToSpawn = entityConfig.getGrassQty() - grassQuantity;
//            worldMap.setMapCells();
//            mapFiller.spawnEntities(worldMap, Grass.class, grassToSpawn);
//        }
//    }
//
//    private void restoreHerbivores(WorldMap worldMap) {
//        EntityConfig entityConfig = new EntityConfig();
//        entityConfig.setCreaturesQuantity(worldMap);
//        setActualCreatureQuantity(worldMap);
//
//        if (entityConfig.getHareQty() > hareQuantity) {
//            MapFiller mapFiller = new MapFiller();
//            int hareToSpawn = entityConfig.getHareQty() - hareQuantity;
//            worldMap.setMapCells();
//            mapFiller.spawnEntities(worldMap, Hare.class, hareToSpawn);
//        }
//        if (entityConfig.getCowQty() > cowQuantity) {
//            MapFiller mapFiller = new MapFiller();
//            int cowToSpawn = entityConfig.getCowQty() - cowQuantity;
//            worldMap.setMapCells();
//            mapFiller.spawnEntities(worldMap, Cow.class, cowToSpawn);
//        }
//    }
//
//    private void setActualCreatureQuantity(WorldMap worldMap) {
//        hareQuantity = 0; // Ensure these start at 0 before counting
//        cowQuantity = 0;
//        for (Map.Entry<Coordinates, Entity> entry : worldMap.getFlatMap().entrySet()) {
//            if (entry.getValue() instanceof Hare) {
//                hareQuantity++;
//            } else if (entry.getValue() instanceof Cow) {
//                cowQuantity++;
//            }
//        }
//    }
//
//    private void setActualGrassQuantity(WorldMap worldMap) {
//        grassQuantity = 0; // Ensure this starts at 0 before counting
//        for (Map.Entry<Coordinates, Entity> entry : worldMap.getFlatMap().entrySet()) {
//            if (entry.getValue() instanceof Grass) {
//                grassQuantity++;
//            }
//        }
//    }
}
