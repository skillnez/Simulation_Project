import Simulation.Simulation;
import Simulation.SimulationTurnCounter;
import Simulation.SimulationUI;
import Simulation.SimulationUX;

public class Main {
    public static void main(String[] args) {
        SimulationTurnCounter counter = new SimulationTurnCounter();
        SimulationUI ui = new SimulationUI(counter);
        Simulation simulation = new Simulation(counter, ui);
        SimulationUX simulationUX = new SimulationUX(simulation, ui);
        System.out.println(ui.consoleGraphics(0));
        simulationUX.perform();
    }
}