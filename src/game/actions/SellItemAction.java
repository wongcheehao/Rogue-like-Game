package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Items.RuneManager;
import game.Items.Sellable;

/**
 * An Action to sell an item.
 * Created by:
 * @author Park Jun Koo
 *
 */
public class SellItemAction extends Action {

    /**
     * The item that is to be sold
     */
    private final Item item;

    private final int price;
    /**
     * Constructor.
     *
     * @param item the item that is to be sold.
     */
    public SellItemAction(Item item, int price) {
        this.item = item;
        this.price = price;
    }

    /**
     * When executed, remove item from actor's inventory.
     *
     * @param actor The actor performing the sell action.
     * @param map The map the actor is on.
     * @return the result of the sold, e.g. whether the item is sold successfully, etc.
     *
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        RuneManager.getInstance().gainRune(price);
        actor.removeItemFromInventory(item);
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " sells " + item + " at " + price + " runes.";
    }
}
