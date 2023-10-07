package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.DeathAction;
import game.enums.Status;

public class WaterVortex extends Ground {
    /**
     * writer: Steve Rahardjo
     * @param displayChar character to display for this type of terrain
     */
    public WaterVortex() {
        super('V');
    }
    /**
     * player dies if stand on Cliff
     *
     * @param location The location of the Ground
     *this method add a DeathAction(actor died) when it is inside Water Vortex
     */
    @Override
    public void tick(Location location) {
        if (location.containsAnActor()) {
            if (location.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)) {
                new DeathAction(location.getActor()).execute(location.getActor(), location.map());
            }
        }
    }

}