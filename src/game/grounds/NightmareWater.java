package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.Ghoul;

public class NightmareWater extends Ground {
    /**
     * writer: Steve Rahardjo
     */
    public NightmareWater() {
        super('^');
    }

    @Override
    public void tick(Location location) {
        if (Math.random() <= 0.30 && !location.containsAnActor()) {
            location.addActor(new Ghoul());
        }
    }
}