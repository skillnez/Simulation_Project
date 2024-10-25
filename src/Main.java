
import Actions.Actions;
import Actions.Motions;
import Actions.MapFiller;
import Actions.Restorer;
import Entities.Entity;
import WorldMap.ConsoleRenderer;
import WorldMap.WorldMap;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        WorldMap worldMap = new WorldMap(30, 30);
        Actions mapFiller = new MapFiller();
        Actions creatureMotions = new Motions();
        Actions restorer = new Restorer();
        ConsoleRenderer consoleRenderer = new ConsoleRenderer();
        mapFiller.perform(worldMap);
        consoleRenderer.render(worldMap);
        System.out.println();
        for (int i = 0; i < 100; i++) {
            creatureMotions.perform(worldMap);
            consoleRenderer.render(worldMap);
            //restorer.perform(worldMap);
            System.out.println();
        }

        int i = 123;
    }
}
