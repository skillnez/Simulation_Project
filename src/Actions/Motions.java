package Actions;

import Entities.Consumable;
import Entities.Creature;
import Entities.Entity;
import Entities.Herbivores.Herbivore;
import Entities.Predators.Predator;
import Entities.StaticObjects.Inanimate;
import Pathfinder.PathFinder;
import WorldMap.Coordinates;
import WorldMap.WorldMap;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Motions extends TurnActions{
    //Починено, но к этому методу повнимательнее
    //Пока все оттестировано и закомичено, мне нравится как работает!
    @Override
    public void perform(WorldMap worldMap) {
        Set<Entity> proceededEntities = new HashSet<>();
        for (Entity entry : worldMap.getEntitiesList()) {
            if (!proceededEntities.contains(entry) && worldMap.getCoordinatesByEntity(entry) != null) {
                if (entry instanceof Predator || entry instanceof Herbivore ) {
                    //когда заяц съедает траву, получаем эксепшн
                    Coordinates seeker = worldMap.getCoordinatesByEntity(entry);
                    List<Coordinates> creaturePath = new PathFinder().findPath(worldMap, seeker);
                    defineMove(worldMap, creaturePath, seeker, entry);
                }
                proceededEntities.add(entry);
            }
        }
    }

    private void defineMove(WorldMap worldMap, List<Coordinates> path, Coordinates current, Entity entity) {
        if (entity instanceof Herbivore || entity instanceof Predator) {
            int entitySpeed = ((Creature) entity).getSpeed();
            if (underAttack(entitySpeed, path) && !path.isEmpty()) {
                Entity target = worldMap.getEntityByCoordinate(path.getLast());
                initiateAttacks(entity, target, worldMap, path, current);
                path.clear();
            }
            if (!underAttack(entitySpeed, path) && !path.isEmpty()) {
                initiateMove(worldMap, path.get(entitySpeed-1), current, entity);
                path.clear();
            }
        }
    }

    private void initiateAttacks(Entity attacker, Entity target, WorldMap worldMap, List<Coordinates> path, Coordinates current) {
        if (attacker instanceof Herbivore) {
            ((Herbivore) attacker).consume((Consumable) target);
            System.out.println(attacker + " съел " + target);
            System.out.println(((Herbivore) attacker).getId());
            System.out.println();
            System.out.println("У существа здоровье: " + ((Herbivore) attacker).getHealthPoints());
            initiateMove(worldMap, path.getLast(), current, attacker);
        }
        if (attacker instanceof Predator) {
            ((Predator) attacker).attack((Herbivore) target);
            System.out.println(attacker + " нанес " + target + " " + ((Predator) attacker).getAttackPower() + " урона");
            System.out.println("id: " +((Predator) attacker).getId());
            System.out.println("id: " + ((Herbivore) target).getId());
            System.out.println("У " + target + " " + ((Herbivore) target).getHealthPoints() + " хп" + " осталось");
            if (!((Herbivore) target).isAlive()) {
                System.out.println(attacker + " съел " + target);
                initiateMove(worldMap, path.getLast(), current, attacker);
            } else if (path.size() != 1) {
                path.removeLast();
                initiateMove(worldMap, path.getLast(), current, attacker);
            }
        }
    }

    private void initiateMove(WorldMap worldMap, Coordinates toMove, Coordinates current, Entity entity) {
        worldMap.placeEntity(toMove, entity);
        worldMap.removeEntity(current);
    }

    private boolean underAttack(int speed, List<Coordinates> path) {
        return (path.size()) <= speed;
    }
}
