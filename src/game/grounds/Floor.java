package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.enums.Status;

/**
 * A class that represents the floor inside a building.
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by: Chee Hao Wong
 *
 */
public class Floor extends Ground {
	/**
	 * Constructor.
	 */
	public Floor() {
		super('_');
	}

	/**
	 * Only passable if the actor is player.
	 *
	 * @param actor the Actor to check
	 * @return true if the actor is player.
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return actor.hasCapability(Status.PLAYER);
	}
}
