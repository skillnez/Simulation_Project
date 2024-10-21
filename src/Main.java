import Actions.InitActions;
import Actions.TurnActions;
import Entities.Entity;
import Pathfinder.PathFinder;
import WorldMap.Renderer;
import WorldMap.WorldMap;

public class Main {
    public static void main(String[] args) {

        WorldMap worldMap = new WorldMap(7, 4);
        InitActions initActions = new InitActions();
        Renderer renderer = new Renderer();
        TurnActions turnActions = new TurnActions();
        initActions.mapSetup(worldMap);
        renderer.render(worldMap);
        System.out.println();
        for (int i = 0; i < 7; i++) {
            turnActions.moveEntities2(worldMap);
            renderer.render(worldMap);
            System.out.println();
        }

        int i = 123;
    }
}
