package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.HeavySkeletalSwordsman;
import game.actors.enemies.LoneWolf;

/**
 * A class that represents Grave Yard.
 * Created by:
 * @author Chee Hao Wong
 * Modified by:
 *
 */
public class Graveyard extends Ground {
    /**
     * Constructor.
     */
    public Graveyard() {
        super('n');
    }

    /**
     * 27% of spawning Heavy Skeletal Swordsman if there is no actor at the location of the ground.
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        if(Math.random() <= 0.27 && !location.containsAnActor()){
            location.addActor(new HeavySkeletalSwordsman());
        }
    }
}
