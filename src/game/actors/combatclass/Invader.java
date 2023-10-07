package game.actors.combatclass;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.enemies.Enemy;
import game.behaviours.Behaviour;
import game.enums.EnemyType;
import game.enums.Status;
import game.resets.ResetManager;

/**
 * An actor which helps enemies to attack players and his allies
 * Created by:
 * @author Park Jun Koo
 */
public class Invader extends Enemy {

    /**
     * Maximum amount of runes to be dropped
     */
    private final static int MAX_RUNE_AMOUNT = 5578;

    /**
     * Minimum amount of runes to be dropped
     */
    private final static int MIN_RUNE_AMOUNT = 1358;
    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Invader() {
        super("Invader", 'ඞ', 1);
        this.addCapability(Status.COMBAT_CLASS);
        this.addCapability(EnemyType.INVADER);
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
     * Get minimum amount of runes
     * @return minimum amount of runes
     */
    @Override
    public int getMinRunes() {
        return MIN_RUNE_AMOUNT;
    }

    /**
     * Get maximum amount of runes
     * @return maximum amount of runes
     */
    @Override
    public int getMaxRunes() {
        return MAX_RUNE_AMOUNT;
    }


}
