package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.enums.EnemyType;
import game.actions.AttackAction;

/**
 * A class that figures out an Attack action that will attack the target.
 *
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by: Chee Hao Wong
 *
 */
public class AttackBehaviour implements Behaviour {

    /**
     * The actor to attack.
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
    public AttackBehaviour(Actor target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    /**
     * Returns a Attack Action to attack an actor, if possible.
     * If no attack is possible, returns null.
     *
     * @param actor the Actor enacting the behaviour
     * @param map the map that actor is currently on
     * @return an Action, or null if no attack is possible
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Action action = null;

//      Check attacker and target are on the map/
        if (!map.contains(target) || !map.contains(actor))
            return action;

        Location here = map.locationOf(actor);
        boolean flag = isTargetInExits(actor, here);
//      if target not in the exits of actor, return null.
        if(!flag){
            return action;
        }

//        Use first weapon to attack if enemy has a weapon
        if (!actor.getWeaponInventory().isEmpty()) {
            action = attackWithFirstWeapon(actor);
        }

//       Use intrinsic weapon to attack if enemy does not have a weapon
        else {
            action = attackWithInstinsicWeapon();
        }

        return action;
    }

//    Attack with instinsic weapon
    private Action attackWithInstinsicWeapon() {
        return new AttackAction(target, direction);
    }

//    Attack with first weapon
    private Action attackWithFirstWeapon(Actor actor) {
        Action action;
        Weapon weapon = actor.getWeaponInventory().get(0);
//            50 % possibilities to use special skill of weapon
        if (Math.random() <= 0.5) {
            action = weapon.getSkill(target, direction);
        } else {
            action = new AttackAction(target, direction, weapon);
        }
        return action;
    }

    private boolean isTargetInExits(Actor actor, Location here) {
        boolean flag = false;

        //        check if target is still in the exits of actor
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if(destination.getActor() != null && destination.getActor().equals(target) &&
                    !(target.findCapabilitiesByType(EnemyType.class).equals(actor.findCapabilitiesByType(EnemyType.class)))){
                flag = true;
            }
        }
        return flag;
    }
}
