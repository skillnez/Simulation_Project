import WorldMap.Renderer;
import WorldMap.WorldMap;

public class Main {
    public static void main(String[] args) {
        WorldMap worldMap = new WorldMap(3,3);
        worldMap.mapSetup();
        Renderer renderer = new Renderer();
        renderer.render(worldMap);
    }
}
