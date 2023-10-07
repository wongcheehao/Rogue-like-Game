package game.Items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.Items.ConsumableItem;
import game.resets.ResetManager;
import game.resets.Resettable;
import game.actions.ConsumeAction;


/**
 * Basic(provided at the start of the game) consumable item
 * Created by:
 * @author Park Jun Koo
 *
 */
public class FlaskOfCrimsonTears extends ConsumableItem implements Resettable {


    /***
     * max quantity of this item
     */
    private final int MAX_QUANTITY = 2;

    /***
     * quantity of this item
     */
    private int quantity = MAX_QUANTITY;

    /***
     * Constructor. Add ConsumeAction to AllowableActions.
     */
    public FlaskOfCrimsonTears() {
        super("Flask Of Crimson Tears",'c' , false);
        ResetManager.getInstance().registerResettable(this);
    }

    /**
     * Checks for location of this item and the actor
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    public void tick(Location currentLocation, Actor actor){
        super.tick(currentLocation,actor);
    }



    /**
     * Actor get heal 250
     *
     * @param actor actor to consume the item.
     */
    public void consume(Actor actor) {
        actor.heal(250);
    }


    /**
     * Get the current quantity of item.
     *
     * @return the quantity of item.
     */
    @Override
    public int getQuantity() {
       return quantity;
    }

    /**
     * Minus the quantity of item.
     *
     */
    @Override
    public void minusQuantity() {
        if (quantity > 0){
            quantity -= 1;
        }
    }

    /**
     * Check whether the item is provided at the start of the game
     * @return true since this item is provided at the start of the game
     */
    @Override
    public boolean isBasicItem() {
        return super.isBasicItem();
    }

    /**
     * Get the maximum quantity of this item
     * @return maximum quantity of this item
     */
    @Override
    public int getMaxQuantity() {
        return MAX_QUANTITY;
    }

    /**
     * Flash of Crimson Tears get reset to max quantity.
     */
    @Override
    public void reset() {
        this.quantity = MAX_QUANTITY;
    }
}
