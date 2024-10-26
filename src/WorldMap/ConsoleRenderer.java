package WorldMap;

import Entities.Entity;
import Sprites.EntitySprite;

public class ConsoleRenderer {

    public void render(GridMap gridMap) {
        for (int vertical = 0; vertical < gridMap.getVerticalMapSize(); vertical++) {
            for (int horizontal = 0; horizontal < gridMap.getHorizontalMapSize(); horizontal++) {
                Coordinates coordinates = new Coordinates(horizontal, vertical);
                System.out.print(EntitySprite.BACKGROUND + " "); // нужно для увеличения интервала между ячейками карты и для фона
                renderEntitySprite(gridMap, coordinates);
            }
            String reset = "\u001B[0m";
            System.out.println(reset); // нужно для переноса строки и цвета фона
        }
        System.out.println();
    }

    private void renderEntitySprite(GridMap gridMap, Coordinates coordinates) {
        if (gridMap.getCoordinatesList().contains(coordinates)) {
            System.out.print(ChoseEntitySprite(gridMap.getEntity(coordinates)));
        } else {
            System.out.print(EntitySprite.GROUND);
        }
    }

    private String ChoseEntitySprite(Entity entity) {
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

