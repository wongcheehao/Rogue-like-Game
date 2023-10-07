package game.Items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;

/**
 * Interface for items that are purchasable in the game.
 * Created by:
 * @author Park Jun Koo
 */
public interface Purchasable {

    /**
     * Get the purchasing price of the item
     *
     * @return purchasing price of the item.
     */
    public int getPurchasePrice();

    /**
     * Get the purchase action of the item
     *
     * @return purchase action of the item, e.g. buyItemAction, buyWeaponAction.
     */
    public Action getPurchaseAction(Actor actor);
}