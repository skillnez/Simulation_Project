package Simulation;

public class SimulationTurnCounter {

    private int turnCounter = 0;

    public void countTurns() {
        turnCounter++;
    }

    public int getTurnCounter() {
        return turnCounter;
    }
}
