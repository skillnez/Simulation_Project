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
        , printCounter(), "b - begin" + " | p - pause" + " | c - continue" + " | q - quit",
                "Введите команду (b, p, c, s, q):"};
        return uiMessages[index];
    }

    private String printCounter() {
        return "--======================--\n" +
                "         "+"Ход: "+simulationTurnCounter.getTurnCounter() +
                "\n--======================--";
    }

}
