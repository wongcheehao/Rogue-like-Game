package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;

/**
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by: Chee Hao Wong
 *
 */
public class Wall extends Ground {

	/**
	 * Constructor.
	 */
	public Wall() {
		super('#');
	}

	/**
	 * No actor can enter.
	 *
	 * @param actor the Actor to check
	 * @return false
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}

	/**
	 * Wall blocks thrown objects but not movement.
	 * @return true
	 */
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
}
