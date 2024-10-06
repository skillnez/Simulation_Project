package WorldMap;

import java.util.Objects;

public class Coordinates {

    private final int horizontal;
    private final int vertical;

    public Coordinates(int horizontal, int vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return horizontal == that.horizontal && vertical == that.vertical;
    }

    @Override
    public int hashCode() {
        return Objects.hash(horizontal, vertical);
    }
}
