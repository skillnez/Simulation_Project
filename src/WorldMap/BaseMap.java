package WorldMap;

import Entities.Entity;

public interface BaseMap {
    Entity getEntity(Coordinates coordinates);

    Coordinates getCoordinates(Entity entity);

    void removeEntity(Entity entity);

    void removeEntity(Coordinates coordinates);

    void placeEntity(Coordinates coordinates, Entity entity);
}
