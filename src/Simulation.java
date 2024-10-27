import Actions.Actions;
import WorldMap.ConsoleRenderer;
import WorldMap.GridMap;
import Actions.Motions;
import Actions.EntityRestorer;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Simulation {

    GridMap gridMap;
    Actions mapFiller;
    List<Actions> actions = List.of(new Motions(), new EntityRestorer());
    ConsoleRenderer consoleRenderer;
    private final AtomicBoolean running = new AtomicBoolean(false);
    private final AtomicBoolean paused = new AtomicBoolean(false);

    public Simulation(GridMap gridMap, Actions mapFiller, List<Actions> actions, ConsoleRenderer consoleRenderer) {
        this.gridMap = gridMap;
        this.mapFiller = mapFiller;
        this.actions = actions;
        this.consoleRenderer = consoleRenderer;
        this.mapFiller.perform(gridMap);
    }

    public void start() {
            running.set(true);
            paused.set(false);

        new Thread(() -> {
            while (running.get()) {
                if (!paused.get()) {
                    nextTurn();
                    try {
                        Thread.sleep(1000); // Задержка между ходами
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.out.println("Симуляция была прервана");
                    }
                }
            }
        }).start();
    }

    public void nextTurn () {
        for (Actions action : actions) {
            action.perform(gridMap);
        }
        consoleRenderer.render(gridMap);
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
