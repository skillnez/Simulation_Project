package Entities;

import java.util.Objects;

public abstract class Entity {

    public static int idCounter = 0;
    public int id;

    public Entity() {
        this.id = generateId();
    }

    public int generateId() {
        return idCounter++;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return id == entity.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
