package game.Items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.SellItemAction;
import game.actions.WeaponExchangeAction;
import game.enums.Status;
import game.weapons.AxeOfGodrick;
import game.weapons.GraftedDragon;

/**
 * An item which can be obtained from Godrick the Grafted
 * It can be used to change to the weapons from the specific trader
 * Created by:
 * @author Park Jun Koo
 */
public class RemembranceOfTheGrafted extends Item implements Sellable{
    /**
     * Selling price of the item
     */
    private final int sellingPrice;

    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public RemembranceOfTheGrafted() {
        super("Remembrance Of The Grafted", 'O', true);
        this.sellingPrice = 20000;
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
     * @return selling action of the item, SellItemAction
     */
    @Override
    public Action getSellAction(Actor actor) {
        return new SellItemAction(this,sellingPrice);
    }


    /**
     * Add sell action of this item to AllowableActions if there is a actor with Status TRADING nearby
     * Add exchange action of this item to AllowableActions if there is a actor with Status BOSS_EXCHANGE nearby
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        for (Exit exit : currentLocation.getExits()) {
            Location destination = exit.getDestination();

            // When trader is within the surrounding
            if (destination.getActor() != null && destination.getActor().hasCapability(Status.TRADING)) {
                this.addAction(this.getSellAction(actor));
                if (destination.getActor().hasCapability(Status.BOSS_EXCHANGE)){
                    this.addAction(new WeaponExchangeAction(this, new AxeOfGodrick()));
                    this.addAction(new WeaponExchangeAction(this, new GraftedDragon()));
                }
                break;
            }
            for (Action action : this.getAllowableActions()){
                this.removeAction(action);
                break;
            }
        }
    }

}
