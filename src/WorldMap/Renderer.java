package WorldMap;

import Entities.Entity;
import Sprites.EntitySprite;

public class Renderer {

//    Попытки сильнее разделить этот метод приводили к кратному усложнению понимания кода,
//    поэтому я не нашел пока необходимости это сделать

    public void render(WorldMap worldMap) {
        for (int vertical = 0; vertical < worldMap.getVerticalMapSize(); vertical++) {
            for (int horizontal = 0; horizontal < worldMap.getHorizontalMapSize(); horizontal++) {
                Coordinates coordinates = new Coordinates(horizontal, vertical);
                System.out.print("\033[48;5;57m"+" ");// нужно для увеличения интервала между ячейками карты
                renderEntitySprite(worldMap, coordinates);
            }
            String reset = "\u001B[0m";
            System.out.println(reset); // нужно для переноса строки
        }
    }

    private void renderEntitySprite(WorldMap worldMap, Coordinates coordinates) {
        if (worldMap.getFlatMap().containsKey(coordinates)) {
            System.out.print(ChoseEntitySprite(worldMap.getFlatMap().get(coordinates)));
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

