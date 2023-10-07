package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.LoneWolf;

/**
 * A class that represents Gust of Wind.
 * Created by:
 * @author Chee Hao Wong
 * Modified by:
 *
 */
public class GustOfWind extends Ground {
    /**
     * Constructor.
     */
    public GustOfWind() {
        super('&');
    }

    /**
     * 33% of spawning Lone Wolf if there is no actor at the location of the ground.
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        if(Math.random() <= 0.33 && !location.containsAnActor()){
            location.addActor(new LoneWolf());
        }
    }
}
