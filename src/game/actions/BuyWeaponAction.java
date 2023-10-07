package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Items.RuneManager;
import game.Items.Purchasable;

/**
 * An Action to buy a weapon
 * Created by:
 * @author Park Jun Koo
 *
 */
public class BuyWeaponAction extends Action {

    int price;
    /**
     * The weapon that is to be purchased
     */
    private final WeaponItem weaponItem;

    /**
     * Constructor.
     *
     * @param item the weapon that is to be purchased
     */
    public BuyWeaponAction(WeaponItem weaponItem, int price) {
        this.weaponItem = weaponItem;
        this.price = price;
    }

    /**
     * When executed, check rune amount, add weapon into actor's inventory.
     *
     * @param actor The actor performing the buy action.
     * @param map   The map the actor is on.
     * @return the result of the purchase, e.g. whether the weapon is purchased successfully, etc.
     */
    public String execute(Actor actor, GameMap map) {
        int balance = RuneManager.getInstance().getRuneAmount();

        if (price > balance) {
            return actor + " does not have enough runes to buy " + weaponItem;
        }

        RuneManager.getInstance().spendRune(price);
        actor.addWeaponToInventory(weaponItem);
        return menuDescription(actor);

    }

    @Override
    public String menuDescription(Actor actor) {
        return (actor + " buys " + weaponItem + " at " + price + " runes.");
    }
}
