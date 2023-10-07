package game.Items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.enums.Status;

/**
 * Define consumable items
 * Created by:
 * @author Park Jun Koo
 */
public abstract class ConsumableItem extends Item {

    protected boolean added = false;
    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public ConsumableItem(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }

    /**
     * Actor consumes the item
     * @param actor
     */
    public abstract void consume(Actor actor);


    /**
     * If player contains the item, let the player consume the item
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    public void tick(Location currentLocation, Actor actor) {

        for (Action action : this.getAllowableActions()){
            this.removeAction(action);
            break;
        }

        if (currentLocation.getActor() != null && currentLocation.getActor().getItemInventory().contains(this)){
            this.addAction(new ConsumeAction(this));
            added = true;
        }


    }

    /**
     * Check whether the item is on the ground
     * If item on the ground remove ConsumeAction
     * @param currentLocation The location of the ground on which we lie.
     * Created by:
     * @author Park Jun Koo
     */
    public void tick(Location currentLocation){

        if (!added) {
            this.addAction(new ConsumeAction(this));
            added = true;
        }

        if (currentLocation.getItems() != null &&  currentLocation.getItems().contains(this)) {
                for (Action action : this.getAllowableActions()){
                    this.removeAction(action);
                    break;
                }
            }

        }


    /**
     * Get max quantity of the item
     * @return max quantity of the item
     */
    public int getMaxQuantity(){return 0;}

    /**
     * Get quantity of the item
     * @return quantity of the item
     */
    public int getQuantity(){return 0;}

    /**
     * Subtract one to the current quantity of the item
     */
    public void minusQuantity(){}

    /**
     * Check whether the item is provided at the start of the game
     * @return true if it is provided
     */
    public boolean isBasicItem(){return true;}
}




