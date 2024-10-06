package Render;

import Sprites.EntitySprite;
import WorldMap.Coordinates;
import WorldMap.WorldMap;

public class WorldMapRenderer {
    public void render(WorldMap worldMap) {
        for (int vertical = 0; vertical < worldMap.getVerticalMapSize(); vertical++) {
            for (int horizontal = 0; horizontal < worldMap.getHorizontalMapSize(); horizontal++) {
                Coordinates coordinates = new Coordinates(horizontal, vertical);
                System.out.print(" ");
                if (worldMap.getFlatMap().containsKey(coordinates)) {
                    System.out.print(worldMap.getFlatMap().get(coordinates));
                } else {
                    System.out.print(EntitySprite.GROUND);
                }
                if (horizontal == (worldMap.getHorizontalMapSize() - 1)) {
                    System.out.println();
                }
            }
        }

    }
}
