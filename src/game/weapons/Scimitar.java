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
 * A simple weapon that has a special attack 'spinning attack'
 * It deals 118 damage with 80% hit rate
 * Created by:
 * @author Park Jun Koo
 */
public class Scimitar extends WeaponItem implements Sellable, Purchasable {
    private final int sellingPrice;
    private final int purchasingPrice;

    /**
     * Constructor.
     *
     */
    public Scimitar() {
        super("Scimitar", 's', 118, "spins", 88);
        this.purchasingPrice = 600;
        this.sellingPrice = 100;
    }

    @Override
    public int getPurchasePrice() {
        return purchasingPrice;
    }


    @Override
    public BuyWeaponAction getPurchaseAction(Actor actor) {
        return new BuyWeaponAction(this,purchasingPrice);
    }

    @Override
    public int getSellPrice() {
        return sellingPrice;
    }

    @Override
    public SellWeaponAction getSellAction(Actor actor) {
        return new SellWeaponAction(this,sellingPrice);
    }

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

    // Get skill for the weapon
    @Override
    public Action getSkill(Actor target, String direction) {
        return (new SpinningAttackAction(target,direction,this));
    }
}
