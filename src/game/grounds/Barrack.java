package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.Dog;
import game.actors.enemies.GodrickSoldier;

/**
 * A class that represents Barrack.
 * Created by:
 * @author Chee Hao Wong
 *
 */
public class Barrack extends Ground {
    /**
     * Constructor.
     */
    public Barrack() {
        super('B');
    }

    /**
     * 45% of spawning Godrick Soldier if there is no actor at the location of the ground.
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        if(Math.random() <= 0.45 && !location.containsAnActor()){
            location.addActor(new GodrickSoldier());
        }
    }
}
