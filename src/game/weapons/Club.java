package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.enums.Status;
import game.actions.BuyWeaponAction;
import game.actions.SellWeaponAction;
import game.Items.Purchasable;
import game.Items.Sellable;

/**
 * A simple weapon that can be used to attack the enemy.
 * It deals 103 damage with 80% hit rate
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Park Jun Koo
 *
 */
public class Club extends WeaponItem implements Sellable, Purchasable {
    /**
     * Selling price of the item
     */
    private final int sellingPrice;

    /**
     * Purchasing price of the item
     */
    private final int purchasingPrice;

    /**
     * Constructor, fixed purchasing price and selling price for now.
     */
    public Club() {
        super("Club", '!', 103, "bonks", 80);
        this.purchasingPrice = 600;
        this.sellingPrice = 100;
    }

    /**
     * Get the purchasing price of the item
     *
     * @return purchasing price of the item.
     */
    @Override
    public int getPurchasePrice() {
        return purchasingPrice;
    }

    /**
     * Get the purchase action of the item
     *
     * @return purchase action of the item, e.g. buyItemAction, buyWeaponAction.
     */
    @Override
    public BuyWeaponAction getPurchaseAction(Actor actor) {
        return new BuyWeaponAction(this,purchasingPrice);
    }

    /**
     * Get the selling action of the item
     *
     * @return selling action of the item, e.g. sellItemAction, sellWeaponAction.
     */
    @Override
    public SellWeaponAction getSellAction(Actor actor) {
        return new SellWeaponAction(this,sellingPrice);
    }

    /**
     * Get the selling price of the item
     *
     * @return selling price of the item.
     */
    @Override
    public int getSellPrice() {
        return sellingPrice;
    }

    /**
     * Add sell action of this item to AllowableActions if there is a actor with Status TRADING nearby.
     *
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor           The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        for (Exit exit : currentLocation.getExits()) {
            Location destination = exit.getDestination();

            // When trader is within the surrounding
            if (destination.getActor() != null && destination.getActor().hasCapability(Status.TRADING)) {
                this.addAction(this.getSellAction(actor));
                break;
            }
            for (Action action : this.getAllowableActions()){
                this.removeAction(action);
                break;
            }
        }
    }
}