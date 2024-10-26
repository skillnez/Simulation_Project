
import Actions.Actions;
import Actions.Motions;
import Actions.MapFiller;
import Actions.EntityRestorer;
import Sprites.EntitySprite;
import WorldMap.ConsoleRenderer;
import WorldMap.GridMap;

public class Main {
    public static void main(String[] args) {

        GridMap gridMap = new GridMap(7, 7);
        Actions mapFiller = new MapFiller();
        Actions creatureMotions = new Motions();
        Actions restorer = new EntityRestorer();
        ConsoleRenderer consoleRenderer = new ConsoleRenderer();
        mapFiller.perform(gridMap);
        consoleRenderer.render(gridMap);
        System.out.println();
        Simulation simulation = new Simulation();
        for (int i = 0; i < 1000; i++) {
//            creatureMotions.perform(gridMap);
//            restorer.perform(gridMap);
//            consoleRenderer.render(gridMap);
//            System.out.println();
            simulation.nextTurn();
        }

        int i = 123;
    }
}
