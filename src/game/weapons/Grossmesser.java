package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.enums.Status;
import game.actions.SellWeaponAction;
import game.actions.AreaAttackAction;
import game.Items.Sellable;

/**
 * A simple weapon that has a special attack 'spinning attack'
 * It deals 115 damage with 85% hit rate
 * Created by:
 * Park Jun koo
 * Modified by: Park Jun Koo, Chee Hao Wong
 */
public class Grossmesser extends WeaponItem implements Sellable {
    /**
     * Selling price of the item
     */
    private final int sellingPrice;

    /**
     * Constructor, fixed selling price for now.
     */
    public Grossmesser() {
        super("Grossmesser", '?', 115, "gross", 85);
        this.sellingPrice = 100;

    }

    /**
     * Special skill of Grossmesser - SlamAttackAction
     *
     * @param target target actor
     * @return SlamAttackAction
     * @see AreaAttackAction
     */
    @Override
    public Action getSkill(Actor target, String direction) {
        return new AreaAttackAction(target, direction, this);
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
            for (Action action : this.getAllowableActions()){
                this.removeAction(action);
                break;
            }

        }
    }
}