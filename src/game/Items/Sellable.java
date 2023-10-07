package game.Items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;

/**
 * Interface for items that are sellable in the game.
 * Created by:
 * @author Park Jun Koo
 */
public interface Sellable {

    /**
     * Get the selling price of the item
     *
     * @return selling price of the item.
     */
    int getSellPrice();

    /**
     * Get the selling action of the item
     *
     * @return selling action of the item, e.g. sellItemAction, sellWeaponAction.
     */
    public Action getSellAction(Actor actor);
}
