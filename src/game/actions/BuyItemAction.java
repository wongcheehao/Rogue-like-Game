package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Items.RuneManager;
import game.Items.Purchasable;

/**
 * An Action to buy an item.
 * Created by:
 * @author Park Jun Koo
 *
 */
public class BuyItemAction extends Action {
    int price;
    /**
     * The item that is to be purchased
     */
    private final Item item;

    /**
     * Constructor.
     *
     * @param item the item that is to be purchased
     */
    public BuyItemAction(Item item) {
        this.item = item;
    }

    /**
     * When executed,  check rune amount, add item into actor's inventory.
     *
     * @param actor The actor performing the buy action.
     * @param map The map the actor is on.
     * @return the result of the purchase, e.g. whether the item is purchased successfully, etc.
     *
     */
    @Override
    public String execute(Actor actor, GameMap map) {

//        if(price > RuneManager.getInstance().getRuneAmount()){
//            return "Not enough runes.";
//        }else{
//            actor.addItemToInventory((Item) item);
            return menuDescription(actor);
        }


    @Override
    public String menuDescription(Actor actor) {
        return actor + " buys " + item + " for " + price + " runes.";
    }
}


