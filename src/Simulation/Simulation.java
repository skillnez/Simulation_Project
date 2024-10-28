package Simulation;

import Actions.Actions;
import WorldMap.ConsoleRenderer;
import WorldMap.GridMap;
import Actions.Motions;
import Actions.EntityRestorer;
import Actions.MapFiller;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Simulation {

    private final GridMap gridMap = new GridMap( 30, 30);
    private final List<Actions> actions = List.of(new Motions(), new EntityRestorer());
    private final ConsoleRenderer consoleRenderer = new ConsoleRenderer();
    private final AtomicBoolean running = new AtomicBoolean(false);
    private final AtomicBoolean paused = new AtomicBoolean(false);
    private final SimulationTurnCounter turnCounter;
    private final SimulationUI simulationUI;

    public Simulation(SimulationTurnCounter turnCounter, SimulationUI simulationUI) {
        this.turnCounter = turnCounter;
        this.simulationUI = simulationUI;
        Actions mapFiller = new MapFiller();
        mapFiller.perform(gridMap);
    }

    public void start() {
        running.set(true);
        paused.set(false);

        new Thread(() -> {
            while (running.get()) {
                if (!paused.get()) {
                    nextTurn();
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.out.println("Симуляция была прервана");
                    }
                }
            }
        }).start();
    }

    public void nextTurn () {
        turnCounter.countTurns();
        System.out.println(simulationUI.consoleGraphics(1));
        for (Actions action : actions) {
            action.perform(gridMap);
        }
        consoleRenderer.render(gridMap);
        System.out.println(simulationUI.consoleGraphics(2));
    }

    public void pause() {
        paused.set(true);
    }

    public void resume() {
        paused.set(false);
    }

    public void stop() {
        running.set(false);
    }

}