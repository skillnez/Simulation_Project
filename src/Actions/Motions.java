package Actions;

import Entities.Consumable;
import Entities.Creature;
import Entities.Entity;
import Entities.Herbivores.Herbivore;
import Entities.Predators.Predator;
import Pathfinder.PathFinder;
import WorldMap.Coordinates;
import WorldMap.GridMap;
import WorldMap.BaseMap;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Motions extends TurnActions{

    @Override
    public void perform(GridMap gridMap) {
        Set<Entity> proceededEntities = new HashSet<>();
        for (Entity entry : gridMap.getEntitiesList()) {
            if (!proceededEntities.contains(entry) && gridMap.getCoordinates(entry) != null) {
                if (entry instanceof Predator || entry instanceof Herbivore ) {
                    Coordinates seeker = gridMap.getCoordinates(entry);
                    List<Coordinates> creaturePath = new PathFinder().findPath(gridMap, seeker);
                    defineMove(gridMap, creaturePath, seeker, entry);
                }
                proceededEntities.add(entry);
            }
        }
    }

    private void defineMove(BaseMap baseMap, List<Coordinates> path, Coordinates from, Entity entity) {
        if (entity instanceof Herbivore || entity instanceof Predator) {
            int entitySpeed = ((Creature) entity).getSpeed();
            if (underAttack(entitySpeed, path) && !path.isEmpty()) {
                Entity target = baseMap.getEntity(path.getLast());
                initiateAttacks(entity, target, baseMap, path, from);
                path.clear();
            }
            if (!underAttack(entitySpeed, path) && !path.isEmpty()) {
                initiateMove(baseMap, path.get(entitySpeed-1), from, entity);
                path.clear();
            }
        }
    }

    private void initiateAttacks(Entity attacker, Entity target, BaseMap baseMap, List<Coordinates> path, Coordinates from) {
        if (attacker instanceof Herbivore) {
            ((Herbivore) attacker).consume((Consumable) target);
//            System.out.println(attacker + " съел " + target);
//            System.out.println(((Herbivore) attacker).getId());
//            System.out.println();
//            System.out.println("У существа здоровье: " + ((Herbivore) attacker).getHealthPoints());
            initiateMove(baseMap, path.getLast(), from, attacker);
        }
        if (attacker instanceof Predator) {
            ((Predator) attacker).attack((Herbivore) target);
//            System.out.println(attacker + " нанес " + target + " " + ((Predator) attacker).getAttackPower() + " урона");
//            System.out.println("id: " +((Predator) attacker).getId());
//            System.out.println("id: " + ((Herbivore) target).getId());
//            System.out.println("У " + target + " " + ((Herbivore) target).getHealthPoints() + " хп" + " осталось");
            if (!((Herbivore) target).isAlive()) {
//                System.out.println(attacker + " съел " + target);
                initiateMove(baseMap, path.getLast(), from, attacker);
            } else if (path.size() != 1) {
                path.removeLast();
                initiateMove(baseMap, path.getLast(), from, attacker);
            }
        }
    }

    private void initiateMove(BaseMap baseMap, Coordinates to, Coordinates from, Entity entity) {
        baseMap.placeEntity(to, entity);
        baseMap.removeEntity(from);
    }

    private boolean underAttack(int speed, List<Coordinates> path) {
        return (path.size()) <= speed;
    }
}
