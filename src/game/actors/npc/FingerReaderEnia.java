package game.actors.npc;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.enums.Status;

/**
 * A trader allows player to exchange item to the specific weapon (Boss Weapons)
 * Players can sell any items to this trader
 * Created by:
 * @author Park Jun Koo
 */
public class FingerReaderEnia extends Actor {
    /**
     * Constructor of FingerReaderEnia class
     */
    public FingerReaderEnia() {
        super("Finger Reader Enia", 'E', 999999999);
        this.addCapability(Status.TRADING);
        this.addCapability(Status.BOSS_EXCHANGE);
    }

    /**
     * At each turn, select a valid action to perform.
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }
}
