package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Items.ConsumableItem;

/**
 * An Action to consume an consumable item.
 * Created by:
 * @author Chee Hao Wong
 * Modified by: Park Jun Koo
 *
 */
public class ConsumeAction extends Action {

    /**
     * The item that is to be consumed.
     */
    private final ConsumableItem item;

    /**
     * Constructor.
     *
     * @param item  is to be consumed.
     */
    public ConsumeAction(ConsumableItem item) {
        this.item = item;
    }

    /**
     * When executed, check if item's quantity still valid. If so execute the consume() method in the item class,
     * and minus the quantity of the item (Basic item).
     *
     * @return the result of the consume, e.g. whether actor successfully consume the item.
     * @see ConsumableItem
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";

        if (item.isBasicItem()) {
            item.consume(actor);
            item.minusQuantity();

            result += actor + " consumes " + item + ".";

            if (item.getQuantity() == 0){
                return "Out of " + item;
            }
            return result;
        }

        item.consume(actor);
        actor.removeItemFromInventory(item);

        result += actor + " consumes " + item + ".";

        return result;
    }

    /**
     * Describe which item the actor consumes with the quantity of item (basic item).
     * Describe which item the actor consumes
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        if (item.isBasicItem()) {
            return actor + " consumes " + item + "(" + item.getQuantity() + "/" + item.getMaxQuantity() + ")";
        }
        return actor + " consumes " + item;
    }
}

