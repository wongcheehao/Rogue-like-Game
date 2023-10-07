package game.grounds;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Ground;
import game.actors.players.Player;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Status;
import edu.monash.fit2099.engine.actors.Actor;

/**
 * writer: Steve Rahardjo
 */
public class Acid extends Ground {
    Actor actor;

    public Acid() {
        super('r');
    }

    public void tick(Location location) {
        actor=location.getActor();
        if (location.containsAnActor()) {
            actor.hurt(50);
            actor.addCapability(Status.ROTTING);
        }
    }
}
