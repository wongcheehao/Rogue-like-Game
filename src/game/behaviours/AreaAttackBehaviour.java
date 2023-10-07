package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AreaAttackAction;

/**
 * A class that figures out an Attack action that will attack the target.
 *
 * Created by:
 * @author Chee Hao Wong
 *
 */
public class AreaAttackBehaviour implements Behaviour {

    /**
     * The target to attack.
     */
    private final Actor target;

    /**
     * The direction of incoming attack.
     */
    private String direction;

    /**
     * Constructor.
     *
     * @param target the Actor to attack
     * @param direction the direction of incoming attack.
     */
    public AreaAttackBehaviour(Actor target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    /**
     * Returns a Area Attack Action to attack an actor, if possible.
     * If no Area Attack is possible, returns null.
     *
     * @param actor the Actor enacting the behaviour
     * @param map the map that actor is currently on
     * @return an Action, or null if no Area Attack is possible
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
//        Check if target and attacker still on map.
        if (!map.contains(target) || !map.contains(actor))
            return null;

        Location here = map.locationOf(actor);
//        Check if target still in the exits of actor
        boolean flag = isTargetInExits(here);
        //      if target not in the exits of actor, return null.
        if(flag == false){
            return null;
        }

//         50 % using special skill of intrinsic weapon
        Action action = useSpecialSkill();

        return action;
    }


//     50 % using special skill of intrinsic weapon
    private Action useSpecialSkill() {
        Action action = null;
        if(Math.random() <= 0.5) {
            action = new AreaAttackAction(target, direction);
        }
        return action;
    }

//      check if target is still in the exits of actor
    private boolean isTargetInExits(Location here) {
        boolean flag = false;
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if(destination.getActor() != null && destination.getActor().equals(target)){
                flag = true;
            }
        }
        return flag;
    }
}
