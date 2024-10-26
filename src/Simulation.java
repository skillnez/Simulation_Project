import Actions.Actions;
import WorldMap.ConsoleRenderer;
import WorldMap.GridMap;
import Actions.MapFiller;
import Actions.Motions;
import Actions.EntityRestorer;

import java.util.ArrayList;
import java.util.List;

public class Simulation {

    GridMap gridMap = new GridMap(7, 7);
    ConsoleRenderer consoleRenderer = new ConsoleRenderer();
    Actions mapFiller = new MapFiller();
    List<Actions> actions = List.of(new Motions(), new EntityRestorer());

    public void nextTurn () {
        for (Actions action : actions) {
            action.perform(gridMap);
        }
        consoleRenderer.render(gridMap);
    }

}
