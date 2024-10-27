package Simulation;

import java.util.Scanner;

public class SimulationUX {
    private final Simulation simulation;
    private final SimulationUI simulationUI;

    public SimulationUX(Simulation simulation, SimulationUI simulationUI) {
        this.simulation = simulation;
        this.simulationUI = simulationUI;
    }

    public void perform() {
        Scanner scanner = new Scanner(System.in);
        String command;

        while (true) {
            System.out.println(simulationUI.consoleGraphics(2));
            System.out.println(simulationUI.consoleGraphics(3));
            command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "b":
                    simulation.start();
                    break;
                case "p":
                    simulation.pause();
                    break;
                case "c":
                    simulation.resume();
                    break;
                case "q":
                    simulation.stop();
                    System.out.println("Они еще придут за тобой...");
                    return; // Завершение программы
                default:
                    System.out.println("Неизвестная команда. Попробуйте снова.");
            }
        }
    }
}