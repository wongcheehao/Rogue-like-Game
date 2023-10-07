package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.enums.Status;
import game.actions.*;
import game.Items.Purchasable;
import game.Items.Sellable;
//import game.actions.QuickStepAction;

/**
 * A simple weapon that has a special attack 'quick step'
 * It deals 75 damage with 70% hit rate
 * Created by:
 * @author Park Jun Koo
 */
public class GreatKnife extends WeaponItem implements Sellable, Purchasable {
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
    public GreatKnife() {
        super("GreatKnife", '/', 75, "attacks", 70);
        this.purchasingPrice = 3500;
        this.sellingPrice = 350;
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
     * Get the selling price of the item
     *
     * @return selling price of the item.
     */
    @Override
    public int getSellPrice() {
        return sellingPrice;
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
     * Add sell action of this item to AllowableActions if there is a actor with Status TRADING nearby.
     *
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor           The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        for (Exit exit : currentLocation.getExits()) {
            Location destination = exit.getDestination();

            if (destination.getActor() != null && destination.getActor().hasCapability(Status.TRADING)) {
                this.addAction(this.getSellAction(actor));
                break;
            }
            for (Action action : this.getAllowableActions()) {
                this.removeAction(action);
                break;
            }
        }
    }

    /**
     * Get an active skill action from the weapon.
     * @param target target actor
     * @return QuickStepAction
     * @see QuickStepAction
     */
    @Override
    public Action getSkill(Actor target,String direction) {
        return (new QuickStepAction(target,direction,this));
    }
}
