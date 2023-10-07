package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Utils.RandomNumberGenerator;
import game.actions.AttackAction;
import game.actions.RemoveAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.WanderBehaviour;
import game.behaviours.FollowBehaviour;
import game.enums.EnemyType;
import game.enums.Status;
import game.Items.RuneManager;
import game.resets.ResetManager;
import game.resets.Resettable;

import java.util.Map;
import java.util.TreeMap;

/**
 * A class to represent Enemy
 *
 * Created by:
 * @author Chee Hao Wong
 * Modified by:
 *
 */
public abstract class Enemy extends Actor implements Resettable {

    /**
     * An ordered collection storing the behaviours of enemies
     */
    protected Map<Integer, Behaviour> behaviours = new TreeMap<>(); // priority, behaviour

    /**
     * Constructor. Every enemy has wander behaviour.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Enemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.HOSTILE);
        behaviours.put(3, new WanderBehaviour());
        RuneManager.getInstance().registerRune(this, RandomNumberGenerator.getRandomInt(this.getMinRunes(),this.getMaxRunes()));
        ResetManager.getInstance().registerResettable(this);
    }

    /**
     * At each turn, select a valid action to perform.
     * Remove this actor from map if it has Status.WIPED.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the valid action that can be performed in that iteration or null if no valid action is found
     */
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        //        If reset, remove the enemy
        if (getWiped()) return new RemoveAction();

        //       Despawn enemy from map
        if (despawned()) return new RemoveAction();

        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if(action != null){
                removeFollowing(behaviour);
                return action;
            }
        }

        return new DoNothingAction();
    }

    //        If the enemy is no longer following the player, remove the FOLLOWING status
    private void removeFollowing(Behaviour behaviour) {
        if(!(behaviour instanceof FollowBehaviour)){
            this.removeCapability(Status.FOLLOWING);
        }
    }

    //        If this actor has Status.WIPED, remove it from map.
    private boolean getWiped() {
        if (this.hasCapability(Status.WIPED)) {
            return true;
        }
        return false;
    }

    //        Every turn there is 10% of possibilities enemy be despawned unless the enemy is following the player
    private boolean despawned() {
        if(Math.random() <= 0.1 && !this.hasCapability(Status.FOLLOWING)){
            return true;
        }
        return false;
    }

    /**
     * The enemy can be attacked by any actor that has the HOSTILE_TO_ENEMY capability
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of Actions.
     */
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            attackWithIntrinsicWeapon(direction, actions);
            attackWithWeapon(otherActor, direction, actions);
            behaviours.put(2, new FollowBehaviour(otherActor));
        }

        behaviours.put(1, new AttackBehaviour(otherActor, direction));
        return actions;
    }

    // Attack with intrinsic weapon
    private void attackWithIntrinsicWeapon(String direction, ActionList actions) {

        actions.add(new AttackAction(this, direction));
    }

    // Attack with weapon
    private void attackWithWeapon(Actor otherActor, String direction, ActionList actions) {
        for (WeaponItem weaponItem: otherActor.getWeaponInventory()){
            actions.add(new AttackAction(this, direction, weaponItem));
            actions.add(weaponItem.getSkill(this, direction));
        }
    }

    /**
     * Add Status.WIPED to this actor.
     */
    @Override
    public void reset() {
        this.addCapability(Status.WIPED);
    }

    /**
     * Get the min runes that dropped when get killed by player
     */
    public abstract int getMinRunes();

    /**
     * Get the max runes that dropped when get killed by player
     */
    public abstract int getMaxRunes();
}
