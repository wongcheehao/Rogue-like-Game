package game.actors.combatclass;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.enums.Status;
import game.resets.ResetManager;
import game.resets.Resettable;

import java.util.Map;
import java.util.TreeMap;

/**
 * An actor which helps player to fight against enemies
 * Created by:
 * @author Park Jun Koo
 */
public class Ally extends Actor implements Resettable{

    /**
     * An ordered collection storing the behaviours of ally
     */
    protected Map<Integer, Behaviour> behaviours = new TreeMap<>(); // priority, behaviour

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Ally() {
        super("Ally", 'A', 1);
        behaviours.put(3, new WanderBehaviour());
        this.addCapability(Status.COMBAT_CLASS);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addCapability(Status.RESTING);
    }

    /**
     * At each turn, select a valid action to perform.
     * Remove this actor from map if it has Status.WIPED.
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {


        // If this actor has Status.WIPED, remove it from map.
        if (this.hasCapability(Status.WIPED) && !this.hasCapability(Status.RESTING)) {
            map.removeActor(this);
            return new DoNothingAction();
        }


        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if(action != null){
                return action;
            }
        }

        return new DoNothingAction();
    }

    /**
     * add actions that can be performed by the ally
     * Ally can be attacked by an actor who has HOSTILE capability
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return actions to
     */
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HOSTILE)){

            // Attack with intrinsic weapon
            actions.add(new AttackAction(this, direction));

            // Attack with weapon
            for (WeaponItem weaponItem: otherActor.getWeaponInventory()){
                actions.add(new AttackAction(this, direction, weaponItem));
                actions.add(weaponItem.getSkill(this,direction));
            }

        }

        behaviours.put(1, new AttackBehaviour(otherActor, direction));
        return actions;
    }

    /**
     * Adds WIPED capability to the ally
     */
    @Override
    public void reset() {
        this.addCapability(Status.WIPED);
    }
}
