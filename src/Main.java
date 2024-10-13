import Actions.InitActions;
import Actions.TurnActions;
import WorldMap.Renderer;
import WorldMap.WorldMap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        WorldMap worldMap = new WorldMap(10, 10);
        InitActions initActions = new InitActions();
        Renderer renderer = new Renderer();
        initActions.mapSetup(worldMap);
        renderer.render(worldMap);
        System.out.println();

        for (int i = 0; i < 20; i++) {
            TurnActions turnActions = new TurnActions();
            turnActions.moveEntities(worldMap);
            renderer.render(worldMap);
            System.out.println();
        }
    }
}
