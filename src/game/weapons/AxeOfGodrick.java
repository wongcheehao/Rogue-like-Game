package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Items.Sellable;
import game.actions.SellWeaponAction;
import game.enums.Status;

public class AxeOfGodrick extends WeaponItem implements Sellable {

    private final int sellingPrice;
    /**
     * Constructor of AxeOfGodrick class
     */
    public AxeOfGodrick() {
        super("Axe of Godrick", 'T', 142, "Axes", 84);
        this.sellingPrice = 100;
    }

    @Override
    public int getSellPrice() {
        return sellingPrice;
    }

    @Override
    public Action getSellAction(Actor actor) {
        return new SellWeaponAction(this,sellingPrice);
    }

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
