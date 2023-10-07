package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.DeathAction;
import game.enums.Status;

/**
 * A class that represents Cliff.
 * Created by:
 * @author Chee Hao Wong
 *
 */
public class Cliff extends Ground {
    /**
     * Constructor.
     */
    public Cliff() {
        super('+');
    }

    /**
     * player dies if stand on Cliff
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        if (location.containsAnActor()){
            if(location.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)){
                new DeathAction(location.getActor()).execute(location.getActor(), location.map());
            }
        }
    }
}
