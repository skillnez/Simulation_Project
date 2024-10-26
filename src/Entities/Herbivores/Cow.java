package Entities.Herbivores;

public class Cow extends Herbivore {

    public Cow(int healthPoints, int speed) {
        super(healthPoints, speed);
    }

    @Override
    public String toString() {
        return "Корова";
    }
}
