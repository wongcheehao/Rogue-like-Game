package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Items.RuneManager;

/**
 * An Action to sell a weapon.
 * Created by:
 * @author Park Jun Koo
 *
 */
public class SellWeaponAction extends Action {
    int price;
    /**
     * The item that is to be sold
     */
    private final WeaponItem weaponItem;

    /**
     * Constructor.
     *
     * @param item the weapon that is to be sold.
     */
    public SellWeaponAction(WeaponItem weaponItem, int price) {
        this.weaponItem = weaponItem;
        this.price = price;
    }

    /**
     * When executed, remove weapon from actor's inventory.
     *
     * @param actor The actor performing the sell action.
     * @param map The map the actor is on.
     * @return the result of the sold, e.g. whether the weapon is sold successfully, etc.
     * @see SellAction
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        RuneManager.getInstance().gainRune(price);
        actor.removeWeaponFromInventory(weaponItem);
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " sells " + weaponItem + " at " + price + " runes.";
    }
}
