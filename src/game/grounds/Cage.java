package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.Dog;
import game.actors.enemies.HeavySkeletalSwordsman;

/**
 * A class that represents Cage.
 * Created by:
 * @author Chee Hao Wong
 *
 */
public class Cage extends Ground {
    /**
     * Constructor.
     */
    public Cage() {
        super('<');
    }

    /**
     * 37% of spawning Dog if there is no actor at the location of the ground.
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        if(Math.random() <= 0.37 && !location.containsAnActor()){
            location.addActor(new Dog());
        }
    }
}
