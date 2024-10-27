package Simulation;

public class SimulationUI {

    private final SimulationTurnCounter simulationTurnCounter;

    public SimulationUI(SimulationTurnCounter simulationTurnCounter) {
        this.simulationTurnCounter = simulationTurnCounter;
    }

    public final String consoleGraphics(int index) {
        String[] uiMessages = {
                        "███████ ██ ███    ███ ██    ██ ██       █████  ████████ ██  ██████  ███    ██ \n" +
                        "██      ██ ████  ████ ██    ██ ██      ██   ██    ██    ██ ██    ██ ████   ██ \n" +
                        "███████ ██ ██ ████ ██ ██    ██ ██      ███████    ██    ██ ██    ██ ██ ██  ██ \n" +
                        "     ██ ██ ██  ██  ██ ██    ██ ██      ██   ██    ██    ██ ██    ██ ██  ██ ██ \n" +
                        "███████ ██ ██      ██  ██████  ███████ ██   ██    ██    ██  ██████  ██   ████ "
        , printCounter(), "p - pause" + " | c - continue" + " | q - quit",
                "Введите команду (p, c, q):", "b - begin" + " | q - quit", "Введите команду (b, q):"};
        return uiMessages[index];
    }

    private String printCounter() {
        return "--======================--\n" +
                "         "+"Ход: "+simulationTurnCounter.getTurnCounter() +
                "\n--======================--";
    }

}
