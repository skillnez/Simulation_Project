import Actions.InitActions;
import Actions.TurnActions;
import Entities.Entity;
import Pathfinder.PathFinder;
import WorldMap.Renderer;
import WorldMap.WorldMap;

public class Main {
    public static void main(String[] args) {

        WorldMap worldMap = new WorldMap(5, 5);
        InitActions initActions = new InitActions();
        Renderer renderer = new Renderer();
        initActions.mapSetup(worldMap);
        renderer.render(worldMap);
        System.out.println();

        PathFinder pathFinder = new PathFinder();
        pathFinder.bfs(worldMap);
        renderer.render(worldMap);


        int i = 123;
    }
}
