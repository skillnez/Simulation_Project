package WorldMap;

import Entities.Entity;
import Sprites.EntitySprite;

public class Renderer {

    public void render(WorldMap worldMap) {
        for (int vertical = 0; vertical < worldMap.getVerticalMapSize(); vertical++) {
            for (int horizontal = 0; horizontal < worldMap.getHorizontalMapSize(); horizontal++) {
                Coordinates coordinates = new Coordinates(horizontal, vertical);
                System.out.print(" ");
                if (worldMap.getFlatMap().containsKey(coordinates)) {
                    System.out.print(setEntitySprite(worldMap.getFlatMap().get(coordinates)));
                } else {
                    System.out.print(EntitySprite.GROUND);
                }
            }
            System.out.println();
        }
    }

    private String setEntitySprite(Entity entity) {
        return switch (entity.getClass().getSimpleName()) {
            case "Wolf" -> EntitySprite.WOLF.toString();
            case "Hare" -> EntitySprite.HARE.toString();
            case "Cow" -> EntitySprite.COW.toString();
            case "Fox" -> EntitySprite.FOX.toString();
            case "Tree" -> EntitySprite.TREE.toString();
            case "Rock" -> EntitySprite.ROCK.toString();
            case "Grass" -> EntitySprite.GRASS.toString();
            default -> EntitySprite.GROUND.toString();
        };
    }
}

