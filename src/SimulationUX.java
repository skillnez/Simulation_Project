import java.util.Scanner;

public class SimulationUX {
    private final Simulation simulation;

    public SimulationUX(Simulation simulation) {
        this.simulation = simulation;
    }

    public void perform() {
        Scanner scanner = new Scanner(System.in);
        String command;

        while (true) {
            System.out.println("b - begin" + " | p - pause" + " | c - continue" + " | s - stop" + " | q - quit");
            System.out.println("Введите команду (b, p, c, s, q):");
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

    public String consoleGraphics(int index) {
        String[] helloMessage = {
                "███████ ██ ███    ███ ██    ██ ██       █████  ████████ ██  ██████  ███    ██ \n" +
                        "██      ██ ████  ████ ██    ██ ██      ██   ██    ██    ██ ██    ██ ████   ██ \n" +
                        "███████ ██ ██ ████ ██ ██    ██ ██      ███████    ██    ██ ██    ██ ██ ██  ██ \n" +
                        "     ██ ██ ██  ██  ██ ██    ██ ██      ██   ██    ██    ██ ██    ██ ██  ██ ██ \n" +
                        "███████ ██ ██      ██  ██████  ███████ ██   ██    ██    ██  ██████  ██   ████ "
        };
        return helloMessage[index];
    }

}