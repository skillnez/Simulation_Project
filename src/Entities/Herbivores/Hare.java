package Entities.Herbivores;

public class Hare extends Herbivore {

    public Hare(int healthPoints, int speed) {
        super(healthPoints, speed);
    }

    @Override
    public String toString() {
        return "Заяц";
    }
}
