package game.actors.npc;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.enums.Status;
import game.Items.Purchasable;
import game.Items.ItemCollection;

import java.util.ArrayList;

/**
 * An actor sells and purchases items from the player
 * Created by:
 * @author Park Jun Koo
 *
 */
public class MerchantKale extends Actor {

    /**
     * An ordered collection storing the behaviours of NPC.
     */
//    protected Map<Integer, Behaviour> behaviours = new TreeMap<>(); // priority, behaviour

    /**
     * An list storing the items the trader is selling.
     */
    ArrayList<Purchasable> sellingItems = new ArrayList<>();

    /**
     * Constructor.
     */
    public MerchantKale(ItemCollection item) {
        super("Merchant Kale", 'K', 999999999);
        this.addCapability(Status.TRADING);
        this.sellingItems.addAll(item.getSellingItems());
    }

    /**
     * At each turn, select a valid action to perform.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the valid action that can be performed in that iteration or null if no valid action is found
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /**
     * The enemy can be attacked by any actor that has the HOSTILE_TO_ENEMY capability
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);

        // Only player can make purchase and sell
        if (otherActor.hasCapability(Status.PLAYER)) {
            for (Purchasable item : sellingItems) {
                actions.add(item.getPurchaseAction(otherActor));
            }
        }
        return actions;
    }
}