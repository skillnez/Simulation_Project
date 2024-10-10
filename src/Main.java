import Entities.EntityConfig;
import WorldMap.WorldMap;
import Actions.InitActions;
import WorldMap.Renderer;

public class Main {
    public static void main(String[] args) {
        WorldMap worldMap = new WorldMap(10,10);
        InitActions initActions = new InitActions();
        Renderer renderer = new Renderer();
        initActions.mapSetup(worldMap);
        renderer.render(worldMap);
    }
}
