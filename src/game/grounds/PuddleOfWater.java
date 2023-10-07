package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.GiantCrab;
import game.actors.enemies.HeavySkeletalSwordsman;
import game.actors.enemies.LoneWolf;

/**
 * A class that represents Puddle of Water.
 * Created by:
 * @author Chee Hao Wong
 * Modified by:
 *
 */
public class PuddleOfWater extends Ground {
    /**
     * Constructor.
     */
    public PuddleOfWater() {
        super('~');
    }

    /**
     * 2% of spawning Giant Crab if there is no actor at the location of the ground.
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        if(Math.random() <= 0.02 && !location.containsAnActor()){
            location.addActor(new GiantCrab());
        }
    }
}
