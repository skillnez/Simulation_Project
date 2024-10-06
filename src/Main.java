import Entities.Wolf;
import Render.WorldMapRenderer;
import Sprites.EntitySprite;
import WorldMap.WorldMap;

public class Main {
    public static void main(String[] args) {
        WorldMap worldMap = new WorldMap();
        worldMap.mapSetup(20,20);

        WorldMapRenderer worldMapRenderer = new WorldMapRenderer();
        worldMapRenderer.render(worldMap);
    }
}
