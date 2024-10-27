
import Actions.Actions;
import Actions.Motions;
import Actions.MapFiller;
import Actions.EntityRestorer;
import WorldMap.ConsoleRenderer;
import WorldMap.GridMap;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        GridMap gridMap = new GridMap( 10, 10);
        Actions mapFiller = new MapFiller();
        List<Actions> actions = List.of(new Motions(), new EntityRestorer());
        ConsoleRenderer consoleRenderer = new ConsoleRenderer();
        Simulation simulation = new Simulation(gridMap, mapFiller, actions, consoleRenderer);
        SimulationUX simulationUX = new SimulationUX(simulation);
        System.out.println(simulationUX.consoleGraphics(0));
        simulationUX.perform();
    }
}
